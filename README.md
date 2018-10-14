项目文档

user  服务提供者   该用户服务用于办绑定数据库

consumer  服务消费者     	使用RestTemplate调用用户微服务的API

eureka-server  服务注册中心      单节点		用于其它的微服务注册
eureka-servers 服务注册中心	   多节点
without-eureka 脱离注册中心使用robbon负载均衡
feign		   声明式Rest,并整合了Ribbon和Eureka，自定义feign
feign-build	   手动创建feign
dashboard	   可视话监控数据
turbine		   聚合监控数据
turbine-mq	   集成了消息收集中间件
zuul		   微服务网关
zull-upload	   微服务网关上传文件
sidecar		   微服务网关，集成非JVM微服务


java -jar      eureka.jar   --spring.profiles.active=peer2
java -jar      user.jar 	--server.port=8001

配置服务中心集群需要修改本地  /etc/hosts
127.0.0.1  peer1  peer2


Spring Boot Actuator 常用端点及描述
端点             描述																		Http方法	 	是否敏感
autoconfig		显示自动配置的信息															Get			是
beans			显示应用程序上下文所有的spring bean											Get			
configprops   	显示所有@ConfigurationProperties的配置属性列表								Get			是
dump			显示线程活动的快照															Get			是
env				显示应用的环境变量															Get			是
health			显示应用程序的健康指标，这些值由HealthIndicator的实现类提供。当应用开启安全
				保护时，对于未经用户认证的请求，只会显示简单的状态;如已认证,则会展开健康详情.		Get         否
info		  	显示应用的信息,可使用info.*属性自定义info端点公开的数据							Get			否		
mappings   		显示所有@RequestMapping的路径列表											Get       	是
metrices		显示应用的宽量标准信息						 								Get			是
shutdown	    关闭应用(默认情况下不启用，如需要启用，需设置end-points.shutdown.enabled=true)  Post     	是
trace			显示跟踪信息(默认情况下为最近100个Http请求)									Get			是

