import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zero
 * @Date: 2019/12/26 9:12
 */
public class whiteList {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("sudo java whiteList.java <socks5_addr:socks5_port>\n" +
                    "example sudo java whiteList.java 127.0.0.1:1080");
            return;
        }

        String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        String addr = args[0];
        String filePath = "/etc/privoxy/whitelist.action";
        String urlName = "https://raw.githubusercontent.com/felixonmars/dnsmasq-china-list/master/accelerated-domains.china.conf";
        String head = new String(new FileInputStream(whiteList.class.getResource("template.txt").getFile()).readAllBytes());

        URL url = new URL(urlName);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        httpsURLConnection.setRequestProperty("Cache-Control", "no-cache");
        httpsURLConnection.setRequestProperty("Charset", "UTF-8");
        httpsURLConnection.setConnectTimeout(3000);

        //下载
        try {
            httpsURLConnection.connect();
        } catch (Exception e) {
            System.out.println("网络错误...( ＿ ＿)ノ｜\ngfw不应许你这么做\nm(=•ェ•=)m");
            return;
        }

        //读取
        InputStream inputStream = httpsURLConnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        //替换
        String line;
        StringBuilder stringBuffer = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            line = line.replace("server=/", ".").replace("/114.114.114.114", ".");
            stringBuffer.append(line).append("\n");
        }
        bufferedReader.close();
        httpsURLConnection.disconnect();
        
        
        //格式化
        head = head.replace("@time", time).replace("@addr", addr).replace("@whiteList", stringBuffer.toString());

        //写入
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(head);
            bufferedWriter.close();
            System.out.println("安装成功\nd=====(￣▽￣*)b");
        } catch (Exception e) {
            System.out.println("权限不够>﹏<\n试试 sudo java whiteList.java 127.0.0.1:1080");
            return;
        }

        //重启privoxy
        String restart = " systemctl restart privoxy.service ";
        try {
            Runtime.getRuntime().exec(restart).waitFor(2, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("重启privoxy失败，可能是权限不够或者没安装造成的\n(；′⌒`)");
        }


        System.out.println("重启privoxy成功\n(≧∇≦)ﾉ");
    }
}
