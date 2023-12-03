#include "GaussianBlur.h"
#include<cmath>
#include<math.h>
using namespace std;
void applyGaussianBlur(vector<vector<Pixel>> &image, float radius)
{
    // Calculate the size of the kernel based on the given radius
    int kernelSize = static_cast<int>(ceil(6.0 * radius) + 1);

    // Ensure the kernel size is odd
    if (kernelSize % 2 == 0)
    {
        kernelSize++;
    }

    // Generate the 1D Gaussian kernel
    std::vector<float> kernel(kernelSize);
    float sigma = radius / 3.0;
    float sum = 0.0;

    for (int i = 0; i < kernelSize; ++i)
    {
        int x = i - kernelSize / 2;
        kernel[i] = exp(-(x * x) / (2.0 * sigma * sigma));
        sum += kernel[i];
    }

    // Normalize the kernel
    for (int i = 0; i < kernelSize; ++i)
    {
        kernel[i] /= sum;
    }

    // Apply horizontal convolution
    for (size_t i = 0; i < image.size(); ++i)
    {
        for (size_t j = 0; j < image[i].size(); ++j)
        {
            Pixel blurredPixel = {0, 0, 0};

            for (int k = 0; k < kernelSize; ++k)
            {
                int idx = j + k - kernelSize / 2;
                idx = std::max(0, std::min(static_cast<int>(image[i].size() - 1), idx));

                blurredPixel.r += static_cast<int>(kernel[k] * image[i][idx].r);
                blurredPixel.g += static_cast<int>(kernel[k] * image[i][idx].g);
                blurredPixel.b += static_cast<int>(kernel[k] * image[i][idx].b);
            }

            image[i][j] = blurredPixel;
        }
    }

    // Apply vertical convolution
    for (int j = 0; j < image[0].size(); ++j)
    {
        for (int i = 0; i < image.size(); ++i)
        {
            Pixel blurredPixel = {0, 0, 0};

            for (int k = 0; k < kernelSize; ++k)
            {
                int idx = i + k - kernelSize / 2;
                idx = std::max(0, std::min(static_cast<int>(image.size() - 1), idx));

                blurredPixel.r += static_cast<int>(kernel[k] * image[idx][j].r);
                blurredPixel.g += static_cast<int>(kernel[k] * image[idx][j].g);
                blurredPixel.b += static_cast<int>(kernel[k] * image[idx][j].b);
            }

            image[i][j] = blurredPixel;
        }
    }
}