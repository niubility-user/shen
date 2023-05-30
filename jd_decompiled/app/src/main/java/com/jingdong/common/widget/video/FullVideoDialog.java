package com.jingdong.common.widget.video;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.jingdong.common.R;
import com.jingdong.common.utils.BackForegroundWatcher;
import com.jingdong.common.widget.custom.CustomIjkPlayer;

/* loaded from: classes12.dex */
public class FullVideoDialog {
    private FrameLayout contentParent;
    private Dialog dialog;
    private BackForegroundWatcher.BackForegroundListener mBackForegroundListener;
    private OnDismissListener mOnDismissListener;
    private CustomIjkPlayer mPlayer;
    private View titleTopView;

    /* loaded from: classes12.dex */
    public interface OnDismissListener {
        void onDismiss(boolean z);
    }

    private void addToFullParent(final Activity activity, String str) {
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(activity).inflate(R.layout.fx_ijk_video_full_content, (ViewGroup) null);
        this.contentParent = frameLayout;
        frameLayout.addView(this.mPlayer, 0, new FrameLayout.LayoutParams(-1, -1));
        ((TextView) this.contentParent.findViewById(R.id.ijk_title_text)).setText(str);
        this.contentParent.findViewById(R.id.ijk_title_back_btn).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.video.FullVideoDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FullVideoDialog.this.dismiss(activity, false);
            }
        });
        this.titleTopView = this.contentParent.findViewById(R.id.ijk_title_layout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideFull(boolean z) {
        BackForegroundWatcher.getInstance().unRegisterListener(this.mBackForegroundListener);
        this.contentParent.removeView(this.mPlayer);
        OnDismissListener onDismissListener = this.mOnDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(z);
        }
    }

    @Deprecated
    public void dismiss(boolean z) {
        this.dialog.dismiss();
        hideFull(z);
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }

    public void showFullScreen(final Activity activity, CustomIjkPlayer customIjkPlayer, String str) {
        this.mPlayer = customIjkPlayer;
        addToFullParent(activity, str);
        this.mPlayer.setAutoHideHeaderBar(this.titleTopView);
        if (activity == null || activity.isFinishing() || activity.isRestricted()) {
            return;
        }
        Dialog dialog = new Dialog(activity, R.style.dialog_ijk_video_fullscreen);
        this.dialog = dialog;
        customIjkPlayer.setKeepScreenOnDialog(dialog);
        this.dialog.setCancelable(true);
        this.dialog.setCanceledOnTouchOutside(true);
        this.dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.jingdong.common.widget.video.FullVideoDialog.2
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                FullVideoDialog.this.hideFull(false);
            }
        });
        this.dialog.getWindow().setFlags(1024, 1024);
        this.dialog.getWindow().setWindowAnimations(R.style.dialog_annim_bottom_style);
        this.dialog.addContentView(this.contentParent, new ViewGroup.LayoutParams(-1, -1));
        this.dialog.show();
        this.mBackForegroundListener = new BackForegroundWatcher.BackForegroundListener() { // from class: com.jingdong.common.widget.video.FullVideoDialog.3
            @Override // com.jingdong.common.utils.BackForegroundWatcher.BackForegroundListener
            public void onBackToForeground() {
            }

            @Override // com.jingdong.common.utils.BackForegroundWatcher.BackForegroundListener
            public void onForeToBackground() {
                FullVideoDialog.this.dismiss(activity, true);
            }
        };
        BackForegroundWatcher.getInstance().registerListener(this.mBackForegroundListener);
    }

    public void dismiss(Activity activity, boolean z) {
        Dialog dialog;
        hideFull(z);
        if (activity == null || activity.isFinishing() || activity.isRestricted() || (dialog = this.dialog) == null) {
            return;
        }
        dialog.dismiss();
    }
}
