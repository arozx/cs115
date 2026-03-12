/**
 * Linked List Class
 * 
 * @author Casey Hopkins, Jack Cooke
 */
public class LinkedList {
    private Node head;
    private int size = 0;

    /**
     * Constructs an empty list.
     */
    public LinkedList() {
        head = null;
    }

    /**
     * Adds the element to the beginning of this list.
     * 
     * @param data the String to be added
     */
    public void add(String data) {
        Node newNode = new Node(data);
        if (head != null) {
            newNode.setNext(head);
        }
        head = newNode;
        size++;
    }

    /**
     * Adds the element to the end of this list.
     * 
     * @param data the String to be added
     */
    public void addLast(String data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node currentNode = head;
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode);
        }
        size++;
    }

    /**
     * Inserts the element at the specified position in this list.
     * 
     * @param index index at which to insert the element
     * @param data  the String to be inserted
     * @throws IndexOutOfBoundsException if an invalid index is provided
     */
    public void insert(int index, String data) {
        // Allow inserting at index == size (inserting at the end)
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        Node newNode = new Node(data);

        // Special case: inserting at the beginning
        if (index == 0) {
            newNode.setNext(head);
            head = newNode;
        } else {
            // Find the node just before the insertion point
            Node currentNode = head;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNext();
            }
            // Insert the new node
            newNode.setNext(currentNode.getNext());
            currentNode.setNext(newNode);
        }
        size++;
    }

    private void validateIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Checks if this list contains the String provided.
     * 
     * @param target the String to look for
     * @return true if this list contains target otherwise false
     */
    public boolean contains(String target) {
        Node currentNode = head;

        while (currentNode != null) {
            if (currentNode.getData().equals(target)) {
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    /**
     * Checks if this list contains the String provided using recursion.
     * 
     * @param target the String to look for
     * @return true if this list contains target otherwise false
     */
    public boolean containsRecursive(String target) {
        return containsRecursiveHelper(head, target);
    }

    /**
     * Helper method for containsRecursive.
     * 
     * @param node   the current node to check
     * @param target the String to look for
     * @return true if target is found, false otherwise
     */
    private boolean containsRecursiveHelper(Node node, String target) {
        // Base case: reached end of list, target not found
        if (node == null) {
            return false;
        }
        // Base case: found the target
        if (node.getData().equals(target)) {
            return true;
        }
        // Recursive case: check the rest of the list
        return containsRecursiveHelper(node.getNext(), target);
    }

    /**
     * Deletes the element at the specified position in this list.
     * 
     * @param index index of the element to delete
     * @throws IndexOutOfBoundsException if an invalid index is provided
     */
    public void delete(int index) {
        validateIndex(index);

        if (index == 0) {
            head = head.getNext();
        } else {
            Node currentNode = head.getNext();
            Node previousNode = head;

            int currentIndex = 1;
            while (currentNode != null) {

                if (currentIndex == index) {
                    previousNode.setNext(currentNode.getNext());
                    return;
                }
                currentIndex++;
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }
        }
        size--;
    }

    /**
     * Deletes the element at the specified position in this list using recursion.
     * 
     * @param index index of the element to delete
     * @throws IndexOutOfBoundsException if an invalid index is provided
     */
    public void deleteRecursive(int index) {
        validateIndex(index);

        if (index == 0) {
            head = head.getNext();
        } else {
            deleteRecursiveHelper(head, index - 1);
        }
        size--;
    }

    /**
     * Helper method for deleteRecursive.
     * 
     * @param node  the current node
     * @param index the remaining steps to the node to delete
     */
    private void deleteRecursiveHelper(Node node, int index) {
        // Base case: the next node is the one to delete
        if (index == 0) {
            node.setNext(node.getNext().getNext());
            return;
        }
        // Recursive case: move to the next node
        deleteRecursiveHelper(node.getNext(), index - 1);
    }

    /**
     * Returns the String at the specified position in this list.
     * 
     * @param index index of the element to return
     * @throws IndexOutOfBoundsException if an invalid index is provided
     */
    public String get(int index) {
        validateIndex(index);

        int currentIndex = 0;
        Node currentNode = head;

        while (currentNode != null) {

            if (currentIndex == index) {
                return currentNode.getData();
            }
            currentIndex++;
            currentNode = currentNode.getNext();
        }
        return null;
    }

    /**
     * Returns the String at the specified position in this list using recursion.
     * 
     * @param index index of the element to return
     * @throws IndexOutOfBoundsException if an invalid index is provided
     */
    public String getRecursive(int index) {
        validateIndex(index);
        return getRecursiveHelper(head, index);
    }

    /**
     * Helper method for getRecursive.
     * 
     * @param node  the current node
     * @param index the remaining index to traverse
     * @return the String at the specified position
     */
    private String getRecursiveHelper(Node node, int index) {
        // Base case: we've reached the target index
        if (index == 0) {
            return node.getData();
        }
        // Recursive case: move to the next node and decrement index
        return getRecursiveHelper(node.getNext(), index - 1);
    }

    /**
     * Returns the number of elements in this list.
     * 
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Reverses the linked list in place.
     */
    public void reverse() {
        Node previousNode = null;
        Node currentNode = head;
        Node nextNode = null;

        while (currentNode != null) {
            // Store the next node
            nextNode = currentNode.getNext();
            // Reverse the link
            currentNode.setNext(previousNode);
            // Move forward
            previousNode = currentNode;
            currentNode = nextNode;
        }
        // Update head to point to the new first node
        head = previousNode;
    }

    /**
     * Reverses the linked list in place using recursion.
     */
    public void reverseRecursive() {
        head = reverseRecursiveHelper(head, null);
    }

    /**
     * Helper method for reverseRecursive.
     * 
     * @param current  the current node being processed
     * @param previous the previous node (already reversed)
     * @return the new head of the reversed list
     */
    private Node reverseRecursiveHelper(Node current, Node previous) {
        // Base case: reached the end of the list
        if (current == null) {
            return previous;
        }
        // Store the next node
        Node next = current.getNext();
        // Reverse the link
        current.setNext(previous);
        // Recursive case: process the rest of the list
        return reverseRecursiveHelper(next, current);
    }

    /**
     * To string method to print linked list in order of nodes
     * 
     * @return string representing the linked list
     */
    @Override
    public String toString() {
        String s = "LinkedList containing [";
        Node currentNode = head;
        while (currentNode != null) {
            s += currentNode.getData() + ",";
            currentNode = currentNode.getNext();
        }
        s += "]";
        return s;
    }
}
