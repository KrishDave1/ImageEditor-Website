#include "Sepia.h"
#include <bits/stdc++.h>

void applySepia(vector<vector<Pixel>> &image)
{
    // Iterate through each pixel in the image
    for (int i = 0; i < image.size(); ++i)
    {
        for (int j = 0; j < image[i].size(); ++j)
        {
            // Apply sepia tone transformation
            int originalR = image[i][j].r;
            int originalG = image[i][j].g;
            int originalB = image[i][j].b;

            int newR = static_cast<int>(0.393 * originalR + 0.769 * originalG + 0.189 * originalB);
            int newG = static_cast<int>(0.349 * originalR + 0.686 * originalG + 0.168 * originalB);
            int newB = static_cast<int>(0.272 * originalR + 0.534 * originalG + 0.131 * originalB);

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