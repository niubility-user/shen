package com.facebook.react.animated;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
class InterpolationAnimatedNode extends ValueAnimatedNode {
    public static final String EXTRAPOLATE_TYPE_CLAMP = "clamp";
    public static final String EXTRAPOLATE_TYPE_EXTEND = "extend";
    public static final String EXTRAPOLATE_TYPE_IDENTITY = "identity";
    private final String mExtrapolateLeft;
    private final String mExtrapolateRight;
    private final double[] mInputRange;
    private final double[] mOutputRange;
    @Nullable
    private ValueAnimatedNode mParent;

    public InterpolationAnimatedNode(ReadableMap readableMap) {
        this.mInputRange = fromDoubleArray(readableMap.getArray("inputRange"));
        this.mOutputRange = fromDoubleArray(readableMap.getArray("outputRange"));
        this.mExtrapolateLeft = readableMap.getString("extrapolateLeft");
        this.mExtrapolateRight = readableMap.getString("extrapolateRight");
    }

    private static int findRangeIndex(double d, double[] dArr) {
        int i2 = 1;
        while (i2 < dArr.length - 1 && dArr[i2] < d) {
            i2++;
        }
        return i2 - 1;
    }

    private static double[] fromDoubleArray(ReadableArray readableArray) {
        int size = readableArray.size();
        double[] dArr = new double[size];
        for (int i2 = 0; i2 < size; i2++) {
            dArr[i2] = readableArray.getDouble(i2);
        }
        return dArr;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x006f, code lost:
        if (r23.equals(com.facebook.react.animated.InterpolationAnimatedNode.EXTRAPOLATE_TYPE_CLAMP) == false) goto L29;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static double interpolate(double d, double d2, double d3, double d4, double d5, String str, String str2) {
        double d6;
        char c2;
        char c3 = 2;
        if (d < d2) {
            str.hashCode();
            switch (str.hashCode()) {
                case -1289044198:
                    if (str.equals("extend")) {
                        c2 = 0;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case -135761730:
                    if (str.equals(EXTRAPOLATE_TYPE_IDENTITY)) {
                        c2 = 1;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 94742715:
                    if (str.equals(EXTRAPOLATE_TYPE_CLAMP)) {
                        c2 = 2;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                default:
                    c2 = '\uffff';
                    break;
            }
            switch (c2) {
                case 0:
                    break;
                case 1:
                    return d;
                case 2:
                    d6 = d2;
                    break;
                default:
                    throw new JSApplicationIllegalArgumentException("Invalid extrapolation type " + str + "for left extrapolation");
            }
            if (d6 > d3) {
                str2.hashCode();
                switch (str2.hashCode()) {
                    case -1289044198:
                        if (str2.equals("extend")) {
                            c3 = 0;
                            break;
                        }
                        c3 = '\uffff';
                        break;
                    case -135761730:
                        if (str2.equals(EXTRAPOLATE_TYPE_IDENTITY)) {
                            c3 = 1;
                            break;
                        }
                        c3 = '\uffff';
                        break;
                    case 94742715:
                        break;
                    default:
                        c3 = '\uffff';
                        break;
                }
                switch (c3) {
                    case 0:
                        break;
                    case 1:
                        return d6;
                    case 2:
                        d6 = d3;
                        break;
                    default:
                        throw new JSApplicationIllegalArgumentException("Invalid extrapolation type " + str2 + "for right extrapolation");
                }
            }
            return d4 + (((d5 - d4) * (d6 - d2)) / (d3 - d2));
        }
        d6 = d;
        if (d6 > d3) {
        }
        return d4 + (((d5 - d4) * (d6 - d2)) / (d3 - d2));
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public void onAttachedToNode(AnimatedNode animatedNode) {
        if (this.mParent == null) {
            if (animatedNode instanceof ValueAnimatedNode) {
                this.mParent = (ValueAnimatedNode) animatedNode;
                return;
            }
            throw new IllegalArgumentException("Parent is of an invalid type");
        }
        throw new IllegalStateException("Parent already attached");
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public void onDetachedFromNode(AnimatedNode animatedNode) {
        if (animatedNode == this.mParent) {
            this.mParent = null;
            return;
        }
        throw new IllegalArgumentException("Invalid parent node provided");
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public void update() {
        ValueAnimatedNode valueAnimatedNode = this.mParent;
        if (valueAnimatedNode == null) {
            return;
        }
        this.mValue = interpolate(valueAnimatedNode.getValue(), this.mInputRange, this.mOutputRange, this.mExtrapolateLeft, this.mExtrapolateRight);
    }

    static double interpolate(double d, double[] dArr, double[] dArr2, String str, String str2) {
        int findRangeIndex = findRangeIndex(d, dArr);
        int i2 = findRangeIndex + 1;
        return interpolate(d, dArr[findRangeIndex], dArr[i2], dArr2[findRangeIndex], dArr2[i2], str, str2);
    }
}
