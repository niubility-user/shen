package com.jingdong.common.sample.jshop.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.common.sample.jshop.Entity.DynamicShopActivity;
import com.jingdong.common.sample.jshop.utils.JshopTextViewUtils;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes6.dex */
public class JshopCommentInputView extends RelativeLayout {
    private static final String TAG = "JshopCommentInputView";
    public TextView commentBtn;
    public TextView commentNum;
    private View commentShare;
    private View commentZan;
    private InputMethodManager imm;
    public TextView inputTxt;
    private MyActivity mActivity;
    private DynamicShopActivity mData;
    public EditText mEditText;
    View.OnClickListener mListener;
    private View mView;
    public TextView noCommentNum;
    private View noCommentShare;
    private View noCommentZan;
    private View toCommentView;
    public View type1Lay;
    public View type2Lay;
    public View type3Lay;
    public TextView zanNum;

    public JshopCommentInputView(Context context) {
        super(context);
        this.mListener = new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopCommentInputView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.comment_zan) {
                    LoginUserHelper.getInstance().executeLoginRunnable(JshopCommentInputView.this.mActivity, new Runnable() { // from class: com.jingdong.common.sample.jshop.ui.JshopCommentInputView.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (JshopCommentInputView.this.mData != null) {
                                JshopCommentInputView jshopCommentInputView = JshopCommentInputView.this;
                                jshopCommentInputView.handleGoodNum(jshopCommentInputView.mData);
                                JshopCommentInputView jshopCommentInputView2 = JshopCommentInputView.this;
                                jshopCommentInputView2.showGoodNum(jshopCommentInputView2.zanNum, jshopCommentInputView2.mData);
                                JshopCommentInputView jshopCommentInputView3 = JshopCommentInputView.this;
                                jshopCommentInputView3.postGoodNum(jshopCommentInputView3.zanNum, jshopCommentInputView3.mData.hadPraised);
                            }
                        }
                    });
                } else if (id != R.id.no_comment_layout) {
                } else {
                    LoginUserHelper.getInstance().executeLoginRunnable(JshopCommentInputView.this.mActivity, new Runnable() { // from class: com.jingdong.common.sample.jshop.ui.JshopCommentInputView.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            JshopCommentInputView jshopCommentInputView = JshopCommentInputView.this;
                            jshopCommentInputView.handleGoodNum(jshopCommentInputView.mData);
                            JshopCommentInputView jshopCommentInputView2 = JshopCommentInputView.this;
                            jshopCommentInputView2.showGoodNum(jshopCommentInputView2.noCommentNum, jshopCommentInputView2.mData);
                            JshopCommentInputView jshopCommentInputView3 = JshopCommentInputView.this;
                            jshopCommentInputView3.postGoodNum(jshopCommentInputView3.noCommentNum, jshopCommentInputView3.mData.hadPraised);
                        }
                    });
                }
            }
        };
        this.mActivity = (MyActivity) context;
        initUI();
    }

    private String getCurPage() {
        return "\u52a8\u6001\u8be6\u60c5";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleGoodNum(DynamicShopActivity dynamicShopActivity) {
        if (dynamicShopActivity == null) {
            return;
        }
        if (dynamicShopActivity.hadPraised) {
            dynamicShopActivity.praiseCount--;
            dynamicShopActivity.hadPraised = false;
        } else {
            dynamicShopActivity.praiseCount++;
            dynamicShopActivity.hadPraised = true;
        }
        MyActivity myActivity = this.mActivity;
        StringBuilder sb = new StringBuilder();
        sb.append(dynamicShopActivity.hadPraised ? "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getCurPage());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(dynamicShopActivity.activityId);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(0);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(dynamicShopActivity.activityType);
        JDMtaUtils.sendCommonData(myActivity, "ShopDynamicState_ThumbIcon", sb.toString(), "", this.mActivity, dynamicShopActivity.shopId + "", "", "", "ShopDynamicState_Main", dynamicShopActivity.shopId + "");
    }

    private void initUI() {
        this.imm = (InputMethodManager) this.mActivity.getSystemService("input_method");
        View inflate = ImageUtil.inflate((int) R.layout.jshop_dyna_input_layout, (ViewGroup) this, true);
        this.mView = inflate;
        this.type1Lay = inflate.findViewById(R.id.comment_type1_layout);
        View findViewById = this.mView.findViewById(R.id.comment_type2_layout);
        this.type2Lay = findViewById;
        findViewById.setVisibility(8);
        View findViewById2 = this.mView.findViewById(R.id.comment_type3_layout);
        this.type3Lay = findViewById2;
        findViewById2.setVisibility(8);
        this.commentBtn = (TextView) findViewById(R.id.comment_btn);
        this.commentNum = (TextView) findViewById(R.id.comment_num);
        this.noCommentNum = (TextView) findViewById(R.id.nocomment_num);
        this.zanNum = (TextView) findViewById(R.id.zan_num);
        this.mEditText = (EditText) findViewById(R.id.input_edit);
        this.inputTxt = (TextView) this.mView.findViewById(R.id.input_txt);
        this.toCommentView = this.mView.findViewById(R.id.to_comment_view);
        this.commentZan = this.mView.findViewById(R.id.comment_zan);
        this.commentShare = this.mView.findViewById(R.id.comment_share);
        this.noCommentShare = this.mView.findViewById(R.id.jshop_dy_share);
        this.noCommentZan = this.mView.findViewById(R.id.no_comment_layout);
        this.commentZan.setOnClickListener(this.mListener);
        this.noCommentZan.setOnClickListener(this.mListener);
        if (JshopTextViewUtils.IS_LARGE_MODE) {
            JshopTextViewUtils jshopTextViewUtils = JshopTextViewUtils.getInstance();
            TextView textView = this.inputTxt;
            jshopTextViewUtils.doTextFontScaled(textView, this.commentBtn, this.mEditText, this.commentNum, this.noCommentNum, this.zanNum, textView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postGoodNum(TextView textView, boolean z) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(Configuration.getJshopHost());
        httpSetting.setFunctionId("updateActivityPraiseCount");
        httpSetting.putJsonParam("activityId", String.valueOf(this.mData.activityId));
        httpSetting.putJsonParam("praise", Boolean.valueOf(z));
        httpSetting.putJsonParam("validTime", Long.valueOf(this.mData.validTime));
        httpSetting.putJsonParam("venderId", Long.valueOf(this.mData.venderId));
        httpSetting.setEffect(0);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setNotifyUser(false);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopCommentInputView.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
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
        this.mActivity.getHttpGroupaAsynPool().add(httpSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCommentNum(TextView textView, DynamicShopActivity dynamicShopActivity) {
        if (dynamicShopActivity == null) {
            return;
        }
        long j2 = dynamicShopActivity.commentCount;
        if (j2 <= 0) {
            textView.setText(getResources().getString(R.string.jshop_comment));
            Drawable drawable = getResources().getDrawable(R.drawable.jshop_dy_comment_icon);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawablePadding(DPIUtil.dip2px(5.0f));
            textView.setCompoundDrawables(drawable, null, null, null);
            textView.setTextColor(getResources().getColor(R.color.jshop_dynamic_comment_color));
            return;
        }
        String str = dynamicShopActivity.commentCounts;
        if (j2 < 10000) {
            str = dynamicShopActivity.commentCount + "";
        } else if (j2 == 10000) {
            str = "1\u4e07";
        }
        textView.setText(str);
        textView.setVisibility(0);
        Drawable drawable2 = getResources().getDrawable(R.drawable.jshop_dy_comment_icon);
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        textView.setCompoundDrawablePadding(DPIUtil.dip2px(5.0f));
        textView.setCompoundDrawables(drawable2, null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showGoodNum(TextView textView, DynamicShopActivity dynamicShopActivity) {
        if (dynamicShopActivity == null) {
            return;
        }
        textView.setVisibility(0);
        long j2 = dynamicShopActivity.praiseCount;
        if (j2 <= 0) {
            textView.setText(getResources().getString(R.string.jshop_like));
            Drawable drawable = getResources().getDrawable(R.drawable.jshop_dy_laud_normal);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawablePadding(DPIUtil.dip2px(5.0f));
            textView.setCompoundDrawables(drawable, null, null, null);
            textView.setTextColor(getResources().getColor(R.color.jshop_dynamic_comment_color));
            return;
        }
        String str = dynamicShopActivity.praiseCounts;
        if (j2 < 10000) {
            str = dynamicShopActivity.praiseCount + "";
        } else if (j2 == 10000) {
            str = "1\u4e07";
        }
        textView.setText(str);
        if (dynamicShopActivity.hadPraised) {
            Drawable drawable2 = getResources().getDrawable(R.drawable.jshop_dy_laud_selected);
            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
            textView.setCompoundDrawablePadding(DPIUtil.dip2px(5.0f));
            textView.setCompoundDrawables(drawable2, null, null, null);
            textView.setTextColor(getResources().getColor(R.color.jshop_dyna_tab));
            return;
        }
        Drawable drawable3 = getResources().getDrawable(R.drawable.jshop_dy_laud_normal);
        drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
        textView.setCompoundDrawablePadding(DPIUtil.dip2px(5.0f));
        textView.setCompoundDrawables(drawable3, null, null, null);
        textView.setTextColor(getResources().getColor(R.color.jshop_dynamic_comment_color));
    }

    public void hidenSoftInput() {
        EditText editText;
        InputMethodManager inputMethodManager = this.imm;
        if (inputMethodManager == null || (editText = this.mEditText) == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public void setMyOnClickListener(View.OnClickListener onClickListener) {
        this.commentBtn.setOnClickListener(onClickListener);
        this.inputTxt.setOnClickListener(onClickListener);
        this.toCommentView.setOnClickListener(onClickListener);
        this.commentShare.setOnClickListener(onClickListener);
        this.noCommentShare.setOnClickListener(onClickListener);
    }

    public void showSoftInput() {
        EditText editText;
        InputMethodManager inputMethodManager = this.imm;
        if (inputMethodManager == null || (editText = this.mEditText) == null) {
            return;
        }
        inputMethodManager.showSoftInput(editText, 0);
    }

    public void updateCommentCount(int i2) {
        if (i2 < 10000) {
            DynamicShopActivity dynamicShopActivity = this.mData;
            if (dynamicShopActivity != null) {
                dynamicShopActivity.commentCount = i2;
            }
            showCommentNum(this.commentNum, i2, i2 + "");
        }
    }

    public void updateViewInfo(final DynamicShopActivity dynamicShopActivity) {
        this.mData = dynamicShopActivity;
        this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.ui.JshopCommentInputView.3
            @Override // java.lang.Runnable
            public void run() {
                JshopCommentInputView jshopCommentInputView = JshopCommentInputView.this;
                jshopCommentInputView.showGoodNum(jshopCommentInputView.zanNum, dynamicShopActivity);
                JshopCommentInputView jshopCommentInputView2 = JshopCommentInputView.this;
                jshopCommentInputView2.showGoodNum(jshopCommentInputView2.noCommentNum, dynamicShopActivity);
                JshopCommentInputView jshopCommentInputView3 = JshopCommentInputView.this;
                jshopCommentInputView3.showCommentNum(jshopCommentInputView3.commentNum, dynamicShopActivity);
                JshopCommentInputView.this.commentZan.setClickable(true);
                JshopCommentInputView.this.noCommentZan.setClickable(true);
            }
        });
    }

    public JshopCommentInputView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mListener = new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopCommentInputView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.comment_zan) {
                    LoginUserHelper.getInstance().executeLoginRunnable(JshopCommentInputView.this.mActivity, new Runnable() { // from class: com.jingdong.common.sample.jshop.ui.JshopCommentInputView.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (JshopCommentInputView.this.mData != null) {
                                JshopCommentInputView jshopCommentInputView = JshopCommentInputView.this;
                                jshopCommentInputView.handleGoodNum(jshopCommentInputView.mData);
                                JshopCommentInputView jshopCommentInputView2 = JshopCommentInputView.this;
                                jshopCommentInputView2.showGoodNum(jshopCommentInputView2.zanNum, jshopCommentInputView2.mData);
                                JshopCommentInputView jshopCommentInputView3 = JshopCommentInputView.this;
                                jshopCommentInputView3.postGoodNum(jshopCommentInputView3.zanNum, jshopCommentInputView3.mData.hadPraised);
                            }
                        }
                    });
                } else if (id != R.id.no_comment_layout) {
                } else {
                    LoginUserHelper.getInstance().executeLoginRunnable(JshopCommentInputView.this.mActivity, new Runnable() { // from class: com.jingdong.common.sample.jshop.ui.JshopCommentInputView.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            JshopCommentInputView jshopCommentInputView = JshopCommentInputView.this;
                            jshopCommentInputView.handleGoodNum(jshopCommentInputView.mData);
                            JshopCommentInputView jshopCommentInputView2 = JshopCommentInputView.this;
                            jshopCommentInputView2.showGoodNum(jshopCommentInputView2.noCommentNum, jshopCommentInputView2.mData);
                            JshopCommentInputView jshopCommentInputView3 = JshopCommentInputView.this;
                            jshopCommentInputView3.postGoodNum(jshopCommentInputView3.noCommentNum, jshopCommentInputView3.mData.hadPraised);
                        }
                    });
                }
            }
        };
        this.mActivity = (MyActivity) context;
        initUI();
    }

    private void showCommentNum(final TextView textView, final int i2, final String str) {
        post(new Runnable() { // from class: com.jingdong.common.sample.jshop.ui.JshopCommentInputView.4
            @Override // java.lang.Runnable
            public void run() {
                int i3 = i2;
                if (i3 <= 0) {
                    textView.setText(JshopCommentInputView.this.getResources().getString(R.string.jshop_comment));
                    Drawable drawable = JshopCommentInputView.this.getResources().getDrawable(R.drawable.jshop_dy_comment_icon);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    textView.setCompoundDrawablePadding(DPIUtil.dip2px(5.0f));
                    textView.setCompoundDrawables(drawable, null, null, null);
                    textView.setTextColor(JshopCommentInputView.this.getResources().getColor(R.color.jshop_dynamic_comment_color));
                    return;
                }
                String str2 = str;
                if (i3 < 10000) {
                    str2 = i2 + "";
                } else if (i3 == 10000) {
                    str2 = "1\u4e07";
                }
                textView.setText(str2);
                textView.setVisibility(0);
                Drawable drawable2 = JshopCommentInputView.this.getResources().getDrawable(R.drawable.jshop_dy_comment_icon);
                drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                textView.setCompoundDrawablePadding(DPIUtil.dip2px(5.0f));
                textView.setCompoundDrawables(drawable2, null, null, null);
            }
        });
    }
}
