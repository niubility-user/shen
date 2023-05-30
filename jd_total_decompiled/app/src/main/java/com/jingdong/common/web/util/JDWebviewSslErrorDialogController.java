package com.jingdong.common.web.util;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.ui.JDListDialog;
import com.jingdong.common.ui.ListDialogEntity;
import com.jingdong.common.web.R;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import java.lang.ref.SoftReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes12.dex */
public class JDWebviewSslErrorDialogController {
    private static final String TAG = "JDWebviewSslErrorDialogController";
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    private static List<SoftReference<JDDialog>> sDialogRefList = Collections.synchronizedList(new ArrayList());
    private BaseActivity mActivity;
    private JDDialog mDetailDialog;
    private String mErrorUrl;
    private String mExtraInfo;
    private JDDialog mMainDialog;
    private SslError mSslError;
    private SslErrorHandler mSslErrorHandler;
    private String mUniqueFlag;

    public JDWebviewSslErrorDialogController(Context context) {
        String str;
        if (context != null && (context instanceof BaseActivity)) {
            this.mActivity = (BaseActivity) context;
            return;
        }
        if (this.mActivity == null) {
            str = "null==mActivity";
        } else {
            str = " mActivity: " + this.mActivity.getClass().toString();
        }
        ExceptionReporter.reportWebViewCommonError("JDWebviewSslErrorDialogController_Error", "", str, "");
    }

    private boolean checkBaseActivityIsRunning() {
        BaseActivity baseActivity = this.mActivity;
        return (baseActivity == null || baseActivity.isFinishing()) ? false : true;
    }

    private static void clearAllReference(List<SoftReference<JDDialog>> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Iterator<SoftReference<JDDialog>> it = list.iterator();
        while (it.hasNext()) {
            SoftReference<JDDialog> next = it.next();
            if (next != null) {
                next.clear();
            }
            it.remove();
        }
        list.clear();
    }

    private static void clearReference(JDDialog jDDialog) {
        List<SoftReference<JDDialog>> list = sDialogRefList;
        if (list == null || list.isEmpty() || jDDialog == null) {
            return;
        }
        Iterator<SoftReference<JDDialog>> it = sDialogRefList.iterator();
        while (it.hasNext()) {
            SoftReference<JDDialog> next = it.next();
            if (next != null && next.get() == jDDialog) {
                next.clear();
                it.remove();
                return;
            }
        }
    }

