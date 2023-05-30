package com.jingdong.common.sample.jshop.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.R;
import com.jingdong.cleanmvp.ui.BaseFragment;
import com.jingdong.common.sample.jshop.JShopSignNewActivity;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes6.dex */
public class JShopRuleFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "JShopRuleFragment";
    private static final String functionId = "signActivityRule";
    private TextView activity_description;
    private TextView award_description;
    private JShopSignNewActivity mActivity;
    private Button mBtnMainShop;
    private Button mBtnRetry;
    private LinearLayout mFailView;
    private View normal_view;
    private TextView rule_time;
    private long vendorId = -1;

    private void convertValue(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.vendorId = Long.parseLong(str);
        } catch (NumberFormatException unused) {
        }
    }

    private void postSignRule() {
        if (this.vendorId == -1) {
            this.normal_view.setVisibility(8);
            this.mFailView.setVisibility(0);
            return;
        }
        this.mActivity.setSubRootView(null);
        this.normal_view.setVisibility(8);
        this.mFailView.setVisibility(8);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(Configuration.getJshopHost());
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setFunctionId(functionId);
        httpSetting.putJsonParam("vendorId", Long.valueOf(this.vendorId));
        httpSetting.setNotifyUser(false);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopRuleFragment.1
            {
                JShopRuleFragment.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (httpResponse == null) {
                    JShopRuleFragment.this.showFailView();
                    return;
                }
                final JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                Log.d(JShopRuleFragment.TAG, "response json --> : " + fastJsonObject);
                JShopRuleFragment.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopRuleFragment.1.1
                    {
                        AnonymousClass1.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        JShopRuleFragment.this.normal_view.setVisibility(0);
                        JShopRuleFragment.this.mFailView.setVisibility(8);
                        JDJSONObject optJSONObject = fastJsonObject.optJSONObject("result");
                        if (optJSONObject == null) {
                            JShopRuleFragment.this.showFailView();
                            return;
                        }
                        JShopRuleFragment.this.rule_time.setText(optJSONObject.optString("activityTime"));
                        String optString = optJSONObject.optString("activityDescription");
                        if (!TextUtils.isEmpty(optString)) {
                            optString.replaceAll("\\\\n", ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                            JShopRuleFragment.this.activity_description.setText(optString);
                        }
                        JDJSONArray optJSONArray = optJSONObject.optJSONArray("awardDescription");
                        if (optJSONArray == null || optJSONArray.size() <= 0) {
                            return;
                        }
                        StringBuffer stringBuffer = new StringBuffer();
                        for (int i2 = 0; i2 < optJSONArray.size(); i2++) {
                            if (i2 < optJSONArray.size() - 1) {
                                stringBuffer.append(optJSONArray.optString(i2));
                                stringBuffer.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                            } else {
                                stringBuffer.append(optJSONArray.optString(i2));
                            }
                        }
                        JShopRuleFragment.this.award_description.setText(stringBuffer.toString());
                    }
                });
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                JShopRuleFragment.this.showFailView();
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

    public void showFailView() {
        post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopRuleFragment.2
            {
                JShopRuleFragment.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                JShopRuleFragment.this.normal_view.setVisibility(8);
                JShopRuleFragment.this.mFailView.setVisibility(0);
            }
        });
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
        int id = view.getId();
        if (id == R.id.btn_main_shop) {
            this.mActivity.finish();
        } else if (id != R.id.btn_retry) {
        } else {
            postSignRule();
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivity.setSubRootView(null);
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.jshop_rule_fragment, (ViewGroup) null);
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        this.normal_view = view.findViewById(R.id.normal_view);
        this.rule_time = (TextView) view.findViewById(R.id.rule_time);
        this.award_description = (TextView) view.findViewById(R.id.award_description);
        this.activity_description = (TextView) view.findViewById(R.id.activity_description);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.fail_view);
        this.mFailView = linearLayout;
        Button button = (Button) linearLayout.findViewById(R.id.btn_main_shop);
        this.mBtnMainShop = button;
        button.setOnClickListener(this);
        Button button2 = (Button) this.mFailView.findViewById(R.id.btn_retry);
        this.mBtnRetry = button2;
        button2.setOnClickListener(this);
        convertValue(getArguments().getString("venderId"));
        postSignRule();
    }
}
