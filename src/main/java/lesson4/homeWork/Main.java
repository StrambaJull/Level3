package lesson4.homeWork;

public class Main {
    public static void main(String[] args) {
       MFU mfu = new MFU();
       User user1 = new User(mfu);
       User user2 = new User(mfu);
       User user3 = new User(mfu);

       user1.mfu.print();
       user2.mfu.scan();
       user3.mfu.copy();
    }
}
