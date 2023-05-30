package com.facebook.react.uimanager;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;

/* loaded from: classes12.dex */
public class TransformHelper {
    private static ThreadLocal<double[]> sHelperMatrix = new ThreadLocal<double[]>() { // from class: com.facebook.react.uimanager.TransformHelper.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public double[] initialValue() {
            return new double[16];
        }
    };

    private static double convertToRadians(ReadableMap readableMap, String str) {
        double d;
        boolean z = true;
        if (readableMap.getType(str) == ReadableType.String) {
            String string = readableMap.getString(str);
            if (string.endsWith("rad")) {
                string = string.substring(0, string.length() - 3);
            } else if (string.endsWith("deg")) {
                string = string.substring(0, string.length() - 3);
                z = false;
            }
            d = Float.parseFloat(string);
        } else {
            d = readableMap.getDouble(str);
        }
        return z ? d : MatrixMathHelper.degreesToRadians(d);
    }

    public static void processTransform(ReadableArray readableArray, double[] dArr) {
        double[] dArr2 = sHelperMatrix.get();
        MatrixMathHelper.resetIdentityMatrix(dArr);
        int size = readableArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            ReadableMap map = readableArray.getMap(i2);
            String nextKey = map.keySetIterator().nextKey();
            MatrixMathHelper.resetIdentityMatrix(dArr2);
            if ("matrix".equals(nextKey)) {
                ReadableArray array = map.getArray(nextKey);
                for (int i3 = 0; i3 < 16; i3++) {
                    dArr2[i3] = array.getDouble(i3);
                }
            } else if ("perspective".equals(nextKey)) {
                MatrixMathHelper.applyPerspective(dArr2, map.getDouble(nextKey));
            } else if ("rotateX".equals(nextKey)) {
                MatrixMathHelper.applyRotateX(dArr2, convertToRadians(map, nextKey));
            } else if ("rotateY".equals(nextKey)) {
                MatrixMathHelper.applyRotateY(dArr2, convertToRadians(map, nextKey));
            } else if (!"rotate".equals(nextKey) && !"rotateZ".equals(nextKey)) {
                if ("scale".equals(nextKey)) {
                    double d = map.getDouble(nextKey);
                    MatrixMathHelper.applyScaleX(dArr2, d);
                    MatrixMathHelper.applyScaleY(dArr2, d);
                } else if ("scaleX".equals(nextKey)) {
                    MatrixMathHelper.applyScaleX(dArr2, map.getDouble(nextKey));
                } else if ("scaleY".equals(nextKey)) {
                    MatrixMathHelper.applyScaleY(dArr2, map.getDouble(nextKey));
                } else if ("translate".equals(nextKey)) {
                    ReadableArray array2 = map.getArray(nextKey);
                    MatrixMathHelper.applyTranslate3D(dArr2, array2.getDouble(0), array2.getDouble(1), array2.size() > 2 ? array2.getDouble(2) : 0.0d);
                } else if ("translateX".equals(nextKey)) {
                    MatrixMathHelper.applyTranslate2D(dArr2, map.getDouble(nextKey), 0.0d);
                } else if ("translateY".equals(nextKey)) {
                    MatrixMathHelper.applyTranslate2D(dArr2, 0.0d, map.getDouble(nextKey));
                } else if ("skewX".equals(nextKey)) {
                    MatrixMathHelper.applySkewX(dArr2, convertToRadians(map, nextKey));
                } else if ("skewY".equals(nextKey)) {
                    MatrixMathHelper.applySkewY(dArr2, convertToRadians(map, nextKey));
                } else {
                    throw new JSApplicationIllegalArgumentException("Unsupported transform type: " + nextKey);
                }
            } else {
                MatrixMathHelper.applyRotateZ(dArr2, convertToRadians(map, nextKey));
            }
            MatrixMathHelper.multiplyInto(dArr, dArr, dArr2);
        }
    }
}
