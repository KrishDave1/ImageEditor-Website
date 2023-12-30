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
import java.lang.reflect.Method;
import java.util.concurrent.*;


@Service
public class PhotoEffectService {
    Callable <Pixel[][]> ImageEffect;
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    @Autowired
    private ProcessingUtils processingUtils;
    @Autowired
    private LoggingService loggingService;
    private Callable<Pixel[][]> imageEffectTask;
    private Callable<Void> loggingTask;
    private ExecutorService executor;
    private Future<Pixel[][]> imageFuture;
    private Future<Void> loggingFuture;

//    Method Signature:
//
//    The method is named applyHueSaturationEffect.
//    It takes three parameters: float hueAmount, float saturationAmount, and MultipartFile imageFile.
//    It returns a ResponseEntity<byte[]>, which is commonly used in Spring applications to represent the HTTP response.
//    Image Processing Setup:
//
//    The method starts by extracting a 2D array of pixels (Pixel[][] inputImage) from the MultipartFile imageFile using a processingUtils object.
//    The original filename of the image is also obtained (String imageName).
//    Asynchronous Processing:
//
//    The code uses the Executor framework to perform tasks asynchronously. It creates a fixed thread pool with 10 threads.
//    Two tasks are defined and submitted to the executor:
//    loggingTask: Logs information about the image processing, including the original filename, the type of effect (Hue Saturation), and the specified amounts.
//    imageEffectTask: Applies the Hue-Saturation effect to the image using the HueSaturationEffect class. The parameters for the effect are set based on the input amounts.
//    Logging and Image Processing Execution:
//
//    Both tasks are submitted to the executor, and their futures are obtained (imageFuture and loggingFuture).
//    The main thread then waits for the completion of these tasks using imageFuture.get() and loggingFuture.get().
//    The modified image is obtained from the imageFuture.
//    The executor is shut down.
//    Exception Handling:
//
//    Exception handling is present to catch IllegalParameterException and IOException.
//    If an exception occurs during image processing, an internal server error response is returned.
//    Return Statement:
//
//    The modified image is then passed through a post-processing step using processingUtils.postProcessing.
//            The final result is returned as a ResponseEntity<byte[]>.
//    Cleanup:
//
//    The executor service is shut down in a finally block to release resources.
//    In summary, this code asynchronously applies a Hue-Saturation effect to an image, logs the processing details, and handles potential exceptions. The method returns the modified image as a byte array wrapped in a ResponseEntity, considering potential exceptions during image processing.

