package com.example.myapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public static final String URL_GET = "http://apis.juhe.cn/mobile/get?phone=13812345678&key=daf8fa858c330b22e342c882bcbac622";
    public static final String URL_POST = "http://apis.juhe.cn/mobile/get ";
    public static final String URL_IMG = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";
    public static final String URL_IMG2 = "http://img2.3lian.com/2014/f7/5/d/22.jpg";
    public static final String URL_IMG3 = "https://img.alicdn.com/tps/TB1eREfLVXXXXaHXFXXXXXXXXXX-480-260.png";
    @Bind(R.id.get)
    Button get;
    @Bind(R.id.post)
    Button post;
    @Bind(R.id.textsuccess)
    TextView textsuccess;
    @Bind(R.id.textfail)
    TextView textfail;
    @Bind(R.id.imageloader)
    Button imageloader;
    @Bind(R.id.imageview)
    ImageView imageview;
    @Bind(R.id.NetworkImageView)
    com.android.volley.toolbox.NetworkImageView NetworkImageView;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        requestQueue = Volley.newRequestQueue(this);
    }

    @OnClick({R.id.get, R.id.post, R.id.imageloader})
    public void onClick(View view) {
        switch (view.getId()) {
            // TODO: 2016/9/27 get请求,别忘记了设置tag
            case R.id.get:
                StringRequest stringRequest_get = new StringRequest(URL_GET, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textsuccess.setText("GET" + response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textfail.setText(error.toString());
                    }
                });
                stringRequest_get.setTag("MainActivity");
                requestQueue.add(stringRequest_get);
                break;
            // TODO: 2016/9/27 post请求,注意集合进行添加参数,并且注意URL和get请求的不同之处就是将组拼放在body中!
            case R.id.post:
                StringRequest stringRequest_post = new StringRequest(Request.Method.POST, URL_POST, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textsuccess.setText("POST" + response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textfail.setText(error.toString());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("phone", "13812345678");
                        hashMap.put("key", "daf8fa858c330b22e342c882bcbac622");
                        return hashMap;
                    }
                };
                stringRequest_post.setTag("MainActivity");
                requestQueue.add(stringRequest_post);
                break;
            // TODO: 2016/9/27 可以参见redbaby进行知悉详细参数,总共有三种方法
            case R.id.imageloader:
                /*ImageRequest imageRequest = new ImageRequest(URL_IMG, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageview.setImageBitmap(response);
                    }
                }, 0, 0, null, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        imageview.setImageResource(R.mipmap.ic_launcher);
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                imageRequest.setTag("MainActivity");
                requestQueue.add(imageRequest);
                break;*/
                ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
                    @Override
                    public Bitmap getBitmap(String url) {
                        return null;
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {

                    }
                });
                /*imageLoader.get(URL_IMG2, imageLoader.getImageListener(imageview, R.mipmap.ic_launcher,
                        R.mipmap.ic_launcher));*/
                NetworkImageView.setImageUrl(URL_IMG2, imageLoader);
        }
    }
}
