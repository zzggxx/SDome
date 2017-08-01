package com.example.myapplication.MyInterfface;

import com.example.myapplication.bean.LocationModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lenovo on 2016/8/31.
 */
public interface LocationAPI {

    //    http://apis.juhe.cn/mobile/get?phone=13992558334&key=daf8fa858c330b22e342c882bcbac622

    //制定的时候是从斜线开始的
    // TODO: 2016/8/31 URL可以拆分成baseUrl,类似API,参数.
    @GET("/mobile/get")
    // TODO: 2016/8/31 返回的数据模型就是javaBean,泛型的更改,后边的是参数,方法名随便改,查询类参数
    public Call<LocationModel> getLocation(@Query("phone") String num, @Query("key") String key);
}
