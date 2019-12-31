import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zero
 * @Date: 2019/12/26 9:12
 */
public class whiteList {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("sudo java whiteList.java <socks5_addr:socks5_port>");
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
        httpsURLConnection.setConnectTimeout(2000);

        //下载
        try {
            httpsURLConnection.connect();
        } catch (Exception e) {
            System.out.println("网络错误\n该死的gfw...( ＿ ＿)ノ｜");
            return;
        }
        
        //读取
        InputStream inputStream = httpsURLConnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        
        //替换
        String line;
        StringBuffer stringBuffer = new StringBuffer(head);
        while ((line = bufferedReader.readLine()) != null) {
            line = line.replace("server=/", ".").replace("/114.114.114.114", ".");
            stringBuffer.append(line).append("\n");
        }
        bufferedReader.close();
        httpsURLConnection.disconnect();

        //写入
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        bufferedWriter.write(stringBuffer.toString());
        bufferedWriter.close();
        
        //重启privoxy
        String restart = " systemctl restart privoxy.service ";
        try {
            Runtime.getRuntime().exec(restart).waitFor(2, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("重启privoxy失败，可能是权限不够或者没安装造成的\n(；′⌒`)");
        }
        
        
        System.out.println("安装成功\nd=====(￣▽￣*)b");
    }
}
