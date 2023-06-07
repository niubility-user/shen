# -*- coding: utf-8 -*-
import logging
import sys

import frida

logging.basicConfig(level=logging.DEBUG)


def on_message(message, data):
    if "payload" in message:
        print("Message from script: ", message["payload"])
    else:
        print("Message: ", message)


device = frida.get_usb_device()
pid = device.spawn("com.jingdong.app.mall")
session = device.attach(pid)  # app包名
print(session)
device.resume(pid)

with open('js/Base.js', 'r', encoding='utf-8') as f:
    baseJs = ''.join(f.readlines())
with open('js/BitmapkitUtils.getSignFromJni.js', 'r', encoding='utf-8') as f:
    businessJs = ''.join(f.readlines())

script = session.create_script(baseJs + businessJs)
print(script)

script.on("message", on_message)

# 加载并执行 Frida 脚本
script.load()

# 命令行保持活动，以便接收消息
try:
    sys.stdin.read()
except KeyboardInterrupt:
    pass

print("Interrupted by user. clear...")
# 删除 Frida 脚本
script.unload()
session.detach()
print("Exiting...")
