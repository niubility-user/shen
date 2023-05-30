package com.jingdong.common.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.network.InternalActivityLifecycleCallbacks;
import com.jingdong.common.network.WindowPopManager;
import com.jingdong.common.sample.jshop.utils.DataCompassUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.dependency.ICustomUIComponent;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.network.utils.HttpUtils;
import com.jingdong.jdsdk.network.utils.MyCountdownTimer;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/* loaded from: classes6.dex */
public class JDGetWayQueueTools {
    public static final int CODE_QUEUE_GETWAY = 601;
    public static final int DIALOG_STYLE_GOLD_FLOW = 2;
    public static final int DIALOG_STYLE_SHEN_DUN = 1;
    public static final String TAG = "PopWindowManager-JDGetWayQueueTools";
    private static int mCode = 601;
    private static JDGetWayQueueTools mJDGetWayQueueTools = null;
    private static String sQT = "";
    private Object mLock = new Object();
    private HashMap<String, GetWayQueue> queueMap = new HashMap<>();
    private WindowPopManager.WindowState mWindowState = new WindowPopManager.WindowState(WindowPopManager.WindowType.TYPE_601);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class GetWayQueue implements InternalActivityLifecycleCallbacks.IDestroyListener {
        private BackgroundCountDownHolder mBgCountDownHolder;
        private QueueItem mCurrentItem;
        private String mDefaultBtnText;
        private DialogHolder mQueueDialogHolder;
        private boolean mWaitingInBackground;
        Activity myActivity;
        private ArrayList<QueueItem> queueItems = null;
        boolean mIsDestroy = false;

        /* loaded from: classes6.dex */
        public class BackgroundCountDownHolder {
            private final int TIMER_WHAT = 9999;
            private QueueItem mQueueItem;
            private MyCountdownTimer myCountdownTimer;

            public BackgroundCountDownHolder(QueueItem queueItem) {
                this.mQueueItem = queueItem;
            }

            public void stopTimer() {
                if (this.myCountdownTimer != null) {
                    if (OKLog.D) {
                        OKLog.d(JDGetWayQueueTools.TAG, "\u5173\u95ed\u5b9a\u65f6\u5668");
                    }
                    this.myCountdownTimer.cancel(9999);
                }
            }

            public void waitToRetryInBackground() {
                MyCountdownTimer myCountdownTimer = this.myCountdownTimer;
                if (myCountdownTimer != null) {
                    myCountdownTimer.cancel(9999);
                }
                this.myCountdownTimer = new MyCountdownTimer(this.mQueueItem.waitTime * 1000, 100L, 9999) { // from class: com.jingdong.common.utils.JDGetWayQueueTools.GetWayQueue.BackgroundCountDownHolder.1
                    @Override // com.jingdong.jdsdk.network.utils.MyCountdownTimer
                    public void onFinish(int i2) {
                        if (OKLog.D) {
                            OKLog.d(JDGetWayQueueTools.TAG, "\u540e\u53f0\u5012\u8ba1\u65f6\u7ed3\u675f");
                        }
                        String unused = JDGetWayQueueTools.sQT = BackgroundCountDownHolder.this.mQueueItem.qt;
                        GetWayQueue.this.retryAllAtQueue();
                        BackgroundCountDownHolder.this.mQueueItem.isCurrent = false;
                        GetWayQueue.this.mWaitingInBackground = false;
                        GetWayQueue.this.removeMeQueue();
                    }

                    @Override // com.jingdong.jdsdk.network.utils.MyCountdownTimer
                    public void onTick(long j2, int i2) {
                        QueueItem queueItem = BackgroundCountDownHolder.this.mQueueItem;
                        long j3 = j2 / 1000;
                        if (BackgroundCountDownHolder.this.mQueueItem.remainTime % 1000 != 0) {
                            j3++;
                        }
                        queueItem.remainTime = j3;
                        BackgroundCountDownHolder.this.mQueueItem.millisUntilFinished = j2;
                    }
                }.start();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public abstract class DialogHolder {
            protected QueueItem mCurrentItem;
            protected Dialog mQueueDialog;

            private DialogHolder() {
            }

            public void createAndHoldDialog(QueueItem queueItem) {
                this.mCurrentItem = queueItem;
                this.mQueueDialog = createDialog(queueItem);
            }

            protected abstract Dialog createDialog(QueueItem queueItem);

            public void dismiss() {
                Dialog dialog = this.mQueueDialog;
                if (dialog != null) {
                    dialog.dismiss();
                }
            }

            public Dialog getDialog() {
                return this.mQueueDialog;
            }

            public boolean isShowing() {
                Dialog dialog = this.mQueueDialog;
                return dialog != null && dialog.isShowing();
            }

            public abstract void show();

            public abstract void stopTimer();
        }

        /* loaded from: classes6.dex */
        private class NewStyleDialogHolder extends DialogHolder {
            private Handler mHandler;

            private NewStyleDialogHolder() {
                super();
                this.mHandler = new Handler();
            }

            private int randomPeriod() {
                return (new Random().nextInt(10) % 6) + 5;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void waitToRetry() {
                if (this.mCurrentItem == null) {
                    return;
                }
                int randomPeriod = randomPeriod();
                QueueItem queueItem = this.mCurrentItem;
                long j2 = queueItem.remainTime - randomPeriod;
                queueItem.remainTime = j2;
                if (j2 <= 0) {
                    queueItem.remainTime = 3L;
                }
                if (OKLog.D) {
                    OKLog.d(JDGetWayQueueTools.TAG, "waitToRetry nextPeriod: " + randomPeriod + ", remainTime: " + this.mCurrentItem.remainTime);
                }
                this.mHandler.postDelayed(new Runnable() { // from class: com.jingdong.common.utils.JDGetWayQueueTools.GetWayQueue.NewStyleDialogHolder.4
                    @Override // java.lang.Runnable
                    public void run() {
                        if (GetWayQueue.this.queueItems != null && !GetWayQueue.this.queueItems.isEmpty()) {
                            if (OKLog.D) {
                                OKLog.d(JDGetWayQueueTools.TAG, "\u540e\u53f0\u91cd\u8bd5\u6392\u961f\u8bf7\u6c42");
                            }
                            String unused = JDGetWayQueueTools.sQT = NewStyleDialogHolder.this.mCurrentItem.qt;
                            GetWayQueue.this.retryAllAtQueue();
                            NewStyleDialogHolder newStyleDialogHolder = NewStyleDialogHolder.this;
                            if (newStyleDialogHolder.mCurrentItem.remainTime <= 10) {
                                newStyleDialogHolder.mHandler.postDelayed(new Runnable() { // from class: com.jingdong.common.utils.JDGetWayQueueTools.GetWayQueue.NewStyleDialogHolder.4.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        if (GetWayQueue.this.queueItems != null && !GetWayQueue.this.queueItems.isEmpty()) {
                                            if (OKLog.D) {
                                                OKLog.d(JDGetWayQueueTools.TAG, "\u6700\u540e\u4e00\u6b21\u540e\u53f0\u68c0\u6d4b\u5230\u8bf7\u6c42\u672a\u6210\u529f\uff0c\u7ee7\u7eed\u505c\u7559\u5f39\u7a97");
                                                return;
                                            }
                                            return;
                                        }
                                        if (OKLog.D) {
                                            OKLog.d(JDGetWayQueueTools.TAG, "\u6700\u540e\u4e00\u6b21\u540e\u53f0\u68c0\u6d4b\u5230\u8bf7\u6c42\u91cd\u8bd5\u6210\u529f\uff0c\u5173\u95ed\u5f39\u7a97");
                                        }
                                        NewStyleDialogHolder newStyleDialogHolder2 = NewStyleDialogHolder.this;
                                        newStyleDialogHolder2.mCurrentItem.isCurrent = false;
                                        GetWayQueue.this.dismissDialog();
                                        GetWayQueue.this.removeMeQueue();
                                        JDGetWayQueueTools.this.mWindowState.dismiss();
                                    }
                                }, NewStyleDialogHolder.this.mCurrentItem.remainTime * 1000);
                                return;
                            } else {
                                newStyleDialogHolder.waitToRetry();
                                return;
                            }
                        }
                        if (OKLog.D) {
                            OKLog.d(JDGetWayQueueTools.TAG, "\u540e\u53f0\u68c0\u6d4b\u5230\u8bf7\u6c42\u91cd\u8bd5\u6210\u529f\uff0c\u5173\u95ed\u5f39\u7a97");
                        }
                        NewStyleDialogHolder newStyleDialogHolder2 = NewStyleDialogHolder.this;
                        newStyleDialogHolder2.mCurrentItem.isCurrent = false;
                        GetWayQueue.this.dismissDialog();
                        GetWayQueue.this.removeMeQueue();
                        JDGetWayQueueTools.this.mWindowState.dismiss();
                    }
                }, randomPeriod * 1000);
            }

            @Override // com.jingdong.common.utils.JDGetWayQueueTools.GetWayQueue.DialogHolder
            protected Dialog createDialog(QueueItem queueItem) {
                JdDialogParam jdDialogParam = new JdDialogParam();
                jdDialogParam.pageId = queueItem.httpSetting.getPageId();
                jdDialogParam.functionId = queueItem.httpSetting.getFunctionId();
                jdDialogParam.countDownTime = queueItem.waitTime;
                jdDialogParam.message = queueItem.tips;
                JdDialogParam.ButtonParam buttonParam = new JdDialogParam.ButtonParam();
                jdDialogParam.left = buttonParam;
                buttonParam.title = queueItem.leftBtnTitle;
                buttonParam.jumpUrl = queueItem.leftBtnUrl;
                JdDialogParam.ButtonParam buttonParam2 = new JdDialogParam.ButtonParam();
                jdDialogParam.right = buttonParam2;
                buttonParam2.title = queueItem.rightBtnTitle;
                buttonParam2.jumpUrl = queueItem.rightBtnUrl;
                return JDHttpTookit.getEngine().getCustomUIComponentImpl().createJdDialogNewStyle(GetWayQueue.this.myActivity, jdDialogParam, new View.OnClickListener() { // from class: com.jingdong.common.utils.JDGetWayQueueTools.GetWayQueue.NewStyleDialogHolder.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        GetWayQueue.this.cancelDialogAndQueue();
                    }
                }, new View.OnClickListener() { // from class: com.jingdong.common.utils.JDGetWayQueueTools.GetWayQueue.NewStyleDialogHolder.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        GetWayQueue.this.cancelDialogAndQueue();
                    }
                }, new DialogInterface.OnCancelListener() { // from class: com.jingdong.common.utils.JDGetWayQueueTools.GetWayQueue.NewStyleDialogHolder.3
                    @Override // android.content.DialogInterface.OnCancelListener
                    public void onCancel(DialogInterface dialogInterface) {
                        GetWayQueue.this.cancelDialogAndQueue();
                    }
                });
            }

            @Override // com.jingdong.common.utils.JDGetWayQueueTools.GetWayQueue.DialogHolder
            public void show() {
                Dialog dialog = this.mQueueDialog;
                if (dialog != null) {
                    dialog.show();
                }
                JDHttpTookit.getEngine().getCustomUIComponentImpl().startTimeCountNew(this.mQueueDialog);
                waitToRetry();
            }

            @Override // com.jingdong.common.utils.JDGetWayQueueTools.GetWayQueue.DialogHolder
            public void stopTimer() {
                Handler handler = this.mHandler;
                if (handler != null) {
                    handler.removeCallbacksAndMessages(null);
                }
            }
        }

