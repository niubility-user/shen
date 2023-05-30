package com.jingdong.common.network;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.common.network.AbsDialogController;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.dependency.ILoginUserController;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpGroupSetting;
import com.jingdong.jdsdk.network.toolbox.HttpRequest;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class HttpErrorAlertController implements HttpGroup.HttpErrorAlertControllerInterface {
    private static final HashMap<String, ArrayList<HttpGroup.HttpErrorAlertListener>> alertDialogStateMap = new HashMap<>();
    private HttpGroupSetting httpGroupSetting;
    private HttpRequest httpRequest;
    private HttpSetting httpSetting;
    private HttpGroup.HttpErrorAlertListener listener;

    /* loaded from: classes5.dex */
    public static class Factory implements HttpGroup.HttpErrorAlertControllerFactory {
        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.HttpErrorAlertControllerFactory
        public HttpGroup.HttpErrorAlertControllerInterface createController(HttpGroupSetting httpGroupSetting, HttpSetting httpSetting, HttpRequest httpRequest) {
            return new HttpErrorAlertController(httpGroupSetting, httpSetting, httpRequest);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public class HttpDialogController extends AbsDialogController {
        protected ArrayList<HttpGroup.HttpErrorAlertListener> httpErrorAlertListeners;
        private boolean isSynchronizHTTP = true;
        protected Activity myActivity;
        protected String myActivityTag;

        HttpDialogController() {
        }

        protected void actionCancel() {
            actionCommon(false);
        }

        protected void actionCommon(boolean z) {
            Activity myActivity = HttpErrorAlertController.this.httpGroupSetting.getMyActivity();
            if (myActivity != null) {
                myActivity.runOnUiThread(new Runnable() { // from class: com.jingdong.common.network.HttpErrorAlertController.HttpDialogController.3
                    @Override // java.lang.Runnable
                    public void run() {
                        HttpDialogController.this.dismiss();
                    }
                });
            }
            if (OKLog.D) {
                OKLog.d("HttpGroup", "id:" + HttpErrorAlertController.this.httpSetting.getId() + "- notifyUser() retry -->> httpErrorAlertListeners.size() = " + this.httpErrorAlertListeners.size());
            }
            synchronized (HttpErrorAlertController.alertDialogStateMap) {
                for (int i2 = 0; i2 < this.httpErrorAlertListeners.size(); i2++) {
                    HttpGroup.HttpErrorAlertListener httpErrorAlertListener = this.httpErrorAlertListeners.get(i2);
                    if (z) {
                        httpErrorAlertListener.reTry();
                    } else {
                        httpErrorAlertListener.sendError();
                    }
                }
                HttpErrorAlertController.alertDialogStateMap.remove(this.myActivityTag);
            }
        }

        protected void actionRetry() {
            actionCommon(true);
        }

        public void init(ArrayList<HttpGroup.HttpErrorAlertListener> arrayList, Activity activity, String str, AbsDialogController.IDialog iDialog) {
            this.myActivity = activity;
            this.httpErrorAlertListeners = arrayList;
            this.myActivityTag = str;
            init(activity, iDialog);
            setOnPositiveClickListener(new DialogInterface.OnClickListener() { // from class: com.jingdong.common.network.HttpErrorAlertController.HttpDialogController.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    HttpDialogController.this.onPositiveButtonClick();
                }
            });
            setOnNegativeClickListener(new DialogInterface.OnClickListener() { // from class: com.jingdong.common.network.HttpErrorAlertController.HttpDialogController.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    HttpDialogController.this.onNegativeButtonClick();
                }
            });
        }

        public void onNegativeButtonClick() {
            final Activity myActivity;
            actionCancel();
            if (HttpErrorAlertController.this.httpSetting.getAlertErrorDialogType() != 2 || (myActivity = HttpErrorAlertController.this.httpGroupSetting.getMyActivity()) == null) {
                return;
            }
            myActivity.runOnUiThread(new Runnable() { // from class: com.jingdong.common.network.HttpErrorAlertController.HttpDialogController.4
                @Override // java.lang.Runnable
                public void run() {
                    myActivity.finish();
                }
            });
        }

        public void onPositiveButtonClick() {
            Intent intent;
            if (HttpErrorAlertController.this.httpSetting.getAlertErrorDialogType() == 3) {
                actionCancel();
                Activity myActivity = HttpErrorAlertController.this.httpGroupSetting.getMyActivity();
                if (myActivity != null) {
                    if (Build.VERSION.SDK_INT > 10) {
                        intent = new Intent("android.settings.SETTINGS");
                    } else {
                        intent = new Intent("android.settings.WIRELESS_SETTINGS");
                    }
                    myActivity.startActivity(intent);
                    return;
                }
                return;
            }
            actionRetry();
        }

        public void setSynchronizHTTP(boolean z) {
            this.isSynchronizHTTP = z;
        }
    }

    public HttpErrorAlertController(HttpGroupSetting httpGroupSetting, HttpSetting httpSetting, HttpRequest httpRequest) {
        this.httpGroupSetting = httpGroupSetting;
        this.httpSetting = httpSetting;
        this.httpRequest = httpRequest;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public void alertErrorDialog(HttpError httpError) {
        if (NetUtils.isNetworkAvailable() && this.httpSetting.isNotifyUser()) {
            if (OKLog.D) {
                OKLog.d("HttpGroup", "id:" + this.httpSetting.getId() + "- alertErrorDialog() -->> true");
            }
            HttpDialogController httpDialogController = new HttpDialogController();
            httpDialogController.setCanceledOnTouchOutside(false);
            if (TextUtils.isEmpty(httpError.getMessage())) {
                if (httpError.getErrorCode() == 4) {
                    httpDialogController.setMessage("\u6570\u636e\u52a0\u8f7d\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5");
                } else if (httpError.getErrorCode() == 33) {
                    httpDialogController.setMessage(StringUtil.alert_message_no_login_error_2);
                } else if (httpError.getErrorCode() == 4) {
                    httpDialogController.setMessage("\u7f51\u7edc\u5728\u5f00\u5c0f\u5dee\uff0c\u68c0\u67e5\u540e\u518d\u8bd5\u5427");
                } else {
                    int errorCode = httpError.getErrorCode();
                    String str = StringUtil.alert_message_default_error;
                    if (errorCode == 11) {
                        httpDialogController.setMessage(StringUtil.alert_message_default_error);
                    } else {
                        try {
                            str = String.format(StringUtil.alert_message_default_error_with_code, Integer.valueOf(httpError.getJsonCode()));
                        } catch (Throwable unused) {
                        }
                        httpDialogController.setMessage(str);
                    }
                }
            } else {
                httpDialogController.setMessage(httpError.getMessage());
            }
            if (this.httpSetting.getAlertErrorDialogType() != 1 && httpError.getErrorCode() != 33) {
                if (this.httpSetting.getAlertErrorDialogType() == 0) {
                    httpDialogController.setPositiveButton("\u91cd\u8bd5");
                    httpDialogController.setNegativeButton(this.httpSetting.isNotifyUserWithExit() ? "\u9000\u51fa" : "\u53d6\u6d88");
                } else if (this.httpSetting.getAlertErrorDialogType() == 2) {
                    httpDialogController.setPositiveButton("\u91cd\u8bd5");
                    httpDialogController.setNegativeButton("\u8fd4\u56de\u4e0a\u4e00\u9875");
                } else if (this.httpSetting.getAlertErrorDialogType() == 3) {
                    httpDialogController.setSynchronizHTTP(false);
                    httpDialogController.setPositiveButton("\u8bbe\u7f6e\u7f51\u7edc");
                    httpDialogController.setNegativeButton("\u53d6\u6d88");
                }
            } else {
                httpDialogController.setPositiveButton("\u91cd\u8bd5");
                httpDialogController.setNegativeButton("\u786e\u5b9a");
            }
            notifyUser(httpDialogController);
            return;
        }
        this.listener.sendError();
    }

    private void notifyUser(final HttpDialogController httpDialogController) {
        String myActivityTag;
        Activity myActivity;
        final ArrayList<HttpGroup.HttpErrorAlertListener> arrayList;
        if (this.httpSetting.showDialogOnTopWindow()) {
            myActivity = JDHttpTookit.getEngine().getAppProxy().getCurrentMyActivity();
            myActivityTag = myActivity != null ? myActivity.toString() : "";
        } else {
            myActivityTag = this.httpGroupSetting.getMyActivityTag();
            myActivity = this.httpGroupSetting.getMyActivity();
        }
        final String str = myActivityTag;
        boolean isStop = this.httpRequest.isStop();
        if (myActivity != null && !isStop) {
            boolean z = false;
            HashMap<String, ArrayList<HttpGroup.HttpErrorAlertListener>> hashMap = alertDialogStateMap;
            synchronized (hashMap) {
                ArrayList<HttpGroup.HttpErrorAlertListener> arrayList2 = hashMap.get(str);
                if (arrayList2 == null) {
                    ArrayList<HttpGroup.HttpErrorAlertListener> arrayList3 = new ArrayList<>();
                    hashMap.put(str, arrayList3);
                    arrayList = arrayList3;
                    z = true;
                } else {
                    arrayList = arrayList2;
                }
                arrayList.add(this.listener);
            }
            if (OKLog.D) {
                OKLog.d("HttpGroup", "id:" + this.httpSetting.getId() + "- notifyUser() -->> result = " + z);
            }
            if (z) {
                final Activity activity = myActivity;
                myActivity.runOnUiThread(new Runnable() { // from class: com.jingdong.common.network.HttpErrorAlertController.2
                    @Override // java.lang.Runnable
                    public void run() {
                        httpDialogController.init(arrayList, activity, str, JDHttpTookit.getEngine().getHttpErrorDialogImpl());
                        if (HttpErrorAlertController.this.httpRequest.isStop()) {
                            return;
                        }
                        httpDialogController.show();
                    }
                });
                return;
            }
            return;
        }
        this.listener.sendError();
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.HttpErrorAlertControllerInterface
    public void throwError(final HttpError httpError, final HttpGroup.HttpErrorAlertListener httpErrorAlertListener) {
        if (httpErrorAlertListener != null) {
            this.listener = httpErrorAlertListener;
            if (OKLog.I) {
                OKLog.i("HttpGroup", "id:" + this.httpSetting.getId() + "- HttpError -->> " + httpError);
            }
            if (httpError.getErrorCode() == 33) {
                JDHttpTookit.getEngine().getLoginUserControllerImpl().logoutOnlineInfo(new ILoginUserController.ILoginStateChecker() { // from class: com.jingdong.common.network.HttpErrorAlertController.1
                    @Override // com.jingdong.jdsdk.network.dependency.ILoginUserController.ILoginStateChecker
                    public void onFailure() {
                        HttpErrorAlertController.this.alertErrorDialog(httpError);
                    }

                    @Override // com.jingdong.jdsdk.network.dependency.ILoginUserController.ILoginStateChecker
                    public void onSuccess() {
                        httpErrorAlertListener.sendError();
                    }
                });
                return;
            } else {
                alertErrorDialog(httpError);
                return;
            }
        }
        throw new IllegalArgumentException("HttpErrorAlertListener is not null");
    }
}
