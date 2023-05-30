package com.jingdong.common.unification.uniwidget;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.jd.lib.cashier.sdk.complete.entity.CashierCustomMessage;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.global.GlobalThemeController;
import com.jd.lib.un.global.IThemeChange;
import com.jingdong.common.DpiUtil;

/* loaded from: classes6.dex */
public class CopyPopupWindow extends PopupWindow implements IThemeChange {
    private GlobalThemeController controller;
    private String copyText;

    /* renamed from: tv  reason: collision with root package name */
    private TextView f12448tv;
    private View view;

    public CopyPopupWindow(Context context) {
        super(context);
        init(context);
        initTheme();
    }

    private void init(final Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.lib_copy_popup_window, (ViewGroup) null);
        this.view = inflate;
        this.f12448tv = (TextView) inflate.findViewById(R.id.f5682tv);
        setWidth(DpiUtil.dip2px(context, 105.0f));
        setHeight(DpiUtil.dip2px(context, 54.0f));
        setContentView(this.view);
        setFocusable(false);
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        this.view.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.unification.uniwidget.CopyPopupWindow.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    ((ClipboardManager) context.getSystemService(CashierCustomMessage.KEY.CHANNEL_CLIP_BOARD)).setPrimaryClip(ClipData.newPlainText(null, CopyPopupWindow.this.copyText));
                    Toast.makeText(context, "\u590d\u5236\u6210\u529f", 0).show();
                } catch (Throwable unused) {
                    Toast.makeText(context, "\u60a8\u7684\u64cd\u4f5c\u7cfb\u7edf\u7248\u672c\u592a\u4f4e\uff0c\u6682\u65f6\u4e0d\u652f\u6301\u526a\u5207\u677f", 0).show();
                }
                CopyPopupWindow.this.dismiss();
            }
        });
    }

    private void initTheme() {
        GlobalThemeController newInstance = GlobalThemeController.newInstance();
        this.controller = newInstance;
        if (newInstance.isCustomTheme()) {
            customTheme();
        }
    }

    @Override // com.jd.lib.un.global.IThemeChange
    public void customTheme() {
        TextView textView = this.f12448tv;
        if (textView != null) {
            textView.setTextColor(this.controller.getThemeConfig().e());
        }
    }

    public void setCopyText(String str) {
        this.copyText = str;
    }

    public void show(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        showAtLocation(view, 48, view.getLayoutParams().width, iArr[1] - getHeight());
    }
}
