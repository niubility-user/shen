package com.google.zxing.maxicode.decoder;

import com.google.zxing.common.BitMatrix;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes12.dex */
final class BitMatrixParser {
    private static final int[][] BITNR = {new int[]{121, 120, 127, 126, 133, 132, R2.anim.live_pop_rotate_amin, R2.anim.lib_cashier_sdk_pop_out_animation_bottom, R2.anim.message_center_dialog_out, 144, 151, 150, R2.anim.out_to_right, R2.anim.out_to_bottom_slow, R2.anim.pop_center_out, 162, 169, R2.anim.pop_left_top_out, R2.anim.popup_anim_feedback, R2.anim.popdown_anim_feedback, R2.anim.push_right_out, 180, R2.anim.slide_in_from_bottom, R2.anim.slide_down, R2.anim.slide_out_from_left, 192, R2.anim.slide_out_top_dongdong, R2.anim.slide_out_to_top_medium_time, -2, -2}, new int[]{123, 122, 129, 128, 135, 134, R2.anim.manto_push_right_in, 140, R2.anim.miaosha_dropdown_out, R2.anim.miaosha_dropdown_in, 153, 152, R2.anim.pickerview_dialog_scale_out, R2.anim.pickerview_dialog_scale_in, R2.anim.pop_left_bottom_in, R2.anim.pop_in, R2.anim.pop_right_bottom_out, 170, 177, R2.anim.popup_center_enter, R2.anim.settlement_dialog_bottom_exit, R2.anim.settlement_dialog_bottom_enter, R2.anim.slide_in_from_left, R2.anim.slide_in_from_bottom_medium_time, R2.anim.slide_out_to_bottom_medium_time, R2.anim.slide_out_to_bottom, 201, 200, R2.attr.endIconDrawable, -3}, new int[]{125, 124, 131, 130, R2.anim.lib_cashier_sdk_pop_in_animation_bottom, R2.anim.lib_cashier_sdk_fragment_right_out, R2.anim.manto_translate_dialog_out, R2.anim.manto_translate_dialog_in, R2.anim.mtrl_bottom_sheet_slide_out, R2.anim.mtrl_bottom_sheet_slide_in, 155, 154, 161, 160, R2.anim.pop_left_top_in, R2.anim.pop_left_bottom_out, R2.anim.pop_right_top_out, R2.anim.pop_right_top_in, R2.anim.popwin_anim_alpha_out, 178, R2.anim.settlement_dialog_right_exit, R2.anim.settlement_dialog_right_enter, R2.anim.slide_in_from_top_medium_time, R2.anim.slide_in_from_top, R2.anim.slide_out_to_top, R2.anim.slide_out_to_right, 203, 202, R2.attr.endIconTint, R2.attr.endIconMode}, new int[]{R2.attr.SimpleEnableHeaderTranslationContent, R2.attr.SimpleEnableFooterTranslationContent, 277, 276, 271, 270, 265, 264, 259, 258, 253, 252, 247, 246, 241, 240, 235, 234, 229, 228, 223, 222, 217, 216, 211, 210, 205, 204, 819, -3}, new int[]{285, 284, 279, 278, 273, 272, 267, R2.attr.OverlayInnerPaddingTop, 261, 260, 255, 254, 249, 248, 243, 242, 237, 236, 231, 230, 225, 224, 219, 218, 213, 212, 207, 206, R2.attr.enforceMaterialTheme, R2.attr.endYear}, new int[]{287, 286, 281, 280, 275, 274, 269, 268, 263, 262, 257, 256, 251, 250, 245, 244, 239, 238, 233, 232, 227, 226, 221, 220, 215, 214, 209, 208, R2.attr.enforceTextAppearance, -3}, new int[]{R2.attr.SimpleEnablePureScrollMode, 288, 295, 294, 301, 300, 307, 306, 313, 312, 319, 318, 325, 324, 331, 330, 337, 336, R2.attr.actionModeTheme, R2.attr.actionModeStyle, R2.attr.actionViewClass, R2.attr.actionTextColorAlpha, R2.attr.adSize, R2.attr.actualImageUri, R2.attr.additionLeft, R2.attr.additionBottom, R2.attr.alertDialogTheme, R2.attr.alertDialogStyle, R2.attr.ensureMinTouchTargetSize, R2.attr.enlargePopType}, new int[]{291, 290, 297, 296, 303, 302, 309, 308, 315, 314, 321, 320, 327, 326, 333, 332, R2.attr.actionModeSelectAllDrawable, R2.attr.actionModePopupWindowStyle, R2.attr.actionOverflowButtonStyle, R2.attr.actionModeWebSearchDrawable, R2.attr.activityChooserViewStyle, R2.attr.actionbar_icon_dark_back, R2.attr.adUnitId, R2.attr.adSizes, R2.attr.additionTop, R2.attr.additionRight, R2.attr.alignItems, R2.attr.alignContent, R2.attr.environment, -3}, new int[]{293, 292, R2.attr.SimpleFooterTriggerRate, 298, 305, 304, 311, 310, 317, 316, 323, 322, R2.attr.actionMenuTextColor, R2.attr.actionMenuTextAppearance, 335, 334, R2.attr.actionModeSplitBackground, R2.attr.actionModeShareDrawable, R2.attr.actionProviderClass, R2.attr.actionOverflowMenuStyle, R2.attr.actualImageScaleType, R2.attr.actualImageResource, R2.attr.addition, R2.attr.addCallbackBufferEnable, R2.attr.alertDialogCenterButtons, R2.attr.alertDialogButtonGroupStyle, R2.attr.allowStacking, R2.attr.allowHorizontalScroll, R2.attr.errorEnabled, R2.attr.errorContentDescription}, new int[]{409, 408, 403, 402, R2.attr.arrowStartPosition, R2.attr.arrowShaftLength, R2.attr.arcMode, R2.attr.applyMotionScene, 79, 78, -2, -2, 13, 12, 37, 36, 2, -1, 44, 43, 109, 108, R2.attr.appBarLayoutStyle, 384, R2.attr.angle, R2.attr.amountSpace, R2.attr.alphabeticModifiers, R2.attr.alpha, R2.attr.errorIconDrawable, -3}, new int[]{411, 410, 405, 404, R2.attr.arrow_color, R2.attr.arrowWidth, R2.attr.arrowHeight, R2.attr.arrowHeadLength, 81, 80, 40, -2, 15, 14, 39, 38, 3, -1, -1, 45, 111, 110, R2.attr.app_actionbar_color, R2.attr.appTheme, R2.attr.animateCircleAngleTo, R2.attr.animSize, R2.attr.ambientEnabled, R2.attr.altSrc, R2.attr.errorIconTintMode, R2.attr.errorIconTint}, new int[]{413, 412, 407, 406, 401, 400, R2.attr.arrowPosition, R2.attr.arrowLocation, 83, 82, 41, -3, -3, -3, -3, -3, 5, 4, 47, 46, 113, 112, R2.attr.app_theme_color, R2.attr.app_content_bg_color, R2.attr.animate_relativeTo, R2.attr.animateRelativeTo, R2.attr.amountMinSize, R2.attr.amountMaxSize, R2.attr.errorTextAppearance, -3}, new int[]{415, 414, R2.attr.backgroundImage, R2.attr.backgroundColor, R2.attr.backgroundSplit, R2.attr.backgroundOverlayColorAlpha, 103, 102, 55, 54, 16, -3, -3, -3, -3, -3, -3, -3, 20, 19, 85, 84, R2.attr.badgeTextColor, R2.attr.badgeStyle, R2.attr.banner_support_touch_interrupt, R2.attr.banner_slide_interval, R2.attr.behavior_autoShrink, R2.attr.behavior_autoHide, R2.attr.et_clear_selector, R2.attr.errorTextColor}, new int[]{417, 416, R2.attr.backgroundInsetEnd, R2.attr.backgroundInsetBottom, R2.attr.backgroundTint, R2.attr.backgroundStacked, 105, 104, 57, 56, -3, -3, -3, -3, -3, -3, -3, -3, 22, 21, 87, 86, R2.attr.banner_looper, R2.attr.banner_auto_scroll, R2.attr.barrierAllowsGoneWidgets, R2.attr.barLength, R2.attr.behavior_expandedOffset, R2.attr.behavior_draggable, R2.attr.exTabBackground, -3}, new int[]{419, 418, R2.attr.backgroundInsetTop, R2.attr.backgroundInsetStart, R2.attr.badgeGravity, R2.attr.backgroundTintMode, 107, 106, 59, 58, -3, -3, -3, -3, -3, -3, -3, -3, -3, 23, 89, 88, R2.attr.banner_slide_duration, R2.attr.banner_slide_direction, 443, R2.attr.barrierDirection, R2.attr.behavior_halfExpandedRatio, 448, R2.attr.exTabGravity, R2.attr.exTabContentStart}, new int[]{R2.attr.bottomSheetStyle, 480, R2.attr.bottomLeftRadius, R2.attr.bottomLeftBorderRadius, R2.attr.border_color, R2.attr.borderWidth, 48, -2, 30, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, 0, 53, 52, R2.attr.blurOverlayColor, R2.attr.blurDownScale, R2.attr.bgColor, R2.attr.bg, R2.attr.behavior_overlapTop, R2.attr.behavior_hideable, R2.attr.exTabIndicatorColor, -3}, new int[]{R2.attr.boxBackgroundMode, R2.attr.boxBackgroundColor, R2.attr.bottomNavigationStyle, R2.attr.bottomMarginTop, R2.attr.border_width, R2.attr.border_overlay, 49, -1, -2, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -2, -1, R2.attr.borderRadius, R2.attr.borderColor, R2.attr.bgSize, R2.attr.bgCorner, R2.attr.behavior_saveFlags, R2.attr.behavior_peekHeight, R2.attr.exTabIndicatorGravity, R2.attr.exTabIndicatorDrawable}, new int[]{R2.attr.boxCornerRadiusBottomEnd, 484, R2.attr.bottomRightRadius, R2.attr.bottomRightBorderRadius, R2.attr.bottomAppBarStyle, R2.attr.borderlessButtonStyle, 51, 50, 31, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, 1, -2, 42, R2.attr.borderRoundPercent, R2.attr.borderRound, R2.attr.blurDefaultColor, R2.attr.blendSrc, R2.attr.betaAngle, R2.attr.behavior_skipCollapsed, R2.attr.exTabIndicatorHeight, -3}, new int[]{R2.attr.boxCornerRadiusTopEnd, R2.attr.boxCornerRadiusBottomStart, R2.attr.brightness, R2.attr.boxStrokeWidthFocused, R2.attr.businessBg, R2.attr.bubbleColor, 97, 96, 61, 60, -3, -3, -3, -3, -3, -3, -3, -3, -3, 26, 91, 90, 505, 504, 511, 510, R2.attr.buttonStyle, R2.attr.buttonSize, R2.attr.exTabIndicatorStretch, R2.attr.exTabIndicatorPadding}, new int[]{489, R2.attr.boxCornerRadiusTopStart, R2.attr.btnIconColor, R2.attr.btnIconAlpha, 501, 500, 99, 98, 63, 62, -3, -3, -3, -3, -3, -3, -3, -3, 28, 27, 93, 92, 507, 506, 513, 512, R2.attr.buttonStyleType, R2.attr.buttonStyleSmall, R2.attr.exTabIndicatorWidth, -3}, new int[]{R2.attr.boxStrokeWidth, R2.attr.boxStrokeErrorColor, R2.attr.btn_title, R2.attr.btn_icon, 503, 502, 101, 100, 65, 64, 17, -3, -3, -3, -3, -3, -3, -3, 18, 29, 95, 94, 509, 508, R2.attr.buttonPanelSideLayout, R2.attr.buttonIconDimen, R2.attr.buttonTint, R2.attr.buttonTextColor, R2.attr.exTabMinWidth, R2.attr.exTabMaxWidth}, new int[]{R2.attr.chatfrom_voice_playing_f1, R2.attr.chatfrom_bg_app, R2.attr.carousel_touchUp_velocityThreshold, R2.attr.carousel_touchUp_dampeningFactor, R2.attr.carousel_forwardTransition, 546, R2.attr.cardPreventCornerOverlap, R2.attr.cardMaxElevation, 73, 72, 32, -3, -3, -3, -3, -3, -3, 10, 67, 66, 115, 114, R2.attr.capsLockShiftIcon, R2.attr.can_rota, R2.attr.cameraBearing, R2.attr.calendarViewShown, R2.attr.buyButtonAppearance, R2.attr.buttonTintMode, R2.attr.exTabMode, -3}, new int[]{R2.attr.chatfrom_voice_playing_f3, R2.attr.chatfrom_voice_playing_f2, R2.attr.chainUseRtl, R2.attr.center_ellipsize_textview, R2.attr.carousel_nextState, R2.attr.carousel_infinite, R2.attr.cardViewStyle, R2.attr.cardUseCompatPadding, 75, 74, -2, -1, 7, 6, 35, 34, 11, -2, 69, 68, 117, 116, R2.attr.cardCornerRadius, R2.attr.cardBackgroundColor, R2.attr.cameraTargetLng, R2.attr.cameraTargetLat, R2.attr.buyButtonText, R2.attr.buyButtonHeight, R2.attr.exTabPaddingBottom, R2.attr.exTabPadding}, new int[]{R2.attr.chatto_bg_app, R2.attr.chatto_bg, R2.attr.chatfrom_bg, R2.attr.channelId, R2.attr.carousel_touchUpMode, R2.attr.carousel_previousState, R2.attr.carousel_emptyViewsBehavior, R2.attr.carousel_backwardTransition, 77, 76, -2, 33, 9, 8, 25, 24, -1, -2, 71, 70, 119, 118, R2.attr.cardForegroundColor, R2.attr.cardElevation, R2.attr.cameraZoom, R2.attr.cameraTilt, R2.attr.calendarTextColor, R2.attr.buyButtonWidth, R2.attr.exTabPaddingEnd, -3}, new int[]{R2.attr.checkbox_selector, R2.attr.checkboxStyle, R2.attr.checkedIconMargin, R2.attr.checkedIconEnabled, R2.attr.chipBackgroundColor, R2.attr.checked_is_bold, R2.attr.chipIconSize, R2.attr.chipIconEnabled, R2.attr.chipSpacingHorizontal, R2.attr.chipSpacing, R2.attr.chipStyle, R2.attr.chipStrokeWidth, 601, 600, R2.attr.circularflow_radiusInDP, R2.attr.circularflow_defaultRadius, R2.attr.civ_fill_color, R2.attr.civ_circle_background_color, R2.attr.clearsTag, R2.attr.classic_space, R2.attr.clockIcon, R2.attr.clockHandColor, R2.attr.closeIconStartPadding, 630, R2.attr.collapsedSize, R2.attr.collapseIcon, R2.attr.colorAccent, R2.attr.color, R2.attr.exTabPaddingTop, R2.attr.exTabPaddingStart}, new int[]{R2.attr.checkedButton, R2.attr.checked, R2.attr.checkedIconTint, R2.attr.checkedIconSize, R2.attr.chipEndPadding, 578, R2.attr.chipIconVisible, R2.attr.chipIconTint, R2.attr.chipStandaloneStyle, R2.attr.chipSpacingVertical, R2.attr.circleCrop, R2.attr.chipSurfaceColor, 603, 602, R2.attr.civ_border_color, R2.attr.circularflow_viewCenter, R2.attr.classic_color_selected, R2.attr.classic_color_normal, R2.attr.clickAutoSelection, 620, R2.attr.closeIcon, R2.attr.clockNumberTextColor, R2.attr.closeIconVisible, R2.attr.closeIconTint, R2.attr.collapsedTitleTextAppearance, R2.attr.collapsedTitleGravity, R2.attr.colorButtonNormal, R2.attr.colorBackgroundFloating, R2.attr.exTabSelectedTextColor, -3}, new int[]{R2.attr.checkedIcon, R2.attr.checkedChip, R2.attr.checkedTextViewStyle, R2.attr.checkedIconVisible, R2.attr.chipIcon, R2.attr.chipGroupStyle, R2.attr.chipMinTouchTargetSize, R2.attr.chipMinHeight, R2.attr.chipStrokeColor, R2.attr.chipStartPadding, R2.attr.circleRadius, R2.attr.circleInterval, 605, 604, R2.attr.civ_border_width, 610, R2.attr.classic_radius, R2.attr.classic_loop, R2.attr.clockFaceBackgroundColor, R2.attr.click_remove_id, R2.attr.closeIconEndPadding, R2.attr.closeIconEnabled, R2.attr.collapseContentDescription, R2.attr.closeItemLayout, R2.attr.collapsingToolbarLayoutStyle, 640, R2.attr.colorControlHighlight, R2.attr.colorControlActivated, R2.attr.exTabTextAppearance, R2.attr.exTabSelectedTextSize}, new int[]{R2.attr.curveFit, R2.attr.currentState, R2.attr.counterTextAppearance, R2.attr.counterOverflowTextColor, R2.attr.cornerSizeTopRight, R2.attr.cornerSizeTopLeft, R2.attr.cornerFamilyTopRight, R2.attr.cornerFamilyTopLeft, 703, 702, R2.attr.contentPaddingRight, R2.attr.contentPaddingLeft, R2.attr.contentInsetStart, 690, R2.attr.content, R2.attr.containerTopDeltaLength, R2.attr.containerCornerRadius, R2.attr.containerBottomDeltaLength, R2.attr.constraintSetEnd, R2.attr.constraintSet, R2.attr.colorful_game, 666, R2.attr.colorSecondary, 660, R2.attr.colorOnSurface, R2.attr.colorOnSecondary, R2.attr.colorError, R2.attr.colorControlNormal, R2.attr.exTabTextColor, -3}, new int[]{R2.attr.customColorDrawableValue, R2.attr.customBoolean, R2.attr.crossfade, R2.attr.counterTextColor, 717, R2.attr.count, R2.attr.cornerSize, R2.attr.cornerRadius, R2.attr.cornerFamily, 704, R2.attr.contentPaddingTop, R2.attr.contentPaddingStart, R2.attr.contentPadding, R2.attr.contentInsetStartWithNavigation, R2.attr.contentInsetEnd, R2.attr.contentDescription, R2.attr.containerRightDeltaLength, 680, R2.attr.constraint_referenced_ids, R2.attr.constraintSetStart, R2.attr.columnSize, R2.attr.colorful_moment, R2.attr.colorSurface, R2.attr.colorSecondaryVariant, R2.attr.colorPrimaryDark, R2.attr.colorPrimary, R2.attr.colorOnError, 650, R2.attr.excludeSpaceEnabled, R2.attr.exTabTextSize}, new int[]{731, R2.attr.customColorValue, R2.attr.currencySymbolMinSize, R2.attr.currencySymbolMaxSize, R2.attr.counterOverflowTextAppearance, R2.attr.counterMaxLength, R2.attr.cornerSizeBottomRight, R2.attr.cornerSizeBottomLeft, R2.attr.cornerFamilyBottomRight, R2.attr.cornerFamilyBottomLeft, 701, 700, R2.attr.contentPaddingEnd, R2.attr.contentPaddingBottom, R2.attr.contentInsetLeft, R2.attr.contentInsetEndWithActions, R2.attr.containerShadowRadius, R2.attr.containerShadowColor, R2.attr.constraints, R2.attr.constraint_referenced_tags, R2.attr.constraintRotate, 670, R2.attr.colorful_card, R2.attr.colorSwitchThumbNormal, R2.attr.colorPrimaryVariant, R2.attr.colorPrimarySurface, R2.attr.colorOnPrimarySurface, R2.attr.colorOnPrimary, R2.attr.excludeSpacePadding, -3}, new int[]{R2.attr.customIntegerValue, R2.attr.customFloatValue, R2.attr.custom_open, R2.attr.customViewStyle, R2.attr.dayOfWeekBackground, R2.attr.dayInvalidStyle, 751, R2.attr.decimalNumber, R2.attr.deltaPolarAngle, R2.attr.deleteIcon, R2.attr.dialogPreferredPadding, R2.attr.dialogCornerRadius, R2.attr.dividerDrawableVertical, R2.attr.dividerDrawableHorizontal, R2.attr.dragScale, R2.attr.dragDirection, R2.attr.drawPath, R2.attr.drag_start_mode, R2.attr.drawableRight2, R2.attr.drawableRight, R2.attr.drawableTitle, R2.attr.drawableTintMode, R2.attr.duration, R2.attr.dropdownListPreferredItemHeight, R2.attr.elevation, R2.attr.editTextStyle, R2.attr.emptyVisibility, R2.attr.emoji_pop_bg_smiley_right, R2.attr.expandActivityOverflowButtonDrawable, R2.attr.exitOffset}, new int[]{R2.attr.customPixelDimension, R2.attr.customNavigationLayout, R2.attr.dark_mode, R2.attr.darkThemeMode, R2.attr.daySelectedStyle, R2.attr.dayOfWeekTextAppearance, R2.attr.defaultState, 752, R2.attr.deltaX, R2.attr.deltaPolarRadius, R2.attr.displayOptions, R2.attr.dialogTheme, R2.attr.dividerPadding, R2.attr.dividerHorizontal, R2.attr.drag_enabled, R2.attr.dragThreshold, R2.attr.drawableEndCompat, R2.attr.drawableBottomCompat, R2.attr.drawableSize, R2.attr.drawableRightCompat, R2.attr.drawerArrowStyle, R2.attr.drawableTopCompat, 801, 800, R2.attr.elevationOverlayEnabled, R2.attr.elevationOverlayColor, R2.attr.enableEdgeToEdge, R2.attr.enable, R2.attr.expanded, -3}, new int[]{R2.attr.customStringValue, R2.attr.customReference, R2.attr.datePickerStyle, R2.attr.datePickerMode, R2.attr.dayTodayStyle, R2.attr.dayStyle, R2.attr.degree, R2.attr.default_bg_color, R2.attr.deriveConstraintsFrom, R2.attr.deltaY, R2.attr.dividerDrawable, R2.attr.divider, R2.attr.dotColor, R2.attr.dividerVertical, R2.attr.drag_scroll_start, R2.attr.drag_handle_id, R2.attr.drawableLeftCompat, R2.attr.drawableLeft, R2.attr.drawableTint, 790, R2.attr.drop_animation_duration, R2.attr.dropDownListViewStyle, 803, 802, R2.attr.emoji_pop_bg_smiley_left, R2.attr.emoji_pop_bg_smiley, R2.attr.endIconContentDescription, R2.attr.endIconCheckable, R2.attr.expandedTitleGravity, R2.attr.expandedHintEnabled}};
    private final BitMatrix bitMatrix;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BitMatrixParser(BitMatrix bitMatrix) {
        this.bitMatrix = bitMatrix;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] readCodewords() {
        byte[] bArr = new byte[144];
        int height = this.bitMatrix.getHeight();
        int width = this.bitMatrix.getWidth();
        for (int i2 = 0; i2 < height; i2++) {
            int[] iArr = BITNR[i2];
            for (int i3 = 0; i3 < width; i3++) {
                int i4 = iArr[i3];
                if (i4 >= 0 && this.bitMatrix.get(i3, i2)) {
                    int i5 = i4 / 6;
                    bArr[i5] = (byte) (((byte) (1 << (5 - (i4 % 6)))) | bArr[i5]);
                }
            }
        }
        return bArr;
    }
}
