package com.iiitb.imageEffectApplication.effectImplementation;

import com.iiitb.imageEffectApplication.baseEffects.Two_ValueDiscreteEffect;
import com.iiitb.imageEffectApplication.libraryInterfaces.FlipInterface;
import com.iiitb.imageEffectApplication.libraryInterfaces.Pixel;
import com.iiitb.imageEffectApplication.service.LoggingService;
import com.iiitb.imageEffectApplication.exception.IllegalParameterException;

public class FlipEffect implements Two_ValueDiscreteEffect {
    private int horizontalFlipValue;
    private int verticalFlipValue;
    @Override
    public void setParameters(int param1, int param2) throws IllegalParameterException {
        if ((horizontalFlipValue != 0 && horizontalFlipValue != 1) || (verticalFlipValue != 1 && verticalFlipValue != 0)){
            throw new IllegalParameterException("Value can be only 1 or 0");
        }
        this.horizontalFlipValue = param1;
        this.verticalFlipValue = param2;
    }
    @Override
    public Pixel[][] apply(Pixel[][] image, String fileName) {
        // Implement the apply method here
        String option = "";
        if (this.horizontalFlipValue == 1){
            option += "Horizontal_Flip";
        }
        if (this.verticalFlipValue == 1){
            if (this.horizontalFlipValue == 1){
                option += " and Vertical_Flip";
            }
            else{
                option += "Horizontal_Flip";
            }
        }
        return FlipInterface.applyFlip(image, horizontalFlipValue, verticalFlipValue);
    }
}
