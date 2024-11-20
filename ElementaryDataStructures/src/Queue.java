
public class Queue<T extends Comparable<T>> {
	private LinkedListNode<T> head;
	private LinkedListNode<T> tail;
	public Queue (T elem) {
		head = new LinkedListNode<>(elem);
		tail = head;
	}
	public Queue() {
		this(null);
	}
	
	public void enqueue(T elem) {
		LinkedListNode<T> tmp = new LinkedListNode<>(elem);
		if (head == null) {
			head = tmp;
			tail = head;
			return;
		}
		tail.setNext(tmp);
		tail = tmp.getNext();
	}
	public T dequeue() {
		if (head == null) {
			return null;
		}
		T first = head.getElem();
		head.setNext(head.getNext());
		return first;
	}
	public void appendQueue(Queue<T> q) {
		if (head == null) {
			head = q.head;
			tail = q.tail;
			return;
		}
		tail.setNext(q.head);
		tail = q.tail;
	}
}
