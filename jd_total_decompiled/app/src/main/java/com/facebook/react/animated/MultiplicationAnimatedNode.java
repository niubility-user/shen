package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

/* loaded from: classes.dex */
public class MultiplicationAnimatedNode extends ValueAnimatedNode {
    private final int[] mInputNodes;
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;

    public MultiplicationAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
        ReadableArray array = readableMap.getArray("input");
        this.mInputNodes = new int[array.size()];
        int i2 = 0;
        while (true) {
            int[] iArr = this.mInputNodes;
            if (i2 >= iArr.length) {
                return;
            }
            iArr[i2] = array.getInt(i2);
            i2++;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x002e, code lost:
        throw new com.facebook.react.bridge.JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.multiply node");
     */
    @Override // com.facebook.react.animated.AnimatedNode
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void update() {
        this.mValue = 1.0d;
        int i2 = 0;
        while (true) {
            int[] iArr = this.mInputNodes;
            if (i2 >= iArr.length) {
                return;
            }
            AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(iArr[i2]);
            if (nodeById == null || !(nodeById instanceof ValueAnimatedNode)) {
                break;
            }
            this.mValue *= ((ValueAnimatedNode) nodeById).getValue();
            i2++;
        }
    }
}
