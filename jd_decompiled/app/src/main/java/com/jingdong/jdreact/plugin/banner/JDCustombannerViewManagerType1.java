package com.jingdong.jdreact.plugin.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.jdreact.plugin.banner.ui.CardSwitchListener;
import java.util.Map;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes13.dex */
public class JDCustombannerViewManagerType1 extends ViewGroupManager<CardSlidePanelStyle6> {
    private static final int CHANGE_LEFT = 1;
    private static final int CHANGE_RIGHT = 2;
    private static final int CLEAR_STATUS = 3;
    private static final int INFLATE = 4;
    private static final int PLAY_AND_PAUSE = 5;
    private static boolean mPlay = true;

    /* loaded from: classes13.dex */
    private enum BannerEvent {
        EVENT_CLICKED("onItemClick"),
        EVENT_SWITCH("onSwitched"),
        EVENT_SCROLL("onViewScroll");
        
        private String mName;

        BannerEvent(String str) {
            this.mName = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mName;
        }
    }

    public static int dip2px(Context context, float f2) {
        return (int) ((f2 * JDReactHelper.newInstance().getDensity(context)) + 0.5f);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("playAndPause", 5);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        MapBuilder.Builder builder = MapBuilder.builder();
        for (BannerEvent bannerEvent : BannerEvent.values()) {
            builder.put(bannerEvent.toString(), MapBuilder.of("registrationName", bannerEvent.toString()));
        }
        return builder.build();
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RTCJDReactCustombannerViewType1";
    }

    @ReactProp(name = "autoScroll")
    public void setAutoScroll(RTCCardSlidePanelStyle6 rTCCardSlidePanelStyle6, boolean z) {
        rTCCardSlidePanelStyle6.setAutoFliper(z ? 1 : 2);
    }

    @ReactProp(name = "autoflip")
    public void setAutoflip(RTCCardSlidePanelStyle6 rTCCardSlidePanelStyle6, @Nullable boolean z) {
        rTCCardSlidePanelStyle6.setAutoFliper(z ? 1 : 2);
    }

    @ReactProp(name = "initialPage")
    public void setInitPage(RTCCardSlidePanelStyle6 rTCCardSlidePanelStyle6, int i2) {
        rTCCardSlidePanelStyle6.setInitIndex(i2);
    }

    @ReactProp(name = "maxShow")
    public void setMaxShow(RTCCardSlidePanelStyle6 rTCCardSlidePanelStyle6, @Nullable int i2) {
        rTCCardSlidePanelStyle6.setMaxShowNumber(i2);
    }

    @ReactProp(name = IMediaPlayer.OnNativeInvokeListener.ARG_OFFSET)
    public void setOffsetStep(RTCCardSlidePanelStyle6 rTCCardSlidePanelStyle6, @Nullable int i2) {
        rTCCardSlidePanelStyle6.setOffsetStep(i2);
    }

    @ReactProp(name = "offsetwithdpi")
    public void setOffsetStepWithDpi(RTCCardSlidePanelStyle6 rTCCardSlidePanelStyle6, @Nullable int i2) {
        rTCCardSlidePanelStyle6.setOffsetStep(dip2px(rTCCardSlidePanelStyle6.getContext(), i2));
    }

    @ReactProp(name = "marginRight")
    public void setPadding(RTCCardSlidePanelStyle6 rTCCardSlidePanelStyle6, @Nullable int i2) {
        rTCCardSlidePanelStyle6.setMarginRight(i2);
    }

    @ReactProp(name = "marginRightwithdpi")
    public void setPaddingWithDpi(RTCCardSlidePanelStyle6 rTCCardSlidePanelStyle6, @Nullable int i2) {
        rTCCardSlidePanelStyle6.setMarginRight(dip2px(rTCCardSlidePanelStyle6.getContext(), i2));
    }

