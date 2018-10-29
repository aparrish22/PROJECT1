package projectGit;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BinarySearchTree<T> implements Iterable<T> {


	private BSTNode<T> root;
	private Comparator<T> comp;
	

	public BinarySearchTree() {
		root = null;

		comp = new Comparator<T>() {

			@Override
			public int compare(T arg0, T arg1) {
				return ((Comparable) arg0).compareTo(arg1);
			}

		};
	}

	private BSTNode insertAux(BSTNode<T> node, T element) {
		if (node == null) {
			return new BSTNode(element, null, null);
		} else {
			int cmp = comp.compare(node.getInfo(), element);

			if (cmp < 0) {
				node.setRight(insertAux(node.getRight(), element));
			} else if (cmp > 0) {
				node.setLeft(insertAux(node.getLeft(), element));
			}

			return node;
		}

	}

	public void insert(T element) {
		root = insertAux(root, element);
	}

	private NodePackage findAux(BSTNode<T> node, T target,int count) {
		if (node != null) {
			int cmp = comp.compare(node.getInfo(), target);
			count++;
			if (cmp == 0)
				return new NodePackage((Node) node.getInfo(),count);
			else if (cmp < 0) {
				return findAux(node.getRight(), target, count);
			} else if (cmp > 0) {
				return findAux(node.getLeft(), target, count);
			}
		}

		return new NodePackage(null, count);
	}

	public NodePackage find(T element) {
		return findAux(root, element, 0);
	}
	private T lastDeleted;
	private BSTNode removeAux(BSTNode<T> node, T target) {
		if (node == null)
			return null;

		int cmp = comp.compare(node.getInfo(), target);
		BSTNode res;
		if (cmp == 0) {
			if(lastDeleted == null)
				lastDeleted = node.getInfo();
			if (node.getLeft() == null)
				return node.getRight();

			if (node.getRight() == null)
				return node.getLeft();

			// find replacement and recursively remove the replacement
			T smallestOnRight = findSmallest(node.getRight());
			// do the replacement and recursively remove the replacement from
			// the right subtree
			node.setInfo(smallestOnRight);
			node.setRight(removeAux(node.getRight(), smallestOnRight));
			
			

		} else if (cmp < 0) {
			node.setRight(removeAux(node.getRight(), target));
		} else {
			node.setLeft(removeAux(node.getLeft(), target));
		}
		
		return node;

    }

    // return minimum node
    public NodePackage min() {
		int count = 0;
		// if tree is not empty
		if (root != null) {
			count++;
			BSTNode<T> node = root;
			NodePackage nodepack = new NodePackage((Node) node.getInfo(),count);
			// find the smallest
			while (node.getLeft() != null) {
				count++;
				node = node.getLeft();
			}

			return new NodePackage((Node) node.getInfo(),count);
		}

        return null;
    }

	// return maximum node
    public NodePackage max() {
		int count = 0;
		if (root != null) {
			count++;
			BSTNode<T> node = root;
			NodePackage nodepack = new NodePackage((Node) node.getInfo(),count);
			while (node.getRight() != null) {
				count++;
				node = node.getRight();
			}

			return new NodePackage((Node) node.getInfo(),count);
		}
        return null;
    }

	private T findSmallest(BSTNode<T> node) {
		while (node.getLeft() != null)
			node = node.getLeft();

		return node.getInfo();
	}

	
	
	
	public String inOrder() {
		return inOrder(root);
	}
	
	private String inOrder(BSTNode node) {
		if(node == null)
			return "";
		
		String left = inOrder(node.getLeft());
		
		String right = inOrder(node.getRight());
		
		return left + "\n" + node.getInfo() + "\n" + right;
	}
	
	
	public String preOrder() {
		return preOrder(root);
	}
	
	private String preOrder(BSTNode node) {
		if(node == null)
			return "";
		
		String left = preOrder(node.getLeft());
		
		String right = preOrder(node.getRight());
		
		return node.getInfo() + "\n" + left + "\n" + right;
	}
	
	private void inOrder(BSTNode<T> node, Queue<T> q) {
		if(node == null)
			return;
		
		// this will result in the inOrder format, recursively
		inOrder(node.left, q);
		q.add(node.getInfo());
		inOrder(node.right, q);
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			
			// iterator variable (it)
			Iterator<T> it;
			
			// here you can initialize inside the curly brackets
			{
				Queue<T> q = new LinkedBlockingQueue<T>();
			
				inOrder(root, q);
				
				it = q.iterator();
			}


			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			@Override
			public T next() {
				return it.next();
			}	
			
		};
	}
	
	
	public BinarySearchTree fromTraversal(Comparable[] inOrder, Comparable[] preOrder) {
		BinarySearchTree<T> res = new BinarySearchTree<T>();
		
		// insert the preOrder
		res = aux(preOrder);
		
		// res.root = something;
		return res; // will return the binary search tree
	}
	
	private BinarySearchTree<T> aux(Comparable[] preOrder) {
		
		BinarySearchTree<T> BST = new BinarySearchTree<T>();
		
		
		// just insert preOrder, d b a c e f
		for (int i = 0; i < preOrder.length; i++)
			BST.insert((T) preOrder[i]);
			
		
		
		return BST;
	}

}
