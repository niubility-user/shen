package com.jingdong.common.unification.navigationbar;

/* loaded from: classes6.dex */
public class NavigationMaterialShowEntity {
    public int combination_type;
    public String material_id;
    public String material_position;
    public long material_show_time;
    public int material_type;

    public int getCombination_type() {
        return this.combination_type;
    }

    public String getMaterial_id() {
        return this.material_id;
    }

    public String getMaterial_position() {
        return this.material_position;
    }

    public long getMaterial_show_time() {
        return this.material_show_time;
    }

    public int getMaterial_type() {
        return this.material_type;
    }

    public void setCombination_type(int i2) {
        this.combination_type = i2;
    }

    public void setMaterial_id(String str) {
        this.material_id = str;
    }

    public void setMaterial_position(String str) {
        this.material_position = str;
    }

    public void setMaterial_show_time(long j2) {
        this.material_show_time = j2;
    }

    public void setMaterial_type(int i2) {
        this.material_type = i2;
    }

    public String toString() {
        return "NavigationMaterialEntity{material_id='" + this.material_id + "', material_type=" + this.material_type + ", combination_type=" + this.combination_type + ", material_position='" + this.material_position + "', material_show_time=" + this.material_show_time + '}';
    }
}
