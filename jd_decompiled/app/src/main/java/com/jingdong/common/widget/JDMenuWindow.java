package com.jingdong.common.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import com.facebook.imagepipeline.image.ImageInfo;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.util.WebNaviSettings;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.navigatorholder.R;
import com.jingdong.sdk.utils.DPIUtil;
import com.jingdong.wireless.iconfont.IconDrawable;
import com.jingdong.wireless.iconfont.widget.IconImpl;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes12.dex */
public class JDMenuWindow extends PopupWindow {
    private static final int MAX_MSG_COUNT = 99;
    public static final int STYLE_BLACK = 1;
    public static final int STYLE_WHITE = 2;
    private MenuAdapter mAdapter;
    private View mContentView;
    private Context mContext;
    private ListView mListView;
    private OnMenuClickListener mOnMenuClickListener;
    private int style;

    /* loaded from: classes12.dex */
    public static class Builder {
        private JDMenuWindow mMenuWindow;

        public Builder(Context context) {
            this.mMenuWindow = new JDMenuWindow(context);
        }

        protected Builder background(@DrawableRes int i2) {
            this.mMenuWindow.getListView().setBackgroundResource(i2);
            return this;
        }

        public Builder bindDatas(List<? extends ISubMenu> list) {
            this.mMenuWindow.bindDatas(list);
            return this;
        }

        public Builder bindItemListener(OnMenuClickListener onMenuClickListener) {
            this.mMenuWindow.setOnMenuClickListener(onMenuClickListener);
            return this;
        }

        public JDMenuWindow create() {
            return this.mMenuWindow;
        }

        public void dismiss() {
            JDMenuWindow jDMenuWindow = this.mMenuWindow;
            if (jDMenuWindow == null || !jDMenuWindow.isShowing()) {
                return;
            }
            this.mMenuWindow.dismiss();
        }

        public boolean isShowing() {
            JDMenuWindow jDMenuWindow = this.mMenuWindow;
            return jDMenuWindow != null && jDMenuWindow.isShowing();
        }

        public JDMenuWindow show(View view, int i2, int i3) {
            JDMenuWindow jDMenuWindow = this.mMenuWindow;
            if (jDMenuWindow != null && !jDMenuWindow.isShowing()) {
                this.mMenuWindow.showAsDropDown(view, i2, i3);
            }
            return this.mMenuWindow;
        }

        public Builder styleType(int i2) {
            this.mMenuWindow.setStyleType(i2);
            return this;
        }
    }

    /* loaded from: classes12.dex */
    public interface ISubMenu {
        Uri getIconUri();

        String getImgUrl();

        String getItemTitle();

        int getMessageCount();

        boolean isRedPointVisible();

        boolean isSameWith(ISubMenu iSubMenu);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class MenuAdapter extends BaseAdapter {
        private List<ISubMenu> mDatas;
        private int mStyleType;

        private MenuAdapter() {
            this.mDatas = new ArrayList();
        }

        public void bindDatas(List<? extends ISubMenu> list) {
            this.mDatas.clear();
            if (list != null) {
                this.mDatas.addAll(list);
            }
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.mDatas.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            return this.mDatas.get(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            View view2;
            ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view2 = View.inflate(JdSdk.getInstance().getApplication(), R.layout.jd_menuwindow_list_item, null);
                viewHolder.imageView = (ImageView) view2.findViewById(R.id.imageView);
                viewHolder.f12563tv = (TextView) view2.findViewById(R.id.f15265tv);
                viewHolder.red = (ImageView) view2.findViewById(R.id.red);
                viewHolder.count = (TextView) view2.findViewById(R.id.count);
                view2.setTag(viewHolder);
            } else {
                view2 = view;
                viewHolder = view.getTag() instanceof ViewHolder ? (ViewHolder) view.getTag() : null;
            }
            if (viewHolder == null) {
                return view2;
            }
            ISubMenu iSubMenu = this.mDatas.get(i2);
            view2.setContentDescription(iSubMenu.getItemTitle());
            Uri iconUri = iSubMenu.getIconUri();
            String imgUrl = iSubMenu.getImgUrl();
            String replace = (TextUtils.isEmpty(imgUrl) || !imgUrl.startsWith(NavigatorHolder.SCHEME_ICONFONT)) ? null : imgUrl.replace(NavigatorHolder.SCHEME_ICONFONT, "");
            if (iconUri != null && !TextUtils.isEmpty(iconUri.getPath())) {
                String str = iSubMenu.getItemTitle() + " use CalorieImage";
                JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
                jDDisplayImageOptions.setPlaceholder(3);
                JDImageLoader.display(iconUri, viewHolder.imageView, jDDisplayImageOptions, (ImageRequestListener<ImageInfo>) null);
            } else if (!TextUtils.isEmpty(replace)) {
                IconImpl iconImpl = new IconImpl("", replace);
                if (viewHolder.imageView.getDrawable() instanceof IconDrawable) {
                    ((IconDrawable) viewHolder.imageView.getDrawable()).setIcon(new IconImpl("", replace)).color(this.mStyleType == 1 ? WebNaviSettings.iconDarkModeColor : WebNaviSettings.iconWhiteModeColor);
                } else {
                    viewHolder.imageView.setImageDrawable(new IconDrawable(view2.getContext(), iconImpl, WebNaviSettings.getIconfontCommonTypeface()).color(this.mStyleType == 1 ? WebNaviSettings.iconDarkModeColor : WebNaviSettings.iconWhiteModeColor));
                }
            } else {
                JDImageUtils.displayImage(iSubMenu.getImgUrl(), viewHolder.imageView);
            }
            viewHolder.imageView.setContentDescription(iSubMenu.getItemTitle());
            viewHolder.f12563tv.setText(iSubMenu.getItemTitle());
            viewHolder.f12563tv.setTextColor(this.mStyleType == 1 ? WebNaviSettings.titleDarkModeColor : WebNaviSettings.titleWhiteModeColor);
            if (iSubMenu.getMessageCount() > 0) {
                viewHolder.count.setVisibility(0);
                viewHolder.red.setVisibility(8);
                if (iSubMenu.getMessageCount() > 99) {
                    viewHolder.count.setText("99+");
                } else {
                    viewHolder.count.setText(String.valueOf(iSubMenu.getMessageCount()));
                }
            } else if (iSubMenu.isRedPointVisible()) {
                viewHolder.red.setVisibility(0);
            } else {
                viewHolder.count.setVisibility(8);
                viewHolder.red.setVisibility(8);
            }
            return view2;
        }

        public void setStyleType(int i2) {
            this.mStyleType = i2;
        }
    }

