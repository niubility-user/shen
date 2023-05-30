package com.jingdong.common.messagecenter.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.utils.ui.view.JDMultiTextView;
import com.jingdong.common.R;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.frame.JDHandler;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.messagecenter.view.NewMessageRedInfo;
import com.jingdong.common.utils.JumpMessageActivityUtil;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import java.util.Map;

@Deprecated
/* loaded from: classes5.dex */
public class MainPageNoTitleMessageDoorView extends RelativeLayout implements MessageRedObserver, View.OnClickListener {
    private static final String MESSAGE_REDKEY = "messageRedInfo";
    private static final int MSG_UPDATE_MESSAGE = 3;
    private static final int RED_POINT_RED = -905168;
    private static final int RED_POINT_WHITE = -1;
    private static final int TYPE_SHOW_NONE = 2;
    private static final int TYPE_SHOW_NUMBER = 0;
    private static final int TYPE_SHOW_NUMBER99 = 4;
    private static final int TYPE_SHOW_REDDOT = 1;
    private String Channal_ID;
    private final Context mContext;
    private JDHandler mHandler;
    private MessageClickListener mMessageClickListener;
    private RelativeLayout noTitlemessage_door_btn;
    private SimpleDraweeView noTitlemessage_door_img;
    private JDMultiTextView noTitlemessage_door_number;
    private SimpleDraweeView noTitlemessage_door_red_dot;
    private int redPointType;
    private int redPoint_Num;

    /* loaded from: classes5.dex */
    public interface MessageClickListener {
        void OnMessageClick();
    }

    public MainPageNoTitleMessageDoorView(Context context) {
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
        this.noTitlemessage_door_btn = (RelativeLayout) findViewById(R.id.notitle_message_door_btn);
        this.noTitlemessage_door_img = (SimpleDraweeView) findViewById(R.id.notitle_message_door_img);
        JDMultiTextView jDMultiTextView = (JDMultiTextView) findViewById(R.id.notitle_message_door_number);
        this.noTitlemessage_door_number = jDMultiTextView;
        jDMultiTextView.setMultiTypeFace();
        setContentDescription();
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) findViewById(R.id.notitle_message_door_red_dot);
        this.noTitlemessage_door_red_dot = simpleDraweeView;
        simpleDraweeView.setBackgroundResource(R.drawable.message_door_redpoint);
        this.noTitlemessage_door_btn.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setContentDescription() {
        RelativeLayout relativeLayout = this.noTitlemessage_door_btn;
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
        clearMessageRed();
        JumpMessageActivityUtil.jumpToMessageCenter(this.mContext);
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
        this.mHandler.sendMessage(obtain);
    }

    public void scrollChangeGrayIcon(boolean z) {
        scrollChangeIconColor(z ? 1 : 0);
    }

    public void scrollChangeIconColor(int i2) {
        if (i2 == 0) {
            this.noTitlemessage_door_img.setImageResource(R.drawable.common_icon_message_white_normal);
        } else if (i2 == 1) {
            this.noTitlemessage_door_img.setImageResource(R.drawable.common_icon_message_normal);
        } else if (i2 != 2) {
            this.noTitlemessage_door_img.setImageResource(R.drawable.common_icon_message_white_normal);
        } else {
            this.noTitlemessage_door_img.setImageResource(R.drawable.common_icon_message_pressed);
        }
    }

    public void setMessageClickListener(MessageClickListener messageClickListener) {
        this.mMessageClickListener = messageClickListener;
    }

    public void setMessageDoorViewColorReverse(int i2) {
        this.redPointType = i2;
        if (i2 != 1) {
            this.noTitlemessage_door_number.setTextColor(-1);
            if (this.redPoint_Num < 10) {
                this.noTitlemessage_door_number.setBackgroundResource(R.drawable.red_point_bg1);
                return;
            } else {
                this.noTitlemessage_door_number.setBackgroundResource(R.drawable.red_point_bg2);
                return;
            }
        }
        this.noTitlemessage_door_number.setTextColor(RED_POINT_RED);
        if (this.redPoint_Num < 10) {
            this.noTitlemessage_door_number.setBackgroundResource(R.drawable.red_point_white_bg1);
        } else {
            this.noTitlemessage_door_number.setBackgroundResource(R.drawable.red_point_white_bg2);
        }
    }

