# online_learning
在线教育系统

## 注意事项&心得体会
### 1.定义实体类时间属性Data和LocalDateTime的问题-------------20230918
    如果不处理，响应给前端的是2020-04-14T19:58:55.764
    所以需要对java转json关于时间类型的进行序列化改写
    @Bean
    public LocalDateTimeSerializer localDateTimeSerializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    @Bean
    public LocalDateTimeDeserializer localDateTimeDeserializer() {
        return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            builder.serializerByType(LocalDateTime.class, localDateTimeSerializer());
            builder.deserializerByType(LocalDateTime.class, localDateTimeDeserializer());
        };
    }
    以上必须保证是LocalDateTime类型才会生效，Data不生效。

### 2.前后端联调时，出现前端（F12）可以接收到响应的数据，但是页面不显示/空白页-------------20230918
    考虑响应的数据格式与前端页面不一致，如json的key不一致，后端响应的是item,而前端页面要的是items


### 3.使用BeanUtils.copyProperties(A,B)复制对象属性时-------------20230919
    是把A的属性值赋值给B的对应的属性值，其中要求属性名一致且属性类型一致，缺一不可

### 4.mybatis自增主键问题   @TableId(type = IdType.AUTO)-----------20230921
    在添加记录功能中，一开始既没有在数据库设置主键策略，也没有在数据实体中使用Mybatis-plus的生成策略，
    所以默认的生成id的策略是雪花算法，这是一个分布式id，id值特别大。首先我的尝试是，在数据库中修改策略
    为自增，但是id值还是在很大的数上自增。然后把数据库中的所有记录删除了，再重新创建了一个id=1的记录，
    然后再添加记录，自动生成的id依然是很大的数，还是无法摆脱大整数的困扰。
    
    解决办法：
    步骤一：备份数据
    在进行修改操作之前，一定要备份我们的数据。如果修改出现了问题，我们可以从备份中恢复所有数据。
    
    步骤二：删除原有的自增id约束
    在修改自增id之前，我们需要先删除原有的自增id约束，否则我们无法修改数据库中的自增id。
    
    步骤三：修改自增id
    现在可以修改自增id了。我们可以使用alter table命令来修改自增id。
    
    步骤四：重新建立自增id约束
    最后一步，我们需要重新建立自增id约束。这可以保证我们在插入新的数据时，数据库可以自动生成正确的自增id。
    
    总之，在修改mysql的自增id时，一定要备份我们的数据，然后按照上述步骤进行操作。除非有必要，否则不要修改自增id

### 5.docker上安装nacos，为其配置宿主机上的mysql,流程注意事项-----------20230921
    ①宿主机的mysql开启允许远程访问连接，否则只允许本地连接
    ②宿主机防火墙允许外部访问3306端口
    ③为nacos配置本地的挂载目录，目的为了修改配置文件application.properties中数据库的连接
    ④application.properties配置文件中的url，其中ip地址不能写localhost或127.0.0.1，因为
      这指的是访问docker上的地址，所以需要使用ip地址192.168.xx.xx或者host.docker.internal，
      这指的是宿主机的地址。

### 6.spring-cloud微服务注册到docker上的nacos------------2023年9月30日
    ①注意单例模式的设置
    docker run --name nacos2.x -d -p 8848:8848 -p 9848:9848 -p 9849:9849 --privileged=true --restart=always -e MODE=standalone -e PREFER_HOST_MODE=hostname -v D:\development_tools\NACOS\init.d\application.properties:/home/nacos/conf/application.properties nacos/nacos-server
    ②注意设置nacos的ip地址
    在application.properties中设置nacos.inetutils.ip-address=192.168.144.8

### 7.配置文件application.yml和bootstrap.yml区别----------------2023年10月1日
    bootstrap类型文件是在多服务项目时，引入了springcloud相关配置才会生效，springboot并不会自动扫描bootstrap文件，只会扫描application文件，所以两者的比较只有在spingcloud多服务项目中才有意义。
    Spring Cloud 构建于 Spring Boot 之上，在 Spring Boot 中有两种上下文，一种是 bootstrap, 另外一种是 application, bootstrap 是应用程序的父上下文，也就是说 bootstrap 加载优先于 applicaton。
    bootstrap 主要用于从额外的资源来加载配置信息，还可以在本地外部配置文件中解密属性。这两个上下文共用一个环境，它是任何Spring应用程序的外部属性的来源。bootstrap 里面的属性会优先加载，它们默认也不能被本地相同配置覆盖。
    boostrap 由父 ApplicationContext 加载，比 applicaton 优先加载
    boostrap 里面的属性不能被覆盖
    在使用nacos作为配置中心时，本地配置文件应该是bootstrap.yml，如果是application.yml则会报错。nacos配置一定要写在bootstrap.yml中，如果你写在application.yml中将是无法生效读取的。
### 8.想要终止idea后台的下载任务，点击取消没有反应---------------2023年10月1日
    解决办法：断网
### 9.gateway和各个微服务要在一个group分组下，否则路由不能转发--------2023年10月1日
    Group间服务仍是隔离的，即服务注册到不同的分组时，无法使用OpenFeign指定服务名负载调用
