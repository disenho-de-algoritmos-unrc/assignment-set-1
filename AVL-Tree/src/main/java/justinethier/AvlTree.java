package justinethier;

/** 
 * Implementation of an AVL Tree, along with code to test insertions on the tree.
 * 
 *  Based on code written by Mark Allen Weiss in his book 
 *  Data Structures and Algorithm Analysis in Java.
 *
 *  Code for remove is based upon postings at:
 *  http://www.dreamincode.net/forums/topic/214510-working-example-of-avl-tree-remove-method/
 *
 *  Implementation was buggy. Updated with code from https://github.com/eugenp/tutorials/
 */
public class AvlTree<T extends Comparable<? super T>> {

    protected static class AvlNode<T> {
        T key;
        int height;
        AvlNode<T> left;
        AvlNode<T> right;

        AvlNode(T key) {
            this.key = key;
        }
    }

    protected AvlNode<T> root;

    public AvlNode<T> find(T key) {
        AvlNode<T> current = root;
        while (current != null) {
            if (current.key.compareTo(key) == 0) {
                break;
            }
            current = current.key.compareTo(key) < 0 ? current.right : current.left;
        }
        return current;
    }

    public boolean contains(T key) {
        return (find(key)!=null);
    }

    public void insert(T key) {
        root = insert(root, key);
    }

    public void delete(T key) {
        root = delete(root, key);
    }

    public void remove(T key) {
        delete(key);
    }

    public AvlNode<T> getRoot() {
        return root;
    }

    public int height() {
        return root == null ? -1 : root.height;
    }

    private AvlNode<T> insert(AvlNode<T> node, T key) {
        if (node == null) {
            return new AvlNode<T>(key);
        } else if (node.key.compareTo(key) > 0) {
            node.left = insert(node.left, key);
        } else if (node.key.compareTo(key) < 0) {
            node.right = insert(node.right, key);
        } else {
            throw new RuntimeException("duplicate Key!");
        }
        return rebalance(node);
    }

    private AvlNode<T> delete(AvlNode<T> node, T key) {
        if (node == null) {
            return node;
        } else if (node.key.compareTo(key) > 0) {
            node.left = delete(node.left, key);
        } else if (node.key.compareTo(key) < 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            } else {
                AvlNode<T> mostLeftChild = mostLeftChild(node.right);
                node.key = mostLeftChild.key;
                node.right = delete(node.right, node.key);
            }
        }
        if (node != null) {
            node = rebalance(node);
        }
        return node;
    }

    private AvlNode<T> mostLeftChild(AvlNode<T> node) {
        AvlNode<T> current = node;
        /* loop down to find the leftmost leaf */
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private AvlNode<T> rebalance(AvlNode<T> z) {
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z.right.right) > height(z.right.left)) {
                z = rotateLeft(z);
            } else {
                z.right = rotateRight(z.right);
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (height(z.left.left) > height(z.left.right)) {
                z = rotateRight(z);
            } else {
                z.left = rotateLeft(z.left);
                z = rotateRight(z);
            }
        }
        return z;
    }

    private AvlNode<T> rotateRight(AvlNode<T> y) {
        AvlNode<T> x = y.left;
        AvlNode<T> z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private AvlNode<T> rotateLeft(AvlNode<T> y) {
        AvlNode<T> x = y.right;
        AvlNode<T> z = x.left;
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private void updateHeight(AvlNode<T> n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    private int height(AvlNode<T> n) {
        return n == null ? -1 : n.height;
    }

    public int getBalance(AvlNode<T> n) {
        return (n == null) ? 0 : height(n.right) - height(n.left);
    }

    /**
     * Determine if the tree is empty.
     * 
     * @return True if the tree is empty 
     */
    public boolean isEmpty(){
        return (root == null);
    }


    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public T findMin( )
    {
        if( isEmpty( ) ) return null;

        return findMin( root ).key;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public T findMax( )
    {
        if( isEmpty( ) ) return null;
        return findMax( root ).key;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    private AvlNode<T> findMin(AvlNode<T> t)
    {
        if( t == null )
            return t;

        while( t.left != null )
            t = t.left;
        return t;
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the largest item.
     */
    private AvlNode<T> findMax( AvlNode<T> t )
    {
        if( t == null )
            return t;

        while( t.right != null )
            t = t.right;
        return t;
    }

    /***********************************************************************/
    // Diagnostic functions for the tree
    public boolean checkBalanceOfTree(AvlTree.AvlNode<Integer> current) {

        boolean balancedRight = true, balancedLeft = true;
        int leftHeight = 0, rightHeight = 0;

        if (current.right != null) {
            balancedRight = checkBalanceOfTree(current.right);
            rightHeight = getDepth(current.right);
        }

        if (current.left != null) {
            balancedLeft = checkBalanceOfTree(current.left);
            leftHeight = getDepth(current.left);
        }

        return balancedLeft && balancedRight && Math.abs(leftHeight - rightHeight) < 2;
    }

    public int getDepth(AvlTree.AvlNode<Integer> n) {
        int leftHeight = 0, rightHeight = 0;

        if (n.right != null)
            rightHeight = getDepth(n.right);
        if (n.left != null)
            leftHeight = getDepth(n.left);

        return Math.max(rightHeight, leftHeight)+1;
    }

    public boolean checkOrderingOfTree(AvlTree.AvlNode<Integer> current) {
        if(current.left != null) {
            if(current.left.key.compareTo(current.key) > 0)
                return false;
            else
                return checkOrderingOfTree(current.left);
        } else  if(current.right != null) {
            if(current.right.key.compareTo(current.key) < 0)
                return false;
            else
                return checkOrderingOfTree(current.right);
        } else if(current.left == null && current.right == null)
            return true;

        return true;
    }

    /**
     * Computes the kth-smallest element in the tree.
     * The argument k runs from 1, i.e., if k == 1, then
     * this method will return the smallest element in the tree.
     * when k exceeds the size of the tree, this method
     * will return null.
     * @param k is the value of the k-smallest value to find in tree.
     * @return the k-th smallest element in the tree.
     */
    public T kthSmallest(int k) {
        throw new UnsupportedOperationException("method not yet implemented");
    }

}

