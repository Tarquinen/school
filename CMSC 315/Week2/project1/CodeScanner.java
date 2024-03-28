/* 
 * Daniel Smolsky
 * Programming Project 1 - Matching Delimiters
 * March 22, 2024
 * This class is designed to scan through a given file character by character, 
 * keeping track of the line and character number. It supports skipping over 
 * non-significant characters such as spaces, character literals, and comments, 
 * to facilitate parsing tasks such as checking for matching delimiters in a Java source file.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

    public class CodeScanner {
    private Scanner file;
    private int charNum = 0;
    private int lineNum = 0;
    private String currLine = null;
    private boolean withinChar = false;
    private boolean withinString = false;
    private boolean withinMultiLineComment = false;
    private boolean print = false; 
    
    public CodeScanner(String fileName) throws FileNotFoundException {
        file = new Scanner(new File(fileName));
    }

    public void close() {
        if (file != null) {
            file.close();
        }
    }

    // Enable printing of characters as they are read
    public void print() {
        print = true;
    }

    // Return the current line number and character number
    public String cursorLocation() {
        return "line number: " + lineNum + " character number: " + charNum;
    }

    // Return the next character in the file
    private char nextChar() {
        //continue to next line when reached end of line
        if (currLine == null || charNum == currLine.length()) {
            if (file.hasNextLine()) {
                lineNum++;
                currLine = file.nextLine();
                charNum = 0;
                if (print)
                    System.out.println();

                //recursive call when blank rows are encountered
                if (currLine.isEmpty()) {
                    return nextChar();
                }
            }
            else {
                //return null character
                return '\0';
            }
        }
        return currLine.charAt(charNum++);
    }

    // Return the next character that is not a space, comment, string, or character literal
    public char nextSignificantChar() {
        char currChar;
        char prevChar;
        char prevChar2;
        boolean prevEscChar;
        do {
            currChar = nextChar();

            // check for escape character prior to current character
            prevChar = (charNum > 1) ? prevChar = currLine.charAt(charNum - 2) : '\0';
            prevChar2 = (charNum > 2) ? prevChar2= currLine.charAt(charNum - 3) : '\0'; 
            prevEscChar = prevChar == '\\' && prevChar2 != '\\';

            // Skip character literals
            if (currChar == '\'' && !withinChar && !prevEscChar) {
                withinChar = true;
                continue;
            } else if (currChar == '\'' && withinChar && !prevEscChar) {
                withinChar = false;
                currChar = nextChar();
                continue;
            }
            
            // Skip string literals
            if (currChar == '\"' && !withinString && !prevEscChar) {
                withinString = true;
                continue;
            } else if (currChar == '\"' && withinString && !prevEscChar) {
                withinString = false;
                currChar = nextChar();
                continue;
            }
    
            // Skip multi-line comments
            if (currChar == '/' && !withinMultiLineComment) {
                if (charNum < currLine.length() && currLine.charAt(charNum) == '*') {
                    withinMultiLineComment = true;
                    continue;
                }
            } else if (currChar == '*' && withinMultiLineComment) {
                if (charNum < currLine.length() && currLine.charAt(charNum) == '/') {
                    withinMultiLineComment = false;
                    nextChar();
                    currChar = nextChar(); // Skip the '/' in "*/"
                    continue;
                }
            }
    
            // Skip single-line comments
            if (currChar == '/' && !withinChar && !withinString) {
                if (charNum < currLine.length() && currLine.charAt(charNum) == '/') {
                    // Move to the next line
                    if (file.hasNextLine()) {
                        lineNum++;
                        currLine = file.nextLine();
                        charNum = 0;
                        if (print)
                            System.out.println();
                    }
                    return nextSignificantChar();
                }
            }
        } while (withinString || withinChar || withinMultiLineComment || currChar == ' ');
        
        if (print)
            System.out.print(currChar);

        return currChar;
    }
}