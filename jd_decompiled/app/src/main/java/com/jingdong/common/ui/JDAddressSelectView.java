package com.jingdong.common.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.framework.json.JDJSON;
import com.jd.lib.un.address.R;
import com.jd.lib.un.global.theme.OnViewThemeConfig;
import com.jd.lib.un.global.theme.UnWidgetThemeController;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.UnLog;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.entity.AddressBaseMode;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.lbs.event.LocationAddressEvent;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.stickyListView.StickyHeaderAdapter;
import com.jingdong.common.stickyListView.StickyHeaderData;
import com.jingdong.common.stickyListView.StickyHeaderListView;
import com.jingdong.common.ui.JDSlideBar;
import com.jingdong.common.ui.address.LocationStateViewHelper;
import com.jingdong.common.ui.address.SelectAddressItemEntity;
import com.jingdong.common.ui.address.UnAddressConstants;
import com.jingdong.common.ui.address.entity.AreaBeanVO;
import com.jingdong.common.ui.address.entity.TabInfoBean;
import com.jingdong.common.ui.address.entity.UnAddressInfo;
import com.jingdong.common.ui.address.listener.OnAddressInfoListener;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.common.unification.title.theme.ThemeTitleHelper;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.widget.image.UnNetImageView;
import com.jingdong.jdsdk.utils.JDCityDataUtils;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class JDAddressSelectView extends RelativeLayout implements JDSlideBar.ISlideBarTouchChangeLisener, OnViewThemeConfig<JDAddressSelectView> {
    public static final int FLAG_ADDRESS = 1;
    public static final int FLAG_CITY = 3;
    public static final int FLAG_COUNTY = 4;
    public static final int FLAG_DIRECT = 11;
    public static final int FLAG_PROVINCE = 2;
    public static final int FLAG_TOWN = 5;
    public static final String INLAND_HOT_CITY = "inland_hot_city";
    public static final String INLAND_TAB = "inland_tab";
    public static final int ITEM_LOCATION = 1001;
    public static final String OVRE_SEAS_ACTION = "over_seas_action";
    public static final String OVRE_SEAS_HOT_CITY = "over_seas_hot_city";
    public static final String OVRE_SEAS_TAB = "over_seas_tab";
    public static final int SUM_INLAND = 101;
    public static final int SUM_SEAS = 102;
    private static final String TAG = JDAddressSelectView.class.getSimpleName();
    public static final int TYPE_CAR = 4;
    public static final int TYPE_DETAIL = 2;
    public static final int TYPE_ORDER = 3;
    public static final int TYPE_ORDER_ADDRESS = 5;
    public static final int TYPE_SEARCH = 1;
    private String areaCode1;
    private String areaCode2;
    private String areaCode3;
    private String areaCode4;
    private int cacheCity;
    private int cacheCounty;
    private boolean cacheIsForeignOverSea;
    private boolean cacheIsGangAoTai;
    private int cacheProvince;
    private int cacheTown;
    private ArrayList<AddressBaseMode> cityList;
    private int clickAddressEnable;
    private ArrayList<AddressBaseMode> countyList;
    private LinearLayout customContainer;
    private View deliverLine;
    private LinearLayout flLocation;
    private int[] gradientColor;
    private JDAddressGridAdapter gridAdapter;
    private boolean isAutoDark;
    public boolean isClick;
    private boolean isDarkMode;
    private boolean isUnSupportAddress;
    private JDSlideBar jdSlideBar;
    private TextView jdSlideBarNoteTv;
    private boolean locationFunction;
    private View locationLine;
    private boolean locationSwitch;
    private LocationStateView locationView;
    private MyAdapter mAdapter;
    private AddressGlobal mAddressGlobal;
    private AddressHelper mAddressHelper;
    private ArrayList<AddressGlobal> mAddressList;
    private TextView mAddressTip;
    public ImageView mBack;
    private ILocationClickStateListener mClickStateListener;
    private long mClickTime;
    public ImageView mClose;
    private Context mContext;
    public int mCurrent;
    private Handler mHandler;
    private boolean mHaveHotCityHeader;
    private long mHotAreaClickTime;
    public int mHotCityHeaderHeight;
    private View mHotCityHeaderView;
    private boolean mIsDestroy;
    private JMAHelper mJMAHelper;
    private StickyHeaderListView mListView;
    private View mLoadingView;
    private View mNavigatorView;
    private OnAddressLoadCompletedListener mOnAddressLoadCompletedListener;
    private View mRootView;
    private List<AddressBaseMode> mSeasProvienceList;
    private ArrayList<TextView> mSelectViewList;
    public int mSumCurrent;
    private View mSumNavigatorView;
    private long mSumTabClickTime;
    private TabIndicator mSumTabIndicator;
    private TabIndicator mTabIndicator;
    private View mTipLayout;
    private TextView mTitle;
    private View mTitleView;
    private int mType;
    private boolean needPosition;
    public int overSeasId;
    private float[] position;
    private ArrayList<AddressBaseMode> provinceList;
    private int requestCode;
    private String saveBusiness;
    private String sceneId;
    RelativeLayout slideBarLayout;
    private String source;
    private StickyHeaderData stickyHeaderData;
    private TextView sumInland;
    private TextView sumSeas;
    public boolean sumTabSwitch;
    private TextView textCity;
    private TextView textCounty;
    private TextView textProvince;
    private TextView textTown;
    private UnNetImageView titleBg;
    private RelativeLayout toOtherAddress;
    private ImageView toOtherImage;
    private TextView toOtherText;
    private boolean topCorners;
    private ArrayList<AddressBaseMode> townList;
    private View viewTemp;
    private View viewTempSum;

    /* loaded from: classes6.dex */
    public interface AddressHelper {
        void close();

        void loadAddress(int i2, String str, AddressGlobal addressGlobal, OnAddressLoadCompletedListener onAddressLoadCompletedListener);

        void onAddressSelected(int i2, AddressGlobal addressGlobal);

        void onHotCitySelected(String str, AddressGlobal addressGlobal);

        void onTabSelected(String str);

        boolean onThirdAddressHasNext(int i2, AddressBaseMode addressBaseMode);

        boolean onThirdAddressSelected(int i2, AddressBaseMode addressBaseMode);
    }

    /* loaded from: classes6.dex */
    public interface JMAHelper {
        void onCloseClick();

        void onSelectedOtherClick();
    }

    /* loaded from: classes6.dex */
    public interface OnAddressLoadCompletedListener {
        void onFullAddressLoadCompleted(boolean z, ArrayList<AddressGlobal> arrayList);

        void onThirdAddressLoadCompleted(boolean z, int i2, ArrayList<AddressBaseMode> arrayList, ArrayList<TabInfoBean> arrayList2, ArrayList<AddressBaseMode> arrayList3);
    }

    /* loaded from: classes6.dex */
    public interface OnItemHotClickListener {
        void onItemClick(AreaBeanVO areaBeanVO);
    }

    public JDAddressSelectView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.provinceList = null;
        this.cityList = null;
        this.countyList = null;
        this.townList = null;
        this.mCurrent = 1;
        this.mSumCurrent = 101;
        this.sumTabSwitch = false;
        this.overSeasId = 0;
        this.cacheProvince = 0;
        this.cacheCity = 0;
        this.cacheCounty = 0;
        this.cacheTown = 0;
        this.cacheIsForeignOverSea = false;
        this.cacheIsGangAoTai = false;
        this.mIsDestroy = false;
        this.mClickTime = 0L;
        this.mHotAreaClickTime = 0L;
        this.mSumTabClickTime = 0L;
        this.needPosition = false;
        this.locationFunction = false;
        this.locationSwitch = false;
        this.mHaveHotCityHeader = false;
        this.mHandler = new Handler();
        this.isAutoDark = false;
        this.gradientColor = null;
        this.position = null;
        this.topCorners = false;
        this.clickAddressEnable = -1;
        this.isClick = false;
        this.sceneId = "basicShoppingProcess";
        this.mOnAddressLoadCompletedListener = new OnAddressLoadCompletedListener() { // from class: com.jingdong.common.ui.JDAddressSelectView.1
            @Override // com.jingdong.common.ui.JDAddressSelectView.OnAddressLoadCompletedListener
            public void onFullAddressLoadCompleted(final boolean z, final ArrayList<AddressGlobal> arrayList) {
                JDAddressSelectView.this.mHandler.post(new Runnable() { // from class: com.jingdong.common.ui.JDAddressSelectView.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (JDAddressSelectView.this.mIsDestroy) {
                            return;
                        }
                        JDAddressSelectView.this.hideProgress();
                        JDAddressSelectView.this.removeHotCityHeader();
                        if (z) {
                            JDAddressSelectView.this.doAddress(arrayList);
                        }
                    }
                });
            }

            @Override // com.jingdong.common.ui.JDAddressSelectView.OnAddressLoadCompletedListener
            public void onThirdAddressLoadCompleted(final boolean z, final int i3, final ArrayList<AddressBaseMode> arrayList, final ArrayList<TabInfoBean> arrayList2, final ArrayList<AddressBaseMode> arrayList3) {
                JDAddressSelectView.this.mHandler.post(new Runnable() { // from class: com.jingdong.common.ui.JDAddressSelectView.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (!JDAddressSelectView.this.mIsDestroy && z) {
                            if (UnLog.D) {
                                UnLog.d(JDAddressSelectView.TAG, "mCurrent " + JDAddressSelectView.this.mCurrent + " flag " + i3);
                            }
                            ArrayList arrayList4 = arrayList;
                            if (arrayList4 != null && arrayList4.size() > 0) {
                                if (UnLog.D) {
                                    UnLog.d(JDAddressSelectView.TAG, "address sort");
                                }
                                JDAddressSelectView jDAddressSelectView = JDAddressSelectView.this;
                                if (jDAddressSelectView.sumTabSwitch) {
                                    jDAddressSelectView.sortAddressListProcess(arrayList);
                                } else if (i3 > 3 && jDAddressSelectView.mAddressGlobal != null && JDAddressSelectView.this.mAddressGlobal.isForeignOverSea) {
                                    JDAddressSelectView.this.sortAddressList(arrayList);
                                }
                            }
                            if (JDAddressSelectView.this.mType == 1) {
                                JDAddressSelectView.this.checkEndAndSave(arrayList);
                            }
                            int i4 = i3;
                            if (i4 == 2) {
                                JDAddressSelectView.this.initSumTabView(arrayList2, arrayList3);
                                ArrayList arrayList5 = arrayList;
                                if (arrayList5 != null && arrayList5.size() > 0) {
                                    JDAddressSelectView jDAddressSelectView2 = JDAddressSelectView.this;
                                    if (jDAddressSelectView2.sumTabSwitch) {
                                        jDAddressSelectView2.sortAddressListProcess(arrayList);
                                    }
                                }
                                if (!JDAddressSelectView.this.needPosition) {
                                    if (JDAddressSelectView.this.checkEndAndSave(arrayList)) {
                                        return;
                                    }
                                    JDAddressSelectView.this.doProvice(arrayList);
                                    return;
                                }
                                JDAddressSelectView jDAddressSelectView3 = JDAddressSelectView.this;
                                int i5 = jDAddressSelectView3.mSumCurrent;
                                if (i5 == 101) {
                                    jDAddressSelectView3.checkAndDoNext(i3, arrayList);
                                } else if (i5 == 102) {
                                    jDAddressSelectView3.checkAndDoNextSum(i3, arrayList);
                                }
                            } else if (i4 == 3) {
                                if (!JDAddressSelectView.this.needPosition) {
                                    if (JDAddressSelectView.this.checkEndAndSave(arrayList)) {
                                        return;
                                    }
                                    JDAddressSelectView.this.doCity(arrayList);
                                    return;
                                }
                                JDAddressSelectView jDAddressSelectView4 = JDAddressSelectView.this;
                                int i6 = jDAddressSelectView4.mSumCurrent;
                                if (i6 == 101) {
                                    jDAddressSelectView4.checkAndDoNext(i3, arrayList);
                                } else if (i6 == 102) {
                                    jDAddressSelectView4.checkAndDoNextSum(i3, arrayList);
                                }
                            } else if (i4 == 4) {
                                if (!JDAddressSelectView.this.needPosition) {
                                    if (JDAddressSelectView.this.checkEndAndSave(arrayList)) {
                                        return;
                                    }
                                    JDAddressSelectView.this.doCounty(arrayList);
                                    return;
                                }
                                JDAddressSelectView jDAddressSelectView5 = JDAddressSelectView.this;
                                int i7 = jDAddressSelectView5.mSumCurrent;
                                if (i7 == 101) {
                                    jDAddressSelectView5.checkAndDoNext(i3, arrayList);
                                } else if (i7 == 102) {
                                    jDAddressSelectView5.checkAndDoNextSum(i3, arrayList);
                                }
                            } else if (i4 != 5) {
                                if (i4 != 11) {
                                    return;
                                }
                                JDAddressSelectView.this.saveAddress();
                            } else if (!JDAddressSelectView.this.needPosition) {
                                if (JDAddressSelectView.this.checkEndAndSave(arrayList)) {
                                    return;
                                }
                                JDAddressSelectView.this.doTown(arrayList);
                            } else {
                                JDAddressSelectView jDAddressSelectView6 = JDAddressSelectView.this;
                                int i8 = jDAddressSelectView6.mSumCurrent;
                                if (i8 == 101) {
                                    jDAddressSelectView6.checkAndDoNext(i3, arrayList);
                                } else if (i8 == 102) {
                                    jDAddressSelectView6.checkAndDoNextSum(i3, arrayList);
                                }
                            }
                        }
                    }
                });
            }
        };
        this.mContext = context;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.JDAddressSelectView);
            int i3 = R.styleable.JDAddressSelectView_jasAutoDark;
            if (obtainStyledAttributes.hasValue(i3)) {
                this.isAutoDark = obtainStyledAttributes.getBoolean(i3, false);
            }
        }
        initView();
        this.mAddressGlobal = new AddressGlobal();
    }

    private void back2Address() {
        ArrayList<AddressGlobal> arrayList = this.mAddressList;
        if (arrayList != null && !arrayList.isEmpty()) {
            doAddress(new ArrayList<>(0));
        } else if (this.mAddressHelper != null) {
            showProgress();
            this.mAddressHelper.loadAddress(1, "", this.mAddressGlobal, this.mOnAddressLoadCompletedListener);
        }
    }

    private void backIconTheme(boolean z) {
        if (z) {
            this.mBack.setImageResource(ThemeTitleHelper.getDarkDrawable(ThemeTitleConstant.TITLE_BACK_DRAWABLE_ID));
        } else {
            this.mBack.setImageResource(ThemeTitleHelper.getLigthDrawable(ThemeTitleConstant.TITLE_BACK_DRAWABLE_ID));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAndDoNext(int i2, ArrayList<AddressBaseMode> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            int i3 = i2 - 1;
            if (i3 == 2) {
                this.mAddressGlobal.setIdCity(0);
                this.mAddressGlobal.setCityName("");
                this.mAddressGlobal.setIdArea(0);
                this.mAddressGlobal.setAreaName("");
                this.mAddressGlobal.setIdTown(0);
                this.mAddressGlobal.setTownName("");
                doProvice(null);
            } else if (i3 != 3) {
                if (i3 != 4) {
                    return;
                }
                this.mAddressGlobal.setIdTown(0);
                this.mAddressGlobal.setTownName("");
                doCounty(null);
            } else {
                this.mAddressGlobal.setIdArea(0);
                this.mAddressGlobal.setAreaName("");
                this.mAddressGlobal.setIdTown(0);
                this.mAddressGlobal.setTownName("");
                doCity(null);
            }
        } else if (i2 == 2) {
            int defaultPosition = getDefaultPosition(String.valueOf(this.mAddressGlobal.getIdProvince()), arrayList);
            if (defaultPosition == -1) {
                this.mAddressGlobal.setIdProvince(0);
                this.mAddressGlobal.setProvinceName("");
                this.mAddressGlobal.setIdCity(0);
                this.mAddressGlobal.setCityName("");
                this.mAddressGlobal.setIdArea(0);
                this.mAddressGlobal.setAreaName("");
                this.mAddressGlobal.setIdTown(0);
                this.mAddressGlobal.setTownName("");
                doProvice(arrayList);
            } else if (this.mAddressHelper != null) {
                AddressBaseMode addressBaseMode = arrayList.get(defaultPosition);
                this.mAddressGlobal.setIdProvince(getId(addressBaseMode));
                this.mAddressGlobal.setProvinceName(addressBaseMode.getName());
                if (this.mAddressHelper.onThirdAddressHasNext(i2, addressBaseMode)) {
                    this.provinceList = new ArrayList<>(arrayList);
                    queryData(addressBaseMode.getAction(), 3);
                    return;
                }
                this.mAddressGlobal.setIdCity(0);
                this.mAddressGlobal.setCityName("");
                this.mAddressGlobal.setIdArea(0);
                this.mAddressGlobal.setAreaName("");
                this.mAddressGlobal.setIdTown(0);
                this.mAddressGlobal.setTownName("");
                doProvice(arrayList);
            }
        } else if (i2 == 3) {
            int defaultPosition2 = getDefaultPosition(String.valueOf(this.mAddressGlobal.getIdCity()), arrayList);
            if (defaultPosition2 == -1) {
                this.mAddressGlobal.setIdCity(0);
                this.mAddressGlobal.setCityName("");
                this.mAddressGlobal.setIdArea(0);
                this.mAddressGlobal.setAreaName("");
                this.mAddressGlobal.setIdTown(0);
                this.mAddressGlobal.setTownName("");
                doCity(arrayList);
            } else if (this.mAddressHelper != null) {
                AddressBaseMode addressBaseMode2 = arrayList.get(defaultPosition2);
                this.mAddressGlobal.setIdCity(getId(addressBaseMode2));
                this.mAddressGlobal.setCityName(addressBaseMode2.getName());
                if (this.mAddressHelper.onThirdAddressHasNext(i2, addressBaseMode2)) {
                    this.cityList = new ArrayList<>(arrayList);
                    queryData(addressBaseMode2.getAction(), 4);
                    return;
                }
                this.mAddressGlobal.setIdArea(0);
                this.mAddressGlobal.setAreaName("");
                this.mAddressGlobal.setIdTown(0);
                this.mAddressGlobal.setTownName("");
                doCity(arrayList);
            }
        } else if (i2 != 4) {
            if (i2 != 5) {
                return;
            }
            int defaultPosition3 = getDefaultPosition(String.valueOf(this.mAddressGlobal.getIdTown()), arrayList);
            if (defaultPosition3 == -1) {
                this.mAddressGlobal.setIdTown(0);
                this.mAddressGlobal.setTownName("");
                doTown(arrayList);
            } else if (this.mAddressHelper != null) {
                AddressBaseMode addressBaseMode3 = arrayList.get(defaultPosition3);
                this.mAddressGlobal.setIdTown(getId(addressBaseMode3));
                this.mAddressGlobal.setTownName(addressBaseMode3.getName());
                doTown(arrayList);
            }
        } else {
            int defaultPosition4 = getDefaultPosition(String.valueOf(this.mAddressGlobal.getIdArea()), arrayList);
            if (defaultPosition4 == -1) {
                this.mAddressGlobal.setIdArea(0);
                this.mAddressGlobal.setAreaName("");
                this.mAddressGlobal.setIdTown(0);
                this.mAddressGlobal.setTownName("");
                doCounty(arrayList);
            } else if (this.mAddressHelper != null) {
                AddressBaseMode addressBaseMode4 = arrayList.get(defaultPosition4);
                this.mAddressGlobal.setIdArea(getId(addressBaseMode4));
                this.mAddressGlobal.setAreaName(addressBaseMode4.getName());
                if (this.mAddressHelper.onThirdAddressHasNext(i2, addressBaseMode4)) {
                    this.countyList = new ArrayList<>(arrayList);
                    queryData(addressBaseMode4.getAction(), 5);
                    return;
                }
                this.mAddressGlobal.setIdTown(0);
                this.mAddressGlobal.setTownName("");
                doCounty(arrayList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAndDoNextSum(int i2, ArrayList<AddressBaseMode> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            int i3 = i2 - 1;
            if (i3 == 2) {
                this.mAddressGlobal.setIdCity(0);
                this.mAddressGlobal.setCityName("");
                this.mAddressGlobal.setIdArea(0);
                this.mAddressGlobal.setAreaName("");
                this.mAddressGlobal.setIdTown(0);
                this.mAddressGlobal.setTownName("");
                doProvice(null);
            } else if (i3 != 3) {
                if (i3 != 4) {
                    return;
                }
                this.mAddressGlobal.setIdTown(0);
                this.mAddressGlobal.setTownName("");
                doCounty(null);
            } else {
                this.mAddressGlobal.setIdArea(0);
                this.mAddressGlobal.setAreaName("");
                this.mAddressGlobal.setIdTown(0);
                this.mAddressGlobal.setTownName("");
                doCity(null);
            }
        } else if (i2 == 2) {
            if (this.mAddressHelper != null) {
                AddressBaseMode addressBaseMode = new AddressBaseMode();
                if (this.mAddressHelper.onThirdAddressHasNext(i2, addressBaseMode)) {
                    this.provinceList = new ArrayList<>(arrayList);
                    if (this.mType == 3) {
                        addressBaseMode.action = "getCitys";
                        int idProvince = this.mAddressGlobal.getIdProvince();
                        this.mAddressGlobal.setIdProvince(this.overSeasId);
                        queryData(addressBaseMode.getAction(), 3);
                        this.mAddressGlobal.setIdProvince(idProvince);
                        return;
                    }
                    addressBaseMode.action = OVRE_SEAS_ACTION;
                    queryData(addressBaseMode.getAction(), 3);
                    return;
                }
                this.mAddressGlobal.setIdCity(0);
                this.mAddressGlobal.setCityName("");
                this.mAddressGlobal.setIdArea(0);
                this.mAddressGlobal.setAreaName("");
                this.mAddressGlobal.setIdTown(0);
                this.mAddressGlobal.setTownName("");
                doProvice(arrayList);
            }
        } else if (i2 == 3) {
            int defaultPosition = getDefaultPosition(String.valueOf(this.mAddressGlobal.getIdCity()), arrayList);
            if (defaultPosition == -1) {
                this.mAddressGlobal.setIdCity(0);
                this.mAddressGlobal.setCityName("");
                this.mAddressGlobal.setIdArea(0);
                this.mAddressGlobal.setAreaName("");
                this.mAddressGlobal.setIdTown(0);
                this.mAddressGlobal.setTownName("");
                doCity(arrayList);
            } else if (this.mAddressHelper != null) {
                AddressBaseMode addressBaseMode2 = arrayList.get(defaultPosition);
                this.mAddressGlobal.setIdCity(getId(addressBaseMode2));
                this.mAddressGlobal.setCityName(addressBaseMode2.getName());
                if (this.mAddressHelper.onThirdAddressHasNext(i2, addressBaseMode2)) {
                    this.cityList = new ArrayList<>(arrayList);
                    queryData(addressBaseMode2.getAction(), 4);
                    return;
                }
                this.mAddressGlobal.setIdArea(0);
                this.mAddressGlobal.setAreaName("");
                this.mAddressGlobal.setIdTown(0);
                this.mAddressGlobal.setTownName("");
                doCity(arrayList);
            }
        } else if (i2 != 4) {
            if (i2 != 5) {
                return;
            }
            int defaultPosition2 = getDefaultPosition(String.valueOf(this.mAddressGlobal.getIdTown()), arrayList);
            if (defaultPosition2 == -1) {
                this.mAddressGlobal.setIdTown(0);
                this.mAddressGlobal.setTownName("");
                doTown(arrayList);
            } else if (this.mAddressHelper != null) {
                AddressBaseMode addressBaseMode3 = arrayList.get(defaultPosition2);
                this.mAddressGlobal.setIdTown(getId(addressBaseMode3));
                this.mAddressGlobal.setTownName(addressBaseMode3.getName());
                doTown(arrayList);
            }
        } else {
            int defaultPosition3 = getDefaultPosition(String.valueOf(this.mAddressGlobal.getIdArea()), arrayList);
            if (defaultPosition3 == -1) {
                this.mAddressGlobal.setIdArea(0);
                this.mAddressGlobal.setAreaName("");
                this.mAddressGlobal.setIdTown(0);
                this.mAddressGlobal.setTownName("");
                doCounty(arrayList);
            } else if (this.mAddressHelper != null) {
                AddressBaseMode addressBaseMode4 = arrayList.get(defaultPosition3);
                this.mAddressGlobal.setIdArea(getId(addressBaseMode4));
                this.mAddressGlobal.setAreaName(addressBaseMode4.getName());
                if (this.mAddressHelper.onThirdAddressHasNext(i2, addressBaseMode4)) {
                    this.countyList = new ArrayList<>(arrayList);
                    queryData(addressBaseMode4.getAction(), 5);
                    return;
                }
                this.mAddressGlobal.setIdTown(0);
                this.mAddressGlobal.setTownName("");
                doCounty(arrayList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkEndAndSave(ArrayList<AddressBaseMode> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            this.mAddressHelper.close();
            if (this.mCurrent != 2) {
                saveAddress();
                return true;
            }
            return true;
        }
        return false;
    }

    private void cloneAddressCommon(AddressGlobal addressGlobal) {
        if (addressGlobal != null) {
            int i2 = this.mType;
            if (i2 != 4 && i2 != 2) {
                this.mAddressGlobal.setId(addressGlobal.getId());
            }
            this.mAddressGlobal.setIdProvince(addressGlobal.getIdProvince());
            this.mAddressGlobal.setIdCity(addressGlobal.getIdCity());
            this.mAddressGlobal.setIdArea(addressGlobal.getIdArea());
            this.mAddressGlobal.setIdTown(addressGlobal.getIdTown());
            this.mAddressGlobal.setWhere(addressGlobal.getWhere());
            this.mAddressGlobal.setProvinceName(addressGlobal.getProvinceName());
            this.mAddressGlobal.setCityName(addressGlobal.getCityName());
            this.mAddressGlobal.setAreaName(addressGlobal.getAreaName());
            this.mAddressGlobal.setTownName(addressGlobal.getTownName());
            this.mAddressGlobal.setLatitude(addressGlobal.getLatitude());
            this.mAddressGlobal.setLongitude(addressGlobal.getLongitude());
            this.mAddressGlobal.setAddressDetail(addressGlobal.getAddressDetail());
            this.mAddressGlobal.setAddressDefault(addressGlobal.getAddressDefault());
            this.mAddressGlobal.setSubAddressDetail(addressGlobal.getSubAddressDetail());
            this.mAddressGlobal.setAddressTitle(addressGlobal.getAddressTitle());
            this.mAddressGlobal.setCoordType(addressGlobal.getCoordType());
            AddressGlobal addressGlobal2 = this.mAddressGlobal;
            addressGlobal2.isForeignOverSea = addressGlobal.isForeignOverSea;
            addressGlobal2.areaCode = addressGlobal.areaCode;
            addressGlobal2.isGangAoTai = addressGlobal.isGangAoTai;
            addressGlobal2.nameCode = addressGlobal.nameCode;
            addressGlobal2.addressTagMap = addressGlobal.addressTagMap;
            addressGlobal2.email = addressGlobal.email;
            addressGlobal2.areaCode = addressGlobal.areaCode;
            addressGlobal2.postCode = addressGlobal.postCode;
        }
    }

    private void cloneAddressThreeAddress(AddressGlobal addressGlobal) {
        int i2;
        if (addressGlobal != null && (i2 = this.mType) != 4 && i2 != 2) {
            this.mAddressGlobal.setId(addressGlobal.getId());
        }
        cloneAddressCommon(addressGlobal);
    }

    private void cloneAddressUsualAddress(AddressGlobal addressGlobal) {
        if (addressGlobal != null) {
            this.mAddressGlobal.setId(addressGlobal.getId());
        }
        cloneAddressCommon(addressGlobal);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doAddress(ArrayList<AddressGlobal> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            this.mAddressList = new ArrayList<>(arrayList);
        }
        ArrayList<AddressGlobal> arrayList2 = this.mAddressList;
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            this.mBack.setVisibility(8);
            int defaultPosition = getDefaultPosition(this.mAddressGlobal.getId(), this.mAddressList);
            if (moveSelectToTop(defaultPosition, this.mAddressList)) {
                defaultPosition = 0;
            }
            showSelector(1, getFullAddressStringArray(this.mAddressList), defaultPosition);
        }
        if (TextUtils.equals(this.source, UnAddressConstants.ADDRESS_SOURCE_PRODUCT_DETAIL)) {
            if (UnAddressSelectUtils.canShowLocWidgetBySource(this.source, true)) {
                if (this.flLocation.getVisibility() != 0) {
                    this.locationView.showMta();
                }
                if (OKLog.D) {
                    OKLog.d(TAG, "doAddress show Location");
                }
                this.flLocation.setVisibility(0);
            } else {
                this.flLocation.setVisibility(8);
            }
        } else {
            this.flLocation.setVisibility(8);
        }
        this.mSumNavigatorView.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doInland() {
        if (this.mIsDestroy || this.mSumCurrent == 101) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.mSumTabClickTime < 1000) {
            return;
        }
        this.mSumTabClickTime = currentTimeMillis;
        AddressGlobal addressGlobal = new AddressGlobal();
        addressGlobal.setIdProvince(this.cacheProvince);
        addressGlobal.setIdCity(this.cacheCity);
        addressGlobal.setIdArea(this.cacheCounty);
        addressGlobal.setIdTown(this.cacheTown);
        addressGlobal.isForeignOverSea = false;
        addressGlobal.isGangAoTai = false;
        this.cacheProvince = this.mAddressGlobal.getIdProvince();
        this.cacheCity = this.mAddressGlobal.getIdCity();
        this.cacheCounty = this.mAddressGlobal.getIdArea();
        this.cacheTown = this.mAddressGlobal.getIdTown();
        AddressGlobal addressGlobal2 = this.mAddressGlobal;
        this.cacheIsForeignOverSea = addressGlobal2.isForeignOverSea;
        this.cacheIsGangAoTai = addressGlobal2.isGangAoTai;
        int i2 = this.mCurrent;
        if (i2 > 1) {
            this.mCurrent = i2 - 1;
        }
        this.mAddressHelper.onTabSelected(INLAND_TAB);
        showThreeAddress(addressGlobal);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doProvice(ArrayList<AddressBaseMode> arrayList) {
        ArrayList<AddressGlobal> arrayList2;
        if (arrayList != null) {
            this.provinceList = new ArrayList<>(arrayList);
        }
        SelectAddressItemEntity[] addressStringArray = getAddressStringArray(this.provinceList);
        if (addressStringArray.length > 0) {
            if (LoginUserBase.hasLogin() && (arrayList2 = this.mAddressList) != null && !arrayList2.isEmpty()) {
                this.mBack.setVisibility(0);
            }
            showSelector(2, addressStringArray, getDefaultPosition(String.valueOf(this.mAddressGlobal.getIdProvince()), this.provinceList));
        }
        showLocationView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doSeas() {
        if (this.mIsDestroy || this.mSumCurrent == 102) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.mSumTabClickTime < 1000) {
            return;
        }
        this.mSumTabClickTime = currentTimeMillis;
        AddressGlobal addressGlobal = new AddressGlobal();
        addressGlobal.setIdProvince(this.cacheProvince);
        addressGlobal.setIdCity(this.cacheCity);
        addressGlobal.setIdArea(this.cacheCounty);
        addressGlobal.setIdTown(this.cacheTown);
        boolean z = this.cacheIsForeignOverSea;
        if (!z && !this.cacheIsGangAoTai) {
            addressGlobal.isForeignOverSea = true;
            addressGlobal.isGangAoTai = true;
        } else {
            addressGlobal.isForeignOverSea = z;
            addressGlobal.isGangAoTai = this.cacheIsGangAoTai;
        }
        this.cacheProvince = this.mAddressGlobal.getIdProvince();
        this.cacheCity = this.mAddressGlobal.getIdCity();
        this.cacheCounty = this.mAddressGlobal.getIdArea();
        this.cacheTown = this.mAddressGlobal.getIdTown();
        AddressGlobal addressGlobal2 = this.mAddressGlobal;
        this.cacheIsForeignOverSea = addressGlobal2.isForeignOverSea;
        this.cacheIsGangAoTai = addressGlobal2.isGangAoTai;
        int i2 = this.mCurrent;
        if (i2 > 0) {
            this.mCurrent = i2 + 1;
        }
        this.mAddressHelper.onTabSelected(OVRE_SEAS_TAB);
        showThreeAddress(addressGlobal);
    }

    private SelectAddressItemEntity[] getAddressStringArray(ArrayList<AddressBaseMode> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            return new SelectAddressItemEntity[0];
        }
        SelectAddressItemEntity[] selectAddressItemEntityArr = new SelectAddressItemEntity[arrayList.size()];
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            SelectAddressItemEntity selectAddressItemEntity = new SelectAddressItemEntity();
            selectAddressItemEntity.content = arrayList.get(i2).getName();
            selectAddressItemEntity.style = arrayList.get(i2).style;
            selectAddressItemEntityArr[i2] = selectAddressItemEntity;
        }
        return selectAddressItemEntityArr;
    }

    private String getBackupDataFromList(AddressBaseMode addressBaseMode, int i2) {
        try {
            List<AddressBaseMode> list = this.mSeasProvienceList;
            if (list == null || list.size() <= 0) {
                return "";
            }
            for (AddressBaseMode addressBaseMode2 : this.mSeasProvienceList) {
                if (addressBaseMode2 != null && addressBaseMode.parentId.equals(addressBaseMode2.id)) {
                    if (i2 == 0) {
                        return addressBaseMode2.getName();
                    }
                    if (i2 == 1) {
                        return addressBaseMode2.getId();
                    }
                    if (i2 == 2) {
                        return addressBaseMode2.areaCode;
                    }
                }
            }
            return "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getCommonId(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e2) {
            if (UnLog.E) {
                UnLog.e(TAG, e2.getMessage());
                return 0;
            }
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getDefaultPosition(String str, ArrayList<AddressBaseMode> arrayList) {
        if ("0".equals(str) || TextUtils.isEmpty(str) || arrayList == null) {
            return -1;
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (str.equals(arrayList.get(i2).getId())) {
                return i2;
            }
        }
        return -1;
    }

    private SelectAddressItemEntity[] getFullAddressStringArray(ArrayList<AddressGlobal> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            return new SelectAddressItemEntity[0];
        }
        SelectAddressItemEntity[] selectAddressItemEntityArr = new SelectAddressItemEntity[arrayList.size()];
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            SelectAddressItemEntity selectAddressItemEntity = new SelectAddressItemEntity();
            String addressDetail = arrayList.get(i2).getAddressDetail();
            selectAddressItemEntity.detail = addressDetail;
            String where = arrayList.get(i2).getWhere();
            if (TextUtils.isEmpty(where)) {
                selectAddressItemEntity.content = arrayList.get(i2).getProvinceName() + arrayList.get(i2).getCityName() + arrayList.get(i2).getAreaName() + arrayList.get(i2).getTownName();
            } else {
                selectAddressItemEntity.content = where.replace(addressDetail, "");
            }
            selectAddressItemEntityArr[i2] = selectAddressItemEntity;
        }
        return selectAddressItemEntityArr;
    }

    private int getHeaderHeight(int i2) {
        if (this.mListView.getSectionHeaderView(i2, null) == null) {
            return 0;
        }
        if (this.sumTabSwitch && this.mHaveHotCityHeader) {
            return DpiUtil.dip2px(getContext(), this.mHotCityHeaderHeight);
        }
        return DpiUtil.dip2px(getContext(), 38.0f);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0159 A[Catch: Exception -> 0x01ca, TryCatch #0 {Exception -> 0x01ca, blocks: (B:43:0x0155, B:45:0x0159, B:51:0x0174, B:53:0x0178, B:57:0x0181, B:59:0x018b, B:60:0x01b7, B:54:0x017c, B:47:0x0165), top: B:65:0x0155 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0173 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0174 A[Catch: Exception -> 0x01ca, TryCatch #0 {Exception -> 0x01ca, blocks: (B:43:0x0155, B:45:0x0159, B:51:0x0174, B:53:0x0178, B:57:0x0181, B:59:0x018b, B:60:0x01b7, B:54:0x017c, B:47:0x0165), top: B:65:0x0155 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean getHotCityHeaderView() {
        /*
            Method dump skipped, instructions count: 463
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.ui.JDAddressSelectView.getHotCityHeaderView():boolean");
    }

    private int getId(AddressBaseMode addressBaseMode) {
        if (addressBaseMode != null) {
            try {
                return Integer.parseInt(addressBaseMode.getId());
            } catch (Exception e2) {
                if (UnLog.E) {
                    UnLog.e(TAG, e2.getMessage());
                    return 0;
                }
                return 0;
            }
        }
        return 0;
    }

    private String getInfoFromCitys(int i2, int i3) {
        int defaultPosition;
        AddressBaseMode addressBaseMode;
        if (this.cityList != null && (defaultPosition = getDefaultPosition(String.valueOf(i2), this.cityList)) != -1 && (addressBaseMode = this.cityList.get(defaultPosition)) != null) {
            if (i3 == 0) {
                return addressBaseMode.parentName;
            }
            if (i3 == 1) {
                return addressBaseMode.parentId;
            }
            if (i3 == 2) {
                return addressBaseMode.areaCode;
            }
        }
        return "";
    }

    private View getNavView(int i2) {
        int i3 = this.mSumCurrent;
        if (i3 == 101) {
            if (i2 == 2) {
                return this.textProvince;
            }
            if (i2 == 3) {
                return this.textCity;
            }
            if (i2 == 4) {
                return this.textCounty;
            }
            if (i2 == 5) {
                return this.textTown;
            }
        } else if (i3 == 102) {
            if (i2 == 3) {
                return this.textProvince;
            }
            if (i2 == 4) {
                return this.textCity;
            }
            if (i2 == 5) {
                return this.textCounty;
            }
        }
        return null;
    }

    private String getNull(String str) {
        return str == null ? "" : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View getOldView() {
        return getNavView(this.mCurrent);
    }

    private int getParentId(AddressBaseMode addressBaseMode) {
        if (addressBaseMode != null) {
            try {
                return Integer.parseInt(addressBaseMode.getParentId());
            } catch (Exception e2) {
                if (UnLog.E) {
                    UnLog.e(TAG, e2.getMessage());
                    return 0;
                }
                return 0;
            }
        }
        return 0;
    }

    private View getSumNavView(int i2) {
        if (i2 != 101) {
            if (i2 != 102) {
                return this.sumInland;
            }
            return this.sumSeas;
        }
        return this.sumInland;
    }

    private View getSumOldView() {
        return getSumNavView(this.mSumCurrent);
    }

    private void handleElder(int i2) {
        if (this.mTitle != null) {
            if (isElderMode() && i2 == 1) {
                this.mTitle.setTypeface(Typeface.defaultFromStyle(1));
            } else {
                this.mTitle.setTypeface(Typeface.defaultFromStyle(0));
            }
        }
        if (this.toOtherImage == null || this.toOtherText == null) {
            return;
        }
        if (isElderMode() && i2 == 1) {
            this.toOtherImage.setVisibility(0);
            this.toOtherText.setText(R.string.un_address_select_other_address_elder);
            this.toOtherText.setTextSize(16.0f);
            return;
        }
        this.toOtherImage.setVisibility(8);
        this.toOtherText.setText(R.string.un_address_select_other_address);
        this.toOtherText.setTextSize(15.0f);
    }

    private void hiddenSlideBar() {
        JDSlideBar jDSlideBar = this.jdSlideBar;
        if (jDSlideBar != null) {
            jDSlideBar.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideProgress() {
        View view = this.mLoadingView;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initSumTabView(List<TabInfoBean> list, List<AddressBaseMode> list2) {
        String str;
        String str2;
        if (list != null && list.size() > 1) {
            this.sumTabSwitch = true;
        } else {
            this.sumTabSwitch = false;
        }
        if (list2 != null && list2.size() > 0) {
            this.mSeasProvienceList = list2;
        }
        if (this.sumTabSwitch) {
            TabInfoBean tabInfoBean = list.get(0);
            TabInfoBean tabInfoBean2 = list.get(1);
            if (tabInfoBean == null || tabInfoBean2 == null) {
                str = "\u4e2d\u56fd\u5927\u9646";
                str2 = "\u6e2f\u6fb3\u53f0\u53ca\u6d77\u5916";
            } else {
                str = tabInfoBean.getName();
                str2 = tabInfoBean2.getName();
                this.overSeasId = getCommonId(tabInfoBean2.getTabId());
            }
            AddressGlobal addressGlobal = this.mAddressGlobal;
            if (!addressGlobal.isGangAoTai && !addressGlobal.isForeignOverSea) {
                showSumNavTab(101);
            } else {
                showSumNavTab(102);
            }
            TextView textView = this.sumInland;
            if (textView != null && this.sumSeas != null) {
                textView.setText(str);
                this.sumSeas.setText(str2);
            }
            this.mSumNavigatorView.setVisibility(0);
            this.mTabIndicator.setVisibility(8);
            return;
        }
        this.mSumCurrent = 101;
        this.mSumNavigatorView.setVisibility(8);
        this.mTabIndicator.setVisibility(0);
    }

    private void initView() {
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.jingdong.common.ui.JDAddressSelectView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (UnLog.D) {
                    UnLog.d(JDAddressSelectView.TAG, "sum view click");
                }
                if (view == null) {
                    return;
                }
                if (view.getId() == R.id.txt_sum_inland) {
                    if (UnLog.D) {
                        UnLog.d(JDAddressSelectView.TAG, "view click txt_sum_inland");
                    }
                    JDAddressSelectView.this.doInland();
                } else if (view.getId() == R.id.txt_sum_seas) {
                    if (UnLog.D) {
                        UnLog.d(JDAddressSelectView.TAG, "view click txt_sum_seas");
                    }
                    JDAddressSelectView.this.doSeas();
                }
            }
        };
        View.OnClickListener onClickListener2 = new View.OnClickListener() { // from class: com.jingdong.common.ui.JDAddressSelectView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (UnLog.D) {
                    UnLog.d(JDAddressSelectView.TAG, "view click");
                }
                if (JDAddressSelectView.this.getOldView() == view) {
                    return;
                }
                JDAddressSelectView jDAddressSelectView = JDAddressSelectView.this;
                jDAddressSelectView.isClick = true;
                int i2 = jDAddressSelectView.mSumCurrent;
                if (i2 != 101) {
                    if (i2 == 102) {
                        if (view.getId() == R.id.txt_1) {
                            if (UnLog.D) {
                                UnLog.d(JDAddressSelectView.TAG, "seas click txt_1");
                            }
                            JDAddressSelectView.this.doCity(null);
                        } else if (view.getId() == R.id.txt_2) {
                            if (UnLog.D) {
                                UnLog.d(JDAddressSelectView.TAG, "seas click txt_2");
                            }
                            JDAddressSelectView.this.doCounty(null);
                        } else if (view.getId() == R.id.txt_3) {
                            if (UnLog.D) {
                                UnLog.d(JDAddressSelectView.TAG, "seas click txt_3");
                            }
                            JDAddressSelectView.this.doTown(null);
                        }
                    }
                } else if (view.getId() == R.id.txt_1) {
                    if (UnLog.D) {
                        UnLog.d(JDAddressSelectView.TAG, "inland click txt_1");
                    }
                    JDAddressSelectView.this.doProvice(null);
                } else if (view.getId() == R.id.txt_2) {
                    if (UnLog.D) {
                        UnLog.d(JDAddressSelectView.TAG, "inland click txt_2");
                    }
                    JDAddressSelectView.this.doCity(null);
                } else if (view.getId() == R.id.txt_3) {
                    if (UnLog.D) {
                        UnLog.d(JDAddressSelectView.TAG, "inland click txt_3");
                    }
                    JDAddressSelectView.this.doCounty(null);
                } else if (view.getId() == R.id.txt_4) {
                    if (UnLog.D) {
                        UnLog.d(JDAddressSelectView.TAG, "inland click txt_4");
                    }
                    JDAddressSelectView.this.doTown(null);
                }
            }
        };
        RelativeLayout.inflate(getContext(), R.layout.jd_address_layout, this);
        this.mRootView = findViewById(R.id.rootLayout);
        this.mListView = (StickyHeaderListView) findViewById(R.id.listView_1);
        this.mLoadingView = findViewById(R.id.pd_info_loading_pb);
        View findViewById = findViewById(R.id.l_layout_2);
        this.viewTemp = findViewById;
        this.mTabIndicator = (TabIndicator) findViewById.findViewById(R.id.indicator);
        this.position = new float[]{0.0f, 0.1f, 0.1f, 1.0f};
        View findViewById2 = findViewById(R.id.l_layout_sum);
        this.viewTempSum = findViewById2;
        this.mSumTabIndicator = (TabIndicator) findViewById2.findViewById(R.id.indicator_sum);
        this.slideBarLayout = (RelativeLayout) findViewById(R.id.slide_bar_view);
        this.jdSlideBar = (JDSlideBar) findViewById(R.id.slide_bar);
        TextView textView = (TextView) findViewById(R.id.slide_note_tv);
        this.jdSlideBarNoteTv = textView;
        this.jdSlideBar.setNotesTextView(textView);
        this.jdSlideBar.setCallback(this);
        this.locationLine = findViewById(R.id.location_line);
        View findViewById3 = findViewById(R.id.l_layout_1);
        this.mTitleView = findViewById3;
        ImageView imageView = (ImageView) findViewById3.findViewById(R.id.img_1);
        this.mBack = imageView;
        imageView.setVisibility(8);
        this.mTitle = (TextView) this.mTitleView.findViewById(R.id.tv_address_title);
        setTitleText(false);
        this.mClose = (ImageView) this.mTitleView.findViewById(R.id.img_2);
        this.textProvince = (TextView) this.viewTemp.findViewById(R.id.txt_1);
        this.textCity = (TextView) this.viewTemp.findViewById(R.id.txt_2);
        this.textCounty = (TextView) this.viewTemp.findViewById(R.id.txt_3);
        this.textTown = (TextView) this.viewTemp.findViewById(R.id.txt_4);
        this.textProvince.setOnClickListener(onClickListener2);
        this.textCity.setOnClickListener(onClickListener2);
        this.textCounty.setOnClickListener(onClickListener2);
        this.textTown.setOnClickListener(onClickListener2);
        ArrayList<TextView> arrayList = new ArrayList<>();
        this.mSelectViewList = arrayList;
        arrayList.add(this.textProvince);
        this.mSelectViewList.add(this.textCity);
        this.mSelectViewList.add(this.textCounty);
        this.mSelectViewList.add(this.textTown);
        this.sumInland = (TextView) this.viewTempSum.findViewById(R.id.txt_sum_inland);
        this.sumSeas = (TextView) this.viewTempSum.findViewById(R.id.txt_sum_seas);
        this.sumInland.setOnClickListener(onClickListener);
        this.sumSeas.setOnClickListener(onClickListener);
        this.toOtherAddress = (RelativeLayout) findViewById(R.id.to_other_layout);
        this.toOtherImage = (ImageView) findViewById(R.id.to_other_im);
        this.toOtherText = (TextView) findViewById(R.id.to_other_txt);
        if (this.mType == 5) {
            this.toOtherAddress.setVisibility(8);
        } else {
            this.toOtherAddress.setVisibility(0);
        }
        this.deliverLine = findViewById(R.id.layout_foot_diliver);
        this.mTipLayout = findViewById(R.id.layout_foot_ques);
        this.mAddressTip = (TextView) findViewById(R.id.layout_foot_tip);
        View view = this.viewTempSum;
        this.mSumNavigatorView = view;
        view.setVisibility(8);
        View view2 = this.viewTemp;
        this.mNavigatorView = view2;
        view2.setVisibility(8);
        this.toOtherAddress.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.JDAddressSelectView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view3) {
                if (JDAddressSelectView.this.canJumpToMap()) {
                    JDAddressSelectView.this.locationView.clickChange();
                } else {
                    JDAddressSelectView.this.initProvinceClick();
                }
                if (JDAddressSelectView.this.mJMAHelper != null) {
                    JDAddressSelectView.this.mJMAHelper.onSelectedOtherClick();
                }
            }
        });
        this.mBack.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.JDAddressSelectView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view3) {
                JDAddressSelectView.this.back();
            }
        });
        this.mClose.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.JDAddressSelectView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view3) {
                JDAddressSelectView.this.removeHotCityHeader();
                if (JDAddressSelectView.this.mAddressHelper != null) {
                    JDAddressSelectView.this.mAddressHelper.close();
                }
                if (JDAddressSelectView.this.mJMAHelper != null) {
                    JDAddressSelectView.this.mJMAHelper.onCloseClick();
                }
            }
        });
        this.flLocation = (LinearLayout) findViewById(R.id.fl_location);
        LocationStateView locationStateView = (LocationStateView) findViewById(R.id.locationView);
        this.locationView = locationStateView;
        if (this.isAutoDark) {
            locationStateView.setAutoDarkMode(true);
        }
        this.locationView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.JDAddressSelectView.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view3) {
                if (JDAddressSelectView.this.locationView.isItemClickBack()) {
                    JDAddressSelectView jDAddressSelectView = JDAddressSelectView.this;
                    jDAddressSelectView.clickLocationCloseView(jDAddressSelectView.locationView);
                }
            }
        });
        this.titleBg = (UnNetImageView) findViewById(R.id.title_bg);
        this.customContainer = (LinearLayout) findViewById(R.id.ll_coustom_container);
        hide();
        if (isDarkMode()) {
            darkMode();
        } else {
            normalMode();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isHeader(int i2) {
        MyAdapter myAdapter = this.mAdapter;
        return myAdapter != null && myAdapter.isHeaderView(i2);
    }

    private boolean isNeedShowSlideBar() {
        AddressGlobal addressGlobal;
        if (this.sumTabSwitch) {
            return (this.mCurrent == 1 || this.mAddressGlobal == null) ? false : true;
        }
        int i2 = this.mCurrent;
        return (i2 == 3 || i2 == 4 || i2 == 5) && (addressGlobal = this.mAddressGlobal) != null && addressGlobal.isForeignOverSea;
    }

    private boolean moveSelectToTop(long j2, ArrayList<AddressGlobal> arrayList) {
        int i2;
        if (j2 <= 0 || arrayList == null || arrayList.size() <= 0 || (i2 = (int) j2) > arrayList.size() - 1) {
            return false;
        }
        arrayList.add(0, arrayList.remove(i2));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void next(int i2) {
        int i3 = this.mCurrent;
        if (i3 == 1) {
            onAddressClick(i2);
        } else if (i3 == 2) {
            onProvinceClick(i2);
        } else if (i3 == 3) {
            onCityClick(i2);
        } else if (i3 == 4) {
            onCountyClick(i2);
        } else if (i3 == 5) {
            onTownClick(i2);
        }
        if (this.mAddressHelper != null) {
            if (UnLog.D) {
                String jSONString = JDJSON.toJSONString(this.mAddressGlobal);
                String str = TAG;
                UnLog.d(str, "onAddressSelected = " + jSONString);
                UnLog.d(str, "onAddressSelected_flag = " + i3);
            }
            this.mAddressHelper.onAddressSelected(i3, this.mAddressGlobal);
        }
    }

    private void onAddressClick(int i2) {
        ArrayList<AddressGlobal> arrayList = this.mAddressList;
        if (arrayList == null || i2 >= arrayList.size()) {
            return;
        }
        AddressGlobal addressGlobal = this.mAddressList.get(i2);
        cloneAddressUsualAddress(addressGlobal);
        this.mAddressGlobal.setIsUserAddress(Boolean.TRUE);
        if (addressGlobal != null) {
            if (this.mType == 2) {
                if (this.mAddressHelper != null) {
                    showProgress();
                    this.mAddressHelper.loadAddress(11, "", this.mAddressGlobal, this.mOnAddressLoadCompletedListener);
                    return;
                }
                return;
            }
            saveAddress();
            int i3 = this.mType;
            if (i3 == 1 || i3 == 4) {
                back();
            }
        }
    }

    private void onCityClick(int i2) {
        this.mAddressGlobal.setIsUserAddress(Boolean.FALSE);
        ArrayList<AddressBaseMode> arrayList = this.cityList;
        if (arrayList == null || i2 >= arrayList.size()) {
            return;
        }
        AddressBaseMode addressBaseMode = this.cityList.get(i2);
        this.mAddressGlobal.setIdCity(getId(addressBaseMode));
        this.mAddressGlobal.setCityName(addressBaseMode.getName());
        if (this.sumTabSwitch && this.mSumCurrent == 102) {
            this.mAddressGlobal.setIdProvince(getParentId(addressBaseMode));
            this.mAddressGlobal.setProvinceName(addressBaseMode.getParentName());
            boolean z = addressBaseMode.isForeignOverSea;
            if (z || addressBaseMode.isGangAoTai) {
                AddressGlobal addressGlobal = this.mAddressGlobal;
                addressGlobal.isForeignOverSea = z;
                addressGlobal.isGangAoTai = addressBaseMode.isGangAoTai;
            }
            if (TextUtils.isEmpty(this.mAddressGlobal.getProvinceName())) {
                this.mAddressGlobal.setProvinceName(getBackupDataFromList(addressBaseMode, 0));
            }
            if (this.mAddressGlobal.getIdProvince() == 0) {
                this.mAddressGlobal.setIdProvince(getCommonId(getBackupDataFromList(addressBaseMode, 1)));
            }
        }
        if (UnLog.D) {
            String jSONString = JDJSON.toJSONString(this.mAddressGlobal);
            UnLog.d(TAG, "onCityClick_mAddressGlobal = " + jSONString);
        }
        String str = addressBaseMode.areaCode;
        this.areaCode2 = str;
        if (!TextUtils.isEmpty(str)) {
            this.mAddressGlobal.areaCode = addressBaseMode.areaCode;
        } else if (!TextUtils.isEmpty(this.areaCode1)) {
            this.mAddressGlobal.areaCode = this.areaCode1;
        } else if (this.sumTabSwitch && this.mSumCurrent == 102) {
            String backupDataFromList = getBackupDataFromList(addressBaseMode, 2);
            if (!TextUtils.isEmpty(backupDataFromList)) {
                this.mAddressGlobal.areaCode = backupDataFromList;
            }
        }
        AddressGlobal addressGlobal2 = this.mAddressGlobal;
        addressGlobal2.nameCode = addressBaseMode.nameCode;
        addressGlobal2.setIdArea(0);
        this.mAddressGlobal.setAreaName("");
        this.mAddressGlobal.setIdTown(0);
        this.mAddressGlobal.setTownName("");
        this.mAddressGlobal.setAddressDetail("");
        this.mAddressGlobal.setAddressTitle("");
        this.mAddressGlobal.setSubAddressDetail("");
        updateTabName();
        AddressHelper addressHelper = this.mAddressHelper;
        if (addressHelper != null) {
            if (!addressHelper.onThirdAddressSelected(3, addressBaseMode)) {
                saveAddress();
            } else {
                queryData(addressBaseMode.getAction(), 4);
            }
        }
    }

    private void onCountyClick(int i2) {
        this.mAddressGlobal.setIsUserAddress(Boolean.FALSE);
        ArrayList<AddressBaseMode> arrayList = this.countyList;
        if (arrayList == null || i2 >= arrayList.size()) {
            return;
        }
        AddressBaseMode addressBaseMode = this.countyList.get(i2);
        this.mAddressGlobal.setIdArea(getId(addressBaseMode));
        this.mAddressGlobal.setAreaName(addressBaseMode.getName());
        String str = addressBaseMode.areaCode;
        this.areaCode3 = str;
        if (!TextUtils.isEmpty(str)) {
            this.mAddressGlobal.areaCode = addressBaseMode.areaCode;
        } else if (!TextUtils.isEmpty(this.areaCode2)) {
            this.mAddressGlobal.areaCode = this.areaCode2;
        } else if (!TextUtils.isEmpty(this.areaCode1)) {
            this.mAddressGlobal.areaCode = this.areaCode1;
        } else if (this.sumTabSwitch && this.mSumCurrent == 102) {
            String infoFromCitys = getInfoFromCitys(this.mAddressGlobal.getIdCity(), 2);
            if (!TextUtils.isEmpty(infoFromCitys)) {
                this.mAddressGlobal.areaCode = infoFromCitys;
            }
        }
        if (this.sumTabSwitch && this.mSumCurrent == 102) {
            if (TextUtils.isEmpty(this.mAddressGlobal.getProvinceName())) {
                AddressGlobal addressGlobal = this.mAddressGlobal;
                addressGlobal.setProvinceName(getInfoFromCitys(addressGlobal.getIdCity(), 0));
            }
            if (this.mAddressGlobal.getIdProvince() == 0) {
                AddressGlobal addressGlobal2 = this.mAddressGlobal;
                addressGlobal2.setIdProvince(getCommonId(getInfoFromCitys(addressGlobal2.getIdCity(), 1)));
            }
        }
        if (UnLog.D) {
            String str2 = TAG;
            UnLog.d(str2, "onCountyClick_areaCode = " + this.mAddressGlobal.areaCode);
            UnLog.d(str2, "onCountyClick_IdProvince = " + this.mAddressGlobal.getIdProvince());
        }
        this.mAddressGlobal.setIdTown(0);
        this.mAddressGlobal.setTownName("");
        this.mAddressGlobal.setAddressDetail("");
        this.mAddressGlobal.setAddressTitle("");
        this.mAddressGlobal.setSubAddressDetail("");
        updateTabName();
        AddressHelper addressHelper = this.mAddressHelper;
        if (addressHelper != null) {
            if (!addressHelper.onThirdAddressSelected(4, addressBaseMode)) {
                saveAddress();
                if (this.mType == 1) {
                    AddressUtil.onAddressChanged();
                    back();
                    return;
                }
                return;
            }
            queryData(addressBaseMode.getAction(), 5);
        }
    }

    private void onProvinceClick(int i2) {
        this.mAddressGlobal.setIsUserAddress(Boolean.FALSE);
        ArrayList<AddressBaseMode> arrayList = this.provinceList;
        if (arrayList == null || i2 >= arrayList.size()) {
            return;
        }
        AddressBaseMode addressBaseMode = this.provinceList.get(i2);
        this.mAddressGlobal.setIdProvince(getId(addressBaseMode));
        this.mAddressGlobal.setProvinceName(addressBaseMode.getName());
        AddressGlobal addressGlobal = this.mAddressGlobal;
        addressGlobal.isForeignOverSea = addressBaseMode.isForeignOverSea;
        addressGlobal.isGangAoTai = addressBaseMode.isGangAoTai;
        String str = addressBaseMode.areaCode;
        this.areaCode1 = str;
        if (!TextUtils.isEmpty(str)) {
            this.mAddressGlobal.areaCode = addressBaseMode.areaCode;
        }
        this.mAddressGlobal.setIdCity(0);
        this.mAddressGlobal.setCityName("");
        this.mAddressGlobal.setIdArea(0);
        this.mAddressGlobal.setAreaName("");
        this.mAddressGlobal.setIdTown(0);
        this.mAddressGlobal.setTownName("");
        this.mAddressGlobal.setAddressDetail("");
        this.mAddressGlobal.setAddressTitle("");
        this.mAddressGlobal.setSubAddressDetail("");
        updateTabName();
        AddressHelper addressHelper = this.mAddressHelper;
        if (addressHelper != null) {
            if (!addressHelper.onThirdAddressSelected(2, addressBaseMode)) {
                saveAddress();
            } else {
                queryData(addressBaseMode.getAction(), 3);
            }
        }
    }

    private void onTownClick(int i2) {
        this.mAddressGlobal.setIsUserAddress(Boolean.FALSE);
        ArrayList<AddressBaseMode> arrayList = this.townList;
        if (arrayList == null || i2 >= arrayList.size()) {
            return;
        }
        AddressBaseMode addressBaseMode = this.townList.get(i2);
        this.mAddressGlobal.setIdTown(getId(addressBaseMode));
        this.mAddressGlobal.setTownName(addressBaseMode.getName());
        String str = addressBaseMode.areaCode;
        this.areaCode4 = str;
        if (!TextUtils.isEmpty(str)) {
            this.mAddressGlobal.areaCode = addressBaseMode.areaCode;
        } else if (!TextUtils.isEmpty(this.areaCode3)) {
            this.mAddressGlobal.areaCode = this.areaCode3;
        } else if (!TextUtils.isEmpty(this.areaCode2)) {
            this.mAddressGlobal.areaCode = this.areaCode2;
        } else if (!TextUtils.isEmpty(this.areaCode1)) {
            this.mAddressGlobal.areaCode = this.areaCode1;
        } else if (this.sumTabSwitch && this.mSumCurrent == 102) {
            String infoFromCitys = getInfoFromCitys(this.mAddressGlobal.getIdCity(), 2);
            if (!TextUtils.isEmpty(infoFromCitys)) {
                this.mAddressGlobal.areaCode = infoFromCitys;
            }
        }
        if (this.sumTabSwitch && this.mSumCurrent == 102) {
            if (TextUtils.isEmpty(this.mAddressGlobal.getProvinceName())) {
                AddressGlobal addressGlobal = this.mAddressGlobal;
                addressGlobal.setProvinceName(getInfoFromCitys(addressGlobal.getIdCity(), 0));
            }
            if (this.mAddressGlobal.getIdProvince() == 0) {
                AddressGlobal addressGlobal2 = this.mAddressGlobal;
                addressGlobal2.setIdProvince(getCommonId(getInfoFromCitys(addressGlobal2.getIdCity(), 1)));
            }
        }
        this.mAddressGlobal.setAddressDetail("");
        this.mAddressGlobal.setAddressTitle("");
        this.mAddressGlobal.setSubAddressDetail("");
        if (UnLog.D) {
            String str2 = TAG;
            UnLog.d(str2, "onTownClick_areaCode = " + this.mAddressGlobal.areaCode);
            UnLog.d(str2, "onTownClick_IdProvince = " + this.mAddressGlobal.getIdProvince());
        }
        updateTabName();
        AddressHelper addressHelper = this.mAddressHelper;
        if (addressHelper != null) {
            if (!addressHelper.onThirdAddressSelected(5, addressBaseMode)) {
                saveAddress();
                if (this.mType == 1) {
                    AddressUtil.onAddressChanged();
                    back();
                    return;
                }
                return;
            }
            queryData(addressBaseMode.getAction(), 5);
        }
    }

    private void queryData(String str, int i2) {
        if (this.mAddressHelper != null) {
            showProgress();
            this.mAddressHelper.loadAddress(i2, str, this.mAddressGlobal, this.mOnAddressLoadCompletedListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeHotCityHeader() {
        StickyHeaderListView stickyHeaderListView;
        View view;
        if (!this.mHaveHotCityHeader || (stickyHeaderListView = this.mListView) == null || (view = this.mHotCityHeaderView) == null) {
            return;
        }
        stickyHeaderListView.removeHeaderView(view);
        this.mHaveHotCityHeader = false;
        if (UnLog.D) {
            UnLog.d(TAG, "removeHotCityHeader");
        }
    }

    private void resetData() {
        this.needPosition = false;
        this.provinceList = null;
        this.cityList = null;
        this.countyList = null;
        this.townList = null;
    }

    private void resetDataCommon() {
        this.mAddressList = null;
        this.mCurrent = 1;
        this.mSumCurrent = 101;
        resetData();
        if (this.mType != 1) {
            this.mAddressGlobal.setId(0L);
            this.mAddressGlobal.setWhere("");
            this.mAddressGlobal.setIdProvince(0);
            this.mAddressGlobal.setIdCity(0);
            this.mAddressGlobal.setIdArea(0);
            this.mAddressGlobal.setIdTown(0);
            AddressGlobal addressGlobal = this.mAddressGlobal;
            addressGlobal.isForeignOverSea = false;
            addressGlobal.isGangAoTai = false;
        }
    }

    private void resetDataThreeAddress() {
        resetData();
        int i2 = this.mType;
        if (i2 != 1) {
            if (i2 != 4 && i2 != 2) {
                this.mAddressGlobal.setId(0L);
            }
            this.mAddressGlobal.setWhere("");
            this.mAddressGlobal.setIdProvince(0);
            this.mAddressGlobal.setIdCity(0);
            this.mAddressGlobal.setIdArea(0);
            this.mAddressGlobal.setIdTown(0);
            AddressGlobal addressGlobal = this.mAddressGlobal;
            addressGlobal.isForeignOverSea = false;
            addressGlobal.isGangAoTai = false;
        }
    }

    private void resetOtherView(View view, View view2) {
        ArrayList<TextView> arrayList;
        if (!this.sumTabSwitch || (arrayList = this.mSelectViewList) == null || arrayList.size() < 4) {
            return;
        }
        try {
            Iterator<TextView> it = this.mSelectViewList.iterator();
            while (it.hasNext()) {
                TextView next = it.next();
                if (next != view && next != view2 && next.isEnabled()) {
                    next.setTypeface(Typeface.defaultFromStyle(0));
                    if (isDarkMode()) {
                        next.setTextColor(getResources().getColor(R.color.un_content_level_1_dark));
                    } else {
                        next.setTextColor(getResources().getColor(R.color.un_content_level_1));
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void resetThirdAddress() {
        this.provinceList = null;
        this.cityList = null;
        this.countyList = null;
        this.townList = null;
        this.mAddressGlobal.setIdProvince(0);
        this.mAddressGlobal.setIdCity(0);
        this.mAddressGlobal.setIdArea(0);
        this.mAddressGlobal.setIdTown(0);
        this.mAddressGlobal.isForeignOverSea = false;
    }

    private void setSelection(int i2) {
        if (this.mListView == null) {
            return;
        }
        if (this.sumTabSwitch && this.mHaveHotCityHeader) {
            if (isNeedShowSlideBar()) {
                this.mListView.setSelectionFromTop(i2, getHeaderHeight(i2 + 1));
                return;
            }
            this.mListView.setSelection(i2);
        } else if (isNeedShowSlideBar()) {
            this.mListView.setSelectionFromTop(i2, getHeaderHeight(i2));
        } else {
            this.mListView.setSelection(i2);
        }
    }

    private void showLocationView() {
        ArrayList<AddressGlobal> arrayList;
        if (this.mType == 3) {
            if (this.locationSwitch && UnAddressSelectUtils.canOrderShowLocWidget()) {
                if (this.flLocation.getVisibility() != 0) {
                    this.locationView.showMta();
                }
                this.flLocation.setVisibility(0);
            }
        } else if (this.locationSwitch && LoginUserBase.hasLogin() && ((arrayList = this.mAddressList) == null || arrayList.isEmpty())) {
            if (this.locationView.hasLocation() || LocationStateViewHelper.canShowAlert()) {
                if (this.flLocation.getVisibility() != 0) {
                    this.locationView.showMta();
                }
                this.flLocation.setVisibility(0);
                if (OKLog.D) {
                    OKLog.d(TAG, "showLocationView show Location true");
                }
            }
        } else if (TextUtils.equals(this.source, UnAddressConstants.ADDRESS_SOURCE_PRODUCT_DETAIL)) {
            this.flLocation.setVisibility(8);
            if (OKLog.D) {
                OKLog.d(TAG, "showLocationView show Location false");
            }
        }
        UnAddressSelectUtils.getLocAddress(this.sceneId, new OnAddressInfoListener() { // from class: com.jingdong.common.ui.JDAddressSelectView.9
            @Override // com.jingdong.common.ui.address.listener.OnAddressInfoListener
            public void onResult(UnAddressInfo unAddressInfo) {
                if (unAddressInfo != null) {
                    JDAddressSelectView.this.post(new Runnable() { // from class: com.jingdong.common.ui.JDAddressSelectView.9.1
                        @Override // java.lang.Runnable
                        public void run() {
                            JDAddressSelectView.this.refresh();
                        }
                    });
                }
            }
        });
    }

    private void showNavTab(int i2) {
        View view;
        if (i2 == 1) {
            return;
        }
        this.mNavigatorView.setVisibility(0);
        final View navView = getNavView(i2);
        final View oldView = getOldView();
        if (navView == this.textProvince && this.sumTabSwitch) {
            StickyHeaderListView stickyHeaderListView = this.mListView;
            if (stickyHeaderListView != null) {
                stickyHeaderListView.removeHeaderView(this.mHotCityHeaderView);
                if (getHotCityHeaderView() && this.mHotCityHeaderView != null) {
                    if (Build.VERSION.SDK_INT < 19) {
                        this.mListView.setAdapter((ListAdapter) null);
                    }
                    this.mListView.addHeaderView(this.mHotCityHeaderView);
                    this.mHaveHotCityHeader = true;
                }
            }
        } else {
            StickyHeaderListView stickyHeaderListView2 = this.mListView;
            if (stickyHeaderListView2 != null && (view = this.mHotCityHeaderView) != null) {
                stickyHeaderListView2.removeHeaderView(view);
                this.mHaveHotCityHeader = false;
            }
        }
        resetOtherView(oldView, navView);
        if (navView != oldView) {
            this.mTabIndicator.postDelayed(new Runnable() { // from class: com.jingdong.common.ui.JDAddressSelectView.12
                @Override // java.lang.Runnable
                public void run() {
                    JDAddressSelectView.this.mTabIndicator.onTabSelected(oldView, navView);
                }
            }, 200L);
        }
        if (oldView != null && (oldView instanceof TextView)) {
            TextView textView = (TextView) oldView;
            textView.setTypeface(Typeface.defaultFromStyle(0));
            if (this.sumTabSwitch) {
                if (isDarkMode()) {
                    textView.setTextColor(getResources().getColor(R.color.un_content_level_1_dark));
                } else {
                    textView.setTextColor(getResources().getColor(R.color.un_content_level_1));
                }
            }
        }
        if (navView != null && (navView instanceof TextView)) {
            if (this.sumTabSwitch) {
                if (isDarkMode()) {
                    ((TextView) navView).setTextColor(getResources().getColor(R.color.un_jd_main_dark));
                } else {
                    ((TextView) navView).setTextColor(getResources().getColor(R.color.un_jd_main));
                }
            } else if (isDarkMode()) {
                ((TextView) navView).setTextColor(getResources().getColor(R.color.un_content_level_1_dark));
            } else {
                ((TextView) navView).setTextColor(getResources().getColor(R.color.un_content_level_1));
            }
            ((TextView) navView).setTypeface(Typeface.defaultFromStyle(1));
        }
        updateTabName();
    }

    private void showProgress() {
        View view = this.mLoadingView;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    private void showSelector(int i2, SelectAddressItemEntity[] selectAddressItemEntityArr, int i3) {
        hideProgress();
        show(i2);
        showNavTab(i2);
        handleElder(i2);
        this.mCurrent = i2;
        if (this.mAdapter == null) {
            this.mAdapter = new MyAdapter();
            this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.jingdong.common.ui.JDAddressSelectView.14
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i4, long j2) {
                    if (UnLog.D) {
                        UnLog.d(JDAddressSelectView.TAG, "onItemClick " + i4);
                    }
                    JDAddressSelectView jDAddressSelectView = JDAddressSelectView.this;
                    jDAddressSelectView.isClick = true;
                    if (!jDAddressSelectView.sumTabSwitch || !jDAddressSelectView.mHaveHotCityHeader) {
                        if (JDAddressSelectView.this.mIsDestroy || JDAddressSelectView.this.isHeader(i4)) {
                            return;
                        }
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis - JDAddressSelectView.this.mClickTime < 1000) {
                            return;
                        }
                        JDAddressSelectView.this.mClickTime = currentTimeMillis;
                        int changeToDataPosition = JDAddressSelectView.this.mAdapter.changeToDataPosition(i4);
                        if (changeToDataPosition == -1) {
                            return;
                        }
                        JDAddressSelectView.this.mAdapter.setSelected(i4);
                        JDAddressSelectView.this.mListView.postInvalidate();
                        JDAddressSelectView.this.next(changeToDataPosition);
                    } else if (JDAddressSelectView.this.mIsDestroy) {
                    } else {
                        int i5 = i4 - 1;
                        if (JDAddressSelectView.this.isHeader(i5)) {
                            return;
                        }
                        long currentTimeMillis2 = System.currentTimeMillis();
                        if (currentTimeMillis2 - JDAddressSelectView.this.mClickTime < 1000) {
                            return;
                        }
                        JDAddressSelectView.this.mClickTime = currentTimeMillis2;
                        int changeToDataPosition2 = JDAddressSelectView.this.mAdapter.changeToDataPosition(i5);
                        if (changeToDataPosition2 == -1) {
                            return;
                        }
                        JDAddressSelectView.this.mAdapter.setSelected(i5);
                        JDAddressSelectView.this.mListView.postInvalidate();
                        JDAddressSelectView.this.next(changeToDataPosition2);
                    }
                }
            });
        }
        updateContent(i3, selectAddressItemEntityArr);
        updateSlideBarStatus();
        updateAddressUi();
        int changeToRealPosition = this.mAdapter.changeToRealPosition(i3);
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        if (changeToRealPosition == -1) {
            changeToRealPosition = 0;
        }
        setSelection(changeToRealPosition);
    }

    private void showSlideBar() {
        JDSlideBar jDSlideBar = this.jdSlideBar;
        if (jDSlideBar != null) {
            jDSlideBar.setVisibility(0);
        }
    }

    private void showSumNavTab(int i2) {
        this.mSumNavigatorView.setVisibility(0);
        final View sumNavView = getSumNavView(i2);
        getSumOldView();
        TabIndicator tabIndicator = this.mSumTabIndicator;
        if (tabIndicator != null) {
            tabIndicator.postDelayed(new Runnable
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0019: INVOKE 
                  (r3v0 'tabIndicator' com.jingdong.common.ui.TabIndicator)
                  (wrap: java.lang.Runnable : 0x0014: CONSTRUCTOR 
                  (r7v0 'this' com.jingdong.common.ui.JDAddressSelectView A[IMMUTABLE_TYPE, THIS])
                  (r2 I:android.view.View A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r0v1 'sumNavView' android.view.View A[DONT_INLINE])
                 A[MD:(com.jingdong.common.ui.JDAddressSelectView, android.view.View, android.view.View):void (m), WRAPPED] (LINE:5) call: com.jingdong.common.ui.JDAddressSelectView.10.<init>(com.jingdong.common.ui.JDAddressSelectView, android.view.View, android.view.View):void type: CONSTRUCTOR)
                  (200 long)
                 type: VIRTUAL call: android.view.View.postDelayed(java.lang.Runnable, long):boolean A[MD:(java.lang.Runnable, long):boolean (c)] (LINE:5) in method: com.jingdong.common.ui.JDAddressSelectView.showSumNavTab(int):void, file: classes6.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                Caused by: java.lang.NullPointerException
                */
            /*
                this = this;
                android.view.View r0 = r7.mSumNavigatorView
                r1 = 0
                r0.setVisibility(r1)
                android.view.View r0 = r7.getSumNavView(r8)
                android.view.View r2 = r7.getSumOldView()
                com.jingdong.common.ui.TabIndicator r3 = r7.mSumTabIndicator
                if (r3 == 0) goto L1c
                com.jingdong.common.ui.JDAddressSelectView$10 r4 = new com.jingdong.common.ui.JDAddressSelectView$10
                r4.<init>()
                r5 = 200(0xc8, double:9.9E-322)
                r3.postDelayed(r4, r5)
            L1c:
                android.widget.TextView r2 = r7.sumInland
                if (r2 == 0) goto L34
                android.widget.TextView r3 = r7.sumSeas
                if (r3 == 0) goto L34
                android.graphics.Typeface r3 = android.graphics.Typeface.defaultFromStyle(r1)
                r2.setTypeface(r3)
                android.widget.TextView r2 = r7.sumSeas
                android.graphics.Typeface r1 = android.graphics.Typeface.defaultFromStyle(r1)
                r2.setTypeface(r1)
            L34:
                if (r0 == 0) goto L44
                boolean r1 = r0 instanceof android.widget.TextView
                if (r1 == 0) goto L44
                android.widget.TextView r0 = (android.widget.TextView) r0
                r1 = 1
                android.graphics.Typeface r1 = android.graphics.Typeface.defaultFromStyle(r1)
                r0.setTypeface(r1)
            L44:
                r7.mSumCurrent = r8
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.ui.JDAddressSelectView.showSumNavTab(int):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void sortAddressList(ArrayList<AddressBaseMode> arrayList) {
            Collections.sort(arrayList, new Comparator<AddressBaseMode>() { // from class: com.jingdong.common.ui.JDAddressSelectView.11
                @Override // java.util.Comparator
                public int compare(AddressBaseMode addressBaseMode, AddressBaseMode addressBaseMode2) {
                    String convertToPinyin = JDCityDataUtils.convertToPinyin(addressBaseMode.getName());
                    String convertToPinyin2 = JDCityDataUtils.convertToPinyin(addressBaseMode2.getName());
                    boolean isLetter = JDCityDataUtils.isLetter(convertToPinyin);
                    boolean isLetter2 = JDCityDataUtils.isLetter(convertToPinyin2);
                    if (isLetter && isLetter2) {
                        return convertToPinyin.compareTo(convertToPinyin2);
                    }
                    if (!isLetter || isLetter2) {
                        return (isLetter || !isLetter2) ? 0 : 1;
                    }
                    return -1;
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void sortAddressListProcess(ArrayList<AddressBaseMode> arrayList) {
            if (arrayList == null) {
                return;
            }
            new AddressBaseMode();
            sortAddressList(arrayList);
            int i2 = -1;
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                if (arrayList.get(i3) != null && arrayList.get(i3).getName() != null && (getCommonId(arrayList.get(i3).getId()) == 999999 || arrayList.get(i3).equals("\u6682\u4e0d\u9009\u62e9"))) {
                    i2 = i3;
                }
            }
            if (i2 != -1) {
                arrayList.remove(i2);
                arrayList.add(arrayList.get(i2));
            }
        }

        private void titleContentLayoutThemeChange(boolean z) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mTitleView.getLayoutParams();
            if (z) {
                layoutParams.height = DpiUtil.dip2px(getContext(), 60.0f);
            } else {
                layoutParams.height = DpiUtil.dip2px(getContext(), 68.0f);
            }
        }

        private void updateAddressUi() {
            MyAdapter myAdapter = this.mAdapter;
            if (myAdapter != null) {
                myAdapter.changeHeaderStatus(isNeedShowSlideBar());
                this.mAdapter.notifyDataSetChanged();
            }
            StickyHeaderListView stickyHeaderListView = this.mListView;
            if (stickyHeaderListView != null) {
                stickyHeaderListView.setStickyHeaders(isNeedShowSlideBar());
            }
        }

        private void updateContent(int i2, SelectAddressItemEntity[] selectAddressItemEntityArr) {
            if (this.mAdapter != null) {
                if (isNeedShowSlideBar()) {
                    StickyHeaderData stickyHeaderData = new StickyHeaderData();
                    this.stickyHeaderData = stickyHeaderData;
                    SelectAddressItemEntity[] updateData = JDCityDataUtils.updateData(selectAddressItemEntityArr, stickyHeaderData);
                    this.jdSlideBar.setDataAndAutoHeight(this.stickyHeaderData.getLetterList());
                    this.mAdapter.setContent(i2, updateData, this.stickyHeaderData.getHeaderList(), this.stickyHeaderData.getRealToDataMap(), this.stickyHeaderData.getDataToRealMap());
                    return;
                }
                this.mAdapter.setContent(i2, selectAddressItemEntityArr);
            }
        }

        private void updateInlandTabName() {
            if (this.mAddressGlobal.getIdProvince() != 0) {
                if (this.sumTabSwitch) {
                    this.mNavigatorView.setVisibility(0);
                }
                this.textProvince.setVisibility(0);
                this.textProvince.setText(this.mAddressGlobal.getProvinceName());
                if (this.mAddressGlobal.getIdCity() != 0) {
                    this.textCity.setVisibility(0);
                    this.textCity.setText(this.mAddressGlobal.getCityName());
                    if (this.mAddressGlobal.getIdArea() != 0) {
                        this.textCounty.setVisibility(0);
                        this.textCounty.setText(this.mAddressGlobal.getAreaName());
                        if (this.mAddressGlobal.getIdTown() != 0) {
                            this.textTown.setVisibility(0);
                            this.textTown.setText(this.mAddressGlobal.getTownName());
                            return;
                        }
                        ArrayList<AddressBaseMode> arrayList = this.townList;
                        if (arrayList != null && !arrayList.isEmpty()) {
                            this.textTown.setVisibility(0);
                            this.textTown.setText(R.string.address_please_select);
                            return;
                        }
                        this.textTown.setVisibility(8);
                        return;
                    }
                    ArrayList<AddressBaseMode> arrayList2 = this.countyList;
                    if (arrayList2 != null && !arrayList2.isEmpty()) {
                        this.textCounty.setVisibility(0);
                        this.textCounty.setText(R.string.address_please_select);
                        this.textTown.setVisibility(8);
                        return;
                    }
                    this.textCounty.setVisibility(8);
                    this.textTown.setVisibility(8);
                    return;
                }
                ArrayList<AddressBaseMode> arrayList3 = this.cityList;
                if (arrayList3 != null && !arrayList3.isEmpty()) {
                    this.textCity.setVisibility(0);
                    this.textCity.setText(R.string.address_please_select);
                    this.textCounty.setVisibility(8);
                    this.textTown.setVisibility(8);
                    return;
                }
                this.textCity.setVisibility(8);
                this.textCounty.setVisibility(8);
                this.textTown.setVisibility(8);
                return;
            }
            ArrayList<AddressBaseMode> arrayList4 = this.provinceList;
            if (arrayList4 == null || arrayList4.isEmpty()) {
                return;
            }
            if (this.sumTabSwitch) {
                this.mNavigatorView.setVisibility(8);
            }
            this.textProvince.setVisibility(0);
            this.textProvince.setText(R.string.address_please_select);
            this.textCity.setVisibility(8);
            this.textCounty.setVisibility(8);
            this.textTown.setVisibility(8);
        }

        private void updateOverSeasTabName() {
            if (this.mAddressGlobal.getIdCity() != 0) {
                if (this.sumTabSwitch) {
                    this.mNavigatorView.setVisibility(0);
                }
                this.textProvince.setVisibility(0);
                this.textProvince.setText(this.mAddressGlobal.getCityName());
                if (this.mAddressGlobal.getIdArea() != 0) {
                    this.textCity.setVisibility(0);
                    this.textCity.setText(this.mAddressGlobal.getAreaName());
                    if (this.mAddressGlobal.getIdTown() != 0) {
                        this.textCounty.setVisibility(0);
                        this.textCounty.setText(this.mAddressGlobal.getTownName());
                        return;
                    }
                    ArrayList<AddressBaseMode> arrayList = this.townList;
                    if (arrayList != null && !arrayList.isEmpty()) {
                        this.textCounty.setVisibility(0);
                        this.textCounty.setText(R.string.address_please_select);
                        this.textTown.setVisibility(8);
                        return;
                    }
                    this.textCounty.setVisibility(8);
                    this.textTown.setVisibility(8);
                    return;
                }
                ArrayList<AddressBaseMode> arrayList2 = this.countyList;
                if (arrayList2 != null && !arrayList2.isEmpty()) {
                    this.textCity.setVisibility(0);
                    this.textCity.setText(R.string.address_please_select);
                    this.textCounty.setVisibility(8);
                    this.textTown.setVisibility(8);
                    return;
                }
                this.textCity.setVisibility(8);
                this.textCounty.setVisibility(8);
                this.textTown.setVisibility(8);
                return;
            }
            ArrayList<AddressBaseMode> arrayList3 = this.cityList;
            if (arrayList3 == null || arrayList3.isEmpty()) {
                return;
            }
            if (this.sumTabSwitch) {
                this.mNavigatorView.setVisibility(8);
            }
            this.textProvince.setVisibility(0);
            this.textProvince.setText(R.string.address_please_select);
            this.textCity.setVisibility(8);
            this.textCounty.setVisibility(8);
            this.textTown.setVisibility(8);
        }

        private void updateSlideBarStatus() {
            if (isNeedShowSlideBar()) {
                showSlideBar();
            } else {
                hiddenSlideBar();
            }
        }

        private void updateTabName() {
            int i2 = this.mSumCurrent;
            if (i2 == 101) {
                updateInlandTabName();
            } else if (i2 == 102) {
                updateOverSeasTabName();
            }
        }

        public void addCustomView(View view) {
            if (view == null) {
                this.customContainer.setVisibility(8);
                return;
            }
            LinearLayout linearLayout = this.customContainer;
            if (linearLayout != null) {
                linearLayout.removeAllViews();
                this.customContainer.addView(view);
                this.customContainer.setVisibility(0);
            }
        }

        public void addJMAHelper(JMAHelper jMAHelper) {
            this.mJMAHelper = jMAHelper;
        }

        public boolean back() {
            ArrayList<AddressGlobal> arrayList;
            if (UnLog.I) {
                UnLog.i(TAG, "back =  mCurrent" + this.mCurrent);
            }
            removeHotCityHeader();
            int i2 = this.mCurrent;
            if (i2 == 1) {
                AddressHelper addressHelper = this.mAddressHelper;
                if (addressHelper != null) {
                    addressHelper.close();
                    return false;
                }
                return false;
            } else if (i2 == 2 || i2 == 3 || i2 == 4 || i2 == 5) {
                int i3 = this.mType;
                if ((i3 == 2 || i3 == 4) && LoginUserBase.hasLogin() && (arrayList = this.mAddressList) != null && !arrayList.isEmpty()) {
                    back2Address();
                    return true;
                }
                AddressHelper addressHelper2 = this.mAddressHelper;
                if (addressHelper2 != null) {
                    addressHelper2.close();
                    return false;
                }
                return false;
            } else {
                return false;
            }
        }

        public boolean canJumpToMap() {
            return false;
        }

        public void changeToTheme(String str, Integer num, Drawable drawable, Drawable drawable2) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            titleContentLayoutThemeChange(false);
            this.titleBg.setVisibility(0);
            this.titleBg.setImage(str);
            if (num != null) {
                this.mTitle.setTextColor(num.intValue());
            }
            if (drawable != null) {
                this.mClose.setImageDrawable(drawable);
            }
            if (drawable2 != null) {
                this.mBack.setImageDrawable(drawable2);
            }
        }

        public void clickLocationCloseView(LocationStateView locationStateView) {
            if (locationStateView == null) {
                return;
            }
            AddressGlobal addressGlobal = locationStateView.getAddressGlobal();
            this.mAddressGlobal = addressGlobal;
            addressGlobal.setIsUserAddress(Boolean.FALSE);
            this.mAddressGlobal.setSaveBusiness(this.saveBusiness);
            this.mAddressGlobal.setSource(this.source);
            if (this.mType != 3) {
                saveAddress();
                AddressUtil.onAddressChanged();
            }
            if (this.mType == 4) {
                ToastUtils.shortToast(this.mContext, "\u5df2\u6210\u529f\u5b9a\u4f4d\u5f53\u524d\u5730\u5740");
            }
            if (this.mType == 2) {
                if (this.mAddressHelper != null) {
                    showProgress();
                    AddressGlobal addressGlobal2 = this.mAddressGlobal;
                    addressGlobal2.setWhere(addressGlobal2.getAddressDetail());
                    this.mAddressHelper.loadAddress(1001, "directStock", this.mAddressGlobal, this.mOnAddressLoadCompletedListener);
                }
            } else if (this.mAddressHelper != null) {
                ILocationClickStateListener iLocationClickStateListener = this.mClickStateListener;
                if (iLocationClickStateListener != null) {
                    iLocationClickStateListener.onClick(11);
                }
                this.mAddressHelper.onAddressSelected(1001, this.mAddressGlobal);
                this.mAddressHelper.close();
            }
        }

        public void destroy() {
            this.mIsDestroy = true;
            this.mListView = null;
            this.mAdapter = null;
            this.mRootView = null;
            this.textProvince = null;
            this.textCity = null;
            this.textCounty = null;
            this.textTown = null;
            this.mHotCityHeaderView = null;
            ArrayList<AddressBaseMode> arrayList = this.provinceList;
            if (arrayList != null) {
                arrayList.clear();
            }
            ArrayList<AddressBaseMode> arrayList2 = this.cityList;
            if (arrayList2 != null) {
                arrayList2.clear();
            }
            ArrayList<AddressBaseMode> arrayList3 = this.countyList;
            if (arrayList3 != null) {
                arrayList3.clear();
            }
            ArrayList<AddressBaseMode> arrayList4 = this.townList;
            if (arrayList4 != null) {
                arrayList4.clear();
            }
            ArrayList<AddressGlobal> arrayList5 = this.mAddressList;
            if (arrayList5 != null) {
                arrayList5.clear();
            }
            ArrayList<TextView> arrayList6 = this.mSelectViewList;
            if (arrayList6 != null) {
                arrayList6.clear();
            }
            StickyHeaderData stickyHeaderData = this.stickyHeaderData;
            if (stickyHeaderData != null) {
                stickyHeaderData.release();
            }
            JDSlideBar jDSlideBar = this.jdSlideBar;
            if (jDSlideBar != null) {
                jDSlideBar.release();
            }
            JDCityDataUtils.release();
        }

        protected void doCity(ArrayList<AddressBaseMode> arrayList) {
            if (arrayList != null) {
                this.cityList = new ArrayList<>(arrayList);
            }
            SelectAddressItemEntity[] addressStringArray = getAddressStringArray(this.cityList);
            if (addressStringArray.length > 0) {
                showSelector(3, addressStringArray, getDefaultPosition(String.valueOf(this.mAddressGlobal.getIdCity()), this.cityList));
            }
            showLocationView();
        }

        protected void doCounty(ArrayList<AddressBaseMode> arrayList) {
            if (arrayList != null) {
                this.countyList = new ArrayList<>(arrayList);
            }
            ArrayList<AddressBaseMode> arrayList2 = this.countyList;
            if (arrayList2 != null && !arrayList2.isEmpty()) {
                SelectAddressItemEntity[] addressStringArray = getAddressStringArray(this.countyList);
                if (addressStringArray.length > 0) {
                    showSelector(4, addressStringArray, getDefaultPosition(String.valueOf(this.mAddressGlobal.getIdArea()), this.countyList));
                }
            }
            showLocationView();
        }

        protected void doTown(ArrayList<AddressBaseMode> arrayList) {
            if (arrayList != null) {
                this.townList = new ArrayList<>(arrayList);
            }
            SelectAddressItemEntity[] addressStringArray = getAddressStringArray(this.townList);
            if (addressStringArray.length > 0) {
                showSelector(5, addressStringArray, getDefaultPosition(String.valueOf(this.mAddressGlobal.getIdTown()), this.townList));
            }
            showLocationView();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
        public JDAddressSelectView elderMode() {
            return this;
        }

        public AddressGlobal getAddressGlobal() {
            return this.mAddressGlobal;
        }

        public OnAddressLoadCompletedListener getAddressLoadCompletedListener() {
            return this.mOnAddressLoadCompletedListener;
        }

        public ImageView getBackBtn() {
            return this.mBack;
        }

        public String getSceneId() {
            return this.sceneId;
        }

        public void hide() {
            hideProgress();
            this.mListView.setVisibility(4);
            this.mSumNavigatorView.setVisibility(8);
            this.mNavigatorView.setVisibility(8);
            this.toOtherAddress.setVisibility(8);
        }

        public void initData(int i2, AddressHelper addressHelper) {
            this.mType = i2;
            if (i2 == 2) {
                this.source = UnAddressConstants.ADDRESS_SOURCE_PRODUCT_DETAIL;
            }
            this.mAddressHelper = addressHelper;
            this.mTitleView.setVisibility(0);
            JDCityDataUtils.getMultiPinYinMap();
        }

        public void initProvinceClick() {
            resetThirdAddress();
            this.mSumCurrent = 101;
            queryData("", 2);
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
            try {
                return JDElderModeUtils.isElderMode();
            } catch (Throwable unused) {
                return false;
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            if (UnLog.D) {
                UnLog.d(TAG, "onAttachedToWindow");
            }
            if (EventBus.getDefault().isRegistered(this)) {
                return;
            }
            EventBus.getDefault().register(this);
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            if (UnLog.D) {
                UnLog.d(TAG, "onDetachedFromWindow");
            }
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);
            }
        }

        public void onEventMainThread(BaseEvent baseEvent) {
            if (baseEvent != null && TextUtils.equals(baseEvent.getType(), LocationAddressEvent.LOCATION_ADDRESS_RESULT) && baseEvent.getBundle() != null && this.locationSwitch) {
                AddressGlobal addressGlobal = (AddressGlobal) baseEvent.getBundle().getParcelable(LocationAddressEvent.LOCATION_ADDRESS_RESULT);
                this.mAddressGlobal = addressGlobal;
                if (addressGlobal != null) {
                    addressGlobal.setIsUserAddress(Boolean.FALSE);
                    setAddressGlobal(this.mAddressGlobal);
                    saveAddress();
                    AddressUtil.onAddressChanged();
                }
                int i2 = this.mType;
                if (i2 == 4) {
                    ToastUtils.shortToast(this.mContext, "\u5df2\u6210\u529f\u5b9a\u4f4d\u5f53\u524d\u5730\u5740");
                } else if (i2 == 2) {
                    if (this.mAddressHelper != null) {
                        showProgress();
                        this.mAddressHelper.loadAddress(1001, "directStock", this.mAddressGlobal, this.mOnAddressLoadCompletedListener);
                    }
                } else if (this.mAddressHelper != null) {
                    ILocationClickStateListener iLocationClickStateListener = this.mClickStateListener;
                    if (iLocationClickStateListener != null) {
                        iLocationClickStateListener.onClick(12);
                    }
                    this.mAddressHelper.onAddressSelected(1001, this.mAddressGlobal);
                    this.mAddressHelper.close();
                }
            }
        }

        @Override // com.jingdong.common.ui.JDSlideBar.ISlideBarTouchChangeLisener
        public void onSlideBarSelected(String str) {
            Map<String, Integer> strToPosMap;
            StickyHeaderData stickyHeaderData = this.stickyHeaderData;
            if (stickyHeaderData == null || (strToPosMap = stickyHeaderData.getStrToPosMap()) == null) {
                return;
            }
            Integer num = strToPosMap.get(str);
            int intValue = num != null ? num.intValue() : -1;
            StickyHeaderListView stickyHeaderListView = this.mListView;
            if (stickyHeaderListView == null || intValue == -1) {
                return;
            }
            stickyHeaderListView.smoothScrollToPositionFromTop(intValue, 0, 300);
        }

        public String optionsAllAddressId(AddressGlobal addressGlobal) {
            if (addressGlobal == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            sb.append("#");
            sb.append(addressGlobal.getIdProvince());
            if (addressGlobal.getIdCity() <= 0) {
                return sb.toString();
            }
            sb.append("-");
            sb.append(addressGlobal.getIdCity());
            if (addressGlobal.getIdArea() <= 0) {
                return sb.toString();
            }
            sb.append("-");
            sb.append(addressGlobal.getIdArea());
            if (addressGlobal.getIdTown() <= 0) {
                return sb.toString();
            }
            sb.append("-");
            sb.append(addressGlobal.getIdTown());
            return sb.toString();
        }

        @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
        public void refresh() {
            LocationStateView locationStateView;
            if (this.locationSwitch && (locationStateView = this.locationView) != null) {
                locationStateView.resume();
            }
        }

        public void refreshTheme() {
            if (isDarkMode()) {
                darkMode();
            } else {
                normalMode();
            }
            MyAdapter myAdapter = this.mAdapter;
            if (myAdapter != null) {
                myAdapter.notifyDataSetInvalidated();
                this.mAdapter.notifyDataSetChanged();
                this.mListView.invalidate();
            }
            JDAddressGridAdapter jDAddressGridAdapter = this.gridAdapter;
            if (jDAddressGridAdapter != null) {
                jDAddressGridAdapter.setDarkMode(isDarkMode());
                this.gridAdapter.notifyDataSetInvalidated();
                this.gridAdapter.notifyDataSetChanged();
            }
        }

        public void saveAddress() {
            int i2 = this.mType;
            if (i2 == 3 || i2 == 5) {
                return;
            }
            if (UnLog.D) {
                UnLog.d(TAG, "saveAddress: " + this.mAddressGlobal.toString());
            }
            if (!this.mAddressGlobal.getIsUserAddress().booleanValue()) {
                this.mAddressGlobal.setId(0L);
            }
            this.mAddressGlobal.setSaveBusiness(this.saveBusiness);
            this.mAddressGlobal.setSource(this.source);
            AddressUtil.updateAddressGlobal(this.mAddressGlobal);
        }

        public void setAddressGlobal(AddressGlobal addressGlobal) {
            if (TextUtils.isEmpty(addressGlobal.getWhere())) {
                addressGlobal.setWhere(getNull(addressGlobal.getProvinceName()) + getNull(addressGlobal.getCityName()) + getNull(addressGlobal.getAreaName()) + getNull(addressGlobal.getTownName()));
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
        public JDAddressSelectView setAutoElderMode(boolean z) {
            return this;
        }

        public void setClickAddressEnable(int i2) {
            if ((this.textProvince == null && this.textCity == null && this.textCounty == null) || this.clickAddressEnable == i2) {
                return;
            }
            this.clickAddressEnable = i2;
            if (i2 >= 1) {
                this.sumSeas.setEnabled(false);
                this.sumSeas.setTextColor(getResources().getColor(R.color.un_content_level_3));
            }
            int i3 = this.clickAddressEnable;
            if (i3 == 1 || i3 == -1) {
                this.textProvince.setEnabled(true);
                this.textCity.setEnabled(true);
                this.textCounty.setEnabled(true);
                this.textTown.setEnabled(true);
            } else if (i3 == 2) {
                this.textProvince.setEnabled(false);
                this.textProvince.setTextColor(getResources().getColor(R.color.un_content_level_3));
            } else if (i3 == 3) {
                this.textProvince.setEnabled(false);
                this.textCity.setEnabled(false);
                TextView textView = this.textProvince;
                Resources resources = getResources();
                int i4 = R.color.un_content_level_3;
                textView.setTextColor(resources.getColor(i4));
                this.textCity.setTextColor(getResources().getColor(i4));
            } else if (i3 == 4) {
                this.textProvince.setEnabled(false);
                this.textCity.setEnabled(false);
                this.textCounty.setEnabled(false);
                TextView textView2 = this.textProvince;
                Resources resources2 = getResources();
                int i5 = R.color.un_content_level_3;
                textView2.setTextColor(resources2.getColor(i5));
                this.textCity.setTextColor(getResources().getColor(i5));
                this.textCounty.setTextColor(getResources().getColor(i5));
            } else {
                this.textProvince.setEnabled(false);
                this.textCity.setEnabled(false);
                this.textCounty.setEnabled(false);
                this.textTown.setEnabled(false);
                TextView textView3 = this.textProvince;
                Resources resources3 = getResources();
                int i6 = R.color.un_content_level_3;
                textView3.setTextColor(resources3.getColor(i6));
                this.textCity.setTextColor(getResources().getColor(i6));
                this.textCounty.setTextColor(getResources().getColor(i6));
                this.textTown.setTextColor(getResources().getColor(i6));
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
        public JDAddressSelectView setElderMode(boolean z) {
            return this;
        }

        public void setLocationFunction(boolean z, int i2) {
            this.locationFunction = z;
            this.locationSwitch = UnAddressSelectUtils.isOpenAddressDetail() && this.locationFunction;
            if (UnLog.D) {
                UnLog.d(TAG, "locationSwitch:" + this.locationSwitch + " isOpenAddressDetail() " + UnAddressSelectUtils.isOpenAddressDetail() + " locationFunction " + this.locationFunction);
            }
            if (i2 != 3 || z) {
                return;
            }
            this.flLocation.setVisibility(8);
        }

        public void setLocationListener(ILocationClickStateListener iLocationClickStateListener) {
            LocationStateView locationStateView = this.locationView;
            if (locationStateView == null || iLocationClickStateListener == null) {
                return;
            }
            this.mClickStateListener = iLocationClickStateListener;
            locationStateView.setClickStateListener(iLocationClickStateListener);
        }

        public void setLocationStateChangeListener(ILocationStateChangeListener iLocationStateChangeListener) {
            LocationStateView locationStateView = this.locationView;
            if (locationStateView == null || iLocationStateChangeListener == null) {
                return;
            }
            locationStateView.setStateChangeListener(iLocationStateChangeListener);
        }

        public void setLocationViewShowListener(OnLocationViewShowListener onLocationViewShowListener) {
            LocationStateView locationStateView = this.locationView;
            if (locationStateView == null || onLocationViewShowListener == null) {
                return;
            }
            locationStateView.setShowListener(onLocationViewShowListener);
        }

        public void setRequestCode(int i2) {
            if (i2 == 0) {
                i2 = 100;
            }
            this.requestCode = i2;
            LocationStateView locationStateView = this.locationView;
            if (locationStateView != null) {
                locationStateView.setRequestCode(i2);
            }
        }

        @Deprecated
        public void setResource(boolean z) {
        }

        public void setSaveBusiness(String str) {
            this.saveBusiness = str;
        }

        public void setSceneId(String str) {
            LocationStateView locationStateView = this.locationView;
            if (locationStateView != null) {
                locationStateView.setSceneId(str);
            }
            this.sceneId = str;
        }

        public void setSource(String str) {
            this.source = str;
        }

        public void setStyle() {
            LocationStateView locationStateView;
            if (this.locationSwitch && (locationStateView = this.locationView) != null) {
                locationStateView.setStyle();
            }
        }

        public void setTitleText(boolean z) {
            this.isUnSupportAddress = z;
            String string = this.mContext.getString(R.string.address_send_to);
            if (this.isUnSupportAddress) {
                string = this.mContext.getString(R.string.address_send_to_reselect);
            }
            this.mTitle.setText(string);
        }

        public void setTopCorners(boolean z) {
            this.topCorners = z;
            if (z) {
                if (isDarkMode()) {
                    this.mRootView.setBackgroundResource(R.drawable.jd_dialog_bottom_common_bg_dark);
                } else {
                    this.mRootView.setBackgroundResource(R.drawable.jd_dialog_bottom_common_bg);
                }
            } else if (isDarkMode()) {
                this.mRootView.setBackgroundResource(R.color.un_bg_level_2_dark);
            } else {
                this.mRootView.setBackgroundResource(R.color.un_bg_level_2);
            }
        }

        public void setUseCustomMap(boolean z) {
            LocationStateView locationStateView = this.locationView;
            if (locationStateView != null) {
                locationStateView.setUseCustomMap(z);
            }
        }

        public void show(int i2) {
            this.mListView.setVisibility(0);
            if (i2 == 1) {
                if (this.mType == 5) {
                    this.toOtherAddress.setVisibility(8);
                } else {
                    this.toOtherAddress.setVisibility(0);
                }
                this.mNavigatorView.setVisibility(8);
                return;
            }
            this.mNavigatorView.setVisibility(0);
            this.toOtherAddress.setVisibility(8);
        }

        public void showAddress() {
            showAddress(null);
        }

        public void showThreeAddress(AddressGlobal addressGlobal) {
            showThreeAddress(addressGlobal, null, null);
        }

        public void toDefaultTheme() {
            titleContentLayoutThemeChange(true);
            this.titleBg.setVisibility(8);
            this.mClose.setImageResource(R.drawable.common_dialog_close);
            this.mTitle.setTextColor(getContext().getResources().getColor(R.color.c_1A1A1A));
            backIconTheme(true);
            if (isDarkMode()) {
                darkMode();
            } else {
                normalMode();
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
        public JDAddressSelectView darkMode() {
            TextView textView = this.mTitle;
            Resources resources = getContext().getResources();
            int i2 = R.color.un_content_level_1_dark;
            textView.setTextColor(resources.getColor(i2));
            LocationStateView locationStateView = this.locationView;
            if (locationStateView != null) {
                locationStateView.setDarkMode(true);
                this.locationView.darkMode();
            }
            if (this.topCorners) {
                this.mRootView.setBackgroundResource(R.drawable.jd_dialog_bottom_common_bg_dark);
            } else {
                this.mRootView.setBackgroundResource(R.color.un_bg_level_2_dark);
            }
            TextView textView2 = this.sumInland;
            if (textView2 != null) {
                textView2.setTextColor(getResources().getColor(i2));
            }
            TextView textView3 = this.sumSeas;
            if (textView3 != null && textView3.isEnabled()) {
                this.sumSeas.setTextColor(getResources().getColor(i2));
            }
            backIconTheme(false);
            JDSlideBar jDSlideBar = this.jdSlideBar;
            if (jDSlideBar != null) {
                jDSlideBar.setBackgroundResource(R.drawable.slidebar_bg_dark);
            }
            TextView textView4 = this.jdSlideBarNoteTv;
            if (textView4 != null) {
                textView4.setTextColor(getResources().getColor(R.color.un_content_level_2_dark));
                this.jdSlideBarNoteTv.setBackgroundResource(R.drawable.button_m_06_dark);
            }
            View view = this.deliverLine;
            if (view != null) {
                view.setBackgroundColor(getResources().getColor(R.color.un_bg_level_3_dark));
            }
            View view2 = this.locationLine;
            if (view2 != null) {
                view2.setBackgroundColor(getResources().getColor(R.color.un_bg_level_3_dark));
            }
            int[] iArr = {Color.argb(255, 255, 56, 38), Color.argb(255, 255, 56, 38), Color.argb(204, 255, 56, 38), Color.argb(102, 255, 56, 38)};
            this.gradientColor = iArr;
            this.mTabIndicator.setGradient(iArr, this.position);
            this.mSumTabIndicator.setGradient(this.gradientColor, this.position);
            RelativeLayout relativeLayout = this.toOtherAddress;
            if (relativeLayout != null && this.toOtherText != null) {
                relativeLayout.setBackgroundResource(R.drawable.button_a_dark);
                this.toOtherText.setTextColor(getContext().getResources().getColorStateList(R.color.button_a_font_color_dark));
            }
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
        public JDAddressSelectView normalMode() {
            TextView textView = this.mTitle;
            Resources resources = getContext().getResources();
            int i2 = R.color.un_content_level_1;
            textView.setTextColor(resources.getColor(i2));
            LocationStateView locationStateView = this.locationView;
            if (locationStateView != null) {
                locationStateView.setDarkMode(false);
                this.locationView.normalMode();
            }
            if (this.topCorners) {
                this.mRootView.setBackgroundResource(R.drawable.jd_dialog_bottom_common_bg);
            } else {
                this.mRootView.setBackgroundResource(R.color.un_bg_level_2);
            }
            TextView textView2 = this.sumInland;
            if (textView2 != null) {
                textView2.setTextColor(getResources().getColor(i2));
            }
            TextView textView3 = this.sumSeas;
            if (textView3 != null && textView3.isEnabled()) {
                this.sumSeas.setTextColor(getResources().getColor(i2));
            }
            backIconTheme(true);
            JDSlideBar jDSlideBar = this.jdSlideBar;
            if (jDSlideBar != null) {
                jDSlideBar.setBackgroundResource(R.drawable.slidebar_bg);
            }
            TextView textView4 = this.jdSlideBarNoteTv;
            if (textView4 != null) {
                textView4.setTextColor(getResources().getColor(R.color.un_content_level_2));
                this.jdSlideBarNoteTv.setBackgroundResource(R.drawable.button_m_06);
            }
            View view = this.deliverLine;
            if (view != null) {
                view.setBackgroundColor(getResources().getColor(R.color.un_bg_level_3));
            }
            View view2 = this.locationLine;
            if (view2 != null) {
                view2.setBackgroundColor(getResources().getColor(R.color.un_bg_level_3));
            }
            Resources resources2 = getResources();
            int i3 = R.color.c_F2270C;
            int[] iArr = {resources2.getColor(i3), getResources().getColor(i3), getResources().getColor(R.color.c_F5503A), getResources().getColor(R.color.c_FAD1CB)};
            this.gradientColor = iArr;
            this.mTabIndicator.setGradient(iArr, this.position);
            this.mSumTabIndicator.setGradient(this.gradientColor, this.position);
            RelativeLayout relativeLayout = this.toOtherAddress;
            if (relativeLayout != null && this.toOtherText != null) {
                relativeLayout.setBackgroundResource(R.drawable.button_b);
                this.toOtherText.setTextColor(getContext().getResources().getColorStateList(R.color.button_b_font_color_dark));
            }
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
        public JDAddressSelectView setAutoDarkMode(boolean z) {
            this.isAutoDark = z;
            LocationStateView locationStateView = this.locationView;
            if (locationStateView != null) {
                locationStateView.setAutoDarkMode(z);
            }
            JDSlideBar jDSlideBar = this.jdSlideBar;
            if (jDSlideBar != null) {
                jDSlideBar.setAutoDarkMode(this.isAutoDark);
            }
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
        public JDAddressSelectView setDarkMode(boolean z) {
            this.isDarkMode = z;
            return this;
        }

        public void showAddress(AddressGlobal addressGlobal) {
            resetDataCommon();
            hide();
            if (addressGlobal != null && addressGlobal.getIsUserAddress().booleanValue()) {
                this.mAddressGlobal.setId(addressGlobal.getId());
            }
            if (UnLog.D) {
                String jSONString = JDJSON.toJSONString(this.mAddressGlobal);
                UnLog.d(TAG, "showAddress_mAddressGlobal = " + jSONString);
            }
            if (this.mAddressHelper != null) {
                showProgress();
                if (LoginUserBase.hasLogin() && !this.isUnSupportAddress) {
                    this.mAddressHelper.loadAddress(1, "", this.mAddressGlobal, this.mOnAddressLoadCompletedListener);
                } else {
                    this.mAddressHelper.loadAddress(2, "", this.mAddressGlobal, this.mOnAddressLoadCompletedListener);
                }
            }
        }

        public void showThreeAddress(AddressGlobal addressGlobal, String str, final String str2) {
            resetDataThreeAddress();
            if (addressGlobal != null) {
                cloneAddressThreeAddress(addressGlobal);
                this.needPosition = true;
            }
            if (UnLog.D) {
                String jSONString = JDJSON.toJSONString(this.mAddressGlobal);
                UnLog.d(TAG, "showThreeAddress_mAddressGlobal = " + jSONString);
            }
            queryData("", 2);
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                this.deliverLine.setVisibility(0);
                this.mTipLayout.setVisibility(0);
                this.mAddressTip.setText(str);
                this.mTipLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.JDAddressSelectView.8
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        URLParamMap uRLParamMap = new URLParamMap();
                        StringBuilder sb = new StringBuilder();
                        sb.append(str2);
                        JDAddressSelectView jDAddressSelectView = JDAddressSelectView.this;
                        sb.append(jDAddressSelectView.optionsAllAddressId(jDAddressSelectView.mAddressGlobal));
                        uRLParamMap.put(RemoteMessageConst.TO, sb.toString());
                        Bundle bundle = new Bundle();
                        SerializableContainer serializableContainer = new SerializableContainer();
                        serializableContainer.setMap(uRLParamMap);
                        bundle.putSerializable("urlParamMap", serializableContainer);
                        bundle.putString("urlAction", RemoteMessageConst.TO);
                        bundle.putBoolean(MBaseKeyNames.IS_USE_RIGHT_BUTTON, false);
                        DeepLinkCommonHelper.startWebActivity(JDAddressSelectView.this.mContext, bundle, true);
                    }
                });
                return;
            }
            this.deliverLine.setVisibility(8);
            this.mTipLayout.setVisibility(8);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public class MyAdapter extends StickyHeaderAdapter {
            SelectAddressItemEntity[] data;
            int selectedPosition;

            /* loaded from: classes6.dex */
            class HeaderViewHolder {
                public TextView headerTv;
                public View itemLine;
                public View itemView;
                public LinearLayout llRoot;

                public HeaderViewHolder(View view) {
                    this.itemView = view;
                    this.llRoot = (LinearLayout) view.findViewById(R.id.ll_root);
                    this.headerTv = (TextView) view.findViewById(R.id.letter_header_tv);
                    this.itemLine = view.findViewById(R.id.itemLine);
                }
            }

            /* loaded from: classes6.dex */
            class ViewHolder {
                View divider;
                ImageView img1;
                ImageView img2;
                View layout1;
                View layout2;
                View rootLayout;
                TextView txt1;
                TextView txt2;
                TextView txt21;

                ViewHolder() {
                }
            }

            private MyAdapter() {
                this.selectedPosition = 0;
            }

            @Override // android.widget.Adapter
            public int getCount() {
                SelectAddressItemEntity[] selectAddressItemEntityArr = this.data;
                if (selectAddressItemEntityArr == null) {
                    return 0;
                }
                return selectAddressItemEntityArr.length;
            }

            @Override // com.jingdong.common.stickyListView.StickyHeaderAdapter
            public View getHeaderView(int i2, View view, ViewGroup viewGroup) {
                HeaderViewHolder headerViewHolder;
                if (view == null) {
                    view = LayoutInflater.from(JDAddressSelectView.this.getContext()).inflate(R.layout.jd_address_layout_header, viewGroup, false);
                    headerViewHolder = new HeaderViewHolder(view);
                    view.setTag(headerViewHolder);
                } else {
                    headerViewHolder = view.getTag() instanceof HeaderViewHolder ? (HeaderViewHolder) view.getTag() : null;
                }
                if (headerViewHolder != null) {
                    headerViewHolder.headerTv.setText(this.data[i2].content);
                    if (JDAddressSelectView.this.isDarkMode()) {
                        headerViewHolder.llRoot.setBackgroundColor(JDAddressSelectView.this.mContext.getResources().getColor(R.color.un_bg_level_2_dark));
                        headerViewHolder.headerTv.setTextColor(JDAddressSelectView.this.getResources().getColor(R.color.un_content_level_1_dark));
                        headerViewHolder.itemLine.setBackgroundColor(JDAddressSelectView.this.getResources().getColor(R.color.un_bg_level_3_dark));
                    } else {
                        headerViewHolder.llRoot.setBackgroundColor(JDAddressSelectView.this.mContext.getResources().getColor(R.color.un_bg_level_2));
                        headerViewHolder.headerTv.setTextColor(JDAddressSelectView.this.getResources().getColor(R.color.un_content_level_1));
                        headerViewHolder.itemLine.setBackgroundColor(JDAddressSelectView.this.getResources().getColor(R.color.un_bg_level_3));
                    }
                }
                return view;
            }

            @Override // com.jingdong.common.stickyListView.StickyHeaderAdapter
            public List<Integer> getHeaders() {
                return this.headers;
            }

            @Override // android.widget.Adapter
            public Object getItem(int i2) {
                SelectAddressItemEntity[] selectAddressItemEntityArr = this.data;
                if (selectAddressItemEntityArr == null) {
                    return null;
                }
                return selectAddressItemEntityArr[i2];
            }

            @Override // android.widget.Adapter
            public long getItemId(int i2) {
                return i2;
            }

            @Override // com.jingdong.common.stickyListView.StickyHeaderAdapter
            public View getItemView(int i2, View view, ViewGroup viewGroup) {
                SelectAddressItemEntity selectAddressItemEntity;
                if (view == null) {
                    view = ImageUtil.inflate(R.layout.jd_address_layout_item, null);
                    ViewHolder viewHolder = new ViewHolder();
                    viewHolder.rootLayout = view.findViewById(R.id.item_root);
                    View findViewById = view.findViewById(R.id.l_layout_1);
                    viewHolder.layout1 = findViewById;
                    int i3 = R.id.txt_1;
                    viewHolder.txt1 = (TextView) findViewById.findViewById(i3);
                    View view2 = viewHolder.layout1;
                    int i4 = R.id.img_1;
                    viewHolder.img1 = (ImageView) view2.findViewById(i4);
                    View findViewById2 = view.findViewById(R.id.l_layout_2);
                    viewHolder.layout2 = findViewById2;
                    viewHolder.txt21 = (TextView) findViewById2.findViewById(R.id.txt_2);
                    viewHolder.txt2 = (TextView) viewHolder.layout2.findViewById(i3);
                    viewHolder.img2 = (ImageView) viewHolder.layout2.findViewById(i4);
                    viewHolder.divider = view.findViewById(R.id.divider);
                    view.setTag(viewHolder);
                }
                if (view.getTag() instanceof ViewHolder) {
                    ViewHolder viewHolder2 = (ViewHolder) view.getTag();
                    if (i2 > getCount() || (selectAddressItemEntity = this.data[i2]) == null) {
                        return view;
                    }
                    if (JDAddressSelectView.this.mCurrent == 1) {
                        viewHolder2.layout1.setVisibility(8);
                        viewHolder2.layout2.setVisibility(0);
                        viewHolder2.txt2.setText(selectAddressItemEntity.detail);
                        viewHolder2.txt21.setText(selectAddressItemEntity.content);
                        if (JDAddressSelectView.this.isElderMode()) {
                            viewHolder2.txt2.setTextSize(18.0f);
                            viewHolder2.txt21.setTextSize(14.0f);
                            TextView textView = viewHolder2.txt2;
                            Resources resources = JDAddressSelectView.this.mContext.getResources();
                            int i5 = R.color.c_333333;
                            textView.setTextColor(resources.getColor(i5));
                            viewHolder2.txt21.setTextColor(JDAddressSelectView.this.mContext.getResources().getColor(i5));
                            if (i2 == getCount() - 1) {
                                viewHolder2.divider.setVisibility(8);
                            } else {
                                viewHolder2.divider.setVisibility(0);
                            }
                        } else {
                            viewHolder2.txt2.setTextSize(16.0f);
                            viewHolder2.txt21.setTextSize(12.0f);
                            TextView textView2 = viewHolder2.txt2;
                            Resources resources2 = JDAddressSelectView.this.mContext.getResources();
                            int i6 = R.color.c_262626;
                            textView2.setTextColor(resources2.getColor(i6));
                            viewHolder2.txt21.setTextColor(JDAddressSelectView.this.mContext.getResources().getColor(i6));
                            viewHolder2.divider.setVisibility(8);
                        }
                        if (i2 == this.selectedPosition) {
                            viewHolder2.txt2.setTypeface(Typeface.defaultFromStyle(1));
                            viewHolder2.img2.setImageResource(R.drawable.un_icon_address_select);
                        } else {
                            viewHolder2.txt2.setTypeface(Typeface.defaultFromStyle(0));
                            ImageView imageView = viewHolder2.img2;
                            int i7 = R.drawable.un_icon_location_success_new;
                            imageView.setImageResource(i7);
                            if (JDAddressSelectView.this.isDarkMode()) {
                                viewHolder2.img2.setImageResource(R.drawable.un_icon_location_success_new_dark);
                            } else {
                                viewHolder2.img2.setImageResource(i7);
                            }
                        }
                        if (JDAddressSelectView.this.isDarkMode()) {
                            TextView textView3 = viewHolder2.txt2;
                            Resources resources3 = JDAddressSelectView.this.mContext.getResources();
                            int i8 = R.color.c_ECECEC;
                            textView3.setTextColor(resources3.getColor(i8));
                            viewHolder2.txt21.setTextColor(JDAddressSelectView.this.mContext.getResources().getColor(i8));
                        } else {
                            TextView textView4 = viewHolder2.txt2;
                            Resources resources4 = JDAddressSelectView.this.mContext.getResources();
                            int i9 = R.color.c_262626;
                            textView4.setTextColor(resources4.getColor(i9));
                            viewHolder2.txt21.setTextColor(JDAddressSelectView.this.mContext.getResources().getColor(i9));
                        }
                    } else {
                        viewHolder2.divider.setVisibility(8);
                        viewHolder2.layout1.setVisibility(0);
                        viewHolder2.layout2.setVisibility(8);
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewHolder2.layout1.getLayoutParams();
                        layoutParams.bottomMargin = DpiUtil.dip2px(JDAddressSelectView.this.mContext, 12.0f);
                        layoutParams.topMargin = DpiUtil.dip2px(JDAddressSelectView.this.mContext, 12.0f);
                        int i10 = i2 + 1;
                        if (i10 < getCount() && getItemViewType(i10) == 0) {
                            layoutParams.bottomMargin = DpiUtil.dip2px(JDAddressSelectView.this.mContext, 24.0f);
                        } else if (i10 == getCount()) {
                            layoutParams.bottomMargin = DpiUtil.dip2px(JDAddressSelectView.this.mContext, 24.0f);
                        }
                        if (!JDAddressSelectView.this.isDarkMode()) {
                            viewHolder2.rootLayout.setBackgroundColor(JDAddressSelectView.this.mContext.getResources().getColor(R.color.un_bg_level_2));
                        } else {
                            viewHolder2.rootLayout.setBackgroundColor(JDAddressSelectView.this.mContext.getResources().getColor(R.color.un_bg_level_2_dark));
                        }
                        int i11 = i2 - 1;
                        if (i11 > 0 && getItemViewType(i11) == 0) {
                            layoutParams.topMargin = DpiUtil.dip2px(JDAddressSelectView.this.mContext, 24.0f);
                        } else if (i2 == 0) {
                            layoutParams.topMargin = DpiUtil.dip2px(JDAddressSelectView.this.mContext, 24.0f);
                        }
                        viewHolder2.layout1.setLayoutParams(layoutParams);
                        viewHolder2.txt1.setText(selectAddressItemEntity.content);
                        if (selectAddressItemEntity.style == 1) {
                            if (JDAddressSelectView.this.isDarkMode()) {
                                viewHolder2.txt1.setTextColor(JDAddressSelectView.this.mContext.getResources().getColor(R.color.un_jd_main_dark));
                            } else {
                                viewHolder2.txt1.setTextColor(JDAddressSelectView.this.mContext.getResources().getColor(R.color.un_jd_main));
                            }
                        } else if (JDAddressSelectView.this.isDarkMode()) {
                            viewHolder2.txt1.setTextColor(JDAddressSelectView.this.mContext.getResources().getColor(R.color.un_content_level_1_dark));
                        } else {
                            viewHolder2.txt1.setTextColor(JDAddressSelectView.this.mContext.getResources().getColor(R.color.un_content_level_1));
                        }
                        if (i2 == this.selectedPosition) {
                            viewHolder2.txt1.setTypeface(Typeface.defaultFromStyle(1));
                            viewHolder2.img1.setVisibility(0);
                            viewHolder2.img1.setImageResource(R.drawable.jd_address_select);
                        } else {
                            viewHolder2.txt1.setTypeface(Typeface.defaultFromStyle(0));
                            viewHolder2.img1.setVisibility(8);
                        }
                    }
                }
                return view;
            }

            void setContent(int i2, SelectAddressItemEntity[] selectAddressItemEntityArr) {
                this.selectedPosition = i2;
                this.data = selectAddressItemEntityArr;
                notifyDataSetChanged();
            }

            void setSelected(int i2) {
                this.selectedPosition = i2;
                notifyDataSetChanged();
            }

            void setContent(int i2, SelectAddressItemEntity[] selectAddressItemEntityArr, List<Integer> list, SparseIntArray sparseIntArray, SparseIntArray sparseIntArray2) {
                this.data = selectAddressItemEntityArr;
                this.headers = list;
                this.realToDataMap = sparseIntArray;
                this.dataTorealMap = sparseIntArray2;
                changeHeaderStatus(true);
                this.selectedPosition = changeToRealPosition(i2);
                notifyDataSetChanged();
            }
        }

        private int getDefaultPosition(long j2, ArrayList<AddressGlobal> arrayList) {
            if (0 == j2 || arrayList == null || arrayList.isEmpty()) {
                return -1;
            }
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (j2 == arrayList.get(i2).getId()) {
                    return i2;
                }
            }
            return -1;
        }

        public void setTitleText(String str) {
            String string = this.mContext.getString(R.string.address_send_to);
            if (TextUtils.isEmpty(str)) {
                str = string;
            }
            this.mTitle.setText(str);
        }

        public void changeToTheme(Bitmap bitmap, Integer num, Drawable drawable) {
            if (bitmap == null || bitmap.isRecycled()) {
                return;
            }
            titleContentLayoutThemeChange(false);
            this.titleBg.setVisibility(0);
            this.titleBg.setImageBitmap(bitmap);
            if (num != null) {
                this.mTitle.setTextColor(num.intValue());
            }
            if (drawable != null) {
                this.mClose.setImageDrawable(drawable);
            }
            backIconTheme(false);
            if (isDarkMode()) {
                darkMode();
            } else {
                normalMode();
            }
        }

        public void changeToTheme(Drawable drawable, Integer num, Drawable drawable2) {
            if (drawable == null) {
                return;
            }
            titleContentLayoutThemeChange(false);
            this.titleBg.setVisibility(0);
            this.titleBg.setImageDrawable(drawable);
            if (num != null) {
                this.mTitle.setTextColor(num.intValue());
            }
            if (drawable2 != null) {
                this.mClose.setImageDrawable(drawable2);
            }
            backIconTheme(false);
            if (isDarkMode()) {
                darkMode();
            } else {
                normalMode();
            }
        }

        public JDAddressSelectView(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, 0);
        }

        public JDAddressSelectView(Context context) {
            this(context, null, 0);
        }

        public JDAddressSelectView(Context context, boolean z) {
            super(context, null, 0);
            this.provinceList = null;
            this.cityList = null;
            this.countyList = null;
            this.townList = null;
            this.mCurrent = 1;
            this.mSumCurrent = 101;
            this.sumTabSwitch = false;
            this.overSeasId = 0;
            this.cacheProvince = 0;
            this.cacheCity = 0;
            this.cacheCounty = 0;
            this.cacheTown = 0;
            this.cacheIsForeignOverSea = false;
            this.cacheIsGangAoTai = false;
            this.mIsDestroy = false;
            this.mClickTime = 0L;
            this.mHotAreaClickTime = 0L;
            this.mSumTabClickTime = 0L;
            this.needPosition = false;
            this.locationFunction = false;
            this.locationSwitch = false;
            this.mHaveHotCityHeader = false;
            this.mHandler = new Handler();
            this.isAutoDark = false;
            this.gradientColor = null;
            this.position = null;
            this.topCorners = false;
            this.clickAddressEnable = -1;
            this.isClick = false;
            this.sceneId = "basicShoppingProcess";
            this.mOnAddressLoadCompletedListener = new OnAddressLoadCompletedListener() { // from class: com.jingdong.common.ui.JDAddressSelectView.1
                @Override // com.jingdong.common.ui.JDAddressSelectView.OnAddressLoadCompletedListener
                public void onFullAddressLoadCompleted(final boolean z2, final ArrayList arrayList) {
                    JDAddressSelectView.this.mHandler.post(new Runnable() { // from class: com.jingdong.common.ui.JDAddressSelectView.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (JDAddressSelectView.this.mIsDestroy) {
                                return;
                            }
                            JDAddressSelectView.this.hideProgress();
                            JDAddressSelectView.this.removeHotCityHeader();
                            if (z2) {
                                JDAddressSelectView.this.doAddress(arrayList);
                            }
                        }
                    });
                }

                @Override // com.jingdong.common.ui.JDAddressSelectView.OnAddressLoadCompletedListener
                public void onThirdAddressLoadCompleted(final boolean z2, final int i3, final ArrayList arrayList, final ArrayList arrayList2, final ArrayList arrayList3) {
                    JDAddressSelectView.this.mHandler.post(new Runnable() { // from class: com.jingdong.common.ui.JDAddressSelectView.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (!JDAddressSelectView.this.mIsDestroy && z2) {
                                if (UnLog.D) {
                                    UnLog.d(JDAddressSelectView.TAG, "mCurrent " + JDAddressSelectView.this.mCurrent + " flag " + i3);
                                }
                                ArrayList arrayList4 = arrayList;
                                if (arrayList4 != null && arrayList4.size() > 0) {
                                    if (UnLog.D) {
                                        UnLog.d(JDAddressSelectView.TAG, "address sort");
                                    }
                                    JDAddressSelectView jDAddressSelectView = JDAddressSelectView.this;
                                    if (jDAddressSelectView.sumTabSwitch) {
                                        jDAddressSelectView.sortAddressListProcess(arrayList);
                                    } else if (i3 > 3 && jDAddressSelectView.mAddressGlobal != null && JDAddressSelectView.this.mAddressGlobal.isForeignOverSea) {
                                        JDAddressSelectView.this.sortAddressList(arrayList);
                                    }
                                }
                                if (JDAddressSelectView.this.mType == 1) {
                                    JDAddressSelectView.this.checkEndAndSave(arrayList);
                                }
                                int i4 = i3;
                                if (i4 == 2) {
                                    JDAddressSelectView.this.initSumTabView(arrayList2, arrayList3);
                                    ArrayList arrayList5 = arrayList;
                                    if (arrayList5 != null && arrayList5.size() > 0) {
                                        JDAddressSelectView jDAddressSelectView2 = JDAddressSelectView.this;
                                        if (jDAddressSelectView2.sumTabSwitch) {
                                            jDAddressSelectView2.sortAddressListProcess(arrayList);
                                        }
                                    }
                                    if (!JDAddressSelectView.this.needPosition) {
                                        if (JDAddressSelectView.this.checkEndAndSave(arrayList)) {
                                            return;
                                        }
                                        JDAddressSelectView.this.doProvice(arrayList);
                                        return;
                                    }
                                    JDAddressSelectView jDAddressSelectView3 = JDAddressSelectView.this;
                                    int i5 = jDAddressSelectView3.mSumCurrent;
                                    if (i5 == 101) {
                                        jDAddressSelectView3.checkAndDoNext(i3, arrayList);
                                    } else if (i5 == 102) {
                                        jDAddressSelectView3.checkAndDoNextSum(i3, arrayList);
                                    }
                                } else if (i4 == 3) {
                                    if (!JDAddressSelectView.this.needPosition) {
                                        if (JDAddressSelectView.this.checkEndAndSave(arrayList)) {
                                            return;
                                        }
                                        JDAddressSelectView.this.doCity(arrayList);
                                        return;
                                    }
                                    JDAddressSelectView jDAddressSelectView4 = JDAddressSelectView.this;
                                    int i6 = jDAddressSelectView4.mSumCurrent;
                                    if (i6 == 101) {
                                        jDAddressSelectView4.checkAndDoNext(i3, arrayList);
                                    } else if (i6 == 102) {
                                        jDAddressSelectView4.checkAndDoNextSum(i3, arrayList);
                                    }
                                } else if (i4 == 4) {
                                    if (!JDAddressSelectView.this.needPosition) {
                                        if (JDAddressSelectView.this.checkEndAndSave(arrayList)) {
                                            return;
                                        }
                                        JDAddressSelectView.this.doCounty(arrayList);
                                        return;
                                    }
                                    JDAddressSelectView jDAddressSelectView5 = JDAddressSelectView.this;
                                    int i7 = jDAddressSelectView5.mSumCurrent;
                                    if (i7 == 101) {
                                        jDAddressSelectView5.checkAndDoNext(i3, arrayList);
                                    } else if (i7 == 102) {
                                        jDAddressSelectView5.checkAndDoNextSum(i3, arrayList);
                                    }
                                } else if (i4 != 5) {
                                    if (i4 != 11) {
                                        return;
                                    }
                                    JDAddressSelectView.this.saveAddress();
                                } else if (!JDAddressSelectView.this.needPosition) {
                                    if (JDAddressSelectView.this.checkEndAndSave(arrayList)) {
                                        return;
                                    }
                                    JDAddressSelectView.this.doTown(arrayList);
                                } else {
                                    JDAddressSelectView jDAddressSelectView6 = JDAddressSelectView.this;
                                    int i8 = jDAddressSelectView6.mSumCurrent;
                                    if (i8 == 101) {
                                        jDAddressSelectView6.checkAndDoNext(i3, arrayList);
                                    } else if (i8 == 102) {
                                        jDAddressSelectView6.checkAndDoNextSum(i3, arrayList);
                                    }
                                }
                            }
                        }
                    });
                }
            };
            this.mContext = context;
            this.isAutoDark = z;
            initView();
            this.mAddressGlobal = new AddressGlobal();
        }
    }
