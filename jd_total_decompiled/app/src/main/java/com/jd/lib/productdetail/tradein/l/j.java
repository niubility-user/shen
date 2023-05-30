package com.jd.lib.productdetail.tradein.l;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.jd.lib.productdetail.tradein.R;
import com.jingdong.common.ui.JDDialog;

/* loaded from: classes16.dex */
public class j extends JDDialog {
    public j(Context context) {
        super(context);
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        dismiss();
    }

    public j a(String str, String str2) {
        this.titleView.setText(str);
        this.messageView.setText(str2);
        this.negButton.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.l.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                j.this.c(view);
            }
        });
        return this;
    }

    public final void b() {
        setContentView(R.layout.tradein_widget_dialog_rule);
        this.titleView = (TextView) findViewById(R.id.tradein_widget_dialog_title);
        Button button = (Button) findViewById(R.id.tradein_widget_dialog_neg_button);
        this.negButton = button;
        useCancelClickEvent(button);
        setCanceledOnTouchOutside(true);
        this.messageView = (TextView) findViewById(R.id.tradein_widget_dialog_message_continer);
    }
}
