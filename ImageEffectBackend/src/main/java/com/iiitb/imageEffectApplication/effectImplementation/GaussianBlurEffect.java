package com.iiitb.imageEffectApplication.effectImplementation;

import com.iiitb.imageEffectApplication.baseEffects.SingleValueParameterizableEffect;
import com.iiitb.imageEffectApplication.exception.IllegalParameterException;
import com.iiitb.imageEffectApplication.libraryInterfaces.GaussianBlurInterface;
import com.iiitb.imageEffectApplication.libraryInterfaces.Pixel;
import com.iiitb.imageEffectApplication.service.LoggingService;

public class GaussianBlurEffect implements SingleValueParameterizableEffect {
    private float parameterValue;

    public GaussianBlurEffect() {
        this.parameterValue = 0;
    }

    @Override
    public Pixel[][] apply(Pixel[][] image, String fileName) {
        return GaussianBlurInterface.applyGaussianBlur(image, this.parameterValue);
    }

    @Override
    public void setParameterValue(float parameterValue) throws IllegalParameterException {
        if (parameterValue < 0 || parameterValue > 50) {
            throw new IllegalParameterException("Parameter value should be in the range of 0 to 50.");
        }
        this.parameterValue = parameterValue;
    }
}
