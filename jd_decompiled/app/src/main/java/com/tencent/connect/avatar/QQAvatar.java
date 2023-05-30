package com.tencent.connect.avatar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.Toast;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.log.SLog;
import com.tencent.open.utils.g;
import com.tencent.open.utils.k;
import com.tencent.open.utils.m;
import com.tencent.tauth.IUiListener;

/* loaded from: classes9.dex */
public class QQAvatar extends BaseApi {
    public static final String FROM_SDK_AVATAR_SET_IMAGE = "FROM_SDK_AVATAR_SET_IMAGE";
    private IUiListener a;

    public QQAvatar(QQToken qQToken) {
        super(qQToken);
    }

    private Intent a(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, ImageActivity.class);
        return intent;
    }

    public void setAvatar(Activity activity, Uri uri, IUiListener iUiListener, int i2) {
        if (com.tencent.connect.a.a("QQAvatar", iUiListener)) {
            return;
        }
        IUiListener iUiListener2 = this.a;
        if (iUiListener2 != null) {
            iUiListener2.onCancel();
        }
        this.a = iUiListener;
        Bundle bundle = new Bundle();
        bundle.putString("picture", uri.toString());
        bundle.putInt("exitAnim", i2);
        bundle.putString("appid", this.f16153c.getAppId());
        bundle.putString(Constants.PARAM_ACCESS_TOKEN, this.f16153c.getAccessToken());
        bundle.putLong(Constants.PARAM_EXPIRES_IN, this.f16153c.getExpireTimeInSecond());
        bundle.putString("openid", this.f16153c.getOpenId());
        Intent a = a(activity);
        Bundle a2 = m.a(this.f16153c.getOpenId(), Constants.VIA_SET_AVATAR, "12", "18", this.f16153c.getAppId());
        a.putExtra(Constants.KEY_PASS_REPORT_VIA_TIMELY, true);
        a.putExtra(Constants.KEY_PASS_REPORT_VIA_PARAM, a2);
        a(activity, bundle, a);
    }

    public void setAvatarByQQ(Activity activity, Uri uri, IUiListener iUiListener) {
        if (com.tencent.connect.a.a("QQAvatar", iUiListener)) {
            return;
        }
        IUiListener iUiListener2 = this.a;
        if (iUiListener2 != null) {
            iUiListener2.onCancel();
        }
        this.a = iUiListener;
        if (!k.b(activity)) {
            Toast.makeText(activity.getApplicationContext(), "\u5f53\u524d\u624b\u673a\u672a\u5b89\u88c5QQ\uff0c\u8bf7\u5b89\u88c5\u6700\u65b0\u7248QQ\u540e\u518d\u8bd5\u3002", 1).show();
        } else if (k.c(activity, "8.0.0") < 0) {
            Toast.makeText(activity.getApplicationContext(), "\u5f53\u524d\u624b\u673aQQ\u7248\u672c\u8fc7\u4f4e\uff0c\u4e0d\u652f\u6301\u8bbe\u7f6e\u5934\u50cf\u529f\u80fd\u3002", 1).show();
        } else {
            String a = m.a(activity);
            StringBuffer stringBuffer = new StringBuffer("mqqapi://profile/sdk_avatar_edit?");
            if (!TextUtils.isEmpty(a)) {
                if (a.length() > 20) {
                    a = a.substring(0, 20) + "...";
                }
                stringBuffer.append("&app_name=" + Base64.encodeToString(m.j(a), 2));
            }
            String appId = this.f16153c.getAppId();
            String openId = this.f16153c.getOpenId();
            if (!TextUtils.isEmpty(appId)) {
                stringBuffer.append("&share_id=" + appId);
            }
            if (!TextUtils.isEmpty(openId)) {
                stringBuffer.append("&open_id=" + Base64.encodeToString(m.j(openId), 2));
            }
            String b = m.b(activity, uri);
            if (!TextUtils.isEmpty(b)) {
                try {
                    activity.grantUriPermission("com.tencent.mobileqq", uri, 3);
                    stringBuffer.append("&set_uri=" + Base64.encodeToString(m.j(uri.toString()), 2));
                } catch (Exception e2) {
                    SLog.e("QQAvatar", "Exception", e2);
                }
            }
            if (!TextUtils.isEmpty(b)) {
                stringBuffer.append("&set_path=" + Base64.encodeToString(m.j(b), 2));
            }
            stringBuffer.append("&sdk_version=" + Base64.encodeToString(m.j(Constants.SDK_VERSION), 2));
            SLog.v("QQAVATAR", "-->set avatar, url: " + stringBuffer.toString());
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setPackage("com.tencent.mobileqq");
            intent.putExtra("FROM_WHERE", FROM_SDK_AVATAR_SET_IMAGE);
            intent.putExtra(Constants.PARAM_PKG_NAME, activity.getPackageName());
            intent.setData(Uri.parse(stringBuffer.toString()));
            UIListenerManager.getInstance().setListenerWithRequestcode(10108, iUiListener);
            a(activity, 10108, intent, false);
        }
    }

    public void setDynamicAvatar(Activity activity, Uri uri, IUiListener iUiListener) {
        if (com.tencent.connect.a.a("QQAvatar", iUiListener)) {
            return;
        }
        IUiListener iUiListener2 = this.a;
        if (iUiListener2 != null) {
            iUiListener2.onCancel();
        }
        this.a = iUiListener;
        if (!k.b(activity)) {
            Toast.makeText(activity.getApplicationContext(), "\u5f53\u524d\u624b\u673a\u672a\u5b89\u88c5QQ\uff0c\u8bf7\u5b89\u88c5\u6700\u65b0\u7248QQ\u540e\u518d\u8bd5\u3002", 1).show();
        } else if (k.c(activity, "8.0.5") < 0) {
            Toast.makeText(activity.getApplicationContext(), "\u5f53\u524d\u624b\u673aQQ\u7248\u672c\u8fc7\u4f4e\uff0c\u4e0d\u652f\u6301\u8bbe\u7f6e\u5934\u50cf\u529f\u80fd\u3002", 1).show();
        } else {
            String a = m.a(activity);
            StringBuffer stringBuffer = new StringBuffer("mqqapi://profile/sdk_dynamic_avatar_edit?");
            if (!TextUtils.isEmpty(a)) {
                if (a.length() > 20) {
                    a = a.substring(0, 20) + "...";
                }
                stringBuffer.append("&app_name=" + Base64.encodeToString(m.j(a), 2));
            }
            String appId = this.f16153c.getAppId();
            String openId = this.f16153c.getOpenId();
            if (!TextUtils.isEmpty(appId)) {
                stringBuffer.append("&share_id=" + appId);
            }
            if (!TextUtils.isEmpty(openId)) {
                stringBuffer.append("&open_id=" + Base64.encodeToString(m.j(openId), 2));
            }
            String b = m.b(activity, uri);
            if (!TextUtils.isEmpty(b)) {
                try {
                    activity.grantUriPermission("com.tencent.mobileqq", uri, 3);
                    stringBuffer.append("&video_uri=");
                    stringBuffer.append(Base64.encodeToString(m.j(uri.toString()), 2));
                } catch (Exception e2) {
                    SLog.e("QQAvatar", "Exception", e2);
                }
            }
            if (!TextUtils.isEmpty(b)) {
                stringBuffer.append("&video_path=" + Base64.encodeToString(m.j(b), 2));
            }
            stringBuffer.append("&sdk_version=" + Base64.encodeToString(m.j(Constants.SDK_VERSION), 2));
            SLog.v("QQAVATAR", "-->set dynamic avatar, url: " + stringBuffer.toString());
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setPackage("com.tencent.mobileqq");
            intent.putExtra("FROM_WHERE", FROM_SDK_AVATAR_SET_IMAGE);
            intent.putExtra(Constants.PARAM_PKG_NAME, activity.getPackageName());
            intent.setData(Uri.parse(stringBuffer.toString()));
            UIListenerManager.getInstance().setListenerWithRequestcode(10110, iUiListener);
            a(activity, 10110, intent, false);
        }
    }

    private void a(Activity activity, Bundle bundle, Intent intent) {
        a(bundle);
        intent.putExtra(Constants.KEY_ACTION, "action_avatar");
        intent.putExtra(Constants.KEY_PARAMS, bundle);
        UIListenerManager.getInstance().setListenerWithRequestcode(11102, this.a);
        a(activity, intent, 11102);
    }

    private void a(Bundle bundle) {
        QQToken qQToken = this.f16153c;
        if (qQToken != null) {
            bundle.putString("appid", qQToken.getAppId());
            if (this.f16153c.isSessionValid()) {
                bundle.putString(Constants.PARAM_KEY_STR, this.f16153c.getAccessToken());
                bundle.putString(Constants.PARAM_KEY_TYPE, "0x80");
            }
            String openId = this.f16153c.getOpenId();
            if (openId != null) {
                bundle.putString("hopenid", openId);
            }
            bundle.putString(Constants.PARAM_PLATFORM, "androidqz");
            try {
                bundle.putString(Constants.PARAM_PLATFORM_ID, g.a().getSharedPreferences(Constants.PREFERENCE_PF, 0).getString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF));
            } catch (Exception e2) {
                e2.printStackTrace();
                bundle.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
            }
        }
        bundle.putString("sdkv", Constants.SDK_VERSION);
        bundle.putString("sdkp", com.jingdong.jdsdk.a.a.a);
    }
}
