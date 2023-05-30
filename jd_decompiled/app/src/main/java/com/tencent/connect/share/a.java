package com.tencent.connect.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.tencent.open.log.SLog;
import com.tencent.open.utils.d;
import com.tencent.open.utils.m;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class a {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean b(String str, int i2, int i3) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            BitmapFactory.decodeFile(str, options);
        } catch (OutOfMemoryError e2) {
            SLog.e("openSDK_LOG.AsynScaleCompressImage", "isBitMapNeedToCompress exception:", e2);
        }
        int i4 = options.outWidth;
        int i5 = options.outHeight;
        if (options.mCancel || i4 == -1 || i5 == -1) {
            return false;
        }
        int i6 = i4 > i5 ? i4 : i5;
        if (i4 >= i5) {
            i4 = i5;
        }
        SLog.d("openSDK_LOG.AsynScaleCompressImage", "longSide=" + i6 + "shortSide=" + i4);
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return i6 > i3 || i4 > i2;
    }

    public static final void a(final Context context, final String str, final d dVar) {
        SLog.i("openSDK_LOG.AsynScaleCompressImage", "scaleCompressImage()");
        if (TextUtils.isEmpty(str)) {
            dVar.a(1, (String) null);
        } else if (!m.a()) {
            dVar.a(2, (String) null);
        } else {
            new Handler(context.getMainLooper()) { // from class: com.tencent.connect.share.a.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    int i2 = message.what;
                    if (i2 == 101) {
                        dVar.a(0, (ArrayList) message.obj);
                    } else if (i2 != 102) {
                        super.handleMessage(message);
                    } else {
                        dVar.a(message.arg1, (String) null);
                    }
                }
            };
            new Thread(new Runnable
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0031: INVOKE 
                  (wrap: java.lang.Thread : 0x002e: CONSTRUCTOR 
                  (wrap: java.lang.Runnable : 0x002b: CONSTRUCTOR 
                  (r3v0 'str' java.lang.String A[DONT_INLINE])
                  (r0 I:android.os.Handler A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r2v0 'context' android.content.Context A[DONT_INLINE])
                 A[MD:(java.lang.String, android.os.Handler, android.content.Context):void (m), WRAPPED] call: com.tencent.connect.share.a.2.<init>(java.lang.String, android.os.Handler, android.content.Context):void type: CONSTRUCTOR)
                 A[MD:(java.lang.Runnable):void (c), WRAPPED] (LINE:8) call: java.lang.Thread.<init>(java.lang.Runnable):void type: CONSTRUCTOR)
                 type: VIRTUAL call: java.lang.Thread.start():void A[MD:():void (c)] (LINE:9) in method: com.tencent.connect.share.a.a(android.content.Context, java.lang.String, com.tencent.open.utils.d):void, file: classes9.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:137)
                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:156)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:133)
                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
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
                java.lang.String r0 = "openSDK_LOG.AsynScaleCompressImage"
                java.lang.String r1 = "scaleCompressImage()"
                com.tencent.open.log.SLog.i(r0, r1)
                boolean r0 = android.text.TextUtils.isEmpty(r3)
                r1 = 0
                if (r0 == 0) goto L13
                r2 = 1
                r4.a(r2, r1)
                return
            L13:
                boolean r0 = com.tencent.open.utils.m.a()
                if (r0 != 0) goto L1e
                r2 = 2
                r4.a(r2, r1)
                return
            L1e:
                com.tencent.connect.share.a$1 r0 = new com.tencent.connect.share.a$1
                android.os.Looper r1 = r2.getMainLooper()
                r0.<init>(r1)
                java.lang.Thread r4 = new java.lang.Thread
                com.tencent.connect.share.a$2 r1 = new com.tencent.connect.share.a$2
                r1.<init>()
                r4.<init>(r1)
                r4.start()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.connect.share.a.a(android.content.Context, java.lang.String, com.tencent.open.utils.d):void");
        }

        private static Bitmap a(Bitmap bitmap, int i2) {
            Matrix matrix = new Matrix();
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (width <= height) {
                width = height;
            }
            float f2 = i2 / width;
            matrix.postScale(f2, f2);
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        }

        private static int b(BitmapFactory.Options options, int i2, int i3) {
            int ceil;
            int min;
            double d = options.outWidth;
            double d2 = options.outHeight;
            if (i3 == -1) {
                ceil = 1;
            } else {
                Double.isNaN(d);
                Double.isNaN(d2);
                double d3 = i3;
                Double.isNaN(d3);
                ceil = (int) Math.ceil(Math.sqrt((d * d2) / d3));
            }
            if (i2 == -1) {
                min = 128;
            } else {
                double d4 = i2;
                Double.isNaN(d);
                Double.isNaN(d4);
                double floor = Math.floor(d / d4);
                Double.isNaN(d2);
                Double.isNaN(d4);
                min = (int) Math.min(floor, Math.floor(d2 / d4));
            }
            if (min < ceil) {
                return ceil;
            }
            if (i3 == -1 && i2 == -1) {
                return 1;
            }
            return i2 == -1 ? ceil : min;
        }

        protected static final String a(Bitmap bitmap, String str, String str2) {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            StringBuffer stringBuffer = new StringBuffer(str);
            stringBuffer.append(str2);
            String stringBuffer2 = stringBuffer.toString();
            File file2 = new File(stringBuffer2);
            if (file2.exists()) {
                file2.delete();
            }
            if (bitmap != null) {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    bitmap.recycle();
                    return stringBuffer2;
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                    return null;
                } catch (IOException e3) {
                    e3.printStackTrace();
                    return null;
                }
            }
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:31:0x0054  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x005a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static final android.graphics.Bitmap a(java.lang.String r6, int r7) {
            /*
                java.lang.String r0 = "openSDK_LOG.AsynScaleCompressImage"
                boolean r1 = android.text.TextUtils.isEmpty(r6)
                r2 = 0
                if (r1 == 0) goto La
                return r2
            La:
                android.graphics.BitmapFactory$Options r1 = new android.graphics.BitmapFactory$Options
                r1.<init>()
                r3 = 1
                r1.inJustDecodeBounds = r3
                android.graphics.BitmapFactory.decodeFile(r6, r1)     // Catch: java.lang.OutOfMemoryError -> L16
                goto L1c
            L16:
                r3 = move-exception
                java.lang.String r4 = "scaleBitmap exception1:"
                com.tencent.open.log.SLog.e(r0, r4, r3)
            L1c:
                int r3 = r1.outWidth
                int r4 = r1.outHeight
                boolean r5 = r1.mCancel
                if (r5 != 0) goto L69
                r5 = -1
                if (r3 == r5) goto L69
                if (r4 != r5) goto L2a
                goto L69
            L2a:
                if (r3 <= r4) goto L2d
                goto L2e
            L2d:
                r3 = r4
            L2e:
                android.graphics.Bitmap$Config r4 = android.graphics.Bitmap.Config.RGB_565
                r1.inPreferredConfig = r4
                if (r3 <= r7) goto L3c
                int r3 = r7 * r7
                int r3 = a(r1, r5, r3)
                r1.inSampleSize = r3
            L3c:
                r3 = 0
                r1.inJustDecodeBounds = r3
                android.graphics.Bitmap r6 = android.graphics.BitmapFactory.decodeFile(r6, r1)     // Catch: java.lang.OutOfMemoryError -> L44 java.lang.Exception -> L4b
                goto L52
            L44:
                r6 = move-exception
                java.lang.String r3 = "scaleBitmap OutOfMemoryError:"
                com.tencent.open.log.SLog.e(r0, r3, r6)
                goto L51
            L4b:
                r6 = move-exception
                java.lang.String r3 = "scaleBitmap exception2:"
                com.tencent.open.log.SLog.e(r0, r3, r6)
            L51:
                r6 = r2
            L52:
                if (r6 != 0) goto L5a
                java.lang.String r6 = "scaleBitmap return null"
                com.tencent.open.log.SLog.e(r0, r6)
                return r2
            L5a:
                int r0 = r1.outWidth
                int r1 = r1.outHeight
                if (r0 <= r1) goto L61
                goto L62
            L61:
                r0 = r1
            L62:
                if (r0 <= r7) goto L68
                android.graphics.Bitmap r6 = a(r6, r7)
            L68:
                return r6
            L69:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.connect.share.a.a(java.lang.String, int):android.graphics.Bitmap");
        }

        public static final int a(BitmapFactory.Options options, int i2, int i3) {
            int b = b(options, i2, i3);
            if (b <= 8) {
                int i4 = 1;
                while (i4 < b) {
                    i4 <<= 1;
                }
                return i4;
            }
            return ((b + 7) / 8) * 8;
        }
    }
