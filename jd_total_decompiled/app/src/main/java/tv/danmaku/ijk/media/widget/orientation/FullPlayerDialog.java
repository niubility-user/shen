package tv.danmaku.ijk.media.widget.orientation;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import tv.danmaku.ijk.media.example.R;
import tv.danmaku.ijk.media.utils.PlayerSystemUtil;

/* loaded from: classes11.dex */
public class FullPlayerDialog extends DialogFragment {
    private FrameLayout contentParent;
    private boolean isLand;
    private OnDismissListener mOnDismissListener;
    private View playerView;

    /* loaded from: classes11.dex */
    public interface OnDismissListener {
        void onDismiss(boolean z);
    }

    private void addToFullParent(Activity activity) {
        View view;
        if (this.contentParent == null || (view = this.playerView) == null) {
            return;
        }
        if (view.getParent() != null && (this.playerView.getParent() instanceof ViewGroup)) {
            ((ViewGroup) this.playerView.getParent()).removeView(this.playerView);
        }
        this.contentParent.addView(this.playerView, 0, new FrameLayout.LayoutParams(-1, -1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void exitFullScreen(boolean z) {
        View view;
        FrameLayout frameLayout = this.contentParent;
        if (frameLayout != null && (view = this.playerView) != null) {
            frameLayout.removeView(view);
        }
        OnDismissListener onDismissListener = this.mOnDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(z);
        }
    }

    public void dismiss(Activity activity, boolean z) {
        exitFullScreen(z);
        if (activity == null || activity.isFinishing() || activity.isRestricted()) {
            return;
        }
        dismissAllowingStateLoss();
    }

    public void enterFullScreen(Activity activity, View view, boolean z) {
        this.playerView = view;
        this.isLand = z;
        if (activity instanceof FragmentActivity) {
            show(((FragmentActivity) activity).getSupportFragmentManager(), "FullPlayerDialog");
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    @NonNull
    public Dialog onCreateDialog(@Nullable Bundle bundle) {
        Dialog dialog = new Dialog(getActivity(), R.style.ijkandvrplayerFullScreenDialog);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: tv.danmaku.ijk.media.widget.orientation.FullPlayerDialog.1
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == 4) {
                    FullPlayerDialog.this.exitFullScreen(false);
                    FullPlayerDialog.this.dismissAllowingStateLoss();
                }
                return false;
            }
        });
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        attributes.gravity = 17;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 28 && !this.isLand) {
            attributes.layoutInDisplayCutoutMode = 1;
        }
        dialog.getWindow().setAttributes(attributes);
        if (i2 >= 21) {
            dialog.getWindow().addFlags(Integer.MIN_VALUE);
        }
        PlayerSystemUtil.hideUIMenu(dialog.getWindow(), true);
        dialog.getWindow().setFlags(1024, 1024);
        this.contentParent = (FrameLayout) LayoutInflater.from(getActivity()).inflate(R.layout.ijkandvrplayer_view_player_full, (ViewGroup) null);
        addToFullParent(getActivity());
        dialog.addContentView(this.contentParent, new ViewGroup.LayoutParams(-1, -1));
        return dialog;
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }
}
