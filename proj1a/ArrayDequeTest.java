public class ArrayDequeTest {
    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /**
     * Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     * <p>
     * && is the "and" operation.
     */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");

        ArrayDeque<Character> lld1 = new ArrayDeque();

        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addLast('a');

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, lld1.size()) && passed;
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.addLast('b');
        passed = checkSize(2, lld1.size()) && passed;

        lld1.addFirst('c');
        passed = checkSize(3, lld1.size()) && passed;

        lld1.addLast('d');
        passed = checkSize(4, lld1.size()) && passed;

        lld1.addLast('e');
        passed = checkSize(5, lld1.size()) && passed;

        lld1.addFirst('f');
        passed = checkSize(6, lld1.size()) && passed;

        lld1.addFirst('g');
        passed = checkSize(7, lld1.size()) && passed;

        lld1.addFirst('h');
        passed = checkSize(8, lld1.size()) && passed;

        lld1.addFirst('i');
        passed = checkSize(9, lld1.size()) && passed;

        System.out.println("Printing out deque: ");
        lld1.removeFirst();
        lld1.removeLast();
        passed = checkSize(7, lld1.size()) && passed;


        printTestStatus(passed);

    }

    /**
     * Adds an item, then removes an item, and ensures that dll is empty afterwards.
     */
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        ArrayDeque<Character> lld1 = new ArrayDeque();
        // should be empty
        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addLast('a');

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, lld1.size()) && passed;
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.addLast('b');
        passed = checkSize(2, lld1.size()) && passed;

        lld1.addFirst('c');
        passed = checkSize(3, lld1.size()) && passed;

        lld1.addLast('d');
        passed = checkSize(4, lld1.size()) && passed;

        lld1.addLast('e');
        passed = checkSize(5, lld1.size()) && passed;

        lld1.addFirst('f');
        passed = checkSize(6, lld1.size()) && passed;

        lld1.removeFirst();
        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(5, lld1.size()) && passed;
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.removeLast();
        passed = checkSize(4, lld1.size()) && passed;

        lld1.removeLast();
        passed = checkSize(3, lld1.size()) && passed;

        lld1.removeFirst();
        passed = checkSize(2, lld1.size()) && passed;

        lld1.removeLast();
        passed = checkSize(1, lld1.size()) && passed;

        lld1.removeLast();
        passed = checkSize(0, lld1.size()) && passed;

        passed = checkEmpty(true, lld1.isEmpty()) && passed;

        System.out.println("Printing out deque: ");
        lld1.printDeque();

        printTestStatus(passed);

    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addRemoveTest();
    }
}