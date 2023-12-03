#ifndef HUE_SATURATION_H
#define HUE_SATURATION_H
#include "../Pixel.h"
#include <vector>
using namespace std;

void applyHueSaturation(vector< vector<Pixel> > &image, float saturationValue, float hueValue);

#endif