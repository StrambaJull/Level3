package lesson5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    private Semaphore semaphore = new Semaphore(MainClass.CARS_COUNT/2); //ограничение движения через тоннель

    @Override
    public void go(Car c) {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                semaphore.acquire(); //счетчик включается
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep((long) length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                semaphore.release(); //сброс счетчика
            }
    }
}