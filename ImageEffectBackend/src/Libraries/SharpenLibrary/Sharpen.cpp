#include "Sharpen.h"
#include <algorithm>
#include <vector>
#include <iostream>
#include <cmath>
using namespace std;
void applySharpen(vector<vector<Pixel>> &image, float sliderValue)
{
    // Normalize the slider value to a range between 0 and 1
    float normalizedSliderValue = sliderValue / 100.0f;
    
    // Adjust the default sharpening amount relative to the slider value
    float defaultAmount = 50.0f; // Default sharpening amount
    float amount = normalizedSliderValue * defaultAmount;

    // Normalize the amount to a range between 0 and 1
    float normalizedAmount = amount / 100.0f;

    // Define a sharpening kernel using the normalized amount
    float kernel[3][3] = {
        {0, -normalizedAmount, 0},
        {-normalizedAmount, 1 + 4 * normalizedAmount, -normalizedAmount},
        {0, -normalizedAmount, 0}};

    // Iterate through each pixel in the image
    for (int i = 1; i < image.size() - 1; ++i)
    {
        for (int j = 1; j < image[i].size() - 1; ++j)
        {
            // Apply convolution
            int newR = 0, newG = 0, newB = 0;

            for (int k = -1; k <= 1; ++k)
            {
                for (int l = -1; l <= 1; ++l)
                {
                    newR += static_cast<int>(kernel[k + 1][l + 1] * image[i + k][j + l].r);
                    newG += static_cast<int>(kernel[k + 1][l + 1] * image[i + k][j + l].g);
                    newB += static_cast<int>(kernel[k + 1][l + 1] * image[i + k][j + l].b);
                }
            }
            // Ensure values are in the valid range [0, 255]
            newR = std::min(std::max(newR, 0), 255);
            newG = std::min(std::max(newG, 0), 255);
            newB = std::min(std::max(newB, 0), 255);
            // Set the new RGB values
            image[i][j].r = newR;
            image[i][j].g = newG;
            image[i][j].b = newB;
        }
    }
}