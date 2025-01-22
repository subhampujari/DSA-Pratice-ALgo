
class ArrayImplementionOfQueue{
    int front;
    int rear;
    int capacity;
    int arr[];
    int size;
    ArrayImplementionOfQueue(int capacity){
        this.capacity=capacity;
        this.front=0;
        this.rear=-1;
       this.arr=new int[capacity];
        this.size=0;
    }

     public void offer(int val){
        if(size==capacity){
            System.out.println("Queue Size is full!..");
            return;
        }
       rear=(rear+1)%capacity;
       arr[rear]=val;
       size++;
     }

   public int remove(){
    if(size==0){
        return -1;
    }
    int value=arr[front];
    front=(front+1)%capacity;
    size--;
    return value;
   }

   public int peek(){
      if(size==0){
        return -1;
      }
      return arr[front];
   }

   public int getSize(){
    return size;
   }

   public boolean isEmpty(){
    return size==0;
   }

   public boolean isFull(){
    return size == capacity;
   }

}

// linkedList Node Class 
class Node{
    int data ;
    Node next;
    Node(int data ){
        this.data=data;
        next=null;
    }
}

class LinkedListImplementationOfQueue{
   int size;
   Node front,rear;
// constrcutor
LinkedListImplementationOfQueue(){
    this.size=0;
    this.front=null;
    this.rear=null;
}
 
// add method or offer method
public void offer(int val){
    Node newNode=new Node(val);
    if(rear==null){
        front=rear=newNode;
    }else{
        rear.next=newNode;
        rear=rear.next;
    }
    size++;
}

   // remove method
   public int remove(){
    if(front==null){
        System.out.println("Queue is Empty!..");
        return -1;
    }
    int val=front.data;
    front=front.next;
    if(front==null){
        rear=null;
    }
    size--;
    return val;
   }

  public boolean isEmpty(){
    return size==0;
  }

  public int peek(){
    if(front==null){
        System.out.println("Queue Is Empty!..");
        return -1;
    }
    return front.data;
  }
   public int getSize(){
    return size;
   }
}
public class Queue1 {
    public static void main(String[] args) {
        System.out.println("This class baiscally for the Queue Implemention in array and linkedList!.....");
        ArrayImplementionOfQueue arrayImplementionOfQueue=new ArrayImplementionOfQueue(5);
        System.out.println(arrayImplementionOfQueue.isEmpty());
        System.out.println(arrayImplementionOfQueue.isFull());
        System.out.println(arrayImplementionOfQueue.getSize());
        arrayImplementionOfQueue.offer(23);
        arrayImplementionOfQueue.offer(243);
        arrayImplementionOfQueue.offer(3);
        arrayImplementionOfQueue.offer(63);
        System.out.println(arrayImplementionOfQueue.peek());
        System.out.println(arrayImplementionOfQueue.remove());
        System.out.println(arrayImplementionOfQueue.isEmpty());
        System.out.println(arrayImplementionOfQueue.isFull());
        System.out.println(arrayImplementionOfQueue.getSize());

        LinkedListImplementationOfQueue linkedListImplementationOfQueue=new LinkedListImplementationOfQueue();
        System.out.println(linkedListImplementationOfQueue.isEmpty());
        System.out.println(linkedListImplementationOfQueue.getSize());
        linkedListImplementationOfQueue.offer(323);
        linkedListImplementationOfQueue.offer(524);
        linkedListImplementationOfQueue.offer(263);
        linkedListImplementationOfQueue.offer(233);
        System.out.println(linkedListImplementationOfQueue.peek());
        System.out.println(linkedListImplementationOfQueue.remove());
        System.out.println(linkedListImplementationOfQueue.isEmpty());
        System.out.println(linkedListImplementationOfQueue.getSize());
       
    }
}
