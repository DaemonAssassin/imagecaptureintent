package com.gmail.mateendev3.imagecapture;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageView ivFullScreenImage = findViewById(R.id.iv_full_screen_image);
        ivFullScreenImage.setImageBitmap((Bitmap) getIntent().getExtras().get(MainActivity.BITMAP_IMAGE_CODE));
    }
}