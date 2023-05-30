package com.jingdong.common.entity;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jingdong.common.entity.ExpandPropertys;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import com.unionpay.tsmservice.mi.data.Constant;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class Catelogy implements Serializable {
    public static final int CATELOGY = 0;
    public static final int CATELOGY_FILTER = 1;
    public static final int CATELOGY_NEW = 3;
    private static final String DESTINATION_AIRLINE_M = "airline_m";
    private static final String DESTINATION_DATA_RECHARGE = "data_charge";
    private static final String DESTINATION_EBOOK_M = "ebook_m";
    private static final String DESTINATION_EDUCATION = "education";
    private static final String DESTINATION_GAME = "game";
    private static final String DESTINATION_GAME_RECHARGE = "game_charge";
    private static final String DESTINATION_HOTBOOK = "hot_book";
    private static final String DESTINATION_JD_GAME_M = "jdgame_to";
    private static final String DESTINATION_LOTTERY_M = "lottery_m";
    private static final String DESTINATION_MOVIE = "movie";
    private static final String DESTINATION_MUSIC = "music";
    private static final String DESTINATION_M_WITH_ACTION = "_m";
    private static final String DESTINATION_M_WITH_TO = "_to";
    private static final String DESTINATION_NEWBOOK = "new_book";
    private static final String DESTINATION_QQ_RECHARGE = "QQ_charge";
    private static final String DESTINATION_RECHARGE_M = "chongzhi_m";
    private static final String DESTINATION_TV = "tv";
    public static final int RECORMMEND = 4;
    private static final String TAG = "Catelogy";
    public String action;
    private ArrayList<Catelogy> addHeaderChildCategories;
    public String cId;
    public String catelogLabel;
    private ArrayList<Catelogy> childCategories;
    public int columNum;
    public String description;
    public String destination;
    public ExpandAttrEntity entity;
    private List<ExpandPropertys> expandPropertysList;
    public String field;
    public boolean hasExpandAttr;
    public boolean hasLevelFour;
    private Catelogy headerCatelogy;
    private List<Image> imageList;
    public String imgUrl;
    public boolean isAllWorldShopping;
    public boolean isCatalogMiddlePage;
    public boolean isIndividual;
    public boolean isMerger;
    private JSONArrayPoxy labelJsonArray;
    public int level;
    public String level1Cid;
    public String level2Cid;
    public String level3Cid;
    private JSONArrayPoxy levelFourJsonArray;
    private List<CatelogyLevelFour> levelFourList;
    private List<MergedCatelogy> mergeCatalogs;
    public String name;
    public int num;
    public String originCid;
    public String path;
    private String searchKey;
    public int sensitiveFlag;
    public Long shopId;
    public boolean showTab;
    public int virtualFlag;
    public Integer wareNumber;

    public Catelogy() {
        this.imageList = new LinkedList();
        this.mergeCatalogs = null;
        this.showTab = false;
        this.isIndividual = false;
    }

    private void doCatelogyArray(JSONArrayPoxy jSONArrayPoxy) {
        if (OKLog.D) {
            OKLog.d(TAG, "doCatelogyArray() -->> ");
            if (jSONArrayPoxy != null) {
                OKLog.d(TAG, "jsonArrayOrNull.length() == " + jSONArrayPoxy.length());
            }
        }
        if (jSONArrayPoxy == null || jSONArrayPoxy.length() < 1 || this.childCategories != null) {
            return;
        }
        this.childCategories = new ArrayList<>();
        int length = jSONArrayPoxy.length();
        for (int i2 = 0; i2 < length; i2++) {
            try {
                this.childCategories.add(i2, new Catelogy(jSONArrayPoxy.getJSONObject(i2), 1));
            } catch (JSONException e2) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public static String getCmsTotalCid(ArrayList<Catelogy> arrayList, int i2) {
        if (i2 <= 0) {
            return DYConstants.DY_NULL_STR;
        }
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append(arrayList.get(i3).cId);
            if (i3 < i2 - 1) {
                sb.append(CartConstant.KEY_YB_INFO_LINK);
            }
        }
        return sb.toString();
    }

    private List<CatelogyLevelFour> getLevelFourList(JSONArrayPoxy jSONArrayPoxy) {
        if (OKLog.D) {
            OKLog.d(TAG, "getLevelFourList() -->> ");
            if (jSONArrayPoxy != null) {
                OKLog.d(TAG, "jsonArrayOrNull.length() == " + jSONArrayPoxy.length());
            }
        }
        if (jSONArrayPoxy == null || jSONArrayPoxy.length() < 1) {
            return null;
        }
        int length = jSONArrayPoxy.length();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < length; i2++) {
            CatelogyLevelFour catelogyLevelFour = new CatelogyLevelFour();
            try {
                JSONObjectProxy jSONObject = jSONArrayPoxy.getJSONObject(i2);
                catelogyLevelFour.catalogId = jSONObject.optString("catalogId");
                catelogyLevelFour.catalogName = jSONObject.optString("getCatalogName");
            } catch (JSONException e2) {
                OKLog.e(TAG, e2);
            }
            arrayList.add(catelogyLevelFour);
        }
        return arrayList;
    }

    private void setExpandAtttributes(JSONArrayPoxy jSONArrayPoxy) {
        if (jSONArrayPoxy != null && jSONArrayPoxy.length() >= 1) {
            List parseArray = JDJSON.parseArray(jSONArrayPoxy.toString(), Integer.TYPE);
            if (parseArray.contains(1)) {
                this.isAllWorldShopping = true;
            } else {
                this.isAllWorldShopping = false;
            }
            this.catelogLabel = listToString(parseArray, '-');
            return;
        }
        this.catelogLabel = DYConstants.DY_NULL_STR;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<com.jingdong.common.entity.Catelogy> toList(com.jingdong.jdsdk.utils.JSONArrayPoxy r4, int r5) {
        /*
            r0 = 0
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: org.json.JSONException -> L31
            r1.<init>()     // Catch: org.json.JSONException -> L31
            r0 = 0
        L7:
            int r2 = r4.length()     // Catch: org.json.JSONException -> L2e
            if (r0 >= r2) goto L3c
            com.jingdong.jdsdk.utils.JSONObjectProxy r2 = r4.getJSONObject(r0)     // Catch: org.json.JSONException -> L2e
            if (r2 == 0) goto L2b
            com.jingdong.jdsdk.utils.JSONObjectProxy r2 = r4.getJSONObject(r0)     // Catch: org.json.JSONException -> L2e
            java.lang.String r3 = "name"
            boolean r2 = r2.isNull(r3)     // Catch: org.json.JSONException -> L2e
            if (r2 != 0) goto L2b
            com.jingdong.common.entity.Catelogy r2 = new com.jingdong.common.entity.Catelogy     // Catch: org.json.JSONException -> L2e
            com.jingdong.jdsdk.utils.JSONObjectProxy r3 = r4.getJSONObject(r0)     // Catch: org.json.JSONException -> L2e
            r2.<init>(r3, r5)     // Catch: org.json.JSONException -> L2e
            r1.add(r2)     // Catch: org.json.JSONException -> L2e
        L2b:
            int r0 = r0 + 1
            goto L7
        L2e:
            r4 = move-exception
            r0 = r1
            goto L32
        L31:
            r4 = move-exception
        L32:
            boolean r5 = com.jingdong.sdk.oklog.OKLog.E
            if (r5 == 0) goto L3b
            java.lang.String r5 = "Ware"
            com.jingdong.sdk.oklog.OKLog.e(r5, r4)
        L3b:
            r1 = r0
        L3c:
            boolean r4 = com.jingdong.sdk.oklog.OKLog.D
            if (r4 == 0) goto L56
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "list:"
            r4.append(r5)
            r4.append(r1)
            java.lang.String r4 = r4.toString()
            java.lang.String r5 = "Catelogy"
            com.jingdong.sdk.oklog.OKLog.d(r5, r4)
        L56:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.entity.Catelogy.toList(com.jingdong.jdsdk.utils.JSONArrayPoxy, int):java.util.ArrayList");
    }

    private void translateExpandAttrsFormat(List<ExpandPropertys> list) {
        if (list != null && list.size() != 0) {
            this.hasExpandAttr = true;
            this.entity = new ExpandAttrEntity();
            for (int i2 = 0; i2 < list.size(); i2++) {
                ExpandPropertys expandPropertys = list.get(i2);
                if (expandPropertys != null) {
                    String str = expandPropertys.expandSuperId;
                    List<ExpandPropertys.ExpandSubProperysBean> list2 = expandPropertys.expandSubProperys;
                    StringBuffer stringBuffer = new StringBuffer("");
                    if (!TextUtils.isEmpty(str) && list2 != null && list2.size() != 0) {
                        for (int i3 = 0; i3 < list2.size(); i3++) {
                            if (list2.get(i3) != null && !TextUtils.isEmpty(list2.get(i3).expandSubId)) {
                                stringBuffer.append(list2.get(i3).expandSubId + "||");
                            }
                        }
                        this.entity.valueMap.put(expandPropertys.expandSuperId, stringBuffer.substring(0, stringBuffer.lastIndexOf("||")));
                    }
                }
            }
            return;
        }
        this.hasExpandAttr = false;
    }

    public String getAction() {
        String str = this.action;
        return str == null ? "" : str;
    }

    public ArrayList<Catelogy> getAddHeaderChildCategories() {
        ArrayList<Catelogy> arrayList = this.childCategories;
        if (arrayList != null && arrayList.size() >= 0) {
            ArrayList<Catelogy> arrayList2 = this.childCategories;
            this.addHeaderChildCategories = arrayList2;
            Catelogy catelogy = this.headerCatelogy;
            if (catelogy != null && !arrayList2.contains(catelogy)) {
                this.addHeaderChildCategories.add(0, this.headerCatelogy);
            }
        }
        return this.addHeaderChildCategories;
    }

    public ArrayList<Catelogy> getChildCategories() {
        return this.childCategories;
    }

    public String getDescription() {
        return TextUtils.isEmpty(this.description) ? "" : this.description;
    }

    public String getDestination() {
        String str = this.destination;
        return str == null ? "" : str;
    }

    public Image getImage() {
        if (this.imageList.size() > 0) {
            return this.imageList.get(0);
        }
        return null;
    }

    public List<Image> getImageList() {
        return this.imageList;
    }

    public List<MergedCatelogy> getMergeCatalogs() {
        return this.mergeCatalogs;
    }

    public String getName() {
        return TextUtils.isEmpty(this.name) ? "" : this.name;
    }

    public String getSearchKey() {
        return TextUtils.isEmpty(this.searchKey) ? "" : this.searchKey;
    }

    public Long getShopId() {
        Long l2 = this.shopId;
        if (l2 != null && l2.longValue() > 0) {
            return this.shopId;
        }
        return 0L;
    }

    public boolean isGoToEducation() {
        return DESTINATION_EDUCATION.equals(getDestination());
    }

    public boolean isGoToGame() {
        return DESTINATION_GAME.equals(getDestination());
    }

    public boolean isGoToHotBook() {
        return DESTINATION_HOTBOOK.equals(getDestination());
    }

    public boolean isGoToMWithAction() {
        if (TextUtils.isEmpty(this.destination)) {
            return false;
        }
        return this.destination.endsWith(DESTINATION_M_WITH_ACTION);
    }

    public boolean isGoToMWithTo() {
        if (TextUtils.isEmpty(this.destination)) {
            return false;
        }
        return this.destination.endsWith(DESTINATION_M_WITH_TO);
    }

    public boolean isGoToMoviePage() {
        if (TextUtils.isEmpty(this.destination)) {
            return false;
        }
        return this.destination.contains(DESTINATION_MOVIE);
    }

    public boolean isGoToMusic() {
        return "music".equals(getDestination());
    }

    public boolean isGoToNewBook() {
        return DESTINATION_NEWBOOK.equals(getDestination());
    }

    public boolean isGoToTv() {
        return DESTINATION_TV.equals(getDestination());
    }

    public boolean isWantedToAirLine() {
        return DESTINATION_AIRLINE_M.equals(getDestination());
    }

    public boolean isWantedToDataRecharge() {
        return DESTINATION_DATA_RECHARGE.equals(getDestination());
    }

    public boolean isWantedToEbookM() {
        return DESTINATION_EBOOK_M.equals(getDestination());
    }

    public boolean isWantedToGameRecharge() {
        return DESTINATION_GAME_RECHARGE.equals(getDestination());
    }

    public boolean isWantedToGoToShop() {
        return getShopId().longValue() > 0;
    }

    public boolean isWantedToJDGame() {
        return DESTINATION_JD_GAME_M.equals(getDestination());
    }

    public boolean isWantedToLottery() {
        return DESTINATION_LOTTERY_M.equals(getDestination());
    }

    public boolean isWantedToQqRecharge() {
        return DESTINATION_QQ_RECHARGE.equals(getDestination());
    }

    public boolean isWantedToRecharge() {
        return DESTINATION_RECHARGE_M.equals(getDestination());
    }

    public boolean isWantedToSearchProduct() {
        return !TextUtils.isEmpty(getSearchKey());
    }

    public String listToString(List list, char c2) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < list.size(); i2++) {
            sb.append(list.get(i2));
            sb.append(c2);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    public void setChildCategories(ArrayList<Catelogy> arrayList) {
        this.childCategories = arrayList;
    }

    public void setImage(String str, String str2) {
        this.imageList.add(new Image(str, str2));
    }

    public void setImageList(List<Image> list) {
        this.imageList = list;
    }

    public void setLevelFourList(List<CatelogyLevelFour> list) {
        this.levelFourList = list;
    }

    public final String toString() {
        return "cId = " + this.cId + ", name = " + this.name + ", level = " + this.level + ", showTab = " + this.showTab + "\r\n";
    }

    /* loaded from: classes5.dex */
    public static class MergedCatelogy implements Serializable {
        public String Id;
        public String name;
        public int order;

        public MergedCatelogy(JSONObjectProxy jSONObjectProxy) {
            this.Id = jSONObjectProxy.optString("id");
            this.name = jSONObjectProxy.optString("name");
            this.order = jSONObjectProxy.optInt("order");
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x0043  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static java.util.ArrayList<com.jingdong.common.entity.Catelogy.MergedCatelogy> toList(com.jingdong.jdsdk.utils.JSONArrayPoxy r4) {
            /*
                r0 = 0
                if (r4 != 0) goto L4
                return r0
            L4:
                java.util.ArrayList r1 = new java.util.ArrayList     // Catch: org.json.JSONException -> L34
                r1.<init>()     // Catch: org.json.JSONException -> L34
                r0 = 0
            La:
                int r2 = r4.length()     // Catch: org.json.JSONException -> L31
                if (r0 >= r2) goto L3f
                com.jingdong.jdsdk.utils.JSONObjectProxy r2 = r4.getJSONObject(r0)     // Catch: org.json.JSONException -> L31
                if (r2 == 0) goto L2e
                com.jingdong.jdsdk.utils.JSONObjectProxy r2 = r4.getJSONObject(r0)     // Catch: org.json.JSONException -> L31
                java.lang.String r3 = "name"
                boolean r2 = r2.isNull(r3)     // Catch: org.json.JSONException -> L31
                if (r2 != 0) goto L2e
                com.jingdong.common.entity.Catelogy$MergedCatelogy r2 = new com.jingdong.common.entity.Catelogy$MergedCatelogy     // Catch: org.json.JSONException -> L31
                com.jingdong.jdsdk.utils.JSONObjectProxy r3 = r4.getJSONObject(r0)     // Catch: org.json.JSONException -> L31
                r2.<init>(r3)     // Catch: org.json.JSONException -> L31
                r1.add(r2)     // Catch: org.json.JSONException -> L31
            L2e:
                int r0 = r0 + 1
                goto La
            L31:
                r4 = move-exception
                r0 = r1
                goto L35
            L34:
                r4 = move-exception
            L35:
                boolean r1 = com.jingdong.sdk.oklog.OKLog.E
                if (r1 == 0) goto L3e
                java.lang.String r1 = "Ware"
                com.jingdong.sdk.oklog.OKLog.e(r1, r4)
            L3e:
                r1 = r0
            L3f:
                boolean r4 = com.jingdong.sdk.oklog.OKLog.D
                if (r4 == 0) goto L59
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                java.lang.String r0 = "list:"
                r4.append(r0)
                r4.append(r1)
                java.lang.String r4 = r4.toString()
                java.lang.String r0 = "Catelogy"
                com.jingdong.sdk.oklog.OKLog.d(r0, r4)
            L59:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.entity.Catelogy.MergedCatelogy.toList(com.jingdong.jdsdk.utils.JSONArrayPoxy):java.util.ArrayList");
        }

        public String getName() {
            return TextUtils.isEmpty(this.name) ? "" : this.name;
        }

        public final String toString() {
            return "Id = " + this.Id + ", name = " + this.name + ", order = " + this.order + "\r\n";
        }

        public MergedCatelogy() {
        }
    }

    public Catelogy(JSONObjectProxy jSONObjectProxy, int i2) {
        this.imageList = new LinkedList();
        this.mergeCatalogs = null;
        this.showTab = false;
        this.isIndividual = false;
        if (i2 == 0) {
            this.cId = jSONObjectProxy.optString("cid");
            this.level = jSONObjectProxy.optInt("level");
            this.name = jSONObjectProxy.optString("name");
            JSONArrayPoxy jSONArrayOrNull = jSONObjectProxy.getJSONArrayOrNull("mergeCatalogs");
            if (jSONArrayOrNull != null) {
                this.mergeCatalogs = MergedCatelogy.toList(jSONArrayOrNull);
            } else {
                this.mergeCatalogs = null;
            }
            this.showTab = jSONObjectProxy.optBoolean("showTab");
            this.isIndividual = jSONObjectProxy.optBoolean("isIndividual");
        } else if (i2 == 1) {
            this.cId = jSONObjectProxy.optString("cid");
            this.name = jSONObjectProxy.optString("name");
            this.wareNumber = Integer.valueOf(jSONObjectProxy.optInt("wareNumber"));
            this.field = jSONObjectProxy.optString("filed");
            doCatelogyArray(jSONObjectProxy.getJSONArrayOrNull("childs"));
            this.headerCatelogy = new Catelogy();
            this.headerCatelogy = this;
        } else if (i2 == 3) {
            if (jSONObjectProxy != null) {
                this.name = jSONObjectProxy.optString("name");
                this.imgUrl = jSONObjectProxy.optString("icon");
                this.cId = jSONObjectProxy.optString("cid");
                this.originCid = jSONObjectProxy.optString("originCid");
                this.isMerger = jSONObjectProxy.optBoolean("isMerger");
                this.isIndividual = jSONObjectProxy.optBoolean("isIndividual");
                this.labelJsonArray = jSONObjectProxy.getJSONArrayOrNull(Constant.KEY_PROMOTION_LABEL);
                int optInt = jSONObjectProxy.optInt("virtualFlag");
                this.virtualFlag = optInt;
                if (optInt != 0) {
                    if (optInt == 1) {
                        this.searchKey = jSONObjectProxy.optString("searchKey");
                    } else if (optInt == 2) {
                        this.shopId = Long.valueOf(jSONObjectProxy.optLong("shopId"));
                    }
                }
                this.action = jSONObjectProxy.optString("action");
                this.destination = jSONObjectProxy.optString("destination");
                this.path = jSONObjectProxy.optString("path");
                if (jSONObjectProxy.optInt("YNlevelF", 0) == 0) {
                    this.hasLevelFour = false;
                } else {
                    this.hasLevelFour = true;
                }
                JSONArrayPoxy jSONArrayOrNull2 = jSONObjectProxy.getJSONArrayOrNull("level_f");
                this.levelFourJsonArray = jSONArrayOrNull2;
                if (jSONArrayOrNull2 != null) {
                    this.levelFourList = getLevelFourList(jSONArrayOrNull2);
                } else {
                    this.levelFourList = null;
                }
                try {
                    this.expandPropertysList = JDJSON.parseArray(new JSONArray(jSONObjectProxy.optString("expandPropertys")).toString(), ExpandPropertys.class);
                } catch (Exception e2) {
                    if (OKLog.E) {
                        OKLog.e(TAG, "expandPropertys", e2);
                    }
                }
                this.isCatalogMiddlePage = jSONObjectProxy.optBoolean("isCatalogMiddlePage");
                translateExpandAttrsFormat(this.expandPropertysList);
                setExpandAtttributes(this.labelJsonArray);
            }
        } else if (i2 == 4 && jSONObjectProxy != null) {
            String optString = jSONObjectProxy.optString("path");
            this.path = optString;
            if (!TextUtils.isEmpty(optString)) {
                String[] split = this.path.split(CartConstant.KEY_YB_INFO_LINK);
                this.level1Cid = split[0];
                this.level2Cid = split[1];
                this.level3Cid = split[2];
            }
            this.cId = jSONObjectProxy.optString("cid");
            this.originCid = jSONObjectProxy.optString("originCid");
            this.name = jSONObjectProxy.optString("name");
            this.imgUrl = jSONObjectProxy.optString("icon");
            this.columNum = jSONObjectProxy.optInt("columNum");
            this.virtualFlag = jSONObjectProxy.optInt("virtualFlag");
            this.isMerger = jSONObjectProxy.optBoolean("isMerger");
            this.isIndividual = jSONObjectProxy.optBoolean("isIndividual");
            int i3 = this.virtualFlag;
            if (i3 != 0) {
                if (i3 == 1) {
                    this.searchKey = jSONObjectProxy.optString("searchKey");
                } else if (i3 == 2) {
                    this.shopId = Long.valueOf(jSONObjectProxy.optLong("shopId"));
                }
            }
            this.action = jSONObjectProxy.optString("action");
            this.destination = jSONObjectProxy.optString("destination");
            this.sensitiveFlag = jSONObjectProxy.optInt("sensitiveFlag");
            if (jSONObjectProxy.optInt("YNlevelF", 0) == 0) {
                this.hasLevelFour = false;
            } else {
                this.hasLevelFour = true;
            }
            JSONArrayPoxy jSONArrayOrNull3 = jSONObjectProxy.getJSONArrayOrNull("level_f");
            this.levelFourJsonArray = jSONArrayOrNull3;
            this.levelFourList = getLevelFourList(jSONArrayOrNull3);
            this.labelJsonArray = jSONObjectProxy.getJSONArrayOrNull(Constant.KEY_PROMOTION_LABEL);
            try {
                this.expandPropertysList = JDJSON.parseArray(new JSONArray(jSONObjectProxy.optString("expandPropertys")).toString(), ExpandPropertys.class);
            } catch (Exception e3) {
                if (OKLog.E) {
                    OKLog.e(TAG, "expandPropertys", e3);
                }
            }
            this.isCatalogMiddlePage = jSONObjectProxy.optBoolean("isCatalogMiddlePage");
            translateExpandAttrsFormat(this.expandPropertysList);
            setExpandAtttributes(this.labelJsonArray);
        }
    }

    public List<CatelogyLevelFour> getLevelFourList() {
        return this.levelFourList;
    }
}
