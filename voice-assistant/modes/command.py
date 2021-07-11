# -*- coding: utf-8 -*-
"""
Created on Thu Jan 14 21:10:38 2021

@author: subhankar
"""
from apis import post
# import numpy as np

# roomSwitchRecord = [0,0,0,0,0,0,0,0]
# [room0, room1, room2, room3, room4, room5, room6, room7, room8, room9, room10, 
# switch7, switch6, switch5, switch4, switch3, switch2, switch1, switch0,
# status]

# def change(roomStatus, switchStatus, status):
#     status = int(status)
#     rooms = [0,0,0,0,0,0,0,0]
#     switches = [0,0,0,0,0,0,0,0]
#     rooms = np.array(rooms)
#     switches = np.array(switches)
#     # text = input("Suvnkr: ")
#     r=0
#     s=0
    
#     if(roomStatus == "allRoom"):
#         for i in range(len(rooms)):
#             rooms[i] = 1
#     elif(roomStatus == "room7"):
#         rooms[7] = 1
#         r = 7
#     elif(roomStatus == "room6"):
#         rooms[6] = 1
#         r = 6
#     elif(roomStatus == "room5"):
#         rooms[5] = 1
#         r = 5
#     elif(roomStatus == "room4"):
#         rooms[4] = 1
#         r = 4
#     elif(roomStatus == "room3"):
#         rooms[3] = 1
#         r = 3
#     elif(roomStatus == "room2"):
#         rooms[2] = 1
#         r = 2
#     elif(roomStatus == "room1"):
#         rooms[1] = 1
#         r = 1
#     elif(roomStatus == "room0"):
#         rooms[0] = 1
#         r = 0
        
#     # text = input("Suvnkr: ")
    
#     if(switchStatus == "allSwitch"):
#         for i in range(len(switches)):
#             switches[i] = status
#             s=255
#     elif(switchStatus == "switch7"):
#         switches[0] = status
#         s=pow(2,7)
#     elif(switchStatus == "switch6"):
#         switches[1] = status
#         s=pow(2,6)
#     elif(switchStatus == "switch5"):
#         switches[2] = status
#         s=pow(2,5)
#     elif(switchStatus == "switch4"):
#         switches[3] = status
#         s=pow(2,4)
#     elif(switchStatus == "switch3"):
#         switches[4] = status
#         s=pow(2,3)
#     elif(switchStatus == "switch2"):
#         switches[5] = status
#         s=pow(2,2)
#     elif(switchStatus == "switch1"):
#         switches[6] = status
#         s=pow(2,1)
#     elif(switchStatus == "switch0"):
#         switches[7] = status
#         s=pow(2,0)
        
    
#     data = 0
#     s = int(s)
#     if(roomStatus == "allRoom"):
#         if(switchStatus == "allSwitch"):
#             for i in range(len(roomSwitchRecord)):
#                 roomSwitchRecord[i] = 0 if status==0 else 255
#         else:
#              for i in range(len(roomSwitchRecord)):
#                 roomSwitchRecord[i] = roomSwitchRecord[i] | (s * status)
#     elif(switchStatus == "allSwitch"):
#         roomSwitchRecord[r] = 0 if status==0 else 255
            
#     else:
#         roomSwitchRecord[r] = roomSwitchRecord[r] | (s * status)
#     for i in rooms:
#         data = (data<<1) + i
#     data = data<<8
#     data = data | roomSwitchRecord[r]
#     print(rooms)
#     print(switches)
#     print(data)
#     print(roomSwitchRecord)
    
    # post.postField1(data)
    
def handler(status,roomValue,switchValue):
    
    value = 0
    data = 0
    for room in roomValue:
        value = value + int(pow(2,room))
    data = data + value
    data = data<<8
    value = 0
    for switch in switchValue:
        value = value + switch
    data = data + value
    
    data = data<<1
    data = data + status
    print(data)
    print(post.postField1(data))

# handler(1,{10,5}, {1,2})
    
