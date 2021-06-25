package Client1;

import Client1.Sqlite.Options;
import util.Aes;
import util.Sha256;
import util.Vigenere;

import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;


public class main {
    public static Socket socket = null;
    public static Object lock = false;
    public static mainThread mainThread;
    public static int hasServer = 0;

    public static void main(String[] args) {

        int choice = choice();

        if (choice == 1) {
            Login.login();
        } else if (choice == 2) {
            Login.register();
        } else {
            System.exit(0);
        }

        /*Options.create();
        Updata updata = new Updata();
        updata.getConfig();//加载配置

        Server server = new Server();
        server.start();//监听线程

        mainThread = new mainThread();
        mainThread.start();//主线程*/

    }

    private static int choice() {
        int choice = 0;
        System.out.println("1.登陆账号");
        System.out.println("2.注册账号");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            if (choice == 1 || choice == 2) {
                System.out.println();
                return choice;
            } else {
                System.out.println();
                choice = choice();
            }
        } else {
            System.out.println();
            choice = choice();
        }
        return choice;
    }
}
