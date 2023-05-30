package com.jingdong.common.unification.filter.filter;

import android.opengl.GLES20;

/* loaded from: classes6.dex */
public class CommonBrightnessFilter extends CommonFilter {
    public static final String BRIGHTNESS_FRAGMENT_SHADER = "varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform lowp float brightness;\n \n void main()\n {\n     lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n     \n     gl_FragColor = vec4((textureColor.rgb + vec3(brightness)), textureColor.w);\n }";
    private float mBrightness;
    private int mBrightnessLocation;

    public CommonBrightnessFilter() {
        this(0.0f);
    }

    @Override // com.jingdong.common.unification.filter.filter.CommonFilter
    public void onInit() {
        super.onInit();
        this.mBrightnessLocation = GLES20.glGetUniformLocation(getProgram(), "brightness");
    }

    @Override // com.jingdong.common.unification.filter.filter.CommonFilter
    public void onInitialized() {
        super.onInitialized();
        setBrightness(this.mBrightness);
    }

    public void setBrightness(float f2) {
        this.mBrightness = f2;
        setFloat(this.mBrightnessLocation, f2);
    }

    public CommonBrightnessFilter(float f2) {
        super(CommonFilter.NO_FILTER_VERTEX_SHADER, BRIGHTNESS_FRAGMENT_SHADER);
        this.mBrightness = f2;
    }
}
