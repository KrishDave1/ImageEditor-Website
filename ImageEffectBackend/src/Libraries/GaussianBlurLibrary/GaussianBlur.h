#ifndef GAUSSIAN_BLUR_H
#define GAUSSIAN_BLUR_H
#include "../Pixel.h"
#include<vector>

using namespace std;

void applyGaussianBlur(vector<vector<Pixel>> &image, float radius);

#endif