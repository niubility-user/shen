package com.jingdong.app.mall.bundle.familyhelper.widget;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.jd.framework.json.JDJSONObject;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.mall.bundle.familyhelper.FamilyHelperActivity;
import com.jingdong.app.mall.bundle.familyhelper.R;
import com.jingdong.app.mall.bundle.familyhelper.util.FamilyHelperConstant;
import com.jingdong.app.mall.bundle.familyhelper.util.FamilyHelperNetUtil;
import com.jingdong.app.mall.bundle.familyhelper.util.StringUtil;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes18.dex */
public class FamilyHelperFloatView extends RelativeLayout {
    private static final String TAG = "FamilyHelperFloatView";
    private String channelId;
    private JDDialog dialog;
    private FhFloatViewListener fhFloatViewListener;
    private Handler handler;
    private int hiddenX;
    private ImageView imgClose;
    private SimpleDraweeView imgIcon;
    private int initX;
    private Intent intent;
    private boolean isBothSide;
    private boolean isHidden;
    private boolean isInitX;
    private boolean isMove;
    private JDJSONObject jsonParam;
    private int lastX;
    private int lastY;
    private Context mContext;
    private int marginBottom;
    private int marginLeft;
    private int marginRight;
    private int marginTop;
    private int parentHeight;
    private int parentWidth;
    private Runnable runnable;
    private int slop;
    private View view;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.bundle.familyhelper.widget.FamilyHelperFloatView$7  reason: invalid class name */
    /* loaded from: classes18.dex */
    public class AnonymousClass7 implements HttpGroup.OnCommonListener {
        AnonymousClass7() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(final HttpResponse httpResponse) {
            FamilyHelperFloatView.this.post(new Runnable() { // from class: com.jingdong.app.mall.bundle.familyhelper.widget.FamilyHelperFloatView.7.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                        String str = "--> getFlowWindowConfig , json = " + fastJsonObject;
                        if (fastJsonObject != null) {
                            JDJSONObject optJSONObject = fastJsonObject.optJSONObject("data");
                            String str2 = "--> getFlowWindowConfig , data = " + optJSONObject;
                            if (optJSONObject != null) {
                                String optString = optJSONObject.optString("imgUrl");
                                if (StringUtil.isEmpty(optString)) {
                                    return;
                                }
                                FamilyHelperFloatView.this.setVisibility(0);
                                FamilyHelperFloatView.this.imgClose.setVisibility(0);
                                FamilyHelperFloatView.this.imgIcon.setVisibility(0);
                                String verifyUri = StringUtil.verifyUri(optString, "http://m.360buyimg.com/mobilecal/jfs/t1/163642/12/13437/4848/604eff54E99cd6a7b/ddc18e5567b12780.png");
                                SimpleDraweeView simpleDraweeView = FamilyHelperFloatView.this.imgIcon;
                                JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
                                int i2 = R.mipmap.icon_float_default;
                                JDImageLoader.display(verifyUri, simpleDraweeView, jDDisplayImageOptions.setPlaceholder(i2).showImageOnFail(i2).showImageOnLoading(i2), (ImageRequestListener<ImageInfo>) null);
                                if (StringUtil.isEmpty(FamilyHelperFloatView.this.channelId)) {
                                    FamilyHelperFloatView familyHelperFloatView = FamilyHelperFloatView.this;
                                    familyHelperFloatView.channelId = familyHelperFloatView.mContext.getClass().toString();
                                }
                                JDMtaUtils.sendExposureDataWithExt(FamilyHelperFloatView.this.mContext, "family_elder_quickChatExpo", "", IMediaPlayer.OnNativeInvokeListener.ARG_FAMILIY, "", "", "{\"source\":\"" + FamilyHelperFloatView.this.channelId + "\"}", null);
                                FamilyHelperFloatView.this.view.post(new Runnable() { // from class: com.jingdong.app.mall.bundle.familyhelper.widget.FamilyHelperFloatView.7.1.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        if (FamilyHelperFloatView.this.getParent() != null) {
                                            ViewGroup viewGroup = (ViewGroup) FamilyHelperFloatView.this.getParent();
                                            FamilyHelperFloatView.this.parentHeight = viewGroup.getHeight();
                                            FamilyHelperFloatView.this.parentWidth = viewGroup.getWidth();
                                        }
                                        FamilyHelperFloatView familyHelperFloatView2 = FamilyHelperFloatView.this;
                                        familyHelperFloatView2.initX = (int) familyHelperFloatView2.getX();
                                        FamilyHelperFloatView familyHelperFloatView3 = FamilyHelperFloatView.this;
                                        familyHelperFloatView3.welt(familyHelperFloatView3.initX);
                                    }
                                });
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            });
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            httpError.printStackTrace();
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* loaded from: classes18.dex */
    public interface FhFloatViewListener {
        void onClose();
    }

    public FamilyHelperFloatView(Context context) {
        super(context);
        this.handler = new Handler();
        initDraw(context, null);
    }

    private void getConfigData() {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("channelId", (Object) this.channelId);
        FamilyHelperNetUtil.sendHttpRequest(null, FamilyHelperConstant.GET_FLOW_WINDOW_CONFIG, jDJSONObject, false, new AnonymousClass7());
    }

    private void handleClick() {
        if (this.isHidden) {
            this.isHidden = false;
            setX(this.initX);
            this.view.setAlpha(1.0f);
            return;
        }
        Bundle bundle = new Bundle();
        JDJSONObject jDJSONObject = this.jsonParam;
        if (jDJSONObject != null) {
            bundle.putString("jsonParam", jDJSONObject.toString());
        }
        if (!StringUtil.isEmpty(this.channelId)) {
            bundle.putString("channelId", this.channelId);
        }
        Intent intent = new Intent(this.mContext, FamilyHelperActivity.class);
        this.intent = intent;
        intent.putExtras(bundle);
        if (!LoginUserBase.hasLogin()) {
            LoginUserHelper.getInstance().executeLoginRunnable((IMyActivity) this.mContext, new Runnable() { // from class: com.jingdong.app.mall.bundle.familyhelper.widget.FamilyHelperFloatView.1
                @Override // java.lang.Runnable
                public void run() {
                }
            });
        } else {
            this.mContext.startActivity(this.intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleTouch(MotionEvent motionEvent) {
        Runnable runnable;
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            this.marginRight = marginLayoutParams.rightMargin;
            this.marginTop = marginLayoutParams.topMargin;
            this.marginLeft = marginLayoutParams.leftMargin;
            this.marginBottom = marginLayoutParams.bottomMargin;
        }
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            this.isMove = false;
            if (!this.isHidden && (runnable = this.runnable) != null) {
                this.handler.removeCallbacks(runnable);
            }
            getParent().requestDisallowInterceptTouchEvent(true);
            this.lastX = rawX;
            this.lastY = rawY;
        } else if (action == 1) {
            if (!this.isMove) {
                handleClick();
            }
            if (!this.isHidden) {
                if (!this.isBothSide) {
                    rawX = this.initX;
                }
                welt(rawX);
            }
        } else if (action == 2) {
            this.isMove = true;
            if (this.parentHeight > 0 && this.parentWidth > 0) {
                int i2 = rawX - this.lastX;
                int i3 = rawY - this.lastY;
                if (Math.ceil(Math.sqrt((i2 * i2) + (i3 * i3))) == 0.0d) {
                    this.isMove = false;
                } else {
                    this.isMove = true;
                    float x = getX() + i2;
                    float y = getY() + i3;
                    if (this.isBothSide) {
                        int i4 = this.marginLeft;
                        if (x < i4) {
                            x = i4;
                        } else if (x > (this.parentWidth - getWidth()) - this.marginRight) {
                            x = (this.parentWidth - getWidth()) - this.marginRight;
                        }
                    } else {
                        x = this.initX;
                        if (this.isHidden) {
                            x += this.hiddenX;
                        }
                    }
                    int i5 = this.marginTop;
                    if (y < i5) {
                        y = i5;
                    } else {
                        float height = getHeight() + y + this.marginBottom;
                        int i6 = this.parentHeight;
                        if (height > i6) {
                            y = (i6 - getHeight()) - this.marginBottom;
                        }
                    }
                    setX(x);
                    setY(y);
                    this.lastX = rawX;
                    this.lastY = rawY;
                }
            } else {
                this.isMove = false;
            }
        }
        return this.isMove;
    }

    private void initDraw(Context context, AttributeSet attributeSet) {
        this.mContext = context;
        setVisibility(8);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.HelperFloatView);
        this.marginTop = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.HelperFloatView_android_layout_marginTop, 0);
        this.marginBottom = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.HelperFloatView_android_layout_marginBottom, 0);
        this.marginRight = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.HelperFloatView_android_layout_marginRight, 0);
        this.marginLeft = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.HelperFloatView_android_layout_marginLeft, 0);
        this.channelId = obtainStyledAttributes.getString(R.styleable.HelperFloatView_channelId);
        this.slop = 1;
        setLayoutParams(new ViewGroup.LayoutParams(DpiUtil.dip2px(this.mContext, 78.0f), DpiUtil.dip2px(this.mContext, 73.0f)));
        View inflate = LayoutInflater.from(context).inflate(R.layout.hepler_float_view_layout, (ViewGroup) null);
        this.view = inflate;
        addView(inflate);
        setFocusableInTouchMode(true);
        this.imgIcon = (SimpleDraweeView) findViewById(R.id.imgIcon);
        ImageView imageView = (ImageView) findViewById(R.id.imgClose);
        this.imgClose = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.familyhelper.widget.FamilyHelperFloatView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FamilyHelperFloatView.this.showNormalDialog();
                if (StringUtil.isEmpty(FamilyHelperFloatView.this.channelId)) {
                    FamilyHelperFloatView familyHelperFloatView = FamilyHelperFloatView.this;
                    familyHelperFloatView.channelId = familyHelperFloatView.mContext.getClass().toString();
                }
                JDMtaUtils.sendClickDataWithExt(FamilyHelperFloatView.this.mContext, "family_elder_quickChatClose", "", "", "family_elder", "", "", "", "{\"source\":\"" + FamilyHelperFloatView.this.channelId + "\"}", null);
            }
        });
        this.imgIcon.setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.app.mall.bundle.familyhelper.widget.FamilyHelperFloatView.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                FamilyHelperFloatView.this.handleTouch(motionEvent);
                return true;
            }
        });
        this.imgClose.setVisibility(8);
        this.imgIcon.setVisibility(8);
        getConfigData();
    }

    private boolean isLeftSide() {
        return getX() == ((float) (0 - this.marginLeft));
    }

    private boolean isRightSide() {
        return getX() == ((float) ((this.parentWidth - getWidth()) - this.marginRight));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNormalDialog() {
        JDDialog createJdDialogWithStyle2 = JDDialogFactory.getInstance().createJdDialogWithStyle2(this.mContext, "\u5173\u95ed\u540e\uff0c\u60a8\u53ef\u5728\u6211\u7684-\u8bbe\u7f6e-\u901a\u7528\n\u518d\u6b21\u6253\u5f00", "\u6211\u518d\u60f3\u60f3", "\u786e\u8ba4\u5173\u95ed");
        this.dialog = createJdDialogWithStyle2;
        createJdDialogWithStyle2.messageView.post(new Runnable() { // from class: com.jingdong.app.mall.bundle.familyhelper.widget.FamilyHelperFloatView.5
            @Override // java.lang.Runnable
            public void run() {
                FamilyHelperFloatView.this.dialog.messageView.setGravity(17);
            }
        });
        this.dialog.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.familyhelper.widget.FamilyHelperFloatView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (FamilyHelperFloatView.this.view != null) {
                    FamilyHelperFloatView.this.setVisibility(8);
                }
                if (FamilyHelperFloatView.this.dialog != null) {
                    FamilyHelperFloatView.this.dialog.dismiss();
                }
                JDElderModeUtils.setElderFloatingWindowSwitch(false);
                if (FamilyHelperFloatView.this.fhFloatViewListener != null) {
                    FamilyHelperFloatView.this.fhFloatViewListener.onClose();
                }
            }
        });
        this.dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void welt(int i2) {
        if (isLeftSide() && isRightSide()) {
            return;
        }
        if (i2 >= this.parentWidth / 2) {
            this.hiddenX = (int) (((r0 - getWidth()) - getX()) + DpiUtil.dip2px(this.mContext, 43.0f));
        } else {
            this.hiddenX = -DpiUtil.dip2px(this.mContext, 34.0f);
        }
        Runnable runnable = new Runnable() { // from class: com.jingdong.app.mall.bundle.familyhelper.widget.FamilyHelperFloatView.2
            @Override // java.lang.Runnable
            public void run() {
                FamilyHelperFloatView.this.isHidden = true;
                FamilyHelperFloatView.this.view.setAlpha(0.6f);
                FamilyHelperFloatView.this.setX(r0.initX + FamilyHelperFloatView.this.hiddenX);
            }
        };
        this.runnable = runnable;
        this.handler.postDelayed(runnable, 3000L);
    }

    public void setBothSide(boolean z) {
        this.isBothSide = z;
    }

    public void setChannelId(String str) {
        this.channelId = str;
    }

    public void setFhFloatViewListener(FhFloatViewListener fhFloatViewListener) {
        this.fhFloatViewListener = fhFloatViewListener;
    }

    public void setJsonParam(JDJSONObject jDJSONObject) {
        this.jsonParam = jDJSONObject;
    }

    public FamilyHelperFloatView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.handler = new Handler();
        initDraw(context, attributeSet);
    }

    public FamilyHelperFloatView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.handler = new Handler();
        initDraw(context, attributeSet);
    }
}
