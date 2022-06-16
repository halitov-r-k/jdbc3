package org.example;

import java.sql.*;

public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "Main method" );
        Connection connection;
        Statement statement;
        //открытие соединения
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db",
                    "root",
                    "root"
                    );
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //проверка соединения
        try {
            if(!connection.isClosed()) { System.out.printf("Connect is open %b \n", true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //работа с db
        try {
            //создание таблицы
            //statement.executeUpdate("create table table1 (Id int, Data varchar(20))");

            //вставка записи
            //statement.execute("INSERT INTO table1(Id, Data) VALUES(1, 'date1');");

            //обновление записи
            //int result = statement.executeUpdate("update table1 set Data='NewData1' where Id=1");
            //System.out.println(result);

            //выборка данных
            ResultSet resultSet = statement.executeQuery("select * from table1 ");
            //обработка результата выборки
            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String data = resultSet.getString("Data");
                System.out.printf("id: %d, data: %s. \n", id, data);

            }

            /*
            //пакетная обработка
            statement.addBatch("insert into table1(Id, Data) VALUES (2, 'batchData2' )");
            statement.addBatch("insert into table1(Id, Data) VALUES (3, 'batchData3' )");
            statement.addBatch("insert into table1(Id, Data) VALUES (4, 'batchData4' )");
            statement.addBatch("insert into table1(Id, Data) VALUES (5, 'batchData5' )");
            statement.executeBatch();*/
            //очистка Batch
            //statement.clearBatch();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //закрытие соединения
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //проверка закрытия соединения
        try {
            if(connection.isClosed()) { System.out.printf("Connect is close %b \n", true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
