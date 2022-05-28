# lib_http
Android上基于Retrofit, okhttp二次封装的http通讯库

1. 缓存retrofit实例，避免每次请求都创建retrofit
2. 利用注解在接口类中定义多环境baseUrl
3. 统一解析封装，将结果封装成ResponseWrapper统一回调
