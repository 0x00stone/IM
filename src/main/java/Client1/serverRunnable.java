package Client1;

import util.Aes;
import util.Rsa;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * description: serverRunnable <br>
 * date: 2020/12/31 18:22 <br>
 * author: s1mple <br>
 * version: 1.0 <br>
 */
public class serverRunnable extends Thread {
    public String rsaPublicKey;
    public String aesKey;
    public Socket socket = null;
    public PrintWriter out;
    public Scanner get;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public boolean auto() throws BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException {
        rsaPublicKey = get.nextLine();//1
        System.out.println(rsaPublicKey);

        out.println(Rsa.publicEncrypt(rsaPublicKey, Updata.rsaPublicKey));
        out.flush();//2
        System.out.println(Rsa.publicEncrypt(rsaPublicKey, Updata.rsaPublicKey));

        aesKey = Rsa.privateDecrypt(Updata.rsaPrivateKey, get.nextLine());//3
        System.out.println(aesKey);

        out.println(Aes.encrypt(aesKey, "ack1"));
        out.flush();//4
        System.out.println(Aes.encrypt(aesKey, "ack1"));

        String ack = Aes.decrypt(aesKey, get.nextLine());
        System.out.println(ack);
        if ("ack2".equals(ack)) {
            log.write("密钥传输成功");
            System.out.println("聊天加密成功");
            return true;
        } else {
            log.write("密钥传输失败");
            System.out.println("聊天加密失败");
            return false;//5
        }
    }

    @Override
    public void run() {
        try {
            InetAddress inetAddress = socket.getInetAddress();

            System.out.println();
            System.out.println(inetAddress + " 已成功连接到此台服务器上。");
            log.write(inetAddress + " 已成功连接到此台服务器上。");


            out = new PrintWriter(socket.getOutputStream());
            get = new Scanner(socket.getInputStream());

            boolean flag = false;
            while (flag == false) {
                flag = auto();
            }

            SocketWriter socketWriter = new SocketWriter(out);
            socketWriter.setAddress(inetAddress);
            FutureTask<Boolean> sw = new FutureTask<>(socketWriter);
            Thread thread = new Thread(sw);
            thread.start();

            SocketReader socketReader = new SocketReader(get);
            socketReader.setAddress(inetAddress);
            FutureTask<Boolean> sr = new FutureTask<>(socketReader);
            Thread thread2 = new Thread(sr);
            thread2.start();

            System.out.println("检测");
            Boolean th1 = false;
            Boolean th2 = false;

            while (true){
                if(th1){
                    sr.cancel(true);
                    thread2.interrupt();
                }
                if (th2){
                    sw.cancel(true);
                    thread.interrupt();
                }
                th1 = sw.isDone();
                th2 = sr.isDone();
                if(th1 && th2){
                    System.out.println("结束");
                    break;
                }
                Thread.sleep(1000);
            }
            System.out.println("结束当前监听");
            main.hasServer -= 1;
            return;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

