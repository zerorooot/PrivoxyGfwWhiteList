# PrivoxyGfwWhiteList
用java写的privoxy gfw白名单模式

# 如何使用？

```
git clone https://github.com/zerorooot/PrivoxyGfwWhiteList.git

cd PrivoxyGfwWhiteList

sudo bash -c 'echo "actionsfile whitelist.action" >> /etc/privoxy/config'

sudo java whiteList.java 127.0.0.1:1080

```
