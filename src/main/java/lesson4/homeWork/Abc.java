package lesson4.homeWork;

public class Abc {
    static Object object = new Object();
    static volatile char currentLetter = 'A';
    static int iter = 5;

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < iter; i++) {
                    synchronized (object) {
                        while (currentLetter != 'A') {
                            try {
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.print('A');
                        currentLetter = 'B';
                        object.notifyAll();
                    }
                }
            }
        });
        thread1.start();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < iter; i++) {
                    synchronized (object) {
                        while (currentLetter != 'B') {
                            try {
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.print('B');
                        currentLetter = 'C';
                        object.notifyAll();
                    }
                }
            }
        });
        thread2.start();
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < iter; i++) {
                    synchronized (object) {
                        while (currentLetter != 'C') {
                            try {
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.print('C');
                        currentLetter = 'A';
                        object.notifyAll();
                    }
                }
            }
        });
        thread3.start();
    }
}
