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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tv.danmaku.ijk.media.example.R;
import tv.danmaku.ijk.media.widget.custom.OptionItemAdapter;

/* loaded from: classes11.dex */
public class OptionPopView extends FrameLayout {
    private OnOptionPopCallback callback;
    private LinearLayoutManager linearLayoutManager;

    /* loaded from: classes11.dex */
    public interface OnOptionPopCallback {
        void onDismiss();

        void onSpeedSelect(int i2);
    }

    public OptionPopView(@NonNull Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.ijkandvrplayer_view_pop_option, this);
        inflate.findViewById(R.id.flContainer).setOnClickListener(new View.OnClickListener() { // from class: tv.danmaku.ijk.media.widget.custom.OptionPopView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OptionPopView.this.dismiss(true);
            }
        });
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, 1, false);
        this.linearLayoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new OptionItemAdapter(context, new OptionItemAdapter.OnItemClickListener() { // from class: tv.danmaku.ijk.media.widget.custom.OptionPopView.2
            @Override // tv.danmaku.ijk.media.widget.custom.OptionItemAdapter.OnItemClickListener
            public void onItemClick(int i2) {
                if (OptionPopView.this.callback == null) {
                    return;
                }
                OptionPopView.this.callback.onSpeedSelect(i2);
                OptionPopView.this.dismiss(false);
            }
        }));
    }

    public void dismiss(boolean z) {
        if (getParent() != null && (getParent() instanceof ViewGroup)) {
            ((ViewGroup) getParent()).removeView(this);
        }
        OnOptionPopCallback onOptionPopCallback = this.callback;
        if (onOptionPopCallback == null || !z) {
            return;
        }
        onOptionPopCallback.onDismiss();
    }

    public void show(final int i2, ViewGroup viewGroup, OnOptionPopCallback onOptionPopCallback) {
        if (viewGroup == null) {
            return;
        }
        viewGroup.addView(this);
        this.callback = onOptionPopCallback;
        post(new Runnable() { // from class: tv.danmaku.ijk.media.widget.custom.OptionPopView.3
            @Override // java.lang.Runnable
            public void run() {
                if (OptionPopView.this.linearLayoutManager == null) {
                    return;
                }
                int i3 = 0;
                while (i3 < OptionPopView.this.linearLayoutManager.getChildCount()) {
                    View findViewByPosition = OptionPopView.this.linearLayoutManager.findViewByPosition(i3);
                    if (findViewByPosition != null) {
                        ((TextView) findViewByPosition.findViewById(R.id.tvInfo)).setTextColor(OptionPopView.this.getResources().getColor(i3 == i2 ? R.color.ijkandvrplayer_speed_btn_select : R.color.ijkandvrplayer_default_progress_color));
                    }
                    i3++;
                }
            }
        });
    }

    public OptionPopView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public OptionPopView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init(context);
    }

    @TargetApi(21)
    public OptionPopView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        init(context);
    }
}
