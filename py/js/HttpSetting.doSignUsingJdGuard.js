Java.perform(function () {
    var HttpSetting = Java.use("com.jingdong.jdsdk.network.toolbox.HttpSetting")
    var HttpSettingTool = Java.use("com.jingdong.jdsdk.network.toolbox.HttpSettingTool");
    // 替换HttpSettingTool的doSignUsingJdGuard方法的实现
    HttpSettingTool.doSignUsingJdGuard.implementation = function (httpSetting, str) {
        this.doSignUsingJdGuard(httpSetting, str); // 先调用原来的方法
        var setting = Java.cast(httpSetting, HttpSetting)
        console.log("[doSignUsingJdGuard] -> funtionID:" + setting.getFunctionId() + " arg2: " + str);
    }
});