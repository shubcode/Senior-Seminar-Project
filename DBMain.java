package com.company;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.lang.*;
import java.sql.*;



public class DBMain
{
    private static Connection conn;


    public static void connect() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        //Connection conn = null;

        try {
            // db parameters
            String url = "jdbc:sqlite:src/JavaGameDatabase.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            //Begin of insert
            //Statement statement = conn.createStatement();
            //statement.executeUpdate("INSERT INTO scoreTable(Number,Score)" + "VALUES (7,700) ");



            System.out.println("Connection to Database Test.");
            //DBTablePrinter.printTable(conn,"scoreTable");
            //DBTablePrinter.printTable(conn,"nameTable");
            DBTablePrinter.printTable(conn, "scoreTable INNER JOIN nameTable");


        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        } finally
        {
            try
            {
                if (conn != null)
                {
                    conn.close();
                }
            } catch (SQLException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }


    public static void printToTable() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        String url2 = "jdbc:sqlite:src/JavaGameDatabase.db";
        String query = "SELECT DISTINCT Name, Number, Score FROM nameTable, scoreTable  WHERE nameTable.Name = scoreTable.scoreTable_Name ORDER BY Score DESC";

        try
        {
            conn = DriverManager.getConnection(url2);
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

             System.out.println("Top 25 Scores");
             System.out.println("Name ID Score");
             System.out.println("----------------");
            while (rs.next()){
                String Name = rs.getString("Name");
                int Number = rs.getInt("Number");
                int Score = rs.getInt("Score");

                System.out.println(Name + "\t"  + Number +  "\t" + Score);


            }


            conn.close();




        } catch (SQLException e) {
            e.printStackTrace();
        }


    }






    public static void main(String[] args) throws ClassNotFoundException
    {
        System.out.println("Test");
        connect();
        printToTable();
       // insertData(Number, Score);


    }
}