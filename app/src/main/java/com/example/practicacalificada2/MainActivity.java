package com.example.practicacalificada2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static final int CAMERA_PIC_REQUEST = 1;

    private int mYearIni, mMonthIni, mDayIni, sYearIni, sMonthIni, sDayIni;
    static final int DATE_ID = 0;
    Calendar C = Calendar.getInstance();
    private String formato_fecha ="";

    EditText et_fecha ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sMonthIni = C.get(Calendar.MONTH);
        sDayIni = C.get(Calendar.DAY_OF_MONTH);
        sYearIni = C.get(Calendar.YEAR);

        et_fecha = (EditText) findViewById(R.id.et_fecha);

        ((Button) findViewById(R.id.btn_foto)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,CAMERA_PIC_REQUEST);
            }
        });

        et_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_ID);
            }
        });


        ((Button) findViewById(R.id.btn_direccion)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri location = Uri.parse("geo:0.0?q=Universidad+Privada+de+Tacna,+Granada,+Tacna ");
                Intent locationIntent  = new Intent(Intent.ACTION_VIEW , location);
                startActivity(locationIntent);
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

    private void colocar_fecha() {

        et_fecha.setText( mDayIni + "-" + (mMonthIni + 1) + "-" + mYearIni+" ");

        formato_fecha = mDayIni + "-" + (mMonthIni + 1) + "-" + mYearIni+" ";
    }
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYearIni = year;
            mMonthIni = monthOfYear;
            mDayIni = dayOfMonth;
            colocar_fecha();
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_ID:
                return new DatePickerDialog(this, mDateSetListener, sYearIni, sMonthIni, sDayIni);
        }
        return null;
    }


}