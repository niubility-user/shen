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
public class JDCustomCardbannerViewManager extends ViewGroupManager<CardSlidePanelStyle3> {
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

    @ReactProp(name = "scalWidthAndHeight")
    public void fixLeftAndRight(RTCCardSlidePanelStyle3 rTCCardSlidePanelStyle3, @Nullable boolean z) {
        rTCCardSlidePanelStyle3.fixLeftAndRight(z);
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
        return "RTCJDReactCustombannerView";
    }

    @ReactProp(name = "autoScroll")
    public void setAutoScroll(RTCCardSlidePanelStyle3 rTCCardSlidePanelStyle3, boolean z) {
        rTCCardSlidePanelStyle3.setAutoFliper(z ? 1 : 2);
    }

    @ReactProp(name = "initialPage")
    public void setInitPage(RTCCardSlidePanelStyle3 rTCCardSlidePanelStyle3, int i2) {
        rTCCardSlidePanelStyle3.setInitIndex(i2);
    }

    @ReactProp(name = "paddings")
    public void setPadding(RTCCardSlidePanelStyle3 rTCCardSlidePanelStyle3, @Nullable ReadableMap readableMap) {
        if (readableMap == null) {
            return;
        }
        int i2 = readableMap.hasKey("paddingLeftRight") ? readableMap.getInt("paddingLeftRight") : 0;
        int i3 = readableMap.hasKey("MarginLeftRight") ? readableMap.getInt("MarginLeftRight") : 0;
        if (i2 == 0 || i3 == 0) {
            return;
        }
        rTCCardSlidePanelStyle3.setPadingMargin(i3, i2);
    }

    @ReactProp(name = "paddingswithdpi")
    public void setPaddingWithDpi(RTCCardSlidePanelStyle3 rTCCardSlidePanelStyle3, @Nullable ReadableMap readableMap) {
        if (readableMap == null) {
            return;
        }
        int i2 = readableMap.hasKey("paddingLeftRight") ? readableMap.getInt("paddingLeftRight") : -1;
        int i3 = readableMap.hasKey("MarginLeftRight") ? readableMap.getInt("MarginLeftRight") : -1;
        if (i2 == -1 || i3 == -1) {
            return;
        }
        rTCCardSlidePanelStyle3.setPadingMargin(dip2px(rTCCardSlidePanelStyle3.getContext(), i3), dip2px(rTCCardSlidePanelStyle3.getContext(), i2));
    }

    @ReactProp(name = "rigthscal")
    public void setRightHeight(RTCCardSlidePanelStyle3 rTCCardSlidePanelStyle3, @Nullable float f2) {
        rTCCardSlidePanelStyle3.setRightHeight(1.0f - f2);
    }

    @ReactProp(name = "scalexy")
    public void setScaleXY(RTCCardSlidePanelStyle3 rTCCardSlidePanelStyle3, @Nullable boolean z) {
        rTCCardSlidePanelStyle3.fixLeftAndRight(z);
    }

    @ReactProp(name = "carouselType")
    public void setStyle(RTCCardSlidePanelStyle3 rTCCardSlidePanelStyle3, String str) {
        rTCCardSlidePanelStyle3.setMode(str);
    }

    @ReactProp(name = "timeInteval")
    public void setTimeInteval(RTCCardSlidePanelStyle3 rTCCardSlidePanelStyle3, @Nullable int i2) {
        rTCCardSlidePanelStyle3.setTimeInteval(i2);
    }

    @ReactProp(name = "leftscal")
    public void setleftHeight(RTCCardSlidePanelStyle3 rTCCardSlidePanelStyle3, @Nullable float f2) {
        rTCCardSlidePanelStyle3.setleftHeight(1.0f - f2);
    }

    /* loaded from: classes13.dex */
    public static class RTCCardSlidePanelStyle3 extends CardSlidePanelStyle3 implements LifecycleEventListener, CardSwitchListener {
        private static final String TAG = "RTCCardSlidePanelStyle3";

        public RTCCardSlidePanelStyle3(ThemedReactContext themedReactContext) {
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
            enableflip(JDCustomCardbannerViewManager.mPlay);
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

        public RTCCardSlidePanelStyle3(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public RTCCardSlidePanelStyle3(Context context, AttributeSet attributeSet, int i2) {
            super(context, attributeSet, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public CardSlidePanelStyle3 createViewInstance(ThemedReactContext themedReactContext) {
        RTCCardSlidePanelStyle3 rTCCardSlidePanelStyle3 = new RTCCardSlidePanelStyle3(themedReactContext);
        rTCCardSlidePanelStyle3.registlistener();
        return rTCCardSlidePanelStyle3;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(CardSlidePanelStyle3 cardSlidePanelStyle3) {
        super.onDropViewInstance((JDCustomCardbannerViewManager) cardSlidePanelStyle3);
        ((ThemedReactContext) cardSlidePanelStyle3.getContext()).removeLifecycleEventListener((RTCCardSlidePanelStyle3) cardSlidePanelStyle3);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(CardSlidePanelStyle3 cardSlidePanelStyle3, int i2, @javax.annotation.Nullable ReadableArray readableArray) {
        if (i2 == 3) {
            cardSlidePanelStyle3.clearStatus();
        } else if (i2 == 4) {
            cardSlidePanelStyle3.inflateAgain();
        } else if (i2 != 5) {
        } else {
            cardSlidePanelStyle3.enableflip(readableArray.getBoolean(0));
            mPlay = readableArray.getBoolean(0);
        }
    }
}
