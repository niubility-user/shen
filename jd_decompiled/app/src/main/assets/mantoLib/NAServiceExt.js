"use strict";

/*
1. 代码需要符合**ES5**标准，ES6的语法会导致 iOS9 设备白屏
*/

jd.o_openToast = function (params) {
  jd.__invokeMethod__("o_openToast", params); //nativeAPIname为原生方法的名称
};

jd.postMessageToNative = function (params) {
  jd.__invokeMethod__("postMessageToNative", params); //nativeAPIname为原生方法的名称
};

jd.onCustomEvent = function (eventName, callback) {
  jd.__onMethod__(eventName, function (e) {
    "function" == typeof callback && callback(e);
  });
};

//金融风控刷脸能力jspai
jd.faceRecognition = function (params) {
  jd.__invokeMethod__("faceRecognition", params); //nativeAPIname为原生方法的名称
};

// cps定制跳转，增加固定参数
jd.cpsJumpToNative = function () {
  var jumpParams = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};
  var param = {};
  if (jumpParams) {
    jumpParams.m_param = {
      jda: "123.158382952868842949672.1577764369.1577764373.1583829529.3"
    };
    jumpParams.from = "JDMiniApp";
    jumpParams.union_open = "union_cps";
    var paramsStr = encodeURIComponent(JSON.stringify(jumpParams));
    var urlStr = "openapp.jdmobile://virtual?params=" + paramsStr;
    param.dataParam = JSON.stringify({ url: urlStr });
  }
  jd.__invokeMethod__("navigateToNative", param);
};
// 茅台专用 图像识别
jd.imgRecognition = function (e) {
  jd.__invokeMethod__("imgRecognition", e);
};
// 门店小程序专用 生成会员码弹窗
jd.showMembersCode = function (e) {
  jd.__invokeMethod__("showMembersCode", e);
};
// 获取加密 uuid
jd.requestUUIDSync = function (e) {
  var t = {};
  jd.__invokeMethod__("requestUUIDSync", {}, {
    beforeSuccess: function beforeSuccess(e) {
      t = e;
    }
  });
  return t;
};
// 摘星0元单
jd.submitZeroOrder = function (e) {
  jd.__invokeMethod__("submitZeroOrder", e);
};

// 获取风控sdk的token，增加与11.3.0版本，用于京东支付小程序
jd.getJDJRRiskToken = function (e) {
  jd.__invokeMethod__("getJDJRRiskToken", e);
};

// 给RN发消息
jd.postMessageToRN = function (params) {
  jd.__invokeMethod__("postMessageToRN", params);
};

// 业务通用请求api
jd.mpCommonRequest = function (params) {
  if (!params) {
    console.error("mpCommonRequest 缺少必要的参数");
    return;
  }
  if (!params.method) {
    console.error("缺少 method 字段");
    return;
  }
  if (!params.functionId) {
    console.error("缺少 functionId 字段");
    return;
  }
  if (!params.data) {
    console.error("缺少 data 字段");
    return;
  };
  jd.__invokeMethod__("mpCommonRequest", params);
};

// 京东支付
jd.onPaySettingBack = function (callback) {
  jd.__onMethod__("onPaySettingBack", function (e) {
    "function" == typeof callback && callback(e);
  });
};

jd.jumpToPaySetting = function (params) {
  jd.__invokeMethod__("jumpToPaySetting", params);
};