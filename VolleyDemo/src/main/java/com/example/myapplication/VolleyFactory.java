package com.example.myapplication;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Lenovo on 2016/9/27.
 */
public class VolleyFactory {
    private Context context;
    private RequestQueue requestQueue;
    private static VolleyFactory volleyFactory;
    private final ImageLoader imageLoader;

    // TODO: 2016/9/27 可以根据需要进行添加
    private VolleyFactory(Context context) {
        this.context = context;
        this.requestQueue = getRequestQueue();
        imageLoader = new ImageLoader(requestQueue, new MImageCache());
    }

    // TODO: 2016/9/27 创建类实例
    public static synchronized VolleyFactory getInstance(Context context) {
        if (volleyFactory == null) {
            VolleyFactory volleyFactory = new VolleyFactory(context);
        }
        return volleyFactory;
    }

    // TODO: 2016/9/27 获取缓存队列,只有一个
    private synchronized RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            RequestQueue requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

    // TODO: 2016/9/27 获取ImageLoader
    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    // TODO: 2016/9/27 添加请求
    public <T> void addRequest(Request<T> request) {
        getRequestQueue().add(request);
    }

    // TODO: 2016/9/27 取消请求
    public void callRequest(String tag) {
        getRequestQueue().cancelAll(tag);
    }

    // TODO: 2016/9/27 清除某一个请求的缓存
    public void removeCache(String key) {
        getRequestQueue().getCache().remove(key);
    }

    // TODO: 2016/9/27 清除全部的缓存
    public void removeAllCache() {
        getRequestQueue().getCache().clear();
    }
}
