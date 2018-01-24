## LeoMVP使用指南
### 引入项目
- Add it in your root build.gradle at the end of repositories:
  ``` java
  allprojects {
      repositories {
          ...
      maven { 
        url 'https://jitpack.io' 
      }
        }
  }
  ```

- Add the dependency
  ``` java
  dependencies {
          compile "com.github.LeXiaoWen:LeoMvp:$rootProject.leoMvpVersion"
   }
  ```

- Define versions in a single place (in project gradle)
 ``` java
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
    leoMvpVersion = "v1.1.8"
}
 ```

### 初始化
- 新建Application继承于BaseApp，重写onCreat()方法。
- 在value文件夹styles中定义主题，继承BaseAppTheme，可配置沉浸式状态栏的颜色
 ```java
  <resources>
    <!-- Base application theme. -->
    <style name="AppTheme" parent="BaseAppTheme">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>

        <!-- 配置app自己的theme -->
        <item name="app_primary_color">@color/app_color_theme_2</item>
        <item name="app_content_bg_color">@color/qmui_config_color_white</item>
    </style>
</resources>
 ```
