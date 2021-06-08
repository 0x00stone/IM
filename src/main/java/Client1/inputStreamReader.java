package Client1;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * description: inputStreamReader <br>
 * date: 2021/6/8 22:48 <br>
 * author: s1mple <br>
 * version: 1.0 <br>
 */
public class inputStreamReader extends InputStreamReader {
    public inputStreamReader(InputStream in) {
        super(in);
    }

    @Override
    public void close() throws IOException {
        System.in.reset();
    }
}
