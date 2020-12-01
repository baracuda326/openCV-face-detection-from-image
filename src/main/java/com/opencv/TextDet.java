package com.opencv;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class TextDet {
    public static void main(String[] args) throws TesseractException {
        String inputFilePath = "i3.jpg";
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(".\\src\\main\\resources\\");
        String text = tesseract.doOCR(new File(inputFilePath));
        System.out.println(text);
    }
}
