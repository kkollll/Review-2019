import org.junit.jupiter.api.Test;

import java.io.*;

public class WriteAof {

    private WriteAof() {
    }


    @Test
    public void write() throws IOException {

        BufferedWriter bf = null;
        StringBuilder sb = new StringBuilder();
        try {
            bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("a.txt"),"utf-8"));
            for (int i = 0; i < 100000; i++) {
                if (i / 30000 == 0) {
                    bf.flush();
                }
                sb.setLength(0);
                sb.append(getString("set", "u" + i, "p" + i));
                bf.append(sb.toString());
            }
        } catch (Exception e) {
        } finally {
            try {
                bf.flush();
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getString(String... args) {
        StringBuilder sb = new StringBuilder();
        sb.append("*").append(args.length).append("\r\n");
        for (String arg : args) {
            sb.append("$").append(arg.length()).append("\r\n");
            sb.append(arg).append("\r\n");
        }
        return sb.toString();
    }

}
