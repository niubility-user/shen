package com.jingdong.common.unification.filter.filter;

import android.annotation.SuppressLint;
import android.opengl.GLES20;
import com.jingdong.sdk.platform.business.personal.R2;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class CommonFilterGroup extends CommonFilter {
    protected List<CommonFilter> mFilters;
    private int[] mFrameBufferTextures;
    private int[] mFrameBuffers;
    private final FloatBuffer mGLCubeBuffer;
    private final FloatBuffer mGLTextureBuffer;
    private final FloatBuffer mGLTextureFlipBuffer;
    protected List<CommonFilter> mMergedFilters;

    public CommonFilterGroup() {
        this(null);
    }

    private void destroyFramebuffers() {
        int[] iArr = this.mFrameBufferTextures;
        if (iArr != null) {
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
            this.mFrameBufferTextures = null;
        }
        int[] iArr2 = this.mFrameBuffers;
        if (iArr2 != null) {
            GLES20.glDeleteFramebuffers(iArr2.length, iArr2, 0);
            this.mFrameBuffers = null;
        }
    }

    public void addFilter(CommonFilter commonFilter) {
        if (commonFilter == null) {
            return;
        }
        this.mFilters.add(commonFilter);
        updateMergedFilters();
    }

    public List<CommonFilter> getFilters() {
        return this.mFilters;
    }

    public List<CommonFilter> getMergedFilters() {
        return this.mMergedFilters;
    }

    @Override // com.jingdong.common.unification.filter.filter.CommonFilter
    public void onDestroy() {
        destroyFramebuffers();
        Iterator<CommonFilter> it = this.mFilters.iterator();
        while (it.hasNext()) {
            it.next().destroy();
        }
        super.onDestroy();
    }

    @Override // com.jingdong.common.unification.filter.filter.CommonFilter
    @SuppressLint({"WrongCall"})
    public void onDraw(int i2, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        List<CommonFilter> list;
        runPendingOnDrawTasks();
        if (!isInitialized() || this.mFrameBuffers == null || this.mFrameBufferTextures == null || (list = this.mMergedFilters) == null) {
            return;
        }
        int size = list.size();
        int i3 = 0;
        while (i3 < size) {
            CommonFilter commonFilter = this.mMergedFilters.get(i3);
            int i4 = size - 1;
            boolean z = i3 < i4;
            if (z) {
                GLES20.glBindFramebuffer(36160, this.mFrameBuffers[i3]);
                GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            }
            if (i3 == 0) {
                commonFilter.onDraw(i2, floatBuffer, floatBuffer2);
            } else if (i3 == i4) {
                commonFilter.onDraw(i2, this.mGLCubeBuffer, size % 2 == 0 ? this.mGLTextureFlipBuffer : this.mGLTextureBuffer);
            } else {
                commonFilter.onDraw(i2, this.mGLCubeBuffer, this.mGLTextureBuffer);
            }
            if (z) {
                GLES20.glBindFramebuffer(36160, 0);
                i2 = this.mFrameBufferTextures[i3];
            }
            i3++;
        }
    }

    @Override // com.jingdong.common.unification.filter.filter.CommonFilter
    public void onInit() {
        super.onInit();
        Iterator<CommonFilter> it = this.mFilters.iterator();
        while (it.hasNext()) {
            it.next().init();
        }
    }

    @Override // com.jingdong.common.unification.filter.filter.CommonFilter
    public void onOutputSizeChanged(int i2, int i3) {
        super.onOutputSizeChanged(i2, i3);
        if (this.mFrameBuffers != null) {
            destroyFramebuffers();
        }
        int size = this.mFilters.size();
        for (int i4 = 0; i4 < size; i4++) {
            this.mFilters.get(i4).onOutputSizeChanged(i2, i3);
        }
        List<CommonFilter> list = this.mMergedFilters;
        if (list == null || list.size() <= 0) {
            return;
        }
        int i5 = 1;
        int size2 = this.mMergedFilters.size() - 1;
        this.mFrameBuffers = new int[size2];
        this.mFrameBufferTextures = new int[size2];
        int i6 = 0;
        while (i6 < size2) {
            GLES20.glGenFramebuffers(i5, this.mFrameBuffers, i6);
            GLES20.glGenTextures(i5, this.mFrameBufferTextures, i6);
            GLES20.glBindTexture(R2.color.c_FF0017, this.mFrameBufferTextures[i6]);
            GLES20.glTexImage2D(R2.color.c_FF0017, 0, R2.dimen.jdreact_base_ui_jd_tip_page_button_paddingLeft, i2, i3, 0, R2.dimen.jdreact_base_ui_jd_tip_page_button_paddingLeft, R2.dimen.HugersTextSize, null);
            GLES20.glTexParameterf(R2.color.c_FF0017, 10240, 9729.0f);
            GLES20.glTexParameterf(R2.color.c_FF0017, R2.drawable.main_bottom_tab_personal_normal, 9729.0f);
            GLES20.glTexParameterf(R2.color.c_FF0017, R2.drawable.main_bottom_tab_personal_normal_dark, 33071.0f);
            GLES20.glTexParameterf(R2.color.c_FF0017, R2.drawable.main_bottom_tab_search_focus, 33071.0f);
            GLES20.glBindFramebuffer(36160, this.mFrameBuffers[i6]);
            GLES20.glFramebufferTexture2D(36160, 36064, R2.color.c_FF0017, this.mFrameBufferTextures[i6], 0);
            GLES20.glBindTexture(R2.color.c_FF0017, 0);
            GLES20.glBindFramebuffer(36160, 0);
            i6++;
            i5 = 1;
        }
    }

    public void updateMergedFilters() {
        if (this.mFilters == null) {
            return;
        }
        List<CommonFilter> list = this.mMergedFilters;
        if (list == null) {
            this.mMergedFilters = new ArrayList();
        } else {
            list.clear();
        }
        for (CommonFilter commonFilter : this.mFilters) {
            if (commonFilter instanceof CommonFilterGroup) {
                CommonFilterGroup commonFilterGroup = (CommonFilterGroup) commonFilter;
                commonFilterGroup.updateMergedFilters();
                List<CommonFilter> mergedFilters = commonFilterGroup.getMergedFilters();
                if (mergedFilters != null && !mergedFilters.isEmpty()) {
                    this.mMergedFilters.addAll(mergedFilters);
                }
            } else {
                this.mMergedFilters.add(commonFilter);
            }
        }
    }

    public CommonFilterGroup(List<CommonFilter> list) {
        this.mFilters = list;
        if (list == null) {
            this.mFilters = new ArrayList();
        } else {
            updateMergedFilters();
        }
        float[] fArr = CommonFilterRenderer.CUBE;
        FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mGLCubeBuffer = asFloatBuffer;
        asFloatBuffer.put(fArr).position(0);
        float[] fArr2 = TextureRotationUtil.TEXTURE_NO_ROTATION;
        FloatBuffer asFloatBuffer2 = ByteBuffer.allocateDirect(fArr2.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mGLTextureBuffer = asFloatBuffer2;
        asFloatBuffer2.put(fArr2).position(0);
        float[] rotation = TextureRotationUtil.getRotation(Rotation.NORMAL, false, true);
        FloatBuffer asFloatBuffer3 = ByteBuffer.allocateDirect(rotation.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mGLTextureFlipBuffer = asFloatBuffer3;
        asFloatBuffer3.put(rotation).position(0);
    }
}
