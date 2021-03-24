package lesson_1;

import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruit> {

    ArrayList<T> box; //Для хранения фруктов внутри коробки можете использовать ArrayList;

    public Box(T... box) {
        this.box = new ArrayList<T>(Arrays.asList(box));
    }

    //    Не забываем про метод добавления фрукта в коробку.
    public void putFruit(T fruit) {
        box.add(fruit);
    }

    public float getWeight(){ //вес коробки
        float weight = 0.0f;
        for (T t : box) {
            weight = weight + t.getWeight();
        }
        return weight;
    }

    public boolean compare(Box<?> box) {
        return box.getWeight() == getWeight();
    }

    public void changeFruit (Box<? super T> someBox){
        someBox.box.addAll(this.box); //добавили в новую коробку все, что было в текущей
        box.clear(); //очистили текущую коробку
    }

}


