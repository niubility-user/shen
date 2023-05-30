package com.jingdong.common.unification.filter.filter;

import android.opengl.GLES20;

/* loaded from: classes6.dex */
public class CommonContrastFilter extends CommonFilter {
    public static final String CONTRAST_FRAGMENT_SHADER = "varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform lowp float contrast;\n \n void main()\n {\n     lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n     \n     gl_FragColor = vec4(((textureColor.rgb - vec3(0.5)) * contrast + vec3(0.5)), textureColor.w);\n }";
    private float mContrast;
    private int mContrastLocation;

    public CommonContrastFilter() {
        this(1.2f);
    }

    @Override // com.jingdong.common.unification.filter.filter.CommonFilter
    public void onInit() {
        super.onInit();
        this.mContrastLocation = GLES20.glGetUniformLocation(getProgram(), "contrast");
    }

    @Override // com.jingdong.common.unification.filter.filter.CommonFilter
    public void onInitialized() {
        super.onInitialized();
        setContrast(this.mContrast);
    }

    public void setContrast(float f2) {
        this.mContrast = f2;
        setFloat(this.mContrastLocation, f2);
    }

    public CommonContrastFilter(float f2) {
        super(CommonFilter.NO_FILTER_VERTEX_SHADER, CONTRAST_FRAGMENT_SHADER);
        this.mContrast = f2;
    }
}
