package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.view.View;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.jingdong.common.UnLog;

@TargetApi(9)
/* loaded from: classes12.dex */
public final class OverscrollHelper {
    static final float DEFAULT_OVERSCROLL_SCALE = 1.0f;
    static final String LOG_TAG = "OverscrollHelper";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.handmark.pulltorefresh.library.OverscrollHelper$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation;

        static {
            int[] iArr = new int[PullToRefreshBase.Orientation.values().length];
            $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation = iArr;
            try {
                iArr[PullToRefreshBase.Orientation.HORIZONTAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[PullToRefreshBase.Orientation.VERTICAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isAndroidOverScrollEnabled(View view) {
        return view.getOverScrollMode() != 2;
    }

    public static void overScrollBy(PullToRefreshBase<?> pullToRefreshBase, int i2, int i3, int i4, int i5, boolean z) {
        overScrollBy(pullToRefreshBase, i2, i3, i4, i5, 0, z);
    }

    public static void overScrollBy(PullToRefreshBase<?> pullToRefreshBase, int i2, int i3, int i4, int i5, int i6, boolean z) {
        overScrollBy(pullToRefreshBase, i2, i3, i4, i5, i6, 0, 1.0f, z);
    }

    public static void overScrollBy(PullToRefreshBase<?> pullToRefreshBase, int i2, int i3, int i4, int i5, int i6, int i7, float f2, boolean z) {
        int scrollX;
        int i8;
        int i9;
        if (AnonymousClass1.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[pullToRefreshBase.getPullToRefreshScrollDirection().ordinal()] != 1) {
            scrollX = pullToRefreshBase.getScrollY();
            i8 = i4;
            i9 = i5;
        } else {
            scrollX = pullToRefreshBase.getScrollX();
            i8 = i2;
            i9 = i3;
        }
        if (!pullToRefreshBase.isPullToRefreshOverScrollEnabled() || pullToRefreshBase.isRefreshing()) {
            return;
        }
        PullToRefreshBase.Mode mode = pullToRefreshBase.getMode();
        if (mode.permitsPullToRefresh() && !z && i8 != 0) {
            int i10 = i8 + i9;
            if (UnLog.D) {
                UnLog.d(LOG_TAG, "OverScroll. DeltaX: " + i2 + ", ScrollX: " + i3 + ", DeltaY: " + i4 + ", ScrollY: " + i5 + ", NewY: " + i10 + ", ScrollRange: " + i6 + ", CurrentScroll: " + scrollX);
            }
            if (i10 < 0 - i7) {
                if (mode.showHeaderLoadingLayout()) {
                    if (scrollX == 0) {
                        pullToRefreshBase.setState(PullToRefreshBase.State.OVERSCROLLING, new boolean[0]);
                    }
                    pullToRefreshBase.setHeaderScroll((int) (f2 * (scrollX + i10)));
                }
            } else if (i10 > i6 + i7) {
                if (mode.showFooterLoadingLayout()) {
                    if (scrollX == 0) {
                        pullToRefreshBase.setState(PullToRefreshBase.State.OVERSCROLLING, new boolean[0]);
                    }
                    pullToRefreshBase.setHeaderScroll((int) (f2 * ((scrollX + i10) - i6)));
                }
            } else if (Math.abs(i10) <= i7 || Math.abs(i10 - i6) <= i7) {
                pullToRefreshBase.setState(PullToRefreshBase.State.RESET, new boolean[0]);
            }
        } else if (z && PullToRefreshBase.State.OVERSCROLLING == pullToRefreshBase.getState()) {
            pullToRefreshBase.setState(PullToRefreshBase.State.RESET, new boolean[0]);
        }
    }
}
