package com.example.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final String URL_IMG2 = "http://img2.3lian.com/2014/f7/5/d/22.jpg";
    @Bind(R.id.sdv_image)
    SimpleDraweeView sdvImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 2016/8/31 必须要在setContentView之前进行初始化
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(URL_IMG2))
                .setProgressiveRenderingEnabled(true)
                .build();

        //转换地址进行显示
        sdvImage.setImageURI(Uri.parse(URL_IMG2));
    }
}
