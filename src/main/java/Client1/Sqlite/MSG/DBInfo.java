package Client1.Sqlite.MSG;

import Client1.Updata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * description: DBInfo <br>
 * date: 2021/6/27 17:47 <br>
 * author: s1mple <br>
 * version: 1.0 <br>
 */
public class DBInfo {
    public static void create() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + Updata.url + "MSG0.db");
            //  System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE DBInfo (" +
                    "tableIndex INTEGER PRIMARY KEY," +
                    "tableVersion INTERGER," +
                    "tableDesc TEXT);";
            stmt.executeUpdate(sql);

            sql = "CREATE INDEX versionIdx ON DBInfo(tableIndex);";
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
