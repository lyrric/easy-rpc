# easy-rpc
用netty建立的easy-rpc

配置文件
#rpc服务端口
rpc.server.port=200
#是否检验token
rpc.server.login=true
#token
rpc.server.token=123456
#service实现类路径
rpc.server.service.package=com.demo.rpc.simple.server.service

启动方式
public static void main(String[] args) {
   ServerApplication.run(args);
}
