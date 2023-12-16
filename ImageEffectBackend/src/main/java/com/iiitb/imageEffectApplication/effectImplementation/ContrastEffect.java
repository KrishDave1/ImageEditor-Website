package com.iiitb.imageEffectApplication.effectImplementation;

import com.iiitb.imageEffectApplication.baseEffects.SingleValueParameterizableEffect;
import com.iiitb.imageEffectApplication.exception.IllegalParameterException;
import com.iiitb.imageEffectApplication.libraryInterfaces.ContrastInterface;
import com.iiitb.imageEffectApplication.libraryInterfaces.Pixel;
import com.iiitb.imageEffectApplication.service.LoggingService;

public class ContrastEffect implements SingleValueParameterizableEffect {
    private float parameterValue;

    public ContrastEffect() {
        this.parameterValue = 0;
    }

    @Override
    public Pixel[][] apply(Pixel[][] image, String fileName) {
        return ContrastInterface.applyContrast(image, this.parameterValue);
    }

    @Override
    public void setParameterValue(float parameterValue) throws IllegalParameterException {
        if (parameterValue > 200 || parameterValue < 0) {
            throw new IllegalParameterException("Parameter value should be in the range 0 to 200.");
        }
        this.parameterValue = parameterValue;
    }
}
