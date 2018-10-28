public class TreeManager {
	 
	public void insert (Object tree, Node n){
		tree.insert(n);
	}
	
	public NodePackage find(Object tree, int id) {
        	return tree.find(id);
    	}

	public NodePackage minID(Object tree) {
        	return tree.min();
	}

	public NodePackage maxID(Object tree) {
        	return tree.max();
    	}
}
