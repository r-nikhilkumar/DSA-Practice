import java.util.Arrays;

public class Sorting {
    static void bubbleSort(int[] array){
        for(int i=0;i<array.length-1;i++){
            boolean flag = false;
            for(int j=0;j<array.length-1-i;j++){
                System.out.println("running...");
                if(array[j]>array[j+1]){
                    array[j] = array[j+1]+(array[j+1]=array[j])-array[j];
                    flag = true;
                }
            }
            if(!flag){
                break;
            }
        }
    }

    static void selectionSort(int[] array){
        int min;
        for(int i=0;i<array.length-1;i++){
            min = i;
            for(int j=i+1;j< array.length;j++){
                if(array[min]>array[j]){
                    min = j;
                }
            }
            array[min] = array[i]+(array[i] = array[min])-array[min];
        }
    }

    static void insertionSort(int[] array){
        int key,j;
        for(int i=1;i< array.length;i++){
            key = array[i];
            for(j=i-1;j>=0;j--){
                if(key<array[j]){
                    array[j+1] = array[j];
                }else{
                    break;
                }
            }
            array[j+1] = key;
        }
    }

    static void shellSort(int[] array){
        int key, j;
        int c=0;
        for(int gap= array.length/2;gap>0;gap/=2){
            for(int i = gap; i< array.length;i+=1){
                key = array[i];
                for(j=i-gap;j>=0;j-=gap){
                    c++;
                    if(key<array[j]){
                        array[j+gap] = array[j];
                    }else{
                        break;
                    }
                }
                array[j+gap] = key;
            }
        }
        System.out.println(c);
    }

    static void countSort(int[] array){
        int max = Arrays.stream(array).max().getAsInt();
        int[] temp = new int[max+1];
        for (int i=0;i<array.length;i++){
            temp[array[i]]++;
        }
        int count=0;
        for(int i=0;i<temp.length;i++){
            while(temp[i]>0){
                array[count]=i;
                count++;
                temp[i]--;

            }
        }
    }

    static void mergeSort(int[] array, int staringIndex, int endingIndex){
        if(staringIndex>=endingIndex){
            return;
        }
        int middle = (staringIndex+endingIndex)/2;
        mergeSort(array, staringIndex, middle);
        mergeSort(array, middle+1,endingIndex);
        merge(array, staringIndex, endingIndex, middle);
    }

    private static void merge(int[] array, int staringIndex, int endingIndex, int middle) {
        int[] merge = new int[endingIndex-staringIndex+1];
        int it=0;
        int i1 = staringIndex;
        int i2 = middle+1;
        while(i1<=middle&&i2<=endingIndex){
            if(array[i1]<=array[i2]){
                merge[it] = array[i1];
                it++;
                i1++;
            }else if(array[i2]<=array[i1]){
                merge[it] = array[i2];
                it++;
                i2++;
            }
        }
        while(i1<=middle){
            merge[it] = array[i1];
            i1++;
            it++;
        }
        while(i2<=endingIndex){
            merge[it] = array[i2];
            i2++;
            it++;
        }
        for(int i=staringIndex,j=0;i<endingIndex+1;i++,j++){
            array[i] = merge[j];
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,3,5,2,2,44,5,3,7,9,8};
//        bubbleSort(arr);
//        selectionSort(arr);
//        insertionSort(arr);
//        shellSort(arr);
//        countSort(arr);
        mergeSort(arr,0,arr.length-1);
        for (int j : arr) {
            System.out.print(j + ", ");
        }
        System.out.println();
    }

}

