package com.jd.verify.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.jd.verify.CallBack;
import com.jd.verify.View.SlideVerifyButton;
import com.jd.verify.model.IninVerifyInfo;
import verify.jd.com.myverify.R;

/* loaded from: classes18.dex */
public class VerifyView extends FrameLayout implements IView {
    private ClickVerifyButton clickVerifyButton;
    private int currentType;
    private TextView loadButton;
    private Context mContext;
    private SlideVerifyButton slideVerifyButton;

    public VerifyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.verify_view_layout, (ViewGroup) null);
        this.loadButton = (TextView) inflate.findViewById(R.id.button_load);
        this.slideVerifyButton = (SlideVerifyButton) inflate.findViewById(R.id.button_slide);
        ClickVerifyButton clickVerifyButton = (ClickVerifyButton) inflate.findViewById(R.id.button_click);
        this.clickVerifyButton = clickVerifyButton;
        clickVerifyButton.changeBgCircle();
        this.loadButton.setVisibility(0);
        this.clickVerifyButton.setVisibility(8);
        this.slideVerifyButton.setVisibility(8);
        addView(inflate);
    }

    public void check() {
        this.clickVerifyButton.check();
    }

    @Override // com.jd.verify.View.IView
    public void checkFinish(int i2, String str) {
        int i3 = this.currentType;
        if (i3 == 1) {
            this.clickVerifyButton.checkFinish(i2, str);
        } else if (i3 != 2) {
        } else {
            this.slideVerifyButton.checkFinish(i2, str);
        }
    }

    public void enableClick(boolean z) {
        this.clickVerifyButton.setEnabled(z);
    }

    public void resetAndLoad() {
        int i2 = this.currentType;
        if (i2 == 1) {
            this.clickVerifyButton.resetAndLoad();
        } else if (i2 != 2) {
        } else {
            this.slideVerifyButton.resetAndLoad();
        }
    }

    public void resetOut() {
        int i2 = this.currentType;
        if (i2 == 1) {
            this.clickVerifyButton.resetOut();
        } else if (i2 == 2) {
            this.slideVerifyButton.resetOut();
        }
        this.slideVerifyButton.resetOut();
    }

    @Override // com.jd.verify.View.IView
    public void setCurrentType(int i2) {
        this.currentType = i2;
        if (i2 == 1) {
            this.loadButton.setVisibility(8);
            this.clickVerifyButton.setVisibility(0);
            this.slideVerifyButton.setVisibility(8);
        } else if (i2 != 2) {
            this.loadButton.setVisibility(0);
            this.clickVerifyButton.setVisibility(8);
            this.slideVerifyButton.setVisibility(8);
        } else {
            this.loadButton.setVisibility(8);
            this.clickVerifyButton.setVisibility(8);
            this.slideVerifyButton.setVisibility(0);
        }
    }

    @Override // com.jd.verify.View.IView
    public void setDialg(e eVar) {
        this.slideVerifyButton.setDialg(eVar);
        this.clickVerifyButton.setDialg(eVar);
    }

    public void setEnableMove(boolean z) {
        this.slideVerifyButton.setEnableMove(z);
    }

    @Override // com.jd.verify.View.IView
    public void setFinishListener(CallBack callBack) {
        this.slideVerifyButton.setFinishListener(callBack);
        this.clickVerifyButton.setFinishListener(callBack);
    }

    @Override // com.jd.verify.View.IView
    public void setInfo(IninVerifyInfo ininVerifyInfo) {
        this.slideVerifyButton.setInfo(ininVerifyInfo);
        this.clickVerifyButton.setInfo(ininVerifyInfo);
    }

    @Override // com.jd.verify.View.IView
    public void setNotifyListener(com.jd.verify.common.a aVar) {
        this.slideVerifyButton.setNotifyListener(aVar);
        this.clickVerifyButton.setNotifyListener(aVar);
    }

    public void setVerifyClick(View.OnClickListener onClickListener) {
        this.clickVerifyButton.setVerifyClick(onClickListener);
    }

    public void setmSlideStateListener(SlideVerifyButton.SlideStateListener slideStateListener) {
        this.slideVerifyButton.setmSlideStateListener(slideStateListener);
    }
}
