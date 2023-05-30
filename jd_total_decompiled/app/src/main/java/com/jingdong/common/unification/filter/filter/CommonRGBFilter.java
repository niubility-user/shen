package com.jingdong.common.unification.filter.filter;

import android.opengl.GLES20;

/* loaded from: classes6.dex */
public class CommonRGBFilter extends CommonFilter {
    public static final String RGB_FRAGMENT_SHADER = "  varying highp vec2 textureCoordinate;\n  \n  uniform sampler2D inputImageTexture;\n  uniform highp float red;\n  uniform highp float green;\n  uniform highp float blue;\n  \n  void main()\n  {\n      highp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n      \n      gl_FragColor = vec4(textureColor.r * red, textureColor.g * green, textureColor.b * blue, 1.0);\n  }\n";
    private float mBlue;
    private int mBlueLocation;
    private float mGreen;
    private int mGreenLocation;
    private boolean mIsInitialized;
    private float mRed;
    private int mRedLocation;

    public CommonRGBFilter() {
        this(1.0f, 1.0f, 1.0f);
    }

    @Override // com.jingdong.common.unification.filter.filter.CommonFilter
    public void onInit() {
        super.onInit();
        this.mRedLocation = GLES20.glGetUniformLocation(getProgram(), "red");
        this.mGreenLocation = GLES20.glGetUniformLocation(getProgram(), "green");
        this.mBlueLocation = GLES20.glGetUniformLocation(getProgram(), "blue");
        this.mIsInitialized = true;
        setRed(this.mRed);
        setGreen(this.mGreen);
        setBlue(this.mBlue);
    }

    public void setBlue(float f2) {
        this.mBlue = f2;
        if (this.mIsInitialized) {
            setFloat(this.mBlueLocation, f2);
        }
    }

    public void setGreen(float f2) {
        this.mGreen = f2;
        if (this.mIsInitialized) {
            setFloat(this.mGreenLocation, f2);
        }
    }

    public void setRed(float f2) {
        this.mRed = f2;
        if (this.mIsInitialized) {
            setFloat(this.mRedLocation, f2);
        }
    }

    public CommonRGBFilter(float f2, float f3, float f4) {
        super(CommonFilter.NO_FILTER_VERTEX_SHADER, RGB_FRAGMENT_SHADER);
        this.mIsInitialized = false;
        this.mRed = f2;
        this.mGreen = f3;
        this.mBlue = f4;
    }
}
