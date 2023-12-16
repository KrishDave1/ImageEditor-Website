package com.iiitb.imageEffectApplication.effectImplementation;

import com.iiitb.imageEffectApplication.baseEffects.DiscreteEffect;
import com.iiitb.imageEffectApplication.libraryInterfaces.RotationInterface;
import com.iiitb.imageEffectApplication.libraryInterfaces.Pixel;
import com.iiitb.imageEffectApplication.service.LoggingService;
import com.iiitb.imageEffectApplication.exception.IllegalParameterException;

public class RotationEffect implements DiscreteEffect {
    private int Degree;

    @Override
    public void selectOptionValue(String optionName, int value) throws IllegalParameterException {
        // Implementation of the selectOptionValue method goes here
        if (value < 0 || value > 3){
            throw new IllegalParameterException("Value can be 0, 90, 180 or 270.");
        }
        this.Degree = value*90;
    }

    @Override
    public Pixel[][] apply(Pixel[][] image, String fileName) {
        // Implementation of the apply method goes here
        return RotationInterface.applyRotation(image, Degree);
    }
}
