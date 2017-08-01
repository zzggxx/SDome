package com.example.lenovo.osdemo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 需求1:
 * 现有一个数组 String[] arr ={"afdsa", "bfdsa", "cfda"},
 * 把其中以字母"a"开头的字符串找出来并加上"from Alpha",最后打印出新的字符串的长度
 * <p/>
 * 需求2:(数据变化的操作)
 * 由指定的一个 drawable 文件 id 取得图片，并显示在 ImageView 中，并在出现异常的时候打印 Toast 报错
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.imageview)
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        String[] arr = {"afdsa", "bfdsa", "cfda"};
//        Method_01(arr);
        Method_02delete(arr);
//        Method_02(arr);

        final int ic_launcher = R.mipmap.ic_launcher;
//        Mthod_03(ic_launcher);

        Course yuwen = new Course("语文", 1);
        Course shuxue = new Course("数学", 2);
        Course yingyu = new Course("英文", 3);
        Course lishi = new Course("历史", 4);
        Course zhengzhi = new Course("政治", 5);
        Course xila = new Course("希腊语", 6);

        ArrayList<Course> course1 = new ArrayList<>();
        course1.add(yuwen);
        course1.add(shuxue);
        course1.add(yingyu);
        course1.add(lishi);
        course1.add(zhengzhi);
        course1.add(xila);
        Student zhangsan = new Student("zhangsan", course1);



//        Mthod_04(zhangsan);
        Method_05(zhangsan);

    }

    // TODO: 2016/8/30 很重要的一对多的案例,注意体会,牛逼之处就是在与数据的传递中
    private void Method_05(Student zhangsan) {
        Observable
                .just(zhangsan)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getCourses());
                    }
                })
                .subscribe(new Action1<Course>() {
                    @Override
                    public void call(Course course) {
                        System.out.println(course.getName());
                    }
                });
    }

    private void Mthod_04(Student zhangsan) {
        ArrayList<Course> courses = zhangsan.getCourses();
        for (Course course : courses) {
            System.out.println(course.getName());
        }
    }

    private void Mthod_03(int ic_launcher) {
        // TODO: 2016/8/30 将不同的工作放到不同的线程中进行执行,主要就是io,数据库,文件,网络交互等
        // TODO: 2016/8/30 数据转换中一对一就是map执行,而一对多就是flagmap 
        Observable
                .just(ic_launcher)              //可以有很多的参数.
                .subscribeOn(Schedulers.io())
                .map(new Func1<Integer, Drawable>() {
                    @Override
                    public Drawable call(Integer integer) {
                        return getResources().getDrawable(integer);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Drawable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "图片错误", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        imageview.setImageDrawable(drawable);
                    }
                });
    }

    private void Method_02(String[] arr) {
        // TODO: 2016/8/30  简写方式,推荐.就和链式编程一样,能不能继续就得看返回值.+合并之后称之为Lambda编程
        Observable
                .from(arr)
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return s.startsWith("a");
                    }
                })
                .subscribe(new Action1<String>() {      // 只需要做一件事所以直接new Action1即可.
                    @Override
                    public void call(String s) {
                        s += "from Alpha";
                        System.out.println(s.length());
                    }
                });
    }

    private void Method_02delete(String[] arr) {
        Observable<String> observable = Observable.from(arr); //数组中的每一个对象添加到的下边的String中,返回值Boolean
        Observable<String> observableResult = observable.filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                return s.startsWith("a");
            }
        });
        Observer<String> observer = new Observer<String>() {    //三种实现的方法
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                s += "from Alpha";
                System.out.println(s.length());
            }
        };
        observableResult.subscribe(observer);   //被观察者联系观察者
    }

    /**
     * 原始的方法
     *
     * @param arr
     */
    private void Method_01(String[] arr) {
        for (String string : arr) {
            if (string.startsWith("a")) {
                string += "from Alpha";
                System.out.println(string.length());
            }
        }
    }
}
