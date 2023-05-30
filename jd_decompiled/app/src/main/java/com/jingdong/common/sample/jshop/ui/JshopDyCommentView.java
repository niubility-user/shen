package com.jingdong.common.sample.jshop.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.common.sample.jshop.Entity.DynamicShopActivity;
import com.jingdong.common.sample.jshop.Entity.JShopShareInfo;
import com.jingdong.common.sample.jshop.Entity.JshopComment;
import com.jingdong.common.sample.jshop.JShopDynamicDetailActivity;
import com.jingdong.common.sample.jshop.adapter.JshopDynaCommentAdapter;
import com.jingdong.common.sample.jshop.ui.XListView;
import com.jingdong.common.sample.jshop.utils.IDialogClickListener;
import com.jingdong.common.sample.jshop.utils.JShopDynaDialogUtils;
import com.jingdong.common.sample.jshop.utils.JShopUtil;
import com.jingdong.common.sample.jshop.utils.JshopTextViewUtils;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.ClickConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.widget.ToastUtils;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JshopDyCommentView extends RelativeLayout implements View.OnClickListener, AdapterView.OnItemClickListener, TextWatcher, AdapterView.OnItemLongClickListener {
    public static final int JSHOP_COMMENT_HIDEN_INPUT = 3;
    private static final int JSHOP_COMMMENT_REPLY = 2;
    public static final int JSHOP_DYNAMIC_COMMENT = 1;
    public static final int JSHOP_DYNAMIC_DEFAULT = 0;
    public static final int JSHOP_DYNAMIC_NO_COMMENT = 2;
    private static final int JSHOP_TO_COMMMENT = 1;
    private static final String TAG = "JshopCommentInputView";
    private boolean hasNextPage;
    private boolean isDoCommentReqing;
    public boolean isNeedShowSoftInput;
    private boolean isSetData;
    protected long lastTimes;
    private MyActivity mActivity;
    private JshopDynaCommentAdapter mAdapter;
    private Bundle mBundle;
    public int mCommentType;
    private View mCommentView;
    private long mCurRepleyId;
    private JShopDynaDialogUtils mDialogUtils;
    private LinearLayout mFloatView;
    private JshopCommentInputView mInputView;
    private long mLastCid;
    private XListView mListView;
    private int mPage;
    private int mPageSize;
    private String mReplayStr;
    public double mRiskLvl;
    private DynamicShopActivity mShopDyDetailData;
    public String mShopId;
    public int mState;
    private String mTmpCommentInfo;
    private long mTmpRid;
    private String mTmpStr;
    public int mTotalCount;
    public int mTotalPage;
    private View mView;
    private Object transParam;

    /* renamed from: com.jingdong.common.sample.jshop.ui.JshopDyCommentView$5 */
    /* loaded from: classes6.dex */
    public class AnonymousClass5 implements Runnable {
        AnonymousClass5() {
            JshopDyCommentView.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            String str = "";
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("activityId", JshopDyCommentView.this.mShopDyDetailData.activityId);
                jSONObject.put("venderId", JshopDyCommentView.this.mShopDyDetailData.venderId);
                String obj = JshopDyCommentView.this.mInputView.mEditText.getText().toString();
                if (TextUtils.isEmpty(obj) && !TextUtils.isEmpty(JshopDyCommentView.this.mTmpCommentInfo)) {
                    obj = JshopDyCommentView.this.mTmpCommentInfo;
                    JshopDyCommentView.this.mTmpCommentInfo = "";
                    if (JshopDyCommentView.this.mTmpRid != 0) {
                        JshopDyCommentView jshopDyCommentView = JshopDyCommentView.this;
                        jshopDyCommentView.mCommentType = 2;
                        jshopDyCommentView.mCurRepleyId = jshopDyCommentView.mTmpRid;
                        JshopDyCommentView.this.mTmpRid = 0L;
                    }
                    if (!TextUtils.isEmpty(JshopDyCommentView.this.mTmpStr)) {
                        JshopDyCommentView jshopDyCommentView2 = JshopDyCommentView.this;
                        jshopDyCommentView2.mReplayStr = jshopDyCommentView2.mTmpStr;
                        JshopDyCommentView.this.mTmpStr = "";
                    }
                }
                JshopDyCommentView jshopDyCommentView3 = JshopDyCommentView.this;
                if (jshopDyCommentView3.mCommentType == 2) {
                    jSONObject.put("replyCId", jshopDyCommentView3.mCurRepleyId);
                    if (!TextUtils.isEmpty(obj)) {
                        obj = obj.replace(JshopDyCommentView.this.mReplayStr + "\uff1a", "");
                    }
                    str = "1";
                } else {
                    str = "0";
                }
                jSONObject.put("cType", JshopDyCommentView.this.mCommentType);
                jSONObject.put("cNote", obj);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            MyActivity myActivity = JshopDyCommentView.this.mActivity;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            double d = JshopDyCommentView.this.mRiskLvl;
            sb.append(d == -100.0d ? DYConstants.DY_NULL_STR : Double.valueOf(d));
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            sb.append(JshopDyCommentView.this.mShopDyDetailData.activityId);
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            sb.append(JshopDyCommentView.this.mShopDyDetailData.activityType);
            String sb2 = sb.toString();
            MyActivity myActivity2 = JshopDyCommentView.this.mActivity;
            String str2 = JshopDyCommentView.this.mShopId;
            JDMtaUtils.sendCommonData(myActivity, "ShopDynamicStateDetail_CommentSuccess", sb2, "", myActivity2, str2, "", "", "ShopDynamicStateDetail_Main", str2);
            JshopDyCommentView.this.sendHttpRequest("activityComment", jSONObject, new HttpGroup.OnAllListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopDyCommentView.5.1
                {
                    AnonymousClass5.this = this;
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(final HttpResponse httpResponse) {
                    Log.d(JshopDyCommentView.TAG, " onEnd " + httpResponse.getFastJsonObject());
                    JshopDyCommentView.this.isDoCommentReqing = false;
                    JshopDyCommentView.this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.ui.JshopDyCommentView.5.1.2
                        {
                            AnonymousClass1.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            JDJSONObject optJSONObject = httpResponse.getFastJsonObject().optJSONObject("result");
                            if (optJSONObject != null) {
                                String optString = optJSONObject.optString("msg");
                                boolean optBoolean = optJSONObject.optBoolean("success");
                                String optString2 = optJSONObject.optString("url");
                                if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2)) {
                                    JshopDyCommentView.this.showRealNameDialog(optString, optString2);
                                    return;
                                }
                                JshopDyCommentView jshopDyCommentView4 = JshopDyCommentView.this;
                                jshopDyCommentView4.mState = 0;
                                jshopDyCommentView4.update();
                                JshopDyCommentView.this.resetEdit();
                                if (!TextUtils.isEmpty(optString)) {
                                    ToastUtils.showToastInCenter((Context) JshopDyCommentView.this.mActivity, optBoolean ? (byte) 2 : (byte) 1, optString, 0);
                                }
                                if (optBoolean) {
                                    JshopComment.JshopDynamicComment jshopDynamicComment = new JshopComment.JshopDynamicComment(optJSONObject.optJSONObject("comment"));
                                    if (JshopDyCommentView.this.mAdapter != null) {
                                        JshopDyCommentView.this.mAdapter.addCommentItem(jshopDynamicComment);
                                        JshopDyCommentView.this.mListView.setSelection(0);
                                        JshopDyCommentView jshopDyCommentView5 = JshopDyCommentView.this;
                                        int i2 = jshopDyCommentView5.mTotalCount + 1;
                                        jshopDyCommentView5.mTotalCount = i2;
                                        jshopDyCommentView5.updateCommentCount(i2);
                                    }
                                }
                            }
                        }
                    });
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    Log.d(JshopDyCommentView.TAG, "onError");
                    JshopDyCommentView.this.isDoCommentReqing = false;
                    JshopDyCommentView.this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.ui.JshopDyCommentView.5.1.1
                        {
                            AnonymousClass1.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            ToastUtils.showToastInCenter((Context) JshopDyCommentView.this.mActivity, (byte) 1, "\u8bc4\u8bba\u5931\u8d25", 0);
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
        }
    }

    public JshopDyCommentView(Context context) {
        super(context);
        this.mState = 0;
        this.mPage = 1;
        this.mPageSize = 10;
        this.mDialogUtils = null;
        this.isSetData = false;
        this.hasNextPage = true;
        this.mCommentType = 1;
        this.mReplayStr = "";
        this.mTmpCommentInfo = "";
        this.mTmpRid = 0L;
        this.mTmpStr = "";
        this.mLastCid = 0L;
        this.mTotalCount = -1;
        this.mTotalPage = 1;
        this.mShopId = "";
        this.mRiskLvl = -100.0d;
        this.isNeedShowSoftInput = false;
        this.isDoCommentReqing = false;
        this.mActivity = (MyActivity) context;
        initUI();
    }

    static /* synthetic */ int access$308(JshopDyCommentView jshopDyCommentView) {
        int i2 = jshopDyCommentView.mPage;
        jshopDyCommentView.mPage = i2 + 1;
        return i2;
    }

    static /* synthetic */ int access$310(JshopDyCommentView jshopDyCommentView) {
        int i2 = jshopDyCommentView.mPage;
        jshopDyCommentView.mPage = i2 - 1;
        return i2;
    }

    public void showRealNameDialog(String str, final String str2) {
        final JDDialog createJdDialogWithStyle2 = JDDialogFactory.getInstance().createJdDialogWithStyle2(this.mActivity, str, "\u53d6\u6d88", "\u53bb\u7ed1\u5b9a");
        createJdDialogWithStyle2.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopDyCommentView.8
            {
                JshopDyCommentView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                createJdDialogWithStyle2.dismiss();
            }
        });
        createJdDialogWithStyle2.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopDyCommentView.9
            {
                JshopDyCommentView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JShopUtil.toWebWithLogin(JshopDyCommentView.this.mActivity, str2);
                createJdDialogWithStyle2.dismiss();
            }
        });
        createJdDialogWithStyle2.show();
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        if (TextUtils.isEmpty(this.mReplayStr)) {
            return;
        }
        String obj = editable.toString();
        Log.d(TAG, " boolean = " + obj.equals(this.mReplayStr));
        if (obj.equals(this.mReplayStr)) {
            this.mInputView.mEditText.setText("");
            this.mCommentType = 1;
            this.mLastCid = 0L;
        }
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }

    public void checkKeyboardState() {
        post(new Runnable() { // from class: com.jingdong.common.sample.jshop.ui.JshopDyCommentView.12
            {
                JshopDyCommentView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                boolean z = JshopDyCommentView.this.mActivity instanceof JShopDynamicDetailActivity ? ((JShopDynamicDetailActivity) JshopDyCommentView.this.mActivity).isShowKeyboard : false;
                JshopDyCommentView jshopDyCommentView = JshopDyCommentView.this;
                if (((!jshopDyCommentView.isNeedShowSoftInput || jshopDyCommentView.mTotalCount > 0) && !z) || jshopDyCommentView.mInputView == null || JshopDyCommentView.this.mInputView.inputTxt == null) {
                    return;
                }
                JshopDyCommentView.this.mInputView.inputTxt.performClick();
                JshopDyCommentView jshopDyCommentView2 = JshopDyCommentView.this;
                jshopDyCommentView2.isNeedShowSoftInput = false;
                ((JShopDynamicDetailActivity) jshopDyCommentView2.mActivity).isShowKeyboard = false;
            }
        });
    }

    public void delComment(final JshopComment.JshopDynamicComment jshopDynamicComment) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("cid", jshopDynamicComment.cId);
            sendHttpRequest("delComment", jSONObject, new HttpGroup.OnAllListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopDyCommentView.6
                {
                    JshopDyCommentView.this = this;
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(final HttpResponse httpResponse) {
                    Log.d(JshopDyCommentView.TAG, "onEnd = " + httpResponse.toString());
                    JshopDyCommentView.this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.ui.JshopDyCommentView.6.2
                        {
                            AnonymousClass6.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            JshopDyCommentView.this.mAdapter.delComment(jshopDynamicComment);
                            JshopDyCommentView jshopDyCommentView = JshopDyCommentView.this;
                            int i2 = jshopDyCommentView.mTotalCount - 1;
                            jshopDyCommentView.mTotalCount = i2;
                            jshopDyCommentView.updateCommentCount(i2);
                            String optString = httpResponse.getFastJsonObject().optString("msg");
                            if (TextUtils.isEmpty(optString)) {
                                return;
                            }
                            ToastUtils.showToastInCenter((Context) JshopDyCommentView.this.mActivity, (byte) 1, optString, 0);
                        }
                    });
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    Log.d(JshopDyCommentView.TAG, "onError = " + httpError.toString());
                    JshopDyCommentView.this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.ui.JshopDyCommentView.6.1
                        {
                            AnonymousClass6.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            JshopDyCommentView.this.showToast("\u5220\u9664\u8bc4\u8bba\u5931\u8d25");
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
            MyActivity myActivity = this.mActivity;
            JDMtaUtils.sendCommonData(myActivity, "ShopDynamicStateDetail_DeleteComment", "", "", myActivity, "", "", "", "ShopDynamicStateDetail_Main", this.mShopId);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void doCommentReq() {
        if (this.isDoCommentReqing) {
            return;
        }
        this.isDoCommentReqing = true;
        if (!LoginUserBase.hasLogin()) {
            this.mTmpCommentInfo = this.mInputView.mEditText.getText().toString();
            this.mTmpRid = this.mCurRepleyId;
            this.mTmpStr = this.mReplayStr;
        }
        LoginUserHelper.getInstance().executeLoginRunnable(this.mActivity, new AnonymousClass5());
    }

    public void enterAndShowInputSoft() {
        if (getVisibility() == 0) {
            Log.d(TAG, "has already entered!!");
            return;
        }
        setVisibility(0);
        this.isNeedShowSoftInput = true;
        checkKeyboardState();
    }

    public void getCommentList() {
        if (this.mShopDyDetailData == null) {
            Log.d(TAG, "mShopDyDetailData is null,return");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("activityId", this.mShopDyDetailData.activityId);
            jSONObject.put("venderId", this.mShopDyDetailData.venderId);
            jSONObject.put("pageIdx", this.mPage);
            jSONObject.put("pageSize", this.mPageSize);
            Object obj = this.transParam;
            if (obj != null) {
                jSONObject.put("transParam", obj);
            }
            sendHttpRequest("getCommentPage", jSONObject, new HttpGroup.OnAllListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopDyCommentView.4
                {
                    JshopDyCommentView.this = this;
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(final HttpResponse httpResponse) {
                    JshopDyCommentView.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.ui.JshopDyCommentView.4.2
                        {
                            AnonymousClass4.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            Log.d(JshopDyCommentView.TAG, " onEnd " + httpResponse.getFastJsonObject());
                            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                            if (fastJsonObject != null) {
                                JshopDyCommentView.this.hasNextPage = fastJsonObject.optBoolean("hasNext");
                                JshopComment jshopComment = new JshopComment(fastJsonObject);
                                JshopDyCommentView jshopDyCommentView = JshopDyCommentView.this;
                                jshopDyCommentView.mTotalCount = jshopComment.totalCount;
                                jshopDyCommentView.mTotalPage = jshopComment.totalPage;
                                jshopDyCommentView.mRiskLvl = jshopComment.riskLvl.doubleValue();
                                JshopDyCommentView.this.mAdapter.mNotice = jshopComment.notice;
                                JshopDyCommentView.this.checkKeyboardState();
                                List<JshopComment.JshopDynamicComment> list = jshopComment.mList;
                                if (list != null && !list.isEmpty()) {
                                    JshopDyCommentView.this.mCommentView.setVisibility(0);
                                    JshopDynaCommentAdapter jshopDynaCommentAdapter = JshopDyCommentView.this.mAdapter;
                                    JshopDyCommentView jshopDyCommentView2 = JshopDyCommentView.this;
                                    jshopDynaCommentAdapter.mTotalCount = jshopDyCommentView2.mTotalCount;
                                    jshopDyCommentView2.mAdapter.setData(jshopComment.mList);
                                }
                                JshopDyCommentView jshopDyCommentView3 = JshopDyCommentView.this;
                                jshopDyCommentView3.updateCommentCount(jshopDyCommentView3.mTotalCount);
                                if (JshopDyCommentView.this.mListView != null) {
                                    JshopDyCommentView.this.mListView.stopLoadMore();
                                }
                                JshopDyCommentView.this.transParam = fastJsonObject.optJSONObject("transParam");
                            }
                            if (JshopDyCommentView.this.mPage == 1 && JshopDyCommentView.this.mActivity != null && (JshopDyCommentView.this.mActivity instanceof JShopDynamicDetailActivity)) {
                                ((JShopDynamicDetailActivity) JshopDyCommentView.this.mActivity).setCommentNetStatus(1);
                            }
                        }
                    });
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    JshopDyCommentView.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.ui.JshopDyCommentView.4.1
                        {
                            AnonymousClass4.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            Log.d(JshopDyCommentView.TAG, "onError");
                            if (JshopDyCommentView.this.mPage == 1 && JshopDyCommentView.this.mActivity != null && (JshopDyCommentView.this.mActivity instanceof JShopDynamicDetailActivity)) {
                                ((JShopDynamicDetailActivity) JshopDyCommentView.this.mActivity).setCommentNetStatus(-1);
                            }
                            JshopDyCommentView.access$310(JshopDyCommentView.this);
                            if (JshopDyCommentView.this.mListView != null) {
                                JshopDyCommentView.this.mListView.stopLoadMore();
                            }
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
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void handlerData(JSONObject jSONObject) {
    }

    public void initUI() {
        this.mView = ImageUtil.inflate((int) R.layout.jshop_dynamic_comment_layout, (ViewGroup) this, true);
        this.mDialogUtils = new JShopDynaDialogUtils(this.mActivity);
        this.mFloatView = (LinearLayout) findViewById(R.id.float_layout);
        if (JshopTextViewUtils.IS_LARGE_MODE) {
            JshopTextViewUtils.getInstance().doTextFontScaled(findViewById(R.id.float_text));
        }
        this.mFloatView.setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopDyCommentView.2
            {
                JshopDyCommentView.this = this;
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                JshopDyCommentView jshopDyCommentView = JshopDyCommentView.this;
                jshopDyCommentView.mState = 0;
                jshopDyCommentView.update();
                return true;
            }
        });
        this.mCommentView = findViewById(R.id.comment_view);
        XListView xListView = (XListView) this.mView.findViewById(R.id.jshop_comment_list);
        this.mListView = xListView;
        xListView.setPullRefreshEnable(true);
        this.mListView.setPullLoadEnable(true);
        this.mListView.setShowHeader(false);
        this.mListView.setFooterHintViewVisibility(false);
        this.mListView.setNeedProgressBar(true);
        this.mListView.setXListViewListener(new XListView.IXListViewListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopDyCommentView.3
            {
                JshopDyCommentView.this = this;
            }

            @Override // com.jingdong.common.sample.jshop.ui.XListView.IXListViewListener
            public void onLoadMore() {
                if (JshopDyCommentView.this.isRepeat()) {
                    JshopDyCommentView.this.mListView.stopLoadMore();
                    Log.d(JshopDyCommentView.TAG, "load more return");
                } else if (!JshopDyCommentView.this.hasNextPage) {
                    JshopDyCommentView.this.mListView.stopLoadMore();
                } else {
                    JshopDyCommentView.access$308(JshopDyCommentView.this);
                    int i2 = JshopDyCommentView.this.mPage;
                    JshopDyCommentView jshopDyCommentView = JshopDyCommentView.this;
                    if (i2 > jshopDyCommentView.mTotalPage) {
                        jshopDyCommentView.mListView.stopLoadMore();
                    } else {
                        jshopDyCommentView.getCommentList();
                    }
                }
            }

            @Override // com.jingdong.common.sample.jshop.ui.XListView.IXListViewListener
            public void onRefresh() {
                Log.d(JshopDyCommentView.TAG, "  ====  XListView  onRefresh  ====  ");
                JshopDyCommentView.this.mListView.stopRefresh();
                if (JshopDyCommentView.this.mActivity != null) {
                    JshopDyCommentView.this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.ui.JshopDyCommentView.3.1
                        {
                            AnonymousClass3.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            if (JshopDyCommentView.this.mActivity instanceof JShopDynamicDetailActivity) {
                                ((JShopDynamicDetailActivity) JshopDyCommentView.this.mActivity).pushCommentView();
                            }
                            JshopDyCommentView jshopDyCommentView = JshopDyCommentView.this;
                            jshopDyCommentView.mState = 0;
                            jshopDyCommentView.update();
                        }
                    });
                }
            }
        });
        this.mListView.setOnItemClickListener(this);
        this.mListView.setOnItemLongClickListener(this);
        JshopDynaCommentAdapter jshopDynaCommentAdapter = new JshopDynaCommentAdapter(this.mActivity);
        this.mAdapter = jshopDynaCommentAdapter;
        this.mListView.setAdapter((ListAdapter) jshopDynaCommentAdapter);
    }

    public boolean isCanComment(String str) {
        if (this.mCommentType == 2) {
            if (str.length() == this.mReplayStr.length() + 1) {
                Log.d(TAG, "flag = false !!");
                return false;
            }
            Log.d(TAG, "flag = true !!");
            return true;
        }
        return true ^ TextUtils.isEmpty(str.trim());
    }

    protected boolean isRepeat() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.lastTimes < 1500) {
            return true;
        }
        this.lastTimes = currentTimeMillis;
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(final View view) {
        String string;
        String string2;
        String string3;
        String string4;
        switch (view.getId()) {
            case R.id.comment_btn /* 2131690662 */:
                Log.d(TAG, "\u8bc4\u8bba");
                String str = ((Object) this.mInputView.mEditText.getText()) + "";
                if (isCanComment(str.trim())) {
                    doCommentReq();
                    return;
                }
                this.mInputView.mEditText.setText(str.trim());
                EditText editText = this.mInputView.mEditText;
                editText.setSelection(editText.getText().length());
                this.mInputView.mEditText.requestFocus();
                return;
            case R.id.comment_share /* 2131690672 */:
            case R.id.jshop_dy_share /* 2131693919 */:
                Log.d(TAG, "comment_share");
                JShopShareInfo jShopShareInfo = this.mShopDyDetailData.shareInfo;
                if (jShopShareInfo != null) {
                    string = jShopShareInfo.title;
                    string2 = jShopShareInfo.desc;
                    string3 = jShopShareInfo.image;
                    string4 = jShopShareInfo.url;
                } else {
                    string = getResources().getString(R.string.jshop_dynamic_share_title);
                    string2 = getResources().getString(R.string.jshop_dynamic_share_content);
                    string3 = getResources().getString(R.string.jshop_dynamic_share_image);
                    string4 = getResources().getString(R.string.jshop_dynamic_share_url);
                }
                String str2 = string2;
                ShareUtil.panel(this.mActivity, new ShareInfo(string, str2, str2, string4, "", ClickConstant.CLICK_SHARE_VALUE_SHOP, string3, null));
                view.postDelayed(new Runnable() { // from class: com.jingdong.common.sample.jshop.ui.JshopDyCommentView.7
                    {
                        JshopDyCommentView.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        view.setEnabled(true);
                    }
                }, 1000L);
                MyActivity myActivity = this.mActivity;
                String str3 = this.mShopId;
                JDMtaUtils.sendCommonData(this.mActivity, "ShopDynamicStateDetail_Share", string4 + "_0", "", myActivity, str3, "", "", "ShopDynamicStateDetail_Main", str3);
                return;
            case R.id.input_txt /* 2131693128 */:
                Log.d(TAG, "input_txt");
                MyActivity myActivity2 = this.mActivity;
                if (myActivity2 != null && (myActivity2 instanceof JShopDynamicDetailActivity)) {
                    ((JShopDynamicDetailActivity) myActivity2).pullCommentView();
                }
                if (this.mCommentType == 2) {
                    resetEdit();
                    this.mCommentType = 1;
                }
                this.mState = 1;
                update();
                return;
            case R.id.to_comment_view /* 2131694540 */:
                Log.d(TAG, "to_comment_view");
                JDMtaUtils.sendCommonData(this.mActivity, "ShopDynamicState_Comment", "\u52a8\u6001\u8be6\u60c5_" + this.mShopDyDetailData.activityId + "_0", "", this.mActivity, this.mShopId + "", "", "", "ShopDynamicState_Main", this.mShopId + "");
                MyActivity myActivity3 = this.mActivity;
                if (myActivity3 instanceof JShopDynamicDetailActivity) {
                    ((JShopDynamicDetailActivity) myActivity3).pullOrPushCommentView();
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [android.widget.Adapter] */
    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        final JshopComment.JshopDynamicComment jshopDynamicComment;
        Log.d(TAG, "onItemClick");
        JshopDynaCommentAdapter jshopDynaCommentAdapter = this.mAdapter;
        if (jshopDynaCommentAdapter == null || jshopDynaCommentAdapter.isEmptyView() || (jshopDynamicComment = (JshopComment.JshopDynamicComment) adapterView.getAdapter().getItem(i2)) == null) {
            return;
        }
        if (jshopDynamicComment.mySelf) {
            this.mDialogUtils.showCancelDialog("\u5220\u9664", "\u8fd4\u56de", DPIUtil.dip2px(49.0f), new IDialogClickListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopDyCommentView.10
                {
                    JshopDyCommentView.this = this;
                }

                @Override // com.jingdong.common.sample.jshop.utils.IDialogClickListener
                public void onCancel() {
                    Log.d(JshopDyCommentView.TAG, "onCancel");
                }

                @Override // com.jingdong.common.sample.jshop.utils.IDialogClickListener
                public void onConfirm() {
                    Log.d(JshopDyCommentView.TAG, "onConfirm");
                    JshopDyCommentView.this.delComment(jshopDynamicComment);
                }
            });
            return;
        }
        this.mCommentType = 2;
        long j3 = jshopDynamicComment.cId;
        this.mCurRepleyId = j3;
        long j4 = this.mLastCid;
        if (j4 == 0 || j4 != j3) {
            this.mReplayStr = this.mActivity.getResources().getString(R.string.jshop_comment_replay, jshopDynamicComment.userName);
            this.mInputView.mEditText.setText(this.mReplayStr + "\uff1a");
        }
        this.mState = 1;
        EditText editText = this.mInputView.mEditText;
        editText.setSelection(editText.getText().length());
        this.mInputView.mEditText.requestFocus();
        update();
        this.mLastCid = jshopDynamicComment.cId;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [android.widget.Adapter] */
    @Override // android.widget.AdapterView.OnItemLongClickListener
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        final JshopComment.JshopDynamicComment jshopDynamicComment;
        Log.d(TAG, "========onItemLongClick==========");
        JshopDynaCommentAdapter jshopDynaCommentAdapter = this.mAdapter;
        if (jshopDynaCommentAdapter == null || jshopDynaCommentAdapter.isEmptyView() || (jshopDynamicComment = (JshopComment.JshopDynamicComment) adapterView.getAdapter().getItem(i2)) == null || !jshopDynamicComment.mySelf) {
            return false;
        }
        this.mDialogUtils.showCancelDialog("\u5220\u9664", "\u8fd4\u56de", DPIUtil.dip2px(49.0f), new IDialogClickListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopDyCommentView.11
            {
                JshopDyCommentView.this = this;
            }

            @Override // com.jingdong.common.sample.jshop.utils.IDialogClickListener
            public void onCancel() {
                Log.d(JshopDyCommentView.TAG, "onCancel");
            }

            @Override // com.jingdong.common.sample.jshop.utils.IDialogClickListener
            public void onConfirm() {
                Log.d(JshopDyCommentView.TAG, "onConfirm");
                JshopDyCommentView.this.delComment(jshopDynamicComment);
            }
        });
        return true;
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }

    public void resetEdit() {
        this.mReplayStr = "";
        this.mLastCid = 0L;
        this.mCurRepleyId = 0L;
        this.mCommentType = 1;
        this.mInputView.mEditText.setText("");
    }

    public void sendHttpRequest(String str, JSONObject jSONObject, HttpGroup.OnAllListener onAllListener) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(Configuration.getJshopHost());
        if (this.mPage <= 1) {
            httpSetting.setEffect(1);
        } else {
            httpSetting.setEffect(0);
        }
        httpSetting.setFunctionId(str);
        httpSetting.setJsonParams(jSONObject);
        httpSetting.setNotifyUser(false);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setUseCookies(true);
        httpSetting.setListener(onAllListener);
        MyActivity myActivity = this.mActivity;
        if (myActivity != null) {
            myActivity.getHttpGroupaAsynPool().add(httpSetting);
        }
    }

    public void setBundle(Bundle bundle) {
        this.mBundle = bundle;
        if (bundle != null) {
            String string = bundle.getString("venderId");
            String string2 = this.mBundle.getString("activityId");
            if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
                return;
            }
            JDJSONObject jDJSONObject = new JDJSONObject();
            try {
                jDJSONObject.put("venderId", (Object) string);
                jDJSONObject.put("activityId", (Object) Long.valueOf(Long.parseLong(string2)));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.isSetData = true;
            this.mShopDyDetailData = new DynamicShopActivity(jDJSONObject);
            getCommentList();
        }
    }

    public void setDetailData(DynamicShopActivity dynamicShopActivity) {
        this.mShopDyDetailData = dynamicShopActivity;
        this.mInputView.updateViewInfo(dynamicShopActivity);
        if (dynamicShopActivity == null) {
            return;
        }
        this.mShopId = dynamicShopActivity.shopId + "";
        int i2 = this.mTotalCount;
        if (i2 != 0) {
            dynamicShopActivity.commentCount = i2;
        }
        if (!this.isSetData) {
            getCommentList();
        } else {
            checkKeyboardState();
        }
        if ("0".equals(dynamicShopActivity.commentSwitch)) {
            this.mState = 2;
            update();
        } else if (this.mTotalCount != 0) {
            if ("0".equals(dynamicShopActivity.commentSwitch)) {
                this.mState = 2;
            } else {
                this.mState = 0;
            }
            update();
        }
    }

    public void setInputView(JshopCommentInputView jshopCommentInputView) {
        this.mInputView = jshopCommentInputView;
        jshopCommentInputView.setMyOnClickListener(this);
        this.mInputView.mEditText.setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopDyCommentView.1
            {
                JshopDyCommentView.this = this;
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                JshopDyCommentView jshopDyCommentView = JshopDyCommentView.this;
                jshopDyCommentView.mState = 1;
                jshopDyCommentView.update();
                return false;
            }
        });
        this.mInputView.mEditText.addTextChangedListener(this);
        resetEdit();
    }

    public void showInput(boolean z) {
        if (z) {
            LinearLayout linearLayout = this.mFloatView;
            if (linearLayout != null && linearLayout.getVisibility() == 8) {
                this.mFloatView.setVisibility(0);
            }
            EditText editText = this.mInputView.mEditText;
            if (editText != null) {
                editText.setFocusable(true);
                this.mInputView.mEditText.setFocusableInTouchMode(true);
                this.mInputView.mEditText.requestFocus();
                this.mInputView.showSoftInput();
                return;
            }
            return;
        }
        LinearLayout linearLayout2 = this.mFloatView;
        if (linearLayout2 != null && linearLayout2.getVisibility() == 0) {
            this.mFloatView.setVisibility(8);
        }
        JshopCommentInputView jshopCommentInputView = this.mInputView;
        if (jshopCommentInputView != null) {
            jshopCommentInputView.hidenSoftInput();
        }
    }

    public void showToast(String str) {
        MyActivity myActivity = this.mActivity;
        if (myActivity != null) {
            Toast.makeText(myActivity, str, 0).show();
        }
    }

    public void update() {
        Log.d(TAG, "mState = " + this.mState);
        if (!isCanComment()) {
            this.mState = 2;
        }
        int i2 = this.mState;
        if (i2 == 0) {
            this.mInputView.type1Lay.setVisibility(0);
            this.mInputView.type2Lay.setVisibility(8);
            this.mInputView.type3Lay.setVisibility(8);
            showInput(false);
        } else if (i2 == 1) {
            this.mInputView.type1Lay.setVisibility(8);
            this.mInputView.type2Lay.setVisibility(0);
            this.mInputView.type3Lay.setVisibility(8);
            showInput(true);
        } else if (i2 == 2) {
            this.mInputView.type1Lay.setVisibility(8);
            this.mInputView.type2Lay.setVisibility(8);
            this.mInputView.type3Lay.setVisibility(0);
            showInput(false);
        } else if (i2 != 3) {
        } else {
            this.mInputView.type1Lay.setVisibility(8);
            this.mInputView.type2Lay.setVisibility(0);
            this.mInputView.type3Lay.setVisibility(8);
            showInput(false);
        }
    }

    public void updateCommentCount(int i2) {
        JshopCommentInputView jshopCommentInputView = this.mInputView;
        if (jshopCommentInputView != null) {
            jshopCommentInputView.updateCommentCount(i2);
        }
        JshopDynaCommentAdapter jshopDynaCommentAdapter = this.mAdapter;
        if (jshopDynaCommentAdapter != null) {
            jshopDynaCommentAdapter.updateListSize(i2);
        }
    }

    public boolean isCanComment() {
        DynamicShopActivity dynamicShopActivity = this.mShopDyDetailData;
        return (dynamicShopActivity == null || "0".equals(dynamicShopActivity.commentSwitch)) ? false : true;
    }

    public JshopDyCommentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mState = 0;
        this.mPage = 1;
        this.mPageSize = 10;
        this.mDialogUtils = null;
        this.isSetData = false;
        this.hasNextPage = true;
        this.mCommentType = 1;
        this.mReplayStr = "";
        this.mTmpCommentInfo = "";
        this.mTmpRid = 0L;
        this.mTmpStr = "";
        this.mLastCid = 0L;
        this.mTotalCount = -1;
        this.mTotalPage = 1;
        this.mShopId = "";
        this.mRiskLvl = -100.0d;
        this.isNeedShowSoftInput = false;
        this.isDoCommentReqing = false;
        this.mActivity = (MyActivity) context;
        initUI();
    }
}
