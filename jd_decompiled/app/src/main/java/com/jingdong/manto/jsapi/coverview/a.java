package com.jingdong.manto.jsapi.coverview;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.l.f;
import com.jingdong.manto.l.h;
import com.jingdong.manto.l.k;
import com.jingdong.manto.t.d;
import com.jingdong.manto.utils.MantoThreadUtils;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(android.widget.TextView r4, org.json.JSONObject r5) {
        /*
            if (r4 == 0) goto Ld0
            if (r5 == 0) goto Ld0
            java.lang.String r0 = "color"
            java.lang.String r0 = r5.getString(r0)     // Catch: org.json.JSONException -> L17
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch: org.json.JSONException -> L17
            if (r1 != 0) goto L17
            int r0 = com.jingdong.manto.utils.MantoDensityUtils.parseColor(r0)     // Catch: org.json.JSONException -> L17
            r4.setTextColor(r0)     // Catch: org.json.JSONException -> L17
        L17:
            r0 = 0
            java.lang.String r1 = "fontSize"
            double r1 = r5.getDouble(r1)     // Catch: org.json.JSONException -> L26
            float r1 = (float) r1     // Catch: org.json.JSONException -> L26
            float r1 = com.jingdong.manto.utils.MantoDensityUtils.convertToDeviceSize(r1)     // Catch: org.json.JSONException -> L26
            r4.setTextSize(r0, r1)     // Catch: org.json.JSONException -> L26
        L26:
            java.lang.String r1 = "textAlign"
            java.lang.String r1 = r5.getString(r1)     // Catch: org.json.JSONException -> L4e
            java.lang.String r2 = "left"
            boolean r2 = r2.equals(r1)     // Catch: org.json.JSONException -> L4e
            if (r2 == 0) goto L39
            r1 = 3
        L35:
            r4.setGravity(r1)     // Catch: org.json.JSONException -> L4e
            goto L4e
        L39:
            java.lang.String r2 = "center"
            boolean r2 = r2.equals(r1)     // Catch: org.json.JSONException -> L4e
            if (r2 == 0) goto L44
            r1 = 17
            goto L35
        L44:
            java.lang.String r2 = "right"
            boolean r1 = r2.equals(r1)     // Catch: org.json.JSONException -> L4e
            if (r1 == 0) goto L4e
            r1 = 5
            goto L35
        L4e:
            r1 = 1
            java.lang.String r2 = "fontWeight"
            java.lang.String r2 = r5.getString(r2)     // Catch: org.json.JSONException -> L75
            java.lang.String r3 = "bold"
            boolean r3 = r3.equals(r2)     // Catch: org.json.JSONException -> L75
            if (r3 == 0) goto L65
            android.text.TextPaint r2 = r4.getPaint()     // Catch: org.json.JSONException -> L75
            r2.setFakeBoldText(r1)     // Catch: org.json.JSONException -> L75
            goto L76
        L65:
            java.lang.String r3 = "normal"
            boolean r2 = r3.equals(r2)     // Catch: org.json.JSONException -> L75
            if (r2 == 0) goto L76
            android.text.TextPaint r2 = r4.getPaint()     // Catch: org.json.JSONException -> L75
            r2.setFakeBoldText(r0)     // Catch: org.json.JSONException -> L75
            goto L76
        L75:
        L76:
            float r2 = r4.getTextSize()
            r3 = 1067030938(0x3f99999a, float:1.2)
            float r2 = r2 * r3
            int r2 = java.lang.Math.round(r2)
            java.lang.String r3 = "lineHeight"
            int r2 = com.jingdong.manto.utils.MantoDensityUtils.convertToDeviceSize2(r5, r3, r2)
            boolean r3 = r4 instanceof com.jingdong.manto.widget.e
            if (r3 == 0) goto L93
            r3 = r4
            com.jingdong.manto.widget.e r3 = (com.jingdong.manto.widget.e) r3
            r3.setLineH(r2)
        L93:
            java.lang.String r2 = "lineBreak"
            java.lang.String r2 = r5.getString(r2)     // Catch: org.json.JSONException -> Lc7
            java.lang.String r3 = "ellipsis"
            boolean r3 = r3.equals(r2)     // Catch: org.json.JSONException -> Lc7
            if (r3 == 0) goto Laa
            android.text.TextUtils$TruncateAt r0 = android.text.TextUtils.TruncateAt.END     // Catch: org.json.JSONException -> Lc7
            r4.setEllipsize(r0)     // Catch: org.json.JSONException -> Lc7
        La6:
            r4.setSingleLine(r1)     // Catch: org.json.JSONException -> Lc7
            goto Lc7
        Laa:
            java.lang.String r3 = "clip"
            boolean r3 = r3.equals(r2)     // Catch: org.json.JSONException -> Lc7
            if (r3 == 0) goto Lb3
            goto La6
        Lb3:
            java.lang.String r1 = "break-word"
            boolean r1 = r1.equals(r2)     // Catch: org.json.JSONException -> Lc7
            if (r1 == 0) goto Lbc
            goto Lc4
        Lbc:
            java.lang.String r1 = "break-all"
            boolean r1 = r1.equals(r2)     // Catch: org.json.JSONException -> Lc7
            if (r1 == 0) goto Lc7
        Lc4:
            r4.setSingleLine(r0)     // Catch: org.json.JSONException -> Lc7
        Lc7:
            java.lang.String r0 = "content"
            java.lang.String r5 = r5.getString(r0)     // Catch: java.lang.Throwable -> Ld0
            r4.setText(r5)     // Catch: java.lang.Throwable -> Ld0
        Ld0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.jsapi.coverview.a.a(android.widget.TextView, org.json.JSONObject):void");
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
