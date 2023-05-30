package com.jingdong.manto.m.u0.o;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.TextUtils;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.b;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class n implements i {
    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "setFillStyle";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.k kVar = (com.jingdong.manto.m.u0.o.k0.k) cVar2;
        if (kVar == null) {
            return false;
        }
        return kVar.a(cVar, canvas);
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        JSONArray optJSONArray;
        Shader radialGradient;
        JSONArray optJSONArray2;
        if (jSONArray.length() < 2) {
            return false;
        }
        String optString = jSONArray.optString(0);
        com.jingdong.manto.m.u0.n nVar = cVar.f13730f;
        if ("linear".equalsIgnoreCase(optString)) {
            if (jSONArray.length() >= 3 && (optJSONArray2 = jSONArray.optJSONArray(1)) != null && optJSONArray2.length() >= 4) {
                float convertToDeviceSize2 = MantoDensityUtils.convertToDeviceSize2(optJSONArray2, 0);
                float convertToDeviceSize22 = MantoDensityUtils.convertToDeviceSize2(optJSONArray2, 1);
                float convertToDeviceSize23 = MantoDensityUtils.convertToDeviceSize2(optJSONArray2, 2);
                float convertToDeviceSize24 = MantoDensityUtils.convertToDeviceSize2(optJSONArray2, 3);
                JSONArray optJSONArray3 = jSONArray.optJSONArray(2);
                if (optJSONArray3 != null && optJSONArray3.length() != 0) {
                    int[] iArr = new int[optJSONArray3.length()];
                    float[] fArr = new float[optJSONArray3.length()];
                    for (int i2 = 0; i2 < optJSONArray3.length(); i2++) {
                        JSONArray optJSONArray4 = optJSONArray3.optJSONArray(i2);
                        if (optJSONArray4.length() >= 2) {
                            fArr[i2] = (float) optJSONArray4.optDouble(0);
                            iArr[i2] = MantoDensityUtils.parseColor(optJSONArray4.optJSONArray(1));
                        }
                    }
                    radialGradient = new LinearGradient(convertToDeviceSize2, convertToDeviceSize22, convertToDeviceSize23, convertToDeviceSize24, iArr, fArr, Shader.TileMode.CLAMP);
                }
            }
            return false;
        } else if (!"radial".equalsIgnoreCase(optString)) {
            BitmapShader bitmapShader = null;
            if ("normal".equalsIgnoreCase(optString)) {
                JSONArray optJSONArray5 = jSONArray.optJSONArray(1);
                if (optJSONArray5 == null || optJSONArray5.length() < 4) {
                    return false;
                }
                nVar.setShader(null);
                nVar.setColor(MantoDensityUtils.parseColor(optJSONArray5));
            } else if ("pattern".equalsIgnoreCase(optString)) {
                String optString2 = jSONArray.optString(1);
                String optString3 = jSONArray.optString(2);
                if (TextUtils.isEmpty(optString2)) {
                    MantoLog.w("SetFillStyleAction", "setFillStyle failed, type is pattern but image path is null or nil.");
                    return false;
                }
                Bitmap a = cVar.b.a(cVar.f13728c.h(), optString2, (b.d) null);
                if (a != null && !a.isRecycled()) {
                    int convertToDeviceSizeByInt = MantoDensityUtils.convertToDeviceSizeByInt(a.getWidth());
                    int convertToDeviceSizeByInt2 = MantoDensityUtils.convertToDeviceSizeByInt(a.getHeight());
                    if (TextUtils.equals(optString3, "repeat")) {
                        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(a, convertToDeviceSizeByInt, convertToDeviceSizeByInt2, false);
                        Shader.TileMode tileMode = Shader.TileMode.REPEAT;
                        bitmapShader = new BitmapShader(createScaledBitmap, tileMode, tileMode);
                    } else if (TextUtils.equals(optString3, "no-repeat")) {
                        Bitmap createBitmap = Bitmap.createBitmap(convertToDeviceSizeByInt + 1, convertToDeviceSizeByInt2 + 1, Bitmap.Config.ARGB_8888);
                        new Canvas(createBitmap).drawBitmap(a, new Rect(0, 0, a.getWidth(), a.getHeight()), new RectF(0.0f, 0.0f, convertToDeviceSizeByInt, convertToDeviceSizeByInt2), (Paint) null);
                        Shader.TileMode tileMode2 = Shader.TileMode.CLAMP;
                        bitmapShader = new BitmapShader(createBitmap, tileMode2, tileMode2);
                    } else if (TextUtils.equals(optString3, "repeat-x")) {
                        Bitmap createBitmap2 = Bitmap.createBitmap(convertToDeviceSizeByInt, convertToDeviceSizeByInt2 + 1, Bitmap.Config.ARGB_8888);
                        new Canvas(createBitmap2).drawBitmap(a, new Rect(0, 0, a.getWidth(), a.getHeight()), new RectF(0.0f, 0.0f, convertToDeviceSizeByInt, convertToDeviceSizeByInt2), (Paint) null);
                        bitmapShader = new BitmapShader(createBitmap2, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
                    } else if (TextUtils.equals(optString3, "repeat-y")) {
                        Bitmap createBitmap3 = Bitmap.createBitmap(convertToDeviceSizeByInt + 1, convertToDeviceSizeByInt2, Bitmap.Config.ARGB_8888);
                        new Canvas(createBitmap3).drawBitmap(a, new Rect(0, 0, a.getWidth(), a.getHeight()), new RectF(0.0f, 0.0f, convertToDeviceSizeByInt, convertToDeviceSizeByInt2), (Paint) null);
                        bitmapShader = new BitmapShader(createBitmap3, Shader.TileMode.CLAMP, Shader.TileMode.REPEAT);
                    }
                    nVar.setShader(bitmapShader);
                }
            }
            return true;
        } else if (jSONArray.length() < 3 || (optJSONArray = jSONArray.optJSONArray(1)) == null || optJSONArray.length() < 3) {
            return false;
        } else {
            float convertToDeviceSize25 = MantoDensityUtils.convertToDeviceSize2(optJSONArray, 0);
            float convertToDeviceSize26 = MantoDensityUtils.convertToDeviceSize2(optJSONArray, 1);
            float convertToDeviceSize27 = MantoDensityUtils.convertToDeviceSize2(optJSONArray, 2);
            if (convertToDeviceSize27 <= 0.0f) {
                MantoLog.i("SetFillStyleAction", "setFillStyle(radial) failed, sr(%s) <= 0.", Float.valueOf(convertToDeviceSize27));
                return false;
            }
            JSONArray optJSONArray6 = jSONArray.optJSONArray(2);
            int[] iArr2 = new int[optJSONArray6.length()];
            float[] fArr2 = new float[optJSONArray6.length()];
            for (int i3 = 0; i3 < optJSONArray6.length(); i3++) {
                JSONArray optJSONArray7 = optJSONArray6.optJSONArray(i3);
                if (optJSONArray7.length() >= 2) {
                    fArr2[i3] = (float) optJSONArray7.optDouble(0);
                    iArr2[i3] = MantoDensityUtils.parseColor(optJSONArray7.optJSONArray(1));
                }
            }
            radialGradient = new RadialGradient(convertToDeviceSize25, convertToDeviceSize26, convertToDeviceSize27, iArr2, fArr2, Shader.TileMode.CLAMP);
        }
        nVar.setShader(radialGradient);
        return true;
    }
}
