# ImageEditor-Website

## Table of Contents

- [Overview](#overview)
- [Backend Architecture](#backend-architecture)
- [Image Editing Functionality](#image-editing-functionality)
- [Key Features](#key-features)
- [Challenges Faced](#challenges-faced)
- [Acknowledgements](#acknowledgements)
- [Authors](#authors)
- [Installation](#image-editor-web-app-installation)
- [Backend setup](#backend-setup)
- [Frontend](#frontend)
- [Contributions](#contributions)

## Overview

ImageEditor-Website is a sophisticated web application designed for image manipulation, leveraging advanced technologies for both backend and frontend. The backend, powered by Spring Boot, seamlessly integrates with C++ libraries via JNI, ensuring high-performance image processing. MongoDB serves as the storage solution for effect logs, adding robustness to the application

## Image Editing Functionality

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

## Backend Architecture

The backend of ImageEditor-Website is powered by **Spring Boot**, providing a robust foundation for the application. 
- **JNI** (Java Native Interface) is used to access C++ libraries, ensuring fast and efficient image modification. 
- **MongoDB** is employed to store logs of the effects applied to images.

## Key Features
- **Modular Structure:** The frontend is organized into modular components, ensuring maintainability and scalability.

- **Responsive Design:** The user interface is designed to be responsive, catering to various screen sizes and devices.

- **Image Upload:** Users can effortlessly upload source images for applying diverse image effects.

- **Effect Application:** The frontend seamlessly communicates with the backend to apply effects such as brightness, contrast, rotation, and more.

- **Result Display:** The resulting images are dynamically displayed, allowing users to preview and download the edited images.

- **Download Functionality:** Users can download the edited images with ease using the provided download buttons.

- **User-friendly Interface:** The frontend is designed to be intuitive, enabling users to navigate and interact with the application effortlessly.

## Challenges Faced
- After refreshing the logs page, the logs were disappearing. Hence, we felt the need of having a database to store the logs to make them persistent. Thus, we used Mongo DB as our database.
- Initially we were not able to integrate Mongo DB with our backend. We then cleaned and re installed the Maven project directory which solved the issue.
- The Dominant Colour effect was initially not present in the Makefile. We added the necessary statements in the Makefile to solve the issue. We made a new folder in the CPP libraries for the Dominant Colour effect. In that folder we made the DominantColourInterface.cpp file which has the required JNI code.
- Initially, we used Executors.callable() method to implement multithreading. However, it did not work. Thus, we switched to using Callable and Future to implement multithreading.

## Acknowledgements
We would like to express our sincere gratitude to our professor and teaching assistants for their invaluable guidance and support throughout the completion of this project.

Additionally, we acknowledge and appreciate the following external resources that proved instrumental in the development of our project:

- https://docs.spring.io/spring-data/mongodb/docs/1.2.0.RELEASE/reference/html/mongo.repositories.html
- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-mongodb
- https://www.baeldung.com/java-runnable-callable
- https://angular.io/api
- https://medium.com/@samuelcatalano/connecting-spring-boot-to-mongodb-a-step-by-step-guide-b9f2fd9e872d
- https://www.mongodb.com/compatibility/spring-boot

## Image editor web app: Installation


Clone the repository:

   ```bash
   git clone https://github.com/KetanGhungralekar/ImageEditor-Website
   ```
For setting up backend: [Skip to here](#backend-setup)   
For setting up frontend: [Skip to here](#frontend-setup)   


## Backend setup
Before proceeding with the backend setup, ensure you have the following prerequisites installed:

  If you are using windows you will need WSL
  + [Windows Subsystem for Linux (WSL)](https://docs.microsoft.com/en-us/windows/wsl/install)

+ [MongoDB](https://docs.mongodb.com/manual/installation/)

- Navigate to the backend folder.

   ```bash
   cd .\ImageEffectBackend\
   ```

- Start WSL.

   ```bash
   wsl
   ```
- Clean the project directory.

   ```bash
   make clean
  ```
- Run the Makefile.

   ```bash
   make
  ```
- Clean the Maven project directory.

   ```bash
   mvn clean
  ```
- Build the Maven package.

   ```bash
   mvn package
   ```
- Create a folder for running the Mongo DB server.

   ```bash
   mkdir mongo_database
   ```
- Open a new terminal and navigate to the recently made folder.

   ```bash
   cd .\ImageEffectBackend\mongo_database
   ```
- Run the Mongo DB server.

   ```bash
   mongod --dbpath . --port 27017
   ```
- Now open Mongo DB Compass GUI and press **CONNECT**.
- Now navigate to the first terminal.
- Run the SpringBoot backend server.

  ```bash
   java -jar target/driveProject-0.0.1-SNAPSHOT.jar
  ```

  This command executes the JAR file, which contains all the compiled Java classes and dependencies needed to run the Spring Boot application. Learn more about [Java JAR files](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/jar.html).
- If something goes wrong, then run the following Maven command.

  ```bash
  mvn clean install
  ```
Return to [Installation](#image-editor-web-app-installation)





# Frontend

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


## Frontend setup

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
Return to [Installation](#image-editor-web-app-installation)

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

## Authors

- [Ketan Ghungralekar](https://www.github.com/KetanGhungralekar/)
- [Krish Dave](https://github.com/KrishDave1)
- [Valmik Belgaonkar](https://github.com/valmikGit)
- [Vedant Mangrulkar](https://github.com/MVedant21)
- [Abhinav Deshpande](https://www.github.com/Abhinav-gh/)

### Contributions

- Valmik : Integrated Mongo DB with the SpringBoot backend to make the logs persistent. Made changes in the Makefile to accommodate the Dominant Colour effect. Made the required CPP library, consisting of the appropriate CPP header file and the file containing the CPP function for processing the image, for the Dominant colour effect and implemented the required JNI code. Effects implemented are Brightness and Dominant Colour.
- Krish : Resolved all the issues related to project setup, Maven setup, WSL setup and GitHub. Helped in resolving errors faced while implementing multithreading. Made changes in the Makefile to accommodate the Dominant Colour effect. Made a new interface, named TwoValueParameterizableEffect, required for the Hue Saturation effect. Effects implemented are Hue Saturation and Gaussian blur.
- Ketan : Cracked the logic of how to implement multithreading and implemented it in the Flip effect which the others then referred to while implementing the effects assigned to them. Made a new interface, named Two_ValueDiscreteEffect, required for the Flip effect class. Effects implemented are Flip and Sharpen.
- Vedant : Did all the additional documentation required for all the effects and the project in general. Tested the functionality of all the effects. Effects implemented are Invert, Sepia and Rotation.
- Abhinav : Made the README.md file. Tested the functionality of all the effects. Calibrated the filters by changing the CPP code wherever necessary. Altered the sensitivity of the sliders wherever required. Effects implemented are Contrast and Grayscale.
