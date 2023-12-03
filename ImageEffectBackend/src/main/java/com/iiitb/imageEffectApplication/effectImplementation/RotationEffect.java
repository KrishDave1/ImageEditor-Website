package com.iiitb.imageEffectApplication.effectImplementation;

import com.iiitb.imageEffectApplication.baseEffects.DiscreteEffect;
import com.iiitb.imageEffectApplication.exception.IllegalParameterException;
import com.iiitb.imageEffectApplication.libraryInterfaces.Pixel;
import com.iiitb.imageEffectApplication.libraryInterfaces.RotationInterface;
import com.iiitb.imageEffectApplication.service.LoggingService;

public class RotationEffect implements DiscreteEffect {
    private int degree;
    private String optionName;
    @Override
    public void selectOptionValue(String optionName, int value) throws IllegalParameterException {
        if (value == 0 || value == 90 || value == 180 || value == 270) {
            this.degree = value;
            this.optionName = optionName;
        }
        else {
            throw new IllegalParameterException("Angle should be one of 0, 90, 180 or 270.");
        }
    }

    @Override
    public Pixel[][] apply(Pixel[][] image, String fileName, LoggingService loggingService) {
        loggingService.addLog(fileName, "Rotation", this.optionName);
        return RotationInterface.applyRotation(image, this.degree);
    }
}
