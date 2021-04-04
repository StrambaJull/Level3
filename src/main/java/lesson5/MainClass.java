package lesson5;
/*
Организуем гонки:
Все участники должны стартовать одновременно, несмотря на то, что на подготовку у каждого из них уходит разное время.
В туннель не может заехать одновременно больше половины участников (условность).
Попробуйте всё это синхронизировать.
Только после того как все завершат гонку, нужно выдать объявление об окончании.
Можете корректировать классы (в т.ч. конструктор машин) и добавлять объекты классов из пакета util.concurrent.
 */

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class MainClass {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_COUNT + 1);
        Race race = new Race(new Road(60), new Tunnel(), new Road(40)); //объект Гонка, в констуркторе указываются этапы гонки

        ExecutorService pool = Executors.newFixedThreadPool(CARS_COUNT, new ThreadFactory() { //запускаем четыре потока с машинами
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        });
        for (int i = 0; i < CARS_COUNT; i++){ //четыре раза
            pool.execute(new Car (race, 20 + (int) (Math.random() * 10), cyclicBarrier)); //создается объект Машина, передается туда гонка и задаеся скорость (рандом)
            //в Машине реализован метод начала гонки (запуск потока)
        }
        pool.shutdown();

        try {
            cyclicBarrier.await(); //ждем когда все потоки будут готовы
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            cyclicBarrier.await(); //подстраховка
            cyclicBarrier.await(); //подстраховка
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }

}


