package com.example.image_resizer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.image_resizer.controller.ImageController;

public class MainActivity extends AppCompatActivity {

    public static int RESULT_CODE_SUCCESS = -1;
    public static int REQUEST_CODE_PHOTOROLL = 0;
    public static int REQUEST_CODE_CAMERA = 1;

    public ImageView imageView;
    private ImageController ic;
    public EditText widthEditText;
    public EditText heightEditText;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        ic = new ImageController(this);
    }

    public void photoRollBtnPressed(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);  //
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_PHOTOROLL);
    }

    public void cameraBtnPressed(View view) {
        // Check for permissons
        checkStorageWritePermission();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    private void checkStorageWritePermission() {
        // Not permitted - ask for permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            checkStorageWritePermission();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        // 1. check if result is OK. If not, then return
        if (resultCode == RESULT_CODE_SUCCESS) {
            ic.handleImageReturn(requestCode, intent);
        }
        return;
    }


}
