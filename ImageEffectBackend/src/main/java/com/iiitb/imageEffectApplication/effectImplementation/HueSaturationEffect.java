package com.iiitb.imageEffectApplication.effectImplementation;

import com.iiitb.imageEffectApplication.exception.IllegalParameterException;
import com.iiitb.imageEffectApplication.libraryInterfaces.HueSaturationInterface;
import com.iiitb.imageEffectApplication.libraryInterfaces.Pixel;
import com.iiitb.imageEffectApplication.service.LoggingService;
import com.iiitb.imageEffectApplication.baseEffects.TwoValueParameterizableEffect;

public class HueSaturationEffect implements TwoValueParameterizableEffect{
    private float hueAmount;
    private float saturationAmount;

    public HueSaturationEffect() {
        this.hueAmount = 0;
        this.saturationAmount = 0;
    } 

    @Override
    public Pixel[][] apply(Pixel[][] image, String fileName) {
        return HueSaturationInterface.applyHueSaturation(image, this.hueAmount, this.saturationAmount);
    }
    @Override
    public void setParameterValue(float hueAmount, float saturationAmount) throws IllegalParameterException {
        if (hueAmount < 0 || hueAmount > 100) {
            throw new IllegalParameterException("Hue amount should be in the range 0 to 100");
        }
        if (saturationAmount < 0 || saturationAmount > 100) {
            throw new IllegalParameterException("Saturation amount should be in the range 0 to 100");
        }
        this.hueAmount = hueAmount;
        this.saturationAmount = saturationAmount;
    }
}
