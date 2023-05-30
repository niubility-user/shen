package com.tencent.connect.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.open.TDialog;
import com.tencent.open.b.e;
import com.tencent.open.log.SLog;
import com.tencent.open.utils.g;
import com.tencent.open.utils.m;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class QzonePublish extends BaseApi {
    public static final String HULIAN_CALL_BACK = "hulian_call_back";
    public static final String HULIAN_EXTRA_SCENE = "hulian_extra_scene";
    public static final String PUBLISH_TO_QZONE_APP_NAME = "appName";
    public static final String PUBLISH_TO_QZONE_EXTMAP = "extMap";
    public static final String PUBLISH_TO_QZONE_IMAGE_URL = "imageUrl";
    public static final String PUBLISH_TO_QZONE_KEY_TYPE = "req_type";
    public static final String PUBLISH_TO_QZONE_SUMMARY = "summary";
    public static final int PUBLISH_TO_QZONE_TYPE_PUBLISHMOOD = 3;
    public static final int PUBLISH_TO_QZONE_TYPE_PUBLISHVIDEO = 4;
    public static final String PUBLISH_TO_QZONE_VIDEO_DURATION = "videoDuration";
    public static final String PUBLISH_TO_QZONE_VIDEO_PATH = "videoPath";
    public static final String PUBLISH_TO_QZONE_VIDEO_SIZE = "videoSize";

    public QzonePublish(Context context, QQToken qQToken) {
        super(qQToken);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ca A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0286  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void b(Activity activity, Bundle bundle, IUiListener iUiListener) {
        String str;
        String str2;
        String str3;
        String appId;
        String openId;
        String str4;
        String str5;
        String str6;
        String str7;
        Bundle bundle2;
        JSONObject jSONObject;
        SLog.i("openSDK_LOG.QzonePublish", "doPublishToQzone() --start");
        StringBuffer stringBuffer = new StringBuffer("mqqapi://qzone/publish?src_type=app&version=1&file_type=news");
        ArrayList<String> stringArrayList = bundle.getStringArrayList("imageUrl");
        String string = bundle.getString("summary");
        int i2 = bundle.getInt("req_type", 3);
        String string2 = bundle.getString("appName");
        String string3 = bundle.getString("videoPath");
        int i3 = bundle.getInt(PUBLISH_TO_QZONE_VIDEO_DURATION);
        long j2 = bundle.getLong(PUBLISH_TO_QZONE_VIDEO_SIZE);
        try {
            bundle2 = bundle.getBundle("extMap");
        } catch (Exception e2) {
            e = e2;
            str = "";
        }
        if (bundle2 != null) {
            Set<String> keySet = bundle2.keySet();
            str = "";
            try {
                jSONObject = new JSONObject();
                Iterator<String> it = keySet.iterator();
                while (it.hasNext()) {
                    Iterator<String> it2 = it;
                    String next = it.next();
                    if (TextUtils.isEmpty(bundle2.getString(next))) {
                        str2 = string2;
                    } else {
                        str2 = string2;
                        try {
                            jSONObject.put(next, bundle2.getString(next));
                        } catch (Exception e3) {
                            e = e3;
                            SLog.e("openSDK_LOG.QzonePublish", "publishToQzone()  --error parse extmap", e);
                            str3 = str;
                            appId = this.f16153c.getAppId();
                            openId = this.f16153c.getOpenId();
                            SLog.v("openSDK_LOG.QzonePublish", "openId:" + openId);
                            if (3 == i2) {
                            }
                            str4 = str3;
                            str5 = "openSDK_LOG.QzonePublish";
                            str6 = openId;
                            str7 = str;
                            if (4 == i2) {
                            }
                            String str8 = str7;
                            if (!TextUtils.isEmpty(string)) {
                            }
                            if (!TextUtils.isEmpty(appId)) {
                            }
                            if (!TextUtils.isEmpty(str2)) {
                            }
                            if (!m.e(str6)) {
                            }
                            if (!TextUtils.isEmpty(str4)) {
                            }
                            stringBuffer.append("&req_type=" + Base64.encodeToString(m.j(String.valueOf(i2)), 2));
                            SLog.v(str5, "doPublishToQzone, url: " + stringBuffer.toString());
                            com.tencent.connect.a.a.a(g.a(), this.f16153c, "requireApi", "shareToNativeQQ");
                            Intent intent = new Intent("android.intent.action.VIEW");
                            intent.setPackage("com.tencent.mobileqq");
                            intent.setData(Uri.parse(stringBuffer.toString()));
                            intent.putExtra(Constants.PARAM_PKG_NAME, activity.getPackageName());
                            intent.putExtra(Constants.KEY_PASS_REPORT_VIA_PARAM, m.a(this.f16153c.getOpenId(), "11", "3", Constants.VIA_SHARE_TO_QZONE, this.f16153c.getAppId(), str8, "", "", "0", "1", "0"));
                            a(activity, 10104, intent, false);
                            SLog.i(SLog.TAG, "doPublishToQzone() --end");
                        }
                    }
                    it = it2;
                    string2 = str2;
                }
                str2 = string2;
            } catch (Exception e4) {
                e = e4;
                str2 = string2;
                SLog.e("openSDK_LOG.QzonePublish", "publishToQzone()  --error parse extmap", e);
                str3 = str;
                appId = this.f16153c.getAppId();
                openId = this.f16153c.getOpenId();
                SLog.v("openSDK_LOG.QzonePublish", "openId:" + openId);
                if (3 == i2) {
                }
                str4 = str3;
                str5 = "openSDK_LOG.QzonePublish";
                str6 = openId;
                str7 = str;
                if (4 == i2) {
                }
                String str82 = str7;
                if (!TextUtils.isEmpty(string)) {
                }
                if (!TextUtils.isEmpty(appId)) {
                }
                if (!TextUtils.isEmpty(str2)) {
                }
                if (!m.e(str6)) {
                }
                if (!TextUtils.isEmpty(str4)) {
                }
                stringBuffer.append("&req_type=" + Base64.encodeToString(m.j(String.valueOf(i2)), 2));
                SLog.v(str5, "doPublishToQzone, url: " + stringBuffer.toString());
                com.tencent.connect.a.a.a(g.a(), this.f16153c, "requireApi", "shareToNativeQQ");
                Intent intent2 = new Intent("android.intent.action.VIEW");
                intent2.setPackage("com.tencent.mobileqq");
                intent2.setData(Uri.parse(stringBuffer.toString()));
                intent2.putExtra(Constants.PARAM_PKG_NAME, activity.getPackageName());
                intent2.putExtra(Constants.KEY_PASS_REPORT_VIA_PARAM, m.a(this.f16153c.getOpenId(), "11", "3", Constants.VIA_SHARE_TO_QZONE, this.f16153c.getAppId(), str82, "", "", "0", "1", "0"));
                a(activity, 10104, intent2, false);
                SLog.i(SLog.TAG, "doPublishToQzone() --end");
            }
            if (jSONObject.length() > 0) {
                str3 = jSONObject.toString();
                appId = this.f16153c.getAppId();
                openId = this.f16153c.getOpenId();
                SLog.v("openSDK_LOG.QzonePublish", "openId:" + openId);
                if (3 == i2 || stringArrayList == null) {
                    str4 = str3;
                    str5 = "openSDK_LOG.QzonePublish";
                    str6 = openId;
                    str7 = str;
                } else {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    StringBuffer stringBuffer3 = new StringBuffer();
                    str5 = "openSDK_LOG.QzonePublish";
                    int size = stringArrayList.size();
                    str4 = str3;
                    int i4 = 0;
                    while (i4 < size) {
                        String str9 = openId;
                        stringBuffer2.append(URLEncoder.encode(stringArrayList.get(i4)));
                        String a = m.a(appId, activity, stringArrayList.get(i4), iUiListener);
                        if (!TextUtils.isEmpty(a)) {
                            stringBuffer3.append(URLEncoder.encode(a));
                        }
                        if (i4 != size - 1) {
                            stringBuffer2.append(";");
                            stringBuffer3.append(";");
                        }
                        i4++;
                        openId = str9;
                    }
                    str6 = openId;
                    stringBuffer.append("&image_url=" + Base64.encodeToString(m.j(stringBuffer2.toString()), 2));
                    if (!TextUtils.isEmpty(stringBuffer3.toString())) {
                        stringBuffer.append("&image_uri=" + Base64.encodeToString(m.j(stringBuffer3.toString()), 2));
                    }
                    str7 = "7";
                }
                if (4 == i2) {
                    stringBuffer.append("&videoPath=" + Base64.encodeToString(m.j(string3), 2));
                    String a2 = m.a(appId, activity, string3, iUiListener);
                    if (!TextUtils.isEmpty(a2)) {
                        stringBuffer.append("&videoUri=" + Base64.encodeToString(m.j(a2), 2));
                    }
                    stringBuffer.append("&videoDuration=" + Base64.encodeToString(m.j(String.valueOf(i3)), 2));
                    stringBuffer.append("&videoSize=" + Base64.encodeToString(m.j(String.valueOf(j2)), 2));
                    str7 = "8";
                }
                String str822 = str7;
                if (!TextUtils.isEmpty(string)) {
                    stringBuffer.append("&description=" + Base64.encodeToString(m.j(string), 2));
                }
                if (!TextUtils.isEmpty(appId)) {
                    stringBuffer.append("&share_id=" + appId);
                }
                if (!TextUtils.isEmpty(str2)) {
                    stringBuffer.append("&app_name=" + Base64.encodeToString(m.j(str2), 2));
                }
                if (!m.e(str6)) {
                    stringBuffer.append("&open_id=" + Base64.encodeToString(m.j(str6), 2));
                }
                if (!TextUtils.isEmpty(str4)) {
                    stringBuffer.append("&share_qzone_ext_str=" + Base64.encodeToString(m.j(str4), 2));
                }
                stringBuffer.append("&req_type=" + Base64.encodeToString(m.j(String.valueOf(i2)), 2));
                SLog.v(str5, "doPublishToQzone, url: " + stringBuffer.toString());
                com.tencent.connect.a.a.a(g.a(), this.f16153c, "requireApi", "shareToNativeQQ");
                Intent intent22 = new Intent("android.intent.action.VIEW");
                intent22.setPackage("com.tencent.mobileqq");
                intent22.setData(Uri.parse(stringBuffer.toString()));
                intent22.putExtra(Constants.PARAM_PKG_NAME, activity.getPackageName());
                intent22.putExtra(Constants.KEY_PASS_REPORT_VIA_PARAM, m.a(this.f16153c.getOpenId(), "11", "3", Constants.VIA_SHARE_TO_QZONE, this.f16153c.getAppId(), str822, "", "", "0", "1", "0"));
                a(activity, 10104, intent22, false);
                SLog.i(SLog.TAG, "doPublishToQzone() --end");
            }
        } else {
            str = "";
            str2 = string2;
        }
        str3 = str;
        appId = this.f16153c.getAppId();
        openId = this.f16153c.getOpenId();
        SLog.v("openSDK_LOG.QzonePublish", "openId:" + openId);
        if (3 == i2) {
        }
        str4 = str3;
        str5 = "openSDK_LOG.QzonePublish";
        str6 = openId;
        str7 = str;
        if (4 == i2) {
        }
        String str8222 = str7;
        if (!TextUtils.isEmpty(string)) {
        }
        if (!TextUtils.isEmpty(appId)) {
        }
        if (!TextUtils.isEmpty(str2)) {
        }
        if (!m.e(str6)) {
        }
        if (!TextUtils.isEmpty(str4)) {
        }
        stringBuffer.append("&req_type=" + Base64.encodeToString(m.j(String.valueOf(i2)), 2));
        SLog.v(str5, "doPublishToQzone, url: " + stringBuffer.toString());
        com.tencent.connect.a.a.a(g.a(), this.f16153c, "requireApi", "shareToNativeQQ");
        Intent intent222 = new Intent("android.intent.action.VIEW");
        intent222.setPackage("com.tencent.mobileqq");
        intent222.setData(Uri.parse(stringBuffer.toString()));
        intent222.putExtra(Constants.PARAM_PKG_NAME, activity.getPackageName());
        intent222.putExtra(Constants.KEY_PASS_REPORT_VIA_PARAM, m.a(this.f16153c.getOpenId(), "11", "3", Constants.VIA_SHARE_TO_QZONE, this.f16153c.getAppId(), str8222, "", "", "0", "1", "0"));
        a(activity, 10104, intent222, false);
        SLog.i(SLog.TAG, "doPublishToQzone() --end");
    }

    public void publishToQzone(final Activity activity, final Bundle bundle, final IUiListener iUiListener) {
        SLog.i("openSDK_LOG.QzonePublish", "publishToQzone() -- start");
        if (com.tencent.connect.a.a("openSDK_LOG.QzonePublish", iUiListener)) {
            return;
        }
        if (bundle == null) {
            iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_NULL_ERROR, null));
            SLog.e("openSDK_LOG.QzonePublish", "-->publishToQzone, params is null");
            e.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f16153c.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, Constants.MSG_PARAM_NULL_ERROR);
        } else if (!m.f(activity)) {
            iUiListener.onError(new UiError(-15, Constants.MSG_PARAM_VERSION_TOO_LOW, null));
            SLog.e("openSDK_LOG.QzonePublish", "-->publishToQzone, this is not support below qq 5.9.5");
            e.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f16153c.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "publicToQzone, this is not support below qq 5.9.5");
            new TDialog(activity, "", a(""), null, this.f16153c).show();
        } else {
            String a = m.a(activity);
            int i2 = 0;
            if (a == null) {
                a = bundle.getString("appName");
            } else if (a.length() > 20) {
                a = a.substring(0, 20) + "...";
            }
            if (!TextUtils.isEmpty(a)) {
                bundle.putString("appName", a);
            }
            int i3 = bundle.getInt("req_type");
            if (i3 == 3) {
                ArrayList<String> stringArrayList = bundle.getStringArrayList("imageUrl");
                if (stringArrayList != null && stringArrayList.size() > 0) {
                    while (i2 < stringArrayList.size()) {
                        if (!m.i(stringArrayList.get(i2))) {
                            stringArrayList.remove(i2);
                            i2--;
                        }
                        i2++;
                    }
                    bundle.putStringArrayList("imageUrl", stringArrayList);
                }
                b(activity, bundle, iUiListener);
                SLog.i("openSDK_LOG.QzonePublish", "publishToQzone() --end");
            } else if (i3 == 4) {
                final String string = bundle.getString("videoPath");
                if (!m.i(string)) {
                    SLog.e("openSDK_LOG.QzonePublish", "publishToQzone() video url invalid");
                    iUiListener.onError(new UiError(-5, Constants.MSG_PUBLISH_VIDEO_ERROR, null));
                    return;
                }
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.tencent.connect.share.QzonePublish.1
                    @Override // android.media.MediaPlayer.OnPreparedListener
                    public void onPrepared(MediaPlayer mediaPlayer2) {
                        long length = new File(string).length();
                        int duration = mediaPlayer2.getDuration();
                        bundle.putString("videoPath", string);
                        bundle.putInt(QzonePublish.PUBLISH_TO_QZONE_VIDEO_DURATION, duration);
                        bundle.putLong(QzonePublish.PUBLISH_TO_QZONE_VIDEO_SIZE, length);
                        QzonePublish.this.b(activity, bundle, iUiListener);
                        SLog.i("openSDK_LOG.QzonePublish", "publishToQzone() --end");
                    }
                });
                mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.tencent.connect.share.QzonePublish.2
                    @Override // android.media.MediaPlayer.OnErrorListener
                    public boolean onError(MediaPlayer mediaPlayer2, int i4, int i5) {
                        SLog.e("openSDK_LOG.QzonePublish", "publishToQzone() mediaplayer onError()");
                        iUiListener.onError(new UiError(-5, Constants.MSG_PUBLISH_VIDEO_ERROR, null));
                        return false;
                    }
                });
                try {
                    mediaPlayer.setDataSource(string);
                    mediaPlayer.prepareAsync();
                } catch (Exception unused) {
                    SLog.e("openSDK_LOG.QzonePublish", "publishToQzone() exception(s) occurred when preparing mediaplayer");
                    iUiListener.onError(new UiError(-5, Constants.MSG_PUBLISH_VIDEO_ERROR, null));
                }
            } else {
                iUiListener.onError(new UiError(-5, Constants.MSG_SHARE_TYPE_ERROR, null));
                SLog.e("openSDK_LOG.QzonePublish", "publishToQzone() error--end\u8bf7\u9009\u62e9\u652f\u6301\u7684\u5206\u4eab\u7c7b\u578b");
                e.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f16153c.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "publishToQzone() \u8bf7\u9009\u62e9\u652f\u6301\u7684\u5206\u4eab\u7c7b\u578b");
            }
        }
    }
}
