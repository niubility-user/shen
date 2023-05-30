package com.jingdong.sdk.jshopsdk.common.favo;

import android.app.Activity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jd.dynamic.DYConstants;
import com.jingdong.sdk.jshopsdk.R;

/* loaded from: classes7.dex */
public class JShopFavToast extends JShopAnimationToast {
    private TextView tvJShopFavToastMsg;

    public JShopFavToast(Activity activity) {
        super(activity);
    }

    @Override // com.jingdong.sdk.jshopsdk.common.favo.JShopAnimationToast
    protected View createContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.jshop_fav_toast, viewGroup, false);
        this.mPushUpView = inflate.findViewById(R.id.lyContent);
        this.tvJShopFavToastMsg = (TextView) inflate.findViewById(R.id.tvJShopFavToastMsg);
        return inflate;
    }

    @Override // com.jingdong.sdk.jshopsdk.common.favo.JShopAnimationToast
    protected int getPushUpStayDuration() {
        return 1000;
    }

    @Override // com.jingdong.sdk.jshopsdk.common.favo.JShopAnimationToast
    protected int getWindowAnimatino() {
        return R.style.jshop_fav_windows_anim;
    }

    public void show(String str, String str2) {
        SparseArray sparseArray = new SparseArray();
        sparseArray.put(0, str);
        sparseArray.put(1, str2);
        addShowTask(sparseArray);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.sdk.jshopsdk.common.favo.JShopAnimationToast
    public void showTask(Object obj) {
        if (obj instanceof SparseArray) {
            super.showTask(obj);
            SparseArray sparseArray = (SparseArray) obj;
            String str = (String) sparseArray.get(0);
            DYConstants.DY_NULL_STR.equals((String) sparseArray.get(1));
            TextView textView = this.tvJShopFavToastMsg;
            if (textView != null) {
                textView.setText(str);
            }
        }
    }
}
