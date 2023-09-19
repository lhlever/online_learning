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
