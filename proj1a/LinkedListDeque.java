public class LinkedListDeque<T> {
    private TypeNode sentinel;
    private int size;

    private class TypeNode {
        private TypeNode prev;
        private T item;
        private TypeNode next;

        public TypeNode(TypeNode p, T x, TypeNode n) {
            prev = p;
            item = x;
            next = n;
        }
    }

    /**
     * create an empty linked list deque
     */
    public LinkedListDeque() {
        size = 0;
        sentinel = new TypeNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /**
     * add an item to the first place of linked list deque
     */
    public void addFirst(T x) {
        sentinel.next = new TypeNode(sentinel, x, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    /**
     * add an item to the last place of linked list deque
     */
    public void addLast(T x) {
        sentinel.prev = new TypeNode(sentinel.prev, x, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    /**
     * remove the first item from the linked list deque
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size--;
        T p = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return p;
    }

    /**
     * remove the last item from the linked list deque{}
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        T p = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return p;
    }

    public T get(int index) {
        int count = 0;
        TypeNode ptr = sentinel;
        while (ptr.next != sentinel) {
            ptr = ptr.next;
            if (count == index) {
                return ptr.item;
            }
            count++;
        }
        return null;
    }

    private T getRecursiveHelper(int index, int count, TypeNode ptr) {
        if (index == count) {
            return ptr.item;
        }
        return getRecursiveHelper(index, count + 1, ptr.next);

    }

    /**
     * same as get, but uses recursion
     */
    public T getRecursive(int index) {
        if (index > size || index < 0) {
            return null;
        }
        int count = 0;
        TypeNode ptr = sentinel.next;
        return getRecursiveHelper(index, count, ptr);
    }


    /**
     * get the size of linked list deque
     */
    public int size() {
        return size;
    }

    /**
     * whether the linked list is empty or not
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * print out the deque
     */
    public void printDeque() {
        TypeNode ptr = sentinel;
        while (ptr.next != sentinel) {
            ptr = ptr.next;
            System.out.print(ptr.item);
            System.out.print(" ");
        }
        System.out.println();
    }
}
