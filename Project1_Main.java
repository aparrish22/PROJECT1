/* 
 * TODO
 * *** new parameter in find(NodePackage _)
 * NodePackage.java class will have 2 variables, the Node and an interger (will pass the size into it)
 * to indicate how many nodes the node has passed through
 *  
 * &
 * minID & maxID
 * 
 * 
 * 
 * TODO
 * Atici wants us to be able to pass T1 or T2
 * Like int minID(Project1 tree) then do tree.min().getID();
 */
import java.util.Collection;
import java.util.Iterator;

public class Project1_Main<T> {
    private BinarySearchTree<Node> BST;
    private AVLTree AVL;

    public Project1_Main() {
        super();
        BST = new BinarySeacrhTree<>();
        AVL = new AVLTree();
    }

    // project find method, not BST or AVL find
    // will call find from respective tree
    // note: AVL: find(int id)
    public int find(Object tree, int id) {
        NodePackage nodepack = tree.find(id);
        if (node == null)
			return null;
		else 
			return node.getNode().getID();
    }

    public Node minID(Object tree) {
        return tree.min();
    }

    public Node maxID(Object tree) {
        return tree.max();
    }

    public static void main(String args[]) {
        BinarySearchTree<Node> T1 = new BinarySearchTree<>();
        

        int amountOfStudents = 10;
        // inserting sample students
        for (int i = 5; i < amountOfStudents; i++) {
            Node student = new Node(i, "name", 4, null);
            T1.insert(student);
        }

        for (int i = 4; i >= 0; i--) {
            Node student = new Node(i, "name", 4, null);
            T1.insert(student);
        }

        System.out.print(T1.inOrder());

        


        // Iterator it = T1.iterator();
		
		// while (it.hasNext())
		// 	System.out.println(it.next());

    }

}
