package lesson3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

public class WorkWithFile { //объект, через который мы будем работать с файлом

    private String path; //путь к файлу

    //проверить, доступен ли для чтения указанный файл
    private boolean existsAndCanBeRead(String path) {
        File file = new File(path);
        if (file.exists() && file.canRead()) {
            return true;
        }
        return false;
    }

    //Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
    public void readFile(String path, VariantToReadFile variant) {
        if (existsAndCanBeRead(path)) {
            if (variant == VariantToReadFile.ONE) {
                BufferedReader bufferedReader = null;
                FileReader fileReader = null;
                try {
                    fileReader = new FileReader(path);
                    bufferedReader = new BufferedReader(fileReader);
                    String currentLine;
                    while ((currentLine = bufferedReader.readLine()) != null) {
                        System.out.println(currentLine);
                    }
                    bufferedReader.close();
                } catch (
                        IOException e) {
                    e.printStackTrace();
                }
            } else if (variant == VariantToReadFile.TWO) {
                try (FileInputStream in = new FileInputStream(path)) {
                    byte[] arr = new byte[path.length()];
                    int x;
                    while ((x = in.read(arr)) != -1) {
                        System.out.print(new String(arr, StandardCharsets.UTF_8));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (variant == VariantToReadFile.THREE) {
                try (InputStreamReader isr = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8)) {
                    byte[] arr = new byte[path.length()];
                    int x;
                    while ((x = isr.read()) != -1) {
                        System.out.print((char) x);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Других вариантов нет");
            }
        } else {
            System.out.println("Файл не существует");
        }
    }


    //объединение файлов (Последовательно сшить 5 файлов в один (файлы примерно 100 байт).)
    public void combineFiles(String... path) {
        ArrayList<InputStream> arrayList = new ArrayList<>(); //массив для файлов
        long length = 0;
        try {
            for (String i : path) {
                //проверить на существование
                if (existsAndCanBeRead(i)) {
                    arrayList.add(new FileInputStream(i)); //добавили в массив все файлы
                    length = length + i.length();
                } else {
                    System.out.println("Нет файла по указанному адресу :" + i + " либо он недоступен для чтения");
                    break;
                }
            }
            SequenceInputStream in = new SequenceInputStream(Collections.enumeration(arrayList));
            byte[] arr = new byte[(int) length];
            int x; // переменная для хранения считываемых байтов
            while ((x = in.read(arr)) != -1) {
                System.out.print(new String(arr, StandardCharsets.UTF_8));
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //листание страниц. Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb).
    // Вводим страницу (за страницу можно принять 1800 символов), программа выводит ее в консоль.
    // Контролируем время выполнения: программа не должна загружаться дольше 10 секунд, а чтение – занимать свыше 5 секунд.
    public void readNextPage(String path, int page, int pageSize) {
        try (RandomAccessFile raf = new RandomAccessFile(path, "r")) { //открываем файл на чтение
            long currentPosition = (long) (page - 1) * pageSize;
            raf.seek(currentPosition); //ставим указатель с которого будем читать файл
            byte[] bytes = new byte[pageSize]; //создать массив по размеру файла
            raf.read(bytes);
            System.out.println(new String(bytes, StandardCharsets.UTF_8));
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }
    public int getTotal(String path, int pageSize, int settings){ //получить параметры файла: размер файла и количество страниц
        File file = new File(path);
        if(settings == 1){ // количество страниц
            return (int)file.length()/pageSize + 1;
        } else {
            return (int)file.length(); //размер файла
        }

    }
}
