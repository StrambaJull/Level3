package lesson4.homeWork;

//Создать MFU c функциями, сканирования, печати и ксерокопирования
public class MFU {

    Object mfu = new Object(); //объект мфу
    volatile CurrentFunction currentFunction = CurrentFunction.FREE; //переменная, которая будет хранить состояние проекта

    public void scan(){ //может работать параллельно только с печатью в файл
        Thread scan = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (mfu) {
                    try {
                        if(currentFunction != CurrentFunction.FREE) {
                            while (currentFunction != CurrentFunction.SCAN || currentFunction != CurrentFunction.PRINT) { //пока текущее состояние МФУ не равно СКАН или ПЕЧАТЬ
                                System.out.println("MFU занят другим процессом"); //сообщаем что МФУ занят
                                mfu.wait(); //ждем
                            }
                        } else {
                            System.out.println("Запущено сканирование"); //если текущее состояние Скан, то сообщаем что сканирование начато
                            currentFunction = CurrentFunction.SCAN; //сохраняем текущее состояние
                            Thread.sleep(100); //выполняем сканирование
                            System.out.println("Сканирование завершено"); //сообщаем об окончании
                            currentFunction = CurrentFunction.FREE;
                            mfu.notifyAll(); //будим другие потоки
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        scan.start();
    }
    public void print(){
        Thread print = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (mfu) {
                    try {
                        if (currentFunction != CurrentFunction.FREE) {
                            while (currentFunction != CurrentFunction.SCAN || currentFunction != CurrentFunction.PRINT) {
                                System.out.println("MFU занят другим процессом");
                                mfu.wait();
                            }
                        } else {
                            System.out.println("Запущена печать");
                            currentFunction = CurrentFunction.PRINT;
                            Thread.sleep(100);
                            System.out.println("Печать завершена");
                            currentFunction = CurrentFunction.FREE;
                            mfu.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        print.start();
    }
    public void copy(){
        Thread copy = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (mfu) {
                    try {
                        if (currentFunction != CurrentFunction.FREE) {
                            while (currentFunction != CurrentFunction.COPY) {
                                System.out.println("MFU занят другим процессом");
                                mfu.wait(); //ждем
                            }
                        } else {
                            System.out.println("Копирование начато");
                            currentFunction = CurrentFunction.COPY;
                            Thread.sleep(100);
                            System.out.println("Копирование завершено");
                            currentFunction = CurrentFunction.FREE;
                            mfu.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
       copy.start();
    }

}
