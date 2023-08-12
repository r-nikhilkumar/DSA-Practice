import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class genericTree {
    public static void main(String[] args) {
        int[] arr = {10,20,50,-1,60,-1,-1,30,70,-1,80,110,3,-1,-1,120,-1,-1,90,-1,-1,40,100,-1,-1,-1};
        GenericTree genericTree = new GenericTree();
        genericTree.buildGenericTree(arr);
//        System.out.println(genericTree.getRoot().data);
        genericTree.LevelOrder(genericTree.getRoot());
//        System.out.println();
//        System.out.println(genericTree.height(genericTree.getRoot()));
        System.out.println();
        System.out.println(genericTree.getDiameter());
        System.out.println(genericTree.getHeight());

    }

    static class GenericTree{
        Node root = null;
        int height, diameter;
        static class Node{
            int data;
            ArrayList<Node> children;
            public Node(int data){
                this.data = data;
                this.children = new ArrayList<>();
            }
        }

        public Node getRoot(){
            return this.root;
        }

        public void buildGenericTree(int[] array){
            Stack<Node> stack = new Stack<>();
            root = new Node(array[0]);
            stack.add(root);
            while (!stack.isEmpty()){
                for(int i=1;i<array.length;i++){
                    if(array[i]==-1){
//                        stack.peek().children = null;
                        stack.pop();
                    }else {
                        Node temp = new Node(array[i]);
                        stack.peek().children.add(temp);
                        stack.push(temp);
                    }
                }
            }

        }

        public void LevelOrder(Node root){
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            queue.add(null);
            while(!queue.isEmpty()){
                Node temp = queue.remove();
                if(temp == null){
                    if(queue.isEmpty()){
                        break;
                    }
                    else {
                        System.out.println();
                        queue.add(null);
                    }
                }else{
                    System.out.print(temp.data+" ");
                    queue.addAll(temp.children);
                }

            }
        }

        public int height(Node root){
            if(root==null){
                return 0;
            }
            int max=0;
            for(Node child: root.children){
                max = Math.max(max, height(child));
            }
            return max+1;
        }

        public int getHeight(){
            diameter(this.getRoot());
            return this.height;
        }
        public int getDiameter(){
            diameter(this.getRoot());
            return this.diameter;
        }
        public int[] diameter(Node root){
            if(root == null){
                return new int[]{0,0};
            }
            ArrayList<Integer> max_height_list = new ArrayList<>();
            int maxDiameter = 0;
            int max_height = 0;
            for(Node child: root.children) {
                max_height = Math.max(max_height, diameter(child)[0]);
                maxDiameter = Math.max(maxDiameter, diameter(child)[1]);
                max_height_list.add(diameter(child)[0]);
            }
            max_height = max_height+1;
            int maxNodes = 0;
            for(int i=0; i<max_height_list.size()-1;i++){
                for(int j=i+1;j<max_height_list.size();j++){
                    maxNodes = Math.max(maxNodes, (max_height_list.get(i) + max_height_list.get(j)));
                }
            }
            maxNodes = maxNodes+1;
            int max_diameter = Math.max(maxNodes, maxDiameter);
            this.height = max_height;
            this.diameter = max_diameter;
            return new int[]{max_height,max_diameter};

        }
    }
}
