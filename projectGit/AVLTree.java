package projectGit;

public class AVLTree {
	private Node root;
	
	//Returns student info with minimum id
	public NodePackage min(){
		Node current = root;
		int count = 1;
		
		while(current.getLeft() != null){
			count++;
			current = current.getLeft();
		}
		return new NodePackage(current, count);
	}
	
	//Returns student info with maximum id
	public NodePackage max(){
		Node current = root;
		int count = 1;
		
		while(current.getRight() != null){
			count++;
			current = current.getRight();
		}
		return new NodePackage(current, count);
	}
	
	//Inserts the given node
	public void insert(Node insert){
		if(root != null){
			Node current = root;
			int count = 0;
			while(true){
				current.setHeight(current.getHeight() + 1);
				count++;
				if(insert.getStudentID() > current.getStudentID()){		//If it is bigger, insert to the right
					if(current.getRight() != null){
						current = current.getRight();
						continue;
					}else{
						current.setRight(insert);
						insert.setParent(current);
						checkHeight(insert);
						break;
					}
				}else if(insert.getStudentID() < current.getStudentID()){	//If it is smaller, insert to the left
					if(current.getLeft() != null){
						current = current.getLeft();
						continue;
					}else{
						current.setLeft(insert);
						insert.setParent(current);
						checkHeight(insert);
						break;
					}
				}else{										//If it is the same, throw an error
					System.out.println("ERROR: Duplicate ID for ID: " + insert.getStudentID() + ". Continuing...");
					System.out.println(insert.getStudentID() + " " +  current.getStudentID() + ", " + insert.getName() + " " +current.getName() + " " + count);
					return;
				}
			}
			//System.out.println(insert.getStudentID() + " inserted as child of " + insert.getParent().getStudentID());
		}else{
			root = insert;
			//System.out.println(insert.getStudentID() + " inserted at root.");
		}
	}
	
	//Ensures the height follows the rules of the AVL tree
	private void checkHeight(Node inserted){
		checkHeight(inserted, true, true);
	}
	
	//Ensures the height follows the rules of the AVL tree, recursively
	/* childWasRight is true if the most recently inserted node was inserted 
	 * to the right, false if inserted to the left.
	 * 
	 * gchildWasRight is true if the most recently inserted node was inserted
	 * to the right, false it inserted to the left.
	 */
	private void checkHeight(Node current, boolean childWasRight, boolean gchildWasRight){
		int rightHeight = -1;
		int leftHeight = -1;
		
		if(current.getRight() != null)
			rightHeight = current.getRight().getHeight();
		if(current.getLeft() != null)
			leftHeight = current.getLeft().getHeight();
		
		if(Math.abs(rightHeight - leftHeight) > 1){
			if(childWasRight == gchildWasRight){
				if(childWasRight)
					current = RightRightRotation(current, current.getRight());
				else
					current = LeftLeftRotation(current, current.getLeft());
			}else{
				if(childWasRight){
					if(current.getRight().getLeft() != null)
						current = RightLeftRotation(current, current.getRight(), current.getRight().getLeft());
					else
						current = RightRightRotation(current, current.getRight());
				}else{
					if(current.getLeft().getRight() != null)
						current = LeftRightRotation(current, current.getLeft(), current.getLeft().getRight());
					else
						current = LeftLeftRotation(current, current.getLeft());
				}
			}
		}
		
		gchildWasRight = childWasRight;
		if(current.getParent() != null){
			if(current.getParent().getRight() != null){
				if(current.getParent().getRight().equals(current)){
					childWasRight = true;
				}else{
					childWasRight = false;
				}
			}else{
				childWasRight = false;
			}
			checkHeight(current.getParent(), childWasRight, gchildWasRight);
		}
		return;
	}
	
	//Finds the node with the given id and returns it
	public NodePackage find(int id){
		Node current = root;
		int count = 0;
		while(true){
			count++;
			if(id == current.getStudentID()){
				return new NodePackage(current, count);
			}
			if(id > current.getStudentID()){
				if(current.getRight() != null){
					current = current.getRight();
					continue;
				}
			}
			if(id < current.getStudentID()){
				if(current.getLeft() != null){
					current = current.getLeft();
					continue;
				}
			}
			return new NodePackage(null, count);
		}
	}
	
