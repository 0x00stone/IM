package Client1;

import java.net.InetAddress;
import java.util.Scanner;

/**
 * description: SocketReader <br>
 * date: 2020/11/18 10:58 <br>
 * author: s1mple <br>
 * version: 1.0 <br>
 */
public class SocketReader extends Thread {
    private Scanner get;
    private InetAddress address;
    private Object lock;
    private String name;

    public SocketReader(Scanner get) {
        this.get = get;
    }

    public void setmain(Object lock) {
        this.lock = lock;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public void setNickName(String name) {
        this.name = name;
    }


    public void run() {
       // synchronized (lock) {

            while (true) {
                    try {

                        String next = get.nextLine();
                        if ("/quit".equals(next)) {
                            System.out.println("退出");
                            lock.notify();

                            break;
                        }
                        System.out.println(name + "(" + address + ")" + ":" + next);
                        System.out.print(Updata.name  + ">");

                    } catch (Exception e) {
                        System.out.println("连接已中断");
                        lock.notify();
                        return;
                   // }
            }
        }
    }}