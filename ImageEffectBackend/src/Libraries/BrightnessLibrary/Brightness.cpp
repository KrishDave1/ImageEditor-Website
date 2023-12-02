#include "Brightness.h"

void applyBrightness(vector<vector<Pixel>> &image, float amount) {
    for (int i = 0; i < image.size(); i++)
    {
        for (int j = 0; j < image[i].size(); j++)
        {
            // Adjust each RGB component individually
            Pixel &pixel = image[i][j];
            pixel.r = static_cast<int>(pixel.r + 255 * amount);
            pixel.b = static_cast<int>(pixel.b + 255 * amount);
            pixel.g = static_cast<int>(pixel.g + 255 * amount);
            // Ensure that the values stay within the valid range [0, 255]
            pixel.r = max(0, min(255, pixel.r));
            pixel.g = max(0, min(255, pixel.g));
            pixel.b = max(0, min(255, pixel.b));
        }
        
    }
    
}


