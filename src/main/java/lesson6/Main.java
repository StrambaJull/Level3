package lesson6;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        //п.2 дз
        int[] arr = {2,3,4,1,2};
        MyArray myArray = new MyArray();

        System.out.println("Вх: "+ Arrays.toString(arr) + " -> вых: " + Arrays.toString(myArray.returnNewArray(arr)));

        //п.3 дз
        int[] arr1 = {2,2,2,2};
        MyArray2 myArray2 = new MyArray2();
        System.out.println(myArray2.checkedArray(arr1));
    }

}
