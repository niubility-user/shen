package com.jingdong.common.stickyListView;

import android.util.SparseIntArray;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class StickyHeaderData {
    private SparseIntArray dataToRealMap;
    private List<Integer> headerList;
    private List<String> letterList;
    private SparseIntArray realToDataMap;
    private Map<String, Integer> strToPosMap;

    private int findLetterIndex(String str) {
        List<String> list = this.letterList;
        if (list == null) {
            return -1;
        }
        return list.indexOf(str);
    }

    public void addHeaderPos(int i2) {
        if (this.headerList == null) {
            this.headerList = new ArrayList();
        }
        this.headerList.add(Integer.valueOf(i2));
    }

    public void addLetter(String str) {
        if (this.letterList == null) {
            this.letterList = new ArrayList();
        }
        int findLetterIndex = findLetterIndex(str);
        this.letterList.add(str);
        if (findLetterIndex != -1) {
            this.letterList.remove(findLetterIndex);
        }
    }

    public SparseIntArray getDataToRealMap() {
        SparseIntArray sparseIntArray = this.dataToRealMap;
        return sparseIntArray == null ? new SparseIntArray() : sparseIntArray;
    }

    public List<Integer> getHeaderList() {
        List<Integer> list = this.headerList;
        return list == null ? new ArrayList() : list;
    }

    public List<String> getLetterList() {
        List<String> list = this.letterList;
        return list == null ? new ArrayList() : list;
    }

    public SparseIntArray getRealToDataMap() {
        SparseIntArray sparseIntArray = this.realToDataMap;
        return sparseIntArray == null ? new SparseIntArray() : sparseIntArray;
    }

    public Map<String, Integer> getStrToPosMap() {
        Map<String, Integer> map = this.strToPosMap;
        return map == null ? new HashMap() : map;
    }

    public void putDataToReal(int i2, int i3) {
        if (this.dataToRealMap == null) {
            this.dataToRealMap = new SparseIntArray();
        }
        this.dataToRealMap.put(i2, i3);
    }

    public void putRealToData(int i2, int i3) {
        if (this.realToDataMap == null) {
            this.realToDataMap = new SparseIntArray();
        }
        this.realToDataMap.put(i2, i3);
    }

    public void putStrToPos(String str, Integer num) {
        if (this.strToPosMap == null) {
            this.strToPosMap = new HashMap();
        }
        this.strToPosMap.put(str, num);
    }

    public void release() {
        Map<String, Integer> map = this.strToPosMap;
        if (map != null) {
            map.clear();
            this.strToPosMap = null;
        }
        SparseIntArray sparseIntArray = this.realToDataMap;
        if (sparseIntArray != null) {
            sparseIntArray.clear();
            this.realToDataMap = null;
        }
        SparseIntArray sparseIntArray2 = this.dataToRealMap;
        if (sparseIntArray2 != null) {
            sparseIntArray2.clear();
            this.dataToRealMap = null;
        }
        List<Integer> list = this.headerList;
        if (list != null) {
            list.clear();
            this.headerList = null;
        }
        List<String> list2 = this.letterList;
        if (list2 != null) {
            list2.clear();
            this.letterList = null;
        }
    }
}
