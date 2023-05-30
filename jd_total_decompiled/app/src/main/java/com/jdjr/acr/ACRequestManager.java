package com.jdjr.acr;

import android.content.Context;
import android.text.TextUtils;
import com.jdjr.identify.IdentifyListener;
import com.jdjr.identify.IdentifyManager;
import com.jdjr.securehttp.HttpHandler;
import com.jdjr.tools.CommonTools;
import com.jdjr.tools.JDJRLog;
import com.jdjr.tools.JDJRSecureUtils;
import com.jdjr.tools.TaskCacheThreadPool;
import com.wangyin.platform.ACMUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes18.dex */
public class ACRequestManager implements IdentifyListener {
    private static final String TAG = "ACRequestManager";
    private static ACRequestManager instance;
    private static final Object lock = new Object();
    private AntiHookManager antiHookMgr;
    private ACMUtil mACMUtil;
    private Context mContext;
    private String mDeviceId;
    private HttpHandler mHttpHandler;
    private String mUserId;
    private String jdPin = "";
    private boolean hasPostInfoWithJdPin = false;
    private ArrayList<JavaMethod> concernedJavaMethods = new ArrayList<>();

    private ACRequestManager(Context context, String str, String str2) {
        this.mACMUtil = null;
        this.mHttpHandler = null;
        this.antiHookMgr = null;
        this.mHttpHandler = new HttpHandler();
        this.antiHookMgr = AntiHookManager.newInstance(context);
        this.mContext = context;
        this.mUserId = str;
        this.mDeviceId = str2;
        ACMUtil newInstance = ACMUtil.newInstance(context);
        this.mACMUtil = newInstance;
        newInstance.InitACMsg();
        setJdPin(IdentifyManager.getInstance().addListener(this));
    }

    public static ACRequestManager newInstance(Context context, String str, String str2) {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ACRequestManager(context, str, str2);
                }
            }
        }
        return instance;
    }

    private void setJdPin(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JDJRLog.i(TAG, "set JDPin:" + str);
        this.jdPin = str;
        if (TextUtils.isEmpty(str) || this.hasPostInfoWithJdPin) {
            return;
        }
        this.hasPostInfoWithJdPin = true;
        ACRequest();
    }

    public byte[] ACRequest() {
        String serverCert = CommonTools.getServerCert();
        String str = this.mUserId;
        if (str == null || str.length() == 0) {
            this.mUserId = "UserId";
        }
        String str2 = this.mDeviceId;
        if (str2 == null || str2.length() == 0) {
            this.mDeviceId = "DeviceId";
        }
        final String str3 = this.jdPin;
        byte[] GetACMsg = this.mACMUtil.GetACMsg(this.mContext, serverCert, this.mUserId, this.mDeviceId, str3, this.concernedJavaMethods);
        if (new String(JDJRSecureUtils.getErrorCode(GetACMsg)).equals("00000")) {
            final byte[] retData = JDJRSecureUtils.getRetData(GetACMsg);
            CommonTools.getACMAddress();
            TaskCacheThreadPool.getInstance().execute(new Runnable
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0053: INVOKE 
                  (wrap: com.jdjr.tools.TaskCacheThreadPool : 0x004f: INVOKE  type: STATIC call: com.jdjr.tools.TaskCacheThreadPool.getInstance():com.jdjr.tools.TaskCacheThreadPool A[MD:():com.jdjr.tools.TaskCacheThreadPool (m), WRAPPED] (LINE:13))
                  (wrap: java.lang.Runnable : 0x004c: CONSTRUCTOR 
                  (r8v0 'this' com.jdjr.acr.ACRequestManager A[IMMUTABLE_TYPE, THIS])
                  (r0v7 'retData' byte[] A[DONT_INLINE])
                  (r1 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r7v0 'str3' java.lang.String A[DONT_INLINE])
                 A[MD:(com.jdjr.acr.ACRequestManager, byte[], java.lang.String, java.lang.String):void (m), WRAPPED] (LINE:12) call: com.jdjr.acr.ACRequestManager.1.<init>(com.jdjr.acr.ACRequestManager, byte[], java.lang.String, java.lang.String):void type: CONSTRUCTOR)
                 type: VIRTUAL call: com.jdjr.tools.TaskCacheThreadPool.execute(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (m)] (LINE:13) in method: com.jdjr.acr.ACRequestManager.ACRequest():byte[], file: classes18.dex
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
                	... 23 more
                */
            /*
                this = this;
                java.lang.String r2 = com.jdjr.tools.CommonTools.getServerCert()
                java.lang.String r0 = r8.mUserId
                if (r0 == 0) goto Le
                int r0 = r0.length()
                if (r0 != 0) goto L12
            Le:
                java.lang.String r0 = "UserId"
                r8.mUserId = r0
            L12:
                java.lang.String r0 = r8.mDeviceId
                if (r0 == 0) goto L1c
                int r0 = r0.length()
                if (r0 != 0) goto L20
            L1c:
                java.lang.String r0 = "DeviceId"
                r8.mDeviceId = r0
            L20:
                java.lang.String r7 = r8.jdPin
                com.wangyin.platform.ACMUtil r0 = r8.mACMUtil
                android.content.Context r1 = r8.mContext
                java.lang.String r3 = r8.mUserId
                java.lang.String r4 = r8.mDeviceId
                java.util.ArrayList<com.jdjr.acr.JavaMethod> r6 = r8.concernedJavaMethods
                r5 = r7
                byte[] r0 = r0.GetACMsg(r1, r2, r3, r4, r5, r6)
                byte[] r1 = com.jdjr.tools.JDJRSecureUtils.getErrorCode(r0)
                java.lang.String r2 = new java.lang.String
                r2.<init>(r1)
                java.lang.String r1 = "00000"
                boolean r1 = r2.equals(r1)
                if (r1 == 0) goto L57
                byte[] r0 = com.jdjr.tools.JDJRSecureUtils.getRetData(r0)
                java.lang.String r1 = com.jdjr.tools.CommonTools.getACMAddress()
                com.jdjr.acr.ACRequestManager$1 r2 = new com.jdjr.acr.ACRequestManager$1
                r2.<init>()
                com.jdjr.tools.TaskCacheThreadPool r1 = com.jdjr.tools.TaskCacheThreadPool.getInstance()
                r1.execute(r2)
                return r0
            L57:
                r0 = 0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jdjr.acr.ACRequestManager.ACRequest():byte[]");
        }

        public byte[] ACRequest_Camera(ArrayList<JavaMethod> arrayList) {
            String serverCert = CommonTools.getServerCert();
            String str = this.mUserId;
            if (str == null || str.length() == 0) {
                this.mUserId = "UserId";
            }
            String str2 = this.mDeviceId;
            if (str2 == null || str2.length() == 0) {
                this.mDeviceId = "DeviceId";
            }
            byte[] checkCameraHook = this.mACMUtil.checkCameraHook(this.mContext, arrayList, serverCert, this.mUserId, this.mDeviceId, this.jdPin);
            if (new String(JDJRSecureUtils.getErrorCode(checkCameraHook)).equals("00000")) {
                return JDJRSecureUtils.getRetData(checkCameraHook);
            }
            return null;
        }

        public void setConcernedJavaMethods(List<JavaMethod> list) {
            if (list != null) {
                this.concernedJavaMethods.clear();
                this.concernedJavaMethods.addAll(list);
            }
        }

        @Override // com.jdjr.identify.IdentifyListener
        public void updateJdPIN(String str) {
            setJdPin(str);
        }
    }
