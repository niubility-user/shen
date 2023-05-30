package com.tencent.smtt.utils;

import android.content.Context;
import android.os.Looper;
import android.os.Process;
import android.widget.TextView;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* loaded from: classes9.dex */
public class TbsLogClient {
    static TbsLogClient a;

    /* renamed from: c */
    static File f17902c;
    static String d;

    /* renamed from: e */
    static byte[] f17903e;
    TextView b;

    /* renamed from: f */
    private SimpleDateFormat f17904f;

    /* renamed from: g */
    private Context f17905g;

    /* renamed from: h */
    private StringBuffer f17906h = new StringBuffer();

    /* renamed from: i */
    private boolean f17907i = false;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class a implements Runnable {
        String a;

        a(String str) {
            TbsLogClient.this = r1;
            this.a = null;
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            TextView textView = TbsLogClient.this.b;
            if (textView != null) {
                textView.append(this.a + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
        }
    }

    public TbsLogClient(Context context) {
        this.f17904f = null;
        this.f17905g = null;
        try {
            this.f17905g = context.getApplicationContext();
            this.f17904f = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS", Locale.US);
        } catch (Exception unused) {
            this.f17904f = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
        }
    }

    private void a() {
        try {
            if (f17902c == null) {
                String a2 = FileUtil.a(this.f17905g, 6);
                if (a2 == null) {
                    f17902c = null;
                } else {
                    f17902c = new File(a2, "tbslog.txt");
                    d = LogFileUtils.createKey();
                    f17903e = LogFileUtils.createHeaderText(f17902c.getName(), d);
                }
            }
        } catch (NullPointerException e2) {
            e2.printStackTrace();
        } catch (SecurityException e3) {
            e3.printStackTrace();
        }
    }

    public void d(String str, String str2) {
    }

    public void e(String str, String str2) {
    }

    public void i(String str, String str2) {
    }

    public void setLogView(TextView textView) {
        this.b = textView;
    }

    public void setWriteLogJIT(boolean z) {
        this.f17907i = z;
    }

    public void showLog(String str) {
        TextView textView = this.b;
        if (textView != null) {
            textView.post(new a(str));
        }
    }

    public void v(String str, String str2) {
    }

    public void w(String str, String str2) {
    }

    public void writeLog(String str) {
        try {
            StringBuffer stringBuffer = this.f17906h;
            stringBuffer.append(System.currentTimeMillis());
            stringBuffer.append(" pid=");
            stringBuffer.append(Process.myPid());
            stringBuffer.append(" tid=");
            stringBuffer.append(Process.myTid());
            stringBuffer.append(str);
            stringBuffer.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            if (Thread.currentThread() != Looper.getMainLooper().getThread() || this.f17907i) {
                writeLogToDisk();
            }
            if (this.f17906h.length() > 524288) {
                StringBuffer stringBuffer2 = this.f17906h;
                stringBuffer2.delete(0, stringBuffer2.length());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void writeLogToDisk() {
        try {
            a();
            File file = f17902c;
            if (file != null) {
                LogFileUtils.writeDataToStorage(file, d, f17903e, this.f17906h.toString(), true);
                StringBuffer stringBuffer = this.f17906h;
                stringBuffer.delete(0, stringBuffer.length());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
