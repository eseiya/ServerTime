# ServerTime

Android上获取服务器时间的工具，只需要设置一次服务器时间，后续都能通过计算得到当前服务器时间。

##### 使用场景

很多情况都需要使用当前时间，但是设备时间是可以修改的，如果时间不准会造成很多问题，所以要使用服务器时间。

##### 方案介绍

根据之前设置的服务器时间，设置时的本地时间，和当前的本地时间，可以计算出当前的服务器时间，计算公式如下：

```
当前服务器时间 = 之前设置的服务器时间 + 中间时间差
```

```java
curServerTime = oldServerTime + elapsedTime;
curServerTime = oldServerTime + curLocalTime - oldLocalTime;
```

本地时间采用SystemClock.elapsedRealtime()`，而不是`System.currentTimeMillis()`，因为`elapsedRealtime`是从设备启动到当前的时间，不会因为系统时间修改而受影响。

##### 使用方法

获取服务器时间之前需要先设置一次服务器时间，首次获取服务器时间有下面几种方式：

1. 如果使用的Http协议通信，Http响应头信息里的Date字段就是服务器时间。
2. 增加和服务器通信的接口，让服务器返回时间。
3. 使用NTP的方式获取服务器时间。

设置服务器时间

```java
 ServerTimeUtils.setServerTime(INIT_SERVER_TIME);
```

使用服务器时间

```
ServerTimeUtils.getServerTime();
```

引入组件

```groovy
implementation "com.eseiya:servertime:1.0.0"
```

