package projectGit;

public class TreeManager {
	 
	public void insert (AVLTree tree, Node n){
			tree.insert(n);
	}
	
	public void insert (BinarySearchTree<Node> tree, Node n){
			tree.insert(n);
	}
	
	public NodePackage find(AVLTree tree, int id) {
        	return tree.find(id);
    }
	
	public NodePackage find(BinarySearchTree<Node> tree, int id) {
    	return tree.find(new Node(id, null, 0, null));
	}

	public NodePackage minID(AVLTree tree) {
        	return tree.min();
	}
	
	public NodePackage minID(BinarySearchTree<Node> tree) {
    	return tree.min();
	}

	public NodePackage maxID(AVLTree tree) {
        	return tree.max();
    }
	
	public NodePackage maxID(BinarySearchTree<Node> tree) {
    	return tree.max();
	}
}
