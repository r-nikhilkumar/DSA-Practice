import java.util.ArrayList;
import java.util.Stack;

public class generic {
    public static void main(String[] args) {
        int[] arr = {10,3,-1,2,1,-1,5,-1,6,7,-1,-1,-1,-1};
    }


    static class genericTree1{
        static class Node{
            int data;
//            Node a1, a2, a3........ a20;
            ArrayList<Node> child;
            Node(int data){
                this.data = data;
                child = new ArrayList<>();
            }
        }

//        public Node buildTree(Node root, int val){
//            Stack<Node> stack = new Stack<>();
//            if(val == -1){
//                stack.pop();
//            }
//            Node temp = new Node(val);
//            root.child.add(temp);
//            stack.push(temp);
//        }

    }
}
