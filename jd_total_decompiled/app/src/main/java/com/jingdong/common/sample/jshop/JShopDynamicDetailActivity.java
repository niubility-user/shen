package com.jingdong.common.sample.jshop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.deeplinkhelper.DeepLinkJShopHomeHelper;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.sample.jshop.Entity.DynamicShopActivity;
import com.jingdong.common.sample.jshop.Entity.JShopShareInfo;
import com.jingdong.common.sample.jshop.fragment.JShopDynaWebFragment;
import com.jingdong.common.sample.jshop.fragment.JShopDynamicNormalDetailFragment;
import com.jingdong.common.sample.jshop.ui.JshopCommentInputView;
import com.jingdong.common.sample.jshop.ui.JshopDyCommentView;
import com.jingdong.common.sample.jshop.utils.JShopFragmentUtils;
import com.jingdong.common.sample.jshop.utils.JShopUtil;
import com.jingdong.common.sample.jshop.utils.JshopShowErrorViewUtils;
import com.jingdong.common.sample.jshop.utils.JshopTextViewUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JShopDynamicDetailActivity extends MyActivity {
    private static final int MSG_UPDATE = 1001;
    private static final String TAG = "JShopDynamicDetailActivity";
    public Bundle mBundle;
    public JshopDyCommentView mCommentView;
    public View mContainer;
    private Fragment mCurFragment;
    public FragmentManager mFragmentManager;
    public JshopCommentInputView mInputView;
    public View mNoDataView;
    public View mNormalView;
    public ImageView mRightIcon;
    public String mShopName;
    public TextView mTitle;
    private JshopShowErrorViewUtils utils = null;
    private boolean isShowComment = false;
    private String mVenderId = "";
    private int mCommentRequestStatus = 0;
    private int mDetailRequestStatus = 0;
    public DynamicShopActivity mShopActivity = null;
    private String mErrorMsg = "";
    private boolean isSend = false;
    private boolean isFromShare = false;
    private boolean isNeedUpdate = false;
    public boolean isShowKeyboard = false;
    private Handler mHandler = new Handler() { // from class: com.jingdong.common.sample.jshop.JShopDynamicDetailActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            JshopDyCommentView jshopDyCommentView;
            if (message.what == 1001 && (jshopDyCommentView = JShopDynamicDetailActivity.this.mCommentView) != null && jshopDyCommentView.mState == 1) {
                jshopDyCommentView.update();
            }
        }
    };
    View.OnClickListener mRetryOnclicklistener = new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.JShopDynamicDetailActivity.7
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            view.setEnabled(false);
            if (JShopDynamicDetailActivity.this.mDetailRequestStatus == -1 || JShopDynamicDetailActivity.this.mDetailRequestStatus == 0) {
                JShopDynamicDetailActivity.this.mDetailRequestStatus = 0;
                JShopDynamicDetailActivity.this.mErrorMsg = "";
                if (JShopDynamicDetailActivity.this.mCurFragment != null && (JShopDynamicDetailActivity.this.mCurFragment instanceof JShopDynamicNormalDetailFragment)) {
                    ((JShopDynamicNormalDetailFragment) JShopDynamicDetailActivity.this.mCurFragment).retryGetData();
                }
            }
            if (JShopDynamicDetailActivity.this.mCommentRequestStatus == -1 || JShopDynamicDetailActivity.this.mCommentRequestStatus == 0) {
                JShopDynamicDetailActivity.this.mCommentRequestStatus = 0;
                JshopDyCommentView jshopDyCommentView = JShopDynamicDetailActivity.this.mCommentView;
                if (jshopDyCommentView != null) {
                    jshopDyCommentView.getCommentList();
                }
            }
        }
    };
    View.OnClickListener mEmptyOnClickListener = new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.JShopDynamicDetailActivity.8
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    };

    private void getActivityDetail() {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(Configuration.getJshopHost());
        httpSetting.setEffect(1);
        httpSetting.setFunctionId("getActivityDetail");
        httpSetting.setUseFastJsonParser(true);
        httpSetting.putJsonParam("page", "1");
        httpSetting.putJsonParam("pageSize", "2");
        if (!TextUtils.isEmpty(this.mBundle.getString("shopId"))) {
            httpSetting.putJsonParam("shopId", this.mBundle.getString("shopId"));
        }
        if (!TextUtils.isEmpty(this.mBundle.getString("venderId"))) {
            httpSetting.putJsonParam("venderId", this.mBundle.getString("venderId"));
        }
        if (!TextUtils.isEmpty(this.mBundle.getString(JshopConst.JSHOP_ACTIVITY_TYPE))) {
            httpSetting.putJsonParam(JshopConst.JSHOP_ACTIVITY_TYPE, this.mBundle.getString(JshopConst.JSHOP_ACTIVITY_TYPE));
        }
        if (!TextUtils.isEmpty(this.mBundle.getString("activityId"))) {
            httpSetting.putJsonParam("activityId", this.mBundle.getString("activityId"));
        }
        if (!TextUtils.isEmpty(this.mBundle.getString("params"))) {
            httpSetting.putJsonParam("params", this.mBundle.getString("params"));
        }
        httpSetting.setNotifyUser(false);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.sample.jshop.JShopDynamicDetailActivity.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(final HttpResponse httpResponse) {
                JShopDynamicDetailActivity.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.JShopDynamicDetailActivity.2.2
                    @Override // java.lang.Runnable
                    public void run() {
                        String string = JShopDynamicDetailActivity.this.getResources().getString(R.string.jshop_dynamic_detail_error_msg);
                        if (httpResponse == null) {
                            JShopDynamicDetailActivity.this.setEmptyState(string);
                            return;
                        }
                        Log.d(JShopDynamicDetailActivity.TAG, " onEnd , response ===> : " + httpResponse.getJSONObject());
                        JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                        if (fastJsonObject == null || fastJsonObject.optJSONObject("activity") == null) {
                            if (fastJsonObject != null) {
                                string = fastJsonObject.optString("errorMsg", JShopDynamicDetailActivity.this.getResources().getString(R.string.jshop_dynamic_detail_error_msg));
                            }
                            JShopDynamicDetailActivity.this.setEmptyState(string);
                            return;
                        }
                        JShopDynamicDetailActivity.this.setDetailRequestSuccess(1);
                        JDJSONObject optJSONObject = fastJsonObject.optJSONObject("activity");
                        try {
                            optJSONObject.put("commentSwitch", (Object) fastJsonObject.optString("commentSwitch"));
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        DynamicShopActivity dynamicShopActivity = new DynamicShopActivity(optJSONObject);
                        JShopDynamicDetailActivity.this.initFrament(dynamicShopActivity);
                        JShopDynamicDetailActivity.this.initTitle(dynamicShopActivity.shopName);
                        JShopDynamicDetailActivity.this.setActivityType(dynamicShopActivity);
                        if (dynamicShopActivity.shopId <= 0 && dynamicShopActivity.venderId <= 0) {
                            JShopDynamicDetailActivity.this.setRightIconVisibility(8);
                        }
                        JDJSONObject optJSONObject2 = fastJsonObject.optJSONObject("shareInfo");
                        if (optJSONObject2 != null) {
                            dynamicShopActivity.shareInfo = new JShopShareInfo(optJSONObject2);
                        }
                        JShopDynamicDetailActivity.this.setDetailData(dynamicShopActivity);
                    }
                });
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                Log.d(JShopDynamicDetailActivity.TAG, "onError");
                JShopDynamicDetailActivity.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.JShopDynamicDetailActivity.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        JShopDynamicDetailActivity.this.setErrorState();
                    }
                });
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        getHttpGroupaAsynPool().add(httpSetting);
    }

    private void sendShareMTA() {
        if (this.isSend) {
            return;
        }
        this.isSend = true;
        if (this.isFromShare) {
            JDMtaUtils.sendCommonData(this, "Shop_ShareReturn", "Shop_ShopDynamicStateDetail_Share", "", this, "", "", "", "", "");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEmptyState(String str) {
        initTitle("");
        setmDetailRequestStatus(-1, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setErrorState() {
        initTitle("");
        setDetailRequestSuccess(-1);
    }

    private void showView() {
        post(new Runnable() { // from class: com.jingdong.common.sample.jshop.JShopDynamicDetailActivity.6
            @Override // java.lang.Runnable
            public void run() {
                if (JShopDynamicDetailActivity.this.mCommentRequestStatus == -1 || JShopDynamicDetailActivity.this.mDetailRequestStatus == -1) {
                    if (!TextUtils.isEmpty(JShopDynamicDetailActivity.this.mVenderId)) {
                        if (JShopDynamicDetailActivity.this.mCommentRequestStatus == 0 || JShopDynamicDetailActivity.this.mCommentRequestStatus == 0) {
                            return;
                        }
                        JShopDynamicDetailActivity.this.mNoDataView.setVisibility(0);
                        JShopDynamicDetailActivity.this.mNormalView.setVisibility(8);
                        if (!TextUtils.isEmpty(JShopDynamicDetailActivity.this.mErrorMsg)) {
                            JShopDynamicDetailActivity.this.toShowEmptyView();
                            return;
                        } else {
                            JShopDynamicDetailActivity.this.toShowErrView();
                            return;
                        }
                    }
                    JShopDynamicDetailActivity.this.mNoDataView.setVisibility(0);
                    JShopDynamicDetailActivity.this.mNormalView.setVisibility(8);
                    if (!TextUtils.isEmpty(JShopDynamicDetailActivity.this.mErrorMsg)) {
                        JShopDynamicDetailActivity.this.toShowEmptyView();
                    } else {
                        JShopDynamicDetailActivity.this.toShowErrView();
                    }
                } else if (JShopDynamicDetailActivity.this.mCommentRequestStatus == 0 || JShopDynamicDetailActivity.this.mCommentRequestStatus == 0 || JShopDynamicDetailActivity.this.mCommentRequestStatus != 1 || JShopDynamicDetailActivity.this.mDetailRequestStatus != 1) {
                } else {
                    JShopDynamicDetailActivity.this.mNormalView.setVisibility(0);
                    JShopDynamicDetailActivity.this.mNoDataView.setVisibility(8);
                    if (JShopDynamicDetailActivity.this.isShowComment) {
                        JShopDynamicDetailActivity.this.mCommentView.enterAndShowInputSoft();
                    } else {
                        JShopDynamicDetailActivity.this.mCommentView.setVisibility(8);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toShowEmptyView() {
        if (this.utils == null) {
            this.utils = new JshopShowErrorViewUtils(this, (LinearLayout) this.mNoDataView);
        }
        this.mNoDataView = this.utils.getErrorViewHasRetry(null);
        this.utils.setMessageInfo(getResources().getString(R.string.jshop_dynamic_detail_error_msg), "", "");
        this.utils.setErrorImage(R.drawable.y_04);
        this.mNoDataView.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toShowErrView() {
        if (this.utils == null) {
            this.utils = new JshopShowErrorViewUtils(this, (LinearLayout) this.mNoDataView);
        }
        this.mNoDataView = this.utils.getErrorViewHasRetry(this.mRetryOnclicklistener);
        this.utils.setMessageInfo(getString(R.string.jshop_net_fail), getString(R.string.jshop_net_check), "");
        this.utils.setErrorImage(R.drawable.y_03);
        this.mNoDataView.setVisibility(0);
    }

    public void initFrament(DynamicShopActivity dynamicShopActivity) {
        if (dynamicShopActivity != null) {
            if (!(dynamicShopActivity.promotionType != 1) || dynamicShopActivity.activityType != 12) {
                long j2 = dynamicShopActivity.activityType;
                if (j2 != 3 && j2 != 7 && j2 != 15) {
                    replaceFragment(JShopDynamicNormalDetailFragment.class, this.mBundle);
                    return;
                }
            }
            URLParamMap uRLParamMap = new URLParamMap();
            uRLParamMap.put(RemoteMessageConst.TO, dynamicShopActivity.mUrl);
            SerializableContainer serializableContainer = new SerializableContainer();
            serializableContainer.setMap(uRLParamMap);
            this.mBundle.putSerializable("urlParamMap", serializableContainer);
            this.mBundle.putString("urlAction", RemoteMessageConst.TO);
            this.mBundle.putBoolean(MBaseKeyNames.IS_USE_RIGHT_BUTTON, false);
            this.mBundle.putBoolean(MBaseKeyNames.IS_NEED_SHARE, false);
            replaceFragment(JShopDynaWebFragment.class, this.mBundle);
        }
    }

    public void initTitle(final String str) {
        post(new Runnable() { // from class: com.jingdong.common.sample.jshop.JShopDynamicDetailActivity.4
            @Override // java.lang.Runnable
            public void run() {
                if (TextUtils.isEmpty(JShopDynamicDetailActivity.this.mShopName)) {
                    if (!TextUtils.isEmpty(str)) {
                        JShopDynamicDetailActivity.this.mTitle.setText(str);
                    } else {
                        JShopDynamicDetailActivity.this.mTitle.setText("\u52a8\u6001\u8be6\u60c5");
                    }
                }
                JShopDynamicDetailActivity.this.mTitle.setText("\u52a8\u6001\u8be6\u60c5");
            }
        });
    }

    public void initView() {
        this.mTitle = (TextView) findViewById(R.id.fd);
        if (!TextUtils.isEmpty(this.mShopName)) {
            this.mTitle.setText(this.mShopName);
        }
        this.mTitle.setText("\u52a8\u6001\u8be6\u60c5");
        if (JshopTextViewUtils.IS_LARGE_MODE) {
            JshopTextViewUtils.getInstance().doTextFontScaled(this.mTitle);
        }
        setTitleBack((ImageView) findViewById(R.id.fe));
        ((ImageView) findViewById(R.id.fe)).setContentDescription("\u8fd4\u56de");
        ImageView imageView = (ImageView) findViewById(R.id.b8a);
        this.mRightIcon = imageView;
        imageView.setImageResource(R.drawable.jshop_dynamic_detail_shop);
        this.mRightIcon.setVisibility(0);
        this.mRightIcon.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.JShopDynamicDetailActivity.3
            /* JADX WARN: Removed duplicated region for block: B:12:0x0099  */
            /* JADX WARN: Removed duplicated region for block: B:16:0x00a5  */
            /* JADX WARN: Removed duplicated region for block: B:19:0x00d8  */
            /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
            @Override // android.view.View.OnClickListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onClick(View view) {
                String str;
                String str2;
                String str3;
                String string;
                JShopDynamicDetailActivity jShopDynamicDetailActivity;
                JShopDynamicDetailActivity jShopDynamicDetailActivity2 = JShopDynamicDetailActivity.this;
                String str4 = "";
                int i2 = -1;
                if (jShopDynamicDetailActivity2.mShopActivity != null) {
                    string = JShopDynamicDetailActivity.this.mShopActivity.shopId + "";
                    String str5 = JShopDynamicDetailActivity.this.mShopActivity.venderId + "";
                    String str6 = JShopDynamicDetailActivity.this.mShopActivity.shopName + "";
                    str4 = JShopDynamicDetailActivity.this.mShopActivity.activityType + "";
                    str2 = str5;
                    str3 = str6;
                    i2 = JShopDynamicDetailActivity.this.mShopActivity.activitySubType;
                } else {
                    Bundle bundle = jShopDynamicDetailActivity2.mBundle;
                    if (bundle != null) {
                        string = bundle.getString("shopId");
                        str2 = JShopDynamicDetailActivity.this.mBundle.getString("venderId");
                        str3 = JShopDynamicDetailActivity.this.mBundle.getString("shopName");
                    } else {
                        str = "";
                        str2 = str;
                        str3 = str2;
                        if (TextUtils.isEmpty(str4)) {
                            str4 = null;
                        }
                        String activityType = TextUtils.isEmpty(JShopUtil.getActivityType(str, i2)) ? null : JShopUtil.getActivityType(str, i2);
                        JDMtaUtils.sendCommonData(JShopDynamicDetailActivity.this, "ShopDynamicStateDetail_ToShop", activityType + CartConstant.KEY_YB_INFO_LINK + str4, "", JShopDynamicDetailActivity.this, "", "JshopMainShopActivity", "", "ShopDynamicStateDetail_Main", str4);
                        jShopDynamicDetailActivity = JShopDynamicDetailActivity.this;
                        if (jShopDynamicDetailActivity.mBundle == null) {
                            jShopDynamicDetailActivity.hideSoftInput();
                            DeepLinkJShopHomeHelper.gotoJShopHome(JShopDynamicDetailActivity.this, str4, str2, str3, "home", new SourceEntity(JshopConst.SOURCE_ENTITY_GOOD_SHOP, "\u5e97\u94fa\u9996\u9875"));
                            return;
                        }
                        return;
                    }
                }
                String str7 = str4;
                str4 = string;
                str = str7;
                if (TextUtils.isEmpty(str4)) {
                }
                if (TextUtils.isEmpty(JShopUtil.getActivityType(str, i2))) {
                }
                JDMtaUtils.sendCommonData(JShopDynamicDetailActivity.this, "ShopDynamicStateDetail_ToShop", activityType + CartConstant.KEY_YB_INFO_LINK + str4, "", JShopDynamicDetailActivity.this, "", "JshopMainShopActivity", "", "ShopDynamicStateDetail_Main", str4);
                jShopDynamicDetailActivity = JShopDynamicDetailActivity.this;
                if (jShopDynamicDetailActivity.mBundle == null) {
                }
            }
        });
        this.mFragmentManager = getSupportFragmentManager();
        this.mCommentView = (JshopDyCommentView) findViewById(R.id.jshop_comment_view);
        JshopCommentInputView jshopCommentInputView = (JshopCommentInputView) findViewById(R.id.jshop_input_view);
        this.mInputView = jshopCommentInputView;
        this.mCommentView.setInputView(jshopCommentInputView);
        this.mCommentView.setBundle(this.mBundle);
        View findViewById = findViewById(R.id.jshop_dynamic_normal_view);
        this.mNormalView = findViewById;
        findViewById.setVisibility(8);
        View findViewById2 = findViewById(R.id.jshop_error_view);
        this.mNoDataView = findViewById2;
        findViewById2.setVisibility(8);
        this.mContainer = findViewById(R.id.jshop_dynamic_detail_container);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.jshop_dynamic_detail_activity);
        setPageId("ShopDynamicStateDetail_Main");
        if (getIntent() != null) {
            Bundle extras = getIntent().getExtras();
            this.mBundle = extras;
            if (extras != null) {
                if (extras.containsKey("params_key")) {
                    try {
                        JSONObject jSONObject = new JSONObject(this.mBundle.getString("params_key"));
                        Intent intent = new Intent();
                        intent.putExtra("shopName", jSONObject.optString("shopName"));
                        intent.putExtra("shopId", jSONObject.optString("shopId"));
                        intent.putExtra("venderId", jSONObject.optString("venderId"));
                        intent.putExtra(JshopConst.JSHOP_ACTIVITY_TYPE, jSONObject.optString(JshopConst.JSHOP_ACTIVITY_TYPE));
                        intent.putExtra("activityId", jSONObject.optString("activityId"));
                        intent.putExtra(JshopConst.JSHOP_SHOW_COMMENT, jSONObject.optBoolean(JshopConst.JSHOP_SHOW_COMMENT));
                        this.mBundle = intent.getExtras();
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
                this.isShowComment = this.mBundle.getBoolean(JshopConst.JSHOP_SHOW_COMMENT);
                this.mShopName = this.mBundle.getString("shopName");
                this.mVenderId = this.mBundle.getString("venderId");
                this.isShowKeyboard = this.mBundle.getBoolean(JshopConst.JSHOP_SHOW_KEYBOARD, false);
                getActivityDetail();
            }
        }
        initView();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Handler handler = this.mHandler;
        if (handler == null || !handler.hasMessages(1001)) {
            return;
        }
        this.mHandler.removeMessages(1001);
    }

    @Override // com.jingdong.common.BaseActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            Fragment fragment = this.mCurFragment;
            if (fragment instanceof JShopDynamicNormalDetailFragment ? ((JShopDynamicNormalDetailFragment) fragment).onKeyDown(i2, keyEvent) : false) {
                return true;
            }
        }
        return super.onKeyDown(i2, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.isNeedUpdate = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Handler handler = this.mHandler;
        if (handler == null || !this.isNeedUpdate) {
            return;
        }
        handler.sendEmptyMessageDelayed(1001, 200L);
    }

    public void pullCommentView() {
        Log.d(TAG, "  +++  pullCommentView  +++   ");
        post(new Runnable() { // from class: com.jingdong.common.sample.jshop.JShopDynamicDetailActivity.5
            @Override // java.lang.Runnable
            public void run() {
                if (JShopDynamicDetailActivity.this.mCommentView.getVisibility() == 0) {
                    JShopDynamicDetailActivity.this.mContainer.setVisibility(8);
                } else {
                    JShopDynamicDetailActivity.this.mContainer.setVisibility(0);
                }
            }
        }, 400);
        JshopDyCommentView jshopDyCommentView = this.mCommentView;
        if (jshopDyCommentView != null && jshopDyCommentView.isCanComment() && this.mCommentView.getVisibility() == 8) {
            this.mCommentView.setVisibility(0);
            this.mCommentView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.jshop_dynamic_in));
        }
    }

    public void pullOrPushCommentView() {
        JshopDyCommentView jshopDyCommentView = this.mCommentView;
        if (jshopDyCommentView != null) {
            if (jshopDyCommentView.getVisibility() == 0) {
                pushCommentView();
            } else {
                pullCommentView();
            }
        }
    }

    public void pushCommentView() {
        Log.d(TAG, "  +++  pushCommentView  +++   ");
        this.mContainer.setVisibility(0);
        JshopDyCommentView jshopDyCommentView = this.mCommentView;
        if (jshopDyCommentView == null || jshopDyCommentView.getVisibility() != 0) {
            return;
        }
        this.mCommentView.setVisibility(8);
        this.mCommentView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.jshop_dynamic_out));
    }

    public void replaceFragment(Class<? extends Fragment> cls, Bundle bundle) {
        this.mCurFragment = JShopFragmentUtils.switchFragment(this.mFragmentManager, (int) R.id.jshop_dynamic_detail_container, this.mCurFragment, cls, bundle);
    }

    public void setActivityType(DynamicShopActivity dynamicShopActivity) {
        this.mShopActivity = dynamicShopActivity;
    }

    public void setCommentNetStatus(int i2) {
        this.mCommentRequestStatus = i2;
        showView();
    }

    public void setDetailData(DynamicShopActivity dynamicShopActivity) {
        JshopDyCommentView jshopDyCommentView = this.mCommentView;
        if (jshopDyCommentView != null) {
            jshopDyCommentView.setDetailData(dynamicShopActivity);
        }
    }

    public void setDetailRequestSuccess(int i2) {
        this.mDetailRequestStatus = i2;
        showView();
    }

    public void setRightIconVisibility(int i2) {
        ImageView imageView = this.mRightIcon;
        if (imageView != null) {
            imageView.setVisibility(i2);
        }
    }

    public void setmDetailRequestStatus(int i2, String str) {
        this.mDetailRequestStatus = i2;
        this.mErrorMsg = str;
        showView();
    }
}
