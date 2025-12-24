import java.util.ArrayList;

public class Main {
    // prepare external objects
    private static final Logger feedback = new Logger("MAIN");
    private static final Controller control = new Controller();

    private static Bite6Encoder coder = new Bite6Encoder();

    public static void main(String[] args) {

        while (true) {
            // fetch next command
            control.run();

            // check first command for encode or decode
            if (control.isHead("encode")) {
                if (control.hasNext()) {
                    coder.newString(control.nextString());
                    feedback.addCUST(coder.encode(), "ENCODE");

                } else {feedback.addTAG("encoder missing argument!");}
            }
            if (control.isHead("decode")) {
                if (control.hasNext()) {
                    coder.newString(control.nextString());
                    feedback.addCUST(coder.decode(), "DECODE");
                } else {feedback.addTAG("decoder missing argument!");}
            }

            // If the command was not recognised
            if (!control.isCmdPicked()) {
                feedback.addTAG("'" + control.getHead() + "' Is Not Valid!");
            }

            // Print Feedback Form Last Command
            while (feedback.hasNext()) {
                System.out.println(feedback.next());
            }
        }
    }
}