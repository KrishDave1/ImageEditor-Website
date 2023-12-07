#include "Contrast.h"

// void applyContrast(vector<vector<Pixel>> &image, float amount) {
//     // Iterate through each pixel in the image
//     for (int i = 0; i < image.size(); ++i) {
//         for (int j = 0; j < image[i].size(); ++j) {
//             // Adjust the contrast of each pixel
//             Pixel &pixel = image[i][j];

//             // Calculate midpoint for each channel
//             int midR = 255 / 2;
//             int midG = 255 / 2;
//             int midB = 255 / 2;

//             // Adjust each RGB component individually
//             pixel.r = static_cast<int>((pixel.r - midR) * 1.275 * (amount - 100) + midR);
//             pixel.g = static_cast<int>((pixel.g - midG) * 1.275 * (amount - 100) + midG);
//             pixel.b = static_cast<int>((pixel.b - midB) * 1.275 * (amount - 100) + midB);

//             // Ensure that the values stay within the valid range [0, 255]
//             pixel.r = max(0, min(255, pixel.r));
//             pixel.g = max(0, min(255, pixel.g));
//             pixel.b = max(0, min(255, pixel.b));
//         }
//     }
// }
void applyContrast(vector<vector<Pixel>> &image, float amount) {
    // Calculate the factor to adjust contrast
    float factor = (259.0 * ((amount-100) + 255.0)) / (255.0 * (259.0 - (amount-100)));

    // Iterate through each pixel in the image
    for (int i = 0; i < image.size(); ++i) {
        for (int j = 0; j < image[i].size(); ++j) {
            // Adjust the contrast of each pixel
            Pixel &pixel = image[i][j];

            // Adjust each RGB component individually
            pixel.r = static_cast<int>((factor * (pixel.r - 128.0)) + 128.0);
            pixel.g = static_cast<int>((factor * (pixel.g - 128.0)) + 128.0);
            pixel.b = static_cast<int>((factor * (pixel.b - 128.0)) + 128.0);

            // Ensure that the values stay within the valid range [0, 255]
            pixel.r = max(0, min(255, pixel.r));
            pixel.g = max(0, min(255, pixel.g));
            pixel.b = max(0, min(255, pixel.b));
        }
    }
}
