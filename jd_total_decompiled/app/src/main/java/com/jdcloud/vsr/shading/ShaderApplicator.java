package com.jdcloud.vsr.shading;

import android.util.ArrayMap;
import com.jdcloud.vsr.JDTBitmap;
import com.jdcloud.vsr.JDTContext;
import com.jdcloud.vsr.Task;
import java.util.Map;

/* loaded from: classes18.dex */
public class ShaderApplicator extends Task {
    private JDTBitmap output;
    private Map<String, JDTBitmap> samplers;
    private ImageShader shader;

    public ShaderApplicator(JDTContext jDTContext) {
        super(jDTContext, newShaderApplicator());
        this.samplers = new ArrayMap();
    }

    private native void addSampler(long j2, JDTBitmap jDTBitmap, String str);

    private static native long newShaderApplicator();

    private native void setOutput(long j2, JDTBitmap jDTBitmap);

    private native void setShader(long j2, ImageShader imageShader);

    public void addSampler(JDTBitmap jDTBitmap, String str) {
        this.samplers.put(str, jDTBitmap);
        addSampler(this.handle, jDTBitmap, str);
    }

    public JDTBitmap getOutput() {
        return this.output;
    }

    public ImageShader getShader() {
        return this.shader;
    }

    public void setOutput(JDTBitmap jDTBitmap) {
        this.output = jDTBitmap;
        setOutput(this.handle, jDTBitmap);
    }

    public void setShader(ImageShader imageShader) {
        this.shader = imageShader;
        setShader(this.handle, imageShader);
    }

    public void addSampler(JDTBitmap jDTBitmap) {
        addSampler(jDTBitmap, ImageShader.INPUT_IMAGE_ID);
    }
}
