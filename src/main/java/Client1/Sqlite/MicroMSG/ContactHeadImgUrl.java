package Client1.Sqlite.MicroMSG;

import Client1.Updata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * description: ContactHeadImgUrl <br>
 * date: 2021/6/27 16:33 <br>
 * author: s1mple <br>
 * version: 1.0 <br>
 */
public class ContactHeadImgUrl {
    public static void create() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + Updata.url + "MicroMSG.db");
            //  System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE ContactHeadImgUrl(" + //图片存放在文件夹中,数据库中保存地址
                    "usrName TEXT PRIMARY KEY," + //用户名称
                    "smallHeadImgUrl TEXT," + //小尺寸头像
                    "bigHeadImgUrl TEXT," + //大尺寸头像
                    "headImgMd5 TEXT," + //两张头像md5
                    "reverse0 INT," +
                    "reverse1 TEXT);";
            stmt.executeUpdate(sql);

            sql = "CREATE INDEX reverse0Index ON ContactHeadImgUrl(reverse0);";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.out.println("创建ContactHeadImgUrl表失败");
            System.exit(0);
        }
        System.out.println("创建ContactHeadImgUrl表成功:)");
    }
}
