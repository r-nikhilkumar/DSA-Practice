import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphDataStructure {
    public static void main(String[] args) {
        Graph graphobj = new Graph(7);
        graphobj.addDirectedEdge(0, 1, 1.5);
        graphobj.addDirectedEdge(0, 2, 3);
        graphobj.addDirectedEdge(2, 3, 1.5);
        graphobj.addDirectedEdge(3, 1, 2);
        graphobj.addDirectedEdge(1, 5, 2);
        graphobj.addDirectedEdge(5, 6, 2);
        graphobj.addDirectedEdge(6, 1, 2);

        Graph graphbuilding = new Graph(7);
        graphbuilding.addUndirectedEdge(1, 0, 2);
        graphbuilding.addUndirectedEdge(0, 2, 3);
        graphbuilding.addUndirectedEdge(2, 3, 4);
        graphbuilding.addUndirectedEdge(1, 3, 4);
        graphbuilding.cycleDetectionUndirected();

        Graph graph = new Graph(6);
        graph.addDirectedEdge(5, 2, 1);
        graph.addDirectedEdge(5, 0, 1);
        graph.addDirectedEdge(2, 3, 1);
        graph.addDirectedEdge(3, 1, 1);
        graph.addDirectedEdge(4, 1, 1);
        graph.addDirectedEdge(4, 0, 1);

        Graph g = new Graph(7);
        g.addUndirectedEdge(0,1,1);
        g.addUndirectedEdge(0,2,1);
        g.addUndirectedEdge(2,4,1);
        g.addUndirectedEdge(1,3,1);
        g.addUndirectedEdge(3,4,1);
        g.addUndirectedEdge(3,5,1);
        g.addUndirectedEdge(4,5,1);
        g.addUndirectedEdge(5,6,1);

//        graphbuilding.cycleDetectionDirected();
//        graphobj.cycleDetectionDirected();
//        System.out.println(graph.topologicalSort());
//        graph.DFS();
//        graph.DFS2();
//        g.DFS2();
//        g.DFS();

    }


    static class Graph {
        private ArrayList<ArrayList<Edge>> graph;
        private int size;

        public ArrayList<ArrayList<Edge>> getGraph() {
            return graph;
        }

        public int getSize() {
            return size;
        }

        static class Edge {
            private int src;
            private int dstn;
            private double weight;

            public Edge(int s, int d, double w) {
                this.src = s;
                this.dstn = d;
                this.weight = w;
            }

            public int getSrc() {
                return src;
            }

            public int getDstn() {
                return dstn;
            }

            public double getWeight() {
                return weight;
            }
        }

        public Graph(int V) {
            this.size = V;
            this.graph = new ArrayList<>();
            for (int i = 0; i < this.size; i++) {
                this.graph.add(new ArrayList<Edge>());
            }
        }

        public void addDirectedEdge(int v, int u, double w) {
            this.graph.get(v).add(new Edge(v, u, w));

        }

        public void addUndirectedEdge(int v, int u, double w) {
            this.graph.get(v).add(new Edge(v, u, w));
            this.graph.get(u).add(new Edge(u, v, w));

        }

        private void BFS_Inside(Boolean[] check, int start) {
            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);
            System.out.print("BFS -> ");
            while (!queue.isEmpty()) {
                int temp = queue.remove();
                if (!check[temp]) {
                    System.out.print(temp + ", ");
                    check[temp] = true;
                    for (int i = 0; i < graph.get(temp).size(); i++) {
                        queue.add(graph.get(temp).get(i).getDstn());
                    }
                }
            }
        }

        public void BFS() {
            Boolean[] check = new Boolean[size];
            for (int i = 0; i < size; i++) {
                check[i] = false;
            }

            for (int i = 0; i < check.length; i++) {
                if (!check[i]) {
                    BFS_Inside(check, i);
                }
            }
        }

        public void DFS() {
            Stack<Integer> stack = new Stack<>();
            boolean[] check = new boolean[size];
            System.out.print("DFS -> ");
            for(int j=0;j< check.length;j++){
                if(!check[j]){
                    stack.add(j);
                    while (!stack.isEmpty()) {
                        int temp = stack.pop();
                        if (!check[temp]) {
                            System.out.print(temp + ", ");
                            check[temp] = true;
                            for (int i = graph.get(temp).size() - 1; i >= 0; i--) {
                                stack.add(graph.get(temp).get(i).getDstn());
                            }
                        }
                    }
                }
            }

        }

        public void DFS2() {
            boolean[] check = new boolean[size];
            System.out.print("DFS -> ");
            for(int i=0;i<size;i++) {
                if (!check[i]) {
                    DFS2Inside(check,i);

                }
            }
        }

        private void DFS2Inside(boolean[] check, int n) {
            if (!check[n]) {
                System.out.print(n + ", ");
                check[n] = true;
                for (int i = 0; i < graph.get(n).size(); i++) {
                    DFS2Inside(check, graph.get(n).get(i).dstn);
                }
            }
        }

        public void AllPathToTarget(int s, int t) {
            boolean[] check = new boolean[size];
            ArrayList<Integer> path = new ArrayList<>();
            System.out.print("All Path to Target -> ");
            AllPathToTargetInside(check, s, t, path);
        }

        private void AllPathToTargetInside(boolean[] check, int s, int t, ArrayList<Integer> path) {
            if (check[s]) {
                return;
            }
            if (s == t) {
                path.add(s);
                System.out.print(path + ", ");
                path.remove(path.size() - 1);
                return;
            }
            if (!check[s]) {
                path.add(s);
                check[s] = true;
                for (int i = 0; i < graph.get(s).size(); i++) {
                    AllPathToTargetInside(check, graph.get(s).get(i).dstn, t, path);
                }
            }
            check[path.get(path.size() - 1)] = false;
            path.remove(path.size() - 1);

        }

        public void cycleDetectionDirected() {
            boolean[] check = new boolean[size];
//            System.out.println(cycleDetectionDirectedInside(check,s));
            for (int i = 0; i < check.length; i++) {
                if (cycleDetectionDirectedInside(check, i)) {
                    System.out.println("Cycle detected: " + true);
                    return;
                }
                check = new boolean[size];
            }
            System.out.println("Cycle detected: " + false);

        }

        private boolean cycleDetectionDirectedInside(boolean[] check, int s) {
            if (check[s]) {
                return true;
            }

            check[s] = true;
            for (int i = 0; i < graph.get(s).size(); i++) {
                if (cycleDetectionDirectedInside(check, graph.get(s).get(i).dstn)) {
                    return true;
                }
            }
            check[s] = false;
            return false;


        }
//      topological sorting - kanh's algorithm:
        public String topologicalSort() {
            Stack<Integer> stack = new Stack<>();
            boolean[] check = new boolean[size];
            for (int i = 0; i < size; i++) {
                topologicalSortInside(check, i, stack);
            }
            ArrayList<Integer> arr = new ArrayList<>();
            while (!stack.isEmpty()) {
                arr.add(stack.pop());
            }
            return arr.toString();
        }

        private void topologicalSortInside(boolean[] check, int s, Stack<Integer> stack) {
            if(check[s]){
                return;
            }
            check[s] = true;
            for (int i = 0; i < graph.get(s).size(); i++) {
                topologicalSortInside(check, graph.get(s).get(i).dstn, stack);
            }
            stack.push(s);
        }

        public void cycleDetectionUndirected() {
            boolean[] check = new boolean[size];
            for (int i = 0; i < check.length; i++) {
                if (cycleDetectionUndirectedInside(check, i,i)) {
                    System.out.println("Cycle detected: " + true);
                    return;
                }
                check = new boolean[size];
            }
            System.out.println("Cycle detected: " + false);

        }

        private boolean cycleDetectionUndirectedInside(boolean[] check, int s, int prev) {
            if (check[s]) {
                return true;
            }

            check[s] = true;
            for (int i = 0; i < graph.get(s).size(); i++) {
                if(graph.get(s).get(i).dstn==prev){
                    continue;
                }
                if (cycleDetectionUndirectedInside(check, graph.get(s).get(i).dstn,s)) {
                    return true;
                }
            }
            check[s] = false;
            return false;


        }
    }
}