	//Executes Left Left rotation
	private Node LeftLeftRotation(Node K1, Node K2){
		Node parent = K1.getParent();
		
		//Move K2 where K1 was
		if(parent != null){
			if(parent.getRight() != null){
				if(parent.getRight().equals(K1)){
					parent.setRight(K2);
				}else{
					parent.setLeft(K2);
				}
			}else{
				parent.setLeft(K2);
			}
			K2.setParent(parent);
		}else{
			root = K2;
			K2.setParent(null);
		}
		
		//Move the right of K2 to the left of K1
		if(K2.getRight() != null){
			Node B = K2.getRight();
			K1.setLeft(B);
			B.setParent(K1);
		}else{
			K1.setLeft(null);
		}
		
		//Move K1 to the right of K2
		K2.setRight(K1);
		K1.setParent(K2);
		
		//Correct the heights
		if((K1.getRight() != null) && (K1.getLeft() != null)){
			if(K1.getLeft().getHeight() > K1.getRight().getHeight()){
				K1.setHeight(K1.getLeft().getHeight() + 1);
			}else{
				K1.setHeight(K1.getRight().getHeight() + 1);
			}
		}else if(K1.getRight() != null){
			K1.setHeight(K1.getRight().getHeight() + 1);
		}else if(K1.getLeft() != null){
			K1.setHeight(K1.getLeft().getHeight() + 1);
		}else{
			K1.setHeight(-1);
		}
		
		if(K2.getLeft() != null){
			if(K2.getLeft().getHeight() > K1.getHeight())
				K2.setHeight(K2.getLeft().getHeight() + 1);
		}else{
			K2.setHeight(K1.getHeight() + 1);
		}
		
		if(parent != null){
			if((parent.getRight() != null) && (parent.getLeft() != null)){
				if(parent.getLeft().getHeight() > parent.getRight().getHeight()){
					parent.setHeight(parent.getLeft().getHeight() + 1);
				}else{
					parent.setHeight(parent.getRight().getHeight() + 1);
				}
			}else{
				parent.setHeight(K2.getHeight() + 1);
			}
		}
		
		return K2;
	}
	
	//Executes Right Right rotation
	private Node RightRightRotation(Node K1, Node K2){
		Node parent = K1.getParent();
		
		//Move K2 where K1 was
		if(parent != null){
			if(parent.getRight() != null){
				if(parent.getRight().equals(K1)){
					parent.setRight(K2);
				}else{
					parent.setLeft(K2);
				}
			}else{
				parent.setLeft(K2);
			}
			K2.setParent(parent);
		}else{
			root = K2;
			K2.setParent(null);
		}
		
		//Move the left of K2 to the right of K1
		if(K2.getLeft() != null){
			Node B = K2.getLeft();
			K1.setRight(B);
			B.setParent(K1);
		}else{
			K1.setRight(null);
		}
		
		//Move K1 to the left of K2
		K2.setLeft(K1);
		K1.setParent(K2);
		
		//Correct the heights
		if((K1.getRight() != null) && (K1.getLeft() != null)){
			if(K1.getLeft().getHeight() > K1.getRight().getHeight()){
				K1.setHeight(K1.getLeft().getHeight() + 1);
			}else{
				K1.setHeight(K1.getRight().getHeight() + 1);
			}
		}else if(K1.getRight() != null){
			K1.setHeight(K1.getRight().getHeight() + 1);
		}else if(K1.getLeft() != null){
			K1.setHeight(K1.getLeft().getHeight() + 1);
		}else{
			K1.setHeight(-1);
		}
		
		if(K2.getRight() != null){
			if(K2.getRight().getHeight() > K1.getHeight())
				K2.setHeight(K2.getRight().getHeight() + 1);
		}else{
			K2.setHeight(K1.getHeight() + 1);
		}
		
		if(parent != null){
			if((parent.getRight() != null) && (parent.getLeft() != null)){
				if(parent.getLeft().getHeight() > parent.getRight().getHeight()){
					parent.setHeight(parent.getLeft().getHeight() + 1);
				}else{
					parent.setHeight(parent.getRight().getHeight() + 1);
				}
			}else{
				parent.setHeight(K2.getHeight() + 1);
			}
		}
		
		return K2;
	}

