package com.jingdong.common.widget.popupwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import androidx.core.view.ViewCompat;
import com.jd.lib.un.basewidget.R;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.widget.popupwindow.adapter.CircleButtonLayoutAdapter;
import com.jingdong.common.widget.popupwindow.entity.BaseCircleButtonInfo;
import com.jingdong.common.widget.popupwindow.view.CircleButtonLayout;
import java.util.List;

/* loaded from: classes12.dex */
public class JDLongPressPopupWindow extends PopupWindow {
    public static int CIRCLE_BUTTON_MAX_COUNT = 3;
    public static int CIRCLE_BUTTON_MIN_COUNT = 2;
    private CircleButtonLayout circleButtonLayout;
    private CircleButtonLayoutAdapter circleButtonLayoutAdapter;
    private View viewBackground;

    public JDLongPressPopupWindow(Context context, int i2, CircleButtonLayoutAdapter.CircleButtonOnItemClickListener circleButtonOnItemClickListener) {
        super(DpiUtil.getWidth(context), i2);
        init(context, circleButtonOnItemClickListener);
    }

    private void init(Context context, CircleButtonLayoutAdapter.CircleButtonOnItemClickListener circleButtonOnItemClickListener) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.lib_long_press_popup_window, (ViewGroup) null);
        this.viewBackground = inflate.findViewById(R.id.view_background);
        this.circleButtonLayout = (CircleButtonLayout) inflate.findViewById(R.id.ll_content);
        setContentView(inflate);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(ViewCompat.MEASURED_SIZE_MASK));
        this.viewBackground.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.popupwindow.JDLongPressPopupWindow.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JDLongPressPopupWindow.this.isShowing()) {
                    JDLongPressPopupWindow.this.dismiss();
                }
            }
        });
        CircleButtonLayoutAdapter circleButtonLayoutAdapter = new CircleButtonLayoutAdapter(context, circleButtonOnItemClickListener);
        this.circleButtonLayoutAdapter = circleButtonLayoutAdapter;
        this.circleButtonLayout.setAdapter(circleButtonLayoutAdapter);
    }

    public void setCircleButtons(List<BaseCircleButtonInfo> list) {
        if (list == null || list.isEmpty() || list.size() > CIRCLE_BUTTON_MAX_COUNT || list.size() < CIRCLE_BUTTON_MIN_COUNT) {
            return;
        }
        this.circleButtonLayoutAdapter.bindData(list);
        this.circleButtonLayout.notifyData(this.circleButtonLayoutAdapter);
    }
}
