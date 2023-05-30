package com.jingdong.app.mall.update.view;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.update.ApplicationUpgradeHelper;
import com.jingdong.app.mall.utils.ui.seekbar.UpgradeSeekBar;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.jdsdk.utils.FontsUtil;
import de.greenrobot.event.EventBus;

/* loaded from: classes4.dex */
public class c extends JDDialog {

    /* renamed from: g  reason: collision with root package name */
    public LinearLayout f11726g;

    /* renamed from: h  reason: collision with root package name */
    public UpgradeSeekBar f11727h;

    /* renamed from: i  reason: collision with root package name */
    public TextView f11728i;

    /* renamed from: j  reason: collision with root package name */
    public ImageView f11729j;

    /* renamed from: k  reason: collision with root package name */
    private boolean f11730k;

    /* renamed from: l  reason: collision with root package name */
    private com.jingdong.app.mall.update.b f11731l;

    /* renamed from: m  reason: collision with root package name */
    private String f11732m;

    /* renamed from: n  reason: collision with root package name */
    private String f11733n;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements UpgradeSeekBar.a {
        a() {
        }

        @Override // com.jingdong.app.mall.utils.ui.seekbar.UpgradeSeekBar.a
        public void a(float f2) {
            int i2 = (int) (f2 * 100.0f);
            TextView textView = c.this.f11728i;
            if (textView != null) {
                textView.setText(i2 + "%");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (c.this.f11731l != null) {
                c.this.f11731l.a();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.update.view.c$c  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    public class DialogInterfaceOnDismissListenerC0381c implements DialogInterface.OnDismissListener {
        DialogInterfaceOnDismissListenerC0381c(c cVar) {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);
            }
            ApplicationUpgradeHelper.checkDialogIsShowing();
        }
    }

    public c(Context context, String str, String str2) {
        super(context);
        this.f11730k = false;
        this.f11732m = str;
        this.f11733n = str2;
        d();
    }

    private void c(boolean z) {
        this.f11730k = z;
        if (z) {
            this.titleView.setText(this.f11733n);
            this.f11726g.setVisibility(8);
            this.f11729j.setVisibility(0);
            return;
        }
        this.titleView.setText(this.f11732m);
        this.f11726g.setVisibility(0);
        this.f11729j.setVisibility(8);
    }

    private void d() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        setContentView(R.layout.dialog_app_upgrade_progress);
        this.titleView = (TextView) findViewById(R.id.bv);
        this.f11726g = (LinearLayout) findViewById(R.id.ll_progress);
        this.f11727h = (UpgradeSeekBar) findViewById(R.id.upsb_progress);
        TextView textView = (TextView) findViewById(R.id.tv_radio);
        this.f11728i = textView;
        FontsUtil.changeTextFont(textView, 4097);
        this.f11727h.c(new a());
        ImageView imageView = (ImageView) findViewById(R.id.iv_tryagain);
        this.f11729j = imageView;
        imageView.setOnClickListener(new b());
        setOnDismissListener(new DialogInterfaceOnDismissListenerC0381c(this));
        c(false);
    }

    public void b(boolean z) {
        if (this.f11730k != z) {
            c(z);
        }
    }

    public void e(com.jingdong.app.mall.update.b bVar) {
        this.f11731l = bVar;
    }

    public void onEventMainThread(com.jingdong.app.mall.update.a aVar) {
        if (aVar != null) {
            int i2 = aVar.a;
            if (i2 == -1) {
                b(true);
            } else if (i2 == 2) {
                b(false);
                com.jingdong.app.mall.update.b bVar = this.f11731l;
                if (bVar != null) {
                    bVar.downloadFinish();
                }
            } else {
                b(false);
                this.f11727h.b(aVar.b, aVar.f11712c);
            }
        }
    }
}
