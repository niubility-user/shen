package com.jingdong.common.widget.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.global.theme.OnViewThemeConfig;
import com.jd.lib.un.global.theme.UnWidgetThemeController;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.utils.JDImageUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/* loaded from: classes12.dex */
public class JDPopupWindow extends PopupWindow implements OnViewThemeConfig<JDPopupWindow> {
    public static final int MODE_DEFAULT = 0;
    public static final int MODE_NO_PIC = 1;
    private boolean isAutoDark;
    private boolean isDarMode;
    private boolean isFilterColor;
    private BaseAdapter mAdapter;
    private LinearLayout mContentLayout;
    protected Context mContext;
    private int mCurrentMode;
    private ListView mListView;
    private View mView;
    private FrameLayout rootContentLayout;
    private TextView triangleDownTv;
    private TextView triangleUpTv;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Mode {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class PopupWindowAdapter extends BaseAdapter {
        private List<ListPopupWindowMoudle> mList;

        /* loaded from: classes12.dex */
        class ViewHolder {
            TextView count;
            ImageView imageView;
            ImageView red;

            /* renamed from: tv  reason: collision with root package name */
            TextView f12566tv;

            ViewHolder() {
            }
        }

        public PopupWindowAdapter(List<ListPopupWindowMoudle> list) {
            this.mList = list;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.mList.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            return this.mList.get(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(JDPopupWindow.this.mContext).inflate(R.layout.jd_popupwindow_list_item, (ViewGroup) null);
                viewHolder = new ViewHolder();
                viewHolder.imageView = (ImageView) view.findViewById(R.id.imageView);
                viewHolder.f12566tv = (TextView) view.findViewById(R.id.f5682tv);
                viewHolder.red = (ImageView) view.findViewById(R.id.red);
                viewHolder.count = (TextView) view.findViewById(R.id.count);
                viewHolder.f12566tv.setTextColor(JDPopupWindow.this.getTextColor());
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            ListPopupWindowMoudle listPopupWindowMoudle = this.mList.get(i2);
            if (!TextUtils.isEmpty(listPopupWindowMoudle.imageUrl)) {
                final ImageView imageView = viewHolder.imageView;
                JDImageUtils.displayImage(listPopupWindowMoudle.imageUrl, imageView, new JDImageLoadingListener() { // from class: com.jingdong.common.widget.popupwindow.JDPopupWindow.PopupWindowAdapter.1
                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str, View view2) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str, View view2, Bitmap bitmap) {
                        JDPopupWindow.this.filterColor(imageView);
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str, View view2, JDFailReason jDFailReason) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingStarted(String str, View view2) {
                    }
                });
            } else {
                Drawable drawable = listPopupWindowMoudle.drawable;
                if (drawable != null) {
                    viewHolder.imageView.setImageDrawable(drawable);
                    JDPopupWindow.this.filterColor(viewHolder.imageView);
                } else {
                    viewHolder.imageView.setImageDrawable(null);
                }
            }
            if (JDPopupWindow.this.mCurrentMode == 1) {
                ViewGroup.LayoutParams layoutParams = viewHolder.f12566tv.getLayoutParams();
                if (layoutParams instanceof RelativeLayout.LayoutParams) {
                    ((RelativeLayout.LayoutParams) layoutParams).addRule(14);
                    viewHolder.f12566tv.setLayoutParams(layoutParams);
                }
                viewHolder.imageView.setVisibility(8);
            } else {
                ViewGroup.LayoutParams layoutParams2 = viewHolder.f12566tv.getLayoutParams();
                if (layoutParams2 instanceof RelativeLayout.LayoutParams) {
                    ((RelativeLayout.LayoutParams) layoutParams2).addRule(14, 0);
                    viewHolder.f12566tv.setLayoutParams(layoutParams2);
                }
                viewHolder.imageView.setVisibility(0);
            }
            viewHolder.f12566tv.setText(listPopupWindowMoudle.info);
            if (listPopupWindowMoudle.count > 0) {
                viewHolder.count.setVisibility(0);
                viewHolder.red.setVisibility(8);
                if (listPopupWindowMoudle.count > 99) {
                    viewHolder.count.setText("99+");
                } else {
                    viewHolder.count.setText(listPopupWindowMoudle.count + "");
                }
            } else {
                viewHolder.count.setVisibility(8);
                if (listPopupWindowMoudle.isShowRedPoint) {
                    viewHolder.red.setVisibility(0);
                } else {
                    viewHolder.red.setVisibility(8);
                }
            }
            return view;
        }
    }

