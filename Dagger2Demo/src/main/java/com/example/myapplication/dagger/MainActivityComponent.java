package com.example.myapplication.dagger;

import com.example.myapplication.MainActivity;

import dagger.Component;

/**
 * Created by Lenovo on 2016/9/26.
 */
@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
