package com.jdjr.generalKeyboard.common;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.jdjr.generalKeyboard.GeneralFunctionalKeyboard;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.List;

/* loaded from: classes18.dex */
public class KeyboardAdapter extends PagerAdapter {
    private List<GeneralFunctionalKeyboard> keyboardList;
    Context mContext;

    public KeyboardAdapter(List<GeneralFunctionalKeyboard> list, Context context) {
        this.keyboardList = list;
        this.mContext = context;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.keyboardList.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i2) {
        View currentView = this.keyboardList.get(i2).getCurrentView();
        viewGroup.setLayoutParams(new LinearLayout.LayoutParams(BaseInfo.getScreenWidth(), viewGroup.getLayoutParams().height));
        viewGroup.addView(currentView);
        return currentView;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return view == obj;
    }
}
