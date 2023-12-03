package com.iiitb.imageEffectApplication.libraryInterfaces;

public class DominantColourInterface {
    static {
        String libraryPath = "DominantColourLib";
        LoadNativeLibrary.loadNativeLibrary(libraryPath);
    }
    public static native Pixel[][] applyDominantColour(Pixel[][] image);
}
