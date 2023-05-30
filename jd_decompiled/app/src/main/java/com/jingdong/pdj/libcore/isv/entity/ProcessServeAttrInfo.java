package com.jingdong.pdj.libcore.isv.entity;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes7.dex */
public class ProcessServeAttrInfo implements Serializable {
    public List<AttrInfos> attrInfos;
    public int attrType;

    public String getAlertText() {
        StringBuilder sb = new StringBuilder();
        List<AttrInfos> list = this.attrInfos;
        if (list != null && list.size() > 0) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.attrInfos.size()) {
                    break;
                }
                AttrInfos attrInfos = this.attrInfos.get(i2);
                if (!attrInfos.havaSelectService()) {
                    sb.append("\u8bf7\u9009\u62e9");
                    sb.append(attrInfos.attName);
                    break;
                }
                i2++;
            }
        }
        return sb.toString();
    }

    public String getAllUnSelectText(boolean z) {
        StringBuilder sb = new StringBuilder();
        List<AttrInfos> list = this.attrInfos;
        if (list != null && list.size() > 0) {
            if (z) {
                sb.append("\u8bf7\u9009\u62e9");
            }
            for (int i2 = 0; i2 < this.attrInfos.size(); i2++) {
                AttrInfos attrInfos = this.attrInfos.get(i2);
                if (!attrInfos.havaSelectService()) {
                    sb.append(attrInfos.attName);
                    if (i2 < this.attrInfos.size() - 1) {
                        sb.append("\u3001");
                    }
                }
            }
        }
        if (sb.length() > 0 && sb.toString().endsWith("\u3001")) {
            return sb.subSequence(0, sb.length() - 1).toString();
        }
        return sb.toString();
    }

    public String getHourlyAttribute() {
        List<ServeAttributeValues> list;
        StringBuilder sb = new StringBuilder("");
        List<AttrInfos> list2 = this.attrInfos;
        if (list2 != null && list2.size() > 0) {
            for (int i2 = 0; i2 < this.attrInfos.size(); i2++) {
                AttrInfos attrInfos = this.attrInfos.get(i2);
                if (attrInfos != null && (list = attrInfos.attValueList) != null && list.size() > 0) {
                    int i3 = 0;
                    while (true) {
                        if (i3 >= attrInfos.attValueList.size()) {
                            break;
                        } else if (attrInfos.attValueList.get(i3).isSelect) {
                            if (i2 > 0) {
                                sb.append("#");
                            }
                            sb.append(attrInfos.attId);
                            sb.append("-");
                            sb.append(attrInfos.attValueList.get(i3).attValId);
                        } else {
                            i3++;
                        }
                    }
                }
            }
        }
        return sb.toString();
    }

    public String getHourlyAttributeText() {
        List<ServeAttributeValues> list;
        StringBuilder sb = new StringBuilder();
        List<AttrInfos> list2 = this.attrInfos;
        if (list2 != null && list2.size() > 0) {
            for (int i2 = 0; i2 < this.attrInfos.size(); i2++) {
                AttrInfos attrInfos = this.attrInfos.get(i2);
                if (attrInfos != null && (list = attrInfos.attValueList) != null && list.size() > 0) {
                    int i3 = 0;
                    while (true) {
                        if (i3 >= attrInfos.attValueList.size()) {
                            break;
                        } else if (attrInfos.attValueList.get(i3).isSelect) {
                            if (i2 > 0 && sb.length() > 0) {
                                sb.append("\uff0c");
                            }
                            sb.append(attrInfos.attValueList.get(i3).attValName);
                        } else {
                            i3++;
                        }
                    }
                }
            }
        }
        return sb.toString();
    }
}
