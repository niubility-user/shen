package com.facebook.react.views.scroll;

import androidx.core.util.Pools;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.jingdong.jdsdk.constant.JshopConst;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class ScrollEvent extends Event<ScrollEvent> {
    private static final Pools.SynchronizedPool<ScrollEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(3);
    private int mContentHeight;
    private int mContentWidth;
    @Nullable
    private ScrollEventType mScrollEventType;
    private int mScrollViewHeight;
    private int mScrollViewWidth;
    private int mScrollX;
    private int mScrollY;
    private double mXVelocity;
    private double mYVelocity;

    private ScrollEvent() {
    }

    private void init(int i2, ScrollEventType scrollEventType, int i3, int i4, float f2, float f3, int i5, int i6, int i7, int i8) {
        super.init(i2);
        this.mScrollEventType = scrollEventType;
        this.mScrollX = i3;
        this.mScrollY = i4;
        this.mXVelocity = f2;
        this.mYVelocity = f3;
        this.mContentWidth = i5;
        this.mContentHeight = i6;
        this.mScrollViewWidth = i7;
        this.mScrollViewHeight = i8;
    }

    public static ScrollEvent obtain(int i2, ScrollEventType scrollEventType, int i3, int i4, float f2, float f3, int i5, int i6, int i7, int i8) {
        ScrollEvent acquire = EVENTS_POOL.acquire();
        if (acquire == null) {
            acquire = new ScrollEvent();
        }
        acquire.init(i2, scrollEventType, i3, i4, f2, f3, i5, i6, i7, i8);
        return acquire;
    }

    private WritableMap serializeEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("top", 0.0d);
        createMap.putDouble("bottom", 0.0d);
        createMap.putDouble("left", 0.0d);
        createMap.putDouble("right", 0.0d);
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putDouble(JshopConst.JSHOP_PROMOTIO_X, PixelUtil.toDIPFromPixel(this.mScrollX));
        createMap2.putDouble(JshopConst.JSHOP_PROMOTIO_Y, PixelUtil.toDIPFromPixel(this.mScrollY));
        WritableMap createMap3 = Arguments.createMap();
        createMap3.putDouble("width", PixelUtil.toDIPFromPixel(this.mContentWidth));
        createMap3.putDouble("height", PixelUtil.toDIPFromPixel(this.mContentHeight));
        WritableMap createMap4 = Arguments.createMap();
        createMap4.putDouble("width", PixelUtil.toDIPFromPixel(this.mScrollViewWidth));
        createMap4.putDouble("height", PixelUtil.toDIPFromPixel(this.mScrollViewHeight));
        WritableMap createMap5 = Arguments.createMap();
        createMap5.putDouble(JshopConst.JSHOP_PROMOTIO_X, this.mXVelocity);
        createMap5.putDouble(JshopConst.JSHOP_PROMOTIO_Y, this.mYVelocity);
        WritableMap createMap6 = Arguments.createMap();
        createMap6.putMap("contentInset", createMap);
        createMap6.putMap("contentOffset", createMap2);
        createMap6.putMap("contentSize", createMap3);
        createMap6.putMap("layoutMeasurement", createMap4);
        createMap6.putMap("velocity", createMap5);
        createMap6.putInt(TouchesHelper.TARGET_KEY, getViewTag());
        createMap6.putBoolean("responderIgnoreScroll", true);
        return createMap6;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public boolean canCoalesce() {
        return this.mScrollEventType == ScrollEventType.SCROLL;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
    }

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return (short) 0;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return ScrollEventType.getJSEventName((ScrollEventType) Assertions.assertNotNull(this.mScrollEventType));
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void onDispose() {
        EVENTS_POOL.release(this);
    }
}
