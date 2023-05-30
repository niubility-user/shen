package com.jingdong.common.messagecenter.view;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.jingdong.common.R;
import com.jingdong.common.messagecenter.view.NewMessageRedInfo;

/* loaded from: classes5.dex */
public class MainPageMessageDoorView extends MessageRedPointView {
    private boolean isBlack;
    @Deprecated
    private MessageClickListener mMessageClickListener;
    private Drawable messageBlackDrawable;
    private Drawable messageWhiteDrawable;

    @Deprecated
    /* loaded from: classes5.dex */
    public interface MessageClickListener {
        @Deprecated
        void OnMessageClick();
    }

    public MainPageMessageDoorView(Context context) {
        this(context, null);
    }

    private void updateIcon() {
        if (this.isBlack) {
            updateIconImage(this.messageBlackDrawable);
        } else {
            updateIconImage(this.messageWhiteDrawable);
        }
    }

    public void initChannelAndColor(int i2, boolean z) {
        initChannelAndColor(NewMessageRedInfo.ChannalIDEnum.getChannalPageName(i2), z);
        scrollChangeGrayIcon(z);
    }

    @Override // com.jingdong.common.messagecenter.view.MessageRedPointView, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        scrollChangeGrayIcon(this.isBlack);
        MessageClickListener messageClickListener = this.mMessageClickListener;
        if (messageClickListener != null) {
            messageClickListener.OnMessageClick();
        }
    }

    public void scrollChangeGrayIcon(boolean z) {
        if (this.isBlack == z) {
            return;
        }
        this.isBlack = z;
        updateIcon();
    }

    @Deprecated
    public void setMessageClickListener(MessageClickListener messageClickListener) {
        this.mMessageClickListener = messageClickListener;
    }

    public void setMessageDoorViewColorReverse(boolean z) {
        updatePointNumColorReverse(z);
    }

    public void setMessageImgSize(int i2) {
        updateIconSize(i2);
    }

    public void setMessageTextSize(int i2) {
    }

    public void setMessageTypeface(Typeface typeface) {
    }

    public void setMsgImgDrawable(int i2, Drawable drawable, Drawable drawable2) {
        setChannalId(i2);
        if (drawable == null) {
            drawable = this.messageWhiteDrawable;
        }
        this.messageWhiteDrawable = drawable;
        if (drawable2 == null) {
            drawable2 = this.messageBlackDrawable;
        }
        this.messageBlackDrawable = drawable2;
        updateIcon();
    }

    public MainPageMessageDoorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isBlack = false;
        updateNumAnim(true);
        this.messageWhiteDrawable = getResources().getDrawable(R.drawable.icon_message_door_white_normal);
        this.messageBlackDrawable = getResources().getDrawable(R.drawable.icon_message_door_black_normal);
    }

    public void initChannelAndColor(String str, boolean z) {
        setChannalId(str);
        scrollChangeGrayIcon(z);
    }
}
