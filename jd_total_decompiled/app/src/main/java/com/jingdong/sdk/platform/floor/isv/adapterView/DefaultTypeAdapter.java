package com.jingdong.sdk.platform.floor.isv.adapterView;

import java.util.HashMap;

/* loaded from: classes10.dex */
class DefaultTypeAdapter implements ITypeAdapter {
    protected int extendBaseLine = 0;
    public HashMap<String, Integer> extendKey2TypeList = new HashMap<>();
    public HashMap<Integer, String> extendType2KeyList = new HashMap<>();

    @Override // com.jingdong.sdk.platform.floor.isv.adapterView.ITypeAdapter
    public boolean hasType(int i2) {
        return this.extendType2KeyList.containsKey(Integer.valueOf(i2));
    }

    @Override // com.jingdong.sdk.platform.floor.isv.adapterView.ITypeAdapter
    public int key2Type(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return -1;
        }
        String str = strArr[0];
        if (this.extendKey2TypeList.containsKey(str)) {
            return this.extendKey2TypeList.get(str).intValue();
        }
        int i2 = this.extendBaseLine + 1;
        this.extendBaseLine = i2;
        this.extendKey2TypeList.put(str, Integer.valueOf(i2));
        this.extendType2KeyList.put(Integer.valueOf(this.extendBaseLine), str);
        return this.extendBaseLine;
    }

    @Override // com.jingdong.sdk.platform.floor.isv.adapterView.ITypeAdapter
    public String type2Key(int i2) {
        return this.extendType2KeyList.get(Integer.valueOf(i2));
    }
}
