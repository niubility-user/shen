package com.jingdong.common.messagecenter.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.common.R;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.frame.JDHandler;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.messagecenter.view.NewMessageRedInfo;
import com.jingdong.common.unification.navigationbar.NavigationBase;
import com.jingdong.common.unification.uniwidget.JDLottieAnimationView;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.common.utils.JumpMessageActivityUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import java.util.Map;

/* loaded from: classes5.dex */
public class MessageDoorView extends RelativeLayout implements MessageRedObserver, View.OnClickListener {
    private static final String MESSAGE_REDKEY = "messageRedInfo";
    private static final int MSG_UPDATE_MESSAGE = 3;
    private static final int RED_POINT_RED = -905168;
    private static final int RED_POINT_WHITE = -1;
    private static final int TYPE_SHOW_NONE = 2;
    private static final int TYPE_SHOW_NUMBER = 0;
    private static final int TYPE_SHOW_NUMBER99 = 4;
    private static final int TYPE_SHOW_REDDOT = 1;
    private String Channal_ID;
    private boolean isBlack;
    private boolean isFirst;
    private boolean isGetMessageFirst;
    private boolean isMsgRingEnable;
    private Context mContext;
    @SuppressLint({"HandlerLeak"})
    private JDHandler mHandler;
    private LayoutInflater mInflater;
    private MessageClickListener mMessageClickListener;
    private View mParentView;
    private ImageView messageBlackImg;
    private View messageDoor;
    private ImageView messageDoorIv;
    private ImageView messageWhiteImg;
    private RelativeLayout message_door_btn;
    private RedPointNumView message_door_number;
    private SimpleDraweeView message_door_red_dot;
    private TextView message_door_text;
    private int prePointNUM;
    private int redPoint_Num;
    private static final int ICON_MSG_WIDTH_HEIGHT = DPIUtil.getWidthByDesignValue750(46);
    private static final int MSG_TEXTSIZE_PX = DPIUtil.getWidthByDesignValue750(15);

    /* loaded from: classes5.dex */
    public interface MessageClickListener {
        void OnMessageClick();
    }

