package lesson6;

public class MyArray2 {
    //Написать метод, который проверяет состав массива из чисел 1 и 4.
    //Если в нем нет хоть одной четверки или единицы, то метод вернет false;
    //Написать набор тестов для этого метода (по 3-4 варианта входных данных).

    public boolean checkedArray(int[] arr) throws NullPointerException{
        if (arr == null){
            throw new NullPointerException("Передан пустой массив");
        }

        for (int j : arr) {
            if (j == 1 || j == 4) {
                return true;
            }
        }
        return false;
    }
}
