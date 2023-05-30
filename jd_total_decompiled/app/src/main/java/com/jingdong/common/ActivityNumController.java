package com.jingdong.common;

import android.app.Activity;
import android.text.TextUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JDSoftReference;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class ActivityNumController {
    private static final String CompleteOrderActivity = "com.jd.lib.settlement.completeorder.activity.CompleteOrderActivity";
    private static final String EvaluateSuccessActivity = "com.jd.lib.evaluatecenter.view.activity.EvaluateSuccessActivity";
    public static final String JDReactChannelCenterActivity = "com.jingdong.common.jdreactFramework.activities.JDReactChannelCenterActivity";
    public static final String JVideoImmersionActivity = "com.jd.lib.videoimmersion.view.activity.VideoImmersionActivity";
    public static final String JVideoImmersionStylbActivity = "com.jd.lib.videoimmersionstyleb.view.activity.VideoImmersionStyleBActivity";
    private static final String JshopMainShopActivity = "com.jingdong.common.sample.jshop.JshopMainShopActivity";
    private static final String MainFrameActivity = "com.jingdong.app.mall.MainFrameActivity";
    public static final String MainRightWebActivity = "com.jingdong.app.mall.home.activity.MainRightWebActivity";
    private static final String NewFillOrderActivity = "com.jd.lib.settlement.fillorder.activity.NewFillOrderActivity";
    private static final String ProductDetailActivity = "com.jingdong.app.mall.productdetail.ProductDetailActivity";
    private static final String ProductDetailActivity_plugin = "com.jd.lib.productdetail.ProductDetailActivity";
    private static final String ProductDetailFeedsActivity = "com.jd.lib.productdetailfeeds.PdFeedsActivity";
    private static final String ProductListActivity = "com.jingdong.app.mall.searchRefactor.view.Activity.ProductListActivity";
    private static final String TAG = "ActivityNumController";
    public static final String WebActivity = "com.jingdong.app.mall.WebActivity";
    private static final ArrayList<JDSoftReference<Activity>> allList = new ArrayList<>();
    private static final HashSet<String> notCtrlList = new HashSet<>();
    private static final HashMap<String, Integer> ctrlList = new HashMap<>();
    private static final HashMap<String, ArrayList<JDSoftReference<Activity>>> ctrlActivityLists = new HashMap<>();
    private static final HashMap<String, Integer> ctrlNum = new HashMap<>();
    public static int defaultAllNum = 10;
    public static boolean isUse = true;
    private static AtomicBoolean needReApplyConfig = new AtomicBoolean(false);
    private static int DEFAULT_PAGE_CTRL_NUM = 2;
    public static String KEY_ACT_STACK_INFO = "actStackInfo";

    static {
        initFilter(false);
    }

    public static void addList(JDSoftReference<Activity> jDSoftReference) {
        try {
            String str = (String) jDSoftReference.getTag();
            Log.e("sym", "addList className : " + str);
            if (str.equals(JVideoImmersionStylbActivity)) {
                str = JVideoImmersionActivity;
            }
            if (!TextUtils.isEmpty(str) && (str.equals("com.jd.lib.productdetail.ProductDetailActivity") || TextUtils.equals(str, ProductDetailFeedsActivity))) {
                jDSoftReference.setTag(ProductDetailActivity);
                str = ProductDetailActivity;
            }
            if (!isUse) {
                if (str.equals("com.jingdong.app.mall.MainFrameActivity")) {
                    return;
                }
                allList.add(jDSoftReference);
                return;
            }
            if (OKLog.D) {
                OKLog.d(TAG, defaultAllNum + "------allList------" + allList.size());
            }
            if (notCtrlList.contains(str)) {
                return;
            }
            HashMap<String, ArrayList<JDSoftReference<Activity>>> hashMap = ctrlActivityLists;
            ArrayList<JDSoftReference<Activity>> arrayList = hashMap.get(str);
            if (arrayList != null && arrayList.size() >= ctrlNum.getOrDefault(str, Integer.valueOf(DEFAULT_PAGE_CTRL_NUM)).intValue()) {
                JDSoftReference<Activity> remove = arrayList.remove(0);
                allList.remove(remove);
                remove.get().finish();
            }
            ArrayList<JDSoftReference<Activity>> arrayList2 = allList;
            if (arrayList2.size() >= defaultAllNum) {
                JDSoftReference<Activity> remove2 = arrayList2.remove(0);
                if (hashMap.get(remove2.getTag()) != null) {
                    hashMap.get(remove2.getTag()).remove(remove2);
                }
                remove2.get().finish();
            }
            if (arrayList != null) {
                arrayList.add(jDSoftReference);
                if (OKLog.D) {
                    OKLog.d(TAG, "------childCtrlList------" + arrayList.size());
                }
            }
            arrayList2.add(jDSoftReference);
            if (OKLog.D) {
                OKLog.d(TAG, "------add activity------" + jDSoftReference.get().getClass().getSimpleName());
            }
            printAllActivity();
        } catch (Throwable unused) {
        }
    }

    private static void ctlListConfig() {
        String config = JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, ABTestUtils.KEY_CONFIG_ACTIVITY_CONTROL, ABTestUtils.KEY_PARAM_CONTROL_LIST, "");
        if (TextUtils.isEmpty(config)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(config);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                ctrlList.put(next.trim(), Integer.valueOf(parseInt(jSONObject.getString(next), DEFAULT_PAGE_CTRL_NUM)));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void defaultConfig() {
        HashSet<String> hashSet = notCtrlList;
        hashSet.add("com.jingdong.app.mall.MainFrameActivity");
        hashSet.add(JDReactChannelCenterActivity);
        hashSet.add(MainRightWebActivity);
        HashMap<String, Integer> hashMap = ctrlList;
        hashMap.put(ProductDetailActivity, 3);
        hashMap.put(ProductListActivity, 3);
        hashMap.put(JshopMainShopActivity, 2);
        hashMap.put(JVideoImmersionActivity, 2);
    }

    public static void exitActivityWithoutTop() {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (true) {
            ArrayList<JDSoftReference<Activity>> arrayList2 = allList;
            if (i2 < arrayList2.size() - 1) {
                JDSoftReference<Activity> jDSoftReference = arrayList2.get(i2);
                if (jDSoftReference != null) {
                    Activity activity = null;
                    try {
                        activity = jDSoftReference.get();
                    } catch (Exception e2) {
                        OKLog.e(TAG, e2);
                    }
                    if (activity == null) {
                        return;
                    }
                    arrayList.add(jDSoftReference);
                    ArrayList<JDSoftReference<Activity>> arrayList3 = ctrlActivityLists.get(activity.getClass().getName());
                    if (arrayList3 != null) {
                        arrayList3.remove(jDSoftReference);
                    }
                    activity.finish();
                }
                i2++;
            } else {
                int size = arrayList.size();
                for (int i3 = 0; i3 < size; i3++) {
                    allList.remove(arrayList.get(i3));
                }
                return;
            }
        }
    }

    public static void exitAllActivityBeyond4ClassName(String str) {
        int i2 = 0;
        while (true) {
            ArrayList<JDSoftReference<Activity>> arrayList = allList;
            if (i2 >= arrayList.size()) {
                return;
            }
            JDSoftReference<Activity> jDSoftReference = arrayList.get(i2);
            if (jDSoftReference != null) {
                Activity activity = null;
                try {
                    activity = jDSoftReference.get();
                } catch (Exception e2) {
                    OKLog.e(TAG, e2);
                }
                if (activity == null) {
                    return;
                }
                if (TextUtils.isEmpty(str) || !activity.getClass().getSimpleName().equals(str)) {
                    if (OKLog.D) {
                        OKLog.d(TAG, "exitActivityNonByClassName() finish " + activity.getClass().getSimpleName());
                    }
                    ArrayList<JDSoftReference<Activity>> arrayList2 = ctrlActivityLists.get(activity.getClass().getName());
                    if (arrayList2 != null) {
                        arrayList2.remove(jDSoftReference);
                    }
                    activity.finish();
                    allList.remove(i2);
                    i2--;
                }
            }
            i2++;
        }
    }

    private static void forceConfig() {
        HashMap<String, Integer> hashMap = ctrlList;
        hashMap.put(NewFillOrderActivity, 1);
        hashMap.put(CompleteOrderActivity, 1);
        HashSet<String> hashSet = notCtrlList;
        if (!hashSet.contains("com.jingdong.app.mall.MainFrameActivity")) {
            hashSet.add("com.jingdong.app.mall.MainFrameActivity");
        }
        if (!hashSet.contains(JDReactChannelCenterActivity)) {
            hashSet.add(JDReactChannelCenterActivity);
        }
        if (hashSet.contains(MainRightWebActivity)) {
            return;
        }
        hashSet.add(MainRightWebActivity);
    }

    public static String getActStackStr() {
        try {
            StringBuilder sb = new StringBuilder();
            Iterator<JDSoftReference<Activity>> it = allList.iterator();
            while (it.hasNext()) {
                JDSoftReference<Activity> next = it.next();
                if (next != null) {
                    sb.append(next.getTag());
                    sb.append(">");
                }
            }
            int length = sb.length();
            if (length > 0) {
                sb.setLength(length - 1);
            }
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    private static void initConfig() {
        String config = JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, ABTestUtils.KEY_CONFIG_ACTIVITY_CONTROL, ABTestUtils.KEY_PARAM_MAX_NUM, String.valueOf(defaultAllNum));
        printLog("maxStackNumTemp -->" + config);
        int parseInt = parseInt(config, 0);
        if (parseInt == 0) {
            defaultConfig();
            return;
        }
        defaultAllNum = parseInt;
        noCtrlListConfig();
        ctlListConfig();
    }

    private static void initFilter(boolean z) {
        try {
            String config = JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, ABTestUtils.KEY_CONFIG_ACTIVITY_CONTROL, "enable");
            boolean z2 = !TextUtils.isEmpty(config.trim());
            needReApplyConfig.set(!z2);
            isUse = z2 ? "1".equals(config) : true;
            if (OKLog.D) {
                OKLog.d(TAG, "initFilter lastState:" + config + "  isUse::" + isUse);
            }
            if (z) {
                notCtrlList.clear();
                ctrlList.clear();
                ctrlNum.clear();
                needReApplyConfig.set(false);
            }
            if (isUse) {
                initConfig();
            }
            forceConfig();
            for (Map.Entry<String, Integer> entry : ctrlList.entrySet()) {
                String key = entry.getKey();
                int intValue = entry.getValue().intValue();
                if (key != null && key.length() >= 3 && intValue > 0) {
                    ctrlActivityLists.put(key, new ArrayList<>());
                    ctrlNum.put(key, Integer.valueOf(intValue));
                    if (OKLog.D) {
                        OKLog.d(TAG, "init--->" + key + "---->" + intValue);
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    private static void noCtrlListConfig() {
        String config = JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, ABTestUtils.KEY_CONFIG_ACTIVITY_CONTROL, ABTestUtils.KEY_PARAM_NOT_CONTROL_LIST, "");
        if (TextUtils.isEmpty(config)) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(config);
            int length = jSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                notCtrlList.add(jSONArray.getString(i2).trim());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static int parseInt(String str, int i2) {
        try {
            return Integer.parseInt(str.trim());
        } catch (Exception unused) {
            return i2;
        }
    }

    private static void printAllActivity() {
        if (Log.D) {
            StringBuilder sb = new StringBuilder("allList --> ");
            Iterator<JDSoftReference<Activity>> it = allList.iterator();
            while (it.hasNext()) {
                JDSoftReference<Activity> next = it.next();
                if (next != null && next.get() != null) {
                    sb.append(next.get().getClass().getSimpleName());
                    sb.append(";");
                }
            }
            printLog(sb.toString());
        }
    }

    public static void printLog(String str) {
        if (Log.D) {
            Log.d(TAG, str);
        }
    }

    public static void remove(JDSoftReference<Activity> jDSoftReference) {
        String str;
        if (jDSoftReference == null) {
            return;
        }
        try {
            ArrayList<JDSoftReference<Activity>> arrayList = allList;
            if (arrayList != null) {
                arrayList.remove(jDSoftReference);
            }
            HashMap<String, ArrayList<JDSoftReference<Activity>>> hashMap = ctrlActivityLists;
            if (hashMap != null) {
                ArrayList<JDSoftReference<Activity>> arrayList2 = hashMap.get(jDSoftReference.getTag());
                if ((jDSoftReference.getTag() instanceof String) && (str = (String) jDSoftReference.getTag()) != null && str.equals(JVideoImmersionStylbActivity)) {
                    arrayList2 = hashMap.get(JVideoImmersionActivity);
                }
                if (arrayList2 != null) {
                    arrayList2.remove(jDSoftReference);
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static void removeActivity(int i2, String str) {
        JDSoftReference<Activity> jDSoftReference;
        ArrayList<JDSoftReference<Activity>> arrayList = allList;
        if (arrayList == null || arrayList.size() <= 1 || TextUtils.isEmpty(str)) {
            return;
        }
        Log.e("sym", "removeActivity : " + str);
        int size = arrayList.size() - i2;
        if (size == 0 || size > arrayList.size() - 1 || (jDSoftReference = arrayList.get(size)) == null || !str.equals(jDSoftReference.getTag())) {
            return;
        }
        Activity activity = null;
        try {
            activity = jDSoftReference.get();
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
        if (activity == null) {
            return;
        }
        ArrayList<JDSoftReference<Activity>> arrayList2 = ctrlActivityLists.get(activity.getClass().getName());
        if (arrayList2 != null) {
            arrayList2.remove(jDSoftReference);
        }
        activity.finish();
        allList.remove(jDSoftReference);
    }

    public static void removeOneActivityInStackTop(String str) {
        ArrayList<JDSoftReference<Activity>> arrayList = allList;
        if (arrayList == null || arrayList.size() <= 1 || TextUtils.isEmpty(str)) {
            return;
        }
        Log.e("sym", "removeActivity : " + str);
        for (int size = arrayList.size() + (-2); size > 0; size--) {
            JDSoftReference<Activity> jDSoftReference = allList.get(size);
            if (jDSoftReference != null && str.equals(jDSoftReference.getTag())) {
                Activity activity = null;
                try {
                    activity = jDSoftReference.get();
                } catch (Exception e2) {
                    OKLog.e(TAG, e2);
                }
                if (activity == null) {
                    return;
                }
                ArrayList<JDSoftReference<Activity>> arrayList2 = ctrlActivityLists.get(activity.getClass().getName());
                if (arrayList2 != null) {
                    arrayList2.remove(jDSoftReference);
                }
                activity.finish();
                allList.remove(jDSoftReference);
                return;
            }
        }
    }

    public static void removeStackSecond() {
        ArrayList<JDSoftReference<Activity>> arrayList = allList;
        int size = arrayList.size();
        if (size > 2) {
            try {
                Activity activity = arrayList.remove(size - 2).get();
                if (activity != null) {
                    activity.finish();
                }
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public static void updateConfig() {
        if (needReApplyConfig.compareAndSet(true, false)) {
            initFilter(true);
        }
    }

    public static void removeActivity(int i2, int i3) {
        ArrayList<JDSoftReference<Activity>> arrayList = allList;
        if (arrayList == null || arrayList.size() <= 0 || i3 == 0) {
            return;
        }
        int size = arrayList.size();
        ArrayList arrayList2 = new ArrayList();
        int i4 = size - i2;
        if (i4 < 0) {
            return;
        }
        if (i4 < i3) {
            i3 = i4;
        }
        for (int i5 = 0; i5 < i3; i5++) {
            JDSoftReference<Activity> jDSoftReference = allList.get((i4 - i5) - 1);
            if (jDSoftReference != null) {
                Activity activity = null;
                try {
                    activity = jDSoftReference.get();
                } catch (Exception e2) {
                    OKLog.e(TAG, e2);
                }
                if (activity == null) {
                    return;
                }
                arrayList2.add(jDSoftReference);
                ArrayList<JDSoftReference<Activity>> arrayList3 = ctrlActivityLists.get(activity.getClass().getName());
                if (arrayList3 != null) {
                    arrayList3.remove(jDSoftReference);
                }
                activity.finish();
            }
        }
        int size2 = arrayList2.size();
        for (int i6 = 0; i6 < size2; i6++) {
            allList.remove(arrayList2.get(i6));
        }
    }

    public static void removeActivity(String str) {
        ArrayList<JDSoftReference<Activity>> arrayList = allList;
        if (arrayList == null || arrayList.size() <= 0 || TextUtils.isEmpty(str)) {
            return;
        }
        Log.e("sym", "removeActivity : " + str);
        ArrayList<JDSoftReference<Activity>> arrayList2 = ctrlActivityLists.get(str);
        if (arrayList2 != null) {
            arrayList2.clear();
        }
        ArrayList arrayList3 = new ArrayList();
        Iterator<JDSoftReference<Activity>> it = arrayList.iterator();
        while (it.hasNext()) {
            JDSoftReference<Activity> next = it.next();
            if (next != null && str.equals(next.getTag())) {
                Activity activity = null;
                try {
                    activity = next.get();
                } catch (Exception e2) {
                    OKLog.e(TAG, e2);
                }
                if (activity == null) {
                    return;
                }
                arrayList3.add(next);
                activity.finish();
            }
        }
        Iterator it2 = arrayList3.iterator();
        while (it2.hasNext()) {
            allList.remove((JDSoftReference) it2.next());
        }
    }
}
