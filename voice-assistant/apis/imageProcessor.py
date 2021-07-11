import cv2
import numpy as np
import face_recognition
import os
from datetime import datetime
from apis.IOFeatures import speak

path = 'models/images'
imageLists = []  # Contains lists of images of each known face
classNames = []  # Contains lists of all known faces
myListFolders = os.listdir(path)

for myList in myListFolders:
    classNames.append(myList)
    imagePath = path + "/" + myList + "/"
    images = os.listdir(imagePath)
    curImg = []
    for image in images:
        curImg.append(cv2.imread(f'{imagePath}{image}'))
    imageLists.append(curImg)
print(classNames)


def __findEncodings(images):
    encodeList = []
    for img in images:
        img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
        encodeImg = face_recognition.face_encodings(img)[0]
        encodeList.append(encodeImg)
    return encodeList


encodeListKnown = []
for images in imageLists:
    encodeListKnown.append(__findEncodings(images))

print(len(encodeListKnown), len(encodeListKnown[0]))
print('Encoding Complete')


def markAttendance(name):
    with open('models/data/Attendance.csv', 'a') as f:
        date = str(datetime.now()).split(' ')
        f.write(f'{date[0]}, {name}, {date[1].split(".")[0]}\n')
        speak('Your entry attendance is recorded')


def verifyImage():
    cap = cv2.VideoCapture(0)
    i = 0
    total_matches = 0

    while i < 100:
        success, img = cap.read()
        imgS = cv2.resize(img, (0, 0), None, 0.25, 0.25)  # Reduce the image size to make the image detection faster
        imgS = cv2.cvtColor(imgS, cv2.COLOR_BGR2RGB)

        facesCurFrame = face_recognition.face_locations(imgS)
        encodeCurFrame = face_recognition.face_encodings(imgS, facesCurFrame)

        for encodeFace, faceLoc in zip(encodeCurFrame, facesCurFrame):
            matchIndex = 0
            faceDist = [1]
            for known_faces in encodeListKnown:
                # matches = face_recognition.compare_faces(encodeListKnown, encodeFace)
                faceDist = face_recognition.face_distance(known_faces, encodeFace)
                if min(faceDist) < 0.4:
                    break
                matchIndex += 1

            if min(faceDist) < 0.4 and matchIndex < len(encodeListKnown):
                total_matches += 1
                name = classNames[matchIndex]
                print(faceDist, name)
                y1, x2, y2, x1 = faceLoc
                y1, x2, y2, x1 = y1 * 4, x2 * 4, y2 * 4, x1 * 4
                cv2.rectangle(img, (x1, y1), (x2, y2), (0, 0, 255), 1)
                cv2.rectangle(img, (x1, y2 - 30), (x2, y2), (0, 0, 255), cv2.FILLED)
                cv2.putText(img, name, (x1 + 6, y2 - 3), cv2.FONT_ITALIC, 0.6, (255, 255, 255), 2)

        if total_matches == 10:
            return True, name
        cv2.imshow('Webcam', img)
        cv2.waitKey(1)
        i += 1

    return False, 'unknown' # Authorization passed


# verified = verifyImage()

