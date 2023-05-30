package tv.danmaku.ijk.media.widget.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import tv.danmaku.ijk.media.example.R;
import tv.danmaku.ijk.media.widget.controller.JDPlayerController;
import tv.danmaku.ijk.media.widget.custom.OptionPopView;

/* loaded from: classes11.dex */
public class SpeedControlButton extends TextView implements View.OnClickListener {
    private JDPlayerController anchorView;
    private SpeedButtonCallback mCallback;
    private boolean mIsFull;
    private OptionPopView optionPopView;
    public static List<String> mDataList = new ArrayList();
    public static int curSpeedIndex = 2;
    private static boolean hasChangeSpeed = false;

    /* loaded from: classes11.dex */
    public interface SpeedButtonCallback {
        void onSpeedSelect(float f2);
    }

    public SpeedControlButton(Context context) {
        super(context);
    }

    private void init() {
        setOnClickListener(this);
        if (hasChangeSpeed) {
            updateBtnText();
        }
    }

    public void updateBtnText() {
        List<String> list = mDataList;
        if (list == null) {
            return;
        }
        String str = list.get(curSpeedIndex);
        SpeedButtonCallback speedButtonCallback = this.mCallback;
        if (speedButtonCallback != null) {
            speedButtonCallback.onSpeedSelect(Float.parseFloat(str));
        }
        setText(getContext().getString(R.string.ijkandvrplayer_speed_format, str));
        hasChangeSpeed = true;
    }

    public void dismiss() {
        OptionPopView optionPopView = this.optionPopView;
        if (optionPopView != null) {
            optionPopView.dismiss(false);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (!this.mIsFull) {
            curSpeedIndex = (curSpeedIndex + 1) % mDataList.size();
            updateBtnText();
            return;
        }
        JDPlayerController jDPlayerController = this.anchorView;
        if (jDPlayerController == null || jDPlayerController.getParent() == null || !(this.anchorView.getParent() instanceof ViewGroup)) {
            return;
        }
        this.anchorView.forceHide();
        if (this.optionPopView == null) {
            this.optionPopView = new OptionPopView(getContext());
        }
        this.optionPopView.show(curSpeedIndex, (ViewGroup) this.anchorView.getParent(), new OptionPopView.OnOptionPopCallback() { // from class: tv.danmaku.ijk.media.widget.custom.SpeedControlButton.1
            {
                SpeedControlButton.this = this;
            }

            @Override // tv.danmaku.ijk.media.widget.custom.OptionPopView.OnOptionPopCallback
            public void onDismiss() {
                if (SpeedControlButton.this.anchorView == null) {
                    return;
                }
                SpeedControlButton.this.anchorView.forceShow();
            }

            @Override // tv.danmaku.ijk.media.widget.custom.OptionPopView.OnOptionPopCallback
            public void onSpeedSelect(int i2) {
                SpeedControlButton.curSpeedIndex = i2;
                SpeedControlButton.this.updateBtnText();
            }
        });
    }

    public void release() {
        hasChangeSpeed = false;
        curSpeedIndex = 2;
    }

    public void setFullMode(boolean z) {
        this.mIsFull = z;
    }

    public void setSpeedCallback(JDPlayerController jDPlayerController, SpeedButtonCallback speedButtonCallback) {
        this.anchorView = jDPlayerController;
        this.mCallback = speedButtonCallback;
    }

    public void setSpeedList(int i2, List<String> list) {
        mDataList = list;
        curSpeedIndex = i2;
    }

    public SpeedControlButton(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public SpeedControlButton(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init();
    }

    @TargetApi(21)
    public SpeedControlButton(Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        init();
    }
}
