package com.jingdong.app.mall.utils.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes4.dex */
public class b extends Dialog {

    /* renamed from: g  reason: collision with root package name */
    private Button f11926g;

    /* renamed from: h  reason: collision with root package name */
    private Button f11927h;

    /* renamed from: i  reason: collision with root package name */
    private TextView f11928i;

    public b(Context context) {
        super(context, R.style.kf);
    }

    public void a(String str, View.OnClickListener onClickListener) {
        if (this.f11926g != null) {
            if (Log.D) {
                Log.d("NewDialog", " initLeftButton -->>in ");
            }
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f11926g.getLayoutParams();
            layoutParams.weight = 1.0f;
            int dip2px = DPIUtil.dip2px(5.0f);
            layoutParams.rightMargin = dip2px;
            layoutParams.leftMargin = dip2px;
            this.f11926g.setText(str);
            this.f11926g.setBackgroundResource(R.drawable.new_item_dialog_cancle_selector);
            this.f11926g.setOnClickListener(onClickListener);
        }
        Button button = this.f11927h;
        if (button != null) {
            button.setVisibility(8);
        }
    }

    public void b(String str) {
        TextView textView = this.f11928i;
        if (textView != null) {
            textView.setText(str);
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Log.D) {
            Log.d("NewDialog", " onCreate -->> ");
        }
        int width = DPIUtil.getWidth();
        View inflate = ImageUtil.inflate(R.layout.cart_dialog_layout, null);
        double d = width;
        Double.isNaN(d);
        setContentView(inflate, new ViewGroup.LayoutParams((int) (d * 0.85d), -2));
        this.f11926g = (Button) findViewById(R.id.cart_dialog_left);
        this.f11927h = (Button) findViewById(R.id.cart_dialog_right);
        this.f11928i = (TextView) findViewById(R.id.cart_dialog_text);
        setCanceledOnTouchOutside(true);
    }
}
