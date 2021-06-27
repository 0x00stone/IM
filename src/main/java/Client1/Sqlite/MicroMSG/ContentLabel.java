package Client1.Sqlite.MicroMSG;

import Client1.Updata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * description: ContentLabel <br>
 * date: 2021/6/27 16:38 <br>
 * author: s1mple <br>
 * version: 1.0 <br>
 */
public class ContentLabel {
    public static void create() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + Updata.url + "MicroMSG.db");
            //  System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE ContactLabel (" +
                    "LabelId INTEGER PRIMARY KEY, " + //标签id
                    "LabelName TEXT, " + //标签名称
                    "Reserved1 INTEGER, " +
                    "Reserved2 INTEGER, " +
                    "Reserved3 TEXT, " +
                    "Reserved4 TEXT, " +
                    "RespData BLOB, " +
                    "Reserved5 BLOB);";
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.out.println("创建ContactLabel表失败");
            System.exit(0);
        }
        System.out.println("创建ContactLabel表成功:)");
    }
}
