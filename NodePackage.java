/* This class stores a node and the number of nodes passed through to find the node,
 * so our find methods can return both the node it found and the number of comparisons
 * made
 */
package project;

public class NodePackage {
	private Node node;
	private int count;
	
	public NodePackage(Node n, int i){
		node = n;
		count = i;
	}

	public Node getNode() {
		return node;
	}

	public int getCount() {
		return count;
	}
	
}
