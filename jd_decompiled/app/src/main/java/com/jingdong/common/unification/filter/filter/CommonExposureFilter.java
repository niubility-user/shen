package com.jingdong.common.unification.filter.filter;

import android.opengl.GLES20;

/* loaded from: classes6.dex */
public class CommonExposureFilter extends CommonFilter {
    public static final String EXPOSURE_FRAGMENT_SHADER = " varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform highp float exposure;\n \n void main()\n {\n     highp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n     \n     gl_FragColor = vec4(textureColor.rgb * pow(2.0, exposure), textureColor.w);\n } ";
    private float mExposure;
    private int mExposureLocation;

    public CommonExposureFilter() {
        this(1.0f);
    }

    @Override // com.jingdong.common.unification.filter.filter.CommonFilter
    public void onInit() {
        super.onInit();
        this.mExposureLocation = GLES20.glGetUniformLocation(getProgram(), "exposure");
    }

    @Override // com.jingdong.common.unification.filter.filter.CommonFilter
    public void onInitialized() {
        super.onInitialized();
        setExposure(this.mExposure);
    }

    public void setExposure(float f2) {
        this.mExposure = f2;
        setFloat(this.mExposureLocation, f2);
    }

    public CommonExposureFilter(float f2) {
        super(CommonFilter.NO_FILTER_VERTEX_SHADER, EXPOSURE_FRAGMENT_SHADER);
        this.mExposure = f2;
    }
}
