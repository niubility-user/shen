package com.facebook.react.uimanager;

import com.facebook.react.bridge.ReadableMap;
import java.util.Arrays;
import java.util.HashSet;

/* loaded from: classes12.dex */
public class ViewProps {
    public static final String ALLOW_FONT_SCALING = "allowFontScaling";
    public static final String ASPECT_RATIO = "aspectRatio";
    public static final String AUTO = "auto";
    public static final String BACKGROUND_COLOR = "backgroundColor";
    public static final String BORDER_BOTTOM_COLOR = "borderBottomColor";
    public static final String BORDER_BOTTOM_END_RADIUS = "borderBottomEndRadius";
    public static final String BORDER_BOTTOM_LEFT_RADIUS = "borderBottomLeftRadius";
    public static final String BORDER_BOTTOM_RIGHT_RADIUS = "borderBottomRightRadius";
    public static final String BORDER_BOTTOM_START_RADIUS = "borderBottomStartRadius";
    public static final String BORDER_BOTTOM_WIDTH = "borderBottomWidth";
    public static final String BORDER_COLOR = "borderColor";
    public static final String BORDER_END_COLOR = "borderEndColor";
    public static final String BORDER_END_WIDTH = "borderEndWidth";
    public static final String BORDER_LEFT_COLOR = "borderLeftColor";
    public static final String BORDER_LEFT_WIDTH = "borderLeftWidth";
    public static final String BORDER_RADIUS = "borderRadius";
    public static final String BORDER_RIGHT_COLOR = "borderRightColor";
    public static final String BORDER_RIGHT_WIDTH = "borderRightWidth";
    public static final String BORDER_START_COLOR = "borderStartColor";
    public static final String BORDER_START_WIDTH = "borderStartWidth";
    public static final String BORDER_TOP_COLOR = "borderTopColor";
    public static final String BORDER_TOP_END_RADIUS = "borderTopEndRadius";
    public static final String BORDER_TOP_LEFT_RADIUS = "borderTopLeftRadius";
    public static final String BORDER_TOP_RIGHT_RADIUS = "borderTopRightRadius";
    public static final String BORDER_TOP_START_RADIUS = "borderTopStartRadius";
    public static final String BORDER_TOP_WIDTH = "borderTopWidth";
    public static final String BORDER_WIDTH = "borderWidth";
    public static final String BOTTOM = "bottom";
    public static final String BOX_NONE = "box-none";
    public static final String COLOR = "color";
    public static final String ELLIPSIZE_MODE = "ellipsizeMode";
    public static final String ENABLED = "enabled";
    public static final String END = "end";
    public static final String FONT_FAMILY = "fontFamily";
    public static final String FONT_SIZE = "fontSize";
    public static final String FONT_STYLE = "fontStyle";
    public static final String FONT_WEIGHT = "fontWeight";
    public static final String HEIGHT = "height";
    public static final String HIDDEN = "hidden";
    public static final String INCLUDE_FONT_PADDING = "includeFontPadding";
    public static final String LEFT = "left";
    public static final String LETTER_SPACING = "letterSpacing";
    public static final String LINESPACING_FROM_FALLBACK = "useLineSpacingFromFallbacks";
    public static final String LINE_HEIGHT = "lineHeight";
    public static final String MARGIN = "margin";
    public static final String MARGIN_BOTTOM = "marginBottom";
    public static final String MARGIN_END = "marginEnd";
    public static final String MARGIN_HORIZONTAL = "marginHorizontal";
    public static final String MARGIN_LEFT = "marginLeft";
    public static final String MARGIN_RIGHT = "marginRight";
    public static final String MARGIN_START = "marginStart";
    public static final String MARGIN_TOP = "marginTop";
    public static final String MARGIN_VERTICAL = "marginVertical";
    public static final String MAX_FONT_SIZE_MULTIPLIER = "maxFontSizeMultiplier";
    public static final String MAX_HEIGHT = "maxHeight";
    public static final String MAX_WIDTH = "maxWidth";
    public static final String NEEDS_OFFSCREEN_ALPHA_COMPOSITING = "needsOffscreenAlphaCompositing";
    public static final String NONE = "none";
    public static final String NUMBER_OF_LINES = "numberOfLines";
    public static final String ON = "on";
    public static final String ON_LAYOUT = "onLayout";
    public static final String OPACITY = "opacity";
    public static final String OVERFLOW = "overflow";
    public static final String PADDING = "padding";
    public static final String PADDING_BOTTOM = "paddingBottom";
    public static final String PADDING_END = "paddingEnd";
    public static final String PADDING_HORIZONTAL = "paddingHorizontal";
    public static final String PADDING_LEFT = "paddingLeft";
    public static final String PADDING_RIGHT = "paddingRight";
    public static final String PADDING_START = "paddingStart";
    public static final String PADDING_TOP = "paddingTop";
    public static final String PADDING_VERTICAL = "paddingVertical";
    public static final String POINTER_EVENTS = "pointerEvents";
    public static final String POSITION = "position";
    public static final String RESIZE_METHOD = "resizeMethod";
    public static final String RESIZE_MODE = "resizeMode";
    public static final String RIGHT = "right";
    public static final String SCROLL = "scroll";
    public static final String START = "start";
    public static final String TEXT_ALIGN = "textAlign";
    public static final String TEXT_ALIGN_VERTICAL = "textAlignVertical";
    public static final String TEXT_BREAK_STRATEGY = "textBreakStrategy";
    public static final String TEXT_DECORATION_LINE = "textDecorationLine";
    public static final String TOP = "top";
    public static final String VIEW_CLASS_NAME = "RCTView";
    public static final String VISIBLE = "visible";
    public static final String WIDTH = "width";
    public static final int[] BORDER_SPACING_TYPES = {8, 4, 5, 1, 3, 0, 2};
    public static final int[] PADDING_MARGIN_SPACING_TYPES = {8, 7, 6, 4, 5, 1, 3, 0, 2};
    public static final int[] POSITION_SPACING_TYPES = {4, 5, 1, 3};
    public static final String ALIGN_SELF = "alignSelf";
    public static final String ALIGN_ITEMS = "alignItems";
    public static final String COLLAPSABLE = "collapsable";
    public static final String FLEX = "flex";
    public static final String FLEX_BASIS = "flexBasis";
    public static final String FLEX_DIRECTION = "flexDirection";
    public static final String FLEX_GROW = "flexGrow";
    public static final String FLEX_SHRINK = "flexShrink";
    public static final String FLEX_WRAP = "flexWrap";
    public static final String JUSTIFY_CONTENT = "justifyContent";
    public static final String ALIGN_CONTENT = "alignContent";
    public static final String DISPLAY = "display";
    public static final String MIN_WIDTH = "minWidth";
    public static final String MIN_HEIGHT = "minHeight";
    private static final HashSet<String> LAYOUT_ONLY_PROPS = new HashSet<>(Arrays.asList(ALIGN_SELF, ALIGN_ITEMS, COLLAPSABLE, FLEX, FLEX_BASIS, FLEX_DIRECTION, FLEX_GROW, FLEX_SHRINK, FLEX_WRAP, JUSTIFY_CONTENT, ALIGN_CONTENT, DISPLAY, "position", "right", "top", "bottom", "left", "start", "end", "width", "height", MIN_WIDTH, "maxWidth", MIN_HEIGHT, "maxHeight", "margin", "marginVertical", "marginHorizontal", "marginLeft", "marginRight", "marginTop", "marginBottom", "marginStart", "marginEnd", "padding", "paddingVertical", "paddingHorizontal", "paddingLeft", "paddingRight", "paddingTop", "paddingBottom", "paddingStart", "paddingEnd"));

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static boolean isLayoutOnly(ReadableMap readableMap, String str) {
        char c2;
        if (LAYOUT_ONLY_PROPS.contains(str)) {
            return true;
        }
        if (POINTER_EVENTS.equals(str)) {
            String string = readableMap.getString(str);
            return "auto".equals(string) || BOX_NONE.equals(string);
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -1989576717:
                if (str.equals(BORDER_RIGHT_COLOR)) {
                    c2 = 0;
                    break;
                }
                c2 = '\uffff';
                break;
            case -1971292586:
                if (str.equals(BORDER_RIGHT_WIDTH)) {
                    c2 = 1;
                    break;
                }
                c2 = '\uffff';
                break;
            case -1470826662:
                if (str.equals(BORDER_TOP_COLOR)) {
                    c2 = 2;
                    break;
                }
                c2 = '\uffff';
                break;
            case -1452542531:
                if (str.equals(BORDER_TOP_WIDTH)) {
                    c2 = 3;
                    break;
                }
                c2 = '\uffff';
                break;
            case -1308858324:
                if (str.equals(BORDER_BOTTOM_COLOR)) {
                    c2 = 4;
                    break;
                }
                c2 = '\uffff';
                break;
            case -1290574193:
                if (str.equals(BORDER_BOTTOM_WIDTH)) {
                    c2 = 5;
                    break;
                }
                c2 = '\uffff';
                break;
            case -1267206133:
                if (str.equals(OPACITY)) {
                    c2 = 6;
                    break;
                }
                c2 = '\uffff';
                break;
            case -242276144:
                if (str.equals(BORDER_LEFT_COLOR)) {
                    c2 = 7;
                    break;
                }
                c2 = '\uffff';
                break;
            case -223992013:
                if (str.equals(BORDER_LEFT_WIDTH)) {
                    c2 = '\b';
                    break;
                }
                c2 = '\uffff';
                break;
            case 529642498:
                if (str.equals(OVERFLOW)) {
                    c2 = '\t';
                    break;
                }
                c2 = '\uffff';
                break;
            case 741115130:
                if (str.equals("borderWidth")) {
                    c2 = '\n';
                    break;
                }
                c2 = '\uffff';
                break;
            case 1349188574:
                if (str.equals("borderRadius")) {
                    c2 = 11;
                    break;
                }
                c2 = '\uffff';
                break;
            default:
                c2 = '\uffff';
                break;
        }
        switch (c2) {
            case 0:
                return readableMap.getInt(BORDER_RIGHT_COLOR) == 0;
            case 1:
                return readableMap.isNull(BORDER_RIGHT_WIDTH) || readableMap.getDouble(BORDER_RIGHT_WIDTH) == 0.0d;
            case 2:
                return readableMap.getInt(BORDER_TOP_COLOR) == 0;
            case 3:
                return readableMap.isNull(BORDER_TOP_WIDTH) || readableMap.getDouble(BORDER_TOP_WIDTH) == 0.0d;
            case 4:
                return readableMap.getInt(BORDER_BOTTOM_COLOR) == 0;
            case 5:
                return readableMap.isNull(BORDER_BOTTOM_WIDTH) || readableMap.getDouble(BORDER_BOTTOM_WIDTH) == 0.0d;
            case 6:
                return readableMap.isNull(OPACITY) || readableMap.getDouble(OPACITY) == 1.0d;
            case 7:
                return readableMap.getInt(BORDER_LEFT_COLOR) == 0;
            case '\b':
                return readableMap.isNull(BORDER_LEFT_WIDTH) || readableMap.getDouble(BORDER_LEFT_WIDTH) == 0.0d;
            case '\t':
                return readableMap.isNull(OVERFLOW) || VISIBLE.equals(readableMap.getString(OVERFLOW));
            case '\n':
                return readableMap.isNull("borderWidth") || readableMap.getDouble("borderWidth") == 0.0d;
            case 11:
                if (!readableMap.hasKey(BACKGROUND_COLOR) || readableMap.getInt(BACKGROUND_COLOR) == 0) {
                    return !readableMap.hasKey("borderWidth") || readableMap.isNull("borderWidth") || readableMap.getDouble("borderWidth") == 0.0d;
                }
                return false;
            default:
                return false;
        }
    }
}
