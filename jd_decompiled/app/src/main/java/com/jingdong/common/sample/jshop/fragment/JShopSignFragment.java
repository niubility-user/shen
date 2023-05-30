package com.jingdong.common.sample.jshop.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.LoginUser;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.app.mall.utils.s;
import com.jingdong.cleanmvp.ui.BaseFragment;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.deeplinkhelper.DeepLinkCartHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkJShopHomeHelper;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.helper.PDHelper;
import com.jingdong.common.sample.jshop.Entity.ProductEntity;
import com.jingdong.common.sample.jshop.JShopSignNewActivity;
import com.jingdong.common.sample.jshop.adapter.ProductAdapter;
import com.jingdong.common.sample.jshop.ui.JshopSignCircleProgress;
import com.jingdong.common.sample.jshop.ui.JshopSignScratchCard;
import com.jingdong.common.sample.jshop.ui.JshopSignScratchCardView;
import com.jingdong.common.sample.jshop.ui.VerticalMarqueeTextView;
import com.jingdong.common.ui.JDCheckDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.ui.JDGridView;
import com.jingdong.common.utils.CommonBridge;
import com.jingdong.common.utils.JshopNewFavoUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.jingdong.sdk.jshopsdk.common.favo.JshopFavoListener;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JShopSignFragment extends BaseFragment implements View.OnClickListener {
    private static final int ALREADY_SIGN = 2;
    public static final String COUPON = "coupon";
    private static final String FAV_CHECKBOX_NOTIFY = "\u5173\u6ce8\u6b64\u5e97\u94fa\uff0c\u660e\u5929\u7ee7\u7eed\u8585\u7f8a\u6bdb";
    public static final String FIRST_TO_LIST = "firstToList";
    public static final String KEY_PAGE_ID = "page_id";
    public static final String KEY_SORT_KEY = "sortKey";
    private static final String SHOP_JINGDOU_HINT = "\uff0c\u53ef\u5728\u6211\u7684\u4eac\u4e1c-\u4eac\u8c46\u5185\u67e5\u770b";
    private static final String SHOP_POINT_HINT = "<br/>\u79ef\u5206\u53ef\u5728\u5e97\u94fa\u9875\u4f1a\u5458\u4e2d\u5fc3\u67e5\u770b\u3002";
    private static final String SHOP_POINT_PRIZE = "\u53ca\u672c\u5e97\u4f1a\u5458\u79ef\u5206+";
    private static final int SIGN_FAILE = -1;
    private static final int SIGN_NO_WIN = 4;
    private static final int SIGN_SUCCESS = 1;
    public static final int SORT_COMMENT_COUNT = 6;
    public static final int SORT_RELATIVE = 0;
    private static final String TAG = "JShopSignFragment";
    private static final int UN_SIGN = 3;
    private Button btnGoShop;
    private Button btnReSignup;
    private String cateJSON;
    private int creditNum;
    private int extraCredit;
    private int isSign;
    JshopSignScratchCardView jshopSignScratchCardView;
    private LinearLayout jshop_sign_failed_layout;
    private TextView jshop_sign_ok_text;
    private TextView jshop_sign_scratchcard_share_text;
    private ScrollView jshop_sign_scroll_view;
    private MyActivity mActivity;
    private JDGridView mGridView;
    private TextView mJshopSignupMore;
    private VerticalMarqueeTextView mScrollTextView;
    private ProductAdapter productAdapter;
    private ArrayList<ProductEntity> productEntities;
    private LinearLayout productFloor;
    public double risk;
    private ShareLinkInterface shareLinkInterface;
    private String shareLinkUrl;
    private String shopId;
    public int signTotal;
    JshopSignScratchCard signType1FrameLayout;
    JshopSignCircleProgress signType2FrameLayout;
    private JDJSONArray tabJsonArray;
    private String venderId;
    private String wareId;
    private ArrayList<String> winners;
    public String zxPrice;
    public int continueDay = 0;
    public int lastContinueDay = 0;
    public int activityRuleType = 2;
    public long batchId = -1;
    private boolean clickHasbeenProcessed = true;
    public int shared = 0;
    private int prizeType = 4;
    private boolean isWin = false;
    private String prizeName = "";
    private boolean isFollowed = false;
    private boolean hasFollowed = false;
    private boolean mAlreadyFollowed = false;
    private long vendorId = -1;
    private int prizeLvl = -1;
    private String selfPriceTips = "";
    private boolean hasSignedToday = false;
    private JshopNewFavoUtils mUtils = null;
    private String mBtnLeftInfo = "";
    private boolean isEnter = false;

    /* renamed from: com.jingdong.common.sample.jshop.fragment.JShopSignFragment$41 */
    /* loaded from: classes6.dex */
    public class AnonymousClass41 implements Runnable {
        final /* synthetic */ String val$tips;

        AnonymousClass41(String str) {
            JShopSignFragment.this = r1;
            this.val$tips = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            JShopSignFragment.this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.41.1
                {
                    AnonymousClass41.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    String string = TextUtils.isEmpty(AnonymousClass41.this.val$tips) ? JShopSignFragment.this.getString(R.string.jshop_add_card_tips) : AnonymousClass41.this.val$tips;
                    JShopSignFragment jShopSignFragment = JShopSignFragment.this;
                    jShopSignFragment.mBtnLeftInfo = jShopSignFragment.getString(R.string.cut_gooncut);
                    final JDCheckDialog createJdDialogWithStyle6 = JDDialogFactory.getInstance().createJdDialogWithStyle6(JShopSignFragment.this.mActivity, "\u52a0\u5165\u8d2d\u7269\u8f66\u6210\u529f", string, JShopSignFragment.this.mBtnLeftInfo, JShopSignFragment.this.getString(R.string.jshop_to_card));
                    createJdDialogWithStyle6.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.41.1.1
                        {
                            AnonymousClass1.this = this;
                        }

                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            createJdDialogWithStyle6.dismiss();
                        }
                    });
                    createJdDialogWithStyle6.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.41.1.2
                        {
                            AnonymousClass1.this = this;
                        }

                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            DeepLinkCartHelper.startCartMain(JShopSignFragment.this.mActivity, null);
                            createJdDialogWithStyle6.dismiss();
                        }
                    });
                    createJdDialogWithStyle6.show();
                }
            });
        }
    }

    /* loaded from: classes6.dex */
    public interface ShareLinkInterface {
        void getIsSign(int i2);

        void getShareLink(String str);

        void getShared(int i2);

        void getTabArray(JDJSONArray jDJSONArray);

        void getWinInfo(boolean z, int i2, int i3, String str);
    }

    private void adjutJDCheckDialog(JDCheckDialog jDCheckDialog) {
        if (jDCheckDialog == null) {
            return;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        if (this.isFollowed) {
            arrayList.add(FAV_CHECKBOX_NOTIFY);
        }
        if (arrayList.size() > 0) {
            jDCheckDialog.initListView(this.mActivity, null, arrayList, "style6");
            final LinearLayout linearLayout = (LinearLayout) jDCheckDialog.findViewById(R.id.gf);
            linearLayout.setVisibility(0);
            linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.38
                {
                    JShopSignFragment.this = this;
                }

                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    linearLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    int height = linearLayout.getHeight();
                    int dimensionPixelSize = JShopSignFragment.this.mActivity.getResources().getDimensionPixelSize(R.dimen.f7723cn);
                    if (height > dimensionPixelSize) {
                        ((LinearLayout.LayoutParams) linearLayout.getLayoutParams()).height = dimensionPixelSize;
                        linearLayout.requestLayout();
                    }
                }
            });
            HashMap<Integer, Object> hashMap = new HashMap<Integer, Object>() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.39
                {
                    JShopSignFragment.this = this;
                }

                @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
                public Object remove(Object obj) {
                    if (containsKey(0) && JShopSignFragment.FAV_CHECKBOX_NOTIFY.equals(get(0))) {
                        JDMtaUtils.sendCommonData(JShopSignFragment.this.mActivity, "ShopCheckIn_CancelFollowShop", "", "", "", JShopSignFragment.this.shopId, "JshopTopicWareActivity", "", "ShopCheckIn_ShopCheckInMain", JShopSignFragment.this.shopId);
                    }
                    return super.remove(obj);
                }
            };
            hashMap.put(0, FAV_CHECKBOX_NOTIFY);
            jDCheckDialog.setDefaultSelectItem(hashMap);
        }
    }

    private void checkMoreItems() {
        JDMtaUtils.sendCommonData(getActivity(), "ShopCheckIn_MoreProducts", "", "", getActivity(), "", "JshopProductListActivity", "", "Shop_CheckIn", this.shopId);
        Intent intent = new Intent();
        intent.putExtra("page_id", "Shop_CheckInMore");
        intent.putExtra("shopId", this.shopId);
        intent.putExtra("sortKey", 0);
        Log.d(TAG, "cateJSON:" + this.cateJSON);
        if (this.cateJSON != null) {
            Log.d(TAG, "<<<<<<<<<<<<<<<<<<<<<<<:" + this.cateJSON);
            intent.putExtra("cateJSON", this.cateJSON);
        }
        intent.putExtra("type", 1);
        Bundle bundle = new Bundle();
        bundle.putSerializable("source", null);
        intent.putExtras(bundle);
        DeepLinkJShopHomeHelper.gotoJShopProductList(getActivity(), intent.getExtras());
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

    private void doNoWin(JDJSONObject jDJSONObject) {
        int i2 = this.activityRuleType;
        if (i2 == 1) {
            this.continueDay = jDJSONObject.optInt("continueDay");
            this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.14
                {
                    JShopSignFragment.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    int i3;
                    try {
                        JShopSignFragment jShopSignFragment = JShopSignFragment.this;
                        int i4 = jShopSignFragment.signTotal;
                        jShopSignFragment.signType2FrameLayout.viewOnClicked((i4 <= 0 || (i3 = jShopSignFragment.continueDay) <= 0) ? 0 : (int) ((i4 / i3) * 100.0f), i4, jShopSignFragment.continueDay);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            });
        } else if (i2 == 0) {
            this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.15
                {
                    JShopSignFragment.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    JShopSignFragment.this.jshopSignScratchCardView.setFailWinBmp();
                }
            });
        }
        if (this.isFollowed) {
            jDJSONObject.optString("signNoteAttach");
            this.hasFollowed = true;
            this.mActivity.post(new Runnable
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0037: INVOKE 
                  (wrap: com.jingdong.app.mall.utils.MyActivity : 0x0030: IGET (r3v0 'this' com.jingdong.common.sample.jshop.fragment.JShopSignFragment A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:8) com.jingdong.common.sample.jshop.fragment.JShopSignFragment.mActivity com.jingdong.app.mall.utils.MyActivity)
                  (wrap: java.lang.Runnable : 0x0034: CONSTRUCTOR 
                  (r3v0 'this' com.jingdong.common.sample.jshop.fragment.JShopSignFragment A[IMMUTABLE_TYPE, THIS])
                  (r4 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                 A[MD:(com.jingdong.common.sample.jshop.fragment.JShopSignFragment, java.lang.String):void (m), WRAPPED] call: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.16.<init>(com.jingdong.common.sample.jshop.fragment.JShopSignFragment, java.lang.String):void type: CONSTRUCTOR)
                 type: VIRTUAL call: com.jingdong.common.BaseActivity.post(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (m)] (LINE:8) in method: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.doNoWin(com.jd.framework.json.JDJSONObject):void, file: classes6.dex
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
                int r0 = r3.activityRuleType
                r1 = 1
                if (r0 != r1) goto L18
                java.lang.String r0 = "continueDay"
                int r0 = r4.optInt(r0)
                r3.continueDay = r0
                com.jingdong.app.mall.utils.MyActivity r0 = r3.mActivity
                com.jingdong.common.sample.jshop.fragment.JShopSignFragment$14 r2 = new com.jingdong.common.sample.jshop.fragment.JShopSignFragment$14
                r2.<init>()
                r0.post(r2)
                goto L24
            L18:
                if (r0 != 0) goto L24
                com.jingdong.app.mall.utils.MyActivity r0 = r3.mActivity
                com.jingdong.common.sample.jshop.fragment.JShopSignFragment$15 r2 = new com.jingdong.common.sample.jshop.fragment.JShopSignFragment$15
                r2.<init>()
                r0.post(r2)
            L24:
                boolean r0 = r3.isFollowed
                if (r0 == 0) goto L3a
                java.lang.String r0 = "signNoteAttach"
                java.lang.String r4 = r4.optString(r0)
                r3.hasFollowed = r1
                com.jingdong.app.mall.utils.MyActivity r0 = r3.mActivity
                com.jingdong.common.sample.jshop.fragment.JShopSignFragment$16 r1 = new com.jingdong.common.sample.jshop.fragment.JShopSignFragment$16
                r1.<init>()
                r0.post(r1)
            L3a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.doNoWin(com.jd.framework.json.JDJSONObject):void");
        }

        public void favShopUseDialogReturn(JDCheckDialog jDCheckDialog) {
            if (this.isFollowed) {
                Object singleResult = jDCheckDialog.getSingleResult();
                if (singleResult == null || !FAV_CHECKBOX_NOTIFY.equals(singleResult)) {
                    cancleFollowShop();
                }
            }
        }

        private String getExtraPoint() {
            StringBuffer stringBuffer = new StringBuffer("");
            if (this.extraCredit == 1 && this.creditNum > 0) {
                stringBuffer.append(SHOP_POINT_PRIZE);
                stringBuffer.append(this.creditNum);
            }
            return stringBuffer.toString();
        }

        public void getSignInfo() {
            this.mActivity.setSubRootView(null);
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(Configuration.getJshopHost());
            httpSetting.setFunctionId("getSignInfo");
            httpSetting.putJsonParam("vendorId", Long.valueOf(this.vendorId));
            httpSetting.setUseCookies(true);
            httpSetting.setEffect(1);
            httpSetting.setNotifyUser(true);
            httpSetting.setUseFastJsonParser(true);
            httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.6
                {
                    JShopSignFragment.this = this;
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    Log.d(JShopSignFragment.TAG, "on End");
                    try {
                        JShopSignFragment.this.parseSignInfoandShow(httpResponse.getFastJsonObject());
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        JShopSignFragment.this.showFailandRequestView();
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    Log.e(JShopSignFragment.TAG, "onError");
                    if (JShopSignFragment.this.shareLinkInterface != null) {
                        JShopSignFragment.this.shareLinkInterface.getTabArray(null);
                    }
                    JShopSignFragment.this.showFailandRequestView();
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                public void onProgress(int i2, int i3) {
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                public void onStart() {
                }
            });
            ((MyActivity) getActivity()).getHttpGroupaAsynPool().add(httpSetting);
        }

        private void getWinGift(final JDJSONObject jDJSONObject) {
            if (jDJSONObject == null) {
                return;
            }
            jDJSONObject.optString("signTitleAttach");
            jDJSONObject.optInt("continueDay");
            JDJSONObject optJSONObject = jDJSONObject.optJSONObject("signReward");
            this.prizeType = optJSONObject.optInt("prizeType");
            this.prizeName = optJSONObject.optString("name");
            this.extraCredit = optJSONObject.optInt("extraCredit");
            this.creditNum = optJSONObject.optInt("creditNum");
            this.selfPriceTips = optJSONObject.optString("tips");
            this.wareId = optJSONObject.optString("wareId");
            this.zxPrice = optJSONObject.optString("zxPrice");
            this.batchId = optJSONObject.optLong(JshopConst.JSKEY_BATCH_ID, 0L);
            this.prizeLvl = optJSONObject.optInt("prizeLvl", -1);
            final String extraPoint = getExtraPoint();
            final boolean z = !extraPoint.isEmpty();
            int i2 = this.activityRuleType;
            if (i2 == 1) {
                this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.17
                    {
                        JShopSignFragment.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            if (JShopSignFragment.this.prizeLvl == 0) {
                                JShopSignFragment.this.continueDay = jDJSONObject.optInt("continueDay");
                                Log.d(JShopSignFragment.TAG, "bottom prize, continue day: " + JShopSignFragment.this.continueDay + " signTotal: " + JShopSignFragment.this.signTotal);
                            } else {
                                Log.d(JShopSignFragment.TAG, "win no bottom prize, continue day: " + JShopSignFragment.this.continueDay + " signTotal: " + JShopSignFragment.this.signTotal);
                                JShopSignFragment.this.continueDay = jDJSONObject.optInt("continueDay");
                                JShopSignFragment jShopSignFragment = JShopSignFragment.this;
                                jShopSignFragment.signTotal = jShopSignFragment.continueDay;
                            }
                            JShopSignFragment jShopSignFragment2 = JShopSignFragment.this;
                            int i3 = jShopSignFragment2.signTotal;
                            int i4 = jShopSignFragment2.continueDay;
                            jShopSignFragment2.signType2FrameLayout.viewOnClicked((int) ((i3 / i4) * 100.0f), i3, i4);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                });
                this.mActivity.post(new Runnable
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0084: INVOKE 
                      (wrap: com.jingdong.app.mall.utils.MyActivity : 0x0079: IGET (r10v0 'this' com.jingdong.common.sample.jshop.fragment.JShopSignFragment A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:17) com.jingdong.common.sample.jshop.fragment.JShopSignFragment.mActivity com.jingdong.app.mall.utils.MyActivity)
                      (wrap: java.lang.Runnable : 0x007f: CONSTRUCTOR 
                      (r10v0 'this' com.jingdong.common.sample.jshop.fragment.JShopSignFragment A[IMMUTABLE_TYPE, THIS])
                      (r3 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r4 I:int A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r5v1 'extraPoint' java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r6v0 'z' boolean A[DONT_GENERATE, DONT_INLINE, REMOVE])
                     A[MD:(com.jingdong.common.sample.jshop.fragment.JShopSignFragment, java.lang.String, int, java.lang.String, boolean):void (m), WRAPPED] call: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.18.<init>(com.jingdong.common.sample.jshop.fragment.JShopSignFragment, java.lang.String, int, java.lang.String, boolean):void type: CONSTRUCTOR)
                      (1000 int)
                     type: VIRTUAL call: com.jingdong.common.BaseActivity.post(java.lang.Runnable, int):void A[MD:(java.lang.Runnable, int):void (m)] (LINE:17) in method: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.getWinGift(com.jd.framework.json.JDJSONObject):void, file: classes6.dex
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
                    */
                /*
                    this = this;
                    if (r11 != 0) goto L3
                    return
                L3:
                    java.lang.String r0 = "signTitleAttach"
                    java.lang.String r3 = r11.optString(r0)
                    java.lang.String r0 = "continueDay"
                    int r4 = r11.optInt(r0)
                    java.lang.String r0 = "signReward"
                    com.jd.framework.json.JDJSONObject r0 = r11.optJSONObject(r0)
                    java.lang.String r1 = "prizeType"
                    int r1 = r0.optInt(r1)
                    r10.prizeType = r1
                    java.lang.String r1 = "name"
                    java.lang.String r1 = r0.optString(r1)
                    r10.prizeName = r1
                    java.lang.String r1 = "extraCredit"
                    int r1 = r0.optInt(r1)
                    r10.extraCredit = r1
                    java.lang.String r1 = "creditNum"
                    int r1 = r0.optInt(r1)
                    r10.creditNum = r1
                    java.lang.String r1 = "tips"
                    java.lang.String r1 = r0.optString(r1)
                    r10.selfPriceTips = r1
                    java.lang.String r1 = "wareId"
                    java.lang.String r1 = r0.optString(r1)
                    r10.wareId = r1
                    java.lang.String r1 = "zxPrice"
                    java.lang.String r1 = r0.optString(r1)
                    r10.zxPrice = r1
                    r1 = 0
                    java.lang.String r5 = "batchId"
                    long r1 = r0.optLong(r5, r1)
                    r10.batchId = r1
                    r1 = -1
                    java.lang.String r2 = "prizeLvl"
                    int r0 = r0.optInt(r2, r1)
                    r10.prizeLvl = r0
                    java.lang.String r5 = r10.getExtraPoint()
                    boolean r0 = r5.isEmpty()
                    r7 = 1
                    r6 = r0 ^ 1
                    int r0 = r10.activityRuleType
                    if (r0 != r7) goto L88
                    com.jingdong.app.mall.utils.MyActivity r0 = r10.mActivity
                    com.jingdong.common.sample.jshop.fragment.JShopSignFragment$17 r1 = new com.jingdong.common.sample.jshop.fragment.JShopSignFragment$17
                    r1.<init>()
                    r0.post(r1)
                    com.jingdong.app.mall.utils.MyActivity r11 = r10.mActivity
                    com.jingdong.common.sample.jshop.fragment.JShopSignFragment$18 r0 = new com.jingdong.common.sample.jshop.fragment.JShopSignFragment$18
                    r1 = r0
                    r2 = r10
                    r1.<init>()
                    r1 = 1000(0x3e8, float:1.401E-42)
                    r11.post(r0, r1)
                    goto L94
                L88:
                    if (r0 != 0) goto L94
                    com.jingdong.app.mall.utils.MyActivity r11 = r10.mActivity
                    com.jingdong.common.sample.jshop.fragment.JShopSignFragment$19 r0 = new com.jingdong.common.sample.jshop.fragment.JShopSignFragment$19
                    r0.<init>()
                    r11.post(r0)
                L94:
                    int r11 = r10.prizeType
                    if (r11 != 0) goto L9c
                    java.lang.String r11 = "\u4f18\u60e0\u5238"
                L9a:
                    r2 = r11
                    goto Laa
                L9c:
                    if (r11 != r7) goto La1
                    java.lang.String r11 = "\u4e13\u4eab\u4ef7"
                    goto L9a
                La1:
                    r0 = 3
                    if (r11 != r0) goto La7
                    java.lang.String r11 = "\u4eac\u8c46"
                    goto L9a
                La7:
                    java.lang.String r11 = "\u5176\u5b83"
                    goto L9a
                Laa:
                    com.jingdong.app.mall.utils.MyActivity r4 = r10.mActivity
                    java.lang.String r9 = r10.shopId
                    java.lang.String r1 = "ShopCheckIn_PopupWindow"
                    java.lang.String r3 = ""
                    java.lang.String r6 = ""
                    java.lang.String r7 = ""
                    java.lang.String r8 = "ShopCheckIn_ShopCheckInMain"
                    r0 = r4
                    r5 = r9
                    com.jingdong.jdsdk.mta.JDMtaUtils.sendCommonData(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.getWinGift(com.jd.framework.json.JDJSONObject):void");
            }

            public void gotoShop(String str, String str2) {
                try {
                    Intent intent = new Intent();
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("venderId", str2);
                        jSONObject.put("shopId", str);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                    intent.putExtra(JshopConst.INTENT_BRAND_JSON, jSONObject.toString());
                    DeepLinkJShopHomeHelper.gotoJShopHome(this.mActivity, intent.getExtras());
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }

            public void gotoShoppingCart(String str, String str2) {
                ShoppingBaseController.addProductForProductList(this.mActivity, str, 1, new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.40
                    {
                        JShopSignFragment.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        JShopSignFragment.this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.40.1
                            {
                                AnonymousClass40.this = this;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                ToastUtils.shortToast(JShopSignFragment.this.mActivity.getApplicationContext(), "\u52a0\u5165\u8d2d\u7269\u8f66\u5931\u8d25");
                            }
                        });
                    }
                }, new AnonymousClass41(str2), null);
            }

            private void hideFailandRequestView() {
                this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.21
                    {
                        JShopSignFragment.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        JShopSignFragment.this.jshop_sign_scroll_view.setVisibility(0);
                        JShopSignFragment.this.jshop_sign_failed_layout.setVisibility(8);
                    }
                });
            }

            private void initView(View view) {
                this.signType1FrameLayout = (JshopSignScratchCard) view.findViewById(R.id.jshop_sign_scratchcard_fm);
                this.signType2FrameLayout = (JshopSignCircleProgress) view.findViewById(R.id.jshop_sign_circleprogressbar_fm);
                this.jshop_sign_scroll_view = (ScrollView) view.findViewById(R.id.jshop_sign_scroll_view);
                this.jshop_sign_failed_layout = (LinearLayout) view.findViewById(R.id.jshop_sign_failed_layout);
                this.mScrollTextView = (VerticalMarqueeTextView) view.findViewById(R.id.jshop_sign_scroll_textview);
                this.jshop_sign_ok_text = (TextView) view.findViewById(R.id.jshop_sign_ok_text);
                this.jshop_sign_scratchcard_share_text = (TextView) view.findViewById(R.id.jshop_sign_scratchcard_share_text);
                this.signType2FrameLayout.setSignButtonListener(new JshopSignCircleProgress.SignButtonListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.1
                    {
                        JShopSignFragment.this = this;
                    }

                    @Override // com.jingdong.common.sample.jshop.ui.JshopSignCircleProgress.SignButtonListener
                    public synchronized void signButtonClicked() {
                        Log.d(JShopSignFragment.TAG, "sign button clicked");
                        if (JShopSignFragment.this.clickHasbeenProcessed) {
                            JShopSignFragment.this.clickHasbeenProcessed = false;
                            try {
                                MyActivity myActivity = JShopSignFragment.this.mActivity;
                                StringBuilder sb = new StringBuilder();
                                sb.append(JShopSignFragment.this.signTotal);
                                sb.append(CartConstant.KEY_YB_INFO_LINK);
                                sb.append(JShopSignFragment.this.hasFollowed ? "\u5df2\u5173\u6ce8" : "\u672a\u5173\u6ce8");
                                sb.append(CartConstant.KEY_YB_INFO_LINK);
                                sb.append(String.valueOf(JShopSignFragment.this.risk));
                                JDMtaUtils.sendCommonData(myActivity, "ShopCheckIn_CheckIn", sb.toString(), "", JShopSignFragment.this.mActivity, JShopSignFragment.this.shopId, "", "", "ShopCheckIn_ShopCheckInMain", JShopSignFragment.this.shopId);
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                            if (!JShopSignFragment.this.hasSignedToday) {
                                JShopSignFragment.this.signRequest();
                            } else {
                                JShopSignFragment.this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.1.1
                                    {
                                        AnonymousClass1.this = this;
                                    }

                                    @Override // java.lang.Runnable
                                    public void run() {
                                        ToastUtils.showToastInCenter(JShopSignFragment.this.mActivity.getApplicationContext(), (byte) 1, "\u6bcf\u5929\u4ec5\u53ef\u7b7e\u5230\u4e00\u6b21", 0);
                                        JShopSignFragment.this.clickHasbeenProcessed = true;
                                    }
                                });
                            }
                        }
                    }
                });
                JshopSignScratchCardView jshopSignScratchCardView = (JshopSignScratchCardView) view.findViewById(R.id.jshop_sign_scratchcard_view);
                this.jshopSignScratchCardView = jshopSignScratchCardView;
                jshopSignScratchCardView.setOnCompleteListener(new JshopSignScratchCardView.OnCompleteListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.2
                    {
                        JShopSignFragment.this = this;
                    }

                    @Override // com.jingdong.common.sample.jshop.ui.JshopSignScratchCardView.OnCompleteListener
                    public void complete() {
                        Log.d(JShopSignFragment.TAG, "scratch card scratched complete!");
                        try {
                            JShopSignFragment jShopSignFragment = JShopSignFragment.this;
                            if (jShopSignFragment.activityRuleType == 0) {
                                MyActivity myActivity = jShopSignFragment.mActivity;
                                StringBuilder sb = new StringBuilder();
                                sb.append(JShopSignFragment.this.hasFollowed ? "\u5df2\u5173\u6ce8" : "\u672a\u5173\u6ce8");
                                sb.append(CartConstant.KEY_YB_INFO_LINK);
                                sb.append(String.valueOf(JShopSignFragment.this.risk));
                                JDMtaUtils.sendCommonData(myActivity, "ShopCheckIn_ScratchForPrize", sb.toString(), "", JShopSignFragment.this.mActivity, JShopSignFragment.this.shopId, "", "", "ShopCheckIn_ShopCheckInMain", JShopSignFragment.this.shopId);
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        JShopSignFragment.this.signRequest();
                    }
                });
                Button button = (Button) view.findViewById(R.id.btn_resign);
                this.btnReSignup = button;
                button.setOnClickListener(this);
                Button button2 = (Button) view.findViewById(R.id.btn_gotoshop);
                this.btnGoShop = button2;
                button2.setOnClickListener(this);
                JDGridView jDGridView = (JDGridView) view.findViewById(R.id.jshop_sign_gridview);
                this.mGridView = jDGridView;
                jDGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.3
                    {
                        JShopSignFragment.this = this;
                    }

                    @Override // android.widget.AdapterView.OnItemClickListener
                    public void onItemClick(AdapterView<?> adapterView, View view2, int i2, long j2) {
                        long parseLong = Long.parseLong(((ProductEntity) JShopSignFragment.this.productEntities.get(i2)).wareId);
                        Log.d(JShopSignFragment.TAG, "id:" + parseLong);
                        JDMtaUtils.sendCommonData(JShopSignFragment.this.getActivity(), "ShopCheckIn_Productid", JShopSignFragment.this.shopId + CartConstant.KEY_YB_INFO_LINK + parseLong, "", JShopSignFragment.this.getActivity(), "", PDHelper.getPDClassName(), "", "Shop_CheckIn", JShopSignFragment.this.shopId);
                        s.g(JShopSignFragment.this.getActivity(), DeeplinkProductDetailHelper.BundleBuilder.from(parseLong).imageTitlePrice(((ProductEntity) JShopSignFragment.this.productEntities.get(i2)).imgPath, ((ProductEntity) JShopSignFragment.this.productEntities.get(i2)).wareName, ((ProductEntity) JShopSignFragment.this.productEntities.get(i2)).jdPrice).build());
                    }
                });
                this.mGridView.setFocusable(false);
                TextView textView = (TextView) view.findViewById(R.id.jshop_signup_more);
                this.mJshopSignupMore = textView;
                textView.setOnClickListener(this);
                this.jshop_sign_scroll_view.setVisibility(8);
                this.productFloor = (LinearLayout) view.findViewById(R.id.jshop_signup_product_floor);
            }

            public void parseSignInfoandShow(JDJSONObject jDJSONObject) {
                try {
                    if (jDJSONObject == null) {
                        showFailandRequestView();
                        return;
                    }
                    Log.d(TAG, "sign info response-->>" + jDJSONObject.toString());
                    String optString = jDJSONObject.optString("code");
                    JDJSONObject optJSONObject = jDJSONObject.optJSONObject("result");
                    if ("0".equals(optString) && optJSONObject != null) {
                        Log.d(TAG, "Sign info: " + jDJSONObject.toString());
                        JDJSONArray optJSONArray = optJSONObject.optJSONArray("products");
                        JDJSONArray optJSONArray2 = optJSONObject.optJSONArray("winInfo");
                        this.activityRuleType = optJSONObject.optInt(JshopConst.JSKEY_SHOP_SIGNTYPE);
                        JDJSONObject optJSONObject2 = optJSONObject.optJSONObject("signInfo");
                        this.tabJsonArray = optJSONObject.optJSONArray("tabNames");
                        this.shareLinkUrl = optJSONObject.optString(JshopConst.JSKEY_SHARE_URL);
                        this.shared = optJSONObject.optInt("shared");
                        this.risk = optJSONObject.optDouble(VerifyTracker.EVENT_RISK);
                        this.hasFollowed = optJSONObject.optBoolean("hasFollowed");
                        this.isSign = optJSONObject2.optInt(JshopConst.JSKEY_JSHOP_ISSIGN);
                        this.signTotal = optJSONObject2.optInt("signTotal");
                        this.continueDay = optJSONObject2.optInt("continueDay");
                        this.activityRuleType = optJSONObject2.optInt(JshopConst.JSKEY_SHOP_SIGNTYPE);
                        ShareLinkInterface shareLinkInterface = this.shareLinkInterface;
                        if (shareLinkInterface != null) {
                            shareLinkInterface.getShareLink(this.shareLinkUrl);
                            this.shareLinkInterface.getTabArray(this.tabJsonArray);
                            this.shareLinkInterface.getShared(this.shared);
                            this.shareLinkInterface.getIsSign(this.isSign);
                        }
                        showSignTypeView();
                        ArrayList<ProductEntity> arrayList = this.productEntities;
                        if (arrayList == null) {
                            this.productEntities = new ArrayList<>();
                        } else {
                            arrayList.clear();
                        }
                        int size = optJSONArray.size() < 10 ? optJSONArray.size() : 10;
                        for (int i2 = 0; i2 < size; i2++) {
                            this.productEntities.add(new ProductEntity(optJSONArray.getJSONObject(i2)));
                        }
                        ArrayList<String> arrayList2 = this.winners;
                        if (arrayList2 == null) {
                            this.winners = new ArrayList<>();
                        } else {
                            arrayList2.clear();
                        }
                        int size2 = optJSONArray2.size() < 10 ? optJSONArray2.size() : 10;
                        for (int i3 = 0; i3 < size2; i3++) {
                            this.winners.add(optJSONArray2.getString(i3));
                        }
                        showProducts();
                        showWinnerInfo();
                        return;
                    }
                    showFailandRequestView();
                } catch (Exception e2) {
                    e2.printStackTrace();
                    showFailandRequestView();
                }
            }

            public void parseSignResponseShow(JDJSONObject jDJSONObject, JDJSONObject jDJSONObject2) {
                if ("0".equals(jDJSONObject.optString("code")) && jDJSONObject2 != null) {
                    int optInt = jDJSONObject2.optInt(JshopConst.JSKEY_JSHOP_ISSIGN);
                    this.isSign = optInt;
                    ShareLinkInterface shareLinkInterface = this.shareLinkInterface;
                    if (shareLinkInterface != null) {
                        shareLinkInterface.getIsSign(optInt);
                    }
                    int i2 = this.isSign;
                    if (i2 != -1) {
                        if (i2 == 4) {
                            this.continueDay = jDJSONObject2.optInt("continueDay");
                            this.signTotal = jDJSONObject2.optInt("signTotal");
                            int i3 = this.activityRuleType;
                            if (i3 == 1) {
                                this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.11
                                    {
                                        JShopSignFragment.this = this;
                                    }

                                    @Override // java.lang.Runnable
                                    public void run() {
                                        int i4;
                                        try {
                                            JShopSignFragment jShopSignFragment = JShopSignFragment.this;
                                            int i5 = jShopSignFragment.signTotal;
                                            jShopSignFragment.signType2FrameLayout.viewOnClicked((i5 <= 0 || (i4 = jShopSignFragment.continueDay) <= 0) ? 0 : (int) ((i5 / i4) * 100.0f), i5, jShopSignFragment.continueDay);
                                        } catch (Exception e2) {
                                            e2.printStackTrace();
                                        }
                                    }
                                });
                            } else if (i3 == 0) {
                                this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.12
                                    {
                                        JShopSignFragment.this = this;
                                    }

                                    @Override // java.lang.Runnable
                                    public void run() {
                                        JShopSignFragment.this.jshopSignScratchCardView.setFailWinBmp();
                                    }
                                });
                            }
                        } else if (i2 == 1) {
                            handSignSuccess(jDJSONObject2);
                        } else if (i2 != 2) {
                            this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.13
                                {
                                    JShopSignFragment.this = this;
                                }

                                @Override // java.lang.Runnable
                                public void run() {
                                    int i4;
                                    ToastUtils.shortToast(JShopSignFragment.this.mActivity.getApplicationContext(), "\u7b7e\u5230\u5f02\u5e38");
                                    JShopSignFragment jShopSignFragment = JShopSignFragment.this;
                                    int i5 = jShopSignFragment.activityRuleType;
                                    if (i5 != 1) {
                                        if (i5 == 0) {
                                            jShopSignFragment.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.13.1
                                                {
                                                    AnonymousClass13.this = this;
                                                }

                                                @Override // java.lang.Runnable
                                                public void run() {
                                                    JShopSignFragment.this.jshopSignScratchCardView.setFailWinBmp();
                                                }
                                            });
                                            return;
                                        }
                                        return;
                                    }
                                    try {
                                        int i6 = jShopSignFragment.signTotal;
                                        int i7 = (i6 <= 0 || (i4 = jShopSignFragment.continueDay) <= 0) ? 0 : (int) ((i6 / i4) * 100.0f);
                                        if (i6 == 0) {
                                            jShopSignFragment.signType2FrameLayout.initAnimation();
                                        } else if (i6 > 0) {
                                            jShopSignFragment.signType2FrameLayout.viewOnClicked(i7, i6, jShopSignFragment.continueDay);
                                        }
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            });
                        } else {
                            this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.7
                                {
                                    JShopSignFragment.this = this;
                                }

                                @Override // java.lang.Runnable
                                public void run() {
                                    JShopSignFragment.this.hasSignedToday = true;
                                    ToastUtils.showToastInCenter(JShopSignFragment.this.mActivity.getApplicationContext(), (byte) 1, "\u6bcf\u5929\u53ea\u53ef\u62bd\u5956\u4e00\u6b21", 0);
                                }
                            });
                            int i4 = this.activityRuleType;
                            if (i4 == 1) {
                                this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.8
                                    {
                                        JShopSignFragment.this = this;
                                    }

                                    @Override // java.lang.Runnable
                                    public void run() {
                                        try {
                                            JShopSignFragment jShopSignFragment = JShopSignFragment.this;
                                            int i5 = jShopSignFragment.signTotal;
                                            int i6 = jShopSignFragment.continueDay;
                                            jShopSignFragment.signType2FrameLayout.viewOnClicked((int) ((i5 / i6) * 100.0f), i5, i6);
                                        } catch (Exception e2) {
                                            e2.printStackTrace();
                                        }
                                    }
                                });
                                return;
                            } else if (i4 == 0) {
                                this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.9
                                    {
                                        JShopSignFragment.this = this;
                                    }

                                    @Override // java.lang.Runnable
                                    public void run() {
                                        JShopSignFragment.this.jshopSignScratchCardView.setSuccessOrWinBmp();
                                    }
                                });
                                return;
                            } else {
                                return;
                            }
                        }
                        ShareLinkInterface shareLinkInterface2 = this.shareLinkInterface;
                        if (shareLinkInterface2 != null) {
                            shareLinkInterface2.getWinInfo(this.isWin, this.prizeType, this.activityRuleType, this.prizeName);
                            return;
                        }
                        return;
                    }
                    this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.10
                        {
                            JShopSignFragment.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            JShopSignFragment jShopSignFragment = JShopSignFragment.this;
                            int i5 = jShopSignFragment.activityRuleType;
                            if (i5 != 0) {
                                if (i5 == 1) {
                                    ToastUtils.shortToast(jShopSignFragment.mActivity.getApplicationContext(), "\u7b7e\u5230\u5931\u8d25");
                                    return;
                                } else {
                                    ToastUtils.shortToast(jShopSignFragment.mActivity.getApplicationContext(), "\u7f51\u7edc\u5f02\u5e38,\u8bf7\u7a0d\u751f\u518d\u8bd5");
                                    return;
                                }
                            }
                            ToastUtils.shortToast(jShopSignFragment.mActivity.getApplicationContext(), "\u62bd\u5956\u5931\u8d25");
                            JShopSignFragment.this.jshop_sign_scratchcard_share_text.setVisibility(JShopSignFragment.this.shared == 1 ? 0 : 8);
                            JshopSignScratchCardView jshopSignScratchCardView = JShopSignFragment.this.jshopSignScratchCardView;
                            jshopSignScratchCardView.happened = false;
                            jshopSignScratchCardView.setStartBmp();
                        }
                    });
                    return;
                }
                showFailandRequestView();
            }

            public void showAlonePointDialog(String str, CharSequence charSequence) {
                final JDCheckDialog createJdDialogWithStyle5 = JDDialogFactory.getInstance().createJdDialogWithStyle5(this.mActivity, str, charSequence, null, "\u6211\u77e5\u9053\u4e86");
                createJdDialogWithStyle5.posButton.setBackgroundResource(R.drawable.b2);
                createJdDialogWithStyle5.posButton.setTextColor(this.mActivity.getResources().getColor(R.color.mk));
                adjutJDCheckDialog(createJdDialogWithStyle5);
                createJdDialogWithStyle5.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.27
                    {
                        JShopSignFragment.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        createJdDialogWithStyle5.dismiss();
                    }
                });
                createJdDialogWithStyle5.show();
            }

            public void showFailandRequestView() {
                this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.20
                    {
                        JShopSignFragment.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        JShopSignFragment.this.jshop_sign_scroll_view.setVisibility(8);
                        JShopSignFragment.this.jshop_sign_failed_layout.setVisibility(0);
                    }
                });
            }

            public void showGetJDStamp(String str, CharSequence charSequence) {
                try {
                    MyActivity myActivity = this.mActivity;
                    String valueOf = String.valueOf(this.batchId);
                    MyActivity myActivity2 = this.mActivity;
                    String str2 = this.shopId;
                    JDMtaUtils.sendCommonData(myActivity, "ShopCheckIn_SystemCoupon", valueOf, "", myActivity2, str2, "", "", "ShopCheckIn_ShopCheckInMain", str2);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                this.mBtnLeftInfo = StringUtil.product_add_negative;
                final JDCheckDialog createJdDialogWithStyle6 = JDDialogFactory.getInstance().createJdDialogWithStyle6(this.mActivity, str, charSequence, this.mBtnLeftInfo, "\u53bb\u4f7f\u7528");
                adjutJDCheckDialog(createJdDialogWithStyle6);
                createJdDialogWithStyle6.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.22
                    {
                        JShopSignFragment.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        JDMtaUtils.sendCommonData(JShopSignFragment.this.mActivity, "ShopCheckIn_GoLookMore", String.valueOf(2), "", JShopSignFragment.this.mActivity, JShopSignFragment.this.shopId, "", "", "ShopCheckIn_ShopCheckInMain", JShopSignFragment.this.shopId);
                        JShopSignFragment jShopSignFragment = JShopSignFragment.this;
                        if (jShopSignFragment.activityRuleType == 0) {
                            jShopSignFragment.getSignInfo();
                        }
                        JShopSignFragment.this.favShopUseDialogReturn(createJdDialogWithStyle6);
                        createJdDialogWithStyle6.dismiss();
                    }
                });
                createJdDialogWithStyle6.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.23
                    {
                        JShopSignFragment.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        JDMtaUtils.sendCommonData(JShopSignFragment.this.mActivity, "ShopCheckIn_ToUseIt", String.valueOf(JShopSignFragment.this.batchId), "", JShopSignFragment.this.mActivity, JShopSignFragment.this.shopId, "", "", "ShopCheckIn_ShopCheckInMain", JShopSignFragment.this.shopId);
                        JShopSignFragment.this.favShopUseDialogReturn(createJdDialogWithStyle6);
                        JShopSignFragment jShopSignFragment = JShopSignFragment.this;
                        jShopSignFragment.gotoShop(jShopSignFragment.shopId, JShopSignFragment.this.venderId);
                        createJdDialogWithStyle6.dismiss();
                    }
                });
                createJdDialogWithStyle6.show();
            }

            public void showGetSelfSharePrice(String str, CharSequence charSequence, final String str2) {
                this.mBtnLeftInfo = StringUtil.product_add_negative;
                final JDCheckDialog showSelfSharePriceDialog = showSelfSharePriceDialog(this.mActivity, str, charSequence, StringUtil.product_add_negative, "\u52a0\u5165\u8d2d\u7269\u8f66");
                adjutJDCheckDialog(showSelfSharePriceDialog);
                showSelfSharePriceDialog.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.24
                    {
                        JShopSignFragment.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        JDMtaUtils.sendCommonData(JShopSignFragment.this.mActivity, "ShopCheckIn_GoLookMore", "1", "", JShopSignFragment.this.mActivity, JShopSignFragment.this.shopId, "", "", "ShopCheckIn_ShopCheckInMain", JShopSignFragment.this.shopId);
                        JShopSignFragment jShopSignFragment = JShopSignFragment.this;
                        if (jShopSignFragment.activityRuleType == 0) {
                            jShopSignFragment.getSignInfo();
                        }
                        JShopSignFragment.this.favShopUseDialogReturn(showSelfSharePriceDialog);
                        showSelfSharePriceDialog.dismiss();
                    }
                });
                showSelfSharePriceDialog.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.25
                    {
                        JShopSignFragment.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        try {
                            JDMtaUtils.sendCommonData(JShopSignFragment.this.mActivity, "ShopCheckIn_ToShopCart", "\u4e13\u4eab_" + JShopSignFragment.this.wareId + CartConstant.KEY_YB_INFO_LINK + JShopSignFragment.this.zxPrice, "", JShopSignFragment.this.mActivity, JShopSignFragment.this.shopId, "", "", "ShopCheckIn_ShopCheckInMain", JShopSignFragment.this.shopId);
                            JShopSignFragment.this.favShopUseDialogReturn(showSelfSharePriceDialog);
                        } catch (Exception e2) {
                            if (Log.D) {
                                e2.printStackTrace();
                            }
                        }
                        JShopSignFragment jShopSignFragment = JShopSignFragment.this;
                        jShopSignFragment.gotoShoppingCart(jShopSignFragment.wareId, str2);
                        showSelfSharePriceDialog.dismiss();
                    }
                });
                showSelfSharePriceDialog.show();
            }

            public void showJingDouDialog(String str, CharSequence charSequence) {
                final JDCheckDialog createJdDialogWithStyle5 = JDDialogFactory.getInstance().createJdDialogWithStyle5(this.mActivity, str, charSequence, null, "\u6211\u77e5\u9053\u4e86");
                createJdDialogWithStyle5.posButton.setBackgroundResource(R.drawable.b2);
                createJdDialogWithStyle5.posButton.setTextColor(this.mActivity.getResources().getColor(R.color.mk));
                adjutJDCheckDialog(createJdDialogWithStyle5);
                createJdDialogWithStyle5.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.26
                    {
                        JShopSignFragment.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        JDMtaUtils.sendCommonData(JShopSignFragment.this.mActivity, "ShopCheckIn_ALaSou", "", "", JShopSignFragment.this.mActivity, JShopSignFragment.this.shopId, "", "", "ShopCheckIn_ShopCheckInMain", JShopSignFragment.this.shopId);
                        JShopSignFragment jShopSignFragment = JShopSignFragment.this;
                        if (jShopSignFragment.activityRuleType == 0) {
                            jShopSignFragment.getSignInfo();
                        }
                        JShopSignFragment.this.favShopUseDialogReturn(createJdDialogWithStyle5);
                        createJdDialogWithStyle5.dismiss();
                    }
                });
                createJdDialogWithStyle5.show();
            }

            public void showNoPrizeFavInfoDialog(CharSequence charSequence) {
                String string = getString(R.string.jshop_comfirm);
                this.mBtnLeftInfo = string;
                final JDCheckDialog createNoPrizeDialog = createNoPrizeDialog(this.mActivity, charSequence, null, string, "\u6211\u77e5\u9053\u4e86");
                adjutJDCheckDialog(createNoPrizeDialog);
                createNoPrizeDialog.negButton.setBackgroundResource(R.drawable.b2);
                createNoPrizeDialog.posButton.setVisibility(8);
                createNoPrizeDialog.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.28
                    {
                        JShopSignFragment.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        JShopSignFragment.this.favShopUseDialogReturn(createNoPrizeDialog);
                        JDMtaUtils.sendCommonData(JShopSignFragment.this.mActivity, "ShopCheckIn_ALaSou", "", "", JShopSignFragment.this.mActivity, JShopSignFragment.this.shopId, "", "", "ShopCheckIn_ShopCheckInMain", JShopSignFragment.this.shopId);
                        createNoPrizeDialog.dismiss();
                    }
                });
                createNoPrizeDialog.show();
            }

            private void showProducts() {
                ArrayList<ProductEntity> arrayList = this.productEntities;
                if (arrayList != null && arrayList.size() > 0) {
                    ProductAdapter productAdapter = this.productAdapter;
                    if (productAdapter == null) {
                        this.productAdapter = new ProductAdapter(this.productEntities, this.mActivity);
                        this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.32
                            {
                                JShopSignFragment.this = this;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                JShopSignFragment.this.productFloor.setVisibility(0);
                                JShopSignFragment.this.mGridView.setAdapter((ListAdapter) JShopSignFragment.this.productAdapter);
                            }
                        });
                        return;
                    }
                    productAdapter.setProductAdapterData(this.productEntities);
                    this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.33
                        {
                            JShopSignFragment.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            JShopSignFragment.this.productFloor.setVisibility(0);
                            JShopSignFragment.this.productAdapter.notifyDataSetChanged();
                        }
                    });
                    return;
                }
                this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.34
                    {
                        JShopSignFragment.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        JShopSignFragment.this.productFloor.setVisibility(8);
                    }
                });
            }

            public void showSignTypeView() {
                hideFailandRequestView();
                this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.31
                    {
                        JShopSignFragment.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        if (JShopSignFragment.this.isSign == 2) {
                            JShopSignFragment.this.hasSignedToday = true;
                        }
                        JShopSignFragment jShopSignFragment = JShopSignFragment.this;
                        int i2 = jShopSignFragment.activityRuleType;
                        if (i2 == 0) {
                            jShopSignFragment.jshop_sign_scroll_view.setVisibility(0);
                            JShopSignFragment.this.signType1FrameLayout.setVisibility(0);
                            JShopSignFragment.this.signType2FrameLayout.setVisibility(8);
                            JShopSignFragment.this.mScrollTextView.setBackgroundColor(-5096150);
                            JShopSignFragment.this.jshop_sign_scratchcard_share_text.setVisibility(JShopSignFragment.this.shared == 1 ? 0 : 8);
                            if (JShopSignFragment.this.isSign != 0) {
                                if (JShopSignFragment.this.isSign == 2 || JShopSignFragment.this.isSign == 1) {
                                    JShopSignFragment.this.jshopSignScratchCardView.setSuccessOrWinBmp();
                                    return;
                                }
                                return;
                            }
                            JshopSignScratchCardView jshopSignScratchCardView = JShopSignFragment.this.jshopSignScratchCardView;
                            jshopSignScratchCardView.happened = false;
                            jshopSignScratchCardView.setStartBmp();
                        } else if (i2 == 1) {
                            jShopSignFragment.jshop_sign_scroll_view.setVisibility(0);
                            JShopSignFragment.this.signType1FrameLayout.setVisibility(8);
                            JShopSignFragment.this.signType2FrameLayout.setVisibility(0);
                            JShopSignFragment.this.mScrollTextView.setBackgroundColor(-5074901);
                            JShopSignFragment.this.jshop_sign_ok_text.setText("\u8fde\u7b7e" + JShopSignFragment.this.continueDay + "\u5929\u66f4\u591a\u5956\u52b1");
                            if (JShopSignFragment.this.isSign != 0) {
                                if (JShopSignFragment.this.isSign == 2 || JShopSignFragment.this.isSign == 1) {
                                    try {
                                        JShopSignFragment jShopSignFragment2 = JShopSignFragment.this;
                                        int i3 = (int) ((jShopSignFragment2.signTotal / jShopSignFragment2.continueDay) * 100.0f);
                                        Log.d(JShopSignFragment.TAG, "process: " + i3);
                                        if (i3 > 100) {
                                            i3 = 100;
                                        }
                                        JShopSignFragment jShopSignFragment3 = JShopSignFragment.this;
                                        jShopSignFragment3.signType2FrameLayout.viewOnClicked(i3, jShopSignFragment3.signTotal, jShopSignFragment3.continueDay);
                                        return;
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                        ToastUtils.shortToast(JShopSignFragment.this.mActivity, "\u7b7e\u5230\u6570\u636e\u6709\u9519");
                                        return;
                                    }
                                }
                                return;
                            }
                            JShopSignFragment.this.signType2FrameLayout.initAnimation();
                        } else {
                            jShopSignFragment.signType1FrameLayout.setVisibility(8);
                            JShopSignFragment.this.signType2FrameLayout.setVisibility(8);
                            JShopSignFragment.this.jshop_sign_scroll_view.setVisibility(8);
                            JShopSignFragment.this.jshop_sign_failed_layout.setVisibility(0);
                        }
                    }
                });
            }

            private void showWinnerInfo() {
                this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.35
                    {
                        JShopSignFragment.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            if (JShopSignFragment.this.winners != null && JShopSignFragment.this.winners.size() > 0) {
                                JShopSignFragment.this.mScrollTextView.setArrayList(JShopSignFragment.this.winners);
                            } else {
                                Log.d(JShopSignFragment.TAG, "winner info is null");
                                JShopSignFragment.this.mScrollTextView.setVisibility(8);
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            ToastUtils.shortToast(JShopSignFragment.this.mActivity.getApplicationContext(), "\u8f6e\u64ad\u5956\u54c1\u6570\u636e\u6709\u9519");
                        }
                    }
                });
            }

            public void signRequest() {
                HttpSetting httpSetting = new HttpSetting();
                httpSetting.setHost(Configuration.getJshopHost());
                httpSetting.setFunctionId("sign");
                httpSetting.putJsonParam("vendorId", Long.valueOf(this.vendorId));
                httpSetting.putJsonParam("sourceRpc", "shop_app_sign_home");
                httpSetting.setUseCookies(true);
                httpSetting.setUseFastJsonParser(true);
                httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.5
                    {
                        JShopSignFragment.this = this;
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                    public void onEnd(HttpResponse httpResponse) {
                        JDJSONObject fastJsonObject;
                        JDJSONObject optJSONObject;
                        Log.d(JShopSignFragment.TAG, "response -->>" + httpResponse);
                        if (httpResponse == null) {
                            return;
                        }
                        try {
                            try {
                                fastJsonObject = httpResponse.getFastJsonObject();
                                Log.d(JShopSignFragment.TAG, "response json --> : " + fastJsonObject);
                                optJSONObject = fastJsonObject.optJSONObject("result");
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                JShopSignFragment.this.showFailandRequestView();
                            }
                            if (JShopSignFragment.this.isNeedOpenPayPsw(fastJsonObject)) {
                                JShopSignFragment.this.hasSignedToday = false;
                                String optString = optJSONObject.optString("openUrl");
                                if (!TextUtils.isEmpty(optString)) {
                                    CommonBridge.goToMWithUrl(JShopSignFragment.this.mActivity, optString);
                                }
                                JShopSignFragment.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.5.1
                                    {
                                        AnonymousClass5.this = this;
                                    }

                                    @Override // java.lang.Runnable
                                    public void run() {
                                        JShopSignFragment.this.showSignTypeView();
                                    }
                                }, 200);
                                return;
                            }
                            JShopSignFragment.this.parseSignResponseShow(fastJsonObject, optJSONObject);
                        } finally {
                            JShopSignFragment.this.clickHasbeenProcessed = true;
                        }
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                    public void onError(HttpError httpError) {
                        Log.e(JShopSignFragment.TAG, "onError");
                        JShopSignFragment.this.showFailandRequestView();
                        JShopSignFragment.this.clickHasbeenProcessed = true;
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

            public void cancleFollowShop() {
                JshopNewFavoUtils jshopNewFavoUtils = this.mUtils;
                if (jshopNewFavoUtils != null) {
                    jshopNewFavoUtils.getFavoStatus(null, false, this.shopId, true, new JshopFavoListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.42
                        {
                            JShopSignFragment.this = this;
                        }

                        @Override // com.jingdong.sdk.jshopsdk.common.favo.JshopFavoListener
                        public void onError() {
                            Log.d(JShopSignFragment.TAG, "onError");
                        }

                        @Override // com.jingdong.sdk.jshopsdk.common.favo.JshopFavoListener
                        public void onFavoStatus(boolean z) {
                            Log.d(JShopSignFragment.TAG, "onFavoStatus bool = " + z);
                        }
                    });
                }
            }

            public JDCheckDialog createNoPrizeDialog(Context context, CharSequence charSequence, ArrayList<String> arrayList, String str, String str2) throws IllegalArgumentException {
                if (context != null) {
                    if (!TextUtils.isEmpty(charSequence)) {
                        if (!TextUtils.isEmpty(str)) {
                            if (!TextUtils.isEmpty(str2)) {
                                JDCheckDialog jDCheckDialog = new JDCheckDialog(context);
                                jDCheckDialog.setContentView(R.layout.jshop_sign_no_prize_dialog);
                                jDCheckDialog.messageView = (TextView) jDCheckDialog.findViewById(R.id.bn);
                                jDCheckDialog.setMessage(charSequence, true);
                                Button button = (Button) jDCheckDialog.findViewById(R.id.br);
                                jDCheckDialog.posButton = button;
                                button.setText(str);
                                jDCheckDialog.useCancelClickEvent(jDCheckDialog.posButton);
                                Button button2 = (Button) jDCheckDialog.findViewById(R.id.bq);
                                jDCheckDialog.negButton = button2;
                                button2.setText(str2);
                                jDCheckDialog.useCancelClickEvent(jDCheckDialog.negButton);
                                return jDCheckDialog;
                            }
                            throw new IllegalArgumentException("the param rightButtonText can not be empty in this dialog style");
                        }
                        throw new IllegalArgumentException("the param leftButtonText can not be empty in this dialog style");
                    }
                    throw new IllegalArgumentException("the param message can not be empty in this dialog style");
                }
                throw new IllegalArgumentException("the param context can not be null in this dialog style");
            }

            public String getLeftBtnStr(String str) {
                return isfollowed() ? str : getString(R.string.jshop_cancle_fallow);
            }

            public void getSignInfoRequest() {
                this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.4
                    {
                        JShopSignFragment.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        LoginUser.getInstance().executeLoginRunnable((MyActivity) JShopSignFragment.this.getActivity(), new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.4.1
                            {
                                AnonymousClass4.this = this;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                JShopSignFragment.this.getSignInfo();
                            }
                        });
                    }
                });
            }

            public void handSignSuccess(JDJSONObject jDJSONObject) {
                this.hasSignedToday = true;
                this.isWin = jDJSONObject.optBoolean("isWin");
                this.signTotal = jDJSONObject.optInt("signTotal");
                this.isFollowed = jDJSONObject.optBoolean(JshopConst.JSKEY_FOLLOWED);
                if (this.isWin) {
                    getWinGift(jDJSONObject);
                } else {
                    doNoWin(jDJSONObject);
                }
            }

            public void handlerText(TextView textView) throws Exception {
                CharSequence charSequence;
                int lineCount = textView.getLineCount();
                String charSequence2 = textView.getText().toString();
                if (!TextUtils.isEmpty(charSequence2)) {
                    int lineEnd = textView.getLayout().getLineEnd(0);
                    int lineEnd2 = textView.getLayout().getLineEnd(1);
                    if (lineCount == 2) {
                        int i2 = lineEnd2 - (lineEnd * 2);
                        if (i2 <= -10) {
                            charSequence = charSequence2 + getString(R.string.jshop_sign_to_detail);
                        } else if (i2 <= 0) {
                            charSequence = ((Object) charSequence2.subSequence(0, lineEnd2 - (i2 + 8))) + getString(R.string.jshop_sign_to_detail);
                        } else {
                            charSequence = ((Object) charSequence2.subSequence(0, lineEnd2 - 8)) + getString(R.string.jshop_sign_to_detail);
                        }
                    } else if (lineCount > 2) {
                        charSequence = ((Object) charSequence2.subSequence(0, lineEnd2 - 8)) + getString(R.string.jshop_sign_to_detail);
                    } else {
                        charSequence = charSequence2 + getString(R.string.jshop_sign_to_detail1);
                    }
                    textView.setText(charSequence);
                    if (textView.getLineCount() > 2) {
                        for (int i3 = 0; i3 < 3 && textView.getLineCount() > 2; i3++) {
                            textView.setText(((Object) charSequence2.subSequence(0, textView.getLayout().getLineEnd(1) - ((i3 * 2) + 8))) + getString(R.string.jshop_sign_to_detail));
                        }
                    }
                    String charSequence3 = textView.getText().toString();
                    SpannableString spannableString = new SpannableString(charSequence3);
                    spannableString.setSpan(new ForegroundColorSpan(-961709), 2, charSequence3.length(), 33);
                    textView.setText(spannableString);
                    return;
                }
                Log.d(TAG, "message is empty and return!");
                throw new IllegalArgumentException("the message can not empty");
            }

            public boolean isNeedOpenPayPsw(JDJSONObject jDJSONObject) {
                try {
                    JDJSONObject jSONObject = jDJSONObject.getJSONObject("result");
                    if (jSONObject == null || !jSONObject.containsKey("isOpenPayPsw")) {
                        return false;
                    }
                    return !jSONObject.optBoolean("isOpenPayPsw");
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                return false;
            }

            public boolean isWord(String str) {
                return str.matches("[a-zA-Z]+");
            }

            public boolean isfollowed() {
                return this.mAlreadyFollowed;
            }

            @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
            public void onAttach(Activity activity) {
                super.onAttach(activity);
                try {
                    this.mActivity = (JShopSignNewActivity) activity;
                    this.shareLinkInterface = (ShareLinkInterface) activity;
                    this.mUtils = new JshopNewFavoUtils(this.mActivity, false, false, JshopNewFavoUtils.SOURCE_RPC_SIGN_UNFOLLOW, null);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.btn_gotoshop) {
                    gotoShop(this.shopId, this.venderId);
                } else if (id == R.id.btn_resign) {
                    getSignInfoRequest();
                } else if (id != R.id.jshop_signup_more) {
                } else {
                    checkMoreItems();
                }
            }

            @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
            public void onCreate(Bundle bundle) {
                super.onCreate(bundle);
            }

            @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
            public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
                setIsUseBasePV(false);
                return layoutInflater.inflate(R.layout.jshop_sign_fragment, (ViewGroup) null);
            }

            @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
            public void onDestroy() {
                Log.d(TAG, "JshopSignFragment onDestroy");
                super.onDestroy();
                ArrayList<ProductEntity> arrayList = this.productEntities;
                if (arrayList != null) {
                    arrayList.clear();
                }
                JshopSignScratchCardView jshopSignScratchCardView = this.jshopSignScratchCardView;
                if (jshopSignScratchCardView != null) {
                    jshopSignScratchCardView.onDestroy();
                }
                JshopSignCircleProgress jshopSignCircleProgress = this.signType2FrameLayout;
                if (jshopSignCircleProgress != null) {
                    jshopSignCircleProgress.onDestroy();
                }
                VerticalMarqueeTextView verticalMarqueeTextView = this.mScrollTextView;
                if (verticalMarqueeTextView != null) {
                    verticalMarqueeTextView.stop();
                }
            }

            @Override // com.jingdong.cleanmvp.ui.BaseFragment
            public boolean onKeyDown(int i2, KeyEvent keyEvent) {
                return false;
            }

            @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
            public void onResume() {
                Log.d(TAG, "onResume");
                super.onResume();
                try {
                    if (this.signType2FrameLayout.getVisibility() == 0) {
                        this.signType2FrameLayout.setCircleAnimationEnabled(true);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
            public void onStop() {
                Log.d(TAG, "onStop");
                super.onStop();
                try {
                    if (this.signType2FrameLayout.getVisibility() == 0) {
                        this.signType2FrameLayout.clearAnimation();
                        this.signType2FrameLayout.setCircleAnimationStopped();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            @Override // androidx.fragment.app.Fragment
            public void onViewCreated(View view, @Nullable Bundle bundle) {
                super.onViewCreated(view, bundle);
                Bundle arguments = getArguments();
                if (arguments != null) {
                    this.shopId = arguments.getString("shopId");
                    this.venderId = arguments.getString("venderId");
                    this.activityRuleType = arguments.getInt("signType");
                    this.cateJSON = arguments.getString("cateJSON");
                    this.mAlreadyFollowed = arguments.getBoolean(JshopConst.FOLLOWED_KEY);
                }
                if (arguments == null || this.shopId == null || this.venderId == null) {
                    Log.d(TAG, "\u53c2\u6570\u4f20\u9012\u4e0d\u5b8c\u6574\uff0c\u7ed3\u675f\u5f53\u524d\u9875\u9762");
                    Toast.makeText(getActivity(), "\u5f53\u524d\u4e0d\u80fd\u7b7e\u5230", 0).show();
                    getActivity().finish();
                }
                convertValue(this.venderId);
                setShopId(this.shopId);
                initView(view);
                getSignInfoRequest();
            }

            public void setShareLinkInterface(ShareLinkInterface shareLinkInterface) {
                this.shareLinkInterface = shareLinkInterface;
            }

            public void showFollowedDialog(String str, CharSequence charSequence) {
                this.mBtnLeftInfo = getString(R.string.jshop_cancle_fallow);
                final JDCheckDialog createJdDialogWithStyle6 = JDDialogFactory.getInstance().createJdDialogWithStyle6(this.mActivity, str, charSequence, this.mBtnLeftInfo, "\u6211\u77e5\u9053\u4e86");
                createJdDialogWithStyle6.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.29
                    {
                        JShopSignFragment.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        JDMtaUtils.sendCommonData(JShopSignFragment.this.mActivity, "ShopCheckIn_ALaSou", "", "", JShopSignFragment.this.mActivity, JShopSignFragment.this.shopId, "", "", "ShopCheckIn_ShopCheckInMain", JShopSignFragment.this.shopId);
                        JShopSignFragment jShopSignFragment = JShopSignFragment.this;
                        if (jShopSignFragment.activityRuleType == 0) {
                            jShopSignFragment.getSignInfo();
                        }
                        JShopSignFragment.this.cancleFollowShop();
                        createJdDialogWithStyle6.dismiss();
                    }
                });
                createJdDialogWithStyle6.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.30
                    {
                        JShopSignFragment.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        JDMtaUtils.sendCommonData(JShopSignFragment.this.mActivity, "ShopCheckIn_ALaSou", "", "", JShopSignFragment.this.mActivity, JShopSignFragment.this.shopId, "", "", "ShopCheckIn_ShopCheckInMain", JShopSignFragment.this.shopId);
                        JShopSignFragment jShopSignFragment = JShopSignFragment.this;
                        if (jShopSignFragment.activityRuleType == 0) {
                            jShopSignFragment.getSignInfo();
                        }
                        createJdDialogWithStyle6.dismiss();
                    }
                });
                createJdDialogWithStyle6.show();
            }

            public JDCheckDialog showSelfSharePriceDialog(Context context, String str, CharSequence charSequence, String str2, String str3) throws IllegalArgumentException {
                if (context != null) {
                    if (!TextUtils.isEmpty(str)) {
                        if (!TextUtils.isEmpty(str2)) {
                            if (!TextUtils.isEmpty(str3)) {
                                final JDCheckDialog jDCheckDialog = new JDCheckDialog(context);
                                jDCheckDialog.setContentView(R.layout.jshop_sign_selfshare_price_dialog_style);
                                TextView textView = (TextView) jDCheckDialog.findViewById(R.id.bv);
                                jDCheckDialog.titleView = textView;
                                textView.setText(str);
                                jDCheckDialog.messageView = (TextView) jDCheckDialog.findViewById(R.id.bn);
                                jDCheckDialog.setMessage(charSequence);
                                String extraPoint = getExtraPoint();
                                TextView textView2 = (TextView) jDCheckDialog.findViewById(R.id.jd_dialog_message_hint);
                                if (!TextUtils.isEmpty(extraPoint)) {
                                    jDCheckDialog.findViewById(R.id.jd_dialog_message_hint2).setVisibility(0);
                                    textView2.setVisibility(0);
                                    textView2.setText(Html.fromHtml("<font color=#F15353>" + extraPoint + "</font>\uff0c"));
                                } else {
                                    textView2.setVisibility(8);
                                    jDCheckDialog.findViewById(R.id.jd_dialog_message_hint2).setVisibility(8);
                                }
                                TextView textView3 = (TextView) jDCheckDialog.findViewById(R.id.jd_dialog_tips);
                                if (!TextUtils.isEmpty(this.selfPriceTips)) {
                                    textView3.setText(this.selfPriceTips);
                                }
                                jDCheckDialog.messageView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.36
                                    {
                                        JShopSignFragment.this = this;
                                    }

                                    @Override // android.view.View.OnClickListener
                                    public void onClick(View view) {
                                        Log.d(JShopSignFragment.TAG, "start product detail");
                                        if (TextUtils.isEmpty(JShopSignFragment.this.wareId)) {
                                            return;
                                        }
                                        try {
                                            s.i(JShopSignFragment.this.mActivity, Long.valueOf(Long.parseLong(JShopSignFragment.this.wareId)), "", null);
                                        } catch (Exception e2) {
                                            e2.printStackTrace();
                                        }
                                    }
                                });
                                this.isEnter = false;
                                jDCheckDialog.messageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopSignFragment.37
                                    {
                                        JShopSignFragment.this = this;
                                    }

                                    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                                    public void onGlobalLayout() {
                                        if (JShopSignFragment.this.isEnter) {
                                            return;
                                        }
                                        JShopSignFragment.this.isEnter = true;
                                        Log.d(JShopSignFragment.TAG, "onGlobalLayout = " + jDCheckDialog.messageView.getLineCount());
                                        try {
                                            JShopSignFragment.this.handlerText(jDCheckDialog.messageView);
                                        } catch (Exception e2) {
                                            e2.printStackTrace();
                                            jDCheckDialog.messageView.setText(JShopSignFragment.this.getString(R.string.home_icon_useless));
                                        }
                                    }
                                });
                                Button button = (Button) jDCheckDialog.findViewById(R.id.br);
                                jDCheckDialog.posButton = button;
                                button.setText(str2);
                                jDCheckDialog.useCancelClickEvent(jDCheckDialog.posButton);
                                Button button2 = (Button) jDCheckDialog.findViewById(R.id.bq);
                                jDCheckDialog.negButton = button2;
                                button2.setText(str3);
                                jDCheckDialog.useCancelClickEvent(jDCheckDialog.negButton);
                                return jDCheckDialog;
                            }
                            throw new IllegalArgumentException("the param rightButtonText can not be empty in this dialog style");
                        }
                        throw new IllegalArgumentException("the param leftButtonText can not be empty in this dialog style");
                    }
                    throw new IllegalArgumentException("the param title can not be empty in this dialog style");
                }
                throw new IllegalArgumentException("the param context can not be null in this dialog style");
            }
        }
