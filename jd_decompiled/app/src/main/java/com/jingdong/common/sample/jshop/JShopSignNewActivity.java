package com.jingdong.common.sample.jshop;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.sample.jshop.fragment.JShopMyAwardFragment;
import com.jingdong.common.sample.jshop.fragment.JShopRuleFragment;
import com.jingdong.common.sample.jshop.fragment.JShopSignFragment;
import com.jingdong.common.sample.jshop.utils.JShopFragmentUtils;
import com.jingdong.common.sample.jshop.utils.JShopUtil;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.ClickConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.widget.ToastUtils;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes6.dex */
public class JShopSignNewActivity extends MyActivity implements View.OnClickListener, JShopSignFragment.ShareLinkInterface {
    private static final String TAG = "JShopSignNewActivity";
    private int activityRuleType;
    private String cateJSON;
    private Fragment mCurFragment;
    private FragmentManager mFragmentManager;
    private View mTabAward;
    private TextView mTabAwardName;
    private View mTabGroup;
    private View mTabRule;
    private TextView mTabRuleName;
    private View mTabSign;
    private TextView mTabSignName;
    private String prizeName;
    private int prizeType;
    private String shareLink;
    public String shopId;
    private String shopName;
    private JSONArray tabArrays;
    private TextView title;
    public String venderId;
    private long venderIdValue;
    private int currentTab = 2;
    private int shared = 0;
    private boolean isWin = false;
    private String backUrl = "";
    private boolean backFinish = false;
    private int isSign = 0;
    public boolean isSignSuccess = false;
    private Bundle bundleShop = new Bundle();

    private void convertValue(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.venderIdValue = Long.parseLong(str);
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
    }

    private void initIntentAndData() {
        Intent intent = getIntent();
        if (intent != null) {
            this.backFinish = intent.getBooleanExtra("backFinish", false);
            this.backUrl = intent.getStringExtra("backUrl");
            this.shopName = intent.getStringExtra("shopName");
            this.shopId = intent.getStringExtra("shopId");
            this.venderId = intent.getStringExtra("venderId");
            this.activityRuleType = intent.getIntExtra(JshopConst.JSKEY_SHOP_SIGNTYPE, -1);
            this.cateJSON = intent.getStringExtra("cateJSON");
            boolean booleanExtra = intent.getBooleanExtra(JshopConst.FOLLOWED_KEY, false);
            this.bundleShop.putString("shopId", this.shopId);
            this.bundleShop.putString("venderId", this.venderId);
            this.bundleShop.putString("shopName", this.shopName);
            this.bundleShop.putString("cateJSON", this.cateJSON);
            this.bundleShop.putBoolean(JshopConst.FOLLOWED_KEY, booleanExtra);
            convertValue(this.venderId);
            int i2 = this.activityRuleType;
            if (i2 != -1) {
                this.bundleShop.putInt(JshopConst.JSKEY_SHOP_SIGNTYPE, i2);
            }
            Log.d(TAG, "shopName: " + this.shopName + ", shopId: " + this.shopId + ", venderId: " + this.venderId + ", activityRuleType: " + this.activityRuleType + " cateJSON" + this.cateJSON);
        }
    }

    private void initView() {
        this.title = (TextView) findViewById(R.id.fd);
        ImageView imageView = (ImageView) findViewById(R.id.fe);
        imageView.setVisibility(0);
        imageView.setOnClickListener(this);
        ImageView imageView2 = (ImageView) findViewById(R.id.b87);
        imageView2.setVisibility(0);
        imageView2.setBackgroundResource(R.drawable.jshop_title_share_big_selector);
        imageView2.setOnClickListener(this);
        this.mTabRule = findViewById(R.id.activity_rule);
        this.mTabSign = findViewById(R.id.shop_sign);
        this.mTabAward = findViewById(R.id.my_award);
        this.mTabRule.setOnClickListener(this);
        this.mTabSign.setOnClickListener(this);
        this.mTabAward.setOnClickListener(this);
        this.mTabRuleName = (TextView) findViewById(R.id.activity_rule_text);
        this.mTabSignName = (TextView) findViewById(R.id.shop_sign_text);
        this.mTabAwardName = (TextView) findViewById(R.id.my_award_text);
        View findViewById = findViewById(R.id.jshop_sign_tab_group);
        this.mTabGroup = findViewById;
        findViewById.setVisibility(8);
        this.mFragmentManager = getSupportFragmentManager();
    }

