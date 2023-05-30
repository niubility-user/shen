package com.facebook.react.uimanager.events;

import android.util.SparseArray;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.common.ViewUtil;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class ReactEventEmitter implements RCTEventEmitter {
    private static final String TAG = "ReactEventEmitter";
    private final SparseArray<RCTEventEmitter> mEventEmitters = new SparseArray<>();
    private final ReactApplicationContext mReactContext;

    public ReactEventEmitter(ReactApplicationContext reactApplicationContext) {
        this.mReactContext = reactApplicationContext;
    }

    private RCTEventEmitter getEventEmitter(int i2) {
        RCTEventEmitter rCTEventEmitter = this.mEventEmitters.get(ViewUtil.getUIManagerType(i2));
        return rCTEventEmitter == null ? (RCTEventEmitter) this.mReactContext.getJSModule(RCTEventEmitter.class) : rCTEventEmitter;
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveEvent(int i2, String str, @Nullable WritableMap writableMap) {
        getEventEmitter(i2).receiveEvent(i2, str, writableMap);
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveTouches(String str, WritableArray writableArray, WritableArray writableArray2) {
        Assertions.assertCondition(writableArray.size() > 0);
        getEventEmitter(writableArray.getMap(0).getInt(TouchesHelper.TARGET_KEY)).receiveTouches(str, writableArray, writableArray2);
    }

    public void register(int i2, RCTEventEmitter rCTEventEmitter) {
        this.mEventEmitters.put(i2, rCTEventEmitter);
    }

    public void unregister(int i2) {
        this.mEventEmitters.remove(i2);
    }
}
