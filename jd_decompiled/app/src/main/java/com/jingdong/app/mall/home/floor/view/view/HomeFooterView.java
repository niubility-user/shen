package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.h;
import com.jingdong.app.mall.home.floor.view.view.title.HomeTitleFactory;
import com.jingdong.app.mall.home.o.a.b;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.l;
import com.jingdong.common.utils.ImageUtil;

/* loaded from: classes4.dex */
public class HomeFooterView extends LinearLayout {
    public static final int FOOTER_NODATA = 3;
    public static final int FOOTER_NONE = 4;
    private int footerState;
    private LinearLayout noDataLayout;
    private RetryListener retryListener;

    /* loaded from: classes4.dex */
    public interface RetryListener {
        void emptyRetry();
    }

    public HomeFooterView(Context context) {
        super(context);
        try {
            ImageUtil.inflate(R.layout.home_product_footer, this);
            initFooter();
        } catch (Throwable th) {
            l.j(th);
        }
    }

    public void footerStateChange(int i2) {
        if (i2 == 3) {
            this.noDataLayout.setVisibility(0);
        } else if (i2 != 4) {
        } else {
            this.noDataLayout.setVisibility(8);
        }
    }

    private void initFooter() {
        setGravity(17);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.loading_error_tips_layout);
        this.noDataLayout = linearLayout;
        linearLayout.setBackgroundColor(0);
        this.noDataLayout.findViewById(R.id.jd_tip_image).setBackgroundResource(R.drawable.y_03);
        this.noDataLayout.setPadding(0, HomeTitleFactory.getDefaultHeight() + h.A, 0, 0);
        this.noDataLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, d.f9278f - m.s()));
        ((TextView) this.noDataLayout.findViewById(R.id.jd_tip_tv1)).setText(R.string.cart_error_fail);
        ((TextView) this.noDataLayout.findViewById(R.id.jd_tip_tv2)).setText(R.string.cart_error_fail_check);
        Button button = (Button) this.noDataLayout.findViewById(R.id.jd_tip_button);
        button.setText(R.string.loading_error_again);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.HomeFooterView.1
            {
                HomeFooterView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (HomeFooterView.this.retryListener != null) {
                    HomeFooterView.this.retryListener.emptyRetry();
                }
            }
        });
    }

    public void notifyHeightChanged(int i2) {
        if (i2 < (d.f9278f >> 1) || this.noDataLayout == null) {
            return;
        }
        this.noDataLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, i2));
    }

    public void setFooterState(final int i2) {
        this.footerState = i2;
        f.E0(new b() { // from class: com.jingdong.app.mall.home.floor.view.view.HomeFooterView.2
            {
                HomeFooterView.this = this;
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            public void safeRun() {
                HomeFooterView.this.footerStateChange(i2);
            }
        });
    }

    public void setRetryListener(RetryListener retryListener) {
        this.retryListener = retryListener;
    }
}
