package com.jingdong.app.mall.bundle.xanimation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieListener;
import com.airbnb.lottie.LottieTask;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.utils.LottieValueAnimator;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.ScaleXY;
import com.jingdong.app.mall.bundle.xanimation.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class d {
    private com.jingdong.app.mall.bundle.xanimation.interfaces.b a;
    private LottieValueAnimator b = new LottieValueAnimator();

    /* renamed from: c  reason: collision with root package name */
    private List<com.jingdong.app.mall.bundle.xanimation.c> f8302c = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class a implements LottieListener<LottieComposition> {
        final /* synthetic */ e a;
        final /* synthetic */ Context b;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.jingdong.app.mall.bundle.xanimation.d$a$a  reason: collision with other inner class name */
        /* loaded from: classes3.dex */
        public class C0262a implements Animator.AnimatorListener {
            C0262a() {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                if (d.this.a != null) {
                    d.this.a.onAnimationCancel(animator);
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (d.this.a != null) {
                    d.this.a.onAnimationEnd(animator);
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
                if (d.this.a != null) {
                    d.this.a.onAnimationRepeat(animator);
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                if (d.this.a != null) {
                    d.this.a.onAnimationStart(animator);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes3.dex */
        public class b implements ViewTreeObserver.OnWindowAttachListener {
            b() {
            }

            @Override // android.view.ViewTreeObserver.OnWindowAttachListener
            public void onWindowAttached() {
            }

            @Override // android.view.ViewTreeObserver.OnWindowAttachListener
            public void onWindowDetached() {
                d.this.h();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes3.dex */
        public class c implements Application.ActivityLifecycleCallbacks {
            c() {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(@NonNull Activity activity) {
                d.this.h();
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(@NonNull Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(@NonNull Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(@NonNull Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(@NonNull Activity activity) {
            }
        }

        a(e eVar, Context context) {
            this.a = eVar;
            this.b = context;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b(ValueAnimator valueAnimator) {
            if (valueAnimator instanceof LottieValueAnimator) {
                float animatedValueAbsolute = ((LottieValueAnimator) valueAnimator).getAnimatedValueAbsolute();
                if (d.this.a != null) {
                    d.this.a.onAnimationUpdate(animatedValueAbsolute);
                }
                if (d.this.f8302c == null) {
                    return;
                }
                for (int i2 = 0; i2 < d.this.f8302c.size(); i2++) {
                    com.jingdong.app.mall.bundle.xanimation.c cVar = (com.jingdong.app.mall.bundle.xanimation.c) d.this.f8302c.get(i2);
                    TransformKeyframeAnimation c2 = cVar.c();
                    if (c2 != null) {
                        c2.setProgress(animatedValueAbsolute);
                    }
                    FloatKeyframeAnimation a = cVar.a();
                    if (a != null) {
                        a.setProgress(animatedValueAbsolute);
                    }
                }
            }
        }

        @Override // com.airbnb.lottie.LottieListener
        /* renamed from: c  reason: merged with bridge method [inline-methods] */
        public void onResult(LottieComposition lottieComposition) {
            View view;
            e eVar = this.a;
            String str = eVar.f8310g;
            String str2 = eVar.f8307c;
            int i2 = eVar.f8309f;
            if (!TextUtils.isEmpty(str) && (i2 == 0 || i2 == 1)) {
                view = com.jingdong.app.mall.bundle.xanimation.f.d.a(this.b, str);
            } else {
                view = this.a.a;
            }
            if (view == null) {
                return;
            }
            if (d.this.a != null) {
                d.this.a.onLottieLoadResult(lottieComposition);
            }
            if (d.this.b == null) {
                return;
            }
            d.this.b.setRepeatCount(this.a.d);
            d.this.b.setSpeed(this.a.f8308e);
            d.this.b.setComposition(lottieComposition);
            d.this.b.setFrame(lottieComposition.getFrameForProgress(d.this.b.getAnimatedFraction()));
            Iterator<Map.Entry<String, List<Layer>>> it = lottieComposition.getPrecomps().entrySet().iterator();
            while (it.hasNext()) {
                d dVar = d.this;
                dVar.g(it.next().getValue(), dVar.f8302c);
            }
            List<Layer> layers = lottieComposition.getLayers();
            d dVar2 = d.this;
            dVar2.g(layers, dVar2.f8302c);
            d.this.b.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.app.mall.bundle.xanimation.a
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    d.a.this.b(valueAnimator);
                }
            });
            d.this.b.addListener(new C0262a());
            if (Build.VERSION.SDK_INT >= 18) {
                view.getViewTreeObserver().addOnWindowAttachListener(new b());
            } else {
                ((Application) this.b.getApplicationContext()).registerActivityLifecycleCallbacks(new c());
            }
            if ((view instanceof ViewGroup) && i2 == 1) {
                if (d.this.f8302c == null) {
                    return;
                }
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                int size = d.this.f8302c.size();
                for (int i3 = 0; i3 < Math.min(size, childCount); i3++) {
                    d.this.f(viewGroup.getChildAt((size - i3) - 1), ((com.jingdong.app.mall.bundle.xanimation.c) d.this.f8302c.get(i3)).c(), null);
                }
            } else {
                d dVar3 = d.this;
                dVar3.f(view, com.jingdong.app.mall.bundle.xanimation.f.b.a(str2, dVar3.f8302c), lottieComposition.getBounds());
            }
            d.this.b.playAnimation();
        }
    }

    /* loaded from: classes3.dex */
    class b implements LottieListener<Throwable> {
        b() {
        }

        @Override // com.airbnb.lottie.LottieListener
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th) {
            th.printStackTrace();
            if (d.this.a != null) {
                d.this.a.onAnimationError(th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class c implements BaseKeyframeAnimation.AnimationListener {
        final /* synthetic */ Layer a;
        final /* synthetic */ FloatKeyframeAnimation b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ List f8306c;

        c(Layer layer, FloatKeyframeAnimation floatKeyframeAnimation, List list) {
            this.a = layer;
            this.b = floatKeyframeAnimation;
            this.f8306c = list;
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
        public void onValueChanged() {
            if (d.this.a != null) {
                d.this.a.onLayerStatusListener(this.a.getName(), (int) this.b.getFloatValue(), this.f8306c.size());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(final View view, final TransformKeyframeAnimation transformKeyframeAnimation, final Rect rect) {
        if (view == null || transformKeyframeAnimation == null) {
            return;
        }
        transformKeyframeAnimation.addListener(new BaseKeyframeAnimation.AnimationListener() { // from class: com.jingdong.app.mall.bundle.xanimation.b
            @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
            public final void onValueChanged() {
                d.j(TransformKeyframeAnimation.this, view, rect);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(List<Layer> list, List<com.jingdong.app.mall.bundle.xanimation.c> list2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            Layer layer = list.get(i2);
            if (layer != null) {
                com.jingdong.app.mall.bundle.xanimation.c cVar = new com.jingdong.app.mall.bundle.xanimation.c();
                List<Keyframe<Float>> inOutKeyframes = layer.getInOutKeyframes();
                if (inOutKeyframes != null && !inOutKeyframes.isEmpty()) {
                    FloatKeyframeAnimation floatKeyframeAnimation = new FloatKeyframeAnimation(inOutKeyframes);
                    floatKeyframeAnimation.addUpdateListener(new c(layer, floatKeyframeAnimation, list));
                    cVar.d(floatKeyframeAnimation);
                }
                cVar.e(layer);
                cVar.f(layer.getTransform().createAnimation());
                list2.add(cVar);
            }
        }
    }

    private void i(LottieValueAnimator lottieValueAnimator) {
        if (lottieValueAnimator == null) {
            return;
        }
        if (lottieValueAnimator.isRunning()) {
            lottieValueAnimator.cancel();
        }
        lottieValueAnimator.removeAllListeners();
        lottieValueAnimator.clearComposition();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void j(TransformKeyframeAnimation transformKeyframeAnimation, View view, Rect rect) {
        float floatValue;
        BaseKeyframeAnimation<ScaleXY, ScaleXY> scale = transformKeyframeAnimation.getScale();
        if (scale != null) {
            ScaleXY value = scale.getValue();
            if (value.getScaleX() != 1.0f || value.getScaleY() != 1.0f) {
                view.setScaleX(value.getScaleX());
                view.setScaleY(value.getScaleY());
            }
        }
        BaseKeyframeAnimation<Float, Float> rotation = transformKeyframeAnimation.getRotation();
        if (rotation != null) {
            if (rotation instanceof ValueCallbackKeyframeAnimation) {
                floatValue = rotation.getValue().floatValue();
            } else {
                floatValue = ((FloatKeyframeAnimation) rotation).getFloatValue();
            }
            if (floatValue != 0.0f) {
                view.setRotation(floatValue);
            }
        }
        if (transformKeyframeAnimation.getOpacity() != null) {
            view.setAlpha(transformKeyframeAnimation.getOpacity().getValue().intValue() / 100.0f);
        }
        BaseKeyframeAnimation<?, PointF> position = transformKeyframeAnimation.getPosition();
        BaseKeyframeAnimation<PointF, PointF> anchorPoint = transformKeyframeAnimation.getAnchorPoint();
        if (rect != null && anchorPoint != null) {
            int width = rect.width();
            int height = rect.height();
            PointF value2 = anchorPoint.getValue();
            if (width != 0 && height != 0) {
                float f2 = width;
                if (value2.x <= f2) {
                    float f3 = height;
                    if (value2.y <= f3) {
                        int width2 = view.getWidth();
                        int height2 = view.getHeight();
                        float f4 = (width2 * value2.x) / f2;
                        float f5 = (height2 * value2.y) / f3;
                        if (f4 != view.getPivotX()) {
                            view.setPivotX(f4);
                        }
                        if (f5 != view.getPivotY()) {
                            view.setPivotY(f5);
                        }
                    }
                }
            }
        }
        if (position != null && anchorPoint != null) {
            PointF value3 = position.getValue();
            PointF value4 = anchorPoint.getValue();
            if (value3 != null) {
                float f6 = value3.x;
                if (f6 == 0.0f && value3.y == 0.0f) {
                    return;
                }
                float f7 = value4.x;
                if (f7 == 0.0f && value4.y == 0.0f) {
                    return;
                }
                float f8 = f6 - f7;
                if (view.getTranslationX() != f8) {
                    view.setTranslationX(f8);
                }
                float f9 = value3.y - value4.y;
                if (view.getTranslationY() != f9) {
                    view.setTranslationY(f9);
                }
            }
        } else if (position != null) {
            PointF value5 = position.getValue();
            if (value5 != null) {
                if (value5.x == 0.0f && value5.y == 0.0f) {
                    return;
                }
                float translationX = view.getTranslationX();
                float f10 = value5.x;
                if (translationX != f10) {
                    view.setTranslationX(f10);
                }
                float translationY = view.getTranslationY();
                float f11 = value5.y;
                if (translationY != f11) {
                    view.setTranslationY(f11);
                }
            }
        } else if (anchorPoint != null) {
            PointF value6 = anchorPoint.getValue();
            if (value6.x == 0.0f && value6.y == 0.0f) {
                return;
            }
            float translationX2 = view.getTranslationX();
            float f12 = value6.x;
            if (translationX2 != f12) {
                view.setTranslationX(-f12);
            }
            float translationY2 = view.getTranslationY();
            float f13 = value6.y;
            if (translationY2 != f13) {
                view.setTranslationY(-f13);
            }
        }
    }

    public void h() {
        i(this.b);
        this.b = null;
        this.f8302c = null;
        this.a = null;
    }

    public void k(com.jingdong.app.mall.bundle.xanimation.interfaces.b bVar) {
        this.a = bVar;
    }

    public void l(Context context, e eVar) {
        LottieTask<LottieComposition> fromAsset;
        if (!com.jingdong.app.mall.bundle.xanimation.f.a.b(context) || eVar == null || com.jingdong.app.mall.bundle.xanimation.f.c.a(eVar.b)) {
            return;
        }
        if (eVar.b.startsWith("http")) {
            fromAsset = LottieCompositionFactory.fromUrl(context, eVar.b);
        } else {
            fromAsset = LottieCompositionFactory.fromAsset(context, eVar.b);
        }
        fromAsset.addListener(new a(eVar, context));
        fromAsset.addFailureListener(new b());
    }
}
