package Client1;

import util.Log.Log;

import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;

import static util.Log.Log.logger;


public class main {
    public static Socket socket = null;
    public static Object lock = false;
    public static mainThread mainThread;
    public static int hasServer = 0;
    // public static Logger logger;

    public static void main(String[] args) {
        //logger = util.Log.log.setLoggerHanlder(Level.INFO);
        logger = Log.setLoggerHanlder(Level.FINEST);
        logger.info("日志开启");

        int choice = choice();

        if (choice == 1) {
            int loginnumber = Login.login();
        } else if (choice == 2) {
            int registerNumber = Login.register();
            if (registerNumber == 0) {
                System.out.println("注册失败");
                Log.config("注册失败");
            } else if (registerNumber == 1) {
                System.out.println("注册成功");
                logger.config("注册成功");
                Log.localCloseToUser();

                logger = Log.setLoggerHanlder(Level.FINEST);
                logger.finest("注册成功123");
            }
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