        /* loaded from: classes6.dex */
        private class OldStyleDialogHolder extends DialogHolder {
            final int TIMER_WHAT;
            MyCountdownTimer myCountdownTimer;

            private OldStyleDialogHolder() {
                super();
                this.TIMER_WHAT = 9999;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void updateTimerToDialog() {
                Activity activity = GetWayQueue.this.myActivity;
                if (activity == null || activity.isFinishing()) {
                    return;
                }
                GetWayQueue.this.myActivity.runOnUiThread(new Runnable() { // from class: com.jingdong.common.utils.JDGetWayQueueTools.GetWayQueue.OldStyleDialogHolder.4
                    @Override // java.lang.Runnable
                    public void run() {
                        QueueItem queueItem;
                        Dialog dialog;
                        OldStyleDialogHolder oldStyleDialogHolder = OldStyleDialogHolder.this;
                        if (GetWayQueue.this.mIsDestroy || (queueItem = oldStyleDialogHolder.mCurrentItem) == null || !queueItem.isCurrent || (dialog = oldStyleDialogHolder.mQueueDialog) == null || !dialog.isShowing()) {
                            return;
                        }
                        ICustomUIComponent customUIComponentImpl = JDHttpTookit.getEngine().getCustomUIComponentImpl();
                        OldStyleDialogHolder oldStyleDialogHolder2 = OldStyleDialogHolder.this;
                        customUIComponentImpl.updateCountDown(oldStyleDialogHolder2.mQueueDialog, (int) oldStyleDialogHolder2.mCurrentItem.remainTime);
                    }
                });
            }

            private void waitToRetry() {
                MyCountdownTimer myCountdownTimer = this.myCountdownTimer;
                if (myCountdownTimer != null) {
                    myCountdownTimer.cancel(9999);
                }
                this.myCountdownTimer = new MyCountdownTimer(this.mCurrentItem.waitTime * 1000, 100L, 9999) { // from class: com.jingdong.common.utils.JDGetWayQueueTools.GetWayQueue.OldStyleDialogHolder.5
                    @Override // com.jingdong.jdsdk.network.utils.MyCountdownTimer
                    public void onFinish(int i2) {
                        if (GetWayQueue.this.mIsDestroy) {
                            return;
                        }
                        if (OKLog.D) {
                            OKLog.d(JDGetWayQueueTools.TAG, "601\u6392\u961f\u540e\u53f0\u5012\u8ba1\u65f6\u7ed3\u675f");
                        }
                        String unused = JDGetWayQueueTools.sQT = OldStyleDialogHolder.this.mCurrentItem.qt;
                        GetWayQueue.this.retryAllAtQueue();
                        OldStyleDialogHolder oldStyleDialogHolder = OldStyleDialogHolder.this;
                        oldStyleDialogHolder.mCurrentItem.isCurrent = false;
                        GetWayQueue.this.dismissDialog();
                        GetWayQueue.this.removeMeQueue();
                        JDGetWayQueueTools.this.mWindowState.dismiss();
                    }

                    @Override // com.jingdong.jdsdk.network.utils.MyCountdownTimer
                    public void onTick(long j2, int i2) {
                        OldStyleDialogHolder oldStyleDialogHolder = OldStyleDialogHolder.this;
                        QueueItem queueItem = oldStyleDialogHolder.mCurrentItem;
                        long j3 = j2 / 1000;
                        if (queueItem.remainTime % 1000 != 0) {
                            j3++;
                        }
                        queueItem.remainTime = j3;
                        queueItem.millisUntilFinished = j2;
                        oldStyleDialogHolder.updateTimerToDialog();
                    }
                }.start();
            }

            @Override // com.jingdong.common.utils.JDGetWayQueueTools.GetWayQueue.DialogHolder
            protected Dialog createDialog(QueueItem queueItem) {
                String str;
                String str2;
                String buttonText = queueItem.getButtonText();
                if (TextUtils.isEmpty(buttonText)) {
                    buttonText = GetWayQueue.this.mDefaultBtnText;
                }
                String str3 = buttonText;
                if (queueItem.type == 1) {
                    JDJSONObject jDJSONObject = new JDJSONObject();
                    jDJSONObject.put("id", (Object) (TextUtils.isEmpty(queueItem.requestID) ? "unknown" : queueItem.requestID));
                    jDJSONObject.put("p", (Object) (TextUtils.isEmpty(queueItem.encPin) ? "unknown" : queueItem.encPin));
                    str2 = jDJSONObject.toJSONString();
                    str = com.jingdong.common.network.StringUtil.queue_feedback;
                } else {
                    str = "";
                    str2 = str;
                }
                return JDHttpTookit.getEngine().getCustomUIComponentImpl().createJdDialogWithStyleTimer(GetWayQueue.this.myActivity, queueItem.tips, str3, str, queueItem.waitTime, str2, new View.OnClickListener() { // from class: com.jingdong.common.utils.JDGetWayQueueTools.GetWayQueue.OldStyleDialogHolder.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        GetWayQueue.this.cancelDialogAndQueue();
                    }
                }, new View.OnClickListener() { // from class: com.jingdong.common.utils.JDGetWayQueueTools.GetWayQueue.OldStyleDialogHolder.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        GetWayQueue.this.cancelDialogAndQueue();
                    }
                }, new DialogInterface.OnCancelListener() { // from class: com.jingdong.common.utils.JDGetWayQueueTools.GetWayQueue.OldStyleDialogHolder.3
                    @Override // android.content.DialogInterface.OnCancelListener
                    public void onCancel(DialogInterface dialogInterface) {
                        GetWayQueue.this.cancelDialogAndQueue();
                    }
                });
            }

