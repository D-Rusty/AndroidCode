package com.project.onepice.travel.HomePage;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiSelector;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * 作者: 方天文
 * 日期: 2017/3/7:下午3:42
 * 概况:
 */

@RunWith(AndroidJUnit4.class)
public class HomePageActivityTest {

    private UiDevice uiDevice;

    /**
     * @return
     * @throws
     * @params:
     * @date: 2017/3/7 下午3:44 <br>
     * @Description 测试APP
     */
    @Before
    public void setUp() throws Exception {
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void homePageTest() throws Exception {
        uiDevice.pressHome();
        uiDevice.findObject(new UiSelector().description("Apps")).click();
        uiDevice.findObject(new UiSelector().description("看看")).click();
    }
}