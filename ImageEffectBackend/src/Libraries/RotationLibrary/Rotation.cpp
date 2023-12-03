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

void applyRotation(vector<vector<Pixel>> &image, int value) {
    // Convert the angle to radians
    float angleRad = static_cast<float>(value) * M_PI / 180.0;

    // Calculate the center of the image
    float centerX = static_cast<float>(image.size() - 1) / 2.0;
    float centerY = static_cast<float>(image[0].size() - 1) / 2.0;

    // Create a new image buffer for the rotated image
    vector<vector<Pixel>> rotatedImage(image.size(), vector<Pixel>(image[0].size()));

    // Iterate through each pixel in the original image
    for (int i = 0; i < image.size(); ++i) {
        for (int j = 0; j < image[i].size(); ++j) {
            // Calculate new coordinates after rotation
            float x = static_cast<float>(i) - centerX;
            float y = static_cast<float>(j) - centerY;
            float newX = x * cos(angleRad) - y * sin(angleRad) + centerX;
            float newY = x * sin(angleRad) + y * cos(angleRad) + centerY;

            // Calculate the corresponding coordinates in the original image
            int originalX = static_cast<int>(newX + 0.5);
            int originalY = static_cast<int>(newY + 0.5);

            // Check boundaries to avoid out-of-bounds access
            if (originalX >= 0 && originalX < image.size() && originalY >= 0 && originalY < image[0].size()) {
                // Copy pixel values to the rotated image
                rotatedImage[i][j] = image[originalX][originalY];
            }
        }
    }
    // Update the original image with the rotated image
    image = rotatedImage;
}

