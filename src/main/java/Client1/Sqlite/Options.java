package Client1.Sqlite;
import Client1.Updata;
import util.Aes;
import util.Sha256;
import util.Vigenere;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * description: Settings 存储客户端设置类 <br>
 * date: 2021/6/18 15:14 <br>
 * author: s1mple <br>
 * version: 1.0 <br>
 */
public class Options {
    public static String url = null;


    public static void create() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + url + "options.db");
          //  System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE `options` (\n" +
                    "  `option_id` INTEGER PRIMARY KEY,\n" +
                    "  `option_name` varchar(191) NOT NULL DEFAULT '',\n" +
                    "  `option_value` longtext NOT NULL,\n" +
                    "  `Reserved1` varchar(20)\n" +
                    ");";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO options (option_name,option_value) " +
                    "VALUES (\"username\",\"" + Updata.name + "\");";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO options (option_name,option_value) " +
                    "VALUES (\"port\",\"" + Updata.port + "\");";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO options (option_name,option_value) " +
                    "VALUES (\"poolSize\",\"" + Updata.poolSize + "\");";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO options (option_name,option_value) " +
                    "VALUES (\"serverSocketPort\",\"" + Updata.serverSocketPort + "\");";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO options (option_name,option_value) " +
                    "VALUES (\"host\",\"" + Updata.host + "\");";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO options (option_name,option_value) " +
                    "VALUES (\"password\",\"" + util.md5.md5(Updata.password)+ "\");";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO options (option_name,option_value) " +
                    "VALUES (\"rsaPublicKey\",\"" + Updata.rsaPublicKey + "\");";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO options (option_name,option_value) " +
                    "VALUES (\"rsaPrivateKey\",\"" + Aes.encrypt(Updata.aesKey,Updata.rsaPrivateKey) + "\");";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO options (option_name,option_value) " +
                    "VALUES (\"aesKey\",\"" + Vigenere.jiami_vigenere(Updata.aesKey, Sha256.getSHA256(Updata.password)) + "\");";
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.out.println("用户创建失败");
            System.exit(0);
        }
        System.out.println("用户新建成功:)");
    }

    public static void insert(String name,String value){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + url + "options.db");
            c.setAutoCommit(false);
           // System.out.println("Opened database successfully");

            stmt = c.createStatement();

            String sql = "INSERT INTO options (option_name,option_value) " +
                    "VALUES (\"" + name +"\",\"" + value + "\");";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    public static void remove(){}

    public static void update(){}

    public static String select(String name1){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + url + "options.db");
            c.setAutoCommit(false);
            //System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM `options`;" );
            while ( rs.next() ) {
                String optionName= rs.getString("option_name");
                if(name1.equals(optionName)){
                    return rs.getString("option_value");
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return "";
    }


    public static void setUrl(String url) {
        Options.url = url;
    }

    public static String getUrl() {
        return url;
    }
}
