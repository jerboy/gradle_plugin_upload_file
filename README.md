# 上传APK 插件

## 配置

+ 下载 uploadapkplugin 到本地项目根目录下
+ buildscript 加入这两行
```
buildscript {
    
    dependencies {
       
        classpath 'com.squareup.retrofit2:retrofit:2.1.0'
        classpath files('uploadapkplugin.jar')

    }
}
```

+ build.gradle 配置加上
apply plugin: 'uploadapk'

```
upload {
    _token ''  //todo  网页 token
    _url ''   // todo  网页地址 类似 "http://team.shafa.com/versions/1890/create/channel"
    _cookie ''  //todo 网页 cookie
    _enable true
}

```

