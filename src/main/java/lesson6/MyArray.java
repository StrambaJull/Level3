package lesson6;

/*
 Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
 Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов, идущих после последней четверки.
 Входной массив должен содержать хотя бы одну четверку, иначе в методе необходимо выбросить RuntimeException.
 Написать набор тестов для этого метода (по 3-4 варианта входных данных).
 Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
 */

import java.util.ArrayList;
import java.util.Arrays;

public class MyArray {
    public int[] returnNewArray (int[] arr) throws RuntimeException {
        boolean isTrue = false; //есть ли искомое число в массиве
        int index = 0; //индекс с которого начнем переписывать массив
        ArrayList<Integer> newArr = new ArrayList<>(); //сюда будем складывать новые значения

        if (arr == null){ //проверка того что пришло на вход
            throw new NullPointerException("Массив пустой");
        }
        for (int j : arr) {
            if (j == 4) {
                isTrue = true;
                break;
            }
        }
        if(!isTrue){
            throw  new RuntimeException("Нет нужного числа в полученном массиве");
        }

        for (int i = 0; i < arr.length; i++){//сначала определим с какого индекса брать начинаем переписывать массив
            if (arr[i] == 4){
                if (index < i){//сравниваем запомненное значение с текущим
                    index = i; //если текущее значение больше, чем запомненное, то запоминаем новое значение
                }
            }
        }
        int[] arr1 = new int[arr.length  - index];
        for (int i = 0; i < arr.length; i++) {//переписываем в новый массив
            if(i > index){
                arr1 = Arrays.copyOfRange(arr, i, arr.length);
                break;
            }
        }
        return arr1;
    }
}
