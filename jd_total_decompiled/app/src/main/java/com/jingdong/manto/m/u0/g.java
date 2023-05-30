package com.jingdong.manto.m.u0;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.manto.jsapi.coverview.CoverViewContainer;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoUtils;
import java.io.File;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class g extends d0 {

    /* loaded from: classes15.dex */
    class a implements Runnable {
        final /* synthetic */ com.jingdong.manto.h a;
        final /* synthetic */ int b;

        /* renamed from: c */
        final /* synthetic */ JSONObject f13735c;

        a(com.jingdong.manto.h hVar, int i2, JSONObject jSONObject) {
            g.this = r1;
            this.a = hVar;
            this.b = i2;
            this.f13735c = jSONObject;
        }

        @Override // java.lang.Runnable
        public final void run() {
            int i2;
            int i3;
            int i4;
            int i5;
            a aVar;
            com.jingdong.manto.h hVar;
            int i6;
            g gVar;
            String str;
            a aVar2 = this;
            com.jingdong.manto.q.n pageView = c0.getPageView(aVar2.a);
            if (!aVar2.a.f() || pageView == null || pageView.n() == null) {
                aVar2.a.a(aVar2.b, g.this.putErrMsg("fail"));
                return;
            }
            try {
                int i7 = aVar2.f13735c.getInt("canvasId");
                View c2 = pageView.n().c(i7);
                if (pageView.i("Canvas") && pageView.a("Canvas", i7) != null) {
                    c2 = pageView.a("Canvas", i7);
                }
                String optString = aVar2.f13735c.optString("type", "");
                if (c2 == null) {
                    hVar = aVar2.a;
                    i6 = aVar2.b;
                    gVar = g.this;
                    str = "fail:get canvas by canvasId failed";
                } else if (!(c2 instanceof CoverViewContainer)) {
                    hVar = aVar2.a;
                    i6 = aVar2.b;
                    gVar = g.this;
                    str = "fail:the view is not a instance of CoverViewContainer";
                } else if (TextUtils.equals(optString, "2d")) {
                    View convertTo = ((CoverViewContainer) c2).convertTo(View.class);
                    if (convertTo == null) {
                        aVar2.a.a(aVar2.b, g.this.putErrMsg("fail:target view is null."));
                        return;
                    }
                    int measuredWidth = convertTo.getMeasuredWidth();
                    int measuredHeight = convertTo.getMeasuredHeight();
                    String optString2 = aVar2.f13735c.optString("canvasId");
                    Bitmap a = com.jingdong.manto.m.u1.f.a(aVar2.a.c() + optString2).a(Math.round(MantoDensityUtils.dip2pixel((float) aVar2.f13735c.optDouble(JshopConst.JSHOP_PROMOTIO_X))), Math.round(MantoDensityUtils.dip2pixel((float) aVar2.f13735c.optDouble(JshopConst.JSHOP_PROMOTIO_Y))), Math.round(MantoDensityUtils.dip2pixel((float) aVar2.f13735c.optDouble("width", measuredWidth))), Math.round(MantoDensityUtils.dip2pixel((float) aVar2.f13735c.optDouble("height", measuredHeight))));
                    if (a == null) {
                        aVar2.a.a(aVar2.b, g.this.putErrMsg("fail:bitmap is null."));
                        return;
                    }
                    Bitmap.CompressFormat c3 = g.c(aVar2.f13735c);
                    String str2 = c3 == Bitmap.CompressFormat.JPEG ? "jpg" : "png";
                    String absolutePath = new File(new File(com.jingdong.manto.utils.n.f14314c + "canvas"), "canvas_" + System.currentTimeMillis() + OrderISVUtil.MONEY_DECIMAL + str2).getAbsolutePath();
                    if (c3 == Bitmap.CompressFormat.JPEG) {
                        com.jingdong.manto.sdk.b.a(a, g.d(aVar2.f13735c), c3, absolutePath, true);
                    } else {
                        com.jingdong.manto.sdk.b.a(a, 70, c3, absolutePath, true);
                    }
                    com.jingdong.manto.t.d a2 = com.jingdong.manto.t.c.a(aVar2.a.c(), absolutePath, false);
                    if (a2 == null) {
                        aVar2.a.a(aVar2.b, g.this.putErrMsg("fail:cast to tmp file failed"));
                        return;
                    }
                    HashMap hashMap = new HashMap();
                    hashMap.put("tempFilePath", a2.a);
                    aVar2.a.a(aVar2.b, g.this.putErrMsg(IMantoBaseModule.SUCCESS, hashMap, "canvasToTempFilePath"));
                    if (a != null) {
                        a.recycle();
                        return;
                    }
                    return;
                } else {
                    View convertTo2 = ((CoverViewContainer) c2).convertTo(View.class);
                    if (convertTo2 == null) {
                        aVar2.a.a(aVar2.b, g.this.putErrMsg("fail:target view is null."));
                        return;
                    }
                    int measuredWidth2 = convertTo2.getMeasuredWidth();
                    int measuredHeight2 = convertTo2.getMeasuredHeight();
                    try {
                        Bitmap createBitmap = Bitmap.createBitmap(measuredWidth2, measuredHeight2, Bitmap.Config.ARGB_8888);
                        float dip2pixel = MantoDensityUtils.dip2pixel((float) aVar2.f13735c.optDouble(JshopConst.JSHOP_PROMOTIO_X));
                        float dip2pixel2 = MantoDensityUtils.dip2pixel((float) aVar2.f13735c.optDouble(JshopConst.JSHOP_PROMOTIO_Y));
                        float dip2pixel3 = MantoDensityUtils.dip2pixel((float) aVar2.f13735c.optDouble("width", measuredWidth2));
                        float dip2pixel4 = MantoDensityUtils.dip2pixel((float) aVar2.f13735c.optDouble("height", measuredHeight2));
                        float f2 = measuredWidth2;
                        if (dip2pixel + dip2pixel3 > f2) {
                            dip2pixel3 = f2 - dip2pixel;
                        }
                        float f3 = measuredHeight2;
                        if (dip2pixel2 + dip2pixel4 > f3) {
                            dip2pixel4 = f3 - dip2pixel2;
                        }
                        float round = Math.round(MantoUtils.getFloat(aVar2.f13735c.optString("destWidth"), dip2pixel3));
                        float round2 = Math.round(MantoUtils.getFloat(aVar2.f13735c.optString("destHeight"), dip2pixel4));
                        int i8 = (int) dip2pixel;
                        if (i8 >= 0 && (i2 = (int) dip2pixel2) >= 0 && (i3 = (int) dip2pixel3) > 0) {
                            int i9 = (int) dip2pixel4;
                            if (i9 > 0 && ((int) (dip2pixel + dip2pixel3)) <= measuredWidth2 && ((int) (dip2pixel2 + dip2pixel4)) <= measuredHeight2 && (i4 = (int) round) > 0 && (i5 = (int) round2) > 0) {
                                try {
                                    m mVar = new m(createBitmap);
                                    if (convertTo2 instanceof com.jingdong.manto.widget.canvas.a) {
                                        ((com.jingdong.manto.widget.canvas.a) convertTo2).a(mVar);
                                    } else {
                                        convertTo2.draw(mVar);
                                    }
                                    if (dip2pixel3 != f2 || dip2pixel4 != f3) {
                                        Bitmap createBitmap2 = Bitmap.createBitmap(createBitmap, i8, i2, i3, i9, (Matrix) null, false);
                                        createBitmap.recycle();
                                        createBitmap = createBitmap2;
                                    }
                                    if (dip2pixel3 == round && dip2pixel4 == round2) {
                                        aVar = this;
                                    } else {
                                        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(createBitmap, i4, i5, false);
                                        createBitmap.recycle();
                                        aVar = this;
                                        createBitmap = createScaledBitmap;
                                    }
                                    Bitmap.CompressFormat c4 = g.c(aVar.f13735c);
                                    String str3 = c4 == Bitmap.CompressFormat.JPEG ? "jpg" : "png";
                                    String absolutePath2 = new File(new File(com.jingdong.manto.utils.n.f14314c + "canvas"), "canvas_" + System.currentTimeMillis() + OrderISVUtil.MONEY_DECIMAL + str3).getAbsolutePath();
                                    if (c4 == Bitmap.CompressFormat.JPEG) {
                                        com.jingdong.manto.sdk.b.a(createBitmap, g.d(aVar.f13735c), c4, absolutePath2, true);
                                    } else {
                                        com.jingdong.manto.sdk.b.a(createBitmap, 70, c4, absolutePath2, true);
                                    }
                                    com.jingdong.manto.t.d a3 = com.jingdong.manto.t.c.a(aVar.a.c(), absolutePath2, false);
                                    if (a3 == null) {
                                        aVar.a.a(aVar.b, g.this.putErrMsg("fail:cast to tmp file failed"));
                                        return;
                                    }
                                    HashMap hashMap2 = new HashMap();
                                    hashMap2.put("tempFilePath", a3.a);
                                    aVar.a.a(aVar.b, g.this.putErrMsg(IMantoBaseModule.SUCCESS, hashMap2, "canvasToTempFilePath"));
                                    mVar.a();
                                    if (createBitmap != null) {
                                        createBitmap.recycle();
                                        return;
                                    }
                                    return;
                                } catch (Throwable unused) {
                                    aVar2 = this;
                                    aVar2.a.a(aVar2.b, g.this.putErrMsg("fail:create bitmap failed..."));
                                    System.gc();
                                    return;
                                }
                            }
                            aVar2 = this;
                        }
                        aVar2.a.a(aVar2.b, g.this.putErrMsg("fail:illegal arguments"));
                        return;
                    } catch (Throwable unused2) {
                    }
                }
                hVar.a(i6, gVar.putErrMsg(str));
            } catch (Throwable unused3) {
                aVar2.a.a(aVar2.b, g.this.putErrMsg("fail:canvasId do not exist"));
            }
        }
    }

    public static Bitmap.CompressFormat c(JSONObject jSONObject) {
        return "jpg".equalsIgnoreCase(jSONObject.optString("fileType")) ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG;
    }

    public static int d(JSONObject jSONObject) {
        float optDouble = (float) jSONObject.optDouble("quality", 1.0d);
        float f2 = 100.0f;
        if (optDouble < 0.0f) {
            f2 = 0.0f;
        } else if (optDouble <= 1.0f) {
            f2 = 100.0f * optDouble;
        }
        return (int) f2;
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        super.exec(hVar, jSONObject, i2, str);
        com.jingdong.manto.b.d().networkIO().execute(new a(hVar, i2, jSONObject));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "canvasToTempFilePath";
    }
}
