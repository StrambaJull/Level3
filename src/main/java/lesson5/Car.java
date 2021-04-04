package lesson5;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable {
    private static AtomicInteger ai = new AtomicInteger(0);
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    private CyclicBarrier cyclicBarrier;


    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, CyclicBarrier cyclicBarrier) {
        this.race = race;
        this.speed = speed;
        this.name = "Участник #" + CARS_COUNT;
        this.cyclicBarrier = cyclicBarrier;
        CARS_COUNT++;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cyclicBarrier.await();
            cyclicBarrier.await(); //подстраховка

            for (int i = 0; i < race.getStages().size(); i++) { //цикл по этапам гонки - их три
                race.getStages().get(i).go(this); //стартуем каждый этап
            }

            if (ai.incrementAndGet() == 1){ //первый поток увеличивший счетчик
                System.out.println(name + " победитель!");
            }
            cyclicBarrier.await();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
