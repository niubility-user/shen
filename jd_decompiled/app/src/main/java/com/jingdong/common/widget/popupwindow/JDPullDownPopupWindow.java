package com.jingdong.common.widget.popupwindow;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import androidx.core.view.ViewCompat;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.utils.config.UnDeviceInfo;

/* loaded from: classes12.dex */
public class JDPullDownPopupWindow extends PopupWindow {
    private LinearLayout mContentLayout;
    protected Context mContext;
    private View mView;

    public JDPullDownPopupWindow(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.jd_pulldown_popupwindow, (ViewGroup) null);
        this.mView = inflate;
        this.mContentLayout = (LinearLayout) inflate.findViewById(R.id.content_layout);
        setContentView(this.mView);
        setWidth(-1);
        setHeight(-2);
        setAnimationStyle(R.style.popwin_anim_alpha_style);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(ViewCompat.MEASURED_SIZE_MASK));
        this.mView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.popupwindow.JDPullDownPopupWindow.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JDPullDownPopupWindow.this.isShowing()) {
                    JDPullDownPopupWindow.this.dismiss();
                }
            }
        });
        this.mContentLayout.setOnClickListener(null);
    }

    public void addContent(View view) {
        this.mContentLayout.removeAllViews();
        this.mContentLayout.addView(view);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i2, int i3) {
        if (Build.VERSION.SDK_INT == 24) {
            Rect rect = new Rect();
            view.getGlobalVisibleRect(rect);
            setHeight(UnDeviceInfo.getScreenHeight() - rect.bottom);
        }
        super.showAsDropDown(view, i2, i3);
    }

    public void showOrClose(View view, int i2, int i3) {
        if (isShowing()) {
            dismiss();
        } else {
            showAsDropDown(view, i2, i3);
        }
    }
}
