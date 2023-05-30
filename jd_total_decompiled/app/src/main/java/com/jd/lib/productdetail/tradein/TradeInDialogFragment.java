package com.jd.lib.productdetail.tradein;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.ProductDetailEntity;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.tradein.bank.TradeInSelectBankFragment;
import com.jd.lib.productdetail.tradein.estimate.TradeInEstimateFragment;
import com.jd.lib.productdetail.tradein.inform.TradeInInformData;
import com.jd.lib.productdetail.tradein.inform.TradeInInformFragment;
import com.jd.lib.productdetail.tradein.result.TradeInResultFragment;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceData;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceFragment;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceSearchView;
import com.jd.lib.productdetail.tradein.ways.TradeInRenewsWaysFragment;
import com.jd.lib.productdetail.tradein.ways.TradeInTradeWaysFragment;
import com.jd.lib.productdetail.tradein.widget.TradeInInquiryStepProgressView;
import com.jd.lib.productdetail.tradein.widget.TradeinErrorView;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.jdmiaosha.utils.cache.JDNetCacheManager;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.unification.dialog.UnBottomDialog;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes16.dex */
public class TradeInDialogFragment extends DialogFragment {
    public static final String EXTRA_PARAMS_KEY_ADDRESS = "extra.params.key.address";
    public static final String EXTRA_PARAMS_KEY_BIZ = "biz";
    public static final String EXTRA_PARAMS_KEY_BIZCODE = "bizCode";
    public static final String EXTRA_PARAMS_KEY_BUTTONTYPE = "extra.params.key.button.type";
    public static final String EXTRA_PARAMS_KEY_ESID = "extra.params.key.esid";
    public static final String EXTRA_PARAMS_KEY_EXTENDS_PARAMS = "extra.params.key.extends.params";
    public static final String EXTRA_PARAMS_KEY_HEIGHT_PERCENT = "extra.params.key.height.percent";
    public static final String EXTRA_PARAMS_KEY_INIT_STEP = "extra.params.key.button.init.step";
    public static final String EXTRA_PARAMS_KEY_IS_MIAOSHA = "extra.params.key.is.miaosha";
    public static final String EXTRA_PARAMS_KEY_IS_YOUSHOU = "extra.params.key.is.yushou";
    public static final String EXTRA_PARAMS_KEY_IS_YUYUE = "extra.params.key.is.yuyue";
    public static final String EXTRA_PARAMS_KEY_LAYER_FROM = "extra.params.key.layer.from";
    public static final String EXTRA_PARAMS_KEY_MAXSUBSIDY = "extra.params.key.maxSubsidy";
    public static final String EXTRA_PARAMS_KEY_PRICEMODE = "extra.params.key.pricemode";
    public static final String EXTRA_PARAMS_KEY_QUALIFICATIONID = "qualificationId";
    public static final String EXTRA_PARAMS_KEY_SETTLETYPE = "settleType";
    public static final String EXTRA_PARAMS_KEY_SKUID = "extra.params.key.skuid";
    public static final String EXTRA_PARAMS_KEY_SOURCE = "extra.params.key.source";
    public static final String EXTRA_PARAMS_KEY_TRADE_EXTENSION = "extension";
    public static final String EXTRA_PARAMS_KEY_TRADE_TYPE = "tradeType";
    public static final String EXTRA_PARAMS_RESULT_HAS_BTN_STYLE = "extra.params.key.has.btn.style";
    public static final String REQUEST_CODE = "pd.tradein.request.code";
    public static final String REQUEST_FROM = "pd.tradein.request.from";
    public static final String RESULT_KEY_EXTENDS = "result.key.tradetype";
    public static final String RESULT_KEY_QFID = "result.key.qfid";
    private static final String TAG = "TradeInDialogFragment";
    public BaseActivity mBaseActivity;
    private View mBtnBack;
    private View mBtnClose;
    private TradeInStep mCurrentStep;
    private TradeinErrorView mErrorView;
    private FragmentManager.OnBackStackChangedListener mFragmentChangedListener = new a();
    private TradeInStep mInitStep;
    private UnBottomDialog mLayerDialog;
    private View mLoadingView;
    public DialogInterface.OnDismissListener mOnDismissListener;
    public ProductDetailEntity mProduct;
    public String mSource;
    private TextView mTitle;
    public TradeInViewModel mViewModel;

