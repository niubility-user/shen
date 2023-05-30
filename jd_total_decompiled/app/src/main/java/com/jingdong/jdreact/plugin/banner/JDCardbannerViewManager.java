package com.jingdong.jdreact.plugin.banner;

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
import com.jingdong.jdreact.plugin.banner.ui.CardSwitchListener;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes13.dex */
public class JDCardbannerViewManager extends ViewGroupManager<CardFragment> {
    private static final int PLAY_AND_PAUSE = 5;
    private static final String TAG = "JDCardbannerViewManager";
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

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class RCTCardFragment extends CardFragment implements LifecycleEventListener, CardSwitchListener {
        public RCTCardFragment(ThemedReactContext themedReactContext) {
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
            clearCardSlide();
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostPause() {
            enableflip(false);
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostResume() {
            enableflip(JDCardbannerViewManager.mPlay);
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
        return "RTCJDReactbannerView";
    }

    @ReactProp(name = "scalexy")
    public void setScaleXY(RCTCardFragment rCTCardFragment, boolean z) {
        rCTCardFragment.setScaleXY(z);
    }

    @ReactProp(name = "source")
    public void setSource(RCTCardFragment rCTCardFragment, @Nullable ReadableMap readableMap) {
        if (readableMap != null && readableMap.hasKey("urls")) {
            ReadableArray array = readableMap.getArray("urls");
            ArrayList arrayList = new ArrayList();
            int size = array.size();
            for (int i2 = 0; i2 < size; i2++) {
                CardDataItem cardDataItem = new CardDataItem();
                cardDataItem.imagePath = array.getString(i2);
                arrayList.add(cardDataItem);
            }
            int i3 = readableMap.hasKey("maxShow") ? readableMap.getInt("maxShow") : 4;
            int i4 = readableMap.hasKey("initIndex") ? readableMap.getInt("initIndex") : 0;
            int i5 = 1;
            if (readableMap.hasKey("styletype")) {
                String string = readableMap.getString("styletype");
                if (!"invertedTimeMachine".equals(string)) {
                    if ("coverFlow".equals(string)) {
                        i5 = 2;
                    } else if ("linear".equals(string)) {
                        i5 = 3;
                    }
                }
                rCTCardFragment.setStyle(i5);
            } else {
                rCTCardFragment.setStyle(1);
            }
            if (readableMap.hasKey("timeInteval")) {
                rCTCardFragment.setTimeInteval(readableMap.getInt("timeInteval"));
            }
            rCTCardFragment.setDataList(arrayList, i3, i4);
            rCTCardFragment.registlistener();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public CardFragment createViewInstance(ThemedReactContext themedReactContext) {
        return new RCTCardFragment(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(CardFragment cardFragment) {
        super.onDropViewInstance((JDCardbannerViewManager) cardFragment);
        ((ThemedReactContext) cardFragment.getContext()).removeLifecycleEventListener((RCTCardFragment) cardFragment);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(CardFragment cardFragment, int i2, @javax.annotation.Nullable ReadableArray readableArray) {
        if (i2 != 5) {
            return;
        }
        mPlay = readableArray.getBoolean(0);
        cardFragment.enableflip(readableArray.getBoolean(0));
    }
}
