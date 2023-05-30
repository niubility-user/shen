package com.jingdong.common.utils;

import com.jingdong.common.PersonalDownloadImageEntity;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes6.dex */
public class DownloadImageUtils {

    /* loaded from: classes6.dex */
    public interface DownloadImageListener {
        void downloadComplete(List<PersonalDownloadImageEntity> list);

        void downloadError();
    }

    public static void downloadImage(final List<PersonalDownloadImageEntity> list, final DownloadImageListener downloadImageListener) {
        if (list == null || list.size() != 2) {
            return;
        }
        new LinkedList();
        final int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            final int i3 = i2;
            downloadImage(list.get(i2).getDownloadUrl(), new HttpGroup.OnAllListener
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x002c: INVOKE 
                  (wrap: java.lang.String : 0x001d: INVOKE 
                  (wrap: com.jingdong.common.PersonalDownloadImageEntity : 0x0017: INVOKE (r11v0 'list' java.util.List<com.jingdong.common.PersonalDownloadImageEntity>), (r8v1 'i2' int) type: INTERFACE call: java.util.List.get(int):java.lang.Object A[MD:(int):E (c), WRAPPED] (LINE:4))
                 type: VIRTUAL call: com.jingdong.common.PersonalDownloadImageEntity.getDownloadUrl():java.lang.String A[MD:():java.lang.String (m), WRAPPED] (LINE:4))
                  (wrap: com.jingdong.jdsdk.network.toolbox.HttpGroup$OnAllListener : 0x0029: CONSTRUCTOR 
                  (r12v0 'downloadImageListener' com.jingdong.common.utils.DownloadImageUtils$DownloadImageListener A[DONT_INLINE])
                  (r11v0 'list' java.util.List<com.jingdong.common.PersonalDownloadImageEntity> A[DONT_INLINE])
                  (r5v0 'i3' int A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r0 I:java.util.List A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r1v1 'size' int A[DONT_INLINE])
                 A[MD:(com.jingdong.common.utils.DownloadImageUtils$DownloadImageListener, java.util.List, int, java.util.List, int):void (m), WRAPPED] call: com.jingdong.common.utils.DownloadImageUtils.1.<init>(com.jingdong.common.utils.DownloadImageUtils$DownloadImageListener, java.util.List, int, java.util.List, int):void type: CONSTRUCTOR)
                 type: STATIC call: com.jingdong.common.utils.DownloadImageUtils.downloadImage(java.lang.String, com.jingdong.jdsdk.network.toolbox.HttpGroup$HttpTaskListener):void A[MD:(java.lang.String, com.jingdong.jdsdk.network.toolbox.HttpGroup$HttpTaskListener):void (m)] (LINE:4) in method: com.jingdong.common.utils.DownloadImageUtils.downloadImage(java.util.List<com.jingdong.common.PersonalDownloadImageEntity>, com.jingdong.common.utils.DownloadImageUtils$DownloadImageListener):void, file: classes6.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:195)
                	at jadx.core.dex.regions.loops.LoopRegion.generate(LoopRegion.java:171)
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
                	... 25 more
                */
            /*
                if (r11 == 0) goto L32
                int r0 = r11.size()
                r1 = 2
                if (r0 == r1) goto La
                goto L32
            La:
                java.util.LinkedList r0 = new java.util.LinkedList
                r0.<init>()
                int r1 = r11.size()
                r2 = 0
                r8 = 0
            L15:
                if (r8 >= r1) goto L32
                java.lang.Object r2 = r11.get(r8)
                com.jingdong.common.PersonalDownloadImageEntity r2 = (com.jingdong.common.PersonalDownloadImageEntity) r2
                java.lang.String r9 = r2.getDownloadUrl()
                com.jingdong.common.utils.DownloadImageUtils$1 r10 = new com.jingdong.common.utils.DownloadImageUtils$1
                r2 = r10
                r3 = r12
                r4 = r11
                r5 = r8
                r6 = r0
                r7 = r1
                r2.<init>()
                downloadImage(r9, r10)
                int r8 = r8 + 1
                goto L15
            L32:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.DownloadImageUtils.downloadImage(java.util.List, com.jingdong.common.utils.DownloadImageUtils$DownloadImageListener):void");
        }

        private static void downloadImage(String str, HttpGroup.HttpTaskListener httpTaskListener) {
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setUrl(str);
            httpSetting.setConnectTimeout(5000);
            httpSetting.setAttempts(1);
            httpSetting.setCacheMode(0);
            httpSetting.setType(5000);
            httpSetting.setListener(httpTaskListener);
            httpSetting.setNeedShareImage(false);
            httpSetting.setLocalFileCacheTime(2592000000L);
            HttpGroupUtils.getHttpGroupaAsynPool(5000).add(httpSetting);
        }
    }
