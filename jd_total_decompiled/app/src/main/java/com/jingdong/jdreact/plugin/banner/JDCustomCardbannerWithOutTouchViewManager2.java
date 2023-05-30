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
public class JDCustomCardbannerWithOutTouchViewManager2 extends ViewGroupManager<CardSlidePanelStyle5> {
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
        EVENT_MORE_SHOWN("onMoreShown"),
        EVENT_SCROLL("onMoreShown");
        
        private String mName;

        BannerEvent(String str) {
            this.mName = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mName;
        }
    }

    /* loaded from: classes13.dex */
    public interface OnMoreDrag {
        void onMoreShown();
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
        return "RTCJDReactCustombannerViewWithOutTouch2";
    }

    @ReactProp(name = "alpha")
    public void setAlpha(CardSlidePanelStyle5 cardSlidePanelStyle5, @Nullable int i2) {
        if (i2 > 100) {
            i2 = 100;
        } else if (i2 < 0) {
            i2 = 0;
        }
        cardSlidePanelStyle5.setAlpha(i2 / 100.0f);
    }

    @ReactProp(name = "autoScroll")
    public void setAutoScroll(CardSlidePanelStyle5 cardSlidePanelStyle5, boolean z) {
        cardSlidePanelStyle5.setAutoFliper(z ? 1 : 2);
    }

    @ReactProp(name = "backgroundresizemode")
    public void setBackGroundResizeMode(CardSlidePanelStyle5 cardSlidePanelStyle5, String str) {
        cardSlidePanelStyle5.setBackgroundResizeMode(str);
    }

    @ReactProp(name = "blur")
    public void setBlur(CardSlidePanelStyle5 cardSlidePanelStyle5, @Nullable int i2) {
        cardSlidePanelStyle5.setBlur(i2);
    }

    @ReactProp(name = "initialPage")
    public void setInitPage(CardSlidePanelStyle5 cardSlidePanelStyle5, int i2) {
        cardSlidePanelStyle5.setInitIndex(i2);
    }

    @ReactProp(name = "showmorebutton")
    public void setMoreButton(CardSlidePanelStyle5 cardSlidePanelStyle5, @Nullable ReadableMap readableMap) {
        cardSlidePanelStyle5.showMoreButton(dip2px(cardSlidePanelStyle5.getContext(), readableMap.hasKey("width") ? readableMap.getInt("width") : 0), dip2px(cardSlidePanelStyle5.getContext(), readableMap.hasKey("margingLeft") ? readableMap.getInt("margingLeft") : 0), dip2px(cardSlidePanelStyle5.getContext(), readableMap.hasKey("margingRight") ? readableMap.getInt("margingRight") : 0));
    }

    @ReactProp(name = "paddings")
    public void setPadding(CardSlidePanelStyle5 cardSlidePanelStyle5, @Nullable ReadableMap readableMap) {
        int i2;
        int i3;
        if (readableMap == null) {
            return;
        }
        if (readableMap.hasKey("paddingLeftRight")) {
            i2 = readableMap.getInt("paddingLeftRight");
            i3 = i2;
        } else {
            int i4 = readableMap.hasKey("paddingLeft") ? readableMap.getInt("paddingLeft") : 0;
            if (readableMap.hasKey("paddingRight")) {
                i2 = i4;
                i3 = readableMap.getInt("paddingRight");
            } else {
                i2 = i4;
                i3 = 0;
            }
        }
        int i5 = readableMap.hasKey("MarginRight") ? readableMap.getInt("MarginRight") : 0;
        int i6 = readableMap.hasKey("MarginLeft") ? readableMap.getInt("MarginLeft") : 0;
        int i7 = readableMap.hasKey("MarginTop") ? readableMap.getInt("MarginTop") : 0;
        int i8 = readableMap.hasKey("MarginBottom") ? readableMap.getInt("MarginBottom") : 0;
        if (readableMap.hasKey("arrowheight")) {
            cardSlidePanelStyle5.setBackHeight(readableMap.getInt("arrowheight"));
        }
        cardSlidePanelStyle5.setPadingMargin(i6, i5, i2, i3, i7, i8);
    }

    @ReactProp(name = "paddingswithdpi")
    public void setPaddingWithDpi(CardSlidePanelStyle5 cardSlidePanelStyle5, @Nullable ReadableMap readableMap) {
        int i2;
        int i3;
        if (readableMap == null) {
            return;
        }
        if (readableMap.hasKey("paddingLeftRight")) {
            i2 = readableMap.getInt("paddingLeftRight");
            i3 = readableMap.getInt("paddingLeftRight");
        } else {
            i2 = readableMap.hasKey("paddingLeft") ? readableMap.getInt("paddingLeft") : 0;
            i3 = readableMap.hasKey("paddingRight") ? readableMap.getInt("paddingRight") : 0;
        }
        int i4 = readableMap.hasKey("MarginRight") ? readableMap.getInt("MarginRight") : 0;
        int i5 = readableMap.hasKey("MarginLeft") ? readableMap.getInt("MarginLeft") : 0;
        int i6 = readableMap.hasKey("MarginTop") ? readableMap.getInt("MarginTop") : 0;
        int i7 = readableMap.hasKey("MarginBottom") ? readableMap.getInt("MarginBottom") : 0;
        if (readableMap.hasKey("arrowheight")) {
            cardSlidePanelStyle5.setBackHeight(readableMap.getInt("arrowheight"));
        }
        cardSlidePanelStyle5.setPadingMargin(dip2px(cardSlidePanelStyle5.getContext(), i5), dip2px(cardSlidePanelStyle5.getContext(), i4), dip2px(cardSlidePanelStyle5.getContext(), i2), dip2px(cardSlidePanelStyle5.getContext(), i3), dip2px(cardSlidePanelStyle5.getContext(), i6), dip2px(cardSlidePanelStyle5.getContext(), i7));
    }

    @ReactProp(name = "rigthscal")
    public void setRightHeight(CardSlidePanelStyle5 cardSlidePanelStyle5, @Nullable float f2) {
        cardSlidePanelStyle5.setRightHeight(1.0f - f2);
    }

    @ReactProp(name = "rotate")
    public void setRotate(CardSlidePanelStyle5 cardSlidePanelStyle5, @Nullable int i2) {
        cardSlidePanelStyle5.setRotate(i2);
    }

    @ReactProp(name = "carouselType")
    public void setStyle(CardSlidePanelStyle5 cardSlidePanelStyle5, String str) {
        cardSlidePanelStyle5.setMode(str);
    }

    @ReactProp(name = "timeInteval")
    public void setTimeInteval(CardSlidePanelStyle5 cardSlidePanelStyle5, @Nullable int i2) {
        cardSlidePanelStyle5.setTimeInteval(i2);
    }

    @ReactProp(name = "forgroundcolor")
    public void setforgroundColor(CardSlidePanelStyle5 cardSlidePanelStyle5, @Nullable int i2) {
        cardSlidePanelStyle5.setforgroundColor(i2);
    }

    @ReactProp(name = "leftscal")
    public void setleftHeight(CardSlidePanelStyle5 cardSlidePanelStyle5, @Nullable float f2) {
        cardSlidePanelStyle5.setleftHeight(1.0f - f2);
    }

    /* loaded from: classes13.dex */
    public static class CardSlidePanel extends CardSlidePanelStyle5 implements LifecycleEventListener, CardSwitchListener, OnMoreDrag {
        private static final String TAG = "CardSlidePanelStyle5";

        public CardSlidePanel(ThemedReactContext themedReactContext) {
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
            enableflip(JDCustomCardbannerWithOutTouchViewManager2.mPlay);
        }

        @Override // com.jingdong.jdreact.plugin.banner.ui.CardSwitchListener
        public void onItemClick(View view, int i2) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("index", i2);
            dispatchEvent(BannerEvent.EVENT_CLICKED.toString(), createMap);
        }

        @Override // com.jingdong.jdreact.plugin.banner.JDCustomCardbannerWithOutTouchViewManager2.OnMoreDrag
        public void onMoreShown() {
            dispatchEvent(BannerEvent.EVENT_MORE_SHOWN.toString(), Arguments.createMap());
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
            setMoreButtonListener(this);
        }

        public CardSlidePanel(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public CardSlidePanel(Context context, AttributeSet attributeSet, int i2) {
            super(context, attributeSet, i2);
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public CardSlidePanelStyle5 createViewInstance(ThemedReactContext themedReactContext) {
        CardSlidePanel cardSlidePanel = new CardSlidePanel(themedReactContext);
        cardSlidePanel.registlistener();
        return cardSlidePanel;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(CardSlidePanelStyle5 cardSlidePanelStyle5) {
        super.onDropViewInstance((JDCustomCardbannerWithOutTouchViewManager2) cardSlidePanelStyle5);
        ((ThemedReactContext) cardSlidePanelStyle5.getContext()).removeLifecycleEventListener((CardSlidePanel) cardSlidePanelStyle5);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(CardSlidePanelStyle5 cardSlidePanelStyle5, int i2, @javax.annotation.Nullable ReadableArray readableArray) {
        if (i2 == 1) {
            cardSlidePanelStyle5.showfilpperleft();
        } else if (i2 == 2) {
            cardSlidePanelStyle5.showfilpper();
        } else if (i2 == 3) {
            cardSlidePanelStyle5.clearStatus();
        } else if (i2 == 4) {
            cardSlidePanelStyle5.inflateAgain();
        } else if (i2 != 5) {
        } else {
            cardSlidePanelStyle5.enableflip(readableArray.getBoolean(0));
            mPlay = readableArray.getBoolean(0);
        }
    }

    @ReactProp(name = "cycle")
    public void setRotate(CardSlidePanelStyle5 cardSlidePanelStyle5, @Nullable boolean z) {
        cardSlidePanelStyle5.setCyle(z);
    }

    @ReactProp(name = "scalexy")
    public void setforgroundColor(CardSlidePanelStyle5 cardSlidePanelStyle5, boolean z) {
        cardSlidePanelStyle5.setScaleXY(z);
    }
}