    public ResponseEntity<byte[]> applyHueSaturationEffect(float hueAmount, float saturationAmount, MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();
            // ACTUAL WORK STARTS HERE
            // TODO
            executor = Executors.newFixedThreadPool(10);

            loggingTask = () -> {
                System.out.println("Will add the log on the thread = " + Thread.currentThread().getName());
                loggingService.addLog(imageName, "Hue Saturation", String.valueOf(hueAmount) + " " + String.valueOf(saturationAmount));
                return null;
            };

            imageEffectTask = () -> {
                System.out.println("Doing image processing on the thread = " + Thread.currentThread().getName());
                HueSaturationEffect hueSaturationEffect = new HueSaturationEffect();
                try {
                    hueSaturationEffect.setParameterValue(hueAmount, saturationAmount);
                } catch (IllegalParameterException e) {
                    System.err.println("Error received: " + e.getMessage());
                }
                Pixel[][] modifiedImage = hueSaturationEffect.apply(inputImage, imageName);
                System.out.println("Processed the image asynchronously.");
                return modifiedImage;
            };
            imageFuture = executor.submit(imageEffectTask);
            loggingFuture = executor.submit(loggingTask);

            Pixel[][] modifiedImage = inputImage;
            try {
                modifiedImage = imageFuture.get();
                loggingFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Now shutting down the executor service.");
                executor.shutdown();
            }

            return processingUtils.postProcessing(modifiedImage);
        } // Replace this with actual modified image
          // ACTUAL WORK ENDS HERE
        catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    Method Signature:
//
//    The method is called applyBrightnessEffect.
//    It takes two parameters: float amount representing the brightness level, and MultipartFile imageFile.
//    The method returns a ResponseEntity<byte[]>, commonly used in Spring applications to represent the HTTP response.
//    Image Processing Setup:
//
//    The method starts by extracting a 2D array of pixels (Pixel[][] inputImage) from the MultipartFile imageFile using a processingUtils object.
//    The original filename of the image is also obtained (String imageName).
//    Asynchronous Processing:
//
//    The code uses the Executor framework to perform tasks asynchronously. It creates a fixed thread pool with 10 threads.
//    Two tasks are defined and submitted to the executor:
//    loggingTask: Logs information about the image processing, including the original filename, the type of effect (Brightness), and the specified amount.
//    imageEffectTask: Applies the Brightness effect to the image using the BrightnessEffect class. The parameter for the effect is set based on the input amount.
//    Logging and Image Processing Execution:
//
//    Both tasks are submitted to the executor, and their futures are obtained (imageFuture and loggingFuture).
//    The main thread then waits for the completion of these tasks using imageFuture.get() and loggingFuture.get().
//    The modified image is obtained from the imageFuture.
//    The executor is shut down.
//    Exception Handling:
//
//    Exception handling is present to catch IllegalParameterException and IOException.
//    If an exception occurs during image processing, an internal server error response is returned.
//    Return Statement:
//
//    The modified image is then passed through a post-processing step using processingUtils.postProcessing.
//            The final result is returned as a ResponseEntity<byte[]>.
//    Cleanup:
//
//    The executor service is shut down in a finally block to release resources.
//    In summary, this code asynchronously applies a brightness effect to an image, logs the processing details, and handles potential exceptions. The method returns the modified image as a byte array wrapped in a ResponseEntity, considering potential exceptions during image processing.
    public ResponseEntity<byte[]> applyBrightnessEffect(float amount, MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();

            // ACTUAL WORK STARTS HERE
            // TODO
            executor = Executors.newFixedThreadPool(10);
            loggingTask = () -> {
                System.out.println("Will add the log on the thread = " + Thread.currentThread().getName());
                loggingService.addLog(imageName, "Brightness", String.valueOf(amount));
                return null;
            };

            imageEffectTask = () -> {
                System.out.println("Doing image processing on the thread = " + Thread.currentThread().getName());
                BrightnessEffect brightnessEffect = new BrightnessEffect();
                try {
                    brightnessEffect.setParameterValue(amount);
                } catch (IllegalParameterException e) {
                    System.err.println("Error received: " + e.getMessage());
                }
                Pixel[][] modifiedImage = brightnessEffect.apply(inputImage, imageName);
                System.out.println("Processed the image asynchronously.");
                return modifiedImage;
            };
            imageFuture = executor.submit(imageEffectTask);
            loggingFuture = executor.submit(loggingTask);

            Pixel[][] modifiedImage = inputImage;
            try {
                modifiedImage = imageFuture.get();
                loggingFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Now shutting down the executor service.");
                executor.shutdown();
            }

            // ACTUAL WORK ENDS HERE

            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    Method Signature:
//
//    The method is named applyContrastEffect.
//    It takes two parameters: float amount representing the contrast level, and MultipartFile imageFile.
//    The method returns a ResponseEntity<byte[]>, commonly used in Spring applications to represent the HTTP response.
//    Image Processing Setup:
//
//    The method starts by extracting a 2D array of pixels (Pixel[][] inputImage) from the MultipartFile imageFile using a processingUtils object.
//    The original filename of the image is also obtained (String imageName).
//    Asynchronous Processing:
//
//    The code uses the Executor framework to perform tasks asynchronously. It creates a fixed thread pool with 10 threads.
//    Two tasks are defined and submitted to the executor:
//    loggingTask: Logs information about the image processing, including the original filename, the type of effect (Contrast), and a placeholder value for the amount ("None" in this case).
//    imageEffectTask: Applies the Contrast effect to the image using the ContrastEffect class. The parameter for the effect is set based on the input amount.
//    Logging and Image Processing Execution:
//
//    Both tasks are submitted to the executor, and their futures are obtained (imageFuture and loggingFuture).
//    The main thread then waits for the completion of these tasks using imageFuture.get() and loggingFuture.get().
//    The modified image is obtained from the imageFuture.
//    The executor is shut down.
//    Exception Handling:
//
//    Exception handling is present to catch IllegalParameterException and IOException.
//    If an exception occurs during image processing, an internal server error response is returned.
//    Return Statement:
//
//    The modified image is then passed through a post-processing step using processingUtils.postProcessing.
//            The final result is returned as a ResponseEntity<byte[]>.
//    Cleanup:
//
//    The executor service is shut down in a finally block to release resources.
//    In summary, this code asynchronously applies a contrast effect to an image, logs the processing details, and handles potential exceptions. The method returns the modified image as a byte array wrapped in a ResponseEntity, considering potential exceptions during image processing.
    public ResponseEntity<byte[]> applyContrastEffect(float amount, MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();

            // ACTUAL WORK STARTS HERE
            // TODO
            executor = Executors.newFixedThreadPool(10);
            loggingTask = () -> {
                System.out.println("Will add the log on the thread = " + Thread.currentThread().getName());
                loggingService.addLog(imageName, "Contrast", "None");
                return null;
            };

            imageEffectTask = () -> {
                System.out.println("Doing image processing on the thread = " + Thread.currentThread().getName());
                ContrastEffect contrastEffect = new ContrastEffect();
                try {
                    contrastEffect.setParameterValue(amount);
                } catch (IllegalParameterException e) {
                    System.err.println("Error received: " + e.getMessage());
                }
                Pixel[][] modifiedImage = contrastEffect.apply(inputImage, imageName);
                System.out.println("Processed the image asynchronously.");
                return modifiedImage;
            };
            imageFuture = executor.submit(imageEffectTask);
            loggingFuture = executor.submit(loggingTask);

            Pixel[][] modifiedImage = inputImage;
            try {
                modifiedImage = imageFuture.get();
                loggingFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Now shutting down the executor service.");
                executor.shutdown();
            }

            // ACTUAL WORK ENDS HERE

            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    Method Signature:
//
//    The method is named applyFlipEffect.
//    It takes three parameters: MultipartFile imageFile, int horizontalFlipValue, and int verticalFlipValue.
//    The method returns a ResponseEntity<byte[]>, commonly used in Spring applications to represent the HTTP response.
//    Image Processing Setup:
//
//    The method starts by extracting a 2D array of pixels (Pixel[][] inputImage) from the MultipartFile imageFile using a processingUtils object.
//    The original filename of the image is also obtained (String imageName).
//    Asynchronous Processing:
//
//    The code uses the Executor framework to perform tasks asynchronously. It creates a fixed thread pool with 10 threads.
//    Two tasks are defined and submitted to the executor:
//    loggingTask: Logs information about the image processing, including the original filename, the type of effect (Flip), and the specified horizontal and vertical flip values.
//    imageEffectTask: Applies the Flip effect to the image using the FlipEffect class. The parameters for the effect are set based on the input values.
//    Logging and Image Processing Execution:
//
//    Both tasks are submitted to the executor, and their futures are obtained (imageFuture and loggingFuture).
//    The main thread then waits for the completion of these tasks using imageFuture.get() and loggingFuture.get().
//    The modified image is obtained from the imageFuture.
//    The executor is shut down.
//    Exception Handling:
//
//    Exception handling is present to catch IllegalParameterException and IOException.
//    If an exception occurs during image processing, an internal server error response is returned.
//    Return Statement:
//
//    The modified image is then passed through a post-processing step using processingUtils.postProcessing.
//            The final result is returned as a ResponseEntity<byte[]>.
//    Cleanup:
//
//    The executor service is shut down in a finally block to release resources.
//    In summary, this code asynchronously applies a flip effect to an image, logs the processing details, and handles potential exceptions. The method returns the modified image as a byte array wrapped in a ResponseEntity, considering potential exceptions during image processing.
    public ResponseEntity<byte[]> applyFlipEffect(MultipartFile imageFile, int horizontalFlipValue, int verticalFlipValue) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();
            // ACTUAL WORK STARTS HERE
            // TODO
            executor = Executors.newFixedThreadPool(10);
            loggingTask = () -> {
                System.out.println("Will add the log on the thread = " + Thread.currentThread().getName());
                loggingService.addLog(imageName,"Flip", String.valueOf(horizontalFlipValue) + " " + String.valueOf(verticalFlipValue));
                return null;
            };

            imageEffectTask = () -> {
                System.out.println("Doing image processing on the thread = " + Thread.currentThread().getName());
                FlipEffect flipEffect = new FlipEffect();
                try {
                    flipEffect.setParameters(horizontalFlipValue, verticalFlipValue);
                }
                catch(IllegalParameterException e){
                    System.err.println("Error received: " + e.getMessage());
                }
                Pixel[][] modifiedImage = flipEffect.apply(inputImage, imageName);
                System.out.println("Processed the image asynchronously.");
                return modifiedImage;
            };
            imageFuture = executor.submit(imageEffectTask);
            loggingFuture = executor.submit(loggingTask);

            Pixel[][] modifiedImage = inputImage;
            try {
                modifiedImage = imageFuture.get();
                loggingFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Now shutting down the executor service.");
                executor.shutdown();
            }
            // ACTUAL WORK ENDS HERE

            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    Method Signature:
//
//    The method is named applyGaussianBlurEffect.
//    It takes two parameters: float radius representing the blur radius, and MultipartFile imageFile.
//    The method returns a ResponseEntity<byte[]>, commonly used in Spring applications to represent the HTTP response.
//    Image Processing Setup:
//
//    The method starts by extracting a 2D array of pixels (Pixel[][] inputImage) from the MultipartFile imageFile using a processingUtils object.
//    The original filename of the image is also obtained (String imageName).
//    Asynchronous Processing:
//
//    The code uses the Executor framework to perform tasks asynchronously. It creates a fixed thread pool with 10 threads.
//    Two tasks are defined and submitted to the executor:
//    loggingTask: Logs information about the image processing, including the original filename, the type of effect (Gaussian Blur), and the specified blur radius.
//    imageEffectTask: Applies the Gaussian Blur effect to the image using the GaussianBlurEffect class. The parameter for the effect is set based on the input radius.
//    Logging and Image Processing Execution:
//
//    Both tasks are submitted to the executor, and their futures are obtained (imageFuture and loggingFuture).
//    The main thread then waits for the completion of these tasks using imageFuture.get() and loggingFuture.get().
//    The modified image is obtained from the imageFuture.
//    The executor is shut down.
//    Exception Handling:
//
//    Exception handling is present to catch IllegalParameterException and IOException.
//    If an exception occurs during image processing, an internal server error response is returned.
//    Return Statement:
//
//    The modified image is then passed through a post-processing step using processingUtils.postProcessing.
//            The final result is returned as a ResponseEntity<byte[]>.
//    Cleanup:
//
//    The executor service is shut down in a finally block to release resources.
//    In summary, this code asynchronously applies a Gaussian blur effect to an image, logs the processing details, and handles potential exceptions. The method returns the modified image as a byte array wrapped in a ResponseEntity, considering potential exceptions during image processing.
    public ResponseEntity<byte[]> applyGaussianBlurEffect(float radius, MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();


            // ACTUAL WORK STARTS HERE

            // TODO
            executor = Executors.newFixedThreadPool(10);
            loggingTask = () -> {
                System.out.println("Will add the log on the thread = " + Thread.currentThread().getName());
                loggingService.addLog(imageName, "Gaussian Blur", String.valueOf(radius));
                return null;
            };
            imageEffectTask = () -> {
                System.out.println("Doing image processing on the thread = " + Thread.currentThread().getName());
                GaussianBlurEffect gaussianBlurEffect = new GaussianBlurEffect();
                try {
                    gaussianBlurEffect.setParameterValue(radius);
                } catch (IllegalParameterException e) {
                    System.err.println("Error received: " + e.getMessage());
                }
                Pixel[][] modifiedImage = gaussianBlurEffect.apply(inputImage, imageName);
                System.out.println("Processed the image asynchronously.");
                return modifiedImage;
            };
            imageFuture = executor.submit(imageEffectTask);
            loggingFuture = executor.submit(loggingTask);

            Pixel[][] modifiedImage = inputImage;
            try {
                modifiedImage = imageFuture.get();
                loggingFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Now shutting down the executor service.");
                executor.shutdown();
            }
            // Replace this with actual modified image

            // ACTUAL WORK ENDS HERE

            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    Method Signature:
//
//    The method is named applyGrayscaleEffect.
//    It takes one parameter: MultipartFile imageFile.
//    The method returns a ResponseEntity<byte[]>, commonly used in Spring applications to represent the HTTP response.
//    Image Processing Setup:
//
//    The method starts by extracting a 2D array of pixels (Pixel[][] inputImage) from the MultipartFile imageFile using a processingUtils object.
//    The original filename of the image is also obtained (String imageName).
//    Asynchronous Processing:
//
//    The code uses the Executor framework to perform tasks asynchronously. It creates a fixed thread pool with 10 threads.
//    Two tasks are defined and submitted to the executor:
//    loggingTask: Logs information about the image processing, including the original filename and the type of effect (Grayscale), with a placeholder value for the effect amount ("None").
//    imageEffectTask: Applies the Grayscale effect to the image using the GrayscaleEffect class.
//    Logging and Image Processing Execution:
//
//    Both tasks are submitted to the executor, and their futures are obtained (imageFuture and loggingFuture).
//    The main thread then waits for the completion of these tasks using imageFuture.get() and loggingFuture.get().
//    The modified image is obtained from the imageFuture.
//    The executor is shut down.
//    Exception Handling:
//
//    Exception handling is present to catch IOException, InterruptedException, and ExecutionException.
//    If an exception occurs during image processing, an internal server error response is returned.
//    Return Statement:
//
//    The modified image is then passed through a post-processing step using processingUtils.postProcessing.
//            The final result is returned as a ResponseEntity<byte[]>.
//    Cleanup:
//
//    The executor service is shut down in a finally block to release resources.
//    In summary, this code asynchronously applies a grayscale effect to an image, logs the processing details, and handles potential exceptions. The method returns the modified image as a byte array wrapped in a ResponseEntity, considering potential exceptions during image processing.
    public ResponseEntity<byte[]> applyGrayscaleEffect(MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();
            // ACTUAL WORK STARTS HERE
            // TODO

            executor = Executors.newFixedThreadPool(10);
            loggingTask =() -> {
                System.out.println("Will add the log on the thread = " + Thread.currentThread().getName());
                loggingService.addLog(imageName, "GrayScale", "None");
                return null;
            };
            imageEffectTask = () -> {
                System.out.println("Will do the image prpcessing on the thread = " + Thread.currentThread().getName());
                GrayscaleEffect grayscaleEffect = new GrayscaleEffect();
                Pixel[][] modifiedImage = grayscaleEffect.apply(inputImage, imageName);
                System.out.println("Processed the image asynchronously");
                return modifiedImage;
            };
            imageFuture = executor.submit(imageEffectTask);
            loggingFuture = executor.submit(loggingTask);

            Pixel[][] modifiedImage = inputImage;
            try{
                modifiedImage = imageFuture.get();
                loggingFuture.get();
            }catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
            } finally {
                System.out.println("Now shutting down the executor service");
                executor.shutdown();
            }

            // ACTUAL WORK ENDS HERE
            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    Method Signature:
//
//    The method is named applyInvertEffect.
//    It takes one parameter: MultipartFile imageFile.
//    The method returns a ResponseEntity<byte[]>, commonly used in Spring applications to represent the HTTP response.
//    Image Processing Setup:
//
//    The method starts by extracting a 2D array of pixels (Pixel[][] inputImage) from the MultipartFile imageFile using a processingUtils object.
//    The original filename of the image is also obtained (String imageName).
//    Asynchronous Processing:
//
//    The code uses the Executor framework to perform tasks asynchronously. It creates a fixed thread pool with 10 threads.
//    Two tasks are defined and submitted to the executor:
//    loggingTask: Logs information about the image processing, including the original filename and the type of effect (Invert), with a placeholder value for the effect amount ("None").
//    imageEffectTask: Applies the Invert effect to the image using the InvertEffect class.
//    Logging and Image Processing Execution:
//
//    Both tasks are submitted to the executor, and their futures are obtained (imageFuture and loggingFuture).
//    The main thread then waits for the completion of these tasks using imageFuture.get() and loggingFuture.get().
//    The modified image is obtained from the imageFuture.
//    The executor is shut down.
//    Exception Handling:
//
//    Exception handling is present to catch IOException, InterruptedException, and ExecutionException.
//    If an exception occurs during image processing, an internal server error response is returned.
//    Return Statement:
//
//    The modified image is then passed through a post-processing step using processingUtils.postProcessing.
//            The final result is returned as a ResponseEntity<byte[]>.
//    Cleanup:
//
//    The executor service is shut down in a finally block to release resources.
//    In summary, this code asynchronously applies an invert effect to an image, logs the processing details, and handles potential exceptions. The method returns the modified image as a byte array wrapped in a ResponseEntity, considering potential exceptions during image processing.

    public ResponseEntity<byte[]> applyInvertEffect(MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();

            // ACTUAL WORK STARTS HERE
            // TODO
            executor = Executors.newFixedThreadPool(10);
            loggingTask = () -> {
                System.out.println("Will add the log on the thread = " + Thread.currentThread().getName());
                loggingService.addLog(imageName, "Invert", "None");
                return null;
            };
            imageEffectTask = () -> {
                System.out.println("Doing image processing on the thread = " + Thread.currentThread().getName());
                InvertEffect invertEffect = new InvertEffect();
                Pixel[][] modifiedImage = invertEffect.apply(inputImage, imageName);
                System.out.println("Processed the image asynchronously.");
                return modifiedImage;
            };
            imageFuture = executor.submit(imageEffectTask);
            loggingFuture = executor.submit(loggingTask);

            Pixel[][] modifiedImage = inputImage;
            try {
                modifiedImage = imageFuture.get();
                loggingFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Now shutting down the executor service.");
                executor.shutdown();
            }
            // ACTUAL WORK ENDS HERE

            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    Method Signature:
//
//    The method is named applyRotationEffect.
//    It takes two parameters: int value representing the rotation angle (0, 90, 180, or 270 degrees) and MultipartFile imageFile.
//    The method returns a ResponseEntity<byte[]>, commonly used in Spring applications to represent the HTTP response.
//    Image Processing Setup:
//
//    The method starts by extracting a 2D array of pixels (Pixel[][] inputImage) from the MultipartFile imageFile using a processingUtils object.
//    The original filename of the image is also obtained (String imageName).
//    Asynchronous Processing:
//
//    The code uses the Executor framework to perform tasks asynchronously. It creates a fixed thread pool with 10 threads.
//    Two tasks are defined and submitted to the executor:
//    loggingTask: Logs information about the image processing, including the original filename and the type of effect (Rotation), with the specified rotation angle.
//    imageEffectTask: Applies the Rotation effect to the image using the RotationEffect class. The parameter for the effect is set based on the input angle.
//    Logging and Image Processing Execution:
//
//    Both tasks are submitted to the executor, and their futures are obtained (imageFuture and loggingFuture).
//    The main thread then waits for the completion of these tasks using imageFuture.get() and loggingFuture.get().
//    The modified image is obtained from the imageFuture.
//    The executor is shut down.
//    Exception Handling:
//
//    Exception handling is present to catch IllegalParameterException and IOException.
//    If an exception occurs during image processing or if the rotation value is invalid, an internal server error response is returned.
//    Return Statement:
//
//    The modified image is then passed through a post-processing step using processingUtils.postProcessing.
//            The final result is returned as a ResponseEntity<byte[]>.
//    Cleanup:
//
//    The executor service is shut down in a finally block to release resources.
//    In summary, this code asynchronously applies a rotation effect to an image, logs the processing details, and handles potential exceptions. The method returns the modified image as a byte array wrapped in a ResponseEntity, considering potential exceptions during image processing.
    public ResponseEntity<byte[]> applyRotationEffect(int value, MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();
            // ACTUAL WORK STARTS HERE

            // TODO
            executor = Executors.newFixedThreadPool(10);
            loggingTask = () -> {
                System.out.println("Will add the log on the thread = " + Thread.currentThread().getName());
                if (value < 0 || value > 3) {
                    throw new IllegalParameterException("Value can be 0, 90, 180 or 270.");
                }
                loggingService.addLog(imageName, "Rotation", String.valueOf(value*90));
                return null;
            };
            imageEffectTask = () -> {
                System.out.println("Doing image processing on the thread = " + Thread.currentThread().getName());
                RotationEffect rotationEffect = new RotationEffect();
                try {
                    rotationEffect.selectOptionValue(imageName, value);
                } catch (IllegalParameterException e) {
                    System.err.println("Error received: " + e.getMessage());
                }
                Pixel[][] modifiedImage = rotationEffect.apply(inputImage, imageName);
                System.out.println("Processed the image asynchronously.");
                return modifiedImage;
            };
            imageFuture = executor.submit(imageEffectTask);
            loggingFuture = executor.submit(loggingTask);

            Pixel[][] modifiedImage = inputImage;
            try {
                modifiedImage = imageFuture.get();
                loggingFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Now shutting down the executor service.");
                executor.shutdown();
            }
            // ACTUAL WORK ENDS HERE
            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    Method Signature:
//
//    The method is named applySepiaEffect.
//    It takes one parameter: MultipartFile imageFile.
//    The method returns a ResponseEntity<byte[]>, commonly used in Spring applications to represent the HTTP response.
//    Image Processing Setup:
//
//    The method starts by extracting a 2D array of pixels (Pixel[][] inputImage) from the MultipartFile imageFile using a processingUtils object.
//    The original filename of the image is also obtained (String imageName).
//    Asynchronous Processing:
//
//    The code uses the Executor framework to perform tasks asynchronously. It creates a fixed thread pool with 10 threads.
//    Two tasks are defined and submitted to the executor:
//    loggingTask: Logs information about the image processing, including the original filename and the type of effect (Sepia), with a placeholder value for the effect amount ("None").
//    imageEffectTask: Applies the Sepia effect to the image using the SepiaEffect class.
//    Logging and Image Processing Execution:
//
//    Both tasks are submitted to the executor, and their futures are obtained (imageFuture and loggingFuture).
//    The main thread then waits for the completion of these tasks using imageFuture.get() and loggingFuture.get().
//    The modified image is obtained from the imageFuture.
//    The executor is shut down.
//    Exception Handling:
//
//    Exception handling is present to catch IOException, InterruptedException, and ExecutionException.
//    If an exception occurs during image processing, an internal server error response is returned.
//    Return Statement:
//
//    The modified image is then passed through a post-processing step using processingUtils.postProcessing.
//            The final result is returned as a ResponseEntity<byte[]>.
//    Cleanup:
//
//    The executor service is shut down in a finally block to release resources.
//    In summary, this code asynchronously applies a sepia tone effect to an image, logs the processing details, and handles potential exceptions. The method returns the modified image as a byte array wrapped in a ResponseEntity, considering potential exceptions during image processing.
    public ResponseEntity<byte[]> applySepiaEffect(MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();

            // ACTUAL WORK STARTS HERE

            // TODO
            executor = Executors.newFixedThreadPool(10);
            loggingTask = () -> {
                System.out.println("Will add the log on the thread = " + Thread.currentThread().getName());
                loggingService.addLog(imageName, "Sepia", "None");
                return null;
            };
            imageEffectTask = () -> {
                System.out.println("Doing image processing on the thread = " + Thread.currentThread().getName());
                SepiaEffect sepiaEffect = new SepiaEffect();
                Pixel[][] modifiedImage = sepiaEffect.apply(inputImage, imageName);
                System.out.println("Processed the image asynchronously.");
                return modifiedImage;
            };
            imageFuture = executor.submit(imageEffectTask);
            loggingFuture = executor.submit(loggingTask);

            Pixel[][] modifiedImage = inputImage;
            try {
                modifiedImage = imageFuture.get();
                loggingFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Now shutting down the executor service.");
                executor.shutdown();
            }
            // Replace this withactual modified image
            // ACTUAL WORK ENDS HERE

            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    Method Signature:
//
//    The method is named applySharpenEffect.
//    It takes two parameters: float amount representing the sharpening amount and MultipartFile imageFile.
//    The method returns a ResponseEntity<byte[]>, commonly used in Spring applications to represent the HTTP response.
//    Image Processing Setup:
//
//    The method starts by extracting a 2D array of pixels (Pixel[][] inputImage) from the MultipartFile imageFile using a processingUtils object.
//    The original filename of the image is also obtained (String imageName).
//    Asynchronous Processing:
//
//    The code uses the Executor framework to perform tasks asynchronously. It creates a fixed thread pool with 10 threads.
//    Two tasks are defined and submitted to the executor:
//    loggingTask: Logs information about the image processing, including the original filename and the type of effect (Sharpen), with the specified sharpening amount.
//    imageEffectTask: Applies the Sharpen effect to the image using the SharpenEffect class. The parameter for the effect is set based on the input amount.
//    Logging and Image Processing Execution:
//
//    Both tasks are submitted to the executor, and their futures are obtained (imageFuture and loggingFuture).
//    The main thread then waits for the completion of these tasks using imageFuture.get() and loggingFuture.get().
//    The modified image is obtained from the imageFuture.
//    The executor is shut down.
//    Exception Handling:
//
//    Exception handling is present to catch IllegalParameterException and IOException.
//    If an exception occurs during image processing, an internal server error response is returned.
//    Return Statement:
//
//    The modified image is then passed through a post-processing step using processingUtils.postProcessing.
//            The final result is returned as a ResponseEntity<byte[]>.
//    Cleanup:
//
//    The executor service is shut down in a finally block to release resources.
//    In summary, this code asynchronously applies a sharpening effect to an image, logs the processing details, and handles potential exceptions. The method returns the modified image as a byte array wrapped in a ResponseEntity, considering potential exceptions during image processing.
    public ResponseEntity<byte[]> applySharpenEffect(float amount, MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();

            // ACTUAL WORK STARTS HERE
            // TODO
            executor = Executors.newFixedThreadPool(10);
            loggingTask = () -> {
                System.out.println("Will add the log on the thread = " + Thread.currentThread().getName());
                loggingService.addLog(imageName, "Sharpen", String.valueOf(amount));
                return null;
            };
            imageEffectTask = () -> {
                System.out.println("Doing image processing on the thread = " + Thread.currentThread().getName());
                SharpenEffect sharpenEffect = new SharpenEffect();
                try {
                    sharpenEffect.setParameterValue(amount);
                } catch (IllegalParameterException e) {
                    System.err.println("Error received: " + e.getMessage());
                }
                Pixel[][] modifiedImage = sharpenEffect.apply(inputImage, imageName);
                System.out.println("Processed the image asynchronously.");
                return modifiedImage;
            };
            imageFuture = executor.submit(imageEffectTask);
            loggingFuture = executor.submit(loggingTask);

            Pixel[][] modifiedImage = inputImage;
            try {
                modifiedImage = imageFuture.get();
                loggingFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Now shutting down the executor service.");
                executor.shutdown();
            }

            // ACTUAL WORK ENDS HERE
            return processingUtils.postProcessing(modifiedImage);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    Method Signature:
//
//    The method is named getDominantColour.
//    It takes one parameter: MultipartFile imageFile.
//    The method returns a ResponseEntity<byte[]>, commonly used in Spring applications to represent the HTTP response.
//    Image Processing Setup:
//
//    The method starts by extracting a 2D array of pixels (Pixel[][] inputImage) from the MultipartFile imageFile using a processingUtils object.
//    The original filename of the image is also obtained (String imageName).
//    Asynchronous Processing:
//
//    The code uses the Executor framework to perform tasks asynchronously. It creates a fixed thread pool with 10 threads.
//    Two tasks are defined and submitted to the executor:
//    loggingTask: Logs information about the image processing, including the original filename and the type of effect (Dominant Color), with a placeholder value for the effect amount ("None").
//    imageEffectTask: Applies the Dominant Color effect to the image using the DominantColourEffect class.
//    Logging and Image Processing Execution:
//
//    Both tasks are submitted to the executor, and their futures are obtained (imageFuture and loggingFuture).
//    The main thread then waits for the completion of these tasks using imageFuture.get() and loggingFuture.get().
//    The modified image is obtained from the imageFuture.
//    The executor is shut down.
//    Exception Handling:
//
//    Exception handling is present to catch IOException, InterruptedException, and ExecutionException.
//    If an exception occurs during image processing, an internal server error response is returned.
//    Return Statement:
//
//    The modified image is then passed through a post-processing step using processingUtils.postProcessing.
//            The final result is returned as a ResponseEntity<byte[]>.
//    Cleanup:
//
//    The executor service is shut down in a finally block to release resources.
//    In summary, this code asynchronously determines the dominant color of an image, logs the processing details, and handles potential exceptions. The method returns the result as a byte array wrapped in a ResponseEntity, considering potential exceptions during image processing.
    public ResponseEntity<byte[]> getDominantColour(MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();

            // ACTUAL WORK STARTS HERE

            // TODO
            executor = Executors.newFixedThreadPool(10);
            loggingTask = () -> {
                System.out.println("Will add the log on the thread = " + Thread.currentThread().getName());
                loggingService.addLog(imageName, "Dominant Color", "None");
                return null;
            };
            imageEffectTask = () -> {
                System.out.println("Doing image processing on the thread = " + Thread.currentThread().getName());
                DominantColourEffect dominantColourEffect = new DominantColourEffect();
                Pixel[][] modifiedImage = dominantColourEffect.apply(inputImage, imageName);
                System.out.println("Processed the image asynchronously.");
                return modifiedImage;
            };
            imageFuture = executor.submit(imageEffectTask);
            loggingFuture = executor.submit(loggingTask);

            Pixel[][] modifiedImage = inputImage;
            try {
                modifiedImage = imageFuture.get();
                loggingFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Now shutting down the executor service.");
                executor.shutdown();
            }
            // ACTUAL WORK ENDS HERE

            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
