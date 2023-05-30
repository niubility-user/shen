package com.jingdong.app.mall.home.category.floor.base;

import android.view.View;
import java.util.List;

/* loaded from: classes.dex */
public interface b<M> {
    void c(M m2, com.jingdong.app.mall.home.category.adapter.a aVar, int i2, List<Object> list);

    void d(M m2, com.jingdong.app.mall.home.category.adapter.a aVar, int i2);

    View getContentView();

    void onViewRecycle();
}
