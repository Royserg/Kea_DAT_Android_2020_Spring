package com.example.imageuploadertofirestore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.example.imageuploadertofirestore.databinding.ActivityMainBinding;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private static int CAMERA_REQUEST_CODE = 5;
    private static int GALLERY_REQUEST_CODE = 9;
    private Bitmap bitmap;

    // Firebase
    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    /* Open gallery */
    public void gelleryBtnPressed(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    public void cameraBtnPressed(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    public void uploadToFirestore(View v) {
        ImageRepo.uploadPhoto(this, bitmap);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE) {
            handleGalleryAcitivityResult(resultCode, data);
        } else if (requestCode == CAMERA_REQUEST_CODE) {
            handleCameraActivityResult(resultCode, data);
        }
    }

    private void handleCameraActivityResult(int resultCode, @Nullable Intent data) {
        if (resultCode == -1) {  // -1 is a code for success
            bitmap = (Bitmap) data.getExtras().get("data");
            // Set image to the image View
            binding.imageView.setImageBitmap(bitmap);
        } else {
            System.out.println("Failed to get image");
        }
    }

    private void handleGalleryAcitivityResult(int resultCode, @Nullable Intent data) {
        if (resultCode == -1) {
            Uri uri = data.getData(); // Get path to the image
            try {
                InputStream is = getContentResolver().openInputStream(uri);
                bitmap = BitmapFactory.decodeStream(is);
                // Assign imageview to bitmap
                binding.imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                System.out.println("Error " + e);
            }
        } else {
            System.out.println("Failed");
        }
    }
}
