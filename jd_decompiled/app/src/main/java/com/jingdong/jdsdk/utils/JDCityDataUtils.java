package com.jingdong.jdsdk.utils;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.UnLog;
import com.jingdong.common.stickyListView.StickyHeaderData;
import com.jingdong.common.ui.address.SelectAddressItemEntity;
import com.jingdong.common.ui.address.entity.AreaBeanVO;
import com.jingdong.common.ui.address.entity.PinYinBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes14.dex */
public class JDCityDataUtils {
    private static final String TAG = "JDCityDataUtils";
    private static Map<String, Boolean> letterCache;
    private static HashMap<String, String> mMultiPinYinMap;
    private static Map<String, String> pinyinCache;

    /* loaded from: classes14.dex */
    static class a implements Comparator<String> {
        a() {
        }

        @Override // java.util.Comparator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public int compare(String str, String str2) {
            String convertToPinyin = JDCityDataUtils.convertToPinyin(str);
            String convertToPinyin2 = JDCityDataUtils.convertToPinyin(str2);
            boolean isLetter = JDCityDataUtils.isLetter(convertToPinyin);
            boolean isLetter2 = JDCityDataUtils.isLetter(convertToPinyin2);
            if (isLetter && isLetter2) {
                return convertToPinyin.compareTo(convertToPinyin2);
            }
            if (!isLetter || isLetter2) {
                return (isLetter || !isLetter2) ? 0 : 1;
            }
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static class b implements Comparator<AreaBeanVO> {
        b() {
        }

        @Override // java.util.Comparator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public int compare(AreaBeanVO areaBeanVO, AreaBeanVO areaBeanVO2) {
            String convertToPinyin = JDCityDataUtils.convertToPinyin(areaBeanVO.getName());
            String convertToPinyin2 = JDCityDataUtils.convertToPinyin(areaBeanVO2.getName());
            boolean isLetter = JDCityDataUtils.isLetter(convertToPinyin);
            boolean isLetter2 = JDCityDataUtils.isLetter(convertToPinyin2);
            if (isLetter && isLetter2) {
                return convertToPinyin.compareTo(convertToPinyin2);
            }
            if (!isLetter || isLetter2) {
                return (isLetter || !isLetter2) ? 0 : 1;
            }
            return -1;
        }
    }

    private static boolean checkParams(SelectAddressItemEntity[] selectAddressItemEntityArr, StickyHeaderData stickyHeaderData) {
        return (selectAddressItemEntityArr == null || stickyHeaderData == null) ? false : true;
    }

    private static boolean checkParams(String[] strArr, StickyHeaderData stickyHeaderData) {
        return (strArr == null || stickyHeaderData == null) ? false : true;
    }

    private static boolean checkParamsAreaCode(AreaBeanVO[] areaBeanVOArr, StickyHeaderData stickyHeaderData) {
        return (areaBeanVOArr == null || stickyHeaderData == null) ? false : true;
    }

    public static String convertToPinyin(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String pinYinFromMultiMap = getPinYinFromMultiMap(str);
        if (!TextUtils.isEmpty(pinYinFromMultiMap)) {
            if (UnLog.D) {
                UnLog.d(TAG, "tmpPinYin = " + pinYinFromMultiMap);
            }
            return pinYinFromMultiMap.toUpperCase();
        }
        return toHanyuPinyin(str);
    }

    public static void getMultiPinYinMap() {
        String str;
        if (mMultiPinYinMap == null) {
            mMultiPinYinMap = new HashMap<>();
            try {
                str = JDMobileConfig.getInstance().getConfig("unification", "addressWidgetConfig", "pinyinList");
            } catch (Exception e2) {
                e2.printStackTrace();
                str = "";
            }
            if (UnLog.D) {
                UnLog.d(TAG, "multiPinYinStr = " + str);
            }
            if (TextUtils.isEmpty(str)) {
                return;
            }
            List<PinYinBean> parseArray = JDJSON.parseArray(str, PinYinBean.class);
            if (UnLog.D) {
                UnLog.d(TAG, "multiPinYinSize = " + parseArray.size());
            }
            if (mMultiPinYinMap == null || parseArray == null || parseArray.size() <= 0) {
                return;
            }
            for (PinYinBean pinYinBean : parseArray) {
                if (pinYinBean != null) {
                    mMultiPinYinMap.put(pinYinBean.getCharacter(), pinYinBean.getPinyin());
                }
            }
        }
    }

