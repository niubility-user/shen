package com.jingdong.app.mall.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Process;
import android.view.View;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.jdsdk.JdSdk;
import java.io.FileInputStream;

/* loaded from: classes4.dex */
class a extends c {
    private boolean d;

    /* renamed from: com.jingdong.app.mall.main.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    class RunnableC0355a implements Runnable {

        /* renamed from: com.jingdong.app.mall.main.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        class ViewOnClickListenerC0356a implements View.OnClickListener {
            ViewOnClickListenerC0356a(RunnableC0355a runnableC0355a) {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Process.killProcess(Process.myPid());
            }
        }

        /* renamed from: com.jingdong.app.mall.main.a$a$b */
        /* loaded from: classes4.dex */
        class b implements View.OnClickListener {
            b(RunnableC0355a runnableC0355a) {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setFlags(268435456);
                intent.setData(Uri.parse("https://h5.m.jd.com/active/download/download.html?channel=jd-ldxf01"));
                com.jingdong.app.mall.safemode.a.a(JdSdk.getInstance().getApplication(), intent, "\u8df3\u8f6c\u5931\u8d25\uff0c\u5efa\u8bae\u60a8\u5230\u5e94\u7528\u5e02\u573a\u4e0b\u8f7d\u6700\u65b0\u7248\u4eac\u4e1c\uff0e");
            }
        }

        RunnableC0355a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            JDDialog createJdDialogWithStyle2 = JDDialogFactory.getInstance().createJdDialogWithStyle2(a.this.a, "\u60a8\u6b63\u5728\u4f7f\u7528\u7684\u4eac\u4e1c\u5ba2\u6237\u7aef\u5df2\u88ab\u6076\u610f\u7be1\u6539\uff0c\u8bf7\u901a\u8fc7\u5b98\u65b9\u6e20\u9053\u91cd\u65b0\u4e0b\u8f7d\u5b89\u88c5", "\u53d6\u6d88", "\u53bb\u4e0b\u8f7d");
            createJdDialogWithStyle2.setOnLeftButtonClickListener(new ViewOnClickListenerC0356a(this));
            createJdDialogWithStyle2.setOnRightButtonClickListener(new b(this));
            createJdDialogWithStyle2.setCancelable(false);
            createJdDialogWithStyle2.setCanceledOnTouchOutside(false);
            createJdDialogWithStyle2.show();
        }
    }

    public a(Activity activity, c cVar) {
        super(activity, cVar);
        this.d = true;
    }

    @Override // com.jingdong.app.mall.main.c
    public void b() {
        Activity activity;
        if (this.d && "1".equals(ConfigUtil.getStringFromPreference("apkCheck")) && f(JdSdk.getInstance().getApplication()) && (activity = this.a) != null && !activity.isFinishing()) {
            this.a.runOnUiThread(new RunnableC0355a());
        } else {
            c();
        }
    }

    public boolean f(Context context) {
        FileInputStream fileInputStream;
        boolean z = false;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(context.getPackageResourcePath());
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
        }
        try {
            byte[] bArr = new byte[4];
            fileInputStream.read(bArr, 0, 4);
            StringBuilder sb = new StringBuilder("");
            for (int i2 = 0; i2 < 4; i2++) {
                String hexString = Integer.toHexString(bArr[i2] & 255);
                if (hexString.length() < 2) {
                    sb.append(0);
                }
                sb.append(hexString);
            }
            z = !sb.toString().equals("504b0304");
            fileInputStream.close();
        } catch (Throwable unused3) {
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            return z;
        }
        return z;
    }
}
