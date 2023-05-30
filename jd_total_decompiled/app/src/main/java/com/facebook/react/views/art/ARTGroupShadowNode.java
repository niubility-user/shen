package com.facebook.react.views.art;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Region;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class ARTGroupShadowNode extends ARTVirtualNode {
    @Nullable
    protected RectF mClipping;

    private static RectF createClipping(float[] fArr) {
        if (fArr.length == 4) {
            return new RectF(fArr[0], fArr[1], fArr[0] + fArr[2], fArr[1] + fArr[3]);
        }
        throw new JSApplicationIllegalArgumentException("Clipping should be array of length 4 (e.g. [x, y, width, height])");
    }

    @Override // com.facebook.react.views.art.ARTVirtualNode
    public void draw(Canvas canvas, Paint paint, float f2) {
        float f3 = f2 * this.mOpacity;
        if (f3 > 0.01f) {
            saveAndSetupCanvas(canvas);
            RectF rectF = this.mClipping;
            if (rectF != null) {
                float f4 = rectF.left;
                float f5 = this.mScale;
                canvas.clipRect(f4 * f5, rectF.top * f5, rectF.right * f5, rectF.bottom * f5, Region.Op.REPLACE);
            }
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                ARTVirtualNode aRTVirtualNode = (ARTVirtualNode) getChildAt(i2);
                aRTVirtualNode.draw(canvas, paint, f3);
                aRTVirtualNode.markUpdateSeen();
            }
            restoreCanvas(canvas);
        }
    }

    @Override // com.facebook.react.views.art.ARTVirtualNode, com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public boolean isVirtual() {
        return true;
    }

    @ReactProp(name = "clipping")
    public void setClipping(@Nullable ReadableArray readableArray) {
        float[] floatArray = PropHelper.toFloatArray(readableArray);
        if (floatArray != null) {
            this.mClipping = createClipping(floatArray);
            markUpdated();
        }
    }
}