    /* loaded from: classes16.dex */
    public class a implements FragmentManager.OnBackStackChangedListener {
        public a() {
            TradeInDialogFragment.this = r1;
        }

        @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
        public void onBackStackChanged() {
            Fragment fragment;
            String string;
            int backStackEntryCount = TradeInDialogFragment.this.getChildFragmentManager().getBackStackEntryCount();
            String str = "onBackStackChanged tBackCount is " + backStackEntryCount;
            if (backStackEntryCount > 1) {
                TradeInDialogFragment.this.mBtnBack.setVisibility(0);
            } else {
                TradeInDialogFragment.this.mBtnBack.setVisibility(8);
            }
            List<Fragment> fragments = TradeInDialogFragment.this.getChildFragmentManager().getFragments();
            if (fragments == null || fragments.size() <= 0 || (fragment = fragments.get(fragments.size() - 1)) == null) {
                return;
            }
            if (fragment instanceof TradeInInformFragment) {
                string = TradeInDialogFragment.this.getString(R.string.tradein_step_inform_title);
            } else if (fragment instanceof TradeInSelectDeviceFragment) {
                string = TradeInDialogFragment.this.getString(R.string.tradein_step_select_old_device_title);
            } else if (fragment instanceof TradeInEstimateFragment) {
                string = TradeInDialogFragment.this.getString(R.string.tradein_step_estimate_title);
            } else {
                string = fragment instanceof TradeInResultFragment ? TradeInDialogFragment.this.getString(R.string.tradein_step_trade_title) : "";
            }
            if (!TextUtils.isEmpty(string)) {
                TradeInDialogFragment.this.setTitle(string);
            }
            String str2 = "onBtnBack Click top fragment is " + fragment.getClass().getSimpleName() + " title = " + string;
        }
    }

    /* loaded from: classes16.dex */
    public class b implements View.OnClickListener {
        public b() {
            TradeInDialogFragment.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            List<Fragment> fragments = TradeInDialogFragment.this.getChildFragmentManager().getFragments();
            if (fragments != null) {
                boolean z = true;
                Fragment fragment = fragments.get(fragments.size() - 1);
                if (fragment instanceof TradeInSelectDeviceFragment) {
                    TradeInSelectDeviceSearchView tradeInSelectDeviceSearchView = ((TradeInSelectDeviceFragment) fragment).f5507k;
                    if (tradeInSelectDeviceSearchView.f5528g) {
                        tradeInSelectDeviceSearchView.a();
                    } else {
                        z = false;
                    }
                    if (z) {
                        return;
                    }
                } else if ((fragment instanceof TradeInTradeWaysFragment) && ((TradeInTradeWaysFragment) fragment).v()) {
                    return;
                }
            }
            TradeInDialogFragment.this.getChildFragmentManager().popBackStack();
        }
    }

    /* loaded from: classes16.dex */
    public class c implements View.OnClickListener {
        public c() {
            TradeInDialogFragment.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TradeInDialogFragment.this.initTradeInStep();
        }
    }

    /* loaded from: classes16.dex */
    public class d implements View.OnClickListener {
        public d() {
            TradeInDialogFragment.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TradeInDialogFragment.this.dismiss();
        }
    }

