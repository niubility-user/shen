package com.jingdong.common.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.jd.framework.json.JDJSON;
import com.jd.lib.un.address.R;
import com.jd.lib.un.global.theme.OnViewThemeConfig;
import com.jd.lib.un.global.theme.UnWidgetThemeController;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.deeplinkhelper.DeepLinkLocationAddressHelper;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.lbs.LocManager;
import com.jingdong.common.lbs.OnLbsStateListener;
import com.jingdong.common.lbs.event.LocationAddressEvent;
import com.jingdong.common.permission.LBSSceneSwitchHelper;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.ui.address.LocationStateViewEntity;
import com.jingdong.common.ui.address.LocationStateViewHelper;
import com.jingdong.common.ui.address.entity.UnAddressInfo;
import com.jingdong.common.ui.address.listener.OnAddressInfoListener;
import com.jingdong.common.ui.address.listener.OnSingleAddressCoverageListener;
import com.jingdong.common.ui.homemix.entity.ShopParam;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import de.greenrobot.event.EventBus;

/* loaded from: classes6.dex */
public class LocationStateView extends FrameLayout implements View.OnClickListener, OnLbsStateListener, OnViewThemeConfig<LocationStateView> {
    public static final int STYLE_ALERT = 1;
    public static final int STYLE_NORMAL = 0;
    public static final int STYLE_SUCCESS = 2;
    private TextView addressDetail;
    public AddressGlobal addressGlobal;
    private boolean canRefreshLocation;
    private ILocationClickStateListener clickStateListener;
    private Context context;
    private LinearLayout coverageDisableLayout;
    private TextView coverageLocation;
    private TextView coverageTip;
    private int customStyle;
    private Handler handler;
    private LocationStateViewHelper helper;
    private boolean isAutoDark;
    private boolean isChangeNormalAddress;
    public boolean isCoverage;
    private boolean isCustomLocation;
    private boolean isDarkMode;
    private ILocationViewListener listener;
    private ImageView locationIcon;
    public boolean mUseCustomMap;
    private boolean needStartLocationByInit;
    public boolean notOptMap;
    private ImageView optIcon;
    private LinearLayout optLayout;
    private TextView optText;
    private View.OnClickListener optTextClickListener;
    private int requestCode;
    private RelativeLayout rootLayout;
    public String sceneId;
    private ShopParam shopParam;
    private boolean showAddressNormalEnable;
    private boolean showDisCoverageToast;
    private OnLocationViewShowListener showListener;
    private boolean startToSetting;
    private ILocationStateChangeListener stateChangeListener;
    private TextView stateText;
    private String stateTextLocationFail;
    private String stateTextLocationRunning;
    private String stateTextNoPermission;
    private String stateTextSuccess;
    private TextView subAddress;
    private boolean successSelect;

    public LocationStateView(@NonNull Context context) {
        super(context);
        this.showDisCoverageToast = false;
        this.mUseCustomMap = false;
        this.requestCode = 100;
        this.notOptMap = false;
        this.needStartLocationByInit = false;
        this.canRefreshLocation = true;
        this.isChangeNormalAddress = false;
        this.showAddressNormalEnable = false;
        this.context = context;
        initView(null);
    }

    public void changeCoverage(boolean z, boolean z2) {
        this.isCoverage = z;
        if (z) {
            this.coverageDisableLayout.setVisibility(8);
            this.optText.setVisibility(0);
            if (isDarkMode()) {
                this.addressDetail.setTextColor(this.context.getResources().getColor(R.color.un_content_level_1_dark));
                return;
            } else {
                this.addressDetail.setTextColor(this.context.getResources().getColor(R.color.un_content_level_1));
                return;
            }
        }
        if (isDarkMode()) {
            this.locationIcon.setImageResource(R.drawable.un_locaion_disable_dark);
            this.addressDetail.setTextColor(this.context.getResources().getColor(R.color.un_content_level_2_dark));
        } else {
            this.locationIcon.setImageResource(R.drawable.un_locaion_disable);
            this.addressDetail.setTextColor(this.context.getResources().getColor(R.color.un_content_level_2));
        }
        this.coverageDisableLayout.setVisibility(0);
        this.optText.setVisibility(8);
        this.addressDetail.setTypeface(Typeface.defaultFromStyle(0));
        if (z2) {
            ToastUtils.shortToast(getContext(), "\u6240\u9009\u5730\u5740\u4e0d\u5728\u53ef\u914d\u9001\u8303\u56f4");
        }
    }

    private AddressGlobal createAddressWithLoc() {
        return UnAddressSelectUtils.getLocAddressGlobal(this.sceneId);
    }

    private String getNull(String str) {
        return str == null ? "" : str;
    }

    private int getShowState() {
        int locateState = LocManager.getLocateState();
        if (locateState != -2) {
            if (locateState == -1 || locateState == 1) {
                return UnAddressSelectUtils.isOpenGps() ? 3 : 4;
            }
            return 2;
        }
        return 1;
    }

    public boolean hasLocationPermission() {
        return LBSSceneSwitchHelper.getLbsSceneSwitch(this.sceneId) && PermissionHelper.hasGrantedLocation(PermissionHelper.generateBundle(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID, getClass().getName(), "startPermissionSetting"));
    }

