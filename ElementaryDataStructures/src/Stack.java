
public class Stack<T extends Comparable<T>> {
	private LinkedListNode<T> head;
	
	public Stack(T elem) {
		head = new LinkedListNode<>(elem);
	}
	public Stack(Stack<T> st) {
		head = st.head;
	}
	public Stack() {
		head = new LinkedListNode<>();
	}
	
	public void push(T elem) {
		head = new LinkedListNode<>(elem, head);
	}
	
	public T pop() {
		if (head == null) {
			return null; // Default value
		}
		T curr = head.getElem();
		head = head.getNext();
		return curr;
	}
	public T get() {
		if (head == null) {
			return null;
		}
		return head.getElem();
	}

	public void appendStack(Stack<T> st) {
		LinkedListNode<T> tmp = head;
		if (head == null) {
			head = st.head;
			return;
		}
		while (tmp.getNext()!=null) {
			tmp = tmp.getNext();
		}
		tmp.setNext(st.head);
	}
}
