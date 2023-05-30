package com.jingdong.app.mall.home.floor.view.special;

import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.ui.JDDrawableCheckBox;
import com.jingdong.jdsdk.utils.DPIUtil;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class a {
    private PopupWindow a;

    /* renamed from: com.jingdong.app.mall.home.floor.view.special.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    class C0302a implements PopupWindow.OnDismissListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ BaseActivity f9988g;

        C0302a(BaseActivity baseActivity) {
            this.f9988g = baseActivity;
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            this.f9988g.getWindow().clearFlags(2);
            a.this.d(1.0f, this.f9988g);
            a.this.a = null;
        }
    }

    /* loaded from: classes4.dex */
    class b implements CompoundButton.OnCheckedChangeListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ d f9990g;

        b(d dVar) {
            this.f9990g = dVar;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (z) {
                a.this.a.dismiss();
                Object tag = compoundButton.getTag();
                if (tag instanceof String) {
                    this.f9990g.onUserClose((String) tag);
                }
            }
        }
    }

    /* loaded from: classes4.dex */
    private static class c {
        private static final a a = new a(null);
    }

    /* loaded from: classes4.dex */
    public interface d {
        void onUserClose(String str);
    }

    /* synthetic */ a(C0302a c0302a) {
        this();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(float f2, BaseActivity baseActivity) {
        WindowManager.LayoutParams attributes = baseActivity.getWindow().getAttributes();
        attributes.alpha = f2;
        baseActivity.getWindow().setAttributes(attributes);
    }

    public static a f() {
        return c.a;
    }

    public int[] e(View view, FrameLayout frameLayout) {
        View findViewById = frameLayout.findViewById(R.id.up_arrow);
        View findViewById2 = frameLayout.findViewById(R.id.down_arrow);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        view.getLocationOnScreen(iArr2);
        int height = view.getHeight();
        int width = view.getWidth();
        int height2 = DPIUtil.getHeight();
        int width2 = DPIUtil.getWidth();
        frameLayout.measure(0, 0);
        int measuredHeight = frameLayout.getMeasuredHeight();
        int measuredWidth = frameLayout.getMeasuredWidth();
        int dip2px = DPIUtil.dip2px(18.0f);
        if ((height2 - iArr2[1]) - height < measuredHeight) {
            findViewById.setVisibility(4);
            findViewById2.setVisibility(0);
            iArr[0] = (width2 - measuredWidth) - Math.abs(dip2px - ((width2 - iArr2[0]) - (width / 2)));
            iArr[1] = (iArr2[1] - measuredHeight) + DPIUtil.dip2px(8.0f);
        } else {
            findViewById.setVisibility(0);
            findViewById2.setVisibility(4);
            iArr[0] = (width2 - measuredWidth) - Math.abs(dip2px - ((width2 - iArr2[0]) - (width / 2)));
            iArr[1] = (iArr2[1] + height) - DPIUtil.dip2px(10.0f);
        }
        return iArr;
    }

    public void g(BaseActivity baseActivity, View view, String[] strArr, d dVar) {
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(view.getContext()).inflate(R.layout.home_feedback_popwindow, (ViewGroup) null);
        ArrayList arrayList = new ArrayList();
        arrayList.add((JDDrawableCheckBox) frameLayout.findViewById(R.id.home_dislike_c1));
        arrayList.add((JDDrawableCheckBox) frameLayout.findViewById(R.id.home_dislike_c2));
        arrayList.add((JDDrawableCheckBox) frameLayout.findViewById(R.id.home_dislike_c3));
        PopupWindow popupWindow = this.a;
        if (popupWindow != null && popupWindow.isShowing()) {
            this.a.dismiss();
        }
        PopupWindow popupWindow2 = new PopupWindow((View) frameLayout, DPIUtil.dip2px(170.0f), -2, false);
        this.a = popupWindow2;
        popupWindow2.setOnDismissListener(new C0302a(baseActivity));
        if (strArr != null && strArr.length > 0) {
            int min = Math.min(arrayList.size(), strArr.length);
            for (int i2 = 0; i2 < min; i2++) {
                JDDrawableCheckBox jDDrawableCheckBox = (JDDrawableCheckBox) arrayList.get(i2);
                jDDrawableCheckBox.setText(strArr[i2]);
                jDDrawableCheckBox.setTag(strArr[i2]);
                jDDrawableCheckBox.setVisibility(0);
                jDDrawableCheckBox.setOnCheckedChangeListener(new b(dVar));
            }
        }
        this.a.setBackgroundDrawable(new ColorDrawable());
        this.a.setOutsideTouchable(true);
        this.a.setFocusable(true);
        this.a.setAnimationStyle(R.style.popwin_anim_alpha_style);
        int[] e2 = e(view, frameLayout);
        this.a.showAtLocation(view, 8388659, e2[0], e2[1]);
        baseActivity.getWindow().addFlags(2);
        d(0.8f, baseActivity);
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        PopupWindow popupWindow;
        String type = baseEvent.getType();
        type.hashCode();
        if (type.equals("home_stop") && (popupWindow = this.a) != null && popupWindow.isShowing()) {
            this.a.dismiss();
        }
    }

    private a() {
        f.G0(this);
    }
}
