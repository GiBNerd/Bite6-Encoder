import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Logger {
    // Initializer to set location for easy debugging
    private final String tag;

    Logger(String location) {
        this.tag = location;
    }

    private final ArrayList<String> register = new ArrayList<>();

    // METHODS FOR ADDING DATA /////////////////////////////////////////////////////////////////////////////////////////\\
    // For adding basic text
    public void add(String input) {
        this.register.add(input);
    }
    public void addTAG (String input) {
        this.register.add(tag + "> " + input);
    }
    public void addCUST (String input, String flag) {this.register.add(flag + "> " + input);}

    // import log
    public void importLog(ArrayList<String> log) {
        for (int i = 0; i < log.indexOf(log.getLast()) +1; i++) {
            this.register.add(log.get(i));
        }
    }

    // METHODS FOR RETRIEVING DATA /////////////////////////////////////////////////////////////////////////////////////\\
    // For returning feedback info
    public String next() {
        try {
            return this.register.removeFirst();
        }
        catch (NoSuchElementException e) {
            return "--";
        }
    }

    public String newest() {
        try {
            return this.register.getLast();
        }
        catch (NoSuchElementException e) {
            return "--";
        }
    }

    public ArrayList<String> exportLog() {
        //ArrayList<String> regClone = this.register;
        //this.register.clear();
        return this.register; // todo Register Bug does not erease after export makes it repeat in encoder
    }

    // METHODS FOR GETTING INFO ABOUT THE DATA /////////////////////////////////////////////////////////////////////////\\
    public int length() {
        return register.indexOf(register.getLast());
    }

    public boolean hasNext() {
        return !register.isEmpty();
    }

    public double getVersion() {
        // Permanent Info About Code
        return 1.3;
    }
}
