package com.facebook.react.fabric;

import android.util.Pair;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.facebook.systrace.Systrace;
import java.util.HashSet;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class FabricEventEmitter implements RCTEventEmitter {
    private static final String TAG = "FabricEventEmitter";
    private final FabricUIManager mUIManager;

    public FabricEventEmitter(FabricUIManager fabricUIManager) {
        this.mUIManager = fabricUIManager;
    }

    private WritableArray copyWritableArray(WritableArray writableArray) {
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        for (int i2 = 0; i2 < writableArray.size(); i2++) {
            writableNativeArray.pushMap(getWritableMap(writableArray.getMap(i2)));
        }
        return writableNativeArray;
    }

    private WritableMap getWritableMap(ReadableMap readableMap) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.merge(readableMap);
        return writableNativeMap;
    }

    private Pair<WritableArray, WritableArray> removeTouchesAtIndices(WritableArray writableArray, WritableArray writableArray2) {
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        WritableNativeArray writableNativeArray2 = new WritableNativeArray();
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < writableArray2.size(); i2++) {
            int i3 = writableArray2.getInt(i2);
            writableNativeArray.pushMap(getWritableMap(writableArray.getMap(i3)));
            hashSet.add(Integer.valueOf(i3));
        }
        for (int i4 = 0; i4 < writableArray.size(); i4++) {
            if (!hashSet.contains(Integer.valueOf(i4))) {
                writableNativeArray2.pushMap(getWritableMap(writableArray.getMap(i4)));
            }
        }
        return new Pair<>(writableNativeArray, writableNativeArray2);
    }

    private Pair<WritableArray, WritableArray> touchSubsequence(WritableArray writableArray, WritableArray writableArray2) {
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        for (int i2 = 0; i2 < writableArray2.size(); i2++) {
            writableNativeArray.pushMap(getWritableMap(writableArray.getMap(writableArray2.getInt(i2))));
        }
        return new Pair<>(writableNativeArray, writableArray);
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveEvent(int i2, String str, @Nullable WritableMap writableMap) {
        Systrace.beginSection(0L, "FabricEventEmitter.receiveEvent('" + str + "')");
        this.mUIManager.receiveEvent(i2, str, writableMap);
        Systrace.endSection(0L);
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveTouches(String str, WritableArray writableArray, WritableArray writableArray2) {
        Pair<WritableArray, WritableArray> removeTouchesAtIndices;
        if (!TouchesHelper.TOP_TOUCH_END_KEY.equalsIgnoreCase(str) && !TouchesHelper.TOP_TOUCH_CANCEL_KEY.equalsIgnoreCase(str)) {
            removeTouchesAtIndices = touchSubsequence(writableArray, writableArray2);
        } else {
            removeTouchesAtIndices = removeTouchesAtIndices(writableArray, writableArray2);
        }
        WritableArray writableArray3 = (WritableArray) removeTouchesAtIndices.first;
        WritableArray writableArray4 = (WritableArray) removeTouchesAtIndices.second;
        for (int i2 = 0; i2 < writableArray3.size(); i2++) {
            WritableMap writableMap = getWritableMap(writableArray3.getMap(i2));
            writableMap.putArray(TouchesHelper.CHANGED_TOUCHES_KEY, copyWritableArray(writableArray3));
            writableMap.putArray(TouchesHelper.TOUCHES_KEY, copyWritableArray(writableArray4));
            int i3 = writableMap.getInt(TouchesHelper.TARGET_KEY);
            if (i3 < 1) {
                FLog.e(TAG, "A view is reporting that a touch occurred on tag zero.");
                i3 = 0;
            }
            receiveEvent(i3, str, writableMap);
        }
    }
}
