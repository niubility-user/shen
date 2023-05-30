package com.jdjr.risk.jdcn.common.permisson;

import android.content.Context;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Looper;
import java.lang.ref.WeakReference;

/* loaded from: classes18.dex */
public class FsCameraTest implements FsPermissionTest {
    private static final Camera.PreviewCallback PREVIEW_CALLBACK = new Camera.PreviewCallback() { // from class: com.jdjr.risk.jdcn.common.permisson.FsCameraTest.2
        @Override // android.hardware.Camera.PreviewCallback
        public final void onPreviewFrame(byte[] bArr, Camera camera) {
        }
    };
    private WeakReference<Context> mContext;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FsCameraTest(Context context) {
        this.mContext = new WeakReference<>(context);
    }

    @Override // com.jdjr.risk.jdcn.common.permisson.FsPermissionTest
    public boolean test() {
        Camera camera;
        try {
            camera = Camera.open();
            try {
                camera.setParameters(camera.getParameters());
                camera.setPreviewCallback(PREVIEW_CALLBACK);
                camera.startPreview();
                return true;
            } catch (Throwable unused) {
                try {
                    Context context = this.mContext.get();
                    if (context != null) {
                        boolean hasSystemFeature = true ^ context.getPackageManager().hasSystemFeature("android.hardware.camera");
                        if (camera != null) {
                            camera.stopPreview();
                            camera.setPreviewCallback(null);
                            camera.release();
                        }
                        return hasSystemFeature;
                    } else if (camera != null) {
                        camera.stopPreview();
                        camera.setPreviewCallback(null);
                        camera.release();
                        return false;
                    } else {
                        return false;
                    }
                } finally {
                    if (camera != null) {
                        camera.stopPreview();
                        camera.setPreviewCallback(null);
                        camera.release();
                    }
                }
            }
        } catch (Throwable unused2) {
            camera = null;
        }
    }

    @Override // com.jdjr.risk.jdcn.common.permisson.FsPermissionTest
    public void testAsync(final IFsPermissionTestCallback iFsPermissionTestCallback) {
        new Handler(Looper.getMainLooper());
        new Thread
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000e: INVOKE 
              (wrap: java.lang.Thread : 0x000b: CONSTRUCTOR 
              (r2v0 'this' com.jdjr.risk.jdcn.common.permisson.FsCameraTest A[IMMUTABLE_TYPE, THIS])
              (r0 I:android.os.Handler A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r3v0 'iFsPermissionTestCallback' com.jdjr.risk.jdcn.common.permisson.IFsPermissionTestCallback A[DONT_INLINE])
             A[MD:(com.jdjr.risk.jdcn.common.permisson.FsCameraTest, android.os.Handler, com.jdjr.risk.jdcn.common.permisson.IFsPermissionTestCallback):void (m), WRAPPED] (LINE:2) call: com.jdjr.risk.jdcn.common.permisson.FsCameraTest.1.<init>(com.jdjr.risk.jdcn.common.permisson.FsCameraTest, android.os.Handler, com.jdjr.risk.jdcn.common.permisson.IFsPermissionTestCallback):void type: CONSTRUCTOR)
             type: VIRTUAL call: java.lang.Thread.start():void A[MD:():void (c)] (LINE:3) in method: com.jdjr.risk.jdcn.common.permisson.FsCameraTest.testAsync(com.jdjr.risk.jdcn.common.permisson.IFsPermissionTestCallback):void, file: classes18.dex
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
            com.jdjr.risk.jdcn.common.permisson.FsCameraTest$1 r1 = new com.jdjr.risk.jdcn.common.permisson.FsCameraTest$1
            r1.<init>()
            r1.start()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdjr.risk.jdcn.common.permisson.FsCameraTest.testAsync(com.jdjr.risk.jdcn.common.permisson.IFsPermissionTestCallback):void");
    }
}
