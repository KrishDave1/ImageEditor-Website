#include "Rotation.h"
#include <vector>
#include <iostream>
#include <string>
#include <fstream>
#include <sstream>
#include <algorithm>
#include <cmath>
using namespace std;
#define M_PI 3.14159265358979323846
void rotatePixel(int& x, int& y, int width, int height, int angleDegrees) {
    if (angleDegrees == 0) return; // If the angle is 0, no need to rotate

    float centerX = static_cast<float>(width - 1) / 2.0;
    float centerY = static_cast<float>(height - 1) / 2.0;

    float angleRad = static_cast<float>(angleDegrees) * M_PI / 180.0;

    float newX = cos(angleRad) * (x - centerX) - sin(angleRad) * (y - centerY) + centerX;
    float newY = sin(angleRad) * (x - centerX) + cos(angleRad) * (y - centerY) + centerY;

    x = static_cast<int>(round(newX));
    y = static_cast<int>(round(newY));
}

void applyRotation(vector<vector<Pixel>>& image, int value) {
    int width = static_cast<int>(image.size());
    if (width == 0) return;
    int height = static_cast<int>(image[0].size());

    float angleRad = static_cast<float>(value) * M_PI / 180.0;

    float centerX = static_cast<float>(width - 1) / 2.0;
    float centerY = static_cast<float>(height - 1) / 2.0;

    vector<vector<Pixel>> tempImage = image;

    for (int i = 0; i < width; ++i) {
        for (int j = 0; j < height; ++j) {
            int x = i;
            int y = j;

            rotatePixel(x, y, width, height, value);

            // Bilinear interpolation
            int x0 = floor(x);
            int x1 = x0 + 1;
            int y0 = floor(y);
            int y1 = y0 + 1;

            if (x0 >= 0 && x1 < width && y0 >= 0 && y1 < height) {
                float xFraction = x - x0;
                float yFraction = y - y0;

                for (int c = 0; c < 3; ++c) {
                    float top = tempImage[x0][y0].r * (1 - xFraction) + tempImage[x1][y0].r * xFraction;
                    float bottom = tempImage[x0][y1].r * (1 - xFraction) + tempImage[x1][y1].r * xFraction;
                    float pixelValue = top * (1 - yFraction) + bottom * yFraction;
                    image[i][j].r = static_cast<int>(pixelValue);

                    top = tempImage[x0][y0].g * (1 - xFraction) + tempImage[x1][y0].g * xFraction;
                    bottom = tempImage[x0][y1].g * (1 - xFraction) + tempImage[x1][y1].g * xFraction;
                    pixelValue = top * (1 - yFraction) + bottom * yFraction;
                    image[i][j].g = static_cast<int>(pixelValue);

                    top = tempImage[x0][y0].b * (1 - xFraction) + tempImage[x1][y0].b * xFraction;
                    bottom = tempImage[x0][y1].b * (1 - xFraction) + tempImage[x1][y1].b * xFraction;
                    pixelValue = top * (1 - yFraction) + bottom * yFraction;
                    image[i][j].b = static_cast<int>(pixelValue);
                }
            }
        }
    }
}