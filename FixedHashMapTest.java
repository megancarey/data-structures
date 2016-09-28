import java.lang.*;
import static org.junit.Assert.*;

/**
 * KPCB Coding Challenge Submission: Megan Carey
 * @author mcarey
 * 
 * Test class to ensure functionality of FixedHashMap data structure.
 **/
public class FixedHashMapTest {

	/* Main method runs all tests. */
	public static void main(String... args) {
		testSetAndGet();
		testDelete();
		testLoad();
	}

	/* Test for set() and get() methods of FixedHashMap.
	 * Test Flow:
	 * 1. Create instance of FixedHashMap, size 4.
	 * 2. Insert 5 key, value pairs with varying value types.
	 * 3. Ensure that only 4 of the 5 pairs were kept.
	 */
	public static void testSetAndGet() {
		FixedHashMap fhashMap = new FixedHashMap(4);
		char[] firstKey = "1".toCharArray();
		fhashMap.set(firstKey, 1);
		assertEquals(fhashMap.get(firstKey), 1);

		char[] secondKey = "2".toCharArray();
		fhashMap.set(secondKey, 2);
		assertEquals(fhashMap.get(secondKey), 2);

		char[] thirdKey = "3".toCharArray();
		fhashMap.set(thirdKey, "Hello!");
		assertEquals(fhashMap.get(thirdKey), "Hello!");

		char[] fourthKey = "4".toCharArray();
		Object obj = new Object();
		fhashMap.set(fourthKey, obj);
		assertEquals(fhashMap.get(fourthKey), obj);

		char[] fifthKey = "5".toCharArray();
		fhashMap.set(fifthKey, 5);
		assertEquals(fhashMap.get(fifthKey), 5);

		assertEquals(fhashMap.getSize(), 4);
	}

	/* Test for delete() method of FixedHashMap.
	 * Test Flow:
	 * 1. Create instance of FixedHashMap, size 4.
	 * 2. Insert 4 key, value pairs with varying value types.
	 * 3. Delete the fourth key, value pair.
	 * 4. Insert a 5th key, value pair.
	 * 5. Assert that the 5th pair was kept, the size is still
	 *    4, and that the 4th key, value pair is no longer present.
	 */
	public static void testDelete() {
		FixedHashMap fhashMap = new FixedHashMap(4);
		char[] firstKey = "1".toCharArray();
		fhashMap.set(firstKey, 1);
		assertEquals(fhashMap.get(firstKey), 1);

		char[] secondKey = "2".toCharArray();
		fhashMap.set(secondKey, 2);
		assertEquals(fhashMap.get(secondKey), 2);

		char[] thirdKey = "3".toCharArray();
		fhashMap.set(thirdKey, "Hello!");
		assertEquals(fhashMap.get(thirdKey), "Hello!");

		char[] fourthKey = "4".toCharArray();
		Object obj = new Object();
		fhashMap.set(fourthKey, obj);
		assertEquals(fhashMap.get(fourthKey), obj);

		fhashMap.delete(fourthKey);

		char[] fifthKey = "5".toCharArray();
		fhashMap.set(fifthKey, 5);
		assertEquals(fhashMap.get(fifthKey), 5);

		assertEquals(fhashMap.getSize(), 4);
		assertNull(fhashMap.get(fourthKey));
	}

	/* Test for load() method of FixedHashMap.
	 * Test Flow:
	 * 1. Create instance of FixedHashMap, size 4.
	 * 2. Insert 1 key, value pair. Assert load factor = 1/4.
	 * 3. Insert another key, value pair. Assert load factor = 1/2.
	 * 4. Insert 2 more key, value pairs. Assert load factor = 1.
	 * 3. Insert another key, value pair. Assert size is fixed,
	 *    and load factor = 1.
	 */
	public static void testLoad() {
		FixedHashMap fhashMap = new FixedHashMap(4);
		assertTrue(fhashMap.load() == 0);

		char[] firstKey = "1".toCharArray();
		fhashMap.set(firstKey, 1);
		assertTrue(fhashMap.load() == 0.25);

		char[] secondKey = "2".toCharArray();
		fhashMap.set(secondKey, 2);
		assertTrue(fhashMap.load() == 0.5);

		char[] thirdKey = "3".toCharArray();
		fhashMap.set(thirdKey, "Hello!");

		char[] fourthKey = "4".toCharArray();
		Object obj = new Object();
		fhashMap.set(fourthKey, obj);

		assertTrue(fhashMap.load() == 1.0);

		char[] fifthKey = "5".toCharArray();
		fhashMap.set(fifthKey, 5);

		assertEquals(fhashMap.getSize(), 4);
		assertTrue(fhashMap.load() == 1.0);
	}
}