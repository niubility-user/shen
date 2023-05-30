package com.jingdong.common.babelrn.view;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.fragment.app.FragmentTransaction;
import com.airbnb.deeplinkdispatch.DeepLink;
import com.facebook.react.ReactRootView;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.mylib.R;
import com.jingdong.cleanmvp.ui.BaseFragment;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.babelrn.entity.BabelNativeInfo;
import com.jingdong.common.babelrn.entity.BabelRNEntity;
import com.jingdong.common.babelrn.utils.BabelCheckNativeUtil;
import com.jingdong.common.babelrn.utils.BabelRNDownloadUtil;
import com.jingdong.common.babelrn.utils.BabelRNFragmentUtil;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.download.PluginListenerNew;
import com.jingdong.common.jdreactFramework.download.PluginUpdateInfo;
import com.jingdong.common.jdreactFramework.download.PluginVersion;
import com.jingdong.common.jdreactFramework.download.ReactNativeFileManager;
import com.jingdong.common.jdreactFramework.preload.JDReactModuleEntity;
import com.jingdong.common.jdreactFramework.utils.ReactModuleAvailabilityUtils;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.web.MKeyNames;
import com.jingdong.common.web.ui.CommonMFragment;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.jdsdk.utils.URLParamMap;
import java.io.File;
import java.net.URLDecoder;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class BabelRNFragment extends RNFragment implements BabelRNFragmentUtil.IBridge {
    public static final String DOGE = "doge";
    public static final String IS_TOPBAR_GONE = "isTopBarGone";
    private String appName;
    private int fId;
    protected String fragmentId;
    private int isDebug;
    private String jsBundleName;
    private BaseFragment mFragment;
    protected LinearLayout mReactRootViewHolder;
    protected View mTopView;
    private String moduleName;
    private String mtaActivityId;
    protected String parentActivityId;
    private String reactMd5;
    private String reactParams;
    private String reactTitle;
    private String reactUrl;
    private String url;
    protected boolean isTopBarGone = false;
    protected boolean isRNPage = true;

    /* JADX INFO: Access modifiers changed from: private */
    public void dealBabelRNEntity(BabelRNEntity babelRNEntity, Bundle bundle) {
        this.reactTitle = babelRNEntity.titleName;
        this.moduleName = babelRNEntity.moduleName;
        this.appName = babelRNEntity.appName;
        this.reactParams = babelRNEntity.params;
        this.jsBundleName = babelRNEntity.jsBundleName;
        this.reactUrl = babelRNEntity.jsUrl;
        this.reactMd5 = babelRNEntity.md5;
        this.force = babelRNEntity.forceRebuild();
        boolean isBuildIn = babelRNEntity.isBuildIn();
        int i2 = babelRNEntity.isDebug;
        this.isDebug = i2;
        if (i2 == 1 && babelRNEntity.debugHttpHost != null) {
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this.thisActivity.getApplicationContext()).edit();
            edit.putString("debug_http_host", babelRNEntity.debugHttpHost);
            edit.commit();
        }
        String str = babelRNEntity.activityId;
        this.mtaActivityId = str;
        if (TextUtils.isEmpty(str)) {
            this.mtaActivityId = this.parentActivityId;
        }
        if (!ReactModuleAvailabilityUtils.getModuleAvailability(this.moduleName)) {
            onReactLoadFail(false);
        } else {
            dealJDReactModuleEntity(bundle, isBuildIn);
        }
    }

    private void dealJDReactModuleEntity(Bundle bundle, boolean z) {
        Bundle bundle2 = new Bundle();
        putParams(bundle2);
        cloneData(bundle, bundle2);
        bundle2.putString("fragmentId", this.fragmentId);
        setJDReactModuleInfo(new JDReactModuleEntity(this.appName, this.moduleName, bundle2));
        if (this.isDebug == 1 && SharedPreferencesUtil.getSharedPreferences().getBoolean(JDReactConstant.JDREACT_DEVELOP_FLAG, false)) {
            this.thisActivity.post(new Runnable() { // from class: com.jingdong.common.babelrn.view.BabelRNFragment.2
                @Override // java.lang.Runnable
                public void run() {
                    BabelRNFragment.this.setupLayout();
                }
            });
        } else if (z) {
            String str = this.moduleName;
            PluginVersion pluginDir = ReactNativeFileManager.getPluginDir(getContext(), str);
            if (pluginDir != null && pluginDir.pluginDir != null) {
                Log.d("TAG", "The plugin path is " + pluginDir.pluginDir);
                bundle.putString("pluginPath", pluginDir.pluginDir + File.separator + str + ".jsbundle");
                if (!TextUtils.isEmpty(pluginDir.pluginVersion)) {
                    bundle.putString("version", pluginDir.pluginVersion);
                }
            }
            this.thisActivity.post(new Runnable() { // from class: com.jingdong.common.babelrn.view.BabelRNFragment.3
                @Override // java.lang.Runnable
                public void run() {
                    BabelRNFragment.this.setupLayout();
                }
            });
        } else {
            downloadBundle();
        }
    }

    private void downloadBundle() {
        if (!TextUtils.isEmpty(this.moduleName) && !TextUtils.isEmpty(this.appName) && !TextUtils.isEmpty(this.jsBundleName) && !TextUtils.isEmpty(this.reactUrl)) {
            BabelRNDownloadUtil.getBundlePath(this.thisActivity, this.reactUrl, this.moduleName, this.jsBundleName, this.mtaActivityId, new PluginListenerNew() { // from class: com.jingdong.common.babelrn.view.BabelRNFragment.4
                @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
                public void onDownloadProgressChanged(int i2) {
                }

                @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
                public void onFailure(String str, String str2, boolean z, String str3) {
                    BabelRNFragment.this.onReactLoadFail(true);
                }

                @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
                public void onFinish(PluginUpdateInfo pluginUpdateInfo) {
                    Bundle arguments = BabelRNFragment.this.getArguments();
                    if (arguments != null && pluginUpdateInfo != null) {
                        arguments.putString("pluginPath", pluginUpdateInfo.pluginUpdateName);
                    }
                    BabelRNFragment.this.thisActivity.post(new Runnable() { // from class: com.jingdong.common.babelrn.view.BabelRNFragment.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            BabelRNFragment.this.setupLayout();
                        }
                    });
                }
            });
            showProgressBarNow();
            return;
        }
        BabelRNDownloadUtil.reportBabelRnError("TTT_Param_Error", "some param error", this.reactUrl, "", this.mtaActivityId);
        onReactLoadFail(true);
    }

    private String getUrl(Bundle bundle) {
        String string = bundle.getString("url");
        if (string == null) {
            try {
                URLParamMap map = ((SerializableContainer) bundle.getSerializable("urlParamMap")).getMap();
                return map != null ? URLDecoder.decode(map.get((Object) RemoteMessageConst.TO), "utf-8") : string;
            } catch (Exception unused) {
                return bundle.getString("webUrl");
            }
        }
        return string;
    }

    private void put(Bundle bundle, String str, Object obj) {
        if (obj != null) {
            try {
                if (obj instanceof Boolean) {
                    bundle.putBoolean(str, ((Boolean) obj).booleanValue());
                } else if (obj instanceof String) {
                    bundle.putString(str, String.valueOf(obj));
                } else if (obj instanceof Byte) {
                    bundle.putByte(str, ((Byte) obj).byteValue());
                } else if (obj instanceof Float) {
                    bundle.putFloat(str, ((Float) obj).floatValue());
                } else if (obj instanceof Integer) {
                    bundle.putInt(str, ((Integer) obj).intValue());
                } else if (obj instanceof Short) {
                    bundle.putShort(str, ((Short) obj).shortValue());
                } else if (obj instanceof Double) {
                    bundle.putDouble(str, ((Double) obj).doubleValue());
                } else if (obj instanceof Long) {
                    bundle.putLong(str, ((Long) obj).longValue());
                } else {
                    bundle.putString(str, obj.toString());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void cloneData(Bundle bundle, Bundle bundle2) {
        if (bundle == null || bundle2 == null) {
            return;
        }
        try {
            for (String str : bundle.keySet()) {
                Object obj = bundle.get(str);
                if (!DOGE.equals(str) && !"splitbundle".equals(str) && !"urlParamMap".equals(str) && !LoginConstans.JUMP_DES.equals(str) && !"urlAction".equals(str) && !"isNative".equals(str) && !"jumpFrom".equals(str) && !BaseActivity.DISPOSEABLE_UNABLE_ANIM.equals(str) && !DeepLink.IS_DEEP_LINK.equals(str) && !"parentActivityId".equals(str)) {
                    put(bundle2, str, obj);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public BaseFragment createJDMView(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        CommonMFragment commonMFragment = new CommonMFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", str);
        bundle.putBoolean("showSubPage", true);
        bundle.putBoolean("isTopBarGone", this.isTopBarGone);
        bundle.putBoolean(MKeyNames.NEED_CHECK_NATIVE, false);
        if (!this.isTopBarGone) {
            bundle.putBoolean(MBaseKeyNames.KEY_ALWAYS_TRANSPARENT_STATUSBAR, this.thisActivity.isStatusBarTintEnable());
        }
        commonMFragment.setArguments(bundle);
        return commonMFragment;
    }

    @Override // com.jingdong.common.babelrn.utils.BabelRNFragmentUtil.IBridge
    public void enableStatusBarTint(boolean z) {
        if (this.mTopView == null || !this.thisActivity.isStatusBarTintEnable()) {
            return;
        }
        if (z) {
            this.mTopView.setLayoutParams(new LinearLayout.LayoutParams(-1, 0));
            return;
        }
        this.mTopView.setLayoutParams(new LinearLayout.LayoutParams(-1, UnStatusBarTintUtil.getStatusBarHeight((Activity) this.thisActivity)));
        this.mTopView.setBackgroundResource(R.color.status_bar_bg);
    }

    public BabelRNEntity getBabelRNEntity(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String string = bundle.getString(DOGE);
        if (!TextUtils.isEmpty(string)) {
            try {
                return (BabelRNEntity) JDJSON.parseObject(string, BabelRNEntity.class);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    @Override // com.jingdong.common.babelrn.view.RNFragment
    protected void getReactEntity() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            BabelRNEntity babelRNEntity = getBabelRNEntity(arguments);
            if (babelRNEntity != null) {
                dealBabelRNEntity(babelRNEntity, arguments);
                return;
            } else if (!TextUtils.isEmpty(this.url)) {
                BabelCheckNativeUtil.checkM2Native(getContext(), this.url, new BabelCheckNativeUtil.OnCheckNativeListener() { // from class: com.jingdong.common.babelrn.view.BabelRNFragment.1
                    @Override // com.jingdong.common.babelrn.utils.BabelCheckNativeUtil.OnCheckNativeListener
                    public void onFailure() {
                        BabelRNFragment.this.onReactLoadFail(false);
                    }

                    @Override // com.jingdong.common.babelrn.utils.BabelCheckNativeUtil.OnCheckNativeListener
                    public void onFinish(final BabelNativeInfo babelNativeInfo) {
                        if (babelNativeInfo.isPageRN()) {
                            if (!babelNativeInfo.babelRNEntity.isBuildIn() && !babelNativeInfo.babelRNEntity.forceToShowRN()) {
                                BabelRNEntity babelRNEntity2 = babelNativeInfo.babelRNEntity;
                                if (!BabelRNDownloadUtil.isBundlePathExists(babelRNEntity2.jsBundleName, babelRNEntity2.moduleName)) {
                                    BabelRNFragment.this.onReactLoadFail(false);
                                    return;
                                }
                            }
                            BabelRNFragment.this.thisActivity.post(new Runnable() { // from class: com.jingdong.common.babelrn.view.BabelRNFragment.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    BabelRNFragment babelRNFragment = BabelRNFragment.this;
                                    babelRNFragment.dealBabelRNEntity(babelNativeInfo.babelRNEntity, babelRNFragment.getArguments());
                                }
                            });
                            return;
                        }
                        BabelRNFragment.this.onReactLoadFail(false);
                    }
                });
                showProgressBarNow();
                return;
            }
        }
        BabelRNDownloadUtil.reportBabelRnError("TTT_Param_Error", "some param error", this.reactUrl, "", this.mtaActivityId);
        onReactLoadFail(true);
    }

    protected void init() {
        View inflate = ImageUtil.inflate(this.thisActivity, R.layout.babel_rn_fragment, (ViewGroup) null);
        this.mRootView.addView(inflate);
        this.mTopView = inflate.findViewById(R.id.top_view);
        this.mReactRootViewHolder = (LinearLayout) inflate.findViewById(R.id.babel_rn_layout);
        if (!this.thisActivity.isStatusBarTintEnable() || this.isTopBarGone) {
            return;
        }
        this.mTopView.setLayoutParams(new LinearLayout.LayoutParams(-1, UnStatusBarTintUtil.getStatusBarHeight((Activity) this.thisActivity)));
        this.mTopView.setBackgroundResource(R.color.status_bar_bg);
    }

    @Override // com.jingdong.common.babelrn.view.RNFragment
    protected void initView(ReactRootView reactRootView) {
        this.mReactRootViewHolder.addView(reactRootView, -1, -1);
    }

    @Override // com.jingdong.common.babelrn.view.RNFragment
    protected boolean isOpenLoadingView() {
        return true;
    }

    protected void notifyParentScrollY(int i2) {
    }

    @Override // com.jingdong.common.babelrn.view.RNFragment
    protected void onCreateViews(LayoutInflater layoutInflater) {
        Bundle arguments = getArguments();
        int hashCode = hashCode();
        this.fId = hashCode;
        this.fragmentId = String.valueOf(hashCode);
        this.mRootView.setId(this.fId);
        if (arguments != null) {
            String string = arguments.getString("parentActivityId");
            this.parentActivityId = string;
            if (TextUtils.isEmpty(string)) {
                this.parentActivityId = arguments.getString("activityId");
            }
            this.isTopBarGone = arguments.getBoolean("isTopBarGone");
            this.url = getUrl(arguments);
        }
        init();
    }

    @Override // com.jingdong.common.babelrn.view.RNFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        BabelRNDownloadUtil.clearCheck();
        BabelRNFragmentUtil.getInstance().onDestory();
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            return;
        }
        realResume();
    }

    @Override // com.jingdong.common.babelrn.view.RNFragment, com.jingdong.cleanmvp.ui.BaseFragment
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        BaseFragment baseFragment;
        if (!this.isRNPage && (baseFragment = this.mFragment) != null && !baseFragment.isRemoving()) {
            return this.mFragment.onKeyDown(i2, keyEvent);
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // com.jingdong.common.babelrn.view.RNFragment
    protected void onReactLoadFail(long j2) {
        BabelRNDownloadUtil.sendTimeData(this.thisActivity, "BabelRNFragment", "rn_ttt", this.reactUrl, j2, System.currentTimeMillis(), "fail");
        BabelRNDownloadUtil.reportBabelRnError("TTT_Render_Error", "load react error", this.reactUrl, "", this.mtaActivityId);
        onReactLoadFail(true);
    }

    @Override // com.jingdong.common.babelrn.view.RNFragment
    protected void onReactLoadSuc(long j2) {
        BabelRNDownloadUtil.sendTimeData(this.thisActivity, "BabelRNFragment", "rn_ttt", this.reactUrl, j2, System.currentTimeMillis(), CartConstant.KEY_CART_TEXTINFO_FINISH);
    }

    @Override // com.jingdong.common.babelrn.view.RNFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (!getUserVisibleHint() || isHidden()) {
            return;
        }
        realResume();
    }

    public void putParams(Bundle bundle) {
        if (bundle == null || TextUtils.isEmpty(this.reactParams)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.reactParams);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                put(bundle, next, jSONObject.opt(next));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    protected void realResume() {
        if (this.isRNPage) {
            notifyParentScrollY(MBaseKeyNames.MAX_SCROLL_HEIGHT_ALPHA);
            BabelRNFragmentUtil.getInstance().onResume(this);
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z) {
            realResume();
        }
    }

    @Override // com.jingdong.common.babelrn.view.RNFragment
    protected void onReactLoadFail(final boolean z) {
        this.thisActivity.post(new Runnable() { // from class: com.jingdong.common.babelrn.view.BabelRNFragment.5
            @Override // java.lang.Runnable
            public void run() {
                if (TextUtils.isEmpty(BabelRNFragment.this.url)) {
                    return;
                }
                BabelRNFragment.this.mRootView.removeAllViews();
                BabelRNFragment babelRNFragment = BabelRNFragment.this;
                babelRNFragment.mFragment = babelRNFragment.createJDMView(babelRNFragment.url);
                if (BabelRNFragment.this.mFragment != null) {
                    BabelRNFragment babelRNFragment2 = BabelRNFragment.this;
                    babelRNFragment2.isRNPage = false;
                    babelRNFragment2.notifyParentScrollY(0);
                    FragmentTransaction beginTransaction = BabelRNFragment.this.getChildFragmentManager().beginTransaction();
                    beginTransaction.replace(BabelRNFragment.this.fId, BabelRNFragment.this.mFragment);
                    beginTransaction.commitAllowingStateLoss();
                }
                if (!z || TextUtils.isEmpty(BabelRNFragment.this.jsBundleName)) {
                    return;
                }
                BabelRNDownloadUtil.delBundle(BabelRNFragment.this.jsBundleName);
            }
        });
        dismissProgressBar();
    }
}
