import java.util.Stack;
class ArrayImplementionOfStack{
    int n;
    int i=0;
    int size=0;
    int arr[];

    ArrayImplementionOfStack(int n){
     this.n=n;
     arr=new int[n];
    }

     public void push(int val){
        if(i==n){
            System.out.println(new RuntimeException("Oops! The stack size is full!..."));
        }
        arr[i]=val;
        i++;
        size++;
     }

     public int pop(){
        if(i<0){
            return-1;
        }
        i--;
        size--;
        int val=arr[i];
        return val;
     }

     public int peek(){
        if(i<0 || i>=n){
            return-1;
        }
        return arr[i-1];
     }

     public boolean isEmpty(){
        return size==0;
     }

}
// this is for the Node in linked list
class Node{
    int data;
    Node next;
    Node(int data){
        this.data=data;
        this.next=null;
    }
}
 class LinkedListImplementionOfStack{
private Node topNode;
LinkedListImplementionOfStack(){
    this.topNode=null;
}

public void push(int val){
    Node newNode=new Node(val);
    if(topNode==null){
   topNode=newNode;
    }else{
        newNode.next=topNode;
        topNode=newNode;
    }
}
      public int pop(){
        if(topNode==null){
            return -1;
        }
          int popVal=topNode.data;
          topNode=topNode.next;
          return popVal;
      }

      public int peek(){
        if(topNode==null){
            return -1;
        }
        return topNode.data;
      }
    public boolean isEmpty(){
        return topNode==null;
    }
}
public class Stack1 { 
    public static boolean isValidParnathis(String s){
       Stack<Character>  st=new Stack<>();
       if(s.length()== 0)return false;
             for(char ch:s.toCharArray()){
                if(!st.isEmpty() && st.peek()=='(' && ch==')'){
                    st.pop();
                }else{
                    st.push(ch);
                }
             }
     return st.isEmpty();// for swap in the paranthish we need a formula (n/2+1)/2;
    }
    public static void main(String[] args) {
       /* System.out.println("this is the class of stack implemention !...");
        System.out.println("Is This balanced or Not "+isValidParnathis("((()))"));
        System.out.println(isValidParnathis(""));*/
       /* ArrayImplementionOfStack arrayImplementionOfStack=new ArrayImplementionOfStack(10);
       arrayImplementionOfStack.push(12);
       arrayImplementionOfStack.push(127);
       arrayImplementionOfStack.push(2);
       System.out.println( arrayImplementionOfStack.peek());
    System.out.println(arrayImplementionOfStack.pop());
    System.out.println(arrayImplementionOfStack.peek());
    System.out.println(arrayImplementionOfStack.isEmpty());*/
       LinkedListImplementionOfStack linkedListImplementionOfStack=new LinkedListImplementionOfStack();
       System.err.println(linkedListImplementionOfStack.isEmpty());
       linkedListImplementionOfStack.push(8);
       linkedListImplementionOfStack.push(83);
       linkedListImplementionOfStack.push(84);
       linkedListImplementionOfStack.push(38);
       linkedListImplementionOfStack.push(2);
       System.out.println(linkedListImplementionOfStack.peek());
       System.out.println(linkedListImplementionOfStack.pop());
       System.out.println(linkedListImplementionOfStack.isEmpty());

    }

}
