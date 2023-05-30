package com.facebook.react.animated;

import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class TransformAnimatedNode extends AnimatedNode {
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;
    private final List<TransformConfig> mTransformConfigs;

    /* loaded from: classes.dex */
    public class AnimatedTransformConfig extends TransformConfig {
        public int mNodeTag;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private AnimatedTransformConfig() {
            super();
            TransformAnimatedNode.this = r2;
        }
    }

    /* loaded from: classes.dex */
    public class StaticTransformConfig extends TransformConfig {
        public double mValue;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private StaticTransformConfig() {
            super();
            TransformAnimatedNode.this = r2;
        }
    }

    /* loaded from: classes.dex */
    public class TransformConfig {
        public String mProperty;

        private TransformConfig() {
            TransformAnimatedNode.this = r1;
        }
    }

    public TransformAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        ReadableArray array = readableMap.getArray("transforms");
        this.mTransformConfigs = new ArrayList(array.size());
        for (int i2 = 0; i2 < array.size(); i2++) {
            ReadableMap map = array.getMap(i2);
            String string = map.getString("property");
            if (map.getString("type").equals("animated")) {
                AnimatedTransformConfig animatedTransformConfig = new AnimatedTransformConfig();
                animatedTransformConfig.mProperty = string;
                animatedTransformConfig.mNodeTag = map.getInt("nodeTag");
                this.mTransformConfigs.add(animatedTransformConfig);
            } else {
                StaticTransformConfig staticTransformConfig = new StaticTransformConfig();
                staticTransformConfig.mProperty = string;
                staticTransformConfig.mValue = map.getDouble("value");
                this.mTransformConfigs.add(staticTransformConfig);
            }
        }
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
    }

    public void collectViewUpdates(JavaOnlyMap javaOnlyMap) {
        double d;
        ArrayList arrayList = new ArrayList(this.mTransformConfigs.size());
        for (TransformConfig transformConfig : this.mTransformConfigs) {
            if (transformConfig instanceof AnimatedTransformConfig) {
                AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(((AnimatedTransformConfig) transformConfig).mNodeTag);
                if (nodeById != null) {
                    if (nodeById instanceof ValueAnimatedNode) {
                        d = ((ValueAnimatedNode) nodeById).getValue();
                    } else {
                        throw new IllegalArgumentException("Unsupported type of node used as a transform child node " + nodeById.getClass());
                    }
                } else {
                    throw new IllegalArgumentException("Mapped style node does not exists");
                }
            } else {
                d = ((StaticTransformConfig) transformConfig).mValue;
            }
            arrayList.add(JavaOnlyMap.of(transformConfig.mProperty, Double.valueOf(d)));
        }
        javaOnlyMap.putArray("transform", JavaOnlyArray.from(arrayList));
    }
}
