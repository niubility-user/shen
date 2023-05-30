package com.jingdong.manto.jsapi.coverview;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.react.uimanager.ViewProps;
import com.jd.dynamic.DYConstants;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.l.f;
import com.jingdong.manto.l.h;
import com.jingdong.manto.l.k;
import com.jingdong.manto.t.d;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.manto.widget.e;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public final class a {

    /* renamed from: com.jingdong.manto.jsapi.coverview.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class C0527a implements h {
        final /* synthetic */ com.jingdong.manto.jsapi.coverview.c a;
        final /* synthetic */ MantoCore b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13187c;
        final /* synthetic */ int d;

        C0527a(com.jingdong.manto.jsapi.coverview.c cVar, MantoCore mantoCore, String str, int i2) {
            this.a = cVar;
            this.b = mantoCore;
            this.f13187c = str;
            this.d = i2;
        }

        @Override // com.jingdong.manto.l.h
        public void a() {
            com.jingdong.manto.jsapi.coverview.c cVar = this.a;
            if (cVar != null) {
                cVar.a(this.b, this.f13187c, this.d);
            }
        }

        @Override // com.jingdong.manto.l.a
        public String b() {
            return null;
        }

        @Override // com.jingdong.manto.l.h
        public void b(Bitmap bitmap) {
            com.jingdong.manto.jsapi.coverview.c cVar = this.a;
            if (cVar == null || bitmap == null) {
                return;
            }
            cVar.a(this.b, this.f13187c, bitmap.getWidth(), bitmap.getHeight(), this.d);
        }

        @Override // com.jingdong.manto.l.h
        public void onStart() {
        }
    }

    /* loaded from: classes15.dex */
    class b implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ com.jingdong.manto.widget.b b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ Bitmap f13188c;

        b(String str, com.jingdong.manto.widget.b bVar, Bitmap bitmap) {
            this.a = str;
            this.b = bVar;
            this.f13188c = bitmap;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.a(this.a, this.b, this.f13188c);
        }
    }

    /* loaded from: classes15.dex */
    class c implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ com.jingdong.manto.widget.b b;

        c(String str, com.jingdong.manto.widget.b bVar) {
            this.a = str;
            this.b = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            String str = this.a;
            com.jingdong.manto.widget.b bVar = this.b;
            a.a(str, bVar, bVar.getSourceBitmap());
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(20:3|4|(1:6)|8|(2:9|10)|12|13|(4:(1:15)(2:54|(1:56)(16:57|(1:59)|17|18|19|(1:21)(2:49|(1:51))|22|23|(1:25)|26|27|(1:29)(2:38|(1:40)(6:41|(1:45)(1:43)|44|31|32|33))|30|31|32|33))|31|32|33)|16|17|18|19|(0)(0)|22|23|(0)|26|27|(0)(0)|30) */
    /* JADX WARN: Can't wrap try/catch for region: R(21:3|4|(1:6)|8|9|10|12|13|(4:(1:15)(2:54|(1:56)(16:57|(1:59)|17|18|19|(1:21)(2:49|(1:51))|22|23|(1:25)|26|27|(1:29)(2:38|(1:40)(6:41|(1:45)(1:43)|44|31|32|33))|30|31|32|33))|31|32|33)|16|17|18|19|(0)(0)|22|23|(0)|26|27|(0)(0)|30) */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00c2, code lost:
        if ("break-all".equals(r2) != false) goto L43;
     */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005d A[Catch: JSONException -> 0x0075, TryCatch #1 {JSONException -> 0x0075, blocks: (B:20:0x004f, B:22:0x005d, B:23:0x0065, B:25:0x006d), top: B:54:0x004f }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0065 A[Catch: JSONException -> 0x0075, TryCatch #1 {JSONException -> 0x0075, blocks: (B:20:0x004f, B:22:0x005d, B:23:0x0065, B:25:0x006d), top: B:54:0x004f }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a1 A[Catch: JSONException -> 0x00c7, TryCatch #3 {JSONException -> 0x00c7, blocks: (B:31:0x0093, B:33:0x00a1, B:34:0x00a6, B:35:0x00aa, B:38:0x00b3, B:43:0x00c4, B:41:0x00bc), top: B:58:0x0093 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00aa A[Catch: JSONException -> 0x00c7, TryCatch #3 {JSONException -> 0x00c7, blocks: (B:31:0x0093, B:33:0x00a1, B:34:0x00a6, B:35:0x00aa, B:38:0x00b3, B:43:0x00c4, B:41:0x00bc), top: B:58:0x0093 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void a(TextView textView, JSONObject jSONObject) {
        String string;
        String string2;
        String string3;
        int i2;
        if (textView == null || jSONObject == null) {
            return;
        }
        try {
            String string4 = jSONObject.getString("color");
            if (!TextUtils.isEmpty(string4)) {
                textView.setTextColor(MantoDensityUtils.parseColor(string4));
            }
        } catch (JSONException unused) {
        }
        try {
            textView.setTextSize(0, MantoDensityUtils.convertToDeviceSize((float) jSONObject.getDouble(ViewProps.FONT_SIZE)));
        } catch (JSONException unused2) {
        }
        try {
            string3 = jSONObject.getString(ViewProps.TEXT_ALIGN);
        } catch (JSONException unused3) {
        }
        try {
            if ("left".equals(string3)) {
                i2 = 3;
            } else if (!DYConstants.DY_CENTER.equals(string3)) {
                if ("right".equals(string3)) {
                    i2 = 5;
                }
                string2 = jSONObject.getString(ViewProps.FONT_WEIGHT);
                if (!"bold".equals(string2)) {
                    textView.getPaint().setFakeBoldText(true);
                } else if ("normal".equals(string2)) {
                    textView.getPaint().setFakeBoldText(false);
                }
                int convertToDeviceSize2 = MantoDensityUtils.convertToDeviceSize2(jSONObject, ViewProps.LINE_HEIGHT, Math.round(textView.getTextSize() * 1.2f));
                if (textView instanceof e) {
                    ((e) textView).setLineH(convertToDeviceSize2);
                }
                string = jSONObject.getString("lineBreak");
                if (!"ellipsis".equals(string)) {
                    textView.setEllipsize(TextUtils.TruncateAt.END);
                } else if (!"clip".equals(string)) {
                    if (!"break-word".equals(string)) {
                    }
                    textView.setSingleLine(false);
                    textView.setText(jSONObject.getString("content"));
                    return;
                }
                textView.setSingleLine(true);
                textView.setText(jSONObject.getString("content"));
                return;
            } else {
                i2 = 17;
            }
            textView.setText(jSONObject.getString("content"));
            return;
        } catch (Throwable unused4) {
            return;
        }
        textView.setGravity(i2);
        string2 = jSONObject.getString(ViewProps.FONT_WEIGHT);
        if (!"bold".equals(string2)) {
        }
        int convertToDeviceSize22 = MantoDensityUtils.convertToDeviceSize2(jSONObject, ViewProps.LINE_HEIGHT, Math.round(textView.getTextSize() * 1.2f));
        if (textView instanceof e) {
        }
        string = jSONObject.getString("lineBreak");
        if (!"ellipsis".equals(string)) {
        }
        textView.setSingleLine(true);
    }

    public static void a(MantoCore mantoCore, int i2, String str, com.jingdong.manto.jsapi.coverview.b bVar, com.jingdong.manto.widget.b bVar2, JSONObject jSONObject, com.jingdong.manto.jsapi.coverview.c cVar, String str2, int i3, String str3) {
        String optString;
        Bitmap a;
        if (bVar2 == null || jSONObject == null) {
            return;
        }
        try {
            optString = jSONObject.optString("iconPath");
        } catch (Throwable unused) {
        }
        try {
            if (TextUtils.isEmpty(optString)) {
                if (bVar2 != null) {
                    MantoThreadUtils.runOnUIThread(new c(str3, bVar2));
                    return;
                }
                return;
            }
            bVar2.a();
            if (optString.startsWith("jdfile://")) {
                d g2 = com.jingdong.manto.t.c.g(str, optString);
                if (g2 == null || TextUtils.isEmpty(g2.b)) {
                    a = null;
                } else {
                    String str4 = g2.b;
                    if (!str4.startsWith("file://")) {
                        str4 = "file://" + str4;
                    }
                    a = k.d().a(str4, null);
                }
            } else {
                if (!optString.startsWith("https://") && !optString.startsWith("http://")) {
                    a = f.a(String.valueOf(i2), bVar, optString, mantoCore);
                }
                a = k.d().a(optString, null);
                if (a == null) {
                    k.d().a(bVar2, optString, null, null, new C0527a(cVar, mantoCore, str2, i3), str3);
                    return;
                }
            }
            if (a != null && !a.isRecycled()) {
                MantoThreadUtils.runOnUIThread(new b(str3, bVar2, a));
                if (cVar != null) {
                    cVar.a(mantoCore, str2, a.getWidth(), a.getHeight(), i3);
                    return;
                }
                return;
            }
            bVar2.setImageBitmap(null);
            if (cVar != null) {
                cVar.a(mantoCore, str2, i3);
            }
        } catch (Throwable unused2) {
            if (cVar != null) {
                cVar.a(mantoCore, str2, i3);
            }
        }
    }

    public static void a(String str, ImageView imageView, Bitmap bitmap) {
        ImageView.ScaleType scaleType;
        if (imageView == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        if (imageView instanceof com.jingdong.manto.widget.b) {
            ((com.jingdong.manto.widget.b) imageView).setContentMode(str);
        }
        if (TextUtils.equals("aspectFit", str)) {
            scaleType = ImageView.ScaleType.FIT_CENTER;
        } else if (!TextUtils.equals("aspectFill", str)) {
            if (TextUtils.equals("scaleToFill", str)) {
                scaleType = ImageView.ScaleType.FIT_XY;
            }
            imageView.setImageBitmap(bitmap);
        } else {
            scaleType = ImageView.ScaleType.CENTER_CROP;
        }
        imageView.setScaleType(scaleType);
        imageView.setImageBitmap(bitmap);
    }
}
