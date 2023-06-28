# 声明

这是本人用于学习springcloud alibaba以及其相关组件的库。

# Commit 0.1

创建一个Springcloud alibaba项目并引入nacos

# Commit 0.2(current)

基于nacos的服务的远程调用与负载均衡

# Commit 0.3

nacos集群部署（3台或以上）: 

确保startup.sh中，mode="cluster"

修改nacos/conf/application.properties中的server.port

```properties
# 第一台
server.port=8847
# 第二台
server.port=8848
# 第三台
server.port=8849
```

修改nacos/conf/application.properties中的db.url块，配置数据源

```properties
# spring.sql.init.platform=mysql

### Count of DB:
# db.num=1

### Connect URL of DB:
# db.url.0=jdbc:mysql://127.0.0.1:3306/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
# db.user.0=nacos
# db.password.0=nacos
```

修改nacos/conf/cluster.conf中的 ip:port

```properties
192.168.16.101:8847
192.168.16.102:8848
192.168.16.103:8849
```

（可选）修改startup.sh中的jvm内存大小参数

使用nacos/conf/mysql-schema.sql创建数据库，nacos用这个数据库进行服务维护

在nginx中，使用stream模块开启负载均衡

```nginx
upstream nacos_cluster	{
	192.168.16.101:8847
	192.168.16.102:8848
	192.168.16.103:8849
}
server {
    listen          8850;
    server_name     localhost;
    location /nacos/{
        proxy_pass http://nacoscluster/nacos/;
    }
}
```

