package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.MyInterfface.LocationAPI;
import com.example.myapplication.R;
import com.example.myapplication.bean.LocationModel;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL = "http://apis.juhe.cn";
//    http://apis.juhe.cn/mobile/get?phone=13992558334&key=daf8fa858c330b22e342c882bcbac622

    @Bind(R.id.btn_get)
    Button btnGet;
    @Bind(R.id.tv_show)
    TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_get)
    public void onClick() {
        // TODO: 2016/8/31 总共分为三步骤
        //第一步:构建Rotrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //使用的的是Gson,所以应该是Gson的工厂创建
                .build();
        //第二步:构建接口实例
        LocationAPI locationAPI = retrofit.create(LocationAPI.class);
        Call<LocationModel> call = locationAPI.getLocation("13992558334", "daf8fa858c330b22e342c882bcbac622");
        // TODO: 2016/8/31 都是异步的,OkHttp也是
        //第三步:执行异步的网络请求,返回是在主线程
        call.enqueue(new Callback<LocationModel>() {
            @Override
            public void onResponse(Call<LocationModel> call, Response<LocationModel> response) {
                LocationModel body = response.body();
                tvShow.setText(body.getResult().getCity());
            }

            @Override
            public void onFailure(Call<LocationModel> call, Throwable t) {
                tvShow.setText(t.toString());
                System.out.println();
            }
        });


    }
}
