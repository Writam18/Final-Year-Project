# -*- coding: utf-8 -*-
"""
Created on Tue Dec 29 13:17:09 2020

@author: subhankar
"""

from apis.chatbot1 import chat
from apis.chatbot3 import extractInfo as extract
from apis.IOFeatures import get_audio, speak, speakThread

from modes.general import handler as genHandler
from modes.command import handler as comHandler
from modes.shutdown import initiateShutdown, verifyUserLogin
import time

start_time = time.time()

mode = "general mode"
while True:
    text = get_audio()
    if text == "ERROR":
        # print("outside ERROR")
        # print("waiting for {}".format(int(time.time() - start_time)))
        continue
    tag, response = chat(text)
    if tag == "wakeup":
        speakThread(response)
        start_time = time.time()
        while time.time() - start_time < 15:
            text = get_audio()
            # print("waiting for {}".format(int(time.time() - start_time)))            
            if text == "ERROR":
                # print("Inside ERROR")
                # print("waiting for {}".format(int(time.time() - start_time)))
                continue

            tag, response = chat(text)

            if "mode" in tag.split():
                mode = tag
                start_time = time.time()
                if mode == "shutdown mode":
                    initiateShutdown()
                    speak(response)
                    verifyUserLogin()
                    mode = "general mode"
                    continue
                speak(response)
                continue

            if tag == "wakeup":
                speak(response)
                start_time = time.time()
                continue
            elif tag == "goodbye":
                speak(response)
                break

            if mode == "general mode":
                tag, response = chat(text)
                genHandler(text, tag, response)
            # elif(mode == "chat mode"):
            #     genHandler(text, tag, response)
            elif mode == "command mode":
                status, room, roomValue, switch, switchValue, response = extract(text)
                comHandler(status, roomValue, switchValue)
                speak(response)

            start_time = time.time()
    elif tag == "greeting":
        speak(response)
    elif "bye" in text or "tata" in text:
        speak("ok! bye bye!, nice to meet you")
        break
