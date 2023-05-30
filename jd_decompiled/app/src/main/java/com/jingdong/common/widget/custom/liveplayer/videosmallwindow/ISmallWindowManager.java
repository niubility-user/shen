package com.jingdong.common.widget.custom.liveplayer.videosmallwindow;

import android.graphics.Point;
import android.view.View;
import com.jingdong.common.widget.custom.liveplayer.LiveUIConfigBean;

/* loaded from: classes12.dex */
public interface ISmallWindowManager {

    /* loaded from: classes12.dex */
    public interface IPlayerChange {
        void onDestory();

        void onMuteClick();

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

    void init(View view, boolean z, IPlayerChange iPlayerChange, ISizeChanger iSizeChanger, boolean z2, float f2, int i2, String str);

    void init(View view, boolean z, IPlayerChange iPlayerChange, ISizeChanger iSizeChanger, boolean z2, float f2, int i2, String str, LiveUIConfigBean liveUIConfigBean);

    void onResume();

    void onStop();

    void showBig();

    void showSmall();

    @Deprecated
    void showSmall(boolean z);

    void showSmall(boolean z, int i2);
}
