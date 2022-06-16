package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
            //вставка записи
            // statement.execute("INSERT INTO table1(Id, Data) VALUES(1, 'date1');");
            //обновление записи
            int result = statement.executeUpdate("update table1 set Data='NewData1' where Id=1");
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
