public class BST<T>{
	private Node<T> root;

	public BST(){
		this.root = null;
	}

	public void insert(T item){
		this.root = insert(root, item);
	}

	private Node<T> insert(Node<T> root, T item){
		if (root==null) {
			root = new Node<T>(item);
		}else if (((Comparable)item).compareTo(root.getData())<0) {
			root.setLeft(insert(root.getLeft(), item));
		}else{
			root.setRight(insert(root.getRight(), item));		
		}
		return root;
	}

	public Node<T> getRoot(){
		return this.root;
	}

	public T search(T item) {
        return searchRecursive(this.root, item);
    }

    private T searchRecursive(Node<T> current, T value) {
        if (current == null) {
            return null;
        }else if (((Comparable)value).compareTo(current.getData())==0) {
            return current.getData();
        }else if (((Comparable)value).compareTo(current.getData())<0) {
            return searchRecursive(current.getLeft(), value);
        }else{
            return searchRecursive(current.getRight(), value);
        }
    }

    public void remove(T item) {
        this.root = deleteRecursive(this.root, item);
    }

    public void removeAll(T item){

    }

    private Node<T> deleteRecursive(Node<T> current, T value) {
        if (current == null) {
            return null;
        }else if (((Comparable)value).compareTo(current.getData())==0) {
            if (current.getLeft() == null && current.getRight() == null) {
                return null;	// leaf root
            }
            if (current.getRight() == null) {
                return current.getLeft();	// root with one child (left)
            }
            if (current.getLeft() == null) {
                return current.getRight();	// root with one child (right)
            }

            T smallestValue = findSmallestValue(current.getRight()); // root with two children
            current.setData(smallestValue);
            current.setRight(deleteRecursive(current.getRight(), smallestValue));
            return current;
        }else if (((Comparable)value).compareTo(current.getData())<0) {
            current.setLeft(deleteRecursive(current.getLeft(), value));
            return current;
        }else{
        	current.setRight(deleteRecursive(current.getRight(), value));
        	return current;
        }
    }

    private T findSmallestValue(Node<T> root){
        T result = null;
        if (root.getLeft() == null){
            result = root.getData();
        }else{
            Node<T> current = root.getLeft();
            while (current.getLeft() != null){
                current = current.getLeft();
            }
            result = current.getData();
        }
        return result;
    }

    private T FindLargestValue(Node<T> root){
        T result = null;
        if (root.getRight() == null){
            result = root.getData();
        }else{
            Node<T> parent = root;
            Node<T> current = root.getRight();
            while (current.getRight() != null){
                parent = current;
                current = current.getRight();
            }
            result = current.getData();
        }
        return result;
    }

    public T removeMin(){
        T result = null;
        if (this.root.getLeft() == null){
            result = this.root.getData();
            this.root = this.root.getRight();
        }else{
            Node<T> parent = this.root;
            Node<T> current = this.root.getLeft();
            while (current.getLeft() != null){
                parent = current;
                current = current.getLeft();
            }
            result = current.getData();
            parent.setLeft(current.getRight());
        }
        return result;
    }
    public T removeMax(){
        T result = null;
        if (this.root.getRight() == null){
            result = this.root.getData();
            this.root = this.root.getLeft();
        }else{
            Node<T> parent = this.root;
            Node<T> current = this.root.getRight();
            while (current.getRight() != null){
                parent = current;
                current = current.getRight();
            }
            result = current.getData();
            parent.setRight(current.getLeft());
        }
        return result;
    }

    public void traverseInOrder(Node<T> root) { // LNR
        if (root != null) {
            traverseInOrder(root.getLeft());
            System.out.println(root.getData()+" ");
            traverseInOrder(root.getRight());
        }
    }

    public void traversePreOrder(Node<T> root) { // NLR
        if (root != null) {
            System.out.println(root.getData()+" ");
            traversePreOrder(root.getLeft());
            traversePreOrder(root.getRight());
        }
    }

    public void traversePostOrder(Node<T> root) { // LRN
        if (root != null) {
            traversePostOrder(root.getLeft());
            traversePostOrder(root.getRight());
            System.out.println(root.getData()+" ");
        }
    }

    private class Node<T>{
    	private T data;
    	private Node<T> left;
    	private Node<T> right;

    	public Node(T data){
    		this.setData(data);
    		this.setRight(null);
    		this.setLeft(null);
    	}
    	public void setData(T data){
    		this.data = data;
    	}
    	public void setLeft(Node<T> left){
    		this.left = left;
    	}
    	public void setRight(Node<T> right){
    		this.right = right;
    	}
    	public T getData(){
    		return this.data;
    	}
    	public Node<T> getLeft(){
    		return this.left;
    	}
    	public Node<T> getRight(){
    		return this.right;
    	}
    }

}