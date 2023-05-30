package com.jingdong.common.unification.scenes.base;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public abstract class BaseScenes {
    public static final int DEFAULT_INT = 0;
    public static final Object DEFAULT_OBJ = null;
    public static final String DEFAULT_STRING = "";
    private Animation currentAnim;
    private View hostView;
    protected Rect rect;
    private List<BaseScenes> childScenes = new ArrayList();
    private Map<String, String> dataMap = new HashMap();
    private Map<String, Integer> intDataMap = new HashMap();
    private Map<String, Object> obDataMap = new HashMap();
    private boolean animIsRunning = false;
    private boolean isVisible = true;

    public BaseScenes(View view, Rect rect) {
        this.hostView = view;
        this.rect = rect;
    }

    private void commit() {
        parseAndUpdateData();
        updateDrawConfig();
    }

    private long computeAnimRemainingTime(Animation animation) {
        long duration = animation.getDuration() - (System.currentTimeMillis() - animation.getStartTime());
        if (duration >= 0) {
            return duration;
        }
        return 0L;
    }

    private void dispatchDrawChild(Canvas canvas, Transformation transformation) {
        if (this.childScenes.isEmpty()) {
            return;
        }
        Iterator<BaseScenes> it = this.childScenes.iterator();
        while (it.hasNext()) {
            Transformation transformation2 = new Transformation();
            transformation2.set(transformation);
            it.next().draw(canvas, transformation2);
        }
    }

    private boolean hasChildScenes() {
        List<BaseScenes> list = this.childScenes;
        return (list == null || list.isEmpty()) ? false : true;
    }

    public void addOrUpdateIntData(String str, int i2) {
        if (str != null) {
            this.intDataMap.put(str, Integer.valueOf(i2));
            commit();
        }
    }

    public void addOrUpdateObjData(String str, Object obj) {
        if (str != null) {
            this.obDataMap.put(str, obj);
            commit();
        }
    }

    public void addOrUpdateStrData(String str, String str2) {
        if (str != null) {
            this.dataMap.put(str, str2);
            commit();
        }
    }

    public void addScenes(BaseScenes baseScenes) {
        if (baseScenes == null || this.childScenes.contains(baseScenes)) {
            return;
        }
        this.childScenes.add(baseScenes);
    }

    public void clearAnim() {
        if (this.currentAnim != null) {
            this.currentAnim = null;
        }
    }

    public void draw(Canvas canvas, Transformation transformation) {
        if (isVisible()) {
            if (transformation == null) {
                transformation = new Transformation();
                transformation.clear();
            }
            Animation animation = this.currentAnim;
            if (animation != null) {
                this.animIsRunning = animation.getTransformation(System.currentTimeMillis(), transformation);
            }
            dispatchDrawChild(canvas, transformation);
            canvas.save();
            drawSelf(canvas, transformation);
            canvas.restore();
        }
    }

    protected abstract void drawSelf(Canvas canvas, Transformation transformation);

    public int getHeight() {
        Rect rect = this.rect;
        if (rect != null) {
            return rect.height();
        }
        return 0;
    }

    public View getHostView() {
        return this.hostView;
    }

    public long getRemainingAnimTime() {
        if (this.currentAnim == null || !isAnimRunning()) {
            return 0L;
        }
        return computeAnimRemainingTime(this.currentAnim);
    }

    public int getWidth() {
        Rect rect = this.rect;
        if (rect != null) {
            return rect.width();
        }
        return 0;
    }

    public boolean isAnimRunning() {
        return this.animIsRunning;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    protected abstract void parseAndUpdateData();

    protected int parseIntData(String str) {
        return parseIntData(str, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object parseObjData(String str) {
        return parseObjData(str, DEFAULT_OBJ);
    }

    protected String parseStrData(String str) {
        return parseStrData(str, "");
    }

    public void release() {
    }

    public void removeIntData(String str) {
        this.intDataMap.remove(str);
        commit();
    }

    public void removeObjData(String str) {
        this.obDataMap.remove(str);
        commit();
    }

    public void removeScenes(BaseScenes baseScenes) {
        if (baseScenes != null) {
            this.childScenes.remove(baseScenes);
        }
    }

    public void removeStrData(String str) {
        this.dataMap.remove(str);
        commit();
    }

    public void setVisible(boolean z) {
        this.isVisible = z;
    }

    public void startAnim(Animation animation) {
        if (animation != null) {
            this.currentAnim = animation;
            animation.initialize(this.rect.width(), this.rect.height(), this.rect.width(), this.rect.height());
            this.currentAnim.start();
        }
    }

    public void stopAnim() {
        Animation animation = this.currentAnim;
        if (animation != null) {
            animation.cancel();
            this.currentAnim = null;
        }
    }

    public void stopChildAnim() {
        if (hasChildScenes()) {
            for (BaseScenes baseScenes : this.childScenes) {
                baseScenes.stopAnim();
                baseScenes.stopChildAnim();
            }
        }
    }

    protected abstract void updateDrawConfig();

    /* JADX INFO: Access modifiers changed from: protected */
    public int parseIntData(String str, int i2) {
        Integer num = this.intDataMap.get(str);
        return num == null ? i2 : num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object parseObjData(String str, Object obj) {
        Object obj2 = this.obDataMap.get(str);
        return obj2 == null ? obj : obj2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String parseStrData(String str, String str2) {
        return this.dataMap.get(str) == null ? str2 : this.dataMap.get(str);
    }
}
