package com.example.practicacalificada2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public static final int CAMERA_PIC_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ((Button) findViewById(R.id.btn_foto)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,CAMERA_PIC_REQUEST);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == CAMERA_PIC_REQUEST)
        {
            if(resultCode == RESULT_OK)
            {
                Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                ImageView iv_foto = (ImageView) findViewById(R.id.iv_mifoto);

                iv_foto.setImageBitmap(bitmap);

            }
        }

    }
}