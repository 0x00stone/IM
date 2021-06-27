package Client1.Sqlite.MSG;

import Client1.Updata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * description: Name2ID <br>
 * date: 2021/6/27 18:15 <br>
 * author: s1mple <br>
 * version: 1.0 <br>
 */
public class Name2ID {
    public static void create() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + Updata.url + "MSG0.db");
            //  System.out.println("Opened database successfully");


            String sql = "CREATE TABLE Name2ID(UsrName TEXT PRIMARY KEY);";
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.out.println("创建DBInfo表失败");
            System.exit(0);
        }
        System.out.println("创建DBInfo表成功:)");
    }
}
