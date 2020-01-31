# PrivoxyGfwWhiteList
用java写的privoxy gfw白名单模式

# 如何使用？

```
git clone https://github.com/zerorooot/PrivoxyGfwWhiteList.git

cd PrivoxyGfwWhiteList

sudo bash -c 'echo "actionsfile whitelist.action" >> /etc/privoxy/config'

sudo java whiteList.java 127.0.0.1:1080

```

如果你还想在此基础上搞点其他花样的话，可以更改"template.txt"文件，按照自己的需要自行更改即可。
