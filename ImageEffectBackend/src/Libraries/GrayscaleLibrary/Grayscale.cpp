#include "Grayscale.h"
#include <bits/stdc++.h>

void applyGrayScale(vector<vector<Pixel>> &imageVector)
{
    // Iterate through each pixel in the image
    for (int i = 0; i < imageVector.size(); ++i)
    {
        for (int j = 0; j < imageVector[i].size(); ++j)
        {
            // Calculate the average of RGB components
            int average = (imageVector[i][j].r + imageVector[i][j].g + imageVector[i][j].b) / 3;

            // Set all RGB components to the calculated average
            imageVector[i][j].r = average;
            imageVector[i][j].g = average;
            imageVector[i][j].b = average;
        }
    }
}