package com.jingdong.manto.m.u0;

import android.graphics.Bitmap;
import android.view.View;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.manto.jsapi.coverview.CoverViewContainer;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.utils.MantoDensityUtils;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class e extends com.jingdong.manto.jsapi.base.d {
    private static ByteBuffer a(int[] iArr) {
        byte[] bArr = new byte[iArr.length * 4];
        int i2 = 0;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            int i4 = i2 + 1;
            bArr[i2] = (byte) ((iArr[i3] >> 16) & 255);
            int i5 = i4 + 1;
            bArr[i4] = (byte) ((iArr[i3] >> 8) & 255);
            int i6 = i5 + 1;
            bArr[i5] = (byte) (iArr[i3] & 255);
            i2 = i6 + 1;
            bArr[i6] = (byte) ((iArr[i3] >> 24) & 255);
        }
        return ByteBuffer.wrap(bArr);
    }

    private static Map<String, Object> a(int[] iArr, int i2, int i3) {
        ByteBuffer a = a(iArr);
        HashMap hashMap = new HashMap();
        hashMap.put("data", a);
        hashMap.put("width", Integer.valueOf(i2));
        hashMap.put("height", Integer.valueOf(i3));
        return hashMap;
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        int i3;
        int i4;
        View view;
        float f2;
        int i5;
        String putErrMsg;
        try {
            int i6 = jSONObject.getInt("canvasId");
            if (hVar == null) {
                return;
            }
            com.jingdong.manto.q.n pageView = c0.getPageView(hVar);
            if (pageView != null && pageView.n() != null) {
                View c2 = pageView.n().c(i6);
                if (pageView.i("Canvas") && pageView.a("Canvas", i6) != null) {
                    c2 = pageView.a("Canvas", i6);
                }
                if (c2 == null) {
                    putErrMsg = putErrMsg("fail:view is null");
                } else if (c2 instanceof CoverViewContainer) {
                    View convertTo = ((CoverViewContainer) c2).convertTo(View.class);
                    if (!(convertTo instanceof com.jingdong.manto.widget.canvas.a)) {
                        hVar.a(i2, putErrMsg("fail:illegal view type"));
                        return;
                    }
                    float density = MantoDensityUtils.getDensity(com.jingdong.manto.b.f());
                    int optInt = jSONObject.optInt(JshopConst.JSHOP_PROMOTIO_X);
                    int optInt2 = jSONObject.optInt(JshopConst.JSHOP_PROMOTIO_Y);
                    int optInt3 = jSONObject.optInt("width");
                    int optInt4 = jSONObject.optInt("height");
                    if (optInt3 != 0 && optInt4 != 0) {
                        if (optInt3 < 0) {
                            optInt += optInt3;
                            optInt3 = -optInt3;
                        }
                        int i7 = optInt3;
                        if (optInt4 < 0) {
                            optInt2 += optInt4;
                            optInt4 = -optInt4;
                        }
                        int round = Math.round(optInt * density);
                        int round2 = Math.round(optInt2 * density);
                        int round3 = Math.round(i7 * density);
                        int round4 = Math.round(optInt4 * density);
                        int measuredWidth = convertTo.getMeasuredWidth();
                        int measuredHeight = convertTo.getMeasuredHeight();
                        int i8 = optInt;
                        if (round < 0) {
                            i3 = optInt2;
                            i4 = 0;
                        } else if (round >= measuredWidth) {
                            hVar.a(i2, a(hVar, IMantoBaseModule.SUCCESS, a(new int[i7 * optInt4], i7, optInt4), "canvasGetImageData"));
                            return;
                        } else {
                            i3 = optInt2;
                            i4 = round;
                        }
                        if (round2 < 0) {
                            view = convertTo;
                            f2 = density;
                            i5 = 0;
                        } else if (round2 >= measuredHeight) {
                            hVar.a(i2, a(hVar, IMantoBaseModule.SUCCESS, a(new int[i7 * optInt4], i7, optInt4), "canvasGetImageData"));
                            return;
                        } else {
                            view = convertTo;
                            f2 = density;
                            i5 = round2;
                        }
                        int i9 = round + round3;
                        if (i9 > measuredWidth) {
                            round3 = measuredWidth - i4;
                        } else if (i9 <= 0) {
                            hVar.a(i2, a(hVar, IMantoBaseModule.SUCCESS, a(new int[i7 * optInt4], i7, optInt4), "canvasGetImageData"));
                            return;
                        } else if (round < 0) {
                            round3 = i9;
                        }
                        int i10 = round2 + round4;
                        if (i10 > measuredHeight) {
                            round4 = measuredHeight - i5;
                        } else if (i10 <= 0) {
                            hVar.a(i2, a(hVar, IMantoBaseModule.SUCCESS, a(new int[i7 * optInt4], i7, optInt4), "canvasGetImageData"));
                            return;
                        } else if (round2 < 0) {
                            round4 = i10;
                        }
                        int round5 = Math.round(i4 / f2);
                        int round6 = Math.round(i5 / f2);
                        int round7 = Math.round(round3 / f2);
                        int round8 = Math.round(round4 / f2);
                        try {
                            Bitmap createBitmap = Bitmap.createBitmap(round3, round4, Bitmap.Config.ARGB_8888);
                            m mVar = new m(createBitmap);
                            mVar.save();
                            mVar.translate(-i4, -i5);
                            ((com.jingdong.manto.widget.canvas.a) view).a(mVar);
                            mVar.restore();
                            int[] iArr = new int[i7 * optInt4];
                            Bitmap.createScaledBitmap(createBitmap, round7, round8, false).getPixels(iArr, ((round6 - i3) * i7) + (round5 - i8), i7, 0, 0, round7, round8);
                            hVar.a(i2, a(hVar, IMantoBaseModule.SUCCESS, a(iArr, i7, optInt4), "canvasGetImageData"));
                            return;
                        } catch (Exception unused) {
                            putErrMsg = putErrMsg("fail:create bitmap failed", null, "canvasGetImageData");
                        }
                    }
                    hVar.a(i2, putErrMsg("fail:width or height is 0"));
                    return;
                } else {
                    putErrMsg = putErrMsg("fail:illegal view type");
                }
                hVar.a(i2, putErrMsg);
                return;
            }
            hVar.a(i2, putErrMsg("fail:view is null"));
        } catch (Throwable unused2) {
            hVar.a(i2, putErrMsg("fail:illegal canvasId", null, "canvasGetImageData"));
        }
    }

    @Override // com.jingdong.manto.jsapi.base.d, com.jingdong.manto.m.a
    public String getJsApiName() {
        return "canvasGetImageData";
    }
}
