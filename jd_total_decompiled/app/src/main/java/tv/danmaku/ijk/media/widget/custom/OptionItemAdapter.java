package tv.danmaku.ijk.media.widget.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import tv.danmaku.ijk.media.example.R;
import tv.danmaku.ijk.media.utils.PlayerSystemUtil;

/* loaded from: classes11.dex */
public class OptionItemAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final OnItemClickListener itemClickListener;
    private final Context mContext;
    private int preHeight;

    /* loaded from: classes11.dex */
    public interface OnItemClickListener {
        void onItemClick(int i2);
    }

    /* loaded from: classes11.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvInfo;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.tvInfo = (TextView) view.findViewById(R.id.tvInfo);
        }
    }

    public OptionItemAdapter(Context context, OnItemClickListener onItemClickListener) {
        List<String> list;
        this.mContext = context;
        this.itemClickListener = onItemClickListener;
        if (PlayerSystemUtil.getScreenSize(context) == null || (list = SpeedControlButton.mDataList) == null || list.size() <= 0) {
            return;
        }
        this.preHeight = (PlayerSystemUtil.getScreenSize(context).heightPixels - PlayerSystemUtil.dip2px(context, 44.0f)) / SpeedControlButton.mDataList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return SpeedControlButton.mDataList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i2) {
        if (!SpeedControlButton.mDataList.isEmpty()) {
            viewHolder.tvInfo.setText(this.mContext.getString(R.string.ijkandvrplayer_speed_format, SpeedControlButton.mDataList.get(i2)));
        }
        viewHolder.tvInfo.setOnClickListener(new View.OnClickListener() { // from class: tv.danmaku.ijk.media.widget.custom.OptionItemAdapter.1
            {
                OptionItemAdapter.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (OptionItemAdapter.this.itemClickListener == null) {
                    return;
                }
                OptionItemAdapter.this.itemClickListener.onItemClick(i2);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ijkandvrplayer_view_pop_item, viewGroup, false);
        if (this.preHeight != 0) {
            ViewGroup.LayoutParams layoutParams = inflate.getLayoutParams();
            layoutParams.height = this.preHeight;
            inflate.setLayoutParams(layoutParams);
        }
        return new ViewHolder(inflate);
    }
}
