package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.button2)
    Button button2;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.button3)
    Button button3;
    @Bind(R.id.switch1)
    Switch switch1;
    @Bind(R.id.imageButton)
    ImageButton imageButton;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        for (int i = 0; i < 10; i++) {
            if (i == 8) {
                Log.i(TAG, "onCreate: " + "j=8");
            }
        }
    }

    @OnClick({R.id.button, R.id.button2, R.id.textView, R.id.button3, R.id.switch1, R.id.imageButton, R.id.imageView, R.id.progressBar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                break;
            case R.id.button2:
                break;
            case R.id.textView:
                break;
            case R.id.button3:
                break;
            case R.id.switch1:
                break;
            case R.id.imageButton:
                break;
            case R.id.imageView:
                break;
            case R.id.progressBar:
                break;
        }
    }
}
