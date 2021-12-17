package com.gmail.mateendev3.imagecapture;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String BITMAP_IMAGE_CODE = "com.gmail.mateendev3.imagecapture.IMAGE_CAPTURE";
    ImageView ivCameraImage;
    Button btnTakePhoto, btnSetFullScreenImage;

    Bitmap mBitmapImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivCameraImage = findViewById(R.id.iv_camera_image);
        btnTakePhoto = findViewById(R.id.btn_take_photo);
        btnSetFullScreenImage = findViewById(R.id.btn_full_screen_image);

        ActivityResultLauncher<Intent> imageLauncher = registerForActivityResult(
                new TakePhoto(),
                new ActivityResultCallback<Intent>() {
                    @Override
                    public void onActivityResult(Intent result) {
                        if (result != null) {
                            mBitmapImage = (Bitmap) result.getExtras().get("data");
                            ivCameraImage.setImageBitmap(mBitmapImage);
                        } else {
                            Toast.makeText(MainActivity.this, "No Image Captured", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        btnTakePhoto.setOnClickListener(v -> {
            imageLauncher.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
        });

        btnSetFullScreenImage.setOnClickListener(v -> {
            if (mBitmapImage == null) {
                Toast.makeText(MainActivity.this, "Please Take Image First", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra(BITMAP_IMAGE_CODE, mBitmapImage);
                startActivity(intent);
            }
        });
    }

    public static final class TakePhoto extends ActivityResultContract<Intent, Intent> {

        @NonNull
        @Override
        public Intent createIntent(@NonNull Context context, Intent input) {
            return input;
        }

        @Override
        public Intent parseResult(int resultCode, @Nullable Intent intent) {
            if (intent == null || resultCode != Activity.RESULT_OK) return null;
            return intent;
        }
    }
}