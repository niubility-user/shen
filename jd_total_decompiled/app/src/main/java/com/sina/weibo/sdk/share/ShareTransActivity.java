package com.sina.weibo.sdk.share;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.b.a;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes9.dex */
public class ShareTransActivity extends BaseActivity {
    private Intent t;
    private FrameLayout u;
    private d v;
    private String w;
    private Handler x = new a(Looper.getMainLooper());

    /* loaded from: classes9.dex */
    final class a extends Handler {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Looper looper) {
            super(looper);
            ShareTransActivity.this = r1;
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                Object obj = message.obj;
                if (obj instanceof Intent) {
                    ShareTransActivity.this.b((Intent) obj);
                    return;
                }
            }
            ShareTransActivity.this.j();
        }
    }

    /* loaded from: classes9.dex */
    final class b implements com.sina.weibo.sdk.share.b {
        b() {
            ShareTransActivity.this = r1;
        }

        @Override // com.sina.weibo.sdk.share.b
        public final void a(c cVar) {
            ShareTransActivity.this.u.setVisibility(4);
            if (cVar != null) {
                if (cVar.z) {
                    ShareTransActivity.this.a(cVar.A);
                    return;
                } else if (TextUtils.isEmpty(cVar.errorMessage)) {
                    ShareTransActivity.this.c("Trans resource fail.");
                    return;
                } else {
                    ShareTransActivity.this.c(cVar.errorMessage);
                    return;
                }
            }
            ShareTransActivity.this.c("Trans result is null.");
        }
    }

    private static void c(Intent intent) {
        if (intent == null || intent.getFlags() == 0) {
            return;
        }
        int flags = intent.getFlags();
        String binaryString = Integer.toBinaryString(flags);
        ArrayList arrayList = new ArrayList();
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 19) {
            arrayList.add(64);
        }
        if (i2 >= 21) {
            arrayList.add(128);
        }
        arrayList.add(1);
        arrayList.add(2);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            flags &= ((Integer) it.next()).intValue() ^ (-1);
        }
        com.sina.weibo.sdk.b.c.a("WBShareTag", "clear flags: " + binaryString + "->" + Integer.toBinaryString(flags));
        intent.setFlags(flags);
    }

    public void j() {
        FrameLayout frameLayout = this.u;
        if (frameLayout != null) {
            frameLayout.setVisibility(8);
        }
        Handler handler = this.x;
        if (handler != null) {
            handler.removeMessages(0);
            this.x = null;
        }
        try {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt("_weibo_resp_errcode", 1);
            intent.putExtras(bundle);
            setResult(-1, intent);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        finish();
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        com.sina.weibo.sdk.b.c.a("WBShareTag", "onActivityResult. Means share result coming!");
        Handler handler = this.x;
        if (handler != null) {
            if (i3 == -1) {
                Message obtain = Message.obtain(handler, 1);
                obtain.obj = intent;
                this.x.sendMessageDelayed(obtain, 100L);
                return;
            }
            handler.sendEmptyMessageDelayed(0, 100L);
        }
    }

    @Override // com.sina.weibo.sdk.share.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        View progressBar;
        super.onCreate(bundle);
        com.sina.weibo.sdk.b.c.a("WBShareTag", "start share activity.");
        Intent intent = getIntent();
        this.t = intent;
        if (intent == null) {
            finish();
        } else if (intent.getIntExtra("start_flag", -1) != 1001) {
            finish();
        } else {
            this.u = new FrameLayout(this);
            int intExtra = getIntent().getIntExtra("progress_id", -1);
            if (intExtra != -1) {
                progressBar = ((LayoutInflater) getSystemService("layout_inflater")).inflate(intExtra, (ViewGroup) null);
            } else {
                progressBar = new ProgressBar(this);
            }
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            this.u.addView(progressBar, layoutParams);
            this.u.setBackgroundColor(855638016);
            setContentView(this.u);
            com.sina.weibo.sdk.b.c.a("WBShareTag", "prepare wb resource.");
            Bundle extras = this.t.getExtras();
            if (extras == null) {
                finish();
                return;
            }
            WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
            weiboMultiMessage.readFromBundle(extras);
            if (weiboMultiMessage.multiImageObject == null && weiboMultiMessage.videoSourceObject == null) {
                a(weiboMultiMessage);
                return;
            }
            d dVar = this.v;
            if (dVar != null) {
                dVar.cancel(true);
            }
            d dVar2 = new d(this, new b());
            this.v = dVar2;
            dVar2.execute(weiboMultiMessage);
        }
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        com.sina.weibo.sdk.b.c.a("WBShareTag", "start share activity again. Means share result coming!");
        int intExtra = intent.getIntExtra("start_flag", -1);
        if (intExtra == 1001) {
            return;
        }
        if (intExtra == 1002) {
            b(intent);
        } else {
            j();
        }
    }

    public void b(Intent intent) {
        FrameLayout frameLayout = this.u;
        if (frameLayout != null) {
            frameLayout.setVisibility(4);
        }
        Handler handler = this.x;
        if (handler != null) {
            handler.removeMessages(0);
            this.x = null;
        }
        if (!a(intent)) {
            j();
            return;
        }
        c(intent);
        setResult(-1, intent);
        finish();
    }

    public void a(WeiboMultiMessage weiboMultiMessage) {
        com.sina.weibo.sdk.b.c.a("WBShareTag", "start wb composer");
        try {
            this.t.putExtra("start_flag", 1002);
            double random = Math.random() * 10000.0d;
            double currentTimeMillis = System.currentTimeMillis();
            Double.isNaN(currentTimeMillis);
            String f2 = com.sina.weibo.sdk.b.d.f(String.valueOf(random + currentTimeMillis));
            this.w = f2;
            this.t.putExtra("share_back_flag", f2);
            this.t.putExtra("share_flag_for_new_version", 1);
            Bundle extras = this.t.getExtras();
            Intent intent = new Intent("com.sina.weibo.sdk.action.ACTION_WEIBO_ACTIVITY");
            a.C0775a c2 = com.sina.weibo.sdk.b.a.c(this);
            if (c2 != null) {
                intent.setPackage(c2.packageName);
            }
            intent.putExtras(weiboMultiMessage.writeToBundle(extras));
            intent.putExtra("_weibo_sdkVersion", "0041005000");
            intent.putExtra("_weibo_appPackage", getPackageName());
            intent.putExtra("_weibo_appKey", com.sina.weibo.sdk.a.a().getAppKey());
            intent.putExtra("_weibo_flag", 538116905);
            intent.putExtra("_weibo_sign", com.sina.weibo.sdk.b.d.f(com.sina.weibo.sdk.b.e.b(this, getPackageName())));
            String stringExtra = this.t.getStringExtra("start_web_activity");
            if (!TextUtils.isEmpty(stringExtra) && "com.sina.weibo.sdk.web.WebActivity".equals(stringExtra)) {
                intent.setClassName(this, stringExtra);
                startActivityForResult(intent, 10001);
            } else if (com.sina.weibo.sdk.a.a(this)) {
                if (c2 != null) {
                    intent.setPackage(c2.packageName);
                }
                startActivityForResult(intent, 10001);
            } else {
                c("Start weibo client's composer fail. And Weibo client is not installed.");
            }
        } catch (Throwable th) {
            com.sina.weibo.sdk.b.c.b("WBShareTag", "start wb composer fail," + th.getMessage());
            c("Start weibo client's composer fail. " + th.getMessage());
        }
    }

    public void c(String str) {
        FrameLayout frameLayout = this.u;
        if (frameLayout != null) {
            frameLayout.setVisibility(4);
        }
        Handler handler = this.x;
        if (handler != null) {
            handler.removeMessages(0);
            this.x = null;
        }
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("_weibo_resp_errcode", 2);
        bundle.putString("_weibo_resp_errstr", str);
        intent.putExtras(bundle);
        setResult(-1, intent);
        finish();
    }

    private boolean a(Intent intent) {
        if (TextUtils.isEmpty(this.w) || intent == null || !intent.getExtras().containsKey("share_back_flag")) {
            return false;
        }
        return TextUtils.equals(this.w, intent.getStringExtra("share_back_flag"));
    }
}
