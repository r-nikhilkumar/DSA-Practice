public class Queue1 {
    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.add(5);
        queue.add(6);
        queue.add(8);
        while (!queue.isEmpty()){
            System.out.println(queue.peek());
            queue.remove();
        }
    }
}



class Queue{
    class Node{
        int data;
        Node next;
        public Node(int data){
            this.data = data;
            next = null;
        }
    }

    Node head,tail;

    public void add(int data){
        Node temp = new Node(data);
        if(head==null){
            head = tail = temp;
        }else{
            tail.next = temp;
            tail = temp;
        }
    }
    public int remove(){
        int temp = head!=null?head.data:-1;
        assert head != null;
        head = head.next;
        return temp;
    }
    public int peek(){
        return head!=null?head.data:-1;
    }

    public boolean isEmpty(){
        if(head==null){
            return true;
        }
        return false;
    }

}
