package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final String URL_IMG2 = "http://img2.3lian.com/2014/f7/5/d/22.jpg";

    @Bind(R.id.tv_image)
    ImageView tvImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //第一步:初始化
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);
        //第二步:设置参数
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .displayer(new CircleBitmapDisplayer())
                .build();
        //第三步:设置图片
        ImageLoader.getInstance().displayImage(URL_IMG2, tvImage,options);
    }
}
