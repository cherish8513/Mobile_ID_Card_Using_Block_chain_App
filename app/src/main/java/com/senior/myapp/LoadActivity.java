package com.senior.myapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class LoadActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button btn_test;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        imageView = findViewById(R.id.iv_view);
        File dir = Environment.getExternalStorageDirectory().getAbsoluteFile();
        String path = dir.getPath() + "/" + "Captures/";
        String fileName = "IMG_20200429-15-24-55.jpg";
        File pictureFile = new File(path,fileName);
        Toast.makeText(LoadActivity.this, "saved" + pictureFile, Toast.LENGTH_SHORT).show();
        String filePath = pictureFile.getPath();
        final Bitmap bitmap = BitmapFactory.decodeFile(filePath);

        btn_test = findViewById(R.id.btn_test);
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageBitmap(bitmap);
            }
        });

       // File imgFile = new File("/sdcard/Images/test_image.jpg");

       // if(imgFile.exists()){
       //     Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
       //     imageView.setImageBitmap(myBitmap);
        }
}
