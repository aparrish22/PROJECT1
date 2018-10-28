public class TreeManager {
	 private BinarySearchTree<Node> BST;
	 private AVLTree AVL;

	 public TreeManager() {
        BST = new BinarySearchTree<>();
        AVL = new AVLTree();
	 }
	 
	 public void insert (Object tree, Node n){
		 tree.insert(n);
	 }
	
	 public NodePackage find(Object tree, int id) {
        NodePackage nodepack = tree.find(id);
        if (nodepack == null)
			return null;
		else 
			return nodepack.getNode().getID();
    }

	 public Node minID(Object tree) {
        return tree.min();
	 }

	 public Node maxID(Object tree) {
        return tree.max();
    }
}
