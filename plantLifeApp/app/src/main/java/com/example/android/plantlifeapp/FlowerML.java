package com.example.android.plantlifeapp;
//import org.tensorflow;
//import tensorflow;
//
//import android.widget.Toast;
//
//import com.example.android.plantlifeapp.ui.camera.Camera;

import android.content.res.Resources;

import org.tensorflow.SavedModelBundle;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;

public class FlowerML {
//    public static void identifyFlower(){
//
//        String[] class_names = new String[]{"daisy", "dandelion", "roses", "sunflowers", "tulips"};
//
//       double img_height = 180;
//        double img_width = 180;
//
//        String model_filepath="flower-ann-model.h5";
//      String  model = keras.models.load_model(model_filepath);
//
//       String sunflower_url = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.D9H_OQXojeeCYap1SW66pQHaE7%26pid%3DApi&f=1";
//        String origin;
//        String sunflower_path = tensorflow.keras.utils.get_file("Red_rose", origin=sunflower_url);
//
//       String img = tensorflow.keras.utils.load_img(
//                sunflower_path, new Size(img_height, img_width)
//);
//        Array img_array = tensorflow.keras.utils.img_to_array(img);
//         img_array = tensorflow.expand_dims(img_array, 0); // Create a batch
//
//                String predictions = model.predict(img_array);
//       String  score = tensorflow.nn.softmax(predictions.get(0));
//
//        System.out.println(
//                "This image most likely belongs to {} with a {:.2f} percent confidence."
//                        .format(class_names[numpy.argmax(score)], 100 * numpy.max(score)));
//    }
//
//
//    String modelFilePath = "flower-ann-model.h5";
//    String imputImageFilePath;
//
//    String MODEL_IMAGE_OPERATION_LABEL = "image_tensor";
//    String DETECTION_SCORES_OPERATION_LABEL = "probabilities";
//
//    public void identifyFlower() {
//        try (SavedModelBundle model = SavedModelBundle.load(modelFilePath, "serve")) {
//            List<Tensor<?>> outputTensors = null;
//            try (Tensor<UInt8> input = makeImageTensor(imputImageFilePath)) {
//                outputTensors = model
//                        .session()
//                        .runner()
//                        .feed(MODEL_IMAGE_OPERATION_LABEL, input)
//                        .fetch(DETECTION_SCORES_OPERATION_LABEL)
//                        .run();
//            }
//            try (Tensor<Float> scoresTensor = outputTensors.get(0).expect(Float.class);) {
//                float[] probabilities = new float[(int) output.shape()[0]];
//                output.copyTo(probabilities);
//                // All these tensors have:
//                // - 1 as the first dimension
//                // - maxObjects as the second dimension
//                // While boxesT will have 4 as the third dimension (2 sets of (x, y) coordinates).
//                // This can be verified by looking at scoresT.shape() etc.
////                int maxObjects = (int) scoresTensor.shape()[1];
////                float[] scores = scoresTensor.copyTo(new float[1][maxObjects])[0];
////                float[] classes = classesT.copyTo(new float[1][maxObjects])[0];
////                float[][] boxes = boxesT.copyTo(new float[1][maxObjects][4])[0];
////                // Print all objects whose score is at least 0.5.
////                System.out.printf("* %s\n", filename);
//                boolean foundSomething = false;
//                for (int i = 0; i < scores.length; ++i) {
//                    if (scores[i] < 0.5) {
//                        continue;
//                    }
//                    foundSomething = true;
//                    System.out.printf("\tFound %-20s (score: %.4f)\n", labels[(int) classes[i]], scores[i]);
//                }
//                if (!foundSomething) {
//                    System.out.println("No objects detected with a high enough score.");
//                }
//            }
//            try (Tensor<?> input = makeInputTensor();) {
//                Runner<?> runner = model.session().runner().feed("INPUT_TENSOR", input).fetch("OUTPUT_TENSOR", output);
//                try (Tensor<?> outputTensor = runner.run())
//
//
//                outputTensor.get(0);
//            }
//        }
//        String[] flowerClassNames = new String[]
//                {
//                        "daisy", "dandelion", "roses", "sunflowers", "tulips"
//                };
//
//        double imageHeight = 180;
//        double imageWidth = 180;
//
//        String model_filepath = "flower-ann-model.h5";
//        String model = keras.models.load_model(model_filepath);
//
//        String sunflower_url = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.D9H_OQXojeeCYap1SW66pQHaE7%26pid%3DApi&f=1";
//        String origin;
//        String sunflower_path = tensorflow.keras.utils.get_file("Red_rose", origin = sunflower_url);
//
//        String img = tensorflow.keras.utils.load_img(
//                sunflower_path, new Size(imageHeight, imageWidth)
//        );
//        Array img_array = tensorflow.keras.utils.img_to_array(img);
//        img_array = tensorflow.expand_dims(img_array, 0); // Create a batch
//
//        String predictions = model.predict(img_array);
//        String score = tensorflow.nn.softmax(predictions.get(0));
//
//        System.out.println(
//                "This image most likely belongs to {} with a {:.2f} percent confidence."
//                        .format(flowerClassNames[numpy.argmax(score)], 100 * numpy.max(score)));
//    }
//
//    private static Tensor<UInt8> makeImageTensor(String filename) throws IOException {
//        BufferedImage img = ImageIO.read(new File(filename));
//        if (img.getType() != BufferedImage.TYPE_3BYTE_BGR) {
//            throw new IOException(
//                    String.format(
//                            "Expected 3-byte BGR encoding in BufferedImage, found %d (file: %s). This code could be made more robust",
//                            img.getType(), filename));
//        }
//        byte[] data = ((DataBufferByte) img.getData().getDataBuffer()).getData();
//        // ImageIO.read seems to produce BGR-encoded images, but the model expects RGB.
//        bgr2rgb(data);
//        final long BATCH_SIZE = 1;
//        final long CHANNELS = 3;
//        long[] shape = new long[]
//                {
//                        BATCH_SIZE, img.getHeight(), img.getWidth(), CHANNELS
//                };
//        return Tensor.create(UInt8.class, shape, ByteBuffer.wrap(data));

    }

