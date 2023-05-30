package com.jingdong.common.lbs.businessAddress;

import android.text.TextUtils;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.lbs.http.JDLbsHttpError;
import com.jingdong.common.lbs.http.JDLbsHttpOption;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.jdsdk.res.StringUtil;

/* loaded from: classes5.dex */
public class JDGlobalAddressManager {
    public static AddressGlobal createGlobalWithJDBusinessAddress(JDLbsHttpOption jDLbsHttpOption, JDBusinessAddress jDBusinessAddress) {
        try {
            if (jDBusinessAddress == null) {
                return new AddressGlobal();
            }
            AddressGlobal addressGlobal = new AddressGlobal();
            addressGlobal.setId(jDBusinessAddress.getAddressID());
            addressGlobal.setIdProvince(jDBusinessAddress.getProvinceCode());
            addressGlobal.setIdCity(jDBusinessAddress.getCityCode());
            addressGlobal.setIdArea(jDBusinessAddress.getDistrictCode());
            addressGlobal.setIdTown(jDBusinessAddress.getTownCode());
            addressGlobal.setWhere(jDBusinessAddress.getFullAddress());
            addressGlobal.setAddressDetail(jDBusinessAddress.getDetailAddress());
            addressGlobal.setName(jDBusinessAddress.getName());
            addressGlobal.setLatitude(String.valueOf(jDBusinessAddress.getLat()));
            addressGlobal.setLongitude(String.valueOf(jDBusinessAddress.getLng()));
            addressGlobal.setProvinceName(jDBusinessAddress.getProvince());
            addressGlobal.setCityName(jDBusinessAddress.getCity());
            addressGlobal.setAreaName(jDBusinessAddress.getDistrict());
            addressGlobal.setTownName(jDBusinessAddress.getTown());
            addressGlobal.setIsUserAddress(Boolean.valueOf(jDBusinessAddress.getAddressID() != 0));
            addressGlobal.setAddressType(getAddressType(jDBusinessAddress));
            addressGlobal.setAddressTitle(jDBusinessAddress.getAddressTitle());
            addressGlobal.setSaveBusiness(jDBusinessAddress.getSaveBusiness());
            addressGlobal.setSource(jDBusinessAddress.getSource());
            return addressGlobal;
        } catch (Exception e2) {
            e2.printStackTrace();
            return new AddressGlobal();
        }
    }

    public static AddressGlobal createGlobalWithJDLocation(JDLbsHttpOption jDLbsHttpOption, JDLocation jDLocation) {
        try {
            if (jDLocation == null) {
                return new AddressGlobal();
            }
            AddressGlobal addressGlobal = new AddressGlobal();
            addressGlobal.setId(0L);
            addressGlobal.setIdProvince(jDLocation.getProvinceId());
            addressGlobal.setIdCity(jDLocation.getCityId());
            addressGlobal.setIdArea(jDLocation.getDistrictId());
            addressGlobal.setIdTown(jDLocation.getTownId());
            addressGlobal.setWhere(jDLocation.getProvinceName() + jDLocation.getCityName() + jDLocation.getDistrictName() + jDLocation.getTownName() + jDLocation.getDetailAddress());
            addressGlobal.setAddressDetail(jDLocation.getDetailAddress());
            addressGlobal.setLatitude(String.valueOf(jDLocation.getLat()));
            addressGlobal.setLongitude(String.valueOf(jDLocation.getLng()));
            addressGlobal.setProvinceName(jDLocation.getProvinceName());
            addressGlobal.setCityName(jDLocation.getCityName());
            addressGlobal.setAreaName(jDLocation.getDistrictName());
            addressGlobal.setTownName(jDLocation.getTownName());
            addressGlobal.setIsUserAddress(Boolean.FALSE);
            addressGlobal.setAddressType(getAddressType(jDLocation));
            addressGlobal.setAddressTitle("");
            addressGlobal.setSaveBusiness("");
            addressGlobal.setSource("");
            return addressGlobal;
        } catch (Exception e2) {
            e2.printStackTrace();
            return new AddressGlobal();
        }
    }

