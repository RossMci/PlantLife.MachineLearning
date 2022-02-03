import tensorflow as tf
from tensorflow import keras
import numpy as np


class_names = ['daisy', 'dandelion', 'roses', 'sunflowers', 'tulips']

img_height = 180
img_width = 180

model_filepath="flower-ann-model.h5"
model = keras.models.load_model(model_filepath)

sunflower_url = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.D9H_OQXojeeCYap1SW66pQHaE7%26pid%3DApi&f=1"
sunflower_path = tf.keras.utils.get_file('Red_rose', origin=sunflower_url)

img = tf.keras.utils.load_img(
    sunflower_path, target_size=(img_height, img_width)
)
img_array = tf.keras.utils.img_to_array(img)
img_array = tf.expand_dims(img_array, 0) # Create a batch

predictions = model.predict(img_array)
score = tf.nn.softmax(predictions[0])

print(
    "ZZ This image most likely belongs to {} with a {:.2f} percent confidence."
    .format(class_names[np.argmax(score)], 100 * np.max(score))
)