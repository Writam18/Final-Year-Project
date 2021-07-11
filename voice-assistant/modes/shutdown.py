# -*- coding: utf-8 -*-
"""
Created on Mon Jun 21 11:24:11 2021

@author: subhankar
"""

import json
from apis import post
from apis.imageProcessor import verifyImage, markAttendance
from apis.IOFeatures import speak
from apis.chatbot3 import extractInfo as extract
from modes.command import handler as comHandler

with open("models\\data\\roomSwitch.json") as file:
    data = json.load(file)


def __findBit(i):
    arr = []
    while i > 0:
        arr.insert(0, i % 2)
        i = i // 2
    return arr


def initiateShutdown():
    print("here")
    devices = data['noofDevices'][0]
    rooms = data['noofRooms'][0]

    roomsValue = int(pow(2, rooms)) - 1
    print(roomsValue, __findBit(roomsValue))

    devicesValue = int(pow(2, devices)) - 1
    print(devicesValue, __findBit(devicesValue))

    sendValue = roomsValue << devices
    sendValue += devicesValue
    print(sendValue, __findBit(sendValue))

    sendValue = sendValue << 1 + 0
    print(sendValue, __findBit(sendValue))

    print(sendValue)
    print(post.postField1(sendValue))


def verifyUserLogin():
    password = 'Iamscratches'
    input_password = ''

    while True:
        while password != input_password:
            input_password = input("Please Enter password: ")
            if password != input_password:
                speak('you have entered wrong password! please try again!!', rate=120)
        verified = verifyImage()
        if verified[0]:
            markAttendance(verified[1])
            speak(f'Welcome home {verified[1]}, it\'s nice to see you again. Hope u had a good day outside', rate=120)
            status, room, roomValue, switch, switchValue, response = extract('turn on hallway exterior light')
            comHandler(status, roomValue, switchValue)
            break
        else:
            speak('Sorry! Face Authorization failed, please face the camera and try again', rate=120)
            input_password = ''