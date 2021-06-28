package Client1;

import java.net.Socket;

/**
 * description: heartBeat <br>
 * date: 2020/12/4 16:19 <br>
 * author: s1mple <br>
 * version: 1.0 <br>
 */
public class heartBeat extends Thread {

    @Override
    public void run() {
        synchronized (this) {
            try {
                //服务器上线
                main.socket = new Socket(Updata.host, Updata.port);
                while (true) {
                    //获取 用户表
                    Updata.UpdataNickname(main.socket);
                    util.Log.log.finest("心跳程序开启");
                    this.wait(60000);
                }
            } catch (Exception e) {
                e.printStackTrace();
                util.Log.log.warning("心跳程序出错:" + e.getMessage());
            }
        }
    }
}

