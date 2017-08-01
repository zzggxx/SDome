package com.u9time.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private static final String mUrlGril = "http://p0.so.qhmsg.com/t01ecc2792af7e73d2f.jpg";
    private static final String mUrlCar = "http://p3.so.qhmsg.com/t01984f03f65d502053.jpg";
    private ImageView mIvShow;
    private Button mBtn;
    private ImageView mIvShow2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIvShow = (ImageView) findViewById(R.id.iv_show);
        mIvShow2 = (ImageView) findViewById(R.id.iv_show2);
        mBtn = (Button) findViewById(R.id.btn);

        Picasso.with(this)
                .load(mUrlGril)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
//                .resize(50, 50)
                .into(mIvShow, new Callback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(MainActivity.this, "加载成功了", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(MainActivity.this, "加载失败了", Toast.LENGTH_SHORT).show();
                    }
                });
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetWorkStateUtils.isNetworkAvailable(MainActivity.this) == 1) {
                    Picasso.with(MainActivity.this)
                            .load(mUrlCar)
                           // .placeholder(R.mipmap.ic_launcher)
//                        .error(R.mipmap.ic_launcher)
                            .into(mIvShow2);
                }else{
                    Picasso.with(MainActivity.this)
                            .load(mUrlCar)
                            .networkPolicy(NetworkPolicy.OFFLINE)
                            .placeholder(R.mipmap.ic_launcher)
//                        .error(R.mipmap.ic_launcher)
                            .into(mIvShow2);
                }
            }
        });
    }
}
