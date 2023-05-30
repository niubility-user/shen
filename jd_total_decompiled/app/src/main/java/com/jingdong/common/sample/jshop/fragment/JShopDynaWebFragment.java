package com.jingdong.common.sample.jshop.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.sample.jshop.Entity.DynamicShopActivity;
import com.jingdong.common.sample.jshop.Entity.DynamicShopProduct;
import com.jingdong.common.sample.jshop.Entity.JShopShareInfo;
import com.jingdong.common.sample.jshop.JShopDynamicDetailActivity;
import com.jingdong.common.web.ui.CommonMFragment;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.tencent.smtt.sdk.WebView;
import java.util.HashSet;

/* loaded from: classes6.dex */
public class JShopDynaWebFragment extends CommonMFragment {
    private static final float OFFSET_RADIO = 2.0f;
    private static final String TAG = "JShopDynaWebFragment";
    private MyActivity mActivity;
    private Bundle mBundle;
    private TextView mHeaderTv;
    private ListUpOrDownListener mListener;
    private WebView mWebView;
    private View titleLayout;
    private ScrollView webScrollView;
    private RelativeLayout webviewLayout;
    private float mLastY = -1.0f;
    private JDWebView mJDWebWiew = null;
    private boolean isSend = false;
    private boolean isFromShare = false;
    private String mShareUrl = "";
    private HashSet<Integer> set = new HashSet<>();
    boolean flag = true;
    private float downY = -1.0f;

    /* loaded from: classes6.dex */
    public interface ListUpOrDownListener {
        void onDown();

        void onUp();
    }

