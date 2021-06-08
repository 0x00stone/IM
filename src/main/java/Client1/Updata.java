package Client1;


import util.Rsa;

import java.io.*;
import java.net.*;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import static Client1.index.getIndex;
import static Client1.index.saveIndex;


public class Updata {
    public static String name ;
    public static int port;    //默认服务器端口
    public static int serverSocketPort;
    public static String host;   //默认服务器地址
    public static String rsaPublicKey;
    public static String rsaPrivateKey;
    public static int poolSize;
/**
 * description: 服务端上线 
 * version: 1.0 
 * date: 2020/10/28 10:54 
 * author: Revers. 
 * 
 * @param 
 * @return void
 */ 
    public static user[] UpdataNickname(Socket socket) throws IOException {
        //构建IO
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        //向服务器端发送一条消息
        String ipv6 = getLocalIPv6Address();
        bw.write(ipv6 + "," + name + "," + rsaPublicKey + "\n");
        bw.flush();

        //读取服务器返回的消息
        String mess = br.readLine();

        if (Menu.first == true) {
            System.out.println("服务器：" + mess);
        }

        //接收用户表
        if (Menu.first == true) {
            System.out.println("服务器:已发送用户表至客户端");
        }

        //读取客户端发送来的消息
        String length = br.readLine();
        bw.write(length + "\n");
        bw.flush();
        log.write("接收用户数");

        user[] User = new user[Integer.valueOf(length)];
        for (int i = 0; i < Integer.valueOf(length); i++) {
            String message = br.readLine();//用户表
            String[] split = message.split(",");
            User[i] = new user(Integer.valueOf(split[0]), split[1], split[2], split[3], Boolean.valueOf(split[4]));
        }
        Menu.U = User;
        log.write("接收用户表");
        bw.write("接收\n");
        bw.flush();

        saveIndex(User);
        Menu.U = User;

        bw.write("朕已阅\n");
        bw.flush();
        log.write("用户表接收完成");


        if(Menu.first){
            System.out.println("用户表接收完成");
        }
        Menu.first = false;
        System.out.print("\n" + Updata.name + ":~$ ");

        return User;
    }


    /**
     * description: 有用户名配置文件,读文件,否则输入用户名
     * version: 1.0
     * date: 2020/10/21 14:58
     * author: Revers.
     *
     * @param
     * @return void
     */
    public void getConfig(){
    try{
        if(new File("./Config.properties").exists()){
            FileInputStream fis = new FileInputStream(new File("./Config.properties"));
            Properties Config = new Properties();
            Config.load(fis);

            //username
            name = Config.getProperty("userName");
            if(name != null || "null".equals(name)) {
                System.out.println("欢迎用户 " + name + " 回来");
            }else {
                name = addName(Config);
            }

            //key
            rsaPrivateKey = Config.getProperty("RsaPrivateKey");
            rsaPublicKey = Config.getProperty("RsaPublicKey");
            if(rsaPublicKey == null || rsaPrivateKey == null){
                Map<Integer, String> key = newKey(Config);
                rsaPublicKey = key.get(0);
                rsaPrivateKey = key.get(1);
            }

            host = Config.getProperty("host");
            if(host == null){
                host = newhost(Config);
            }

            String stringport = Config.getProperty("port");
            if(stringport == null){
                port = Integer.parseInt(newport(Config));
            }else {
                port = Integer.parseInt(stringport);
            }

            String stringserverSocketport = Config.getProperty("serverSocketPort");
            if(stringserverSocketport == null){
                serverSocketPort = Integer.parseInt(newserverSocketPort(Config));
            }else {
                serverSocketPort = Integer.parseInt(stringserverSocketport);
            }

            String stringpoolSize = Config.getProperty("poolSize");
            if(stringpoolSize == null){
                poolSize = Integer.parseInt(newpoolSize(Config));
            }else{
                poolSize = Integer.parseInt(stringpoolSize);
            }

        }else{
            FileOutputStream fos = new FileOutputStream(new File("./Config.properties"));
            Properties Config = new Properties();
            name = addName(Config);

            Map<Integer, String> key = newKey(Config);
            rsaPublicKey = key.get(0);
            rsaPrivateKey = key.get(1);

            host = newhost(Config);

            port = Integer.parseInt(newport(Config));

            serverSocketPort = Integer.parseInt(newserverSocketPort(Config));

            poolSize = Integer.parseInt(newpoolSize(Config));
            Config.store(fos,"new");
        }
        Menu.U = getIndex();
    }catch (Exception e){
        e.printStackTrace();
        log.write(e.getMessage());
    }
}
    public String addName(Properties properties) {
        System.out.println("请输入用户名:");
        Scanner scanner = new Scanner(System.in);
        name = scanner.nextLine();
        setProperties("userName", name, "new name",properties);
        System.out.println("欢迎用户 " + name + " 登录");

        return name;
    }

    public Map<Integer, String> newKey(Properties properties) {
        Map<Integer, String> rsaKey = Rsa.getRsaKey(1024);
        rsaPublicKey = rsaKey.get(0);
        rsaPrivateKey = rsaKey.get(1);
        setProperties("RsaPublicKey", rsaPublicKey, "rsapublic",properties);
        setProperties("RsaPrivateKey", rsaPrivateKey, "rsaprivate",properties);

        return rsaKey;
    }

    public String newhost(Properties properties) {
        setProperties("host", "1.15.141.195","默认连接服务器地址",properties);
        return "1.15.141.195";
    }

    public String newport(Properties properties){
        setProperties("port", "10000","默认连接服务器端口",properties);
        return "10000";
    }

    public String newserverSocketPort(Properties properties){
        setProperties("serverSocketPort", "9000","客户端监听端口",properties);
        return "9000";
    }

    public String newpoolSize(Properties properties){
        setProperties("poolSize","5","默认线程池大小",properties);
        return "5";
    }

    public void setProperties(String name,String value,String remarks,Properties properties){
        try {
            properties.setProperty(name, value);

            FileOutputStream fos = new FileOutputStream(new File("./Config.properties"));
            properties.store(fos, remarks);//文件名和路径
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
            log.write(e.getMessage());
        }
    }

    public static String getLocalIPv6Address() throws SocketException {
        InetAddress inetAddress =null;

        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        outer:
        while(networkInterfaces.hasMoreElements()) {
            Enumeration<InetAddress> inetAds = networkInterfaces.nextElement().getInetAddresses();
            while(inetAds.hasMoreElements()) {
                inetAddress = inetAds.nextElement();
                //检查此地址是否是IPv6地址以及是否是保留地址
                if(inetAddress instanceof Inet6Address&& !isReservedAddr(inetAddress)) {
                    break outer;

                }
            }
        }
        String ipAddr = inetAddress.getHostAddress();
        //过滤网卡
        int index = ipAddr.indexOf('%');
        if(index>0) {
            ipAddr = ipAddr.substring(0, index);
        }

        return ipAddr;
    }
    private static boolean isReservedAddr(InetAddress inetAddr) {
        if(inetAddr.isAnyLocalAddress()||inetAddr.isLinkLocalAddress()||inetAddr.isLoopbackAddress())
        {
            return true;
        }
        return false;
    }

}
