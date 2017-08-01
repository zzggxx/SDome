package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    public static final int FAILURE = 1;
    private static final int SUCCESS = 2;
    @Bind(R.id.btn_get)
    Button btnGet;
    @Bind(R.id.btn_post)
    Button btnPost;
    @Bind(R.id.tv_show)
    TextView tvShow;
    public static final String URL_GET = "http://apis.juhe.cn/mobile/get?phone=13812345678&key=daf8fa858c330b22e342c882bcbac622";
    public static final String URL_POST = "http://apis.juhe.cn/mobile/get ";
    private OkHttpClient okHttpClient;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    tvShow.setText((String) msg.obj);
                    break;
                case 2:
                    tvShow.setText((String) msg.obj);
                    break;
                default:

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // TODO: 2016/9/8 官方推荐只是使用一个OkHttpClient的实例,所以以后不管是多少个请求都应该只是一个实例
        okHttpClient = new OkHttpClient();
    }

    @OnClick({R.id.btn_get, R.id.btn_post})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                // TODO: 2016/8/31 请求的方式,地址,和build.get请求方式是可以不写请求的方式的,因为是默认的.
                Request request = new Request.Builder()
                        .get()
                        .url(URL_GET)
                        .build();
                // TODO: 2016/8/31 应该使用的是异步的网络请求
//                okHttpClient.newCall(request).execute();
                okHttpClient.newCall(request).enqueue(new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {
                        Message msg = Message.obtain();
                        msg.what = FAILURE;
                        msg.obj = e.toString();
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        // TODO: 2016/8/31 获取到返回文件的主体,并可以将其转为String,byte[],Stream等等
                        ResponseBody body = response.body();
                        String s = body.string();   // TODO: 2016/8/31 注意这里并不是toString.
//                        byte[] bytes = body.bytes();
//                        InputStream inputStream = body.byteStream();
                        Message msg = Message.obtain();
                        msg.what = SUCCESS;
                        msg.obj = s;
                        handler.sendMessage(msg);
                    }
                });
                break;
            case R.id.btn_post:
                // TODO: 2016/8/31 post就是将get的参数独立的添加进来
                RequestBody post_body = new FormBody.Builder()
                        .add("phone", "13812345678")
                        .add("key", "daf8fa858c330b22e342c882bcbac622")
                        .build();
                Request post_request = new Request.Builder()
                        .post(post_body)
                        .url(URL_POST)
                        .build();
                okHttpClient.newCall(post_request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Message msg = Message.obtain();
                        msg.what = FAILURE;
                        msg.obj = e.toString();
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        ResponseBody body = response.body();
                        String s = body.string();
                        Message msg = Message.obtain();
                        msg.what = SUCCESS;
                        msg.obj = s;
                        handler.sendMessage(msg);
                    }
                });
                break;
            case R.id.btn_up:
                
                break;
        }
    }
}
