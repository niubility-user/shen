package com.jingdong.common.messagecenter.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jingdong.app.mall.utils.ui.view.JDMultiTextView;
import com.jingdong.common.R;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.frame.JDHandler;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.messagecenter.view.NewMessageRedInfo;
import com.jingdong.common.utils.JumpMessageActivityUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.Map;

/* loaded from: classes5.dex */
public class ElderModeMessageDoorView extends ConstraintLayout implements MessageRedObserver, View.OnClickListener {
    private static final String MESSAGE_REDKEY = "messageRedInfo";
    private static final int MSG_UPDATE_MESSAGE = 3;
    private static final int RED_POINT_RED = -905168;
    private static final int RED_POINT_WHITE = -1;
    private static final int TYPE_SHOW_NONE = 2;
    private static final int TYPE_SHOW_NUMBER = 0;
    private static final int TYPE_SHOW_NUMBER99 = 4;
    private static final int TYPE_SHOW_REDDOT = 1;
    private String Channal_ID;
    private String TAG;
    private View bgView;
    private ConstraintLayout mClMessageDoorBtn;
    private final Context mContext;
    @SuppressLint({"HandlerLeak"})
    private JDHandler mHandler;
    private ImageView mImgElderMode;
    private LayoutInflater mInflater;
    private boolean mIsUpdateTextSize;
    private MessageClickListener mMessageClickListener;
    private View mParentView;
    private TextView mTvElderMode;
    private JDMultiTextView mTvUnreadNum;
    private int mUnreadNumTextSize;
    private float mUnreadNumTvSize;
    private int redPointType;
    private int redPoint_Num;

    /* loaded from: classes5.dex */
    public interface MessageClickListener {
        void OnMessageClick();
    }

    public ElderModeMessageDoorView(Context context) {
        this(context, null);
    }

    private void clearMessageRed() {
        Message obtain = Message.obtain();
        obtain.what = 3;
        Bundle bundle = new Bundle();
        bundle.putInt(DeeplinkProductDetailHelper.LAYER_STYLE, 2);
        bundle.putInt("num", 0);
        obtain.setData(bundle);
        this.mHandler.sendMessageDelayed(obtain, 500L);
    }

    private void initView() {
        this.mClMessageDoorBtn = (ConstraintLayout) findViewById(R.id.message_door_btn);
        this.mImgElderMode = (ImageView) findViewById(R.id.img_elder_mode);
        this.mTvElderMode = (TextView) findViewById(R.id.tv_elder_mode);
        this.bgView = findViewById(R.id.bgView);
        this.mTvUnreadNum = (JDMultiTextView) findViewById(R.id.tv_unread_num);
        this.mClMessageDoorBtn.setOnClickListener(this);
        this.mTvUnreadNum.setMultiTypeFace();
        this.bgView.setVisibility(8);
        this.mTvUnreadNum.setVisibility(8);
        this.mTvElderMode.setTextSize(0, DPIUtil.dip2px(18.0f));
        this.mTvUnreadNum.setTextSize(0, DPIUtil.dip2px(this.mUnreadNumTvSize));
        setContentDescription();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setContentDescription() {
        ConstraintLayout constraintLayout = this.mClMessageDoorBtn;
        if (constraintLayout != null) {
            constraintLayout.setContentDescription("\u6d88\u606f,\u672a\u8bfb" + Math.max(this.redPoint_Num, 0) + "\u6761");
        }
    }

    public TextView getElderTextView() {
        if (this.mTvElderMode == null) {
            this.mTvElderMode = (TextView) this.mParentView.findViewById(R.id.tv_elder_mode);
        }
        return this.mTvElderMode;
    }

    public void getMessageDoorRedDot(HttpGroup httpGroup) {
        if (!LoginUserBase.hasLogin()) {
            clearMessageRed();
            return;
        }
        NewMessagRedManager.getInstance(LoginUserBase.getUserPin());
        NewMessagRedManager.requestMessage(httpGroup);
    }

    public void initChannelAndColor(int i2, int i3) {
        this.Channal_ID = NewMessageRedInfo.ChannalIDEnum.getChannalPageName(i2);
        scrollChangeIconColor(i3);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.Channal_ID != null) {
            JDMtaUtils.onClick(this.mContext, "App_MessageCenterAccess", null, this.Channal_ID + CartConstant.KEY_YB_INFO_LINK + this.redPoint_Num);
        }
        JumpMessageActivityUtil.jumpToMessageCenter(this.mContext);
        MessageClickListener messageClickListener = this.mMessageClickListener;
        if (messageClickListener != null) {
            messageClickListener.OnMessageClick();
        }
    }

