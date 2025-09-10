import java.util.Arrays;

public class AList<T>{
	private T[] list;
	private int count;
	
	public AList(){
		this.count = 0;
		list = (T[]) new Object[25];
	} 

	public AList(int size){
		if(size < 25){
			size = 25;
			this.count = 0;
			list = (T[]) new Object[size];
		}
	}	

	public void add(T item){
		this.list[count++] = item;
		checkCapacity();
	}
	public void add(int position, T item){
		if(position >=0 && position <= count){
			for (int i = count; i >= position ;i-- ) {
				list[i] = list[i - 1];
			}
			list[position] = item;
			checkCapacity();
			count++;
		}
	}

	private void checkCapacity(){
		if(count == this.list.length){
			T[] newList = (T[]) new Object[this.list.length * 2];
			for (int i = 0; i < this.list.length; i++) {
				newList[i] = list[i];
			}
			list = newList;
		}
	}

	public T remove(int position){
		T value;
		if(position >=0 && position < count){
			T[] newList = (T[]) new Object[this.list.length];
			int j = 0;
			value = this.list[position];
			for (int i = 0; i < this.count - 1; i++) {
				if(i==position) {
					j++;
					newList[i] = this.list[j];
				}else {
					newList[i] = this.list[j];
				}
				j++;
			}
			this.list = newList;
			count--;
			return value;
		}else {
			return null;
		}
	}
	
	public void remove(T value){
			T[] newList = (T[]) new Object[this.list.length];
			int j = 0;
			for (int i = 0 ; i<this.size(); i++) {
				if(((Comparable)this.list[i]).compareTo(value)==0) {
					i++;
					newList[j] = this.list[i];
				}else {
					newList[j] = this.list[i];
				}
				j++;
			}
			this.list = newList;
			count--;
	}
	
	public T removeLast() {
		return remove(size()-1);
	}
	
	public void replace(int position, T item){
		if(position >=0 && position < count){
			this.list[position] = item;
		}
	}
	public void clear(){
		this.count = 0;
	}
	public boolean isEmpty(){
		return this.count == 0;
	}
	public T get(int position){
		T value = null;
		if(position >=0 && position < count){
			value = this.list[position];	
		}
		return value;
	}
	public boolean contains(T item){
		for (int i = 0; i < count ; i++) {
			if(this.list[i].equals(item))
				return true;
		}
		return false;
	}

	public <T> int search(T item){
		sort();
		int start = 0, mid, end = size()-1;
		while(start<=end) {
			mid = (start+end)/2;
			if(this.list[mid].equals(item))
				return mid;
			else if (((Comparable)list[mid]).compareTo(item)>0)
                end = mid - 1;
            else
                start = mid + 1;
            	
		} // While-Loop
		return -1;	
	}

	public int size(){
		return this.count;
	}
	public T[] toArray(){
		T[] arr = (T[]) new Object[this.count];
		for (int i = 0; i < count ; i++) {
			arr[i] = this.get(i);
		}
		return arr;
	}

	public void print(){
		System.out.print("[");
		for (int i = 0;i < count ; i++) {
			System.out.print(this.list[i]);
			if(i<count-1){
				System.out.print(",");
			}
		}
		System.out.println("]");
	}

	public <object> void sort(){
        T temp;
        for (int i = 0; i < size()-1; i++) {
            for (int j = i + 1; j < size(); j++) {
                if (i != j) {
                    if (((Comparable) list[j]).compareTo((T) list[i])<0) {
                        temp = (T) list[i];
                        list[i] = list[j];
                        list[j] = (T) temp;
                    } // Inner-if
                } // Outer-if
            } // Inner-For-Loop
        } // Outer-For-Loop	
	}
	
	public void addBeginning(T item){
		checkCapacity();
		for (int i = this.count; i>0 ; i--) {
			this.list[i] = this.list[i-1];
		}
		this.list[0] = item;
		count++;
	}

	public void updateBeginning(T item){
		this.list[0] = item;
	}
	public T removeBeginning(){
		return remove(0);
	}

}