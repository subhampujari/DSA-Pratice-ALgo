class Node{
    int data;
    Node next;
    Node(int data){
      this.data=data;
      next=null;
    }
}
class LinkedListImplemention{
    private Node head;
    LinkedListImplemention(){
        head=null;
    }
    //addlast Mehtod
    public void addLast(int val){
       Node newNode=new Node(val);
       if(head==null){
        head=newNode;
       }else{
        Node currNode=head;
          while(currNode.next!=null){
            currNode=currNode.next;
          }
          currNode.next=newNode;
       }
    }
     // addFirst method
     public void addFirst(int val){
        Node newNode=new Node(val);
        if(head==null){
            head=newNode;
        }
        newNode.next=head;
        head=newNode;
     }
         
     // deleteFirst
     public int removeFirst(){
        if(head==null){
            System.out.println("Linked List is Empty!..");
        }
        int val=head.data;
        head=head.next;
        return val;
     }
     public int removeLast(){
        if(head==null){
            System.out.println("LinkedList Is Empty!..");
        }
        Node currNode=head;
        while(currNode.next!=null){
            currNode=currNode.next;
        }
        int val=currNode.data;
       currNode.next=null;
       return val;
     }
     public void delete(int data) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        if (head.data == data) {
            head = head.next; // If the element to be deleted is at the head
            return;
        }

        Node current = head;
        while (current.next != null && current.next.data != data) {
            current = current.next; // Traverse until we find the node
        }

        if (current.next == null) {
            System.out.println("Element " + data + " not found.");
        } else {
            current.next = current.next.next; // Remove the node
        }
    }

    // display the LinkedList
    public void display(){
        if(head==null){
            System.out.println("null");
            return;
        }
        Node currNode=head;
        while (currNode!=null) {
            System.out.print(currNode.data);
            currNode=currNode.next;
        }
        System.out.println("null");
    }

   // search the val in the linked list
   public boolean search(int val){
    if(head==null){
        System.out.println("LinkedList is empty!");
        return false;
    }
    if(head.data==val){
      return true;
    }
    Node currNode=head;
    while(currNode!=null){
        if(currNode.data==val){
            return true;
        }
        currNode=currNode.next;
    }
    System.out.println("the element is not found!..");
    return false;
   }
   
}
public class LinkedList1 {
    public static void main(String[] args) {
        System.out.println("This is LinkedList Implemention Class !...");
     
    }
}
