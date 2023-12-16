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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
@Service
public class PhotoEffectService {
    Callable <Void> loggingTask;
    Callable <Pixel[][]> ImageEffect;
    Future <Pixel[][]> imagefuture;
    Future <Void> loggingfuture;
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    @Autowired
    private ProcessingUtils processingUtils;
    @Autowired
    private LoggingService loggingService;
    public ResponseEntity<byte[]> applyHueSaturationEffect(float hueAmount, float saturationAmount, MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();
            // TODO
            loggingTask = () -> {
                loggingService.addLog(imageName, imageName, imageName);
                System.out.println("djdj");
                System.out.println("hdhd");
                return null;
            };
            ImageEffect = () -> {
                System.out.println("djdj");
                HueSaturationEffect effect = new HueSaturationEffect();
                try {
                    effect.setParameterValue(hueAmount, saturationAmount);
                }
                catch(IllegalParameterException e){
                    System.err.println("Error received: " + e.getMessage());
                }
                Pixel[][] modifiedimage = effect.apply(inputImage, imageName);
                System.out.println("dhdj");
                return modifiedimage;
            };
            loggingfuture = executorService.submit(loggingTask);
            imagefuture = executorService.submit(ImageEffect);
            Pixel[][] modifiedimage = inputImage;
            try {
                modifiedimage = imagefuture.get();
                loggingfuture.get();
            }
            catch(Exception e){
                System.err.println("Error recieved " + e.getMessage());
            }
            finally {
                executorService.shutdown();
            }
            return processingUtils.postProcessing(modifiedimage);
        }
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
            // Replace this with actual modified image
            // ACTUAL WORK ENDS HERE
            loggingTask = () -> {
                loggingService.addLog(imageName,"Brightness Effect", String.valueOf(amount));
                return null;
            };
            ImageEffect = () -> {
                BrightnessEffect effect = new BrightnessEffect();
                try {
                    effect.setParameterValue(amount);
                }
                catch(IllegalParameterException e){
                    System.err.println("Error Recieved " + e.getMessage());
                }
                Pixel[][] modifiedimage = effect.apply(inputImage, imageName);
                return modifiedimage;
            };
            loggingfuture = executorService.submit(loggingTask);
            imagefuture = executorService.submit(ImageEffect);
            Pixel[][] modifiedimage = inputImage;
            try {
                modifiedimage = imagefuture.get();
                loggingfuture.get();
            }
            catch(Exception e){
                System.err.println("Error recieved " + e.getMessage());
            }
            return processingUtils.postProcessing(modifiedimage);
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
            loggingTask = () -> {
                loggingService.addLog(imageName,"Contrast Effect", String.valueOf(amount));
                return null;
            };
            ImageEffect = () -> {
                ContrastEffect effect = new ContrastEffect();
                try {
                    effect.setParameterValue(amount);
                }
                catch(IllegalParameterException e){
                    System.err.println("Error Recieved " + e.getMessage());
                }
                Pixel[][] modifiedimage = effect.apply(inputImage, imageName);
                return modifiedimage;
            };
            loggingfuture = executorService.submit(loggingTask);
            imagefuture = executorService.submit(ImageEffect);
            Pixel[][] modifiedimage = inputImage;
            try {
                modifiedimage = imagefuture.get();
                loggingfuture.get();
            }
            catch(Exception e){
                System.err.println("Error recieved " + e.getMessage());
            }
            // ACTUAL WORK ENDS HERE
            return processingUtils.postProcessing(modifiedimage);

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
            loggingTask = () -> {
                String option = "";
                if (horizontalFlipValue == 1){
                    option += "Horizontal_Flip";
                }
                if (verticalFlipValue == 1){
                    if (horizontalFlipValue == 1){
                        option += " and Vertical_Flip";
                    }
                    else{
                        option += "Horizontal_Flip";
                    }
                }
                loggingService.addLog(imageName,"Flip Effect",option);
                return null;
            };
            ImageEffect = () -> {
                FlipEffect effect = new FlipEffect();
                try {
                    effect.setParameters(horizontalFlipValue, verticalFlipValue);
                }
                catch(IllegalParameterException e){
                    System.err.println("Error Recieved " + e.getMessage());
                }
                Pixel[][] modifiedimage = effect.apply(inputImage, imageName);
                return modifiedimage;
            };
            loggingfuture = executorService.submit(loggingTask);
            imagefuture = executorService.submit(ImageEffect);
            Pixel[][] modifiedimage = inputImage;
            try {
                modifiedimage = imagefuture.get();
                loggingfuture.get();
            }
            catch(Exception e){
                System.err.println("Error recieved " + e.getMessage());
            }
            return processingUtils.postProcessing(modifiedimage);
            // ACTUAL WORK ENDS HERE
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
            loggingTask = () -> {
                loggingService.addLog(imageName,"GausianBlur Effect", String.valueOf(radius));
                return null;
            };
            ImageEffect = () -> {
                GaussianBlurEffect effect = new GaussianBlurEffect();
                try {
                    effect.setParameterValue(radius);
                }
                catch(IllegalParameterException e){
                    System.err.println("Error Recieved " + e.getMessage());
                }
                Pixel[][] modifiedimage = effect.apply(inputImage, imageName);
                return modifiedimage;
            };
            loggingfuture = executorService.submit(loggingTask);
            imagefuture = executorService.submit(ImageEffect);
            Pixel[][] modifiedimage = inputImage;
            try {
                modifiedimage = imagefuture.get();
                loggingfuture.get();
            }
            catch(Exception e){
                System.err.println("Error recieved " + e.getMessage());
            }
            // ACTUAL WORK ENDS HERE
            return processingUtils.postProcessing(modifiedimage);
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
            loggingTask = () -> {
                loggingService.addLog(imageName,"Grayscale effect",String.valueOf(1));
                return null;
            };
            ImageEffect = () -> {
                GrayscaleEffect effect = new GrayscaleEffect();
                Pixel[][] modifiedimage = effect.apply(inputImage, imageName);
                return modifiedimage;
            };
            loggingfuture = executorService.submit(loggingTask);
            imagefuture = executorService.submit(ImageEffect);
            Pixel[][] modifiedimage = inputImage;
            try {
                modifiedimage = imagefuture.get();
                loggingfuture.get();
            }
            catch(Exception e){
                System.err.println("Error recieved " + e.getMessage());
            }
            // ACTUAL WORK ENDS HERE
            return processingUtils.postProcessing(modifiedimage);

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
            Pixel[][] modifiedImage = invertEffect.apply(inputImage, imageName); // Replace this with actual modified image
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
                System.err.println("Error received: " + e.getMessage());
            }
            Pixel[][] modifiedImage = rotationEffect.apply(inputImage, imageName);
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

            Pixel[][] modifiedImage = sepiaEffect.apply(inputImage, imageName); // Replace this with
            // actual modified imag
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
            Pixel[][] modifiedImage = sharpenEffect.apply(inputImage, imageName); // Replace this with actual modified image
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
            DominantColourEffect dominantColourEffect = new DominantColourEffect();
            Pixel[][] modifiedImage = dominantColourEffect.apply(inputImage, imageName); // Replace this with actual modified image
            // ACTUAL WORK ENDS HERE

            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