    private void makeShare(View view) {
        int i2;
        String str;
        String str2;
        String string;
        String string2;
        Fragment fragment = this.mCurFragment;
        if (fragment instanceof JShopSignFragment) {
            JShopSignFragment jShopSignFragment = (JShopSignFragment) fragment;
            String str3 = this.shareLink + CartConstant.KEY_YB_INFO_LINK + jShopSignFragment.signTotal;
            String str4 = this.shopId;
            JDMtaUtils.sendCommonData(this, "ShopCheckIn_RightTopShare", str3, "", this, str4, "", "", "ShopCheckIn_ShopCheckInMain", str4);
            this.activityRuleType = jShopSignFragment.activityRuleType;
            this.shared = jShopSignFragment.shared;
            Log.d(TAG, "Share information, shared: " + this.shared + " isWin: " + this.isWin + " prizeType: " + this.prizeType + " prizeName: " + this.prizeName + " activityRuleType: " + this.activityRuleType);
            view.setEnabled(false);
            try {
                String str5 = this.shareLink;
                if (str5 != null && ((i2 = this.activityRuleType) == 1 || i2 == 0)) {
                    Drawable drawable = getResources().getDrawable(R.drawable.jshop_share_image);
                    String string3 = getString(R.string.wx_share_shop_sign_normal_hint, new Object[]{str5, ""});
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                    if (bitmapDrawable != null) {
                        boolean z = this.isWin;
                        if (!z) {
                            string = getString(R.string.wx_share_shop_sign_hint_noprize);
                            string2 = getString(R.string.wx_share_shop_sign_normal_hint, new Object[]{string + str5});
                        } else {
                            if (z) {
                                int i3 = this.prizeType;
                                if (i3 == 0) {
                                    string = "\u5e97\u94fa\u62bd\u5956\u6d3b\u52a8\u8bf6!\u6211\u83b7\u5f97\u4e86" + this.prizeName + "\uff0c\u5feb\u6765\u548c\u6211\u4eec\u4e00\u8d77\u73a9\u5427~";
                                    string2 = getString(R.string.wx_share_shop_sign_normal_hint, new Object[]{string + str5});
                                } else if (i3 == 1) {
                                    string = getString(R.string.wx_share_shop_sign_hint_selfshare_price);
                                    string2 = getString(R.string.wx_share_shop_sign_normal_hint, new Object[]{string + str5});
                                } else if (i3 == 2) {
                                    string = getString(R.string.wx_share_shop_sign_hint_object);
                                    string2 = getString(R.string.wx_share_shop_sign_normal_hint, new Object[]{string + str5});
                                }
                            }
                            str = "";
                            str2 = string3;
                            ShareUtil.showShareDialog(this, new ShareInfo(this.shopName, str, str, str5, str2, ClickConstant.CLICK_SHARE_VALUE_SHOP_SIGN, null, bitmapDrawable.getBitmap()));
                            view.postDelayed(new Runnable() { // from class: com.jingdong.common.sample.jshop.JShopSignNewActivity.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    JShopSignNewActivity.this.findViewById(R.id.b87).setEnabled(true);
                                }
                            }, 1000L);
                            return;
                        }
                        str = string;
                        str2 = string2;
                        ShareUtil.showShareDialog(this, new ShareInfo(this.shopName, str, str, str5, str2, ClickConstant.CLICK_SHARE_VALUE_SHOP_SIGN, null, bitmapDrawable.getBitmap()));
                        view.postDelayed(new Runnable() { // from class: com.jingdong.common.sample.jshop.JShopSignNewActivity.1
                            @Override // java.lang.Runnable
                            public void run() {
                                JShopSignNewActivity.this.findViewById(R.id.b87).setEnabled(true);
                            }
                        }, 1000L);
                        return;
                    }
                    return;
                }
                view.setEnabled(true);
                ToastUtils.shortToast(getApplicationContext(), "\u6ca1\u6709\u6b63\u786e\u7684\u5206\u4eab\u94fe\u63a5\uff0c\u65e0\u6cd5\u5206\u4eab");
            } catch (Exception e2) {
                e2.printStackTrace();
                view.setEnabled(true);
            }
        }
    }

    private void postSignShareRequest(String str) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(Configuration.getJshopHost());
        httpSetting.setFunctionId("signShare");
        httpSetting.putJsonParam("vendorId", Long.valueOf(this.venderIdValue));
        httpSetting.putJsonParam("shareChannel", str);
        httpSetting.setUseCookies(true);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.sample.jshop.JShopSignNewActivity.5
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                Log.d(JShopSignNewActivity.TAG, "arg0 is -->>" + httpResponse);
                if (httpResponse == null) {
                    return;
                }
                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                Log.d(JShopSignNewActivity.TAG, "response json --> : " + fastJsonObject);
                try {
                    String optString = fastJsonObject.optString("code");
                    Boolean valueOf = Boolean.valueOf(fastJsonObject.optBoolean("result"));
                    if ("0".equals(optString) && valueOf.booleanValue()) {
                        Log.d(JShopSignNewActivity.TAG, "share times added");
                        JShopSignNewActivity.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.JShopSignNewActivity.5.1
                            @Override // java.lang.Runnable
                            public void run() {
                                JShopSignNewActivity.this.showShareSucessDialog("\u5206\u4eab\u6210\u529f\uff0c\u4eca\u5929\u589e\u52a0\u4e00\u6b21\u62bd\u5956\u673a\u4f1a\n\u6bcf\u5929\u4ec5\u6709\u4e00\u6b21\u5206\u4eab\u589e\u52a0\u673a\u4f1a\u6743\u5229\u54e6");
                            }
                        });
                    } else {
                        JShopSignNewActivity.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.JShopSignNewActivity.5.2
                            @Override // java.lang.Runnable
                            public void run() {
                                ToastUtils.showToastInCenter((Context) JShopSignNewActivity.this, (byte) 2, "\u5206\u4eab\u6210\u529f", 0);
                            }
                        });
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                Log.e(JShopSignNewActivity.TAG, "onError");
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

    private void replaceFragment(Class<? extends Fragment> cls) {
        Fragment switchFragment = JShopFragmentUtils.switchFragment(this.mFragmentManager, (int) R.id.sign_container, this.mCurFragment, cls, this.bundleShop);
        this.mCurFragment = switchFragment;
        if (switchFragment instanceof JShopMyAwardFragment) {
            ((JShopMyAwardFragment) switchFragment).showNormalData();
            ((JShopMyAwardFragment) this.mCurFragment).postRequestMyAwardAgain();
        }
    }

    private void setShareBtnShow(int i2) {
        findViewById(R.id.b87).setVisibility(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showShareSucessDialog(String str) {
        JDDialog createJdDialogWithStyle1 = JDDialogFactory.getInstance().createJdDialogWithStyle1(this, str, "\u6211\u77e5\u9053\u4e86");
        createJdDialogWithStyle1.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.jingdong.common.sample.jshop.JShopSignNewActivity.7
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                JShopSignNewActivity.this.currentTab = 2;
                if (JShopSignNewActivity.this.mCurFragment != null && (JShopSignNewActivity.this.mCurFragment instanceof JShopSignFragment)) {
                    Log.d(JShopSignNewActivity.TAG, "jShopSignFragment is not null ,getSignInfoRequest");
                    ((JShopSignFragment) JShopSignNewActivity.this.mCurFragment).getSignInfoRequest();
                }
                dialogInterface.dismiss();
            }
        });
        createJdDialogWithStyle1.show();
    }

    private void showTab(int i2) {
        this.mTabRule.setSelected(false);
        this.mTabSign.setSelected(false);
        this.mTabAward.setSelected(false);
        if (i2 == 1) {
            this.mTabRule.setSelected(true);
            this.title.setText(this.mTabRuleName.getText().toString());
            replaceFragment(JShopRuleFragment.class);
            String str = "ShopCheckIn_ActivityRulesTAB";
            String str2 = this.activityRuleType == 0 ? "\u6982\u7387\u7b7e\u5230" : "\u8fde\u7eed\u7b7e\u5230";
            String str3 = this.shopId;
            JDMtaUtils.sendCommonData(this, str, str2, "showTab", this, str3, "", "", "ShopCheckIn_ActivityRulesMain", str3);
        } else if (i2 != 2) {
            if (i2 != 3) {
                return;
            }
            this.mTabAward.setSelected(true);
            this.title.setText(this.mTabAwardName.getText().toString());
            replaceFragment(JShopMyAwardFragment.class);
            String str4 = this.shopId;
            JDMtaUtils.sendCommonData(this, "ShopCheckIn_MyPrizeTAB", "", "showTab", this, str4, "", "", "ShopCheckIn_MyPrizeMain", str4);
        } else {
            this.mTabSign.setSelected(true);
            this.title.setText(this.mTabSignName.getText().toString());
            replaceFragment(JShopSignFragment.class);
            String str5 = "ShopCheckIn_ShopCheckInTAB";
            int i3 = this.isSign;
            String str6 = (i3 == 1 || i3 == 2) ? "\u5df2\u7b7e\u5230" : "\u672a\u7b7e\u5230";
            String str7 = this.shopId;
            JDMtaUtils.sendCommonData(this, str5, str6, "showTab", this, str7, "", "", "ShopCheckIn_ShopCheckInMain", str7);
        }
    }

    public void clickSignTab() {
        View view = this.mTabSign;
        if (view != null) {
            view.performClick();
        }
    }

    @Override // com.jingdong.common.sample.jshop.fragment.JShopSignFragment.ShareLinkInterface
    public void getIsSign(int i2) {
        this.isSign = i2;
        this.isSignSuccess = i2 == 1;
    }

    @Override // com.jingdong.common.sample.jshop.fragment.JShopSignFragment.ShareLinkInterface
    public void getShareLink(String str) {
        this.shareLink = str;
    }

    @Override // com.jingdong.common.sample.jshop.fragment.JShopSignFragment.ShareLinkInterface
    public void getShared(int i2) {
        this.shared = i2;
    }

    @Override // com.jingdong.common.sample.jshop.fragment.JShopSignFragment.ShareLinkInterface
    public void getTabArray(final JDJSONArray jDJSONArray) {
        post(new Runnable() { // from class: com.jingdong.common.sample.jshop.JShopSignNewActivity.6
            @Override // java.lang.Runnable
            public void run() {
                JDJSONArray jDJSONArray2 = jDJSONArray;
                if (jDJSONArray2 != null && jDJSONArray2.size() == 3) {
                    CommonBase.putStringToPreference(JshopConst.JSHOP_TAB_TEXT_ARRAY, jDJSONArray.toString());
                    JShopSignNewActivity.this.mTabRuleName.setText(jDJSONArray.optString(0));
                    JShopSignNewActivity.this.mTabSignName.setText(jDJSONArray.optString(1));
                    JShopSignNewActivity.this.mTabAwardName.setText(jDJSONArray.optString(2));
                } else {
                    String stringFromPreference = CommonBase.getStringFromPreference(JshopConst.JSHOP_TAB_TEXT_ARRAY, "");
                    if (!TextUtils.isEmpty(stringFromPreference)) {
                        try {
                            JSONArray jSONArray = new JSONArray(stringFromPreference);
                            if (jSONArray.length() == 3) {
                                JShopSignNewActivity.this.mTabRuleName.setText(jSONArray.optString(0));
                                JShopSignNewActivity.this.mTabSignName.setText(jSONArray.optString(1));
                                JShopSignNewActivity.this.mTabAwardName.setText(jSONArray.optString(2));
                            }
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                if (TextUtils.isEmpty(JShopSignNewActivity.this.mTabRuleName.getText().toString()) || TextUtils.isEmpty(JShopSignNewActivity.this.mTabSignName.getText().toString()) || TextUtils.isEmpty(JShopSignNewActivity.this.mTabAwardName.getText().toString())) {
                    JShopSignNewActivity.this.mTabRuleName.setText(R.string.jshop_activity_rule);
                    JShopSignNewActivity.this.mTabSignName.setText(R.string.jshop_shop_sign);
                    JShopSignNewActivity.this.mTabAwardName.setText(R.string.jshop_my_award);
                }
                if (JShopSignNewActivity.this.title != null) {
                    JShopSignNewActivity.this.title.setText(JShopSignNewActivity.this.mTabSignName.getText().toString());
                }
                JShopSignNewActivity.this.mTabGroup.setVisibility(0);
            }
        });
    }

    @Override // com.jingdong.common.sample.jshop.fragment.JShopSignFragment.ShareLinkInterface
    public void getWinInfo(boolean z, int i2, int i3, String str) {
        this.isWin = z;
        this.prizeType = i2;
        this.activityRuleType = i3;
        this.prizeName = str;
    }

    @Override // com.jingdong.common.BaseActivity
    public void hideSoftInput() {
        Log.d(TAG, "hide imm");
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_rule /* 2131689615 */:
                this.currentTab = 1;
                setShareBtnShow(8);
                showTab(this.currentTab);
                return;
            case R.id.fe /* 2131689696 */:
                int i2 = this.currentTab;
                if (i2 != 3 && i2 != 1) {
                    if (this.backFinish && !TextUtils.isEmpty(this.backUrl)) {
                        JShopUtil.toWeb(this, this.backUrl);
                    }
                    finish();
                    return;
                }
                clickSignTab();
                return;
            case R.id.b87 /* 2131692357 */:
                makeShare(view);
                return;
            case R.id.my_award /* 2131694240 */:
                this.currentTab = 3;
                setShareBtnShow(8);
                showTab(this.currentTab);
                return;
            case R.id.shop_sign /* 2131694481 */:
                this.currentTab = 2;
                setShareBtnShow(0);
                showTab(this.currentTab);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.jshop_sign_new_activity);
        initIntentAndData();
        setShopId(this.shopId);
        setPageId("Shop_CheckIn");
        initView();
        showTab(this.currentTab);
    }

    @Override // com.jingdong.common.BaseActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        int i3 = this.currentTab;
        if (i3 != 3 && i3 != 1) {
            if (this.backFinish && !TextUtils.isEmpty(this.backUrl)) {
                JShopUtil.toWeb(this, this.backUrl);
                finish();
                return true;
            }
            return super.onKeyDown(i2, keyEvent);
        }
        clickSignTab();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override // com.jingdong.common.BaseActivity
    public void onShareCancel() {
        post(new Runnable() { // from class: com.jingdong.common.sample.jshop.JShopSignNewActivity.2
            @Override // java.lang.Runnable
            public void run() {
                ToastUtils.showToastInCenter((Context) JShopSignNewActivity.this, (byte) 1, "\u5206\u4eab\u53d6\u6d88", 0);
            }
        });
    }

    @Override // com.jingdong.common.BaseActivity
    public void onShareComplete(String str) {
        Log.d(TAG, "shareChannel : " + str);
        try {
            if (this.activityRuleType == 0 && this.shared == 1) {
                Log.d(TAG, "add share");
                postSignShareRequest(str);
            } else {
                post(new Runnable() { // from class: com.jingdong.common.sample.jshop.JShopSignNewActivity.3
                    @Override // java.lang.Runnable
                    public void run() {
                        ToastUtils.showToastInCenter((Context) JShopSignNewActivity.this, (byte) 2, "\u5206\u4eab\u6210\u529f", 0);
                    }
                });
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.common.BaseActivity
    public void onShareError(String str) {
        post(new Runnable() { // from class: com.jingdong.common.sample.jshop.JShopSignNewActivity.4
            @Override // java.lang.Runnable
            public void run() {
                ToastUtils.showToastInCenter((Context) JShopSignNewActivity.this, (byte) 1, "\u5206\u4eab\u5931\u8d25", 0);
            }
        });
    }

    public void selectTabFromOutside(int i2) {
        setShareBtnShow(8);
        showTab(3);
    }
}
