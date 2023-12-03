#include "Rotation.h"

#include <cmath>
#include<vector>
#define M_PI 3.14159265358979323846
using namespace std;

void applyRotation(vector<vector<Pixel>> &image, int value)
{
    // Convert the angle to radians
    float angleRad = static_cast<float>(value) * M_PI / 180.0;

    // Calculate the center of the image
    float centerX = static_cast<float>(image.size() - 1) / 2.0;
    float centerY = static_cast<float>(image[0].size() - 1) / 2.0;

    // Iterate through each pixel in the image
    for (int i = 0; i < image.size(); ++i)
    {
        for (int j = 0; j < image[i].size(); ++j)
        {
            // Calculate new coordinates after rotation
            float x = static_cast<float>(i) - centerX;
            float y = static_cast<float>(j) - centerY;

            float newX = x * cos(angleRad) - y * sin(angleRad) + centerX;
            float newY = x * sin(angleRad) + y * cos(angleRad) + centerY;

            // Interpolate pixel values using the new coordinates (you may use more sophisticated interpolation)
            int pixelR = static_cast<int>(newX + 0.5) < 0 || static_cast<int>(newX + 0.5) >= image.size() || static_cast<int>(newY + 0.5) < 0 || static_cast<int>(newY + 0.5) >= image[i].size() ? 0 : image[static_cast<int>(newX + 0.5)][static_cast<int>(newY + 0.5)].r;

            int pixelG = static_cast<int>(newX + 0.5) < 0 || static_cast<int>(newX + 0.5) >= image.size() || static_cast<int>(newY + 0.5) < 0 || static_cast<int>(newY + 0.5) >= image[i].size() ? 0 : image[static_cast<int>(newX + 0.5)][static_cast<int>(newY + 0.5)].g;

            int pixelB = static_cast<int>(newX + 0.5) < 0 || static_cast<int>(newX + 0.5) >= image.size() || static_cast<int>(newY + 0.5) < 0 || static_cast<int>(newY + 0.5) >= image[i].size() ? 0 : image[static_cast<int>(newX + 0.5)][static_cast<int>(newY + 0.5)].b;

            // Set the pixel values in the rotated image
            image[i][j].r = pixelR;
            image[i][j].g = pixelG;
            image[i][j].b = pixelB;
        }
    }
}