# ImageEditor-Website

## Table of Contents

- [Installation](#installation)
- [Features](#features)
- [Backend](#backend)
- [Usage](#usage)
- [Running_The_Backend](#running_the_backend)
- [Frontend](#ImageEffectFrontend)

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

## Running_The_Backend

- Navigate to the backend folder.

   ```bash
   cd .\ImageEffectBackend\
- Start WSL.

   ```bash
   wsl
- Clean the project directory.

   ```bash
   make clean
- Run the Makefile.

   ```bash
   make
- Clean the Maven project directory.

   ```bash
   mvn clean

- Build the Maven package.

   ```bash
   mvn package
- Create a folder for running the Mongo DB server.

   ```bash
   mkdir mongo_database
- Open a new terminal and navigate to the recently made folder.

   ```bash
   cd .\ImageEffectBackend\mongo_database
- Run the Mongo DB server.

   ```bash
   mongod --dbpath . --port 27017
- Now open Mongo DB Compass GUI and press **CONNECT**.
- Now navigate to the first terminal.
- Run the SpringBoot backend server.

   ```bash
   java -jar target/driveProject-0.0.1-SNAPSHOT.jar

- If something goes wrong, then run the following Maven command.

  ```bash
  mvn clean install

# ImageEffectFrontend

## Overview

The `ImageEffectFrontend` component is a robust Angular-based frontend (version 14.2.10) for the ImageEditor-Website, offering an intuitive and feature-rich interface for image editing. This section provides an overview of the project structure, key features, and technologies used.


## Technologies Used

- **Angular:** The frontend is built using the Angular framework, providing a modular and scalable architecture.

- **TypeScript:** The primary language used for building Angular applications, offering static typing for enhanced code reliability.

- **HTML and SCSS:** Structuring and styling the user interface for a seamless and visually appealing experience.

- **RxJS:** Utilized for handling asynchronous operations and events efficiently.

- **Angular Forms:** Employed for building dynamic and interactive forms, crucial for user input.

- **Angular Services:** Used to handle data and communication with the backend.

- **Angular Router:** Facilitates navigation within the application, enabling a smooth user experience.

- **DOM Sanitization:** Ensures the security of dynamically generated URLs and content.

## Key Features

- **Modular Structure:** The frontend is organized into modular components, ensuring maintainability and scalability.

- **Responsive Design:** The user interface is designed to be responsive, catering to various screen sizes and devices.

- **Image Upload:** Users can effortlessly upload source images for applying diverse image effects.

- **Effect Application:** The frontend seamlessly communicates with the backend to apply effects such as brightness, contrast, rotation, and more.

- **Result Display:** The resulting images are dynamically displayed, allowing users to preview and download the edited images.

- **Download Functionality:** Users can download the edited images with ease using the provided download buttons.

- **User-friendly Interface:** The frontend is designed to be intuitive, enabling users to navigate and interact with the application effortlessly.

## Getting Started

### 1. Clone the repository:

```bash
git clone https://github.com/KetanGhungralekar/ImageEditor-Website
```

### 2. Install dependencies using npm:

```bash
npm install
```
### 3. Navigate to the ImageEffectFrontend directory:

```bash
cd .\ImageEffectFrontend\
```
### 4. Start the development server:

```bash
ng serve
```
The application should be accessible at http://localhost:4200/ in your web browser.

### Code Scaffolding
Generate a new component using the following command:
```bash
ng generate component component-name
```

### Build
Run the following command to build the project. The build artifacts will be stored in the dist/ directory.
```bash
ng build
```

### Running Unit Tests
Execute the unit tests via Karma with the following command:
``` bash
ng test
```

### Running End-to-End Tests
Execute the end-to-end tests via a platform of your choice:
```bash
ng e2e
```

### Further Help
For more help on the Angular CLI, use the following command:
```bash
ng help
```
or you can go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli)
  page.



