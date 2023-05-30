package com.jd.aips.verify.face.biz;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.jd.aips.verify.identity.R;

/* loaded from: classes12.dex */
public class VerityFaceDialogAnim {
    public void animationReqVerityProcess(ImageView imageView) {
        animationStopVerify(imageView);
        imageView.setVisibility(0);
        imageView.setImageResource(R.drawable.aips_fcvf_loading);
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

    public void animationReqVeritySuccess(ImageView imageView) {
        animationStopVerify(imageView);
        imageView.setVisibility(0);
        imageView.setImageResource(R.drawable.aips_fcvf_anim_success);
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

    public void animationStopVerify(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        imageView.setVisibility(8);
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).stop();
        }
        imageView.setImageDrawable(null);
    }
}
