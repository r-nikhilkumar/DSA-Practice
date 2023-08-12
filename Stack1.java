import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class Stack1 {
    public static void main(String[] args) {
        int[] givenArray = {5,4,3,4,5};
        int [] left = new int[givenArray.length], right = new int[givenArray.length];
        Stack<Integer> st = new Stack<>();
        for(int i=0;i<givenArray.length;i++){
            while (!st.isEmpty()&&givenArray[i]>givenArray[st.peek()]){
                right[st.peek()] = i+1;
                st.pop();
            }
            st.push(i);

        }
        st.clear();
        for(int i=givenArray.length-1;i>=0;i--){
            while (!st.isEmpty()&&givenArray[i]>givenArray[st.peek()]){
                left[st.peek()] = i+1;
                st.pop();
            }
            st.push(i);
        }
        int max=-1;
        for (int i=0;i< givenArray.length;i++){
            max = Math.max(max,left[i]*right[i]);
        }
        System.out.println(max);

    }
}
