package com.jingdong.sdk.jshopsdk.common.favo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jingdong.sdk.jshopsdk.R;

/* loaded from: classes7.dex */
public class JShopUnfavToast extends JShopAnimationToast {
    private TextView tvJShopUnfavToastMsg;

    public JShopUnfavToast(Activity activity) {
        super(activity);
    }

    @Override // com.jingdong.sdk.jshopsdk.common.favo.JShopAnimationToast
    protected View createContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.jshop_unfav_toast, viewGroup, false);
        this.tvJShopUnfavToastMsg = (TextView) inflate.findViewById(R.id.tvJShopUnfavToastMsg);
        this.mPushUpView = inflate.findViewById(R.id.lyContent);
        return inflate;
    }

    @Override // com.jingdong.sdk.jshopsdk.common.favo.JShopAnimationToast
    protected int getPushUpStayDuration() {
        return 400;
    }

    @Override // com.jingdong.sdk.jshopsdk.common.favo.JShopAnimationToast
    protected int getWindowAnimatino() {
        return R.style.jshop_unfav_windows_anim;
    }

    public void show(String str) {
        addShowTask(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.sdk.jshopsdk.common.favo.JShopAnimationToast
    public void showTask(Object obj) {
        super.showTask(obj);
        TextView textView = this.tvJShopUnfavToastMsg;
        if (textView != null) {
            textView.setText(obj.toString());
        }
    }
}
