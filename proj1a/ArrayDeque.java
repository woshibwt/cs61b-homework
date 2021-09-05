public class ArrayDeque<T> {
    private static final int RFACTOR = 2;
    private T[] items;
    private int size;
    private int capacity;
    private int nextFirst = 4;
    private int nextLast = 5;

    /**
     * creates an empty array deque
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        capacity = 8;
    }

    /**
     * Adds an item if type T to the front of the deque
     */
    public void addFirst(T item) {
        if (nextFirst == nextLast) {
            size++;
            items[nextFirst] = item;
            resize();
            return;
        }
        size++;
        items[nextFirst] = item;
        nextFirst--;
        nextFirst = (capacity + nextFirst % capacity) % capacity;
    }

    /**
     * Adds an item of type T to the back of the deque
     */
    public void addLast(T item) {
        if (nextFirst == nextLast) {
            size++;
            items[nextLast] = item;
            resize();
            return;
        }
        size++;
        items[nextLast] = item;
        nextLast++;
        nextLast %= capacity;
    }


    /**
     * Returns true if deque is empty, false otherwise
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns the number of items in the deque
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last
     * separated by a space
     */
    public void printDeque() {
        for (int i = 0; i < capacity; i++) {
            System.out.print(get(i) + " ");
        }
    }

    /**
     * Removes and returns the item at the front of the deque
     * if no such item exists, returns null
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst++;
        nextFirst %= capacity;
        size--;
        T temp = items[nextFirst];
        items[nextFirst] = null;
        return temp;
    }

    /**
     * Removes and returns the item at the back of the deque
     * if no such item exists, returns null
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast--;
        nextLast = (capacity + nextLast % capacity) % capacity;
        size--;
        T temp = items[nextLast];
        items[nextLast] = null;
        return temp;
    }

    /**
     * Gets the item at the given index. where 0 is the front
     * 1 is the next item, and so forth. If no such item exists
     * return null.
     * Must not alter the deque
     */
    public T get(int index) {
        if (index >= capacity) {
            return null;
        }
        return items[index];
    }

    /**
     * resize the length of array
     */
    public void resize() {
        double ratio = (double) size / capacity;
        if (ratio >= 0.5) {
            T[] a = (T[]) new Object[capacity * RFACTOR];
            System.arraycopy(items, 0, a, 0, capacity);
            items = a;
            nextFirst = capacity + capacity / 2;
            nextLast = capacity + capacity / 2 + 1;
            capacity = capacity * RFACTOR;
        }
    }


}