    public MessageDoorView(Context context) {
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

    private void initMessageImgSize(int i2) {
        Log.d("MessageDoorView", "initMessageImgSize");
        ViewGroup.LayoutParams layoutParams = this.messageWhiteImg.getLayoutParams();
        layoutParams.width = i2;
        layoutParams.height = i2;
        this.messageWhiteImg.setLayoutParams(layoutParams);
        this.messageWhiteImg.setImageResource(R.drawable.icon_message_door_white_normal);
        ViewGroup.LayoutParams layoutParams2 = this.messageBlackImg.getLayoutParams();
        layoutParams2.width = i2;
        layoutParams2.height = i2;
        this.messageBlackImg.setLayoutParams(layoutParams2);
        this.messageBlackImg.setImageResource(R.drawable.icon_message_door_black_normal);
        ViewGroup.LayoutParams layoutParams3 = this.messageDoorIv.getLayoutParams();
        layoutParams3.width = i2;
        layoutParams3.height = i2;
        this.messageDoorIv.setLayoutParams(layoutParams3);
        this.message_door_text.setTextSize(0, MSG_TEXTSIZE_PX);
    }

    private void initView() {
        this.message_door_btn = (RelativeLayout) findViewById(R.id.message_door_btn);
        ImageView imageView = (ImageView) findViewById(R.id.message_door_white_img);
        this.messageWhiteImg = imageView;
        imageView.setVisibility(8);
        ImageView imageView2 = (ImageView) findViewById(R.id.message_door_black_img);
        this.messageBlackImg = imageView2;
        imageView2.setVisibility(8);
        this.messageDoor = findViewById(R.id.ll_message_door);
        this.message_door_text = (TextView) findViewById(R.id.message_door_text);
        this.message_door_number = (RedPointNumView) findViewById(R.id.message_door_number);
        this.message_door_red_dot = (SimpleDraweeView) findViewById(R.id.message_door_red_dot);
        this.message_door_btn.setOnClickListener(this);
        ImageView imageView3 = this.messageWhiteImg;
        int i2 = R.drawable.icon_message_door_white_normal;
        imageView3.setImageResource(i2);
        this.messageBlackImg.setImageResource(R.drawable.icon_message_door_black_normal);
        this.message_door_red_dot.setBackgroundResource(R.drawable.message_door_redpoint);
        this.messageDoorIv = (ImageView) findViewById(R.id.message_door_iv);
        setContentDescription();
        if (Build.VERSION.SDK_INT < 16) {
            this.messageDoorIv.setVisibility(8);
            this.messageWhiteImg.setVisibility(0);
        } else {
            this.messageDoorIv.setVisibility(0);
            this.messageDoorIv.setImageResource(i2);
        }
        this.isMsgRingEnable = SharedPreferencesUtil.getBoolean("msg_ring_enable", true);
        if (!(this.messageBlackImg instanceof JDLottieAnimationView) || !ABTestUtils.isLottieEnable()) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playMessageAnti(int i2) {
        if (Build.VERSION.SDK_INT >= 16 && ABTestUtils.isLottieEnable()) {
            ImageView imageView = this.messageBlackImg;
            if ((imageView instanceof JDLottieAnimationView) && (this.messageWhiteImg instanceof JDLottieAnimationView) && !this.isMsgRingEnable) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.icon_message_door_black_normal));
                this.messageWhiteImg.setImageDrawable(getResources().getDrawable(R.drawable.icon_message_door_white_normal));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setContentDescription() {
        RelativeLayout relativeLayout = this.message_door_btn;
        if (relativeLayout != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("\u6d88\u606f,\u672a\u8bfb");
            int i2 = this.redPoint_Num;
            if (i2 < 0) {
                i2 = 0;
            }
            sb.append(i2);
            sb.append("\u6761");
            relativeLayout.setContentDescription(sb.toString());
        }
    }

    private void setImageSize(ImageView imageView, int i2) {
        ViewGroup.LayoutParams layoutParams = this.messageDoorIv.getLayoutParams();
        layoutParams.width = i2;
        layoutParams.height = i2;
        imageView.setLayoutParams(layoutParams);
    }

    private void unRegisterRefresh() {
        NewMessagRedManager.deregisterPersonalMessageObserver(this);
    }

    public void getMessageDoorRedDot(HttpGroup httpGroup) {
        if (!LoginUserBase.hasLogin()) {
            clearMessageRed();
            return;
        }
        if (this.isFirst) {
            this.message_door_number.setPreNum(this.prePointNUM);
            this.isFirst = false;
        }
        if (NavigationBase.getInstance().isNavigationType(1)) {
            return;
        }
        NewMessagRedManager.getInstance(LoginUserBase.getUserPin());
        NewMessagRedManager.requestMessage(httpGroup);
    }

    public void initChannelAndColor(int i2, boolean z) {
        this.Channal_ID = NewMessageRedInfo.ChannalIDEnum.getChannalPageName(i2);
        scrollChangeGrayIcon(z);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.Channal_ID != null) {
            JDMtaUtils.onClick(this.mContext, "App_MessageCenterAccess", null, this.Channal_ID + CartConstant.KEY_YB_INFO_LINK + this.redPoint_Num);
        }
        JumpMessageActivityUtil.jumpToMessageCenter(this.mContext);
        scrollChangeGrayIcon(this.isBlack);
        this.mMessageClickListener.OnMessageClick();
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
        if (this.isGetMessageFirst) {
            this.isGetMessageFirst = false;
            this.mHandler.sendMessageDelayed(obtain, 4000L);
            return;
        }
        this.mHandler.sendMessage(obtain);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
    }

    public void scrollChangeGrayIcon(boolean z) {
        if (this.isBlack == z) {
            return;
        }
        this.isBlack = z;
        if (Build.VERSION.SDK_INT < 16) {
            this.messageBlackImg.setVisibility(z ? 0 : 8);
            this.messageWhiteImg.setVisibility(this.isBlack ? 8 : 0);
        } else if (z) {
            this.messageDoorIv.setImageResource(R.drawable.icon_message_door_black_normal);
        } else {
            this.messageDoorIv.setImageResource(R.drawable.icon_message_door_white_normal);
        }
        playMessageAnti(this.prePointNUM);
        this.message_door_number.onDestory();
        if (this.isBlack) {
            this.message_door_text.setTextColor(-16777216);
        } else {
            this.message_door_text.setTextColor(-1);
        }
    }

    public void setDefaultOldManModeStyle() {
        setOldManModeStyle(R.drawable.icon_message_elder_mode_white, R.color.white);
    }

    public void setMessageClickListener(MessageClickListener messageClickListener) {
        this.mMessageClickListener = messageClickListener;
    }

    public void setMessageDoorViewColorReverse(boolean z) {
        if (z) {
            this.message_door_number.setRedPointColor(RED_POINT_RED, false);
        } else {
            this.message_door_number.setRedPointColor(-1, true);
        }
    }

    public void setMessageImgSize(int i2) {
        Log.d("MessageDoorView", "setMessageImgSize");
        ViewGroup.LayoutParams layoutParams = this.messageWhiteImg.getLayoutParams();
        layoutParams.width = i2;
        layoutParams.height = i2;
        this.messageWhiteImg.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = this.messageBlackImg.getLayoutParams();
        layoutParams2.width = i2;
        layoutParams2.height = i2;
        this.messageBlackImg.setLayoutParams(layoutParams2);
        ViewGroup.LayoutParams layoutParams3 = this.messageDoorIv.getLayoutParams();
        layoutParams3.width = i2;
        layoutParams3.height = i2;
        this.messageDoorIv.setLayoutParams(layoutParams3);
        this.messageDoor.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jingdong.common.messagecenter.view.MessageDoorView.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                int right = MessageDoorView.this.messageDoor.getRight();
                int top = MessageDoorView.this.messageDoor.getTop() - DPIUtil.dip2px(4.0f) < 0 ? 0 : MessageDoorView.this.messageDoor.getTop() - DPIUtil.dip2px(4.0f);
                MessageDoorView.this.message_door_number.layout((right - DPIUtil.dip2px(10.0f)) + 0, top, (right - DPIUtil.dip2px(10.0f)) + MessageDoorView.this.message_door_number.getWidth(), MessageDoorView.this.message_door_number.getHeight() + top);
            }
        });
    }

    public void setMessageTextSize(int i2) {
        this.message_door_text.setTextSize(0, i2);
        if (i2 <= 0) {
            this.message_door_text.setVisibility(8);
        } else {
            this.message_door_text.setVisibility(0);
        }
    }

    public void setMessageTypeface(Typeface typeface) {
        this.message_door_text.setTypeface(typeface);
    }

    public void setOldManModeStyle(@DrawableRes int i2, int i3) {
        View view = this.mParentView;
        if (view instanceof ViewGroup) {
            ((ViewGroup) view).removeAllViews();
        }
        View inflate = this.mInflater.inflate(R.layout.message_door_old_man_style_view, (ViewGroup) this, true);
        this.mParentView = inflate;
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.message_door_btn);
        this.message_door_btn = relativeLayout;
        relativeLayout.setOnClickListener(this);
        ImageView imageView = (ImageView) this.mParentView.findViewById(R.id.img_old_man_style);
        TextView textView = (TextView) this.mParentView.findViewById(R.id.tv_old_man_style);
        this.message_door_red_dot = (SimpleDraweeView) this.mParentView.findViewById(R.id.sdv_old_man_style_red_dot);
        this.message_door_number = (RedPointNumView) this.mParentView.findViewById(R.id.v_old_man_style_num);
        try {
            imageView.setImageResource(i2);
            textView.setTextColor(getResources().getColor(i3));
        } catch (Exception e2) {
            e2.printStackTrace();
            imageView.setImageResource(R.drawable.icon_message_elder_mode_white);
            textView.setTextColor(-1);
        }
    }

    public MessageDoorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isBlack = false;
        this.isFirst = true;
        this.isGetMessageFirst = true;
        this.mHandler = new JDHandler() { // from class: com.jingdong.common.messagecenter.view.MessageDoorView.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                Bundle data;
                if (message.what != 3 || MessageDoorView.this.message_door_red_dot == null || MessageDoorView.this.message_door_number == null || (data = message.getData()) == null) {
                    return;
                }
                int i2 = data.getInt(DeeplinkProductDetailHelper.LAYER_STYLE);
                int i3 = data.getInt("num");
                if (i2 == 1) {
                    MessageDoorView.this.redPoint_Num = -1;
                    MessageDoorView.this.setContentDescription();
                    MessageDoorView.this.message_door_red_dot.setVisibility(0);
                    MessageDoorView.this.message_door_number.setVisibility(8);
                } else if (i2 == 4 && i3 > 0) {
                    MessageDoorView.this.redPoint_Num = i3;
                    MessageDoorView.this.setContentDescription();
                    MessageDoorView.this.message_door_red_dot.setVisibility(8);
                    MessageDoorView.this.message_door_number.setVisibility(0);
                    if (i3 == MessageDoorView.this.prePointNUM || i3 > 99 || !MessageDoorView.this.isMsgRingEnable) {
                        MessageDoorView.this.message_door_number.showCurNum(i3);
                    } else {
                        Log.d("\u5165\u53e3\u52a8\u6653", "\u52a8\u6653\u6eda\u52a8\u5f00\u59cb");
                        if (i3 > 99) {
                            MessageDoorView.this.message_door_number.setCurNum(i3);
                        } else if (i3 > 9) {
                            MessageDoorView.this.message_door_number.setCurNum(i3);
                        } else {
                            MessageDoorView.this.message_door_number.setCurNum(i3);
                        }
                        MessageDoorView.this.playMessageAnti(i3);
                        if (Build.VERSION.SDK_INT >= 16 && (MessageDoorView.this.messageBlackImg instanceof JDLottieAnimationView) && (MessageDoorView.this.messageWhiteImg instanceof JDLottieAnimationView)) {
                            if (MessageDoorView.this.isBlack) {
                                MessageDoorView.this.messageBlackImg.setVisibility(8);
                                MessageDoorView.this.messageWhiteImg.setVisibility(8);
                                MessageDoorView.this.messageDoorIv.setVisibility(0);
                                ((JDLottieAnimationView) MessageDoorView.this.messageBlackImg).playAnimation();
                            } else {
                                MessageDoorView.this.messageBlackImg.setVisibility(8);
                                MessageDoorView.this.messageWhiteImg.setVisibility(8);
                                MessageDoorView.this.messageDoorIv.setVisibility(0);
                                ((JDLottieAnimationView) MessageDoorView.this.messageWhiteImg).playAnimation();
                            }
                        }
                    }
                } else if (i2 == 2) {
                    MessageDoorView.this.redPoint_Num = 0;
                    MessageDoorView.this.setContentDescription();
                    MessageDoorView.this.message_door_number.showCurNum(i3);
                    MessageDoorView.this.message_door_red_dot.setVisibility(8);
                    MessageDoorView.this.message_door_number.setVisibility(8);
                }
                MessageDoorView.this.prePointNUM = i3;
            }
        };
        this.mContext = context;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        this.mInflater = layoutInflater;
        this.mParentView = layoutInflater.inflate(R.layout.message_door_view, (ViewGroup) this, true);
        NewMessagRedManager.getInstance(LoginUserBase.getUserPin());
        NewMessagRedManager.registPersonalMessageObserver(this);
        initView();
    }

    public void initChannelAndColor(String str, boolean z) {
        if (str != null) {
            this.Channal_ID = str;
        }
        scrollChangeGrayIcon(z);
    }
}
