import java.util.ArrayList;

public class Bite6Encoder {
    private String currentString;

    private final ArrayList<String> alphaKeyRow1 = new ArrayList<>();
    private final ArrayList<String> alphaKeyRow2 = new ArrayList<>();
    private final ArrayList<String> alphaKeyRow3 = new ArrayList<>();

    private final ArrayList<String> numeralKey = new ArrayList<>();

    Bite6Encoder() {
        System.out.println("Bite6 V2.0");

        // Build Alpha Key row 1
        if (true) {
            alphaKeyRow1.add("a");
            alphaKeyRow1.add("b");
            alphaKeyRow1.add("c");
            alphaKeyRow1.add("d");
            alphaKeyRow1.add("e");
            alphaKeyRow1.add("f");
            alphaKeyRow1.add("g");
            alphaKeyRow1.add("h");
            alphaKeyRow1.add("i");
            alphaKeyRow1.add("j");
            alphaKeyRow1.add("k");
            alphaKeyRow1.add("l");
            alphaKeyRow1.add("m");
            alphaKeyRow1.add("n");
            alphaKeyRow1.add("o");
            alphaKeyRow1.add("p");
            alphaKeyRow1.add("q");
            alphaKeyRow1.add("r");
            alphaKeyRow1.add("s");
            alphaKeyRow1.add("t");
            alphaKeyRow1.add("u");
            alphaKeyRow1.add("v");
            alphaKeyRow1.add("w");
            alphaKeyRow1.add("x");
            alphaKeyRow1.add("y");
            alphaKeyRow1.add("z");
            alphaKeyRow1.add(" ");
            alphaKeyRow1.add("[STX]");
            alphaKeyRow1.add("[ETX]");
            alphaKeyRow1.add("\n");
            alphaKeyRow1.add("[]");
            alphaKeyRow1.add("~");
            alphaKeyRow1.add("|");
            alphaKeyRow1.add("[row1]");
            alphaKeyRow1.add("[row2]");
            alphaKeyRow1.add("[row3]");
        }

        // Build Alpha Key row 2
        if (true) {
            alphaKeyRow2.add("A");
            alphaKeyRow2.add("B");
            alphaKeyRow2.add("C");
            alphaKeyRow2.add("D");
            alphaKeyRow2.add("E");
            alphaKeyRow2.add("F");
            alphaKeyRow2.add("G");
            alphaKeyRow2.add("H");
            alphaKeyRow2.add("I");
            alphaKeyRow2.add("J");
            alphaKeyRow2.add("K");
            alphaKeyRow2.add("L");
            alphaKeyRow2.add("M");
            alphaKeyRow2.add("N");
            alphaKeyRow2.add("O");
            alphaKeyRow2.add("P");
            alphaKeyRow2.add("Q");
            alphaKeyRow2.add("R");
            alphaKeyRow2.add("S");
            alphaKeyRow2.add("T");
            alphaKeyRow2.add("U");
            alphaKeyRow2.add("V");
            alphaKeyRow2.add("W");
            alphaKeyRow2.add("X");
            alphaKeyRow2.add("Y");
            alphaKeyRow2.add("Z");
            alphaKeyRow2.add("?");
            alphaKeyRow2.add("!");
            alphaKeyRow2.add("&");
            alphaKeyRow2.add("\"");
            alphaKeyRow2.add("'");
            alphaKeyRow2.add(".");
            alphaKeyRow2.add(",");
            alphaKeyRow2.add("[row1]");
            alphaKeyRow2.add("[row2]");
            alphaKeyRow2.add("[row3]");
        }

        // Build Alpha Key row 3
        if (true) {
            alphaKeyRow3.add("0");
            alphaKeyRow3.add("1");
            alphaKeyRow3.add("2");
            alphaKeyRow3.add("3");
            alphaKeyRow3.add("4");
            alphaKeyRow3.add("5");
            alphaKeyRow3.add("6");
            alphaKeyRow3.add("7");
            alphaKeyRow3.add("8");
            alphaKeyRow3.add("9");
            alphaKeyRow3.add("(");
            alphaKeyRow3.add("[");
            alphaKeyRow3.add("{");
            alphaKeyRow3.add(")");
            alphaKeyRow3.add("]");
            alphaKeyRow3.add("}");
            alphaKeyRow3.add("$");
            alphaKeyRow3.add("#");
            alphaKeyRow3.add("%");
            alphaKeyRow3.add("@");
            alphaKeyRow3.add("+");
            alphaKeyRow3.add("-");
            alphaKeyRow3.add("*");
            alphaKeyRow3.add("/");
            alphaKeyRow3.add("^");
            alphaKeyRow3.add("<");
            alphaKeyRow3.add("=");
            alphaKeyRow3.add(">");
            alphaKeyRow3.add(":");
            alphaKeyRow3.add(";");
            alphaKeyRow3.add("_");
            alphaKeyRow3.add("`");
            alphaKeyRow3.add("\\");
            alphaKeyRow3.add("[row1]");
            alphaKeyRow3.add("[row2]");
            alphaKeyRow3.add("[row3]");
        }

        // build numeralKey
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                numeralKey.add("" + i + j);
            }
        }
    }

    public void newString(String text) {
        currentString = text;
    }

    //------------------------------------------------------------------------------------------------------------------|||||||||
    //------------------------------------------------------------------------------------------------------------------|||||||||
    //------------------------------------------------------------------------------------------------------------------|||||||||

    public String encode() {
        // creates output, curRow, Adds Bite6 starting character
        String output = "";
        int curRow = 1;
        output = output.concat("43");
        // Encoding loop, Works characters and switches rows
        for (int i = 0; i < currentString.length(); i++) {
            // Check for char in row 1, if not check other rows.
            if (curRow == 1) {
                /* CurRow Keeps track of the row currently in use. Always starts at row 1
                * and if the char was not found in selected row, it will test other rows.*/
                if (alphaKeyRow1.contains("" + currentString.charAt(i))) {
                    output = output.concat(numeralKey.get(alphaKeyRow1.indexOf("" + currentString.charAt(i))));
                } else if (alphaKeyRow2.contains("" + currentString.charAt(i))) {
                    curRow = 2;
                    output = output.concat("54");
                    output = output.concat(numeralKey.get(alphaKeyRow2.indexOf("" + currentString.charAt(i))));
                } else if (alphaKeyRow3.contains("" + currentString.charAt(i))) {
                    curRow = 3;
                    output = output.concat("55");
                    output = output.concat(numeralKey.get(alphaKeyRow3.indexOf("" + currentString.charAt(i))));
                } else {
                    System.out.println("Char '" + currentString.charAt(i) + "' @index-" + i + " Is Not A Defined Char");
                }
            }
            else if (curRow == 2) {
                /* CurRow Keeps track of the row currently in use. Always starts at row 1
                 * and if the char was not found in selected row, it will test other rows.*/
                if (alphaKeyRow2.contains("" + currentString.charAt(i))) {
                    output = output.concat(numeralKey.get(alphaKeyRow2.indexOf("" + currentString.charAt(i))));
                    /*
                    C:\Users\radic\.jdks\openjdk-23.0.1\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2024.3.1.1\lib\idea_rt.jar=52215:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2024.3.1.1\bin" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath "C:\Users\radic\IdeaProjects\Bite6 Encoder\out\production\Bite6 Encoder" Main
                    Bite6 V2.0
                    < encode abcdeft ABCDEFG 1234567 Bite6 2.0 BABY!
                    ENCODE> 43000102030405314254000102030405105342550102030405101153425401531231045510534255025451550053425401000140435344
                    < decode 43000102030405314254000102030405105342550102030405101153425401531231045510534255025451550053425401000140435344
                    DECODE> [STX]abcdeft ABCDEFG 1234567 Bite6 2.0 BABY![ETX]
                    < -6/2/2025 im so high rn
                    */
                } else if (alphaKeyRow3.contains("" + currentString.charAt(i))) {
                    curRow = 3;
                    output = output.concat("55");
                    output = output.concat(numeralKey.get(alphaKeyRow3.indexOf("" + currentString.charAt(i))));
                } else if (alphaKeyRow1.contains("" + currentString.charAt(i))) {
                    curRow = 1;
                    output = output.concat("53");
                    output = output.concat(numeralKey.get(alphaKeyRow1.indexOf("" + currentString.charAt(i))));
                } else {
                    System.out.println("Char '" + currentString.charAt(i) + "' @index-" + i + " Is Not A Defined Char");
                }
            }
            else if (curRow == 3) {
                /* CurRow Keeps track of the row currently in use. Always starts at row 1
                 * and if the char was not found in selected row, it will test other rows.*/
                if (alphaKeyRow3.contains("" + currentString.charAt(i))) {
                    output = output.concat(numeralKey.get(alphaKeyRow3.indexOf("" + currentString.charAt(i))));
                } else if (alphaKeyRow1.contains("" + currentString.charAt(i))) {
                    curRow = 1;
                    output = output.concat("53");
                    output = output.concat(numeralKey.get(alphaKeyRow1.indexOf("" + currentString.charAt(i))));
                } else if (alphaKeyRow2.contains("" + currentString.charAt(i))) {
                    curRow = 2;
                    output = output.concat("54");
                    output = output.concat(numeralKey.get(alphaKeyRow2.indexOf("" + currentString.charAt(i))));
                } else {
                    System.out.println("Char '" + currentString.charAt(i) + "' @index-" + i + " Is Not A Defined Char");
                }
            }
            else {
                System.out.println("Char '" + currentString.charAt(i) + "' @index-" + i + " Was Not Found!"); // todo debug
            }
        }
        // Set cur row back if it was changed
        if (curRow != 1) {
            curRow = 1;
            output = output.concat("53");
        }
        // add closing character and returns output,
        output = output.concat("44");
        return output;
    }

    public String decode() {
        String output = "";
        int curRow = 1;
        for (int i = 0; i < currentString.length(); i++) {
            if (("" + currentString.charAt(i) + currentString.charAt(i + 1)).equals("43")) {
                for (int j = i+2; j < currentString.length(); j = j + 2) {
                    String ch = "" + currentString.charAt(j) + currentString.charAt(j + 1);
                    if (numeralKey.contains(ch)) {
                        if (ch.equals("53")) {
                            curRow = 1;
                        }
                        else if (ch.equals("54")) {
                            curRow = 2;
                        }
                        else if (ch.equals("55")) {
                            curRow = 3;
                        }
                        else if (ch.equals("44") && (curRow == 1)) {
                            j = currentString.length();
                            i = currentString.length();
                        }
                        else {
                            if (curRow == 1) {
                                output = output.concat(alphaKeyRow1.get(numeralKey.indexOf(ch)));
                            } else if (curRow == 2) {
                                output = output.concat(alphaKeyRow2.get(numeralKey.indexOf(ch)));
                            } else if (curRow == 3) {
                                output = output.concat(alphaKeyRow3.get(numeralKey.indexOf(ch)));
                            }
                        }
                    }
                    else {
                        System.out.println("Unidentified Bite6 '" + ch + "'");
                    }
                }
            }
        }
        return output;
    }
}
