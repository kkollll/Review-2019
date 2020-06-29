import org.junit.jupiter.api.Test;

import java.io.*;

public class Main {
    /**
     * 格式化成输入字符串
     */
    private String getString(String... args) {
        StringBuilder sb = new StringBuilder();
        sb.append("*").append(args.length).append("\r\n");
        for (String arg : args) {
            sb.append("$").append(arg.length()).append("\r\n");
            sb.append(arg).append("\r\n");
        }
        return sb.toString();
    }

    @Test
    public void initFile2() {
        Long startTime = System.currentTimeMillis();
        String file = "d:\\d.txt";
        BufferedWriter w = null;
        StringBuilder sb = new StringBuilder();
        try {
            w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
            for(int i=100000000 ;i < 100100000;i++){
                if (i / 30000 == 0) {
                    w.flush();
                }
                sb.setLength(0);
                sb.append(this.getString("set", "u" + i, "name" + i));
                w.append(sb.toString());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                w.flush();
                w.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        long endTime = System.currentTimeMillis();
        System.out.println("耗时: "+(endTime - startTime)/1000+" s。");
    }


    public static void main(String[] args) throws IOException {
        
    }
}
