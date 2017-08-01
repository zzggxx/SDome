package com.example.myapplication.dagger;

import com.example.myapplication.bean.User;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lenovo on 2016/9/26.
 */
@Module
public class MainActivityModule {
    @Provides
    User providesUser() {
        return new User("周高雄", 27);
    }
}
