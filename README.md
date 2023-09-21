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

