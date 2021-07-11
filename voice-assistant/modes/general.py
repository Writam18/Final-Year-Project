# -*- coding: utf-8 -*-
"""
Created on Thu Jan 14 18:45:26 2021

@author: subhankar
"""

from apis.APIFeatures import define, playVideo, findLocation, googleSearch, calenderCheck, noteMaker, dateTime, findNews
from apis.IOFeatures import get_audio, speak, speakThread


def handler(text, tag, response):
    if tag == "greeting" or tag == "age" or tag == "name":
        speak(response)
    elif tag == "define":  # problem
        speak(response)
        define(text)
    elif tag == "youtube":
        playVideo(text)
        speak(response)
    elif tag == "location":
        speak(response)
        findLocation()
    elif tag == "google search":
        googleSearch(text)
        speak(response)
    elif tag == "calender":
        speak(response)
        calenderCheck(text)
    elif tag == "note":
        speak(response)
        information = get_audio()
        noteMaker(information)
    elif tag == "date n time":
        speak(response, 200)
        speak(dateTime(text), 120)
    elif tag == "news":  # stop button, needs training
        speak(response)
        findNews()
    elif tag == "none":
        speakThread(response)
