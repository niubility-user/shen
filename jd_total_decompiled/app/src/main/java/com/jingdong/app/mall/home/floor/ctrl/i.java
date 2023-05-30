package com.jingdong.app.mall.home.floor.ctrl;

import android.view.View;
import android.view.ViewGroup;
import com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter;
import com.jingdong.app.mall.home.floor.view.widget.LinerPagerCursor;

/* loaded from: classes4.dex */
public interface i {
    int a();

    void b(int i2, ViewGroup viewGroup, int i3);

    boolean c(int i2);

    View d();

    void e(int i2);

    void f(LinerPagerCursor linerPagerCursor, int i2, ICursorContentViewPresenter iCursorContentViewPresenter, int i3);

    void g(int i2);

    void h();

    void onPageScrolled(int i2, float f2, int i3);

    void onPageSelected(int i2);
}
