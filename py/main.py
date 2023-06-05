# -*- coding: utf-8 -*-
import logging
import frida
import sys

logging.basicConfig(level=logging.DEBUG)


def on_message(message, data):
    if message['type'] == 'send':
        print("[*] {0}".format(message['payload']))
    else:
        print(message)


with open('test.js', 'r', encoding='utf-8') as f:
    sta = ''.join(f.readlines())

rdev = frida.get_remote_device()
print(frida.get_usb_device().enumerate_processes())
# session = rdev.attach(8885)  # app包名
# print(session)
# script = session.create_script(sta)
# print(script)
#
#
# def show(message, data):
#     print(message)
#
#
# script.on("message", show)
#
# # 加载脚本
# script.load()
# sys.stdin.read()
