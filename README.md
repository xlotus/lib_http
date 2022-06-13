# lib_http
Android上基于Retrofit, okhttp二次封装的http通讯库

1. 缓存retrofit实例，避免每次请求都创建retrofit
2. 利用注解在接口类中定义多环境baseUrl
3. 统一解析封装，将结果封装成ResponseWrapper统一回调

### 开始使用
在 project 的 build.gradle 文件中找到 allprojects{} 代码块添加以下代码：

allprojects {

      repositories {
      
           google()
      
           jcenter()
      
           maven { url 'https://jitpack.io'}  //增加 jitPack Maven 仓库
      }

}

在 app 的 build.gradle 文件中找到 dependencies{} 代码块，并在其中加入以下语句：

dependencies {

      implementation "com.github.xlotus:lib_http:1.0.4"

}
