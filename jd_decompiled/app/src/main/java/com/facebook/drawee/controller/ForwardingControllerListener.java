package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import com.facebook.fresco.ui.common.DimensionsInfo;
import com.facebook.fresco.ui.common.OnDrawControllerListener;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* loaded from: classes.dex */
public class ForwardingControllerListener<INFO> implements ControllerListener<INFO>, OnDrawControllerListener<INFO> {
    private static final String TAG = "FdingControllerListener";
    private final List<ControllerListener<? super INFO>> mListeners = new ArrayList(2);

    public static <INFO> ForwardingControllerListener<INFO> create() {
        return new ForwardingControllerListener<>();
    }

    public static <INFO> ForwardingControllerListener<INFO> of(ControllerListener<? super INFO> controllerListener) {
        ForwardingControllerListener<INFO> create = create();
        create.addListener(controllerListener);
        return create;
    }

    public static <INFO> ForwardingControllerListener<INFO> of(ControllerListener<? super INFO> controllerListener, ControllerListener<? super INFO> controllerListener2) {
        ForwardingControllerListener<INFO> create = create();
        create.addListener(controllerListener);
        create.addListener(controllerListener2);
        return create;
    }

    private synchronized void onException(String str, Throwable th) {
    }

    public synchronized void addListener(ControllerListener<? super INFO> controllerListener) {
        this.mListeners.add(controllerListener);
    }

    public synchronized void clearListeners() {
        this.mListeners.clear();
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public synchronized void onCancelled() {
        int size = this.mListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                this.mListeners.get(i2).onCancelled();
            } catch (Exception e2) {
                onException("InternalListener exception in onCancelled", e2);
            }
        }
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public synchronized void onFailure(String str, Throwable th) {
        int size = this.mListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i2);
                if (controllerListener != null) {
                    controllerListener.onFailure(str, th);
                }
            } catch (Exception e2) {
                onException("InternalListener exception in onFailure", e2);
            }
        }
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public synchronized void onFinalImageSet(String str, @Nullable INFO info, @Nullable Animatable animatable, Drawable drawable) {
        int size = this.mListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i2);
                if (controllerListener != null) {
                    controllerListener.onFinalImageSet(str, info, animatable, drawable);
                }
            } catch (Exception e2) {
                onException("InternalListener exception in onFinalImageSet", e2);
            }
        }
    }

    @Override // com.facebook.fresco.ui.common.OnDrawControllerListener
    public void onImageDrawn(String str, INFO info, DimensionsInfo dimensionsInfo) {
        int size = this.mListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i2);
                if (controllerListener instanceof OnDrawControllerListener) {
                    ((OnDrawControllerListener) controllerListener).onImageDrawn(str, info, dimensionsInfo);
                }
            } catch (Exception e2) {
                onException("InternalListener exception in onImageDrawn", e2);
            }
        }
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public void onIntermediateImageFailed(String str, Throwable th) {
        int size = this.mListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i2);
                if (controllerListener != null) {
                    controllerListener.onIntermediateImageFailed(str, th);
                }
            } catch (Exception e2) {
                onException("InternalListener exception in onIntermediateImageFailed", e2);
            }
        }
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public void onIntermediateImageSet(String str, @Nullable INFO info) {
        int size = this.mListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i2);
                if (controllerListener != null) {
                    controllerListener.onIntermediateImageSet(str, info);
                }
            } catch (Exception e2) {
                onException("InternalListener exception in onIntermediateImageSet", e2);
            }
        }
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public synchronized void onRelease(String str) {
        int size = this.mListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i2);
                if (controllerListener != null) {
                    controllerListener.onRelease(str);
                }
            } catch (Exception e2) {
                onException("InternalListener exception in onRelease", e2);
            }
        }
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public synchronized void onStart() {
        int size = this.mListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                this.mListeners.get(i2).onStart();
            } catch (Exception e2) {
                onException("InternalListener exception in onStart", e2);
            }
        }
    }

    @Override // com.facebook.drawee.controller.ControllerListener
    public synchronized void onSubmit(String str, Object obj) {
        int size = this.mListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                ControllerListener<? super INFO> controllerListener = this.mListeners.get(i2);
                if (controllerListener != null) {
                    controllerListener.onSubmit(str, obj);
                }
            } catch (Exception e2) {
                onException("InternalListener exception in onSubmit", e2);
            }
        }
    }

    public synchronized void removeListener(ControllerListener<? super INFO> controllerListener) {
        int indexOf = this.mListeners.indexOf(controllerListener);
        if (indexOf != -1) {
            this.mListeners.set(indexOf, null);
        }
    }
}
