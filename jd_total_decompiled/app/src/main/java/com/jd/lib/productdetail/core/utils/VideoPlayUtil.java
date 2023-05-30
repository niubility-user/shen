package com.jd.lib.productdetail.core.utils;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.jd.lib.productdetail.core.R;
import com.jingdong.common.deeplinkhelper.VideoPlayerDeepLinkHelper;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.jdsdk.utils.NetUtils;

/* loaded from: classes15.dex */
public class VideoPlayUtil {
    private static final String TAG = "VideoPlayUtil";
    private CheckBox dialogCheckBox;
    private Context mContext;

    /* loaded from: classes15.dex */
    public interface OnVideoPlayListener {
        void onPlay();
    }

    public VideoPlayUtil(Context context) {
        this.mContext = context;
    }

    private JDDialog createDialog() {
        JDDialog jDDialog = new JDDialog(this.mContext);
        jDDialog.setContentView(R.layout.lib_pd_core_comment_video_dialog);
        TextView textView = (TextView) jDDialog.findViewById(R.id.comment_video_dialog_message);
        jDDialog.messageView = textView;
        textView.setText(this.mContext.getString(R.string.lib_pd_core_video_nowifi_tips));
        Button button = (Button) jDDialog.findViewById(R.id.comment_video_dialog_pos_button);
        jDDialog.posButton = button;
        button.setText(this.mContext.getString(R.string.lib_pd_core_cancel));
        jDDialog.useCancelClickEvent(jDDialog.posButton);
        Button button2 = (Button) jDDialog.findViewById(R.id.comment_video_dialog_neg_button);
        jDDialog.negButton = button2;
        button2.setText(this.mContext.getString(R.string.lib_pd_core_ok));
        jDDialog.useCancelClickEvent(jDDialog.negButton);
        jDDialog.setCanceledOnTouchOutside(true);
        CheckBox checkBox = (CheckBox) jDDialog.findViewById(R.id.comment_video_dialog_checkbox);
        this.dialogCheckBox = checkBox;
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.jd.lib.productdetail.core.utils.VideoPlayUtil.7
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            }
        });
        return jDDialog;
    }

    public void play(final String str, final String str2, final String str3) {
        if (NetUtils.isNetworkAvailable()) {
            if (CommonBase.getJdSharedPreferences().getBoolean("is_remind_none_wifi", true) && !NetUtils.isWifi()) {
                final JDDialog createDialog = createDialog();
                createDialog.negButton.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.core.utils.VideoPlayUtil.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (VideoPlayUtil.this.dialogCheckBox != null && VideoPlayUtil.this.dialogCheckBox.isChecked()) {
                            CommonBase.getJdSharedPreferences().edit().putBoolean("is_remind_none_wifi", false).commit();
                        }
                        createDialog.cancel();
                        VideoPlayerDeepLinkHelper.startVideoPlayer(VideoPlayUtil.this.mContext, str, str2, str3, 0, 4);
                    }
                });
                createDialog.posButton.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.core.utils.VideoPlayUtil.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        createDialog.cancel();
                    }
                });
                createDialog.show();
                return;
            }
            VideoPlayerDeepLinkHelper.startVideoPlayer(this.mContext, str, str2, str3, 0, 4);
            return;
        }
        VideoPlayerDeepLinkHelper.startVideoPlayer(this.mContext, str, str2, str3, 0, 4);
    }

    public void playLive(final String str, final String str2, final String str3, final String str4) {
        if (NetUtils.isNetworkAvailable()) {
            if (CommonBase.getJdSharedPreferences().getBoolean("is_remind_none_wifi", true) && !NetUtils.isWifi()) {
                final JDDialog createDialog = createDialog();
                createDialog.negButton.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.core.utils.VideoPlayUtil.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (VideoPlayUtil.this.dialogCheckBox != null && VideoPlayUtil.this.dialogCheckBox.isChecked()) {
                            CommonBase.getJdSharedPreferences().edit().putBoolean("is_remind_none_wifi", false).commit();
                        }
                        createDialog.cancel();
                        VideoPlayerDeepLinkHelper.startVideoPlayerForLive(VideoPlayUtil.this.mContext, str, str2, str3, 2, str4);
                    }
                });
                createDialog.posButton.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.core.utils.VideoPlayUtil.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        createDialog.cancel();
                    }
                });
                createDialog.show();
                return;
            }
            VideoPlayerDeepLinkHelper.startVideoPlayerForLive(this.mContext, str, str2, str3, 2, str4);
        }
    }

    public void play(final OnVideoPlayListener onVideoPlayListener) {
        if (NetUtils.isNetworkAvailable()) {
            if (CommonBase.getJdSharedPreferences().getBoolean("is_remind_none_wifi", true) && !NetUtils.isWifi()) {
                final JDDialog createDialog = createDialog();
                createDialog.negButton.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.core.utils.VideoPlayUtil.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (VideoPlayUtil.this.dialogCheckBox != null && VideoPlayUtil.this.dialogCheckBox.isChecked()) {
                            CommonBase.getJdSharedPreferences().edit().putBoolean("is_remind_none_wifi", false).commit();
                        }
                        createDialog.cancel();
                        onVideoPlayListener.onPlay();
                    }
                });
                createDialog.posButton.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.core.utils.VideoPlayUtil.6
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        createDialog.cancel();
                    }
                });
                createDialog.show();
                return;
            }
            onVideoPlayListener.onPlay();
        }
    }
}
