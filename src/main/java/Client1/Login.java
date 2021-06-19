package Client1;

import Client1.Sqlite.Options;
import util.Rsa;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

/**
 * description: Login <br>
 * date: 2021/6/18 16:18 <br>
 * author: s1mple <br>
 * version: 1.0 <br>
 */
public class Login {
    public static void login() {
        System.out.println("请输入用户名:");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();

        String filePath = System.getProperty("user.home") +"\\Documents\\IM\\data\\id_" + username  + "\\";
        Options.setUrl(filePath);
        File file = new File(filePath);
        if(file.exists()){
            System.out.println("请输入密码:");
            String password = scanner.nextLine();
            String optionsPassword = Options.select("password");
            if(optionsPassword.equals(util.md5.md5(password))){
                //登陆,加载配置,解密私钥
                Updata.password = password;
                Updata.rsaPublicKey = Options.select("rsaPublicKey");
                Updata.rsaPrivateKey = Options.select("rsaPrivateKey");
                Updata.host = Options.select("host");
                Updata.name = username;
                Updata.poolSize = Integer.valueOf(Options.select("poolSize"));
                Updata.port = Integer.valueOf(Options.select("port"));
                Updata.serverSocketPort = Integer.valueOf(Options.select("serverSocketPort"));
                System.out.println("登陆成功");

            }else{
                System.out.println("密码错误,请重新登陆");
            }
        }else {
            System.out.println("当前计算机无" + username + "用户存档");
        }
    }

    public static void register() { //还需要对私钥通过密码加密
        System.out.println("请输入用户名:");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();

        System.out.println("请输入密码:");
        String password1 = scanner.nextLine();

        System.out.println("请在一次输入密码:");
        String password2 = scanner.nextLine();

        if (password1.equals(password2) && !"".equals(password1)){
            Updata.name = username;
            Map<Integer, String> rsaKey = Rsa.getRsaKey(4096);
            Updata.rsaPublicKey = rsaKey.get(0);
            Updata.rsaPrivateKey = rsaKey.get(1);
            Updata.host = "1.15.141.195";
            Updata.port = 10000;
            Updata.serverSocketPort = 9000;
            Updata.poolSize = 5;
            Updata.password = util.md5.md5(password1);

            String filePath = System.getProperty("user.home") +"\\Documents\\IM\\data\\id_" + username  + "\\";
            File file = new File(filePath);
            if(!file.exists()){
                file.mkdirs();
                System.out.println("文件夹创建完毕");
                Options.setUrl(filePath);
                Options.create();
                System.out.println("数据库创建成功");
            }else{
                System.out.println("本地以有同名用户,请更改名称重新注册");
                register();
            }
        }else{
            System.out.println("两次密码不一致,请重新注册");
            register();
        }
    }


}
