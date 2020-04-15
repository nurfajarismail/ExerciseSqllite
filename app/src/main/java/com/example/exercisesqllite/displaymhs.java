package com.example.exercisesqllite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class displaymhs extends AppCompatActivity {
    int from_Where_I_Am_Coming = 0;
    private DBHelper mydb ;

    EditText email ;
    EditText phone;
    EditText nama;
    EditText alamat;

    int id_To_Update = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaymhs);
        nama = (EditText) findViewById(R.id.editname);
        phone= (EditText) findViewById(R.id.editPhone);
        email=(EditText) findViewById(R.id.editemail);
        alamat=(EditText) findViewById(R.id.editalamat);

        mydb = new DBHelper(this);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");

            if(Value>0){
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();

                String nam = rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_NAMA));
                String pho = rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_PHONE));
                String emaill = rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_EMAIL));
                String alamattt = rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_ALAMAT));
                if (!rs.isClosed())  {
                    rs.close();
                }
                Button b = (Button)findViewById(R.id.button1);
                b.setVisibility(View.INVISIBLE);


                nama.setText((CharSequence)nam);
                nama.setFocusable(false);
                nama.setClickable(false);

                phone.setText((CharSequence)pho);
                phone.setFocusable(false);
                phone.setClickable(false);

                email.setText((CharSequence)emaill);
                email.setFocusable(false);
                email.setClickable(false);

                alamat.setText((CharSequence)alamattt);
                alamat.setFocusable(false);
                alamat.setClickable(false);

            }
        }
    }

    public  void run(View view){
        if (nama.getText().toString().equals("") ||
                phone.getText().toString().equals("") ||
                alamat.getText().toString().equals("") ||
                email.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Data Harus Diisi Semua",Toast.LENGTH_LONG).show();
        }
        else{
            mydb.insertContact(nama.getText().toString(), phone.getText().toString(), email.getText().toString(), alamat.getText().toString());
            Toast.makeText(getApplicationContext(), "Insert Data Berhasil",Toast.LENGTH_LONG).show();
            Intent i =new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
    }

}



