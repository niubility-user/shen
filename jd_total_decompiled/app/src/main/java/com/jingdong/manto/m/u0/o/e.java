package com.jingdong.manto.m.u0.o;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.b;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class e implements i {

    /* loaded from: classes15.dex */
    public class a implements b.d {
        a(e eVar) {
        }

        @Override // com.jingdong.manto.utils.b.d
        public final void a() {
        }
    }

    /* loaded from: classes15.dex */
    public class b implements b.d {
        b(e eVar) {
        }

        @Override // com.jingdong.manto.utils.b.d
        public final void a() {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:171:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, int i2, String str, float f2, float f3, float f4, float f5, int i3, int i4, int i5, int i6) {
        float f6;
        float f7;
        int i7;
        int i8;
        int i9;
        Bitmap bitmap;
        float f8 = f4;
        float f9 = f5;
        int i10 = i5;
        int i11 = i6;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (i2 < 5) {
            f6 = f2;
        } else if (f8 == 0.0f || f9 == 0.0f) {
            return true;
        } else {
            if (f8 < 0.0f) {
                f6 = f2 + f8;
                f8 = -f8;
            } else {
                f6 = f2;
            }
            if (f9 < 0.0f) {
                f7 = f3 + f9;
                f9 = -f9;
                float f10 = f6 + f8;
                float f11 = f7 + f9;
                if (i2 >= 9) {
                    bitmap = cVar.b.a(cVar.f13728c.h(), str, new a(this));
                    if (bitmap == null || bitmap.isRecycled()) {
                        return false;
                    }
                    if (f8 == 0.0f) {
                        f10 = MantoDensityUtils.convertToDeviceSizeByInt(bitmap.getWidth()) + f6;
                    }
                    if (f9 == 0.0f) {
                        f11 = MantoDensityUtils.convertToDeviceSizeByInt(bitmap.getHeight()) + f7;
                    }
                } else if (i10 == 0 || i11 == 0) {
                    return true;
                } else {
                    if (i10 < 0) {
                        i7 = i3 + i10;
                        i10 = -i10;
                    } else {
                        i7 = i3;
                    }
                    if (i11 < 0) {
                        i8 = i4 + i11;
                        i11 = -i11;
                    } else {
                        i8 = i4;
                    }
                    int i12 = i7 + i10;
                    if (i12 <= 0 || (i9 = i8 + i11) <= 0) {
                        return true;
                    }
                    float f12 = f7;
                    Bitmap a2 = cVar.b.a(cVar.f13728c.h(), str, new Rect(i7 > 0 ? i7 : 0, i8 > 0 ? i8 : 0, i12, i9), new b(this));
                    if (a2 == null || a2.isRecycled()) {
                        return false;
                    }
                    int convertToDeviceSizeByInt = MantoDensityUtils.convertToDeviceSizeByInt(i7);
                    int convertToDeviceSizeByInt2 = MantoDensityUtils.convertToDeviceSizeByInt(i8);
                    float convertToDeviceSizeByInt3 = f8 / MantoDensityUtils.convertToDeviceSizeByInt(i10);
                    float convertToDeviceSizeByInt4 = f9 / MantoDensityUtils.convertToDeviceSizeByInt(i11);
                    f6 += (MantoDensityUtils.convertToDeviceSizeByInt(r15) - convertToDeviceSizeByInt) * convertToDeviceSizeByInt3;
                    f7 = f12 + ((MantoDensityUtils.convertToDeviceSizeByInt(r10) - convertToDeviceSizeByInt2) * convertToDeviceSizeByInt4);
                    f10 = f6 + (MantoDensityUtils.convertToDeviceSizeByInt(a2.getWidth()) * convertToDeviceSizeByInt3);
                    f11 = (MantoDensityUtils.convertToDeviceSizeByInt(a2.getHeight()) * convertToDeviceSizeByInt4) + f7;
                    bitmap = a2;
                }
                canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
                canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new RectF(f6, f7, f10, f11), cVar.f13730f);
                return true;
            }
        }
        f7 = f3;
        float f102 = f6 + f8;
        float f112 = f7 + f9;
        if (i2 >= 9) {
        }
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
        canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new RectF(f6, f7, f102, f112), cVar.f13730f);
        return true;
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "drawImage";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.b bVar = (com.jingdong.manto.m.u0.o.k0.b) cVar2;
        if (bVar == null) {
            return false;
        }
        return a(cVar, canvas, bVar.b, bVar.f13749c, bVar.d, bVar.f13750e, bVar.f13751f, bVar.f13752g, bVar.f13753h, bVar.f13754i, bVar.f13755j, bVar.f13756k);
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        int length = jSONArray.length();
        if (length < 3) {
            return false;
        }
        return a(cVar, canvas, length, jSONArray.optString(0), MantoDensityUtils.convertToDeviceSize2(jSONArray, 1), MantoDensityUtils.convertToDeviceSize2(jSONArray, 2), MantoDensityUtils.convertToDeviceSize2(jSONArray, 3), MantoDensityUtils.convertToDeviceSize2(jSONArray, 4), jSONArray.optInt(5), jSONArray.optInt(6), jSONArray.optInt(7), jSONArray.optInt(8));
    }
}
