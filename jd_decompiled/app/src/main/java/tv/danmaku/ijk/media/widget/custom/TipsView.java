package tv.danmaku.ijk.media.widget.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import tv.danmaku.ijk.media.example.R;

/* loaded from: classes11.dex */
public class TipsView extends FrameLayout {
    private Boolean isShow;
    private OnTipsInvoke tipsInvoke;
    private TextView tvInfo;
    private TextView tvRefresh;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: tv.danmaku.ijk.media.widget.custom.TipsView$3  reason: invalid class name */
    /* loaded from: classes11.dex */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$tv$danmaku$ijk$media$widget$custom$TipsView$TipType;

        static {
            int[] iArr = new int[TipType.values().length];
            $SwitchMap$tv$danmaku$ijk$media$widget$custom$TipsView$TipType = iArr;
            try {
                iArr[TipType.USER_DEFINE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$tv$danmaku$ijk$media$widget$custom$TipsView$TipType[TipType.NOT_WIFI.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$tv$danmaku$ijk$media$widget$custom$TipsView$TipType[TipType.PLAY_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$tv$danmaku$ijk$media$widget$custom$TipsView$TipType[TipType.NET_ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$tv$danmaku$ijk$media$widget$custom$TipsView$TipType[TipType.OTHER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes11.dex */
    public interface OnTipsInvoke {
        void doRetry();
    }

    /* loaded from: classes11.dex */
    public enum TipType {
        NOT_WIFI,
        PLAY_ERROR,
        NET_ERROR,
        USER_DEFINE,
        OTHER
    }

    public TipsView(@NonNull Context context) {
        this(context, null);
    }

    private void initView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.ijkandvrplayer_view_tips, this);
        this.tvInfo = (TextView) inflate.findViewById(R.id.tvInfo);
        TextView textView = (TextView) inflate.findViewById(R.id.tvRefresh);
        this.tvRefresh = textView;
        textView.setOnClickListener(new View.OnClickListener() { // from class: tv.danmaku.ijk.media.widget.custom.TipsView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TipsView.this.tipsInvoke != null) {
                    TipsView.this.tipsInvoke.doRetry();
                }
                TipsView.this.dismiss();
            }
        });
    }

    public void dismiss() {
        dismiss(0L);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void showTip(TipType tipType, ViewGroup viewGroup) {
        showTip(tipType, viewGroup, -1);
    }

    public TipsView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void dismiss(long j2) {
        postDelayed(new Runnable() { // from class: tv.danmaku.ijk.media.widget.custom.TipsView.2
            @Override // java.lang.Runnable
            public void run() {
                if (TipsView.this.getContext() == null || TipsView.this.getParent() == null || !(TipsView.this.getParent() instanceof ViewGroup)) {
                    return;
                }
                ((ViewGroup) TipsView.this.getParent()).removeView(TipsView.this);
                TipsView.this.isShow = Boolean.FALSE;
            }
        }, j2);
    }

    public void showTip(TipType tipType, ViewGroup viewGroup, int i2) {
        showTip(tipType, viewGroup, null, i2);
    }

    public TipsView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isShow = Boolean.FALSE;
        initView(context);
    }

    public void showTip(TipType tipType, ViewGroup viewGroup, OnTipsInvoke onTipsInvoke) {
        showTip(tipType, viewGroup, onTipsInvoke, -1);
    }

    public void showTip(TipType tipType, ViewGroup viewGroup, OnTipsInvoke onTipsInvoke, int i2) {
        if (viewGroup == null || this.isShow.booleanValue()) {
            return;
        }
        int i3 = AnonymousClass3.$SwitchMap$tv$danmaku$ijk$media$widget$custom$TipsView$TipType[tipType.ordinal()];
        boolean z = true;
        if (i3 == 1) {
            if (i2 == -1) {
                i2 = R.string.ijkandvrplayer_tips_not_wifi;
            }
            this.tvInfo.setText(i2);
            this.tvRefresh.setVisibility(8);
        } else if (i3 != 2) {
            if (i3 == 3) {
                this.tvInfo.setText(R.string.ijkandvrplayer_tips_play_error);
                this.tvRefresh.setVisibility(0);
            } else if (i3 == 4) {
                this.tvInfo.setText(R.string.ijkandvrplayer_tips_net_error);
                this.tvRefresh.setVisibility(0);
            } else if (i3 == 5) {
                this.tvInfo.setText(R.string.ijkandvrplayer_tips_other);
                this.tvRefresh.setVisibility(0);
            }
            z = false;
        } else {
            this.tvInfo.setText(R.string.ijkandvrplayer_tips_not_wifi);
            this.tvRefresh.setVisibility(8);
        }
        viewGroup.addView(this);
        this.tipsInvoke = onTipsInvoke;
        this.isShow = Boolean.TRUE;
        if (z) {
            dismiss(2000L);
        }
    }

    @TargetApi(21)
    public TipsView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.isShow = Boolean.FALSE;
        initView(context);
    }
}
