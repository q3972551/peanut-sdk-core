# peanut-sdk-core
核心工具类
一些工具类 
coder 里面是netty的decoder或者coder 类，用于处理粘包解包的一些东西
common是一个公共的类，里面是一个公共业务调用
db 是数据相关的
http 是httpclient的工具类
redis 是jedis的工具类，已经解决了回收资源等，
sms 是短信验证的，给予阿里云
tools 是一些基础的工具

以上基本都是用静态方法直接调用即可，但是会启动一个配置文件 config.json。例如 redis配置需要密码和地址还有端口，需要改config.json里的配置
