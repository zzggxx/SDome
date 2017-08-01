package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.myapplication.bean.User;
import com.example.myapplication.dagger.DaggerMainActivityComponent;
import com.example.myapplication.dagger.MainActivityComponent;
import com.example.myapplication.dagger.MainActivityModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivityComponent component = DaggerMainActivityComponent.builder().mainActivityModule(new MainActivityModule()).build();
        component.inject(this);

        Toast.makeText(this, user.getName() + user.getAge(), Toast.LENGTH_SHORT).show();

    }
}
