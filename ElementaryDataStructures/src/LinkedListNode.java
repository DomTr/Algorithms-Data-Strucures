
public class LinkedListNode<T extends Comparable<T>> {
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
	public void append(LinkedListNode<T> node) { // could go to infinite loop if there is a cycle between list nodes
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
	
	public LinkedListNode<T> sortList(LinkedListNode<T> head) {
	     if (head == null || head.next == null) {
	       return head;
	     }
	     LinkedListNode<T> slow = head, fast = head.next;
	     while (fast != null && fast.next != null) {
	       slow = slow.next;
	       fast = fast.next.next;
	     }
	     LinkedListNode<T> mid = slow.next;
	     slow.next = null;
	     
	     LinkedListNode<T> leftHalf = sortList(head);
	     LinkedListNode<T> rightHalf = sortList(mid);
	     return merge(leftHalf, rightHalf);
	}
	
	public LinkedListNode<T>  merge( LinkedListNode<T>  leftHalf, LinkedListNode<T>  rightHalf) {
		 LinkedListNode<T>  dummyHead = new LinkedListNode<T> ();
		 LinkedListNode<T>  current = dummyHead;
	     while (leftHalf != null && rightHalf != null) {
	       if (leftHalf.elem.compareTo(rightHalf.elem) < 0) {
	         current.next = leftHalf; 
	         leftHalf = leftHalf.next;
	       } else {
	         current.next = rightHalf;
	         rightHalf = rightHalf.next;
	       }
	       current = current.next;
	     }
	     if (leftHalf == null) {
	       current.next = rightHalf;
	     }
	     else if (rightHalf == null) {
	       current.next = leftHalf;
	     }
	     return dummyHead.next;
	}
	
	public LinkedListNode<T> bubbleSort(LinkedListNode<T> head) {
		LinkedListNode<T> tmp;
	     boolean sorted = false;
	     while (!sorted) {
	       sorted = true;
	       tmp = head;
	       while (tmp != null && tmp.next != null) {
	         if (tmp.elem.compareTo(tmp.next.elem) > 0) {
	           sorted = false;
	           swapValues(tmp, tmp.next);
	         }
	         tmp = tmp.next;
	       }
	     }
	     return head;
	   }
	   public void swapValues(LinkedListNode<T> a, LinkedListNode<T> b) {
	     T tmp = a.elem;
	     a.elem = b.elem;
	     b.elem = tmp;
	   }
	   public int getLength(LinkedListNode<T> head) {
	     int ans = 0;
	     LinkedListNode<T> tmp = head;
	     while (tmp != null) {
	       ans++;
	       tmp = tmp.next;
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
