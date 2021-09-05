public class LinkedListDeque<T> {
    private IntNode sentinel;
    private int size = 0;

    public class IntNode {
        private IntNode prev;
        private T item;
        private IntNode next;

        public IntNode(T x) {
            item = x;
        }
    }

    /**
     * create an empty linked list deque
     */
    public LinkedListDeque() {
        sentinel = new IntNode(null);
    }

    /**
     * add an item to the first place of linked list deque
     */
    public void addFirst(T x) {
        IntNode p = new IntNode(x);
        if (size == 0) {
            sentinel.next = p;
            sentinel.prev = p;
            p.prev = sentinel;
            p.next = sentinel;
            size++;
            return;
        }
        p.next = sentinel.next;
        p.prev = sentinel;
        sentinel.next = p;
        size++;
    }

    /**
     * add an item to the last place of linked list deque
     */
    public void addLast(T x) {
        IntNode p = new IntNode(x);
        if (size == 0) {
            sentinel.next = p;
            sentinel.prev = p;
            p.prev = sentinel;
            p.next = sentinel;
            size++;
            return;
        }
        sentinel.prev.next = p;
        p.prev = sentinel.prev;
        sentinel.prev = p;
        p.next = sentinel;
        size++;
    }

    /**
     * remove the first item from the linked list deque
     */
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T p = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return p;
    }

    /**
     * remove the last item from the linked list deque{}
     */
    public T removeLast() {
        if (sentinel.prev == sentinel){
            return null;
        }
        T p = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -- ;
        return p;
    }

    public T get(int index) {
        if (size < index) {
            return null;
        }
        IntNode p = sentinel.next;
        if (p.equals(sentinel)) {
            return null;
        }

        for (int i = 0; i < index; i++) {
            if (i == index) {
                return p.item;
            }
            p = p.next;
        }
        return null;

    }

    /**
     * same as get, but uses recursion
     */
//    public T getRecursive(int index) {
//
//    }

    /**
     * get the size of linked list deque
     */
    public int size() {
        return size;
    }

    /**
     * whether the linked list is empty or not
     */
    boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * print out the deque
     */
    public void printDeque() {
        if (size == 0) {
            return;
        }
        IntNode p = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(p.item + " ");
            p = p.next;
        }

    }


}
