package com.jingdong.common.widget.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.jingdong.common.R;
import com.jingdong.common.utils.ImageUtil;

/* loaded from: classes12.dex */
public class CustomNetFailLayout extends FrameLayout {
    private View loadingFail;

    public CustomNetFailLayout(Context context) {
        super(context);
    }

    public void closeFail() {
        if (getChildCount() > 0) {
            removeAllViews();
        }
    }

    public void showFail(View.OnClickListener onClickListener, boolean z) {
        showFail(onClickListener);
        View view = this.loadingFail;
        if (view == null || z) {
            return;
        }
        view.setBackgroundColor(0);
    }

    public CustomNetFailLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void showFail(final View.OnClickListener onClickListener) {
        if (this.loadingFail == null) {
            View inflate = ImageUtil.inflate(R.layout.jd_common_state_page_01, null);
            this.loadingFail = inflate;
            final Button button = (Button) inflate.findViewById(R.id.jd_tip_button);
            button.setText(getResources().getString(R.string.reload));
            ((ImageView) this.loadingFail.findViewById(R.id.jd_tip_image)).setBackgroundResource(R.drawable.y_03);
            ((TextView) this.loadingFail.findViewById(R.id.jd_tip_tv1)).setText(getResources().getString(R.string.lib_voice_network_failed));
            ((TextView) this.loadingFail.findViewById(R.id.jd_tip_tv2)).setText(getResources().getString(R.string.lib_voice_network_please_check));
            button.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.CustomNetFailLayout.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    onClickListener.onClick(button);
                }
            });
        }
        closeFail();
        addView(this.loadingFail);
    }
}
