package com.iiitb.imageEffectApplication.effectImplementation;

import com.iiitb.imageEffectApplication.baseEffects.PhotoEffect;
import com.iiitb.imageEffectApplication.libraryInterfaces.Pixel;
import com.iiitb.imageEffectApplication.libraryInterfaces.SepiaInterface;
import com.iiitb.imageEffectApplication.service.LoggingService;

public class SepiaEffect implements PhotoEffect {
    @Override
    public Pixel[][] apply(Pixel[][] image, String fileName) {
        return SepiaInterface.applySepia(image);
    }
}