    public JDPopupWindow(Context context) {
        super(context);
        this.mCurrentMode = 0;
        this.mContext = context;
        init();
    }

    private void changeAnim(boolean z) {
        if (z) {
            setAnimationStyle(R.style.popwin_anim_style);
        } else {
            setAnimationStyle(R.style.popwin_anim_up_style);
        }
    }

    private int changeToOldYoff(int i2) {
        Context context = this.mContext;
        return context == null ? i2 : i2 + DpiUtil.dip2px(context, 4.0f);
    }

    private void changeTriangleLocation(View view, boolean z, int i2) {
        if (view == null || this.mContext == null || this.triangleUpTv == null || this.triangleDownTv == null) {
            return;
        }
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int width = ((iArr[0] - i2) + (view.getWidth() / 2)) - DpiUtil.dip2px(this.mContext, 10.0f);
        if (z) {
            this.triangleUpTv.setTranslationX(width);
        } else {
            this.triangleDownTv.setTranslationX(width);
        }
    }

    private void changeTriangleState(boolean z) {
        TextView textView = this.triangleDownTv;
        if (textView == null || this.triangleUpTv == null) {
            return;
        }
        if (z) {
            textView.setVisibility(8);
            this.triangleUpTv.setVisibility(0);
            return;
        }
        textView.setVisibility(0);
        this.triangleUpTv.setVisibility(8);
    }

    private void changeTriangleToOldLocation(boolean z) {
        if (this.mContext == null || this.triangleUpTv == null || this.triangleDownTv == null) {
            return;
        }
        float realWidth = getRealWidth() - DpiUtil.dip2px(this.mContext, 36.0f);
        if (z) {
            this.triangleUpTv.setTranslationX(realWidth);
        } else {
            this.triangleDownTv.setTranslationX(realWidth);
        }
    }

    private boolean checkPopupDirection(View view, View view2, int i2, int i3) {
        if (view != null && view2 != null) {
            int realHeight = getRealHeight() + i3;
            Rect rect = new Rect();
            view2.getGlobalVisibleRect(rect);
            Rect rect2 = new Rect();
            view.getGlobalVisibleRect(rect2);
            int abs = Math.abs(rect.bottom - rect2.bottom);
            int abs2 = Math.abs(rect2.top - rect.top);
            if (realHeight > abs && realHeight <= abs2) {
                return false;
            }
        }
        return true;
    }

    private View getActivityContentView() {
        Context context = this.mContext;
        if (context == null) {
            return null;
        }
        Activity activity = context instanceof Activity ? (Activity) context : null;
        if (activity == null) {
            return null;
        }
        return activity.findViewById(16908290);
    }

    private int getActivityContentViewHeight() {
        View activityContentView = getActivityContentView();
        if (activityContentView == null) {
            return 0;
        }
        return activityContentView.getHeight();
    }

    private int[] getLocationInfo(View view, View view2, boolean z, int i2, int i3) {
        if (view == null || view2 == null) {
            return new int[]{0, 0, 0};
        }
        if (!z) {
            i3 = -i3;
        }
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int i4 = iArr[0] + i2;
        int i5 = iArr[1] + i3;
        Rect rect = new Rect();
        view2.getGlobalVisibleRect(rect);
        int realWidth = getRealWidth();
        int realHeight = getRealHeight();
        int abs = Math.abs(rect.right - rect.left);
        int i6 = realWidth + i4;
        if (i6 > abs) {
            i4 -= i6 - abs;
        }
        int height = z ? i5 + view.getHeight() : i5 - realHeight;
        if (z) {
            int abs2 = Math.abs(rect.bottom - height);
            if (realHeight <= abs2) {
                setHeight(-2);
            } else {
                setHeight(abs2);
            }
        } else {
            int abs3 = Math.abs((height + realHeight) - rect.top);
            if (realHeight <= abs3) {
                setHeight(-2);
            } else {
                setHeight(abs3);
            }
        }
        return new int[]{8388659, Math.max(i4, rect.left), Math.max(height, rect.top)};
    }

