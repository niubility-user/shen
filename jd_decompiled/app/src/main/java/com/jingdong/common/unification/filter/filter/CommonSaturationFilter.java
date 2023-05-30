package com.jingdong.common.unification.filter.filter;

import android.opengl.GLES20;

/* loaded from: classes6.dex */
public class CommonSaturationFilter extends CommonFilter {
    public static final String SATURATION_FRAGMENT_SHADER = " varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform lowp float saturation;\n \n // Values from \"Graphics Shaders: Theory and Practice\" by Bailey and Cunningham\n const mediump vec3 luminanceWeighting = vec3(0.2125, 0.7154, 0.0721);\n \n void main()\n {\n    lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n    lowp float luminance = dot(textureColor.rgb, luminanceWeighting);\n    lowp vec3 greyScaleColor = vec3(luminance);\n    \n    gl_FragColor = vec4(mix(greyScaleColor, textureColor.rgb, saturation), textureColor.w);\n     \n }";
    private float mSaturation;
    private int mSaturationLocation;

    public CommonSaturationFilter() {
        this(1.0f);
    }

    @Override // com.jingdong.common.unification.filter.filter.CommonFilter
    public void onInit() {
        super.onInit();
        this.mSaturationLocation = GLES20.glGetUniformLocation(getProgram(), "saturation");
    }

    @Override // com.jingdong.common.unification.filter.filter.CommonFilter
    public void onInitialized() {
        super.onInitialized();
        setSaturation(this.mSaturation);
    }

    public void setSaturation(float f2) {
        this.mSaturation = f2;
        setFloat(this.mSaturationLocation, f2);
    }

    public CommonSaturationFilter(float f2) {
        super(CommonFilter.NO_FILTER_VERTEX_SHADER, SATURATION_FRAGMENT_SHADER);
        this.mSaturation = f2;
    }
}
