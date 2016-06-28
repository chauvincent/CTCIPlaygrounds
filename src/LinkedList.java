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
		numberList.addValueAtIndex(20, 1);
		numberList.addValue(30);
		numberList.addValue(40);
		numberList.removeDataAt(1);
		numberList.removeDataWithValue(40);
		numberList.print();
		
		
		
	}
	
}

