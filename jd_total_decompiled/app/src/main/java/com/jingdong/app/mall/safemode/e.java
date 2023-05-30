package com.jingdong.app.mall.safemode;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;

/* loaded from: classes4.dex */
public class e extends d {

    /* loaded from: classes4.dex */
    class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ JDDialog f11616g;

        a(e eVar, JDDialog jDDialog) {
            this.f11616g = jDDialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f11616g.dismiss();
        }
    }

    /* loaded from: classes4.dex */
    class b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ JDDialog f11617g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f11618h;

        b(JDDialog jDDialog, String str) {
            this.f11617g = jDDialog;
            this.f11618h = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            g.b("safemodefixing", "safeModeApk");
            i.g().i();
            e.this.f11615g = true;
            this.f11617g.dismiss();
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setFlags(268435456);
            intent.setData(Uri.parse(this.f11618h));
            com.jingdong.app.mall.safemode.a.a(e.this.f11613e.getApplication(), intent, e.this.f11613e.getString(R.string.safemode_activitynotfound));
        }
    }

    public e(Activity activity, TextView textView, TextView textView2, TextView textView3, Button button) {
        this.a = textView;
        this.b = textView2;
        this.f11612c = textView3;
        this.d = button;
        this.f11613e = activity;
    }

    @Override // com.jingdong.app.mall.safemode.d
    public void b(String str) {
        JDDialogFactory jDDialogFactory = JDDialogFactory.getInstance();
        Activity activity = this.f11613e;
        JDDialog createJdDialogWithStyle2 = jDDialogFactory.createJdDialogWithStyle2(activity, activity.getString(R.string.safemode_apk_handler_info), this.f11613e.getString(R.string.safemode_no), this.f11613e.getString(R.string.safemode_yes));
        createJdDialogWithStyle2.setOnLeftButtonClickListener(new a(this, createJdDialogWithStyle2));
        createJdDialogWithStyle2.setOnRightButtonClickListener(new b(createJdDialogWithStyle2, str));
        createJdDialogWithStyle2.show();
    }
}