    private void getActivityDetail() {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(Configuration.getJshopHost());
        httpSetting.setEffect(1);
        httpSetting.setFunctionId("getActivityDetail");
        httpSetting.putJsonParam("page", "1");
        httpSetting.putJsonParam("pageSize", "20");
        httpSetting.setUseFastJsonParser(true);
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
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopDynaWebFragment.1
            {
                JShopDynaWebFragment.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(final HttpResponse httpResponse) {
                JShopDynaWebFragment.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopDynaWebFragment.1.1
                    {
                        AnonymousClass1.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        String string = JShopDynaWebFragment.this.mActivity.getResources().getString(R.string.jshop_dynamic_detail_error_msg);
                        if (httpResponse == null) {
                            JShopDynaWebFragment.this.setEmptyState(string);
                            return;
                        }
                        Log.d(JShopDynaWebFragment.TAG, " onEnd , response ===> : " + httpResponse.getJSONObject());
                        JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                        if (fastJsonObject == null || fastJsonObject.optJSONObject("activity") == null) {
                            if (fastJsonObject != null) {
                                string = fastJsonObject.optString("errorMsg", JShopDynaWebFragment.this.mActivity.getResources().getString(R.string.jshop_dynamic_detail_error_msg));
                            }
                            JShopDynaWebFragment.this.setEmptyState(string);
                            return;
                        }
                        if (JShopDynaWebFragment.this.mActivity instanceof JShopDynamicDetailActivity) {
                            ((JShopDynamicDetailActivity) JShopDynaWebFragment.this.mActivity).setDetailRequestSuccess(1);
                        }
                        JDJSONObject optJSONObject = fastJsonObject.optJSONObject("activity");
                        try {
                            optJSONObject.put("commentSwitch", (Object) fastJsonObject.optString("commentSwitch"));
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        DynamicShopActivity dynamicShopActivity = new DynamicShopActivity(optJSONObject);
                        if (JShopDynaWebFragment.this.mActivity != null && (JShopDynaWebFragment.this.mActivity instanceof JShopDynamicDetailActivity)) {
                            ((JShopDynamicDetailActivity) JShopDynaWebFragment.this.mActivity).initTitle(dynamicShopActivity.shopName);
                            ((JShopDynamicDetailActivity) JShopDynaWebFragment.this.mActivity).setActivityType(dynamicShopActivity);
                            if (dynamicShopActivity.shopId <= 0 && dynamicShopActivity.venderId <= 0) {
                                ((JShopDynamicDetailActivity) JShopDynaWebFragment.this.mActivity).setRightIconVisibility(8);
                            }
                        }
                        JShopDynaWebFragment.this.parseJSON(dynamicShopActivity);
                        JDJSONObject optJSONObject2 = fastJsonObject.optJSONObject("shareInfo");
                        if (optJSONObject2 != null) {
                            JShopShareInfo jShopShareInfo = new JShopShareInfo(optJSONObject2);
                            dynamicShopActivity.shareInfo = jShopShareInfo;
                            JShopDynaWebFragment.this.mShareUrl = jShopShareInfo.url;
                        }
                        if (JShopDynaWebFragment.this.mActivity == null || !(JShopDynaWebFragment.this.mActivity instanceof JShopDynamicDetailActivity)) {
                            return;
                        }
                        ((JShopDynamicDetailActivity) JShopDynaWebFragment.this.mActivity).setDetailData(dynamicShopActivity);
                    }
                });
                JShopDynaWebFragment.this.sendShareMTA();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                Log.d(JShopDynaWebFragment.TAG, "onError");
                JShopDynaWebFragment.this.sendShareMTA();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        MyActivity myActivity = this.mActivity;
        if (myActivity != null) {
            myActivity.getHttpGroupaAsynPool().add(httpSetting);
        }
    }

    public void parseJSON(DynamicShopActivity dynamicShopActivity) {
        boolean z = dynamicShopActivity.isLargePic;
        DynamicShopProduct.toProductList(dynamicShopActivity.getProducts());
        Log.d(TAG, "isLargePic  ==  :  " + z + "  ,  shopActivity.activityType  ==  :  " + dynamicShopActivity.activityType);
        if (!z) {
            long j2 = dynamicShopActivity.activityType;
            if (j2 != 11 && j2 != 3 && j2 != 12) {
                Log.d(TAG, "  ===  show small view  ===  ");
                return;
            }
        }
        Log.d(TAG, "  ===  show large view  ===  ");
    }

    private void resetFooterHeight() {
    }

    public void sendShareMTA() {
        if (this.isSend) {
            return;
        }
        this.isSend = true;
        if (this.isFromShare) {
            MyActivity myActivity = this.mActivity;
            JDMtaUtils.sendCommonData(myActivity, "Shop_ShareReturn", "Shop_ShopDynamicStateDetail_Share", "", myActivity, "", "", "", "", "");
        }
    }

    public void setEmptyState(String str) {
        MyActivity myActivity = this.mActivity;
        if (myActivity instanceof JShopDynamicDetailActivity) {
            ((JShopDynamicDetailActivity) myActivity).initTitle("");
            ((JShopDynamicDetailActivity) this.mActivity).setmDetailRequestStatus(-1, str);
        }
    }

    private void updateFooterHeight(float f2) {
        setBottomMargin(getBottomMargin() + ((int) f2));
    }

    public int getBottomMargin() {
        return ((FrameLayout.LayoutParams) this.mJDWebWiew.getLayoutParams()).bottomMargin;
    }

    public int getVisiableHeight() {
        return this.mJDWebWiew.getHeight();
    }

    public void handerScroll() {
        WebView webView = this.mWebView;
        if (webView != null) {
            webView.setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopDynaWebFragment.2
                {
                    JShopDynaWebFragment.this = this;
                }

                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 2 || motionEvent.getAction() == 0) {
                        float contentHeight = (JShopDynaWebFragment.this.mWebView.getContentHeight() * JShopDynaWebFragment.this.mWebView.getScale()) - (JShopDynaWebFragment.this.mWebView.getHeight() + JShopDynaWebFragment.this.mWebView.getWebScrollY());
                        if (contentHeight > 5.0f) {
                            JShopDynaWebFragment.this.set.add(0);
                        } else if (contentHeight <= 5.0f) {
                            JShopDynaWebFragment.this.set.add(1);
                        }
                    } else if (motionEvent.getAction() == 1) {
                        JShopDynaWebFragment.this.set.clear();
                    }
                    if (JShopDynaWebFragment.this.set.contains(1) && !JShopDynaWebFragment.this.set.contains(0)) {
                        JShopDynaWebFragment.this.handlerTouchEvent(motionEvent);
                    }
                    return false;
                }
            });
        }
    }

    public void handlerTouchEvent(MotionEvent motionEvent) {
        if (this.downY == -1.0f) {
            this.downY = motionEvent.getRawY();
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.downY = motionEvent.getRawY();
        } else if (action != 2) {
        } else {
            int rawY = (int) (motionEvent.getRawY() - this.downY);
            Log.d(TAG, "tmpDeltaY = " + rawY);
            if (rawY > 50) {
                Log.d(TAG, "=======onUp()=========");
                ListUpOrDownListener listUpOrDownListener = this.mListener;
                if (listUpOrDownListener != null) {
                    listUpOrDownListener.onUp();
                }
            }
            if (rawY < -50) {
                Log.d(TAG, "=======onDown()=========");
                ListUpOrDownListener listUpOrDownListener2 = this.mListener;
                if (listUpOrDownListener2 != null) {
                    listUpOrDownListener2.onDown();
                }
                MyActivity myActivity = this.mActivity;
                if (myActivity != null) {
                    if (this.flag) {
                        ((JShopDynamicDetailActivity) myActivity).pullCommentView();
                        this.flag = false;
                    }
                    this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopDynaWebFragment.3
                        {
                            JShopDynaWebFragment.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            JShopDynaWebFragment.this.flag = true;
                        }
                    }, 2000);
                }
            }
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mActivity = (MyActivity) activity;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.common.web.ui.CommonMFragment, com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setIsUseBasePV(false);
    }

    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle arguments = getArguments();
        this.mBundle = arguments;
        if (arguments.containsKey("source")) {
            this.isFromShare = "activityDetail".equals(this.mBundle.getString("source"));
        }
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        JDWebView jDWebView = (JDWebView) getView();
        this.mJDWebWiew = jDWebView;
        jDWebView.setTopBarGone(true);
        View findViewById = this.mJDWebWiew.findViewById(R.id.r);
        this.titleLayout = findViewById;
        if (findViewById != null) {
            findViewById.setVisibility(8);
        }
        this.mWebView = this.mJDWebWiew.getWebView();
        handerScroll();
        sendShareMTA();
    }

    public void setBottomMargin(int i2) {
        if (i2 < 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mJDWebWiew.getLayoutParams();
        layoutParams.bottomMargin = i2;
        this.mJDWebWiew.setLayoutParams(layoutParams);
    }

    public void setUpOrDownListener(ListUpOrDownListener listUpOrDownListener) {
        this.mListener = listUpOrDownListener;
    }

    public void setVisiableHeight(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mJDWebWiew.getLayoutParams();
        layoutParams.height = i2;
        this.mJDWebWiew.setLayoutParams(layoutParams);
    }

    public void updateBottomHeight(float f2) {
        setVisiableHeight(((int) f2) + getVisiableHeight());
    }
}
