package com.babel.coupon.bean;

/* loaded from: classes.dex */
public class BenefitData {
    public String biPv;
    public String chainId;
    public String code;
    public DataDTO data;
    public String msg;
    public Long time;

    /* loaded from: classes.dex */
    public static class DataDTO {
        public BenefitReceiveResultDTO benefitReceiveResult;

        /* loaded from: classes.dex */
        public static class BenefitReceiveResultDTO {
            public BenefitReceiveVODTO benefitReceiveVO;
            public Integer code;
            public String message;
            public String type;

            /* loaded from: classes.dex */
            public static class BenefitReceiveVODTO {
                public Integer code;
                public String discountAmount;
                public String discountId;
                public Integer discountTtl;
                public String discountType;
                public Integer state;

                public Integer getCode() {
                    return this.code;
                }

                public String getDiscountAmount() {
                    return this.discountAmount;
                }

                public String getDiscountId() {
                    return this.discountId;
                }

                public Integer getDiscountTtl() {
                    return this.discountTtl;
                }

                public String getDiscountType() {
                    return this.discountType;
                }

                public Integer getState() {
                    return this.state;
                }

                public void setCode(Integer num) {
                    this.code = num;
                }

                public void setDiscountAmount(String str) {
                    this.discountAmount = str;
                }

                public void setDiscountId(String str) {
                    this.discountId = str;
                }

                public void setDiscountTtl(Integer num) {
                    this.discountTtl = num;
                }

                public void setDiscountType(String str) {
                    this.discountType = str;
                }

                public void setState(Integer num) {
                    this.state = num;
                }
            }

            public BenefitReceiveVODTO getBenefitReceiveVO() {
                return this.benefitReceiveVO;
            }

            public Integer getCode() {
                return this.code;
            }

            public String getMessage() {
                return this.message;
            }

            public String getType() {
                return this.type;
            }

            public void setBenefitReceiveVO(BenefitReceiveVODTO benefitReceiveVODTO) {
                this.benefitReceiveVO = benefitReceiveVODTO;
            }

            public void setCode(Integer num) {
                this.code = num;
            }

            public void setMessage(String str) {
                this.message = str;
            }

            public void setType(String str) {
                this.type = str;
            }
        }

        public BenefitReceiveResultDTO getBenefitReceiveResult() {
            return this.benefitReceiveResult;
        }

        public void setBenefitReceiveResult(BenefitReceiveResultDTO benefitReceiveResultDTO) {
            this.benefitReceiveResult = benefitReceiveResultDTO;
        }
    }

    public String getBiPv() {
        return this.biPv;
    }

    public String getChainId() {
        return this.chainId;
    }

    public String getCode() {
        return this.code;
    }

    public DataDTO getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public Long getTime() {
        return this.time;
    }

    public void setBiPv(String str) {
        this.biPv = str;
    }

    public void setChainId(String str) {
        this.chainId = str;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setData(DataDTO dataDTO) {
        this.data = dataDTO;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setTime(Long l2) {
        this.time = l2;
    }
}
