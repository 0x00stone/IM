package Client1;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.concurrent.Callable;

/**
 * description: SocketWriter <br>
 * date: 2020/11/18 10:56 <br>
 * author: s1mple <br>
 * version: 1.0 <br>
 */
public class SocketWriter implements Callable {
    private PrintWriter out;
    private Scanner get_key = null;
    private InetAddress address;
    private Object lock;
    private String name;
    private int num;

    public void setNum(int num) {
        this.num = num;
    }

    public void setNickName(String name) {
        this.name = name;
    }

    public void setmain(Object lock) {
        this.lock = lock;
    }

    public SocketWriter(PrintWriter out) {
        this.out = out;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public Boolean call() {
            out.println("已成功连接到远程服务器！");
            out.flush();//将缓冲流中的数据全部输出

            while (true) {
                try {
                    System.out.print(name + ">");
                    get_key = new Scanner(System.in);
                    String msg = get_key.nextLine();
                    if (Thread.currentThread().isInterrupted()){
                        System.out.println("连接已中断");
                        return true;
                    }
                    out.println(msg);
                    out.flush();
                    if ("/quit".equals(msg)) {
                        System.out.println("连接已中断");
                        return true;
                    }
                    System.out.print(name + ">");


                } catch (Exception e) {
                    System.out.println("连接已断开！");
                    e.printStackTrace();
                    return true;
                }


            }
        }
    }

