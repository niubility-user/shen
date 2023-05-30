package com.jingdong.common.unification.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import com.jingdong.common.R;
import com.jingdong.common.unification.filter.filter.CommonBeautyFilter;
import com.jingdong.common.unification.filter.filter.CommonBeautyWhiteFilter;
import com.jingdong.common.unification.filter.filter.CommonBrightFilter;
import com.jingdong.common.unification.filter.filter.CommonBrightnessFilter;
import com.jingdong.common.unification.filter.filter.CommonCartridgeFilter;
import com.jingdong.common.unification.filter.filter.CommonColdFilter;
import com.jingdong.common.unification.filter.filter.CommonColorfulFilter;
import com.jingdong.common.unification.filter.filter.CommonContrastFilter;
import com.jingdong.common.unification.filter.filter.CommonExposureFilter;
import com.jingdong.common.unification.filter.filter.CommonFilter;
import com.jingdong.common.unification.filter.filter.CommonLevelsFilter;
import com.jingdong.common.unification.filter.filter.CommonOneKeyBeautyFilter;
import com.jingdong.common.unification.filter.filter.CommonRGBFilter;
import com.jingdong.common.unification.filter.filter.CommonSaturationFilter;
import com.jingdong.common.unification.filter.filter.CommonSharpenFilter;
import com.jingdong.common.unification.filter.filter.CommonVignetteFilter;
import com.jingdong.common.unification.filter.filter.CommonWarmFilter;
import com.jingdong.common.unification.filter.filter.FilterImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes6.dex */
public class FilterTools {

    /* renamed from: com.jingdong.common.unification.filter.FilterTools$2 */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$common$unification$filter$FilterTools$FilterType;

