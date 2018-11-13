package com.sxmh.wt.lotterysystem.base;

import com.sxmh.wt.lotterysystem.ApiService;
import com.sxmh.wt.lotterysystem.IView;
import com.sxmh.wt.lotterysystem.MyApplication;
import com.sxmh.wt.lotterysystem.R;

import java.lang.ref.WeakReference;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Wang Tao on 2018/4/10 0010.
 */

public abstract class BasePresenter {

    protected WeakReference<IView> iView;

    public BasePresenter(IView iView) {
        this.iView = new WeakReference<>(iView);
    }
}
