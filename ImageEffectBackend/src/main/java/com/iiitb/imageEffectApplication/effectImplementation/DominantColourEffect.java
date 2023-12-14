package com.iiitb.imageEffectApplication.effectImplementation;

import com.iiitb.imageEffectApplication.baseEffects.PhotoEffect;
import com.iiitb.imageEffectApplication.libraryInterfaces.DominantColourInterface;
import com.iiitb.imageEffectApplication.libraryInterfaces.Pixel;
import com.iiitb.imageEffectApplication.service.LoggingService;

public class DominantColourEffect implements PhotoEffect {
    @Override
    public Pixel[][] apply(Pixel[][] image, String fileName) {
        return DominantColourInterface.applyDominantColour(image);
    }
}
