package com.jingdong.common.web;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.view.View;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.ToastUtil;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes.dex */
public class WebDebug {
    public static final String OPENAPP = "openapp";
    public static final String WEB = "webview";
    private static List<WebDebugEntry> data = null;
    private static final String fileName = "jdwebview";
    public static boolean report = SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.DEBUG_ENABLE, false);

    public static String getDataStr() {
        List<WebDebugEntry> list = data;
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<WebDebugEntry> it = data.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next().toString());
            stringBuffer.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        return stringBuffer.toString();
    }

    public static void log(String str, String str2) {
        log(str, str2, null);
    }

    public static boolean shouldReport() {
        return report;
    }

    public static void showDebugDialog(Context context) {
        final JDDialog createJdDialogWithStyle2 = JDDialogFactory.getInstance().createJdDialogWithStyle2(context, getDataStr(), "Cancel", "Save To SDCard");
        createJdDialogWithStyle2.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.web.WebDebug.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDDialog.this.cancel();
            }
        });
        createJdDialogWithStyle2.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.web.WebDebug.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WebDebug.write2SDCard();
                JDDialog.this.cancel();
            }
        });
        createJdDialogWithStyle2.messageView.setTextIsSelectable(true);
        createJdDialogWithStyle2.messageView.setTextSize(8.0f);
        createJdDialogWithStyle2.show();
    }

    public static void write2SDCard() {
        String str;
        if (Build.VERSION.SDK_INT >= 19) {
            str = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + File.separator + fileName;
        } else {
            str = Environment.getExternalStorageDirectory() + File.separator + fileName;
        }
        File file = new File(str);
        if (!file.exists() && !file.mkdirs()) {
            ToastUtil.showToast("\u65e0\u6cd5\u521b\u5efa\u6587\u4ef6\uff01");
            return;
        }
        File file2 = new File(str + File.separator + System.currentTimeMillis() + ".log");
        try {
            FileWriter fileWriter = new FileWriter(file2);
            fileWriter.write(getDataStr());
            fileWriter.close();
            ToastUtil.showToast("\u5df2\u4fdd\u5b58\u5230" + file2.getAbsolutePath() + "\u6587\u4ef6\u4e0b!");
        } catch (IOException e2) {
            ToastUtil.showToast("\u65e0\u6cd5\u5199\u5165\u6587\u4ef6\uff01");
            e2.printStackTrace();
        }
    }

    public static void log(String str, String str2, Object obj) {
        if (report) {
            if (data == null) {
                data = new LinkedList();
            }
            if (data.size() > 1000) {
                data.remove(0);
            }
            data.add(new WebDebugEntry(str, str2, System.currentTimeMillis(), obj));
        }
    }

    public static String getDataStr(String str) {
        List<WebDebugEntry> list = data;
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (WebDebugEntry webDebugEntry : data) {
            if (str != null && str.equals(webDebugEntry.tag)) {
                stringBuffer.append(webDebugEntry.toString());
                stringBuffer.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
        }
        return stringBuffer.toString();
    }
}
