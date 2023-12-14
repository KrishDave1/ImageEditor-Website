package com.iiitb.imageEffectApplication.effectImplementation;

import com.iiitb.imageEffectApplication.baseEffects.PhotoEffect;
import com.iiitb.imageEffectApplication.libraryInterfaces.GrayscaleInterface;
import com.iiitb.imageEffectApplication.libraryInterfaces.Pixel;
import com.iiitb.imageEffectApplication.service.LoggingService;

public class GrayscaleEffect implements PhotoEffect {
    private float parameterValue;

    public GrayscaleEffect() {
        this.parameterValue = 0;
    }

    @Override
    public Pixel[][] apply(Pixel[][] image, String fileName) {
        return GrayscaleInterface.applyGrayscale(image);
    }
}
