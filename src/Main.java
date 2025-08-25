
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        int[]arr = {8,-5,16,1,21,3,-7,2};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    LinkedList<Integer> ll1 = new LinkedList<>();
    ll1.add(8);
    ll1.add(-5);
    ll1.add(16);
    ll1.add(1);
    ll1.add(21);
    ll1.add(3);
    ll1.add(-7);
    ll1.add(2);


    System.out.println(ll1);
    }


    public static void selectionSort(int[] arr){

        for(int i = 0; i<arr.length-1; i++){
            int min =i;

            for(int j = i+1; j<arr.length; j++){
                if(arr[j]<arr[min]){
                    min = j;
                }

            }
            int temp = arr[i];
            arr[i]=arr[min];
            arr[min]=temp;

        }
    }

    public static void selectionSort(double[] arr){

        for(int i = 0; i<arr.length-1; i++){
            int min =i;

            for(int j = i+1; j<arr.length; j++){
                if(arr[j]<arr[min]){
                    min = j;
                }

            }
            double temp = arr[i];
            arr[i]=arr[min];
            arr[min]=temp;

        }
    }

    public static void selectionSort(LinkedList<Integer> arr){

        for(int i = 0; i<arr.size(); i++){
            int min =i;

            for(int j = i+1; j<arr.size(); j++){  //comparison
                if(arr.get(i)>arr.get(min)){
                    arr.set(min, i);
                }

            }
            int temp = arr.get(i);
//            arr[i] = arr[min];
//            arr[min] = temp;*/
            arr.set(i, min);
            arr.set(min, temp);


        }
    }
}