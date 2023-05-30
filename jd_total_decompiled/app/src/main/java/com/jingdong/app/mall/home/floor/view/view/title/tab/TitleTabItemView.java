package com.jingdong.app.mall.home.floor.view.view.title.tab;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.ctrl.e;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class TitleTabItemView extends RelativeLayout {
    private static long sClickTime;
    private final boolean isUseSpread;
    private TitleTabItem mBindItem;
    private final f mImgSize;
    private final AtomicBoolean mSelectState;
    private final SimpleDraweeView mSelectView;
    private int mTabPosition;
    private final TextView mTitleText;
    private final AtomicBoolean mUnSelectState;
    private final SimpleDraweeView mUnSelectView;

    public TitleTabItemView(Context context, boolean z) {
        super(context);
        this.mSelectState = new AtomicBoolean(false);
        this.mUnSelectState = new AtomicBoolean(false);
        this.mTabPosition = 1;
        this.isUseSpread = z;
        g gVar = new g(context, false);
        gVar.h();
        gVar.l(-1);
        gVar.d(17);
        TextView a = gVar.a();
        this.mTitleText = a;
        addView(a, new ViewGroup.LayoutParams(-1, -1));
        HomeDraweeView homeDraweeView = new HomeDraweeView(context);
        this.mSelectView = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        HomeDraweeView homeDraweeView2 = new HomeDraweeView(context);
        this.mUnSelectView = homeDraweeView2;
        homeDraweeView2.setScaleType(ImageView.ScaleType.FIT_CENTER);
        f fVar = new f(92, 48);
        this.mImgSize = fVar;
        RelativeLayout.LayoutParams u = fVar.u(homeDraweeView);
        u.addRule(13);
        homeDraweeView.setAlpha(0.0f);
        homeDraweeView2.setAlpha(0.0f);
        addView(homeDraweeView, u);
        addView(homeDraweeView2, u);
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabItemView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (TitleTabItemView.this.mBindItem == null || elapsedRealtime - TitleTabItemView.sClickTime < 200) {
                    return;
                }
                long unused = TitleTabItemView.sClickTime = elapsedRealtime;
                TitleTabItemView titleTabItemView = TitleTabItemView.this;
                titleTabItemView.onTabClick(titleTabItemView.mBindItem);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkTabImage() {
        TitleTabItem titleTabItem = this.mBindItem;
        if (titleTabItem != null && titleTabItem.useTabImg(this.isUseSpread) && this.mTabPosition != 1) {
            if (this.mSelectState.get() && this.mUnSelectState.get()) {
                this.mTitleText.setVisibility(8);
                boolean matchView = matchView(this.mTabPosition);
                this.mSelectView.setAlpha(matchView ? 1.0f : 0.0f);
                this.mUnSelectView.setAlpha(matchView ? 0.0f : 1.0f);
                return;
            }
            showTabText();
            return;
        }
        showTabText();
    }

    private void forceShowTabText() {
        this.mSelectState.set(false);
        this.mUnSelectState.set(false);
        showTabText();
    }

    private void loadBtnImg(SimpleDraweeView simpleDraweeView, String str, final AtomicBoolean atomicBoolean) {
        e.p(simpleDraweeView, str, e.b, new e.b() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabItemView.2
            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onFailed(String str2, View view) {
                atomicBoolean.set(false);
                TitleTabItemView.this.checkTabImage();
            }

            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onStart(String str2, View view) {
                atomicBoolean.set(false);
                TitleTabItemView.this.checkTabImage();
            }

            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onSuccess(String str2, View view) {
                atomicBoolean.set(true);
                TitleTabItemView.this.checkTabImage();
            }
        });
    }

    private void showTabText() {
        this.mTitleText.setVisibility(0);
        this.mSelectView.setAlpha(0.0f);
        this.mUnSelectView.setAlpha(0.0f);
    }

    public void bindTabItem(TitleTabItem titleTabItem) {
        this.mBindItem = titleTabItem;
        this.mTitleText.setText(titleTabItem.getTabName());
        if (titleTabItem.useTabImg(this.isUseSpread)) {
            f.c(this.mSelectView, this.mImgSize);
            f.c(this.mUnSelectView, this.mImgSize);
            loadBtnImg(this.mSelectView, titleTabItem.getSelectImg(this.isUseSpread), this.mSelectState);
            loadBtnImg(this.mUnSelectView, titleTabItem.getUnSelectImg(this.isUseSpread), this.mUnSelectState);
            return;
        }
        forceShowTabText();
    }

    public boolean matchView(int i2) {
        TitleTabItem titleTabItem = this.mBindItem;
        return titleTabItem != null && i2 == titleTabItem.getSelectPosition();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onTabClick(TitleTabItem titleTabItem) {
    }

    public void refreshText() {
        TitleTabItem titleTabItem = this.mBindItem;
        if (titleTabItem == null) {
            return;
        }
        this.mTitleText.setText(titleTabItem.getTabName());
    }

    public void refreshTextInfo(int i2) {
        if (this.mBindItem == null) {
            return;
        }
        this.mTabPosition = i2;
        boolean matchView = matchView(i2);
        int tabTextColor = TitleTabManager.getInstance().getTitleTabInfo().getTabTextColor(this.isUseSpread);
        int tabUnSelectTextColor = TitleTabManager.getInstance().getTitleTabInfo().getTabUnSelectTextColor(this.isUseSpread);
        TextView textView = this.mTitleText;
        if (!matchView) {
            tabTextColor = tabUnSelectTextColor;
        }
        textView.setTextColor(tabTextColor);
        int tabTextSize = TitleTabManager.getInstance().getTitleTabInfo().getTabTextSize(this.isUseSpread);
        int tabUnSelectTextSize = TitleTabManager.getInstance().getTitleTabInfo().getTabUnSelectTextSize(this.isUseSpread);
        TextView textView2 = this.mTitleText;
        if (!matchView) {
            tabTextSize = tabUnSelectTextSize;
        }
        g.o(textView2, tabTextSize);
        g.k(this.mTitleText, matchView);
        checkTabImage();
    }
}
