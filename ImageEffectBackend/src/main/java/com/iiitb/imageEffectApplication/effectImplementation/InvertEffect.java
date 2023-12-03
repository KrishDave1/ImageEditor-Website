package com.iiitb.imageEffectApplication.effectImplementation;
import com.iiitb.imageEffectApplication.libraryInterfaces.Pixel;
import com.iiitb.imageEffectApplication.exception.IllegalParameterException;
import com.iiitb.imageEffectApplication.service.LoggingService;
import com.iiitb.imageEffectApplication.baseEffects.PhotoEffect;
import com.iiitb.imageEffectApplication.libraryInterfaces.InvertInterface;

public class InvertEffect implements PhotoEffect {
    @Override
    public Pixel[][] apply(Pixel[][] pixels, String imageName, LoggingService loggingService) {
        loggingService.addLog(imageName, "Invert", "None");
        return InvertInterface.applyInvert(pixels);
    }
}
