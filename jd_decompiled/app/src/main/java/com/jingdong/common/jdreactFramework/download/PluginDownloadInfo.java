package com.jingdong.common.jdreactFramework.download;

import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.download.JDReactHttpSetting;
import com.jingdong.common.jdreactFramework.utils.JDReactCustomException;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.ReactSharedPreferenceUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
public class PluginDownloadInfo implements PluginListenerNew, Comparable<PluginDownloadInfo> {
    public static final String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDcyO45Dxmc3i8HGYqhjpqE3n3xE7ophEA+dQYiczEV4PXeXzY8AxRfIQ0z3UIG1VvV0NDYBGP0XalKs5fDX9Dj5OBQvaku2wgr1XBBc7dEOZkizu0mIqJ0Eo4V4lMg0ka9DdMYGaW4clTg6UG8GxzxX5Vwjc4tyI2pp/udueBHTQIDAQAB";
    private static final String TAG = "PluginDownloadInfo";
    public static ArrayList<Map> sDownReportList = new ArrayList<>();
    private JDReactHttpSetting httpSetting;
    private PluginUpdateInfo pluginResult;
    private int priority = 0;
    private int background = 0;
    private ArrayList<PluginListenerNew> pluginListeners = new ArrayList<>();
    private ArrayList<PluginListener> pluginListenersOld = new ArrayList<>();

