package com.jingdong.common.navutils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.navutils.NavCenterParser;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.deeplink.a;
import com.jingdong.sdk.deeplink.b;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class NavCenter {
    private static final String NAV_MAIDIAN_DEGRADE_TO_H5 = "navMaidianDegradeToH5";
    private static final String NAV_MAIDIAN_DEGRADE_TO_ORIGIN = "navMaidianDegradeToOrigin";
    private static final String NAV_MAIDIAN_INTENT_NULL = "navMaidianIntentNull";
    private static final String NAV_MAIDIAN_NOT_JUMP = "navMaidianNotJump";
    private static final String NAV_MAIDIAN_RESOLVE_AGAIN = "navMaidianRelolveAgain";
    private static final String NAV_MAIDIAN_RESOLVE_AGAIN1 = "navMaidianRelolveAgain1";
    private static final String NAV_MAIDIAN_RESOLVE_ERROR = "navMaidianRelolveError";
    private static final String NAV_MAIDIAN_RESOLVE_ERROR_NOT_JUMP = "navMaidianRelolveErrorNotJump";
    private static final String NAV_MAIDIAN_RESOLVE_ERROR_TO_H5 = "navMaidianRelolveErrorToH5";
    private static final String NAV_MAIDIAN_RESOLVE_INFO = "navMaidianRelolveInfo";
    private static final String NAV_MAIDIAN_TOTAL = "navMaidianTotal";
    private static final String NAV_MAIDIAN_USE_PLUGIN = "navMaidianUsePlugin";
    private Bundle bundle;
    private final Context context;
    private final Intent intent;
    private int requestCode;

    private NavCenter(Context context) {
        this.context = context;
        Intent intent = new Intent("android.intent.action.VIEW");
        this.intent = intent;
        intent.setPackage(context.getPackageName());
        this.requestCode = -1;
    }

    private Intent createIntent(Uri uri) {
        NavCenterParser.NavActivityInfo navActivityInfo;
        ComponentName componentName;
        String str;
        if (uri.getBooleanQueryParameter("isH5Page", false)) {
            createWebIntent(uri.buildUpon().appendQueryParameter("isDegrade2H5", DYConstants.DY_TRUE).build());
            return this.intent;
        }
        this.intent.setData(uri);
        ResolveInfo resolveActivity = this.context.getPackageManager().resolveActivity(this.intent, 65536);
        if (resolveActivity == null) {
            sendMaiDianData2(NAV_MAIDIAN_RESOLVE_AGAIN, uri.getPath());
            resolveActivity = getResolveInfo(this.context.getPackageManager().queryIntentActivities(this.intent, 65536));
        }
        if (resolveActivity == null) {
            sendMaiDianData2(NAV_MAIDIAN_RESOLVE_AGAIN1, uri.getPath());
            navActivityInfo = NavCenterParser.getInstance().parseUri(uri);
            if (navActivityInfo == null) {
                sendMaiDianData2(NAV_MAIDIAN_RESOLVE_ERROR, uri.getHost() + "" + uri.getPath());
                if (DegradeConfig.getInstance().shouldNav2WebIfException()) {
                    sendMaiDianData2(NAV_MAIDIAN_RESOLVE_ERROR_TO_H5, uri.getPath());
                    createWebIntent(uri.buildUpon().appendQueryParameter("isDegrade2H5", DYConstants.DY_TRUE).build());
                    return this.intent;
                }
                sendMaiDianData2(NAV_MAIDIAN_RESOLVE_ERROR_NOT_JUMP, uri.getPath());
                throw new ActivityNotFoundException("No Activity found to handle " + this.intent);
            }
        } else {
            navActivityInfo = null;
        }
        if (resolveActivity != null) {
            ActivityInfo activityInfo = resolveActivity.activityInfo;
            componentName = new ComponentName(activityInfo.packageName, activityInfo.name);
            str = NavUtils.findBundleNameByComponentName(this.context, componentName);
        } else {
            ComponentName componentName2 = new ComponentName(this.context.getPackageName(), navActivityInfo.activityName);
            String findBundleNameByNavActivityInfo = NavUtils.findBundleNameByNavActivityInfo(navActivityInfo);
            componentName = componentName2;
            str = findBundleNameByNavActivityInfo;
        }
        if (!TextUtils.isEmpty(str)) {
            sendMaiDianData2(NAV_MAIDIAN_TOTAL, str);
            if (NavUtils.isUsePlugin(str)) {
                this.intent.setComponent(componentName);
                Bundle bundle = this.bundle;
                if (bundle != null) {
                    this.intent.putExtras(bundle);
                }
                sendMaiDianData(NAV_MAIDIAN_USE_PLUGIN, str);
            } else if (NavUtils.isDegradeToOrigin(str)) {
                this.intent.addCategory("com.jingdong.app.mall.DEGRADE_ORIGIN");
                Bundle bundle2 = this.bundle;
                if (bundle2 != null) {
                    this.intent.putExtras(bundle2);
                }
                sendMaiDianData(NAV_MAIDIAN_DEGRADE_TO_ORIGIN, str);
            } else if (NavUtils.isDegradeToH5(str)) {
                createWebIntent(uri.buildUpon().appendQueryParameter("isDegrade2H5", DYConstants.DY_TRUE).build());
                sendMaiDianData(NAV_MAIDIAN_DEGRADE_TO_H5, str);
            } else {
                sendMaiDianData(NAV_MAIDIAN_NOT_JUMP, str);
                return null;
            }
            return this.intent;
        }
        throw new IllegalStateException("can not find bundleName");
    }

    private Intent createWebIntent(Uri uri) {
        URLParamMap uRLParamMap = new URLParamMap();
        uRLParamMap.put(RemoteMessageConst.TO, uri.toString());
        SerializableContainer serializableContainer = new SerializableContainer();
        serializableContainer.setMap(uRLParamMap);
        a d = b.a().d(new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_WEBACTIVITY).toString());
        if (d != null) {
            this.intent.setComponent(new ComponentName(this.context, d.a()));
            this.intent.putExtra("urlParamMap", serializableContainer);
            this.intent.putExtra("urlAction", RemoteMessageConst.TO);
            return this.intent;
        }
        return null;
    }

    public static NavCenter from(Context context) {
        if (context != null) {
            return new NavCenter(context);
        }
        throw new IllegalArgumentException("context is null!");
    }

    private ResolveInfo getResolveInfo(List<ResolveInfo> list) {
        if (list != null && list.size() != 0) {
            if (list.size() == 1) {
                ResolveInfo resolveInfo = list.get(0);
                sendMaiDianData2(NAV_MAIDIAN_RESOLVE_INFO, "getResolveInfo1:" + resolveInfo.activityInfo.packageName);
                return resolveInfo;
            }
            String packageName = this.context.getPackageName();
            if (TextUtils.isEmpty(packageName)) {
                sendMaiDianData2(NAV_MAIDIAN_RESOLVE_INFO, "getResolveInfo2");
                return null;
            }
            ArrayList arrayList = new ArrayList();
            String str = "";
            for (ResolveInfo resolveInfo2 : list) {
                str = resolveInfo2.activityInfo.packageName + ":" + str;
                if (packageName.equals(resolveInfo2.activityInfo.packageName)) {
                    arrayList.add(resolveInfo2);
                }
            }
            sendMaiDianData2(NAV_MAIDIAN_RESOLVE_INFO, "getResolveInfo3:" + str);
            if (arrayList.size() <= 0) {
                sendMaiDianData2(NAV_MAIDIAN_RESOLVE_INFO, "getResolveInfo4");
                return null;
            }
            return (ResolveInfo) arrayList.get(0);
        }
        sendMaiDianData2(NAV_MAIDIAN_RESOLVE_INFO, "getResolveInfo0");
        return null;
    }

    private void sendMaiDianData(String str, String str2) {
        int versionCode = PackageInfoUtil.getVersionCode();
        int bundleConfig = DegradeConfig.getInstance().getBundleConfig(str2);
        JDMtaUtils.sendCommonData(this.context, str, str2 + CartConstant.KEY_YB_INFO_LINK + bundleConfig, "NavCenter.createIntent", (Object) null, "" + versionCode, "", "");
    }

    private void sendMaiDianData2(String str, String str2) {
        int versionCode = PackageInfoUtil.getVersionCode();
        JDMtaUtils.sendCommonData(this.context, str, str2, "NavCenter.createIntent", (Object) null, "" + versionCode, "", "");
    }

    public NavCenter addCategory(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.intent.addCategory(str);
            return this;
        }
        throw new IllegalArgumentException("category is empty!!");
    }

    public NavCenter addFlags(int i2) {
        this.intent.addFlags(i2);
        return this;
    }

    public boolean navTo(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return navTo(Uri.parse(str));
    }

    public NavCenter putBundle(Bundle bundle) {
        if (bundle != null) {
            this.bundle = bundle;
            return this;
        }
        throw new IllegalArgumentException("bundle is null!");
    }

    public NavCenter setRequestCode(int i2) {
        if (this.context instanceof Activity) {
            this.requestCode = i2;
            return this;
        }
        throw new IllegalStateException("Only valid from Activity, but from " + this.context);
    }

    public boolean navTo(Uri uri) {
        if (uri == null) {
            return false;
        }
        Intent createIntent = createIntent(uri);
        if (createIntent == null) {
            sendMaiDianData2(NAV_MAIDIAN_INTENT_NULL, uri.getPath());
            return false;
        }
        int i2 = this.requestCode;
        if (i2 >= 0) {
            ((Activity) this.context).startActivityForResult(createIntent, i2);
            return true;
        }
        if (!(this.context instanceof Activity)) {
            createIntent.addFlags(268435456);
        }
        this.context.startActivity(createIntent);
        return true;
    }
}
