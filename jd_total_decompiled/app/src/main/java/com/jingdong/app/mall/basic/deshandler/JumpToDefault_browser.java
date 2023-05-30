package com.jingdong.app.mall.basic.deshandler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.mta.JDMtaUtils;

@Des(des = JumpUtil.VALUE_DES_DEFAULT_BROWSER)
/* loaded from: classes19.dex */
public class JumpToDefault_browser extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ JDDialog f8017g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Context f8018h;

        a(JDDialog jDDialog, Context context) {
            this.f8017g = jDDialog;
            this.f8018h = context;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f8017g.dismiss();
            JumpToDefault_browser.this.c(this.f8018h);
        }
    }

    /* loaded from: classes19.dex */
    class b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f8020g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Context f8021h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ JDDialog f8022i;

        b(String str, Context context, JDDialog jDDialog) {
            this.f8020g = str;
            this.f8021h = context;
            this.f8022i = jDDialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent("android.intent.action.VIEW");
            try {
                intent.setData(Uri.parse(this.f8020g));
                this.f8021h.startActivity(intent);
                JDMtaUtils.onClick(this.f8021h, "Discover_ContentGoForLookConfirm", "", "", "");
            } catch (Exception e2) {
                Log.d(JumpToDefault_browser.this.a, "forwardDefaultBrowser error:" + e2.getMessage());
            }
            this.f8022i.dismiss();
            JumpToDefault_browser.this.c(this.f8021h);
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        String string = bundle.getString("msg");
        String string2 = bundle.getString("url");
        if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2) && (context instanceof Activity)) {
            JDDialog createJdDialogWithStyle2 = JDDialogFactory.getInstance().createJdDialogWithStyle2(context, string, context.getResources().getString(R.string.b6), context.getResources().getString(R.string.e3));
            createJdDialogWithStyle2.setOnLeftButtonClickListener(new a(createJdDialogWithStyle2, context));
            createJdDialogWithStyle2.setOnRightButtonClickListener(new b(string2, context, createJdDialogWithStyle2));
            createJdDialogWithStyle2.show();
            return;
        }
        c(context);
    }
}
