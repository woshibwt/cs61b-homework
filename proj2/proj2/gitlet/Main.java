package gitlet;

import static gitlet.MyUtils.exit;
/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        // DONE: what if args is empty?
        if (args.length == 0) {
            exit("Please enter a command");
        }
        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                // Done: handle the `init` command
                validateNumArgs(args, 1);
                Repository.init();
                break;
            case "add":
                // DONE: handle the `add [filename]` command
                Repository.checkWorkingDir();
                validateNumArgs(args, 2);
                String fileName = args[1];
                new Repository().add(fileName);
                break;
            // TODO: FILL THE REST IN
        }
    }

    private static void validateNumArgs(String[] args, int n) {
        if (args.length != n) {
            exit("Incorrect operands");
        }
    }
}


