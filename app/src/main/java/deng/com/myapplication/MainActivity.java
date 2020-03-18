package deng.com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String db_name = "SQLiteDB";
    public static final String table_name = "user";
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openSQLiteDB();

        addData("Gary","123456@gmail.com","0912345678","boy");
        addData("sharron","654321@gmail.com","0911222333","girl");

        TextView info = findViewById(R.id.userInfo);
        Cursor cursor = db.rawQuery("Select * from " + table_name,null);
        info.setText(db.getPath()+"\n"
                    +db.getPageSize()+"\n"
                    +db.getMaximumSize()+"\n"
                    +"共"+cursor.getCount()+"筆資料");
        System.out.println("共"+cursor.getCount()+"筆資料");
        db.close();

    }

    private void openSQLiteDB() {
        db = openOrCreateDatabase(db_name, Context.MODE_PRIVATE,null);
        String SQL = "Create TABLE IF NOT EXISTS " + table_name +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(32),"+
                "email VARCHAR(32),"+
                "phone VARCHAR(12),"+
                "sex VARCHAR(10))";
        db.execSQL(SQL);
    }

    public void addData(String name,String email,String phone,String sex){
        ContentValues contentValues = new ContentValues(4);
        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("phone",phone);
        contentValues.put("sex",sex);
        db.insert(table_name,null,contentValues);
    }

}
