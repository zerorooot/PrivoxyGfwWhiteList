# PrivoxyGfwWhiteList
用java写的privoxy gfw白名单模式

# 如何使用？

```
git clone https://github.com/zerorooot/PrivoxyGfwWhiteList.git

cd PrivoxyGfwWhiteList

sudo java whiteList.java 127.0.0.1:1080
```

如果出现以下错误，意味着你需要fq下载

```
Exception in thread "main" java.net.ConnectException: 拒绝连接 (Connection refused)
        at java.base/java.net.PlainSocketImpl.socketConnect(Native Method)
        at java.base/java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:399)
        at java.base/java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:242)
        at java.base/java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:224)
        at java.base/java.net.SocksSocketImpl.connect(SocksSocketImpl.java:403)
        at java.base/java.net.Socket.connect(Socket.java:609)
        at java.base/sun.security.ssl.SSLSocketImpl.connect(SSLSocketImpl.java:285)
        at java.base/sun.security.ssl.BaseSSLSocketImpl.connect(BaseSSLSocketImpl.java:173)
        at java.base/sun.net.NetworkClient.doConnect(NetworkClient.java:182)
        at java.base/sun.net.www.http.HttpClient.openServer(HttpClient.java:474)
        at java.base/sun.net.www.http.HttpClient.openServer(HttpClient.java:569)
        at java.base/sun.net.www.protocol.https.HttpsClient.<init>(HttpsClient.java:265)
        at java.base/sun.net.www.protocol.https.HttpsClient.New(HttpsClient.java:372)
        at java.base/sun.net.www.protocol.https.AbstractDelegateHttpsURLConnection.getNewHttpClient(AbstractDelegateHttpsURLConnection.java:191)
        at java.base/sun.net.www.protocol.http.HttpURLConnection.plainConnect0(HttpURLConnection.java:1187)
        at java.base/sun.net.www.protocol.http.HttpURLConnection.plainConnect(HttpURLConnection.java:1081)
        at java.base/sun.net.www.protocol.https.AbstractDelegateHttpsURLConnection.connect(AbstractDelegateHttpsURLConnection.java:177)
        at java.base/sun.net.www.protocol.https.HttpsURLConnectionImpl.connect(HttpsURLConnectionImpl.java:168)
        at whiteList.main(whiteList.java:30)
```

