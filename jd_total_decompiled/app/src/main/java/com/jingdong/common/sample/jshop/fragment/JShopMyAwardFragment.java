package com.jingdong.common.sample.jshop.fragment;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.R;
import com.jingdong.cleanmvp.ui.BaseFragment;
import com.jingdong.common.sample.jshop.Entity.JShopSpecialPriceBean;
import com.jingdong.common.sample.jshop.JShopSignNewActivity;
import com.jingdong.common.sample.jshop.JShopSpecialPriceAdapter;
import com.jingdong.common.sample.jshop.JshopAwardCouponAdapter;
import com.jingdong.common.shop.JshopTakeCouponUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class JShopMyAwardFragment extends BaseFragment implements View.OnClickListener {
    private static final int COUPONS = 2;
    private static final long DURATION = 300;
    private static final int REAL_GOODS = 1;
    private static final int SPECIAL_PRICE = 0;
    private static final String TAG = "JShopMyAwardFragment";
    private static final String prizeFunctionId = "getSignPrize";
    private static final String prizeTypeFunctionId = "getSignPrizeType";
    private JShopSpecialPriceAdapter adapter;
    private JshopAwardCouponAdapter couponAdapter;
    private JShopSignNewActivity mActivity;
    private ImageView mArrowDown;
    private ImageView mArrowUp;
    private Button mBtnMainShop;
    private Button mBtnRetry;
    private LinearLayout mFailView;
    private Button mNoDataBtn;
    private ImageView mNoDataImage;
    private TextView mNoDataTV1;
    private TextView mNoDataTV2;
    private TextView mNoDataTV3;
    private LinearLayout mNoDataView;
    private ListView special_listview;
    private View tab_coupons;
    private View tab_group;
    private View tab_real_goods;
    private View tab_specail_price;
    private View used_view_down;
    private TextView used_view_down_tv;
    private View used_view_up;
    private TextView used_view_up_tv;
    private int curTab = 0;
    boolean hasSpecialPrice = false;
    boolean hasCoupon = false;
    private long vendorId = -1;
    private ArrayList<JShopSpecialPriceBean> canUseSpecailList = null;
    private ArrayList<JShopSpecialPriceBean> cannotUseSpecailList = null;
    private ArrayList<JShopSpecialPriceBean> canUseCouponList = null;
    private ArrayList<JShopSpecialPriceBean> cannotUseCouponList = null;
    private boolean hasSpecialPost = false;
    private boolean hasCouponPost = false;
    private boolean isFirst = false;

    public void checkCanUseViewIsShow() {
        ArrayList<JShopSpecialPriceBean> arrayList;
        int i2 = this.curTab;
        if (i2 == 0) {
            arrayList = this.cannotUseSpecailList;
            this.used_view_up_tv.setText(R.string.jshop_used_award_text_specail);
            this.used_view_down_tv.setText(R.string.jshop_used_award_text_specail);
        } else if (i2 == 2) {
            arrayList = this.cannotUseCouponList;
            this.used_view_up_tv.setText(R.string.jshop_used_award_text_specail);
            this.used_view_down_tv.setText(R.string.jshop_used_award_text_specail);
        } else {
            arrayList = null;
        }
        if (arrayList != null && arrayList.size() > 0) {
            this.used_view_up.setVisibility(8);
            this.used_view_down.setVisibility(0);
            return;
        }
        this.used_view_up.setVisibility(8);
        this.used_view_down.setVisibility(8);
    }

    public void checkCouponData() {
        ArrayList<JShopSpecialPriceBean> arrayList = this.canUseSpecailList;
        if (arrayList == null || arrayList.size() <= 0) {
            ArrayList<JShopSpecialPriceBean> arrayList2 = this.cannotUseSpecailList;
            if (arrayList2 == null || arrayList2.size() <= 0) {
                ArrayList<JShopSpecialPriceBean> arrayList3 = this.canUseCouponList;
                if (arrayList3 == null || arrayList3.size() <= 0) {
                    ArrayList<JShopSpecialPriceBean> arrayList4 = this.cannotUseCouponList;
                    if (arrayList4 == null || arrayList4.size() <= 0) {
                        this.tab_group.setVisibility(8);
                        showEmptyView();
                    }
                }
            }
        }
    }

    public void checkSpecialData() {
        ArrayList<JShopSpecialPriceBean> arrayList = this.canUseSpecailList;
        if (arrayList == null || arrayList.size() <= 0) {
            ArrayList<JShopSpecialPriceBean> arrayList2 = this.cannotUseSpecailList;
            if ((arrayList2 == null || arrayList2.size() <= 0) && !this.hasCouponPost) {
                this.curTab = 2;
                showTab();
                postSignPrize(0);
            }
        }
    }

    private void convertValue(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.vendorId = Long.parseLong(str);
        } catch (NumberFormatException unused) {
        }
    }

    public void handleSpecialPrice(ArrayList<JShopSpecialPriceBean> arrayList, boolean z) {
        ArrayList<JShopSpecialPriceBean> arrayList2 = arrayList != null ? (ArrayList) arrayList.clone() : null;
        if (arrayList2 != null && arrayList2.size() > 0) {
            this.mNoDataView.setVisibility(8);
            this.special_listview.setVisibility(0);
            int i2 = this.curTab;
            if (i2 == 0) {
                JShopSpecialPriceAdapter jShopSpecialPriceAdapter = this.adapter;
                if (jShopSpecialPriceAdapter == null) {
                    this.adapter = new JShopSpecialPriceAdapter(this.mActivity, arrayList2, z);
                } else {
                    jShopSpecialPriceAdapter.setData(arrayList2, z);
                }
                this.adapter.notifyDataSetChanged();
                this.special_listview.setAdapter((ListAdapter) this.adapter);
                return;
            } else if (i2 == 2) {
                JshopAwardCouponAdapter jshopAwardCouponAdapter = this.couponAdapter;
                if (jshopAwardCouponAdapter == null) {
                    this.couponAdapter = new JshopAwardCouponAdapter(this.mActivity, arrayList2);
                } else {
                    jshopAwardCouponAdapter.setData(arrayList2);
                }
                this.couponAdapter.notifyDataSetChanged();
                this.special_listview.setAdapter((ListAdapter) this.couponAdapter);
                return;
            } else {
                return;
            }
        }
        this.special_listview.setVisibility(8);
        showEmptyView();
    }

    public void postSignPrize(final int i2) {
        if (i2 == 1 && this.hasSpecialPost) {
            checkCanUseViewIsShow();
            handleSpecialPrice(this.canUseSpecailList, true);
        } else if (i2 == 0 && this.hasCouponPost) {
            checkCanUseViewIsShow();
            handleSpecialPrice(this.canUseCouponList, true);
        } else {
            this.mActivity.setSubRootView(null);
            setViewGone();
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(Configuration.getJshopHost());
            httpSetting.setFunctionId(prizeFunctionId);
            httpSetting.putJsonParam("type", Integer.valueOf(i2));
            httpSetting.putJsonParam("vendorId", this.vendorId + "");
            JshopTakeCouponUtils.getInstance().addRMParams(getContext(), httpSetting, "-1");
            httpSetting.setUseFastJsonParser(true);
            httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopMyAwardFragment.2
                {
                    JShopMyAwardFragment.this = this;
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    if (httpResponse == null) {
                        JShopMyAwardFragment.this.showNetworkFailView(i2);
                        return;
                    }
                    final JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                    Log.d(JShopMyAwardFragment.TAG, "response json --> : " + fastJsonObject);
                    JShopMyAwardFragment.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopMyAwardFragment.2.1
                        {
                            AnonymousClass2.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                            int i3 = i2;
                            if (i3 == 1) {
                                JShopMyAwardFragment.this.hasSpecialPost = true;
                                JShopMyAwardFragment.this.canUseSpecailList = JShopSpecialPriceBean.toList(fastJsonObject.optJSONArray("result"), 1);
                                JShopMyAwardFragment.this.cannotUseSpecailList = JShopSpecialPriceBean.toList(fastJsonObject.optJSONArray("result"), 2);
                                JShopMyAwardFragment.this.checkCanUseViewIsShow();
                                JShopMyAwardFragment jShopMyAwardFragment = JShopMyAwardFragment.this;
                                jShopMyAwardFragment.handleSpecialPrice(jShopMyAwardFragment.canUseSpecailList, true);
                                JShopMyAwardFragment.this.checkSpecialData();
                            } else if (i3 == 0) {
                                JShopMyAwardFragment.this.hasCouponPost = true;
                                JShopMyAwardFragment.this.canUseCouponList = JShopSpecialPriceBean.toList(fastJsonObject.optJSONArray("result"), 3);
                                JShopMyAwardFragment.this.cannotUseCouponList = JShopSpecialPriceBean.toList(fastJsonObject.optJSONArray("result"), 4);
                                JShopMyAwardFragment.this.checkCanUseViewIsShow();
                                JShopMyAwardFragment jShopMyAwardFragment2 = JShopMyAwardFragment.this;
                                jShopMyAwardFragment2.handleSpecialPrice(jShopMyAwardFragment2.canUseCouponList, true);
                                JShopMyAwardFragment.this.checkCouponData();
                            }
                        }
                    });
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    JShopMyAwardFragment.this.showNetworkFailView(i2);
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                public void onProgress(int i3, int i4) {
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                public void onStart() {
                }
            });
            this.mActivity.getHttpGroupaAsynPool().add(httpSetting);
        }
    }

    public void postSignPrizeType() {
        this.isFirst = true;
        this.mActivity.setSubRootView(null);
        this.mNoDataView.setVisibility(8);
        this.mFailView.setVisibility(8);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(Configuration.getJshopHost());
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setFunctionId(prizeTypeFunctionId);
        httpSetting.putJsonParam("vendorId", Long.valueOf(this.vendorId));
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopMyAwardFragment.1
            {
                JShopMyAwardFragment.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JShopMyAwardFragment.this.mActivity.isSignSuccess = false;
                if (httpResponse == null) {
                    JShopMyAwardFragment.this.showNetworkFailView1();
                    return;
                }
                final JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                Log.d(JShopMyAwardFragment.TAG, "response json --> : " + fastJsonObject);
                JShopMyAwardFragment.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopMyAwardFragment.1.1
                    {
                        AnonymousClass1.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        String optString = fastJsonObject.optString("result");
                        if (!TextUtils.isEmpty(optString)) {
                            JShopMyAwardFragment.this.hasSpecialPrice = optString.contains("1");
                            JShopMyAwardFragment.this.hasCoupon = optString.contains("0");
                        }
                        JShopMyAwardFragment jShopMyAwardFragment = JShopMyAwardFragment.this;
                        if (jShopMyAwardFragment.hasSpecialPrice || jShopMyAwardFragment.hasCoupon) {
                            jShopMyAwardFragment.tab_group.setVisibility(0);
                            JShopMyAwardFragment jShopMyAwardFragment2 = JShopMyAwardFragment.this;
                            if (!jShopMyAwardFragment2.hasCoupon) {
                                jShopMyAwardFragment2.hasCouponPost = true;
                            }
                            JShopMyAwardFragment jShopMyAwardFragment3 = JShopMyAwardFragment.this;
                            if (!jShopMyAwardFragment3.hasSpecialPrice) {
                                jShopMyAwardFragment3.hasSpecialPost = true;
                            }
                            JShopMyAwardFragment jShopMyAwardFragment4 = JShopMyAwardFragment.this;
                            if (jShopMyAwardFragment4.hasSpecialPrice) {
                                jShopMyAwardFragment4.curTab = 0;
                                JShopMyAwardFragment.this.postSignPrize(1);
                            } else if (jShopMyAwardFragment4.hasCoupon) {
                                jShopMyAwardFragment4.curTab = 2;
                                JShopMyAwardFragment.this.postSignPrize(0);
                            }
                            JShopMyAwardFragment.this.showTab();
                            return;
                        }
                        jShopMyAwardFragment.tab_group.setVisibility(8);
                        JShopMyAwardFragment.this.showEmptyView();
                    }
                });
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                JShopMyAwardFragment.this.showNetworkFailView1();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        this.mActivity.getHttpGroupaAsynPool().add(httpSetting);
    }

    private void setViewGone() {
        this.used_view_up.setVisibility(8);
        this.used_view_down.setVisibility(8);
        this.mNoDataView.setVisibility(8);
        this.mFailView.setVisibility(8);
        this.special_listview.setVisibility(8);
    }

    private void showCanUseOrNot() {
        String str;
        int i2 = this.curTab;
        String str2 = JshopConst.JSKEY_CATE_OPEN;
        String str3 = "close";
        if (i2 == 0) {
            if (this.used_view_up.getVisibility() == 0) {
                handleSpecialPrice(this.cannotUseSpecailList, false);
            } else {
                if (this.used_view_down.getVisibility() == 0) {
                    handleSpecialPrice(this.canUseSpecailList, true);
                }
                str2 = "close";
            }
            str = "\u4e13\u4eab\u4ef7";
        } else if (i2 == 2) {
            if (this.used_view_up.getVisibility() == 0) {
                handleSpecialPrice(this.cannotUseCouponList, true);
            } else {
                if (this.used_view_down.getVisibility() == 0) {
                    handleSpecialPrice(this.canUseCouponList, true);
                }
                str2 = "close";
            }
            str = "\u4f18\u60e0\u5238";
        } else {
            str = "";
            JShopSignNewActivity jShopSignNewActivity = this.mActivity;
            String str4 = str + CartConstant.KEY_YB_INFO_LINK + str3;
            JShopSignNewActivity jShopSignNewActivity2 = this.mActivity;
            String str5 = jShopSignNewActivity2.shopId;
            JDMtaUtils.sendCommonData(jShopSignNewActivity, "MyPrize_ExpiredAndUsedPrize", str4, "", jShopSignNewActivity2, str5, "", "", "ShopCheckIn_MyPrizeMain", str5);
        }
        str3 = str2;
        JShopSignNewActivity jShopSignNewActivity3 = this.mActivity;
        String str42 = str + CartConstant.KEY_YB_INFO_LINK + str3;
        JShopSignNewActivity jShopSignNewActivity22 = this.mActivity;
        String str52 = jShopSignNewActivity22.shopId;
        JDMtaUtils.sendCommonData(jShopSignNewActivity3, "MyPrize_ExpiredAndUsedPrize", str42, "", jShopSignNewActivity22, str52, "", "", "ShopCheckIn_MyPrizeMain", str52);
    }

    public void showEmptyView() {
        this.mNoDataView.setVisibility(0);
        this.mNoDataTV2.setText(R.string.jshop_no_prize_desire);
        this.mNoDataImage.setBackgroundResource(R.drawable.y_04);
        if (!this.hasCoupon && !this.hasSpecialPrice) {
            this.mNoDataTV1.setText(R.string.jshop_no_prize);
        } else {
            this.mNoDataTV1.setText(R.string.jshop_no_use_prize);
        }
    }

    public void showNetworkFailView(final int i2) {
        post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopMyAwardFragment.3
            {
                JShopMyAwardFragment.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                int i3 = i2;
                if (i3 == 1) {
                    JShopMyAwardFragment.this.hasSpecialPost = false;
                } else if (i3 == 0) {
                    JShopMyAwardFragment.this.hasCouponPost = false;
                }
                JShopMyAwardFragment.this.mFailView.setVisibility(0);
                JShopMyAwardFragment.this.mBtnRetry.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopMyAwardFragment.3.1
                    {
                        AnonymousClass3.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (JShopMyAwardFragment.this.curTab == 0) {
                            JShopMyAwardFragment.this.postSignPrize(1);
                        } else if (JShopMyAwardFragment.this.curTab == 2) {
                            JShopMyAwardFragment.this.postSignPrize(0);
                        }
                    }
                });
            }
        });
    }

    public void showNetworkFailView1() {
        post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopMyAwardFragment.4
            {
                JShopMyAwardFragment.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                JShopMyAwardFragment.this.mFailView.setVisibility(0);
                JShopMyAwardFragment.this.mBtnRetry.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopMyAwardFragment.4.1
                    {
                        AnonymousClass4.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        JShopMyAwardFragment.this.postSignPrizeType();
                    }
                });
            }
        });
    }

    public void showTab() {
        checkCanUseViewIsShow();
        this.tab_specail_price.setSelected(false);
        this.tab_real_goods.setSelected(false);
        this.tab_coupons.setSelected(false);
        int i2 = this.curTab;
        if (i2 == 0) {
            JShopSignNewActivity jShopSignNewActivity = this.mActivity;
            String str = jShopSignNewActivity.shopId;
            JDMtaUtils.sendCommonData(jShopSignNewActivity, "MyPrize_SpecialPriceTAB", "", "", jShopSignNewActivity, str, "", "", "ShopCheckIn_MyPrizeMain", str);
            this.tab_specail_price.setSelected(true);
        } else if (i2 == 1) {
            this.tab_real_goods.setSelected(true);
        } else if (i2 == 2) {
            JShopSignNewActivity jShopSignNewActivity2 = this.mActivity;
            String str2 = jShopSignNewActivity2.shopId;
            JDMtaUtils.sendCommonData(jShopSignNewActivity2, "MyPrize_CouponTAB", "", "", jShopSignNewActivity2, str2, "", "", "ShopCheckIn_MyPrizeMain", str2);
            this.tab_coupons.setSelected(true);
        }
    }

    private void upArrow(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "rotation", -180.0f, 0.0f);
        ofFloat.setDuration(DURATION);
        ofFloat.start();
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mActivity = (JShopSignNewActivity) activity;
        } catch (Exception unused) {
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_main_shop /* 2131690321 */:
                this.mActivity.finish();
                return;
            case R.id.tab_coupons /* 2131694511 */:
                this.curTab = 2;
                showTab();
                postSignPrize(0);
                return;
            case R.id.tab_real_goods /* 2131694518 */:
                this.curTab = 1;
                showTab();
                return;
            case R.id.tab_specail_price /* 2131694519 */:
                this.curTab = 0;
                showTab();
                postSignPrize(1);
                return;
            case R.id.used_view_down /* 2131694626 */:
                this.used_view_up.setVisibility(0);
                this.used_view_down.setVisibility(8);
                upArrow(this.mArrowUp);
                showCanUseOrNot();
                return;
            case R.id.used_view_up /* 2131694628 */:
                this.used_view_up.setVisibility(8);
                this.used_view_down.setVisibility(0);
                upArrow(this.mArrowDown);
                showCanUseOrNot();
                return;
            default:
                return;
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivity.setSubRootView(null);
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.jshop_award_fragment, (ViewGroup) null);
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        this.tab_group = view.findViewById(R.id.tab_group);
        this.tab_specail_price = view.findViewById(R.id.tab_specail_price);
        this.tab_real_goods = view.findViewById(R.id.tab_real_goods);
        this.tab_coupons = view.findViewById(R.id.tab_coupons);
        this.tab_specail_price.setOnClickListener(this);
        this.tab_real_goods.setOnClickListener(this);
        this.tab_coupons.setOnClickListener(this);
        this.used_view_up = view.findViewById(R.id.used_view_up);
        this.used_view_down = view.findViewById(R.id.used_view_down);
        this.used_view_up.setOnClickListener(this);
        this.used_view_down.setOnClickListener(this);
        this.used_view_up_tv = (TextView) view.findViewById(R.id.used_view_up_tv);
        this.used_view_down_tv = (TextView) view.findViewById(R.id.used_view_down_tv);
        this.special_listview = (ListView) view.findViewById(R.id.special_listview);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.empty_view);
        this.mNoDataView = linearLayout;
        linearLayout.setVisibility(8);
        this.mNoDataImage = (ImageView) this.mNoDataView.findViewById(R.id.bz);
        this.mNoDataTV1 = (TextView) this.mNoDataView.findViewById(R.id.c0);
        this.mNoDataTV2 = (TextView) this.mNoDataView.findViewById(R.id.c1);
        TextView textView = (TextView) this.mNoDataView.findViewById(R.id.c2);
        this.mNoDataTV3 = textView;
        textView.setVisibility(8);
        Button button = (Button) this.mNoDataView.findViewById(R.id.bw);
        this.mNoDataBtn = button;
        button.setVisibility(8);
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.fail_view);
        this.mFailView = linearLayout2;
        Button button2 = (Button) linearLayout2.findViewById(R.id.btn_main_shop);
        this.mBtnMainShop = button2;
        button2.setOnClickListener(this);
        this.mBtnRetry = (Button) this.mFailView.findViewById(R.id.btn_retry);
        this.mArrowDown = (ImageView) view.findViewById(R.id.award_arrow_down);
        this.mArrowUp = (ImageView) view.findViewById(R.id.award_arrow_up);
        String string = getArguments().getString("venderId");
        if (TextUtils.isEmpty(string)) {
            showEmptyView();
            return;
        }
        convertValue(string);
        postSignPrizeType();
    }

    public void postRequestMyAwardAgain() {
        if (this.tab_group != null) {
            Log.d(TAG, "  +++  postRequestMyAwardAgain  +++  ");
            if (this.vendorId != -1) {
                postSignPrizeType();
            } else {
                showEmptyView();
            }
        }
    }

    public void showNormalData() {
        View view = this.used_view_up;
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        this.used_view_up.performClick();
    }
}
