package com.mtha.mynoteproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CaptureActivity extends AppCompatActivity {
    ImageView imageView;
    Button takePhoto;
    ActivityResultLauncher activityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        imageView  = findViewById(R.id.imgProfile);
        takePhoto = findViewById(R.id.takePhoto);

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                            //khoi tao doi tuong Bundle de chua du lieu dang object
                            Bundle bundle = result.getData().getExtras();
                            Bitmap img = (Bitmap) bundle.get("data");
                            //set hien thi tren imageview
                            imageView.setImageBitmap(img);
                        }
                    }
                });
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mo dich vu chup anh cua he thong
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager())!=null){
                    //thuc hien chup anh va xu ly ket qua
                    activityResultLauncher.launch(intent);
                }else{
                    //khong ho tro
                    Toast.makeText(CaptureActivity.this, "Not support",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}