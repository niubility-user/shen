package com.handmark.pulltorefresh.library;

import android.content.Context;
import androidx.annotation.NonNull;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.internal.BaseLoadingLayout;
import com.handmark.pulltorefresh.library.internal.FlipLoadingLayout;
import com.handmark.pulltorefresh.library.internal.JDLoadingLayout;
import com.handmark.pulltorefresh.library.internal.JDLoadingLayoutApi16;
import com.handmark.pulltorefresh.library.internal.RotateLoadingLayout;

/* loaded from: classes12.dex */
public class LoadingLayoutUtils {

    /* renamed from: com.handmark.pulltorefresh.library.LoadingLayoutUtils$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$AnimationStyle;

        static {
            int[] iArr = new int[PullToRefreshBase.AnimationStyle.values().length];
            $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$AnimationStyle = iArr;
            try {
                iArr[PullToRefreshBase.AnimationStyle.ROTATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$AnimationStyle[PullToRefreshBase.AnimationStyle.FLIP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$AnimationStyle[PullToRefreshBase.AnimationStyle.JINGDONG_FLIP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$AnimationStyle[PullToRefreshBase.AnimationStyle.JINGDONG.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static void changeLoadingLayout(PullToRefreshBase pullToRefreshBase, BaseLoadingLayout baseLoadingLayout, boolean z) {
        if (pullToRefreshBase == null || baseLoadingLayout == null) {
            return;
        }
        pullToRefreshBase.setState(PullToRefreshBase.State.RESET, new boolean[0]);
        baseLoadingLayout.setVisibility(4);
        if (z) {
            pullToRefreshBase.removeView(pullToRefreshBase.getHeaderLayout());
            pullToRefreshBase.setHeaderLayout(baseLoadingLayout);
        } else {
            pullToRefreshBase.removeView(pullToRefreshBase.getFooterLayout());
            pullToRefreshBase.setFooterLayout(baseLoadingLayout);
        }
        pullToRefreshBase.updateUIForMode();
    }

    public static BaseLoadingLayout createDefaultLoadingLayout(@NonNull PullToRefreshBase.AnimationStyle animationStyle, @NonNull Context context, @NonNull PullToRefreshBase.Mode mode, @NonNull PullToRefreshBase.Orientation orientation) {
        int i2 = AnonymousClass1.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$AnimationStyle[animationStyle.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (!PullToRefreshConfig.getInstance().isLottieEnable()) {
                        return new JDLoadingLayoutApi16(context, mode, orientation, null);
                    }
                    return new JDLoadingLayout(context, mode, orientation, null);
                } else if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                    if (!PullToRefreshConfig.getInstance().isLottieEnable()) {
                        return new JDLoadingLayoutApi16(context, mode, orientation, null);
                    }
                    return new JDLoadingLayout(context, mode, orientation, null);
                } else {
                    return new FlipLoadingLayout(context, mode, orientation, null);
                }
            }
            return new FlipLoadingLayout(context, mode, orientation, null);
        }
        return new RotateLoadingLayout(context, mode, orientation, null);
    }
}
