package Client1.Sqlite.MSG;

import Client1.Updata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * description: MSG <br>
 * date: 2021/6/27 17:50 <br>
 * author: s1mple <br>
 * version: 1.0 <br>
 */
public class MSG {
    public static void create() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + Updata.url + "MSG0.db");
            //  System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE MSG (" +
                    "localId INTEGER PRIMARY KEY AUTOINCREMENT," + //本地存储消息编号,自增
                    "TalkerId INT DEFAULT 0," + //通信对方
                    "MsgSvrID INT," + //不为空说明服务器同步过
                    "Type INT," +  //消息类型:1文本消息(含表情),3图片消息,4图文消息,42推荐用户,43视频格式文件,48链接,49文件,50语音聊天,53,地理位置消息10000红包,群里的消息提示
                    "SubType INT," + //49文件下面包含的文件格式
                    "IsSender INT," +//最新一条消息是否是发送方,是为1,不是为0
                    "CreateTime INT," + //聊天记录发送时间
                    "Sequence INT DEFAULT 0," + //createTime + 同时发生的序号
                    "StatusEx INT DEFAULT 0," +
                    "FlagEx INT," +
                    "Status INT," +
                    "MsgServerSeq INT," +
                    "MsgSequence INT," +//接收与发送消息的顺序的编号
                    "StrTalker TEXT," +//通信方
                    "StrContent TEXT," +//通信内容
                    "DisplayContent TEXT," +
                    "Reserved0 INT DEFAULT 0," +
                    "Reserved1 INT DEFAULT 0," +
                    "Reserved2 INT DEFAULT 0," +
                    "Reserved3 INT DEFAULT 0," +
                    "Reserved4 TEXT," +
                    "Reserved5 TEXT," +
                    "Reserved6 TEXT," +
                    "CompressContent BLOB," +
                    "BytesExtra BLOB," + //消息的md5 + 附带的文件存储地址
                    "BytesTrans BLOB);";
            stmt.executeUpdate(sql);

            sql = "CREATE INDEX MsgTalkerIdSeqIndex ON MSG(talkerId,sequence DESC);";
            stmt.executeUpdate(sql);

            sql = "CREATE INDEX MsgTalkerIdTypeSeqIndex ON MSG(talkerId,type,sequence DESC);";
            stmt.executeUpdate(sql);

            sql = "CREATE INDEX SvrIdIndex ON MSG(MsgSvrID);";
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.out.println("创建MSG表失败");
            System.exit(0);
        }
        System.out.println("创建MSG表成功:)");
    }
}
