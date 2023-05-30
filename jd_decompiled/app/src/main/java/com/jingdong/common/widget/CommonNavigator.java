package com.jingdong.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.jingdong.common.widget.NavigatorHolder;
import com.jingdong.sdk.navigatorholder.R;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class CommonNavigator extends FrameLayout {
    public static final int MAX_SCROLL_HEIGHT_ALPHA = DPIUtil.dip2px(48.0f);
    private static final String TAG = CommonNavigator.class.getSimpleName();
    private Context mContext;
    private NavigatorHolder mNavigatorHolder;

    /* loaded from: classes12.dex */
    public static class NaviClickAdaper implements NavigatorHolder.NaviListener {
        @Override // com.jingdong.common.widget.NavigatorHolder.NaviListener
        public void onClickCalendar() {
            OKLog.d(CommonNavigator.TAG, "onClickCalendar");
        }

        @Override // com.jingdong.common.widget.NavigatorHolder.NaviListener
        public void onClickCart() {
            OKLog.d(CommonNavigator.TAG, "onClickCart");
        }

        @Override // com.jingdong.common.widget.NavigatorHolder.NaviListener
        public void onClickClose() {
            OKLog.d(CommonNavigator.TAG, "onClickClose");
        }

        @Override // com.jingdong.common.widget.NavigatorHolder.NaviListener
        public void onClickCustom(String str) {
            OKLog.d(CommonNavigator.TAG, "onClickCustom");
        }

        @Override // com.jingdong.common.widget.NavigatorHolder.NaviListener
        public void onClickHome() {
            OKLog.d(CommonNavigator.TAG, "onClickHome");
        }

        @Override // com.jingdong.common.widget.NavigatorHolder.NaviListener
        public void onClickMore() {
            OKLog.d(CommonNavigator.TAG, "onClickMore");
        }

        @Override // com.jingdong.common.widget.NavigatorHolder.NaviListener
        public void onClickMsg() {
            OKLog.d(CommonNavigator.TAG, "onClickMsg");
        }

        @Override // com.jingdong.common.widget.NavigatorHolder.NaviListener
        public void onClickPopCart() {
            OKLog.d(CommonNavigator.TAG, "onClickPopCart");
        }

        @Override // com.jingdong.common.widget.NavigatorHolder.NaviListener
        public void onClickPopCustom(String str) {
            OKLog.d(CommonNavigator.TAG, "onClickPopCustom");
        }

        @Override // com.jingdong.common.widget.NavigatorHolder.NaviListener
        public void onClickPopFeedback() {
            OKLog.d(CommonNavigator.TAG, "onClickPopFeedback");
        }

        @Override // com.jingdong.common.widget.NavigatorHolder.NaviListener
        public void onClickPopHome() {
            OKLog.d(CommonNavigator.TAG, "onClickPopHome");
        }

        @Override // com.jingdong.common.widget.NavigatorHolder.NaviListener
        public void onClickPopMsg() {
            OKLog.d(CommonNavigator.TAG, "onClickPopMsg");
        }

        @Override // com.jingdong.common.widget.NavigatorHolder.NaviListener
        public void onClickPopSearch() {
            OKLog.d(CommonNavigator.TAG, "onClickPopSearch");
        }

        @Override // com.jingdong.common.widget.NavigatorHolder.NaviListener
        public void onClickPopShare() {
            OKLog.d(CommonNavigator.TAG, "onClickPopShare");
        }

        @Override // com.jingdong.common.widget.NavigatorHolder.NaviListener
        public void onClickSearch() {
            OKLog.d(CommonNavigator.TAG, "onClickSearch");
        }

        @Override // com.jingdong.common.widget.NavigatorHolder.NaviListener
        public void onClickShare() {
            OKLog.d(CommonNavigator.TAG, "onClickShare");
        }

        @Override // com.jingdong.common.widget.NavigatorHolder.NaviListener
        public void onClickTitleBack() {
            OKLog.d(CommonNavigator.TAG, "onClickTitleBack");
        }

        @Override // com.jingdong.common.widget.NavigatorHolder.NaviListener
        public void onRightTextView() {
            OKLog.d(CommonNavigator.TAG, "onRightTextView");
        }
    }

    public CommonNavigator(Context context) {
        super(context);
        initView(context, null);
    }

    private void initView(Context context, AttributeSet attributeSet) {
        this.mContext = context;
        boolean z = false;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CommonNavigator);
            z = obtainStyledAttributes.getBoolean(R.styleable.CommonNavigator_dark_mode, false);
            obtainStyledAttributes.recycle();
        }
        View.inflate(context, R.layout.common_navigator, this);
        long currentTimeMillis = System.currentTimeMillis();
        this.mNavigatorHolder = new NavigatorHolder(this.mContext, this, z, true);
        new Handler().postDelayed(new Runnable() { // from class: com.jingdong.common.widget.CommonNavigator.1
            @Override // java.lang.Runnable
            public void run() {
                CommonNavigator.this.mNavigatorHolder.defaultNaviWithoutClostBtn();
            }
        }, 2000L);
        String str = "init time = " + (System.currentTimeMillis() - currentTimeMillis);
    }

    public void configNavi(String str, String str2, String str3, List<JSONObject> list, boolean z, boolean z2, boolean z3) {
        NavigatorHolder navigatorHolder = this.mNavigatorHolder;
        if (navigatorHolder != null) {
            navigatorHolder.resetBtn();
            this.mNavigatorHolder.setShareBtnState(z, z2);
            this.mNavigatorHolder.setTitleTextOrImg(str, str2);
            if (list != null) {
                Iterator<JSONObject> it = list.iterator();
                while (it.hasNext()) {
                    this.mNavigatorHolder.controlNavigationItems(it.next());
                }
            }
            this.mNavigatorHolder.setNaviImmersive(z3, str3);
        }
    }

    public NavigatorHolder getNavigatorHolder() {
        return this.mNavigatorHolder;
    }

    public void refreshCart() {
    }

    public void refreshCartWithAnim() {
    }

    public void reset() {
        NavigatorHolder navigatorHolder = this.mNavigatorHolder;
        if (navigatorHolder != null) {
            navigatorHolder.resetBtn();
        }
    }

    public void setHeadType(String str, String str2, String str3, int i2, int i3, int i4, int i5) {
        this.mNavigatorHolder.setHeadStyle(str, str2, !TextUtils.isEmpty(str3) ? 1 : 0, i2, i3, i4, i5);
    }

    public void setMsgRedPointNum(int i2) {
        NavigatorHolder navigatorHolder = this.mNavigatorHolder;
        if (navigatorHolder != null) {
            navigatorHolder.setMsgRedPointNum(i2);
        }
    }

    public void setNaviBgAlphaByScrollY(int i2, int i3, int i4) {
        int i5 = MAX_SCROLL_HEIGHT_ALPHA;
        if (i2 > i5) {
            i2 = i5;
        }
        if (i2 < i5) {
            if (i2 < 0) {
                i2 = 0;
            }
            float f2 = (1.0f / i5) * i2;
            NavigatorHolder navigatorHolder = this.mNavigatorHolder;
            if (navigatorHolder != null) {
                navigatorHolder.setNaviAndStatusBarHeightViewAlpha(f2);
                this.mNavigatorHolder.updateNaviIconAndTitle(i3);
                return;
            }
            return;
        }
        NavigatorHolder navigatorHolder2 = this.mNavigatorHolder;
        if (navigatorHolder2 != null) {
            navigatorHolder2.setNaviAndStatusBarHeightViewAlpha(1.0f);
            this.mNavigatorHolder.updateNaviIconAndTitle(i4);
        }
    }

    public void setNaviClickAdapter(NaviClickAdaper naviClickAdaper) {
        this.mNavigatorHolder.setNaviListener(naviClickAdaper);
    }

    public void setNaviFollowAdapter(NavigatorHolder.BaseNaviFollowAdapter baseNaviFollowAdapter) {
        this.mNavigatorHolder.setNaviFollowAdapter(baseNaviFollowAdapter);
    }

    public void setNaviVisible(int i2) {
        NavigatorHolder navigatorHolder = this.mNavigatorHolder;
        if (navigatorHolder != null) {
            navigatorHolder.setNaviVisible(i2);
        }
    }

    public void setRedPointVisibility(boolean z) {
        NavigatorHolder navigatorHolder = this.mNavigatorHolder;
        if (navigatorHolder != null) {
            navigatorHolder.setRedPointVisibility(z);
        }
    }

    public void setStatusBarAlwaysTransparent(boolean z) {
        NavigatorHolder navigatorHolder = this.mNavigatorHolder;
        if (navigatorHolder != null) {
            navigatorHolder.setStatusBarAlwaysTransparent(z);
        }
    }

    public void setStatusBarAndTitleBg(int i2, Drawable drawable, Drawable drawable2) {
        this.mNavigatorHolder.setStatusBarAndTitleBg(i2, drawable, drawable2);
    }

    public void showThirdBtn(View view) {
        NavigatorHolder navigatorHolder = this.mNavigatorHolder;
        if (navigatorHolder != null) {
            navigatorHolder.showThirdBtn(view);
        }
    }

    public CommonNavigator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context, attributeSet);
    }

    public void setMsgRedPointNum(int i2, boolean z) {
        NavigatorHolder navigatorHolder = this.mNavigatorHolder;
        if (navigatorHolder != null) {
            navigatorHolder.setMsgRedPointNum(i2, z);
        }
    }

    public CommonNavigator(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        initView(context, attributeSet);
    }

    public void setNaviBgAlphaByScrollY(int i2) {
        setNaviBgAlphaByScrollY(i2, 2, 1);
    }
}
