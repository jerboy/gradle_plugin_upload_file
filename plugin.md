# gradle 插件开发

## 插件开发环境
```
apply plugin: 'maven'

uploadArchives {
    repositories {
        mavenDeployer {
            // 上传到本地指定的maven仓库
            repository(url: 'file:.lib/')
            File file = file("local.properties")
            
            // 上传到远程仓库
            java.util.Properties properties = new Properties();
            properties.load(new FileInputStream(file))
//            repository(url: "TODO地址") {
//                authentication(userName:"TODO username" )
//                        , password: "TODO password")
//            }
            pom.version = "1.0"
            pom.artifactId = "##"
            pom.groupId = "####"
        }
    }
}


```

**上传插件到本地 `./gradlew uploadArchives`**


## 插件使用环境开发

```
  repositories {
        mavenCentral()
        // 指定本地仓库
        maven {url uri('../Upload_Apk_Plugin/.lib')}
    }
    
    dependencies {
        classpath 'groupId:artifictId:version'
    }
```
