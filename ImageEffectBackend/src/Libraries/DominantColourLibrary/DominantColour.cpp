#include "DominantColour.h"
#include <unordered_map>

void applyDominantColour(vector<vector<Pixel>> &image) {
    // Use an unordered_map to count the occurrences of each color
    std::unordered_map<int, int> colorCount;

    // Iterate through each pixel in the image
    for (int i = 0; i < image.size(); ++i) {
        for (int j = 0; j < image[i].size(); ++j) {
            // Convert RGB values to a unique identifier for hashing
            int colorKey = (image[i][j].r << 16) | (image[i][j].g << 8) | image[i][j].b;

            // Increment the count for this color
            colorCount[colorKey]++;
        }
    }

    // Find the color with the highest count (dominant color)
    int dominantColorKey = 0;
    int maxCount = 0;
    for (const auto &entry : colorCount) {
        if (entry.second > maxCount) {
            maxCount = entry.second;
            dominantColorKey = entry.first;
        }
    }

    // Extract RGB values from the dominant color key
    int avgR = (dominantColorKey >> 16) & 0xFF;
    int avgG = (dominantColorKey >> 8) & 0xFF;
    int avgB = dominantColorKey & 0xFF;

    // Set all pixels to the dominant color
    for (int i = 0; i < image.size(); ++i) {
        for (int j = 0; j < image[i].size(); ++j) {
            image[i][j].r = avgR;
            image[i][j].g = avgG;
            image[i][j].b = avgB;
        }
    }
}


