public class LLPractice {
    public static void main(String[] args) {
        MyLinkedList<Integer> L = new MyLinkedList<>();
        L.insertAtHead(1);
        L.add(4);
        L.add(5);
        L.add(8);
        L.add(7);
        L.insertAt(6, 2);
//        1->4->6->5
        System.out.println(L.head.value); // 1
        System.out.println(L.tail.value); // 5
        L.display();
        L.reverse(); // 5->6->4->1
        System.out.println(L.head.value); // 5
        System.out.println(L.tail.value); // 1
        L.display();

    }
}
