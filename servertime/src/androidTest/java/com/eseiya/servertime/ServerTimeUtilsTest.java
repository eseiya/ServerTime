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


import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * ServerTimeUtils测试.
 * @author AndyZheng
 * @since 2019/10/19
 */
@RunWith(AndroidJUnit4.class)
public class ServerTimeUtilsTest {
    private static final long INIT_SERVER_TIME = 1567785600 * 1000L;
    private static final long SLEEP_TIME = 10 * 1000L;

    @Test
    public void getServerTime() {
        ServerTimeUtils.setServerTime(INIT_SERVER_TIME);
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long diff = Math.abs(ServerTimeUtils.getServerTime() - (INIT_SERVER_TIME + SLEEP_TIME));
        Assert.assertTrue(diff < 100);//误差小于100ms
    }
}