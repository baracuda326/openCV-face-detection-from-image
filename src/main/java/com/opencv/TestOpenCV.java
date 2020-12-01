package com.opencv;

import nu.pattern.OpenCV;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class TestOpenCV {
    public static void main(String[] args) {
        OpenCV.loadShared();
        // Loading the OpenCV core library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Reading the Image from the file and storing it in to a Matrix object
        String file = "i2.jpg";
        Mat src = Imgcodecs.imread(file);

        // Instantiating the CascadeClassifier
        String xmlFile = "./src/main/resources/haarcascades/haarcascade_frontalface_alt.xml";
        CascadeClassifier classifier = new CascadeClassifier(xmlFile);

        // Detecting the face in the snap
        MatOfRect faceDetections = new MatOfRect();
        classifier.detectMultiScale(src, faceDetections);
        System.out.println(String.format("Detected %s faces",
                faceDetections.toArray().length));

        // Drawing boxes
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(
                    src,                                               // where to draw the box
                    new Point(rect.x, rect.y),                            // bottom left
                    new Point(rect.x + rect.width, rect.y + rect.height), // top right
                    new Scalar(0, 0, 255),
                    3                                                     // RGB colour
            );
        }

        // Writing the image
        Imgcodecs.imwrite("./src/main/resources/i2.jpg", src);

        System.out.println("Image Processed");
    }

    public static void saveImage(Mat imageMatrix, String targetPath) {
        Imgcodecs imgcodecs = new Imgcodecs();
        imgcodecs.imwrite(targetPath, imageMatrix);
    }

    private static Mat loadImage(String s) {
        Imgcodecs imageCodecs = new Imgcodecs();
        return imageCodecs.imread(s);
    }
}