    private void initView(AttributeSet attributeSet) {
        this.handler = new Handler(getContext().getMainLooper());
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.LocationStateView, 0, 0);
            this.customStyle = obtainStyledAttributes.getInt(R.styleable.LocationStateView_customViewStyle, 0);
            this.stateTextNoPermission = obtainStyledAttributes.getString(R.styleable.LocationStateView_stateTextNoPermission);
            this.stateTextLocationRunning = obtainStyledAttributes.getString(R.styleable.LocationStateView_stateTextLocationRunning);
            this.stateTextLocationFail = obtainStyledAttributes.getString(R.styleable.LocationStateView_stateTextLocationFail);
            this.isAutoDark = obtainStyledAttributes.getBoolean(R.styleable.LocationStateView_lsvAutoDark, false);
            this.needStartLocationByInit = obtainStyledAttributes.getBoolean(R.styleable.LocationStateView_needStartLocationByInit, false);
            this.sceneId = obtainStyledAttributes.getString(R.styleable.LocationStateView_sceneId);
        }
        if (TextUtils.isEmpty(this.sceneId)) {
            this.sceneId = "basicShoppingProcess";
        }
        this.helper = new LocationStateViewHelper(this.sceneId);
        setStyle();
        if (this.needStartLocationByInit) {
            startLocationByInit();
        }
    }

    public int isGangAoTai() {
        AddressGlobal locAddressGlobal = UnAddressSelectUtils.getLocAddressGlobal(this.sceneId);
        if (locAddressGlobal == null) {
            return 0;
        }
        if (locAddressGlobal.isForeignOverSea) {
            return 1;
        }
        return locAddressGlobal.isGangAoTai ? 2 : 0;
    }

    private void normalOptSetting(boolean z) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.optLayout.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.stateText.getLayoutParams();
        if (z) {
            layoutParams.leftMargin = DpiUtil.dip2px(this.context, 6.0f);
            layoutParams.addRule(11, 0);
            layoutParams.addRule(1, R.id.stateText);
            this.optLayout.setLayoutParams(layoutParams);
            this.optIcon.setVisibility(0);
            layoutParams2.addRule(0, 0);
            this.stateText.setLayoutParams(layoutParams2);
            return;
        }
        layoutParams.leftMargin = DpiUtil.dip2px(this.context, 12.0f);
        layoutParams.addRule(11);
        layoutParams.addRule(1, 0);
        this.optLayout.setLayoutParams(layoutParams);
        this.optIcon.setVisibility(8);
        layoutParams2.addRule(0, R.id.optLayout);
        this.stateText.setLayoutParams(layoutParams2);
    }

    private void normalStyleDark() {
        this.rootLayout.setBackgroundResource(R.drawable.un_location_state_normal_bg_dark);
    }

    private void optTextClick() {
        if (this.customStyle == 0 && showAddressNormalEnable()) {
            ILocationClickStateListener iLocationClickStateListener = this.clickStateListener;
            if (iLocationClickStateListener != null) {
                iLocationClickStateListener.onClick(-1);
                return;
            }
            return;
        }
        int i2 = this.customStyle;
        if (i2 != 1 && i2 != 0) {
            clickChange();
            ILocationClickStateListener iLocationClickStateListener2 = this.clickStateListener;
            if (iLocationClickStateListener2 != null) {
                iLocationClickStateListener2.onClick(2);
            }
        } else if (LocManager.getLocateState() != -2) {
            clickChange();
            if (UnAddressSelectUtils.isOpenGps()) {
                ILocationClickStateListener iLocationClickStateListener3 = this.clickStateListener;
                if (iLocationClickStateListener3 != null) {
                    iLocationClickStateListener3.onClick(3);
                    return;
                }
                return;
            }
            ILocationClickStateListener iLocationClickStateListener4 = this.clickStateListener;
            if (iLocationClickStateListener4 != null) {
                iLocationClickStateListener4.onClick(4);
            }
        } else {
            startPermissionSetting();
            ILocationClickStateListener iLocationClickStateListener5 = this.clickStateListener;
            if (iLocationClickStateListener5 != null) {
                iLocationClickStateListener5.onClick(1);
            }
        }
    }

    private void requestCoverage(final boolean z) {
        Log.d("address_location", "requestCoverage:" + this.shopParam);
        if (this.shopParam == null) {
            return;
        }
        UnAddressSelectUtils.singleAddreessDeliveryArea(UnAddressSelectUtils.addressGlobalToAddressInfo(this.addressGlobal), this.shopParam, new OnSingleAddressCoverageListener() { // from class: com.jingdong.common.ui.LocationStateView.6
            {
                LocationStateView.this = this;
            }

            @Override // com.jingdong.common.ui.address.listener.OnSingleAddressCoverageListener
            public void onResult(final UnAddressInfo unAddressInfo) {
                LocationStateView.this.post(new Runnable() { // from class: com.jingdong.common.ui.LocationStateView.6.1
                    {
                        AnonymousClass6.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        UnAddressInfo unAddressInfo2 = unAddressInfo;
                        if (unAddressInfo2 != null && unAddressInfo2.isCoverage) {
                            AnonymousClass6 anonymousClass6 = AnonymousClass6.this;
                            LocationStateView.this.changeCoverage(true, z);
                        } else {
                            AnonymousClass6 anonymousClass62 = AnonymousClass6.this;
                            LocationStateView.this.changeCoverage(false, z);
                        }
                        if (LocationStateView.this.showDisCoverageToast) {
                            return;
                        }
                        LocationStateView.this.showDisCoverageToast = true;
                    }
                });
            }
        });
    }

    private void setAddressGlobal(AddressGlobal addressGlobal, boolean z) {
        if (addressGlobal == null) {
            return;
        }
        if (this.addressGlobal == null) {
            this.addressGlobal = new AddressGlobal();
        }
        this.addressGlobal.setWhere(addressGlobal.getWhere());
        this.addressGlobal.setAddressDetail(addressGlobal.getAddressDetail());
        this.addressGlobal.setAddressTitle(addressGlobal.getAddressTitle());
        this.addressGlobal.setSaveBusiness(addressGlobal.getSaveBusiness());
        this.addressGlobal.setSource(addressGlobal.getSource());
        this.addressGlobal.setLongitude(addressGlobal.getLongitude());
        this.addressGlobal.setLatitude(addressGlobal.getLatitude());
        this.addressGlobal.setIdTown(addressGlobal.getIdTown());
        this.addressGlobal.setTownName(addressGlobal.getTownName());
        this.addressGlobal.setProvinceName(addressGlobal.getProvinceName());
        this.addressGlobal.setIdProvince(addressGlobal.getIdProvince());
        this.addressGlobal.setCityName(addressGlobal.getCityName());
        this.addressGlobal.setIdCity(addressGlobal.getIdCity());
        this.addressGlobal.setAreaName(addressGlobal.getAreaName());
        this.addressGlobal.setIdArea(addressGlobal.getIdArea());
    }

    public void setSuccess() {
        String str;
        LocationStateViewEntity showAddress = this.helper.showAddress();
        if (showAddress == null) {
            return;
        }
        AddressGlobal addressGlobal = showAddress.address;
        this.addressGlobal = addressGlobal;
        if (addressGlobal == null) {
            return;
        }
        changeCoverage(showAddress.isCoverage, false);
        if (showAddress.isLocal) {
            this.stateText.setText("\u5f53\u524d\u5b9a\u4f4d");
        } else {
            this.stateText.setText("\u4e0a\u6b21\u5b9a\u4f4d");
        }
        String str2 = getNull(this.addressGlobal.getAddressTitle());
        String str3 = getNull(this.addressGlobal.getAddressDetail());
        String str4 = getNull(this.addressGlobal.getProvinceName()) + getNull(this.addressGlobal.getCityName()) + getNull(this.addressGlobal.getAreaName()) + getNull(this.addressGlobal.getTownName());
        if (TextUtils.isEmpty(str2)) {
            str = getNull(str3);
        } else {
            if (!TextUtils.equals(str2, str3)) {
                str4 = str4 + str3;
            }
            str = str2;
        }
        if (TextUtils.isEmpty(this.addressGlobal.getWhere())) {
            this.addressGlobal.setWhere(str4 + str2);
        }
        TextView textView = this.subAddress;
        if (textView != null) {
            textView.setText(str4);
        }
        TextView textView2 = this.addressDetail;
        if (textView2 != null) {
            textView2.setText(str);
        }
        boolean z = showAddress.isSelect;
        this.successSelect = z;
        if (z) {
            this.locationIcon.setImageResource(R.drawable.jd_address_select);
            this.addressDetail.setTypeface(Typeface.defaultFromStyle(1));
        } else {
            if (isDarkMode()) {
                this.locationIcon.setImageResource(R.drawable.un_icon_location_success_dark);
            } else {
                this.locationIcon.setImageResource(R.drawable.un_icon_location_success);
            }
            this.addressDetail.setTypeface(Typeface.defaultFromStyle(0));
        }
        requestCoverage(false);
    }

    public boolean showAddressEnable() {
        if (this.helper.showAddress().isSelect && hasLocation()) {
            return true;
        }
        return hasLocationPermission() && UnAddressSelectUtils.isOpenGps() && hasLocation();
    }

    public void showAddressNormal() {
        String str;
        this.customStyle = 0;
        AddressGlobal showAddressNormal = this.helper.showAddressNormal();
        if (showAddressNormal == null) {
            return;
        }
        removeAllViews();
        FrameLayout.inflate(getContext(), R.layout.un_location_state_view, this);
        this.rootLayout = (RelativeLayout) findViewById(R.id.rootLayout);
        this.locationIcon = (ImageView) findViewById(R.id.stateIcon);
        this.stateText = (TextView) findViewById(R.id.stateText);
        this.optText = (TextView) findViewById(R.id.optText);
        this.optIcon = (ImageView) findViewById(R.id.optIcon);
        this.optLayout = (LinearLayout) findViewById(R.id.optLayout);
        if (isDarkMode()) {
            darkMode();
        } else {
            normalMode();
        }
        if (isElderMode()) {
            elderMode();
        } else {
            defaultMode();
        }
        this.optText.setOnClickListener(this);
        normalOptSetting(false);
        this.optText.setVisibility(0);
        this.optText.setText("\u4fee\u6539");
        if (!TextUtils.isEmpty(showAddressNormal.getAddressTitle())) {
            str = showAddressNormal.getAddressTitle();
        } else if (!TextUtils.isEmpty(showAddressNormal.getAddressDetail())) {
            str = showAddressNormal.getAddressDetail();
        } else if (!TextUtils.isEmpty(showAddressNormal.getWhere())) {
            str = getNull(showAddressNormal.getWhere());
        } else {
            str = getNull(showAddressNormal.getProvinceName() + showAddressNormal.getCityName() + showAddressNormal.getAreaName() + showAddressNormal.getTownName());
        }
        this.stateText.setText(str);
    }

    public boolean showAddressNormalEnable() {
        AddressGlobal showAddressNormal = this.helper.showAddressNormal();
        if (showAddressNormal == null) {
            return false;
        }
        return (TextUtils.isEmpty(showAddressNormal.getWhere()) && TextUtils.isEmpty(showAddressNormal.getAddressDetail()) && TextUtils.isEmpty(showAddressNormal.getProvinceName())) ? false : true;
    }

    private void showGpsSettingDialog() {
        final JDCheckDialog createJdDialogWithStyle6 = JDDialogFactory.getInstance().createJdDialogWithStyle6(getContext(), "\u662f\u5426\u6253\u5f00\u624b\u673aGPS\uff1f", "\u6253\u5f00\u540e\u53ef\u5feb\u901f\u5b9a\u4f4d\u5f53\u524d\u5730\u5740", "\u53d6\u6d88", "\u786e\u5b9a");
        createJdDialogWithStyle6.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.LocationStateView.3
            {
                LocationStateView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LocationStateView.this.startGpsSetting();
                createJdDialogWithStyle6.dismiss();
            }
        });
        createJdDialogWithStyle6.show();
    }

    public void startGpsSetting() {
        this.startToSetting = true;
        getContext().startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
    }

    private void startPermissionSetting() {
        this.isCustomLocation = true;
        if (hasLocationPermission()) {
            LocManager.locateState = 1;
            startLocation();
            setStyle();
            return;
        }
        this.startToSetting = true;
        if (hasLocationPermission()) {
            LocManager.locateState = 1;
            startLocation();
            setStyle();
            return;
        }
        PermissionHelper.manualRequestLocationPermissionWithScene((Activity) getContext(), new PermissionHelper.PermissionSceneCallback() { // from class: com.jingdong.common.ui.LocationStateView.4
            {
                LocationStateView.this = this;
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionSceneCallback
            public void onAgree() {
                super.onAgree();
                LocManager.locateState = 1;
                LocationStateView.this.startLocation();
                LocationStateView.this.setStyle();
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onCanceled() {
                super.onCanceled();
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onDenied() {
                super.onDenied();
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionSceneCallback
            public void onDisagree() {
                super.onDisagree();
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onGranted() {
                super.onGranted();
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onIgnored() {
                super.onIgnored();
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onOpenSetting() {
                super.onOpenSetting();
                LocationStateView.this.startToSetting = true;
            }
        }, this.sceneId, "", "");
    }

    private void styleAlert() {
        int locateState = LocManager.getLocateState();
        if (locateState == -2) {
            this.optText.setVisibility(0);
            this.optText.setText("\u53bb\u5f00\u542f");
        } else if (locateState == -1) {
            this.optText.setVisibility(0);
            if (UnAddressSelectUtils.isOpenGps()) {
                this.optText.setText("\u624b\u52a8\u5b9a\u4f4d");
            } else {
                this.optText.setText("\u53bb\u6253\u5f00");
            }
        } else if (locateState == 0) {
            if (UnAddressSelectUtils.isOpenGps()) {
                this.optText.setText("\u624b\u52a8\u5b9a\u4f4d");
            } else {
                this.optText.setText("\u53bb\u6253\u5f00");
            }
        } else if (locateState != 1) {
        } else {
            if (UnAddressSelectUtils.isOpenGps()) {
                this.optText.setText("\u624b\u52a8\u5b9a\u4f4d");
            } else {
                this.optText.setText("\u53bb\u6253\u5f00");
            }
        }
    }

    private void styleNormal() {
        int locateState = LocManager.getLocateState();
        if (locateState == -2) {
            normalOptSetting(true);
            this.optText.setText("\u53bb\u5f00\u542f");
            this.optText.setVisibility(0);
            if (isDarkMode()) {
                this.locationIcon.setImageResource(R.drawable.un_icon_iocation_normal_dark);
            } else {
                this.locationIcon.setImageResource(R.drawable.un_icon_iocation_normal);
            }
        } else if (locateState == -1) {
            if (UnAddressSelectUtils.isOpenGps()) {
                normalOptSetting(false);
                this.optText.setVisibility(8);
            } else {
                normalOptSetting(true);
                this.optText.setVisibility(0);
                this.optText.setText("\u53bb\u5f00\u542f");
            }
            if (isDarkMode()) {
                this.locationIcon.setImageResource(R.drawable.un_icon_location_normal_fail_dark);
            } else {
                this.locationIcon.setImageResource(R.drawable.un_icon_location_normal_fail);
            }
        } else if (locateState == 0) {
            this.optText.setVisibility(0);
            this.optText.setText("\u4fee\u6539");
            normalOptSetting(false);
            if (isDarkMode()) {
                this.locationIcon.setImageResource(R.drawable.un_icon_location_success_dark);
            } else {
                this.locationIcon.setImageResource(R.drawable.un_icon_location_success);
            }
        } else if (locateState != 1) {
        } else {
            normalOptSetting(false);
            this.optText.setVisibility(8);
            if (isDarkMode()) {
                this.locationIcon.setImageResource(R.drawable.un_icon_iocation_normal_dark);
            } else {
                this.locationIcon.setImageResource(R.drawable.un_icon_iocation_normal);
            }
        }
    }

    private void toChangeLocationPage() {
        if (this.customStyle == 0) {
            this.isChangeNormalAddress = true;
        }
        Context context = this.context;
        if (!(context instanceof Activity) || this.mUseCustomMap) {
            return;
        }
        DeepLinkLocationAddressHelper.startLocationAddressActivity((Activity) context, this.requestCode);
    }

    public LocationStateView changeAlert() {
        this.customStyle = 1;
        removeAllViews();
        FrameLayout.inflate(getContext(), R.layout.un_location_state_alert_view, this);
        this.rootLayout = (RelativeLayout) findViewById(R.id.rootLayout);
        this.locationIcon = (ImageView) findViewById(R.id.stateIcon);
        this.stateText = (TextView) findViewById(R.id.stateText);
        this.optText = (TextView) findViewById(R.id.optText);
        this.stateText.setText(getStateText(LocManager.getLocateState()));
        if (isDarkMode()) {
            this.locationIcon.setImageResource(R.drawable.un_icon_location_alert_dark);
            this.rootLayout.setBackgroundResource(R.color.un_notify_bg_dark);
        } else {
            this.locationIcon.setImageResource(R.drawable.un_icon_location_alert);
            this.rootLayout.setBackgroundResource(R.color.c_FFFCD9);
        }
        styleAlert();
        this.optText.setOnClickListener(this);
        return this;
    }

    public LocationStateView changeNormal() {
        this.customStyle = 0;
        removeAllViews();
        FrameLayout.inflate(getContext(), R.layout.un_location_state_view, this);
        this.locationIcon = (ImageView) findViewById(R.id.stateIcon);
        this.stateText = (TextView) findViewById(R.id.stateText);
        this.optText = (TextView) findViewById(R.id.optText);
        this.optIcon = (ImageView) findViewById(R.id.optIcon);
        this.optLayout = (LinearLayout) findViewById(R.id.optLayout);
        this.rootLayout = (RelativeLayout) findViewById(R.id.rootLayout);
        this.stateText.setText(getStateText(LocManager.getLocateState()));
        styleNormal();
        if (isDarkMode()) {
            this.rootLayout.setBackgroundResource(R.drawable.un_location_state_normal_bg_dark);
            this.stateText.setTextColor(getResources().getColor(R.color.un_content_level_1_dark));
            this.optText.setTextColor(getResources().getColor(R.color.un_jd_main_dark));
            this.optIcon.setImageResource(R.drawable.un_address_jump_red_dark);
        } else {
            this.rootLayout.setBackgroundResource(R.drawable.un_location_state_normal_bg);
            this.stateText.setTextColor(getResources().getColor(R.color.un_content_level_1));
            this.optText.setTextColor(getResources().getColor(R.color.un_jd_main));
            this.optIcon.setImageResource(R.drawable.un_address_jump_red);
        }
        this.optText.setOnClickListener(this);
        return this;
    }

    public LocationStateView changeSuccess() {
        this.customStyle = 2;
        removeAllViews();
        FrameLayout.inflate(getContext(), R.layout.un_location_state_success_view, this);
        this.locationIcon = (ImageView) findViewById(R.id.stateIcon);
        this.stateText = (TextView) findViewById(R.id.stateText);
        this.subAddress = (TextView) findViewById(R.id.subAddress);
        this.addressDetail = (TextView) findViewById(R.id.addressDetail);
        this.coverageDisableLayout = (LinearLayout) findViewById(R.id.coverageDisableLayout);
        this.coverageLocation = (TextView) findViewById(R.id.location);
        this.coverageTip = (TextView) findViewById(R.id.coverageTip);
        TextView textView = (TextView) findViewById(R.id.optText);
        this.optText = textView;
        textView.setOnClickListener(this);
        this.coverageLocation.setOnClickListener(this);
        if (isDarkMode()) {
            this.locationIcon.setImageResource(R.drawable.un_icon_location_success_dark);
            this.stateText.setBackgroundResource(R.drawable.un_location_state_success_text_bg_dark);
            TextView textView2 = this.stateText;
            Resources resources = getResources();
            int i2 = R.color.un_content_level_2_dark;
            textView2.setTextColor(resources.getColor(i2));
            this.subAddress.setTextColor(getResources().getColor(i2));
            this.addressDetail.setTextColor(getResources().getColor(R.color.un_content_level_1_dark));
            TextView textView3 = this.coverageLocation;
            Resources resources2 = getResources();
            int i3 = R.color.un_jd_main_dark;
            textView3.setTextColor(resources2.getColor(i3));
            this.optText.setTextColor(getResources().getColor(i3));
        } else {
            this.locationIcon.setImageResource(R.drawable.un_icon_location_success);
            this.stateText.setBackgroundResource(R.drawable.un_location_state_success_text_bg);
            TextView textView4 = this.stateText;
            Resources resources3 = getResources();
            int i4 = R.color.un_content_level_2;
            textView4.setTextColor(resources3.getColor(i4));
            this.subAddress.setTextColor(getResources().getColor(i4));
            this.addressDetail.setTextColor(getResources().getColor(R.color.un_content_level_1));
            TextView textView5 = this.coverageLocation;
            Resources resources4 = getResources();
            int i5 = R.color.un_jd_main;
            textView5.setTextColor(resources4.getColor(i5));
            this.optText.setTextColor(getResources().getColor(i5));
        }
        return this;
    }

    public void clickChange() {
        if (!hasLocationPermission()) {
            startPermissionSetting();
        } else if (!UnAddressSelectUtils.isOpenGps()) {
            showGpsSettingDialog();
        } else {
            toChangeLocationPage();
        }
    }

    public LocationStateView defaultMode() {
        if (this.customStyle == 0) {
            TextView textView = this.stateText;
            if (textView != null) {
                textView.setTextSize(12.0f);
            }
            TextView textView2 = this.optText;
            if (textView2 != null) {
                textView2.setTextSize(12.0f);
            }
        }
        return this;
    }

    public AddressGlobal getAddressGlobal() {
        AddressGlobal addressGlobal = this.addressGlobal;
        if (addressGlobal != null) {
            return addressGlobal;
        }
        LocationStateViewEntity showAddress = this.helper.showAddress();
        if (showAddress != null && showAddress.address != null) {
            return this.helper.showAddress().address;
        }
        return createAddressWithLoc();
    }

    public TextView getStateText() {
        return this.stateText;
    }

    public boolean hasLocation() {
        AddressGlobal addressGlobal;
        LocationStateViewEntity showAddress = this.helper.showAddress();
        if (showAddress == null || (addressGlobal = showAddress.address) == null) {
            return false;
        }
        return !TextUtils.isEmpty(addressGlobal.getAddressDetail());
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
        if (isAutoDarkMode()) {
            return UnWidgetThemeController.getInstance().isDarkMode();
        }
        return this.isDarkMode;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public boolean isElderMode() {
        return JDElderModeUtils.isElderMode();
    }

    public boolean isItemClickBack() {
        return this.customStyle == 2;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.showListener != null && this.customStyle == 0) {
            Log.d("LocationStateView", "onAttachedToWindow");
            this.showListener.onShow(true, getShowState());
        }
        LocManager.getInstance().registStateListener(this);
        if (!hasLocation() && LocationStateViewHelper.canShowAlert()) {
            LocationStateViewHelper.putAlertShowTime();
        }
        if (EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.optText || id == R.id.location) {
            View.OnClickListener onClickListener = this.optTextClickListener;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
            optTextClick();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EventBus.getDefault().unregister(this);
        OnLocationViewShowListener onLocationViewShowListener = this.showListener;
        if (onLocationViewShowListener != null && this.customStyle == 0) {
            onLocationViewShowListener.onShow(false, getShowState());
        }
        LocManager.getInstance().unRegistSateListener(this);
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        if (baseEvent == null || !TextUtils.equals(baseEvent.getType(), LocationAddressEvent.LOCATION_ADDRESS_RESULT) || baseEvent.getBundle() == null) {
            return;
        }
        AddressGlobal addressGlobal = (AddressGlobal) baseEvent.getBundle().getParcelable(LocationAddressEvent.LOCATION_ADDRESS_RESULT);
        this.addressGlobal = addressGlobal;
        if (addressGlobal == null) {
            return;
        }
        int i2 = this.customStyle;
        if (i2 == 0 && this.isChangeNormalAddress) {
            setAddressGlobal(addressGlobal, false);
            normalOptSetting(false);
            this.stateText.setText(this.addressGlobal.getAddressDetail());
            ILocationViewListener iLocationViewListener = this.listener;
            if (iLocationViewListener != null) {
                iLocationViewListener.locationResult(this.addressGlobal);
            }
            this.isChangeNormalAddress = false;
            return;
        }
        this.canRefreshLocation = false;
        if (i2 == 1) {
            changeSuccess();
        }
        setSuccess(this.addressGlobal);
        this.locationIcon.setImageResource(R.drawable.jd_address_n);
        this.addressDetail.setTypeface(Typeface.defaultFromStyle(0));
    }

    @Override // com.jingdong.common.lbs.OnLbsStateListener
    public void onResult(int i2) {
        Log.d("LocationStateView", "lbsState:" + i2);
        if (i2 == -2) {
            setStyle();
        } else if (i2 == -1) {
            setStyle();
            ILocationViewListener iLocationViewListener = this.listener;
            if (iLocationViewListener != null) {
                iLocationViewListener.locationResult(null);
            }
        } else if (i2 != 0) {
        } else {
            setStyle();
            if (this.listener != null) {
                AddressGlobal locAddressGlobal = UnAddressSelectUtils.getLocAddressGlobal(this.sceneId);
                if (locAddressGlobal != null && !TextUtils.isEmpty(locAddressGlobal.getAddressDetail())) {
                    this.listener.locationResult(createAddressWithLoc());
                } else {
                    this.listener.locationResult(null);
                }
            }
        }
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public void refresh() {
        if (isDarkMode()) {
            darkMode();
        } else {
            normalMode();
        }
        if (isElderMode()) {
            elderMode();
        } else {
            defaultMode();
        }
    }

    public void resume() {
        Log.d("LocationStateView", "startToSetting: " + this.startToSetting);
        if (this.startToSetting) {
            this.startToSetting = false;
            if (hasLocationPermission()) {
                if (hasLocation()) {
                    ILocationViewListener iLocationViewListener = this.listener;
                    if (iLocationViewListener != null) {
                        iLocationViewListener.locationResult(UnAddressSelectUtils.getLocAddressGlobal(this.sceneId));
                    }
                } else if (UnAddressSelectUtils.isOpenGps()) {
                    LocManager.locateState = 1;
                    startLocation();
                }
                setStyle();
            }
        }
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public LocationStateView setAutoElderMode(boolean z) {
        return this;
    }

    public void setClickStateListener(ILocationClickStateListener iLocationClickStateListener) {
        this.clickStateListener = iLocationClickStateListener;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public LocationStateView setElderMode(boolean z) {
        return this;
    }

    public void setLocationViewListener(ILocationViewListener iLocationViewListener) {
        this.listener = iLocationViewListener;
    }

    public void setOptClickListener(View.OnClickListener onClickListener) {
        this.optTextClickListener = onClickListener;
    }

    public void setRequestCode(int i2) {
        this.requestCode = i2;
    }

    public void setSceneId(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.sceneId = str;
        this.helper.setSceneId(str);
    }

    public void setShopParam(ShopParam shopParam) {
        this.shopParam = shopParam;
    }

    public void setShowListener(OnLocationViewShowListener onLocationViewShowListener) {
        this.showListener = onLocationViewShowListener;
    }

    public void setStateChangeListener(ILocationStateChangeListener iLocationStateChangeListener) {
        this.stateChangeListener = iLocationStateChangeListener;
    }

    public void setStateTextLocationFail(String str) {
        this.stateTextLocationFail = str;
    }

    public void setStateTextLocationRunning(String str) {
        this.stateTextLocationRunning = str;
    }

    public void setStateTextNoPermission(String str) {
        this.stateTextNoPermission = str;
    }

    public void setStyle() {
        this.handler.post(new Runnable() { // from class: com.jingdong.common.ui.LocationStateView.2
            {
                LocationStateView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (LocationStateView.this.customStyle == 0) {
                    if (LocationStateView.this.isChangeNormalAddress) {
                        return;
                    }
                    if (LocationStateView.this.showAddressNormalEnable()) {
                        LocationStateView.this.showAddressNormal();
                        return;
                    }
                    LocationStateView.this.changeNormal();
                    if (LocationStateView.this.stateChangeListener != null) {
                        LocationStateView.this.stateChangeListener.stateChangeTo(3, LocationStateView.this.isGangAoTai(), LocationStateView.this.hasLocation(), LocationStateView.this.hasLocationPermission(), UnAddressSelectUtils.isOpenGps());
                    }
                } else if (LocationStateView.this.showAddressEnable()) {
                    LocationStateView.this.changeSuccess();
                    LocationStateView.this.setSuccess();
                    if (LocationStateView.this.stateChangeListener != null) {
                        LocationStateView.this.stateChangeListener.stateChangeTo(1, LocationStateView.this.isGangAoTai(), LocationStateView.this.hasLocation(), LocationStateView.this.hasLocationPermission(), UnAddressSelectUtils.isOpenGps());
                    }
                } else {
                    LocationStateView.this.changeAlert();
                    if (LocationStateView.this.stateChangeListener != null) {
                        LocationStateView.this.stateChangeListener.stateChangeTo(2, LocationStateView.this.isGangAoTai(), LocationStateView.this.hasLocation(), LocationStateView.this.hasLocationPermission(), UnAddressSelectUtils.isOpenGps());
                    }
                }
            }
        });
    }

    public void setSuccessForOutside(AddressGlobal addressGlobal) {
        if (addressGlobal == null || TextUtils.isEmpty(addressGlobal.getAddressDetail()) || LocManager.getLocateState() == -2) {
            return;
        }
        int i2 = this.customStyle;
        if (i2 == 1) {
            changeSuccess();
            setSuccess(addressGlobal);
        } else if (i2 == 2) {
            setSuccess(addressGlobal);
        }
    }

    public void setSuccessWithLoc() {
        AddressGlobal locAddressGlobal = UnAddressSelectUtils.getLocAddressGlobal(this.sceneId);
        if (locAddressGlobal != null) {
            String str = getNull(locAddressGlobal.getProvinceName()) + getNull(locAddressGlobal.getCityName()) + getNull(locAddressGlobal.getAreaName()) + getNull(locAddressGlobal.getTownName());
            String str2 = getNull(locAddressGlobal.getAddressDetail());
            TextView textView = this.subAddress;
            if (textView != null) {
                textView.setText(str);
            }
            TextView textView2 = this.addressDetail;
            if (textView2 != null) {
                textView2.setText(str2);
            }
        }
        this.addressGlobal = createAddressWithLoc();
    }

    public void setUseCustomMap(boolean z) {
        this.mUseCustomMap = z;
    }

    public void showMta() {
        if (this.showListener != null) {
            Log.d("LocationStateView", "showMta");
            this.showListener.onShow(true, getShowState());
        }
    }

    public void startLocation() {
        this.isCustomLocation = true;
        UnAddressSelectUtils.getLocAddress(this.sceneId, new OnAddressInfoListener() { // from class: com.jingdong.common.ui.LocationStateView.5
            {
                LocationStateView.this = this;
            }

            @Override // com.jingdong.common.ui.address.listener.OnAddressInfoListener
            public void onResult(final UnAddressInfo unAddressInfo) {
                LocationStateView.this.post(new Runnable() { // from class: com.jingdong.common.ui.LocationStateView.5.1
                    {
                        AnonymousClass5.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        UnAddressInfo unAddressInfo2 = unAddressInfo;
                        if (unAddressInfo2 != null) {
                            if (TextUtils.isEmpty(unAddressInfo2.detailAddress)) {
                                LocManager.locateState = -1;
                            } else {
                                LocManager.locateState = 0;
                            }
                            if (LocationStateView.this.listener != null) {
                                LocationStateView.this.listener.locationResult(UnAddressSelectUtils.addressInfoToAddressGlobal(unAddressInfo));
                            }
                            LocationStateView.this.isCustomLocation = false;
                        } else {
                            LocManager.locateState = -1;
                            if (LocationStateView.this.listener != null) {
                                LocationStateView.this.listener.locationResult(null);
                            }
                            LocationStateView.this.isCustomLocation = false;
                        }
                        LocationStateView.this.setStyle();
                    }
                });
            }
        });
    }

    public void startLocationByInit() {
        if (this.customStyle == 0) {
            return;
        }
        if (this.helper.showAddress().isSelect && hasLocation()) {
            return;
        }
        this.canRefreshLocation = true;
        if (hasLocationPermission() && UnAddressSelectUtils.isOpenGps() && !hasLocation()) {
            UnAddressSelectUtils.getLocAddress(this.sceneId, new OnAddressInfoListener() { // from class: com.jingdong.common.ui.LocationStateView.1
                {
                    LocationStateView.this = this;
                }

                @Override // com.jingdong.common.ui.address.listener.OnAddressInfoListener
                public void onResult(final UnAddressInfo unAddressInfo) {
                    if (unAddressInfo == null) {
                        return;
                    }
                    LocationStateView.this.post(new Runnable() { // from class: com.jingdong.common.ui.LocationStateView.1.1
                        {
                            AnonymousClass1.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            if (LocationStateView.this.canRefreshLocation) {
                                if (OKLog.D) {
                                    OKLog.d("LocationStateView", "startLocationByInit true:" + JDJSON.toJSONString(unAddressInfo));
                                }
                                LocationStateView.this.setStyle();
                            }
                        }
                    });
                }
            });
        }
    }

    private String getStateText(int i2) {
        if (i2 == -2) {
            if (TextUtils.isEmpty(this.stateTextNoPermission)) {
                if (this.customStyle == 0) {
                    this.stateTextNoPermission = "\u5f00\u542f\u5b9a\u4f4d\u6743\u9650\uff0c\u4e3a\u60a8\u5339\u914d\u5f53\u524d\u5e93\u5b58\u5546\u54c1";
                } else {
                    this.stateTextNoPermission = "\u60a8\u5c1a\u672a\u5f00\u542f\u5b9a\u4f4d\u6743\u9650\uff0c";
                }
            }
            return this.stateTextNoPermission;
        } else if (i2 == -1) {
            if (TextUtils.isEmpty(this.stateTextLocationFail)) {
                if (this.customStyle == 0) {
                    if (UnAddressSelectUtils.isOpenGps()) {
                        this.stateTextLocationFail = "\u5b9a\u4f4d\u5931\u8d25\uff0c\u5728\u201c\u7b5b\u9009-\u5730\u5740\u201d\u4e2d\u9009\u62e9\u5730\u5740";
                    } else {
                        this.stateTextLocationFail = "\u60a8\u672a\u6253\u5f00\u624b\u673aGPS";
                    }
                } else if (UnAddressSelectUtils.isOpenGps()) {
                    this.stateTextLocationFail = "\u5f53\u524dGPS\u4fe1\u53f7\u5f31\uff0c\u60a8\u53ef\u4ee5";
                } else {
                    this.stateTextLocationFail = "\u60a8\u672a\u6253\u5f00\u624b\u673aGPS\uff0c";
                }
            }
            return this.stateTextLocationFail;
        } else if (i2 != 0) {
            if (i2 != 1) {
                return "";
            }
            if (TextUtils.isEmpty(this.stateTextLocationRunning)) {
                if (this.customStyle == 0) {
                    this.stateTextLocationRunning = "\u6b63\u5728\u4e3a\u60a8\u5b9a\u4f4d\u2026\u2026";
                } else if (this.isCustomLocation) {
                    this.stateTextLocationRunning = "\u6b63\u5728\u4e3a\u60a8\u5b9a\u4f4d\u2026\u2026\uff0c";
                } else if (UnAddressSelectUtils.isOpenGps()) {
                    this.stateTextLocationRunning = "\u5f53\u524dGPS\u4fe1\u53f7\u5f31\uff0c\u60a8\u53ef\u4ee5";
                } else {
                    this.stateTextLocationRunning = "\u60a8\u672a\u6253\u5f00\u624b\u673aGPS\uff0c";
                }
            }
            return this.stateTextLocationRunning;
        } else {
            if (this.customStyle == 0) {
                AddressGlobal locAddressGlobal = UnAddressSelectUtils.getLocAddressGlobal(this.sceneId);
                if (locAddressGlobal != null) {
                    this.stateTextSuccess = getNull(locAddressGlobal.getProvinceName()) + getNull(locAddressGlobal.getCityName()) + getNull(locAddressGlobal.getAreaName()) + getNull(locAddressGlobal.getTownName()) + getNull(locAddressGlobal.getAddressDetail());
                }
            } else if (UnAddressSelectUtils.isOpenGps()) {
                this.stateTextSuccess = "\u5f53\u524dGPS\u4fe1\u53f7\u5f31\uff0c\u60a8\u53ef\u4ee5";
            } else {
                this.stateTextSuccess = "\u60a8\u672a\u6253\u5f00\u624b\u673aGPS\uff0c";
            }
            return this.stateTextSuccess;
        }
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public LocationStateView darkMode() {
        int i2 = this.customStyle;
        if (i2 == 2) {
            TextView textView = this.stateText;
            if (textView != null) {
                textView.setBackgroundResource(R.drawable.un_location_state_success_text_bg_dark);
                this.stateText.setTextColor(getResources().getColor(R.color.un_content_level_2_dark));
            }
            TextView textView2 = this.subAddress;
            if (textView2 != null) {
                textView2.setTextColor(getResources().getColor(R.color.un_content_level_2_dark));
            }
            TextView textView3 = this.addressDetail;
            if (textView3 != null) {
                Resources resources = getResources();
                int i3 = R.color.un_content_level_1_dark;
                textView3.setTextColor(resources.getColor(i3));
                this.addressDetail.setTextColor(getResources().getColor(i3));
            }
            TextView textView4 = this.coverageLocation;
            if (textView4 != null) {
                textView4.setTextColor(getResources().getColor(R.color.un_jd_main_dark));
            }
            TextView textView5 = this.optText;
            if (textView5 != null) {
                textView5.setTextColor(getResources().getColor(R.color.un_jd_main_dark));
            }
            ImageView imageView = this.locationIcon;
            if (imageView != null) {
                if (this.successSelect) {
                    imageView.setImageResource(R.drawable.jd_address_select);
                } else {
                    imageView.setImageResource(R.drawable.un_icon_location_success_dark);
                }
            }
        } else if (i2 == 1) {
            ImageView imageView2 = this.locationIcon;
            if (imageView2 != null) {
                imageView2.setImageResource(R.drawable.un_icon_location_alert_dark);
            }
            RelativeLayout relativeLayout = this.rootLayout;
            if (relativeLayout != null) {
                relativeLayout.setBackgroundResource(R.color.un_notify_bg_dark);
            }
        } else if (i2 == 0) {
            RelativeLayout relativeLayout2 = this.rootLayout;
            if (relativeLayout2 != null) {
                relativeLayout2.setBackgroundResource(R.drawable.un_location_state_normal_bg_dark);
            }
            ImageView imageView3 = this.locationIcon;
            if (imageView3 != null) {
                imageView3.setImageResource(R.drawable.un_icon_iocation_normal_dark);
            }
            TextView textView6 = this.stateText;
            if (textView6 != null) {
                textView6.setTextColor(getResources().getColor(R.color.un_content_level_1_dark));
            }
            TextView textView7 = this.optText;
            if (textView7 != null) {
                textView7.setTextColor(getResources().getColor(R.color.un_jd_main_dark));
            }
            ImageView imageView4 = this.optIcon;
            if (imageView4 != null) {
                imageView4.setImageResource(R.drawable.un_address_jump_red_dark);
            }
        }
        return this;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public LocationStateView elderMode() {
        if (this.customStyle == 0) {
            TextView textView = this.stateText;
            if (textView != null) {
                textView.setTextSize(14.0f);
            }
            TextView textView2 = this.optText;
            if (textView2 != null) {
                textView2.setTextSize(14.0f);
            }
        }
        return this;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public LocationStateView normalMode() {
        int i2 = this.customStyle;
        if (i2 == 2) {
            TextView textView = this.stateText;
            if (textView != null) {
                textView.setBackgroundResource(R.drawable.un_location_state_success_text_bg);
                this.stateText.setTextColor(getResources().getColor(R.color.un_content_level_2));
            }
            TextView textView2 = this.subAddress;
            if (textView2 != null) {
                textView2.setTextColor(getResources().getColor(R.color.un_content_level_2));
            }
            TextView textView3 = this.addressDetail;
            if (textView3 != null) {
                Resources resources = getResources();
                int i3 = R.color.un_content_level_1;
                textView3.setTextColor(resources.getColor(i3));
                this.addressDetail.setTextColor(getResources().getColor(i3));
            }
            TextView textView4 = this.coverageLocation;
            if (textView4 != null) {
                textView4.setTextColor(getResources().getColor(R.color.un_jd_main));
            }
            TextView textView5 = this.optText;
            if (textView5 != null) {
                textView5.setTextColor(getResources().getColor(R.color.un_jd_main));
            }
            ImageView imageView = this.locationIcon;
            if (imageView != null) {
                if (this.successSelect) {
                    imageView.setImageResource(R.drawable.jd_address_select);
                } else {
                    imageView.setImageResource(R.drawable.un_icon_location_success);
                }
            }
        } else if (i2 == 1) {
            ImageView imageView2 = this.locationIcon;
            if (imageView2 != null) {
                imageView2.setImageResource(R.drawable.un_icon_location_alert);
            }
            RelativeLayout relativeLayout = this.rootLayout;
            if (relativeLayout != null) {
                relativeLayout.setBackgroundResource(R.color.c_FFFCD9);
            }
        } else if (i2 == 0) {
            RelativeLayout relativeLayout2 = this.rootLayout;
            if (relativeLayout2 != null) {
                relativeLayout2.setBackgroundResource(R.drawable.un_location_state_normal_bg);
            }
            ImageView imageView3 = this.locationIcon;
            if (imageView3 != null) {
                imageView3.setImageResource(R.drawable.un_icon_iocation_normal);
            }
            TextView textView6 = this.stateText;
            if (textView6 != null) {
                textView6.setTextColor(getResources().getColor(R.color.un_content_level_1));
            }
            TextView textView7 = this.optText;
            if (textView7 != null) {
                textView7.setTextColor(getResources().getColor(R.color.un_jd_main));
            }
            ImageView imageView4 = this.optIcon;
            if (imageView4 != null) {
                imageView4.setImageResource(R.drawable.un_address_jump_red);
            }
        }
        return this;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public LocationStateView setAutoDarkMode(boolean z) {
        this.isAutoDark = z;
        return this;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public LocationStateView setDarkMode(boolean z) {
        this.isDarkMode = z;
        return this;
    }

    public LocationStateView(Context context, int i2) {
        super(context);
        this.showDisCoverageToast = false;
        this.mUseCustomMap = false;
        this.requestCode = 100;
        this.notOptMap = false;
        this.needStartLocationByInit = false;
        this.canRefreshLocation = true;
        this.isChangeNormalAddress = false;
        this.showAddressNormalEnable = false;
        this.context = context;
        this.customStyle = i2;
        initView(null);
    }

    public LocationStateView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.showDisCoverageToast = false;
        this.mUseCustomMap = false;
        this.requestCode = 100;
        this.notOptMap = false;
        this.needStartLocationByInit = false;
        this.canRefreshLocation = true;
        this.isChangeNormalAddress = false;
        this.showAddressNormalEnable = false;
        this.context = context;
        initView(attributeSet);
    }

    public void setSuccess(AddressGlobal addressGlobal) {
        if (addressGlobal == null) {
            return;
        }
        String str = getNull(addressGlobal.getAddressTitle());
        String str2 = getNull(addressGlobal.getAddressDetail());
        String str3 = getNull(addressGlobal.getProvinceName()) + getNull(addressGlobal.getCityName()) + getNull(addressGlobal.getAreaName()) + getNull(addressGlobal.getTownName());
        if (!TextUtils.isEmpty(str)) {
            if (!TextUtils.equals(str, str2)) {
                str3 = str3 + str2;
            }
            str2 = str;
        }
        if (TextUtils.isEmpty(this.addressGlobal.getWhere())) {
            this.addressGlobal.setWhere(str3 + str);
        }
        TextView textView = this.subAddress;
        if (textView != null) {
            textView.setText(str3);
        }
        TextView textView2 = this.addressDetail;
        if (textView2 != null) {
            textView2.setText(str2);
        }
        setAddressGlobal(addressGlobal, false);
        requestCoverage(true);
    }

    public LocationStateView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.showDisCoverageToast = false;
        this.mUseCustomMap = false;
        this.requestCode = 100;
        this.notOptMap = false;
        this.needStartLocationByInit = false;
        this.canRefreshLocation = true;
        this.isChangeNormalAddress = false;
        this.showAddressNormalEnable = false;
        this.context = context;
        initView(attributeSet);
    }

    @RequiresApi(api = 21)
    public LocationStateView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.showDisCoverageToast = false;
        this.mUseCustomMap = false;
        this.requestCode = 100;
        this.notOptMap = false;
        this.needStartLocationByInit = false;
        this.canRefreshLocation = true;
        this.isChangeNormalAddress = false;
        this.showAddressNormalEnable = false;
        this.context = context;
        initView(attributeSet);
    }
}
