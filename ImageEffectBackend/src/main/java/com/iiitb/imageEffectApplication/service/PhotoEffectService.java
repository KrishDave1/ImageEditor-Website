package com.iiitb.imageEffectApplication.service;

import com.iiitb.imageEffectApplication.effectImplementation.*;
import com.iiitb.imageEffectApplication.exception.IllegalParameterException;
import com.iiitb.imageEffectApplication.libraryInterfaces.Pixel;
import com.iiitb.imageEffectApplication.utils.ProcessingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ConcurrentModificationException;

@Service
public class PhotoEffectService {

    @Autowired
    private ProcessingUtils processingUtils;

    @Autowired
    private LoggingService loggingService;

    public ResponseEntity<byte[]> applyHueSaturationEffect(float hueAmount, float saturationAmount, MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();
            // ACTUAL WORK STARTS HERE

            // TODO
            HueSaturationEffect hueSaturationEffect = new HueSaturationEffect();
            try {
                hueSaturationEffect.setParameterValue(hueAmount, saturationAmount);
            } catch (IllegalParameterException e) {
                System.err.println("Error received: " + e.getMessage());
            }
            Pixel[][] modifiedImage = hueSaturationEffect.apply(inputImage, imageName, loggingService); // Replace this with actual modified image
            return processingUtils.postProcessing(modifiedImage);
        } // Replace this with actual modified image
          // ACTUAL WORK ENDS HERE
        catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<byte[]> applyBrightnessEffect(float amount, MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();




            // ACTUAL WORK STARTS HERE
            // TODO
            BrightnessEffect brightnessEffect = new BrightnessEffect();
            try {
                brightnessEffect.setParameterValue(amount);
            } catch (IllegalParameterException e) {
                System.err.println("Error received: " + e.getMessage());
            }
            Pixel[][] modifiedImage = brightnessEffect.apply(inputImage, imageName, loggingService); // Replace this with actual modified image
            // ACTUAL WORK ENDS HERE

            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<byte[]> applyContrastEffect(float amount, MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();



            // ACTUAL WORK STARTS HERE

            // TODO
            ContrastEffect contrastEffect = new ContrastEffect();
            try {
                contrastEffect.setParameterValue(amount);
            } catch (IllegalParameterException e) {
                System.err.println("Received an error: " + e.getMessage());
            }
            Pixel[][] modifiedImage = contrastEffect.apply(inputImage, imageName, loggingService); // Replace this with actual modified image

            // ACTUAL WORK ENDS HERE



            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<byte[]> applyFlipEffect(MultipartFile imageFile, int horizontalFlipValue, int verticalFlipValue) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();


            // ACTUAL WORK STARTS HERE

            // TODO
            FlipEffect flipEffect = new FlipEffect();
            try {
                flipEffect.setParameters(horizontalFlipValue, verticalFlipValue);
            }
            catch(IllegalParameterException e){
                System.err.println("Recieved Error " + e.getMessage());
            }
            Pixel[][] modifiedImage = flipEffect.apply(inputImage, imageName, loggingService);
            // ACTUAL WORK ENDS HERE




            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<byte[]> applyGaussianBlurEffect(float radius, MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();


            // ACTUAL WORK STARTS HERE

            // TODO
            GaussianBlurEffect gaussianBlurEffect = new GaussianBlurEffect();
            try {
                gaussianBlurEffect.setParameterValue(radius);
            } catch (IllegalParameterException e) {
                System.err.println("Error received is: " + e.getMessage());
            }
            Pixel[][] modifiedImage = gaussianBlurEffect.apply(inputImage, imageName, loggingService); // Replace this with actual modified image

            // ACTUAL WORK ENDS HERE



            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<byte[]> applyGrayscaleEffect(MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();


            // ACTUAL WORK STARTS HERE

            // TODO

            GrayscaleEffect grayscaleEffect = new GrayscaleEffect();
            Pixel[][] modifiedImage = grayscaleEffect.apply(inputImage, imageName, loggingService); // Replace this with actual modified image

            // ACTUAL WORK ENDS HERE

            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<byte[]> applyInvertEffect(MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();

            // ACTUAL WORK STARTS HERE

            // TODO
            InvertEffect invertEffect = new InvertEffect();
            Pixel[][] modifiedImage = invertEffect.apply(inputImage, imageName, loggingService); // Replace this with actual modified image
            // ACTUAL WORK ENDS HERE

            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<byte[]> applyRotationEffect(int value, MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();

            // ACTUAL WORK STARTS HERE

            // TODO
            RotationEffect rotationEffect = new RotationEffect();
            try {
                rotationEffect.selectOptionValue(imageName, value);
            }
            catch (IllegalParameterException e){
                System.err.println("Recieved Error " + e.getMessage());
            }
            Pixel[][] modifiedImage = rotationEffect.apply(inputImage, imageName, loggingService);
            // ACTUAL WORK ENDS HERE
            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<byte[]> applySepiaEffect(MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();

            // ACTUAL WORK STARTS HERE

            // TODO
            SepiaEffect sepiaEffect = new SepiaEffect();

            Pixel[][] modifiedImage = sepiaEffect.apply(inputImage, imageName, loggingService); // Replace this with
                                                                                                // actual modified image

            // ACTUAL WORK ENDS HERE

            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<byte[]> applySharpenEffect(float amount, MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();

            // ACTUAL WORK STARTS HERE
            SharpenEffect sharpenEffect = new SharpenEffect();
            // TODO
            try {
                sharpenEffect.setParameterValue(amount);
            } catch (IllegalParameterException e) {
                System.err.println("Error received: " + e.getMessage());
            }
            Pixel[][] modifiedImage = sharpenEffect.apply(inputImage, imageName, loggingService); // Replace this with actual modified image
            // ACTUAL WORK ENDS HERE
            return processingUtils.postProcessing(modifiedImage);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<byte[]> getDominantColour(MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();

            // ACTUAL WORK STARTS HERE

            // TODO
            Pixel[][] modifiedImage = inputImage; // Replace this with actual modified image

            // ACTUAL WORK ENDS HERE

            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
