package Client1;


import util.Log.Log;

import java.io.*;
import java.net.*;

import static Client1.index.saveIndex;


public class Updata {
    public static String name ;
    public static int port;    //默认服务器端口
    public static int serverSocketPort;
    public static String host;   //默认服务器地址
    public static String rsaPublicKey;
    public static String rsaPrivateKey;
    public static int poolSize;
    public static String password;
    public static String aesKey;
    public static String url;
/**
 * description: 服务端上线 
 * version: 1.0 
 * date: 2020/10/28 10:54 
 * author: Revers. 
 * 
 * @param 
 * @return void
 */ 
    public static user[] UpdataNickname(Socket socket) throws IOException {
        //构建IO
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        //向服务器端发送一条消息
        String ipv6 = util.net.getLocalIPv6Address();
        bw.write(ipv6 + "," + name + "," + rsaPublicKey + "\n");
        bw.flush();

        //读取服务器返回的消息
        String mess = br.readLine();

        if (Menu.first == true) {
            System.out.println("服务器：" + mess);
        }

        //接收用户表
        if (Menu.first == true) {
            System.out.println("服务器:已发送用户表至客户端");
        }

        //读取客户端发送来的消息
        String length = br.readLine();
        bw.write(length + "\n");
        bw.flush();
        Log.logger.finest("接收用户数");

        user[] User = new user[Integer.valueOf(length)];
        for (int i = 0; i < Integer.valueOf(length); i++) {
            String message = br.readLine();//用户表
            String[] split = message.split(",");
            User[i] = new user(Integer.valueOf(split[0]), split[1], split[2], split[3], Boolean.valueOf(split[4]));
        }
        Menu.U = User;
        Log.finest("接收用户表");
        bw.write("接收\n");
        bw.flush();

        saveIndex(User);
        Menu.U = User;

        bw.write("朕已阅\n");
        bw.flush();
        Log.finest("用户表接收完成");


        if(Menu.first){
            System.out.println("用户表接收完成");
        }
        Menu.first = false;
        if(main.hasServer == 0) {
            System.out.print("\n" + Updata.name + ":~$ ");
        }
        return User;
    }
}
