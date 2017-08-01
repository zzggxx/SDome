package com.u9time.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2016/6/8.
 */
public class NetWorkStateUtils {

    /**
     * 判断当前是否有可用的网络以及网络类型 0：无网络 1：WIFI 2：2G/3G/4G
     *
     * @param context
     * @return
     */
    public static int isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivity.getActiveNetworkInfo();
        if (activeNetworkInfo==null||!activeNetworkInfo.isAvailable()) {
            return 0;
        } else {
            int networkType = activeNetworkInfo.getType();
            if(networkType == ConnectivityManager.TYPE_WIFI){
                return 1;
            }else if(networkType== ConnectivityManager.TYPE_MOBILE){
                return 2;
            }
        }
        return 0;
    }

}