            @Override // com.jingdong.common.utils.JDGetWayQueueTools.GetWayQueue.DialogHolder
            public void show() {
                Dialog dialog = this.mQueueDialog;
                if (dialog != null) {
                    dialog.show();
                }
                updateTimerToDialog();
                waitToRetry();
            }

            @Override // com.jingdong.common.utils.JDGetWayQueueTools.GetWayQueue.DialogHolder
            public void stopTimer() {
                if (this.myCountdownTimer != null) {
                    if (OKLog.D) {
                        OKLog.d(JDGetWayQueueTools.TAG, "\u5173\u95ed\u5b9a\u65f6\u5668");
                    }
                    this.myCountdownTimer.cancel(9999);
                }
            }
        }

        GetWayQueue(Activity activity) {
            this.mDefaultBtnText = "";
            if (activity != null) {
                this.myActivity = activity;
                this.mDefaultBtnText = com.jingdong.common.network.StringUtil.queue_not_wait;
                JDHttpTookit.getEngine().getInternalActivityLifecycleCallbacks().addDestroyListener(this);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized void addToQueue(QueueItem queueItem) {
            if (!this.mIsDestroy && this.myActivity != null) {
                if (this.queueItems == null) {
                    this.queueItems = new ArrayList<>(1);
                }
                this.queueItems.add(queueItem);
                if (OKLog.D) {
                    OKLog.d(JDGetWayQueueTools.TAG, "queueItems.size = " + this.queueItems.size());
                }
                if (queueItem.showDialog) {
                    checkAndShowDialog(queueItem);
                } else {
                    checkWithoutShowDialog(queueItem);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void cancelDialogAndQueue() {
            stopTimer();
            removeAndCancelAllQueue();
            forceDismissDialog();
            JDGetWayQueueTools.this.mWindowState.dismiss();
            if (this.myActivity != null) {
                try {
                    QueueItem queueItem = this.mCurrentItem;
                    if (queueItem != null) {
                        String pageId = queueItem.httpSetting.getPageId();
                        String functionId = this.mCurrentItem.httpSetting.getFunctionId();
                        String valueOf = String.valueOf(r1.waitTime - this.mCurrentItem.remainTime);
                        JDHttpTookit.getEngine().getExceptionReportDelegate().sendMtaCommonData(this.myActivity, "UnifiedControls_QueuePopupClose", functionId + CartConstant.KEY_YB_INFO_LINK + valueOf, "onClick", "", "", "", "", pageId);
                    }
                } catch (Exception e2) {
                    if (OKLog.D) {
                        e2.printStackTrace();
                    }
                }
            }
        }

        private synchronized void checkAndShowDialog(final QueueItem queueItem) {
            if (OKLog.D) {
                OKLog.d(JDGetWayQueueTools.TAG, "checkAndShowDialog \u5904\u7406601\u5f39\u8d77\u5bf9\u8bdd\u6846");
            }
            JDGetWayQueueTools.this.mWindowState.show();
            Activity activity = this.myActivity;
            if (activity != null) {
                activity.runOnUiThread(new Runnable() { // from class: com.jingdong.common.utils.JDGetWayQueueTools.GetWayQueue.2
                    @Override // java.lang.Runnable
                    public void run() {
                        Activity activity2;
                        synchronized (JDGetWayQueueTools.this.mLock) {
                            GetWayQueue getWayQueue = GetWayQueue.this;
                            if (!getWayQueue.mIsDestroy && (activity2 = getWayQueue.myActivity) != null && !activity2.isFinishing()) {
                                if (GetWayQueue.this.mCurrentItem != null && GetWayQueue.this.mQueueDialogHolder != null) {
                                    if (OKLog.D) {
                                        OKLog.d(JDGetWayQueueTools.TAG, "\u5f53\u524d\u5b58\u5728\u6b63\u5728\u5c55\u793a\u7684\u6392\u961f\u5f39\u7a97");
                                    }
                                    if (!GetWayQueue.this.mQueueDialogHolder.isShowing()) {
                                        QueueItem queueItem2 = queueItem;
                                        queueItem2.isCurrent = true;
                                        queueItem2.remainTime = queueItem2.waitTime;
                                        queueItem2.millisUntilFinished = r2 * 1000;
                                        GetWayQueue.this.mCurrentItem = queueItem2;
                                        GetWayQueue.this.mQueueDialogHolder.show();
                                    }
                                } else {
                                    if (OKLog.D) {
                                        OKLog.d(JDGetWayQueueTools.TAG, "\u5373\u5c06\u5f39\u8d77601\u6392\u961f\u5f39\u7a97");
                                    }
                                    QueueItem queueItem3 = queueItem;
                                    if (queueItem3.dialogType == 2 && queueItem3.isNewStyle) {
                                        GetWayQueue getWayQueue2 = GetWayQueue.this;
                                        getWayQueue2.mQueueDialogHolder = new NewStyleDialogHolder();
                                    } else {
                                        GetWayQueue getWayQueue3 = GetWayQueue.this;
                                        getWayQueue3.mQueueDialogHolder = new OldStyleDialogHolder();
                                    }
                                    GetWayQueue.this.mQueueDialogHolder.createAndHoldDialog(queueItem);
                                    QueueItem queueItem4 = queueItem;
                                    queueItem4.isCurrent = true;
                                    queueItem4.remainTime = queueItem4.waitTime;
                                    queueItem4.millisUntilFinished = r2 * 1000;
                                    GetWayQueue.this.mCurrentItem = queueItem4;
                                    GetWayQueue.this.mQueueDialogHolder.show();
                                }
                            }
                        }
                    }
                });
            }
        }

        private synchronized void checkWithoutShowDialog(final QueueItem queueItem) {
            if (OKLog.D) {
                OKLog.d(JDGetWayQueueTools.TAG, "checkWithoutShowDialog \u4e0d\u5f39\u7a97\u62e6\u622a\u6392\u961f\u8bf7\u6c42");
            }
            Activity activity = this.myActivity;
            if (activity != null) {
                activity.runOnUiThread(new Runnable() { // from class: com.jingdong.common.utils.JDGetWayQueueTools.GetWayQueue.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (GetWayQueue.this.mCurrentItem != null && GetWayQueue.this.mWaitingInBackground) {
                            if (OKLog.D) {
                                OKLog.d(JDGetWayQueueTools.TAG, "\u6b63\u5728\u540e\u53f0\u7b49\u5f85\u4e2d\uff0c\u4e0d\u505a\u7279\u6b8a\u64cd\u4f5c");
                                return;
                            }
                            return;
                        }
                        QueueItem queueItem2 = queueItem;
                        queueItem2.isCurrent = true;
                        queueItem2.remainTime = queueItem2.waitTime;
                        queueItem2.millisUntilFinished = r2 * 1000;
                        GetWayQueue.this.mCurrentItem = queueItem2;
                        GetWayQueue.this.mWaitingInBackground = true;
                        GetWayQueue getWayQueue = GetWayQueue.this;
                        getWayQueue.mBgCountDownHolder = new BackgroundCountDownHolder(queueItem);
                        GetWayQueue.this.mBgCountDownHolder.waitToRetryInBackground();
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void dismissDialog() {
            synchronized (JDGetWayQueueTools.this.mLock) {
                QueueItem queueItem = this.mCurrentItem;
                if (queueItem != null && !queueItem.isCurrent && this.mQueueDialogHolder != null) {
                    if (OKLog.D) {
                        OKLog.d(JDGetWayQueueTools.TAG, "dismiss");
                    }
                    forceDismissDialog();
                }
            }
        }

        private void forceDismissDialog() {
            Activity activity = this.myActivity;
            if (activity != null) {
                activity.runOnUiThread(new Runnable() { // from class: com.jingdong.common.utils.JDGetWayQueueTools.GetWayQueue.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (GetWayQueue.this.mQueueDialogHolder != null) {
                            if (OKLog.D) {
                                OKLog.d(JDGetWayQueueTools.TAG, "forceDismissDialog");
                            }
                            try {
                                GetWayQueue.this.mQueueDialogHolder.dismiss();
                            } catch (Exception e2) {
                                if (OKLog.D) {
                                    e2.printStackTrace();
                                }
                            }
                            GetWayQueue.this.mQueueDialogHolder = null;
                        }
                    }
                });
            }
        }

        private synchronized void removeAndCancelAllQueue() {
            ArrayList<QueueItem> arrayList = this.queueItems;
            if (arrayList != null && !arrayList.isEmpty()) {
                if (OKLog.D) {
                    OKLog.d(JDGetWayQueueTools.TAG, "removeAndCancelAllQueue queueItems.size = " + this.queueItems.size());
                }
                Iterator<QueueItem> it = this.queueItems.iterator();
                while (it.hasNext()) {
                    QueueItem next = it.next();
                    if (next != null) {
                        next.cancel();
                    }
                }
                this.queueItems.clear();
                if (OKLog.D) {
                    OKLog.d(JDGetWayQueueTools.TAG, "removeAndCancelAllQueue queueItems.size clear = " + this.queueItems.size());
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeMeQueue() {
            synchronized (JDGetWayQueueTools.this.queueMap) {
                if (this.myActivity == null) {
                    return;
                }
                JDGetWayQueueTools.this.queueMap.remove(this.myActivity.toString());
                if (OKLog.D) {
                    OKLog.d(JDGetWayQueueTools.TAG, "queueMap.size = " + JDGetWayQueueTools.this.queueMap.size());
                }
                this.myActivity = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized void retryAllAtQueue() {
            if (OKLog.D) {
                OKLog.d(JDGetWayQueueTools.TAG, "retryAllAtQueue queueItems.size = " + this.queueItems.size());
            }
            ArrayList<QueueItem> arrayList = this.queueItems;
            if (arrayList != null && !arrayList.isEmpty()) {
                Iterator<QueueItem> it = this.queueItems.iterator();
                while (it.hasNext()) {
                    QueueItem next = it.next();
                    if (next != null) {
                        next.retry(this.myActivity);
                    }
                }
                this.queueItems.clear();
            }
        }

        @Override // com.jingdong.common.network.InternalActivityLifecycleCallbacks.IDestroyListener
        public boolean isActivityHolder(Activity activity) {
            Activity activity2 = this.myActivity;
            if (activity2 != null) {
                return activity2.toString().equals(activity.toString());
            }
            return false;
        }

        @Override // com.jingdong.common.network.InternalActivityLifecycleCallbacks.IDestroyListener
        public void onDestroy() {
            if (OKLog.D) {
                OKLog.d(JDGetWayQueueTools.TAG, "onDestroy = ");
            }
            this.mIsDestroy = true;
            if (this.mQueueDialogHolder != null) {
                if (OKLog.D) {
                    OKLog.d(JDGetWayQueueTools.TAG, "onDestroy mQueueDialog dismiss");
                }
                try {
                    this.mQueueDialogHolder.dismiss();
                } catch (Exception e2) {
                    if (OKLog.D) {
                        e2.printStackTrace();
                    }
                }
                this.mQueueDialogHolder = null;
            }
            cancelDialogAndQueue();
            removeMeQueue();
        }

        public void stopTimer() {
            if (OKLog.D) {
                OKLog.d(JDGetWayQueueTools.TAG, "\u5173\u95ed\u5b9a\u65f6\u5668");
            }
            DialogHolder dialogHolder = this.mQueueDialogHolder;
            if (dialogHolder != null) {
                dialogHolder.stopTimer();
            }
            BackgroundCountDownHolder backgroundCountDownHolder = this.mBgCountDownHolder;
            if (backgroundCountDownHolder != null) {
                backgroundCountDownHolder.stopTimer();
            }
        }
    }

    /* loaded from: classes6.dex */
    public static class JdDialogParam {
        public int countDownTime;
        public String functionId;
        public ButtonParam left;
        public String message;
        public String pageId;
        public ButtonParam right;
        public String title;
        public String type;

        /* loaded from: classes6.dex */
        public static class ButtonParam {
            public String jumpUrl;
            public String title;

            public ButtonParam() {
            }

            public ButtonParam(String str, String str2) {
                this.title = str;
                this.jumpUrl = str2;
            }
        }
    }

    /* loaded from: classes6.dex */
    public interface OnQueueCancelListener {
        void onCancel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class QueueItem {
        int dialogType;
        String encPin;
        HttpSetting httpSetting;
        boolean isCurrent;
        boolean isNewStyle;
        String leftBtnTitle;
        String leftBtnUrl;
        long millisUntilFinished;
        String qt;
        long remainTime;
        String requestID;
        String respStr;
        String rightBtnTitle;
        String rightBtnUrl;
        boolean showDialog;
        String tips;
        int type;
        int waitTime;

        QueueItem(JDGetWayQueueTools jDGetWayQueueTools, HttpSetting httpSetting, String str, String str2, int i2) {
            this(httpSetting, "", str, str2, i2, 0, "", "");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void cancel() {
            HttpSetting httpSetting = this.httpSetting;
            if (httpSetting != null) {
                OnQueueCancelListener onQueueCancelListener = httpSetting.getOnQueueCancelListener();
                if (onQueueCancelListener != null) {
                    onQueueCancelListener.onCancel();
                    return;
                }
                HttpError httpError = new HttpError();
                HttpResponse httpResponse = new HttpResponse(null);
                httpResponse.setString(this.respStr);
                httpError.setHttpResponse(httpResponse);
                httpError.setJsonCode(601);
                httpError.setErrorCode(601);
                httpError.setMessage(this.tips);
                this.httpSetting.onError(httpError);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void retry(Activity activity) {
            HttpGroup httpGroupaAsynPool;
            HttpSetting httpSetting;
            if (activity == null || (httpGroupaAsynPool = HttpGroupUtils.getHttpGroupaAsynPool(activity)) == null || (httpSetting = this.httpSetting) == null) {
                return;
            }
            httpSetting.setUrl(httpSetting.getRequestUrl());
            if (!TextUtils.isEmpty(this.qt)) {
                this.httpSetting.putMapParams("qt", JDGetWayQueueTools.sQT);
            }
            this.httpSetting.resetHttpSetting();
            try {
                httpGroupaAsynPool.add(this.httpSetting);
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                }
                cancel();
            }
        }

        public String getButtonText() {
            HttpSetting httpSetting = this.httpSetting;
            return (httpSetting == null || TextUtils.isEmpty(httpSetting.getButtonText())) ? "" : this.httpSetting.getButtonText();
        }

        QueueItem(HttpSetting httpSetting, String str, String str2, String str3, int i2, int i3, String str4, String str5) {
            this.isCurrent = false;
            this.isNewStyle = false;
            this.httpSetting = httpSetting;
            this.tips = str2;
            this.waitTime = Math.max(i2, 10);
            this.qt = str3;
            this.requestID = str4;
            this.encPin = str5;
            this.type = i3;
            this.respStr = str;
        }
    }

    /* loaded from: classes.dex */
    public interface QueueMode {
        public static final String MODE_CART = "mode_cart";
        public static final String MODE_ORDER = "mode_order";
        public static final String MODE_PAY = "mode_pay";
        public static final String MODE_PRODUCT_DETAIL = "mode_product_detail";
        public static final String MODE_SEARCH = "mode_search";
    }

    private JDGetWayQueueTools() {
    }

    private void dealItem(Activity activity, QueueItem queueItem) {
        GetWayQueue getWayQueue = getGetWayQueue(activity, queueItem.httpSetting);
        if (getWayQueue != null) {
            getWayQueue.addToQueue(queueItem);
        }
    }

    private GetWayQueue getGetWayQueue(Activity activity, HttpSetting httpSetting) {
        GetWayQueue getWayQueue;
        if (activity == null || activity.isFinishing()) {
            return null;
        }
        synchronized (this.queueMap) {
            getWayQueue = this.queueMap.get(activity.toString());
            if (getWayQueue == null && httpSetting != null) {
                if (OKLog.D) {
                    OKLog.d(TAG, "\u8bf7\u6c42\u89e6\u53d1\u9875\u9762\u540d\uff1a" + httpSetting.getCurrentPageName() + "\uff0c\u5f53\u524d\u6240\u5728\u9875\u9762\u540d\uff1a" + activity);
                }
                if (TextUtils.equals(activity.toString(), httpSetting.getCurrentPageName())) {
                    getWayQueue = new GetWayQueue(activity);
                    this.queueMap.put(activity.toString(), getWayQueue);
                    if (OKLog.D) {
                        OKLog.d(TAG, "\u7b26\u5408\u5f39\u8d77\u6761\u4ef6\uff0c\u8fd4\u56de\u5f39\u7a97\u961f\u5217 \uff0cqueueMap.size = " + this.queueMap.size());
                    }
                }
            }
        }
        return getWayQueue;
    }

    public static synchronized JDGetWayQueueTools getInstance() {
        JDGetWayQueueTools jDGetWayQueueTools;
        synchronized (JDGetWayQueueTools.class) {
            if (mJDGetWayQueueTools == null) {
                mJDGetWayQueueTools = new JDGetWayQueueTools();
            }
            jDGetWayQueueTools = mJDGetWayQueueTools;
        }
        return jDGetWayQueueTools;
    }

    public static String getQT() {
        return sQT;
    }

    private boolean handleQueueItem(QueueItem queueItem) {
        Activity currentMyActivity = JDHttpTookit.getEngine().getAppProxy().getCurrentMyActivity();
        if (currentMyActivity == null) {
            return false;
        }
        int i2 = queueItem.waitTime;
        String str = queueItem.tips;
        HttpSetting httpSetting = queueItem.httpSetting;
        if (i2 <= 0 || TextUtils.isEmpty(str)) {
            return false;
        }
        dealItem(currentMyActivity, queueItem);
        JDHttpTookit.getEngine().getExceptionReportDelegate().sendMtaCommonData(currentMyActivity, "UnifiedControls_QueuePopup", httpSetting.getFunctionId(), "onClick", "", "", "", "", httpSetting.getPageId());
        return true;
    }

    public boolean checkAndDoQueue(HttpResponse httpResponse, HttpSetting httpSetting, boolean z) {
        Object jSONObject;
        if (httpResponse == null || httpSetting == null) {
            return false;
        }
        if (httpSetting.isUseFastJsonParser()) {
            jSONObject = httpResponse.getFastJsonObject();
        } else {
            jSONObject = httpResponse.getJSONObject();
        }
        Map<String, String> header = httpResponse.getHeader();
        if (jSONObject != null) {
            return checkAndDoQueue(jSONObject, httpSetting, header, z);
        }
        return false;
    }

    public boolean isWindowShowing() {
        return this.mWindowState.getState() == 1;
    }

    public void setWindowStateChangeListener(WindowPopManager.WindowStateListener windowStateListener) {
        this.mWindowState.setStateChangeLister(windowStateListener);
    }

    public boolean shouldInterceptRequest(HttpResponse httpResponse) {
        return httpResponse.getCode() == 601;
    }

    public void triggerPendingRequest() {
        if (OKLog.D) {
            OKLog.d(TAG, "triggerPendingRequest");
        }
        synchronized (this.queueMap) {
            HashMap<String, GetWayQueue> hashMap = this.queueMap;
            if (hashMap != null && !hashMap.isEmpty()) {
                Iterator<Map.Entry<String, GetWayQueue>> it = this.queueMap.entrySet().iterator();
                while (it.hasNext()) {
                    GetWayQueue value = it.next().getValue();
                    value.stopTimer();
                    value.retryAllAtQueue();
                }
                this.queueMap.clear();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01f1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public <T> boolean checkAndDoQueue(T t, HttpSetting httpSetting, Map<String, String> map, boolean z) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String jSONObjectProxy;
        String str6;
        String str7;
        int i2;
        String str8;
        String str9;
        JSONObjectProxy jSONObject;
        String str10;
        String str11;
        int i3;
        QueueItem queueItem;
        String dataFromSwitchQuery;
        JDJSONObject parseObject;
        String str12;
        String str13;
        String str14;
        if (httpSetting == null || httpSetting.isEnableGatewayQueue()) {
            if (t == null || httpSetting == null) {
                return false;
            }
            String str15 = null;
            if (t instanceof JDJSONObject) {
                JDJSONObject jDJSONObject = (JDJSONObject) t;
                String optString = jDJSONObject.optString("tips");
                int optInt = jDJSONObject.optInt("wait");
                String optString2 = jDJSONObject.optString("qt");
                JDJSONObject jSONObject2 = jDJSONObject.getJSONObject("buttons");
                if (jSONObject2 != null) {
                    JDJSONObject jSONObject3 = jSONObject2.getJSONObject("left");
                    if (jSONObject3 != null) {
                        str14 = jSONObject3.optString(DataCompassUtils.MODULE_TYPE_HEAD);
                        str12 = jSONObject3.optString("url");
                    } else {
                        str12 = null;
                        str14 = null;
                    }
                    JDJSONObject jSONObject4 = jSONObject2.getJSONObject("right");
                    if (jSONObject4 != null) {
                        String optString3 = jSONObject4.optString(DataCompassUtils.MODULE_TYPE_HEAD);
                        str13 = jSONObject4.optString("url");
                        str = optString3;
                    } else {
                        str = null;
                        str13 = null;
                    }
                    str15 = str14;
                } else {
                    str = null;
                    str12 = null;
                    str13 = null;
                }
                jSONObjectProxy = jDJSONObject.toString();
                str6 = str12;
                str5 = str13;
                str7 = optString2;
                i2 = optInt;
                str9 = str15;
                str8 = optString;
            } else {
                JSONObjectProxy jSONObjectProxy2 = (JSONObjectProxy) t;
                String optString4 = jSONObjectProxy2.optString("tips");
                int optInt2 = jSONObjectProxy2.optInt("wait");
                String optString5 = jSONObjectProxy2.optString("qt");
                try {
                    jSONObject = ((JSONObjectProxy) t).getJSONObject("buttons");
                } catch (Exception unused) {
                    str = null;
                    str2 = null;
                    str3 = null;
                }
                if (jSONObject != null) {
                    JSONObjectProxy jSONObject5 = jSONObject.getJSONObject("left");
                    if (jSONObject5 != null) {
                        str3 = jSONObject5.optString(DataCompassUtils.MODULE_TYPE_HEAD);
                        try {
                            str2 = jSONObject5.optString("url");
                        } catch (Exception unused2) {
                            str = null;
                            str2 = null;
                            str4 = null;
                            str15 = str3;
                            str5 = str4;
                            jSONObjectProxy = jSONObjectProxy2.toString();
                            str6 = str2;
                            str7 = optString5;
                            i2 = optInt2;
                            str8 = optString4;
                            str9 = str15;
                            String str16 = str;
                            if (map != null) {
                            }
                            str10 = "";
                            str11 = str10;
                            i3 = 2;
                            String str17 = str10;
                            String str18 = str11;
                            int i4 = i3;
                            String str19 = jSONObjectProxy;
                            queueItem = new QueueItem(httpSetting, jSONObjectProxy, str8, str7, i2, i3, str18, str17);
                            queueItem.dialogType = i4;
                            queueItem.showDialog = z;
                            queueItem.leftBtnTitle = str9;
                            queueItem.leftBtnUrl = str6;
                            queueItem.rightBtnTitle = str16;
                            queueItem.rightBtnUrl = str5;
                            dataFromSwitchQuery = JDHttpTookit.getEngine().getRuntimeConfigImpl().getDataFromSwitchQuery("GateWayQueueConfig", "");
                            if (OKLog.D) {
                            }
                            if (!TextUtils.isEmpty(dataFromSwitchQuery)) {
                            }
                            return handleQueueItem(queueItem);
                        }
                    } else {
                        str2 = null;
                        str3 = null;
                    }
                    try {
                        JSONObjectProxy jSONObject6 = jSONObject.getJSONObject("right");
                        if (jSONObject6 != null) {
                            str = jSONObject6.optString(DataCompassUtils.MODULE_TYPE_HEAD);
                            try {
                                str4 = jSONObject6.optString("url");
                            } catch (Exception unused3) {
                                str4 = null;
                                str15 = str3;
                                str5 = str4;
                                jSONObjectProxy = jSONObjectProxy2.toString();
                                str6 = str2;
                                str7 = optString5;
                                i2 = optInt2;
                                str8 = optString4;
                                str9 = str15;
                                String str162 = str;
                                if (map != null) {
                                }
                                str10 = "";
                                str11 = str10;
                                i3 = 2;
                                String str172 = str10;
                                String str182 = str11;
                                int i42 = i3;
                                String str192 = jSONObjectProxy;
                                queueItem = new QueueItem(httpSetting, jSONObjectProxy, str8, str7, i2, i3, str182, str172);
                                queueItem.dialogType = i42;
                                queueItem.showDialog = z;
                                queueItem.leftBtnTitle = str9;
                                queueItem.leftBtnUrl = str6;
                                queueItem.rightBtnTitle = str162;
                                queueItem.rightBtnUrl = str5;
                                dataFromSwitchQuery = JDHttpTookit.getEngine().getRuntimeConfigImpl().getDataFromSwitchQuery("GateWayQueueConfig", "");
                                if (OKLog.D) {
                                }
                                if (!TextUtils.isEmpty(dataFromSwitchQuery)) {
                                    queueItem.isNewStyle = TextUtils.equals(parseObject.optString("isNewStyle"), "1");
                                    if (TextUtils.isEmpty(queueItem.leftBtnTitle)) {
                                    }
                                    if (TextUtils.isEmpty(queueItem.leftBtnUrl)) {
                                    }
                                    if (TextUtils.isEmpty(queueItem.rightBtnTitle)) {
                                    }
                                    if (TextUtils.isEmpty(queueItem.rightBtnUrl)) {
                                    }
                                }
                                return handleQueueItem(queueItem);
                            }
                        } else {
                            str4 = null;
                            str = null;
                        }
                    } catch (Exception unused4) {
                        str = null;
                    }
                    str15 = str3;
                    str5 = str4;
                    jSONObjectProxy = jSONObjectProxy2.toString();
                    str6 = str2;
                    str7 = optString5;
                    i2 = optInt2;
                    str8 = optString4;
                    str9 = str15;
                } else {
                    str4 = null;
                    str = null;
                    str2 = null;
                    str5 = str4;
                    jSONObjectProxy = jSONObjectProxy2.toString();
                    str6 = str2;
                    str7 = optString5;
                    i2 = optInt2;
                    str8 = optString4;
                    str9 = str15;
                }
            }
            String str1622 = str;
            if (map != null || map.isEmpty()) {
                str10 = "";
                str11 = str10;
                i3 = 2;
            } else {
                int i5 = TextUtils.equals(HttpUtils.getHeaderFieldIgnoreCase(map, "X-Itrp-Type"), "1") ? 1 : 2;
                if (i5 == 1) {
                    str10 = !TextUtils.isEmpty(HttpUtils.getHeaderFieldIgnoreCase(map, "X-Enc-Pin")) ? HttpUtils.getHeaderFieldIgnoreCase(map, "X-Enc-Pin") : "";
                    if (TextUtils.isEmpty(HttpUtils.getHeaderFieldIgnoreCase(map, "X-API-Request-Id"))) {
                        i3 = i5;
                        str11 = "";
                    } else {
                        i3 = i5;
                        str11 = HttpUtils.getHeaderFieldIgnoreCase(map, "X-API-Request-Id");
                    }
                } else {
                    i3 = i5;
                    str10 = "";
                    str11 = str10;
                }
            }
            String str1722 = str10;
            String str1822 = str11;
            int i422 = i3;
            String str1922 = jSONObjectProxy;
            queueItem = new QueueItem(httpSetting, jSONObjectProxy, str8, str7, i2, i3, str1822, str1722);
            queueItem.dialogType = i422;
            queueItem.showDialog = z;
            queueItem.leftBtnTitle = str9;
            queueItem.leftBtnUrl = str6;
            queueItem.rightBtnTitle = str1622;
            queueItem.rightBtnUrl = str5;
            dataFromSwitchQuery = JDHttpTookit.getEngine().getRuntimeConfigImpl().getDataFromSwitchQuery("GateWayQueueConfig", "");
            if (OKLog.D) {
                StringBuilder sb = new StringBuilder();
                sb.append("\u62e6\u622a\u5230601\u8bf7\u6c42\u4e0b\u884c\u6570\u636e  json: ");
                sb.append(str1922);
                sb.append("\n functionId: ");
                sb.append(httpSetting.getFunctionId());
                sb.append("\n requestId: ");
                sb.append(str1822);
                sb.append("\n encPin: ");
                sb.append(str1722);
                sb.append("\n \u662f\u5426\u4e3a\u795e\u76fe\u5f39\u7a97\uff1a ");
                sb.append(i422 == 1 ? "\u662f" : "\u5426");
                sb.append("\n \u83b7\u53d6\u5230SwitchQuery\u7ebf\u4e0a\u914d\u7f6e : ");
                sb.append(dataFromSwitchQuery);
                OKLog.d(TAG, sb.toString());
            }
            if (!TextUtils.isEmpty(dataFromSwitchQuery) && (parseObject = JDJSON.parseObject(dataFromSwitchQuery)) != null) {
                queueItem.isNewStyle = TextUtils.equals(parseObject.optString("isNewStyle"), "1");
                if (TextUtils.isEmpty(queueItem.leftBtnTitle)) {
                    queueItem.leftBtnTitle = parseObject.optString("leftBtnTitle", "");
                }
                if (TextUtils.isEmpty(queueItem.leftBtnUrl)) {
                    queueItem.leftBtnUrl = parseObject.optString("leftBtnUrl", "");
                }
                if (TextUtils.isEmpty(queueItem.rightBtnTitle)) {
                    queueItem.rightBtnTitle = parseObject.optString("rightBtnTitle", "");
                }
                if (TextUtils.isEmpty(queueItem.rightBtnUrl)) {
                    queueItem.rightBtnUrl = parseObject.optString("rightBtnUrl", "");
                }
            }
            return handleQueueItem(queueItem);
        }
        return false;
    }
}
