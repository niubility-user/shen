package com.facebook.react.animated;

import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableMap;

/* loaded from: classes.dex */
class ModulusAnimatedNode extends ValueAnimatedNode {
    private final int mInputNode;
    private final double mModulus;
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;

    public ModulusAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
        this.mInputNode = readableMap.getInt("input");
        this.mModulus = readableMap.getDouble("modulus");
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public void update() {
        AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(this.mInputNode);
        if (nodeById != null && (nodeById instanceof ValueAnimatedNode)) {
            this.mValue = ((ValueAnimatedNode) nodeById).getValue() % this.mModulus;
            return;
        }
        throw new JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.modulus node");
    }
}
