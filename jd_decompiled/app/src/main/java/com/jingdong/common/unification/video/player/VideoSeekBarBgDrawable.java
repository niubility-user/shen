package com.jingdong.common.unification.video.player;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import com.jingdong.common.UnLog;
import java.util.List;
import tv.danmaku.ijk.media.JDPlayerSdk;
import tv.danmaku.ijk.media.utils.PlayerSystemUtil;

/* loaded from: classes6.dex */
public class VideoSeekBarBgDrawable extends Drawable {
    private int endColor;
    private int maxProgress;
    private List<Integer> points;
    private int progress;
    private LinearGradient shader;
    private int startColor;
    private int duration = 0;
    private Paint paint = new Paint();
    private Paint paintCircleCenter = new Paint();
    private RectF rectF = new RectF();
    private RectF rectPoint = new RectF();
    private int round = PlayerSystemUtil.dip2px(JDPlayerSdk.getInstance().getApplicationContext(), 2.0f);

    public VideoSeekBarBgDrawable(List<Integer> list, int i2, int i3, int i4) {
        this.points = list;
        this.startColor = i2;
        this.endColor = i3;
        this.maxProgress = i4;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, bounds.width(), bounds.height(), this.startColor, this.endColor, Shader.TileMode.MIRROR);
        this.shader = linearGradient;
        this.paint.setShader(linearGradient);
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.paint.setAntiAlias(true);
        this.paintCircleCenter.setAntiAlias(true);
        this.paintCircleCenter.setColor(-1);
        int width = bounds.width();
        this.rectF.set(0.0f, bounds.centerY() - (bounds.height() / 2), getProgressWidth(this.progress, width), bounds.centerY() + (bounds.height() / 2));
        UnLog.d("bgdrawable", "bounds.width():" + bounds.width());
        UnLog.d("bgdrawable", "bounds.height():" + bounds.height());
        RectF rectF = this.rectF;
        int i2 = this.round;
        canvas.drawRoundRect(rectF, (float) i2, (float) i2, this.paint);
        List<Integer> list = this.points;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (int i3 = 0; i3 < this.points.size(); i3++) {
            float positionWidth = getPositionWidth(this.points.get(i3).intValue(), width);
            if (positionWidth == -1.0f) {
                return;
            }
            this.rectPoint.set(positionWidth - PlayerSystemUtil.dip2px(JDPlayerSdk.getInstance().getApplicationContext(), 1.0f), bounds.centerY() - (bounds.height() / 2), positionWidth + PlayerSystemUtil.dip2px(JDPlayerSdk.getInstance().getApplicationContext(), 1.0f), bounds.centerY() + (bounds.height() / 2));
            canvas.drawRect(this.rectPoint, this.paintCircleCenter);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public float getPositionWidth(int i2, int i3) {
        int i4 = this.duration;
        if (i4 == 0) {
            return -1.0f;
        }
        return (i2 * i3) / i4;
    }

    public float getProgressWidth(int i2, int i3) {
        int i4 = this.maxProgress;
        return i4 == -1 ? i3 : (i2 * i3) / i4;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.paint.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
    }

    public void setDuration(int i2) {
        this.duration = i2;
    }

    public void setPoints(List<Integer> list) {
        this.points = list;
    }

    public void setProgress(int i2) {
        this.progress = i2;
    }
}
