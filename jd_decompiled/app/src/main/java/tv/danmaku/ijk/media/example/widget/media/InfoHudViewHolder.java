package tv.danmaku.ijk.media.example.widget.media;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.internal.view.SupportMenu;
import com.huawei.hms.adapter.internal.CommonCode;
import java.util.HashMap;
import java.util.Locale;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* loaded from: classes11.dex */
public class InfoHudViewHolder {
    private static final int MSG_UPDATE_HUD = 1;
    private Context context;
    private LinearLayout leftInfoLayout;
    private IjkMediaPlayer mMediaPlayer;
    private LinearLayout rightInfoLayout;
    private HashMap<String, TextView> mRowMap = new HashMap<>();
    @SuppressLint({"HandlerLeak"})
    private final Handler mHandler = new Handler() { // from class: tv.danmaku.ijk.media.example.widget.media.InfoHudViewHolder.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i2;
            if (message.what == 1 && InfoHudViewHolder.this.mMediaPlayer != null) {
                int propertyLong = (int) InfoHudViewHolder.this.mMediaPlayer.getPropertyLong(20003);
                if (propertyLong == 1) {
                    InfoHudViewHolder.this.setRowValue("vdec", "avcodec");
                } else if (propertyLong != 2) {
                    InfoHudViewHolder.this.setRowValue("vdec", "unknown");
                } else {
                    InfoHudViewHolder.this.setRowValue("vdec", "MediaCodec");
                }
                float propertyFloat = InfoHudViewHolder.this.mMediaPlayer.getPropertyFloat(10002);
                float propertyFloat2 = InfoHudViewHolder.this.mMediaPlayer.getPropertyFloat(10001);
                InfoHudViewHolder infoHudViewHolder = InfoHudViewHolder.this;
                Locale locale = Locale.US;
                infoHudViewHolder.setRowValue("fps", String.format(locale, "%.2f / %.2f", Float.valueOf(propertyFloat2), Float.valueOf(propertyFloat)));
                long propertyLong2 = InfoHudViewHolder.this.mMediaPlayer.getPropertyLong(20005);
                long propertyLong3 = InfoHudViewHolder.this.mMediaPlayer.getPropertyLong(20006);
                long propertyLong4 = InfoHudViewHolder.this.mMediaPlayer.getPropertyLong(20007);
                long propertyLong5 = InfoHudViewHolder.this.mMediaPlayer.getPropertyLong(20008);
                long propertyLong6 = InfoHudViewHolder.this.mMediaPlayer.getPropertyLong(20200);
                long propertyLong7 = InfoHudViewHolder.this.mMediaPlayer.getPropertyLong(20100);
                long propertyLong8 = InfoHudViewHolder.this.mMediaPlayer.getPropertyLong(20300);
                InfoHudViewHolder.this.setRowValue("v_cache", String.format(locale, "%s, %s", InfoHudViewHolder.formattedDurationMilli(propertyLong2), InfoHudViewHolder.formattedSize(propertyLong4)));
                InfoHudViewHolder.this.setRowValue("a_cache", String.format(locale, "%s, %s", InfoHudViewHolder.formattedDurationMilli(propertyLong3), InfoHudViewHolder.formattedSize(propertyLong5)));
                InfoHudViewHolder.this.setRowValue("seek_cost", String.format(locale, "%d ms", Long.valueOf(propertyLong8)));
                InfoHudViewHolder.this.setRowValue("tcp_speed", String.format(locale, "%s", InfoHudViewHolder.formattedSpeed(propertyLong6, 1000L)));
                InfoHudViewHolder.this.setRowValue("bit_rate", String.format(locale, "%.2f kbs", Float.valueOf(((float) propertyLong7) / 1000.0f)));
                InfoHudViewHolder.this.setRowValue("display_duration", String.format(locale, "%s", InfoHudViewHolder.formattedDurationMilli2(InfoHudViewHolder.this.mMediaPlayer.getPropertyLong(22001))));
                long propertyLong9 = InfoHudViewHolder.this.mMediaPlayer.getPropertyLong(22002);
                if (propertyLong9 > 0) {
                    i2 = 1;
                    InfoHudViewHolder.this.setRowValue("audio_cb_duration", String.format(locale, "%s", InfoHudViewHolder.formattedDurationMilli2(propertyLong9)));
                } else {
                    i2 = 1;
                }
                InfoHudViewHolder.this.mHandler.removeMessages(i2);
                InfoHudViewHolder.this.mHandler.sendEmptyMessageDelayed(i2, 500L);
            }
        }
    };

    public InfoHudViewHolder(Context context, ViewGroup viewGroup) {
        this.context = context;
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(0);
        viewGroup.addView(linearLayout, new FrameLayout.LayoutParams(-2, -2, 17));
        LinearLayout linearLayout2 = new LinearLayout(context);
        this.leftInfoLayout = linearLayout2;
        linearLayout2.setOrientation(1);
        LinearLayout linearLayout3 = new LinearLayout(context);
        this.rightInfoLayout = linearLayout3;
        linearLayout3.setOrientation(1);
        linearLayout.addView(this.leftInfoLayout);
        linearLayout.addView(this.rightInfoLayout);
    }

    private TextView createValueView(String str) {
        TextView textView = new TextView(this.context);
        textView.setTextColor(SupportMenu.CATEGORY_MASK);
        textView.setText(str + ":  ");
        this.leftInfoLayout.addView(textView);
        TextView textView2 = new TextView(this.context);
        textView2.setTextColor(SupportMenu.CATEGORY_MASK);
        this.rightInfoLayout.addView(textView2);
        return textView2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String formattedDurationMilli(long j2) {
        return j2 >= 1000 ? String.format(Locale.US, "%.2f sec", Float.valueOf(((float) j2) / 1000.0f)) : String.format(Locale.US, "%d msec", Long.valueOf(j2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String formattedDurationMilli2(long j2) {
        return String.format(Locale.US, "%d msec", Long.valueOf(j2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String formattedSize(long j2) {
        return j2 >= 100000 ? String.format(Locale.US, "%.2f MB", Float.valueOf((((float) j2) / 1000.0f) / 1000.0f)) : j2 >= 100 ? String.format(Locale.US, "%.1f KB", Float.valueOf(((float) j2) / 1000.0f)) : String.format(Locale.US, "%d B", Long.valueOf(j2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String formattedSpeed(long j2, long j3) {
        if (j3 > 0 && j2 > 0) {
            float f2 = (((float) j2) * 1000.0f) / ((float) j3);
            return f2 >= 1000000.0f ? String.format(Locale.US, "%.2f MB/s", Float.valueOf((f2 / 1000.0f) / 1000.0f)) : f2 >= 1000.0f ? String.format(Locale.US, "%.1f KB/s", Float.valueOf(f2 / 1000.0f)) : String.format(Locale.US, "%d B/s", Long.valueOf(f2));
        }
        return "0 B/s";
    }

    private String formattedTime(long j2) {
        long j3 = j2 / 1000;
        long j4 = j3 / 3600;
        long j5 = (j3 % 3600) / 60;
        long j6 = j3 % 60;
        return j2 <= 0 ? "--:--" : j4 >= 100 ? String.format(Locale.US, "%d:%02d:%02d", Long.valueOf(j4), Long.valueOf(j5), Long.valueOf(j6)) : j4 > 0 ? String.format(Locale.US, "%02d:%02d:%02d", Long.valueOf(j4), Long.valueOf(j5), Long.valueOf(j6)) : String.format(Locale.US, "%02d:%02d", Long.valueOf(j5), Long.valueOf(j6));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRowValue(String str, String str2) {
        TextView textView = this.mRowMap.get(str);
        if (textView == null) {
            textView = createValueView(str);
            this.mRowMap.put(str, textView);
        }
        textView.setText(str2);
    }

    public void setMediaPlayer(IjkMediaPlayer ijkMediaPlayer) {
        this.mMediaPlayer = ijkMediaPlayer;
        if (ijkMediaPlayer != null) {
            this.mHandler.sendEmptyMessageDelayed(1, 500L);
        } else {
            this.mHandler.removeMessages(1);
        }
    }

    public void updateLoadCost(long j2) {
        setRowValue("load_cost", String.format(Locale.US, "%d ms", Long.valueOf(j2)));
        if (this.mMediaPlayer == null) {
            return;
        }
        setRowValue(CommonCode.MapKey.HAS_RESOLUTION, "w:" + this.mMediaPlayer.getVideoWidth() + " h:" + this.mMediaPlayer.getVideoHeight() + " Num:" + this.mMediaPlayer.getVideoSarNum() + " Den:" + this.mMediaPlayer.getVideoSarDen());
    }

    public void updateSeekCost(long j2) {
        setRowValue("seek_cost", String.format(Locale.US, "%d ms", Long.valueOf(j2)));
    }
}