    public static final String bytesToHexString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length);
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                stringBuffer.append(0);
            }
            stringBuffer.append(hexString.toUpperCase());
        }
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadNormalBundle(String str) {
        ReactNativeUpdateManager.getInstance().addForceDownloadTask(ReactVersionDiff.getReactDownloadInfoByModuleName(str, false));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postException(String str, Throwable th) {
        postException(str, th, "0.0");
    }

    public JDReactHttpSetting createHttpSetting(PluginUpdateInfo pluginUpdateInfo) {
        return createHttpSetting(pluginUpdateInfo, false);
    }

    public JDReactHttpSetting createHttpSettingforDebug(final PluginUpdateInfo pluginUpdateInfo) {
        JDReactHelper newInstance = JDReactHelper.newInstance();
        JDReactFileGuider jDReactFileGuider = new JDReactFileGuider();
        final String str = pluginUpdateInfo.pluginUpdateName;
        final String str2 = pluginUpdateInfo.pluginUpdateVersion;
        final double d = pluginUpdateInfo.PluginId;
        String str3 = pluginUpdateInfo.realDownloadUrl;
        boolean z = pluginUpdateInfo.downType == 0;
        final String str4 = pluginUpdateInfo.pluginCommonVersion;
        final String str5 = !TextUtils.isEmpty(pluginUpdateInfo.realPatchMd5) ? pluginUpdateInfo.realPatchMd5 : z ? pluginUpdateInfo.zipPathSignature : pluginUpdateInfo.zipSplitPathSignature;
        JDReactConstant.ReactDownloadPath.getAbsolutePath();
        JDReactConstant.FLUTTERDownloadPath.getAbsolutePath();
        jDReactFileGuider.setSpace(newInstance.getSpace("DOWNLOAD"));
        jDReactFileGuider.setImmutable(false);
        jDReactFileGuider.setFileName(str.concat(JDReactConstant.DOWNLOAD_TMPFILE_SUFFIX));
        final String str6 = str + "---" + pluginUpdateInfo.pluginUpdateVersion;
        JLog.d(TAG, "React native download url is " + str3);
        ReactSharedPreferenceUtils.putDownLoadingStatus(str, z, str4, JDReactConstant.IN_PROGRESS);
        JDReactHttpSetting httpSetting = newInstance.getHttpSetting();
        httpSetting.setUrl(str3);
        httpSetting.setCacheMode(newInstance.getCacheMode("DOWNLOAD"));
        httpSetting.setNotifyUser(false);
        httpSetting.setEffect(newInstance.getEffect("DOWNLOAD"));
        if (TextUtils.isEmpty(str)) {
            httpSetting.setReferer(JDReactConstant.NET_REQUEST_FLAG_DOWNLOAD);
        } else {
            httpSetting.setReferer("Download_JDreact_" + str);
        }
        final boolean z2 = z;
        httpSetting.setListener(new JDReactHttpSetting.HttpCallback
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x00cf: INVOKE 
              (r2v3 'httpSetting' com.jingdong.common.jdreactFramework.download.JDReactHttpSetting)
              (wrap: com.jingdong.common.jdreactFramework.download.JDReactHttpSetting$HttpCallback : 0x00cc: CONSTRUCTOR 
              (r18v0 'this' com.jingdong.common.jdreactFramework.download.PluginDownloadInfo A[IMMUTABLE_TYPE, THIS])
              (r2v4 'z2' boolean A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r3v0 'str' java.lang.String A[DONT_INLINE])
              (r19v0 'pluginUpdateInfo' com.jingdong.common.jdreactFramework.download.PluginUpdateInfo A[DONT_INLINE])
              (r6v0 'str5' java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r10 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r7v0 'str2' java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r8v0 'd' double A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r14v2 'str6' java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r11v0 'str4' java.lang.String A[DONT_INLINE])
             A[MD:(com.jingdong.common.jdreactFramework.download.PluginDownloadInfo, boolean, java.lang.String, com.jingdong.common.jdreactFramework.download.PluginUpdateInfo, java.lang.String, java.lang.String, java.lang.String, double, java.lang.String, java.lang.String):void (m), WRAPPED] (LINE:26) call: com.jingdong.common.jdreactFramework.download.PluginDownloadInfo.1.<init>(com.jingdong.common.jdreactFramework.download.PluginDownloadInfo, boolean, java.lang.String, com.jingdong.common.jdreactFramework.download.PluginUpdateInfo, java.lang.String, java.lang.String, java.lang.String, double, java.lang.String, java.lang.String):void type: CONSTRUCTOR)
             type: VIRTUAL call: com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.setListener(com.jingdong.common.jdreactFramework.download.JDReactHttpSetting$HttpCallback):void A[MD:(com.jingdong.common.jdreactFramework.download.JDReactHttpSetting$HttpCallback):void (m)] (LINE:26) in method: com.jingdong.common.jdreactFramework.download.PluginDownloadInfo.createHttpSettingforDebug(com.jingdong.common.jdreactFramework.download.PluginUpdateInfo):com.jingdong.common.jdreactFramework.download.JDReactHttpSetting, file: classes5.dex
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
            this = this;
            r4 = r19
            com.jingdong.common.jdreactFramework.JDReactHelper r12 = com.jingdong.common.jdreactFramework.JDReactHelper.newInstance()
            com.jingdong.common.jdreactFramework.download.JDReactFileGuider r13 = new com.jingdong.common.jdreactFramework.download.JDReactFileGuider
            r13.<init>()
            java.lang.String r3 = r4.pluginUpdateName
            java.lang.String r7 = r4.pluginUpdateVersion
            double r8 = r4.PluginId
            java.lang.String r0 = r4.realDownloadUrl
            int r1 = r4.downType
            r2 = 0
            if (r1 != 0) goto L1a
            r5 = 1
            goto L1b
        L1a:
            r5 = 0
        L1b:
            java.lang.String r11 = r4.pluginCommonVersion
            java.lang.String r1 = r4.realPatchMd5
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L29
            java.lang.String r1 = r4.realPatchMd5
        L27:
            r6 = r1
            goto L31
        L29:
            if (r5 == 0) goto L2e
            java.lang.String r1 = r4.zipPathSignature
            goto L27
        L2e:
            java.lang.String r1 = r4.zipSplitPathSignature
            goto L27
        L31:
            java.io.File r1 = com.jingdong.common.jdreactFramework.JDReactConstant.ReactDownloadPath
            java.lang.String r10 = r1.getAbsolutePath()
            java.io.File r1 = com.jingdong.common.jdreactFramework.JDReactConstant.FLUTTERDownloadPath
            r1.getAbsolutePath()
            java.lang.String r15 = "DOWNLOAD"
            int r1 = r12.getSpace(r15)
            r13.setSpace(r1)
            r13.setImmutable(r2)
            java.lang.String r1 = ".tmp.download"
            java.lang.String r1 = r3.concat(r1)
            r13.setFileName(r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            java.lang.String r14 = "---"
            r1.append(r14)
            java.lang.String r14 = r4.pluginUpdateVersion
            r1.append(r14)
            java.lang.String r14 = r1.toString()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "React native download url is "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "PluginDownloadInfo"
            com.jingdong.common.jdreactFramework.utils.JLog.d(r2, r1)
            java.lang.String r1 = "progressing"
            com.jingdong.common.jdreactFramework.utils.ReactSharedPreferenceUtils.putDownLoadingStatus(r3, r5, r11, r1)
            com.jingdong.common.jdreactFramework.download.JDReactHttpSetting r2 = r12.getHttpSetting()
            r2.setUrl(r0)
            int r0 = r12.getCacheMode(r15)
            r2.setCacheMode(r0)
            r0 = 0
            r2.setNotifyUser(r0)
            int r0 = r12.getEffect(r15)
            r2.setEffect(r0)
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 == 0) goto La7
            java.lang.String r0 = "Download_JDreact"
            r2.setReferer(r0)
            goto Lbb
        La7:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Download_JDreact_"
            r0.append(r1)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            r2.setReferer(r0)
        Lbb:
            com.jingdong.common.jdreactFramework.download.PluginDownloadInfo$1 r1 = new com.jingdong.common.jdreactFramework.download.PluginDownloadInfo$1
            r0 = r1
            r16 = r13
            r13 = r1
            r1 = r18
            r17 = r12
            r12 = r2
            r2 = r5
            r4 = r19
            r5 = r6
            r6 = r10
            r10 = r14
            r0.<init>()
            r12.setListener(r13)
            r0 = r17
            int r0 = r0.getType(r15)
            r12.setType(r0)
            r0 = r16
            r12.setSavePath(r0)
            r0 = 1
            r12.setAttempts(r0)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdreactFramework.download.PluginDownloadInfo.createHttpSettingforDebug(com.jingdong.common.jdreactFramework.download.PluginUpdateInfo):com.jingdong.common.jdreactFramework.download.JDReactHttpSetting");
    }

    public JDReactHttpSetting getHttpSetting() {
        return this.httpSetting;
    }

    public PluginUpdateInfo getPluginResult() {
        return this.pluginResult;
    }

    @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
    public synchronized void onDownloadProgressChanged(int i2) {
        int size = this.pluginListeners.size();
        for (int i3 = 0; i3 < size; i3++) {
            PluginListenerNew pluginListenerNew = this.pluginListeners.get(i3);
            if (pluginListenerNew != null) {
                pluginListenerNew.onDownloadProgressChanged(i2);
            }
        }
        int size2 = this.pluginListenersOld.size();
        for (int i4 = 0; i4 < size2; i4++) {
            PluginListener pluginListener = this.pluginListenersOld.get(i4);
            if (pluginListener != null) {
                pluginListener.onDownloadProgressChanged(i2);
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
    public synchronized void onFailure(String str, String str2, boolean z, String str3) {
        ReactSharedPreferenceUtils.putDownLoadingStatus(str2, z, str3, JDReactConstant.FAILED);
        ArrayList arrayList = new ArrayList(Arrays.asList(new Integer[this.pluginListeners.size()]));
        Collections.copy(arrayList, this.pluginListeners);
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            PluginListenerNew pluginListenerNew = (PluginListenerNew) arrayList.get(i2);
            if (pluginListenerNew != null) {
                pluginListenerNew.onFailure(str, str2, z, str3);
            }
        }
        arrayList.clear();
        ArrayList arrayList2 = new ArrayList(Arrays.asList(new Integer[this.pluginListenersOld.size()]));
        Collections.copy(arrayList2, this.pluginListenersOld);
        int size2 = arrayList2.size();
        for (int i3 = 0; i3 < size2; i3++) {
            PluginListener pluginListener = (PluginListener) arrayList2.get(i3);
            if (pluginListener != null) {
                pluginListener.onFailure(str);
            }
        }
        arrayList2.clear();
    }

    @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
    public synchronized void onFinish(PluginUpdateInfo pluginUpdateInfo) {
        JLog.d(TAG, "onFinish notify:" + pluginUpdateInfo.toString());
        ArrayList arrayList = new ArrayList(Arrays.asList(new Integer[this.pluginListeners.size()]));
        Collections.copy(arrayList, this.pluginListeners);
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            PluginListenerNew pluginListenerNew = (PluginListenerNew) arrayList.get(i2);
            if (pluginListenerNew != null) {
                pluginListenerNew.onFinish(pluginUpdateInfo);
            }
        }
        arrayList.clear();
        ArrayList arrayList2 = new ArrayList(Arrays.asList(new Integer[this.pluginListenersOld.size()]));
        Collections.copy(arrayList2, this.pluginListenersOld);
        int size2 = arrayList2.size();
        for (int i3 = 0; i3 < size2; i3++) {
            PluginListener pluginListener = (PluginListener) arrayList2.get(i3);
            if (pluginListener != null) {
                pluginListener.onFinish(pluginUpdateInfo);
            }
        }
        arrayList2.clear();
    }

    public synchronized void regisiterListener(PluginListenerNew pluginListenerNew) {
        if (pluginListenerNew != null) {
            this.pluginListeners.add(pluginListenerNew);
        }
    }

    public void setHttpSetting(JDReactHttpSetting jDReactHttpSetting) {
        this.httpSetting = jDReactHttpSetting;
    }

    public void setPluginResult(PluginUpdateInfo pluginUpdateInfo) {
        this.pluginResult = pluginUpdateInfo;
    }

    public void setPriority(int i2) {
        this.priority = i2;
    }

    public String toString() {
        return "PluginDownloadInfo{pluginResult=" + this.pluginResult + '}';
    }

    public synchronized void unregisiterListener(PluginListenerNew pluginListenerNew) {
        JLog.d(TAG, "unregisiterListener" + pluginListenerNew);
        Iterator<PluginListenerNew> it = this.pluginListeners.iterator();
        while (it.hasNext()) {
            if (pluginListenerNew == it.next()) {
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postException(String str, Throwable th, String str2) {
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put("moduleName", "RNPluginDownload");
        arrayMap.put("appName", "RNPluginDownload");
        arrayMap.put("moduleVersion", str2);
        JDReactHelper.newInstance().postException(new JDReactCustomException(str, th), arrayMap);
    }

    @Override // java.lang.Comparable
    public int compareTo(PluginDownloadInfo pluginDownloadInfo) {
        if (pluginDownloadInfo == null) {
            return 1;
        }
        return this.priority - pluginDownloadInfo.priority;
    }

    public JDReactHttpSetting createHttpSetting(final PluginUpdateInfo pluginUpdateInfo, boolean z) {
        JDReactHelper newInstance = JDReactHelper.newInstance();
        JDReactFileGuider jDReactFileGuider = new JDReactFileGuider();
        final String str = pluginUpdateInfo.pluginUpdateName;
        String str2 = pluginUpdateInfo.realDownloadUrl;
        boolean z2 = pluginUpdateInfo.downType == 0;
        final String str3 = pluginUpdateInfo.pluginCommonVersion;
        final String str4 = !TextUtils.isEmpty(pluginUpdateInfo.realPatchMd5) ? pluginUpdateInfo.realPatchMd5 : z2 ? pluginUpdateInfo.zipPathSignature : pluginUpdateInfo.zipSplitPathSignature;
        JDReactConstant.ReactDownloadPath.getAbsolutePath();
        jDReactFileGuider.setSpace(newInstance.getSpace("DOWNLOAD"));
        jDReactFileGuider.setImmutable(false);
        jDReactFileGuider.setFileName((str + System.currentTimeMillis()).concat(JDReactConstant.DOWNLOAD_TMPFILE_SUFFIX));
        final String str5 = str + "---" + pluginUpdateInfo.pluginUpdateVersion;
        JLog.d(TAG, "React native download url is " + str2);
        ReactSharedPreferenceUtils.putDownLoadingStatus(str, z2, str3, JDReactConstant.IN_PROGRESS);
        JDReactHttpSetting httpSetting = newInstance.getHttpSetting();
        if (TextUtils.isEmpty(str)) {
            httpSetting.setReferer(JDReactConstant.NET_REQUEST_FLAG_DOWNLOAD);
        } else {
            httpSetting.setReferer("Download_JDreact_" + str);
        }
        httpSetting.setUrl(str2);
        httpSetting.setCacheMode(newInstance.getCacheMode("DOWNLOAD"));
        httpSetting.setNotifyUser(false);
        httpSetting.setEffect(newInstance.getEffect("DOWNLOAD"));
        final boolean z3 = z2;
        httpSetting.setListener(new JDReactHttpSetting.HttpCallback
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x00d3: INVOKE 
              (r14v2 'httpSetting' com.jingdong.common.jdreactFramework.download.JDReactHttpSetting)
              (wrap: com.jingdong.common.jdreactFramework.download.JDReactHttpSetting$HttpCallback : 0x00d0: CONSTRUCTOR 
              (r16v0 'this' com.jingdong.common.jdreactFramework.download.PluginDownloadInfo A[IMMUTABLE_TYPE, THIS])
              (r2v1 'z3' boolean A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r3v0 'str' java.lang.String A[DONT_INLINE])
              (r17v0 'pluginUpdateInfo' com.jingdong.common.jdreactFramework.download.PluginUpdateInfo A[DONT_INLINE])
              (r6v0 'str4' java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r7 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r13v4 'str5' java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r8v0 'str3' java.lang.String A[DONT_INLINE])
             A[MD:(com.jingdong.common.jdreactFramework.download.PluginDownloadInfo, boolean, java.lang.String, com.jingdong.common.jdreactFramework.download.PluginUpdateInfo, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void (m), WRAPPED] (LINE:24) call: com.jingdong.common.jdreactFramework.download.PluginDownloadInfo.2.<init>(com.jingdong.common.jdreactFramework.download.PluginDownloadInfo, boolean, java.lang.String, com.jingdong.common.jdreactFramework.download.PluginUpdateInfo, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void type: CONSTRUCTOR)
             type: VIRTUAL call: com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.setListener(com.jingdong.common.jdreactFramework.download.JDReactHttpSetting$HttpCallback):void A[MD:(com.jingdong.common.jdreactFramework.download.JDReactHttpSetting$HttpCallback):void (m)] (LINE:24) in method: com.jingdong.common.jdreactFramework.download.PluginDownloadInfo.createHttpSetting(com.jingdong.common.jdreactFramework.download.PluginUpdateInfo, boolean):com.jingdong.common.jdreactFramework.download.JDReactHttpSetting, file: classes5.dex
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
            this = this;
            r4 = r17
            com.jingdong.common.jdreactFramework.JDReactHelper r9 = com.jingdong.common.jdreactFramework.JDReactHelper.newInstance()
            com.jingdong.common.jdreactFramework.download.JDReactFileGuider r10 = new com.jingdong.common.jdreactFramework.download.JDReactFileGuider
            r10.<init>()
            java.lang.String r3 = r4.pluginUpdateName
            java.lang.String r0 = r4.realDownloadUrl
            int r1 = r4.downType
            r11 = 1
            r2 = 0
            if (r1 != 0) goto L17
            r5 = 1
            goto L18
        L17:
            r5 = 0
        L18:
            java.lang.String r8 = r4.pluginCommonVersion
            java.lang.String r1 = r4.realPatchMd5
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L26
            java.lang.String r1 = r4.realPatchMd5
        L24:
            r6 = r1
            goto L2e
        L26:
            if (r5 == 0) goto L2b
            java.lang.String r1 = r4.zipPathSignature
            goto L24
        L2b:
            java.lang.String r1 = r4.zipSplitPathSignature
            goto L24
        L2e:
            java.io.File r1 = com.jingdong.common.jdreactFramework.JDReactConstant.ReactDownloadPath
            java.lang.String r7 = r1.getAbsolutePath()
            java.lang.String r12 = "DOWNLOAD"
            int r1 = r9.getSpace(r12)
            r10.setSpace(r1)
            r10.setImmutable(r2)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            long r13 = java.lang.System.currentTimeMillis()
            r1.append(r13)
            java.lang.String r1 = r1.toString()
            java.lang.String r13 = ".tmp.download"
            java.lang.String r1 = r1.concat(r13)
            r10.setFileName(r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            java.lang.String r13 = "---"
            r1.append(r13)
            java.lang.String r13 = r4.pluginUpdateVersion
            r1.append(r13)
            java.lang.String r13 = r1.toString()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r14 = "React native download url is "
            r1.append(r14)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            java.lang.String r14 = "PluginDownloadInfo"
            com.jingdong.common.jdreactFramework.utils.JLog.d(r14, r1)
            java.lang.String r1 = "progressing"
            com.jingdong.common.jdreactFramework.utils.ReactSharedPreferenceUtils.putDownLoadingStatus(r3, r5, r8, r1)
            com.jingdong.common.jdreactFramework.download.JDReactHttpSetting r14 = r9.getHttpSetting()
            boolean r1 = android.text.TextUtils.isEmpty(r3)
            if (r1 == 0) goto L9d
            java.lang.String r1 = "Download_JDreact"
            r14.setReferer(r1)
            goto Lb1
        L9d:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r15 = "Download_JDreact_"
            r1.append(r15)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            r14.setReferer(r1)
        Lb1:
            r14.setUrl(r0)
            int r0 = r9.getCacheMode(r12)
            r14.setCacheMode(r0)
            r14.setNotifyUser(r2)
            int r0 = r9.getEffect(r12)
            r14.setEffect(r0)
            com.jingdong.common.jdreactFramework.download.PluginDownloadInfo$2 r15 = new com.jingdong.common.jdreactFramework.download.PluginDownloadInfo$2
            r0 = r15
            r1 = r16
            r2 = r5
            r4 = r17
            r5 = r6
            r6 = r7
            r7 = r13
            r0.<init>()
            r14.setListener(r15)
            int r0 = r9.getType(r12)
            r14.setType(r0)
            r14.setSavePath(r10)
            if (r18 != 0) goto Le6
            r14.setAttempts(r11)
            goto Lea
        Le6:
            r0 = 5
            r14.setAttempts(r0)
        Lea:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdreactFramework.download.PluginDownloadInfo.createHttpSetting(com.jingdong.common.jdreactFramework.download.PluginUpdateInfo, boolean):com.jingdong.common.jdreactFramework.download.JDReactHttpSetting");
    }

    public synchronized void regisiterListener(PluginListener pluginListener) {
        if (pluginListener != null) {
            this.pluginListenersOld.add(pluginListener);
        }
    }

    public synchronized void unregisiterListener(PluginListener pluginListener) {
        JLog.d(TAG, "unregisiterListener" + pluginListener);
        Iterator<PluginListener> it = this.pluginListenersOld.iterator();
        while (it.hasNext()) {
            if (pluginListener == it.next()) {
                it.remove();
            }
        }
    }
}
