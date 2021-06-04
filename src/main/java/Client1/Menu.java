package Client1;

import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;


/**
 * description: Menu <br>
 * date: 2020/10/28 15:13 <br>
 * author: s1mple <br>
 * version: 1.0 <br>
 */
public class Menu {
    public static user[] U;
    static public boolean first; //第一次连接服务器终端有输出,之后心跳连接不输出

    public static void printMenu() {
        System.out.println("-----------------------------------------------------");
        System.out.println("+ -- --=[ Instant Messangeing v0.0.2                ]");
        System.out.println("+ -- --=[ 有关某个命令的详细信息，请键入 HELP 命令名        ]");
        System.out.println("+ -- --=[   login         连接服务器进入频道            ]");
        System.out.println("+ -- --=[   connectv4     同局域网下                  ]");
        System.out.println("+ -- --=[   connectv6     互联网中双方具有ipv6          ]");
        System.out.println("+ -- --=[   user          查看本地用户表                ]");
        System.out.println("+ -- --=[   help          帮助菜单                   ]");
        System.out.println("------------------------------------------------------");
    }

    public static void scan(Object lock) {
        try {
            String[] choice = new Scanner(System.in).nextLine().split(" ");
            if (choice.length >= 2 && "help".equals(choice[1])) {
                if ("login".equals(choice[0])) {
                    System.out.println("      描述:连接服务器,客户端在服务器上线,获取服务器上存储的用户表");
                    System.out.println("      用法:login [ip]");
                    System.out.println("                 ip:服务器ip地址,默认使用配置文件中地址");
                } else if ("connectv4".equals(choice[0])) {
                    System.out.println("      描述:同局域网下点对点通信");
                    System.out.println("      用法:connectv4 [ip] [port]");
                    System.out.println("                    ip:对方客户机ipv4地址");
                    System.out.println("                    port:对方客户机监听端口");
                } else if ("connectv6".equals(choice[0])) {
                    System.out.println("      描述:广域网下点对点通信");
                    System.out.println("      用法:connectv6 [ip] [port] [username]");
                    System.out.println("                     ip:对方客户机ipv6地址");
                    System.out.println("                     port:对方客户机监听端口");
                    System.out.println("                     username:用户表中对方用户名");
                } else if ("user".equals(choice[0])) {
                    System.out.println("      描述:打印本地存储的用户表");
                    System.out.println("      用法:user");
                } else if ("help".equals(choice[0])) {
                    System.out.println("      描述:打印菜单页面");
                    System.out.println("      用法:menu");
                }
                return;
            }

            if ("login".equals(choice[0])) {
                //socket连接
                first = true;
                new heartBeat().start();
            } else if ("connectv4".equals(choice[0])) {
                //ip和端口
                if (choice.length == 2) {
                    main.socket = new Socket(choice[1], 9000);
                } else if (choice.length == 3) {
                    main.socket = new Socket(choice[1], Integer.parseInt(choice[2]));
                }
                new Client().client(main.socket, lock, "");

            } else if ("user".equals(choice[0])) {
                System.out.println("用户名-----" + "ipv6----" + "状态-----" + "公钥");
                for (int i = 0; i < U.length; i++) {
                    System.out.println(U[i].NickName + "-----" + U[i].IPv6 + "-----" + U[i].status + "-----" + U[i].key);
                }
            } else if ("connectv6".equals(choice[0])) {
                if (choice.length == 2) {
                    //用户名,连接数据库,未写
                    for (user users : U) {
                        if (choice[1].equals(users.NickName)) {
                            main.socket = new Socket(users.IPv6, 9000);   //2的要改
                            new Client().client(main.socket, lock, users.NickName);

                        }
                    }
                } else if (choice.length == 3) {
                    //ip和端口
                    main.socket = new Socket(choice[1], Integer.parseInt(choice[2]));
                    new Client().client(main.socket, lock, "");
                }
            } else if ("help".equals(choice[0])) {
                printMenu();
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.write(e.toString());
        }
    }

}
