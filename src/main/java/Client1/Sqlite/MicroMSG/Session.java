package Client1.Sqlite.MicroMSG;

import util.Sqlite;

import java.sql.Connection;
import java.sql.Statement;

/**
 * description: Session <br>
 * date: 2021/6/27 16:42 <br>
 * author: s1mple <br>
 * version: 1.0 <br>
 */
public class Session {
    public static void create() {
        Connection c = null;
        Statement stmt = null;
        try {
            c = Sqlite.getConnection("MicroMSG.db");

            stmt = Sqlite.getStatement(c);
            String sql = "CREATE TABLE Session(" +
                    "strUsrName TEXT  PRIMARY KEY," + //用户名
                    "nOrder INT DEFAULT 0," + //按顺序自动生成序号
                    "nUnReadCount INTEGER DEFAULT 0," +  //当前会话未读条数
                    "parentRef TEXT," +
                    "Reserved0 INTEGER DEFAULT 0," +
                    "Reserved1 TEXT," +
                    "strNickName TEXT," + //当前会话昵称,优先级 : 备注>群名>昵称>用户名
                    "nStatus INTEGER," +
                    "nIsSend INTEGER," + //最新一条消息是否是发送方,是为1,不是为0
                    "strContent TEXT," + //消息内容
                    "nMsgType INTEGER," + //消息类型:1文本消息(含表情),3图片消息,4图文消息,42推荐用户,43视频格式文件,48链接,49文件,50语音聊天,53,地理位置消息10000红包,群里的消息提示
                    "nMsgLocalID INTEGER," + //本地消息id号
                    "nMsgStatus INTEGER," +
                    "nTime INTEGER," +//接收或发送消息的时间
                    "editContent TEXT," +
                    "othersAtMe INT," + //是否有人@我,是为1,不是为0
                    "Reserved2 INTEGER DEFAULT 0," +
                    "Reserved3 TEXT," +
                    "Reserved4 INTEGER DEFAULT 0," +
                    "Reserved5 TEXT," +
                    "bytesXml BLOB);"; //有xml的存放xml内容
            stmt.executeUpdate(sql);

            sql =  "CREATE INDEX nOrderIndex ON Session(nOrder);";
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.out.println("创建Session表失败");
            System.exit(0);
        }
        System.out.println("创建Session表成功:)");
    }
}
