# ImageEditor-Website

## Table of Contents

- [Installation](#installation)
- [Features](#features)
- [Backend](#backend)

ImageEditor-Website is a web application that allows users to edit images using various effects. The application is built with Spring Boot for the backend, utilizes JNI to access C++ libraries for fast image modification, and stores effect logs in MongoDB.

## Features

- **Hue Saturation:** Adjust the hue and saturation of images.
- **Brightness:** Modify the brightness of images.
- **Contrast:** Change the contrast of images.
- **Flip:** Flip images horizontally or vertically.
- **Gaussian Blur:** Apply Gaussian blur to images.
- **Grayscale:** Convert images to grayscale.
- **Invert:** Invert colors of images.
- **Rotation:** Rotate images to the desired angle.
- **Sepia:** Apply a sepia tone to images.
- **Sharpen:** Enhance the sharpness of images.
- **Dominant Colour:** Identify and display the dominant color in images.

## Backend

The backend of ImageEditor-Website is powered by Spring Boot, providing a robust foundation for the application. JNI (Java Native Interface) is used to access C++ libraries, ensuring fast and efficient image modification. MongoDB is employed to store logs of the effects applied to images.

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/KetanGhungralekar/ImageEditor-Website

## Usage

- Access the ImageEditor-Website through your web browser.
- Upload an image to the platform.
- Apply various effects from the provided list.
- Save or download the edited image.
- Check the logs section to see your image effect application history.
