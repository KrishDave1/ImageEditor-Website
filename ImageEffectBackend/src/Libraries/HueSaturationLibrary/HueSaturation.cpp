#include "HueSaturation.h"
#include <cmath>
#include <vector>
using namespace std;

void applyHueSaturation(vector<vector<Pixel>> &image, float saturationValue, float hueValue)
{
    // Iterate through each pixel in the image
    for (int i = 0; i < image.size(); ++i)
    {
        for (int j = 0; j < image[i].size(); ++j)
        {
            // Convert RGB to HSV
            float r = image[i][j].r / 255.0;
            float g = image[i][j].g / 255.0;
            float b = image[i][j].b / 255.0;

            float cmax = fmax(fmax(r, g), b);
            float cmin = fmin(fmin(r, g), b);
            float delta = cmax - cmin;

            // Calculate hue
            float hue = 0.0;
            if (delta != 0.0)
            {
                if (cmax == r)
                {
                    hue = fmod((g - b) / delta, 6.0);
                }
                else if (cmax == g)
                {
                    hue = ((b - r) / delta) + 2.0;
                }
                else
                {
                    hue = ((r - g) / delta) + 4.0;
                }
            }

            hue *= 60.0;

            if (hue < 0.0)
            {
                hue += 360.0;
            }

            // Apply adjustments
            hue += hueValue;

            // Ensure hue is in the range [0, 360)
            if (hue < 0.0)
            {
                hue += 360.0;
            }
            else if (hue >= 360.0)
            {
                hue -= 360.0;
            }

            // Calculate saturation
            float saturation = (cmax == 0.0) ? 0.0 : (delta / cmax);

            // Apply saturation adjustment
            saturation += saturationValue;

            // Ensure saturation is in the range [0, 1]
            saturation = fmin(fmax(saturation, 0.0), 1.0);

            // Convert back to RGB
            float x = saturation * (1.0 - fabs(fmod(hue / 60.0, 2.0) - 1.0));
            float m = 1.0 - saturation;
            float r1, g1, b1;

            if (hue < 60.0)
            {
                r1 = 1.0;
                g1 = x;
                b1 = 0.0;
            }
            else if (hue < 120.0)
            {
                r1 = x;
                g1 = 1.0;
                b1 = 0.0;
            }
            else if (hue < 180.0)
            {
                r1 = 0.0;
                g1 = 1.0;
                b1 = x;
            }
            else if (hue < 240.0)
            {
                r1 = 0.0;
                g1 = x;
                b1 = 1.0;
            }
            else if (hue < 300.0)
            {
                r1 = x;
                g1 = 0.0;
                b1 = 1.0;
            }
            else
            {
                r1 = 1.0;
                g1 = 0.0;
                b1 = x;
            }

            // Clamp values to [0, 1]
            r1 = fmin(fmax(r1, 0.0), 1.0);
            g1 = fmin(fmax(g1, 0.0), 1.0);
            b1 = fmin(fmax(b1, 0.0), 1.0);

            // Convert back to RGB and set the values
            image[i][j].r = static_cast<int>(r1 * 255.0);
            image[i][j].g = static_cast<int>(g1 * 255.0);
            image[i][j].b = static_cast<int>(b1 * 255.0);
        }
    }
}
