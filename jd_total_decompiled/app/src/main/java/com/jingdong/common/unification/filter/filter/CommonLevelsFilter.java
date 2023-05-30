package com.jingdong.common.unification.filter.filter;

import android.opengl.GLES20;

/* loaded from: classes6.dex */
public class CommonLevelsFilter extends CommonFilter {
    public static final String LEVELS_FRAGMET_SHADER = " varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform mediump vec3 levelMinimum;\n uniform mediump vec3 levelMiddle;\n uniform mediump vec3 levelMaximum;\n uniform mediump vec3 minOutput;\n uniform mediump vec3 maxOutput;\n \n void main()\n {\n     mediump vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n     \n     gl_FragColor = vec4( mix(minOutput, maxOutput, pow(min(max(textureColor.rgb -levelMinimum, vec3(0.0)) / (levelMaximum - levelMinimum  ), vec3(1.0)), 1.0 /levelMiddle)) , textureColor.a);\n }\n";
    private float[] mMax;
    private int mMaxLocation;
    private float[] mMaxOutput;
    private int mMaxOutputLocation;
    private float[] mMid;
    private int mMidLocation;
    private float[] mMin;
    private int mMinLocation;
    private float[] mMinOutput;
    private int mMinOutputLocation;

    public CommonLevelsFilter() {
        this(new float[]{0.0f, 0.0f, 0.0f}, new float[]{1.0f, 1.0f, 1.0f}, new float[]{1.0f, 1.0f, 1.0f}, new float[]{0.0f, 0.0f, 0.0f}, new float[]{1.0f, 1.0f, 1.0f});
    }

    @Override // com.jingdong.common.unification.filter.filter.CommonFilter
    public void onInit() {
        super.onInit();
        this.mMinLocation = GLES20.glGetUniformLocation(getProgram(), "levelMinimum");
        this.mMidLocation = GLES20.glGetUniformLocation(getProgram(), "levelMiddle");
        this.mMaxLocation = GLES20.glGetUniformLocation(getProgram(), "levelMaximum");
        this.mMinOutputLocation = GLES20.glGetUniformLocation(getProgram(), "minOutput");
        this.mMaxOutputLocation = GLES20.glGetUniformLocation(getProgram(), "maxOutput");
    }

    @Override // com.jingdong.common.unification.filter.filter.CommonFilter
    public void onInitialized() {
        super.onInitialized();
        updateUniforms();
    }

    public void setBlueMin(float f2, float f3, float f4, float f5, float f6) {
        this.mMin[2] = f2;
        this.mMid[2] = f3;
        this.mMax[2] = f4;
        this.mMinOutput[2] = f5;
        this.mMaxOutput[2] = f6;
        updateUniforms();
    }

    public void setGreenMin(float f2, float f3, float f4, float f5, float f6) {
        this.mMin[1] = f2;
        this.mMid[1] = f3;
        this.mMax[1] = f4;
        this.mMinOutput[1] = f5;
        this.mMaxOutput[1] = f6;
        updateUniforms();
    }

    public void setMin(float f2, float f3, float f4, float f5, float f6) {
        setRedMin(f2, f3, f4, f5, f6);
        setGreenMin(f2, f3, f4, f5, f6);
        setBlueMin(f2, f3, f4, f5, f6);
    }

    public void setRedMin(float f2, float f3, float f4, float f5, float f6) {
        this.mMin[0] = f2;
        this.mMid[0] = f3;
        this.mMax[0] = f4;
        this.mMinOutput[0] = f5;
        this.mMaxOutput[0] = f6;
        updateUniforms();
    }

    public void updateUniforms() {
        setFloatVec3(this.mMinLocation, this.mMin);
        setFloatVec3(this.mMidLocation, this.mMid);
        setFloatVec3(this.mMaxLocation, this.mMax);
        setFloatVec3(this.mMinOutputLocation, this.mMinOutput);
        setFloatVec3(this.mMaxOutputLocation, this.mMaxOutput);
    }

    private CommonLevelsFilter(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, float[] fArr5) {
        super(CommonFilter.NO_FILTER_VERTEX_SHADER, LEVELS_FRAGMET_SHADER);
        this.mMin = fArr;
        this.mMid = fArr2;
        this.mMax = fArr3;
        this.mMinOutput = fArr4;
        this.mMaxOutput = fArr5;
        setMin(0.0f, 1.0f, 1.0f, 0.0f, 1.0f);
    }

    public void setMin(float f2, float f3, float f4) {
        setMin(f2, f3, f4, 0.0f, 1.0f);
    }

    public void setBlueMin(float f2, float f3, float f4) {
        setBlueMin(f2, f3, f4, 0.0f, 1.0f);
    }

    public void setGreenMin(float f2, float f3, float f4) {
        setGreenMin(f2, f3, f4, 0.0f, 1.0f);
    }

    public void setRedMin(float f2, float f3, float f4) {
        setRedMin(f2, f3, f4, 0.0f, 1.0f);
    }
}