    private static String getPinYinFromMultiMap(String str) {
        HashMap<String, String> hashMap;
        return (TextUtils.isEmpty(str) || (hashMap = mMultiPinYinMap) == null || hashMap.size() <= 0) ? "" : mMultiPinYinMap.get(str);
    }

    private static AreaBeanVO[] insertAreaCodeHeaderToArray(AreaBeanVO[] areaBeanVOArr, StickyHeaderData stickyHeaderData) {
        if (checkParamsAreaCode(areaBeanVOArr, stickyHeaderData)) {
            stickyHeaderData.release();
            ArrayList arrayList = new ArrayList();
            char c2 = 0;
            char c3 = 0;
            for (int i2 = 0; i2 < areaBeanVOArr.length; i2++) {
                String convertToPinyin = convertToPinyin(areaBeanVOArr[i2].getName());
                if (!TextUtils.isEmpty(convertToPinyin)) {
                    c2 = convertToPinyin.charAt(0);
                }
                if (!isLetter(c2 + "")) {
                    c2 = '#';
                }
                if (c2 != c3) {
                    AreaBeanVO areaBeanVO = new AreaBeanVO();
                    areaBeanVO.setName(c2 + "");
                    arrayList.add(areaBeanVO);
                    int size = arrayList.size() - 1;
                    String str = c2 + "";
                    stickyHeaderData.putStrToPos(str, Integer.valueOf(size));
                    stickyHeaderData.addHeaderPos(size);
                    stickyHeaderData.addLetter(str);
                    c3 = c2;
                }
                arrayList.add(areaBeanVOArr[i2]);
                int size2 = arrayList.size() - 1;
                stickyHeaderData.putRealToData(size2, i2);
                stickyHeaderData.putDataToReal(i2, size2);
            }
            return listToArrayEntityAreaCode(arrayList);
        }
        return areaBeanVOArr;
    }

    private static SelectAddressItemEntity[] insertHeaderToArray(SelectAddressItemEntity[] selectAddressItemEntityArr, StickyHeaderData stickyHeaderData) {
        if (checkParams(selectAddressItemEntityArr, stickyHeaderData)) {
            stickyHeaderData.release();
            ArrayList arrayList = new ArrayList();
            char c2 = 0;
            char c3 = 0;
            for (int i2 = 0; i2 < selectAddressItemEntityArr.length; i2++) {
                String convertToPinyin = convertToPinyin(selectAddressItemEntityArr[i2].content);
                if (!TextUtils.isEmpty(convertToPinyin)) {
                    c2 = convertToPinyin.charAt(0);
                }
                if (!isLetter(c2 + "")) {
                    c2 = '#';
                }
                if (c2 != c3) {
                    SelectAddressItemEntity selectAddressItemEntity = new SelectAddressItemEntity();
                    selectAddressItemEntity.content = c2 + "";
                    arrayList.add(selectAddressItemEntity);
                    int size = arrayList.size() - 1;
                    String str = c2 + "";
                    stickyHeaderData.putStrToPos(str, Integer.valueOf(size));
                    stickyHeaderData.addHeaderPos(size);
                    stickyHeaderData.addLetter(str);
                    c3 = c2;
                }
                arrayList.add(selectAddressItemEntityArr[i2]);
                int size2 = arrayList.size() - 1;
                stickyHeaderData.putRealToData(size2, i2);
                stickyHeaderData.putDataToReal(i2, size2);
            }
            return listToArrayEntity(arrayList);
        }
        return selectAddressItemEntityArr;
    }

    public static boolean isLetter(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (letterCache == null) {
            letterCache = new HashMap();
        }
        Boolean bool = letterCache.get(str);
        if (bool == null) {
            bool = Boolean.TRUE;
            letterCache.put(str, bool);
        }
        return bool.booleanValue();
    }

    private static String[] listToArray(List<String> list) {
        String[] strArr = new String[1];
        if (list != null && !list.isEmpty()) {
            strArr = new String[list.size()];
            for (int i2 = 0; i2 < list.size(); i2++) {
                strArr[i2] = list.get(i2);
            }
        }
        return strArr;
    }

