package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.widget.TextView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.r.f.a.k;

/* loaded from: classes4.dex */
public class MallFloorDebug extends BaseMallFloor<k> {
    private final TextView mTextView;

    public MallFloorDebug(Context context) {
        super(context);
        TextView a = new g(context, false).a();
        this.mTextView = a;
        addView(a);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onRefreshViewOnMainThread() {
        super.onRefreshViewOnMainThread();
        int d = d.d(20);
        this.mTextView.setPadding(0, d, 0, d);
        this.mTextView.setText(((k) this.mPresenter).s());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public k createPresenter() {
        return new k();
    }
}
