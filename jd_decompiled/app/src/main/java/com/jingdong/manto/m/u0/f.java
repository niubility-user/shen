package com.jingdong.manto.m.u0;

import android.graphics.Bitmap;
import android.view.View;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.manto.jsapi.coverview.CoverViewContainer;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.utils.u;
import com.jingdong.manto.widget.canvas.a;
import java.nio.ByteBuffer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class f extends com.jingdong.manto.jsapi.base.d {

    /* loaded from: classes15.dex */
    class a implements a.InterfaceC0691a {
        final /* synthetic */ com.jingdong.manto.h a;
        final /* synthetic */ int b;

        a(com.jingdong.manto.h hVar, int i2) {
            this.a = hVar;
            this.b = i2;
        }

        @Override // com.jingdong.manto.widget.canvas.a.InterfaceC0691a
        public final void a(com.jingdong.manto.widget.canvas.b bVar) {
            this.a.a(this.b, f.this.putErrMsg(IMantoBaseModule.SUCCESS, null, "canvasPutImageData"));
        }
    }

    private static int[] a(ByteBuffer byteBuffer) {
        byte[] a2 = u.a(byteBuffer);
        int length = a2.length / 4;
        int[] iArr = new int[length];
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = i3 + 3;
            int i5 = i3 + 4;
            int i6 = (a2[i3 + 1] & 255) << 8;
            iArr[i2] = ((a2[i3] & 255) << 16) | i6 | (a2[i3 + 2] & 255) | ((a2[i4] & 255) << 24);
            i2++;
            i3 = i5;
        }
        return iArr;
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        String putErrMsg;
        super.exec(hVar, jSONObject, i2, str);
        try {
            int i3 = jSONObject.getInt("canvasId");
            if (hVar == null) {
                return;
            }
            com.jingdong.manto.q.n pageView = c0.getPageView(hVar);
            if (pageView != null && pageView.n() != null) {
                View c2 = pageView.n().c(i3);
                if (pageView.i("Canvas") && pageView.a("Canvas", i3) != null) {
                    c2 = pageView.a("Canvas", i3);
                }
                if (c2 == null) {
                    putErrMsg = putErrMsg("fail:view is null", null, "canvasPutImageData");
                } else if (c2 instanceof CoverViewContainer) {
                    View convertTo = ((CoverViewContainer) c2).convertTo(View.class);
                    if (!(convertTo instanceof com.jingdong.manto.widget.canvas.a)) {
                        hVar.a(i2, putErrMsg("fail:illegal view type", null, "canvasPutImageData"));
                        return;
                    }
                    int optInt = jSONObject.optInt(JshopConst.JSHOP_PROMOTIO_X);
                    int optInt2 = jSONObject.optInt(JshopConst.JSHOP_PROMOTIO_Y);
                    int optInt3 = jSONObject.optInt("width");
                    int optInt4 = jSONObject.optInt("height");
                    if (optInt3 == 0 || optInt4 == 0) {
                        hVar.a(i2, putErrMsg("fail:width or height is 0", null, "canvasPutImageData"));
                        return;
                    }
                    if (optInt3 < 0) {
                        optInt += optInt3;
                        optInt3 = -optInt3;
                    }
                    if (optInt4 < 0) {
                        optInt2 += optInt4;
                        optInt4 = -optInt4;
                    }
                    try {
                        if (u.a(jSONObject)) {
                            u.a(hVar, jSONObject, this);
                        }
                        Object obj = jSONObject.get("data");
                        if (!(obj instanceof ByteBuffer)) {
                            hVar.a(i2, putErrMsg("fail:illegal data", null, "canvasPutImageData"));
                            return;
                        }
                        JSONArray jSONArray = new JSONArray();
                        int[] a2 = a((ByteBuffer) obj);
                        JSONObject jSONObject2 = new JSONObject();
                        try {
                            JSONArray jSONArray2 = new JSONArray();
                            jSONArray2.put(optInt);
                            jSONArray2.put(optInt2);
                            jSONArray2.put(optInt3);
                            jSONArray2.put(optInt4);
                            jSONArray2.put(Bitmap.createBitmap(a2, optInt3, optInt4, Bitmap.Config.ARGB_8888));
                            jSONObject2.put("method", "__setPixels");
                            jSONObject2.put("data", jSONArray2);
                            jSONArray.put(jSONObject2);
                            com.jingdong.manto.widget.canvas.a aVar = (com.jingdong.manto.widget.canvas.a) convertTo;
                            aVar.a(jSONArray, new a(hVar, i2));
                            aVar.a();
                            return;
                        } catch (JSONException unused) {
                            hVar.a(i2, putErrMsg("fail:build action JSON error", null, "canvasPutImageData"));
                            return;
                        }
                    } catch (JSONException unused2) {
                        putErrMsg = putErrMsg("fail:missing data", null, "canvasPutImageData");
                    }
                } else {
                    putErrMsg = putErrMsg("fail:illegal view type", null, "canvasPutImageData");
                }
                hVar.a(i2, putErrMsg);
                return;
            }
            hVar.a(i2, putErrMsg("fail:view is null", null, "canvasPutImageData"));
        } catch (Throwable unused3) {
            hVar.a(i2, putErrMsg("fail:illegal canvasId", null, "canvasPutImageData"));
        }
    }

    @Override // com.jingdong.manto.jsapi.base.d, com.jingdong.manto.m.a
    public String getJsApiName() {
        return "canvasPutImageData";
    }
}
