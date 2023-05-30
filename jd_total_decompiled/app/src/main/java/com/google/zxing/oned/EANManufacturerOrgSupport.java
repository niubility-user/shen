package com.google.zxing.oned;

import com.google.zxing.client.result.ExpandedProductParsedResult;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes12.dex */
final class EANManufacturerOrgSupport {
    private final List<int[]> ranges = new ArrayList();
    private final List<String> countryIdentifiers = new ArrayList();

    private void add(int[] iArr, String str) {
        this.ranges.add(iArr);
        this.countryIdentifiers.add(str);
    }

    private synchronized void initIfNeeded() {
        if (this.ranges.isEmpty()) {
            add(new int[]{0, 19}, "US/CA");
            add(new int[]{30, 39}, "US");
            add(new int[]{60, R2.anim.live_pop_rotate_amin}, "US/CA");
            add(new int[]{300, R2.attr.angle}, "FR");
            add(new int[]{R2.attr.animSize}, "BG");
            add(new int[]{R2.attr.animate_relativeTo}, "SI");
            add(new int[]{R2.attr.appBarLayoutStyle}, "HR");
            add(new int[]{R2.attr.app_actionbar_color}, "BA");
            add(new int[]{400, R2.attr.barLength}, "DE");
            add(new int[]{R2.attr.behavior_hideable, R2.attr.bgSize}, "JP");
            add(new int[]{R2.attr.blendSrc, R2.attr.border_color}, "RU");
            add(new int[]{R2.attr.border_width}, "TW");
            add(new int[]{R2.attr.bottomLeftBorderRadius}, "EE");
            add(new int[]{R2.attr.bottomLeftRadius}, "LV");
            add(new int[]{R2.attr.bottomMarginTop}, "AZ");
            add(new int[]{R2.attr.bottomNavigationStyle}, "LT");
            add(new int[]{R2.attr.bottomRightBorderRadius}, "UZ");
            add(new int[]{R2.attr.bottomRightRadius}, "LK");
            add(new int[]{480}, "PH");
            add(new int[]{R2.attr.bottomSheetStyle}, "BY");
            add(new int[]{R2.attr.boxBackgroundColor}, "UA");
            add(new int[]{484}, "MD");
            add(new int[]{R2.attr.boxCornerRadiusBottomEnd}, "AM");
            add(new int[]{R2.attr.boxCornerRadiusBottomStart}, "GE");
            add(new int[]{R2.attr.boxCornerRadiusTopEnd}, "KZ");
            add(new int[]{489}, "HK");
            add(new int[]{R2.attr.boxStrokeErrorColor, R2.attr.businessBg}, "JP");
            add(new int[]{500, 509}, CashierPayChannelCode.JD_PAY_GB);
            add(new int[]{R2.attr.buttonTextColor}, "GR");
            add(new int[]{R2.attr.calendarViewShown}, ExpandedProductParsedResult.POUND);
            add(new int[]{R2.attr.cameraBearing}, "CY");
            add(new int[]{R2.attr.cameraTargetLng}, "MK");
            add(new int[]{R2.attr.capsLockShiftIcon}, "MT");
            add(new int[]{R2.attr.cardForegroundColor}, "IE");
            add(new int[]{R2.attr.cardMaxElevation, R2.attr.carousel_nextState}, "BE/LU");
            add(new int[]{R2.attr.chatfrom_voice_playing_f2}, "PT");
            add(new int[]{R2.attr.checkedIcon}, "IS");
            add(new int[]{R2.attr.checkedIconEnabled, R2.attr.chipEndPadding}, "DK");
            add(new int[]{R2.attr.chipSpacingVertical}, "PL");
            add(new int[]{R2.attr.chipStrokeWidth}, "RO");
            add(new int[]{R2.attr.circleRadius}, "HU");
            add(new int[]{600, 601}, "ZA");
            add(new int[]{603}, "GH");
            add(new int[]{R2.attr.circularflow_viewCenter}, "BH");
            add(new int[]{R2.attr.civ_border_color}, "MU");
            add(new int[]{R2.attr.civ_border_width}, "MA");
            add(new int[]{R2.attr.civ_fill_color}, "DZ");
            add(new int[]{R2.attr.classic_loop}, "KE");
            add(new int[]{R2.attr.classic_space}, "CI");
            add(new int[]{R2.attr.clearsTag}, "TN");
            add(new int[]{R2.attr.clickAutoSelection}, "SY");
            add(new int[]{R2.attr.click_remove_id}, "EG");
            add(new int[]{R2.attr.clockHandColor}, "LY");
            add(new int[]{R2.attr.clockIcon}, "JO");
            add(new int[]{R2.attr.clockNumberTextColor}, "IR");
            add(new int[]{R2.attr.closeIcon}, "KW");
            add(new int[]{R2.attr.closeIconEnabled}, "SA");
            add(new int[]{R2.attr.closeIconEndPadding}, "AE");
            add(new int[]{640, R2.attr.colorError}, "FI");
            add(new int[]{690, R2.attr.contentPaddingEnd}, "CN");
            add(new int[]{700, R2.attr.cornerFamilyTopRight}, "NO");
            add(new int[]{R2.attr.customColorDrawableValue}, "IL");
            add(new int[]{R2.attr.customColorValue, R2.attr.custom_open}, "SE");
            add(new int[]{R2.attr.darkThemeMode}, "GT");
            add(new int[]{R2.attr.dark_mode}, "SV");
            add(new int[]{R2.attr.datePickerMode}, "HN");
            add(new int[]{R2.attr.datePickerStyle}, "NI");
            add(new int[]{R2.attr.dayInvalidStyle}, "CR");
            add(new int[]{R2.attr.dayOfWeekBackground}, "PA");
            add(new int[]{R2.attr.dayOfWeekTextAppearance}, "DO");
            add(new int[]{R2.attr.decimalNumber}, "MX");
            add(new int[]{R2.attr.default_bg_color, R2.attr.degree}, "CA");
            add(new int[]{R2.attr.deltaX}, "VE");
            add(new int[]{R2.attr.deltaY, R2.attr.dividerDrawableVertical}, "CH");
            add(new int[]{R2.attr.dividerHorizontal}, "CO");
            add(new int[]{R2.attr.dotColor}, "UY");
            add(new int[]{R2.attr.dragScale}, "PE");
            add(new int[]{R2.attr.drag_enabled}, "BO");
            add(new int[]{R2.attr.drag_scroll_start}, "AR");
            add(new int[]{R2.attr.drag_start_mode}, "CL");
            add(new int[]{R2.attr.drawableLeft}, "PY");
            add(new int[]{R2.attr.drawableLeftCompat}, "PE");
            add(new int[]{R2.attr.drawableRight}, "EC");
            add(new int[]{R2.attr.drawableSize, 790}, "BR");
            add(new int[]{800, R2.attr.exTabIndicatorGravity}, "IT");
            add(new int[]{R2.attr.exTabIndicatorHeight, R2.attr.exTabPaddingEnd}, "ES");
            add(new int[]{R2.attr.exTabPaddingStart}, "CU");
            add(new int[]{R2.attr.excludeSpacePadding}, "SK");
            add(new int[]{R2.attr.exitOffset}, "CZ");
            add(new int[]{R2.attr.expandActivityOverflowButtonDrawable}, "YU");
            add(new int[]{R2.attr.expandedTitleMarginBottom}, "MN");
            add(new int[]{R2.attr.expandedTitleMarginStart}, "KP");
            add(new int[]{R2.attr.expandedTitleMarginTop, R2.attr.expandedTitleTextAppearance}, "TR");
            add(new int[]{R2.attr.extendMotionSpec, R2.attr.fabSize}, "NL");
            add(new int[]{R2.attr.fadeDuration}, "KR");
            add(new int[]{R2.attr.fastScrollHorizontalTrackDrawable}, "TH");
            add(new int[]{R2.attr.fat_aar_excluded_headerBackground}, "SG");
            add(new int[]{R2.attr.fat_aar_excluded_wheelview_gravity}, "IN");
            add(new int[]{R2.attr.firstColor}, "VN");
            add(new int[]{R2.attr.flexDirection}, "PK");
            add(new int[]{R2.attr.flex_alignSelf}, "ID");
            add(new int[]{900, R2.attr.floatingActionButtonStyle}, "AT");
            add(new int[]{R2.attr.flow_lastVerticalBias, R2.attr.font}, "AU");
            add(new int[]{R2.attr.fontFamily, R2.attr.fontVariationSettings}, "AZ");
            add(new int[]{R2.attr.form_hint}, "MY");
            add(new int[]{R2.attr.fractionTextRatio}, "MO");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String lookupCountryIdentifier(String str) {
        int[] iArr;
        int i2;
        initIfNeeded();
        int parseInt = Integer.parseInt(str.substring(0, 3));
        int size = this.ranges.size();
        for (int i3 = 0; i3 < size && parseInt >= (i2 = (iArr = this.ranges.get(i3))[0]); i3++) {
            if (iArr.length != 1) {
                i2 = iArr[1];
            }
            if (parseInt <= i2) {
                return this.countryIdentifiers.get(i3);
            }
        }
        return null;
    }
}
