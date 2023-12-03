#include "Invert.h"
#include <algorithm>
#include <vector>
#include <iostream>
#include <cmath>
using namespace std;
void applyInvert(vector<vector<Pixel>> &image) {
    // Iterate through each pixel in the image
    for (int i = 0; i < image.size(); ++i) {
        for (int j = 0; j < image[i].size(); ++j) {
            // Invert each RGB component by subtracting it from the maximum intensity
            image[i][j].r = 255 - image[i][j].r;
            image[i][j].g = 255 - image[i][j].g;
            image[i][j].b = 255 - image[i][j].b;
        }
    }
}