    @Override // com.jingdong.common.messagecenter.view.MessageRedObserver
    public void onMessageRedReceived(Map<String, NewMessageRedInfo> map) {
        NewMessageRedInfo newMessageRedInfo;
        int i2;
        if (map == null || !map.containsKey(MESSAGE_REDKEY) || (newMessageRedInfo = map.get(MESSAGE_REDKEY)) == null) {
            return;
        }
        if (newMessageRedInfo.isShowRedDot()) {
            i2 = 1;
        } else {
            i2 = newMessageRedInfo.isShow99Number() ? 4 : 2;
        }
        Message obtain = Message.obtain();
        obtain.what = 3;
        Bundle bundle = new Bundle();
        bundle.putInt(DeeplinkProductDetailHelper.LAYER_STYLE, i2);
        bundle.putInt("num", newMessageRedInfo.num);
        obtain.setData(bundle);
        this.mHandler.sendMessage(obtain);
    }

    public void scrollChangeGrayIcon(boolean z) {
        scrollChangeIconColor(z ? 1 : 0);
    }

    public void scrollChangeIconColor(int i2) {
        if (i2 == 0) {
            this.mImgElderMode.setImageResource(R.drawable.icon_message_elder_mode_white);
            this.mTvElderMode.setTextColor(-1);
        } else if (i2 != 1) {
            this.mImgElderMode.setImageResource(R.drawable.icon_message_elder_mode_white);
            this.mTvElderMode.setTextColor(-1);
        } else {
            this.mImgElderMode.setImageResource(R.drawable.icon_message_elder_mode_black);
            this.mTvElderMode.setTextColor(getResources().getColor(R.color.c_333333));
        }
    }

    public void setDefaultElderModeStyle() {
        setElderModeStyle(R.drawable.icon_message_elder_mode_white, -1);
    }

    public void setElderModeStyle(@DrawableRes int i2, int i3) {
        try {
            this.mImgElderMode.setImageResource(i2);
            this.mTvElderMode.setTextColor(getResources().getColor(i3));
        } catch (Exception e2) {
            e2.printStackTrace();
            this.mImgElderMode.setImageResource(R.drawable.icon_message_elder_mode_white);
            this.mTvElderMode.setTextColor(-1);
        }
    }

    public void setMessageClickListener(MessageClickListener messageClickListener) {
        this.mMessageClickListener = messageClickListener;
    }

    public void setMessageDoorViewColorReverse(int i2) {
        this.redPointType = i2;
        if (this.redPoint_Num == 0) {
            this.bgView.setVisibility(8);
            this.mTvUnreadNum.setVisibility(8);
            return;
        }
        this.bgView.setVisibility(0);
        GradientDrawable gradientDrawable = null;
        try {
            gradientDrawable = (GradientDrawable) this.bgView.getBackground();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (gradientDrawable == null) {
            gradientDrawable = new GradientDrawable();
        }
        gradientDrawable.setCornerRadius(DPIUtil.dip2px(10.0f));
        if (i2 != 1) {
            this.mTvUnreadNum.setTextColor(-1);
            gradientDrawable.setColor(RED_POINT_RED);
        } else {
            this.mTvUnreadNum.setTextColor(RED_POINT_RED);
            gradientDrawable.setColor(-1);
        }
        int i3 = this.redPoint_Num;
        if (i3 < 0) {
            this.mTvUnreadNum.setVisibility(8);
            gradientDrawable.setSize(DPIUtil.dip2px(10.0f), DPIUtil.dip2px(10.0f));
            gradientDrawable.setShape(1);
            if (i2 != 1) {
                gradientDrawable.setStroke(DPIUtil.dip2px(0.5f), -1);
            }
        } else if (i3 > 99) {
            this.mTvUnreadNum.setVisibility(0);
            float f2 = this.mUnreadNumTvSize;
            gradientDrawable.setSize(DPIUtil.dip2px(f2 + f2), DPIUtil.dip2px(this.mUnreadNumTvSize + 1.0f));
            gradientDrawable.setShape(0);
        } else if (i3 > 9) {
            this.mTvUnreadNum.setVisibility(0);
            gradientDrawable.setSize(DPIUtil.dip2px(this.mUnreadNumTvSize + 8.0f), DPIUtil.dip2px(this.mUnreadNumTvSize + 1.0f));
            gradientDrawable.setShape(0);
        } else {
            this.mTvUnreadNum.setVisibility(0);
            gradientDrawable.setSize(DPIUtil.dip2px(this.mUnreadNumTvSize + 1.0f), DPIUtil.dip2px(this.mUnreadNumTvSize + 1.0f));
            gradientDrawable.setShape(1);
        }
        ViewGroup.LayoutParams layoutParams = this.bgView.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = gradientDrawable.getIntrinsicWidth();
            layoutParams.height = gradientDrawable.getIntrinsicHeight();
        }
        this.bgView.setBackground(gradientDrawable);
    }

