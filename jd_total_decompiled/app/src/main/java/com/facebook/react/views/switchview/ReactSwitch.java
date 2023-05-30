package com.facebook.react.views.switchview;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.Switch;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
class ReactSwitch extends Switch {
    private boolean mAllowChange;
    @Nullable
    private Integer mTrackColorForFalse;
    @Nullable
    private Integer mTrackColorForTrue;

    public ReactSwitch(Context context) {
        super(context);
        this.mAllowChange = true;
        this.mTrackColorForFalse = null;
        this.mTrackColorForTrue = null;
    }

    @Override // android.widget.Switch, android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        if (!this.mAllowChange || isChecked() == z) {
            return;
        }
        this.mAllowChange = false;
        super.setChecked(z);
        setTrackColor(z);
    }

    void setColor(Drawable drawable, @Nullable Integer num) {
        if (num == null) {
            drawable.clearColorFilter();
        } else {
            drawable.setColorFilter(num.intValue(), PorterDuff.Mode.MULTIPLY);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOn(boolean z) {
        if (isChecked() != z) {
            super.setChecked(z);
            setTrackColor(z);
        }
        this.mAllowChange = true;
    }

    @Override // android.widget.Switch
    public void setShowText(boolean z) {
    }

    public void setThumbColor(@Nullable Integer num) {
        setColor(super.getThumbDrawable(), num);
    }

    public void setTrackColor(@Nullable Integer num) {
        setColor(super.getTrackDrawable(), num);
    }

    public void setTrackColorForFalse(@Nullable Integer num) {
        if (num == this.mTrackColorForFalse) {
            return;
        }
        this.mTrackColorForFalse = num;
        if (isChecked()) {
            return;
        }
        setTrackColor(this.mTrackColorForFalse);
    }

    public void setTrackColorForTrue(@Nullable Integer num) {
        if (num == this.mTrackColorForTrue) {
            return;
        }
        this.mTrackColorForTrue = num;
        if (isChecked()) {
            setTrackColor(this.mTrackColorForTrue);
        }
    }

    private void setTrackColor(boolean z) {
        Integer num = this.mTrackColorForTrue;
        if (num == null && this.mTrackColorForFalse == null) {
            return;
        }
        if (!z) {
            num = this.mTrackColorForFalse;
        }
        setTrackColor(num);
    }
}
