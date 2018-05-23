package com.parag.sqlitedbtest;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText et1,et2,et3,et4;
    SQLiteDatabase dBase;

    ListView lview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        et3=(EditText)findViewById(R.id.et3);
        et4=(EditText)findViewById(R.id.et4);
        dBase=openOrCreateDatabase("empdb",
                Context.MODE_PRIVATE,null);
        dBase.execSQL("create table if not exists employee" +
                "(id number,name varchar(100),desig varchar(100),dept varchar(100))");

     //  lview=(ListView)findViewById(R.id.lview1);
    }

    public void insert(View v){
        ContentValues cv=new ContentValues();
        cv.put("id", Integer.parseInt(et1.getText().toString()));
        cv.put("name",et2.getText().toString());
        cv.put("desig",et3.getText().toString());
        cv.put("dept", et4.getText().toString());

         long count=dBase.insert("employee",null,cv);


       if(count>0) {
             Toast.makeText(MainActivity.this,"Record Inserted..", Toast.LENGTH_LONG).show();
             et1.setText("");
             et2.setText("");
             et3.setText("");
             et4.setText("");
        }else {
             Toast.makeText(MainActivity.this,
                     "Failed to insert..",
                     Toast.LENGTH_LONG).show();
        }

    }
     public void read(View v){


        Cursor c = dBase.query("employee",null,"id=?",
                new String[]{et1.getText().toString()},null,null,null);
        while(c.moveToNext()){

            Toast.makeText(getApplicationContext(),
                    c.getInt(0)+"\n"+
                            c.getString(1)+"\n"+
                            c.getString(2)+"\n"+
                            c.getString(3) ,
                    Toast.LENGTH_LONG).show();
        }
    }

    public void update(View v){
        ContentValues cv=new ContentValues();
        cv.put("id", Integer.parseInt(et1.getText().toString()));
        cv.put("name",et2.getText().toString());
        cv.put("desig",et3.getText().toString());
        cv.put("dept", et4.getText().toString());

        long count = dBase.update("employee",cv,"id=?",new String[]{et1.getText().toString()});

        if(count>0){
        Toast.makeText(getApplicationContext(),"Data is Updated",Toast.LENGTH_LONG).show();
            et1.setText("");
            et2.setText("");
            et3.setText("");
            et4.setText("");
        }else {
            Toast.makeText(getApplicationContext(), "Failed to Update", Toast.LENGTH_LONG).show();
        }

}
    public void delete(View v){
       long count =  dBase.delete("employee","id=?",new String[]{et1.getText().toString()});
        if(count>0){
            Toast.makeText(getApplicationContext(),"Data is Deleted",Toast.LENGTH_LONG).show();
            et1.setText("");
            et2.setText("");
            et3.setText("");
            et4.setText("");
        }else {
            Toast.makeText(getApplicationContext(), "Failed to Delete", Toast.LENGTH_LONG).show();
        }

    }
}
