package com.jdcloud.vsr.shading;

import com.jdcloud.vsr.JDTContext;
import com.jdcloud.vsr.geometry.IntRectangle;
import com.jdcloud.vsr.utils.VariablesBundle;

/* loaded from: classes18.dex */
public class ImageShader extends VariablesBundle {
    ShaderType shaderType;
    public static String INPUT_IMAGE_ID = getInputImageId();
    public static String INPUT_IMAGE_DECL_TYPE = getInputImageDeclType();

    /* loaded from: classes18.dex */
    public enum ShaderType {
        COPY,
        SHARP
    }

    public ImageShader(JDTContext jDTContext) {
        super(newImageShader(jDTContext));
    }

    private static native String getInputImageDeclType();

    private static native String getInputImageId();

    private static native long newImageShader(JDTContext jDTContext);

    private static native long newImageShaderType(JDTContext jDTContext, int i2);

    private native void setOutputClipping(IntRectangle intRectangle);

    private native void setSourceCode(long j2, String str);

    private native void setVertexSourceCode(long j2, String str);

    public void setSourceCode(String str) {
        setSourceCode(this.handle, str);
    }

    public void setVertexSourceCode(String str) {
        setVertexSourceCode(this.handle, str);
    }

    public ImageShader(JDTContext jDTContext, int i2) {
        super(newImageShaderType(jDTContext, i2));
    }
}
