public class LinkedList<T>{
	private Node<T> first;
	private Node<T> last;
	private Node<T> currentNode;
	private int count;

	public LinkedList() {
		this.first = null;
		this.last = null;
		this.count = 0;
	}

	public void add(T item) {
		Node<T> n = new Node<T>(item);
		if (isEmpty()) {
			this.first = n;
		} else {
			this.last.setNext(n);
		}
		this.last = n;
		this.count++;
	}

	public void add(int position, T item) {
		if (position >= 0 && position <= count) {
			if (position == 0) {
				this.addBeginning(item);
			} else {
				Node<T> n = new Node<T>(item);
				this.currentNode = this.first;
				for (int i = 1; i <= count; i++) {
					if (i == position) {
						n.setNext(currentNode.getNext());
						this.currentNode.setNext(n);
					}
					this.currentNode = this.currentNode.getNext();
				} // For-Loop
				this.last = this.currentNode;
				this.count++;
			}
		}
	}

	public Node<T> getLinkedList() {
		if(!this.isEmpty())
			return first;
		else
			System.out.println("List is empty.");
		return null;
	}
	
	public Node<T> getLastNode() {
		if(!this.isEmpty())
			return last;
		else
			System.out.println("List is empty.");
		return null;
	}

	public T removeBeginning() {
		T value;
		if (!this.isEmpty()) {
			value = this.first.getData();
			this.first = this.first.getNext();
			this.count--;
			return value;
		}else{
			System.out.println("Linked List is null.");
			return null;
		}
	}

	public T remove(int position) {
		T value;
		if(!this.isEmpty()){
			value = null;
			if (position >= 0 && position < count) {
				if (position == 0) {
					value = removeBeginning();
				}else{
					currentNode = first;

					for (int i = 1; i <= count; i++) {
						if (i == position) {
							last = currentNode;
						}
						if (i == position + 1) {
							value = currentNode.getData();
							last.setNext(currentNode.getNext());
						}
						currentNode = currentNode.getNext();
					} // For-Loop
					last = currentNode;
					count--;
				}
			}
			return value;
		}else {
			System.out.println("Linked List is null.");
			return null;
		}
	}

	public T removeLast() {
		return remove(this.size() - 1);
	}

	public void replace(int position, T item) {
		if (position >= 0 && position < count) {
			if (position == 0) {
				updateBeginning(item);
			} else {
				currentNode = first;
				for (int i = 0; i < count; i++) {
					if (i == position) {
						currentNode.setData(item);
					}
					currentNode = currentNode.getNext();
				} // For-Loop
			}
		} // outer-if
	} // Replace-Method

	public void clear() {
		this.first = this.last = this.currentNode = null;
		this.count = 0;
	}

	public boolean isEmpty() {
		return this.count == 0 && this.first == null && this.last == null;
	}

	public T get(int position) {
		T value = null;
		if (position >= 0 && position < count) {
			currentNode = first;
			for (int i = 0; i < this.count; i++) {
				if (i == position)
					value = currentNode.getData();

				currentNode = currentNode.getNext();
			}
		} // if
		return value;
	} // get-Method

	public boolean contains(T item) {
		currentNode = first;
		while (currentNode != null) {
			if (currentNode.getData().equals(item))
				return true;
			
			currentNode = currentNode.getNext();
		}
		return false;
	}

	public int search(T item) {
		currentNode = first;
		int i = 0;
		while (currentNode != null) {
			if (currentNode.getData().equals(item)) {
				return i;
			}
			currentNode = currentNode.getNext();
			i++;
		}
		return -1;
	}

	public int size() {
		return this.count;
	}

	public T[] toArray() {
		T[] nodesArray = (T[]) new Object[count];
		currentNode = first;
		int i = 0;
		while (currentNode != null) {
			nodesArray[i] = currentNode.getData();
			currentNode = currentNode.getNext();
			i++;
		}
		return nodesArray;
	}

	public void print() {
		currentNode = first;
		System.out.print("[");
		while (currentNode != null) {
			System.out.print(currentNode.getData());
			if (currentNode.getNext() != null) {
				System.out.print(",");
			}
			currentNode = currentNode.getNext();
		}
		System.out.println("]");
	}

	public void printReverse() {
		System.out.print("[");
		printRecursive(first);
		System.out.println("]");
	}

	private void printRecursive(Node<T> temp) {
		if (temp != null) {
			printRecursive(temp.getNext());
			if (temp.getNext() != null) {
				System.out.print(",");
			}
			System.out.print(temp.getData());
		}
	}

	public void addBeginning(T item) {
		Node<T> n = new Node<T>(item);
		if (isEmpty()) {
			this.first = n;
			this.last = n;
		} else {
			n.setNext(first);
			first = n;
		}
		this.count++;
	} // AddBeginning-Method;

	public void updateBeginning(T item) {
		first.setData(item);
	}
	
	public <Object> void bubbleSort(){
        Node<T> index = null;
        this.currentNode = this.first;
        
        if (this.isEmpty()) {
            System.out.println("Cannot sort List because list is empty.");
        }else{
            while (this.currentNode != null) {
            	System.out.println("2");
                index = (Node<T>) currentNode.getNext();
                while (index != null) {
                	System.out.println(index.getData());
                    // If current node's data is greater
                    // than index's node data, swap the data
                    // between them
                    if (((Comparable)currentNode.getData()).compareTo(index.getData())<0) {
                        T temp = (T) currentNode.getData();
                        currentNode.setData(index.getData());
                        index.setData(temp);
                    }
                    index = index.getNext();
                }
                currentNode = currentNode.getNext();
            }
        }
	}
	
	private class Node<T>{
		private T data;
		private Node<T> next;

		public Node(T data){
			this.setData(data);
			this.setNext(null);
		}

		public void setData(T data){
			this.data = data;
		}
		public void setNext(Node<T> next){
			this.next = next;
		}
		public T getData(){
			return this.data;
		}
		public Node<T> getNext(){
			return this.next;
		}
	}
	
}