/* 
 *
 */
import java.util.Collection;
import java.util.Iterator;

public class Project1_Main<T> {
    public static void main(String args[]) {
	BinarySearchTree<Node> BST = new BinarySearchTree<>();
	AVLTree AVL = new AVLTree();
	TreeManager trees = new TreeManager();
	File input = new File("input.txt");
	Scanner reader = new Scanner(input);
	Scanner user = new Scanner(System.in);
		
	//Build the BST and AVL trees
	int count = reader.nextInt();
	for(int i = 1; i <= count; i++){
		int id = reader.nextInt();
		String name = reader.next();
		double gpa = reader.nextDouble();
		
		trees.insert(BST, new Node(id, name, gpa, null));
		trees.insert(AVL, new Node(id, name, gpa, null));
	}
		
	boolean run = true;
	while(run){
		System.out.print("What would you like to do? Enter [Find, Max, Min, or Quit]: ");
		String command = user.next().toLowerCase();
		switch(command){
		
		//Quits out of the loop
		case "quit":
			run = false;
			break;
			
		//Finds the given node
		case "find":
			System.out.print("What is the id of the desired student: ");
			int id = user.nextInt();
			NodePackage BSTResult = trees.find(BST, id);
			NodePackage AVLResult = trees.find(AVL, id);
			System.out.println("Student Info \t BST Comp \t AVL Comp\t");
			System.out.println(BSTResult.getNode().getID() + "\t" + BSTResult.getCount() + "\t" + AVLResult.getCount() + "\n");
			break;
			
		//Finds the student with the maximum id number
		case "max":
			BSTResult = trees.maxID(BST);
			AVLResult = trees.maxID(AVL);
			
			System.out.println("Student Info \t BST Comp \t AVL Comp\t");
			System.out.println(BSTResult.getNode().getID() + "\t" + BSTResult.getCount() + "\t" + AVLResult.getCount() + "\n");
			break;
			
		//Finds the student with the minimum id number
		case "min":
			BSTResult = trees.minID(BST);
			AVLResult = trees.minID(AVL);
			
			System.out.println("Student Info \t BST Comp \t AVL Comp\t");
			System.out.println(BSTResult.getNode().getID() + "\t" + BSTResult.getCount() + "\t" + AVLResult.getCount() + "\n");
			break;
			
		default:
			System.out.println("Error. Please enter [Find, Max, Min, or Quit]");
			continue;
		}
	}
	System.out.println("Thanks. Goodbye.");
    }

}
