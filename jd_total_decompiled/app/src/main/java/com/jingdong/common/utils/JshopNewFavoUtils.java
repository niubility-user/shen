package com.jingdong.common.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.XView.IXView;
import com.jingdong.common.XView.XViewCallBack;
import com.jingdong.common.XView.XViewEntity;
import com.jingdong.common.XView.XViewHelper;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.entity.Product;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.jingdong.sdk.jshopsdk.R;
import com.jingdong.sdk.jshopsdk.common.favo.JShopFavStatusEvent;
import com.jingdong.sdk.jshopsdk.common.favo.JshopFavoInfoListener;
import com.jingdong.sdk.jshopsdk.common.favo.JshopFavoListener;
import com.jingdong.sdk.log.Log;
import com.jingdong.union.common.config.UnionConstants;
import de.greenrobot.event.EventBus;

/* loaded from: classes6.dex */
public class JshopNewFavoUtils {
    public static final int FAVO_JSHOP_ALL = -1;
    public static final int FAVO_JSHOP_MAIN = 0;
    public static final String FOLLOW_GIFT_HAVE = "FOLLD";
    public static final String FOLLOW_GIFT_NO = "NFOLL";
    public static final int FOLLOW_HAVE = 1001;
    public static final int FOLLOW_SUCCESS = 1000;
    public static final String HAS_CONCERNED = "F0402";
    public static final String MAX_VAL = "F0410";
    public static final String OPT_ERR = "F0409";
    public static final String OPT_SUCCESS = "F10000";
    public static final String PARAM_ERR = "F10001";
    public static final String SOURCE_RPC_ACTIVITY_DETAIL = "shop_app_activity_detail";
    public static final String SOURCE_RPC_CUSTOM_CENTER_COMERGIFT = "shop_app_customercenter_comergift";
    public static final String SOURCE_RPC_GOOD_SHOP_FOLLOW = "shop_app_goodshop_follow";
    public static final String SOURCE_RPC_HOME_FOLLOW = "shop_app_home_follow";
    public static final String SOURCE_RPC_MY_FOLLOW_SHOP = "shop_app_myfollows_shop";
    public static final String SOURCE_RPC_SHOPDETAIL_FOLLOW = "shop_app_shopdetail_follow";
    public static final String SOURCE_RPC_SIGN_UNFOLLOW = "shop_app_sign_unfollow";
    private static String TAG = "JshopFavoUtils";
    public static final String TIPS = JdSdk.getInstance().getApplicationContext().getString(R.string.jshop_followed);
    public static final String UNLOGIN = "F10002";
    private boolean isShowToast;
    private BaseActivity mActivity;
    public int mFollowCode;
    public String mFollowGiftOptCode;
    public IXView mIXView;
    private String refer;
    private boolean showAnimationToast;
    private String sourceRpc;

    public JshopNewFavoUtils(BaseActivity baseActivity) {
        this.mFollowCode = -1;
        this.mFollowGiftOptCode = "";
        this.isShowToast = true;
        this.showAnimationToast = false;
        this.mActivity = baseActivity;
    }

