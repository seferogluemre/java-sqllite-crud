package com.example.sanatkitabm;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView txtName;
    TextView txtNumber;
    TextView txtYazar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        try {
             SQLiteDatabase database=this.openOrCreateDatabase("Kütüphane",MODE_PRIVATE,null);
             database.execSQL("CREATE TABLE IF NOT EXISTS Ögrenciler (numara INTEGER PRIMARY KEY,isim VARCHAR(25),sınıf VARCHAR)");

             database.execSQL("Delete from Ögrenciler");
             database.execSQL("INSERT INTO Ögrenciler (isim,sınıf) values ('Ahmet','11h')");
             database.execSQL("INSERT INTO Ögrenciler (isim,sınıf) values ('Efe','11h')");

             Cursor cursor=database.rawQuery("Select * from Ögrenciler where isim='Efe' " ,null);

             int nameIndex=cursor.getColumnIndex("isim");
             int numaraIndex=cursor.getColumnIndex("numara");
             int yazarIndex=cursor.getColumnIndex("sınıf");

             while (cursor.moveToNext()){
                 String name = cursor.getString(nameIndex).toString();
                 int numara = cursor.getInt(numaraIndex);
                 String yazar = cursor.getString(yazarIndex).toString();
                 System.out.println("Ögrenci Ad: "+name);
                 System.out.println("Kitabın Numarası: "+numara);
                 System.out.println("Ögrenci Sınıfı: "+yazar);
                 System.out.println("----------------------");
             }
            cursor.close();

        }catch (Exception err){
            err.printStackTrace();
        }

    }
}