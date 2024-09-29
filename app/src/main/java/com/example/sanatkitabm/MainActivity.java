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
             database.execSQL("CREATE TABLE IF NOT EXISTS kitaplar (isim VARCHAR,numara INT PRİMARY KEY,yazar VARCHAR)");
             database.execSQL("INSERT INTO kitaplar(isim,numara,yazar) values ('Varoluşun Anlamı',1922,'Deneme Kitabıdır')");

             Cursor cursor=database.rawQuery("SELECT  * from kitaplar",null);

             int nameIndex=cursor.getColumnIndex("isim");
             int numaraIndex=cursor.getColumnIndex("numara");
             int yazarIndex=cursor.getColumnIndex("yazar");

             while (cursor.moveToNext()){
                 String name=cursor.getString(nameIndex).toString();
                 int numara=cursor.getInt(numaraIndex);
                 String yazar=cursor.getString(yazarIndex).toString();

                 System.out.println("Kitabın ismi: "+name);
                 System.out.println("Kitabın Numarası: "+numara);
                 System.out.println("Kitabın Yazarı: "+yazar);
             }
            cursor.close();

        }catch (Exception err){
            err.printStackTrace();
        }

    }
}