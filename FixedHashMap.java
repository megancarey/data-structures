/**
 * @author mcarey
 **/

public class FixedHashMap {

	/* The fixed value of this HashMap's size limit. */
	private int capacity;

	/* The value of the HashMap's current size. */
	private int currentSize;

	/* The array that stores our keys and pointers to values. */
	private LinkedList[] array;

	/* The load factor of the HashMap. */
	private float loadFactor;

	/* Return an instance of the class with pre-allocated space for
	 * the given number of objects SIZE.
	 */
	public FixedHashMap(int size) {
		this.capacity = size;
		this.array = new LinkedList[size];
		this.currentSize = 0;
		this.loadFactor = 0;
	}

	/* Stores the given KEY/VALUE pair in the hash map. Returns a
	 * boolean value indicating succes/failure of the operation.
	 */
	public boolean set(char[] key, Object value) {
		int index = key.hashCode() % this.capacity;
		if (this.currentSize < this.capacity) {
			this.currentSize++;
			if (this.currentSize == this.capacity) {
				System.out.println("You have reached maximum capacity!"
					+ " Next insertion will force a deletion.");
			}
			if (this.array[index] == null) {
				this.array[index] = new LinkedList(key, value, 1);
				return true;
			} else {
				return (this.array[index]).insert(key, value);
			}
		} else {
			for (int i = 0; i < this.capacity; i++) {
				if (this.array[i] != null) {
					Object deleted = (this.array[i]).deleteLeastRecentlyUsed();
					System.out.println("You have exceeded maximum capacity. "
						+ "The following object was deleted: " + deleted.toString());
					this.currentSize--;
					return set(key, value);
				}
			}
			return false;
		}
	}

	/* Return the value associated with the given KEY, or null if no
	 * value is set.
	 */
	public Object get(char[] key) {
		int index = key.hashCode() % this.capacity;
		if (this.array[index] == null) {
			return null;
		}
		return (this.array[index]).getValue(key);
	}

	/* Delete the value associated with the given KEY, returning the
	 * value on success or null if the key has no value.
	 */
	public Object delete(char[] key) {
		int index = key.hashCode() % this.capacity;
		if (this.array[index] == null) {
			return null;
		}
		this.currentSize--;
		return (this.array[index]).delete(key);
	}

	/* Return a float value representing the load factor of the data
	 * structure. Since the size of the data structure is fixed, this
	 * should never be greater than 1.
	 */
	public float load() {
		return ((float)this.currentSize) / this.capacity;
	}

	/* Returns the current size of the HashMap. For testing purposes only. */
	public int getSize() {
		return this.currentSize;
	}

}
