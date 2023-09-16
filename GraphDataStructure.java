import java.util.*;
import java.util.Queue;

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
//        graphbuilding.cycleDetectionUndirected();

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

        Graph gr = new Graph(6);
        gr.addDirectedEdge(0,1,2);
        gr.addDirectedEdge(0,2,4);
        gr.addDirectedEdge(1,2,1);
        gr.addDirectedEdge(1,3,3);
        gr.addDirectedEdge(2,4,2);
        gr.addDirectedEdge(3,4,1);
        gr.addDirectedEdge(4,5,1);
//        gr.dijkastraAlgo(0);

        Graph grr = new Graph(4);
        grr.addUndirectedEdge(0,1,10);
        grr.addUndirectedEdge(0,2,15);
        grr.addUndirectedEdge(0,3,30);
        grr.addUndirectedEdge(1,3,40);
        grr.addUndirectedEdge(2,3,50);
//        grr.primsAlgo();
//        ArrayList<Graph.Edge> MTSEdge = grr.primsAlgo();
//        Graph MST = new Graph(4);
//        for(Graph.Edge edge :MTSEdge ){
//            MST.addUndirectedEdge(edge.getSrc(), edge.getDstn(), edge.getWeight());
//        }
//        MST.DFS();

//        graphbuilding.cycleDetectionDirected();
//        graphobj.cycleDetectionDirected();
//        System.out.println(graph.topologicalSort());
//        graph.DFS();
//        graph.DFS2();
//        g.DFS2();
//        g.DFS();


        Graph grap = new Graph(5);
        grap.addDirectedEdge(0,2,1);
        grap.addDirectedEdge(0,3,1);
        grap.addDirectedEdge(1,0,1);
        grap.addDirectedEdge(2,1,1);
        grap.addDirectedEdge(3,4,1);