    private static SelectAddressItemEntity[] listToArrayEntity(List<SelectAddressItemEntity> list) {
        SelectAddressItemEntity[] selectAddressItemEntityArr = new SelectAddressItemEntity[1];
        if (list != null && !list.isEmpty()) {
            selectAddressItemEntityArr = new SelectAddressItemEntity[list.size()];
            for (int i2 = 0; i2 < list.size(); i2++) {
                selectAddressItemEntityArr[i2] = list.get(i2);
            }
        }
        return selectAddressItemEntityArr;
    }

    private static AreaBeanVO[] listToArrayEntityAreaCode(List<AreaBeanVO> list) {
        AreaBeanVO[] areaBeanVOArr = new AreaBeanVO[1];
        if (list != null && !list.isEmpty()) {
            areaBeanVOArr = new AreaBeanVO[list.size()];
            for (int i2 = 0; i2 < list.size(); i2++) {
                areaBeanVOArr[i2] = list.get(i2);
            }
        }
        return areaBeanVOArr;
    }

    public static void release() {
        Map<String, String> map = pinyinCache;
        if (map != null) {
            map.clear();
            pinyinCache = null;
        }
        Map<String, Boolean> map2 = letterCache;
        if (map2 != null) {
            map2.clear();
            letterCache = null;
        }
        HashMap<String, String> hashMap = mMultiPinYinMap;
        if (hashMap != null) {
            hashMap.clear();
            mMultiPinYinMap = null;
        }
    }

    private static void sortCityArray(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return;
        }
        Arrays.sort(strArr, new a());
    }

    private static void sortCityArrayAreaCode(AreaBeanVO[] areaBeanVOArr) {
        if (areaBeanVOArr == null || areaBeanVOArr.length == 0) {
            return;
        }
        Arrays.sort(areaBeanVOArr, new b());
    }

    public static String toHanyuPinyin(String str) {
        try {
            char[] charArray = str.trim().toCharArray();
            return (charArray == null || charArray.length <= 0) ? "" : g.a.a.a.a.d(charArray[0]).toUpperCase();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String[] updateData(String[] strArr, StickyHeaderData stickyHeaderData) {
        return insertHeaderToArray(strArr, stickyHeaderData);
    }

    public static AreaBeanVO[] updateDataAreaCode(AreaBeanVO[] areaBeanVOArr, StickyHeaderData stickyHeaderData) {
        sortCityArrayAreaCode(areaBeanVOArr);
        return insertAreaCodeHeaderToArray(areaBeanVOArr, stickyHeaderData);
    }

    public static SelectAddressItemEntity[] updateData(SelectAddressItemEntity[] selectAddressItemEntityArr, StickyHeaderData stickyHeaderData) {
        return insertHeaderToArray(selectAddressItemEntityArr, stickyHeaderData);
    }

    private static String[] insertHeaderToArray(String[] strArr, StickyHeaderData stickyHeaderData) {
        if (checkParams(strArr, stickyHeaderData)) {
            stickyHeaderData.release();
            ArrayList arrayList = new ArrayList();
            char c2 = 0;
            char c3 = 0;
            for (int i2 = 0; i2 < strArr.length; i2++) {
                String str = strArr[i2];
                String convertToPinyin = convertToPinyin(str);
                if (!TextUtils.isEmpty(convertToPinyin)) {
                    c2 = convertToPinyin.charAt(0);
                }
                if (!isLetter(c2 + "")) {
                    c2 = '#';
                }
                if (c2 != c3) {
                    arrayList.add(c2 + "");
                    int size = arrayList.size() - 1;
                    String str2 = c2 + "";
                    stickyHeaderData.putStrToPos(str2, Integer.valueOf(size));
                    stickyHeaderData.addHeaderPos(size);
                    stickyHeaderData.addLetter(str2);
                    c3 = c2;
                }
                arrayList.add(str);
                int size2 = arrayList.size() - 1;
                stickyHeaderData.putRealToData(size2, i2);
                stickyHeaderData.putDataToReal(i2, size2);
            }
            return listToArray(arrayList);
        }
        return strArr;
    }
}
