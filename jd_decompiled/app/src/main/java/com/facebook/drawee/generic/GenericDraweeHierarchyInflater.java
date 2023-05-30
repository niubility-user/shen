package com.facebook.drawee.generic;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import com.facebook.drawee.R;
import com.facebook.drawee.drawable.AutoRotateDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import com.facebook.infer.annotation.ReturnsOwnership;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class GenericDraweeHierarchyInflater {
    @Nullable
    private static Drawable getDrawable(Context context, TypedArray typedArray, int i2) {
        int resourceId = typedArray.getResourceId(i2, 0);
        if (resourceId == 0) {
            return null;
        }
        return context.getResources().getDrawable(resourceId);
    }

    @ReturnsOwnership
    private static RoundingParams getRoundingParams(GenericDraweeHierarchyBuilder genericDraweeHierarchyBuilder) {
        if (genericDraweeHierarchyBuilder.getRoundingParams() == null) {
            genericDraweeHierarchyBuilder.setRoundingParams(new RoundingParams());
        }
        return genericDraweeHierarchyBuilder.getRoundingParams();
    }

    @Nullable
    private static ScalingUtils.ScaleType getScaleTypeFromXml(TypedArray typedArray, int i2) {
        switch (typedArray.getInt(i2, -2)) {
            case -1:
                return null;
            case 0:
                return ScalingUtils.ScaleType.FIT_XY;
            case 1:
                return ScalingUtils.ScaleType.FIT_START;
            case 2:
                return ScalingUtils.ScaleType.FIT_CENTER;
            case 3:
                return ScalingUtils.ScaleType.FIT_END;
            case 4:
                return ScalingUtils.ScaleType.CENTER;
            case 5:
                return ScalingUtils.ScaleType.CENTER_INSIDE;
            case 6:
                return ScalingUtils.ScaleType.CENTER_CROP;
            case 7:
                return ScalingUtils.ScaleType.FOCUS_CROP;
            case 8:
                return ScalingUtils.ScaleType.FIT_BOTTOM_START;
            default:
                throw new RuntimeException("XML attribute not specified!");
        }
    }

    public static GenericDraweeHierarchyBuilder inflateBuilder(Context context, @Nullable AttributeSet attributeSet) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("GenericDraweeHierarchyBuilder#inflateBuilder");
        }
        GenericDraweeHierarchyBuilder updateBuilder = updateBuilder(new GenericDraweeHierarchyBuilder(context.getResources()), context, attributeSet);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
        return updateBuilder;
    }

    public static GenericDraweeHierarchy inflateHierarchy(Context context, @Nullable AttributeSet attributeSet) {
        return inflateBuilder(context, attributeSet).build();
    }

    /* JADX WARN: Finally extract failed */
    public static GenericDraweeHierarchyBuilder updateBuilder(GenericDraweeHierarchyBuilder genericDraweeHierarchyBuilder, Context context, @Nullable AttributeSet attributeSet) {
        boolean z;
        int i2;
        int i3;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        Context context2 = context;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R.styleable.GenericDraweeHierarchy);
            try {
                int indexCount = obtainStyledAttributes.getIndexCount();
                boolean z7 = true;
                int i4 = 0;
                int i5 = 0;
                boolean z8 = true;
                boolean z9 = true;
                boolean z10 = true;
                boolean z11 = true;
                boolean z12 = true;
                boolean z13 = true;
                boolean z14 = true;
                int i6 = 0;
                while (i5 < indexCount) {
                    int index = obtainStyledAttributes.getIndex(i5);
                    if (index == R.styleable.GenericDraweeHierarchy_actualImageScaleType) {
                        genericDraweeHierarchyBuilder.setActualImageScaleType(getScaleTypeFromXml(obtainStyledAttributes, index));
                    } else if (index == R.styleable.GenericDraweeHierarchy_placeholderImage) {
                        genericDraweeHierarchyBuilder.setPlaceholderImage(getDrawable(context2, obtainStyledAttributes, index));
                    } else if (index == R.styleable.GenericDraweeHierarchy_pressedStateOverlayImage) {
                        genericDraweeHierarchyBuilder.setPressedStateOverlay(getDrawable(context2, obtainStyledAttributes, index));
                    } else if (index == R.styleable.GenericDraweeHierarchy_progressBarImage) {
                        genericDraweeHierarchyBuilder.setProgressBarImage(getDrawable(context2, obtainStyledAttributes, index));
                    } else if (index == R.styleable.GenericDraweeHierarchy_fadeDuration) {
                        genericDraweeHierarchyBuilder.setFadeDuration(obtainStyledAttributes.getInt(index, 0));
                    } else if (index == R.styleable.GenericDraweeHierarchy_viewAspectRatio) {
                        genericDraweeHierarchyBuilder.setDesiredAspectRatio(obtainStyledAttributes.getFloat(index, 0.0f));
                    } else if (index == R.styleable.GenericDraweeHierarchy_placeholderImageScaleType) {
                        genericDraweeHierarchyBuilder.setPlaceholderImageScaleType(getScaleTypeFromXml(obtainStyledAttributes, index));
                    } else if (index == R.styleable.GenericDraweeHierarchy_retryImage) {
                        genericDraweeHierarchyBuilder.setRetryImage(getDrawable(context2, obtainStyledAttributes, index));
                    } else if (index == R.styleable.GenericDraweeHierarchy_retryImageScaleType) {
                        genericDraweeHierarchyBuilder.setRetryImageScaleType(getScaleTypeFromXml(obtainStyledAttributes, index));
                    } else if (index == R.styleable.GenericDraweeHierarchy_failureImage) {
                        genericDraweeHierarchyBuilder.setFailureImage(getDrawable(context2, obtainStyledAttributes, index));
                    } else if (index == R.styleable.GenericDraweeHierarchy_failureImageScaleType) {
                        genericDraweeHierarchyBuilder.setFailureImageScaleType(getScaleTypeFromXml(obtainStyledAttributes, index));
                    } else if (index == R.styleable.GenericDraweeHierarchy_progressBarImageScaleType) {
                        genericDraweeHierarchyBuilder.setProgressBarImageScaleType(getScaleTypeFromXml(obtainStyledAttributes, index));
                    } else if (index == R.styleable.GenericDraweeHierarchy_progressBarAutoRotateInterval) {
                        i4 = obtainStyledAttributes.getInteger(index, i4);
                    } else if (index == R.styleable.GenericDraweeHierarchy_backgroundImage) {
                        genericDraweeHierarchyBuilder.setBackground(getDrawable(context2, obtainStyledAttributes, index));
                    } else if (index == R.styleable.GenericDraweeHierarchy_overlayImage) {
                        genericDraweeHierarchyBuilder.setOverlay(getDrawable(context2, obtainStyledAttributes, index));
                    } else if (index == R.styleable.GenericDraweeHierarchy_roundAsCircle) {
                        getRoundingParams(genericDraweeHierarchyBuilder).setRoundAsCircle(obtainStyledAttributes.getBoolean(index, false));
                    } else {
                        int i7 = i6;
                        if (index == R.styleable.GenericDraweeHierarchy_roundedCornerRadius) {
                            i6 = obtainStyledAttributes.getDimensionPixelSize(index, i7);
                        } else {
                            if (index == R.styleable.GenericDraweeHierarchy_roundTopLeft) {
                                z8 = obtainStyledAttributes.getBoolean(index, z8);
                            } else if (index == R.styleable.GenericDraweeHierarchy_roundTopRight) {
                                z10 = obtainStyledAttributes.getBoolean(index, z10);
                            } else if (index == R.styleable.GenericDraweeHierarchy_roundBottomLeft) {
                                z14 = obtainStyledAttributes.getBoolean(index, z14);
                            } else if (index == R.styleable.GenericDraweeHierarchy_roundBottomRight) {
                                z12 = obtainStyledAttributes.getBoolean(index, z12);
                            } else if (index == R.styleable.GenericDraweeHierarchy_roundTopStart) {
                                z9 = obtainStyledAttributes.getBoolean(index, z9);
                            } else if (index == R.styleable.GenericDraweeHierarchy_roundTopEnd) {
                                z11 = obtainStyledAttributes.getBoolean(index, z11);
                            } else if (index == R.styleable.GenericDraweeHierarchy_roundBottomStart) {
                                z7 = obtainStyledAttributes.getBoolean(index, z7);
                            } else if (index == R.styleable.GenericDraweeHierarchy_roundBottomEnd) {
                                z13 = obtainStyledAttributes.getBoolean(index, z13);
                            } else if (index == R.styleable.GenericDraweeHierarchy_roundWithOverlayColor) {
                                i6 = i7;
                                getRoundingParams(genericDraweeHierarchyBuilder).setOverlayColor(obtainStyledAttributes.getColor(index, 0));
                            } else {
                                i6 = i7;
                                if (index == R.styleable.GenericDraweeHierarchy_roundingBorderWidth) {
                                    getRoundingParams(genericDraweeHierarchyBuilder).setBorderWidth(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                                } else if (index == R.styleable.GenericDraweeHierarchy_roundingBorderColor) {
                                    getRoundingParams(genericDraweeHierarchyBuilder).setBorderColor(obtainStyledAttributes.getColor(index, 0));
                                } else if (index == R.styleable.GenericDraweeHierarchy_roundingBorderPadding) {
                                    getRoundingParams(genericDraweeHierarchyBuilder).setPadding(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                                    i5++;
                                    context2 = context;
                                }
                            }
                            i6 = i7;
                        }
                    }
                    i5++;
                    context2 = context;
                }
                obtainStyledAttributes.recycle();
                if (Build.VERSION.SDK_INT >= 17) {
                    z = true;
                    if (context.getResources().getConfiguration().getLayoutDirection() == 1) {
                        z5 = z8 && z11;
                        z2 = z10 && z9;
                        z6 = z12 && z7;
                        if (!z14 || !z13) {
                            z4 = z6;
                            z3 = z5;
                            i2 = i4;
                            i3 = i6;
                            z = false;
                        }
                        z4 = z6;
                        i2 = i4;
                        z3 = z5;
                        i3 = i6;
                    }
                } else {
                    z = true;
                }
                z5 = z8 && z9;
                z2 = z10 && z11;
                z4 = z12 && z13;
                if (z14 && z7) {
                    z6 = z4;
                    z4 = z6;
                    i2 = i4;
                    z3 = z5;
                    i3 = i6;
                }
                z3 = z5;
                i2 = i4;
                i3 = i6;
                z = false;
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                if (Build.VERSION.SDK_INT >= 17) {
                    context.getResources().getConfiguration().getLayoutDirection();
                }
                throw th;
            }
        } else {
            z = true;
            i2 = 0;
            i3 = 0;
            z2 = true;
            z3 = true;
            z4 = true;
        }
        if (genericDraweeHierarchyBuilder.getProgressBarImage() != null && i2 > 0) {
            genericDraweeHierarchyBuilder.setProgressBarImage(new AutoRotateDrawable(genericDraweeHierarchyBuilder.getProgressBarImage(), i2));
        }
        if (i3 > 0) {
            getRoundingParams(genericDraweeHierarchyBuilder).setCornersRadii(z3 ? i3 : 0.0f, z2 ? i3 : 0.0f, z4 ? i3 : 0.0f, z ? i3 : 0.0f);
        }
        return genericDraweeHierarchyBuilder;
    }
}
