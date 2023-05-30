package com.jingdong.common.widget.custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.R;
import com.jingdong.common.widget.custom.CustomFollowUtil;

@Deprecated
/* loaded from: classes12.dex */
public class CustomFollowButton extends Button implements View.OnClickListener {
    private String authorId;
    public final CustomFollowUtil customFollowUtil;
    private int hasfollowed;
    public int iconHei;
    public int iconWid;
    private int needFollowGift;
    private BaseActivity thisActivity;

    public CustomFollowButton(Context context) {
        this(context, null);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        CustomFollowUtil customFollowUtil = this.customFollowUtil;
        BaseActivity baseActivity = this.thisActivity;
        String str = this.authorId;
        int i2 = this.hasfollowed;
        int i3 = this.needFollowGift;
        customFollowUtil.getClass();
        customFollowUtil.follow(baseActivity, str, i2, i3, false, new CustomFollowUtil.FollowProgress(customFollowUtil, view) { // from class: com.jingdong.common.widget.custom.CustomFollowButton.1
            final /* synthetic */ View val$v;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                this.val$v = view;
                customFollowUtil.getClass();
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.jingdong.common.widget.custom.CustomFollowUtil.FollowProgress
            public void end() {
                super.end();
                CustomFollowButton.this.setEnabled(true);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.jingdong.common.widget.custom.CustomFollowUtil.FollowProgress
            public void fail(boolean z) {
                super.fail(z);
                if (z) {
                    Toast.makeText(this.val$v.getContext(), CustomFollowButton.this.getResources().getString(R.string.quxiao_subscribe_fail), 0).show();
                } else {
                    Toast.makeText(this.val$v.getContext(), CustomFollowButton.this.getResources().getString(R.string.subscribe_fail), 0).show();
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.jingdong.common.widget.custom.CustomFollowUtil.FollowProgress
            public void start(int i4) {
                super.start(i4);
                CustomFollowButton.this.setEnabled(false);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.jingdong.common.widget.custom.CustomFollowUtil.FollowProgress
            public void success(int i4, boolean z) {
                super.success(i4, z);
                CustomFollowButton customFollowButton = CustomFollowButton.this;
                customFollowButton.setFollow(customFollowButton.thisActivity, i4, CustomFollowButton.this.authorId);
            }
        });
    }

    public void setFollow(BaseActivity baseActivity, int i2, String str) {
        this.thisActivity = baseActivity;
        this.hasfollowed = i2;
        this.authorId = str;
        if (1 == i2) {
            setFollowed();
        } else {
            setNoFollow();
        }
        setGravity(17);
        setVisibility(0);
    }

    public void setFollowed() {
        setBackgroundResource(R.drawable.author_has_followed_img);
    }

    public void setNoFollow() {
        setText(getResources().getString(R.string.subscribe));
        setTextColor(-1);
        Drawable drawable = getResources().getDrawable(R.drawable.faxian_small_follow_add);
        int i2 = this.iconWid;
        if (i2 == 0) {
            i2 = drawable.getMinimumWidth();
        }
        int i3 = this.iconHei;
        if (i3 == 0) {
            i3 = drawable.getMinimumHeight();
        }
        drawable.setBounds(0, 0, i2, i3);
        setCompoundDrawables(drawable, null, null, null);
        setBackgroundColor(-319456);
    }

    public CustomFollowButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setVisibility(4);
        setOnClickListener(this);
        this.customFollowUtil = new CustomFollowUtil();
    }

    public void setFollow(BaseActivity baseActivity, int i2, String str, int i3) {
        setFollow(baseActivity, i2, str);
        this.needFollowGift = i3;
    }
}
