package lesson6;

import lesson6.MyArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Arrays;

public class Test_MyArray {
    MyArray myArray;

    @Before
    public void init(){
        myArray = new MyArray();
    }


    @Test
    @DisplayName ("Передан null массив")
    public void nullPointerExeption() {
        int[] arr = null;
        Assert.assertThrows ("Ожидаемый результат не получен", NullPointerException.class, () -> myArray.returnNewArray(arr));
    }
    @Test
    @DisplayName("В массиве нет числа 4")
    public void runtimeExeption(){
        int[] arr = {3,3,3,3};
        Assert.assertThrows("Ожидаемый результат не получен", RuntimeException.class, () -> myArray.returnNewArray(arr));
    }
    @Test
    @DisplayName("На вход поданы правильные данные")
    public void currentData() {
        int[] arr = {4,5,5,6,4,3,2,2,1};
        int[] newArr = {3,2,2,1};
        Assert.assertArrayEquals(newArr, myArray.returnNewArray(arr));
    }

    @Test
    @DisplayName("Тест должен упасть, т.к. сравнение с заведомо неверным результатом")
    public void currentData1() {
        int[] arr = {4,5,5,6,4,3,2,2,1};
        int[] newArr = {5,5,6,4,3,2,2,1};
        Assert.assertArrayEquals(newArr, myArray.returnNewArray(arr));
    }

}