    public void setMsgImgDrawable(int i2, Drawable drawable) {
        this.Channal_ID = NewMessageRedInfo.ChannalIDEnum.getChannalPageName(i2);
        if (drawable != null) {
            this.noTitlemessage_door_img.setImageDrawable(drawable);
        }
    }

    public MainPageNoTitleMessageDoorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.redPointType = 0;
        this.mHandler = new JDHandler() { // from class: com.jingdong.common.messagecenter.view.MainPageNoTitleMessageDoorView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                Bundle data;
                if (message.what != 3 || MainPageNoTitleMessageDoorView.this.noTitlemessage_door_number == null || MainPageNoTitleMessageDoorView.this.noTitlemessage_door_red_dot == null || (data = message.getData()) == null) {
                    return;
                }
                int i2 = data.getInt(DeeplinkProductDetailHelper.LAYER_STYLE);
                int i3 = data.getInt("num");
                if (i2 == 1) {
                    MainPageNoTitleMessageDoorView.this.redPoint_Num = -1;
                    MainPageNoTitleMessageDoorView.this.setContentDescription();
                    MainPageNoTitleMessageDoorView.this.noTitlemessage_door_red_dot.setVisibility(0);
                    MainPageNoTitleMessageDoorView.this.noTitlemessage_door_number.setVisibility(8);
                } else if (i2 != 4 || i3 <= 0) {
                    if (i2 == 2) {
                        MainPageNoTitleMessageDoorView.this.redPoint_Num = 0;
                        MainPageNoTitleMessageDoorView.this.setContentDescription();
                        MainPageNoTitleMessageDoorView.this.noTitlemessage_door_red_dot.setVisibility(8);
                        MainPageNoTitleMessageDoorView.this.noTitlemessage_door_number.setVisibility(8);
                    }
                } else {
                    MainPageNoTitleMessageDoorView.this.redPoint_Num = i3;
                    MainPageNoTitleMessageDoorView.this.setContentDescription();
                    MainPageNoTitleMessageDoorView.this.noTitlemessage_door_red_dot.setVisibility(8);
                    MainPageNoTitleMessageDoorView.this.noTitlemessage_door_number.setVisibility(0);
                    if (i3 > 99) {
                        MainPageNoTitleMessageDoorView.this.noTitlemessage_door_number.setText("99+");
                        MainPageNoTitleMessageDoorView mainPageNoTitleMessageDoorView = MainPageNoTitleMessageDoorView.this;
                        mainPageNoTitleMessageDoorView.setMessageDoorViewColorReverse(mainPageNoTitleMessageDoorView.redPointType);
                    } else if (i3 > 9) {
                        MainPageNoTitleMessageDoorView.this.noTitlemessage_door_number.setText(i3 + "");
                        MainPageNoTitleMessageDoorView mainPageNoTitleMessageDoorView2 = MainPageNoTitleMessageDoorView.this;
                        mainPageNoTitleMessageDoorView2.setMessageDoorViewColorReverse(mainPageNoTitleMessageDoorView2.redPointType);
                    } else {
                        MainPageNoTitleMessageDoorView.this.noTitlemessage_door_number.setText(i3 + "");
                        MainPageNoTitleMessageDoorView mainPageNoTitleMessageDoorView3 = MainPageNoTitleMessageDoorView.this;
                        mainPageNoTitleMessageDoorView3.setMessageDoorViewColorReverse(mainPageNoTitleMessageDoorView3.redPointType);
                    }
                }
            }
        };
        this.mContext = context;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.main_page_notitle_message_door_view, (ViewGroup) this, true);
        NewMessagRedManager.getInstance(LoginUserBase.getUserPin());
        NewMessagRedManager.registPersonalMessageObserver(this);
        initView();
    }

    public void initChannelAndColor(int i2, boolean z) {
        this.Channal_ID = NewMessageRedInfo.ChannalIDEnum.getChannalPageName(i2);
        scrollChangeGrayIcon(z);
    }

    public void setMsgImgDrawable(String str, Drawable drawable) {
        if (str != null) {
            this.Channal_ID = str;
        }
        if (drawable != null) {
            this.noTitlemessage_door_img.setImageDrawable(drawable);
        }
    }

    public void initChannelAndColor(String str, boolean z) {
        if (str != null) {
            this.Channal_ID = str;
        }
        scrollChangeGrayIcon(z);
    }
}
