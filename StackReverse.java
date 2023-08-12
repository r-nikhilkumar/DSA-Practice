public class StackReverse {
    public static void main(String[] args) {

        stack.stack_func obj = new stack.stack_func();
        obj.push(1);
        obj.push(2);
        obj.push(3);
        obj.push(4);
        obj.push(5);
        obj.push(6);
        int size = 6;
        stack.stack_func obj2 = new stack.stack_func();
        int count=0;
        while(true){
            if(count==size/2){
                obj.pop();
                while (!obj2.isEmpty()){
                    obj.push(obj2.peek());
                    obj2.pop();
                }
                break;
            }
            obj2.push(obj.peek());
            obj.pop();
            count++;
        }

        while (!obj.isEmpty()) {
            System.out.println(obj.peek());
            obj.pop();
        }




    }
}

class stack {//_linkedlist

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;

        }
    }

    static class stack_func {
        Node head;

        boolean isEmpty() {
            return head == null;
        }

        void push(int data) {
            Node temp = new Node(data);
            if (isEmpty()) {
                head = temp;
                return;
            }

            temp.next = head;
            head = temp;

        }

        int pop() {
            if (isEmpty()) {
                return -1;
            }
            int top = head.data;
            head = head.next;
            return top;
        }

        int peek() {
            if (isEmpty()) {
                return -1;
            }
            return head.data;
        }

        void reverse(){
            if(head == null){
                return;
            }
            Node temp = head;
            Node temp2;
            int count = 0;
            while (temp!=null){
                temp2 = temp.next;
                if(count==0){
                    temp.next = null;
                    count++;
                }else{
                    temp.next = head;
                }
                head = temp;
                temp = temp2;
            }
        }



    }


}
