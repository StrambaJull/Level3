package lesson3;

/*
1. Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
2. Последовательно сшить 5 файлов в один (файлы примерно 100 байт).
Может пригодиться следующая конструкция: ArrayList<InputStream> al = new ArrayList<>(); ...
Enumeration<InputStream> e = Collections.enumeration(al);
3. Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb).
Вводим страницу (за страницу можно принять 1800 символов), программа выводит ее в консоль.
Контролируем время выполнения: программа не должна загружаться дольше 10 секунд, а чтение – занимать свыше 5 секунд.

 */

import java.util.Scanner;

public class NewFile {

    public static void main(String[] args) {
        int pageSize = 1800; //размер страницы
        long t = System.currentTimeMillis();//контроль времени выполнения

        WorkWithFile file = new WorkWithFile(); //объект, через который будем работать с файлами
        String path = "mainDirToFiles/file.txt";
        String path1 =  "mainDirToFiles/file1.txt";
        String path2 =  "mainDirToFiles/file2.txt";

        System.out.println("========== прочитать файл =================");
        file.readFile(path, VariantToReadFile.ONE); //читаем файл несколькими способами

        System.out.println("========== объединить файлы =================");
        file.combineFiles(path, path1, path2);

        System.out.println("\n" + "========== консольное приложение ===============");
        System.out.println("Размер файла: " + file.getTotal(path, pageSize, 0));
        Scanner scanner = new Scanner(System.in);
        System.out.print("Всего страниц: " +file.getTotal(path, pageSize, 1)+  "\n Введите страницу: ");
        int page = scanner.nextInt();
        file.readNextPage(path, page, pageSize);

        System.out.println("\n" + "=========================");
        System.out.println("Время выполнения: " + (System.currentTimeMillis() - t));

    }
}