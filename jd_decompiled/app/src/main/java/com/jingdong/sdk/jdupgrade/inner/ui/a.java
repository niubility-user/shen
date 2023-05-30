package com.jingdong.sdk.jdupgrade.inner.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.jingdong.sdk.jdupgrade.DownloadView;
import com.jingdong.sdk.jdupgrade.R;

/* loaded from: classes7.dex */
public class a extends DownloadView implements View.OnClickListener {
    private TextView a;
    private ProgressBar b;

    /* renamed from: c */
    private ImageButton f15129c;

    @Override // com.jingdong.sdk.jdupgrade.DownloadView
    public boolean isInstallView() {
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.upgrade_retry) {
            retry();
        }
    }

    @Override // com.jingdong.sdk.jdupgrade.DownloadView, com.jingdong.sdk.jdupgrade.a.b
    public View onCreateView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.upgrade_download_dialog_layout, (ViewGroup) null);
        this.a = (TextView) inflate.findViewById(R.id.upgrade_description);
        this.b = (ProgressBar) inflate.findViewById(R.id.download_process);
        Drawable z = com.jingdong.sdk.jdupgrade.a.c.z();
        if (z != null) {
            try {
                this.b.setProgressDrawable(z);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        this.f15129c = (ImageButton) inflate.findViewById(R.id.upgrade_retry);
        return inflate;
    }

    @Override // com.jingdong.sdk.jdupgrade.DownloadView
    public void onDownloadError(String str) {
        this.a.setText(com.jingdong.sdk.jdupgrade.a.c.j().getString(R.string.upgrade_download_fail) + "(" + str + ")");
        if (this.b.getVisibility() != 8) {
            this.b.setVisibility(8);
        }
        if (this.f15129c.getVisibility() != 0) {
            this.f15129c.setVisibility(0);
        }
        this.f15129c.setOnClickListener(this);
    }

    @Override // com.jingdong.sdk.jdupgrade.DownloadView
    public void onDownloadProgress(int i2) {
        String v = com.jingdong.sdk.jdupgrade.a.c.v();
        if (TextUtils.isEmpty(v)) {
            this.a.setText(R.string.upgrade_downloading_force_view_tips);
        } else {
            this.a.setText(v);
        }
        if (this.f15129c.getVisibility() != 8) {
            this.f15129c.setVisibility(8);
        }
        if (this.b.getVisibility() != 0) {
            this.b.setVisibility(0);
        }
        this.b.setProgress(i2);
    }

    @Override // com.jingdong.sdk.jdupgrade.DownloadView
    public void onDownloadRetry(int i2) {
        String v = com.jingdong.sdk.jdupgrade.a.c.v();
        if (TextUtils.isEmpty(v)) {
            this.a.setText(R.string.upgrade_retrying);
        } else {
            this.a.setText(v);
        }
        if (this.f15129c.getVisibility() != 8) {
            this.f15129c.setVisibility(8);
        }
        if (this.b.getVisibility() != 0) {
            this.b.setVisibility(0);
        }
    }

    @Override // com.jingdong.sdk.jdupgrade.DownloadView
    public void onDownloadStart() {
        String v = com.jingdong.sdk.jdupgrade.a.c.v();
        if (TextUtils.isEmpty(v)) {
            this.a.setText(R.string.upgrade_downloading_force_view_tips);
        } else {
            this.a.setText(v);
        }
        if (this.f15129c.getVisibility() != 8) {
            this.f15129c.setVisibility(8);
        }
        if (this.b.getVisibility() != 0) {
            this.b.setVisibility(0);
        }
    }

    @Override // com.jingdong.sdk.jdupgrade.DownloadView
    public void onDownloadSuccess(String str) {
        install(str);
    }
}