    @ReactProp(name = "scal")
    public void setScal(RTCCardSlidePanelStyle6 rTCCardSlidePanelStyle6, @Nullable double d) {
        rTCCardSlidePanelStyle6.setScal((float) d);
    }

    @ReactProp(name = "carouselType")
    public void setStyle(RTCCardSlidePanelStyle6 rTCCardSlidePanelStyle6, String str) {
        rTCCardSlidePanelStyle6.setMode(str);
    }

    @ReactProp(name = "timeInteval")
    public void setTimeInteval(RTCCardSlidePanelStyle6 rTCCardSlidePanelStyle6, @Nullable int i2) {
        rTCCardSlidePanelStyle6.setTimeInteval(i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public CardSlidePanelStyle6 createViewInstance(ThemedReactContext themedReactContext) {
        RTCCardSlidePanelStyle6 rTCCardSlidePanelStyle6 = new RTCCardSlidePanelStyle6(themedReactContext);
        rTCCardSlidePanelStyle6.registlistener();
        return rTCCardSlidePanelStyle6;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(CardSlidePanelStyle6 cardSlidePanelStyle6) {
        super.onDropViewInstance((JDCustombannerViewManagerType1) cardSlidePanelStyle6);
        ((ThemedReactContext) cardSlidePanelStyle6.getContext()).removeLifecycleEventListener((RTCCardSlidePanelStyle6) cardSlidePanelStyle6);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(CardSlidePanelStyle6 cardSlidePanelStyle6, int i2, @javax.annotation.Nullable ReadableArray readableArray) {
        if (i2 == 1) {
            cardSlidePanelStyle6.showfilpperleft();
        } else if (i2 == 2) {
            cardSlidePanelStyle6.showfilpper();
        } else if (i2 == 3) {
            cardSlidePanelStyle6.clearStatus();
        } else if (i2 == 4) {
            cardSlidePanelStyle6.inflateAgain();
        } else if (i2 != 5) {
        } else {
            cardSlidePanelStyle6.enableflip(readableArray.getBoolean(0));
            mPlay = readableArray.getBoolean(0);
        }
    }

    /* loaded from: classes13.dex */
    public static class RTCCardSlidePanelStyle6 extends CardSlidePanelStyle6 implements LifecycleEventListener, CardSwitchListener {
        private static final String TAG = "RTCCardSlidePanelStyle6";

        public RTCCardSlidePanelStyle6(ThemedReactContext themedReactContext) {
            super(themedReactContext);
            themedReactContext.addLifecycleEventListener(this);
            setCardSwitchListener(this);
        }

        private void dispatchEvent(String str, WritableMap writableMap) {
            ((RCTEventEmitter) ((ReactContext) getContext()).getJSModule(RCTEventEmitter.class)).receiveEvent(getId(), str, writableMap);
        }

        @Override // com.jingdong.jdreact.plugin.banner.ui.CardSwitchListener
        public void onCardVanish(int i2, int i3) {
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostDestroy() {
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostPause() {
            enableflip(false);
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostResume() {
            enableflip(JDCustombannerViewManagerType1.mPlay);
        }

        @Override // com.jingdong.jdreact.plugin.banner.ui.CardSwitchListener
        public void onItemClick(View view, int i2) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("index", i2);
            dispatchEvent(BannerEvent.EVENT_CLICKED.toString(), createMap);
        }

        @Override // com.jingdong.jdreact.plugin.banner.ui.CardSwitchListener
        public void onShow(int i2) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("index", i2);
            dispatchEvent(BannerEvent.EVENT_SWITCH.toString(), createMap);
        }

        @Override // com.jingdong.jdreact.plugin.banner.ui.CardSwitchListener
        public void onViewScroll(double d) {
        }

        public void registlistener() {
            setCardSwitchListener(this);
        }

        public RTCCardSlidePanelStyle6(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public RTCCardSlidePanelStyle6(Context context, AttributeSet attributeSet, int i2) {
            super(context, attributeSet, i2);
        }
    }
}