    public void setMessageImgSize(int i2) {
        Log.d(this.TAG, "setMessageImgSize");
        ViewGroup.LayoutParams layoutParams = this.mImgElderMode.getLayoutParams();
        layoutParams.width = i2;
        layoutParams.height = i2;
        this.mImgElderMode.setLayoutParams(layoutParams);
    }

    public void setMessageTextSize(int i2) {
        this.mTvElderMode.setTextSize(0, i2);
    }

    public void setMessageTypeface(Typeface typeface) {
        this.mTvElderMode.setTypeface(typeface);
    }

    public void setUnReadNumTextSize(int i2) {
        this.mUnreadNumTextSize = i2;
        this.mUnreadNumTvSize = DPIUtil.px2dip(r3);
        this.mIsUpdateTextSize = true;
        this.mTvUnreadNum.setTextSize(0, i2);
    }

    public ElderModeMessageDoorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.redPointType = 0;
        this.mIsUpdateTextSize = false;
        this.mUnreadNumTvSize = 14.0f;
        this.TAG = ElderModeMessageDoorView.class.getSimpleName();
        this.mHandler = new JDHandler() { // from class: com.jingdong.common.messagecenter.view.ElderModeMessageDoorView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                Bundle data;
                if (message.what != 3 || ElderModeMessageDoorView.this.mTvUnreadNum == null || ElderModeMessageDoorView.this.bgView == null || (data = message.getData()) == null) {
                    return;
                }
                int i2 = data.getInt(DeeplinkProductDetailHelper.LAYER_STYLE);
                int i3 = data.getInt("num");
                if (i2 == 1) {
                    ElderModeMessageDoorView.this.redPoint_Num = -1;
                } else if (i2 == 4 && i3 > 0) {
                    ElderModeMessageDoorView.this.redPoint_Num = i3;
                    if (i3 > 99) {
                        ElderModeMessageDoorView.this.mTvUnreadNum.setText("99+");
                    } else {
                        ElderModeMessageDoorView.this.mTvUnreadNum.setText(String.valueOf(i3));
                    }
                    if (ElderModeMessageDoorView.this.mIsUpdateTextSize) {
                        ElderModeMessageDoorView elderModeMessageDoorView = ElderModeMessageDoorView.this;
                        elderModeMessageDoorView.setUnReadNumTextSize(elderModeMessageDoorView.mUnreadNumTextSize);
                    }
                } else if (i2 == 2) {
                    ElderModeMessageDoorView.this.redPoint_Num = 0;
                }
                ElderModeMessageDoorView.this.setContentDescription();
                ElderModeMessageDoorView elderModeMessageDoorView2 = ElderModeMessageDoorView.this;
                elderModeMessageDoorView2.setMessageDoorViewColorReverse(elderModeMessageDoorView2.redPointType);
            }
        };
        this.mContext = context;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        this.mInflater = layoutInflater;
        this.mParentView = layoutInflater.inflate(R.layout.elder_mode_message_door_view, (ViewGroup) this, true);
        NewMessagRedManager.getInstance(LoginUserBase.getUserPin());
        NewMessagRedManager.registPersonalMessageObserver(this);
        initView();
    }

    public void initChannelAndColor(int i2, boolean z) {
        this.Channal_ID = NewMessageRedInfo.ChannalIDEnum.getChannalPageName(i2);
        scrollChangeGrayIcon(z);
    }

    public void initChannelAndColor(String str, boolean z) {
        if (str != null) {
            this.Channal_ID = str;
        }
        scrollChangeGrayIcon(z);
    }
}
