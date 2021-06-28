package Client1.Sqlite.MicroMSG;

import util.Sqlite;

import java.sql.Connection;
import java.sql.Statement;

/**
 * description: Chatroom <br>
 * date: 2021/6/27 16:22 <br>
 * author: s1mple <br>
 * version: 1.0 <br>
 */
public class Chatroom {

    public static void create() {
        Connection c = null;
        Statement stmt = null;
        try {
            c = Sqlite.getConnection("MicroMSG.db");

            stmt = Sqlite.getStatement(c);
            String sql = "CREATE TABLE ChatRoom(" + //聊天群成员和个人设置表
                    "ChatRoomName TEXT PRIMARY KEY," + //聊天室名称 = 随机id + @chatroom
                    "UserNameList TEXT," +  //用户id列表
                    "DisplayNameList TEXT," +  //显示用户名称,有别名的用别名,有备注的用备注
                    "ChatRoomFlag int Default 0," + //是否存在,存在为1,不存在为0
                    "Owner INTEGER DEFAULT 0," + //是否是当前群众或管理员
                    "IsShowName INTEGER DEFAULT 0," + //是否显示群成员昵称
                    "SelfDisplayName TEXT," + //在群中的昵称
                    "Reserved1 INTEGER DEFAULT 0," +
                    "Reserved2 TEXT," +
                    "Reserved3 INTEGER DEFAULT 0," +
                    "Reserved4 TEXT," +
                    "Reserved5 INTEGER DEFAULT 0," +
                    "Reserved6 TEXT," +
                    "RoomData BLOB," +//用户id+昵称数组
                    "Reserved7 INTEGER DEFAULT 0," +
                    "Reserved8 TEXT);";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.out.println("创建ChatRoom表失败");
            System.exit(0);
        }
        System.out.println("创建ChatRoom表成功:)");
    }
}
