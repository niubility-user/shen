package com.jingdong.common.XView;

import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.BaseActivity;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.mta.JDMtaUtils;

/* loaded from: classes5.dex */
public class ShareGiftXViewHelper {
    static final String TAG = "ShareGiftXViewHelper";

    public static void dealShareGiftOnCreate(final BaseActivity baseActivity, final Bundle bundle) {
        if (bundle == null || baseActivity == null) {
            return;
        }
        try {
            String string = bundle.getString("shareRuleType");
            if (Log.D) {
                Log.d(TAG, "dealShareGiftOnCreate:" + string);
            }
            if (TextUtils.isEmpty(string)) {
                return;
            }
            if (string.equals("2") || string.equals("3")) {
                String string2 = bundle.getString("shareActivityId");
                String string3 = bundle.getString("shareToken");
                if (TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
                    return;
                }
                if (Log.D) {
                    Log.d(TAG, "dealShareGiftOnCreate,actId=" + string2 + "  token=" + string3);
                }
                bundle.getString("webUrl");
                final XViewEntity xViewEntity = new XViewEntity();
                xViewEntity.isFragment = false;
                xViewEntity.url = String.format("https://" + Configuration.getNewShareHost() + "/share/index.html?shareActivityId=%s&shareToken=%s", string2, string3);
                new XViewCallBackAdapter() { // from class: com.jingdong.common.XView.ShareGiftXViewHelper.1
                    @Override // com.jingdong.common.XView.XViewCallBackAdapter, com.jingdong.common.XView.XViewCallBack
                    public void onXViewDisplayed() {
                        super.onXViewDisplayed();
                        BaseActivity baseActivity2 = BaseActivity.this;
                        JDMtaUtils.onClick(baseActivity2, "Share_XviewExpoOpen", baseActivity2.getClass().getSimpleName(), "", bundle.getString("shareType"));
                    }
                };
                baseActivity.post(new Runnable
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x00b6: INVOKE 
                      (r6v0 'baseActivity' com.jingdong.common.BaseActivity)
                      (wrap: java.lang.Runnable : 0x00b3: CONSTRUCTOR 
                      (r6v0 'baseActivity' com.jingdong.common.BaseActivity A[DONT_INLINE])
                      (r2v4 'xViewEntity' com.jingdong.common.XView.XViewEntity A[DONT_INLINE])
                      (r0 I:com.jingdong.common.XView.XViewCallBackAdapter A[DONT_GENERATE, DONT_INLINE, REMOVE])
                     A[MD:(com.jingdong.common.BaseActivity, com.jingdong.common.XView.XViewEntity, com.jingdong.common.XView.XViewCallBackAdapter):void (m), WRAPPED] (LINE:16) call: com.jingdong.common.XView.ShareGiftXViewHelper.2.<init>(com.jingdong.common.BaseActivity, com.jingdong.common.XView.XViewEntity, com.jingdong.common.XView.XViewCallBackAdapter):void type: CONSTRUCTOR)
                     type: VIRTUAL call: com.jingdong.common.BaseActivity.post(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (m)] (LINE:16) in method: com.jingdong.common.XView.ShareGiftXViewHelper.dealShareGiftOnCreate(com.jingdong.common.BaseActivity, android.os.Bundle):void, file: classes5.dex
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
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                    	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
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
                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                    	... 41 more
                    */
                /*
                    if (r7 == 0) goto Lbe
                    if (r6 != 0) goto L6
                    goto Lbe
                L6:
                    java.lang.String r0 = "shareRuleType"
                    java.lang.String r0 = r7.getString(r0)     // Catch: java.lang.Exception -> Lba
                    boolean r1 = com.jingdong.corelib.utils.Log.D
                    if (r1 == 0) goto L26
                    java.lang.String r1 = com.jingdong.common.XView.ShareGiftXViewHelper.TAG
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    java.lang.String r3 = "dealShareGiftOnCreate:"
                    r2.append(r3)
                    r2.append(r0)
                    java.lang.String r2 = r2.toString()
                    com.jingdong.corelib.utils.Log.d(r1, r2)
                L26:
                    boolean r1 = android.text.TextUtils.isEmpty(r0)
                    if (r1 == 0) goto L2d
                    return
                L2d:
                    java.lang.String r1 = "2"
                    boolean r1 = r0.equals(r1)
                    if (r1 != 0) goto L3d
                    java.lang.String r1 = "3"
                    boolean r0 = r0.equals(r1)
                    if (r0 == 0) goto Lb9
                L3d:
                    java.lang.String r0 = "shareActivityId"
                    java.lang.String r0 = r7.getString(r0)
                    java.lang.String r1 = "shareToken"
                    java.lang.String r1 = r7.getString(r1)
                    boolean r2 = android.text.TextUtils.isEmpty(r0)
                    if (r2 != 0) goto Lb9
                    boolean r2 = android.text.TextUtils.isEmpty(r1)
                    if (r2 != 0) goto Lb9
                    boolean r2 = com.jingdong.corelib.utils.Log.D
                    if (r2 == 0) goto L77
                    java.lang.String r2 = com.jingdong.common.XView.ShareGiftXViewHelper.TAG
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder
                    r3.<init>()
                    java.lang.String r4 = "dealShareGiftOnCreate,actId="
                    r3.append(r4)
                    r3.append(r0)
                    java.lang.String r4 = "  token="
                    r3.append(r4)
                    r3.append(r1)
                    java.lang.String r3 = r3.toString()
                    com.jingdong.corelib.utils.Log.d(r2, r3)
                L77:
                    java.lang.String r2 = "webUrl"
                    r7.getString(r2)
                    com.jingdong.common.XView.XViewEntity r2 = new com.jingdong.common.XView.XViewEntity
                    r2.<init>()
                    r3 = 0
                    r2.isFragment = r3
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder
                    r4.<init>()
                    java.lang.String r5 = "https://"
                    r4.append(r5)
                    java.lang.String r5 = com.jingdong.jdsdk.config.Configuration.getNewShareHost()
                    r4.append(r5)
                    java.lang.String r5 = "/share/index.html?shareActivityId=%s&shareToken=%s"
                    r4.append(r5)
                    java.lang.String r4 = r4.toString()
                    r5 = 2
                    java.lang.Object[] r5 = new java.lang.Object[r5]
                    r5[r3] = r0
                    r0 = 1
                    r5[r0] = r1
                    java.lang.String r0 = java.lang.String.format(r4, r5)
                    r2.url = r0
                    com.jingdong.common.XView.ShareGiftXViewHelper$1 r0 = new com.jingdong.common.XView.ShareGiftXViewHelper$1
                    r0.<init>()
                    com.jingdong.common.XView.ShareGiftXViewHelper$2 r7 = new com.jingdong.common.XView.ShareGiftXViewHelper$2
                    r7.<init>()
                    r6.post(r7)
                Lb9:
                    return
                Lba:
                    r6 = move-exception
                    r6.printStackTrace()
                Lbe:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.XView.ShareGiftXViewHelper.dealShareGiftOnCreate(com.jingdong.common.BaseActivity, android.os.Bundle):void");
            }
        }
