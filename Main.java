public class Main {
    public static void main(String[] args) {
        MyLinkedList<Integer> L = new MyLinkedList<>();
        L.insertAtHead(1);
        L.add(4);
        L.add(5);
        L.insertAt(6, 2);
        L.display();
//        System.out.println(L.size);
        L.reverse();
        L.display();
    }
}

class Node<T>{
    Node<T> next;
    T value;
    Node(T a){
        this.value=a;
    }
}

class MyLinkedList<T>{
    Node<T> head, tail;
    int size=0;
    void add(T val){
        Node<T> temp = new Node<>(val);
        Node<T> move = head;
        if(head==null){
            insertAtHead(val);
            return;
        }else{
            while(move.next!=null){
                move=move.next;
            }
            move.next=temp;
            tail = temp;
            size++;
        }
    }
    void display(){
        Node move = head;
        while (move!=null) {
            System.out.print(move.value+" ");
            move=move.next;
        }
        System.out.println();
    }
    void insertAtHead(T val){
        Node<T> temp = new Node<>(val);
        if(head==null){
            head = tail = temp;
        }
        else{
            temp.next = head;
            head = temp;
        }
        size++;
    }
    void insertAt(T val, int idx){
        Node<T> temp = new Node<>(val);
        Node move = head;
        int count=0;
        if(idx==0){
            insertAtHead(val);
        }
        else if(idx==size){
            add(val);
        }
        else{
            while(move!=null){
                if(count==idx-1){
                    temp.next = move.next;
                    move.next = temp;
                    size++;
                }
                move = move.next;
                count++;
            }
        }
    }

    void delete(int idx){
        
    }
    void delete(T val){

    }

    void reverse(){
        Node<T> temp = head;
        while(temp.next!=null){
            if(temp.next.next==null){
                temp.next.next = temp;
                temp.next=null;
                temp = head;
            }else{
                temp = temp.next;
            }

        }
        head = tail;
        tail = temp;
    }
}


