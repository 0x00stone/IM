package Client1;

import java.net.Socket;


public class main {
    public static Socket socket = null;
    public static Object LockServer = false;
    public static Object LockClick = false;
    public static mainThread mainThread;

    public static void main(String[] args){
        Updata updata = new Updata();
        updata.getConfig();//加载配置

        Server server = new Server();
        server.start();//监听线程

        mainThread = new mainThread();
        mainThread.start();//主线程



    }
}
