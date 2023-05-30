package com.jingdong.common.messagepop;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.View;
import android.view.Window;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.mylib.R;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.messagecenter.MessageBasicConfigUtils;
import com.jingdong.common.messagecenter.MessageCommonUtils;
import com.jingdong.common.messagecenter.NotificationSettingUtils;
import com.jingdong.common.messagepop.JDMessageNoticeManager;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.CallBackWithReturnListener;
import com.jingdong.common.unification.router.builder.RouterEntry;
import com.jingdong.common.utils.MSGWithDDUtil;
import com.jingdong.common.web.IRouterParams;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.utils.PackageInfoUtil;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JDMessageNoticeManager {
    protected static final String BIZ_TYPE_HOME = "10000";
    public static final int CHANNEL_COUPON = 5;
    public static final int CHANNEL_GET_JING_DOU = 6;
    public static final int CHANNEL_HOME_PAGE = 2;
    public static final int CHANNEL_MESSAGE_CENTER = 1;
    public static final int CHANNEL_ORDER_TRACE = 4;
    public static final int CHANNEL_SHOP_CART = 3;
    private static final String PAGE_MAIN_BOTTOM_TAB_CLASS_NAME = "com.jingdong.app.mall.navigationbar.JDNavigationFragment";
    private static final String PAGE_MAIN_CLASS_NAME = "com.jingdong.app.mall.MainFrameActivity";
    private static JDMessageNoticeManager instance;
    private String lastPageName;
    private JDMessageNoticeGuideDialog mGuideDialog;
    private PushOpenListener mListener;
    private Map<String, String> pageMap = new ConcurrentHashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.common.messagepop.JDMessageNoticeManager$4  reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass4 implements HttpGroup.OnCommonListener {
        final /* synthetic */ JDMessageNoticeCallback val$callback;
        final /* synthetic */ JDMessageNoticeModel val$model;
        final /* synthetic */ WeakReference val$weakActivity;

        AnonymousClass4(JDMessageNoticeCallback jDMessageNoticeCallback, JDMessageNoticeModel jDMessageNoticeModel, WeakReference weakReference) {
            this.val$callback = jDMessageNoticeCallback;
            this.val$model = jDMessageNoticeModel;
            this.val$weakActivity = weakReference;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b(JDMessageNoticeCallback jDMessageNoticeCallback, JDMessageNoticeModel jDMessageNoticeModel) {
            try {
                if (JDMessageNoticeManager.this.mGuideDialog != null && !JDMessageNoticeManager.this.mGuideDialog.isShown()) {
                    WeakReference activity = JDMessageNoticeManager.this.getActivity();
                    if (!JDMessageNoticeManager.this.isActive(activity)) {
                        JDMessageNoticeManager.this.notifyNoticeCallback(jDMessageNoticeCallback, false);
                        return;
                    }
                    Activity activity2 = (Activity) activity.get();
                    String currentFragmentName = JDMessageNoticeManager.this.isInMainPage(activity2) ? JDMessageNoticeManager.this.getCurrentFragmentName(activity2) : "";
                    if (TextUtils.isEmpty(currentFragmentName)) {
                        currentFragmentName = activity2.getClass().getName();
                    }
                    String str = (String) JDMessageNoticeManager.this.pageMap.get(currentFragmentName);
                    if (!currentFragmentName.equals(JDMessageNoticeManager.this.lastPageName) || TextUtils.isEmpty(str)) {
                        return;
                    }
                    if (str.equals(jDMessageNoticeModel.bizId + CartConstant.KEY_YB_INFO_LINK + jDMessageNoticeModel.pageId)) {
                        JDMessageNoticeManager.this.mGuideDialog.show(activity2, JDMessageNoticeManager.this.lastPageName, jDMessageNoticeModel, jDMessageNoticeCallback);
                        JDMessageNoticeManager.this.pageMap.remove(currentFragmentName);
                        return;
                    }
                    return;
                }
                JDMessageNoticeManager.this.notifyNoticeCallback(jDMessageNoticeCallback, false);
            } catch (Exception unused) {
                JDMessageNoticeManager.this.notifyNoticeCallback(jDMessageNoticeCallback, false);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            if (httpResponse.getCode() != 0 || fastJsonObject == null || !fastJsonObject.containsKey("pushStyleId") || TextUtils.isEmpty(fastJsonObject.optString("pushStyleId")) || DYConstants.DY_NULL_STR.equalsIgnoreCase(fastJsonObject.optString("pushStyleId"))) {
                JDMessageNoticeManager.this.notifyNoticeCallback(this.val$callback, false);
                return;
            }
            this.val$model.update(fastJsonObject);
            if (!this.val$model.isCanShow || !JDMessageNoticeManager.this.isActive(this.val$weakActivity)) {
                JDMessageNoticeManager.this.notifyNoticeCallback(this.val$callback, false);
                return;
            }
            if (JDMessageNoticeManager.this.mGuideDialog == null) {
                JDMessageNoticeManager.this.mGuideDialog = new JDMessageNoticeGuideDialog();
            }
            final JDMessageNoticeCallback jDMessageNoticeCallback = this.val$callback;
            final JDMessageNoticeModel jDMessageNoticeModel = this.val$model;
            ((Activity) this.val$weakActivity.get()).runOnUiThread(new Runnable() { // from class: com.jingdong.common.messagepop.f
                @Override // java.lang.Runnable
                public final void run() {
                    JDMessageNoticeManager.AnonymousClass4.this.b(jDMessageNoticeCallback, jDMessageNoticeModel);
                }
            });
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void b(RouterEntry routerEntry, boolean z) {
        CallBackListener callBackListener;
        if (routerEntry == null || (callBackListener = routerEntry.callBackListener) == null || !(callBackListener instanceof CallBackWithReturnListener)) {
            return;
        }
        ((CallBackWithReturnListener) callBackListener).onComplete(showPushOpenGuideResult(z).toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WeakReference<Activity> getActivity() {
        Map map;
        try {
            if (BaseFrameUtil.getInstance().getCurrentMyActivity() != null && BaseFrameUtil.getInstance().getCurrentMyActivity().getThisActivity() != null) {
                Activity thisActivity = BaseFrameUtil.getInstance().getCurrentMyActivity().getThisActivity();
                WeakReference<Activity> weakReference = new WeakReference<>(thisActivity);
                if (thisActivity != null) {
                    return weakReference;
                }
            }
        } catch (Exception unused) {
        }
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Object invoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mActivities");
            declaredField.setAccessible(true);
            if (Build.VERSION.SDK_INT < 19) {
                map = (HashMap) declaredField.get(invoke);
            } else {
                map = (ArrayMap) declaredField.get(invoke);
            }
            for (Object obj : map.values()) {
                Class<?> cls2 = obj.getClass();
                Field declaredField2 = cls2.getDeclaredField("paused");
                declaredField2.setAccessible(true);
                if (!declaredField2.getBoolean(obj)) {
                    Field declaredField3 = cls2.getDeclaredField("activity");
                    declaredField3.setAccessible(true);
                    return new WeakReference<>((Activity) declaredField3.get(obj));
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCurrentFragmentName(Activity activity) {
        List<Fragment> fragments;
        if (activity != null && (fragments = ((FragmentActivity) activity).getSupportFragmentManager().getFragments()) != null && !fragments.isEmpty() && fragments.size() == 2) {
            Iterator<Fragment> it = fragments.iterator();
            while (it.hasNext()) {
                String name = it.next().getClass().getName();
                if (!isMainBottomTab(name)) {
                    return name;
                }
            }
        }
        return "";
    }

    public static JDMessageNoticeManager getInstance() {
        if (instance == null) {
            synchronized (JDMessageNoticeManager.class) {
                if (instance == null) {
                    instance = new JDMessageNoticeManager();
                }
            }
        }
        return instance;
    }

    private List<MessageNoticeModel> getMessageNotice(String str) {
        if (str == null) {
            return null;
        }
        return JDJSON.parseArray(str, MessageNoticeModel.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean getNotificationStatus() {
        return NotificationManagerCompat.from(JdSdk.getInstance().getApplication()).areNotificationsEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isActive(WeakReference<Activity> weakReference) {
        if (weakReference == null || weakReference.get() == null) {
            return false;
        }
        return !weakReference.get().isFinishing();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isInMainPage(Activity activity) {
        return "com.jingdong.app.mall.MainFrameActivity".equals(activity.getClass().getName());
    }

    private boolean isMainBottomTab(String str) {
        return PAGE_MAIN_BOTTOM_TAB_CLASS_NAME.equals(str);
    }

    private boolean isShowNotificationReminder() {
        try {
            if (!MSGWithDDUtil.getPopMainSwitch() || MSGWithDDUtil.getMsgVersion().equals(PackageInfoUtil.getVersionName(JdSdk.getInstance().getApplication()))) {
                return false;
            }
            return !NotificationManagerCompat.from(JdSdk.getInstance().getApplication()).areNotificationsEnabled();
        } catch (Error | Exception unused) {
            return false;
        }
    }

    public static void jumpToAppInfoSetting(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        NotificationSettingUtils.jumpToAppInfoSetting(context);
    }

    public static void jumpToNotificationSetting(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        NotificationSettingUtils.jumpToNotificationSetting(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyNoticeCallback(final JDMessageNoticeCallback jDMessageNoticeCallback, final boolean z) {
        if (jDMessageNoticeCallback != null) {
            try {
                if (Looper.getMainLooper() == Looper.myLooper()) {
                    jDMessageNoticeCallback.onJDMessageNotice(z);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        if (jDMessageNoticeCallback != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jingdong.common.messagepop.JDMessageNoticeManager.5
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        jDMessageNoticeCallback.onJDMessageNotice(z);
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
            });
        }
    }

    private void requestToGetConfigInfo(String str, String str2, HttpGroup.OnCommonListener onCommonListener) {
        HttpSetting setting = MessageHttpUtils.getSetting();
        setting.setFunctionId("pushGuideService");
        setting.putJsonParam("bizId", str);
        setting.putJsonParam("pageId", str2);
        setting.setListener(onCommonListener);
        HttpGroupUtils.getHttpGroupaAsynPool().add(setting);
    }

    private void showNotificationReminder(final BaseActivity baseActivity, final int i2) {
        final AlertDialog create = new AlertDialog.Builder(baseActivity).create();
        Window window = create.getWindow();
        if (window != null) {
            create.show();
            create.setCancelable(true);
            create.setCanceledOnTouchOutside(true);
            window.setContentView(R.layout.message_notice_dialog);
            window.findViewById(R.id.message_notice_close).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.messagepop.JDMessageNoticeManager.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    create.dismiss();
                    JDMtaUtils.onClick(baseActivity, "App_MessageSetupPopup", "\u5f15\u5bfc\u6253\u5f00\u63a8\u9001\u5f00\u5173\u7ec4\u4ef6", i2 + "");
                }
            });
            window.findViewById(R.id.message_notice_sure).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.messagepop.JDMessageNoticeManager.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    JDMessageNoticeManager.this.openNoticeSettings(baseActivity);
                    create.dismiss();
                    JDMtaUtils.onClick(baseActivity, "App_MessagePopuptoSetup", "\u5f15\u5bfc\u6253\u5f00\u63a8\u9001\u5f00\u5173\u7ec4\u4ef6", i2 + "");
                }
            });
            new Handler().postDelayed(new Runnable() { // from class: com.jingdong.common.messagepop.JDMessageNoticeManager.3
                @Override // java.lang.Runnable
                public void run() {
                    BaseActivity baseActivity2 = baseActivity;
                    if (baseActivity2 == null || baseActivity2.isFinishing()) {
                        return;
                    }
                    try {
                        create.dismiss();
                    } catch (Exception unused) {
                    }
                }
            }, Final.FIVE_SECOND);
        }
    }

    public static void showPushOpenGuide(final IRouterParams iRouterParams) {
        if (iRouterParams == null) {
            return;
        }
        if (iRouterParams.getContext() == null) {
            iRouterParams.onCallBack(showPushOpenGuideResult(false));
            return;
        }
        try {
            if (!TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                getInstance().showPushOpenGuide(new JSONObject(iRouterParams.getRouterParam()).optString("scenesId"), new JDMessageNoticeCallback() { // from class: com.jingdong.common.messagepop.h
                    @Override // com.jingdong.common.messagepop.JDMessageNoticeCallback
                    public final void onJDMessageNotice(boolean z) {
                        IRouterParams.this.onCallBack(JDMessageNoticeManager.showPushOpenGuideResult(z));
                    }
                });
            } else {
                iRouterParams.onCallBack(showPushOpenGuideResult(false));
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            iRouterParams.onCallBack(showPushOpenGuideResult(false));
        }
    }

    private static JSONObject showPushOpenGuideResult(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("result", z);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PushOpenListener getListener() {
        return this.mListener;
    }

    public boolean isNotificationEnable() {
        return getNotificationStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void openNoticeSettings(Context context) {
        if (context == null || MessageCommonUtils.isFastClick()) {
            return;
        }
        NotificationSettingUtils.jumpToNotificationSetting(context);
    }

    public void pushNotificationReminder(BaseActivity baseActivity, int i2) {
        List<MessageNoticeModel> messageNotice;
        if (!isShowNotificationReminder() || (messageNotice = getMessageNotice(MSGWithDDUtil.getPopSwitchs())) == null) {
            return;
        }
        for (int i3 = 0; i3 < messageNotice.size(); i3++) {
            if (i2 == messageNotice.get(i3).channelId && messageNotice.get(i3).isOn) {
                MSGWithDDUtil.putMsgVersion(PackageInfoUtil.getVersionName(JdSdk.getInstance().getApplication()));
                showNotificationReminder(baseActivity, i2);
            }
        }
    }

    public boolean isNotificationEnable(PushOpenListener pushOpenListener) {
        this.mListener = pushOpenListener;
        return getNotificationStatus();
    }

    public static boolean isNotificationEnable(IRouterParams iRouterParams) {
        return getNotificationStatus();
    }

    public static void isNotificationEnable(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (routerEntry == null || (callBackListener = routerEntry.callBackListener) == null || !(callBackListener instanceof CallBackWithReturnListener)) {
            return;
        }
        ((CallBackWithReturnListener) callBackListener).onComplete(Boolean.valueOf(getNotificationStatus()));
    }

    public void showPushOpenGuide(String str) {
        showPushOpenGuide(str, null);
    }

    public void showPushOpenGuide(String str, JDMessageNoticeCallback jDMessageNoticeCallback) {
        try {
            if (!getNotificationStatus() && LoginUserBase.hasLogin() && !MessageBasicConfigUtils.isDegradeForX(MessageBasicConfigUtils.PUSH_GUIDE_SWITCH)) {
                if (str.contains("10000") && MSGWithDDUtil.isHomePushGuiDialogShowed()) {
                    notifyNoticeCallback(jDMessageNoticeCallback, false);
                    return;
                }
                int indexOf = str.indexOf(CartConstant.KEY_YB_INFO_LINK);
                if (!TextUtils.isEmpty(str) && indexOf > 0) {
                    JDMessageNoticeModel jDMessageNoticeModel = new JDMessageNoticeModel();
                    String substring = str.substring(0, indexOf);
                    String substring2 = str.substring(indexOf + 1);
                    if (!TextUtils.isEmpty(substring) && !TextUtils.isEmpty(substring2) && substring.length() == 5) {
                        WeakReference<Activity> activity = getActivity();
                        if (!isActive(activity)) {
                            notifyNoticeCallback(jDMessageNoticeCallback, false);
                            return;
                        }
                        Activity activity2 = activity.get();
                        this.lastPageName = "";
                        if (isInMainPage(activity2)) {
                            this.lastPageName = getCurrentFragmentName(activity2);
                        }
                        String name = TextUtils.isEmpty(this.lastPageName) ? activity2.getClass().getName() : this.lastPageName;
                        this.lastPageName = name;
                        this.pageMap.put(name, substring + CartConstant.KEY_YB_INFO_LINK + substring2);
                        requestToGetConfigInfo(substring, substring2, new AnonymousClass4(jDMessageNoticeCallback, jDMessageNoticeModel, activity));
                        return;
                    }
                    notifyNoticeCallback(jDMessageNoticeCallback, false);
                    return;
                }
                notifyNoticeCallback(jDMessageNoticeCallback, false);
                return;
            }
            notifyNoticeCallback(jDMessageNoticeCallback, false);
        } catch (Exception unused) {
            notifyNoticeCallback(jDMessageNoticeCallback, false);
        }
    }

    public static void showPushOpenGuide(Context context, JDJSONObject jDJSONObject, final RouterEntry routerEntry) {
        getInstance().showPushOpenGuide(jDJSONObject != null ? jDJSONObject.optString("scenesId") : "", new JDMessageNoticeCallback() { // from class: com.jingdong.common.messagepop.g
            @Override // com.jingdong.common.messagepop.JDMessageNoticeCallback
            public final void onJDMessageNotice(boolean z) {
                JDMessageNoticeManager.b(RouterEntry.this, z);
            }
        });
    }
}
