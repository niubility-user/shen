package com.googlecode.mp4parser.h264.model;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.util.Arrays;

/* loaded from: classes12.dex */
public class ScalingMatrix {
    public ScalingList[] ScalingList4x4;
    public ScalingList[] ScalingList8x8;

    public String toString() {
        StringBuilder sb = new StringBuilder("ScalingMatrix{ScalingList4x4=");
        ScalingList[] scalingListArr = this.ScalingList4x4;
        sb.append(scalingListArr == null ? null : Arrays.asList(scalingListArr));
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append(", ScalingList8x8=");
        ScalingList[] scalingListArr2 = this.ScalingList8x8;
        sb.append(scalingListArr2 != null ? Arrays.asList(scalingListArr2) : null);
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append('}');
        return sb.toString();
    }
}