    public static void addProductFavorite(Product product, final BaseActivity baseActivity) {
        if (product == null) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("addFavorite");
        httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.PERSONAL_HOST));
        httpSetting.putJsonParam("wareId", "" + product.getId());
        httpSetting.putJsonParam("isNewText", Boolean.TRUE);
        httpSetting.putJsonParam("pin", LoginUserBase.getLoginUserName());
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.utils.JshopNewFavoUtils.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                Boolean booleanOrNull = httpResponse.getJSONObject().getBooleanOrNull("flag");
                if (booleanOrNull == null || !booleanOrNull.booleanValue()) {
                    return;
                }
                ToastUtils.shortToast(BaseActivity.this, JdSdk.getInstance().getApplicationContext().getString(R.string.jshop_product_follow_success));
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        httpSetting.setNotifyUser(true);
        baseActivity.addHttpGroupWithNPSSetting(httpSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFollowToast(String str, boolean z) {
        if (this.isShowToast || !z) {
            if (z) {
                if (this.showAnimationToast) {
                    return;
                }
                ToastUtils.showToastInCenter((Context) this.mActivity, (byte) 2, str, 0);
                return;
            }
            ToastUtils.showToastInCenter((Context) this.mActivity, (byte) 1, str, 0);
        }
    }

    public void getFavoStatus(View view, boolean z, String str, JshopFavoListener jshopFavoListener) {
        Log.d(TAG, "getFavoStatus");
        getFavoStatus(view, z, str, false, jshopFavoListener);
    }

    public String getRefer() {
        return this.refer;
    }

    public String getSourceRpc() {
        return this.sourceRpc;
    }

    public void setRefer(String str) {
        this.refer = str;
    }

    public void setShowAnimationToast(boolean z) {
        this.showAnimationToast = z;
    }

    public void setShowToast(boolean z) {
        this.isShowToast = z;
    }

    public void setSourceRpc(String str) {
        this.sourceRpc = str;
    }

    public void getFavoStatus(View view, String str, JshopFavoListener jshopFavoListener, XViewCallBack xViewCallBack) {
        Log.d(TAG, "getFavoStatus");
        getFavoStatus(view, true, true, str, false, jshopFavoListener, xViewCallBack, -1);
    }

    public void getFavoStatus(View view, String str, boolean z, boolean z2, JshopFavoListener jshopFavoListener, XViewCallBack xViewCallBack) {
        Log.d(TAG, "getFavoStatus");
        getFavoStatus(view, z, z2, str, false, jshopFavoListener, xViewCallBack, -1);
    }

    public JshopNewFavoUtils(BaseActivity baseActivity, boolean z) {
        this(baseActivity, z, true);
    }

    public void getFavoStatus(View view, String str, boolean z, boolean z2, boolean z3, JshopFavoListener jshopFavoListener, XViewCallBack xViewCallBack) {
        Log.d(TAG, "getFavoStatus");
        getFavoStatus(view, z, z2, str, z3, jshopFavoListener, xViewCallBack, -1);
    }

    public JshopNewFavoUtils(BaseActivity baseActivity, boolean z, boolean z2) {
        this(baseActivity, z, z2, "", "");
    }

    public JshopNewFavoUtils(BaseActivity baseActivity, boolean z, boolean z2, String str, String str2) {
        this.mFollowCode = -1;
        this.mFollowGiftOptCode = "";
        this.isShowToast = true;
        this.showAnimationToast = false;
        this.mActivity = baseActivity;
        this.showAnimationToast = z;
        this.isShowToast = z2;
        this.sourceRpc = str;
        this.refer = str2;
    }

    public void getFavoStatus(View view, boolean z, String str, boolean z2, JshopFavoListener jshopFavoListener) {
        getFavoStatus(view, z, false, str, z2, jshopFavoListener, null, -1);
    }

    public void getFavoStatus(View view, String str, boolean z, boolean z2, JshopFavoListener jshopFavoListener, XViewCallBack xViewCallBack, int i2) {
        Log.d(TAG, "getFavoStatus");
        getFavoStatus(view, z, z2, str, false, jshopFavoListener, xViewCallBack, i2);
    }

    private void getFavoStatus(final View view, final boolean z, final boolean z2, final String str, boolean z3, final JshopFavoListener jshopFavoListener, final XViewCallBack xViewCallBack, final int i2) {
        if (view != null) {
            view.setEnabled(false);
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(Configuration.getJshopHost());
        httpSetting.setFunctionId("followShop");
        httpSetting.putJsonParam("shopId", str);
        if (z2) {
            httpSetting.putJsonParam("follow", Boolean.TRUE);
            httpSetting.putJsonParam("award", DYConstants.DY_TRUE);
        } else {
            httpSetting.putJsonParam("follow", Boolean.valueOf(z));
        }
        if (!TextUtils.isEmpty(this.sourceRpc)) {
            httpSetting.putJsonParam("sourceRpc", this.sourceRpc);
        }
        if (!TextUtils.isEmpty(this.refer)) {
            httpSetting.putJsonParam(UnionConstants.BUNDLE_REFER, this.refer);
        }
        httpSetting.setNotifyUser(false);
        httpSetting.setUseFastJsonParser(true);
        if (z3) {
            httpSetting.setEffect(1);
        } else {
            httpSetting.setEffect(0);
        }
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.utils.JshopNewFavoUtils.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                Log.d(JshopNewFavoUtils.TAG, "=======onEnd=============");
                final JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                final String str2 = "";
                if (z2) {
                    JshopNewFavoUtils jshopNewFavoUtils = JshopNewFavoUtils.this;
                    jshopNewFavoUtils.mFollowGiftOptCode = "";
                    jshopNewFavoUtils.mActivity.post(new Runnable() { // from class: com.jingdong.common.utils.JshopNewFavoUtils.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            JDJSONObject jDJSONObject = fastJsonObject;
                            if (jDJSONObject != null) {
                                if (jDJSONObject.optBoolean("follow")) {
                                    String optString = fastJsonObject.optString("awardUrl");
                                    if (!TextUtils.isEmpty(optString)) {
                                        View findViewById = JshopNewFavoUtils.this.mActivity.getRootFrameLayout().findViewById(16908290);
                                        if (findViewById instanceof ViewGroup) {
                                            XViewEntity xViewEntity = new XViewEntity();
                                            xViewEntity.url = optString;
                                            xViewEntity.isIntercepted = true;
                                            xViewEntity.needCloseButton = true;
                                            AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                                            JshopNewFavoUtils jshopNewFavoUtils2 = JshopNewFavoUtils.this;
                                            IXView iXView = jshopNewFavoUtils2.mIXView;
                                            if (iXView == null) {
                                                jshopNewFavoUtils2.mIXView = XViewHelper.createXView(jshopNewFavoUtils2.mActivity, (ViewGroup) findViewById, JshopNewFavoUtils.this.mActivity.getClass().getSimpleName(), xViewEntity, xViewCallBack);
                                            } else {
                                                iXView.configXView((ViewGroup) findViewById, xViewEntity, xViewCallBack);
                                            }
                                            JshopNewFavoUtils.this.mIXView.autoShowXView();
                                        }
                                    } else {
                                        ToastUtils.showToastInCenter((Context) JshopNewFavoUtils.this.mActivity, (byte) 2, JshopNewFavoUtils.this.mActivity.getString(R.string.jshop_follow_gift_success), 0);
                                    }
                                    JshopFavoListener jshopFavoListener2 = jshopFavoListener;
                                    if (jshopFavoListener2 != null) {
                                        if (jshopFavoListener2 instanceof JshopFavoInfoListener) {
                                            ((JshopFavoInfoListener) jshopFavoListener2).onFavoInfo(fastJsonObject);
                                        }
                                        jshopFavoListener.onFavoStatus(true);
                                        EventBus.getDefault().post(new JShopFavStatusEvent(str, true));
                                    }
                                } else {
                                    JshopNewFavoUtils.this.mFollowGiftOptCode = fastJsonObject.optString("optCode");
                                    String optString2 = fastJsonObject.optString("msg");
                                    if (TextUtils.isEmpty(optString2)) {
                                        optString2 = JshopNewFavoUtils.this.mActivity.getString(R.string.jshop_follow_gift_fail);
                                    }
                                    JshopNewFavoUtils.this.showFollowToast(optString2, false);
                                    JshopFavoListener jshopFavoListener3 = jshopFavoListener;
                                    if (jshopFavoListener3 != null) {
                                        if (jshopFavoListener3 instanceof JshopFavoInfoListener) {
                                            ((JshopFavoInfoListener) jshopFavoListener3).onFavoInfo(fastJsonObject);
                                        }
                                        jshopFavoListener.onFavoStatus(false);
                                        EventBus.getDefault().post(new JShopFavStatusEvent(str, false));
                                    }
                                }
                            } else {
                                JshopNewFavoUtils jshopNewFavoUtils3 = JshopNewFavoUtils.this;
                                jshopNewFavoUtils3.showFollowToast(jshopNewFavoUtils3.mActivity.getString(R.string.jshop_follow_gift_fail), false);
                                JshopFavoListener jshopFavoListener4 = jshopFavoListener;
                                if (jshopFavoListener4 != null) {
                                    if (jshopFavoListener4 instanceof JshopFavoInfoListener) {
                                        ((JshopFavoInfoListener) jshopFavoListener4).onFavoInfo(fastJsonObject);
                                    }
                                    jshopFavoListener.onFavoStatus(false);
                                    EventBus.getDefault().post(new JShopFavStatusEvent(str, false));
                                }
                            }
                            View view2 = view;
                            if (view2 != null) {
                                view2.setEnabled(true);
                            }
                        }
                    });
                    return;
                }
                JshopNewFavoUtils.this.mFollowCode = -1;
                String optString = fastJsonObject.optString("optCode");
                String optString2 = fastJsonObject.optString("msg");
                fastJsonObject.optString("subMsg");
                String optString3 = fastJsonObject.optString(CartConstant.KEY_YANBAO_ACTIVITY_FLAG);
                Object obj = fastJsonObject.get("activityInfo");
                fastJsonObject.optInt(XView2Constants.STATE);
                if ("xview".equals(optString3) && i2 == 0) {
                    try {
                        str2 = (String) obj;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                final String string = TextUtils.isEmpty(optString2) ? JdSdk.getInstance().getApplicationContext().getString(R.string.jshop_request_exception) : optString2;
                if (!"F10000".equals(optString) && !JshopNewFavoUtils.TIPS.equals(optString2) && !"F0402".equals(optString)) {
                    if ("F10001".equals(optString)) {
                        JshopFavoListener jshopFavoListener2 = jshopFavoListener;
                        if (jshopFavoListener2 != null) {
                            if (jshopFavoListener2 instanceof JshopFavoInfoListener) {
                                ((JshopFavoInfoListener) jshopFavoListener2).onFavoInfo(fastJsonObject);
                            }
                            jshopFavoListener.onError();
                        }
                        JshopNewFavoUtils.this.mActivity.post(new Runnable() { // from class: com.jingdong.common.utils.JshopNewFavoUtils.1.5
                            @Override // java.lang.Runnable
                            public void run() {
                                JshopNewFavoUtils.this.showFollowToast(string, false);
                            }
                        });
                    } else if ("F0410".equals(optString)) {
                        JshopFavoListener jshopFavoListener3 = jshopFavoListener;
                        if (jshopFavoListener3 != null) {
                            if (jshopFavoListener3 instanceof JshopFavoInfoListener) {
                                ((JshopFavoInfoListener) jshopFavoListener3).onFavoInfo(fastJsonObject);
                            }
                            jshopFavoListener.onError();
                        }
                        JshopNewFavoUtils.this.mActivity.post(new Runnable() { // from class: com.jingdong.common.utils.JshopNewFavoUtils.1.6
                            @Override // java.lang.Runnable
                            public void run() {
                                JshopNewFavoUtils.this.showFollowToast(string, false);
                            }
                        });
                    } else if (!"F0409".equals(optString)) {
                        JshopNewFavoUtils.this.mActivity.post(new Runnable() { // from class: com.jingdong.common.utils.JshopNewFavoUtils.1.8
                            @Override // java.lang.Runnable
                            public void run() {
                                JshopNewFavoUtils.this.showFollowToast(string, false);
                            }
                        });
                    } else {
                        JshopFavoListener jshopFavoListener4 = jshopFavoListener;
                        if (jshopFavoListener4 != null) {
                            if (jshopFavoListener4 instanceof JshopFavoInfoListener) {
                                ((JshopFavoInfoListener) jshopFavoListener4).onFavoInfo(fastJsonObject);
                            }
                            jshopFavoListener.onError();
                        }
                        JshopNewFavoUtils.this.mActivity.post(new Runnable() { // from class: com.jingdong.common.utils.JshopNewFavoUtils.1.7
                            @Override // java.lang.Runnable
                            public void run() {
                                JshopNewFavoUtils.this.showFollowToast(string, false);
                            }
                        });
                    }
                } else {
                    if (z) {
                        if (!JshopNewFavoUtils.TIPS.equals(optString2) && !"F0402".equals(optString)) {
                            JshopNewFavoUtils.this.mFollowCode = 1000;
                        } else {
                            JshopNewFavoUtils.this.mFollowCode = 1001;
                        }
                    }
                    JshopFavoListener jshopFavoListener5 = jshopFavoListener;
                    if (jshopFavoListener5 != null) {
                        if (jshopFavoListener5 instanceof JshopFavoInfoListener) {
                            ((JshopFavoInfoListener) jshopFavoListener5).onFavoInfo(fastJsonObject);
                        }
                        jshopFavoListener.onFavoStatus(z);
                        EventBus.getDefault().post(new JShopFavStatusEvent(str, z));
                    }
                    JshopNewFavoUtils.this.mActivity.post(new Runnable
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x016c: INVOKE 
                          (wrap: com.jingdong.common.BaseActivity : 0x0163: IGET 
                          (wrap: com.jingdong.common.utils.JshopNewFavoUtils : 0x0161: IGET (r7v0 'this' com.jingdong.common.utils.JshopNewFavoUtils$1 A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:47) com.jingdong.common.utils.JshopNewFavoUtils.1.this$0 com.jingdong.common.utils.JshopNewFavoUtils)
                         A[MD:(com.jingdong.common.utils.JshopNewFavoUtils):com.jingdong.common.BaseActivity (m), WRAPPED] (LINE:1) com.jingdong.common.utils.JshopNewFavoUtils.mActivity com.jingdong.common.BaseActivity)
                          (wrap: java.lang.Runnable : 0x0169: CONSTRUCTOR 
                          (r7v0 'this' com.jingdong.common.utils.JshopNewFavoUtils$1 A[IMMUTABLE_TYPE, THIS])
                          (r1v2 'str2' java.lang.String A[DONT_INLINE])
                          (r4v5 'string' java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                          (r3 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                         A[MD:(com.jingdong.common.utils.JshopNewFavoUtils$1, java.lang.String, java.lang.String, java.lang.String):void (m), WRAPPED] call: com.jingdong.common.utils.JshopNewFavoUtils.1.3.<init>(com.jingdong.common.utils.JshopNewFavoUtils$1, java.lang.String, java.lang.String, java.lang.String):void type: CONSTRUCTOR)
                         type: VIRTUAL call: com.jingdong.common.BaseActivity.post(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (m)] (LINE:1) in method: com.jingdong.common.utils.JshopNewFavoUtils.1.onEnd(com.jingdong.jdsdk.network.toolbox.HttpResponse):void, file: classes6.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:137)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
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
                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                        	... 25 more
                        */
                    /*
                        Method dump skipped, instructions count: 398
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.JshopNewFavoUtils.AnonymousClass1.onEnd(com.jingdong.jdsdk.network.toolbox.HttpResponse):void");
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(final HttpError httpError) {
                    Log.d(JshopNewFavoUtils.TAG, "======onerror=====");
                    JshopNewFavoUtils.this.mActivity.post(new Runnable() { // from class: com.jingdong.common.utils.JshopNewFavoUtils.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            View view2 = view;
                            if (view2 != null) {
                                view2.setEnabled(true);
                            }
                            if (!z2) {
                                JshopNewFavoUtils.this.showFollowToast(JdSdk.getInstance().getApplicationContext().getString(R.string.jshop_request_exception), false);
                                return;
                            }
                            HttpError httpError2 = httpError;
                            String optString = (httpError2 == null || httpError2.getHttpResponse() == null || httpError.getHttpResponse().getJSONObject() == null) ? "" : httpError.getHttpResponse().getJSONObject().optString("msg");
                            if (TextUtils.isEmpty(optString)) {
                                optString = JshopNewFavoUtils.this.mActivity.getString(R.string.jshop_follow_gift_fail);
                            }
                            JshopNewFavoUtils.this.showFollowToast(optString, false);
                        }
                    });
                    JshopFavoListener jshopFavoListener2 = jshopFavoListener;
                    if (jshopFavoListener2 != null) {
                        jshopFavoListener2.onError();
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                public void onProgress(int i3, int i4) {
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                public void onStart() {
                    Log.d(JshopNewFavoUtils.TAG, "onStart");
                }
            });
            this.mActivity.getHttpGroupaAsynPool().add(httpSetting);
        }
    }