    /* loaded from: classes12.dex */
    public interface OnMenuClickListener {
        void onClick(AdapterView<?> adapterView, View view, ISubMenu iSubMenu, int i2);
    }

    /* loaded from: classes12.dex */
    public static class SubMenuModel implements ISubMenu {
        private String mImgUrl;
        private int mMessageCount;
        private boolean mRedPointVisible;
        private String mTitle;

        public SubMenuModel(String str, String str2, boolean z, int i2) {
            this.mImgUrl = str;
            this.mTitle = str2;
            this.mRedPointVisible = z;
            this.mMessageCount = i2;
        }

        @Override // com.jingdong.common.widget.JDMenuWindow.ISubMenu
        public Uri getIconUri() {
            return null;
        }

        @Override // com.jingdong.common.widget.JDMenuWindow.ISubMenu
        public String getImgUrl() {
            return this.mImgUrl;
        }

        @Override // com.jingdong.common.widget.JDMenuWindow.ISubMenu
        public String getItemTitle() {
            return this.mTitle;
        }

        @Override // com.jingdong.common.widget.JDMenuWindow.ISubMenu
        public int getMessageCount() {
            return this.mMessageCount;
        }

        @Override // com.jingdong.common.widget.JDMenuWindow.ISubMenu
        public boolean isRedPointVisible() {
            return this.mRedPointVisible;
        }

        @Override // com.jingdong.common.widget.JDMenuWindow.ISubMenu
        public boolean isSameWith(ISubMenu iSubMenu) {
            return iSubMenu != null && TextUtils.equals(getImgUrl(), iSubMenu.getImgUrl()) && TextUtils.equals(getItemTitle(), iSubMenu.getItemTitle()) && isRedPointVisible() == iSubMenu.isRedPointVisible() && getMessageCount() == iSubMenu.getMessageCount();
        }
    }

    /* loaded from: classes12.dex */
    private static class ViewHolder {
        TextView count;
        ImageView imageView;
        ImageView red;
        int style;

        /* renamed from: tv  reason: collision with root package name */
        TextView f12563tv;

        private ViewHolder() {
            this.style = -999;
        }
    }

    public JDMenuWindow(Context context) {
        super(context);
        this.style = 2;
        this.mContext = context;
        initViews();
    }

    private void initViews() {
        View inflate = View.inflate(this.mContext, R.layout.jd_navi_sub_menu, null);
        this.mContentView = inflate;
        this.mListView = (ListView) inflate.findViewById(R.id.listview);
        setContentView(this.mContentView);
        setWidth(DPIUtil.dip2px(157.0f));
        setHeight(-2);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setAnimationStyle(R.style.web_popwin_anim);
        ListView listView = this.mListView;
        MenuAdapter menuAdapter = new MenuAdapter();
        this.mAdapter = menuAdapter;
        listView.setAdapter((ListAdapter) menuAdapter);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.jingdong.common.widget.JDMenuWindow.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
                if (JDMenuWindow.this.mOnMenuClickListener != null) {
                    Object item = JDMenuWindow.this.mAdapter.getItem(i2);
                    if (item instanceof ISubMenu) {
                        JDMenuWindow.this.mOnMenuClickListener.onClick(adapterView, view, (ISubMenu) item, i2);
                    }
                    JDMenuWindow.this.dismiss();
                }
            }
        });
    }

    private boolean isSameData(List<? extends ISubMenu> list) {
        if (this.mAdapter.getCount() != (list != null ? list.size() : 0)) {
            return false;
        }
        if (this.mAdapter.getCount() <= 0 || list == null || list.size() <= 0) {
            return true;
        }
        for (int i2 = 0; i2 < this.mAdapter.getCount(); i2++) {
            ISubMenu iSubMenu = this.mAdapter.getItem(i2) instanceof ISubMenu ? (ISubMenu) this.mAdapter.getItem(i2) : null;
            ISubMenu iSubMenu2 = list.get(i2);
            if (iSubMenu != iSubMenu2 && (iSubMenu == null || !iSubMenu.isSameWith(iSubMenu2))) {
                return false;
            }
        }
        return true;
    }

    public void bindDatas(List<? extends ISubMenu> list) {
        boolean z = !isSameData(list);
        this.mAdapter.bindDatas(list);
        if (z) {
            this.mListView.requestLayout();
        }
    }

    public ListView getListView() {
        return this.mListView;
    }

    public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
        this.mOnMenuClickListener = onMenuClickListener;
    }

    public void setStyleType(int i2) {
        if (this.style != i2) {
            this.style = i2;
            if (i2 != 1) {
                this.mListView.setBackgroundResource(R.drawable.jd_popupwindow_bg);
            } else {
                this.mListView.setBackgroundResource(R.drawable.jd_popupwindow_bg_black);
            }
            this.mAdapter.setStyleType(i2);
            this.mAdapter.notifyDataSetChanged();
        }
    }
}
