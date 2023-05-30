package com.jingdong.app.mall.bundle.familyhelper;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.mall.bundle.familyhelper.adapter.MemberVOAdapter;
import com.jingdong.app.mall.bundle.familyhelper.entity.FamilyCatWindowDTO;
import com.jingdong.app.mall.bundle.familyhelper.entity.FamilyRelationDTO;
import com.jingdong.app.mall.bundle.familyhelper.util.FamilyHelperConstant;
import com.jingdong.app.mall.bundle.familyhelper.util.FamilyHelperNetUtil;
import com.jingdong.app.mall.bundle.familyhelper.util.JumpUtil;
import com.jingdong.app.mall.bundle.familyhelper.util.StringUtil;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.log.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes18.dex */
public class FamilyHelperActivity extends BaseActivity {
    private static final String TAG = "FamilyHelperActivity";
    private String channelId;
    private AtomicBoolean closeAnimationAlreadyStart = new AtomicBoolean(false);
    private ImageView closeBt;
    private LinearLayout customerService;
    private View emptyView;
    private FamilyCatWindowDTO familyCatWindowDTO;
    private List<FamilyRelationDTO> familyRelationDtoList;
    private LinearLayout invite;
    private JDJSONObject jsonParam;
    private FamilyHelperActivity mActivity;
    private double mFinishMillis;
    private RelativeLayout mRootView;
    private RecyclerView memberList;
    private MemberVOAdapter memberVoAdapter;
    private View panelView;
    private SimpleDraweeView serviceIcon;
    private TextView serviceText;

