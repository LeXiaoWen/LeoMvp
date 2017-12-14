# LeoMvp
# 简单使用
- Add it in your root build.gradle at the end of repositories:
 ```
 	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
			}
		}
 ```
 - Add the dependency
 
 ```
 dependencies {
	        compile 'com.github.LeXiaoWen:LeoMvp:v1.1.2'
	}
 ```

- Define versions in a single place (in project gradle)
 ```
    ext {
        // Sdk and tools
        minSdkVersion = 15
        targetSdkVersion = 25
        compileSdkVersion = 25
        buildToolsVersion = '25.0.3'

        // App dependencies
        supportLibraryVersion = '25.3.1'
        frescoVersion = "1.5.0"
        loggerVersion = '1.15'
        retrofitVersion = "2.2.0"
        okhttploggingVersion = "3.4.1"
        okhttpVersion = "3.4.1"
        gsonVersion = "2.7"
        multidexVersion = "1.0.1"
        eventbusVersion = "3.0.0"
        circleImageviewVersion = '2.1.0'
        rxPerssionsVersion = '0.9.4@aar'
        xrecyclerviewVersion = "1.3.2"
        picassoVersion = "2.3.2"
        nineoldVersion = "2.4.0"
        sliderVersion = "1.1.5@aar"
        rxjavaVersion = '2.0.1'
        rxandroidVersion = '2.0.1'
        daggerVersion  = "2.10-rc4"
        fragmentationVersion = "1.1.6"
        qmuiVersion = "1.0.4"
    }
 ```