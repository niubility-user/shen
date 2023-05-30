package com.jd.aips.common.permisson;

import android.content.Context;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Looper;
import java.lang.ref.WeakReference;

/* loaded from: classes12.dex */
public class FsCameraTest implements FsPermissionTest {
    private static final Camera.PreviewCallback b = new Camera.PreviewCallback() { // from class: com.jd.aips.common.permisson.FsCameraTest.2
        @Override // android.hardware.Camera.PreviewCallback
        public void onPreviewFrame(byte[] bArr, Camera camera) {
        }
    };
    private WeakReference<Context> a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FsCameraTest(Context context) {
        this.a = new WeakReference<>(context);
    }

    @Override // com.jd.aips.common.permisson.FsPermissionTest
    public boolean test() throws Throwable {
        Camera camera;
        try {
            camera = Camera.open();
        } catch (Throwable unused) {
            camera = null;
        }
        try {
            camera.setParameters(camera.getParameters());
            camera.setPreviewCallback(b);
            camera.startPreview();
            camera.stopPreview();
            camera.setPreviewCallback(null);
            camera.release();
            return true;
        } catch (Throwable unused2) {
            try {
                Context context = this.a.get();
                if (context != null) {
                    return true ^ context.getPackageManager().hasSystemFeature("android.hardware.camera");
                }
                if (camera != null) {
                    camera.stopPreview();
                    camera.setPreviewCallback(null);
                    camera.release();
                }
                return false;
            } finally {
                if (camera != null) {
                    camera.stopPreview();
                    camera.setPreviewCallback(null);
                    camera.release();
                }
            }
        }
    }

    @Override // com.jd.aips.common.permisson.FsPermissionTest
    public void testAsync(final IFsPermissionTestCallback iFsPermissionTestCallback) {
        new Handler(Looper.getMainLooper());
        new Thread
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000e: INVOKE 
              (wrap: java.lang.Thread : 0x000b: CONSTRUCTOR 
              (r2v0 'this' com.jd.aips.common.permisson.FsCameraTest A[IMMUTABLE_TYPE, THIS])
              (r0 I:android.os.Handler A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r3v0 'iFsPermissionTestCallback' com.jd.aips.common.permisson.IFsPermissionTestCallback A[DONT_INLINE])
             A[MD:(com.jd.aips.common.permisson.FsCameraTest, android.os.Handler, com.jd.aips.common.permisson.IFsPermissionTestCallback):void (m), WRAPPED] (LINE:2) call: com.jd.aips.common.permisson.FsCameraTest.1.<init>(com.jd.aips.common.permisson.FsCameraTest, android.os.Handler, com.jd.aips.common.permisson.IFsPermissionTestCallback):void type: CONSTRUCTOR)
             type: VIRTUAL call: java.lang.Thread.start():void A[MD:():void (c)] (LINE:3) in method: com.jd.aips.common.permisson.FsCameraTest.testAsync(com.jd.aips.common.permisson.IFsPermissionTestCallback):void, file: classes12.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
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
            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
            	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:96)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:840)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
            	... 15 more
            */
        /*
            this = this;
            android.os.Handler r0 = new android.os.Handler
            android.os.Looper r1 = android.os.Looper.getMainLooper()
            r0.<init>(r1)
            com.jd.aips.common.permisson.FsCameraTest$1 r1 = new com.jd.aips.common.permisson.FsCameraTest$1
            r1.<init>()
            r1.start()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.aips.common.permisson.FsCameraTest.testAsync(com.jd.aips.common.permisson.IFsPermissionTestCallback):void");
    }
}
