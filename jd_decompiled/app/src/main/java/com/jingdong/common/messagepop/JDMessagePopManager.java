package com.jingdong.common.messagepop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.ArrayMap;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.productdetail.core.utils.PdMtaUtil;
import com.jingdong.app.mall.mylib.R;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.database.table.MessageIdTable;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.messagecenter.JDDDMessageRouterUtil;
import com.jingdong.common.messagecenter.StationMessageUtils;
import com.jingdong.common.messagepop.template.PopViewTemplateFactory;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.permission.RomUtil;
import com.jingdong.common.utils.MSGWithDDUtil;
import com.jingdong.common.utils.MessageId;
import com.jingdong.common.widget.custom.contentutils.FragmentUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.bmode.util.JDBModeUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JDMessagePopManager {
    private static final long DEFAULT_MESSAGE_DISPLAY_TIME = 6000;
    private static final String DEFAULT_VALUE = "-100";
    private static final int MOVE_MIN_INTERVAL = 50;
    private static final String PACK_NAME = "com.jingdong.common.messagepop.JDMessagePopManager";
    private static final String PRODUCETAIL_NAME = "com.jd.lib.productdetail.ProductDetailActivity";
    private static final String STATION_BLACK_FLAG = "0";
    private static final String STATION_WHITE_FLAG = "1";
    private static final String TAG = "com.jingdong.common.messagepop.JDMessagePopManager";
    private static final int TEMPLATE_ID = 18;
    static final int V920_TMP_ID = 14;
    private static volatile JDMessagePopManager instance;
    private static Handler mHandler;
    private String activityClassName;
    private String eventParam = "none_none_none";
    private JSONObject jsonParam = new JSONObject();
    private final PopViewTemplateFactory mFactory = new PopViewTemplateFactory();
    private PopupWindow mPopwindow;
    private volatile ScheduledThreadPoolExecutor scheduled;
    private static ConcurrentHashMap<String, JDMessagePopListener> currentMessagePopListeners = new ConcurrentHashMap<>();
    private static boolean isShieldPop = false;

    /* JADX INFO: Access modifiers changed from: private */
    public void OnMessagePopClick(MessagePopModel messagePopModel) {
        String jSONObject;
        getEventParam(messagePopModel);
        try {
            JSONObject jSONObject2 = this.jsonParam;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(getDefaultIfEmpty(messagePopModel.channelTitle));
            stringBuffer.append(CartConstant.KEY_YB_INFO_LINK);
            stringBuffer.append(getDefaultIfEmpty(messagePopModel.title));
            stringBuffer.append(CartConstant.KEY_YB_INFO_LINK);
            stringBuffer.append(getDefaultIfEmpty(messagePopModel.alert));
            stringBuffer.append(CartConstant.KEY_YB_INFO_LINK);
            stringBuffer.append(getDefaultIfEmpty(messagePopModel.benefit));
            jSONObject2.put("text", stringBuffer.toString());
            this.jsonParam.put("requestid", getDefaultIfEmpty(messagePopModel.requestId));
            jSONObject = this.jsonParam.toString();
            this.jsonParam.remove("text");
            this.jsonParam.remove("requestid");
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = this.jsonParam.toString();
        }
        JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplicationContext(), "MessageCoupon_Check", this.eventParam, "", "Message_CouponDialog", PACK_NAME, "", "", jSONObject, null);
        new OpenAppJumpBuilder.Builder(Uri.parse(messagePopModel.landPageUrl)).build().jump(JdSdk.getInstance().getApplicationContext());
        cleanMessageRedDot(messagePopModel.msgId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(MessagePopModel messagePopModel, View view) {
        notifyMessageInfo(messagePopModel);
        nonUiDismissPopView();
    }

    public static void cancelIcsPopWindow(Context context) {
        JDDDMessageRouterUtil.cancelIcsPopWindow(context);
    }

    private boolean checkMessage(MessagePopModel messagePopModel) {
        if (messagePopModel == null) {
            return false;
        }
        try {
            if (currentMessagePopListeners.isEmpty()) {
                return false;
            }
            boolean z = false;
            for (Map.Entry<String, JDMessagePopListener> entry : currentMessagePopListeners.entrySet()) {
                boolean z2 = true;
                boolean z3 = entry.getValue() != null;
                if (entry.getKey() == null) {
                    z2 = false;
                }
                if ((z3 & z2) && entry.getKey().equals(messagePopModel.channelId)) {
                    z = entry.getValue().OnMessagePopShow(messagePopModel.transmission);
                }
            }
            return z;
        } catch (Exception unused) {
            return false;
        }
    }

    private boolean checkMsgRepeat(MessagePopModel messagePopModel) {
        try {
            return MessageIdTable.checkRepeated(new MessageId(messagePopModel.msgId));
        } catch (Exception unused) {
            return true;
        }
    }

    private boolean checkPopCondition(MessagePopModel messagePopModel, Context context) {
        int i2;
        if (context == null || !MSGWithDDUtil.getDDStationWindowKey() || messagePopModel == null || TextUtils.isEmpty(messagePopModel.alert) || TextUtils.isEmpty(messagePopModel.landPageUrl) || (i2 = messagePopModel.notifyTemplateId) == 0) {
            return false;
        }
        if (isProductTemplateId(i2)) {
            return true;
        }
        return checkWhiteAndBlackPage(messagePopModel) && checkTriggerPage(messagePopModel);
    }

    private boolean checkTriggerPage(MessagePopModel messagePopModel) {
        if (TextUtils.isEmpty(messagePopModel.allowPopModel)) {
            return true;
        }
        String currentMode = JDBModeUtils.getCurrentMode();
        if (TextUtils.isEmpty(currentMode)) {
            currentMode = "0";
        }
        return messagePopModel.allowPopModel.contains(currentMode);
    }

    private boolean checkWhiteAndBlackPage(MessagePopModel messagePopModel) {
        if (messagePopModel.whitePageList != null && !TextUtils.isEmpty(messagePopModel.whitePageFlag)) {
            boolean contains = Arrays.asList(messagePopModel.whitePageList).contains(StationMessageUtils.getPageId());
            String str = messagePopModel.whitePageFlag;
            str.hashCode();
            if (str.equals("0")) {
                return !contains;
            }
            if (str.equals("1")) {
                return contains;
            }
        }
        return true;
    }

    private void cleanMessageRedDot(String str) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("updateUnReadedCountV7012");
        httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.NEW_MSG_CENTER_HOST));
        httpSetting.setNotifyUser(false);
        httpSetting.putJsonParam("msgId", str);
        httpSetting.setPost(true);
        httpSetting.setEffect(0);
        httpSetting.setUseFastJsonParser(true);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    private void delayDismissPopView(long j2) {
        if (this.scheduled == null) {
            this.scheduled = new ScheduledThreadPoolExecutor(2);
        }
        this.scheduled.schedule(new Runnable() { // from class: com.jingdong.common.messagepop.JDMessagePopManager.2
            @Override // java.lang.Runnable
            public void run() {
                JDMessagePopManager.this.nonUiDismissPopView();
            }
        }, j2, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: dismissPopView  reason: merged with bridge method [inline-methods] */
    public void d() {
        if (this.scheduled != null) {
            this.scheduled.shutdownNow();
            this.scheduled = null;
        }
        try {
            PopupWindow popupWindow = this.mPopwindow;
            if (popupWindow != null) {
                popupWindow.dismiss();
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void e(Map.Entry entry, MessagePopModel messagePopModel) {
        if ((!(entry.getValue() != null) || !(entry.getKey() != null)) || !((String) entry.getKey()).equals(messagePopModel.channelId)) {
            return;
        }
        ((JDMessagePopListener) entry.getValue()).OnMessagePopClick(messagePopModel.transmission);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void f(View view) {
    }

    private WeakReference<Activity> getActivity() {
        Map map;
        try {
            if (BaseFrameUtil.getInstance() != null || BaseFrameUtil.getInstance().getCurrentMyActivity() != null || BaseFrameUtil.getInstance().getCurrentMyActivity().getThisActivity() != null) {
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

    private String getActivityClassName() {
        WeakReference<Activity> activity = getActivity();
        if (activity != null && activity.get() != null) {
            this.activityClassName = activity.get().getComponentName().getClassName();
        }
        return this.activityClassName;
    }

    private View.OnClickListener getBtnClickListener(final MessagePopModel messagePopModel) {
        return new View.OnClickListener() { // from class: com.jingdong.common.messagepop.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                JDMessagePopManager.this.b(messagePopModel, view);
            }
        };
    }

    private void getEventParam(MessagePopModel messagePopModel) {
        this.eventParam = (TextUtils.isEmpty(messagePopModel.wareId) ? "none" : messagePopModel.wareId) + CartConstant.KEY_YB_INFO_LINK + (TextUtils.isEmpty(messagePopModel.batchId) ? "none" : messagePopModel.batchId) + CartConstant.KEY_YB_INFO_LINK + (TextUtils.isEmpty(messagePopModel.msgId) ? "none" : messagePopModel.msgId);
    }

    public static JDMessagePopManager getInstance() {
        if (instance == null) {
            synchronized (JDMessagePopManager.class) {
                if (instance == null) {
                    instance = new JDMessagePopManager();
                }
            }
        }
        return instance;
    }

    private void getJsonJDMtaParam(MessagePopModel messagePopModel, String str) {
        try {
            this.jsonParam.put(PdMtaUtil.PARAM_KEY_SKUID, getDefaultIfEmpty(messagePopModel.wareId));
            this.jsonParam.put("batchid", getDefaultIfEmpty(messagePopModel.batchId));
            this.jsonParam.put("msgid", getDefaultIfEmpty(messagePopModel.msgId));
            this.jsonParam.put("taskid", getDefaultIfEmpty(messagePopModel.taskId));
            this.jsonParam.put("pagename", getDefaultIfEmpty(str));
            this.jsonParam.put("accounttype", getDefaultIfEmpty(messagePopModel.accountType));
            this.jsonParam.put("sourcebusiness", getDefaultIfEmpty(messagePopModel.sourceBusiness));
            this.jsonParam.put("testid", getDefaultIfEmpty(messagePopModel.testId));
            this.jsonParam.put("bcat", getDefaultIfEmpty(messagePopModel.bCat));
            this.jsonParam.put("templetid", messagePopModel.notifyTemplateId);
            this.jsonParam.put("ispull", messagePopModel.isPull ? "1" : "0");
            this.jsonParam.put("show_pageid", StationMessageUtils.getPageId());
            this.jsonParam.put("vctemplateid", getDefaultIfEmpty(messagePopModel.getTemplateId() + ""));
            this.jsonParam.put("visit_pageid", getDefaultIfEmpty(messagePopModel.visitPageId));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private String getTopFagmentName() {
        Fragment topFragment;
        Activity thisActivity = BaseFrameUtil.getInstance().getCurrentMyActivity().getThisActivity();
        this.activityClassName = thisActivity.getComponentName().getClassName();
        try {
            if ((thisActivity instanceof FragmentActivity) && (topFragment = FragmentUtil.getTopFragment(((FragmentActivity) thisActivity).getSupportFragmentManager())) != null) {
                this.activityClassName = topFragment.getClass().getSimpleName();
            }
        } catch (Exception unused) {
        }
        return this.activityClassName;
    }

    private void initPopwindow(View view) {
        if (view == null) {
            return;
        }
        this.mPopwindow = new PopupWindow(view, -1, -2, false);
        new ShapeDrawable(new RectShape()).getPaint().setColor(view.getResources().getColor(17170445));
        this.mPopwindow.setFocusable(false);
        this.mPopwindow.setOutsideTouchable(false);
        this.mPopwindow.setBackgroundDrawable(view.getResources().getDrawable(17170445));
        this.mPopwindow.setSoftInputMode(16);
        try {
            this.mPopwindow.setAnimationStyle(R.style.showPopAnimation_fordongdong);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private boolean isProductTemplateId(int i2) {
        return i2 == 3 || i2 == 5 || i2 == 14;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyMessageInfo(final MessagePopModel messagePopModel) {
        String jSONObject;
        getEventParam(messagePopModel);
        try {
            JSONObject jSONObject2 = this.jsonParam;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(getDefaultIfEmpty(messagePopModel.channelTitle));
            stringBuffer.append(CartConstant.KEY_YB_INFO_LINK);
            stringBuffer.append(getDefaultIfEmpty(messagePopModel.title));
            stringBuffer.append(CartConstant.KEY_YB_INFO_LINK);
            stringBuffer.append(getDefaultIfEmpty(messagePopModel.alert));
            stringBuffer.append(CartConstant.KEY_YB_INFO_LINK);
            stringBuffer.append(getDefaultIfEmpty(messagePopModel.benefit));
            jSONObject2.put("text", stringBuffer.toString());
            this.jsonParam.put("requestid", getDefaultIfEmpty(messagePopModel.requestId));
            jSONObject = this.jsonParam.toString();
            this.jsonParam.remove("text");
            this.jsonParam.remove("requestid");
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = this.jsonParam.toString();
        }
        JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplicationContext(), "MessageCoupon_Receive", this.eventParam, "", "Message_CouponDialog", PACK_NAME, "", "", jSONObject, null);
        if (!currentMessagePopListeners.isEmpty()) {
            for (final Map.Entry<String, JDMessagePopListener> entry : currentMessagePopListeners.entrySet()) {
                postDelayed(new Runnable() { // from class: com.jingdong.common.messagepop.k
                    @Override // java.lang.Runnable
                    public final void run() {
                        JDMessagePopManager.e(entry, messagePopModel);
                    }
                }, 0);
            }
        }
        cleanMessageRedDot(messagePopModel.msgId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: notifyWindow  reason: merged with bridge method [inline-methods] */
    public void h(MessagePopModel messagePopModel) {
        printLog("notifyWindow");
        cancelIcsPopWindow(JdSdk.getInstance().getApplicationContext());
        PopupWindow popupWindow = this.mPopwindow;
        if (popupWindow != null && popupWindow.isShowing()) {
            c();
        }
        try {
            setMessagePopPattern(messagePopModel);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void playVibrator() {
        if (!MSGWithDDUtil.getMsgShakeSwitch() || isCurrentInTimeScope(22, 0, 7, 0)) {
            return;
        }
        ((Vibrator) JdSdk.getInstance().getApplicationContext().getSystemService("vibrator")).vibrate(500L);
    }

    private static void postDelayed(Runnable runnable, int i2) {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        mHandler.postDelayed(runnable, i2);
    }

    private void setMessageArriveExposure() {
        JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), "MessageCoupon_DialogArrive", null, "Message_CouponDialog", this.activityClassName, null, this.jsonParam.toString(), null);
    }

    private void setMessagePopPattern(MessagePopModel messagePopModel) {
        getEventParam(messagePopModel);
        getJsonJDMtaParam(messagePopModel, getActivityClassName());
        setMessageArriveExposure();
        printLog("\u4e1a\u52a1channelId" + messagePopModel.channelId);
        showPopFromTemplateId(messagePopModel);
    }

    private void setPopViewOnTouchEvent(View view, final MessagePopModel messagePopModel) {
        if (view == null) {
            return;
        }
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.messagepop.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                JDMessagePopManager.f(view2);
            }
        });
        view.setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.messagepop.JDMessagePopManager.1
            float x1;
            float x2;
            float y1;
            float y2;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    this.x1 = motionEvent.getX();
                    this.y1 = motionEvent.getY();
                }
                if (motionEvent.getAction() == 1) {
                    this.x2 = motionEvent.getX();
                    float y = motionEvent.getY();
                    this.y2 = y;
                    if (this.y1 - y > 50.0f) {
                        JDMessagePopManager.this.nonUiDismissPopView();
                        JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplicationContext(), "MessageCoupon_SlideClose", null, "", "Message_CouponDialog", JDMessagePopManager.this.activityClassName, null, "", JDMessagePopManager.this.jsonParam.toString(), null);
                    }
                    if (Math.abs(this.y1 - this.y2) >= 50.0f || Math.abs(this.x1 - this.x2) >= 50.0f) {
                        return false;
                    }
                    MessagePopModel messagePopModel2 = messagePopModel;
                    if (messagePopModel2.notifyTemplateId == 14) {
                        JDMessagePopManager.this.notifyMessageInfo(messagePopModel2);
                    } else {
                        JDMessagePopManager.this.OnMessagePopClick(messagePopModel2);
                    }
                    JDMessagePopManager.this.nonUiDismissPopView();
                    return false;
                }
                return false;
            }
        });
    }

    private void showPopFromTemplateId(MessagePopModel messagePopModel) {
        int templateId = messagePopModel.getTemplateId();
        boolean checkMessage = isProductTemplateId(templateId) ? checkMessage(messagePopModel) : checkMessage();
        printLog("checkMessage" + checkMessage);
        if (checkMessage) {
            View initPopView = this.mFactory.getPopView(templateId).initPopView(JdSdk.getInstance().getApplicationContext(), messagePopModel, getBtnClickListener(messagePopModel));
            setPopViewOnTouchEvent(initPopView, messagePopModel);
            initPopwindow(initPopView);
            if (isProductTemplateId(templateId)) {
                showProductMessagePop(messagePopModel);
            } else {
                showMessagePop(messagePopModel);
            }
        }
    }

    private void showPopWindow(WeakReference<Activity> weakReference, MessagePopModel messagePopModel, int i2) {
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (checkMsgRepeat(messagePopModel)) {
            return;
        }
        if (messagePopModel.getTemplateId() != 18) {
            this.mPopwindow.showAtLocation(weakReference.get().findViewById(16908290), i2 | 48, 0, 0);
            if (!RomUtil.isOppo() || Build.VERSION.SDK_INT < 29) {
                playMessageVoice();
                playVibrator();
            }
        }
        MessagePullUtils.pullMessageCallBack(messagePopModel.msgId, messagePopModel.allowPopModel);
        String str = "";
        try {
            this.jsonParam.put("requestid", getDefaultIfEmpty(messagePopModel.requestId));
            str = this.jsonParam.toString();
            this.jsonParam.remove("requestid");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), "MessageCoupon_DialogExpo", null, "Message_CouponDialog", this.activityClassName, null, str, null);
    }

    private void showProductMessagePop(MessagePopModel messagePopModel) {
        printLog("showProductMessagePop_start");
        WeakReference<Activity> activity = getActivity();
        if (activity != null && activity.get() != null) {
            String className = activity.get().getComponentName().getClassName();
            this.activityClassName = className;
            getJsonJDMtaParam(messagePopModel, className);
            String str = "activityClassName:" + this.activityClassName;
            if ("com.jd.lib.productdetail.ProductDetailActivity".equals(this.activityClassName)) {
                showPopWindow(activity, messagePopModel, GravityCompat.START);
            }
        }
        long j2 = messagePopModel.displayTime;
        if (j2 == 0) {
            delayDismissPopView(DEFAULT_MESSAGE_DISPLAY_TIME);
        } else {
            delayDismissPopView(j2);
        }
        printLog("showProductMessagePop_end");
    }

    public void activePopView() {
        isShieldPop = false;
    }

    public boolean checkMessagePopShow() {
        PopupWindow popupWindow = this.mPopwindow;
        return popupWindow != null && popupWindow.isShowing();
    }

    public String getDefaultIfEmpty(String str) {
        return TextUtils.isEmpty(str) ? "-100" : str;
    }

    public boolean isCurrentInTimeScope(int i2, int i3, int i4, int i5) {
        long currentTimeMillis = System.currentTimeMillis();
        Time time = new Time();
        time.set(currentTimeMillis);
        Time time2 = new Time();
        time2.set(currentTimeMillis);
        time2.hour = i2;
        time2.minute = i3;
        Time time3 = new Time();
        time3.set(currentTimeMillis);
        time3.hour = i4;
        time3.minute = i5;
        boolean z = false;
        if (time2.before(time3)) {
            return false;
        }
        time2.set(time2.toMillis(true) - 86400000);
        if (!time.before(time2) && !time.after(time3)) {
            z = true;
        }
        Time time4 = new Time();
        time4.set(time2.toMillis(true) + 86400000);
        if (time.before(time4)) {
            return z;
        }
        return true;
    }

    public void nonUiDismissPopView() {
        postDelayed(new Runnable() { // from class: com.jingdong.common.messagepop.j
            @Override // java.lang.Runnable
            public final void run() {
                JDMessagePopManager.this.d();
            }
        }, 0);
    }

    public void playMessageVoice() {
        if (!MSGWithDDUtil.getMSGSOUND() || isCurrentInTimeScope(22, 0, 7, 0)) {
            return;
        }
        RingtoneManager.getRingtone(JdSdk.getInstance().getApplicationContext(), RingtoneManager.getDefaultUri(2)).play();
    }

    public void printLog(String str) {
        if (!OKLog.D || TextUtils.isEmpty(str)) {
            return;
        }
        OKLog.d(TAG, str);
    }

    public void setMessagePopClickListener(String str, JDMessagePopListener jDMessagePopListener) {
        if (TextUtils.isEmpty(str) || jDMessagePopListener == null) {
            return;
        }
        currentMessagePopListeners.put(str, jDMessagePopListener);
    }

    public void shieldActivePopView() {
        isShieldPop = true;
    }

    public void showMessagePop(final MessagePopModel messagePopModel, Context context) {
        if (checkPopCondition(messagePopModel, context)) {
            postDelayed(new Runnable() { // from class: com.jingdong.common.messagepop.m
                @Override // java.lang.Runnable
                public final void run() {
                    JDMessagePopManager.this.h(messagePopModel);
                }
            }, 100);
        } else {
            printLog("\u4e0d\u7b26\u5408\u5f39\u51fa\u6761\u4ef6\uff0c\u65e0\u6cd5\u5f39\u51fa\u7ad9\u5185\u4fe1");
        }
    }

    public void unRegisterMessagePopListener(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        currentMessagePopListeners.remove(str);
    }

    private void showMessagePop(MessagePopModel messagePopModel) {
        printLog("showMessagePop_start");
        WeakReference<Activity> activity = getActivity();
        if (activity != null) {
            String topFagmentName = getTopFagmentName();
            this.activityClassName = topFagmentName;
            getJsonJDMtaParam(messagePopModel, topFagmentName);
            showPopWindow(activity, messagePopModel, 3);
        }
        long j2 = messagePopModel.displayTime;
        if (j2 == 0) {
            delayDismissPopView(DEFAULT_MESSAGE_DISPLAY_TIME);
        } else {
            delayDismissPopView(j2);
        }
        printLog("showMessagePop_end");
    }

    private boolean checkMessage() {
        return !isShieldPop;
    }
}
