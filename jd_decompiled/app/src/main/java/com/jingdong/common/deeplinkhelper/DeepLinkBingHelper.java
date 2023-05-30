package com.jingdong.common.deeplinkhelper;

import android.os.Bundle;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;

/* loaded from: classes5.dex */
public class DeepLinkBingHelper {
    public static final String HOST_BING = "bing";

    public static void launchBingActivity(IMyActivity iMyActivity, Bundle bundle) {
        startBingActivity(iMyActivity, bundle);
    }

    public static void startBingActivity(final IMyActivity iMyActivity, final Bundle bundle) {
        DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(14));
        LoginUserHelper.getInstance().executeLoginRunnable(iMyActivity, new Runnable
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0017: INVOKE 
              (wrap: com.jingdong.common.login.LoginUserHelper : 0x000e: INVOKE  type: STATIC call: com.jingdong.common.login.LoginUserHelper.getInstance():com.jingdong.common.login.LoginUserHelper A[MD:():com.jingdong.common.login.LoginUserHelper (m), WRAPPED] (LINE:2))
              (r3v0 'iMyActivity' com.jingdong.common.frame.IMyActivity)
              (wrap: java.lang.Runnable : 0x0014: CONSTRUCTOR 
              (r0 I:boolean A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r3v0 'iMyActivity' com.jingdong.common.frame.IMyActivity A[DONT_INLINE])
              (r4v0 'bundle' android.os.Bundle A[DONT_INLINE])
             A[MD:(boolean, com.jingdong.common.frame.IMyActivity, android.os.Bundle):void (m), WRAPPED] call: com.jingdong.common.deeplinkhelper.DeepLinkBingHelper.1.<init>(boolean, com.jingdong.common.frame.IMyActivity, android.os.Bundle):void type: CONSTRUCTOR)
             type: VIRTUAL call: com.jingdong.common.login.LoginUserHelper.executeLoginRunnable(com.jingdong.common.frame.IMyActivity, java.lang.Runnable):void A[MD:(com.jingdong.common.frame.IMyActivity, java.lang.Runnable):void (m)] (LINE:2) in method: com.jingdong.common.deeplinkhelper.DeepLinkBingHelper.startBingActivity(com.jingdong.common.frame.IMyActivity, android.os.Bundle):void, file: classes5.dex
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
            */
        /*
            com.jingdong.common.deeplinkhelper.DeepLinkSwitch r0 = com.jingdong.common.deeplinkhelper.DeepLinkSwitch.getInstance()
            r1 = 14
            long r1 = com.jingdong.jdsdk.auraSetting.AuraBundleInfos.getSwitchMaskFromBundleId(r1)
            boolean r0 = r0.isSwitchOpen(r1)
            com.jingdong.common.login.LoginUserHelper r1 = com.jingdong.common.login.LoginUserHelper.getInstance()
            com.jingdong.common.deeplinkhelper.DeepLinkBingHelper$1 r2 = new com.jingdong.common.deeplinkhelper.DeepLinkBingHelper$1
            r2.<init>()
            r1.executeLoginRunnable(r3, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.deeplinkhelper.DeepLinkBingHelper.startBingActivity(com.jingdong.common.frame.IMyActivity, android.os.Bundle):void");
    }
}
