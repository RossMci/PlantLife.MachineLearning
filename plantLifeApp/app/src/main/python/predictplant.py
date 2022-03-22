import json
import numpy as np
import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '3'

import tensorflow as tf
import sys

from tensorflow import keras

def main():
    # loading saved model
    model_filepath="flower-ann-model.h5"
    model = keras.models.load_model(model_filepath)

    sunflower_path = sys.argv[1] # adjustment 1

    img_height = 180
    img_width = 180

    img = tf.keras.utils.load_img(
    sunflower_path, target_size=(img_height, img_width)
    )
    img_array = tf.keras.utils.img_to_array(img)
    img_array = tf.expand_dims(img_array, 0) # Create a batch

    predictions = model.predict(img_array)

    score = tf.nn.softmax(predictions[0])

    # adjustment 2
    class_names=['daisy', 'dandelion', 'roses', 'sunflowers', 'tulips']

    json_result_dictonary = {'class': class_names[np.argmax(score)], 'score': 100 * np.max(score)}
    return json.dumps(json_result_dictonary)
    #return class_names[np.argmax(score)] + "," +  (100 * np.max(score))

#print(    "This image most likely belongs to {} with a {:.2f} percent confidence."     .format(class_names[np.argmax(score)], 100 * np.max(score)))
#print(main())