package com.jingdong.remoteimage;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.jdsdk.security.DesCbcCrypto;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.utils.JDReminderNewUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.utils.PackageInfoUtil;
import com.jingdong.sdk.utils.b.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public class DiffMapManger {
    private static final String TAG = " RemoteImage";
    private static volatile DiffMapManger defaultInstance;
    static RemoteBridge sRemoteBridge;
    private final byte[] DESIV;
    private final char[] LASTKEY;
    private final String clientVersion;
    private HashMap<String, String> dataVersionMap;
    private ConcurrentHashMap<String, String> diffConcurrentHashMap;
    private final String diffFileName;
    private String host;
    private volatile String imgPrefix;
    private volatile boolean isChecking;
    private final String key;
    private long refreshTime;
    private long startTime;
    private final String suffix;

    private DiffMapManger() {
        byte[] bytes = "ilove618".getBytes();
        this.DESIV = bytes;
        char[] cArr = {'C', '8', '8', 'D', '9'};
        this.LASTKEY = cArr;
        this.startTime = 0L;
        this.refreshTime = JDReminderNewUtils.REMINDER_DURATION_TIME_MAX;
        this.diffConcurrentHashMap = new ConcurrentHashMap<>();
        this.isChecking = false;
        this.imgPrefix = "https://m.360buyimg.com/mobilecms/";
        this.suffix = ".xjs";
        String str = AppUtil.getApplication().getString(R.string.start_key) + cArr[0] + cArr[1] + cArr[3] + cArr[4] + AppUtil.getApplication().getString(R.string.end_key);
        this.key = str;
        this.refreshTime = SharedPreferenceHelp.getDefault(AppUtil.getApplication()).getLong("refreshTime", this.refreshTime);
        String decrypt = DesCbcCrypto.decrypt(SharedPreferenceHelp.getDefault(AppUtil.getApplication()).getString("remoteimageHost", ""), str, bytes);
        if (!TextUtils.isEmpty(decrypt)) {
            this.imgPrefix = decrypt;
        }
        String versionName = PackageInfoUtil.getVersionName(AppUtil.getApplication());
        this.clientVersion = versionName;
        this.diffFileName = FileUtil.getAppFilesPath(AppUtil.getApplication()) + File.separator + versionName + "_diffimage.xjs";
        readDiffCache();
    }

    public static DiffMapManger getDefault() {
        DiffMapManger diffMapManger = defaultInstance;
        if (diffMapManger == null) {
            synchronized (DiffMapManger.class) {
                diffMapManger = defaultInstance;
                if (diffMapManger == null) {
                    diffMapManger = new DiffMapManger();
                    defaultInstance = diffMapManger;
                }
            }
        }
        return diffMapManger;
    }

    private void getHttpInfo() {
        RemoteBridge remoteBridge = sRemoteBridge;
        if (remoteBridge == null || !remoteBridge.isXTime()) {
            System.currentTimeMillis();
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(this.host);
            httpSetting.setFunctionId("clientImage");
            httpSetting.setEffect(0);
            httpSetting.setUseFastJsonParser(true);
            httpSetting.putQueryParam("client", a.a());
            httpSetting.putQueryParam(HybridSDK.APP_VERSION, this.clientVersion);
            initAssetFileDataVersion();
            HashMap<String, String> hashMap = this.dataVersionMap;
            if (hashMap != null) {
                httpSetting.putJsonParam("moduleParams", hashMap);
            }
            httpSetting.putJsonParam("scale", RemoteImageManager.getScreen());
            httpSetting.setListener(new HttpGroup.OnAllListener
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0050: INVOKE 
                  (r2v0 'httpSetting' com.jingdong.jdsdk.network.toolbox.HttpSetting)
                  (wrap: com.jingdong.jdsdk.network.toolbox.HttpGroup$OnAllListener : 0x004d: CONSTRUCTOR 
                  (r5v0 'this' com.jingdong.remoteimage.DiffMapManger A[IMMUTABLE_TYPE, THIS])
                  (r0 I:long A[DONT_GENERATE, DONT_INLINE, REMOVE])
                 A[MD:(com.jingdong.remoteimage.DiffMapManger, long):void (m), WRAPPED] (LINE:14) call: com.jingdong.remoteimage.DiffMapManger.2.<init>(com.jingdong.remoteimage.DiffMapManger, long):void type: CONSTRUCTOR)
                 type: VIRTUAL call: com.jingdong.jdsdk.network.toolbox.HttpSetting.setListener(com.jingdong.jdsdk.network.toolbox.HttpGroup$HttpTaskListener):void A[MD:(com.jingdong.jdsdk.network.toolbox.HttpGroup$HttpTaskListener):void (m)] (LINE:14) in method: com.jingdong.remoteimage.DiffMapManger.getHttpInfo():void, file: classes7.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
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
                com.jingdong.remoteimage.RemoteBridge r0 = com.jingdong.remoteimage.DiffMapManger.sRemoteBridge
                if (r0 == 0) goto Lb
                boolean r0 = r0.isXTime()
                if (r0 == 0) goto Lb
                return
            Lb:
                long r0 = java.lang.System.currentTimeMillis()
                com.jingdong.jdsdk.network.toolbox.HttpSetting r2 = new com.jingdong.jdsdk.network.toolbox.HttpSetting
                r2.<init>()
                java.lang.String r3 = r5.host
                r2.setHost(r3)
                java.lang.String r3 = "clientImage"
                r2.setFunctionId(r3)
                r3 = 0
                r2.setEffect(r3)
                r3 = 1
                r2.setUseFastJsonParser(r3)
                java.lang.String r3 = com.jingdong.sdk.utils.b.a.a()
                java.lang.String r4 = "client"
                r2.putQueryParam(r4, r3)
                java.lang.String r3 = r5.clientVersion
                java.lang.String r4 = "clientVersion"
                r2.putQueryParam(r4, r3)
                r5.initAssetFileDataVersion()
                java.util.HashMap<java.lang.String, java.lang.String> r3 = r5.dataVersionMap
                if (r3 == 0) goto L42
                java.lang.String r4 = "moduleParams"
                r2.putJsonParam(r4, r3)
            L42:
                java.lang.String r3 = com.jingdong.remoteimage.RemoteImageManager.getScreen()
                java.lang.String r4 = "scale"
                r2.putJsonParam(r4, r3)
                com.jingdong.remoteimage.DiffMapManger$2 r3 = new com.jingdong.remoteimage.DiffMapManger$2
                r3.<init>()
                r2.setListener(r3)
                com.jingdong.jdsdk.network.toolbox.HttpGroup r0 = com.jingdong.common.network.HttpGroupUtils.getHttpGroupaAsynPool()
                r0.add(r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.remoteimage.DiffMapManger.getHttpInfo():void");
        }

        public synchronized void initAssetFileDataVersion() {
            String[] split;
            if (this.dataVersionMap == null) {
                try {
                    String[] list = AppUtil.getApplication().getResources().getAssets().list(Constants.REMOTE_URL_DIR);
                    this.dataVersionMap = new HashMap<>(list.length);
                    for (String str : list) {
                        if (str.endsWith(".xjs") && (split = str.substring(0, str.length() - 4).split(CartConstant.KEY_YB_INFO_LINK)) != null && split.length == 2) {
                            this.dataVersionMap.put(split[0], split[1]);
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        private void readDiffCache() {
            new Thread(new Runnable() { // from class: com.jingdong.remoteimage.DiffMapManger.1
                {
                    DiffMapManger.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    ObjectInputStream objectInputStream;
                    Exception e2;
                    Object readObject;
                    DiffMapManger.this.initAssetFileDataVersion();
                    if (FileUtil.isFileExist(DiffMapManger.this.diffFileName)) {
                        ObjectInputStream objectInputStream2 = null;
                        try {
                            try {
                                try {
                                    boolean z = Constants.DEBUG;
                                    objectInputStream = new ObjectInputStream(new FileInputStream(new File(DiffMapManger.this.diffFileName)));
                                } catch (Exception e3) {
                                    objectInputStream = null;
                                    e2 = e3;
                                } catch (Throwable th) {
                                    th = th;
                                    if (0 != 0) {
                                        try {
                                            objectInputStream2.close();
                                        } catch (IOException e4) {
                                            e4.printStackTrace();
                                        }
                                    }
                                    throw th;
                                }
                                try {
                                    readObject = objectInputStream.readObject();
                                } catch (Exception e5) {
                                    e2 = e5;
                                    boolean z2 = Constants.DEBUG;
                                    e2.printStackTrace();
                                    MtaService.sendErrMsg("remoteURL_readDiffError", e2.getMessage());
                                    if (objectInputStream != null) {
                                        objectInputStream.close();
                                    }
                                    return;
                                }
                                if (readObject == null) {
                                    objectInputStream.close();
                                    try {
                                        objectInputStream.close();
                                        return;
                                    } catch (IOException e6) {
                                        e6.printStackTrace();
                                        return;
                                    }
                                }
                                if (readObject != null && (readObject instanceof Map)) {
                                    for (Map.Entry entry : ((Map) readObject).entrySet()) {
                                        DiffMapManger.this.diffConcurrentHashMap.put((String) entry.getKey(), (String) entry.getValue());
                                    }
                                    objectInputStream.close();
                                }
                                boolean z3 = Constants.DEBUG;
                                objectInputStream.close();
                            } catch (IOException e7) {
                                e7.printStackTrace();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } else if (Constants.DEBUG) {
                        String str = DiffMapManger.this.diffFileName + " is not exist";
                    }
                }
            }).start();
        }

        public void check() {
            if (this.isChecking) {
                boolean z = Constants.DEBUG;
                return;
            }
            this.isChecking = true;
            if (this.startTime == 0) {
                boolean z2 = Constants.DEBUG;
                getHttpInfo();
            } else if ((System.currentTimeMillis() - this.startTime) / 1000 >= this.refreshTime) {
                boolean z3 = Constants.DEBUG;
                getHttpInfo();
            } else {
                boolean z4 = Constants.DEBUG;
                this.isChecking = false;
            }
        }

        public String getImageUrl(@NonNull String str) {
            if (str == null) {
                return null;
            }
            String str2 = this.diffConcurrentHashMap.get(str);
            if (TextUtils.isEmpty(str2)) {
                return null;
            }
            return str2;
        }

        public String getImgPrefix() {
            return this.imgPrefix;
        }

        public String getRemoteUrlModuleFileName(String str) {
            initAssetFileDataVersion();
            HashMap<String, String> hashMap = this.dataVersionMap;
            return hashMap != null ? String.format("%s_%s.xjs", str, hashMap.get(str)) : "";
        }

        public void setHost(String str) {
            this.host = str;
        }
    }
