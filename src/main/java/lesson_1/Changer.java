package lesson_1;

public class Changer<T> {
    public void swapElements (T[] arr, int i1, int i2) {
        T elem; //переменная для хранения элемента 1;
        elem = arr[i1]; //запомнить значение элемента из index1;
        arr[i1] = arr[i2]; //записать index1 значение элемента из index2;
        arr[i2] = elem; //записать в index2 значение элемента из index1.
    }
}
