package com.jd.lib.productdetail.tradein.l;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.bean.TradeInTitleInfo;
import com.jingdong.common.ui.JDDialog;

/* loaded from: classes16.dex */
public class g extends JDDialog {
    public g(Context context) {
        super(context);
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        dismiss();
    }

    public g a(TradeInTitleInfo tradeInTitleInfo) {
        this.titleView.setText(tradeInTitleInfo.rulePopupTitle);
        this.messageView.setText(tradeInTitleInfo.rulePopupContent);
        this.negButton.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.l.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                g.this.c(view);
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
