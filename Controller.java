import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Controller {
    // Permanent Info About Code
    private final double version = 2.3;

    // Statistical Info
    private int Status = 0;

    // Prepare Utilities
    private final Scanner scanner = new Scanner(System.in);
    private final Logger feedback = new Logger("CON");
    private String path = "";

    // method to receive command, and process it ///////////////////////////////////////////////////////////////////////\\
    public String run(String path) {
        this.path = path;
        return run();
    }
    public String run() {
        boolean runLoop = true;
        while (runLoop) {
            // To receive rawCommand and process it
            getCommand();

            // If user calls 'sys' the command line will be internalized and separate from output

            // Changes where the command is sent if getHead is 'sys'
            if (isHead("sys")) {
                systemCommand();
            } else {runLoop = false;}
        }

        return exportBody();
    }

    // Method that retrieves next command and puts it in CommandHandler
    private void getCommand() {
        System.out.print(this.path + "< ");
        this.path = "";
        newBodyRaw(scanner.nextLine());
    }

    // Method For System Commands //////////////////////////////////////////////////////////////////////////////////////\\
    private void systemCommand() {
        // Boolean allows system commands to loop independently of main.
        boolean sysLoop = true;

        while (sysLoop) {
            // todo make 'sys' password lock
            // Sets path to sys
            this.path = "sys";

            // if there was no command added to 'sys' get command
            if (!hasNext()) {
                getCommand();
            } else {
                next();
                setCmdPicked(false);
            }

            // Command Prints help text
            if (isHead("help")) {
                helpCommand();
            }
            // Command to return stats about the program
            if (isHead("stats")) {
                feedback.add("Versions:");
                feedback.add("Controller " + version);
                feedback.add("Logger " + feedback.getVersion());
            }
            // Command to close loop and return to program
            if (isHead(">")) {
                sysLoop = false;
                feedback.addTAG("Closing System Query");
            }

            // If the command was not picked
            if (!isCmdPicked()) {
                feedback.addTAG("'" + getHead() + "' Is Not An Internal Command");
            }

            // Print Feedback Form Last Command
            while (feedback.hasNext()) {
                System.out.println(feedback.next());
            }
        }
        this.path = "";
    }

    // Help Command ////////////////////////////////////////////////////////////////////////////////////////////////////\\
    private void helpCommand() {
        feedback.add("Type Commands Onto The Screen");
        feedback.add("Press Enter To Send A Command");
        feedback.add("--");
        feedback.add("Send An Unfinished Command To View");
        feedback.add("It's Parameters");
        feedback.add("--");
        feedback.add("System Commands:");
        feedback.add("help");
        feedback.add("stats");
        feedback.add(">");
        feedback.addTAG("Help Text Printed");
    }

    //------------------------------------------------------------------------------------------------------------------|||||||||
    //------------------------------------------------------------------------------------------------------------------|||||||||
    //------------------------------------------------------------------------------------------------------------------|||||||||
    // CommandHandler Methods //////////////////////////////////////////////////////////////////////////////////////////\\
    // Body is where the entire command is stored, It does not change until a newBodyRaw or newBody
    private String rawBody;
    private ArrayList<String> body;
    // Head points to the currently selected word in the command
    private int head;
    // cmdPicked is a boolean value that states if the command was recognized
    private boolean cmdPicked;

    // METHODS FOR CHANGING BODY ///////////////////////////////////////////////////////////////////////////////////////\\
    // This allows a new input from a raw scanner.nextLine()
    private void newBodyRaw(String input) {
        // First, set caps and split input
        rawBody = input;
        //input = input.toLowerCase();
        String[] arrayCommand = input.split(" ");
        // Convert Command Into Array List OF Each Key Word
        body = new ArrayList<>();
        body.addAll(Arrays.stream(arrayCommand).toList());

        // Check for any empty spaces in command
        for (int i = body.size() - 1; i >= 0; i--) {
            if (body.get(i).isBlank()) {
                body.remove(i);
            }
        }

        // Some small steps to reset counters
        head = 0;
        cmdPicked = false;
    }

    // Allows appending more body to body
    private void bodyAdd(String input) {
        rawBody = rawBody + " " + input;
        newBodyRaw(rawBody);
    }

    // METHODS FOR GETTING BODY DATA ///////////////////////////////////////////////////////////////////////////////////\\

    public String getHead() {
        return body.get(head).toLowerCase();
    }

    public String next() {
        // Method to move getHead to next item of body
        if (hasNext()) {
            head += 1;
            // temporary fix for cmd picked getting reset
            //cmdPicked = false;
            return getHead();
        }
        else {return null;}
    }

    public String nextString() {
        String output = "";
        if (hasNext()) {
            head += 1;
            for (int i = head; i < body.size(); i++) {
                if (i == head) {
                    output = output.concat(body.get(i));
                }
                else {
                    output = output.concat(" " + body.get(i));
                }
            }
            return output;
        } else {
            return null;
        }

    }

    public String prev() {
        // method to move head to previous position
        if (head > 0) {
            head -= 1;
            cmdPicked = false;
            return getHead();
        }
        else {return null;}
    }

    private String exportBody() {
        // Method to extract body
        return rawBody;
    }

    // Return info about command ///////////////////////////////////////////////////////////////////////////////////////\\

    public boolean isHead(String input) {
        // Returns true or false if head is Input, Sets cmdPicked
        if (!cmdPicked) {
            this.cmdPicked = getHead().equals(input);
        }
        return getHead().equals(input);
    }

    public boolean hasNext() {
        // Returns True if there is another command word.
        // Returns False if there is no more words in the command after getHead.
        return body.size()-1 > head;
    }

    // Setter and getter for cmd picked
    public boolean isCmdPicked() {return cmdPicked;}
    public void setCmdPicked(boolean input) {cmdPicked = input;}
}