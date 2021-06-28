package util;

import Client1.Updata;
import com.mysql.cj.util.StringUtils;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: SqliteUtil <br>
 * date: 2021/6/28 19:26 <br>
 * author: s1mple <br>
 * version: 1.0 <br>
 */
public class Sqlite {
    private final static String CLASS_NAME = "org.sqlite.JDBC";
    private final static String url = "jdbc:sqlite:" + Updata.url;

    /**
     * 获取数据库连接
     */
    public static Connection getConnection(String dbname) throws SQLException, ClassNotFoundException {
        if ("".equals(dbname)) {
            return null;
        }
        Class.forName(CLASS_NAME);
        if (!dbname.endsWith(".db")) {
            dbname = dbname + ".db";
        }
        return DriverManager.getConnection(url + dbname);
    }

    /**
     * 获取一个数据连接声明
     */
    public static Statement getStatement(Connection conn) throws SQLException {
        if (null == conn) {
            return null;
        }
        return conn.createStatement();
    }


    /**
     * 关闭声明
     */
    public static void closeStatement(Statement statement) throws SQLException {
        if (null != statement && !statement.isClosed()) {
            statement.close();
        }
    }

    /**
     * 关闭连接
     */
    public static void closeConnection(Connection conn) throws SQLException {
        if (null != conn && !conn.isClosed()) {
            conn.close();
        }
    }


}
