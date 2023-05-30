package com.jingdong.manto.jsapi.refact.media;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Pair;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.manto.h;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.m.e0;
import com.jingdong.manto.m.f1.b;
import com.jingdong.manto.pkg.b.g;
import com.jingdong.manto.t.c;
import com.jingdong.manto.t.d;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.n;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class JsApiCompressImage extends d0 {

    /* loaded from: classes15.dex */
    public static abstract class IImageCompress {
        IImageCompress() {
        }

        abstract Pair<Boolean, String> compress(e0 e0Var, String str, int i2);

        /* JADX WARN: Removed duplicated region for block: B:107:0x00ab A[Catch: Exception -> 0x00b7, TRY_LEAVE, TryCatch #0 {Exception -> 0x00b7, blocks: (B:77:0x0003, B:79:0x000e, B:81:0x0018, B:83:0x0023, B:86:0x002c, B:89:0x0036, B:91:0x0063, B:93:0x006d, B:94:0x0074, B:96:0x007a, B:97:0x007d, B:99:0x0088, B:101:0x008f, B:103:0x0097, B:105:0x00a1, B:107:0x00ab, B:88:0x0031), top: B:110:0x0003 }] */
        /* JADX WARN: Removed duplicated region for block: B:96:0x007a A[Catch: Exception -> 0x00b7, TryCatch #0 {Exception -> 0x00b7, blocks: (B:77:0x0003, B:79:0x000e, B:81:0x0018, B:83:0x0023, B:86:0x002c, B:89:0x0036, B:91:0x0063, B:93:0x006d, B:94:0x0074, B:96:0x007a, B:97:0x007d, B:99:0x0088, B:101:0x008f, B:103:0x0097, B:105:0x00a1, B:107:0x00ab, B:88:0x0031), top: B:110:0x0003 }] */
        /* JADX WARN: Removed duplicated region for block: B:99:0x0088 A[Catch: Exception -> 0x00b7, TryCatch #0 {Exception -> 0x00b7, blocks: (B:77:0x0003, B:79:0x000e, B:81:0x0018, B:83:0x0023, B:86:0x002c, B:89:0x0036, B:91:0x0063, B:93:0x006d, B:94:0x0074, B:96:0x007a, B:97:0x007d, B:99:0x0088, B:101:0x008f, B:103:0x0097, B:105:0x00a1, B:107:0x00ab, B:88:0x0031), top: B:110:0x0003 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        protected Pair<Boolean, String> innerCompress(InputStream inputStream, int i2, String str) {
            BitmapFactory.Options options;
            Bitmap decodeStream;
            String str2;
            Bitmap.CompressFormat compressFormat;
            File file;
            FileOutputStream fileOutputStream;
            try {
                options = new BitmapFactory.Options();
                decodeStream = BitmapFactory.decodeStream(inputStream, null, options);
            } catch (Exception unused) {
            }
            if (decodeStream == null) {
                return new Pair<>(Boolean.FALSE, "compress fail:decode image fail");
            }
            String a = b.a(options);
            if (a.indexOf("jpg") <= -1 && a.indexOf("jpeg") <= -1) {
                compressFormat = Bitmap.CompressFormat.PNG;
                str2 = "png";
                String str3 = n.b + "/photo/mantoMsg.tmp." + System.currentTimeMillis() + OrderISVUtil.MONEY_DECIMAL + str2;
                file = new File(str3);
                if (file.getParentFile() != null && !file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
                fileOutputStream = new FileOutputStream(file);
                if (!decodeStream.compress(compressFormat, i2, fileOutputStream)) {
                    d a2 = c.a(str, str3, false);
                    return (a2 == null || TextUtils.isEmpty(a2.a)) ? new Pair<>(Boolean.FALSE, "compress fail:create tmp file fail") : new Pair<>(Boolean.TRUE, a2.a);
                }
                decodeStream.recycle();
                fileOutputStream.flush();
                fileOutputStream.close();
                inputStream.close();
                return null;
            }
            str2 = "jpg";
            compressFormat = Bitmap.CompressFormat.JPEG;
            String str32 = n.b + "/photo/mantoMsg.tmp." + System.currentTimeMillis() + OrderISVUtil.MONEY_DECIMAL + str2;
            file = new File(str32);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
            }
            fileOutputStream = new FileOutputStream(file);
            if (!decodeStream.compress(compressFormat, i2, fileOutputStream)) {
            }
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
        com.jingdong.manto.b.d().diskIO().execute(new Runnable
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
