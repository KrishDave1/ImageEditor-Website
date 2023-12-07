#include "Brightness.h"
#include<vector>
#include<algorithm>
using namespace std;

void applyBrightness(vector<vector<Pixel>> &image, float amount) {
    for (int i = 0; i < image.size(); i++)
    {
        for (int j = 0; j < image[i].size(); j++)
        {
            // Adjust each RGB component individually
            Pixel &pixel = image[i][j];
            pixel.r = static_cast<int>(pixel.r + 1.75 * (amount-100));
            pixel.b = static_cast<int>(pixel.b + 1.75 * (amount-100));
            pixel.g = static_cast<int>(pixel.g + 1.75 * (amount-100));
            // Ensure that the values stay within the valid range [0, 255]
            pixel.r = max(0, min(255, pixel.r));
            pixel.g = max(0, min(255, pixel.g));
            pixel.b = max(0, min(255, pixel.b));
        }
    }
}


