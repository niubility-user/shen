package com.jingdong.wireless.iconfont.widget;

import com.jingdong.wireless.iconfont.Icon;

/* loaded from: classes12.dex */
public class IconImpl implements Icon {
    private String character;
    private String key;

    public IconImpl(String str, String str2) {
        this.key = str;
        this.character = str2;
    }

    @Override // com.jingdong.wireless.iconfont.Icon
    public String character() {
        return this.character;
    }

    @Override // com.jingdong.wireless.iconfont.Icon
    public String key() {
        return this.key;
    }

    public void setCharacter(String str) {
        this.character = str;
    }

    public void setKey(String str) {
        this.key = str;
    }
}
