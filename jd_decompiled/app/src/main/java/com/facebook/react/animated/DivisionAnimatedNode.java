package com.facebook.react.animated;

import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class DivisionAnimatedNode extends ValueAnimatedNode {
    private final int[] mInputNodes;
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;

    public DivisionAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
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

    @Override // com.facebook.react.animated.AnimatedNode
    public void update() {
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
            double value = ((ValueAnimatedNode) nodeById).getValue();
            if (i2 == 0) {
                this.mValue = value;
            } else if (value != 0.0d) {
                this.mValue /= value;
            } else {
                throw new JSApplicationCausedNativeException("Detected a division by zero in Animated.divide node");
            }
            i2++;
        }
        throw new JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.divide node");
    }
}
