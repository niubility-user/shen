package com.jd.lib.productdetail.tradein.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.result.TradeInResultData;
import java.util.List;

/* loaded from: classes16.dex */
public class TradeInOldItem extends ConstraintLayout {

    /* renamed from: g  reason: collision with root package name */
    public TextView f5636g;

    /* renamed from: h  reason: collision with root package name */
    public TextView f5637h;

    public TradeInOldItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(TradeInResultData.OldContentList oldContentList) {
        if (oldContentList != null) {
            this.f5636g.setText(oldContentList.title);
            List<String> list = oldContentList.textList;
            if (list == null || list.isEmpty()) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < oldContentList.textList.size(); i2++) {
                if (i2 != 0) {
                    sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                }
                sb.append(oldContentList.textList.get(i2));
            }
            this.f5637h.setText(sb.toString());
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f5636g = (TextView) findViewById(R.id.tradein_old_title);
        this.f5637h = (TextView) findViewById(R.id.tradein_old_value);
    }
}
