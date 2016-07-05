public class LinkedList {
	private Node head;
	private int count;
	
	public LinkedList(){
		head = new Node(null);
		count = 0;
	}
	public void addValue(Integer data){
		Node temp = new Node(data);
		Node current = head;
		while(current.getNext() != null){
			current = current.getNext();
		}
		current.setNext(temp);
		count++;
	}
	public void addValueAtIndex(Integer data, int index){
		Node temp = new Node(data);
		Node current = head;
		for(int i = 1; i < index && current.getNext() != null; i++){
			current = current.getNext();
		}
		temp.setNext(current.getNext());
		current.setNext(temp);
		count++;
	}
	public boolean removeDataAt(int index){
		if(index < 1 || index > size()){
			return false;
		}
		Node current = head;
		for(int i = 0; i < index; i++){
			if(current.getNext() == null)
				return false;
			current = current.getNext();
		}
		current.setNext(current.getNext().getNext());
		count--;
		return true;
	}
	public boolean removeDataWithValue(Integer d){
		Node temp = head;
		if(temp.data == d){
			head = temp.getNext();
			return true;
		}
		while(temp.getNext() != null){
			if(temp.next.data == d){
				temp.next = temp.next.next;
				return true;
			}
			temp = temp.next;
		}
		return false;
	}
	public int size(){
		return count;
	}
	public void print(){
		Node temp = head.next;
		while(temp != null){
			System.out.print(temp.data + " ");
			temp = temp.next;
		}
	}
// 2.1 Remove Duplicates from linked list
	public void removeDuplicate(){
		Node first = head.next; 
		Node runner = first;
		while(runner != null && first != null){
			runner = first;
			while(runner.next!=null){
				if(runner.next.data == first.data ){
					Node duplicate = runner.next;
					runner.next = runner.next.next;
				}else{
					runner = runner.next;
				}
			}
			first = first.next;
		}
	}
// 2.2 Find the Kth to last element in a linked list
	public int kthToLastRecursive(Node curNode, int index){
		// Note knowing the count of an linkedlist we can just (count - index)
			// In Theory:
			//  10 20 30 40 50 60 70
			//  ->  -> -> -> -> -> 0
			//               <- <- <- // 1+1+1 k is 4, so 50
		int count = 0;
		
		if (curNode == null){  //base case to get length of linked list, returns 0 at the end of list
			return 0;
		}
		int i = kthToLastRecursive(curNode.next, index) + 1; // recurse the last node (0), and on return keep track of i until == index
		if(i == index){
			System.out.println("");
			System.out.println(index + "th to last node is " + curNode.data);
		}
		return i;
	}
	public Node kthToLastIterative(Node head, int k){
		// In Theory:  Sliding window approach
		//  10 20 30 40 50 60 70
		//  /\    /\
		//     /\    /\
	    //        /\    /\
	    //           /\    /\
	    //               /\   /\
		Node runner = head.next;
		head = head.next;
		for(int i = 0; i < k; i++)
			runner = runner.next;
		
		while(runner != null){
			runner = runner.next;
			head = head.next;
		}
		//System.out.print(head.data);
		return head;
	}
// 2.3 Delete middle node, when given access only to that node 
/* Example: Input: Node c,     a->b->c->d->e
			Output: no return but a->b->d->e
 	Not allowed access to head node
*/
	public void deleteMiddleNode(Node middleNode){
		// In Theory:  10 20 30 40 50 60 70
		//             10 20 30 50 60 70
		middleNode.data = middleNode.next.data;    // Middle Node C  = D's Data
		middleNode.next = middleNode.next.next;   //  Middle Node C -> E
		
	}
// 2.4 Partition: Write code to partition a linkedlist around a value X such that all nodes less than x comes before all nodes greater than or equal to x. 
//	If X is contained within the list, the values of x only need to be after the elements less than x
//  Input:   3->5->8->5->10->2->1  [partition = 5]
//  Output:  3->1->2->10->5->5->8
/*	In Theory:
 * 			3 5 8 5 10 2 1
 *		    /\                 Left List:
 *			  /\			   Right List:
*/
	public void partitionFirstAttempt(int value){
		Node headPtr = head.next;
		LinkedList leftList = new LinkedList();
		LinkedList rightList = new LinkedList();
		Node leftEnd = leftList.head;
		while(headPtr != null){
			if(headPtr.data < value){
				leftList.addValue( headPtr.data);
				leftEnd = leftEnd.next;
			}else{
				rightList.addValue( headPtr.data);
			}
			headPtr = headPtr.next;
		}
		System.out.println("\nMERGED:");
		leftEnd.next = rightList.head.next;
		leftList.print();
	}
// 2.4 Partition Optimized
/*	In Theory:
 * 	Have a head and tail pointer
 *  Insert at head or tail depending on value
 *  reset the new head and tail then traverse
 */     
	public void partitionOptimized(int value){
				
	}
//	2.5 Sum Lists
/*  You have two numbers represented by a linked list. Each node contains a single digit
 *  Digit are stored in reverse order, 1's digit is at the head. Write a function that adds the two numbers
 *  and returns the sum as a linked list
 *  Example:
 *  Input: (7->1->6) (5->9->2)  that is 617 + 295
 *  Output: (2->1->9) which is 912
 *   7 1 6
 * + 5 9 2
 * --------
 *   2
 *   7 + 5 = 12, carry 1
 *   2 1
 *   1 + 9 = 10 carry 1
 *   2 1 9
 */
	// if we know count
	public Node sumList(Node L1, Node L2, int carry){
		// base case
		if(L1 == null && L2 == null && carry == 0){
			return null;
		}
		
		int value = carry + L1.data + L2.data;		
		Node sum = new Node(value % 10);
		
		// Recurse
		if(L1 != null || L2 != null){
			int carr = value >= 10 ? 1 : 0;
			Node temp = sumList( L1.next, L2.next, carr);
			sum.setNext(temp);
		}
		return sum;
		
	}
	public Node merge(Node L1, Node L2){
		if (L1 == null)
			return L1;
		if (L2 == null)
			return L2;
		if(L1.data < L2.data){
			L1.next = merge(L1.next, L2.next);
			return L1;
		}else{
			L2.next = merge(L2.next, L1.next);
			return L2;
		}
	}
// 2.6 Palindrome: check if a linked list is a palindrome
/*
 * 
 */


// Node Class Declaration
	private class Node{
		private Node next;
		private Integer data;
		public Node(Integer data){
			next = null;
			this.data = data;
		}
		public Integer getData(){
			return data;
		}
		public void setData(Integer _number){
			data = _number;
		}
		public Node getNext(){
			return next;
		}
		public void setNext(Node _next){
			next = _next;
		}
	}
	public static void main(String[] args) {
		LinkedList numberList = new LinkedList();
		numberList.addValue(10);
		numberList.addValue(60);
		numberList.addValue(30);
		numberList.addValue(40);
		numberList.addValue(50);
		numberList.addValue(60);
		numberList.addValue(10);
		numberList.addValue(60);
		numberList.addValue(60);
		//numberList.print();	
//		numberList.removeDuplicate();
//		System.out.println("REMOVE DUP CALLED");
//		numberList.print();
//		numberList.kthToLastRecursive(numberList.head, 3);
//		Node middleNode = numberList.kthToLastIterative(numberList.head, 4);
//		numberList.print();
//		System.out.println("\nRemove middle node");
//		numberList.deleteMiddleNode(middleNode);
//		numberList.print();
//		numberList.partitionFirstAttempt(50);
		LinkedList L1 = new LinkedList();
		LinkedList L2 = new LinkedList();
		L1.addValue(7);
		L1.addValue(1);
		L1.addValue(6);
		L2.addValue(5);
		L2.addValue(9);
		L2.addValue(2);
		int carry = 0;
		Node head = numberList.sumList(L1.head.next, L2.head.next, carry);
		while(head!=null){
			System.out.print(" " + head.data);
			head = head.next;
		}

		/*  7 1 6
		 * + 5 9 2
		 * --------
		 */
		
	}	
}

