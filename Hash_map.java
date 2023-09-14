import java.util.*;

public class Hash_map {
    public static void main(String[] args) {
        Hashmap<String, Integer> map = new Hashmap<>();
        map.put("Nikhil", 1);
        map.put("Manasvi",2);
        map.put("N", 1);
        map.put("M",2);
        map.put("S", 1);
        map.put("R",2);
        map.show();
    }
}

class Hashmap<S,T>{
     static class Node<S,T>{
        S key;
        T value;
        Node<S,T> next;
        Node(S k,T v){
            this.key = k;
            this.value = v;
            this.next=null;
        }
    }
    int size = 2;
     double addedSize = 0;
    LinkedList<Node<S, T>>[] HashMap;
    void createHM(){
        this.HashMap = new LinkedList[this.size];
        for(int i = 0;i<this.size;i++){
            this.HashMap[i] = new LinkedList<>();
        }
    }
    Hashmap(){
        createHM();
    }
    void reHashing(){
        this.size*=2;
        this.addedSize=0;
        LinkedList<Node<S, T>>[] Temp = HashMap;
        createHM();
        for(int i =0;i<Temp.length;i++){
            for(int j=0;j<Temp[i].size();j++){
                Node<S,T> node = Temp[i].get(j);
                put(node.key,node.value);
            }
        }

    }


    void put(S key, T value){
        if((addedSize/size)<2.0){
            int code = Math.abs(key.hashCode())%size;
            HashMap[code].add(new Node<>(key, value));
        }else{
            reHashing();
            int code = key.hashCode()%size;
            HashMap[code].add(new Node<>(key, value));
        }
        addedSize++;

    }

    void show(){
        System.out.print("{");
        for(int i =0;i<HashMap.length;i++){
            for(int j=0;j<HashMap[i].size();j++){
                Node<S,T> node = HashMap[i].get(j);
                System.out.print("("+node.key+", "+node.value+"),");
            }
        }
        System.out.print("}");
    }

}
