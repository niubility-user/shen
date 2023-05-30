package com.jingdong.common.jdreactFramework.views.pureVideo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import com.jingdong.common.unification.video.player.IVideoViewOnTouchListener;
import com.jingdong.common.unification.video.player.VideoPlayView;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class RCTVideoPlayView extends VideoPlayView implements IVideoViewOnTouchListener {
    public static final String TAG = "RCTVideoPlayView";
    private AutoPlayEnum autoPlay;
    private GestureDetector gestureDetector;
    private RCTVideoPlayViewListener mListener;
    private BroadcastReceiver myNetReceiver;

    /* renamed from: com.jingdong.common.jdreactFramework.views.pureVideo.RCTVideoPlayView$2  reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$common$jdreactFramework$views$pureVideo$RCTVideoPlayView$AutoPlayEnum;

        static {
            int[] iArr = new int[AutoPlayEnum.values().length];
            $SwitchMap$com$jingdong$common$jdreactFramework$views$pureVideo$RCTVideoPlayView$AutoPlayEnum = iArr;
            try {
                iArr[AutoPlayEnum.WIFI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jingdong$common$jdreactFramework$views$pureVideo$RCTVideoPlayView$AutoPlayEnum[AutoPlayEnum.NEVER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jingdong$common$jdreactFramework$views$pureVideo$RCTVideoPlayView$AutoPlayEnum[AutoPlayEnum.ALWAYS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes5.dex */
    public enum AutoPlayEnum {
        WIFI("wifi"),
        ALWAYS(ReactScrollViewHelper.OVER_SCROLL_ALWAYS),
        NEVER(ReactScrollViewHelper.OVER_SCROLL_NEVER);
        
        private String mName;

        AutoPlayEnum(String str) {
            this.mName = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mName;
        }
    }

    /* loaded from: classes5.dex */
    public interface RCTVideoPlayViewListener {
        void netChange();
    }

    public RCTVideoPlayView(Context context) {
        super(context);
        this.autoPlay = AutoPlayEnum.NEVER;
        init();
    }

    private void init() {
        registerNetworkStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void netWorkChanged() {
        if (this.autoPlay == AutoPlayEnum.WIFI) {
            setAutoPlay(NetUtils.isWifi());
        }
        RCTVideoPlayViewListener rCTVideoPlayViewListener = this.mListener;
        if (rCTVideoPlayViewListener != null) {
            rCTVideoPlayViewListener.netChange();
        }
    }

    private void registerNetworkStatus() {
        if (this.myNetReceiver == null) {
            this.myNetReceiver = new BroadcastReceiver() { // from class: com.jingdong.common.jdreactFramework.views.pureVideo.RCTVideoPlayView.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                        RCTVideoPlayView.this.netWorkChanged();
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            JdSdk.getInstance().getApplication().registerReceiver(this.myNetReceiver, intentFilter);
        }
    }

    private void unregisterNetworkStatus() {
        if (this.myNetReceiver != null) {
            try {
                JdSdk.getInstance().getApplication().unregisterReceiver(this.myNetReceiver);
                this.myNetReceiver = null;
            } catch (IllegalArgumentException e2) {
                OKLog.e("CustomIjkPlayer", e2);
            }
        }
    }

    @Override // com.jingdong.common.unification.video.player.VideoPlayView
    public Activity getActivity() {
        if (getContext() instanceof ThemedReactContext) {
            ThemedReactContext themedReactContext = (ThemedReactContext) getContext();
            if (themedReactContext.getCurrentActivity() != null) {
                return themedReactContext.getCurrentActivity();
            }
        }
        return super.getActivity();
    }

    @Override // com.jingdong.common.unification.video.player.VideoPlayView
    public void onDestroy() {
        OKLog.i(TAG, "onDestroy");
        super.onDestroy();
        unregisterNetworkStatus();
    }

    @Override // com.jingdong.common.unification.video.player.VideoPlayView, android.view.View.OnTouchListener, com.jingdong.common.unification.video.player.IVideoViewOnTouchListener, tv.danmaku.ijk.media.widget.uniform.inter.IJDVideoViewOnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        GestureDetector gestureDetector = this.gestureDetector;
        if (gestureDetector != null) {
            gestureDetector.onTouchEvent(motionEvent);
            return true;
        }
        return true;
    }

    public void setAutoPlay(AutoPlayEnum autoPlayEnum) {
        if (this.autoPlay == autoPlayEnum) {
            return;
        }
        this.autoPlay = autoPlayEnum;
        int i2 = AnonymousClass2.$SwitchMap$com$jingdong$common$jdreactFramework$views$pureVideo$RCTVideoPlayView$AutoPlayEnum[autoPlayEnum.ordinal()];
        if (i2 == 1) {
            setAutoPlay(NetUtils.isWifi());
        } else if (i2 == 2) {
            setAutoPlay(true);
        } else if (i2 != 3) {
        } else {
            setAutoPlay(false);
        }
    }

    public void setGestureDetector(GestureDetector gestureDetector) {
        this.gestureDetector = gestureDetector;
        setVideoViewOnTouchListener(gestureDetector == null ? null : this);
    }

    public void setListener(RCTVideoPlayViewListener rCTVideoPlayViewListener) {
        this.mListener = rCTVideoPlayViewListener;
    }

    public boolean setVideoPath(String str, AutoPlayEnum autoPlayEnum) {
        this.autoPlay = autoPlayEnum;
        int i2 = AnonymousClass2.$SwitchMap$com$jingdong$common$jdreactFramework$views$pureVideo$RCTVideoPlayView$AutoPlayEnum[autoPlayEnum.ordinal()];
        if (i2 != 1) {
            if (i2 != 3) {
                setPlaySourceWithoutPlay(str);
            } else {
                setPlaySource(str);
                return true;
            }
        } else if (NetUtils.isWifi()) {
            setPlaySource(str);
            return true;
        } else {
            setPlaySourceWithoutPlay(str);
        }
        return false;
    }

    public RCTVideoPlayView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.autoPlay = AutoPlayEnum.NEVER;
        init();
    }
}
