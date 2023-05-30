package com.jd.aips.verify.face.biz;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.jd.aips.verify.identity.R;

/* loaded from: classes12.dex */
public class VerityFaceNormalAnim {
    public void resetAnimation(@NonNull ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).stop();
        }
        imageView.setImageResource(R.drawable.aips_fcvf_avatar_default);
    }

    public void startFaceAnimation(@NonNull ImageView imageView, int i2) {
        try {
            switch (i2) {
                case 1002:
                    imageView.setImageResource(R.drawable.aips_fcvf_anim_mouth);
                    break;
                case 1003:
                    imageView.setImageResource(R.drawable.aips_fcvf_anim_blink);
                    break;
                case 1004:
                    imageView.setImageResource(R.drawable.aips_fcvf_anim_shake);
                    break;
                case 1005:
                    imageView.setImageResource(R.drawable.aips_fcvf_anim_node);
                    break;
            }
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof Animatable) {
                ((Animatable) drawable).start();
            }
        } catch (Exception unused) {
        }
    }

    public void stopAnimation(@NonNull ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).stop();
        }
    }
}
