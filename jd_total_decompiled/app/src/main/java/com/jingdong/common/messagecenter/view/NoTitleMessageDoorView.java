package com.jingdong.common.messagecenter.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.jingdong.common.R;

/* loaded from: classes5.dex */
public class NoTitleMessageDoorView extends MessageRedPointView {
    @Deprecated
    private MessageClickListener mMessageClickListener;

    @Deprecated
    /* loaded from: classes5.dex */
    public interface MessageClickListener {
        @Deprecated
        void OnMessageClick();
    }

    public NoTitleMessageDoorView(Context context) {
        this(context, null);
    }

    public void initChannelAndColor(int i2, int i3) {
        setChannalId(i2);
        scrollChangeIconColor(i3);
    }

    @Deprecated
    public void initMessageImgScaleType(ImageView.ScaleType scaleType) {
    }

    public void initMessageImgSize(int i2) {
        updateIconSize(i2);
    }

    @Override // com.jingdong.common.messagecenter.view.MessageRedPointView, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        MessageClickListener messageClickListener = this.mMessageClickListener;
        if (messageClickListener != null) {
            messageClickListener.OnMessageClick();
        }
    }

    public void scrollChangeGrayIcon(boolean z) {
        scrollChangeIconColor(z ? 1 : 0);
    }

    public void scrollChangeIconColor(int i2) {
        if (i2 == 0) {
            updateIconImage(getResources().getDrawable(R.drawable.common_icon_message_white_normal));
        } else if (i2 == 1) {
            updateIconImage(getResources().getDrawable(R.drawable.common_icon_message_normal));
        } else if (i2 != 2) {
            updateIconImage(getResources().getDrawable(R.drawable.common_icon_message_white_normal));
        } else {
            updateIconImage(getResources().getDrawable(R.drawable.common_icon_message_pressed));
        }
    }

    public void setMarginLeft(int i2) {
        View findViewById = findViewById(R.id.iconIv);
        if (findViewById == null || findViewById.getLayoutParams() == null || !(findViewById.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) findViewById.getLayoutParams();
        marginLayoutParams.leftMargin = i2;
        findViewById.setLayoutParams(marginLayoutParams);
    }

    @Deprecated
    public void setMessageClickListener(MessageClickListener messageClickListener) {
        this.mMessageClickListener = messageClickListener;
    }

    public void setMessageDoorViewColorReverse(int i2) {
        updatePointNumColorReverse(i2 == 1);
    }

    public void setMsgImgDrawable(int i2, Drawable drawable) {
        setChannalId(i2);
        if (drawable != null) {
            updateIconImage(drawable);
        }
    }

    public NoTitleMessageDoorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setMarginLeft((int) (getResources().getDisplayMetrics().density * 4.0f));
    }

    public void initChannelAndColor(int i2, boolean z) {
        setChannalId(i2);
        scrollChangeGrayIcon(z);
    }

    public void setMsgImgDrawable(String str, Drawable drawable) {
        setChannalId(str);
        if (drawable != null) {
            updateIconImage(drawable);
        }
    }

    public void initChannelAndColor(String str, boolean z) {
        setChannalId(str);
        scrollChangeGrayIcon(z);
    }
}
