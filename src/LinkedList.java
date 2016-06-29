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
		numberList.addValue(20);
		numberList.addValue(30);
		numberList.addValue(40);
		numberList.addValue(50);
		numberList.addValue(60);
		numberList.addValue(70);
		numberList.addValue(60);
		numberList.addValue(60);
		numberList.print();	
		numberList.removeDuplicate();
		System.out.println("REMOVE DUP CALLED");
		numberList.print();
		
	}	
}

