package com.jingdong.common.web.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.common.web.R;

/* loaded from: classes12.dex */
public class XWebErrView extends RelativeLayout {
    private Button button;
    private ImageView imageView;
    private TextView title1;
    private TextView title2;

    public XWebErrView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.jd_common_state_page_01, this);
        this.imageView = (ImageView) findViewById(R.id.jd_tip_image);
        this.title1 = (TextView) findViewById(R.id.jd_tip_tv1);
        this.title2 = (TextView) findViewById(R.id.jd_tip_tv2);
        this.button = (Button) findViewById(R.id.jd_tip_button);
        this.imageView.setImageResource(R.drawable.y_03);
        this.button.setText(R.string.hybrid_err_reload);
        this.title1.setText(R.string.hybrid_err_title);
        this.title2.setText(R.string.hybrid_err_title2);
    }

    public void setErrCode(int i2) {
    }

    public void setOnButtonClickListener(View.OnClickListener onClickListener) {
        Button button = this.button;
        if (button != null) {
            button.setOnClickListener(onClickListener);
        }
    }

    public XWebErrView(Context context) {
        super(context);
        init(context);
    }
}
