package com.jingdong.common.widget.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.R;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.widget.custom.CustomFollowUtil;
import com.jingdong.common.widget.custom.FollowObservableManager;

/* loaded from: classes12.dex */
public class FaXianFollowBtn extends FrameLayout implements View.OnClickListener {
    private String authorId;
    public final CustomFollowUtil customFollowUtil;
    public View follow;
    public View followed;
    private int hasfollowed;
    private boolean isNotice;
    private FollowObservableManager.FollowListener mFollowListener;
    public int needFollowGift;
    private boolean needJingDou;
    private BaseActivity thisActivity;

    public FaXianFollowBtn(Context context) {
        this(context, null);
    }

    private void registerFollowObserver() {
        if (this.mFollowListener != null) {
            FollowObservableManager.getFollowObservableManager().registerFollowObserver(this.mFollowListener);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        registerFollowObserver();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        CustomFollowUtil customFollowUtil = this.customFollowUtil;
        BaseActivity baseActivity = this.thisActivity;
        String str = this.authorId;
        int i2 = this.hasfollowed;
        int i3 = this.needFollowGift;
        boolean z = this.needJingDou;
        customFollowUtil.getClass();
        customFollowUtil.follow(baseActivity, str, i2, i3, z, new CustomFollowUtil.FollowProgress(customFollowUtil, view) { // from class: com.jingdong.common.widget.custom.FaXianFollowBtn.1
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
                FaXianFollowBtn.this.setEnabled(true);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.jingdong.common.widget.custom.CustomFollowUtil.FollowProgress
            public void fail(boolean z2) {
                super.fail(z2);
                if (z2) {
                    Toast.makeText(this.val$v.getContext(), FaXianFollowBtn.this.getResources().getString(R.string.quxiao_subscribe_fail), 0).show();
                } else {
                    Toast.makeText(this.val$v.getContext(), FaXianFollowBtn.this.getResources().getString(R.string.subscribe_fail), 0).show();
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.jingdong.common.widget.custom.CustomFollowUtil.FollowProgress
            public void start(int i4) {
                super.start(i4);
                FaXianFollowBtn.this.setEnabled(false);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.jingdong.common.widget.custom.CustomFollowUtil.FollowProgress
            public void success(int i4, boolean z2) {
                super.success(i4, z2);
                FaXianFollowBtn faXianFollowBtn = FaXianFollowBtn.this;
                faXianFollowBtn.setFollow(faXianFollowBtn.thisActivity, i4, FaXianFollowBtn.this.authorId);
                if (FaXianFollowBtn.this.isNotice) {
                    FollowObservableManager.getFollowObservableManager().notifyFollowState(FaXianFollowBtn.this.authorId, FaXianFollowBtn.this.hasfollowed);
                }
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mFollowListener != null) {
            FollowObservableManager.getFollowObservableManager().deregisterFollowObserver(this.mFollowListener);
        }
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
        setVisibility(0);
    }

    public void setFollowVisible(boolean z) {
        if (z) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }

    public void setFollowWithJingDou(BaseActivity baseActivity, int i2, String str) {
        this.needJingDou = true;
        setFollow(baseActivity, i2, str);
    }

    public void setFollowed() {
        this.follow.setVisibility(8);
        this.followed.setVisibility(0);
        setBackgroundResource(R.drawable.video_buy_followed_bg);
    }

    public void setNoFollow() {
        this.followed.setVisibility(8);
        this.follow.setVisibility(0);
        setBackgroundResource(R.drawable.video_buy_follow_bg);
    }

    public FaXianFollowBtn(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isNotice = false;
        setVisibility(4);
        setOnClickListener(this);
        View inflate = ImageUtil.inflate(R.layout.layout_faxian_small_follow, (ViewGroup) null, false);
        this.follow = inflate.findViewById(R.id.follow);
        this.followed = inflate.findViewById(R.id.followed);
        addView(inflate);
        this.customFollowUtil = new CustomFollowUtil();
    }

    public void setFollow(BaseActivity baseActivity, int i2, String str, boolean z) {
        setFollow(baseActivity, i2, str);
        this.isNotice = z;
    }

    public void setFollow(BaseActivity baseActivity, int i2, String str, boolean z, FollowObservableManager.FollowListener followListener) {
        setFollow(baseActivity, i2, str, z);
        this.mFollowListener = followListener;
        registerFollowObserver();
    }

    public void setFollow(BaseActivity baseActivity, int i2, String str, int i3) {
        this.needFollowGift = i3;
        setFollow(baseActivity, i2, str);
    }
}
