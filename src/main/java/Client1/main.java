package Client1;

import Client1.Sqlite.Options;

import java.net.Socket;


public class main {
    public static Socket socket = null;
    public static Object lock = false;
    public static mainThread mainThread;
    public static int hasServer = 0;

    public static void main(String[] args){
//        Login.register();
        Login.login();

        /*Options.create();
        Updata updata = new Updata();
        updata.getConfig();//加载配置

        Server server = new Server();
        server.start();//监听线程

        mainThread = new mainThread();
        mainThread.start();//主线程*/

    }
}
