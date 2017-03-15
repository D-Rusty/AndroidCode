//package com.project.onepice.travel.data.source.local;
//
//import android.support.test.InstrumentationRegistry;
//import android.support.test.runner.AndroidJUnit4;
//
//import com.project.onepice.travel.data.source.local.cityInfo.CityInfoLocalDataSource;
//import com.project.onepice.travel.data.source.local.scenic.ScenicLocalDataSource;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
///**
// * 作者: 方天文
// * 日期: 2017/3/7:下午3:31
// * 概况:
// */
//
////@RunWith(AndroidJUnit4.class)
//public class LocalDataSourceTest {
//
//    private LocalDataSource localDataSource=null;
//
//    @Before
//    public void setUp() throws Exception {
//        localDataSource = new LocalDataRepository(
//                new CityInfoLocalDataSource(InstrumentationRegistry.getContext()),
//                new ScenicLocalDataSource(InstrumentationRegistry.getContext()));
//    }
//
//
//    @Test
//    public void initLocalSqlite() throws Exception {
//        Assert.assertTrue(localDataSource.initLocalSqlite());
//    }
//
//    @Test
//    public void queryCityCode() throws Exception {
//        localDataSource.queryCityCode("");
//    }
//
//    @Test
//    public void queryScenic() throws Exception {
//        localDataSource.queryScenic("",0);
//    }
//
//    @Test
//    public void queryAllProvice() throws Exception {
//         localDataSource.queryAllProvice();
//    }
//
//    @After
//    public void tearDown() throws Exception {
//         localDataSource=null;
//    }
//
//}