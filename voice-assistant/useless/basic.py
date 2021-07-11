import cv2
import numpy as np
import face_recognition

imgElon = face_recognition.load_image_file('imageBasics/jeff besos.jpg')
imgElon = cv2.cvtColor(imgElon, cv2.COLOR_BGR2RGB)

imgElonTest = face_recognition.load_image_file('imageBasics/jeff_test.jpg')
imgElonTest = cv2.cvtColor(imgElonTest, cv2.COLOR_BGR2RGB)

faceLoc = face_recognition.face_locations(imgElon)[0]
encodeElon = face_recognition.face_encodings(imgElon)[0]
cv2.rectangle(imgElon, (faceLoc[3], faceLoc[0]), (faceLoc[1], faceLoc[2]), (255,0,0), 2)
# print(faceLoc)
# print(encodeElon)

faceLocTest = face_recognition.face_locations(imgElonTest)[0]
encodeElonTest = face_recognition.face_encodings(imgElonTest)[0]
cv2.rectangle(imgElonTest, (faceLocTest[3], faceLocTest[0]), (faceLocTest[1], faceLocTest[2]), (255,0,0), 2)
print(faceLocTest)
results = face_recognition.compare_faces([encodeElon], encodeElonTest)
faceDist = face_recognition.face_distance([encodeElon], encodeElonTest)

print(results, faceDist)
cv2.putText(imgElonTest, f'{results} {round(faceDist[0],2)}', (faceLocTest[3],faceLocTest[2]+7), cv2.FONT_HERSHEY_SIMPLEX, 0.3, (255,255,255), 1)

cv2.imshow('Elon Musk', imgElon)
cv2.imshow('Elon Test', imgElonTest)
#
cv2.waitKey(0)