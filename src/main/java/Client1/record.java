package Client1;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description: record 处理聊天记录类 <br>
 * date: 2021/6/9 13:41 <br>
 * author: s1mple <br>
 * version: 1.0 <br>
 */
public class record {

    private FileWriter frecord;

    public void Clientstart(){
        try{
            frecord = new FileWriter("Clientlog.txt",true);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void write(String items){
        try {
            Clientstart();
            String format = new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date());
            frecord.write(items + " : " + format + "\n");
            frecord.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
