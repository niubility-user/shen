package com.jingdong.common.web.entity;

import androidx.annotation.NonNull;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.smtt.export.external.interfaces.ConsoleMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes6.dex */
public class WebLogEntity {
    private final String TAG = "WebLog";
    private volatile StringBuffer jsLogs;
    private SimpleDateFormat sdf;
    private final String url;

    public WebLogEntity(String str) {
        this.url = str;
        try {
            this.sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS", Locale.US);
        } catch (Exception unused) {
            this.sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
        }
    }

    private String getStringFromMsg(long j2, ConsoleMessage consoleMessage) {
        if (consoleMessage == null) {
            return this.sdf.format(new Date(j2)) + ": Null ConsoleMessage";
        }
        Locale locale = Locale.US;
        Object[] objArr = new Object[5];
        objArr[0] = this.sdf.format(new Date(j2));
        objArr[1] = consoleMessage.messageLevel() != null ? consoleMessage.messageLevel().name() : "Null_Lvl";
        objArr[2] = consoleMessage.message();
        objArr[3] = consoleMessage.sourceId();
        objArr[4] = Integer.valueOf(consoleMessage.lineNumber());
        return String.format(locale, "%s [%s]: %s (%s:%d)", objArr);
    }

    public synchronized void addJsLog(ConsoleMessage consoleMessage) {
        addJsLog(System.currentTimeMillis(), consoleMessage);
    }

    public synchronized String getJsLogs() {
        return this.jsLogs != null ? this.jsLogs.toString() : "No JS Log";
    }

    public void reportLogs() {
        if (OKLog.D) {
            OKLog.d("WebLog", "Report JS logs.\n" + toString());
        }
        ExceptionReporter.reportWebPageError("JS_ERR", getJsLogs(), this.url, null);
    }

    @NonNull
    public String toString() {
        return "[" + this.url + "]: \nJS LOG ->  " + getJsLogs() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
    }

    public synchronized void addJsLog(long j2, ConsoleMessage consoleMessage) {
        if (this.jsLogs == null) {
            this.jsLogs = new StringBuffer();
        }
        if (this.jsLogs.length() > 524288) {
            this.jsLogs.delete(0, this.jsLogs.length());
        }
        StringBuffer stringBuffer = this.jsLogs;
        stringBuffer.append(getStringFromMsg(j2, consoleMessage));
        stringBuffer.append(" ||| ");
    }
}
