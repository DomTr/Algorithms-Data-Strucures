
public class LinkedListNode<T> {
	private T elem;
	private LinkedListNode<T> next;
	
	public LinkedListNode(T val, LinkedListNode<T> next) {
		this.elem = val;
		this.next = next;
	}
	public LinkedListNode(T val) {
		this(val, null);
	}
	public LinkedListNode() {
		this(null, null);
	}
	
	public T get(int k) {
		LinkedListNode<T> tmp = this;
		while (tmp.next != null && k > 0) {
			tmp = tmp.next;
			k--;
		}
		return tmp.elem;
	}
	public void append(LinkedListNode<T> node) { // coule go to infinite loop if there is a cycle between list nodes
		LinkedListNode<T> tmp = this;
		while (tmp.next != null) {
			tmp = tmp.next;
		}
		tmp.next = node;
	}
	private LinkedListNode<T> getPrev(int k) {
		LinkedListNode<T> tmp = this;
		while (tmp.next != null && k > 0) {
			tmp = tmp.next;
			k--;
		}
		return tmp;
	}
	public void insertAt(LinkedListNode<T> node, int k) {
		LinkedListNode<T> tmp = getPrev(k);
		node.next = tmp.next;
		tmp.next = node;
	}
	public T deleteAt(int k) {
		LinkedListNode<T> tmp = getPrev(k);
		T ans = null;
		if (tmp.next == null) {
			tmp.next = null;
		} else {
			ans = tmp.elem;
			tmp.next = tmp.next.next;
		}
		return ans;
	}
	public T getElem() {
		return elem;
	}
	public LinkedListNode<T> getNext() {
		return next;
	}
	public void setElem(T elem) {
		this.elem = elem;
	}
	public void setNext(LinkedListNode<T> next) {
		this.next = next;
	}
}
