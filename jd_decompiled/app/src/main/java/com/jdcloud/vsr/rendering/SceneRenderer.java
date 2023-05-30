package com.jdcloud.vsr.rendering;

import com.jdcloud.vsr.JDTBitmap;
import com.jdcloud.vsr.JDTContext;
import com.jdcloud.vsr.Task;
import com.jdcloud.vsr.exceptions.CoreException;
import com.jdcloud.vsr.rendering.Scene;

/* loaded from: classes18.dex */
public final class SceneRenderer extends Task {
    private JDTBitmap background;
    private JDTBitmap output;
    private Scene scene;

    /* loaded from: classes18.dex */
    public enum OutputMapping {
        STRETCH,
        FIT_WIDTH_TO_TOP,
        FIT_WIDTH,
        FIT_HEIGHT
    }

    public SceneRenderer(JDTContext jDTContext, boolean z) {
        super(jDTContext, z ? newSceneRenderer(jDTContext) : -100L);
    }

    private native int getOutputMapping(long j2);

    private native boolean getOutputPixelsFetching(long j2);

    private native int getOutputReferenceWidth(long j2);

    private static native long newSceneRenderer(JDTContext jDTContext);

    private native Scene.Layer pickLayer(long j2, float f2, float f3, boolean z);

    private native void resetOutput(long j2);

    private native void setBackgroundBitmap(long j2, JDTBitmap jDTBitmap);

    private native void setOutput(long j2, JDTBitmap jDTBitmap);

    private native void setOutputMapping(long j2, int i2);

    private native void setOutputPixelsFetching(long j2, boolean z);

    private native void setOutputReferenceWidth(long j2, int i2);

    private native void setScene(long j2, Scene scene);

    public JDTBitmap getBackground() {
        return this.background;
    }

    public JDTBitmap getOutput() {
        return this.output;
    }

    public OutputMapping getOutputMapping() {
        return OutputMapping.values()[getOutputMapping(this.handle)];
    }

    public boolean getOutputPixelFetching() {
        return getOutputPixelsFetching(this.handle);
    }

    public int getOutputReferenceWidth() {
        return getOutputReferenceWidth(this.handle);
    }

    public Scene getScene() {
        return this.scene;
    }

    public Scene.Layer pickLayer(float f2, float f3, boolean z) {
        return pickLayer(this.handle, f2, f3, z);
    }

    public float render() throws CoreException {
        return this.context.performTask(this);
    }

    public void resetOutput() {
        this.output = null;
        resetOutput(this.handle);
    }

    public void setBackground(JDTBitmap jDTBitmap) {
        this.background = jDTBitmap;
        if (jDTBitmap != null) {
            setBackgroundBitmap(this.handle, jDTBitmap);
        } else {
            setBackgroundBitmap(this.handle, null);
        }
    }

    public void setOutput(JDTBitmap jDTBitmap) {
        setOutput(this.handle, jDTBitmap);
        this.output = jDTBitmap;
    }

    public void setOutputMapping(OutputMapping outputMapping) {
        setOutputMapping(this.handle, outputMapping.ordinal());
    }

    public void setOutputPixelsFetching(boolean z) {
        setOutputPixelsFetching(this.handle, z);
    }

    public void setOutputReferenceWidth(int i2) {
        setOutputReferenceWidth(this.handle, i2);
    }

    public void setScene(Scene scene) {
        setScene(this.handle, scene);
        this.scene = scene;
    }
}
