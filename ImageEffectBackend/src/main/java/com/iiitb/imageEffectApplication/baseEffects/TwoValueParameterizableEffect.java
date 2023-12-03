package com.iiitb.imageEffectApplication.baseEffects;

import com.iiitb.imageEffectApplication.exception.IllegalParameterException;

// Could be things like changing hue, saturation, brightness, contrast (i.e. only single parameter)
public interface TwoValueParameterizableEffect extends PhotoEffect {
    void setParameterValue(float parameter1Value , float parameter2Value) throws IllegalParameterException;
}
