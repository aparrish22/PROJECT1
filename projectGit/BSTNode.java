package projectGit;

public class BSTNode<T> {
	T info;
	BSTNode<T> left, right;

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public BSTNode<T> getLeft() {
		return left;
	}

	public void setLeft(BSTNode<T> left) {
		this.left = left;
	}

	public BSTNode<T> getRight() {
		return right;
	}

	public void setRight(BSTNode<T> right) {
		this.right = right;
	}

	public BSTNode(T info, BSTNode<T> left,
			BSTNode<T> right) {
		super();
		this.info = info;
		this.left = left;
		this.right = right;
	}

}
