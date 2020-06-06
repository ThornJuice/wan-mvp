package com.hzy.wan.base;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<V extends IView> implements IPresenter, LifecycleObserver {
    protected final String TAG = this.getClass().getSimpleName();
    // protected CompositeDisposable mCompositeDisposable;
//    protected M mModel;
    protected V mView;

//    public BasePresenter(M model, V view) {
//        this.mModel = model;
//        this.mView = view;
//        init();
//    }
    public BasePresenter(V view) {
        this.mView = view;
        init();
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        Log.e(TAG, "onDestroy"+mView.toString());
    }

    @Override
    public void init() {
        if (mView != null && mView instanceof LifecycleOwner) {
            ((LifecycleOwner) mView).getLifecycle().addObserver(this);
//            if (mModel != null && mModel instanceof LifecycleObserver) {
//                ((LifecycleOwner) mView).getLifecycle().addObserver((LifecycleObserver) mModel);
//            }
        }
    }
}
