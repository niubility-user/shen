package com.jingdong.common.widget.videosmallwindow;

import android.graphics.Point;
import android.view.View;

/* loaded from: classes12.dex */
public interface ISmallWindowManager {

    /* loaded from: classes12.dex */
    public interface IPlayerChange {
        void onDestory();

        void onPause();

        void onSmallClick();

        void showAtBig();

        void showAtSmall();
    }

    /* loaded from: classes12.dex */
    public interface ISizeChanger {
        Point getSize();
    }

    void close();

    void destory();

    void init(View view, boolean z, IPlayerChange iPlayerChange);

    void onResume();

    void onStop();

    void showBig();

    void showSmall();
}