        static {
            int[] iArr = new int[FilterType.values().length];
            $SwitchMap$com$jingdong$common$unification$filter$FilterTools$FilterType = iArr;
            try {
                iArr[FilterType.NO_FILTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jingdong$common$unification$filter$FilterTools$FilterType[FilterType.BRIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jingdong$common$unification$filter$FilterTools$FilterType[FilterType.ONE_KEY_BEAUTY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$jingdong$common$unification$filter$FilterTools$FilterType[FilterType.COLORFUL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$jingdong$common$unification$filter$FilterTools$FilterType[FilterType.COLD.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$jingdong$common$unification$filter$FilterTools$FilterType[FilterType.WARM.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$jingdong$common$unification$filter$FilterTools$FilterType[FilterType.CARTRIDGE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$jingdong$common$unification$filter$FilterTools$FilterType[FilterType.BEAUTY_WHITEN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* loaded from: classes6.dex */
    public static class FilterAdjuster {
        private final Adjuster<? extends CommonFilter> adjuster;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public abstract class Adjuster<T extends CommonFilter> {
            private T filter;

            private Adjuster() {
                FilterAdjuster.this = r1;
            }

            public abstract void adjust(int i2);

            /* JADX WARN: Multi-variable type inference failed */
            public Adjuster<T> filter(CommonFilter commonFilter) {
                this.filter = commonFilter;
                return this;
            }

            public T getFilter() {
                return this.filter;
            }

            protected float range(int i2, float f2, float f3) {
                return (((f3 - f2) * i2) / 100.0f) + f2;
            }

            protected int range(int i2, int i3, int i4) {
                return (((i4 - i3) * i2) / 100) + i3;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public class CommonBeautyWhiteFilterAdjuster extends Adjuster<CommonBeautyWhiteFilter> {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            private CommonBeautyWhiteFilterAdjuster() {
                super();
                FilterAdjuster.this = r2;
            }

            @Override // com.jingdong.common.unification.filter.FilterTools.FilterAdjuster.Adjuster
            public void adjust(int i2) {
                CommonFilter beautyFilter = getFilter().getBeautyFilter();
                if (beautyFilter instanceof CommonBeautyFilter) {
                    CommonBeautyFilter commonBeautyFilter = (CommonBeautyFilter) beautyFilter;
                    commonBeautyFilter.setBeautyLevel(range(i2, -3.0f, 0.42f), range(i2, -0.67f, 0.47f));
                    commonBeautyFilter.setBrightLevel(range(i2, 0.5f, 0.34f));
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public class CommonBrightFilterAdjuster extends Adjuster<CommonBrightFilter> {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            private CommonBrightFilterAdjuster() {
                super();
                FilterAdjuster.this = r2;
            }

            @Override // com.jingdong.common.unification.filter.FilterTools.FilterAdjuster.Adjuster
            public void adjust(int i2) {
                CommonFilter sharpenFilter = getFilter().getSharpenFilter();
                if (sharpenFilter instanceof CommonSharpenFilter) {
                    ((CommonSharpenFilter) sharpenFilter).setSharpness(range(i2, 0.0f, 0.2f));
                }
                CommonFilter exposureFilter = getFilter().getExposureFilter();
                if (exposureFilter instanceof CommonExposureFilter) {
                    ((CommonExposureFilter) exposureFilter).setExposure(range(i2, 0.0f, 0.375f));
                }
                CommonFilter brightnessFilter = getFilter().getBrightnessFilter();
                if (brightnessFilter instanceof CommonBrightnessFilter) {
                    ((CommonBrightnessFilter) brightnessFilter).setBrightness(range(i2, 0.0f, 0.066f));
                }
                CommonFilter contrastFilter = getFilter().getContrastFilter();
                if (contrastFilter instanceof CommonContrastFilter) {
                    ((CommonContrastFilter) contrastFilter).setContrast(range(i2, 1.0f, 1.15f));
                }
                CommonFilter saturationFilter = getFilter().getSaturationFilter();
                if (saturationFilter instanceof CommonSaturationFilter) {
                    ((CommonSaturationFilter) saturationFilter).setSaturation(range(i2, 1.0f, 1.15f));
                }
                CommonFilter levelsFilter = getFilter().getLevelsFilter();
                if (levelsFilter instanceof CommonLevelsFilter) {
                    ((CommonLevelsFilter) levelsFilter).setMin(0.0f, range(i2, 1.0f, 0.95f), 1.0f);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public class CommonCartridgeFilterAdjuster extends Adjuster<CommonCartridgeFilter> {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            private CommonCartridgeFilterAdjuster() {
                super();
                FilterAdjuster.this = r2;
            }

            @Override // com.jingdong.common.unification.filter.FilterTools.FilterAdjuster.Adjuster
            public void adjust(int i2) {
                CommonFilter sharpenFilter = getFilter().getSharpenFilter();
                if (sharpenFilter instanceof CommonSharpenFilter) {
                    ((CommonSharpenFilter) sharpenFilter).setSharpness(range(i2, 0.0f, 0.2f));
                }
                CommonFilter saturationFilter = getFilter().getSaturationFilter();
                if (saturationFilter instanceof CommonSaturationFilter) {
                    ((CommonSaturationFilter) saturationFilter).setSaturation(range(i2, 1.0f, 0.75f));
                }
                CommonFilter brightnessFilter = getFilter().getBrightnessFilter();
                if (brightnessFilter instanceof CommonBrightnessFilter) {
                    ((CommonBrightnessFilter) brightnessFilter).setBrightness(range(i2, 0.0f, 0.1f));
                }
                CommonFilter rGBFilter = getFilter().getRGBFilter();
                if (rGBFilter instanceof CommonRGBFilter) {
                    CommonRGBFilter commonRGBFilter = (CommonRGBFilter) rGBFilter;
                    commonRGBFilter.setRed(range(i2, 1.0f, 1.02f));
                    commonRGBFilter.setGreen(range(i2, 1.0f, 1.04f));
                    commonRGBFilter.setBlue(range(i2, 1.0f, 1.02f));
                }
                CommonFilter exposureFilter = getFilter().getExposureFilter();
                if (exposureFilter instanceof CommonExposureFilter) {
                    ((CommonExposureFilter) exposureFilter).setExposure(range(i2, 0.0f, -0.038f));
                }
                CommonFilter vignetteFilter = getFilter().getVignetteFilter();
                if (vignetteFilter == null || !(vignetteFilter instanceof CommonVignetteFilter)) {
                    return;
                }
                CommonVignetteFilter commonVignetteFilter = (CommonVignetteFilter) vignetteFilter;
                commonVignetteFilter.setVignetteStart(range(i2, 1.0f, 0.45f));
                commonVignetteFilter.setVignetteEnd(range(i2, 1.0f, 0.85f));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public class CommonColdFilterAdjuster extends Adjuster<CommonColdFilter> {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            private CommonColdFilterAdjuster() {
                super();
                FilterAdjuster.this = r2;
            }

            @Override // com.jingdong.common.unification.filter.FilterTools.FilterAdjuster.Adjuster
            public void adjust(int i2) {
                CommonFilter sharpenFilter = getFilter().getSharpenFilter();
                if (sharpenFilter instanceof CommonSharpenFilter) {
                    ((CommonSharpenFilter) sharpenFilter).setSharpness(range(i2, 0.0f, 0.2f));
                }
                CommonFilter rGBFilter = getFilter().getRGBFilter();
                if (rGBFilter instanceof CommonRGBFilter) {
                    CommonRGBFilter commonRGBFilter = (CommonRGBFilter) rGBFilter;
                    commonRGBFilter.setBlue(range(i2, 1.0f, 1.2f));
                    commonRGBFilter.setGreen(range(i2, 1.0f, 1.04f));
                }
                CommonFilter saturationFilter = getFilter().getSaturationFilter();
                if (saturationFilter instanceof CommonSaturationFilter) {
                    ((CommonSaturationFilter) saturationFilter).setSaturation(range(i2, 1.0f, 1.05f));
                }
                CommonFilter brightnessFilter = getFilter().getBrightnessFilter();
                if (brightnessFilter instanceof CommonBrightnessFilter) {
                    ((CommonBrightnessFilter) brightnessFilter).setBrightness(range(i2, 0.0f, 0.02f));
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public class CommonColorfulFilterAdjuster extends Adjuster<CommonColorfulFilter> {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            private CommonColorfulFilterAdjuster() {
                super();
                FilterAdjuster.this = r2;
            }

            @Override // com.jingdong.common.unification.filter.FilterTools.FilterAdjuster.Adjuster
            public void adjust(int i2) {
                CommonFilter sharpenFilter = getFilter().getSharpenFilter();
                if (sharpenFilter instanceof CommonSharpenFilter) {
                    ((CommonSharpenFilter) sharpenFilter).setSharpness(range(i2, 0.0f, 0.2f));
                }
                CommonFilter saturationFilter = getFilter().getSaturationFilter();
                if (saturationFilter instanceof CommonSaturationFilter) {
                    ((CommonSaturationFilter) saturationFilter).setSaturation(range(i2, 1.0f, 1.6f));
                }
                CommonFilter contrastFilter = getFilter().getContrastFilter();
                if (contrastFilter instanceof CommonContrastFilter) {
                    ((CommonContrastFilter) contrastFilter).setContrast(range(i2, 1.0f, 1.2f));
                }
                CommonFilter brightnessFilter = getFilter().getBrightnessFilter();
                if (brightnessFilter instanceof CommonBrightnessFilter) {
                    ((CommonBrightnessFilter) brightnessFilter).setBrightness(range(i2, 0.0f, 0.005f));
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public class CommonOneKeyBeautyFilterAdjuster extends Adjuster<CommonOneKeyBeautyFilter> {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            private CommonOneKeyBeautyFilterAdjuster() {
                super();
                FilterAdjuster.this = r2;
            }

            @Override // com.jingdong.common.unification.filter.FilterTools.FilterAdjuster.Adjuster
            public void adjust(int i2) {
                CommonFilter sharpenFilter = getFilter().getSharpenFilter();
                if (sharpenFilter instanceof CommonSharpenFilter) {
                    ((CommonSharpenFilter) sharpenFilter).setSharpness(range(i2, 0.0f, 0.2f));
                }
                CommonFilter exposureFilter = getFilter().getExposureFilter();
                if (exposureFilter instanceof CommonExposureFilter) {
                    ((CommonExposureFilter) exposureFilter).setExposure(range(i2, 0.0f, 0.375f));
                }
                CommonFilter brightnessFilter = getFilter().getBrightnessFilter();
                if (brightnessFilter instanceof CommonBrightnessFilter) {
                    ((CommonBrightnessFilter) brightnessFilter).setBrightness(range(i2, 0.0f, 0.066f));
                }
                CommonFilter contrastFilter = getFilter().getContrastFilter();
                if (contrastFilter instanceof CommonContrastFilter) {
                    ((CommonContrastFilter) contrastFilter).setContrast(range(i2, 1.0f, 1.15f));
                }
                CommonFilter saturationFilter = getFilter().getSaturationFilter();
                if (saturationFilter instanceof CommonSaturationFilter) {
                    ((CommonSaturationFilter) saturationFilter).setSaturation(range(i2, 1.0f, 1.15f));
                }
                CommonFilter levelsFilter = getFilter().getLevelsFilter();
                if (levelsFilter instanceof CommonLevelsFilter) {
                    ((CommonLevelsFilter) levelsFilter).setMin(0.0f, range(i2, 1.0f, 0.95f), 1.0f);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public class CommonWarmFilterAdjuster extends Adjuster<CommonWarmFilter> {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            private CommonWarmFilterAdjuster() {
                super();
                FilterAdjuster.this = r2;
            }

            @Override // com.jingdong.common.unification.filter.FilterTools.FilterAdjuster.Adjuster
            public void adjust(int i2) {
                CommonFilter sharpenFilter = getFilter().getSharpenFilter();
                if (sharpenFilter instanceof CommonSharpenFilter) {
                    ((CommonSharpenFilter) sharpenFilter).setSharpness(range(i2, 0.0f, 0.2f));
                }
                CommonFilter rGBFilter = getFilter().getRGBFilter();
                if (rGBFilter instanceof CommonRGBFilter) {
                    CommonRGBFilter commonRGBFilter = (CommonRGBFilter) rGBFilter;
                    commonRGBFilter.setRed(range(i2, 1.0f, 1.2f));
                    commonRGBFilter.setGreen(range(i2, 1.0f, 1.04f));
                }
                CommonFilter saturationFilter = getFilter().getSaturationFilter();
                if (saturationFilter instanceof CommonSaturationFilter) {
                    ((CommonSaturationFilter) saturationFilter).setSaturation(range(i2, 1.0f, 1.05f));
                }
                CommonFilter brightnessFilter = getFilter().getBrightnessFilter();
                if (brightnessFilter instanceof CommonBrightnessFilter) {
                    ((CommonBrightnessFilter) brightnessFilter).setBrightness(range(i2, 0.0f, 0.02f));
                }
            }
        }

        public FilterAdjuster(CommonFilter commonFilter) {
            if (commonFilter instanceof CommonBrightFilter) {
                this.adjuster = new CommonBrightFilterAdjuster().filter(commonFilter);
            } else if (commonFilter instanceof CommonColorfulFilter) {
                this.adjuster = new CommonColorfulFilterAdjuster().filter(commonFilter);
            } else if (commonFilter instanceof CommonColdFilter) {
                this.adjuster = new CommonColdFilterAdjuster().filter(commonFilter);
            } else if (commonFilter instanceof CommonWarmFilter) {
                this.adjuster = new CommonWarmFilterAdjuster().filter(commonFilter);
            } else if (commonFilter instanceof CommonCartridgeFilter) {
                this.adjuster = new CommonCartridgeFilterAdjuster().filter(commonFilter);
            } else if (commonFilter instanceof CommonOneKeyBeautyFilter) {
                this.adjuster = new CommonOneKeyBeautyFilterAdjuster().filter(commonFilter);
            } else if (commonFilter instanceof CommonBeautyWhiteFilter) {
                this.adjuster = new CommonBeautyWhiteFilterAdjuster().filter(commonFilter);
            } else {
                this.adjuster = null;
            }
        }

        public void adjust(int i2) {
            Adjuster<? extends CommonFilter> adjuster = this.adjuster;
            if (adjuster != null) {
                adjuster.adjust(i2);
            }
        }

        public boolean canAdjust() {
            return this.adjuster != null;
        }
    }

    /* loaded from: classes6.dex */
    public enum FilterType {
        NO_FILTER,
        BRIGHT,
        COLORFUL,
        COLD,
        WARM,
        CARTRIDGE,
        ONE_KEY_BEAUTY,
        BEAUTY_WHITEN
    }

    public static CommonFilter createFilterForType(FilterType filterType) {
        switch (AnonymousClass2.$SwitchMap$com$jingdong$common$unification$filter$FilterTools$FilterType[filterType.ordinal()]) {
            case 2:
                return new CommonBrightFilter();
            case 3:
                return new CommonOneKeyBeautyFilter();
            case 4:
                return new CommonColorfulFilter();
            case 5:
                return new CommonColdFilter();
            case 6:
                return new CommonWarmFilter();
            case 7:
                return new CommonCartridgeFilter();
            case 8:
                return new CommonBeautyWhiteFilter();
            default:
                return new CommonFilter();
        }
    }

    private static FilterInfo createFilterInfoForType(Context context, FilterType filterType, Bitmap bitmap) {
        switch (AnonymousClass2.$SwitchMap$com$jingdong$common$unification$filter$FilterTools$FilterType[filterType.ordinal()]) {
            case 1:
                return new FilterInfo(FilterType.NO_FILTER, context.getResources().getString(R.string.filter_no_filter), bitmap);
            case 2:
                return new FilterInfo(FilterType.BRIGHT, context.getResources().getString(R.string.filter_bright), null);
            case 3:
                return new FilterInfo(FilterType.ONE_KEY_BEAUTY, context.getResources().getString(R.string.filter_one_key_beauty), null);
            case 4:
                return new FilterInfo(FilterType.COLORFUL, context.getResources().getString(R.string.filter_colorful), null);
            case 5:
                return new FilterInfo(FilterType.COLD, context.getResources().getString(R.string.filter_cold), null);
            case 6:
                return new FilterInfo(FilterType.WARM, context.getResources().getString(R.string.filter_warm), null);
            case 7:
                return new FilterInfo(FilterType.CARTRIDGE, context.getResources().getString(R.string.filter_cartridge), null);
            case 8:
                return new FilterInfo(FilterType.BEAUTY_WHITEN, context.getResources().getString(R.string.filter_skinwhiten), null);
            default:
                return null;
        }
    }

    public static void getFilterBitmap(final Context context, final List<FilterInfo> list, final Bitmap bitmap, final Handler handler) {
        new Thread(new Runnable() { // from class: com.jingdong.common.unification.filter.FilterTools.1
            @Override // java.lang.Runnable
            public void run() {
                FilterImage filterImage = new FilterImage(context);
                filterImage.setImage(bitmap);
                for (int i2 = 0; i2 < list.size(); i2++) {
                    FilterInfo filterInfo = (FilterInfo) list.get(i2);
                    filterImage.setFilter(FilterTools.createFilterForType(filterInfo.type));
                    filterInfo.bitmap = filterImage.getBitmapWithFilterApplied();
                    Message obtainMessage = handler.obtainMessage(200);
                    obtainMessage.obj = filterInfo;
                    obtainMessage.arg1 = i2;
                    handler.sendMessage(obtainMessage);
                }
            }
        }).start();
    }

    public static String getFilterName(FilterType filterType) {
        switch (AnonymousClass2.$SwitchMap$com$jingdong$common$unification$filter$FilterTools$FilterType[filterType.ordinal()]) {
            case 1:
                return "YuanTu";
            case 2:
                return "MingLiang";
            case 3:
                return "YiJianMeiHua";
            case 4:
                return "XianYan";
            case 5:
                return "LengSe";
            case 6:
                return "NuanSe";
            case 7:
                return "JiaoPian";
            case 8:
                return "MeiYan";
            default:
                return "";
        }
    }

    public static List<FilterInfo> initFilter(Context context, Bitmap bitmap) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(FilterType.BRIGHT);
        arrayList.add(FilterType.COLORFUL);
        arrayList.add(FilterType.COLD);
        arrayList.add(FilterType.WARM);
        arrayList.add(FilterType.CARTRIDGE);
        arrayList.add(FilterType.BEAUTY_WHITEN);
        arrayList.add(FilterType.ONE_KEY_BEAUTY);
        return initFilter(context, bitmap, arrayList);
    }

    public static float range(int i2, float f2, float f3) {
        return (((f3 - f2) * i2) / 100.0f) + f2;
    }

    public static Bitmap getFilterBitmap(Context context, FilterType filterType, Uri uri) {
        FilterImage filterImage = new FilterImage(context);
        filterImage.setImage(uri);
        filterImage.setFilter(createFilterForType(filterType));
        return filterImage.getBitmapWithFilterApplied();
    }

    public static CommonFilter createFilterForType(FilterType filterType, int i2) {
        switch (AnonymousClass2.$SwitchMap$com$jingdong$common$unification$filter$FilterTools$FilterType[filterType.ordinal()]) {
            case 1:
                return new CommonFilter();
            case 2:
                return new CommonBrightFilter(i2);
            case 3:
                return new CommonOneKeyBeautyFilter(i2);
            case 4:
                return new CommonColorfulFilter(i2);
            case 5:
                return new CommonColdFilter(i2);
            case 6:
                return new CommonWarmFilter(i2);
            case 7:
                return new CommonCartridgeFilter(i2);
            case 8:
                return new CommonBeautyWhiteFilter(i2);
            default:
                return null;
        }
    }

    public static List<FilterInfo> initFilter(Context context, Bitmap bitmap, List<FilterType> list) {
        LinkedList linkedList = new LinkedList();
        if (list != null) {
            list.add(0, FilterType.NO_FILTER);
            for (int i2 = 0; i2 < list.size(); i2++) {
                linkedList.add(createFilterInfoForType(context, list.get(i2), bitmap));
            }
            ((FilterInfo) linkedList.get(0)).isSelect = true;
        }
        return linkedList;
    }
}
