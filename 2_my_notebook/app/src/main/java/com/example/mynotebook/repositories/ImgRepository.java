package com.example.mynotebook.repositories;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.mynotebook.ui.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;

public class ImgRepository {
    static FirebaseStorage storage = FirebaseStorage.getInstance();

    public static void uploadTextFile(Context context) {
        try {
            InputStream is = context.getAssets().open("alice.txt");
            StorageReference ref = storage.getReference("alice.txt");
            ref.putStream(is)
                    .addOnCompleteListener(task -> System.out.println("download completed"))
                    .addOnFailureListener(e -> System.out.println("error" + e.getMessage()));

            if (is != null) {
                System.out.println("Found the file");
            } else {
                System.out.println("not found");
            }
        } catch (Exception e) {

        }
    }

    public static void uploadImage(Context context) {
        try {
            StorageReference ref = storage.getReference().child("solo_leveling.png");
//            ref.putFile();

        } catch (Exception e) {

        }
    }

    public static void downloadImage(String name, ImageView iv) {
        // get a reference from the file storage, given a file name
        StorageReference ref = storage.getReference(name);
        int max = 1024 * 1024 * 2; // 2 mb maximum
        ref.getBytes(max).addOnSuccessListener(bytes -> {
            Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            iv.setImageBitmap(bm); // set image to imageView
        });

    }
}
