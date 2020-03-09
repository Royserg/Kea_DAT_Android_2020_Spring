package com.example.mynotebook.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.mynotebook.R;
import com.example.mynotebook.databinding.ActivityMainBinding;
import com.example.mynotebook.repositories.ImgRepository;
import com.example.mynotebook.repositories.NoteRepository;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    AppBarConfiguration appBarConfiguration;

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

//        ImgRepository.uploadTextFile(this);
//        ImgRepository.uploadImage(this);

    }

    /* Gallery opening */
    public void galleryBtnPressed() {

        // Make implicit intent, which displays options to choose
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    public void cameraBtnPressed() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) { // From gallery
            if (resultCode != -1) {
                Uri uri = data.getData(); // Get path to the image
                try {
                    InputStream is = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    // Assign imageview to bitmap
                } catch (Exception e) {
                    System.out.println("Error " + e);
                }
            } else {
                System.out.println("Failed");
            }
        }
        else if (requestCode == 2) {  // From camera
            if (resultCode != -1) {  // -1 is a code for success
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");

                // Set image to the image View
                //...
            } else {
                System.out.println("Failed to get image");
            }
        }


    }

    /* == Gallery opening - end == */

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        navController = Navigation.findNavController(this, R.id.main_content);

        Toolbar toolbar = (Toolbar) binding.toolbar;
        appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbar, navController);
        setSupportActionBar(toolbar);

//        NoteRepository.addRandomNote();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        /* Gallery Selected */
        if (item.getItemId() == R.id.open_gallery) {
            galleryBtnPressed();
//            cameraBtnPressed();
            return true;
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavController navcontroller = Navigation.findNavController(this, R.id.main_content);
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);

    }
}
