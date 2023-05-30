package com.jd.viewkit.templates.model;

import android.text.TextUtils;
import android.widget.ImageView;
import com.jd.dynamic.DYConstants;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.helper.JDViewKitConstant;
import com.jd.viewkit.templates.model.JDViewKitVirtualBannerView.JDViewKitVirtualBannerView;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualChainEvent;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualEvent;
import com.jd.viewkit.templates.model.jdveiwkitvirtualmultistateview.JDViewKitVirtualMultistateView;
import com.jd.viewkit.templates.model.jdviewkitdynamicbanner.JDViewKitVirtualDynamicBanner;
import com.jd.viewkit.templates.model.jdviewkitvirtualanchornavview.JDViewKitVirtualAnchorNavView;
import com.jd.viewkit.templates.model.jdviewkitvirtualbottomnavview.JDViewKitVirtualBottomNavView;
import com.jd.viewkit.templates.model.jdviewkitvirtualcountdownview.JDViewKitVirtualCountdownView;
import com.jd.viewkit.templates.model.jdviewkitvirtualcouponview.JDViewKitVirtualCouponView;
import com.jd.viewkit.templates.model.jdviewkitvirtualfreecouponview.JDViewKitVirtualFreeCouponView;
import com.jd.viewkit.templates.model.jdviewkitvirtualrichtextview.JDViewKitVirtualRichTextView;
import com.jd.viewkit.templates.model.jdviewkitvirtualscrollview.JDViewKitVirtualScrollView;
import com.jd.viewkit.templates.model.jdviewkitvirtualsearchview.JDViewKitVirtualSearchView;
import com.jd.viewkit.templates.model.jdviewkitvirtualstatefulview.JDViewKitVirtualStatefulView;
import com.jd.viewkit.templates.model.jdviewkitvirtualtopnavview.JDViewKitVirtualTopNavView;
import com.jd.viewkit.templates.model.jdviewkitvirtualvidelview.JDViewKitVirtualVideoView;
import com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel;
import com.jd.viewkit.tool.ColorTool;
import com.jd.viewkit.tool.JSONTool;
import com.jd.viewkit.tool.NumberTool;
import com.jd.viewkit.tool.StringTool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualView {
    public static String ImgSTcenterCrop = "centerCrop";
    public static String ImgSTfitCenter = "fitCenter";
    public static String ImgSTfitXY = "fitXY";
    public static String backgroundColorKey = "bgColor";
    public static String backgroundImageKey = "bgImage";
    public static String bgScaleTypeKey = "bgScaleType";
    public static String borderColorKey = "borderColor";
    public static String borderRadiusKey = "borderRadius";
    public static String borderWidthKey = "borderWidth";
    public static String childrenKey = "children";
    public static String dslIdKey = "dslId";
    public static String eventChainKey = "eventChain";
    public static String eventKey = "event";
    public static String expoEventKey = "expoEvent";
    public static String frameKey = "frame";
    public static String hiddenKey = "hidden";
    public static String isFilterKey = "isFilter";
    public static String layoutKey = "layout";
    public static String layout_alignSelfKey = "alignSelf";
    public static String layout_directionKey = "direction";
    public static String layout_itemSpaceKey = "itemSpace";
    public static String layout_mainAxisKey = "mainAxis";
    public static String layout_subAxisKey = "subAxis";
    public static String layout_typeKey = "type";
    public static String paddingKey = "padding";
    public static String roundedRadiusKey = "roundedRadius";
    public static String styleKey = "style";
    public static String subTypeKey = "subType";
    public static String subViewTypeCardHeap = "cardHeap";
    public static String subViewTypeFullHorizontalBanner = "horizontalFullScreen";
    public static String subViewTypeGradientVerticalBanner = "gradient";
    public static String subViewTypeHorizontalBanner = "horizontal";
    public static String subViewTypeReversalVerticalBanner = "reversal";
    public static String subViewTypeStateful = "radioContainer";
    public static String subViewTypeVerticalBanner = "vertical";
    public static String templateKey = "template";
    public static String valueReferKey = "valueRefer";
    public static String viewType = "type";
    public static String viewTypeAnchorNav = "anchorNav";
    public static String viewTypeBanner = "banner";
    public static String viewTypeBottomNav = "bottomNav";
    public static String viewTypeCountdown = "countdown";
    public static String viewTypeCoupon = "coupon";
    public static String viewTypeDynamicBanner = "dynamicBanner";
    public static String viewTypeFlatView = "flatView";
    public static String viewTypeFreeCoupon = "freeCoupon";
    public static String viewTypeImage = "image";
    public static String viewTypeMultiPlusTab = "multiTabPlus";
    public static String viewTypeMultiTab = "multiTab";
    public static String viewTypeMultistateView = "multistateView";
    public static String viewTypeProgress = "progress";
    public static String viewTypeRichText = "richText";
    public static String viewTypeScroll = "scroll";
    public static String viewTypeSearch = "search";
    public static String viewTypeStateful = "statefulContainer";
    public static String viewTypeText = "text";
    public static String viewTypeTopNav = "topNav";
    public static String viewTypeVideo = "video";
    public static String viewTypeView = "view";
    public static Map<String, Class<? extends JDViewKitVirtualView>> virtualClassMap;
    private String backgroundColor;
    private int backgroundColorInt;
    private String backgroundImage;
    private String bgScaleType;
    private String borderColor;
    private int borderColorInt;
    private int borderRadius;
    private int borderWidth;
    private int bottomLeftRadius;
    private int bottomRightRadius;
    private ArrayList<JDViewKitVirtualView> children;
    private String dslId;
    private Map<String, JDViewKitVirtualView> dslMap;
    private JDViewKitVirtualEvent expoEvent;
    private int heigh;
    private String hidden;
    private String isFilter;
    private String layout_alignSelf;
    private String layout_direction;
    private int layout_itemSpace;
    private String layout_mainAxis;
    private String layout_subAxis;
    private String layout_type;
    private int orgX;
    private int orgY;
    private int paddingBottom;
    private int paddingLeft;
    private int paddingRight;
    private int paddingTop;
    protected JDViewKitVirtualServiceModel serviceModel;
    private String subType;
    private int topLeftRadius;
    private int topRightRadius;
    private String type;
    private String valueRefer;
    private List<JDViewKitVirtualChainEvent> virtualChainEventList;
    private List<JDViewKitVirtualEvent> virtualEventList;
    private int width;

    static {
        HashMap hashMap = new HashMap();
        virtualClassMap = hashMap;
        hashMap.put(viewTypeView, JDViewKitVirtualView.class);
        virtualClassMap.put(viewTypeText, JDViewKitVirtualTextView.class);
        virtualClassMap.put(viewTypeImage, JDViewKitVirtualImageView.class);
        virtualClassMap.put(viewTypeFlatView, JDViewKitVirtualView.class);
        virtualClassMap.put(viewTypeBanner, JDViewKitVirtualBannerView.class);
        virtualClassMap.put(viewTypeDynamicBanner, JDViewKitVirtualDynamicBanner.class);
        virtualClassMap.put(viewTypeScroll, JDViewKitVirtualScrollView.class);
        virtualClassMap.put(viewTypeStateful, JDViewKitVirtualStatefulView.class);
        virtualClassMap.put(viewTypeMultiTab, JDViewKitVirtualView.class);
        virtualClassMap.put(viewTypeMultiPlusTab, JDViewKitVirtualView.class);
        virtualClassMap.put(viewTypeAnchorNav, JDViewKitVirtualAnchorNavView.class);
        virtualClassMap.put(viewTypeBottomNav, JDViewKitVirtualBottomNavView.class);
        virtualClassMap.put(viewTypeTopNav, JDViewKitVirtualTopNavView.class);
        virtualClassMap.put(viewTypeFreeCoupon, JDViewKitVirtualFreeCouponView.class);
        virtualClassMap.put(viewTypeCoupon, JDViewKitVirtualCouponView.class);
        virtualClassMap.put(viewTypeRichText, JDViewKitVirtualRichTextView.class);
        virtualClassMap.put(viewTypeProgress, JDViewKitVirtualProgressView.class);
        virtualClassMap.put(viewTypeSearch, JDViewKitVirtualSearchView.class);
        virtualClassMap.put(viewTypeMultistateView, JDViewKitVirtualMultistateView.class);
        virtualClassMap.put(viewTypeCountdown, JDViewKitVirtualCountdownView.class);
        virtualClassMap.put(viewTypeVideo, JDViewKitVirtualVideoView.class);
    }

    public JDViewKitVirtualView(JSONObject jSONObject, JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel, Map<String, JDViewKitVirtualView> map) {
        this.serviceModel = jDViewKitVirtualServiceModel;
        this.dslMap = map;
        setType(JSONTool.getString(jSONObject, viewType));
        setSubType(JSONTool.getString(jSONObject, subTypeKey));
        setHidden(JSONTool.getString(jSONObject, hiddenKey));
        JSONObject jSONObject2 = JSONTool.getJSONObject(jSONObject, styleKey);
        setBackgroundColor(JSONTool.getString(jSONObject2, backgroundColorKey));
        if (!StringTool.isEmpty(getBackgroundColor())) {
            setBackgroundColorInt(ColorTool.parseColor(getBackgroundColor(), 0));
        } else {
            setBackgroundColorInt(0);
        }
        setBackgroundImage(JSONTool.getString(jSONObject2, backgroundImageKey));
        setBgScaleType(JSONTool.getString(jSONObject2, bgScaleTypeKey));
        setBorderColor(JSONTool.getString(jSONObject2, borderColorKey));
        if (!StringTool.isEmpty(getBorderColor())) {
            setBorderColorInt(ColorTool.parseColor(getBorderColor(), ColorTool.parseColor("#00000000", -1)));
        }
        String string = JSONTool.getString(jSONObject2, frameKey);
        if (!StringTool.isEmpty(string)) {
            String[] split = string.split(DYConstants.DY_REGEX_COMMA);
            if (split.length == 4) {
                setOrgX(NumberTool.valueOfInt(split[0]));
                setOrgY(NumberTool.valueOfInt(split[1]));
                setWidth(NumberTool.valueOfInt(split[2]));
                setHeigh(NumberTool.valueOfInt(split[3]));
            }
        }
        String string2 = JSONTool.getString(jSONObject2, paddingKey);
        if (StringTool.isNotEmpty(string2)) {
            String[] split2 = string2.split(DYConstants.DY_REGEX_COMMA);
            if (split2.length == 4) {
                setPaddingTop(NumberTool.valueOfInt(split2[0]));
                setPaddingRight(NumberTool.valueOfInt(split2[1]));
                setPaddingBottom(NumberTool.valueOfInt(split2[2]));
                setPaddingLeft(NumberTool.valueOfInt(split2[3]));
            }
        }
        setBorderWidth(JSONTool.getInt(jSONObject2, borderWidthKey));
        if (JSONTool.get(jSONObject2, borderRadiusKey) != null) {
            setBorderRadius(JSONTool.getInt(jSONObject2, borderRadiusKey));
        } else if (StringTool.isNotEmpty(JSONTool.getString(jSONObject2, roundedRadiusKey))) {
            String[] split3 = JSONTool.getString(jSONObject2, roundedRadiusKey).split(DYConstants.DY_REGEX_COMMA);
            if (split3.length == 4) {
                setTopLeftRadius(NumberTool.valueOfInt(split3[0]));
                setTopRightRadius(NumberTool.valueOfInt(split3[1]));
                setBottomRightRadius(NumberTool.valueOfInt(split3[2]));
                setBottomLeftRadius(NumberTool.valueOfInt(split3[3]));
            }
        }
        setValueRefer(JSONTool.getString(jSONObject, valueReferKey));
        setFilter(JSONTool.getString(jSONObject, isFilterKey));
        setDslId(JSONTool.getString(jSONObject, dslIdKey));
        JSONArray jSONArray = JSONTool.getJSONArray(jSONObject, eventKey);
        if (jSONArray != null && jSONArray.length() > 0) {
            this.virtualEventList = new ArrayList();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                this.virtualEventList.add(new JDViewKitVirtualEvent(JSONTool.getJSONObject(jSONArray, i2)));
            }
        }
        JSONObject jSONObject3 = JSONTool.getJSONObject(jSONObject, layoutKey);
        if (jSONObject3 != null) {
            this.layout_type = JSONTool.getString(jSONObject3, layout_typeKey);
            this.layout_alignSelf = JSONTool.getString(jSONObject3, layout_alignSelfKey);
            this.layout_direction = JSONTool.getString(jSONObject3, layout_directionKey);
            this.layout_mainAxis = JSONTool.getString(jSONObject3, layout_mainAxisKey);
            this.layout_subAxis = JSONTool.getString(jSONObject3, layout_subAxisKey);
            this.layout_itemSpace = JSONTool.getInt(jSONObject3, layout_itemSpaceKey);
        }
        JSONArray jSONArray2 = JSONTool.getJSONArray(jSONObject, eventChainKey);
        if (jSONArray2 != null && jSONArray2.length() > 0) {
            this.virtualChainEventList = new ArrayList();
            for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                this.virtualChainEventList.add(new JDViewKitVirtualChainEvent(JSONTool.getJSONObject(jSONArray2, i3)));
            }
        }
        JSONObject jSONObject4 = JSONTool.getJSONObject(jSONObject, expoEventKey);
        this.expoEvent = jSONObject4 != null ? new JDViewKitVirtualEvent(jSONObject4) : null;
        if (jSONObject.isNull(childrenKey)) {
            return;
        }
        this.children = new ArrayList<>();
        JSONArray jSONArray3 = JSONTool.getJSONArray(jSONObject, childrenKey);
        for (int i4 = 0; i4 < jSONArray3.length(); i4++) {
            JDViewKitVirtualView parseVirtualView = parseVirtualView(JSONTool.getJSONObject(jSONArray3, i4), jDViewKitVirtualServiceModel, map);
            if (parseVirtualView != null) {
                this.children.add(parseVirtualView);
            }
        }
    }

    public static JDViewKitVirtualView parseVirtualView(JSONObject jSONObject, JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel, Map<String, JDViewKitVirtualView> map) {
        String string = JSONTool.getString(jSONObject, viewType);
        if (StringTool.isEmpty(string)) {
            return null;
        }
        try {
            Class<? extends JDViewKitVirtualView> cls = virtualClassMap.get(string);
            if (cls != null) {
                return cls.getConstructor(JSONObject.class, JDViewKitVirtualServiceModel.class, Map.class).newInstance(jSONObject, jDViewKitVirtualServiceModel, map);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return null;
    }

    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    public int getBackgroundColorInt() {
        return this.backgroundColorInt;
    }

    public String getBackgroundImage() {
        return this.backgroundImage;
    }

    public ImageView.ScaleType getBgScaleType() {
        ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_CROP;
        if (TextUtils.isEmpty(this.bgScaleType)) {
            return scaleType;
        }
        if (ImgSTfitCenter.equals(this.bgScaleType)) {
            return ImageView.ScaleType.FIT_CENTER;
        }
        return ImgSTfitXY.equals(this.bgScaleType) ? ImageView.ScaleType.FIT_XY : scaleType;
    }

    public String getBgScaleTypeStr() {
        return this.bgScaleType;
    }

    public String getBorderColor() {
        return this.borderColor;
    }

    public int getBorderColorInt() {
        return this.borderColorInt;
    }

    public int getBorderRadius() {
        return this.borderRadius;
    }

    public int getBorderWidth() {
        return this.borderWidth;
    }

    public int getBottomLeftRadius() {
        return this.bottomLeftRadius;
    }

    public int getBottomRightRadius() {
        return this.bottomRightRadius;
    }

    public ArrayList<JDViewKitVirtualView> getChildren() {
        return this.children;
    }

    public String getDslId() {
        return this.dslId;
    }

    public Map<String, JDViewKitVirtualView> getDslMap() {
        return this.dslMap;
    }

    public JDViewKitVirtualEvent getExpoEvent() {
        return this.expoEvent;
    }

    public int getHeigh() {
        return this.heigh;
    }

    public String getHidden() {
        return this.hidden;
    }

    public String getLayout_alignSelf() {
        if (StringTool.isEmpty(this.layout_alignSelf)) {
            return JDViewKitConstant.LAYOUT_ALIGN_START;
        }
        return this.layout_alignSelf;
    }

    public String getLayout_direction() {
        if (StringTool.isEmpty(this.layout_direction)) {
            return JDViewKitConstant.LAYOUT_DIRECTION_HORIZONTAL;
        }
        return this.layout_direction;
    }

    public int getLayout_itemSpace() {
        return this.layout_itemSpace;
    }

    public String getLayout_mainAxis() {
        if (StringTool.isEmpty(this.layout_mainAxis)) {
            return JDViewKitConstant.LAYOUT_ALIGN_START;
        }
        return this.layout_mainAxis;
    }

    public String getLayout_subAxis() {
        if (StringTool.isEmpty(this.layout_subAxis)) {
            return JDViewKitConstant.LAYOUT_ALIGN_START;
        }
        return this.layout_subAxis;
    }

    public String getLayout_type() {
        if (StringTool.isEmpty(this.layout_type)) {
            return JDViewKitConstant.LYAOUT_TYPE_ABSOLUTE;
        }
        return this.layout_type;
    }

    public int getOrgX() {
        return this.orgX;
    }

    public int getOrgY() {
        return this.orgY;
    }

    public int getPaddingBottom() {
        return this.paddingBottom;
    }

    public int getPaddingLeft() {
        return this.paddingLeft;
    }

    public int getPaddingRight() {
        return this.paddingRight;
    }

    public int getPaddingTop() {
        return this.paddingTop;
    }

    public JDViewKitVirtualServiceModel getServiceModel() {
        return this.serviceModel;
    }

    public String getSubType() {
        return this.subType;
    }

    public int getTopLeftRadius() {
        return this.topLeftRadius;
    }

    public int getTopRightRadius() {
        return this.topRightRadius;
    }

    public String getType() {
        return this.type;
    }

    public String getValueRefer() {
        return this.valueRefer;
    }

    public List<JDViewKitVirtualChainEvent> getVirtualChainEventList() {
        return this.virtualChainEventList;
    }

    public JDViewKitVirtualEvent getVirtualEventByType(String str) {
        List<JDViewKitVirtualChainEvent> list = this.virtualChainEventList;
        if (list != null && list.size() > 0) {
            for (int i2 = 0; i2 < this.virtualChainEventList.size(); i2++) {
                JDViewKitVirtualChainEvent jDViewKitVirtualChainEvent = this.virtualChainEventList.get(i2);
                if (StringTool.isNotEmpty(jDViewKitVirtualChainEvent.getType()) && jDViewKitVirtualChainEvent.getType().equals(str)) {
                    return jDViewKitVirtualChainEvent;
                }
            }
        }
        List<JDViewKitVirtualEvent> list2 = this.virtualEventList;
        if (list2 == null || list2.size() <= 0) {
            return null;
        }
        for (int i3 = 0; i3 < this.virtualEventList.size(); i3++) {
            JDViewKitVirtualEvent jDViewKitVirtualEvent = this.virtualEventList.get(i3);
            if (StringTool.isNotEmpty(jDViewKitVirtualEvent.getType()) && jDViewKitVirtualEvent.getType().equals(str)) {
                return jDViewKitVirtualEvent;
            }
        }
        return null;
    }

    public List<JDViewKitVirtualEvent> getVirtualEventList() {
        return this.virtualEventList;
    }

    public int getWidth() {
        return this.width;
    }

    public boolean hasRadius() {
        return this.topLeftRadius > 0 || this.topRightRadius > 0 || this.bottomRightRadius > 0 || this.bottomLeftRadius > 0;
    }

    public String isFilter() {
        return this.isFilter;
    }

    public void setBackgroundColor(String str) {
        if (!StringTool.isEmpty(str)) {
            str = str.toLowerCase();
        }
        this.backgroundColor = str;
    }

    public void setBackgroundColorInt(int i2) {
        this.backgroundColorInt = i2;
    }

    public void setBackgroundImage(String str) {
        this.backgroundImage = str;
    }

    public void setBgScaleType(String str) {
        this.bgScaleType = str;
    }

    public void setBorderColor(String str) {
        this.borderColor = str;
    }

    public void setBorderColorInt(int i2) {
        this.borderColorInt = i2;
    }

    public void setBorderRadius(int i2) {
        this.borderRadius = i2;
        setTopLeftRadius(i2);
        setTopRightRadius(i2);
        setBottomRightRadius(i2);
        setBottomLeftRadius(i2);
    }

    public void setBorderWidth(int i2) {
        this.borderWidth = i2;
    }

    public void setBottomLeftRadius(int i2) {
        this.bottomLeftRadius = GlobalManage.getInstance().getRealPx(Math.min(Math.min(this.width, this.heigh) / 2, i2), null);
    }

    public void setBottomRightRadius(int i2) {
        this.bottomRightRadius = GlobalManage.getInstance().getRealPx(Math.min(Math.min(this.width, this.heigh) / 2, i2), null);
    }

    public void setDslId(String str) {
        this.dslId = str;
    }

    public void setDslMap(Map<String, JDViewKitVirtualView> map) {
        this.dslMap = map;
    }

    public void setExpoEvent(JDViewKitVirtualEvent jDViewKitVirtualEvent) {
        this.expoEvent = jDViewKitVirtualEvent;
    }

    public void setFilter(String str) {
        this.isFilter = str;
    }

    public void setHeigh(int i2) {
        this.heigh = i2;
    }

    public void setHidden(String str) {
        this.hidden = str;
    }

    public void setLayout_alignSelf(String str) {
        this.layout_alignSelf = str;
    }

    public void setLayout_direction(String str) {
        this.layout_direction = str;
    }

    public void setLayout_itemSpace(int i2) {
        this.layout_itemSpace = i2;
    }

    public void setLayout_mainAxis(String str) {
        this.layout_mainAxis = str;
    }

    public void setLayout_subAxis(String str) {
        this.layout_subAxis = str;
    }

    public void setLayout_type(String str) {
        this.layout_type = str;
    }

    public void setOrgX(int i2) {
        this.orgX = i2;
    }

    public void setOrgY(int i2) {
        this.orgY = i2;
    }

    public void setPaddingBottom(int i2) {
        this.paddingBottom = i2;
    }

    public void setPaddingLeft(int i2) {
        this.paddingLeft = i2;
    }

    public void setPaddingRight(int i2) {
        this.paddingRight = i2;
    }

    public void setPaddingTop(int i2) {
        this.paddingTop = i2;
    }

    public void setServiceModel(JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel) {
        this.serviceModel = jDViewKitVirtualServiceModel;
    }

    public void setSubType(String str) {
        this.subType = str;
    }

    public void setTopLeftRadius(int i2) {
        this.topLeftRadius = GlobalManage.getInstance().getRealPx(Math.min(Math.min(this.width, this.heigh) / 2, i2), null);
    }

    public void setTopRightRadius(int i2) {
        this.topRightRadius = GlobalManage.getInstance().getRealPx(Math.min(Math.min(this.width, this.heigh) / 2, i2), null);
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setValueRefer(String str) {
        this.valueRefer = str;
    }

    public void setVirtualChainEventList(List<JDViewKitVirtualChainEvent> list) {
        this.virtualChainEventList = list;
    }

    public void setVirtualEventList(List<JDViewKitVirtualEvent> list) {
        this.virtualEventList = list;
    }

    public void setWidth(int i2) {
        this.width = i2;
    }
}
