package Client1.Sqlite;

import Client1.Updata;
import util.Aes;
import util.Sha256;
import util.Vigenere;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * description: Contact 通讯录<br>
 * date: 2021/6/25 23:26 <br>
 * author: s1mple <br>
 * version: 1.0 <br>
 */
public class Contact {
    private static String url = System.getProperty("user.home") + "\\Documents\\IM\\data\\id_" + Updata.name + "\\";

    public static void create() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + url + "Contact.db");
            //  System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE Contact(" +
                    "UserName TEXT PRIMARY KEY ," +
                    "Alias TEXT," +
                    "EncryptUserName TEXT," +
                    "DelFlag INTEGER DEFAULT 0," +
                    "Type INTEGER DEFAULT 0," +
                    "VerifyFlag INTEGER DEFAULT 0," +
                    "Reserved1 INTEGER DEFAULT 0," +
                    "Reserved2 INTEGER DEFAULT 0," +
                    "Reserved3 TEXT," +
                    "Reserved4 TEXT," +
                    "Remark TEXT," +
                    "NickName TEXT," +
                    "LabelIDList TEXT," +
                    "DomainList TEXT," +
                    "ChatRoomType int," +
                    "PYInitial TEXT," +
                    "QuanPin TEXT," +
                    "RemarkPYInitial TEXT," +
                    "RemarkQuanPin TEXT," +
                    "BigHeadImgUrl TEXT," +
                    "SmallHeadImgUrl TEXT," +
                    "HeadImgMd5 TEXT," +
                    "ChatRoomNotify INTEGER DEFAULT 0," +
                    "Reserved5 INTEGER DEFAULT 0," +
                    "Reserved6 TEXT," +
                    "Reserved7 TEXT," +
                    "ExtraBuf BLOB," +
                    "Reserved8 INTEGER DEFAULT 0," +
                    "Reserved9 INTEGER DEFAULT 0," +
                    "Reserved10 TEXT," +
                    "Reserved11 TEXT);";
            stmt.executeUpdate(sql);

            sql = "CREATE INDEX Contact_Idx0 ON Contact(Reserved2);";
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
            System.out.println("创建Contact.db成功");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.out.println("用户创建失败");
            System.exit(0);
        }
        System.out.println("用户新建成功:)");
    }
}
