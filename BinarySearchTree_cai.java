/** @author 
 *  Binary search tree (starter code)
 **/

package pxc190029;

import java.util.*;

public class BinarySearchTree_cai<T extends Comparable<? super T>> implements Iterable<T> {
    static class Entry<T> {
        T element;
        Entry<T> left, right;

        public Entry(T x, Entry<T> left, Entry<T> right) {
            this.element = x;
	        this.left = left;
	        this.right = right;
        }
    }
    
    Entry<T> root;
    int size;

    public BinarySearchTree_cai() {
	    root = null;
	    size = 0;
    }


    /** TO DO: Is x contained in tree?
     */
    public boolean contains(T x) {
        if (contains(x, root) == null) return false;
        return true;
    }

    private Entry contains(T x, Entry cur) {
        if (cur == null) return null;
        if (x.equals(cur.element)) return cur;
        if (x.compareTo((T)cur.element) < 0) return contains(x, cur.left);
        return contains(x, cur.right);
    }

    /** TO DO: Is there an element that is equal to x in the tree?
     *  Element in tree that is equal to x is returned, null otherwise.
     */
    public T get(T x) {
        if (contains(x)) return x;
        return null;
    }



    /** TO DO: Add x to tree. 
     *  If tree contains a node with same key, replace element by x.
     *  Returns true if x is a new element added to tree.
     */
    public boolean add(T x) {
        if (contains(x)) return false;
        if (this.root == null) {
            root = new Entry<>(x, null, null);
        }
        else add(x, root);
        size++;
        return true;
    }

    private void add(T x, Entry cur) {

        if (x.compareTo((T)cur.element) < 0) {
            if (cur.left == null) {
                cur.left = new Entry(x, null, null);
                return;
            }
            else add(x, cur.left);
        }
        else {
            if (cur.right == null) {
                cur.right = new Entry(x, null, null);
                return;
            }
            else add(x, cur.right);
        }
    }

    /** TO DO: Remove x from tree. 
     *  Return x if found, otherwise return null
     */
    public T remove(T x) {
        if(!contains(x)) return null;
        this.root = remove(x, this.root);
        size--;
        return x;
    }

    private Entry remove(T x, Entry root) {
        if (root == null) return null;
        if (root.element.equals(x)) {
            if (root.left == null || root.right == null) root = root.left != null ? root.left : root.right;
            else {
                Entry leftMost = max(root.left);
                leftMost.right = root.right;
                root = root.left;
            }
        }
        else {
            root.left = remove(x, root.left);
            root.right = remove(x, root.right);
        }
        return root;
    }

    public T min() {
	    return (T)min(root).element;
    }

    /**
     * Find left-most of x.
     * @param x
     * @return Entry of left most.
     */
    private Entry min(Entry x) {
        if (x == null || x.left == null) return x;
        return min(x.left);
    }

    public T max() {
        return (T)max(root).element;
    }

    private Entry max(Entry x) {
        if (x == null || x.right == null) return x;
        return max(x.right);
    }

    // TODO: Create an array with the elements using in-order traversal of tree
    public Comparable[] toArray() {
        Comparable[] arr = new Comparable[size];
        /* write code to place elements in array here */
        List<Entry<T>> res = new LinkedList<>();
        inOrderTraverse(this.root, res);
        int i = 0;
        for (Entry<T> e :
                res) {
            arr[i++] = e.element;
        }
        return arr;
    }

    private void inOrderTraverse(Entry<T> node, List<Entry<T>> res) {
        if(node != null) {
            inOrderTraverse(node.left, res);
            res.add(node);
            inOrderTraverse(node.right, res);
        }
    }

// Start of Optional problem 2

    /** Optional problem 2: Iterate elements in sorted order of keys
	Solve this problem without creating an array using in-order traversal (toArray()).
     */
    public Iterator<T> iterator() {
	return null;
    }

    // Optional problem 2.  Find largest key that is no bigger than x.  Return null if there is no such key.
    public T floor(T x) {
        return null;
    }

    // Optional problem 2.  Find smallest key that is no smaller than x.  Return null if there is no such key.
    public T ceiling(T x) {
        return null;
    }

    // Optional problem 2.  Find predecessor of x.  If x is not in the tree, return floor(x).  Return null if there is no such key.
    public T predecessor(T x) {
        return null;
    }

    // Optional problem 2.  Find successor of x.  If x is not in the tree, return ceiling(x).  Return null if there is no such key.
    public T successor(T x) {
        return null;
    }

// End of Optional problem 2

    public static void main(String[] args) {
	BinarySearchTree_cai<Integer> t = new BinarySearchTree_cai<>();
//        Scanner in = new Scanner(System.in);
        Scanner in = new Scanner("1 3 5 7 9 2 4 6 8 10 -1 -3 -6 -3 -10 0");
        while(in.hasNext()) {
            int x = in.nextInt();
            if(x > 0) {
                System.out.print("Add " + x + " : ");
                t.add(x);
                t.printTree();
            } else if(x < 0) {
                System.out.print("Remove " + x + " : ");
                t.remove(-x);
                t.printTree();
            } else {
                Comparable[] arr = t.toArray();
                System.out.print("Final: ");
                for(int i=0; i<t.size; i++) {
                    System.out.print(arr[i] + " ");
                }
                System.out.println();
                return;
            }           
        }
    }


    public void printTree() {
	System.out.print("[" + size + "]");
	printTree(root);
	System.out.println();
    }

    // Inorder traversal of tree
    void printTree(Entry<T> node) {
        if(node != null) {
            printTree(node.left);
            System.out.print(" " + node.element);
            printTree(node.right);
        }
    }

}
/*
Sample input:
1 3 5 7 9 2 4 6 8 10 -3 -6 -3 0

Output:
Add 1 : [1] 1
Add 3 : [2] 1 3
Add 5 : [3] 1 3 5
Add 7 : [4] 1 3 5 7
Add 9 : [5] 1 3 5 7 9
Add 2 : [6] 1 2 3 5 7 9
Add 4 : [7] 1 2 3 4 5 7 9
Add 6 : [8] 1 2 3 4 5 6 7 9
Add 8 : [9] 1 2 3 4 5 6 7 8 9
Add 10 : [10] 1 2 3 4 5 6 7 8 9 10
Remove -3 : [9] 1 2 4 5 6 7 8 9 10
Remove -6 : [8] 1 2 4 5 7 8 9 10
Remove -3 : [8] 1 2 4 5 7 8 9 10
Final: 1 2 4 5 7 8 9 10 

*/
