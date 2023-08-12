import java.security.PublicKey;
import java.util.*;
import java.util.Queue;

public class tree_data_structure {
    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.BuildTree(new int[]{1, 3, 5,-1,-1, 4, 6,-1,-1,-1,8,-1,-1});
        tree.preOrder(tree.getRoot());
        System.out.println();
        tree.inOrder(tree.getRoot());
        System.out.println();
        tree.postOrder(tree.getRoot());
        System.out.println();
        tree.LevelOrder(tree.getRoot());
        System.out.println();
        System.out.println("height = "+tree.height(tree.getRoot()));
        System.out.println("Diameter = "+tree.getDiameter(tree.getRoot()));
        System.out.println("height = "+tree.getHeight(tree.getRoot()));
    }
}

class Tree{
    int idx = 0;
    Node root;
    int[] TreeInfo = new int[2];

    static class Node{
        public int data;
        public Node leftNode, rightNode;

        public Node(int data){
            this.data = data;
        }
    }

    public Node getRoot(){
        return root;
    }

    public Node BuildTree(int[] array){
        if(array[idx]==-1){
            idx++;
            return null;
        }
        Node node = new Node(array[idx]);
        idx++;
        node.leftNode = BuildTree(array);
        node.rightNode = BuildTree(array);

        this.root = node;
        return node;
    }

    public void preOrder(Node root){
        if(root==null){
            return;
        }
        System.out.print(root.data+" ");
        preOrder(root.leftNode);
        preOrder(root.rightNode);
    }

    public void inOrder(Node root){
        if(root==null){
            return;
        }
        preOrder(root.leftNode);
        System.out.print(root.data+" ");
        preOrder(root.rightNode);
    }

    public void postOrder(Node root){
        if(root==null){
            return;
        }
        preOrder(root.leftNode);
        preOrder(root.rightNode);
        System.out.print(root.data+" ");
    }

    public void LevelOrder(Node root){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        while (!queue.isEmpty()){
            Node temp = queue.remove();
            if(temp==null&&!queue.isEmpty()){
                queue.add(null);
                System.out.println();
            }
            else {
                if(temp!=null){
                    System.out.print(temp.data + " ");
                    if (temp.leftNode != null) {
                        queue.add(temp.leftNode);
                    }
                    if (temp.rightNode != null) {
                        queue.add(temp.rightNode);
                    }
                }
            }

        }

    }

    public int height(Node root){
        if(root==null){
            return 0;
        }
        int left = height(root.leftNode);
        int right = height(root.rightNode);
        return Math.max(left, right)+1;
    }

    static class TreeInfo{
        int height;
        int diameter;
        public TreeInfo(int h, int d){
            this.height = h;
            this.diameter = d;
        }
    }

    public int[] diameter(Node root){
        if(root == null){
            return new int[]{0,0};

        }
        int diam1 = diameter(root.leftNode)[1];
        int diam2 = diameter(root.rightNode)[1];
        int diam3 = diameter(root.leftNode)[0]+diameter(root.rightNode)[0]+1;
        int h = Math.max(diameter(root.leftNode)[0],diameter(root.rightNode)[0])+1;
        int d = Math.max(diam3,Math.max(diam1,diam2));
        return new int[]{h,d};
    }
    public int getDiameter(Node root){
        this.TreeInfo = diameter(root);
        return TreeInfo[1];
    }

    public int getHeight(Node root){
        this.TreeInfo = diameter(root);
        return TreeInfo[0];
    }

//    public TreeInfo

}
