package Client1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * description: Server <br>
 * date: 2020/11/18 14:41 <br>
 * author: s1mple <br>
 * version: 1.0 <br>
 */
public class Server extends Thread{
    static ServerSocket server;

    static{
        try {
            server=new ServerSocket(Updata.serverSocketPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void run(){

        try {

                System.out.println("服务正在运行，等待客户端连接！");
                log.write("点对点服务端开启");
                ExecutorService pool = Executors.newFixedThreadPool(Updata.poolSize);//线程池大小
            while (true) {
                Socket client = server.accept();
                //接收socket,打断主线程输入
                //lock.wait();

                    main.LockClick.wait();
                    System.out.println("收到socket");
                    serverRunnable serverRunnable = new serverRunnable();
                    serverRunnable.setSocket(client);
                    pool.submit(serverRunnable);

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
