package Client1;

import util.Aes;
import util.Rsa;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.FutureTask;

/**
 * description: Client <br>
 * date: 2020/11/18 14:42 <br>
 * author: s1mple <br>
 * version: 1.0 <br>
 */
public class Client {
    public String rsaPublicKey;
    public String aesKey;
    public Scanner get;
    public PrintWriter out;
    public String nickName;

    public boolean auto() {
        try {
            out.println(Updata.rsaPublicKey);
            out.flush();//1.客户机发送公钥
            log.write(Updata.rsaPublicKey);
            //rsaPublicKey = get.nextLine();//1
            System.out.println(Updata.rsaPublicKey);

            //out.println(Rsa.encrypt(rsaPublicKey,Updata.rsaPublicKey));
            //out.flush();//2
            rsaPublicKey = Rsa.privateDecrypt(Updata.rsaPrivateKey, get.nextLine());
            log.write(rsaPublicKey);
            System.out.println(rsaPublicKey);

            aesKey = Aes.getAseKey(256);
            out.println(Rsa.publicEncrypt(rsaPublicKey, aesKey));
            out.flush();
            //aesKey = Rsa.privateDecrypt(Updata.rsaPrivateKey,get.nextLine());//3
            //        System.out.println(aesKey);
            log.write(aesKey);
            System.out.println(aesKey);

            //out.println(Aes.encrypt(aesKey,"ack1"));
            //out.flush();//4
            String decrypt = Aes.decrypt(aesKey, get.nextLine());
            log.write(decrypt);
            System.out.println(decrypt);

            if ("ack1".equals(decrypt)) {
                out.println(Aes.encrypt(aesKey, "ack2"));
                out.flush();
                log.write("密钥传输成功");
                System.out.println("聊天加密成功");
                return true;
            } else {
                out.println(Aes.encrypt(aesKey, "fail"));
                out.flush();
                log.write("密钥传输成功");
                System.out.println("聊天加密失败");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.write(e.getMessage());
            return false;
        }
    }

    public void client(Socket client, String nickName) {
        try {
            get = new Scanner(client.getInputStream());
            out = new PrintWriter(client.getOutputStream());

            boolean flag = false;
            while (flag == false) {
                flag = auto();
            }

            int isclose = 0;
            InetAddress inetAddress = client.getInetAddress();

            SocketWriter socketWriter = new SocketWriter(out);
            socketWriter.setAddress(inetAddress);
            socketWriter.setNickName(nickName);
            FutureTask<Boolean> sw = new FutureTask<>(socketWriter);
            Thread thread = new Thread(sw);
            thread.start();

            SocketReader socketReader = new SocketReader(get);
            socketReader.setAddress(inetAddress);
            socketReader.setNickName(nickName);
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
            System.out.println("结束当前通信");
            return;


        } catch (Exception e) {
            e.printStackTrace();
            log.write(e.getMessage());
        }
    }
}
