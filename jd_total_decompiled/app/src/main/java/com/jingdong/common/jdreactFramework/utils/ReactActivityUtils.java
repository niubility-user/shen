package com.jingdong.common.jdreactFramework.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.lib.babel.task.viewkit.VKEventUtil;
import com.jingdong.app.mall.bundle.jdweather.action.JDWeatherActionKey;
import com.jingdong.app.mall.e;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.activities.JDReactDebugTabActivity;
import com.jingdong.common.jdreactFramework.activities.JDReactNativeCommonActivity;
import com.jingdong.common.jdreactFramework.activities.JDReactNativeFlightOrderDetailActivity;
import com.jingdong.common.jdreactFramework.activities.JDReactNativeGamePropBuyActivity;
import com.jingdong.common.jdreactFramework.activities.JDReactNativeJShopMineActivity;
import com.jingdong.common.jdreactFramework.activities.JDReactNativePaySucessActivity;
import com.jingdong.common.jdreactFramework.activities.JDReactNativeSignRankingListActivity;
import com.jingdong.common.jdreactFramework.activities.JDReactNativeUPhoneModuleActivity;
import com.jingdong.common.jdreactFramework.activities.UPhoneModuleTransparentActivity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.utils.CommonBridge;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import jd.wjlogin_sdk.util.f;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class ReactActivityUtils extends ReactActivityUtilsHelperExt {
    private static boolean bring2Front(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        JDReactNativeUPhoneModuleActivity.setLastActivity(JDReactHelper.newInstance().getCurrentMyActivity());
        try {
            intent.setComponent(new ComponentName(JDReactNativeUPhoneModuleActivity.instance.getApplicationContext(), JDReactNativeUPhoneModuleActivity.class));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        intent.addFlags(270663680);
        JDReactNativeUPhoneModuleActivity.instance.getApplicationContext().startActivity(intent);
        return true;
    }

    public static synchronized void closeUMGuardActivity() {
        synchronized (ReactActivityUtils.class) {
            UPhoneModuleTransparentActivity uPhoneModuleTransparentActivity = UPhoneModuleTransparentActivity.instance;
            if (uPhoneModuleTransparentActivity != null) {
                uPhoneModuleTransparentActivity.finish();
            }
        }
    }

    public static synchronized JDReactNativeUPhoneModuleActivity getUMActivity() {
        synchronized (ReactActivityUtils.class) {
            JDReactNativeUPhoneModuleActivity jDReactNativeUPhoneModuleActivity = JDReactNativeUPhoneModuleActivity.instance;
            if (jDReactNativeUPhoneModuleActivity != null) {
                return jDReactNativeUPhoneModuleActivity;
            }
            return null;
        }
    }

    public static synchronized void hideUMActivity() {
        synchronized (ReactActivityUtils.class) {
            JDReactHelper.newInstance().getCurrentMyActivity().moveTaskToBack(true);
        }
    }

    public static boolean mainProcessIsBackground() {
        Context applicationContext = JdSdk.getInstance().getApplication().getApplicationContext();
        ActivityManager activityManager = (ActivityManager) applicationContext.getSystemService("activity");
        Iterator<ActivityManager.RunningAppProcessInfo> it = BaseInfo.getRunningAppProcesses(applicationContext).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ActivityManager.RunningAppProcessInfo next = it.next();
            if (next.processName.equals(applicationContext.getPackageName())) {
                applicationContext.getPackageName();
                String str = "\u6b64appimportace =" + next.importance + ",context.getClass().getName()=" + applicationContext.getClass().getName();
                if (next.importance != 100) {
                    applicationContext.getPackageName();
                    String str2 = "\u5904\u4e8e\u540e\u53f0" + next.processName;
                    return true;
                }
                applicationContext.getPackageName();
                String str3 = "\u5904\u4e8e\u524d\u53f0" + next.processName;
            }
        }
        return false;
    }

    private static boolean needLogin(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        Object obj = bundle.get(VKEventUtil.JUMP_NEEDLOGIN);
        if (obj instanceof String) {
            return DYConstants.DY_TRUE.equals(obj);
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return false;
    }

    public static synchronized void sendPhoneChargeCoupon(String str, HashMap hashMap) {
        synchronized (ReactActivityUtils.class) {
            if (JDReactNativeGamePropBuyActivity.getmReactInstanceManager() != null) {
                CommonUtil.send(JDReactNativeGamePropBuyActivity.getmReactInstanceManager().getCurrentReactContext(), str, hashMap);
            }
        }
    }

    public static synchronized void showDReactUPhoneModuleActivity() {
        synchronized (ReactActivityUtils.class) {
            if (Build.VERSION.SDK_INT >= 11) {
                if (!JDReactNativeUPhoneModuleActivity.isExist) {
                    startJDReactUPhoneModuleActivity();
                    return;
                }
                BaseActivity a = e.b().a();
                ActivityManager activityManager = (ActivityManager) a.getSystemService("activity");
                List<ActivityManager.RunningTaskInfo> runningTasks = BaseInfo.getRunningTasks(a);
                boolean z = false;
                int i2 = 0;
                while (true) {
                    if (i2 >= runningTasks.size()) {
                        break;
                    } else if (runningTasks.get(i2).baseActivity.toShortString().indexOf("com.jingdong.common.jdreactFramework.activities.JDReactNativeUPhoneModuleActivity") > -1) {
                        activityManager.moveTaskToFront(runningTasks.get(i2).id, 1);
                        z = true;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (!z) {
                    startJDReactUPhoneModuleActivity();
                }
            }
        }
    }

    public static synchronized void showDReactUPhoneModuleActivityForRN() {
        synchronized (ReactActivityUtils.class) {
            if (Build.VERSION.SDK_INT >= 11) {
                JDReactNativeUPhoneModuleActivity jDReactNativeUPhoneModuleActivity = JDReactNativeUPhoneModuleActivity.instance;
                if (jDReactNativeUPhoneModuleActivity == null) {
                    startJDReactUPhoneModuleActivity();
                } else if (!bring2Front(jDReactNativeUPhoneModuleActivity)) {
                    startJDReactUPhoneModuleActivity();
                }
            }
        }
    }

    public static synchronized void starJDReactMovieHomeActivity(Context context, Intent intent) {
        synchronized (ReactActivityUtils.class) {
            CommonBridge.goToMWithUrlNotInFrame(e.b().a(), "http://m-movie.jd.com/va/index.html#!");
        }
    }

    public static synchronized void startFlutterActivity(Context context, Bundle bundle) {
        synchronized (ReactActivityUtils.class) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(f.f19954c, "com.jd.lib.jdflutter.JDFlutterMainActivity"));
            intent.putExtras(bundle);
            try {
                context.startActivity(intent);
            } catch (Exception unused) {
            }
        }
    }

    public static synchronized void startJDReactCardPwdBuyMain(Context context, Bundle bundle) {
        synchronized (ReactActivityUtils.class) {
            StringBuffer stringBuffer = new StringBuffer();
            if (bundle != null) {
                stringBuffer.append("\"skuId\":");
                stringBuffer.append("\"" + bundle.getString("skuId") + "\"");
            }
            Intent intent = new Intent();
            String str = "openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"jdreactcommon\",\"modulename\":\"JDReactCardPwdBuy\",\"appname\":\"JDReactCardPwdBuy\",\"title\":\"\u586b\u5199\u8ba2\u5355\",\"ishidden\":false,\"param\":{" + ((Object) stringBuffer) + "}}";
            OKLog.d("card_uri", str);
            intent.setData(Uri.parse(str));
            OpenAppJumpController.dispatchJumpRequest(context, intent);
        }
    }

    public static synchronized void startJDReactCardPwdDetail(Context context, Bundle bundle) {
        synchronized (ReactActivityUtils.class) {
            StringBuffer stringBuffer = new StringBuffer();
            if (bundle != null) {
                stringBuffer.append("\"orderId\":");
                stringBuffer.append("\"" + bundle.getString("orderId") + "\"");
            }
            Intent intent = new Intent();
            String str = "openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"jdreactcommon\",\"modulename\":\"JDReactCardPwd\",\"appname\":\"JDReactCardPwd\",\"title\":\"\u8ba2\u5355\u8be6\u60c5\",\"ishidden\":false,\"param\":{" + ((Object) stringBuffer) + "}}";
            OKLog.d("card_uri", str);
            intent.setData(Uri.parse(str));
            OpenAppJumpController.dispatchJumpRequest(context, intent);
        }
    }

    public static synchronized void startJDReactCommonActivity(final Context context, final Bundle bundle) {
        synchronized (ReactActivityUtils.class) {
            if (bundle != null) {
                if (bundle.getBoolean("isJDFlutter", false)) {
                    if (needLogin(bundle)) {
                        JDReactHelper.newInstance().launchLogin(context, new JDReactHelper.JDReactLoginCallback() { // from class: com.jingdong.common.jdreactFramework.utils.ReactActivityUtils.1
                            @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactLoginCallback
                            public void onSuccess(String str) {
                                ReactActivityUtils.startFlutterActivity(context, bundle);
                            }
                        });
                    } else {
                        startFlutterActivity(context, bundle);
                    }
                    return;
                }
            }
            String string = bundle.getString(JDReactConstant.IntentConstant.MODULE_NAME);
            if (string != null && string.equals("JDReactUPhoneModule")) {
                Intent intent = new Intent();
                JDReactNativeUPhoneModuleActivity.setLastActivity(JDReactHelper.newInstance().getCurrentMyActivity());
                intent.setClass(context, JDReactNativeUPhoneModuleActivity.class);
                intent.putExtras(bundle);
                ReactActivityUtilsHelperBase.startClassPluginActivity(context, JDReactNativeUPhoneModuleActivity.class, intent, string);
            } else {
                Intent intent2 = new Intent();
                intent2.setClass(context, JDReactNativeCommonActivity.class);
                intent2.putExtras(bundle);
                ReactActivityUtilsHelperBase.startClassPluginActivity(context, JDReactNativeCommonActivity.class, intent2, bundle.getString(JDReactConstant.IntentConstant.MODULE_NAME));
            }
        }
    }

    public static synchronized void startJDReactFlightOrderDetailActivity(Context context, Intent intent) {
        synchronized (ReactActivityUtils.class) {
            if (intent == null) {
                intent = new Intent();
            }
            intent.setClass(context, JDReactNativeFlightOrderDetailActivity.class);
            ReactActivityUtilsHelperBase.startClassPluginActivity(context, JDReactNativeFlightOrderDetailActivity.class, intent, JDReactConstant.AVAILABILITY_FLIGHTORDERDETAIL);
        }
    }

    public static synchronized void startJDReactGuessBallActivity(Context context) {
        synchronized (ReactActivityUtils.class) {
            Intent intent = new Intent();
            intent.setData(Uri.parse("openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"jdreactcommon\",\"modulename\":\"JDReactGuessBall\",\"appname\":\"JDReactGuessBall\",\"ishidden\":true,\"param\":{}}"));
            OpenAppJumpController.dispatchJumpRequest(context, intent);
        }
    }

    public static synchronized void startJDReactJShopMineActivity(Context context, Intent intent) {
        synchronized (ReactActivityUtils.class) {
            if (intent == null) {
                intent = new Intent();
            }
            intent.setClass(context, JDReactNativeJShopMineActivity.class);
            ReactActivityUtilsHelperBase.startClassPluginActivity(context, JDReactNativeJShopMineActivity.class, intent, JDReactConstant.AVAILABILITY_JSHOP_MINE);
        }
    }

    public static synchronized void startJDReactLivingPayActivity(Context context, Bundle bundle) {
        synchronized (ReactActivityUtils.class) {
            StringBuffer stringBuffer = new StringBuffer();
            if (bundle != null) {
                stringBuffer.append("\"cityId\":");
                stringBuffer.append("\"" + bundle.getLong(JDWeatherActionKey.CITY_ID) + "\",");
                stringBuffer.append("\"cityName\":");
                stringBuffer.append("\"" + bundle.getString("cityName") + "\",");
                stringBuffer.append("\"itemId\":");
                stringBuffer.append("\"" + bundle.getLong("itemId") + "\",");
                stringBuffer.append("\"itemName\":");
                stringBuffer.append("\"" + bundle.getString("itemName") + "\",");
                stringBuffer.append("\"imgUrl\":");
                stringBuffer.append("\"" + bundle.getString("imgUrl") + "\",");
                stringBuffer.append("\"unitId\":");
                stringBuffer.append("\"" + bundle.getLong("unitId") + "\",");
                stringBuffer.append("\"fullName\":");
                stringBuffer.append("\"" + bundle.getString("fullName") + "\",");
                stringBuffer.append("\"billKey\":");
                stringBuffer.append("\"" + bundle.getString("billKey") + "\",");
                stringBuffer.append("\"phoneNumber\":");
                stringBuffer.append("\"" + bundle.getString("phoneNumber") + "\"");
            }
            Intent intent = new Intent();
            String str = "openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"jdreactcommon\",\"modulename\":\"JDReactLivingPay\",\"appname\":\"JDReactLivingPay\",\"ishidden\":true,\"param\":{" + stringBuffer.toString() + "}}";
            OKLog.d("living_pay_uri", str);
            intent.setData(Uri.parse(str));
            OpenAppJumpController.dispatchJumpRequest(context, intent);
        }
    }

    public static synchronized void startJDReactLivingPayOrderDetailActivity(Context context, Bundle bundle) {
        synchronized (ReactActivityUtils.class) {
            StringBuffer stringBuffer = new StringBuffer();
            if (bundle != null) {
                stringBuffer.append("\"orderId\":");
                stringBuffer.append("\"" + bundle.getLong("orderId") + "\"");
            }
            Intent intent = new Intent();
            String str = "openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"jdreactcommon\",\"modulename\":\"JDReactLivingPayOrderDetail\",\"appname\":\"JDReactLivingPayOrderDetail\",\"title\":\"\u8ba2\u5355\u8be6\u60c5\",\"ishidden\":false,\"param\":{" + stringBuffer.toString() + "}}";
            OKLog.d("living_pay_uri", str);
            intent.setData(Uri.parse(str));
            OpenAppJumpController.dispatchJumpRequest(context, intent);
        }
    }

    public static synchronized void startJDReactLivingPayRecordsActivity(Context context, Bundle bundle) {
        synchronized (ReactActivityUtils.class) {
            Intent intent = new Intent();
            OKLog.d("living_pay_uri", "openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"jdreactcommon\",\"modulename\":\"JDReactLivingPayRecords\",\"appname\":\"JDReactLivingPayRecords\",\"title\":\"\u7f34\u8d39\u8bb0\u5f55\",\"ishidden\":false,\"param\":{}}");
            intent.setData(Uri.parse("openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"jdreactcommon\",\"modulename\":\"JDReactLivingPayRecords\",\"appname\":\"JDReactLivingPayRecords\",\"title\":\"\u7f34\u8d39\u8bb0\u5f55\",\"ishidden\":false,\"param\":{}}"));
            OpenAppJumpController.dispatchJumpRequest(context, intent);
        }
    }

    public static synchronized void startJDReactNativeAPIDemosActivity(Context context) {
        synchronized (ReactActivityUtils.class) {
            Intent intent = new Intent();
            intent.setData(Uri.parse("openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"jdreactcommon\",\"modulename\":\"JDReactAPIDemos\",\"appname\":\"JDReactAPIDemos\",\"ishidden\":true,\"param\":{}}"));
            OpenAppJumpController.dispatchJumpRequest(context, intent);
        }
    }

    public static synchronized void startJDReactNativeFaxianActivity(Context context, String str) {
        synchronized (ReactActivityUtils.class) {
            if (TextUtils.isEmpty(str)) {
                ToastUtils.shortToast("\u4e1a\u52a1\u4e0d\u5b58\u5728");
                return;
            }
            Intent intent = new Intent();
            intent.setData(Uri.parse("openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"jdreactcommon\",\"modulename\":\"JDReactFaxian\",\"appname\":\"JDReactFaxian\",\"ishidden\":true,\"param\":{" + str + "}}"));
            OpenAppJumpController.dispatchJumpRequest(context, intent);
        }
    }

    public static synchronized void startJDReactNativeGamePropBuyActivity(Context context, Intent intent) {
        synchronized (ReactActivityUtils.class) {
            if (intent == null) {
                intent = new Intent();
            }
            intent.setClass(context, JDReactNativeGamePropBuyActivity.class);
            ReactActivityUtilsHelperBase.startClassPluginActivity(context, JDReactNativeGamePropBuyActivity.class, intent, JDReactConstant.AVAILABILITY_GAME_PROP);
        }
    }

    public static synchronized void startJDReactNewProductActivity(Context context, Intent intent) {
        synchronized (ReactActivityUtils.class) {
            if (intent == null) {
                intent = new Intent();
            }
            StringBuffer stringBuffer = new StringBuffer();
            Bundle extras = intent.getExtras();
            if (extras != null) {
                stringBuffer.append("\"cityId\":");
                stringBuffer.append("\"" + extras.getLong(JDWeatherActionKey.CITY_ID) + "\",");
                stringBuffer.append("\"cityName\":");
                stringBuffer.append("\"" + extras.getString("cityName") + "\"");
            }
            intent.setData(Uri.parse("openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"jdreactcommon\",\"modulename\":\"JDReactNewProduct\",\"appname\":\"JDReactNewProduct\",\"ishidden\":true,\"param\":{" + stringBuffer.toString() + "}}"));
            OpenAppJumpController.dispatchJumpRequest(context, intent);
        }
    }

    public static synchronized void startJDReactOneTreasureActivity(Context context, Intent intent) {
        synchronized (ReactActivityUtils.class) {
            intent.setData(Uri.parse("openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"jdreactcommon\",\"modulename\":\"JDReactOneTreasure\",\"appname\":\"JDReactOneTreasure\",\"ishidden\":true,\"param\":{}}"));
            OpenAppJumpController.dispatchJumpRequest(context, intent);
        }
    }

    public static synchronized void startJDReactPaySuccessActivity(Context context, Intent intent) {
        synchronized (ReactActivityUtils.class) {
            if (intent == null) {
                intent = new Intent();
            }
            intent.setClass(context, JDReactNativePaySucessActivity.class);
            ReactActivityUtilsHelperBase.startClassPluginActivity(context, JDReactNativePaySucessActivity.class, intent, JDReactConstant.AVAILABILITY_PAYSUCCESS);
        }
    }

    public static synchronized void startJDReactRankListActivity(Context context, Bundle bundle) {
        synchronized (ReactActivityUtils.class) {
            Intent intent = new Intent();
            intent.putExtra("openAppParam", bundle);
            intent.setClass(context, JDReactNativeSignRankingListActivity.class);
            ReactActivityUtilsHelperBase.startClassPluginActivity(context, JDReactNativeSignRankingListActivity.class, intent, JDReactConstant.AVAILABILITY_SIGNRANK);
        }
    }

    public static synchronized void startJDReactUPhoneModuleActivity() {
        synchronized (ReactActivityUtils.class) {
            Intent intent = new Intent();
            intent.setData(Uri.parse("openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"jdreactcommon\",\"modulename\":\"JDReactUPhoneModule\",\"appname\":\"JDReactUPhoneModule\",\"ishidden\":true,\"param\":{}}"));
            OpenAppJumpController.dispatchJumpRequest(e.b().a(), intent);
        }
    }

    public static synchronized void startJDReactVersionActivity(Context context) {
        synchronized (ReactActivityUtils.class) {
            Intent intent = new Intent();
            intent.setClass(context, JDReactDebugTabActivity.class);
            context.startActivity(intent);
        }
    }

    public static void startOrShowActivityForPackage() {
        BaseActivity a = e.b().a();
        if (a == null) {
            return;
        }
        a.startActivity(a.getPackageManager().getLaunchIntentForPackage(a.getPackageName()));
    }

    public static synchronized void startReactPage(Context context, String str) {
        JSONObjectProxy jSONObjectProxy;
        synchronized (ReactActivityUtils.class) {
            try {
                jSONObjectProxy = new JSONObjectProxy(new JSONObject(str));
            } catch (JSONException e2) {
                e2.printStackTrace();
                jSONObjectProxy = null;
            }
            if (jSONObjectProxy != null) {
                startJDReactCommonActivity(context, JumpUtil.getBundleFromJson(jSONObjectProxy));
            }
        }
    }

    public static synchronized void startUMGuardActivity() {
        synchronized (ReactActivityUtils.class) {
            Intent intent = new Intent();
            intent.putExtra("isFormUPhoneModule", true);
            BaseActivity a = e.b().a();
            intent.setClass(a, UPhoneModuleTransparentActivity.class);
            UPhoneModuleTransparentActivity.initTime();
            a.startActivity(intent);
        }
    }

    public static synchronized void startJDReactJShopMineActivity(Context context) {
        synchronized (ReactActivityUtils.class) {
            startJDReactJShopMineActivity(context, new Intent());
        }
    }
}