    public static AddressGlobal createGlobalWithJDYFAddress(JDLbsHttpOption jDLbsHttpOption, JDYFAddress jDYFAddress) {
        try {
            if (jDYFAddress == null) {
                return new AddressGlobal();
            }
            AddressGlobal addressGlobal = new AddressGlobal();
            addressGlobal.setId(jDYFAddress.getAddressID());
            addressGlobal.setIdProvince((int) jDYFAddress.getProvinceCode());
            addressGlobal.setIdCity((int) jDYFAddress.getCityCode());
            addressGlobal.setIdArea((int) jDYFAddress.getDistrictCode());
            addressGlobal.setIdTown((int) jDYFAddress.getTownCode());
            addressGlobal.setWhere(jDYFAddress.getFullAddress());
            addressGlobal.setAddressDetail(jDYFAddress.getDetailAddress());
            addressGlobal.setLatitude(String.valueOf(jDYFAddress.getLat()));
            addressGlobal.setLongitude(String.valueOf(jDYFAddress.getLng()));
            addressGlobal.setProvinceName(jDYFAddress.getProvince());
            addressGlobal.setCityName(jDYFAddress.getCity());
            addressGlobal.setAreaName(jDYFAddress.getDistrict());
            addressGlobal.setTownName(jDYFAddress.getTown());
            addressGlobal.setIsUserAddress(Boolean.valueOf(jDYFAddress.getAddressID() != 0));
            addressGlobal.setAddressType(getAddressType(jDYFAddress));
            addressGlobal.setAddressTitle(jDYFAddress.getAddressTitle());
            addressGlobal.setSaveBusiness(jDYFAddress.getSaveBusiness());
            addressGlobal.setSource(jDYFAddress.getSource());
            return addressGlobal;
        } catch (Exception e2) {
            e2.printStackTrace();
            return new AddressGlobal();
        }
    }

