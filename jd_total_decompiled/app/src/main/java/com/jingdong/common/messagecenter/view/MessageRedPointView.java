package com.jingdong.common.messagecenter.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.R;
import com.jingdong.common.frame.JDHandler;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.messagecenter.view.NewMessageRedInfo;
import com.jingdong.common.unification.navigationbar.NavigationBase;
import com.jingdong.common.utils.JumpMessageActivityUtil;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import java.util.Map;

/* loaded from: classes5.dex */
public class MessageRedPointView extends ConstraintLayout implements MessageRedObserver, View.OnClickListener {
    private static final String MESSAGE_REDKEY = "messageRedInfo";
    private static final int MSG_UPDATE_MESSAGE = 3;
    private static boolean animSwitch;
    private String channalId;
    private int dp20;
    private int dp9;
    private View iconIv;
    @SuppressLint({"HandlerLeak"})
    private JDHandler mHandler;
    private OnMsgIconClickListener mOnMessageClickListener;
    private int numColor;
    protected RedPointNumTextView numTv;
    private int pointColor;
    private int redPointNum;

    /* loaded from: classes5.dex */
    public interface OnMsgIconClickListener {
        void onMsgIconClick();
    }

    public MessageRedPointView(Context context) {
        this(context, null);
    }

    private void initView() {
        this.numTv = (RedPointNumTextView) findViewById(R.id.numTv);
        this.iconIv = findViewById(R.id.iconIv);
        this.numTv.setTextSize(this.dp9);
        this.numTv.setTextColor(this.numColor);
        this.numTv.setBgColor(this.pointColor);
        setOnClickListener(this);
        this.numTv.setIsUseAnim(false);
        setContentDescription();
    }

    private void setContentDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("\u6d88\u606f,\u672a\u8bfb");
        int i2 = this.redPointNum;
        if (i2 < 0) {
            i2 = 0;
        }
        sb.append(i2);
        sb.append("\u6761");
        setContentDescription(sb.toString());
    }

    protected void changeNum(int i2) {
        changeNum(i2, 0L);
    }

    public void getMessageDoorRedDot(HttpGroup httpGroup) {
        if (!LoginUserBase.hasLogin()) {
            changeNum(0, 500L);
        } else if (NavigationBase.getInstance().isNavigationType(1)) {
        } else {
            NewMessagRedManager.getInstance(LoginUserBase.getUserPin());
            NewMessagRedManager.requestMessage(httpGroup);
        }
    }

    protected NewMessageRedInfo getMessageRedInfo(Map<String, NewMessageRedInfo> map) {
        if (map == null) {
            return null;
        }
        return map.get(MESSAGE_REDKEY);
    }

    public void onClick(View view) {
        if (this.channalId != null) {
            JDMtaUtils.onClick(getContext(), "App_MessageCenterAccess", null, this.channalId + CartConstant.KEY_YB_INFO_LINK + this.redPointNum);
        }
        JumpMessageActivityUtil.jumpToMessageCenter(getContext());
        OnMsgIconClickListener onMsgIconClickListener = this.mOnMessageClickListener;
        if (onMsgIconClickListener != null) {
            onMsgIconClickListener.onMsgIconClick();
        }
    }

    @Override // com.jingdong.common.messagecenter.view.MessageRedObserver
    public void onMessageRedReceived(Map<String, NewMessageRedInfo> map) {
        NewMessageRedInfo messageRedInfo = getMessageRedInfo(map);
        if (messageRedInfo != null) {
            int i2 = messageRedInfo.num;
            if (i2 <= 0) {
                i2 = messageRedInfo.redPoint ? -1 : 0;
            }
            changeNum(i2);
        }
    }

    public void setChannalId(int i2) {
        setChannalId(NewMessageRedInfo.ChannalIDEnum.getChannalPageName(i2));
    }

    public void setOnMsgIconClickListener(OnMsgIconClickListener onMsgIconClickListener) {
        this.mOnMessageClickListener = onMsgIconClickListener;
    }

    public void updateIconImage(Drawable drawable) {
        this.iconIv.setBackground(drawable);
    }

    public void updateIconSize(int i2) {
        try {
            ViewGroup.LayoutParams layoutParams = this.iconIv.getLayoutParams();
            layoutParams.width = i2;
            layoutParams.height = i2;
            this.iconIv.setLayoutParams(layoutParams);
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) this.numTv.getLayoutParams();
            layoutParams2.circleRadius = i2 / 2;
            this.numTv.setLayoutParams(layoutParams2);
            updateNumTextSize((i2 * this.dp9) / this.dp20);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected void updateMsgNumInMainThread(int i2) {
        RedPointNumTextView redPointNumTextView = this.numTv;
        if (redPointNumTextView == null || this.iconIv == null) {
            return;
        }
        this.redPointNum = i2;
        redPointNumTextView.setNum(i2);
        setContentDescription();
    }

    public void updateNumAnim(boolean z) {
        this.numTv.setIsUseAnim(z && animSwitch);
    }

    public void updateNumTextSize(int i2) {
        this.numTv.setTextSize(i2);
    }

    public void updateNumTypeface(Typeface typeface) {
        this.numTv.setTypeface(typeface);
    }

    public void updatePointNumColor(int i2, int i3) {
        this.pointColor = i3;
        this.numColor = i2;
        this.numTv.setTextColor(i2);
        this.numTv.setBgColor(i3);
    }

    public void updatePointNumColorReverse(boolean z) {
        if (z) {
            this.numTv.setTextColor(this.pointColor);
            this.numTv.setBgColor(this.numColor);
            return;
        }
        this.numTv.setTextColor(this.numColor);
        this.numTv.setBgColor(this.pointColor);
    }

    public void updateRedPointRingWidth(int i2) {
        this.numTv.setRedPointWidth(i2);
    }

    public MessageRedPointView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.pointColor = Color.parseColor("#F23030");
        this.numColor = Color.parseColor("#FFFFFF");
        this.mHandler = new JDHandler() { // from class: com.jingdong.common.messagecenter.view.MessageRedPointView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 3 && message != null) {
                    MessageRedPointView.this.updateMsgNumInMainThread(((Integer) message.obj).intValue());
                }
            }
        };
        try {
            ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.layout_message_redpoint_view, (ViewGroup) this, true);
            NewMessagRedManager.getInstance(LoginUserBase.getUserPin());
            NewMessagRedManager.registPersonalMessageObserver(this);
            this.dp9 = DpiUtil.dip2px(getContext(), 9.0f);
            this.dp20 = DpiUtil.dip2px(getContext(), 20.0f);
            initView();
            animSwitch = SharedPreferencesUtil.getBoolean("msg_ring_enable", true);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected void changeNum(int i2, long j2) {
        Message obtain = Message.obtain();
        obtain.what = 3;
        obtain.obj = Integer.valueOf(i2);
        this.mHandler.sendMessageDelayed(obtain, j2);
    }

    public void setChannalId(String str) {
        if (str != null) {
            this.channalId = str;
        }
    }
}
