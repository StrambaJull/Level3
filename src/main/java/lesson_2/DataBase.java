package lesson_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    private static Connection connection; //для организации подключения к БД
    private static Statement statement; //для выполнения запросов

    public static void connect(){ //реализует подключение к БД
        try {
            Class.forName("org.sqlite.JDBC"); //инициализируем работу драйвера который будем использовать
            connection = DriverManager.getConnection("jdbc:sqlite:main.db");  //указываем к какой базе будем подключаться
            statement = connection.createStatement(); //инициализируем переменную statement
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static void disconnect(){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void createData(String nameTable){ //создание таблицы
        try {
            boolean table = statement.execute("CREATE TABLE IF NOT EXISTS " + nameTable + "(StudId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NAME TEXT NOT NULL,SCORE INTEGER NOT NULL)");
            System.out.println("Результат создания таблицы : " + table);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void readData(String nameTable){ //чтение данных
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM " + nameTable);
            System.out.println("===========Данные таблицы ============");
            while (rs.next()){
                System.out.println(rs.getInt(1) + " " + rs.getString("name")+" "+rs.getString("score"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void readMetaData(String nameTable){
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM " + nameTable);
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println("===========Описание таблицы=============");
            for (int i = 1; i <= rsmd.getColumnCount(); i++){
                System.out.println("COLUMN: " + rsmd.getColumnName(i) + " TYPE: " + rsmd.getColumnType(i) + " Строковое представление типа: " + rsmd.getColumnTypeName(i));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void updateData(String nameTable){ //добавление данных
        try {
            int table = statement.executeUpdate("INSERT INTO "+ nameTable +"  (name, score) VALUES ('Bob', 20)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void deleteData(String nameTable){ //очистка таблицы
        try {
            boolean table = statement.execute("DELETE FROM " + nameTable);
            System.out.println("Результат удаления записей: " + table);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void dropTable(String nameTable){ //удаление таблицы
        try {
            boolean table = statement.execute("DROP TABLE " + nameTable);
            System.out.println("Результат удаления таблицы: " + table);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String nameTable = "students";
        try {
            connect();
            createData(nameTable); //создание новой таблицы
            long t = System.currentTimeMillis();
            connection.setAutoCommit(false);
            for(int i = 0; i < 1000; i++){
                updateData(nameTable); //добавление записей в таблицу
            }
            connection.setAutoCommit(true);
            System.out.println("Время выполнения: " + (System.currentTimeMillis() - t));
            readMetaData(nameTable); //структура таблицы
            readData(nameTable); //чтение записей
            deleteData(nameTable); //удаление записей из таблицы
            dropTable(nameTable); //удаление таблицы

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            disconnect();
        }
    }
}
