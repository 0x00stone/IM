package Client1;

import util.Welcome;

/**
 * description: mainThread <br>
 * date: 2020/12/25 15:13 <br>
 * author: s1mple <br>
 * version: 1.0 <br>
 */
public class mainThread extends Thread {

    public void run() {
        this.setPriority(MIN_PRIORITY);
        //log开始
        try {
            log.Clientstart();
            log.write("启动");

            Welcome.graph();

            //获取名称

            //本地登录 , 获取密钥

            System.out.println();
            System.out.println();
            Menu.printMenu();
            synchronized (main.lock) {
                while (true) {
                    if (main.hasServer == 0) {
                        System.out.print(Updata.name + ":~$ ");
                        Menu.scan();//看作Scanner.next()
                    }
                    Thread.sleep(200);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
