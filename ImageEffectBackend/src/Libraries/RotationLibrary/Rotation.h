#ifndef ROTATION_H
#define ROTATION_H
#include<vector>
#include "../Pixel.h"

using namespace std;

void applyRotation(vector<vector<Pixel>> &image, int value);
void rotatePixel(int& x, int& y, int width, int height, float angleRad);

#endif