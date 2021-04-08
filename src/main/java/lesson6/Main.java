package lesson6;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
//        int[] arr = null;
        int[] arr = {2,3,4,1,2};
        MyArray myArray = new MyArray();

        System.out.println("Вх: "+ Arrays.toString(arr) + " -> вых: " + Arrays.toString(myArray.returnNewArray(arr)));

    }

}
