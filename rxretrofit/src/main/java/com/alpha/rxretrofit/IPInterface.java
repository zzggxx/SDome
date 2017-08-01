package com.alpha.rxretrofit;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Alpha on 2016/4/22.
 */
public interface IPInterface {
// http://ip.taobao.com/service/getIpInfo.php?ip=202.178.10.23

    @GET("/service/getIpInfo.php")
    public Observable<IPBean> getLocation(@Query("ip") String ip);

}
