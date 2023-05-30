package com.jingdong.jdreact.plugin.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.jdreact.plugin.banner.ui.CardSwitchListener;
import java.util.Map;

/* loaded from: classes13.dex */
public class JDCustomCardbannerWithOutTouchViewManager extends ViewGroupManager<CardSlidePanelStyle4> {
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
        return "RTCJDReactCustombannerViewWithOutTouch";
    }

    @ReactProp(name = "autoScroll")
    public void setAutoScroll(RTCCardSlidePanelStyle4 rTCCardSlidePanelStyle4, boolean z) {
        rTCCardSlidePanelStyle4.setAutoFliper(z ? 1 : 2);
    }

    @ReactProp(name = "initialPage")
    public void setInitPage(RTCCardSlidePanelStyle4 rTCCardSlidePanelStyle4, int i2) {
        rTCCardSlidePanelStyle4.setInitIndex(i2);
    }

    @ReactProp(name = "paddings")
    public void setPadding(RTCCardSlidePanelStyle4 rTCCardSlidePanelStyle4, @Nullable ReadableMap readableMap) {
        if (readableMap == null) {
            return;
        }
        int i2 = readableMap.hasKey("paddingLeftRight") ? readableMap.getInt("paddingLeftRight") : 0;
        int i3 = readableMap.hasKey("MarginLeftRight") ? readableMap.getInt("MarginLeftRight") : 0;
        if (i2 == 0 || i3 == 0) {
            return;
        }
        rTCCardSlidePanelStyle4.setPadingMargin(i3, i2);
    }

    @ReactProp(name = "paddingswithdpi")
    public void setPaddingWithDpi(RTCCardSlidePanelStyle4 rTCCardSlidePanelStyle4, @Nullable ReadableMap readableMap) {
        if (readableMap == null) {
            return;
        }
        int i2 = readableMap.hasKey("paddingLeftRight") ? readableMap.getInt("paddingLeftRight") : -1;
        int i3 = readableMap.hasKey("MarginLeftRight") ? readableMap.getInt("MarginLeftRight") : -1;
        if (i2 == -1 || i3 == -1) {
            return;
        }
        rTCCardSlidePanelStyle4.setPadingMargin(dip2px(rTCCardSlidePanelStyle4.getContext(), i3), dip2px(rTCCardSlidePanelStyle4.getContext(), i2));
    }

    @ReactProp(name = "scalexy")
    public void setScaleXY(RTCCardSlidePanelStyle4 rTCCardSlidePanelStyle4, boolean z) {
        rTCCardSlidePanelStyle4.setScaleXY(z);
    }

    @ReactProp(name = "carouselType")
    public void setStyle(RTCCardSlidePanelStyle4 rTCCardSlidePanelStyle4, String str) {
        rTCCardSlidePanelStyle4.setMode(str);
    }

    @ReactProp(name = "timeInteval")
    public void setTimeInteval(RTCCardSlidePanelStyle4 rTCCardSlidePanelStyle4, @Nullable int i2) {
        rTCCardSlidePanelStyle4.setTimeInteval(i2);
    }

    /* loaded from: classes13.dex */
    public static class RTCCardSlidePanelStyle4 extends CardSlidePanelStyle4 implements LifecycleEventListener, CardSwitchListener {
        private static final String TAG = "RTCCardSlidePanelStyle4";

        public RTCCardSlidePanelStyle4(ThemedReactContext themedReactContext) {
            super(themedReactContext);
            themedReactContext.addLifecycleEventListener(this);
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
            enableflip(JDCustomCardbannerWithOutTouchViewManager.mPlay);
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

        public RTCCardSlidePanelStyle4(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public RTCCardSlidePanelStyle4(Context context, AttributeSet attributeSet, int i2) {
            super(context, attributeSet, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public CardSlidePanelStyle4 createViewInstance(ThemedReactContext themedReactContext) {
        RTCCardSlidePanelStyle4 rTCCardSlidePanelStyle4 = new RTCCardSlidePanelStyle4(themedReactContext);
        rTCCardSlidePanelStyle4.registlistener();
        return rTCCardSlidePanelStyle4;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(CardSlidePanelStyle4 cardSlidePanelStyle4) {
        super.onDropViewInstance((JDCustomCardbannerWithOutTouchViewManager) cardSlidePanelStyle4);
        ((ThemedReactContext) cardSlidePanelStyle4.getContext()).removeLifecycleEventListener((RTCCardSlidePanelStyle4) cardSlidePanelStyle4);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(CardSlidePanelStyle4 cardSlidePanelStyle4, int i2, @javax.annotation.Nullable ReadableArray readableArray) {
        if (i2 == 1) {
            cardSlidePanelStyle4.showfilpperleft();
        } else if (i2 == 2) {
            cardSlidePanelStyle4.showfilpper();
        } else if (i2 == 3) {
            cardSlidePanelStyle4.clearStatus();
        } else if (i2 == 4) {
            cardSlidePanelStyle4.inflateAgain();
        } else if (i2 != 5) {
        } else {
            cardSlidePanelStyle4.enableflip(readableArray.getBoolean(0));
            mPlay = readableArray.getBoolean(0);
        }
    }
}
