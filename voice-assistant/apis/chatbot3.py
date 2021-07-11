# -*- coding: utf-8 -*-
"""
Created on Mon Jan 18 20:18:27 2021

@author: subhankar
"""
# import numpy as np

import random
import json
# import pickle
# import os.path
# from os import path



with open("models\\data\\roomSwitch.json") as file:
    data = json.load(file)
def extractInfo(text):
    # status,room, roomValue, switch, switchValue, response
    status = 0
    room = set()       # room names
    roomValue = set()  #room values
    switch = set()     #switch names
    switchValue = set()#switch values
    
    if(text.find('turn on') != -1 or text.find('switch on') != -1 or text.find('activate') != -1):
        status = 1
        # print(status)
        # print(text.find('activate'))
    for rooms in data['deviceMapping']:
        if (text.find(rooms['room']) != -1):
            
            for devices in rooms['devices']:
                if (text.find(devices) != -1):
                    room.add(rooms['room'])
                    roomValue.add(rooms['value'])
                    switch.add(devices)
                    switchValue.add(rooms['values'][rooms['devices'].index(devices)])
    
    # print(status)
    # print(room)
    # print(roomValue)
    # print(switch)
    # print(switchValue)
    if(len(switch)==0):
        record = data['essentials']
        # print(record['device'])
        for device in range(0,len(record['device'])):
            if(text.find(record['device'][device]) != -1):
                room.add(record['room'][device])
                roomValue.add(record['roomValue'][device])
                switchValue.add(record['deviceValue'][device])
                switch.add(record['device'][device])
                break
    # print(status)
    # print(room)
    # print(roomValue)
    # print(switch)
    # print(switchValue)
    response = ""
    # print(len(switch))
    if(len(switch) != len(switchValue)):
        response = "you have provided an abnormal request for me please try again by breaking down your statement."
        response = response + "if the problem persist please contact our customer service."
    elif(len(switch)==0 or len(room)==0):
        response = "no such devices are found please try again"
    else:
        if(status == 0):
            response = random.choice(data["negativeResponse"]) + " " + str(room) + " " + str(switch)
        else:
            response = random.choice(data["positiveResponse"]) + " " + str(room) + " " + str(switch)
    response = response.replace("{","")
    response = response.replace("'","")
    response = response.replace("}","")
    print(status)
    print(room)
    print(roomValue)
    print(switch)
    print(switchValue)
    print(response)
    
    return status,room, roomValue, switch, switchValue, response
                    
    # print(data['deviceMapping'][0]['room0'])
# extractInfo("turn off the garage and guest room light and router")