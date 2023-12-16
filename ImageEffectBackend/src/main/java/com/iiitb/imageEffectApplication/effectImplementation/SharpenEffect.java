package com.iiitb.imageEffectApplication.effectImplementation;
import com.iiitb.imageEffectApplication.baseEffects.SingleValueParameterizableEffect;
import com.iiitb.imageEffectApplication.exception.IllegalParameterException;
import com.iiitb.imageEffectApplication.libraryInterfaces.Pixel;
import com.iiitb.imageEffectApplication.service.LoggingService;
import com.iiitb.imageEffectApplication.libraryInterfaces.SharpenInterface;

public class SharpenEffect implements SingleValueParameterizableEffect {
    private float value;
    public SharpenEffect() {
        this.value = 0;
    }
    @Override
    public void setParameterValue(float value) throws IllegalParameterException {
        if (value < 0 || value > 200) {
            throw new IllegalParameterException("Parameter value must be in the range -200 to 200.");
        }
        this.value = value;
    }
    @Override
    public Pixel[][] apply(Pixel[][] image, String fileName) {
        return SharpenInterface.applySharpen(image, this.value);
    }
}