//        grap.kosarajuAlgo();

        Graph tGraph = new Graph(6);
        tGraph.addUndirectedEdge(0,1,1);
        tGraph.addUndirectedEdge(0,2,1);
        tGraph.addUndirectedEdge(0,3,1);
        tGraph.addUndirectedEdge(1,2,1);
        tGraph.addUndirectedEdge(3,4,1);
        tGraph.addUndirectedEdge(4,5,1);
        tGraph.addUndirectedEdge(5,3,1);
        tGraph.tarjansAlgoBridge();

        Graph tGraph1 = new Graph(6);
        tGraph1.addUndirectedEdge(0,1,1);
        tGraph1.addUndirectedEdge(0,2,1);
        tGraph1.addUndirectedEdge(0,3,1);
        tGraph1.addUndirectedEdge(1,2,1);
        tGraph1.addUndirectedEdge(3,4,1);
        tGraph1.tarjansAlgoArticulationPoint();
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

            public Edge(){}
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
        static class Connector implements Comparable<Connector>{
            int src;
            double wgt;
            Edge edge;
            public Connector(int s, double w){
                this.src=s;
                this.wgt=w;
            }
            public Connector(int s, double w, Edge e){
                this.src=s;
                this.wgt=w;
                this.edge=e;
            }
            public void setEdge(Edge e){
                this.edge = e;
            }

            public Edge getEdge(){
                return this.edge;
            }

            @Override
            public int compareTo(Connector o) {
                return (int) (this.wgt-o.wgt);
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

//        Dijkstra's algorithm:
        public void dijkastraAlgo(int s){
            boolean[] check = new boolean[size];
            double[] distance = new double[size];
            for(int i=0;i<size;i++){
                distance[i] = Float.MAX_VALUE;
            }
            PriorityQueue<Connector> priorityQueue = new PriorityQueue<>();
            priorityQueue.add(new Connector(s,0));
            distance[s] = 0;

            while (!priorityQueue.isEmpty()){
                Connector temp = priorityQueue.remove();
                check[temp.src] = true;

                for(int i=0;i<graph.get(temp.src).size();i++){
                    if(!check[graph.get(temp.src).get(i).dstn]&&(distance[graph.get(temp.src).get(i).dstn]> temp.wgt+graph.get(temp.src).get(i).weight)){
                        distance[graph.get(temp.src).get(i).dstn] = temp.wgt+graph.get(temp.src).get(i).weight;
                        priorityQueue.add(new Connector(graph.get(temp.src).get(i).dstn,temp.wgt+graph.get(temp.src).get(i).weight));
                    }
                }

            }
            for(int i=0;i<size;i++){
                System.out.println("Shortest distance of "+i+" from source "+s+" is: "+distance[i]);
            }

        }

//        Bellman Ford algorithm:
        public void bellmanFordAlgo(int s){
            double[] distance = new double[size];
            for(int i=0;i< distance.length;i++){
                if(i!=s){
                    distance[i] = Integer.MAX_VALUE;
                }
            }

            for(int i=0;i<size-1;i++){
                for(int j=0;j<size;j++){
                    for(int k=0;k<graph.get(j).size();k++){
                        if(distance[graph.get(j).get(k).src]!=Integer.MAX_VALUE&&distance[graph.get(j).get(k).dstn]>distance[graph.get(j).get(k).src]+graph.get(j).get(k).weight){
                            distance[graph.get(j).get(k).dstn]=distance[graph.get(j).get(k).src]+graph.get(j).get(k).weight;
                        }
                    }
                }
            }
        }

//        MST - Minimum spanning tree:
//        Prim's Algorithm:
        public ArrayList<Edge> primsAlgo(){
            ArrayList<Edge> MSTree = new ArrayList<>();
            double MSTCost=0;
            boolean[] check = new boolean[size];
            PriorityQueue<Connector> priorityQueue = new PriorityQueue<>();
            priorityQueue.add(new Connector(graph.get(0).get(0).src, 0, new Edge()));
            while(!priorityQueue.isEmpty()){
                Connector temp = priorityQueue.remove();
                if(!check[temp.src]){
                    check[temp.src]=true;
                    MSTCost += temp.wgt;
                    MSTree.add(temp.getEdge());
                    for(int i =0; i<graph.get(temp.src).size();i++){
                        if(!check[graph.get(temp.src).get(i).dstn]){
                            priorityQueue.add(new Connector(graph.get(temp.src).get(i).dstn,graph.get(temp.src).get(i).weight, graph.get(temp.src).get(i)));
                        }
                    }
                }
            }
            System.out.println(MSTCost);
            System.out.println(MSTree);
            for(Edge edge:MSTree){
                if(edge!=null){
                    System.out.println(edge.src+" -> "+edge.dstn);
                }
            }
            return MSTree;
        }
        private void toposortKosaraju(Stack<Integer> stack,int s, boolean[] check){
            check[s] = true;
            for(int i=0;i<graph.get(s).size();i++){
                if(!check[graph.get(s).get(i).dstn]) {
                    toposortKosaraju(stack, graph.get(s).get(i).dstn, check);
                }
            }

            stack.push(s);

        }

        private void dfsKosaraju(Graph g, int s, boolean[] check){
            Stack<Integer> st = new Stack<>();
            st.add(s);
            while (!st.isEmpty()){
                int temp = st.pop();
                if(!check[temp]) {
                    check[temp] = true;
                    System.out.print(temp+" ");
                    for (int i = 0;i<g.getGraph().get(temp).size(); i++) {
                        if (!check[g.getGraph().get(temp).get(i).dstn]) {
                            st.push(g.getGraph().get(temp).get(i).dstn);
                        }
                    }
                }
            }

        }
        public void kosarajuAlgo(){
//            reverse dfs or topological sorting:
            Stack<Integer> stack = new Stack<>();
            boolean[] check = new boolean[size];
            for(int i=0;i<size;i++){
                if(!check[i]) {
                    toposortKosaraju(stack, i, check);
                }
            }
//            transpose of graph by reversing direction of edges
            Graph transpose = new Graph(size);
            for(int i= 0;i<size;i++){
                for(int j=0;j<graph.get(i).size();j++) {
                    transpose.addDirectedEdge(graph.get(i).get(j).dstn, graph.get(i).get(j).src, graph.get(i).get(j).weight);
                }
            }
//            dfs on elements stored in stack:
            check = new boolean[size];
            System.out.println("Strongly connected components:");
            while (!stack.isEmpty()){
                dfsKosaraju(transpose, stack.pop(), check);
                System.out.println();
            }


        }

        private void dfsTarjanBridge(boolean[] check, int s, int[] dT, int[] lowT, int curr, int time){
            if(!check[curr]) {
                check[curr] = true;
                dT[curr] = lowT[curr] = ++time;
            }
            for(int i=0;i<graph.get(curr).size();i++){
                if(graph.get(curr).get(i).dstn==s){
                    continue;
                }
                if(check[graph.get(curr).get(i).dstn]){
                    lowT[curr] = Math.min(lowT[curr],dT[graph.get(curr).get(i).dstn]);
                }else if(!check[graph.get(curr).get(i).dstn]){
                    dfsTarjanBridge(check, curr, dT, lowT, graph.get(curr).get(i).dstn, time);
                    lowT[curr] = Math.min(lowT[curr],lowT[graph.get(curr).get(i).dstn]);
                    if(dT[curr]<lowT[graph.get(curr).get(i).dstn]){
                        System.out.println(curr+" -> "+graph.get(curr).get(i).dstn);
                    }
                }
            }
        }

        public void tarjansAlgoBridge(){
            boolean[] check = new boolean[size];
            System.out.println("Bridge(s) in graph are: ");
            for(int i=0;i<size;i++){
                if(!check[i]){
                    dfsTarjanBridge(check, 0, new int[size], new int[size], 0, 0 );
                }
            }
        }


        private void dfstarjansArticulationPoint(int s, int curr, int[] discoveryTime, int[] lowestTime, boolean[] check, int time, boolean[] ArticulationPoints){
            check[curr] = true;
            discoveryTime[curr] = lowestTime[curr] = ++time;
            int child=0;
            for(int i =0;i<graph.get(curr).size();i++){
                if(graph.get(curr).get(i).dstn==s){
                    continue;
                }
                if(!check[graph.get(curr).get(i).dstn]){
                    dfstarjansArticulationPoint(curr, graph.get(curr).get(i).dstn, discoveryTime, lowestTime, check, time, ArticulationPoints);
                    lowestTime[curr] = Math.min(lowestTime[curr],lowestTime[graph.get(curr).get(i).dstn]);
                    if(discoveryTime[curr]<=lowestTime[graph.get(curr).get(i).dstn]&&s!=-1){
                        ArticulationPoints[curr] = true;
                    }
                    child++;
                }else{
                    lowestTime[curr] = Math.min(lowestTime[curr],discoveryTime[graph.get(curr).get(i).dstn]);
                }

            }
            if(s==-1&&child>1){
                ArticulationPoints[curr] = true;
            }
        }

        public void tarjansAlgoArticulationPoint(){
            boolean[] ArticulationPoints = new boolean[size];
            boolean[] check = new boolean[size];
            for(int i=0; i<size;i++){
                if(!check[i]){
                    dfstarjansArticulationPoint(-1, i, new int[size], new int[size], check, 0, ArticulationPoints);
                }
            }
            System.out.println("Articulation points are:");
            for(int i=0;i<size;i++){
                if(ArticulationPoints[i]){
                    System.out.print(i+", ");
                }
            }
        }

//end1
    }
}



