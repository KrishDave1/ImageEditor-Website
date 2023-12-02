package com.iiitb.imageEffectApplication.libraryInterfaces;

public class BrightnessInterface {
    static {
        String libraryPath = "BrightnessLib";
        System.out.println("Loading library " + libraryPath);
        LoadNativeLibrary.loadNativeLibrary(libraryPath);
        System.out.println("Library loaded successfully.");
    }
    public static native Pixel[][] applyBrightness(Pixel[][] image, float amount);
}
