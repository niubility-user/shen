package com.tencent.connect.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.jd.dynamic.DYConstants;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.TDialog;
import com.tencent.open.b.e;
import com.tencent.open.log.SLog;
import com.tencent.open.utils.c;
import com.tencent.open.utils.d;
import com.tencent.open.utils.g;
import com.tencent.open.utils.k;
import com.tencent.open.utils.m;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.File;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class QQShare extends BaseApi {
    public static final int QQ_SHARE_SUMMARY_MAX_LENGTH = 512;
    public static final int QQ_SHARE_TITLE_MAX_LENGTH = 128;
    public static final String SHARE_TO_QQ_APP_NAME = "appName";
    public static final String SHARE_TO_QQ_ARK_INFO = "share_to_qq_ark_info";
    public static final String SHARE_TO_QQ_AUDIO_URL = "audio_url";
    public static final String SHARE_TO_QQ_EXT_INT = "cflag";
    public static final String SHARE_TO_QQ_EXT_STR = "share_qq_ext_str";
    public static final int SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN = 1;
    public static final int SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE = 2;
    public static final String SHARE_TO_QQ_GAME_MESSAGE_EXT = "game_message_ext";
    public static final String SHARE_TO_QQ_GAME_TAG_NAME = "game_tag_name";
    public static final String SHARE_TO_QQ_IMAGE_LOCAL_URL = "imageLocalUrl";
    public static final String SHARE_TO_QQ_IMAGE_URL = "imageUrl";
    public static final String SHARE_TO_QQ_KEY_TYPE = "req_type";
    public static final int SHARE_TO_QQ_MINI_PROGRAM = 7;
    public static final String SHARE_TO_QQ_MINI_PROGRAM_APPID = "mini_program_appid";
    public static final String SHARE_TO_QQ_MINI_PROGRAM_PATH = "mini_program_path";
    public static final String SHARE_TO_QQ_MINI_PROGRAM_TYPE = "mini_program_type";
    public static final String SHARE_TO_QQ_SITE = "site";
    public static final String SHARE_TO_QQ_SUMMARY = "summary";
    public static final String SHARE_TO_QQ_TARGET_URL = "targetUrl";
    public static final String SHARE_TO_QQ_TITLE = "title";
    public static final int SHARE_TO_QQ_TYPE_AUDIO = 2;
    public static final int SHARE_TO_QQ_TYPE_DEFAULT = 1;
    public static final int SHARE_TO_QQ_TYPE_IMAGE = 5;
    public String mViaShareQQType;

    public QQShare(Context context, QQToken qQToken) {
        super(qQToken);
        this.mViaShareQQType = "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Activity activity, Bundle bundle, IUiListener iUiListener) {
        SLog.i("openSDK_LOG.QQShare", "doShareToQQ() -- start");
        StringBuffer stringBuffer = new StringBuffer("mqqapi://share/to_fri?src_type=app&version=1&file_type=news");
        String string = bundle.getString("imageUrl");
        String string2 = bundle.getString("title");
        String string3 = bundle.getString("summary");
        String string4 = bundle.getString("targetUrl");
        String string5 = bundle.getString("audio_url");
        int i2 = bundle.getInt("req_type", 1);
        String string6 = bundle.getString(SHARE_TO_QQ_ARK_INFO);
        String string7 = bundle.getString(SHARE_TO_QQ_MINI_PROGRAM_APPID);
        String string8 = bundle.getString(SHARE_TO_QQ_MINI_PROGRAM_PATH);
        String string9 = bundle.getString(SHARE_TO_QQ_MINI_PROGRAM_TYPE);
        int i3 = bundle.getInt("cflag", 0);
        String string10 = bundle.getString("share_qq_ext_str");
        String a = m.a(activity);
        if (a == null) {
            a = bundle.getString("appName");
        }
        String str = a;
        String string11 = bundle.getString("imageLocalUrl");
        ArrayList<String> stringArrayList = bundle.getStringArrayList("imageLocalUrlArray");
        String appId = this.f16153c.getAppId();
        String openIdWithCache = this.f16153c.getOpenIdWithCache();
        SLog.i("openSDK_LOG.QQShare", "doShareToQQ -- openid: " + openIdWithCache + ",appName=" + str);
        if (stringArrayList != null && stringArrayList.size() >= 2) {
            String str2 = stringArrayList.get(0);
            if (str2 == null) {
                str2 = "";
            }
            stringBuffer.append("&file_data=" + Base64.encodeToString(m.j(str2), 2));
            String str3 = stringArrayList.get(1);
            if (i2 == 7 && !TextUtils.isEmpty(str3) && k.c(activity, "8.3.3") < 0) {
                str3 = null;
                SLog.e("openSDK_LOG.QQShare", "doShareToQQ() share to mini program set file uri empty");
            }
            Uri a2 = m.a(activity, appId, str3);
            if (a2 != null) {
                stringBuffer.append("&file_uri=");
                stringBuffer.append(Base64.encodeToString(m.j(a2.toString()), 2));
            }
        } else if (!TextUtils.isEmpty(string11)) {
            stringBuffer.append("&file_data=" + Base64.encodeToString(m.j(string11), 2));
        }
        if (!TextUtils.isEmpty(string)) {
            stringBuffer.append("&image_url=" + Base64.encodeToString(m.j(string), 2));
        }
        if (!TextUtils.isEmpty(string2)) {
            stringBuffer.append("&title=" + Base64.encodeToString(m.j(string2), 2));
        }
        if (!TextUtils.isEmpty(string3)) {
            stringBuffer.append("&description=" + Base64.encodeToString(m.j(string3), 2));
        }
        if (!TextUtils.isEmpty(appId)) {
            stringBuffer.append("&share_id=" + appId);
        }
        if (!TextUtils.isEmpty(string4)) {
            stringBuffer.append("&url=" + Base64.encodeToString(m.j(string4), 2));
        }
        if (!TextUtils.isEmpty(str)) {
            if (str.length() > 20) {
                str = str.substring(0, 20) + "...";
            }
            stringBuffer.append("&app_name=" + Base64.encodeToString(m.j(str), 2));
        }
        if (!TextUtils.isEmpty(openIdWithCache)) {
            stringBuffer.append("&open_id=" + Base64.encodeToString(m.j(openIdWithCache), 2));
        }
        if (!TextUtils.isEmpty(string5)) {
            stringBuffer.append("&audioUrl=" + Base64.encodeToString(m.j(string5), 2));
        }
        stringBuffer.append("&req_type=" + Base64.encodeToString(m.j(String.valueOf(i2)), 2));
        if (!TextUtils.isEmpty(string7)) {
            stringBuffer.append("&mini_program_appid=" + Base64.encodeToString(m.j(String.valueOf(string7)), 2));
        }
        if (!TextUtils.isEmpty(string8)) {
            stringBuffer.append("&mini_program_path=" + Base64.encodeToString(m.j(String.valueOf(string8)), 2));
        }
        if (!TextUtils.isEmpty(string9)) {
            stringBuffer.append("&mini_program_type=" + Base64.encodeToString(m.j(String.valueOf(string9)), 2));
        }
        if (!TextUtils.isEmpty(string6)) {
            stringBuffer.append("&share_to_qq_ark_info=" + Base64.encodeToString(m.j(string6), 2));
        }
        if (!TextUtils.isEmpty(string10)) {
            stringBuffer.append("&share_qq_ext_str=" + Base64.encodeToString(m.j(string10), 2));
        }
        stringBuffer.append("&cflag=" + Base64.encodeToString(m.j(String.valueOf(i3)), 2));
        stringBuffer.append("&third_sd=" + Base64.encodeToString(m.j(String.valueOf(m.c())), 2));
        SLog.v("openSDK_LOG.QQShare", "doShareToQQ -- url: " + stringBuffer.toString());
        com.tencent.connect.a.a.a(g.a(), this.f16153c, "requireApi", "shareToNativeQQ");
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(stringBuffer.toString()));
        intent.putExtra(Constants.KEY_PASS_REPORT_VIA_PARAM, m.a(this.f16153c.getOpenId(), i3 == 1 ? "11" : "10", "3", Constants.VIA_SHARE_TO_QQ, this.f16153c.getAppId(), this.mViaShareQQType, "", "", "0", "1", "0"));
        intent.putExtra(Constants.PARAM_PKG_NAME, activity.getPackageName());
        if (m.f(activity, "4.6.0")) {
            SLog.i("openSDK_LOG.QQShare", "doShareToQQ, qqver below 4.6.");
            UIListenerManager.getInstance().setListenerWithRequestcode(11103, iUiListener);
            a(activity, intent, 11103);
        } else {
            SLog.i("openSDK_LOG.QQShare", "doShareToQQ, qqver greater than 4.6.");
            if (UIListenerManager.getInstance().setListnerWithAction("shareToQQ", iUiListener) != null) {
                SLog.i("openSDK_LOG.QQShare", "doShareToQQ, last listener is not null, cancel it.");
            }
            a(activity, 10103, intent, true);
        }
        SLog.i("openSDK_LOG.QQShare", "doShareToQQ() --end");
    }

    @Override // com.tencent.connect.common.BaseApi
    public void releaseResource() {
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x02d0  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02e0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01d9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void shareToQQ(Activity activity, Bundle bundle, IUiListener iUiListener) {
        int i2;
        Bundle bundle2;
        String str;
        SLog.i("openSDK_LOG.QQShare", "shareToQQ() -- start.");
        if (com.tencent.connect.a.a("openSDK_LOG.QQShare", iUiListener)) {
            return;
        }
        String string = bundle.getString("imageUrl");
        String string2 = bundle.getString("title");
        String string3 = bundle.getString("summary");
        String string4 = bundle.getString("targetUrl");
        String string5 = bundle.getString("imageLocalUrl");
        String string6 = bundle.getString(SHARE_TO_QQ_MINI_PROGRAM_APPID);
        String string7 = bundle.getString(SHARE_TO_QQ_MINI_PROGRAM_PATH);
        int i3 = bundle.getInt("req_type", 1);
        SLog.i("openSDK_LOG.QQShare", "shareToQQ -- type: " + i3);
        if (i3 == 1) {
            this.mViaShareQQType = "1";
        } else if (i3 == 2) {
            this.mViaShareQQType = "3";
        } else if (i3 == 5) {
            this.mViaShareQQType = "2";
        } else if (i3 == 7) {
            this.mViaShareQQType = "9";
        }
        if (!m.a() && m.f(activity, "4.5.0")) {
            iUiListener.onError(new UiError(-6, Constants.MSG_SHARE_NOSD_ERROR, null));
            SLog.e("openSDK_LOG.QQShare", "shareToQQ sdcard is null--end");
            e.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f16153c.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ sdcard is null");
            return;
        }
        if (i3 == 5) {
            if (m.f(activity, "4.3.0")) {
                iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_QQ_VERSION_ERROR, null));
                SLog.e("openSDK_LOG.QQShare", "shareToQQ, version below 4.3 is not support.");
                e.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f16153c.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, version below 4.3 is not support.");
                return;
            } else if (!m.i(string5)) {
                iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_IMAGE_URL_FORMAT_ERROR, null));
                SLog.e("openSDK_LOG.QQShare", "shareToQQ -- error: \u975e\u6cd5\u7684\u56fe\u7247\u5730\u5740!");
                e.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f16153c.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, Constants.MSG_PARAM_IMAGE_URL_FORMAT_ERROR);
                return;
            }
        }
        if (i3 != 5) {
            i2 = 7;
            if (i3 != 7) {
                if (!TextUtils.isEmpty(string4) && (string4.startsWith("http://") || string4.startsWith("https://"))) {
                    if (TextUtils.isEmpty(string2)) {
                        iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_TITLE_NULL_ERROR, null));
                        SLog.e("openSDK_LOG.QQShare", "shareToQQ, title is empty.");
                        e.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f16153c.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, title is empty.");
                        return;
                    }
                } else {
                    iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_ERROR, null));
                    SLog.e("openSDK_LOG.QQShare", "shareToQQ, targetUrl is empty or illegal..");
                    e.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f16153c.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, targetUrl is empty or illegal..");
                    return;
                }
            }
            if (i3 == i2) {
                if (!TextUtils.isEmpty(string6) && !TextUtils.isEmpty(string7) && !TextUtils.isEmpty(string4) && !TextUtils.isEmpty(this.f16153c.getAppId())) {
                    if (!(k.c(activity, "8.0.8") >= 0 || k.d(activity, "3.1") >= 0)) {
                        iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_QQ_VERSION_ERROR, "\u7248\u672c\u8fc7\u4f4e\uff0c\u4e0d\u652f\u6301\u5206\u4eab\u5c0f\u7a0b\u5e8f"));
                        return;
                    } else if (TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
                        iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, "title || summary empty."));
                        return;
                    }
                } else {
                    iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, "appid || path || url empty."));
                    return;
                }
            }
            if (TextUtils.isEmpty(string) && !string.startsWith("http://") && !string.startsWith("https://") && !new File(string).exists()) {
                iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_IMAGE_URL_FORMAT_ERROR, null));
                SLog.e("openSDK_LOG.QQShare", "shareToQQ, image url is emprty or illegal.");
                e.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f16153c.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, image url is emprty or illegal.");
                return;
            }
            if (!TextUtils.isEmpty(string2) || string2.length() <= 128) {
                bundle2 = bundle;
                str = null;
            } else {
                str = null;
                bundle2 = bundle;
                bundle2.putString("title", m.a(string2, 128, (String) null, (String) null));
            }
            if (!TextUtils.isEmpty(string3) && string3.length() > 512) {
                bundle2.putString("summary", m.a(string3, 512, str, str));
            }
            if (!m.a(activity, bundle2.getInt("cflag", 0) != 1)) {
                SLog.i("openSDK_LOG.QQShare", "shareToQQ, support share");
                b(activity, bundle, iUiListener);
            } else {
                try {
                    SLog.w("openSDK_LOG.QQShare", "shareToQQ, don't support share, will show download dialog");
                    new TDialog(activity, "", a(""), null, this.f16153c).show();
                } catch (RuntimeException e2) {
                    SLog.e("openSDK_LOG.QQShare", " shareToQQ, TDialog.show not in main thread", e2);
                    iUiListener.onError(new UiError(-6, Constants.MSG_NOT_CALL_ON_MAIN_THREAD, null));
                }
            }
            SLog.i("openSDK_LOG.QQShare", "shareToQQ() -- end.");
        }
        i2 = 7;
        if (i3 == i2) {
        }
        if (TextUtils.isEmpty(string)) {
        }
        if (TextUtils.isEmpty(string2)) {
        }
        bundle2 = bundle;
        str = null;
        if (!TextUtils.isEmpty(string3)) {
            bundle2.putString("summary", m.a(string3, 512, str, str));
        }
        if (!m.a(activity, bundle2.getInt("cflag", 0) != 1)) {
        }
        SLog.i("openSDK_LOG.QQShare", "shareToQQ() -- end.");
    }

    private void b(final Activity activity, final Bundle bundle, final IUiListener iUiListener) {
        SLog.i("openSDK_LOG.QQShare", "shareToMobileQQ() -- start.");
        String string = bundle.getString("imageUrl");
        bundle.getString("title");
        bundle.getString("summary");
        SLog.v("openSDK_LOG.QQShare", "shareToMobileQQ -- imageUrl: " + string);
        if (!TextUtils.isEmpty(string)) {
            if (m.h(string)) {
                if (!m.f(activity, "4.3.0")) {
                    d(activity, bundle, iUiListener);
                } else {
                    new c(activity).a(string, new d
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0055: INVOKE 
                          (wrap: com.tencent.open.utils.c : 0x0048: CONSTRUCTOR (r12v0 'activity' android.app.Activity) A[MD:(android.app.Activity):void (m), WRAPPED] (LINE:11) call: com.tencent.open.utils.c.<init>(android.app.Activity):void type: CONSTRUCTOR)
                          (r2v0 'string' java.lang.String)
                          (wrap: com.tencent.open.utils.d : 0x0052: CONSTRUCTOR 
                          (r11v0 'this' com.tencent.connect.share.QQShare A[IMMUTABLE_TYPE, THIS])
                          (r13v0 'bundle' android.os.Bundle A[DONT_INLINE])
                          (r7 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                          (r8 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                          (r14v0 'iUiListener' com.tencent.tauth.IUiListener A[DONT_INLINE])
                          (r12v0 'activity' android.app.Activity A[DONT_INLINE])
                         A[MD:(com.tencent.connect.share.QQShare, android.os.Bundle, java.lang.String, java.lang.String, com.tencent.tauth.IUiListener, android.app.Activity):void (m), WRAPPED] (LINE:12) call: com.tencent.connect.share.QQShare.1.<init>(com.tencent.connect.share.QQShare, android.os.Bundle, java.lang.String, java.lang.String, com.tencent.tauth.IUiListener, android.app.Activity):void type: CONSTRUCTOR)
                         type: VIRTUAL call: com.tencent.open.utils.c.a(java.lang.String, com.tencent.open.utils.d):void A[MD:(java.lang.String, com.tencent.open.utils.d):void (m)] (LINE:12) in method: com.tencent.connect.share.QQShare.b(android.app.Activity, android.os.Bundle, com.tencent.tauth.IUiListener):void, file: classes9.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:137)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
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
                        	... 33 more
                        */
                    /*
                        this = this;
                        java.lang.String r0 = "openSDK_LOG.QQShare"
                        java.lang.String r1 = "shareToMobileQQ() -- start."
                        com.tencent.open.log.SLog.i(r0, r1)
                        java.lang.String r1 = "imageUrl"
                        java.lang.String r2 = r13.getString(r1)
                        java.lang.String r3 = "title"
                        java.lang.String r7 = r13.getString(r3)
                        java.lang.String r3 = "summary"
                        java.lang.String r8 = r13.getString(r3)
                        java.lang.StringBuilder r3 = new java.lang.StringBuilder
                        r3.<init>()
                        java.lang.String r4 = "shareToMobileQQ -- imageUrl: "
                        r3.append(r4)
                        r3.append(r2)
                        java.lang.String r3 = r3.toString()
                        com.tencent.open.log.SLog.v(r0, r3)
                        boolean r3 = android.text.TextUtils.isEmpty(r2)
                        if (r3 != 0) goto L9e
                        boolean r3 = com.tencent.open.utils.m.h(r2)
                        java.lang.String r4 = "4.3.0"
                        if (r3 == 0) goto L59
                        boolean r1 = com.tencent.open.utils.m.f(r12, r4)
                        if (r1 != 0) goto L46
                        r11.d(r12, r13, r14)
                        goto Laf
                    L46:
                        com.tencent.open.utils.c r1 = new com.tencent.open.utils.c
                        r1.<init>(r12)
                        com.tencent.connect.share.QQShare$1 r3 = new com.tencent.connect.share.QQShare$1
                        r4 = r3
                        r5 = r11
                        r6 = r13
                        r9 = r14
                        r10 = r12
                        r4.<init>()
                        r1.a(r2, r3)
                        goto Laf
                    L59:
                        r3 = 0
                        r13.putString(r1, r3)
                        boolean r1 = com.tencent.open.utils.m.f(r12, r4)
                        if (r1 == 0) goto L6c
                        java.lang.String r1 = "shareToMobileQQ -- QQ Version is < 4.3.0 "
                        com.tencent.open.log.SLog.d(r0, r1)
                        r11.d(r12, r13, r14)
                        goto Laf
                    L6c:
                        boolean r1 = com.tencent.open.utils.m.m(r2)
                        boolean r3 = com.tencent.open.utils.m.c()
                        java.lang.StringBuilder r4 = new java.lang.StringBuilder
                        r4.<init>()
                        java.lang.String r5 = "shareToMobileQQ -- QQ Version is > 4.3.0:isAppSpecificDir="
                        r4.append(r5)
                        r4.append(r1)
                        java.lang.String r1 = ",hasSDPermission:"
                        r4.append(r1)
                        r4.append(r3)
                        java.lang.String r1 = r4.toString()
                        com.tencent.open.log.SLog.d(r0, r1)
                        com.tencent.connect.share.QQShare$2 r1 = new com.tencent.connect.share.QQShare$2
                        r4 = r1
                        r5 = r11
                        r6 = r13
                        r9 = r14
                        r10 = r12
                        r4.<init>()
                        com.tencent.connect.share.a.a(r12, r2, r1)
                        goto Laf
                    L9e:
                        r1 = 1
                        java.lang.String r2 = "req_type"
                        int r1 = r13.getInt(r2, r1)
                        r2 = 5
                        if (r1 != r2) goto Lac
                        r11.c(r12, r13, r14)
                        goto Laf
                    Lac:
                        r11.d(r12, r13, r14)
                    Laf:
                        java.lang.String r12 = "shareToMobileQQ() -- end"
                        com.tencent.open.log.SLog.i(r0, r12)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.tencent.connect.share.QQShare.b(android.app.Activity, android.os.Bundle, com.tencent.tauth.IUiListener):void");
                }

                /* JADX WARN: Code restructure failed: missing block: B:18:0x00d1, code lost:
                    if (r2 != false) goto L21;
                 */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                private void c(Activity activity, Bundle bundle, IUiListener iUiListener) {
                    String str;
                    String string = bundle.getString("imageLocalUrl");
                    String str2 = null;
                    if (new File(string).length() >= 5242880) {
                        if (iUiListener != null) {
                            iUiListener.onError(new UiError(-16, Constants.MSG_SHARE_IMAGE_TOO_LARGE_ERROR, null));
                        }
                        SLog.e("openSDK_LOG.QQShare", "doShareImageToQQ -- error: \u56fe\u7247\u592a\u5927\uff0c\u8bf7\u538b\u7f29\u52305M\u5185\u518d\u5206\u4eab!");
                        return;
                    }
                    File a = g.a("Images");
                    if (a != null) {
                        str2 = a.getAbsolutePath() + File.separator + Constants.QQ_SHARE_TEMP_DIR;
                    } else {
                        SLog.i("openSDK_LOG.QQShare", "doShareImageToQQ() getExternalFilesDir return null");
                    }
                    File file = new File(string);
                    String absolutePath = file.getAbsolutePath();
                    String name = file.getName();
                    boolean m2 = m.m(absolutePath);
                    SLog.i("openSDK_LOG.QQShare", "doShareImageToQQ() check file: isAppSpecificDir=" + m2 + ",hasSDPermission=" + m.c() + ",fileDir=" + absolutePath);
                    ArrayList<String> arrayList = new ArrayList<>(2);
                    if (!m2 && !TextUtils.isEmpty(str2)) {
                        str = str2 + File.separator + name;
                        boolean a2 = m.a((Context) activity, absolutePath, str);
                        SLog.i("openSDK_LOG.QQShare", "doShareImageToQQ() sd permission not denied. copy to app specific:" + str + ",isSuccess=" + a2);
                    }
                    str = absolutePath;
                    arrayList.add(absolutePath);
                    arrayList.add(str);
                    SLog.i("openSDK_LOG.QQShare", "doShareImageToQQ() destFilePaths=[" + arrayList.get(0) + DYConstants.DY_REGEX_COMMA + arrayList.get(1) + "]");
                    bundle.putStringArrayList("imageLocalUrlArray", arrayList);
                    d(activity, bundle, iUiListener);
                }
            }
