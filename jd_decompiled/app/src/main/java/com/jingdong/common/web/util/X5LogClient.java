package com.jingdong.common.web.util;

import android.content.Context;
import android.os.Process;
import android.widget.TextView;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.tencent.smtt.utils.TbsLogClient;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* loaded from: classes12.dex */
public class X5LogClient extends TbsLogClient {
    private SimpleDateFormat dateFormat;
    private int writeLogType;

    public X5LogClient(Context context) {
        super(context);
        this.writeLogType = SwitchQueryFetcher.getSwitchIntValue("x5WriteLogType", 1);
        try {
            this.dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS", Locale.US);
        } catch (Exception unused) {
            this.dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
        }
    }

    @Override // com.tencent.smtt.utils.TbsLogClient
    public void d(String str, String str2) {
        super.d(str, str2);
    }

    @Override // com.tencent.smtt.utils.TbsLogClient
    public void e(String str, String str2) {
        super.e(str, str2);
    }

    @Override // com.tencent.smtt.utils.TbsLogClient
    public void i(String str, String str2) {
        super.i(str, str2);
    }

    @Override // com.tencent.smtt.utils.TbsLogClient
    public void setLogView(TextView textView) {
        super.setLogView(textView);
    }

    @Override // com.tencent.smtt.utils.TbsLogClient
    public void showLog(String str) {
        super.showLog(str);
    }

    @Override // com.tencent.smtt.utils.TbsLogClient
    public void v(String str, String str2) {
        super.v(str, str2);
    }

    @Override // com.tencent.smtt.utils.TbsLogClient
    public void w(String str, String str2) {
        super.w(str, str2);
    }

    @Override // com.tencent.smtt.utils.TbsLogClient
    public void writeLog(String str) {
        if (this.writeLogType != 2) {
            return;
        }
        try {
            WebLogFileUtils.addLog(this.dateFormat.format(Long.valueOf(System.currentTimeMillis())) + " pid=" + Process.myPid() + " tid=" + Process.myTid() + str + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.tencent.smtt.utils.TbsLogClient
    public void writeLogToDisk() {
    }
}
