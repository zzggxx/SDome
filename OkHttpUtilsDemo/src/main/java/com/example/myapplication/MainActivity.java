package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn_get)
    Button btnGet;
    @Bind(R.id.btn_post)
    Button btnPost;
    @Bind(R.id.tv_show)
    TextView tvShow;
    public static final String URL_GET = "http://apis.juhe.cn/mobile/get?phone=13812345678&key=daf8fa858c330b22e342c882bcbac622";
    public static final String URL_POST = "http://apis.juhe.cn/mobile/get ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_get, R.id.btn_post})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                OkHttpUtils
                        .get()
                        .url(URL_GET)
                        .build()
                        .execute(new StringCallback() {     //回调类型看你是什么了,这里是/String而已
                            @Override
                            public void onError(Call call, Exception e) {
                                tvShow.setText(e.toString());
                            }

                            @Override
                            public void onResponse(String response) {
                                tvShow.setText(response);
                            }
                        });
                break;
            case R.id.btn_post:
                OkHttpUtils
                        .post()
                        .addParams("phone", "13812345678")
                        .addParams("key", "daf8fa858c330b22e342c882bcbac622")
                        .url(URL_POST)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e) {
                                tvShow.setText(e.toString());
                            }

                            @Override
                            public void onResponse(String response) {
                                tvShow.setText(response);
                            }
                        });
                break;
        }
    }
}