    public static void closeAllDialogs() {
        List<SoftReference<JDDialog>> list = sDialogRefList;
        if (list != null && !list.isEmpty()) {
            try {
                Iterator<SoftReference<JDDialog>> it = sDialogRefList.iterator();
                while (it.hasNext()) {
                    SoftReference<JDDialog> next = it.next();
                    JDDialog jDDialog = next != null ? next.get() : null;
                    if (jDDialog != null && jDDialog.isShowing()) {
                        jDDialog.dismiss();
                    }
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2.getMessage(), e2);
                }
            }
        }
        clearAllReference(sDialogRefList);
    }

    private List<ListDialogEntity> createDialogData() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ListDialogEntity("\u9881\u53d1\u7ed9:", this.mSslError.getCertificate() == null ? "unKnown" : this.mSslError.getCertificate().getIssuedTo().getCName()));
        arrayList.add(new ListDialogEntity("\u9881\u53d1\u8005:", this.mSslError.getCertificate() == null ? "unKnown" : this.mSslError.getCertificate().getIssuedBy().getCName()));
        StringBuilder sb = new StringBuilder(this.mSslError.getCertificate() == null ? "unKnown" : formatDate(this.mSslError.getCertificate().getValidNotBeforeDate()));
        sb.append("\u81f3");
        sb.append(this.mSslError.getCertificate() != null ? formatDate(this.mSslError.getCertificate().getValidNotAfterDate()) : "unKnown");
        arrayList.add(new ListDialogEntity("\u6709\u6548\u671f:", sb.toString()));
        arrayList.add(new ListDialogEntity("\u5176\u4ed6:", this.mErrorUrl));
        return arrayList;
    }

    private String formatDate(Date date) {
        try {
            return dateFormat.format(date);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, "JDWebviewSslErrorDialogController-->formatDate   exception: " + e2);
            }
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void safelyDismissDialog(JDDialog jDDialog) {
        clearReference(jDDialog);
        if (checkBaseActivityIsRunning() && isDialogShowing(jDDialog)) {
            jDDialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void safelyHideDialog(JDDialog jDDialog) {
        if (checkBaseActivityIsRunning() && isDialogShowing(jDDialog)) {
            jDDialog.hide();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void safelyShowDialog(JDDialog jDDialog) {
        if (checkBaseActivityIsRunning()) {
            jDDialog.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryShowDetailDialog() {
        if (!checkBaseActivityIsRunning() || this.mSslError == null) {
            return;
        }
        if (this.mDetailDialog == null) {
            JDListDialog createJdDialogWithStyle8 = JDDialogFactory.getInstance().createJdDialogWithStyle8(this.mActivity, "\u8bc1\u4e66\u8be6\u60c5", createDialogData(), "\u786e\u5b9a");
            this.mDetailDialog = createJdDialogWithStyle8;
            createJdDialogWithStyle8.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.web.util.JDWebviewSslErrorDialogController.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    JDWebviewSslErrorDialogController jDWebviewSslErrorDialogController = JDWebviewSslErrorDialogController.this;
                    jDWebviewSslErrorDialogController.safelyHideDialog(jDWebviewSslErrorDialogController.mDetailDialog);
                    JDWebviewSslErrorDialogController jDWebviewSslErrorDialogController2 = JDWebviewSslErrorDialogController.this;
                    jDWebviewSslErrorDialogController2.safelyShowDialog(jDWebviewSslErrorDialogController2.mMainDialog);
                }
            });
            sDialogRefList.add(new SoftReference<>(this.mDetailDialog));
        }
        safelyHideDialog(this.mMainDialog);
        safelyShowDialog(this.mDetailDialog);
    }

    private void tryShowMainDialog() {
        if (!checkBaseActivityIsRunning() || this.mSslError == null) {
            return;
        }
        if (this.mMainDialog == null) {
            TextView textView = new TextView(this.mActivity);
            textView.setText("\u67e5\u770b\u8bc1\u4e66\u8be6\u60c5 >");
            textView.setTextColor(this.mActivity.getResources().getColor(R.color.c_232326));
            textView.setTextSize(0, this.mActivity.getResources().getDimension(R.dimen.base_ui_jd_dialog_content_size));
            JDDialog createJdDialogWithStyle9 = JDDialogFactory.getInstance().createJdDialogWithStyle9(this.mActivity, "", "\u5f53\u524d\u7f51\u7ad9\u8bc1\u4e66\u4e0d\u53ef\u4fe1\u4e14\u8bc1\u4e66\u94fe\u957f\u5ea6\u4e3a1\uff0c\u53ef\u80fd\u662f\u670d\u52a1\u5668\u6ca1\u6709\u914d\u7f6e\u5b8c\u6574\u8bc1\u4e66\u94fe\uff0c\u662f\u5426\u4fe1\u4efb\u5e76\u7ee7\u7eed\u8bbf\u95ee\uff1f", textView, "\u53d6\u6d88", "\u7ee7\u7eed\u8bbf\u95ee");
            this.mMainDialog = createJdDialogWithStyle9;
            createJdDialogWithStyle9.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.web.util.JDWebviewSslErrorDialogController.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (JDWebviewSslErrorDialogController.this.mSslErrorHandler != null) {
                        JDWebviewSslErrorDialogController.this.mSslErrorHandler.cancel();
                    }
                    JDWebviewSslErrorDialogController jDWebviewSslErrorDialogController = JDWebviewSslErrorDialogController.this;
                    jDWebviewSslErrorDialogController.safelyDismissDialog(jDWebviewSslErrorDialogController.mMainDialog);
                    JDWebviewSslErrorDialogController jDWebviewSslErrorDialogController2 = JDWebviewSslErrorDialogController.this;
                    jDWebviewSslErrorDialogController2.safelyDismissDialog(jDWebviewSslErrorDialogController2.mDetailDialog);
                }
            });
            this.mMainDialog.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.web.util.JDWebviewSslErrorDialogController.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (JDWebviewSslErrorDialogController.this.mSslErrorHandler != null) {
                        JDWebviewSslErrorDialogController.this.mSslErrorHandler.proceed();
                    }
                    JDWebviewSslErrorDialogController jDWebviewSslErrorDialogController = JDWebviewSslErrorDialogController.this;
                    jDWebviewSslErrorDialogController.safelyDismissDialog(jDWebviewSslErrorDialogController.mMainDialog);
                    JDWebviewSslErrorDialogController jDWebviewSslErrorDialogController2 = JDWebviewSslErrorDialogController.this;
                    jDWebviewSslErrorDialogController2.safelyDismissDialog(jDWebviewSslErrorDialogController2.mDetailDialog);
                }
            });
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.web.util.JDWebviewSslErrorDialogController.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    JDWebviewSslErrorDialogController jDWebviewSslErrorDialogController = JDWebviewSslErrorDialogController.this;
                    jDWebviewSslErrorDialogController.safelyHideDialog(jDWebviewSslErrorDialogController.mMainDialog);
                    JDWebviewSslErrorDialogController.this.tryShowDetailDialog();
                }
            });
            sDialogRefList.add(new SoftReference<>(this.mMainDialog));
        }
        safelyShowDialog(this.mMainDialog);
    }

    public void configData(SslError sslError, SslErrorHandler sslErrorHandler, String str, String str2) {
        if (checkBaseActivityIsRunning() && this.mMainDialog == null && this.mDetailDialog == null) {
            this.mSslError = sslError;
            this.mExtraInfo = str2;
            this.mSslErrorHandler = sslErrorHandler;
            this.mErrorUrl = str;
            tryShowMainDialog();
        } else if (OKLog.D) {
            OKLog.d(TAG, "JDWebviewSslErrorDialogController-->configData   neglect sslError : " + sslError);
        }
    }

    public boolean isDialogShowing(JDDialog jDDialog) {
        return jDDialog != null && jDDialog.isShowing();
    }
}