    private boolean closePanelAnimation(final boolean z) {
        RelativeLayout relativeLayout;
        if (this.closeAnimationAlreadyStart.getAndSet(false) || (relativeLayout = this.mRootView) == null || relativeLayout.getChildCount() != 1) {
            return false;
        }
        final TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(200L);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.jingdong.app.mall.bundle.familyhelper.FamilyHelperActivity.7
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                FamilyHelperActivity.this.mRootView.removeAllViews();
                FamilyHelperActivity.this.closeAnimationAlreadyStart.set(false);
                if (z) {
                    FamilyHelperActivity.super.finish();
                    FamilyHelperActivity.this.overridePendingTransition(0, 0);
                }
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                FamilyHelperActivity.this.closeAnimationAlreadyStart.set(true);
            }
        });
        post(new Runnable() { // from class: com.jingdong.app.mall.bundle.familyhelper.FamilyHelperActivity.8
            @Override // java.lang.Runnable
            public void run() {
                FamilyHelperActivity.this.mRootView.getChildAt(0).startAnimation(translateAnimation);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getFamilyMember() {
        if (NetUtils.isNetworkAvailable()) {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("channelId", (Object) this.channelId);
            FamilyHelperNetUtil.sendHttpRequest(null, FamilyHelperConstant.GET_CHAT_WINDOW_CONFIG, jDJSONObject, true, new HttpGroup.OnCommonListener() { // from class: com.jingdong.app.mall.bundle.familyhelper.FamilyHelperActivity.6
                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(final HttpResponse httpResponse) {
                    FamilyHelperActivity.this.mActivity.post(new Runnable() { // from class: com.jingdong.app.mall.bundle.familyhelper.FamilyHelperActivity.6.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                                Log.d(FamilyHelperActivity.TAG, "--> getCatWindowConfig , json = " + fastJsonObject);
                                if (fastJsonObject != null) {
                                    JDJSONObject optJSONObject = fastJsonObject.optJSONObject("data");
                                    Log.d(FamilyHelperActivity.TAG, "--> getCatWindowConfig , data = " + optJSONObject);
                                    if (optJSONObject != null) {
                                        JDJSONObject optJSONObject2 = optJSONObject.optJSONObject("familyCatWindowDTO");
                                        if (optJSONObject2 != null) {
                                            FamilyHelperActivity.this.familyCatWindowDTO = (FamilyCatWindowDTO) JDJSON.parseObject(optJSONObject2.toString(), FamilyCatWindowDTO.class);
                                            Log.d(FamilyHelperActivity.TAG, "--> familyCatWindowDTO , data = " + FamilyHelperActivity.this.familyCatWindowDTO);
                                        }
                                        JDJSONArray optJSONArray = optJSONObject.optJSONArray(ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID);
                                        Log.d(FamilyHelperActivity.TAG, "--> familySunSetCatDto , jdjsonArray = " + optJSONArray);
                                        if (optJSONArray != null && optJSONArray.size() > 0) {
                                            FamilyHelperActivity.this.familyRelationDtoList = new ArrayList();
                                            for (int i2 = 0; i2 < optJSONArray.size(); i2++) {
                                                FamilyRelationDTO familyRelationDTO = new FamilyRelationDTO();
                                                familyRelationDTO.imgUrl = optJSONArray.getJSONObject(i2).optString("imgUrl");
                                                familyRelationDTO.labelName = optJSONArray.getJSONObject(i2).optString(CustomThemeConstance.NAVI_LABEL_NAME);
                                                familyRelationDTO.nickname = optJSONArray.getJSONObject(i2).optString("nickname");
                                                familyRelationDTO.pin = optJSONArray.getJSONObject(i2).optString("pin");
                                                familyRelationDTO.unReadNum = optJSONArray.getJSONObject(i2).optInt("unReadNum");
                                                FamilyHelperActivity.this.familyRelationDtoList.add(familyRelationDTO);
                                            }
                                            Log.d(FamilyHelperActivity.TAG, "--> familySunSetCatDto , familyRelationDtoList = " + FamilyHelperActivity.this.familyRelationDtoList);
                                            if (FamilyHelperActivity.this.familyRelationDtoList != null && FamilyHelperActivity.this.familyRelationDtoList.size() > 0) {
                                                if (FamilyHelperActivity.this.familyRelationDtoList.size() < 10) {
                                                    FamilyRelationDTO familyRelationDTO2 = new FamilyRelationDTO();
                                                    familyRelationDTO2.setInvite(true);
                                                    FamilyHelperActivity.this.familyRelationDtoList.add(0, familyRelationDTO2);
                                                }
                                                FamilyHelperActivity.this.initMemberView();
                                                FamilyHelperActivity familyHelperActivity = FamilyHelperActivity.this;
                                                familyHelperActivity.memberVoAdapter = new MemberVOAdapter(familyHelperActivity.familyRelationDtoList, FamilyHelperActivity.this.mActivity, FamilyHelperActivity.this.jsonParam, FamilyHelperActivity.this.channelId);
                                                FamilyHelperActivity.this.memberList.setAdapter(FamilyHelperActivity.this.memberVoAdapter);
                                                return;
                                            }
                                        }
                                    }
                                }
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                            FamilyHelperActivity.this.initInviteView();
                        }
                    });
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    FamilyHelperActivity.this.mActivity.post(new Runnable() { // from class: com.jingdong.app.mall.bundle.familyhelper.FamilyHelperActivity.6.2
                        @Override // java.lang.Runnable
                        public void run() {
                            FamilyHelperActivity.this.initInviteView();
                        }
                    });
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
                public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                }
            });
            return;
        }
        initInviteView();
    }

    private void initCommonView() {
        getWindow().getDecorView().setBackgroundResource(R.color.colorMainBg);
        View inflate = getLayoutInflater().inflate(R.layout.layout_family_helper_panel, (ViewGroup) null);
        this.panelView = inflate;
        this.invite = (LinearLayout) inflate.findViewById(R.id.invite);
        this.memberList = (RecyclerView) this.panelView.findViewById(R.id.memberList);
        ImageView imageView = (ImageView) this.panelView.findViewById(R.id.closeBt);
        this.closeBt = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.familyhelper.FamilyHelperActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDMtaUtils.sendClickDataWithExt(FamilyHelperActivity.this.mActivity, "family_elder_popupClose", "", "", "family_elder", "", "", "", "{\"source\":\"" + FamilyHelperActivity.this.channelId + "\"}", null);
                FamilyHelperActivity.this.finish();
            }
        });
        this.customerService = (LinearLayout) this.panelView.findViewById(R.id.customerService);
        this.serviceIcon = (SimpleDraweeView) this.panelView.findViewById(R.id.serviceIcon);
        this.serviceText = (TextView) this.panelView.findViewById(R.id.serviceText);
        FamilyCatWindowDTO familyCatWindowDTO = this.familyCatWindowDTO;
        if (familyCatWindowDTO != null && !StringUtil.isEmpty(familyCatWindowDTO.url) && !StringUtil.isEmpty(this.familyCatWindowDTO.text)) {
            this.serviceText.setText(this.familyCatWindowDTO.text);
            JDImageLoader.display(StringUtil.verifyUri(this.familyCatWindowDTO.imgUrl, ""), this.serviceIcon, new JDDisplayImageOptions(), (ImageRequestListener<ImageInfo>) null);
            this.customerService.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.familyhelper.FamilyHelperActivity.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    JDMtaUtils.sendClickDataWithExt(FamilyHelperActivity.this.mActivity, "family_elder_popupConfig", "", "", "family_elder", "", "", "", "{\"source\":\"" + FamilyHelperActivity.this.channelId + "\"}", null);
                    JumpUtil.jumpToCommon(FamilyHelperActivity.this.mActivity, FamilyHelperActivity.this.familyCatWindowDTO.url);
                }
            });
            this.customerService.setVisibility(0);
            return;
        }
        this.customerService.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initInviteView() {
        initCommonView();
        RecyclerView recyclerView = this.memberList;
        if (recyclerView != null) {
            recyclerView.setVisibility(8);
        }
        LinearLayout linearLayout = this.invite;
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
            this.invite.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.familyhelper.FamilyHelperActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    JDMtaUtils.sendClickDataWithExt(FamilyHelperActivity.this.mActivity, "family_elder_popupInviteOpen", "", "", "family_elder", "", "", "", "{\"source\":\"" + FamilyHelperActivity.this.channelId + "\"}", null);
                    JumpUtil.jumpToJDReactMyFamily(FamilyHelperActivity.this.mActivity, FamilyHelperActivity.this.channelId, FamilyHelperActivity.this.jsonParam);
                }
            });
        }
        showPanelAnimation(this.panelView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initMemberView() {
        initCommonView();
        LinearLayout linearLayout = this.invite;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        RecyclerView recyclerView = this.memberList;
        if (recyclerView != null) {
            recyclerView.setVisibility(0);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(0);
            this.memberList.setLayoutManager(linearLayoutManager);
        }
        showPanelAnimation(this.panelView);
    }

    private void showPanelAnimation(View view) {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(200L);
        this.mRootView.addView(view);
        view.startAnimation(translateAnimation);
    }

    @Override // com.jingdong.common.BaseActivity, android.app.Activity, com.jingdong.common.frame.IMyActivity
    public void finish() {
        if (this.mFinishMillis + 250.0d <= System.currentTimeMillis()) {
            this.mFinishMillis = System.currentTimeMillis();
            if (closePanelAnimation(true)) {
                return;
            }
            super.finish();
            overridePendingTransition(0, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.needSetOrientation = false;
        super.onCreate(bundle);
        this.statusBarTransparentEnable = true;
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_family_helper);
        View findViewById = findViewById(R.id.emptyView);
        this.emptyView = findViewById;
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.familyhelper.FamilyHelperActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FamilyHelperActivity.this.finish();
            }
        });
        UnStatusBarTintUtil.setStatusBarLightMode(this);
        Log.d(TAG, "intent:" + getIntent().getExtras());
        this.channelId = getIntent().getStringExtra("channelId");
        String stringExtra = getIntent().getStringExtra("jsonParam");
        if (StringUtil.isEmpty(this.channelId)) {
            this.channelId = "FamilyHelper";
        }
        if (!StringUtil.isEmpty(stringExtra)) {
            Log.d(TAG, "jsonStr:" + stringExtra);
            try {
                this.jsonParam = JDJSON.parseObject(stringExtra);
            } catch (Exception e2) {
                Log.d(TAG, e2.getMessage());
            }
        }
        if (this.jsonParam == null) {
            this.jsonParam = new JDJSONObject();
        }
        this.mActivity = this;
        this.mRootView = (RelativeLayout) findViewById(R.id.mRootView);
        if (!LoginUserBase.hasLogin()) {
            LoginUserHelper.getInstance().executeLoginRunnable(this, new Runnable() { // from class: com.jingdong.app.mall.bundle.familyhelper.FamilyHelperActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    FamilyHelperActivity.this.getFamilyMember();
                }
            });
        } else {
            getFamilyMember();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        JDMtaUtils.sendExposureDataWithExt(this.mActivity, "family_elder_quickChatExpo", "", IMediaPlayer.OnNativeInvokeListener.ARG_FAMILIY, "", "", "{\"source\":\"" + this.channelId + "\"}", null);
    }
}
