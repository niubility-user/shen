package com.jingdong.common.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.basic.ShareActivity;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.appshare.R;
import com.jingdong.common.entity.ShareImageInfo;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.jdreactFramework.utils.RSAUtils;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.jdshare.utils.WeiboUtils;
import com.jingdong.sdk.jdshare.utils.e;
import com.jingdong.sdk.jdshare.utils.g;
import com.jingdong.sdk.jdshare.utils.i;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.utils.DPIUtil;
import com.jingdong.sdk.utils.c.a;
import java.io.ByteArrayOutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import javax.crypto.Cipher;
import rx.functions.Action1;

/* loaded from: classes6.dex */
public class ShareUtil {
    public static final int ACTION_BACK = 3;
    public static final int ACTION_LOTTERY = 4;
    public static final int ACTION_OPEN = 2;
    public static final int ACTION_PANEL = 1;
    public static final int BIZ_PRODUCT_DETAIL = 1;
    public static final int REQUEST_CODE = 1215;
    public static final int RESULT_BLOCK = 14;
    public static final int RESULT_CANCEL = 13;
    public static final int RESULT_CLOSE = 15;
    public static final int RESULT_ERROR = 12;
    public static final int RESULT_SUCCESS = 11;
    public static final String SEPARATOR_SIGN = "##";
    public static final String S_COPY_URL = "CopyURL";
    public static final String S_Hw_CaasShare = "HwCaasShare";
    public static final String S_JDFamily = "JDFamily";
    public static final String S_JD_SAVE_IMG = "JDSaveImg";
    public static final String S_QQ_FRIENDS = "QQfriends";
    public static final String S_QQ_ZONE = "QQzone";
    public static final String S_QRCODE = "QRCode";
    public static final String S_SINA_WEIBO = "Sinaweibo";
    public static final String S_WX_FRIENDS = "Wxfriends";
    public static final String S_WX_MOMENTS = "Wxmoments";
    private static final String TAG = "ShareUtil";
    public static String shareActivityAction = "com.jingdong.app.mall.ACTION_SHARE_UTIL_ACTIVITY";
    private static final Handler sHandler = new Handler(Looper.getMainLooper());
    public static long mLastUsedTime = 0;

    /* loaded from: classes6.dex */
    public interface CallbackListener {
        void onCancel();

        void onComplete(Object obj);

        void onError(String str);
    }

    /* loaded from: classes6.dex */
    public interface ClickCallbackListener {
        void onClick(String str);
    }

    public static String addShareUrlParam(String str, String str2, String str3) {
        if (str.contains(str2 + ContainerUtils.KEY_VALUE_DELIMITER)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.contains("?") ? ContainerUtils.FIELD_DELIMITER : "?");
        return sb.toString() + str2 + ContainerUtils.KEY_VALUE_DELIMITER + str3;
    }

