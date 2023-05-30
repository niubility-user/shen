package com.jingdong.common.ui.address;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.un.address.R;
import com.jd.lib.un.global.theme.OnViewThemeConfig;
import com.jd.lib.un.global.theme.UnWidgetThemeController;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.ui.LocationStateView;
import com.jingdong.common.ui.UnAddressSelectUtils;
import com.jingdong.common.ui.address.entity.UnAddressInfo;
import com.jingdong.common.ui.address.listener.OnAddressInfoListener;
import com.jingdong.common.ui.homemix.entity.ShopParam;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.common.widget.image.UnNetImageView;
import com.jingdong.sdk.jdtoast.ToastUtils;
import java.util.List;

/* loaded from: classes6.dex */
public class UnAddressSelectView extends RelativeLayout implements View.OnClickListener, OnViewThemeConfig<UnAddressSelectView> {
    public static final int TYPE_CAR = 4;
    public static final int TYPE_DETAIL = 2;
    public static final int TYPE_ORDER = 3;
    public static final int TYPE_ORDER_ADDRESS = 5;
    public static final int TYPE_SEARCH = 1;
    private UnAddressAdapter addressAdapter;
    private RecyclerView addressRv;
    private ImageView closeIv;
    private boolean isAutoDark;
    private boolean isDarkMode;
    private LinearLayout locationLayout;
    private View locationLine;
    private LocationStateView locationStateView;
    private View mLoadingView;
    private int mType;
    private Button newAddressBtn;
    public OnAddressLoadListener onAddressLoadListener;
    public OnItemClickListener onItemClickListener;
    private RelativeLayout rootLayout;
    private String saveBusiness;
    private String sceneId;
    private ShopParam shopParam;
    private String source;
    private UnNetImageView titleBg;
    private RelativeLayout titleContent;
    private TextView titleTv;
    private OnUnAddressListener unAddressListener;

    /* loaded from: classes6.dex */
    public interface OnAddressLoadListener {
        void onComplete(boolean z, List<UnAddressInfo> list);

        void onStart();
    }

    /* loaded from: classes6.dex */
    public interface OnItemClickListener {
        void onItemClick(UnAddressInfo unAddressInfo);
    }

    /* loaded from: classes6.dex */
    public interface OnUnAddressListener {
        void addressSelected(UnAddressInfo unAddressInfo, boolean z);

        void close();
    }

