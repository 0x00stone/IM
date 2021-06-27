package Client1.Sqlite.MicroMSG;

import Client1.Updata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * description: ChatRoomInfo <br>
 * date: 2021/6/27 16:28 <br>
 * author: s1mple <br>
 * version: 1.0 <br>
 */
public class ChatRoomInfo {
    public static void create() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + Updata.url + "MicroMSG.db");
            //  System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE ChatRoomInfo(" + //群公告表
                    "ChatRoomName TEXT PRIMARY KEY," + //群名称,和上一个表对应
                    "Announcement TEXT," + //公告内容
                    "InfoVersion INTEGER DEFAULT 0," + //公告版本数
                    "AnnouncementEditor TEXT," + //公告编辑者
                    "AnnouncementPublishTime INTEGER DEFAULT 0," +  //公告发布时间
                    "ChatRoomStatus INTEGER DEFAULT 0," + //群状态2的n次划分
                    "Reserved1 INTEGER DEFAULT 0," +
                    "Reserved2 TEXT," +
                    "Reserved3 INTEGER DEFAULT 0," +
                    "Reserved4 TEXT," +
                    "Reserved5 INTEGER DEFAULT 0," +
                    "Reserved6 TEXT," +
                    "Reserved7 INTEGER DEFAULT 0," +
                    "Reserved8 TEXT);";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.out.println("创建ChatRoomInfo表失败");
            System.exit(0);
        }
        System.out.println("创建ChatRoomInfo表成功:)");
    }
}