    /* loaded from: classes16.dex */
    public class e implements Observer<PdBaseProtocolLiveData.Result<TradeInInformData>> {
        public e() {
            TradeInDialogFragment.this = r1;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(PdBaseProtocolLiveData.Result<TradeInInformData> result) {
            boolean z;
            TradeInInformData.Data data;
            PdBaseProtocolLiveData.Result<TradeInInformData> result2 = result;
            String str = "getInformData result = " + result2.mStatus;
            PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
            if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FETCHING) {
                return;
            }
            if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.SUCCESS) {
                TradeInInformData tradeInInformData = result2.mData;
                if ((tradeInInformData instanceof TradeInInformData) && (data = tradeInInformData.data) != null) {
                    TradeInStep destStep = data.getDestStep();
                    TradeInStep tradeInStep = TradeInStep.INFORM;
                    if (destStep == tradeInStep) {
                        TradeInInformData.Data.TradeinInformInfo tradeinInformInfo = result2.mData.data.noHaveLocalMachineInfo;
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("extra.params.key.inform.data", tradeinInformInfo);
                        TradeInDialogFragment.this.moveToStep(tradeInStep, bundle);
                    } else {
                        TradeInStep tradeInStep2 = TradeInStep.ESTIMATE;
                        if (destStep == tradeInStep2) {
                            Bundle bundle2 = new Bundle();
                            bundle2.putSerializable("extra.key.old.device.estimate", result2.mData.data.localMachineInfo);
                            bundle2.putSerializable("extra.key.old.device.tag", result2.mData.data.localMachineInfo.tagInfo);
                            bundle2.putSerializable("extra.key.old.device", result2.mData.data.localMachineInfo.oldProductInfo);
                            TradeInDialogFragment.this.moveToStep(tradeInStep2, bundle2);
                        } else {
                            TradeInStep tradeInStep3 = TradeInStep.TRADEIN;
                            if (destStep == tradeInStep3) {
                                TradeInDialogFragment.this.moveToStep(tradeInStep3, new Bundle());
                            }
                        }
                    }
                    z = true;
                    TradeInDialogFragment.this.showLoading(false, !z);
                }
                z = false;
                TradeInDialogFragment.this.showLoading(false, !z);
            } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FAIL) {
                TradeInDialogFragment.this.showLoading(false, true);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class f extends UnBottomDialog {
        public f(TradeInDialogFragment tradeInDialogFragment, Context context) {
            super(context);
        }

        @Override // android.app.Dialog
        public void hide() {
        }

        @Override // com.jingdong.common.unification.dialog.UnBottomDialog, android.app.Dialog
        public void show() {
            if (isShowing()) {
                return;
            }
            super.show();
        }
    }

    @Keep
    public TradeInDialogFragment(BaseActivity baseActivity) {
        this.mBaseActivity = baseActivity;
    }

    /* renamed from: a */
    public boolean b(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
        boolean z;
        if (i2 == 4 && keyEvent.getAction() == 0) {
            List<Fragment> fragments = getChildFragmentManager().getFragments();
            if (fragments != null && fragments.size() > 0) {
                Fragment fragment = fragments.get(fragments.size() - 1);
                if (fragment instanceof TradeInSelectDeviceFragment) {
                    TradeInSelectDeviceSearchView tradeInSelectDeviceSearchView = ((TradeInSelectDeviceFragment) fragment).f5507k;
                    if (tradeInSelectDeviceSearchView.f5528g) {
                        tradeInSelectDeviceSearchView.a();
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        return true;
                    }
                } else if ((fragment instanceof TradeInTradeWaysFragment) && ((TradeInTradeWaysFragment) fragment).v()) {
                    return true;
                }
            }
            if (getChildFragmentManager().getBackStackEntryCount() > 1) {
                getChildFragmentManager().popBackStack();
                return true;
            }
        }
        return false;
    }

    private void initJdBottomDialog() {
        if (this.mLayerDialog == null) {
            this.mLayerDialog = new f(this, getContext());
            float f2 = getArguments().getFloat("extra.params.key.height.percent", -1.0f);
            if (f2 != -1.0f) {
                this.mLayerDialog.addContentWithHeight(new View(getContext()), null, f2, false);
            } else {
                this.mLayerDialog.addContentWithHeight(new View(getContext()), (String) null, false);
            }
        }
        this.mLayerDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.jd.lib.productdetail.tradein.c
            {
                TradeInDialogFragment.this = this;
            }

            @Override // android.content.DialogInterface.OnKeyListener
            public final boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
                return TradeInDialogFragment.this.b(dialogInterface, i2, keyEvent);
            }
        });
    }

    private void setContentViewVisibility(int i2) {
        TextView textView = this.mTitle;
        if (textView != null) {
            textView.setVisibility(i2);
        }
        View view = this.mBtnBack;
        if (view != null) {
            view.setVisibility(i2);
        }
    }

    public void showLoading(boolean z, boolean z2) {
        View view;
        if (this.mErrorView == null || (view = this.mLoadingView) == null) {
            return;
        }
        if (z) {
            setContentViewVisibility(8);
            this.mErrorView.setVisibility(8);
            this.mLoadingView.setVisibility(0);
            return;
        }
        view.setVisibility(8);
        if (z2) {
            setContentViewVisibility(8);
            this.mErrorView.setVisibility(0);
            if (NetUtils.isNetworkAvailable()) {
                this.mErrorView.a(TradeinErrorView.a.UNKNOWN);
                return;
            } else {
                this.mErrorView.a(TradeinErrorView.a.NONET);
                return;
            }
        }
        this.mErrorView.setVisibility(8);
        setContentViewVisibility(0);
    }

    public void initTradeInStep() {
        if (isAdded()) {
            TradeInStep tradeInStep = this.mInitStep;
            if (tradeInStep == TradeInStep.INFORM) {
                showLoading(true, false);
                TradeInViewModel tradeInViewModel = this.mViewModel;
                tradeInViewModel.getClass();
                HashMap hashMap = new HashMap();
                hashMap.put("bizCode", Integer.valueOf(tradeInViewModel.f5255g));
                hashMap.put("skuId", tradeInViewModel.f5253e);
                hashMap.put("tradeType", Integer.valueOf(tradeInViewModel.f5256h));
                hashMap.put(JDNetCacheManager.BRAND_BIZKEY, BaseInfo.getDeviceBrand());
                hashMap.put(CustomThemeConstance.NAVI_MODEL, BaseInfo.getDeviceModel());
                hashMap.put("noticeDataType", "4");
                hashMap.put("buttonType", tradeInViewModel.f5261m.toStringValue());
                hashMap.put("settleType", Integer.valueOf(tradeInViewModel.f5258j));
                hashMap.put("extend", tradeInViewModel.t);
                if (!TextUtils.isEmpty(tradeInViewModel.f5262n)) {
                    hashMap.put("esId", tradeInViewModel.f5262n);
                }
                if (!TextUtils.isEmpty(tradeInViewModel.o)) {
                    hashMap.put("qualificationId", tradeInViewModel.o);
                }
                hashMap.put("queryDetailedType", Integer.valueOf(tradeInViewModel.p));
                JDJSONObject jDJSONObject = tradeInViewModel.s;
                if (jDJSONObject != null) {
                    hashMap.put("extension", jDJSONObject);
                    String str = "getTradeInEnterData:" + tradeInViewModel.s;
                }
                TradeInAddressInfo c2 = tradeInViewModel.c();
                if (c2 != null) {
                    hashMap.put("addressInfo", c2);
                }
                com.jd.lib.productdetail.tradein.k.c cVar = new com.jd.lib.productdetail.tradein.k.c(hashMap);
                cVar.request(tradeInViewModel.b);
                cVar.mResult.observe(tradeInViewModel.d, new com.jd.lib.productdetail.tradein.b(tradeInViewModel, cVar));
                cVar.mResult.observe(getViewLifecycleOwner(), new e());
                return;
            }
            moveToStep(tradeInStep);
            this.mInitStep = null;
        }
    }

    public void moveToEstimatePage(Bundle bundle) {
        if (getChildFragmentManager() != null) {
            getChildFragmentManager().popBackStack(TradeInStep.ESTIMATE.name(), 0);
            List<Fragment> fragments = getChildFragmentManager().getFragments();
            String str = "moveToEstimatePage tFragments count = " + fragments.size();
            if (fragments.size() > 0) {
                for (Fragment fragment : fragments) {
                    if (fragment instanceof TradeInEstimateFragment) {
                        TradeInEstimateFragment tradeInEstimateFragment = (TradeInEstimateFragment) fragment;
                        tradeInEstimateFragment.getClass();
                        if (bundle != null) {
                            tradeInEstimateFragment.f5297j = (TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries) bundle.getSerializable("extra.key.old.device");
                            tradeInEstimateFragment.f5299l = (TradeInSelectDeviceData.Data.TagsInfo.TagItem) bundle.getSerializable("extra.key.old.device.tag");
                            tradeInEstimateFragment.f5298k = (TradeInSelectDeviceData.Data.CategoriesInfo.CateItem) bundle.getSerializable("extra.key.old.device.cate");
                        }
                        TradeInInquiryStepProgressView tradeInInquiryStepProgressView = tradeInEstimateFragment.u;
                        if (tradeInInquiryStepProgressView != null) {
                            tradeInInquiryStepProgressView.setVisibility(4);
                            tradeInEstimateFragment.p.setVisibility(0);
                        }
                        tradeInEstimateFragment.a();
                    }
                }
            }
        }
    }

    public void moveToStep(TradeInStep tradeInStep) {
        moveToStep(tradeInStep, null);
    }

    public void moveToTradeInPage(Bundle bundle) {
        if (getChildFragmentManager() != null) {
            this.mViewModel.u.setValue(null);
            getChildFragmentManager().popBackStack(TradeInStep.TRADEIN.name(), 0);
            List<Fragment> fragments = getChildFragmentManager().getFragments();
            String str = "moveToTradeInPage tFragments count = " + fragments.size();
            if (fragments.size() > 0) {
                for (Fragment fragment : fragments) {
                    if (fragment instanceof TradeInResultFragment) {
                        ((TradeInResultFragment) fragment).x();
                    }
                }
            }
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        TradeInViewModel tradeInViewModel = (TradeInViewModel) new ViewModelProvider(this).get(TradeInViewModel.class);
        this.mViewModel = tradeInViewModel;
        tradeInViewModel.b = this.mBaseActivity;
        tradeInViewModel.d = this;
        Bundle arguments = getArguments();
        tradeInViewModel.f5252c = this.mProduct;
        if (arguments != null) {
            tradeInViewModel.r = (TradeInAddressInfo) arguments.getSerializable("extra.params.key.address");
            tradeInViewModel.t = arguments.getString("extra.params.key.extends.params");
            tradeInViewModel.f5262n = arguments.getString("extra.params.key.esid");
            String string = arguments.getString("qualificationId");
            tradeInViewModel.o = string;
            tradeInViewModel.p = TextUtils.isEmpty(string) ? 2 : 1;
            arguments.getString("extra.params.key.source");
            try {
                tradeInViewModel.s = (JDJSONObject) JDJSON.parse(arguments.getString("extension"));
            } catch (Exception e2) {
                ExceptionReporter.reportExceptionToBugly(e2);
            }
            if (arguments.getSerializable("extra.params.key.layer.from") instanceof TradeInOpenLayerFrom) {
                tradeInViewModel.f5254f = (TradeInOpenLayerFrom) arguments.getSerializable("extra.params.key.layer.from");
            }
            if (tradeInViewModel.f5254f == null) {
                tradeInViewModel.f5254f = TradeInOpenLayerFrom.FORM_OTHER;
            }
            tradeInViewModel.f5253e = arguments.getString("extra.params.key.skuid");
            tradeInViewModel.f5255g = arguments.getInt("bizCode");
            tradeInViewModel.f5256h = arguments.getInt("tradeType");
            tradeInViewModel.f5258j = arguments.getInt("settleType");
            tradeInViewModel.f5259k = (TradeInPriceMode) arguments.getSerializable("extra.params.key.pricemode");
            TradeInButtonType tradeInButtonType = (TradeInButtonType) arguments.getSerializable("extra.params.key.button.type");
            tradeInViewModel.f5261m = tradeInButtonType;
            if (tradeInButtonType == null) {
                tradeInViewModel.f5261m = TradeInButtonType.DEFAULT;
            }
            tradeInViewModel.f5260l = arguments.getBoolean("extra.params.key.has.btn.style", false);
            String str = "mEsId = " + tradeInViewModel.f5262n;
            String str2 = "mQualificationId = " + tradeInViewModel.o;
            String str3 = "mOpenFrom = " + tradeInViewModel.f5254f;
            String str4 = "mSkuId = " + tradeInViewModel.f5253e;
            String str5 = "mBizCode = " + tradeInViewModel.f5255g;
            String str6 = "mTradeType = " + tradeInViewModel.f5256h;
            String str7 = "mSettleType = " + tradeInViewModel.f5258j;
            String str8 = "mPriceMode = " + tradeInViewModel.f5259k;
            String str9 = "mButtonType = " + tradeInViewModel.f5261m;
            String str10 = "mResultPageNeedBtnStyle = " + tradeInViewModel.f5260l;
            String str11 = "mAddressInfo = " + tradeInViewModel.r;
            String str12 = "mExtendsParams = " + tradeInViewModel.t;
        }
        this.mInitStep = (TradeInStep) getArguments().getSerializable("extra.params.key.button.init.step");
        this.mInitStep = TradeInStep.INFORM;
        this.mViewModel.h("Productdetail_yjhxToastAll", null);
    }

    @Override // androidx.fragment.app.DialogFragment
    @NonNull
    public Dialog onCreateDialog(@Nullable Bundle bundle) {
        initJdBottomDialog();
        return this.mLayerDialog;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.tradein_main, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        getChildFragmentManager().removeOnBackStackChangedListener(this.mFragmentChangedListener);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(@NonNull DialogInterface dialogInterface) {
        ProductDetailEntity productDetailEntity;
        DialogInterface.OnDismissListener onDismissListener;
        super.onDismiss(dialogInterface);
        TradeInViewModel tradeInViewModel = this.mViewModel;
        if (tradeInViewModel == null || (productDetailEntity = tradeInViewModel.f5252c) == null || productDetailEntity.mIsInTradeInStep || (onDismissListener = this.mOnDismissListener) == null) {
            return;
        }
        onDismissListener.onDismiss(dialogInterface);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mLoadingView = view.findViewById(R.id.tradein_loading);
        TradeinErrorView tradeinErrorView = (TradeinErrorView) view.findViewById(R.id.tradein_error_view);
        this.mErrorView = tradeinErrorView;
        tradeinErrorView.setVisibility(8);
        this.mLoadingView.setVisibility(8);
        this.mTitle = (TextView) view.findViewById(R.id.tradein_main_title_text);
        View findViewById = view.findViewById(R.id.tradein_main_title_btn_back);
        this.mBtnBack = findViewById;
        findViewById.setVisibility(8);
        this.mBtnBack.setOnClickListener(new b());
        TextView textView = this.mErrorView.f5654i;
        if (textView != null) {
            textView.setOnClickListener(new c());
        }
        View findViewById2 = view.findViewById(R.id.tradein_main_title_btn_close);
        this.mBtnClose = findViewById2;
        findViewById2.setOnClickListener(new d());
        getChildFragmentManager().addOnBackStackChangedListener(this.mFragmentChangedListener);
        initTradeInStep();
    }

    public void setTitle(String str) {
        TextView textView = this.mTitle;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void moveToStep(TradeInStep tradeInStep, Bundle bundle) {
        if (tradeInStep != null) {
            this.mCurrentStep = tradeInStep;
            int i2 = tradeInStep.mTitleResId;
            if (i2 != -1) {
                this.mTitle.setText(i2);
            } else {
                this.mTitle.setText("");
            }
            if (bundle == null) {
                bundle = new Bundle();
            }
            if (tradeInStep == TradeInStep.INFORM) {
                TradeInInformFragment tradeInInformFragment = new TradeInInformFragment(this.mBaseActivity, this);
                tradeInInformFragment.setArguments(bundle);
                getChildFragmentManager().beginTransaction().addToBackStack(null).add(R.id.tradein_main_fragment_container, tradeInInformFragment).commit();
                TradeInViewModel tradeInViewModel = this.mViewModel;
                if (tradeInViewModel != null) {
                    tradeInViewModel.h("Productdetail_yjhxInformExpo", null);
                }
            } else if (tradeInStep == TradeInStep.SELECT_OLD_DEVICE) {
                TradeInSelectDeviceFragment tradeInSelectDeviceFragment = new TradeInSelectDeviceFragment(this.mBaseActivity, this);
                tradeInSelectDeviceFragment.setArguments(bundle);
                getChildFragmentManager().beginTransaction().addToBackStack(null).add(R.id.tradein_main_fragment_container, tradeInSelectDeviceFragment).commit();
                TradeInViewModel tradeInViewModel2 = this.mViewModel;
                if (tradeInViewModel2 != null) {
                    tradeInViewModel2.h("Productdetail_yjhxChooseToastExpo", null);
                }
            } else {
                TradeInStep tradeInStep2 = TradeInStep.TRADEIN;
                if (tradeInStep == tradeInStep2) {
                    TradeInResultFragment tradeInResultFragment = new TradeInResultFragment(this.mBaseActivity, this);
                    tradeInResultFragment.setArguments(bundle);
                    FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
                    if (tradeInStep != this.mInitStep) {
                        beginTransaction.addToBackStack(tradeInStep2.name());
                    }
                    beginTransaction.add(R.id.tradein_main_fragment_container, tradeInResultFragment);
                    beginTransaction.commit();
                    TradeInViewModel tradeInViewModel3 = this.mViewModel;
                    if (tradeInViewModel3 != null) {
                        tradeInViewModel3.h("Productdetail_yjhxToastExpo", null);
                        return;
                    }
                    return;
                }
                TradeInStep tradeInStep3 = TradeInStep.TRADEIN_MODE;
                if (tradeInStep == tradeInStep3) {
                    TradeInRenewsWaysFragment tradeInRenewsWaysFragment = new TradeInRenewsWaysFragment(this.mBaseActivity, this);
                    tradeInRenewsWaysFragment.setArguments(bundle);
                    FragmentTransaction beginTransaction2 = getChildFragmentManager().beginTransaction();
                    if (tradeInStep != this.mInitStep) {
                        beginTransaction2.addToBackStack(tradeInStep3.name());
                    }
                    beginTransaction2.add(R.id.tradein_main_fragment_container, tradeInRenewsWaysFragment);
                    beginTransaction2.commit();
                    return;
                }
                TradeInStep tradeInStep4 = TradeInStep.WAY;
                if (tradeInStep == tradeInStep4) {
                    TradeInTradeWaysFragment tradeInTradeWaysFragment = new TradeInTradeWaysFragment(this.mBaseActivity, this);
                    tradeInTradeWaysFragment.setArguments(bundle);
                    FragmentTransaction beginTransaction3 = getChildFragmentManager().beginTransaction();
                    if (tradeInStep != this.mInitStep) {
                        beginTransaction3.addToBackStack(tradeInStep4.name());
                    }
                    beginTransaction3.add(R.id.tradein_main_fragment_container, tradeInTradeWaysFragment);
                    beginTransaction3.commit();
                    return;
                }
                TradeInStep tradeInStep5 = TradeInStep.BANK;
                if (tradeInStep == tradeInStep5) {
                    TradeInSelectBankFragment tradeInSelectBankFragment = new TradeInSelectBankFragment(this.mBaseActivity, this);
                    tradeInSelectBankFragment.setArguments(bundle);
                    FragmentTransaction beginTransaction4 = getChildFragmentManager().beginTransaction();
                    if (tradeInStep != this.mInitStep) {
                        beginTransaction4.addToBackStack(tradeInStep5.name());
                    }
                    beginTransaction4.add(R.id.tradein_main_fragment_container, tradeInSelectBankFragment);
                    beginTransaction4.commit();
                    return;
                }
                TradeInStep tradeInStep6 = TradeInStep.ESTIMATE;
                if (tradeInStep == tradeInStep6) {
                    TradeInEstimateFragment tradeInEstimateFragment = new TradeInEstimateFragment(this.mBaseActivity, this);
                    tradeInEstimateFragment.setArguments(bundle);
                    FragmentTransaction beginTransaction5 = getChildFragmentManager().beginTransaction();
                    if (tradeInStep != this.mInitStep) {
                        beginTransaction5.addToBackStack(tradeInStep6.name());
                    }
                    beginTransaction5.add(R.id.tradein_main_fragment_container, tradeInEstimateFragment);
                    beginTransaction5.commit();
                    TradeInViewModel tradeInViewModel4 = this.mViewModel;
                    if (tradeInViewModel4 != null) {
                        tradeInViewModel4.h("Productdetail_yjhxAskPriceExpo", null);
                    }
                }
            }
        }
    }
}
