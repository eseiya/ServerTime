/*
 * Copyright (C) 2019 AndyZheng.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.eseiya.servertime;

import android.os.SystemClock;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 获取服务器时间的工具,只需要设置一次服务器时间,后续都能通过计算得到当前服务器时间.<br>
 * 计算公式如下:<br>
 * 当前服务器时间 = 之前设置的服务器时间 + 中间时间差<br>
 * curServerTime = oldServerTime + elapsedTime;<br>
 * elapsedTime = curLocalTime - oldLocalTime;<br>
 * curServerTime = oldServerTime - oldLocalTime + curLocalTime;<br>
 * relativeServerTime = oldServerTime - oldLocalTime;<br>
 * curServerTime = relativeServerTime + curLocalTime;<br>
 *
 * @author AndyZheng
 * @since 2019/10/19
 */
public class ServerTimeUtils {

    private ServerTimeUtils() {
    }

    /**
     * 相对服务器时间.服务器时间减去当前时间.
     */
    private static AtomicLong relativeServerTime = new AtomicLong(System.currentTimeMillis() - getCurLocalTime());

    /**
     * 设置服务器时间,单位毫秒.
     */
    public static void setServerTime(long serverTime) {
        relativeServerTime.set(serverTime - getCurLocalTime());
    }

    /**
     * 获取服务器时间,单位毫秒.如果未设置过服务器时间则为本地时间.
     */
    public static long getServerTime() {
        return relativeServerTime.get() + getCurLocalTime();
    }

    /**
     * 返回服务器时间,单位秒.如果还未设置过服务器时间则为本地时间.
     */
    public static int getServerTimeInSecond() {
        return (int) (getServerTime() / 1000);
    }

    /**
     * 获取当前本地时间.采用不会跟随系统时间变化的elapsedRealtime.
     */
    private static long getCurLocalTime() {
        return SystemClock.elapsedRealtime();
    }
}