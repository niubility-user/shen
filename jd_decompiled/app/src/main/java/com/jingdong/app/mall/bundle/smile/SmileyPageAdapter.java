package com.jingdong.app.mall.bundle.smile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import androidx.viewpager.widget.PagerAdapter;
import com.jingdong.app.mall.bundle.icssdk_smile.R;
import com.jingdong.app.mall.bundle.smile.model.SmileBean;
import com.jingdong.app.mall.bundle.smile.utils.DisplayUtils;
import com.jingdong.app.mall.bundle.smile.utils.RouterUtil;
import com.jingdong.common.unification.router.CallBackWithReturnListener;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class SmileyPageAdapter extends PagerAdapter {
    private static final int SMILEY_ROWS = 3;
    private static final String TAG = "SmileyPageAdapter";
    private Context mContext;
    private GridViewItemSelected mListener;
    private int mPageCount;
    private ArrayList<View> mPageList;
    private int mPageSize;
    private GridView mSmileyGridView;
    private List<SmileBean> mSmileyList;
    private LinearLayout mSmileyTagLayout;
    private ArrayList<ImageView> mTagList;

    /* loaded from: classes3.dex */
    public interface GridViewItemSelected {
        void onGridViewItemSelected(View view, SmileBean smileBean);
    }

    public SmileyPageAdapter(Context context, List<SmileBean> list, LinearLayout linearLayout, ArrayList<ImageView> arrayList) {
        this.mContext = context;
        this.mSmileyList = list;
        this.mTagList = arrayList;
        this.mSmileyTagLayout = linearLayout;
        int screenWidth = (BaseInfo.getScreenWidth() / DisplayUtils.dip2px(51.0f)) * 3;
        this.mPageSize = screenWidth;
        if (screenWidth == 24) {
            this.mPageSize = 21;
        }
        this.mPageSize--;
        double size = this.mSmileyList.size();
        double d = this.mPageSize;
        Double.isNaN(size);
        Double.isNaN(d);
        this.mPageCount = (int) Math.ceil(size / d);
        this.mSmileyTagLayout.removeAllViews();
        if (this.mPageList == null) {
            this.mPageList = new ArrayList<>();
        }
        for (int i2 = 0; i2 < this.mPageCount; i2++) {
            this.mPageList.add(LayoutInflater.from(context).inflate(R.layout.icssdk_smile_chat_smile_gridview, (ViewGroup) null));
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            imageView.setPadding(0, 0, 15, 0);
            if (i2 == 0) {
                imageView.setImageResource(R.drawable.icssdk_smile_page_tag_pressed);
            } else {
                imageView.setImageResource(R.drawable.icssdk_smile_page_tag_normol);
            }
            arrayList.add(imageView);
            this.mSmileyTagLayout.addView(imageView);
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
        viewGroup.removeView(this.mPageList.get(i2));
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.mPageCount;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i2) {
        View view = this.mPageList.get(i2);
        viewGroup.addView(view, new ViewGroup.LayoutParams(-1, -2));
        GridView gridView = (GridView) view.findViewById(R.id.jd_ics_sdk_gv_smiley);
        this.mSmileyGridView = gridView;
        if (gridView.getChildCount() > 0) {
            return view;
        }
        int i3 = this.mPageSize;
        int i4 = i2 * i3;
        int i5 = (i2 + 1) * i3;
        if (i5 > this.mSmileyList.size() - 1) {
            i5 = this.mSmileyList.size();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.mSmileyList.subList(i4, i5));
        final SmileBean smileBean = new SmileBean();
        RouterUtil.isLightMode(this.mContext, new CallBackWithReturnListener() { // from class: com.jingdong.app.mall.bundle.smile.SmileyPageAdapter.1
            {
                SmileyPageAdapter.this = this;
            }

            @Override // com.jingdong.common.unification.router.CallBackListener
            public void onComplete() {
            }

            @Override // com.jingdong.common.unification.router.CallBackWithReturnListener
            public void onComplete(Object obj) {
                int i6;
                if (obj == null || !(obj instanceof Boolean)) {
                    return;
                }
                if (((Boolean) obj).booleanValue()) {
                    i6 = R.drawable.icssdk_smile_delete_btn;
                } else {
                    i6 = R.drawable.icssdk_smile_delete_btn_dark;
                }
                smileBean.delIcon = i6;
            }

            @Override // com.jingdong.common.unification.router.CallBackListener
            public void onError(int i6) {
            }
        });
        smileBean.name = this.mContext.getString(R.string.icssdk_smiley_del_name);
        smileBean.code = this.mContext.getString(R.string.icssdk_smiley_del_code);
        arrayList.add(smileBean);
        this.mSmileyGridView.setAdapter((ListAdapter) new SmileyAdapter(this.mContext, arrayList, new GridViewItemSelected() { // from class: com.jingdong.app.mall.bundle.smile.SmileyPageAdapter.2
            {
                SmileyPageAdapter.this = this;
            }

            @Override // com.jingdong.app.mall.bundle.smile.SmileyPageAdapter.GridViewItemSelected
            public void onGridViewItemSelected(View view2, SmileBean smileBean2) {
                if (SmileyPageAdapter.this.mListener != null) {
                    SmileyPageAdapter.this.mListener.onGridViewItemSelected(view2, smileBean2);
                }
            }
        }));
        return view;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public void setItems(List<SmileBean> list) {
        this.mSmileyList = list;
        notifyDataSetChanged();
    }

    public void setOnGridViewItemSelectedListener(GridViewItemSelected gridViewItemSelected) {
        this.mListener = gridViewItemSelected;
    }
}
