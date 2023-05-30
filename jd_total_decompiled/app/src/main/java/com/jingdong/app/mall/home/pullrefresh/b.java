package com.jingdong.app.mall.home.pullrefresh;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import com.jingdong.app.mall.home.widget.HomePullRefreshRecyclerView;

/* loaded from: classes4.dex */
public class b {

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(16)
    /* loaded from: classes4.dex */
    public static class a {
        public static void a(View view, Runnable runnable) {
            view.postOnAnimation(runnable);
        }
    }

    public static void a(View view, Runnable runnable) {
        if (view instanceof HomePullRefreshRecyclerView) {
            view.postDelayed(runnable, 7L);
        } else if (Build.VERSION.SDK_INT >= 16) {
            a.a(view, runnable);
        } else {
            view.postDelayed(runnable, 16L);
        }
    }
}
