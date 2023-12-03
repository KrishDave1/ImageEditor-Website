package com.iiitb.imageEffectApplication.baseEffects;
import com.iiitb.imageEffectApplication.exception.IllegalParameterException;

public interface Two_ValueDiscreteEffect extends PhotoEffect{
    void setParameters(int horizontalFlipValue,int verticalFlipValue) throws IllegalParameterException;
}