    public UnAddressSelectView(Context context) {
        super(context);
        init();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideProgress() {
        View view = this.mLoadingView;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    private void init() {
        RelativeLayout.inflate(getContext(), R.layout.un_address_select_layout, this);
        this.rootLayout = (RelativeLayout) findViewById(R.id.rootLayout);
        this.locationLine = findViewById(R.id.location_line);
        this.titleContent = (RelativeLayout) findViewById(R.id.l_layout_1);
        this.addressRv = (RecyclerView) findViewById(R.id.addressList);
        this.closeIv = (ImageView) findViewById(R.id.close);
        this.titleTv = (TextView) findViewById(R.id.tv_address_title);
        this.newAddressBtn = (Button) findViewById(R.id.new_address);
        this.locationLayout = (LinearLayout) findViewById(R.id.fl_location);
        this.locationStateView = (LocationStateView) findViewById(R.id.locationView);
        this.mLoadingView = findViewById(R.id.pd_info_loading_pb);
        this.addressRv.setLayoutManager(new LinearLayoutManager(getContext()));
        this.closeIv.setOnClickListener(this);
        this.newAddressBtn.setOnClickListener(this);
        this.locationStateView.setOnClickListener(this);
        this.locationStateView.setSceneId(this.sceneId);
        initListener();
        if (!UnAddressSelectUtils.canShowLocWidget()) {
            this.locationStateView.setVisibility(8);
        }
        UnAddressSelectUtils.getLocAddress(this.sceneId, new OnAddressInfoListener() { // from class: com.jingdong.common.ui.address.UnAddressSelectView.1
            @Override // com.jingdong.common.ui.address.listener.OnAddressInfoListener
            public void onResult(UnAddressInfo unAddressInfo) {
                if (unAddressInfo != null) {
                    UnAddressSelectView.this.post(new Runnable() { // from class: com.jingdong.common.ui.address.UnAddressSelectView.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            UnAddressSelectView.this.locationStateView.resume();
                        }
                    });
                }
            }
        });
        this.titleBg = (UnNetImageView) findViewById(R.id.title_bg);
        if (isDarkMode()) {
            refreshTheme();
        }
    }

    private void initListener() {
        this.onAddressLoadListener = new OnAddressLoadListener() { // from class: com.jingdong.common.ui.address.UnAddressSelectView.2
            @Override // com.jingdong.common.ui.address.UnAddressSelectView.OnAddressLoadListener
            public void onComplete(boolean z, List<UnAddressInfo> list) {
                UnAddressSelectView.this.hideProgress();
                if (z) {
                    UnAddressSelectView.this.showAddress(list);
                }
            }

            @Override // com.jingdong.common.ui.address.UnAddressSelectView.OnAddressLoadListener
            public void onStart() {
                UnAddressSelectView.this.showProgress();
            }
        };
        this.onItemClickListener = new OnItemClickListener() { // from class: com.jingdong.common.ui.address.UnAddressSelectView.3
            @Override // com.jingdong.common.ui.address.UnAddressSelectView.OnItemClickListener
            public void onItemClick(UnAddressInfo unAddressInfo) {
                unAddressInfo.saveBusiness = UnAddressSelectView.this.saveBusiness;
                unAddressInfo.source = UnAddressSelectView.this.source;
                if (UnAddressSelectView.this.shopParam != null && !unAddressInfo.isCoverage) {
                    ToastUtils.showToast(UnAddressSelectView.this.getContext(), "\u8be5\u5730\u5740\u4e0d\u652f\u6301\u914d\u9001");
                    return;
                }
                UnAddressSelectUtils.saveAddress(unAddressInfo);
                if (UnAddressSelectView.this.unAddressListener != null) {
                    UnAddressSelectView.this.unAddressListener.addressSelected(unAddressInfo, UnAddressSelectView.this.mType == 2);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showProgress() {
        View view = this.mLoadingView;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    private void titleContentLayoutThemeChange(boolean z) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.titleContent.getLayoutParams();
        if (z) {
            layoutParams.height = DpiUtil.dip2px(getContext(), 60.0f);
        } else {
            layoutParams.height = DpiUtil.dip2px(getContext(), 68.0f);
        }
    }

    public void changeToTheme(String str, Integer num, Drawable drawable) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        titleContentLayoutThemeChange(false);
        this.titleBg.setVisibility(0);
        this.titleBg.setImage(str);
        if (num != null) {
            this.titleTv.setTextColor(num.intValue());
        }
        if (drawable != null) {
            this.closeIv.setImageDrawable(drawable);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public UnAddressSelectView elderMode() {
        return null;
    }

    public void initData(ShopParam shopParam, int i2) {
        this.mType = i2;
        this.shopParam = shopParam;
        this.locationStateView.setShopParam(shopParam);
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
        return this.isDarkMode;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public boolean isElderMode() {
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.close) {
            OnUnAddressListener onUnAddressListener = this.unAddressListener;
            if (onUnAddressListener != null) {
                onUnAddressListener.close();
            }
        } else if (id == R.id.new_address) {
            LocationStateView locationStateView = this.locationStateView;
            if (locationStateView != null) {
                locationStateView.clickChange();
            }
        } else if (id == R.id.locationView && this.locationStateView.isItemClickBack()) {
            if (this.shopParam != null && !this.locationStateView.isCoverage) {
                ToastUtils.longToast(getContext(), "\u5f53\u524d\u95e8\u5e97\u4e0d\u652f\u6301\u914d\u9001\u5230\u8be5\u5b9a\u4f4d\uff0c\u8bf7\u5207\u6362\u5b9a\u4f4d");
                return;
            }
            UnAddressInfo addressGlobalToAddressInfo = UnAddressSelectUtils.addressGlobalToAddressInfo(this.locationStateView.addressGlobal);
            addressGlobalToAddressInfo.addressType = 2;
            addressGlobalToAddressInfo.saveBusiness = this.saveBusiness;
            addressGlobalToAddressInfo.source = this.source;
            UnAddressSelectUtils.saveAddress(addressGlobalToAddressInfo);
            this.unAddressListener.addressSelected(addressGlobalToAddressInfo, false);
        }
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public void refresh() {
        LocationStateView locationStateView = this.locationStateView;
        if (locationStateView != null) {
            locationStateView.resume();
        }
    }

    public void refreshTheme() {
        if (isDarkMode()) {
            darkMode();
        } else {
            normalMode();
        }
        UnAddressAdapter unAddressAdapter = this.addressAdapter;
        if (unAddressAdapter != null) {
            unAddressAdapter.setDarkMode(isDarkMode());
            this.addressRv.setAdapter(this.addressAdapter);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public UnAddressSelectView setAutoElderMode(boolean z) {
        return null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public UnAddressSelectView setElderMode(boolean z) {
        return null;
    }

    public void setSaveBusiness(String str) {
        this.saveBusiness = str;
    }

    public void setSceneId(String str) {
        this.sceneId = str;
        LocationStateView locationStateView = this.locationStateView;
        if (locationStateView != null) {
            locationStateView.setSceneId(str);
        }
    }

    public void setShopParam(ShopParam shopParam) {
        this.shopParam = shopParam;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public void setUnAddressListener(OnUnAddressListener onUnAddressListener) {
        this.unAddressListener = onUnAddressListener;
    }

    public void showAddress(List<UnAddressInfo> list) {
        AddressGlobal addressGlobal = AddressUtil.getAddressGlobal();
        UnAddressAdapter unAddressAdapter = new UnAddressAdapter(list, addressGlobal == null ? -1L : addressGlobal.getId(), isDarkMode());
        this.addressAdapter = unAddressAdapter;
        if (this.shopParam == null) {
            unAddressAdapter.setCoverageInfoEnable(false);
        }
        this.addressAdapter.setOnItemClickListener(this.onItemClickListener);
        this.addressRv.setAdapter(this.addressAdapter);
    }

    public void toDefaultTheme() {
        titleContentLayoutThemeChange(true);
        this.titleBg.setVisibility(8);
        this.closeIv.setImageResource(R.drawable.common_dialog_close);
        if (isDarkMode()) {
            this.titleTv.setTextColor(getContext().getResources().getColor(R.color.un_content_level_1_dark));
        } else {
            this.titleTv.setTextColor(getContext().getResources().getColor(R.color.un_content_level_1_dark));
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public UnAddressSelectView darkMode() {
        LocationStateView locationStateView = this.locationStateView;
        if (locationStateView != null) {
            locationStateView.setDarkMode(true);
            this.locationStateView.darkMode();
        }
        this.rootLayout.setBackgroundResource(R.drawable.jd_bg_address_dark);
        this.titleTv.setTextColor(getResources().getColor(R.color.un_content_level_1_dark));
        this.locationLine.setBackgroundResource(R.color.un_content_level_3_dark);
        Button button = this.newAddressBtn;
        if (button != null) {
            button.setBackgroundResource(R.drawable.button_a_dark);
            this.newAddressBtn.setTextColor(getContext().getResources().getColorStateList(R.color.button_a_font_color_dark));
        }
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public UnAddressSelectView normalMode() {
        LocationStateView locationStateView = this.locationStateView;
        if (locationStateView != null) {
            locationStateView.setDarkMode(false);
            this.locationStateView.normalMode();
        }
        this.rootLayout.setBackgroundResource(R.drawable.jd_bg_address);
        this.titleTv.setTextColor(getResources().getColor(R.color.un_content_level_1));
        this.locationLine.setBackgroundResource(R.color.un_content_level_3);
        Button button = this.newAddressBtn;
        if (button != null) {
            button.setBackgroundResource(R.drawable.button_b);
            this.newAddressBtn.setTextColor(getContext().getResources().getColorStateList(R.color.button_b_font_color));
        }
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public UnAddressSelectView setAutoDarkMode(boolean z) {
        this.isAutoDark = z;
        this.locationStateView.setAutoDarkMode(z);
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public UnAddressSelectView setDarkMode(boolean z) {
        this.isDarkMode = z;
        return this;
    }

    public UnAddressSelectView(Context context, boolean z) {
        super(context);
        this.isAutoDark = z;
        init();
    }

    public UnAddressSelectView(Context context, boolean z, String str) {
        super(context);
        this.isAutoDark = z;
        this.sceneId = str;
        init();
    }

    public void changeToTheme(Bitmap bitmap, Integer num, Drawable drawable) {
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        titleContentLayoutThemeChange(false);
        this.titleBg.setVisibility(0);
        this.titleBg.setImageBitmap(bitmap);
        if (num != null) {
            this.titleTv.setTextColor(num.intValue());
        }
        if (drawable != null) {
            this.closeIv.setImageDrawable(drawable);
        }
    }

    public UnAddressSelectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public UnAddressSelectView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init();
    }

    public void changeToTheme(Drawable drawable, Integer num, Drawable drawable2) {
        if (drawable == null) {
            return;
        }
        titleContentLayoutThemeChange(false);
        this.titleBg.setVisibility(0);
        this.titleBg.setImageDrawable(drawable);
        if (num != null) {
            this.titleTv.setTextColor(num.intValue());
        }
        if (drawable2 != null) {
            this.closeIv.setImageDrawable(drawable2);
        }
    }

    @RequiresApi(api = 21)
    public UnAddressSelectView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        init();
    }
}
