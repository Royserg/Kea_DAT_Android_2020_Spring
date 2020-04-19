package com.example.image_resizer.controller;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.image_resizer.MainActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.example.image_resizer.MainActivity.REQUEST_CODE_CAMERA;
import static com.example.image_resizer.MainActivity.REQUEST_CODE_PHOTOROLL;

public class ImageController {

    private MainActivity mainActivity;
    int width;
    int height;
    int newWidth = 200;
    int newHeight = 200;
    Matrix matrix;
    Bitmap resizedBitmap;
    float scaleWidth;
    float scaleHeight;
    ByteArrayOutputStream outputStream;


    public ImageController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void handleImageReturn(int requestCode, @Nullable Intent intent) {
        if(requestCode == REQUEST_CODE_PHOTOROLL) {
            // get the url for the image
            Uri uri = intent.getData();
            try {
                // Create an inputstream to read the file
                InputStream is = mainActivity.getContentResolver().openInputStream(uri);  // the other is ContentProvider
                // Make Bitmap from stream
                Bitmap bitmap = BitmapFactory.decodeStream(is);

                // Set bitmap to imageView
                mainActivity.imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(requestCode == REQUEST_CODE_CAMERA) {
            // data provided with the intent
            Bitmap bitmap = (Bitmap) intent.getExtras().get("data");

            // Get original size of bitmap
            width = bitmap.getWidth();
            height = bitmap.getHeight();

            matrix = new Matrix();
            scaleWidth = ((float) newWidth) / width;
            scaleHeight = ((float) newHeight) / height;
            matrix.postScale(scaleWidth, scaleHeight);

            // Resize bitmap
            resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
            outputStream = new ByteArrayOutputStream();
            resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

            // Place resized bitmap into ImageView
            mainActivity.imageView.setImageBitmap(resizedBitmap);

            // Place bitmap into ImageView
//            mainActivity.imageView.setImageBitmap(bitmap);

            mainActivity.imageView.buildDrawingCache();
            Bitmap image = mainActivity.imageView.getDrawingCache();
            MediaStore.Images.Media.insertImage(mainActivity.getContentResolver(), image, "ImageFromCamera", "Description of Image");

        }
    }
}
