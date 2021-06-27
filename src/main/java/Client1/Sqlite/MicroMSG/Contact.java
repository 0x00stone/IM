package Client1.Sqlite.MicroMSG;

import Client1.Updata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * description: Contact <br>
 * date: 2021/6/27 16:31 <br>
 * author: s1mple <br>
 * version: 1.0 <br>
 */
public class Contact {

    public static void create() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + Updata.url + "MicroMSG.db");
            //  System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE Contact(" + //添加用户通讯录表
                    "UserName TEXT PRIMARY KEY ," +  //用户名
                    "Alias TEXT," + //别名
                    "DelFlag INTEGER DEFAULT 0," + //删除标记
                    "Type INTEGER DEFAULT 0," +  /*
                            好友类型
                   数字     二进制       意义
                   0       0           微信运动
                   1       1           微信应用
                   2       10          app+群
                   3       11          好友
                   4       100         群里面的人
                   6       110         群好友,对方加你,你未通过
                   7       111         群里面的,而且互为好友
                   11      1011        拉黑别人
                   67      1000011     标星
                   256     100000000   删除好友
                   259     100000011   不让他看我的朋友圈
                   65539   10000000000000011 不看它的朋友圈

                    */
                    "Reserved1 INTEGER DEFAULT 0," +  //为0,该用户存在于通讯录中;为2该用户为群内用户,不存在于通讯录中
                    "Reserved2 INTEGER DEFAULT 0," +  //为1,该用户存在于通讯录中;为2该用户为群内用户,不存在于通讯录中,用户自身和文件传输助手都是2
                    "Reserved3 TEXT," +
                    "Reserved4 TEXT," +
                    "Remark TEXT," + //备注
                    "NickName TEXT," + //用户昵称
                    "LabelIDList TEXT," + //标签
                    "DomainList TEXT," +
                    "ChatRoomType int," + //是否是群,是为1,不是为0,自己和文件传输助手也为1
                    "PYInitial TEXT," + //昵称每个字的第一个拼音,便于搜索
                    "QuanPin TEXT," + //昵称全拼
                    "RemarkPYInitial TEXT," + //备注每个字的第一个拼音
                    "RemarkQuanPin TEXT," + //备注的全拼
                    "BigHeadImgUrl TEXT," +
                    "SmallHeadImgUrl TEXT," +
                    "HeadImgMd5 TEXT," +
                    "ChatRoomNotify INTEGER DEFAULT 0," + //群通知标记
                    "Reserved5 INTEGER DEFAULT 0," +
                    "Reserved6 TEXT," +
                    "Reserved7 TEXT," +
                    "ExtraBuf BLOB," +
                    "Reserved8 INTEGER DEFAULT 0," +
                    "Reserved9 INTEGER DEFAULT 0," +
                    "Reserved10 TEXT," +
                    "Reserved11 TEXT);\n";
            stmt.executeUpdate(sql);

            sql = "CREATE INDEX Contact_Idx0 ON Contact(Reserved2);"; //添加用户通讯录索引
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.out.println("创建Contact表失败");
            System.exit(0);
        }
        System.out.println("创建Contact表成功:)");
    }
}