    public static void backShareActivity(Activity activity, int i2, String str, String str2) {
        Intent intent = new Intent(shareActivityAction);
        intent.putExtra("action", 3);
        intent.putExtra("result", i2);
        intent.putExtra("transaction", str);
        intent.putExtra("msg", str2);
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            OKLog.d(TAG, e2);
        }
    }

    private static void bizFilter(Activity activity, ShareInfo shareInfo) {
        if (activity == null || shareInfo == null || !activity.getClass().getSimpleName().equals("ProductDetailActivity")) {
            return;
        }
        shareInfo.setBizType(1);
    }

    public static void clearJDTransferActivity(Activity activity) {
        if (activity == null || activity.isFinishing() || !activity.toString().contains("JDTransferActivity")) {
            return;
        }
        activity.finish();
    }

    public static void convertShortUrl(final String str, final Action1<String> action1) {
        final Runnable runnable = new Runnable() { // from class: com.jingdong.common.utils.ShareUtil.5
            @Override // java.lang.Runnable
            public void run() {
                action1.call(str);
            }
        };
        sHandler.postDelayed(runnable, 2100L);
        System.currentTimeMillis();
        OKLog.d(TAG, "originUrl: " + str);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setFunctionId("shortUrl");
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.putJsonParam("originUrl", str);
        httpSetting.setListener(new HttpGroup.OnAllListener
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0048: INVOKE 
              (r6v0 'httpSetting' com.jingdong.jdsdk.network.toolbox.HttpSetting)
              (wrap: com.jingdong.jdsdk.network.toolbox.HttpGroup$OnAllListener : 0x0045: CONSTRUCTOR 
              (r9v0 'action1' rx.functions.Action1<java.lang.String> A[DONT_INLINE])
              (r8v0 'str' java.lang.String A[DONT_INLINE])
              (r3 I:long A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r5v0 'runnable' java.lang.Runnable A[DONT_INLINE])
             A[MD:(rx.functions.Action1, java.lang.String, long, java.lang.Runnable):void (m), WRAPPED] (LINE:10) call: com.jingdong.common.utils.ShareUtil.6.<init>(rx.functions.Action1, java.lang.String, long, java.lang.Runnable):void type: CONSTRUCTOR)
             type: VIRTUAL call: com.jingdong.jdsdk.network.toolbox.HttpSetting.setListener(com.jingdong.jdsdk.network.toolbox.HttpGroup$HttpTaskListener):void A[MD:(com.jingdong.jdsdk.network.toolbox.HttpGroup$HttpTaskListener):void (m)] (LINE:10) in method: com.jingdong.common.utils.ShareUtil.convertShortUrl(java.lang.String, rx.functions.Action1<java.lang.String>):void, file: classes6.dex
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
            com.jingdong.common.utils.ShareUtil$5 r5 = new com.jingdong.common.utils.ShareUtil$5
            r5.<init>()
            android.os.Handler r0 = com.jingdong.common.utils.ShareUtil.sHandler
            r1 = 2100(0x834, double:1.0375E-320)
            r0.postDelayed(r5, r1)
            long r3 = java.lang.System.currentTimeMillis()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "originUrl: "
            r0.append(r1)
            r0.append(r8)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "ShareUtil"
            com.jingdong.sdk.oklog.OKLog.d(r1, r0)
            com.jingdong.jdsdk.network.toolbox.HttpSetting r6 = new com.jingdong.jdsdk.network.toolbox.HttpSetting
            r6.<init>()
            r0 = 1
            r6.setUseFastJsonParser(r0)
            java.lang.String r0 = "shortUrl"
            r6.setFunctionId(r0)
            java.lang.String r0 = com.jingdong.jdsdk.config.Configuration.getPortalHost()
            r6.setHost(r0)
            java.lang.String r0 = "originUrl"
            r6.putJsonParam(r0, r8)
            com.jingdong.common.utils.ShareUtil$6 r7 = new com.jingdong.common.utils.ShareUtil$6
            r0 = r7
            r1 = r9
            r2 = r8
            r0.<init>()
            r6.setListener(r7)
            com.jingdong.jdsdk.network.toolbox.HttpGroup r8 = com.jingdong.common.network.HttpGroupUtils.getHttpGroupaAsynPool()
            r8.add(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.ShareUtil.convertShortUrl(java.lang.String, rx.functions.Action1):void");
    }

    public static Bitmap createQRCode(String str) {
        return createQRCode(str, 256);
    }

    public static String createQRCodeToBase64(String str) {
        Bitmap createQRCode;
        return (TextUtils.isEmpty(str) || (createQRCode = createQRCode(str)) == null) ? "" : g.a(createQRCode);
    }

    @Deprecated
    public static void dealSharedPin(Bundle bundle) {
    }

    public static void dealSharedPin(Bundle bundle, String str) {
        if (bundle == null || isColdDown()) {
            return;
        }
        String string = bundle.getString("ShareTm", "");
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(str)) {
            return;
        }
        String encryptPin = encryptPin(str);
        if (TextUtils.isEmpty(encryptPin)) {
            return;
        }
        String urlEncode = urlEncode(string);
        String urlEncode2 = urlEncode(encryptPin);
        if (TextUtils.isEmpty(urlEncode) || TextUtils.isEmpty(urlEncode2)) {
            return;
        }
        String str2 = "https://" + Configuration.getWmpHost() + "/relation/save?src=" + urlEncode + "&dest=" + urlEncode2;
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(str2);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        if (OKLog.D) {
            OKLog.d(TAG, str2);
        }
    }

    public static byte[] encryptByPublicKey(byte[] bArr, String str) throws Exception {
        byte[] doFinal;
        PublicKey generatePublic = KeyFactory.getInstance(RSAUtils.KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(a.d(str)));
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, generatePublic);
        int length = bArr.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = length - i2;
            if (i4 > 0) {
                if (i4 > 117) {
                    doFinal = cipher.doFinal(bArr, i2, 117);
                } else {
                    doFinal = cipher.doFinal(bArr, i2, i4);
                }
                byteArrayOutputStream.write(doFinal, 0, doFinal.length);
                i3++;
                i2 = i3 * 117;
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            }
        }
    }

    public static String encryptPin(String str) {
        String str2 = str + System.currentTimeMillis() + ((TextUtils.isEmpty(str) || str.length() < 3) ? "abc" : str.substring(0, 3));
        if (OKLog.D) {
            OKLog.d(TAG, "encryptPin: " + str2);
        }
        try {
            byte[] encryptByPublicKey = encryptByPublicKey(str2.getBytes("utf-8"), "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4Xg//g1DKrpzNH71UFOZLH4MEi0DrBc/ODr5UjuMywf4vxPuZQOfSyN+XZCHDbMOSWvzFdOiR3DgP2lsYJn7RGfDVtFtyXpmdfO6L5qMZZ/rUEV/JF81ewNP/ho+iFjMEg0j9T69YlaMMM+jg6k5uhUJt8HsIpm5I3/8h1w6UvQIDAQAB");
            return (encryptByPublicKey == null || encryptByPublicKey.length <= 0) ? "" : a.k(encryptByPublicKey);
        } catch (Exception e2) {
            if (OKLog.E) {
                e2.printStackTrace();
                return "";
            }
            return "";
        }
    }

    public static String getShareUrl(String str, String str2) {
        if (str == null) {
            str = "";
        }
        return addShareUrlParam(addShareUrlParam(addShareUrlParam(addShareUrlParam(addShareUrlParam(str, "ad_od", "share"), "utm_source", "androidapp"), "utm_medium", "appshare"), "utm_campaign", "t_335139774"), "utm_term", str2);
    }

    public static String getShareUrlOnlyRes(String str, String str2) {
        return addShareUrlParam(addShareUrlParam(addShareUrlParam(addShareUrlParam(str, "utm_source", "androidapp"), "utm_medium", "appshare"), "utm_campaign", "t_335139774"), "utm_term", str2);
    }

    public static void init(ShareActivity shareActivity) {
        i.h();
        if (shareActivity != null) {
            WeiboUtils.register(shareActivity.getApplicationContext());
            e.e(shareActivity);
        }
    }

    public static boolean isColdDown() {
        long j2 = mLastUsedTime;
        if (j2 <= 0 || j2 + 1000 <= System.currentTimeMillis()) {
            mLastUsedTime = System.currentTimeMillis();
            return false;
        }
        return true;
    }

    public static boolean isUseSwitchQuery() {
        String config = JDMobileConfig.getInstance().getConfig("JDShare", "isUseSwitchQuery", "switchType");
        return !TextUtils.isEmpty(config) && config.equals("1");
    }

    public static void lottery(final Activity activity, final ShareInfo shareInfo, final String str, final String str2, String str3) {
        if (isColdDown()) {
            return;
        }
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            System.currentTimeMillis();
            final Handler handler = new Handler();
            final Runnable runnable = new Runnable() { // from class: com.jingdong.common.utils.ShareUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    ToastUtils.shortToast(JdSdk.getInstance().getApplicationContext(), R.string.share_failed_try_again);
                }
            };
            handler.postDelayed(runnable, 3750L);
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setUseFastJsonParser(true);
            httpSetting.setFunctionId("getShareRule");
            httpSetting.setHost(str3);
            httpSetting.putJsonParam("type", str);
            httpSetting.putJsonParam("bizId", str2);
            httpSetting.putJsonParam("channel", "2");
            httpSetting.setListener(new HttpGroup.OnEndListener
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0053: INVOKE 
                  (r0v4 'httpSetting' com.jingdong.jdsdk.network.toolbox.HttpSetting)
                  (wrap: com.jingdong.jdsdk.network.toolbox.HttpGroup$OnEndListener : 0x0050: CONSTRUCTOR 
                  (r2 I:long A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r4v0 'handler' android.os.Handler A[DONT_INLINE])
                  (r5v0 'runnable' java.lang.Runnable A[DONT_INLINE])
                  (r11v0 'shareInfo' com.jingdong.common.entity.ShareInfo A[DONT_INLINE])
                  (r12v0 'str' java.lang.String A[DONT_INLINE])
                  (r13v0 'str2' java.lang.String A[DONT_INLINE])
                  (r10v0 'activity' android.app.Activity A[DONT_INLINE])
                 A[MD:(long, android.os.Handler, java.lang.Runnable, com.jingdong.common.entity.ShareInfo, java.lang.String, java.lang.String, android.app.Activity):void (m), WRAPPED] (LINE:14) call: com.jingdong.common.utils.ShareUtil.2.<init>(long, android.os.Handler, java.lang.Runnable, com.jingdong.common.entity.ShareInfo, java.lang.String, java.lang.String, android.app.Activity):void type: CONSTRUCTOR)
                 type: VIRTUAL call: com.jingdong.jdsdk.network.toolbox.HttpSetting.setListener(com.jingdong.jdsdk.network.toolbox.HttpGroup$HttpTaskListener):void A[MD:(com.jingdong.jdsdk.network.toolbox.HttpGroup$HttpTaskListener):void (m)] (LINE:14) in method: com.jingdong.common.utils.ShareUtil.lottery(android.app.Activity, com.jingdong.common.entity.ShareInfo, java.lang.String, java.lang.String, java.lang.String):void, file: classes6.dex
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
                boolean r0 = isColdDown()
                if (r0 == 0) goto L7
                return
            L7:
                boolean r0 = android.text.TextUtils.isEmpty(r12)
                if (r0 != 0) goto L5e
                boolean r0 = android.text.TextUtils.isEmpty(r13)
                if (r0 == 0) goto L14
                goto L5e
            L14:
                long r2 = java.lang.System.currentTimeMillis()
                android.os.Handler r4 = new android.os.Handler
                r4.<init>()
                com.jingdong.common.utils.ShareUtil$1 r5 = new com.jingdong.common.utils.ShareUtil$1
                r5.<init>()
                r0 = 3750(0xea6, double:1.8527E-320)
                r4.postDelayed(r5, r0)
                com.jingdong.jdsdk.network.toolbox.HttpSetting r0 = new com.jingdong.jdsdk.network.toolbox.HttpSetting
                r0.<init>()
                r1 = 1
                r0.setUseFastJsonParser(r1)
                java.lang.String r1 = "getShareRule"
                r0.setFunctionId(r1)
                r0.setHost(r14)
                java.lang.String r14 = "type"
                r0.putJsonParam(r14, r12)
                java.lang.String r14 = "bizId"
                r0.putJsonParam(r14, r13)
                java.lang.String r14 = "channel"
                java.lang.String r1 = "2"
                r0.putJsonParam(r14, r1)
                com.jingdong.common.utils.ShareUtil$2 r14 = new com.jingdong.common.utils.ShareUtil$2
                r1 = r14
                r6 = r11
                r7 = r12
                r8 = r13
                r9 = r10
                r1.<init>()
                r0.setListener(r14)
                com.jingdong.jdsdk.network.toolbox.HttpGroup r10 = com.jingdong.common.network.HttpGroupUtils.getHttpGroupaAsynPool()
                r10.add(r0)
                return
            L5e:
                com.jingdong.jdsdk.JdSdk r10 = com.jingdong.jdsdk.JdSdk.getInstance()
                android.content.Context r10 = r10.getApplicationContext()
                int r11 = com.jingdong.appshare.R.string.share_failed_try_again
                com.jingdong.sdk.jdtoast.ToastUtils.shortToast(r10, r11)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.ShareUtil.lottery(android.app.Activity, com.jingdong.common.entity.ShareInfo, java.lang.String, java.lang.String, java.lang.String):void");
        }

        public static void open(Activity activity, ShareInfo shareInfo) {
            startShareActivityForResult(activity, shareInfo, 2, shareInfo.getShareLogoBytes());
        }

        @Deprecated
        public static List<Map<String, Object>> packChannels(List list, boolean z, boolean z2) {
            ArrayList arrayList = new ArrayList();
            if (i.c()) {
                arrayList.add(new Object[]{"Wxfriends", Integer.valueOf(R.drawable.share_to_wx_friend_icon), Integer.valueOf(R.string.share_to_wx_friends)});
                arrayList.add(new Object[]{"Wxmoments", Integer.valueOf(R.drawable.share_to_wx_circle_icon), Integer.valueOf(R.string.share_to_wx_friends_circle)});
            }
            if (e.b()) {
                arrayList.add(new Object[]{"QQfriends", Integer.valueOf(R.drawable.share_to_qq_friend_icon), Integer.valueOf(R.string.share_to_qq_friends)});
                arrayList.add(new Object[]{"QQzone", Integer.valueOf(R.drawable.share_to_qzone_icon), Integer.valueOf(R.string.share_to_qzone)});
            }
            if (WeiboUtils.checkWbSdk()) {
                arrayList.add(new Object[]{"Sinaweibo", Integer.valueOf(R.drawable.share_to_weibo_icon), Integer.valueOf(R.string.share_to_weibo)});
            }
            arrayList.add(new Object[]{"CopyURL", Integer.valueOf(R.drawable.share_to_copy_icon), Integer.valueOf(R.string.share_to_copy)});
            arrayList.add(new Object[]{"QRCode", Integer.valueOf(R.drawable.share_to_qr_code_icon), Integer.valueOf(R.string.share_to_big_pic)});
            Application application = JdSdk.getInstance().getApplication();
            ArrayList arrayList2 = new ArrayList();
            int size = z2 ? 4 : arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                Object[] objArr = (Object[]) arrayList.get(i2);
                if (list.contains(objArr[0]) || (z && objArr[0].toString().equals("QRCode"))) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("channel", objArr[0]);
                    hashMap.put("image", objArr[1]);
                    hashMap.put("text", application.getString(Integer.parseInt(objArr[2].toString())));
                    arrayList2.add(hashMap);
                }
            }
            return arrayList2;
        }

        public static void panel(Activity activity, ShareInfo shareInfo) {
            startShareActivityForResult(activity, shareInfo, 1, shareInfo.getShareLogoBytes());
        }

        @Nullable
        public static ShareInfo parseShareInfoFromBundle(Bundle bundle) {
            if (bundle == null || bundle.isEmpty()) {
                return null;
            }
            JDJSONObject jDJSONObject = new JDJSONObject();
            for (String str : bundle.keySet()) {
                jDJSONObject.put(str, bundle.get(str));
            }
            if (OKLog.D) {
                OKLog.d(TAG, "parsing shareInfo from bundle. ---> Raw bundle: " + bundle.toString() + " ---> Converted JSON: " + jDJSONObject.toJSONString());
            }
            return parseShareInfoFromJson(jDJSONObject);
        }

        @Nullable
        public static ShareInfo parseShareInfoFromJson(String str) {
            JDJSONObject jDJSONObject = null;
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                jDJSONObject = JDJSON.parseObject(str);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
            return parseShareInfoFromJson(jDJSONObject);
        }

        public static void sendShare(Activity activity, ShareInfo shareInfo, CallbackListener callbackListener) {
            if (callbackListener != null) {
                startShareActivityForCallback(activity, shareInfo, 2, shareInfo.getShareLogoBytes(), callbackListener, null);
            } else {
                startShareActivityForResult(activity, shareInfo, 2, shareInfo.getShareLogoBytes());
            }
        }

        public static void setShareInfoByJson(ShareInfo shareInfo, JDJSONObject jDJSONObject) {
            if (shareInfo == null || jDJSONObject == null || jDJSONObject.isEmpty()) {
                return;
            }
            shareInfo.setEventFrom(jDJSONObject.optString("from", ""));
            shareInfo.setTitleTimeline(jDJSONObject.optString("timeline_title", ""));
            shareInfo.setMpId(jDJSONObject.optString("mpId", ""));
            shareInfo.setMpType(jDJSONObject.optInt("mpType", 0));
            shareInfo.setMpPath(jDJSONObject.optString("mpPath", ""));
            shareInfo.setMpIconUrl(jDJSONObject.optString("mpIconUrl", ""));
            JDJSONObject optJSONObject = jDJSONObject.optJSONObject("qrparam");
            if (optJSONObject != null && optJSONObject.size() >= 2) {
                int optInt = optJSONObject.optInt("qr_type", -1);
                if (optInt != 0 && optInt != 1 && optInt != 2) {
                    optInt = optJSONObject.optInt("imageShareType", 0);
                }
                shareInfo.setShareImageInfo(new ShareImageInfo(optJSONObject.optString("top_pic"), optJSONObject.optString("slogan"), optJSONObject.optString(optJSONObject.optInt("qr_type", 0) == 2 ? "mid_pic_x" : "mid_pic"), optJSONObject.optString("qr_title"), optJSONObject.optString("qr_content")));
                shareInfo.getShareImageInfo().directUrl = optJSONObject.optString("qr_direct", "");
                shareInfo.getShareImageInfo().imageShareType = optInt;
                shareInfo.getShareImageInfo().isDecodeDirectUrl = optJSONObject.optInt("isDecodeDirectUrl", 0);
                shareInfo.getShareImageInfo().directPath = optJSONObject.optString("qr_direct_path", "");
                shareInfo.getShareImageInfo().productPath = optJSONObject.optString("mid_pic_path", "");
            }
            shareInfo.setPanelBanner(jDJSONObject.optString("panelBanner", ""));
            if ("1".equals(jDJSONObject.optString("hidePlus", ""))) {
                shareInfo.hidePlusTag();
            }
            String optString = jDJSONObject.optString("showFriendList", "");
            if (TextUtils.equals(optString, "0")) {
                shareInfo.showJDFriendChannel(false);
            } else if (TextUtils.equals(optString, "1")) {
                shareInfo.showJDFriendChannel(true);
            }
            shareInfo.setChannels(jDJSONObject.optString("channel", ""));
            String optString2 = jDJSONObject.optString("incentiveBizType", "");
            String optString3 = jDJSONObject.optString("incentiveBizId", "");
            if (!TextUtils.isEmpty(optString3) && !TextUtils.isEmpty(optString2)) {
                shareInfo.setIncentiveBizId(optString3);
                shareInfo.setIncentiveBizType(optString2);
            }
            shareInfo.setAction(jDJSONObject.optString("shareActionType", ""));
            JDJSONObject optJSONObject2 = jDJSONObject.optJSONObject("keyparam");
            if (optJSONObject2 != null && optJSONObject2.size() > 1) {
                shareInfo.setKeyShareJsonStr(optJSONObject2.toJSONString());
            }
            shareInfo.setWeiXinContentParam(jDJSONObject.optString("weiXinContentParam", ""));
            shareInfo.setIsNewWeixinShareUI(jDJSONObject.optString("isNewWeixinShareUI", "0"));
            shareInfo.setShareSource(jDJSONObject.optString("shareSource", ""));
            if (OKLog.D) {
                OKLog.d(TAG, " new shareInfo -->> title : " + shareInfo.getTitle() + ", content : " + shareInfo.getSummary() + ", wxMomentsContent : " + shareInfo.getWxMomentsContent() + ", shareUrl : " + shareInfo.getUrl() + ", iconUrl : " + shareInfo.getIconUrl() + ", from : " + shareInfo.getEventFrom());
            }
        }

        public static void showShareDialog(Activity activity, ShareInfo shareInfo) {
            startShareActivityForResult(activity, shareInfo, 1, shareInfo.getShareLogoBytes());
        }

        public static String[] splitTransaction(String str) {
            if (TextUtils.isEmpty(str)) {
                return new String[]{"", ""};
            }
            String[] split = str.split("##");
            return split.length > 1 ? split : new String[]{str, ""};
        }

        public static void startShareActivityForCallback(Activity activity, ShareInfo shareInfo, int i2, byte[] bArr, CallbackListener callbackListener, ClickCallbackListener clickCallbackListener) {
            if (isColdDown()) {
                return;
            }
            bizFilter(activity, shareInfo);
            ShareCallbackListenerParcel shareCallbackListenerParcel = new ShareCallbackListenerParcel(new ShareCallbackListenerBinder(callbackListener, clickCallbackListener));
            Intent intent = new Intent(shareActivityAction);
            Bundle bundle = new Bundle();
            bundle.putParcelable("shareInfo", shareInfo);
            intent.putExtra("bundle", bundle);
            intent.putExtra("action", i2);
            intent.putExtra("bytes", bArr);
            intent.putExtra("parcel", shareCallbackListenerParcel);
            try {
                activity.startActivityForResult(intent, 1215);
            } catch (ActivityNotFoundException e2) {
                OKLog.d(TAG, e2);
                ToastUtils.shortToast(JdSdk.getInstance().getApplicationContext(), R.string.share_exception);
            }
            clearJDTransferActivity(activity);
        }

        public static void startShareActivityForResult(Activity activity, ShareInfo shareInfo, int i2, byte[] bArr) {
            if (isColdDown()) {
                return;
            }
            bizFilter(activity, shareInfo);
            Intent intent = new Intent(shareActivityAction);
            Bundle bundle = new Bundle();
            bundle.putParcelable("shareInfo", shareInfo);
            intent.putExtra("bundle", bundle);
            intent.putExtra("action", i2);
            intent.putExtra("bytes", bArr);
            try {
                activity.startActivityForResult(intent, 1215);
            } catch (ActivityNotFoundException e2) {
                OKLog.d(TAG, e2);
                ToastUtils.shortToast(JdSdk.getInstance().getApplicationContext(), R.string.share_exception);
            }
            clearJDTransferActivity(activity);
        }

        public static String urlDecode(String str) {
            if (str == null) {
                return "";
            }
            try {
                return URLDecoder.decode(str, "utf8");
            } catch (Exception e2) {
                if (OKLog.E) {
                    e2.printStackTrace();
                    return str;
                }
                return str;
            }
        }

        public static String urlEncode(String str) {
            if (str == null) {
                return "";
            }
            try {
                return URLEncoder.encode(str, "utf8");
            } catch (Exception e2) {
                if (OKLog.E) {
                    e2.printStackTrace();
                    return str;
                }
                return str;
            }
        }

        public static Bitmap createQRCode(String str, int i2) {
            String str2 = "createQRCode - " + Thread.currentThread().getName();
            Bitmap bitmap = null;
            try {
                Hashtable hashtable = new Hashtable();
                hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
                BitMatrix encode = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, DPIUtil.getWidthByDesignValue720(JdSdk.getInstance().getApplicationContext(), i2), DPIUtil.getWidthByDesignValue720(JdSdk.getInstance().getApplicationContext(), i2), hashtable);
                int width = encode.getWidth();
                int height = encode.getHeight();
                int[] iArr = new int[width * height];
                int i3 = 0;
                int i4 = 0;
                for (int i5 = 0; i5 < height; i5++) {
                    for (int i6 = 0; i6 < width; i6++) {
                        if (encode.get(i6, i5)) {
                            if (i3 == 0 && i4 == 0) {
                                i4 = i5;
                                i3 = i6;
                            }
                            iArr[(i5 * width) + i6] = -16777216;
                        } else {
                            iArr[(i5 * width) + i6] = -1;
                        }
                    }
                }
                int i7 = width - (i3 * 2);
                int i8 = height - (i4 * 2);
                int[] iArr2 = new int[i7 * i8];
                for (int i9 = 0; i9 < i8; i9++) {
                    for (int i10 = 0; i10 < i7; i10++) {
                        iArr2[(i9 * i7) + i10] = iArr[((i9 + i4) * width) + i10 + i3];
                    }
                }
                bitmap = Bitmap.createBitmap(i7, i8, Bitmap.Config.ARGB_8888);
                bitmap.setPixels(iArr2, 0, i7, 0, 0, i7, i8);
                bitmap.toString();
                return bitmap;
            } catch (Exception e2) {
                if (OKLog.E) {
                    e2.printStackTrace();
                    return bitmap;
                }
                return bitmap;
            }
        }

        public static void showShareDialog(Activity activity, ShareInfo shareInfo, CallbackListener callbackListener) {
            if (callbackListener != null) {
                startShareActivityForCallback(activity, shareInfo, 1, shareInfo.getShareLogoBytes(), callbackListener, null);
            } else {
                startShareActivityForResult(activity, shareInfo, 1, shareInfo.getShareLogoBytes());
            }
        }

        @Nullable
        public static ShareInfo parseShareInfoFromJson(JDJSONObject jDJSONObject) {
            if (jDJSONObject == null || jDJSONObject.isEmpty()) {
                return null;
            }
            ShareInfo shareInfo = new ShareInfo(jDJSONObject.optString("shareUrl", ""), jDJSONObject.optString("title", ""), jDJSONObject.optString("content"), jDJSONObject.optString("iconUrl", ""), jDJSONObject.optString("shareSource", ""));
            setShareInfoByJson(shareInfo, jDJSONObject);
            return shareInfo;
        }

        public static void showShareDialog(Activity activity, ShareInfo shareInfo, CallbackListener callbackListener, ClickCallbackListener clickCallbackListener) {
            if (callbackListener == null && clickCallbackListener == null) {
                startShareActivityForResult(activity, shareInfo, 1, shareInfo.getShareLogoBytes());
            } else {
                startShareActivityForCallback(activity, shareInfo, 1, shareInfo.getShareLogoBytes(), callbackListener, clickCallbackListener);
            }
        }

        @Deprecated
        public static void lottery(final Activity activity, final ShareInfo shareInfo, final String str, final String str2) {
            if (isColdDown()) {
                return;
            }
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                System.currentTimeMillis();
                final Handler handler = new Handler();
                final Runnable runnable = new Runnable() { // from class: com.jingdong.common.utils.ShareUtil.3
                    @Override // java.lang.Runnable
                    public void run() {
                        ToastUtils.shortToast(JdSdk.getInstance().getApplicationContext(), R.string.share_failed_try_again);
                    }
                };
                handler.postDelayed(runnable, 3750L);
                HttpSetting httpSetting = new HttpSetting();
                httpSetting.setUseFastJsonParser(true);
                httpSetting.setFunctionId("getShareRule");
                httpSetting.setHost(Configuration.getPortalHost());
                httpSetting.putJsonParam("type", str);
                httpSetting.putJsonParam("bizId", str2);
                httpSetting.putJsonParam("channel", "2");
                httpSetting.setListener(new HttpGroup.OnEndListener
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0057: INVOKE 
                      (r0v4 'httpSetting' com.jingdong.jdsdk.network.toolbox.HttpSetting)
                      (wrap: com.jingdong.jdsdk.network.toolbox.HttpGroup$OnEndListener : 0x0054: CONSTRUCTOR 
                      (r2 I:long A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r4v0 'handler' android.os.Handler A[DONT_INLINE])
                      (r5v0 'runnable' java.lang.Runnable A[DONT_INLINE])
                      (r12v0 'shareInfo' com.jingdong.common.entity.ShareInfo A[DONT_INLINE])
                      (r13v0 'str' java.lang.String A[DONT_INLINE])
                      (r14v0 'str2' java.lang.String A[DONT_INLINE])
                      (r11v0 'activity' android.app.Activity A[DONT_INLINE])
                     A[MD:(long, android.os.Handler, java.lang.Runnable, com.jingdong.common.entity.ShareInfo, java.lang.String, java.lang.String, android.app.Activity):void (m), WRAPPED] (LINE:30) call: com.jingdong.common.utils.ShareUtil.4.<init>(long, android.os.Handler, java.lang.Runnable, com.jingdong.common.entity.ShareInfo, java.lang.String, java.lang.String, android.app.Activity):void type: CONSTRUCTOR)
                     type: VIRTUAL call: com.jingdong.jdsdk.network.toolbox.HttpSetting.setListener(com.jingdong.jdsdk.network.toolbox.HttpGroup$HttpTaskListener):void A[MD:(com.jingdong.jdsdk.network.toolbox.HttpGroup$HttpTaskListener):void (m)] (LINE:30) in method: com.jingdong.common.utils.ShareUtil.lottery(android.app.Activity, com.jingdong.common.entity.ShareInfo, java.lang.String, java.lang.String):void, file: classes6.dex
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
                    boolean r0 = isColdDown()
                    if (r0 == 0) goto L7
                    return
                L7:
                    boolean r0 = android.text.TextUtils.isEmpty(r13)
                    if (r0 != 0) goto L62
                    boolean r0 = android.text.TextUtils.isEmpty(r14)
                    if (r0 == 0) goto L14
                    goto L62
                L14:
                    long r2 = java.lang.System.currentTimeMillis()
                    android.os.Handler r4 = new android.os.Handler
                    r4.<init>()
                    com.jingdong.common.utils.ShareUtil$3 r5 = new com.jingdong.common.utils.ShareUtil$3
                    r5.<init>()
                    r0 = 3750(0xea6, double:1.8527E-320)
                    r4.postDelayed(r5, r0)
                    com.jingdong.jdsdk.network.toolbox.HttpSetting r0 = new com.jingdong.jdsdk.network.toolbox.HttpSetting
                    r0.<init>()
                    r1 = 1
                    r0.setUseFastJsonParser(r1)
                    java.lang.String r1 = "getShareRule"
                    r0.setFunctionId(r1)
                    java.lang.String r1 = com.jingdong.jdsdk.config.Configuration.getPortalHost()
                    r0.setHost(r1)
                    java.lang.String r1 = "type"
                    r0.putJsonParam(r1, r13)
                    java.lang.String r1 = "bizId"
                    r0.putJsonParam(r1, r14)
                    java.lang.String r1 = "channel"
                    java.lang.String r6 = "2"
                    r0.putJsonParam(r1, r6)
                    com.jingdong.common.utils.ShareUtil$4 r10 = new com.jingdong.common.utils.ShareUtil$4
                    r1 = r10
                    r6 = r12
                    r7 = r13
                    r8 = r14
                    r9 = r11
                    r1.<init>()
                    r0.setListener(r10)
                    com.jingdong.jdsdk.network.toolbox.HttpGroup r11 = com.jingdong.common.network.HttpGroupUtils.getHttpGroupaAsynPool()
                    r11.add(r0)
                    return
                L62:
                    com.jingdong.jdsdk.JdSdk r11 = com.jingdong.jdsdk.JdSdk.getInstance()
                    android.content.Context r11 = r11.getApplicationContext()
                    int r12 = com.jingdong.appshare.R.string.share_failed_try_again
                    com.jingdong.sdk.jdtoast.ToastUtils.shortToast(r11, r12)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.ShareUtil.lottery(android.app.Activity, com.jingdong.common.entity.ShareInfo, java.lang.String, java.lang.String):void");
            }
        }
