package com.tencent.mapsdk.internal;

import android.text.TextUtils;
import com.facebook.cache.disk.DefaultDiskStorage;
import com.jd.dynamic.DYConstants;
import com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider;
import com.tencent.map.sdk.utilities.visualization.glmodel.GLModelOverlayProvider;
import com.tencent.map.tools.json.JsonUtils;
import com.tencent.map.tools.net.NetManager;
import com.tencent.map.tools.net.NetResponse;
import com.tencent.mapsdk.internal.f4;
import com.tencent.mapsdk.internal.g4;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import java.io.File;
import java.util.List;

/* loaded from: classes9.dex */
public class lh implements kh {
    @Override // com.tencent.mapsdk.internal.kh
    public BaseOverlayProvider a(f4 f4Var) {
        GLModelOverlayProvider.AnimationType animationType;
        if ((f4Var instanceof g4) && f4Var.a()) {
            g4 g4Var = (g4) f4Var;
            f4.a.C0792a.c cVar = (f4.a.C0792a.c) g4Var.b.b.b.get(0);
            if (TextUtils.isEmpty(cVar.b)) {
                return null;
            }
            LatLng latLng = g4Var.b.f16589c.f16590c.a.a.a;
            GLModelOverlayProvider gLModelOverlayProvider = new GLModelOverlayProvider(cVar.b, new LatLng(latLng.latitude, latLng.longitude, latLng.altitude));
            List<Double> list = g4Var.b.f16589c.f16590c.a.a.b;
            if (list != null && list.size() == 3) {
                gLModelOverlayProvider.rotationX(list.get(0).floatValue() + 90.0f).rotationY(list.get(1).floatValue()).rotationZ(list.get(2).floatValue());
            }
            gLModelOverlayProvider.latLngBounds(g4Var.b.f16589c.f16590c.a.a.f16593f);
            if (g4Var.b.f16589c.f16590c.a.a.d) {
                gLModelOverlayProvider.coordType(GLModelOverlayProvider.CoordType.GeoGraphicType);
                gLModelOverlayProvider.scale(g4Var.b.f16589c.f16590c.a.a.f16591c);
            } else {
                gLModelOverlayProvider.coordType(GLModelOverlayProvider.CoordType.PixelType);
                List<Integer> list2 = g4Var.b.f16589c.f16590c.a.a.f16592e;
                if (list2 != null && list2.size() == 2) {
                    gLModelOverlayProvider.pixelBounds(list2.get(0).intValue(), list2.get(1).intValue());
                }
            }
            int i2 = g4Var.b.f16589c.f16590c.a.a.f16594g.a;
            if (i2 != 0) {
                if (i2 == 1) {
                    animationType = GLModelOverlayProvider.AnimationType.FlattenRise;
                }
                gLModelOverlayProvider.setExposure((float) g4Var.b.f16589c.f16590c.a.a.f16595h);
                g4.a.C0795a.C0796a c0796a = g4Var.b.f16589c.d;
                gLModelOverlayProvider.zoomRange(c0796a.f16500f, c0796a.f16499e);
                gLModelOverlayProvider.zIndex(g4Var.b.f16589c.d.b);
                gLModelOverlayProvider.displayLevel(g4Var.b.f16589c.d.a);
                gLModelOverlayProvider.opacity((float) g4Var.b.f16589c.d.d);
                gLModelOverlayProvider.visibility(!g4Var.b.f16589c.d.f16498c);
                return gLModelOverlayProvider;
            }
            animationType = GLModelOverlayProvider.AnimationType.None;
            gLModelOverlayProvider.animateType(animationType);
            gLModelOverlayProvider.setExposure((float) g4Var.b.f16589c.f16590c.a.a.f16595h);
            g4.a.C0795a.C0796a c0796a2 = g4Var.b.f16589c.d;
            gLModelOverlayProvider.zoomRange(c0796a2.f16500f, c0796a2.f16499e);
            gLModelOverlayProvider.zIndex(g4Var.b.f16589c.d.b);
            gLModelOverlayProvider.displayLevel(g4Var.b.f16589c.d.a);
            gLModelOverlayProvider.opacity((float) g4Var.b.f16589c.d.d);
            gLModelOverlayProvider.visibility(!g4Var.b.f16589c.d.f16498c);
            return gLModelOverlayProvider;
        }
        return null;
    }

    @Override // com.tencent.mapsdk.internal.kh
    public f4 a(f4 f4Var, String str) {
        String str2;
        f4.a.C0792a.c cVar;
        String str3;
        boolean z = f4Var instanceof g4;
        g4 g4Var = f4Var;
        if (z) {
            g4 g4Var2 = (g4) f4Var;
            f4.a.C0792a.c cVar2 = (f4.a.C0792a.c) g4Var2.b.b.b.get(0);
            String str4 = str + "/model/";
            ma.a(la.x, "# 2\u6b21\u5904\u7406\u6570\u636e\u7f13\u5b58\u6839\u76ee\u5f55: [" + str4 + "]");
            StringBuilder sb = new StringBuilder();
            sb.append(str4);
            sb.append(cVar2.f16495h);
            String sb2 = sb.toString();
            File file = new File(sb2, cVar2.f16494g);
            ma.a(la.x, "# 2\u6b21\u5904\u7406\u6570\u636e\u7f13\u5b58\u76ee\u6807\u6587\u4ef6: [" + file.getAbsolutePath() + "]");
            if (file.exists()) {
                str2 = "2\u6b21\u5904\u7406\u6570\u636e\u5df2\u5b58\u5728\u7f13\u5b58\u4e2d";
            } else {
                if (!TextUtils.isEmpty(cVar2.d) && !cVar2.d.equals(DYConstants.DY_NULL_STR)) {
                    cVar2.f16491c = cVar2.d;
                }
                ma.a(la.x, "2\u6b21\u5904\u7406\u6570\u636e\u8bf7\u6c42url: [" + cVar2.f16491c + "]");
                NetResponse doGet = NetManager.getInstance().builder().url(cVar2.f16491c).doGet();
                if (doGet.available()) {
                    fa.e(new File(str4));
                    byte[] bArr = doGet.data;
                    File file2 = new File(str4, cVar2.f16493f + DefaultDiskStorage.FileType.TEMP);
                    fa.b(file2, bArr);
                    ia.b(file2, sb2);
                    fa.d(file2);
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("# 2\u6b21\u4e0b\u8f7d\u5904\u7406\u6570\u636e\u5927\u5c0f: {");
                    sb3.append(cVar2.f16493f);
                    sb3.append("} [");
                    byte[] bArr2 = doGet.data;
                    sb3.append(bArr2 != null ? bArr2.length : 0);
                    sb3.append("]");
                    str2 = sb3.toString();
                } else {
                    ma.a(la.x, "2\u6b21\u5904\u7406\u6570\u636e\u8bf7\u6c42\u5931\u8d25");
                    cVar = (f4.a.C0792a.c) g4Var2.b.b.b.get(0);
                    str3 = "";
                    cVar.b = str3;
                    g4Var = g4Var2;
                }
            }
            ma.a(la.x, str2);
            cVar = (f4.a.C0792a.c) g4Var2.b.b.b.get(0);
            str3 = file.getAbsolutePath();
            cVar.b = str3;
            g4Var = g4Var2;
        }
        return g4Var;
    }

    @Override // com.tencent.mapsdk.internal.kh
    public f4 a(byte[] bArr) {
        return (f4) JsonUtils.parseToModel(new String(bArr), g4.class, new Object[0]);
    }
}
