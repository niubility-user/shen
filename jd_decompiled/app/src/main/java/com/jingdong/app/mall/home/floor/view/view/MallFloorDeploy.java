package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.deploy.view.base.b;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.widget.FitTopImage;
import com.jingdong.app.mall.home.n.h.e;
import com.jingdong.app.mall.home.p.b.e.a;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.f.a.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes4.dex */
public class MallFloorDeploy extends BaseMallFloor<d> {
    private GradientDrawable mBgDrawable;
    private String mBgUrl;
    protected FitTopImage mBgView;
    private HashMap<String, b> mCacheParser;
    private com.jingdong.app.mall.home.p.b.e.b mNodeModel;

    public MallFloorDeploy(Context context) {
        super(context);
        this.mCacheParser = new HashMap<>();
        this.mBgUrl = "";
    }

    private void bindBg() {
        if (!this.mNodeModel.f()) {
            m.K(this.mBgView);
            return;
        }
        if (this.mBgDrawable == null) {
            FitTopImage fitTopImage = new FitTopImage(this.mContext);
            this.mBgView = fitTopImage;
            fitTopImage.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        }
        m.b(this, this.mBgView, 0);
        int d = com.jingdong.app.mall.home.floor.common.d.d(12);
        GradientDrawable gradientDrawable = new GradientDrawable();
        this.mBgDrawable = gradientDrawable;
        gradientDrawable.setColor(-1);
        this.mBgDrawable.setCornerRadius(d);
        e.d(this.mBgView, d);
        com.jingdong.app.mall.home.floor.ctrl.e.m(this.mBgView, this.mBgUrl, this.mBgDrawable);
    }

    private void bindNode() {
        b bVar;
        List<a> e2 = this.mNodeModel.e();
        ArrayList<f> b = this.mNodeModel.b();
        int size = e2.size();
        boolean z = this.mCacheParser.size() == b.size();
        if (z) {
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                }
                if (this.mCacheParser.get(e2.get(i2).e(i2)) == null) {
                    z = false;
                    break;
                }
                i2++;
            }
        }
        if (!z) {
            cleanUI();
            this.mCacheParser.clear();
        }
        for (int i3 = 0; i3 < size; i3++) {
            a aVar = e2.get(i3);
            aVar.l(i3);
            String e3 = aVar.e(i3);
            com.jingdong.app.mall.home.p.b.b a = aVar.a();
            if (z) {
                bVar = this.mCacheParser.get(e3);
                bVar.j(aVar, aVar.f());
            } else {
                b crateParser = a.crateParser(this.mContext);
                crateParser.j(aVar, crateParser.c());
                this.mCacheParser.put(e3, crateParser);
                bVar = crateParser;
            }
            bVar.h(aVar);
            bVar.a(this, this.mNodeModel);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public boolean isShowItemDivider() {
        return false;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onRefreshViewOnMainThread() {
        com.jingdong.app.mall.home.p.b.e.b nodeModel = ((d) this.mPresenter).P().getNodeModel();
        if (nodeModel == null || this.mNodeModel == nodeModel) {
            return;
        }
        this.mNodeModel = nodeModel;
        bindBg();
        bindNode();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    protected boolean useBgMarginColor() {
        return this.mFloorBindElement.s != null;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public d createPresenter() {
        return new d();
    }
}
