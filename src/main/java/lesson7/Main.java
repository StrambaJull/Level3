package lesson7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        processing(TestClass.class);
    }

    private static void processing(Class c) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = c.getDeclaredMethods(); //получаем все методы класса и записываем их в массив
        List<Method> list = new ArrayList<>(); //для хранения всех методов с аннотациями будем использовать коллекцию List
        for (Method m : methods) {
            if(m.isAnnotationPresent(Test.class)){ //если для текущего метода возвращается аннотация указанного типа
                list.add(m); //записываем в коллекцию этот метод
            }
        }
        list.sort(new Comparator<Method>() { //сортируем методы по приоритету
            @Override
            public int compare(Method o1, Method o2) {
                return o2.getAnnotation(Test.class).priority() - o1.getAnnotation(Test.class).priority(); //вычитаем один приоритет из другого, т.о. все методы отсортируются в убывающем порядке
            }
        });
        for (Method o : methods) { //расставим по местам Before и After
            if(o.isAnnotationPresent(BeforeSuite.class)){ //если метод содержит указанную аннотацию, тогда
                if(list.get(0).isAnnotationPresent(BeforeSuite.class)){ // если метод с указанной аннотацией находится на первой позиции, тогда
                    throw new RuntimeException("BeforeSuite exception"); //выбрасываем исключение
                }
                list.add(0, o); //иначе записываем этот метод на первую позицию в списке
            }
            if(o.isAnnotationPresent(AfterSuite.class)){ //если метод содержит указанную аннотацию, тогда
                if(list.get(list.size()-1).isAnnotationPresent(AfterSuite.class)){ // если метод с указанной аннотацией находится на последней позиции, тогда
                    throw new RuntimeException("AfterSuite exception"); //выбрасываем исключение
                }
                list.add(o); //иначе записываем этот метод на последнюю позицию в списке
            }
        }
        for (Method o : list) { //вызываем каждый объект из списка
            o.invoke(null);
        }
    }

}
