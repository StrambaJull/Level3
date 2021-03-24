package lesson_1;

import java.util.ArrayList;
import java.util.Collections;

public class ConverterToArrayList<T> {

    public ArrayList<T> convertToArrayList1(T[] inputArray){
        ArrayList<T> newArr = new ArrayList<>();
        Collections.addAll(newArr, inputArray);
        return newArr;
    }

    public ArrayList<T> convertToArrayList2(T[] inputArr){
        ArrayList<T> newArr = new ArrayList<>();
        for(T a : inputArr) {
            newArr.add(a);
        }
        return newArr;
    }
}
