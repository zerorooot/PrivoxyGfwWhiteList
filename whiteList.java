import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

/**
 * @Author: zerorooot
 * @Date: 2019/12/26 9:12
 */
public class whiteList {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("java whiteList.java <socks5_addr:socks5_port>");
            return;
        }
        String addr = args[0];
        String filePath = "/etc/privoxy/whitelist.action";
        String urlName = "https://raw.githubusercontent.com/felixonmars/dnsmasq-china-list/master/accelerated-domains.china.conf";
        String head = "{{alias}}\n" +
                "socks5 = +forward-override{forward-socks5 " + addr + " .}\n" +
                "direct = +forward-override{forward .}\n" +
                "\n" +
                "{socks5}\n" +
                "/\n" +
                "{direct}\n";

        URL url = new URL(urlName);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        httpsURLConnection.setRequestProperty("Cache-Control", "no-cache");
        httpsURLConnection.setRequestProperty("Charset", "UTF-8");
        httpsURLConnection.connect();
        InputStream inputStream = httpsURLConnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line;
        StringBuffer stringBuffer = new StringBuffer(head);
        while ((line = bufferedReader.readLine()) != null) {
            line = line.replace("server=/", ".").replace("/114.114.114.114", ".");
            stringBuffer.append(line).append("\n");
        }
        bufferedReader.close();
        httpsURLConnection.disconnect();

        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        bufferedWriter.write(stringBuffer.toString());
        bufferedWriter.close();
        System.out.println("ok~");
    }
}
