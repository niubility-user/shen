package com.jingdong.common.lbs.search;

import com.jingdong.common.BaseApplication;
import com.jingdong.common.lbs.businessAddress.JDBusinessAddress;
import com.jingdong.common.lbs.http.JDLbsHttpError;
import com.jingdong.common.lbs.http.JDLbsHttpListListener;
import com.jingdong.common.lbs.http.JDLbsHttpListener;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class JDLbsSearchManager {
    private static JDLbsSearchManager manager;

    public static JDLbsSearchManager getInstance() {
        JDLbsSearchManager jDLbsSearchManager;
        JDLbsSearchManager jDLbsSearchManager2 = manager;
        if (jDLbsSearchManager2 != null) {
            return jDLbsSearchManager2;
        }
        synchronized (JDLbsSearchManager.class) {
            if (manager == null) {
                manager = new JDLbsSearchManager();
            }
            jDLbsSearchManager = manager;
        }
        return jDLbsSearchManager;
    }

    public void getAddressByKeyWord(JDLbsSearchOption jDLbsSearchOption, final JDLbsHttpListListener<JDLbsSearchAddress> jDLbsHttpListListener) {
        if (jDLbsHttpListListener == null) {
            return;
        }
        try {
            JDLbsSearchNet.getInstance().getAddressByKeyWord(jDLbsSearchOption, new JDLbsHttpListListener<JDLbsSearchAddress>() { // from class: com.jingdong.common.lbs.search.JDLbsSearchManager.7
                @Override // com.jingdong.common.lbs.http.JDLbsHttpListListener
                public void onFail(final JDLbsHttpError jDLbsHttpError) {
                    if (jDLbsHttpListListener != null) {
                        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.search.JDLbsSearchManager.7.2
                            @Override // java.lang.Runnable
                            public void run() {
                                jDLbsHttpListListener.onFail(jDLbsHttpError);
                            }
                        });
                    }
                }

                @Override // com.jingdong.common.lbs.http.JDLbsHttpListListener
                public void onSuccess(final ArrayList<JDLbsSearchAddress> arrayList) {
                    if (jDLbsHttpListListener != null) {
                        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.search.JDLbsSearchManager.7.1
                            @Override // java.lang.Runnable
                            public void run() {
                                jDLbsHttpListListener.onSuccess(arrayList);
                            }
                        });
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            if (jDLbsHttpListListener != null) {
                BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.search.JDLbsSearchManager.8
                    @Override // java.lang.Runnable
                    public void run() {
                        JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                        jDLbsHttpError.setCode(300);
                        jDLbsHttpError.setMessage(JDLbsHttpError.MSG_EXCEPTION);
                        jDLbsHttpListListener.onFail(jDLbsHttpError);
                    }
                });
            }
        }
    }

    public void getAddressByLatLng(JDLbsSearchOption jDLbsSearchOption, final JDLbsHttpListener<JDLbsSearchAddress> jDLbsHttpListener) {
        if (jDLbsHttpListener == null) {
            return;
        }
        try {
            JDLbsSearchNet.getInstance().getAddressByLatLng(jDLbsSearchOption, new JDLbsHttpListener<JDLbsSearchAddress>() { // from class: com.jingdong.common.lbs.search.JDLbsSearchManager.5
                @Override // com.jingdong.common.lbs.http.JDLbsHttpListener
                public void onFail(final JDLbsHttpError jDLbsHttpError) {
                    if (jDLbsHttpListener != null) {
                        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.search.JDLbsSearchManager.5.2
                            @Override // java.lang.Runnable
                            public void run() {
                                jDLbsHttpListener.onFail(jDLbsHttpError);
                            }
                        });
                    }
                }

                @Override // com.jingdong.common.lbs.http.JDLbsHttpListener
                public void onSuccess(final JDLbsSearchAddress jDLbsSearchAddress) {
                    if (jDLbsHttpListener != null) {
                        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.search.JDLbsSearchManager.5.1
                            @Override // java.lang.Runnable
                            public void run() {
                                jDLbsHttpListener.onSuccess(jDLbsSearchAddress);
                            }
                        });
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            if (jDLbsHttpListener != null) {
                BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.search.JDLbsSearchManager.6
                    @Override // java.lang.Runnable
                    public void run() {
                        JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                        jDLbsHttpError.setCode(300);
                        jDLbsHttpError.setMessage(JDLbsHttpError.MSG_EXCEPTION);
                        jDLbsHttpListener.onFail(jDLbsHttpError);
                    }
                });
            }
        }
    }

    public void getBusinessAddressByLatLng(final JDLbsSearchOption jDLbsSearchOption, final JDLbsHttpListener<JDBusinessAddress> jDLbsHttpListener) {
        if (jDLbsHttpListener == null) {
            return;
        }
        try {
            JDLbsSearchNet.getInstance().getLocationByLatLng(jDLbsSearchOption, new JDLbsHttpListener<JDLbsSearchLocation>() { // from class: com.jingdong.common.lbs.search.JDLbsSearchManager.1
                @Override // com.jingdong.common.lbs.http.JDLbsHttpListener
                public void onFail(final JDLbsHttpError jDLbsHttpError) {
                    if (jDLbsHttpListener != null) {
                        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.search.JDLbsSearchManager.1.2
                            @Override // java.lang.Runnable
                            public void run() {
                                jDLbsHttpListener.onFail(jDLbsHttpError);
                            }
                        });
                    }
                }

                @Override // com.jingdong.common.lbs.http.JDLbsHttpListener
                public void onSuccess(final JDLbsSearchLocation jDLbsSearchLocation) {
                    if (jDLbsHttpListener != null) {
                        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.search.JDLbsSearchManager.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                JDBusinessAddress jDBusinessAddress = new JDBusinessAddress();
                                jDBusinessAddress.setProvinceCode(jDLbsSearchLocation.getProvinceCode());
                                jDBusinessAddress.setCityCode(jDLbsSearchLocation.getCityCode());
                                jDBusinessAddress.setDistrictCode(jDLbsSearchLocation.getDistrictCode());
                                jDBusinessAddress.setTownCode(jDLbsSearchLocation.getTownCode());
                                jDBusinessAddress.setProvince(jDLbsSearchLocation.getProvince());
                                jDBusinessAddress.setCity(jDLbsSearchLocation.getCity());
                                jDBusinessAddress.setDistrict(jDLbsSearchLocation.getDistrict());
                                jDBusinessAddress.setTown(jDLbsSearchLocation.getTown());
                                jDBusinessAddress.setDetailAddress(jDLbsSearchLocation.getDetailAddress());
                                jDBusinessAddress.setFullAddress(jDLbsSearchLocation.getProvince() + jDLbsSearchLocation.getCity() + jDLbsSearchLocation.getDistrict() + jDLbsSearchLocation.getTown() + jDLbsSearchLocation.getDetailAddress());
                                jDBusinessAddress.setLng(jDLbsSearchOption.getLng());
                                jDBusinessAddress.setLat(jDLbsSearchOption.getLat());
                                jDBusinessAddress.setType("gis");
                                jDBusinessAddress.setTitle(jDLbsSearchOption.getTitle());
                                jDLbsHttpListener.onSuccess(jDBusinessAddress);
                            }
                        });
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            if (jDLbsHttpListener != null) {
                BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.search.JDLbsSearchManager.2
                    @Override // java.lang.Runnable
                    public void run() {
                        JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                        jDLbsHttpError.setCode(300);
                        jDLbsHttpError.setMessage(JDLbsHttpError.MSG_EXCEPTION);
                        jDLbsHttpListener.onFail(jDLbsHttpError);
                    }
                });
            }
        }
    }

    public void getCityByKeyWord(JDLbsSearchOption jDLbsSearchOption, final JDLbsHttpListListener<JDLbsSearchCity> jDLbsHttpListListener) {
        if (jDLbsHttpListListener == null) {
            return;
        }
        try {
            JDLbsSearchNet.getInstance().getCityByKeyWord(jDLbsSearchOption, new JDLbsHttpListListener<JDLbsSearchCity>() { // from class: com.jingdong.common.lbs.search.JDLbsSearchManager.11
                @Override // com.jingdong.common.lbs.http.JDLbsHttpListListener
                public void onFail(final JDLbsHttpError jDLbsHttpError) {
                    if (jDLbsHttpListListener != null) {
                        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.search.JDLbsSearchManager.11.2
                            @Override // java.lang.Runnable
                            public void run() {
                                jDLbsHttpListListener.onFail(jDLbsHttpError);
                            }
                        });
                    }
                }

                @Override // com.jingdong.common.lbs.http.JDLbsHttpListListener
                public void onSuccess(final ArrayList<JDLbsSearchCity> arrayList) {
                    if (jDLbsHttpListListener != null) {
                        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.search.JDLbsSearchManager.11.1
                            @Override // java.lang.Runnable
                            public void run() {
                                jDLbsHttpListListener.onSuccess(arrayList);
                            }
                        });
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            if (jDLbsHttpListListener != null) {
                BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.search.JDLbsSearchManager.12
                    @Override // java.lang.Runnable
                    public void run() {
                        JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                        jDLbsHttpError.setCode(300);
                        jDLbsHttpError.setMessage(JDLbsHttpError.MSG_EXCEPTION);
                        jDLbsHttpListListener.onFail(jDLbsHttpError);
                    }
                });
            }
        }
    }

    public void getCityList(JDLbsSearchOption jDLbsSearchOption, final JDLbsHttpListListener<JDLbsSearchCity> jDLbsHttpListListener) {
        if (jDLbsHttpListListener == null) {
            return;
        }
        try {
            JDLbsSearchNet.getInstance().getCityList(jDLbsSearchOption, new JDLbsHttpListListener<JDLbsSearchCity>() { // from class: com.jingdong.common.lbs.search.JDLbsSearchManager.9
                @Override // com.jingdong.common.lbs.http.JDLbsHttpListListener
                public void onFail(final JDLbsHttpError jDLbsHttpError) {
                    if (jDLbsHttpListListener != null) {
                        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.search.JDLbsSearchManager.9.2
                            @Override // java.lang.Runnable
                            public void run() {
                                jDLbsHttpListListener.onFail(jDLbsHttpError);
                            }
                        });
                    }
                }

                @Override // com.jingdong.common.lbs.http.JDLbsHttpListListener
                public void onSuccess(final ArrayList<JDLbsSearchCity> arrayList) {
                    if (jDLbsHttpListListener != null) {
                        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.search.JDLbsSearchManager.9.1
                            @Override // java.lang.Runnable
                            public void run() {
                                jDLbsHttpListListener.onSuccess(arrayList);
                            }
                        });
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            if (jDLbsHttpListListener != null) {
                BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.search.JDLbsSearchManager.10
                    @Override // java.lang.Runnable
                    public void run() {
                        JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                        jDLbsHttpError.setCode(300);
                        jDLbsHttpError.setMessage(JDLbsHttpError.MSG_EXCEPTION);
                        jDLbsHttpListListener.onFail(jDLbsHttpError);
                    }
                });
            }
        }
    }

    public void getPOIsByLatLng(JDLbsSearchOption jDLbsSearchOption, final JDLbsHttpListListener<JDLbsSearchAddress> jDLbsHttpListListener) {
        if (jDLbsHttpListListener == null) {
            return;
        }
        try {
            JDLbsSearchNet.getInstance().getPOIsByLatLng(jDLbsSearchOption, new JDLbsHttpListListener<JDLbsSearchAddress>() { // from class: com.jingdong.common.lbs.search.JDLbsSearchManager.3
                @Override // com.jingdong.common.lbs.http.JDLbsHttpListListener
                public void onFail(final JDLbsHttpError jDLbsHttpError) {
                    if (jDLbsHttpListListener != null) {
                        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.search.JDLbsSearchManager.3.2
                            @Override // java.lang.Runnable
                            public void run() {
                                jDLbsHttpListListener.onFail(jDLbsHttpError);
                            }
                        });
                    }
                }

                @Override // com.jingdong.common.lbs.http.JDLbsHttpListListener
                public void onSuccess(final ArrayList<JDLbsSearchAddress> arrayList) {
                    if (jDLbsHttpListListener != null) {
                        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.search.JDLbsSearchManager.3.1
                            @Override // java.lang.Runnable
                            public void run() {
                                jDLbsHttpListListener.onSuccess(arrayList);
                            }
                        });
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            if (jDLbsHttpListListener != null) {
                BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.lbs.search.JDLbsSearchManager.4
                    @Override // java.lang.Runnable
                    public void run() {
                        JDLbsHttpError jDLbsHttpError = new JDLbsHttpError();
                        jDLbsHttpError.setCode(300);
                        jDLbsHttpError.setMessage(JDLbsHttpError.MSG_EXCEPTION);
                        jDLbsHttpListListener.onFail(jDLbsHttpError);
                    }
                });
            }
        }
    }
}
