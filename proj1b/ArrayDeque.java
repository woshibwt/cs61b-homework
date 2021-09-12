public class ArrayDeque<T> implements Deque<T> {
    private static final int RFACTOR = 2;
    private static final int START_SIZE = 8;
    private T[] items;
    private int size;
    private int firstIndex;
    private int lastIndex;

    /**
     * creates an empty array deque
     */
    public ArrayDeque() {
        items = (T[]) new Object[START_SIZE];
        firstIndex = 0;
        lastIndex = 0;
        size = 0;
    }

    /**
     * Adds an item if type T to the front of the deque
     */
    @Override
    public void addFirst(T item) {
        if (size == 0) {
            firstIndex = 0;
            lastIndex = 0;
            items[0] = item;
            size++;
            return;
        }
        if (size == items.length) {
            resizeUp();
        }
        if (firstIndex == 0) {
            firstIndex = items.length - 1;
        } else {
            firstIndex--;
        }
        size++;
        items[firstIndex] = item;
    }

    /**
     * Adds an item of type T to the back of the deque
     */
    @Override
    public void addLast(T item) {
        if (size == 0) {
            firstIndex = 0;
            lastIndex = 0;
            items[0] = item;
            size++;
            return;
        }
        if (size == items.length) {
            resizeUp();
        }
        if (lastIndex == items.length - 1) {
            lastIndex = 0;
        } else {
            lastIndex++;
        }
        size++;
        items[lastIndex] = item;
    }


    /**
     * Returns true if deque is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque
     */
    @Override
    public int size() {
        if (size <= 0) {
            return 0;
        }
        return size;
    }

    /**
     * Prints the items in the deque from first to last
     * separated by a space
     */
    @Override
    public void printDeque() {
        for (int i = 0; i < items.length; i++) {
            System.out.print(get(i) + " ");
        }
    }

    /**
     * Removes and returns the item at the front of the deque
     * if no such item exists, returns null
     */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T temp = items[firstIndex];
        items[firstIndex] = null;
        if (firstIndex == items.length - 1) {
            firstIndex = 0;
        } else {
            firstIndex++;
        }
        size--;
        if (size == 0) {
            firstIndex = 0;
            lastIndex = 0;
        }
        if (size < items.length / 4) {
            resizeDown();
        }
        return temp;
    }

    /**
     * Removes and returns the item at the back of the deque
     * if no such item exists, returns null
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T temp = items[lastIndex];
        items[lastIndex] = null;
        if (lastIndex == 0) {
            lastIndex = items.length - 1;
        } else {
            lastIndex--;
        }
        size--;
        if (size == 0) {
            firstIndex = 0;
            lastIndex = 0;
        }
        if (size < items.length / 4) {
            resizeDown();
        }
        return temp;
    }

    /**
     * Gets the item at the given index. where 0 is the front
     * 1 is the next item, and so forth. If no such item exists
     * return null.
     * Must not alter the deque
     */
    @Override
    public T get(int index) {
        return items[(firstIndex + index) % items.length];
    }

    /**
     * resize up the length of array
     */
    private void resizeUp() {
        T[] a = (T[]) new Object[items.length * RFACTOR];
        int sizeOfFirstCopy = items.length - firstIndex;
        System.arraycopy(items, firstIndex, a, 0, sizeOfFirstCopy);
        System.arraycopy(items, 0, a, sizeOfFirstCopy, size - sizeOfFirstCopy);
        items = a;
        firstIndex = 0;
        lastIndex = size - 1;
    }

    /**
     * resize down the length of array
     */
    private void resizeDown() {
        T[] a = (T[]) new Object[items.length / RFACTOR];
        if (lastIndex < firstIndex) {
            int sizeOfFirstCopy = items.length - firstIndex;
            System.arraycopy(items, firstIndex, a, 0, sizeOfFirstCopy);
            System.arraycopy(items, 0, a, sizeOfFirstCopy, size - sizeOfFirstCopy);
        } else {
            System.arraycopy(items, firstIndex, a, 0, size);
        }
        items = a;
        firstIndex = 0;
        lastIndex = size - 1;

    }
}
