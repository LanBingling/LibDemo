package com.lanshuai.libdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        testRxMap();
//        testJustWithFuncParam();
//        testPublishSubject();
//        testRxRange();
//        testRxTimer();
        testRxFilter();
    }

    private void testRxMap() {
        Disposable disposable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "string = " +integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("wahaha", "accept: " + s);
            }
        });
    }

    private String hello() {
        return "hello";
    }

    private void testJustWithFuncParam() {
        Disposable disposable = Observable.just(hello()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("wahaha", "accept: " + s);
            }
        });
    }

    private void testPublishSubject() {
        PublishSubject<String> publishSubject = PublishSubject.create();
        publishSubject.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("wahaha", "onSubscribe: ");
            }

            @Override
            public void onNext(String s) {
                Log.d("wahaha", "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("wahaha", "onError: ");
            }

            @Override
            public void onComplete() {
                Log.d("wahaha", "onComplete: ");
            }
        });
        publishSubject.onNext("hello");
    }

    private void testRxRange() {
        Disposable disposable = Observable.range(10, 5).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d("wahaha", "accept: " + integer);
            }
        });
    }

    private void testRxTimer() {
        Disposable disposable = Observable.timer(2, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.d("wahaha", "accept: " + aLong);
            }
        });
    }

    private void testRxFilter() {
        List<Integer> items = new ArrayList<>(4);
        items.add(1);
        items.add(10);
        items.add(100);
        items.add(1000);
        Disposable disposable = Observable.fromIterable(items).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer > 10;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d("wahaha", "accept: " + integer);
            }
        });
    }
}
