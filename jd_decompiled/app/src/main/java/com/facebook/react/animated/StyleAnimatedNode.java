package com.facebook.react.animated;

import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class StyleAnimatedNode extends AnimatedNode {
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;
    private final Map<String, Integer> mPropMapping;

    public StyleAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        ReadableMap map = readableMap.getMap(DeeplinkProductDetailHelper.LAYER_STYLE);
        ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
        this.mPropMapping = new HashMap();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            this.mPropMapping.put(nextKey, Integer.valueOf(map.getInt(nextKey)));
        }
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
    }

    public void collectViewUpdates(JavaOnlyMap javaOnlyMap) {
        for (Map.Entry<String, Integer> entry : this.mPropMapping.entrySet()) {
            AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(entry.getValue().intValue());
            if (nodeById != null) {
                if (nodeById instanceof TransformAnimatedNode) {
                    ((TransformAnimatedNode) nodeById).collectViewUpdates(javaOnlyMap);
                } else if (nodeById instanceof ValueAnimatedNode) {
                    javaOnlyMap.putDouble(entry.getKey(), ((ValueAnimatedNode) nodeById).getValue());
                } else {
                    throw new IllegalArgumentException("Unsupported type of node used in property node " + nodeById.getClass());
                }
            } else {
                throw new IllegalArgumentException("Mapped style node does not exists");
            }
        }
    }
}
