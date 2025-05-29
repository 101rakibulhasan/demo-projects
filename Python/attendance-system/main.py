import cv2
import numpy as np
import os

# Haarcascade file path (make sure this file is uploaded)
haar_file = 'haarcascade_frontalface_default.xml'

# Load the image from your uploaded file
img_path = 'img1.jpeg'  # Update with your image path
image = cv2.imread(img_path)

if image is None:
    print("Image not loaded correctly.")
else:
    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

    if not os.path.exists(haar_file):
        print(f"Haarcascade file not found: {haar_file}")
    else:
        face_cascade = cv2.CascadeClassifier(haar_file)

        faces = face_cascade.detectMultiScale(gray, 1.1, 4)

        for (x, y, w, h) in faces:
            cv2.rectangle(image, (x, y), (x + w, y + h), (255, 0, 0), 2)

        cv2.imshow('Detected Faces', image)
        cv2.waitKey(0)
        cv2.destroyAllWindows()
