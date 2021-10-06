import pickle
import tensorflow as tf
import os

VGG=tf.keras.applications.VGG19(weights="C:\\Users\\HP\\anaconda3\\envs\\training\\Lib\\site-packages\\keras\\applications\\VGG19.h5")
image_encoder=tf.keras.Model(inputs=VGG.input,outputs=VGG.layers[-2].output)
print(image_encoder.summary())

features=pickle.load(open('C:\\Users\\HP\\coco_image_features_from_drive_final_final','rb'))
#features={}
directory="C:\\Users\\HP\\COCO\\train2017"

a=1
processed=1

for name in os.listdir(directory):
        # load an image from file
        processed=processed+1
        print(a,processed)
        print(name.split('\\')[-1])
        if name.split('\\')[-1] in features:
          continue

        filename = directory + "\\" + name
        image = tf.keras.preprocessing.image.load_img(filename, target_size=(224, 224))
        # convert the image pixels to a numpy array
        image = tf.keras.preprocessing.image.img_to_array(image)
        # reshape data for the model
        image = image.reshape((1, image.shape[0], image.shape[1], image.shape[2]))
        # prepare the image for the VGG model
        image = tf.keras.applications.vgg19.preprocess_input(image)
        # get features
        feature = image_encoder.predict(image, verbose=0)
        # get image id
        image_id = name.split('\\')[-1]
        # store feature
        features[image_id] = feature
        
        a=a+1
        if a==500:
                break

pickle.dump(features, open('coco_image_features_from_drive_final_final_final', 'wb'))
