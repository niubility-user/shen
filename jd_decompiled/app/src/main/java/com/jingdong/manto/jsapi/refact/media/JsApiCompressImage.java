package com.jingdong.manto.jsapi.refact.media;

import android.text.TextUtils;
import android.util.Pair;
import com.jingdong.manto.b;
import com.jingdong.manto.h;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.m.e0;
import com.jingdong.manto.pkg.b.g;
import com.jingdong.manto.t.c;
import com.jingdong.manto.t.d;
import com.jingdong.manto.utils.MantoStringUtils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class JsApiCompressImage extends d0 {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public static abstract class IImageCompress {
        IImageCompress() {
        }

        abstract Pair<Boolean, String> compress(e0 e0Var, String str, int i2);

        /* JADX WARN: Removed duplicated region for block: B:59:0x007a A[Catch: Exception -> 0x00b7, TryCatch #0 {Exception -> 0x00b7, blocks: (B:40:0x0003, B:42:0x000e, B:44:0x0018, B:46:0x0023, B:49:0x002c, B:52:0x0036, B:54:0x0063, B:56:0x006d, B:57:0x0074, B:59:0x007a, B:60:0x007d, B:62:0x0088, B:64:0x008f, B:66:0x0097, B:68:0x00a1, B:70:0x00ab, B:51:0x0031), top: B:73:0x0003 }] */
        /* JADX WARN: Removed duplicated region for block: B:62:0x0088 A[Catch: Exception -> 0x00b7, TryCatch #0 {Exception -> 0x00b7, blocks: (B:40:0x0003, B:42:0x000e, B:44:0x0018, B:46:0x0023, B:49:0x002c, B:52:0x0036, B:54:0x0063, B:56:0x006d, B:57:0x0074, B:59:0x007a, B:60:0x007d, B:62:0x0088, B:64:0x008f, B:66:0x0097, B:68:0x00a1, B:70:0x00ab, B:51:0x0031), top: B:73:0x0003 }] */
        /* JADX WARN: Removed duplicated region for block: B:70:0x00ab A[Catch: Exception -> 0x00b7, TRY_LEAVE, TryCatch #0 {Exception -> 0x00b7, blocks: (B:40:0x0003, B:42:0x000e, B:44:0x0018, B:46:0x0023, B:49:0x002c, B:52:0x0036, B:54:0x0063, B:56:0x006d, B:57:0x0074, B:59:0x007a, B:60:0x007d, B:62:0x0088, B:64:0x008f, B:66:0x0097, B:68:0x00a1, B:70:0x00ab, B:51:0x0031), top: B:73:0x0003 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        protected android.util.Pair<java.lang.Boolean, java.lang.String> innerCompress(java.io.InputStream r9, int r10, java.lang.String r11) {
            /*
                r8 = this;
                java.lang.String r0 = "jpg"
                r1 = 0
                android.graphics.BitmapFactory$Options r2 = new android.graphics.BitmapFactory$Options     // Catch: java.lang.Exception -> Lb7
                r2.<init>()     // Catch: java.lang.Exception -> Lb7
                android.graphics.Bitmap r3 = android.graphics.BitmapFactory.decodeStream(r9, r1, r2)     // Catch: java.lang.Exception -> Lb7
                if (r3 != 0) goto L18
                android.util.Pair r9 = new android.util.Pair     // Catch: java.lang.Exception -> Lb7
                java.lang.Boolean r10 = java.lang.Boolean.FALSE     // Catch: java.lang.Exception -> Lb7
                java.lang.String r11 = "compress fail:decode image fail"
                r9.<init>(r10, r11)     // Catch: java.lang.Exception -> Lb7
                return r9
            L18:
                java.lang.String r2 = com.jingdong.manto.m.f1.b.a(r2)     // Catch: java.lang.Exception -> Lb7
                int r4 = r2.indexOf(r0)     // Catch: java.lang.Exception -> Lb7
                r5 = -1
                if (r4 > r5) goto L31
                java.lang.String r4 = "jpeg"
                int r2 = r2.indexOf(r4)     // Catch: java.lang.Exception -> Lb7
                if (r2 <= r5) goto L2c
                goto L31
            L2c:
                android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.PNG     // Catch: java.lang.Exception -> Lb7
                java.lang.String r2 = "png"
                goto L36
            L31:
                android.graphics.Bitmap$CompressFormat r2 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch: java.lang.Exception -> Lb7
                r7 = r2
                r2 = r0
                r0 = r7
            L36:
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lb7
                r4.<init>()     // Catch: java.lang.Exception -> Lb7
                java.lang.String r5 = com.jingdong.manto.utils.n.b     // Catch: java.lang.Exception -> Lb7
                r4.append(r5)     // Catch: java.lang.Exception -> Lb7
                java.lang.String r5 = "/photo/mantoMsg.tmp."
                r4.append(r5)     // Catch: java.lang.Exception -> Lb7
                long r5 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> Lb7
                r4.append(r5)     // Catch: java.lang.Exception -> Lb7
                java.lang.String r5 = "."
                r4.append(r5)     // Catch: java.lang.Exception -> Lb7
                r4.append(r2)     // Catch: java.lang.Exception -> Lb7
                java.lang.String r2 = r4.toString()     // Catch: java.lang.Exception -> Lb7
                java.io.File r4 = new java.io.File     // Catch: java.lang.Exception -> Lb7
                r4.<init>(r2)     // Catch: java.lang.Exception -> Lb7
                java.io.File r5 = r4.getParentFile()     // Catch: java.lang.Exception -> Lb7
                if (r5 == 0) goto L74
                java.io.File r5 = r4.getParentFile()     // Catch: java.lang.Exception -> Lb7
                boolean r5 = r5.exists()     // Catch: java.lang.Exception -> Lb7
                if (r5 != 0) goto L74
                java.io.File r5 = r4.getParentFile()     // Catch: java.lang.Exception -> Lb7
                r5.mkdirs()     // Catch: java.lang.Exception -> Lb7
            L74:
                boolean r5 = r4.exists()     // Catch: java.lang.Exception -> Lb7
                if (r5 != 0) goto L7d
                r4.createNewFile()     // Catch: java.lang.Exception -> Lb7
            L7d:
                java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch: java.lang.Exception -> Lb7
                r5.<init>(r4)     // Catch: java.lang.Exception -> Lb7
                boolean r10 = r3.compress(r0, r10, r5)     // Catch: java.lang.Exception -> Lb7
                if (r10 == 0) goto Lab
                r9 = 0
                com.jingdong.manto.t.d r9 = com.jingdong.manto.t.c.a(r11, r2, r9)     // Catch: java.lang.Exception -> Lb7
                if (r9 == 0) goto La1
                java.lang.String r10 = r9.a     // Catch: java.lang.Exception -> Lb7
                boolean r10 = android.text.TextUtils.isEmpty(r10)     // Catch: java.lang.Exception -> Lb7
                if (r10 != 0) goto La1
                android.util.Pair r10 = new android.util.Pair     // Catch: java.lang.Exception -> Lb7
                java.lang.Boolean r11 = java.lang.Boolean.TRUE     // Catch: java.lang.Exception -> Lb7
                java.lang.String r9 = r9.a     // Catch: java.lang.Exception -> Lb7
                r10.<init>(r11, r9)     // Catch: java.lang.Exception -> Lb7
                return r10
            La1:
                android.util.Pair r9 = new android.util.Pair     // Catch: java.lang.Exception -> Lb7
                java.lang.Boolean r10 = java.lang.Boolean.FALSE     // Catch: java.lang.Exception -> Lb7
                java.lang.String r11 = "compress fail:create tmp file fail"
                r9.<init>(r10, r11)     // Catch: java.lang.Exception -> Lb7
                return r9
            Lab:
                r3.recycle()     // Catch: java.lang.Exception -> Lb7
                r5.flush()     // Catch: java.lang.Exception -> Lb7
                r5.close()     // Catch: java.lang.Exception -> Lb7
                r9.close()     // Catch: java.lang.Exception -> Lb7
            Lb7:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.jsapi.refact.media.JsApiCompressImage.IImageCompress.innerCompress(java.io.InputStream, int, java.lang.String):android.util.Pair");
        }
    }

    /* loaded from: classes15.dex */
    static class JdFileImageCompress extends IImageCompress {
        JdFileImageCompress() {
        }

        @Override // com.jingdong.manto.jsapi.refact.media.JsApiCompressImage.IImageCompress
        public Pair<Boolean, String> compress(e0 e0Var, String str, int i2) {
            String c2 = e0Var.c();
            d g2 = c.g(c2, str);
            if (g2 == null || TextUtils.isEmpty(g2.b)) {
                return new Pair<>(Boolean.FALSE, "compress fail:file does't exist.");
            }
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(g2.b);
            } catch (FileNotFoundException unused) {
            }
            return fileInputStream == null ? new Pair<>(Boolean.FALSE, "compress fail:file does't exist.") : innerCompress(fileInputStream, i2, c2);
        }
    }

    /* loaded from: classes15.dex */
    static class PkgImageCompress extends IImageCompress {
        PkgImageCompress() {
        }

        @Override // com.jingdong.manto.jsapi.refact.media.JsApiCompressImage.IImageCompress
        public Pair<Boolean, String> compress(e0 e0Var, String str, int i2) {
            String c2 = e0Var.c();
            InputStream d = g.d(e0Var.h(), str);
            return d == null ? new Pair<>(Boolean.FALSE, "compress fail:file doesn't exist") : innerCompress(d, i2, c2);
        }
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(final h hVar, JSONObject jSONObject, final int i2, String str) {
        super.exec(hVar, jSONObject, i2, str);
        if (hVar == null || !hVar.d) {
            return;
        }
        if (jSONObject == null) {
            hVar.a(i2, putErrMsg("fail:invalid data"));
            return;
        }
        final String optString = jSONObject.optString("src");
        int optInt = jSONObject.optInt("quality", 80);
        if (MantoStringUtils.isEmpty(optString)) {
            hVar.a(i2, putErrMsg("fail:src is null"));
            return;
        }
        final int i3 = (optInt <= 0 || optInt > 100) ? 80 : optInt;
        new WeakReference(hVar);
        b.d().diskIO().execute(new Runnable
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0055: INVOKE 
              (wrap: java.util.concurrent.Executor : 0x0048: INVOKE 
              (wrap: com.jingdong.manto.pkg.AppExecutors : 0x0044: INVOKE  type: STATIC call: com.jingdong.manto.b.d():com.jingdong.manto.pkg.AppExecutors A[MD:():com.jingdong.manto.pkg.AppExecutors (m), WRAPPED])
             type: VIRTUAL call: com.jingdong.manto.pkg.AppExecutors.diskIO():java.util.concurrent.Executor A[MD:():java.util.concurrent.Executor (m), WRAPPED])
              (wrap: java.lang.Runnable : 0x0052: CONSTRUCTOR 
              (r7v0 'this' com.jingdong.manto.jsapi.refact.media.JsApiCompressImage A[IMMUTABLE_TYPE, THIS])
              (r2v0 'optString' java.lang.String A[DONT_INLINE])
              (r8v0 'hVar' com.jingdong.manto.h A[DONT_INLINE])
              (r4v1 'i3' int A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r5 I:java.lang.ref.WeakReference A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r10v0 'i2' int A[DONT_INLINE])
             A[MD:(com.jingdong.manto.jsapi.refact.media.JsApiCompressImage, java.lang.String, com.jingdong.manto.h, int, java.lang.ref.WeakReference, int):void (m), WRAPPED] call: com.jingdong.manto.jsapi.refact.media.JsApiCompressImage.1.<init>(com.jingdong.manto.jsapi.refact.media.JsApiCompressImage, java.lang.String, com.jingdong.manto.h, int, java.lang.ref.WeakReference, int):void type: CONSTRUCTOR)
             type: INTERFACE call: java.util.concurrent.Executor.execute(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (c)] in method: com.jingdong.manto.jsapi.refact.media.JsApiCompressImage.exec(com.jingdong.manto.h, org.json.JSONObject, int, java.lang.String):void, file: classes15.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            Caused by: java.lang.NullPointerException
            */
        /*
            this = this;
            super.exec(r8, r9, r10, r11)
            if (r8 == 0) goto L58
            boolean r11 = r8.d
            if (r11 != 0) goto La
            goto L58
        La:
            if (r9 != 0) goto L16
            java.lang.String r9 = "fail:invalid data"
            java.lang.String r9 = r7.putErrMsg(r9)
            r8.a(r10, r9)
            goto L58
        L16:
            java.lang.String r11 = "src"
            java.lang.String r2 = r9.optString(r11)
            java.lang.String r11 = "quality"
            r0 = 80
            int r9 = r9.optInt(r11, r0)
            boolean r11 = com.jingdong.manto.utils.MantoStringUtils.isEmpty(r2)
            if (r11 == 0) goto L34
            java.lang.String r9 = "fail:src is null"
            java.lang.String r9 = r7.putErrMsg(r9)
            r8.a(r10, r9)
            return
        L34:
            if (r9 <= 0) goto L3d
            r11 = 100
            if (r9 <= r11) goto L3b
            goto L3d
        L3b:
            r4 = r9
            goto L3f
        L3d:
            r4 = 80
        L3f:
            java.lang.ref.WeakReference r5 = new java.lang.ref.WeakReference
            r5.<init>(r8)
            com.jingdong.manto.pkg.AppExecutors r9 = com.jingdong.manto.b.d()
            java.util.concurrent.Executor r9 = r9.diskIO()
            com.jingdong.manto.jsapi.refact.media.JsApiCompressImage$1 r11 = new com.jingdong.manto.jsapi.refact.media.JsApiCompressImage$1
            r0 = r11
            r1 = r7
            r3 = r8
            r6 = r10
            r0.<init>()
            r9.execute(r11)
        L58:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.jsapi.refact.media.JsApiCompressImage.exec(com.jingdong.manto.h, org.json.JSONObject, int, java.lang.String):void");
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "compressImage";
    }
}
