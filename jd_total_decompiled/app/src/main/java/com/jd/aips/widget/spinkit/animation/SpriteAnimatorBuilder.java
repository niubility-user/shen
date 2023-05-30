package com.jd.aips.widget.spinkit.animation;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.util.Property;
import android.view.animation.Interpolator;
import com.jd.aips.widget.spinkit.animation.interpolator.KeyFrameInterpolator;
import com.jd.aips.widget.spinkit.sprite.Sprite;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes12.dex */
public class SpriteAnimatorBuilder {
    private Sprite a;
    private Interpolator b;

    /* renamed from: c  reason: collision with root package name */
    private int f1655c = -1;
    private long d = 2000;

    /* renamed from: e  reason: collision with root package name */
    private int f1656e = 0;

    /* renamed from: f  reason: collision with root package name */
    private Map<String, FrameData> f1657f = new HashMap();

    /* loaded from: classes12.dex */
    class FloatFrameData extends FrameData<Float> {
        public FloatFrameData(SpriteAnimatorBuilder spriteAnimatorBuilder, float[] fArr, Property property, Float[] fArr2) {
            super(spriteAnimatorBuilder, fArr, property, fArr2);
        }
    }

    /* loaded from: classes12.dex */
    class FrameData<T> {
        float[] a;
        Property b;

        /* renamed from: c  reason: collision with root package name */
        T[] f1658c;

        public FrameData(SpriteAnimatorBuilder spriteAnimatorBuilder, float[] fArr, Property property, T[] tArr) {
            this.a = fArr;
            this.b = property;
            this.f1658c = tArr;
        }
    }

    /* loaded from: classes12.dex */
    class IntFrameData extends FrameData<Integer> {
        public IntFrameData(SpriteAnimatorBuilder spriteAnimatorBuilder, float[] fArr, Property property, Integer[] numArr) {
            super(spriteAnimatorBuilder, fArr, property, numArr);
        }
    }

    public SpriteAnimatorBuilder(Sprite sprite) {
        this.a = sprite;
    }

    public ObjectAnimator build() {
        PropertyValuesHolder[] propertyValuesHolderArr = new PropertyValuesHolder[this.f1657f.size()];
        Iterator<Map.Entry<String, FrameData>> it = this.f1657f.entrySet().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            FrameData value = it.next().getValue();
            float[] fArr = value.a;
            Keyframe[] keyframeArr = new Keyframe[fArr.length];
            int i3 = this.f1656e;
            float f2 = fArr[i3];
            while (true) {
                int i4 = this.f1656e;
                Object[] objArr = value.f1658c;
                if (i3 < objArr.length + i4) {
                    int i5 = i3 - i4;
                    int length = i3 % objArr.length;
                    float f3 = fArr[length] - f2;
                    if (f3 < 0.0f) {
                        f3 += fArr[fArr.length - 1];
                    }
                    if (value instanceof IntFrameData) {
                        keyframeArr[i5] = Keyframe.ofInt(f3, ((Integer) objArr[length]).intValue());
                    } else if (value instanceof FloatFrameData) {
                        keyframeArr[i5] = Keyframe.ofFloat(f3, ((Float) objArr[length]).floatValue());
                    } else {
                        keyframeArr[i5] = Keyframe.ofObject(f3, objArr[length]);
                    }
                    i3++;
                }
            }
            propertyValuesHolderArr[i2] = PropertyValuesHolder.ofKeyframe(value.b, keyframeArr);
            i2++;
        }
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.a, propertyValuesHolderArr);
        ofPropertyValuesHolder.setDuration(this.d);
        ofPropertyValuesHolder.setRepeatCount(this.f1655c);
        ofPropertyValuesHolder.setInterpolator(this.b);
        return ofPropertyValuesHolder;
    }

    public SpriteAnimatorBuilder duration(long j2) {
        this.d = j2;
        return this;
    }

    public SpriteAnimatorBuilder easeInOut(float... fArr) {
        interpolator(KeyFrameInterpolator.easeInOut(fArr));
        return this;
    }

    public SpriteAnimatorBuilder interpolator(Interpolator interpolator) {
        this.b = interpolator;
        return this;
    }

    public SpriteAnimatorBuilder repeatCount(int i2) {
        this.f1655c = i2;
        return this;
    }

    public SpriteAnimatorBuilder scale(float[] fArr, Float... fArr2) {
        Property<Sprite, Float> property = Sprite.SCALE;
        int length = fArr.length;
        int length2 = fArr2.length;
        if (length == length2) {
            this.f1657f.put(property.getName(), new FloatFrameData(this, fArr, property, fArr2));
            return this;
        }
        throw new IllegalStateException(String.format(Locale.getDefault(), "The fractions.length must equal values.length, fraction.length[%d], values.length[%d]", Integer.valueOf(length), Integer.valueOf(length2)));
    }

    public SpriteAnimatorBuilder startFrame(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        this.f1656e = i2;
        return this;
    }
}