    private int getPicPadding() {
        Context context = this.mContext;
        if (context == null) {
            return 0;
        }
        return DpiUtil.dip2px(context, 10.0f);
    }

    private int getRealHeight() {
        ListView listView = this.mListView;
        if (listView != null && listView.getVisibility() == 0) {
            return getRealListHeight() + (getPicPadding() * 2);
        }
        return getRealLayoutHeight();
    }

    private int getRealLayoutHeight() {
        View contentView = getContentView();
        if (contentView == null) {
            return 0;
        }
        contentView.measure(0, 0);
        return contentView.getMeasuredHeight();
    }

    private int getRealListHeight() {
        ListView listView = this.mListView;
        if (listView == null) {
            return 0;
        }
        listView.measure(0, 0);
        int measuredHeight = listView.getMeasuredHeight();
        ListAdapter adapter = listView.getAdapter();
        int count = adapter != null ? adapter.getCount() : 0;
        return Math.min((measuredHeight * count) + (listView.getDividerHeight() * (count - 1)), getActivityContentViewHeight());
    }

    private int getRealWidth() {
        return getWidth();
    }

    private void init() {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.jd_popupwindow, (ViewGroup) null);
        this.mView = inflate;
        inflate.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        this.mListView = (ListView) this.mView.findViewById(R.id.listView);
        this.mContentLayout = (LinearLayout) this.mView.findViewById(R.id.contentLayout);
        this.triangleDownTv = (TextView) this.mView.findViewById(R.id.un_triangle_down_tv);
        this.triangleUpTv = (TextView) this.mView.findViewById(R.id.un_triangle_up_tv);
        this.rootContentLayout = (FrameLayout) this.mView.findViewById(R.id.root_content_layout);
        setContentView(this.mView);
        setWidth(DpiUtil.dip2px(this.mContext, 157.0f));
        setHeight(-2);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setAnimationStyle(R.style.popwin_anim_style);
    }

    private void showAsDropDownOrUp(View view, View view2, boolean z, int i2, int i3) {
        if (isDarkMode()) {
            darkMode();
        }
        int[] locationInfo = getLocationInfo(view, view2, z, i2, i3);
        int i4 = locationInfo[0];
        int i5 = locationInfo[1];
        int i6 = locationInfo[2];
        changeTriangleState(z);
        changeTriangleLocation(view, z, i5);
        changeAnim(z);
        showAtLocation(view, i4, i5, i6);
    }

    public void addContent(List<ListPopupWindowMoudle> list) {
        this.mListView.setVisibility(0);
        this.mContentLayout.setVisibility(8);
        PopupWindowAdapter popupWindowAdapter = new PopupWindowAdapter(list);
        this.mAdapter = popupWindowAdapter;
        this.mListView.setAdapter((ListAdapter) popupWindowAdapter);
    }

    @Deprecated
    public void addRootContent(View view) {
        View view2 = this.mView;
        if (view2 == null || !(view2 instanceof ViewGroup)) {
            return;
        }
        ((ViewGroup) view2).removeAllViews();
        ((ViewGroup) this.mView).addView(view);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDPopupWindow elderMode() {
        return null;
    }

    public void filterColor(ImageView imageView) {
        if (imageView == null || !this.isFilterColor) {
            return;
        }
        if (isDarkMode()) {
            imageView.setColorFilter(this.mContext.getResources().getColor(R.color.un_content_level_1_dark));
        } else {
            imageView.setColorFilter(this.mContext.getResources().getColor(R.color.un_content_level_1));
        }
    }

    public int getTextColor() {
        if (isDarkMode()) {
            return this.mContext.getResources().getColor(R.color.un_content_level_1_dark);
        }
        return this.mContext.getResources().getColor(R.color.un_content_level_1);
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public boolean isAutoDarkMode() {
        return this.isAutoDark;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public boolean isAutoElderMode() {
        return false;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public boolean isDarkMode() {
        if (this.isAutoDark) {
            return UnWidgetThemeController.getInstance().isDarkMode();
        }
        return this.isDarMode;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public boolean isElderMode() {
        return false;
    }

    public void isFilterColor(boolean z) {
        this.isFilterColor = z;
    }

    public void notifyDataChanged() {
        BaseAdapter baseAdapter = this.mAdapter;
        if (baseAdapter != null) {
            baseAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public void refresh() {
        if (isDarkMode()) {
            darkMode();
        } else {
            normalMode();
        }
        BaseAdapter baseAdapter = this.mAdapter;
        if (baseAdapter != null) {
            this.mListView.setAdapter((ListAdapter) baseAdapter);
        }
    }

    public void refreshListView() {
        BaseAdapter baseAdapter = this.mAdapter;
        if (baseAdapter != null) {
            baseAdapter.notifyDataSetChanged();
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDPopupWindow setAutoElderMode(boolean z) {
        return null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDPopupWindow setElderMode(boolean z) {
        return null;
    }

    public void setListViewItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        ListView listView;
        if (onItemClickListener == null || (listView = this.mListView) == null) {
            return;
        }
        listView.setOnItemClickListener(onItemClickListener);
    }

    public void setMode(int i2) {
        this.mCurrentMode = i2;
    }

    public void showOrClose(View view, int i2, int i3) {
        if (isShowing()) {
            dismiss();
            return;
        }
        if (isDarkMode()) {
            darkMode();
        }
        changeTriangleState(true);
        changeAnim(true);
        changeTriangleToOldLocation(true);
        showAsDropDown(view, i2, changeToOldYoff(i3));
    }

    public void showOrCloseAutoFit(View view, int i2, int i3) {
        showOrCloseAutoFit(view, getActivityContentView(), i2, i3);
    }

    public void showOrCloseDown(View view, int i2, int i3) {
        if (isShowing()) {
            dismiss();
        } else {
            showAsDropDownOrUp(view, getActivityContentView(), true, i2, i3);
        }
    }

    public void showOrCloseUp(View view, int i2, int i3) {
        if (isShowing()) {
            dismiss();
        } else {
            showAsDropDownOrUp(view, getActivityContentView(), false, i2, i3);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDPopupWindow darkMode() {
        this.rootContentLayout.setBackgroundResource(R.drawable.un_popu_bg_dark);
        this.mListView.setDivider(this.mContext.getResources().getDrawable(R.color.un_content_level_2_dark));
        this.mListView.setDividerHeight(1);
        this.triangleDownTv.setBackgroundResource(R.drawable.un_popu_triangle_down_dark);
        this.triangleUpTv.setBackgroundResource(R.drawable.un_popu_triangle_up_dark);
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDPopupWindow normalMode() {
        this.rootContentLayout.setBackgroundResource(R.drawable.un_popu_bg);
        this.mListView.setDivider(this.mContext.getResources().getDrawable(R.color.c_F0F0F0));
        this.mListView.setDividerHeight(1);
        this.triangleDownTv.setBackgroundResource(R.drawable.un_popu_triangle_down);
        this.triangleUpTv.setBackgroundResource(R.drawable.un_popu_triangle_up);
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDPopupWindow setAutoDarkMode(boolean z) {
        this.isAutoDark = z;
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDPopupWindow setDarkMode(boolean z) {
        this.isDarMode = z;
        return this;
    }

    public void showOrCloseAutoFit(View view, View view2, int i2, int i3) {
        if (isShowing()) {
            dismiss();
        } else {
            showAsDropDownOrUp(view, view2, checkPopupDirection(view, view2, i2, i3), i2, i3);
        }
    }

    public void addContent(List<ListPopupWindowMoudle> list, AdapterView.OnItemClickListener onItemClickListener) {
        addContent(list);
        if (onItemClickListener != null) {
            this.mListView.setOnItemClickListener(onItemClickListener);
        }
    }

    @Deprecated
    public void addContent(BaseAdapter baseAdapter) {
        this.mAdapter = baseAdapter;
        this.mListView.setVisibility(0);
        this.mContentLayout.setVisibility(8);
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
    }

    @Deprecated
    public void addContent(BaseAdapter baseAdapter, AdapterView.OnItemClickListener onItemClickListener) {
        addContent(baseAdapter);
        if (onItemClickListener != null) {
            this.mListView.setOnItemClickListener(onItemClickListener);
        }
    }

    @Deprecated
    public void addContent(View view) {
        this.mListView.setVisibility(8);
        this.mContentLayout.setVisibility(0);
        this.mContentLayout.removeAllViews();
        this.mContentLayout.addView(view);
    }
}