	//Executes Left Right rotation
	private Node LeftRightRotation(Node K1, Node K2, Node K3){
		Node parent = K1.getParent(); 
		
		//Move K3 where K1 was
		if(parent != null){
			if(parent.getRight() != null){
				if(parent.getRight().equals(K1)){
					parent.setRight(K3);
				}else{
					parent.setLeft(K3);
				}
			}else{
				parent.setLeft(K3);
			}
			K3.setParent(parent);
		}else{
			root = K3;
			K3.setParent(null);
		}
		
		//Move the left of K3 to the right of K2
		if(K3.getLeft() != null){
			Node B = K3.getLeft();
			K2.setRight(B);
			B.setParent(K2);
		}else{
			K2.setRight(null);
		}
		
		//Move K2 to the left of K3
		K3.setLeft(K2);
		K2.setParent(K3);
		
		//Move the right of K3 to the left of K1
		if(K3.getRight() != null){
			Node C = K3.getRight();
			K1.setLeft(C);
			C.setParent(K1);
		}else{
			K1.setLeft(null);
		}
		
		//Move K1 to the right of K3
		K3.setRight(K1);
		K1.setParent(K3);
		
		//Correct the heights
		if((K1.getRight() != null) && (K1.getLeft() != null)){
			if(K1.getLeft().getHeight() > K1.getRight().getHeight()){
				K1.setHeight(K1.getLeft().getHeight() + 1);
			}else{
				K1.setHeight(K1.getRight().getHeight() + 1);
			}
		}else if(K1.getRight() != null){
			K1.setHeight(K1.getRight().getHeight() + 1);
		}else if(K1.getLeft() != null){
			K1.setHeight(K1.getLeft().getHeight() + 1);
		}else{
			K1.setHeight(-1);
		}
		
		if((K2.getRight() != null) && (K2.getLeft() != null)){
			if(K2.getLeft().getHeight() > K2.getRight().getHeight()){
				K2.setHeight(K2.getLeft().getHeight() + 1);
			}else{
				K2.setHeight(K2.getRight().getHeight() + 1);
			}
		}else if(K2.getRight() != null){
			K2.setHeight(K2.getRight().getHeight() + 1);
		}else if(K2.getLeft() != null){
			K2.setHeight(K2.getLeft().getHeight() + 1);
		}else{
			K2.setHeight(-1);
		}
		
		if(K1.getHeight() > K2.getHeight()){
			K3.setHeight(K1.getHeight() + 1);
		}else{
			K3.setHeight(K2.getHeight() + 1);
		}
		
		return K3;
	}
	
	//Executes Right Left rotation
	private Node RightLeftRotation(Node K1, Node K2, Node K3){
		Node parent = K1.getParent(); 
		
		//Move K3 where K1 was
		if(parent != null){
			if(parent.getRight() != null){
				if(parent.getRight().equals(K1)){
					parent.setRight(K3);
				}else{
					parent.setLeft(K3);
				}
			}else{
				parent.setLeft(K3);
			}
			K3.setParent(parent);
		}else{
			root = K3;
			K3.setParent(null);
		}
		
		//Move the left of K3 to the right of K1
		if(K3.getLeft() != null){
			Node B = K3.getLeft();
			K1.setRight(B);
			B.setParent(K1);
		}else{
			K1.setRight(null);
		}
		
		//Move K1 to the left of K3
		K3.setLeft(K1);
		K1.setParent(K3);
		
		//Move the right of K3 to the left of K2
		if(K3.getRight() != null){
			Node C = K3.getRight();
			K2.setLeft(C);
			C.setParent(K2);
		}else{
			K2.setLeft(null);
		}
		
		//Move K2 to the right of K3
		K3.setRight(K2);
		K2.setParent(K3);
		
		//Correct the heights
		if((K1.getRight() != null) && (K1.getLeft() != null)){
			if(K1.getLeft().getHeight() > K1.getRight().getHeight()){
				K1.setHeight(K1.getLeft().getHeight() + 1);
			}else{
				K1.setHeight(K1.getRight().getHeight() + 1);
			}
		}else if(K1.getRight() != null){
			K1.setHeight(K1.getRight().getHeight() + 1);
		}else if(K1.getLeft() != null){
			K1.setHeight(K1.getLeft().getHeight() + 1);
		}else{
			K1.setHeight(-1);
		}
		
		if((K2.getRight() != null) && (K2.getLeft() != null)){
			if(K2.getLeft().getHeight() > K2.getRight().getHeight()){
				K2.setHeight(K2.getLeft().getHeight() + 1);
			}else{
				K2.setHeight(K2.getRight().getHeight() + 1);
			}
		}else if(K2.getRight() != null){
			K2.setHeight(K2.getRight().getHeight() + 1);
		}else if(K2.getLeft() != null){
			K2.setHeight(K2.getLeft().getHeight() + 1);
		}else{
			K2.setHeight(-1);
		}
		
		if(K1.getHeight() > K2.getHeight()){
			K3.setHeight(K1.getHeight() + 1);
		}else{
			K3.setHeight(K2.getHeight() + 1);
		}
		
		return K3;
	}
}
