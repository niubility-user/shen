package com.jingdong.common.listui;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.jingdong.listui.R;

/* loaded from: classes5.dex */
public class ErrorView {
    public static final int DATAERROR = 2;
    public static final int HTTPERROR = 0;
    public static final int NEW_NODATA = 3;
    public static final int NODATA = 1;
    private View.OnClickListener mOnClickListener;

    public View getView(Context context, int i2) {
        View inflate = View.inflate(context, R.layout.listui_errorview, null);
        Button button = (Button) inflate.findViewById(R.id.jd_tip_button);
        button.setText(R.string.listui_reloading);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.jd_tip_image);
        TextView textView = (TextView) inflate.findViewById(R.id.jd_tip_tv1);
        TextView textView2 = (TextView) inflate.findViewById(R.id.jd_tip_tv2);
        if (i2 == 1) {
            textView2.setVisibility(0);
            textView2.setText(R.string.listui_errview_nodata);
            imageView.setImageResource(R.drawable.liui_joy_no_msg_center);
            textView.setVisibility(8);
            button.setVisibility(8);
        } else if (i2 != 3) {
            textView.setVisibility(0);
            textView.setText(R.string.listui_errview_neterror);
            textView2.setVisibility(0);
            textView2.setText(R.string.listui_errview_checksetting);
            imageView.setImageResource(R.drawable.y_03);
            button.setVisibility(0);
            button.setOnClickListener(this.mOnClickListener);
        } else {
            textView2.setVisibility(0);
            textView2.setText(R.string.listui_errview_newnodata);
            imageView.setImageResource(R.drawable.liui_nodata_dog);
            textView.setVisibility(8);
            button.setVisibility(8);
            textView2.setOnClickListener(this.mOnClickListener);
            imageView.setOnClickListener(this.mOnClickListener);
        }
        return inflate;
    }

    public ErrorView setOnBtnListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
        return this;
    }
}
