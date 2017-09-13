/** 
 * @author mcarey
 **/

public class LinkedList {

	/* Head node of the LinkedList. */
	private Node head;

	/* Current size of the LinkedList. */
	private int size;

	/* Capacity of LinkedList, set upon initialization. */
	private int capacity;

	/* Return an instance of the class with the specified CAPACITY. */
	private LinkedList(int capacity) {
		this.size = size;
		this.head = null;
	}

	/* Return an instance of the class with the specified CAPACITY and
	 * a head node with the given KEY/VALUE pair. */
	public LinkedList(char[] key, Object value, int capacity) {
		this.head = new Node(key, value, null);
		this.capacity = capacity;
	}

	/* Inserts the given KEY/VALUE pair to the front of this LinkedList. */
	public boolean insert(char[] key, Object value) {
		this.capacity += 1;
		if (this.size < this.capacity) {
			Node newHead = new Node(key, value, this.head);
			this.head = newHead;
			this.size++;
			return true;
		} else {
			if (this.head == null) {
				return false;
			} else {
				deleteLeastRecentlyUsed();
				Node newHead = new Node(key, value, this.head);
				this.head = newHead;
				return true;
			}
			
		}
	}

	/* Deletes the least recently used node from this LinkedList. */
	public Object deleteLeastRecentlyUsed() {
		Node pointer = this.head;
		if (pointer == null) {
			return null;
		} else if (pointer.next == null) {
			this.head = null;
			return pointer.getValue();
		}
		while (pointer.next.next != null) {
			pointer = pointer.next;
		}
		Object toReturn = pointer.next.getValue();
		pointer.next = null;
		return toReturn;
	}

	/* Gets the value that corresponds with KEY from the LinkedList,
	 * if present. Moves the corresponding node to the front of the
	 * LinkedList, to maintain LRU. */
	public Object getValue(char[] key) {
		Node pointer = this.head;
		if (pointer == null) {
			return null;
		} else if (pointer.key.equals(key)) {
			return pointer.getValue();
		}
		while (pointer.next != null) {
			if (pointer.next.key.equals(key)) {
				Node newHead = pointer.next;
				pointer.next = pointer.next.next;
				newHead.next = this.head;
				this.head = newHead;
				return newHead.getValue();
			}
			pointer = pointer.next;
		}
		return null;
	}

	/* Deletes the node that corresponds with KEY from the LinkedList,
	 * if present. Returns the deleted value. Otherwise, returns null. */
	public Object delete(char[] key) {
		Node pointer = this.head;
		if (pointer == null) {
			return null;
		} else if (pointer.next == null) {
			this.head = null;
			return pointer.getValue();
		} else if (pointer.key.equals(key)) {
			this.head = this.head.next;
			return pointer.getValue();
		}
		while (pointer.next.next != null) {
			if (pointer.next.key.equals(key)) {
				pointer.next = pointer.next.next;
				return pointer.getValue();
			}
			pointer = pointer.next;
		}
		return null;
	}

	/**
	 * Internal Node class to store nodes within the LinkedList.
	 **/
	private class Node {

		/* The key string for the given node. */
		public char[] key;

		/* The value associated with the given key. */
		private Object value;

		/* The next node in the LinkedList. */
		public Node next;

		/* Constructor for a new node object with string KEY, Object
		 * VALUE, and next Node NEXT. */
		public Node(char[] key, Object value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}

		/* Returns this node's value. */
		public Object getValue() {
			return this.value;
		}
	}

}
