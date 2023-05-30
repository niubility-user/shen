package com.jingdong.app.mall.main;

import android.app.Activity;
import android.content.DialogInterface;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.network.ExceptionReporterImpl;
import com.jingdong.common.ui.DialogController;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpSettingTool;

/* loaded from: classes4.dex */
class e extends c {
    private boolean d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends DialogController {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ CheckBox f11245g;

        a(CheckBox checkBox) {
            this.f11245g = checkBox;
        }

        @Override // com.jingdong.common.ui.DialogController, android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            e.this.d = false;
            if (i2 == -2) {
                dialogInterface.dismiss();
                BaseFrameUtil.exitAll();
            } else if (i2 != -1) {
            } else {
                if (this.f11245g.isChecked()) {
                    try {
                        CommonBase.putBooleanToPreference(Configuration.HAS_INIT_TIP, Boolean.TRUE);
                    } catch (Exception e2) {
                        if (Log.E) {
                            e2.printStackTrace();
                        }
                    }
                }
                JDMtaUtils.acceptProtocal(true);
                JDMtaUtils.init(e.this.a);
                ExceptionReporter.acceptProtocol(true);
                ExceptionReporter.init(e.this.a, new ExceptionReporterImpl());
                HttpSettingTool.isNetPromptAgree = true;
                dialogInterface.dismiss();
                e.this.c();
            }
        }
    }

    public e(Activity activity, c cVar) {
        super(activity, cVar);
        this.d = false;
    }

    private void g() {
        TextView textView = new TextView(this.a);
        textView.setTextSize(18.0f);
        textView.setTextColor(-1);
        textView.setText(R.string.connect_net_prompt);
        textView.setPadding(0, 0, 0, 20);
        CheckBox checkBox = new CheckBox(this.a);
        checkBox.setText(R.string.no_show_again);
        LinearLayout linearLayout = new LinearLayout(this.a);
        linearLayout.setOrientation(1);
        linearLayout.addView(textView);
        linearLayout.addView(checkBox);
        linearLayout.setPadding(20, 5, 20, 0);
        ScrollView scrollView = new ScrollView(this.a);
        scrollView.addView(linearLayout);
        a aVar = new a(checkBox);
        this.b = aVar;
        aVar.setTitle(this.a.getString(R.string.k0));
        this.b.setPositiveButton(this.a.getString(R.string.ig));
        this.b.setNegativeButton(this.a.getString(R.string.b6));
        this.b.setView(scrollView);
        this.b.setCanceledOnTouchOutside(false);
        this.b.init(this.a);
        this.b.show();
    }

    private boolean h() {
        return !Configuration.getBooleanProperty(Configuration.BEFORE_INIT_TIP).booleanValue() || CommonBase.getBooleanFromPreference(Configuration.HAS_INIT_TIP, Boolean.FALSE).booleanValue();
    }

    @Override // com.jingdong.app.mall.main.c
    public void b() {
        if (h()) {
            c();
        } else if (this.d) {
        } else {
            this.d = true;
            g();
        }
    }
}
