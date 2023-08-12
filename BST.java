import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BST {
    public static void main(String[] args) {
        int[] array = new int[]{5,3,1,6,8,7,9};
        BST.BinarySearchTree binarySearchTree = new BinarySearchTree();
        BinarySearchTree.Node root = null;
        for(int val : array){
            root = binarySearchTree.buildTree(root, val);
        }
//        System.out.println(root.data);
//        System.out.println(root.data);
//        System.out.println(binarySearchTree.getRoot().right.right.left.data);
//        System.out.println();
        binarySearchTree.LevelOrder(binarySearchTree.getRoot());
        System.out.println();
//        binarySearchTree.inOrder(binarySearchTree.getRoot());
//        System.out.println();
//        binarySearchTree.delete(binarySearchTree.getRoot(), 8);
//        binarySearchTree.inOrder(binarySearchTree.getRoot());
//        System.out.println();
//        System.out.println(binarySearchTree.inOrderSequence(binarySearchTree.getRoot().right).data);
//        System.out.println();
//        binarySearchTree.inRange(binarySearchTree.getRoot(), 5,7);
//        System.out.println();
        binarySearchTree.root2leaf(binarySearchTree.getRoot(), new ArrayList<>());


    }

    static class BinarySearchTree{
        Node root;
        static class Node{
            int data;
            Node left, right;
            Node(int data){
                this.data = data;
                this.left = null;
                this.right = null;
            }
        }

        public Node getRoot(){
            return this.root;
        }

        public Node buildTree(Node root, int val){
            if(root == null){

                return new Node(val);
            }
            if(val<root.data){
                root.left = buildTree(root.left,val);
            }else if(val>root.data){
                root.right = buildTree(root.right,val);
            }
            this.root = root;
            return root;
        }

        public void LevelOrder(Node root){
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            queue.add(null);
            while(!queue.isEmpty()){
                Node temp = queue.remove();
                if(temp==null){
                    if(queue.isEmpty()){
                        break;
                    }else {
                        queue.add(null);
                        System.out.println();
                    }
                }else{
                    System.out.print(temp.data+" ");
                    if(temp.left!=null) {
                        queue.add(temp.left);
                    }
                    if(temp.right!=null){
                        queue.add(temp.right);
                    }
                }
            }
        }

        public void inOrder(Node root){
            if(root==null){
                return;
            }
            inOrder(root.left);
            System.out.print(root.data+" ");
            inOrder(root.right);

        }

        public Node delete(Node root, int val){
            if(val<root.data){
                root.left = delete(root.left, val);
            }
            else if(val>root.data){
                root.right = delete(root.right, val);
            }
            else{
                if(root.right==null&&root.left==null){
                    return null;
                }
                else if(root.right==null){
                    return root.left;
                }else if(root.left==null){
                    return root.right;
                }else{
                    Node iOS = inOrderSequence(root.right);
                    root.data = iOS.data;
                    root.right = delete(root.right, iOS.data);
                }
            }
            this.root = root;
            return root;
        }

        public Node inOrderSequence(Node root){
            if(root.left==null){
                return root;
            }
            return inOrderSequence(root.left);
        }

        public void inRange(Node root, int start, int end){
            if(root==null){
                return;
            }
            if(start>root.data){
                inRange(root.right, start,end);
            }else if(end<root.data){
                inRange(root.left, start,end);
            }else {
                inRange(root.left,start,end);
                System.out.print(root.data+", ");
                inRange(root.right,start,end);
            }
        }

        public void root2leaf(Node root, ArrayList<Integer> array){
            if(root==null){
                return;
            }
            array.add(root.data);
            if(root.left==null&&root.right==null){
                StringBuilder string = new StringBuilder();
                boolean t = false;
                for(int i : array){
                    if(t){
                        string.append("->");
                    }
                    string.append(i);
                    t = true;
                }
                System.out.println(string.toString());
            }
            root2leaf(root.left, array);
            root2leaf(root.right, array);
            array.remove(array.size()-1);
        }
    }

}