    public static JDBusinessAddress createJDBusinessAddressWithGlobal(JDLbsHttpOption jDLbsHttpOption) {
        if (jDLbsHttpOption == null) {
            try {
                jDLbsHttpOption = new JDLbsHttpOption();
            } catch (Exception e2) {
                e2.printStackTrace();
                return new JDBusinessAddress();
            }
        }
        JDBusinessAddress jDBusinessAddress = new JDBusinessAddress();
        AddressGlobal addressGlobal = getAddressGlobal(jDLbsHttpOption);
        if (addressGlobal != null) {
            jDBusinessAddress.setAddressID(addressGlobal.getId());
            jDBusinessAddress.setProvinceCode(addressGlobal.getIdProvince());
            jDBusinessAddress.setCityCode(addressGlobal.getIdCity());
            jDBusinessAddress.setDistrictCode(addressGlobal.getIdArea());
            jDBusinessAddress.setTownCode(addressGlobal.getIdTown());
            jDBusinessAddress.setFullAddress(addressGlobal.getWhere());
            jDBusinessAddress.setDetailAddress(addressGlobal.getAddressDetail());
            jDBusinessAddress.setName(addressGlobal.getName());
            jDBusinessAddress.setAddressName(TextUtils.isEmpty(addressGlobal.getAddressDetail()) ? addressGlobal.getWhere() : addressGlobal.getAddressDetail());
            jDBusinessAddress.setMobile(addressGlobal.getMobile());
            try {
                jDBusinessAddress.setLng(Double.parseDouble(addressGlobal.getLongitude()));
                jDBusinessAddress.setLat(Double.parseDouble(addressGlobal.getLatitude()));
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            jDBusinessAddress.setProvince(addressGlobal.getProvinceName());
            jDBusinessAddress.setCity(addressGlobal.getCityName());
            jDBusinessAddress.setDistrict(addressGlobal.getAreaName());
            jDBusinessAddress.setTown(addressGlobal.getTownName());
            jDBusinessAddress.setUseGlobal(true);
            jDBusinessAddress.setAddressTitle(addressGlobal.getAddressTitle());
            jDBusinessAddress.setSaveBusiness(addressGlobal.getSaveBusiness());
            jDBusinessAddress.setSource(addressGlobal.getSource());
        }
        return jDBusinessAddress;
    }

    public static JDBusinessAddress createJDBusinessAddressWithJDLocation(JDLbsHttpOption jDLbsHttpOption, JDLocation jDLocation) {
        JDBusinessAddress jDBusinessAddress = new JDBusinessAddress();
        try {
            jDBusinessAddress.setAddressID(0L);
            jDBusinessAddress.setProvinceCode(jDLocation.getProvinceId());
            jDBusinessAddress.setProvince(jDLocation.getProvinceName());
            jDBusinessAddress.setCityCode(jDLocation.getCityId());
            jDBusinessAddress.setCity(jDLocation.getCityName());
            jDBusinessAddress.setDistrictCode(jDLocation.getDistrictId());
            jDBusinessAddress.setDistrict(jDLocation.getDistrictName());
            jDBusinessAddress.setTown(jDLocation.getTownName());
            jDBusinessAddress.setTownCode(jDLocation.getTownId());
            jDBusinessAddress.setDetailAddress(jDLocation.getDetailAddress());
            jDBusinessAddress.setFullAddress(jDLocation.getProvinceName() + jDLocation.getCityName() + jDLocation.getDistrictName() + jDLocation.getTownName() + jDLocation.getDetailAddress());
            jDBusinessAddress.setLat(jDLocation.getLat());
            jDBusinessAddress.setLng(jDLocation.getLng());
            jDBusinessAddress.setMobile("");
            jDBusinessAddress.setAddressName(TextUtils.isEmpty(jDLocation.getDetailAddress()) ? jDBusinessAddress.getFullAddress() : jDLocation.getDetailAddress());
            jDBusinessAddress.setType("gis");
            return jDBusinessAddress;
        } catch (Exception unused) {
            return new JDBusinessAddress();
        }
    }

    public static AddressGlobal getAddressGlobal(JDLbsHttpOption jDLbsHttpOption) {
        double d;
        double d2;
        try {
            AddressGlobal addressGlobal = AddressUtil.getAddressGlobal(jDLbsHttpOption.getSceneId());
            if (jDLbsHttpOption != null && "2b8ad271e577175adc9f0e7b93e76592".equalsIgnoreCase(jDLbsHttpOption.getBusinessId())) {
                if (addressGlobal == null) {
                    addressGlobal = getDefaultGlobal(jDLbsHttpOption);
                } else {
                    try {
                        d = Double.parseDouble(addressGlobal.getLatitude());
                    } catch (Exception e2) {
                        e = e2;
                        d = 0.0d;
                    }
                    try {
                        d2 = Double.parseDouble(addressGlobal.getLongitude());
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        addressGlobal = getDefaultGlobal(jDLbsHttpOption);
                        d2 = 0.0d;
                        if (d != 0.0d) {
                        }
                        addressGlobal = getDefaultGlobal(jDLbsHttpOption);
                        if (addressGlobal != null) {
                            addressGlobal.setWhere(addressGlobal.getProvinceName() + addressGlobal.getCityName() + addressGlobal.getAreaName() + addressGlobal.getTownName() + addressGlobal.getAddressDetail());
                        }
                        return addressGlobal;
                    }
                    if (d != 0.0d || d2 == 0.0d) {
                        addressGlobal = getDefaultGlobal(jDLbsHttpOption);
                    }
                }
            }
            if (addressGlobal != null && TextUtils.isEmpty(addressGlobal.getWhere())) {
                addressGlobal.setWhere(addressGlobal.getProvinceName() + addressGlobal.getCityName() + addressGlobal.getAreaName() + addressGlobal.getTownName() + addressGlobal.getAddressDetail());
            }
            return addressGlobal;
        } catch (Exception e4) {
            e4.printStackTrace();
            return new AddressGlobal();
        }
    }

    private static int getAddressType(JDBusinessAddress jDBusinessAddress) {
        if (jDBusinessAddress == null) {
            return 0;
        }
        String type = jDBusinessAddress.getType();
        char c2 = '\uffff';
        int hashCode = type.hashCode();
        if (hashCode != -1396673086) {
            if (hashCode != 102353) {
                if (hashCode == 105533 && type.equals("jsf")) {
                    c2 = 1;
                }
            } else if (type.equals("gis")) {
                c2 = 0;
            }
        } else if (type.equals(JDBusinessAddress.TYPE_BACKUP)) {
            c2 = 2;
        }
        if (c2 != 0) {
            return c2 != 1 ? 0 : 2;
        }
        return 3;
    }

    private static AddressGlobal getDefaultGlobal(JDLbsHttpOption jDLbsHttpOption) {
        JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
        jDLocationCacheOption.setBusinessId(jDLbsHttpOption.getBusinessId());
        jDLocationCacheOption.setSceneId(jDLbsHttpOption.getSceneId());
        JDLocation location = JDLocationCache.getInstance().getLocation(jDLocationCacheOption);
        if (location.getLng() == 0.0d && location.getLat() == 0.0d) {
            JDBusinessAddress jDBusinessAddress = new JDBusinessAddress();
            jDBusinessAddress.setAddressID(0L);
            jDBusinessAddress.setProvinceCode(1);
            jDBusinessAddress.setProvince(StringUtil.product_province_beijing);
            jDBusinessAddress.setCityCode(72);
            jDBusinessAddress.setCity("\u671d\u9633\u533a");
            jDBusinessAddress.setFullAddress("\u5317\u4eac\u5e02\u671d\u9633\u533a");
            jDBusinessAddress.setLat(39.921469d);
            jDBusinessAddress.setLng(116.443107d);
            jDBusinessAddress.setType(JDBusinessAddress.TYPE_BACKUP);
            jDBusinessAddress.setShowToast(false);
            jDBusinessAddress.setCode(501);
            jDBusinessAddress.setMessage(JDLbsHttpError.MSG_LOCATION_LATLNG_EMPTY);
            return createGlobalWithJDBusinessAddress(jDLbsHttpOption, jDBusinessAddress);
        }
        return createGlobalWithJDLocation(jDLbsHttpOption, location);
    }

    public static boolean updateAddressGlobal(JDLbsHttpOption jDLbsHttpOption, AddressGlobal addressGlobal) {
        return AddressUtil.updateAddressGlobal(addressGlobal);
    }

    private static int getAddressType(JDLocation jDLocation) {
        if (jDLocation == null) {
            return 0;
        }
        String callType = jDLocation.getCallType();
        callType.hashCode();
        if (callType.equals(JDLocation.IP_SERVICE)) {
            return 1;
        }
        return !callType.equals(JDLocation.GIS_SERVICE) ? 0 : 3;
    }

    public static boolean updateAddressGlobal(JDLbsHttpOption jDLbsHttpOption, JDBusinessAddress jDBusinessAddress) {
        return AddressUtil.updateAddressGlobal(createGlobalWithJDBusinessAddress(jDLbsHttpOption, jDBusinessAddress));
    }

    private static int getAddressType(JDYFAddress jDYFAddress) {
        if (jDYFAddress == null) {
            return 0;
        }
        String type = jDYFAddress.getType();
        char c2 = '\uffff';
        switch (type.hashCode()) {
            case -1361218025:
                if (type.equals("choose")) {
                    c2 = 2;
                    break;
                }
                break;
            case 102353:
                if (type.equals("gis")) {
                    c2 = 0;
                    break;
                }
                break;
            case 105533:
                if (type.equals("jsf")) {
                    c2 = 1;
                    break;
                }
                break;
            case 1544803905:
                if (type.equals("default")) {
                    c2 = 3;
                    break;
                }
                break;
        }
        if (c2 != 0) {
            return c2 != 1 ? 0 : 2;
        }
        return 3;
    }

    @Deprecated
    public static boolean updateAddressGlobal(JDYFAddress jDYFAddress) {
        JDLbsHttpOption jDLbsHttpOption = new JDLbsHttpOption();
        jDLbsHttpOption.setBusinessId("15f39f78ae41d235baf6dfdc573ccd47");
        jDLbsHttpOption.setSourceId(4);
        return updateAddressGlobal(jDLbsHttpOption, jDYFAddress);
    }

    public static boolean updateAddressGlobal(JDLbsHttpOption jDLbsHttpOption, JDYFAddress jDYFAddress) {
        return AddressUtil.updateAddressGlobal(createGlobalWithJDYFAddress(jDLbsHttpOption, jDYFAddress));
    }
}
