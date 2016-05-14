package com.example.azurezoomi.testproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";


    @OnClick(R.id.button)
    public void btnClick(View v) {
        WebService webService = RestApiBuilder.buildRetrofitService();

        Observable<Blog> blogObservable = webService.findFeeds("1.0", "Official Google Blogs");

        blogObservable.filter(new Func1<Blog, Boolean>() {
            @Override
            public Boolean call(Blog blog) {
                //filter to check if respose is proper or not
                return blog.getResponseStatus() == 200;
            }
        }).map(new Func1<Blog, ArrayList<Entry>>() {
            @Override
            public ArrayList<Entry> call(Blog blog) {
                return blog.getResponseData().getEntries();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<Entry>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError " + e.toString());
                    }

                    @Override
                    public void onNext(ArrayList<Entry> entries) {

                    }

                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


    }
}
