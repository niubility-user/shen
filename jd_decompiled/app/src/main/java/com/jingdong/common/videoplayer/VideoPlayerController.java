package com.jingdong.common.videoplayer;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.jdsdk.utils.NetUtils;
import tv.danmaku.ijk.media.example.R;

/* loaded from: classes6.dex */
public class VideoPlayerController {
    public static boolean canShowDialog(Context context) {
        return (context == null || !context.getSharedPreferences("jdAndroidClient", 0).getBoolean("is_remind_none_wifi", true) || NetUtils.isWifi()) ? false : true;
    }

    public static JDDialog createTrafficDailog(final Context context, final ITrafficDialog iTrafficDialog) {
        if (context == null) {
            return null;
        }
        final JDDialog jDDialog = new JDDialog(context);
        jDDialog.setContentView(R.layout.ijkandvrplayer_traffic_alert_dialog);
        TextView textView = (TextView) jDDialog.findViewById(R.id.jdlib_comment_video_dialog_message);
        jDDialog.messageView = textView;
        textView.setText(context.getString(R.string.un_video_un_wifi));
        jDDialog.setCanceledOnTouchOutside(false);
        Button button = (Button) jDDialog.findViewById(R.id.jdlib_comment_video_dialog_pos_button);
        jDDialog.posButton = button;
        button.setText(context.getString(R.string.un_cancel));
        jDDialog.useCancelClickEvent(jDDialog.posButton);
        Button button2 = (Button) jDDialog.findViewById(R.id.jdlib_comment_video_dialog_neg_button);
        jDDialog.negButton = button2;
        button2.setText(context.getString(R.string.un_confirm));
        jDDialog.useCancelClickEvent(jDDialog.negButton);
        final CheckBox checkBox = (CheckBox) jDDialog.findViewById(R.id.jdlib_comment_video_dialog_checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.jingdong.common.videoplayer.VideoPlayerController.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ITrafficDialog iTrafficDialog2 = ITrafficDialog.this;
                if (iTrafficDialog2 != null) {
                    iTrafficDialog2.onCheckedChanged();
                }
            }
        });
        jDDialog.negButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.videoplayer.VideoPlayerController.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Context context2;
                JDDialog.this.cancel();
                ITrafficDialog iTrafficDialog2 = iTrafficDialog;
                if (iTrafficDialog2 != null) {
                    iTrafficDialog2.confirm();
                }
                CheckBox checkBox2 = checkBox;
                if (checkBox2 == null || !checkBox2.isChecked() || (context2 = context) == null) {
                    return;
                }
                context2.getSharedPreferences("jdAndroidClient", 0).edit().putBoolean("is_remind_none_wifi", false).apply();
            }
        });
        jDDialog.posButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.videoplayer.VideoPlayerController.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDDialog.this.cancel();
                ITrafficDialog iTrafficDialog2 = iTrafficDialog;
                if (iTrafficDialog2 != null) {
                    iTrafficDialog2.cancel();
                }
            }
        });
        return jDDialog;
    }
}
