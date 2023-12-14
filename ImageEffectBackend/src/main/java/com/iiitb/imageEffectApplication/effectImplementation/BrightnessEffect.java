package com.iiitb.imageEffectApplication.effectImplementation;
import com.iiitb.imageEffectApplication.baseEffects.SingleValueParameterizableEffect;
import com.iiitb.imageEffectApplication.exception.IllegalParameterException;
import com.iiitb.imageEffectApplication.libraryInterfaces.BrightnessInterface;
import com.iiitb.imageEffectApplication.libraryInterfaces.Pixel;
import com.iiitb.imageEffectApplication.service.LoggingService;

public class BrightnessEffect implements SingleValueParameterizableEffect {
    private float parameterValue;

    public BrightnessEffect() {
        this.parameterValue = 0;
    }

    @Override
    public Pixel[][] apply(Pixel[][] image, String fileName) {
        return BrightnessInterface.applyBrightness(image, this.parameterValue);
    }

    @Override
    public void setParameterValue(float parameterValue) throws IllegalParameterException {
        if (parameterValue < 0 || parameterValue > 200) {
            throw new IllegalParameterException("Parameter value must be in the range -200 to 200.");
        }
        this.parameterValue = parameterValue;
    }
}
