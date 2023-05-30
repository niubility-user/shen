package com.un.lib.popup;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.un.global.theme.OnViewThemeConfig;
import com.jd.lib.un.global.theme.UnWidgetThemeController;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.UnLog;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.messagecenter.view.MessageRedObserver;
import com.jingdong.common.messagecenter.view.NewMessagRedManager;
import com.jingdong.common.messagecenter.view.NewMessageRedInfo;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.widget.popupwindow.ListPopupWindowMoudle;
import java.util.List;
import java.util.Map;

/* loaded from: classes11.dex */
public class JDTopPopupWindow extends PopupWindow implements OnViewThemeConfig<JDTopPopupWindow>, MessageRedObserver {
    private static final String TAG = "JDTopPopupWindow";
    private boolean isAutoDark;
    private boolean isDarMode;
    private boolean isFilterColor;
    private boolean isHideCart;
    private TopPopupWindowAdapter mAdapter;
    protected Activity mContext;
    private Handler mHandler;
    private List<ListPopupWindowMoudle> mNewList;
    private View mRootView;
    private RelativeLayout mTopPopupClose;
    private RelativeLayout mTopPopupLayout;
    private RecyclerView mTopPopupRecycle;
    private TextView mTopPopupText;

    /* loaded from: classes11.dex */
    public interface OnItemClickListener {
        void onItemClicked(String str, int i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class TopPopupWindowAdapter extends RecyclerView.Adapter<JdPopupViewHolder> {
        private Activity mContext;
        private List<ListPopupWindowMoudle> mList;
        private OnItemClickListener mListener;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes11.dex */
        public class JdPopupViewHolder extends RecyclerView.ViewHolder {
            TextView count;
            ImageView imageView;
            RelativeLayout itemLayout;
            ImageView red;

            /* renamed from: tv  reason: collision with root package name */
            TextView f18103tv;

            private JdPopupViewHolder(View view) {
                super(view);
                this.itemLayout = (RelativeLayout) view.findViewById(R.id.item_layout);
                this.imageView = (ImageView) view.findViewById(R.id.imageView);
                this.f18103tv = (TextView) view.findViewById(R.id.f18108tv);
                this.red = (ImageView) view.findViewById(R.id.red);
                this.count = (TextView) view.findViewById(R.id.count);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mListener = onItemClickListener;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            List<ListPopupWindowMoudle> list = this.mList;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        private TopPopupWindowAdapter(Activity activity, List<ListPopupWindowMoudle> list) {
            this.mContext = activity;
            this.mList = list;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull JdPopupViewHolder jdPopupViewHolder, final int i2) {
            final ListPopupWindowMoudle listPopupWindowMoudle;
            if (JDTopPopupWindow.this.isDarkMode()) {
                jdPopupViewHolder.itemLayout.setBackgroundResource(R.drawable.jd_top_popup_item_bg_dark);
                jdPopupViewHolder.red.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.jd_top_popup_item_red_dark));
                jdPopupViewHolder.count.setBackgroundResource(R.drawable.jd_top_popup_item_count_dark);
                jdPopupViewHolder.f18103tv.setTextColor(this.mContext.getResources().getColor(R.color.un_content_level_1_dark));
            } else {
                jdPopupViewHolder.itemLayout.setBackgroundResource(R.drawable.jd_top_popup_item_bg);
                jdPopupViewHolder.red.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.jd_top_popup_item_red));
                jdPopupViewHolder.count.setBackgroundResource(R.drawable.jd_top_popup_item_count);
                jdPopupViewHolder.f18103tv.setTextColor(this.mContext.getResources().getColor(R.color.un_content_level_1));
            }
            List<ListPopupWindowMoudle> list = this.mList;
            if (list == null || list.size() <= i2 || (listPopupWindowMoudle = this.mList.get(i2)) == null) {
                return;
            }
            if (!TextUtils.isEmpty(listPopupWindowMoudle.imageUrl)) {
                final ImageView imageView = jdPopupViewHolder.imageView;
                JDImageUtils.displayImage(listPopupWindowMoudle.imageUrl, imageView, new JDImageLoadingListener() { // from class: com.un.lib.popup.JDTopPopupWindow.TopPopupWindowAdapter.1
                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str, View view) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                        JDTopPopupWindow.this.filterColor(imageView);
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingStarted(String str, View view) {
                    }
                });
            } else {
                Drawable drawable = listPopupWindowMoudle.drawable;
                if (drawable != null) {
                    jdPopupViewHolder.imageView.setImageDrawable(drawable);
                    JDTopPopupWindow.this.filterColor(jdPopupViewHolder.imageView);
                } else {
                    jdPopupViewHolder.imageView.setImageDrawable(null);
                }
            }
            jdPopupViewHolder.imageView.setVisibility(0);
            jdPopupViewHolder.f18103tv.setText(listPopupWindowMoudle.info);
            if (listPopupWindowMoudle.count > 0) {
                jdPopupViewHolder.count.setVisibility(0);
                jdPopupViewHolder.red.setVisibility(8);
                if (listPopupWindowMoudle.count > 99) {
                    jdPopupViewHolder.count.setText("99+");
                } else {
                    jdPopupViewHolder.count.setText(listPopupWindowMoudle.count + "");
                }
            } else {
                jdPopupViewHolder.count.setVisibility(8);
                if (listPopupWindowMoudle.isShowRedPoint) {
                    jdPopupViewHolder.red.setVisibility(0);
                } else {
                    jdPopupViewHolder.red.setVisibility(8);
                }
            }
            jdPopupViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.un.lib.popup.JDTopPopupWindow.TopPopupWindowAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    int i3 = i2;
                    if (i3 == 0) {
                        JDTopPopupWindowHelper.jumpToMessage(TopPopupWindowAdapter.this.mContext);
                        JDTopPopupWindow.this.dismiss();
                    } else if (i3 == 1) {
                        JDTopPopupWindow.this.dismiss();
                        JDTopPopupWindowHelper.jumpToHome(TopPopupWindowAdapter.this.mContext);
                    } else if (i3 == 2) {
                        JDTopPopupWindowHelper.jumpToSearch(TopPopupWindowAdapter.this.mContext);
                        JDTopPopupWindow.this.dismiss();
                    } else if (i3 != 3) {
                        TopPopupWindowAdapter.this.mListener.onItemClicked(listPopupWindowMoudle.info, i2);
                    } else {
                        if (JDTopPopupWindow.this.isHideCart) {
                            TopPopupWindowAdapter.this.mListener.onItemClicked(listPopupWindowMoudle.info, i2);
                        } else {
                            JDTopPopupWindowHelper.jumpToCart(TopPopupWindowAdapter.this.mContext);
                        }
                        JDTopPopupWindow.this.dismiss();
                    }
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @NonNull
        public JdPopupViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
            return new JdPopupViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.jd_top_popupwindow_list_item, viewGroup, false));
        }
    }

    public JDTopPopupWindow(Activity activity) {
        super(activity);
        this.isAutoDark = false;
        this.isDarMode = false;
        this.isFilterColor = false;
        this.isHideCart = false;
        this.mContext = activity;
        this.mHandler = new Handler(Looper.getMainLooper());
        init(activity);
        initPopupWindow();
    }

    /* JADX INFO: Access modifiers changed from: private */
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

    private void init(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.jd_top_popupwindow, (ViewGroup) null);
        this.mRootView = inflate;
        this.mTopPopupLayout = (RelativeLayout) inflate.findViewById(R.id.jd_top_popup_back);
        this.mTopPopupRecycle = (RecyclerView) this.mRootView.findViewById(R.id.jd_top_popup_recycle);
        this.mTopPopupText = (TextView) this.mRootView.findViewById(R.id.text_jd_top_pipup_more);
        RelativeLayout relativeLayout = (RelativeLayout) this.mRootView.findViewById(R.id.im_top_popup_close);
        this.mTopPopupClose = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.un.lib.popup.JDTopPopupWindow.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDTopPopupWindow.this.dismiss();
            }
        });
    }

    @SuppressLint({"InlinedApi"})
    private void initPopupWindow() {
        setContentView(this.mRootView);
        setWidth(-1);
        setHeight(-2);
        setFocusable(true);
        setAnimationStyle(R.style.popwin_anim_top_style);
        setBackgroundDrawable(new BitmapDrawable());
        setOutsideTouchable(true);
        setClippingEnabled(false);
    }

    private void registRedMessage() {
        boolean z = UnLog.D;
        NewMessagRedManager.getInstance(LoginUserBase.getLoginUserName());
        NewMessagRedManager.registPersonalMessageObserver(this);
    }

    private void requestRedMessage() {
        boolean z = UnLog.D;
        if (LoginUserBase.hasLogin()) {
            NewMessagRedManager.getInstance(LoginUserBase.getLoginUserName());
            NewMessagRedManager.requestMessage(HttpGroupUtils.getHttpGroupaAsynPool());
        }
    }

    public void addBaseContent() {
        List<ListPopupWindowMoudle> baseContent = JDTopPopupWindowHelper.getBaseContent(this.mContext, isDarkMode(), this.isHideCart);
        this.mNewList = baseContent;
        this.mAdapter = new TopPopupWindowAdapter(this.mContext, baseContent);
        this.mTopPopupRecycle.setLayoutManager(new GridLayoutManager(getContentView().getContext(), 4));
        this.mTopPopupRecycle.setAdapter(this.mAdapter);
    }

    public void addContent(List<ListPopupWindowMoudle> list) {
        List<ListPopupWindowMoudle> addBaseContent = addBaseContent(list);
        this.mNewList = addBaseContent;
        this.mAdapter = new TopPopupWindowAdapter(this.mContext, addBaseContent);
        this.mTopPopupRecycle.setLayoutManager(new GridLayoutManager(getContentView().getContext(), 4));
        this.mTopPopupRecycle.setAdapter(this.mAdapter);
    }

    @Override // android.widget.PopupWindow
    public void dismiss() {
        super.dismiss();
        boolean z = UnLog.D;
        NewMessagRedManager.getInstance(null);
        NewMessagRedManager.deregisterPersonalMessageObserver(this);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDTopPopupWindow elderMode() {
        return null;
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
        TopPopupWindowAdapter topPopupWindowAdapter = this.mAdapter;
        if (topPopupWindowAdapter != null) {
            topPopupWindowAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.jingdong.common.messagecenter.view.MessageRedObserver
    public void onMessageRedReceived(Map<String, NewMessageRedInfo> map) {
        boolean z = UnLog.D;
        if (map == null || !map.containsKey("messageRedInfo")) {
            return;
        }
        NewMessageRedInfo newMessageRedInfo = map.get("messageRedInfo");
        boolean z2 = UnLog.D;
        if (newMessageRedInfo == null || newMessageRedInfo.num < 0) {
            return;
        }
        if (UnLog.D) {
            String str = "redMessageNum = " + newMessageRedInfo.num;
        }
        List<ListPopupWindowMoudle> list = this.mNewList;
        if (list == null || list.size() < 0 || this.mNewList.get(0) == null) {
            return;
        }
        this.mNewList.get(0).count = newMessageRedInfo.num;
        this.mHandler.post(new Runnable() { // from class: com.un.lib.popup.JDTopPopupWindow.2
            @Override // java.lang.Runnable
            public void run() {
                JDTopPopupWindow.this.notifyDataChanged();
            }
        });
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public void refresh() {
        TopPopupWindowAdapter topPopupWindowAdapter;
        if (isDarkMode()) {
            darkMode();
        } else {
            normalMode();
        }
        RecyclerView recyclerView = this.mTopPopupRecycle;
        if (recyclerView != null && (topPopupWindowAdapter = this.mAdapter) != null) {
            recyclerView.setAdapter(topPopupWindowAdapter);
        }
        notifyDataChanged();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDTopPopupWindow setAutoElderMode(boolean z) {
        return null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDTopPopupWindow setElderMode(boolean z) {
        return null;
    }

    public JDTopPopupWindow setHideCart(boolean z) {
        this.isHideCart = z;
        return this;
    }

    public void setItemClickListener(OnItemClickListener onItemClickListener) {
        TopPopupWindowAdapter topPopupWindowAdapter;
        if (onItemClickListener == null || (topPopupWindowAdapter = this.mAdapter) == null) {
            return;
        }
        topPopupWindowAdapter.setOnItemClickListener(onItemClickListener);
    }

    public void showOrClose(View view) {
        if (isShowing()) {
            dismiss();
            return;
        }
        boolean z = UnLog.D;
        registRedMessage();
        requestRedMessage();
        if (isDarkMode()) {
            darkMode();
        } else {
            normalMode();
        }
        showAtLocation(view, 49, 0, 0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDTopPopupWindow darkMode() {
        this.mTopPopupLayout.setBackgroundResource(R.drawable.jd_top_popup_pic_bg_dark);
        this.mTopPopupText.setTextColor(this.mContext.getResources().getColor(R.color.un_content_level_1_dark));
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDTopPopupWindow normalMode() {
        this.mTopPopupLayout.setBackgroundResource(R.drawable.jd_top_popup_pic_bg);
        this.mTopPopupText.setTextColor(this.mContext.getResources().getColor(R.color.un_content_level_1));
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDTopPopupWindow setAutoDarkMode(boolean z) {
        this.isAutoDark = z;
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDTopPopupWindow setDarkMode(boolean z) {
        this.isDarMode = z;
        return this;
    }

    private List<ListPopupWindowMoudle> addBaseContent(List<ListPopupWindowMoudle> list) {
        List<ListPopupWindowMoudle> baseContent = JDTopPopupWindowHelper.getBaseContent(this.mContext, isDarkMode(), this.isHideCart);
        if (list != null && list.size() > 0) {
            baseContent.addAll(list);
        }
        return baseContent;
    }

    public void addContent(List<ListPopupWindowMoudle> list, OnItemClickListener onItemClickListener) {
        TopPopupWindowAdapter topPopupWindowAdapter;
        addContent(list);
        if (onItemClickListener == null || (topPopupWindowAdapter = this.mAdapter) == null) {
            return;
        }
        topPopupWindowAdapter.setOnItemClickListener(onItemClickListener);
    }
}
