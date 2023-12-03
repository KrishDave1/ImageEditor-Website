#include "Flip.h"
#include<vector>
#include "../Pixel.h"
#include<iostream>
#include<string>
#include<fstream>
using namespace std;
#include<algorithm>

void applyFlip(vector<vector<Pixel>> &image, int horizontalFlipValue, int verticalFlipValue) {
    // Get image dimensions
    int rows = image.size();
    int columns = image[0].size();

    // Apply horizontal flip if specified
    if (horizontalFlipValue == 1) {
        for (int i = 0; i < rows; ++i) {
            reverse(image[i].begin(), image[i].end());
        }
    }

    // Apply vertical flip if specified
    if (verticalFlipValue == 1) {
        reverse(image.begin(), image.end());
    }
}


