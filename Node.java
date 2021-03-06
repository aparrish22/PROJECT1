//This class is a Node for the BST and AVL Trees, and stores all the student information.
public class Node implements Comparable<Node>{
	//Student Variables
	private int ID;
	private String name;
	private double gpa;
	
	//Node Variables
	private Node parent;
	private Node left;
	private Node right;
	private int height; //Height of the tree at this node
	
	//Constructor
	public Node (int i, String n, double g, Node p){
		ID = i;
		name = n;
		gpa = g;
		parent = p;
		left = right = null;	
	}

	@Override
	public int compareTo(Node arg0) {
		return Integer.toString(this.getID()).compareTo(Integer.toString(arg0.getID()));
	}

	@Override
	public String toString() {
		return "Student ID: " + this.getID() + "\nStudent Name: " + this.getName() + "\nStudent GPA: " + this.getGpa() + "\n";
	}
	
	//Getters and setters
	// to make compatible with class BinarySearchTree.java
	public int getInfo() {
		return getID();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
