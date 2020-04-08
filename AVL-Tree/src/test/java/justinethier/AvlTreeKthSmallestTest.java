package justinethier;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests method kthSmallest from class AvlTree.
 * 
 */
public class AvlTreeKthSmallestTest {

	/**
	 * Tests kthSmallest on invalid value for k.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testKthSmallestInvalidK() {
		AvlTree<Integer> tree = new AvlTree<Integer>();
		int k = 0;
		
		tree.kthSmallest(k);
	}
	
	/**
	 * Tests kthSmallest on empty tree.
	 */
	@Test
	public void testKthSmallestEmptyTree() {
		AvlTree<Integer> tree = new AvlTree<Integer>();
		int k = 1;
		
		Integer result = tree.kthSmallest(k);
		
		assertNull(result);
	}
	
	/**
	 * Tests kthSmallest on singleton tree.
	 */
	@Test
	public void testKthSmallestSingletonTree() {
		AvlTree<Integer> tree = new AvlTree<Integer>();
		tree.insert(10);
		int k = 1;
		
		Integer result = tree.kthSmallest(k);
		
		assertEquals(new Integer(10), result);
	}
	
	/**
	 * Tests kthSmallest on singleton tree, k largest than size of tree.
	 */
	@Test
	public void testKthSmallestSingletonTreeKLargestThanOne() {
		AvlTree<Integer> tree = new AvlTree<Integer>();
		tree.insert(10);
		int k = 2;
		
		Integer result = tree.kthSmallest(k);
		
		assertNull(result);
	}

	/**
	 * Tests kthSmallest on larger tree.
	 */
	@Test
	public void testKthSmallestSLargerTree() {
		AvlTree<Integer> tree = new AvlTree<Integer>();
		for (int i = 0; i < 100; i++) {
			tree.insert(i);
		}
		tree.remove(0);
		
		for (int i = 1; i < 99; i++) {
			assertEquals(new Integer(i), tree.kthSmallest(i));
		}		
	}

	

}
