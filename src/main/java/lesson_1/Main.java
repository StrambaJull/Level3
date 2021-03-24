package lesson_1;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        //поменять местами два элемента массива
        Integer[] newArr = {1,2,3,4,5};
        Changer<Integer> newChanger = new Changer<>();
        newChanger.swapElements(newArr, 0,4);
        System.out.println(Arrays.toString(newArr));

        //преобразовать массив в ArrayList, 1-й вариант
        String[] inputArr = {"str1", "str2", "str3", "str4"};
        ConverterToArrayList<String> newArrayList = new ConverterToArrayList<>();
        ArrayList<String> newAr = newArrayList.convertToArrayList1(inputArr);
        newAr.add("str5");
        System.out.println("1 вариант: ");
        System.out.println(newAr.getClass().getName());
        System.out.println(newAr.toString());

        //преобразовать массив в ArrayList, 2-й вариант
        String[] arr2 = {"1", "2", "3", "4"};
        ConverterToArrayList<String> newArr2 = new ConverterToArrayList<>();
        ArrayList<String> convertArr = newArr2.convertToArrayList2(arr2);
        convertArr.add("5");
        System.out.println("2 вариант: ");
        System.out.println(convertArr.getClass().getName());
        System.out.println(convertArr.toString());

        //задача с яблоками
        Apple[] apples1 = {new Apple(0.1f), new Apple(0.3f), new Apple(0.5f)};
        Box<Apple> appleBox1 = new Box<>(apples1);
        System.out.println("Вес коробки с яблоками: " + appleBox1.getWeight());

        Orange[] oranges1 = {new Orange(1.5f), new Orange(1.8f), new Orange(1.9f)};
        Box<Orange> orangeBox1 = new Box<>(oranges1);
        System.out.println("Вес коробки с апельсинами: " + orangeBox1.getWeight());

        System.out.println("Вес коробки 1 с апельсинами равен весу коробки 1 с яблоками: " + appleBox1.compare(orangeBox1));

        Box<Apple> appleBox2 = new Box<>(new Apple(0.1f), new Apple(0.3f), new Apple(0.5f));
        System.out.println("Вес коробки 1 с яблоками равен весу коробки 2 с яблоками: " + appleBox1.compare(appleBox2));

        Box<Fruit> someFruitInBox = new Box<>();
        appleBox1.changeFruit(someFruitInBox);
        System.out.println("Вес новой коробки: " + someFruitInBox.getWeight());
        System.out.println("Вес измененной коробки: " + appleBox1.getWeight());
    }
}
