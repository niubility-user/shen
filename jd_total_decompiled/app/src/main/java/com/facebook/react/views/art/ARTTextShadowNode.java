package com.facebook.react.views.art;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.text.TextUtils;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class ARTTextShadowNode extends ARTShapeShadowNode {
    private static final int DEFAULT_FONT_SIZE = 12;
    private static final String PROP_FONT = "font";
    private static final String PROP_FONT_FAMILY = "fontFamily";
    private static final String PROP_FONT_SIZE = "fontSize";
    private static final String PROP_FONT_STYLE = "fontStyle";
    private static final String PROP_FONT_WEIGHT = "fontWeight";
    private static final String PROP_LINES = "lines";
    private static final int TEXT_ALIGNMENT_CENTER = 2;
    private static final int TEXT_ALIGNMENT_LEFT = 0;
    private static final int TEXT_ALIGNMENT_RIGHT = 1;
    @Nullable
    private ReadableMap mFrame;
    private int mTextAlignment = 0;

    private void applyTextPropertiesToPaint(Paint paint) {
        ReadableMap map;
        int i2 = this.mTextAlignment;
        int i3 = 2;
        if (i2 == 0) {
            paint.setTextAlign(Paint.Align.LEFT);
        } else if (i2 == 1) {
            paint.setTextAlign(Paint.Align.RIGHT);
        } else if (i2 == 2) {
            paint.setTextAlign(Paint.Align.CENTER);
        }
        ReadableMap readableMap = this.mFrame;
        if (readableMap == null || !readableMap.hasKey(PROP_FONT) || (map = this.mFrame.getMap(PROP_FONT)) == null) {
            return;
        }
        paint.setTextSize((map.hasKey("fontSize") ? (float) map.getDouble("fontSize") : 12.0f) * this.mScale);
        boolean z = map.hasKey("fontWeight") && "bold".equals(map.getString("fontWeight"));
        boolean z2 = map.hasKey("fontStyle") && "italic".equals(map.getString("fontStyle"));
        if (z && z2) {
            i3 = 3;
        } else if (z) {
            i3 = 1;
        } else if (!z2) {
            i3 = 0;
        }
        paint.setTypeface(Typeface.create(map.getString("fontFamily"), i3));
    }

    @Override // com.facebook.react.views.art.ARTShapeShadowNode, com.facebook.react.views.art.ARTVirtualNode
    public void draw(Canvas canvas, Paint paint, float f2) {
        ReadableArray array;
        ReadableMap readableMap = this.mFrame;
        if (readableMap == null) {
            return;
        }
        float f3 = f2 * this.mOpacity;
        if (f3 > 0.01f && readableMap.hasKey(PROP_LINES) && (array = this.mFrame.getArray(PROP_LINES)) != null && array.size() != 0) {
            saveAndSetupCanvas(canvas);
            int size = array.size();
            String[] strArr = new String[size];
            for (int i2 = 0; i2 < size; i2++) {
                strArr[i2] = array.getString(i2);
            }
            String join = TextUtils.join(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, strArr);
            if (setupStrokePaint(paint, f3)) {
                applyTextPropertiesToPaint(paint);
                Path path = this.mPath;
                if (path == null) {
                    canvas.drawText(join, 0.0f, -paint.ascent(), paint);
                } else {
                    canvas.drawTextOnPath(join, path, 0.0f, 0.0f, paint);
                }
            }
            if (setupFillPaint(paint, f3)) {
                applyTextPropertiesToPaint(paint);
                Path path2 = this.mPath;
                if (path2 == null) {
                    canvas.drawText(join, 0.0f, -paint.ascent(), paint);
                } else {
                    canvas.drawTextOnPath(join, path2, 0.0f, 0.0f, paint);
                }
            }
            restoreCanvas(canvas);
            markUpdateSeen();
        }
    }

    @ReactProp(defaultInt = 0, name = "alignment")
    public void setAlignment(int i2) {
        this.mTextAlignment = i2;
    }

    @ReactProp(name = "frame")
    public void setFrame(@Nullable ReadableMap readableMap) {
        this.mFrame = readableMap;
    }
}
