package com.example.shivam.databasehelper;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView mTextView,mTextView1;
    EditText mEditText,mEditText1;
    Button mButton,mButton1;
    ListView mListView;
    DatabaseHelper mDatabaseHelper;
    String db_name="studentInfo";
    int db_version=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabaseHelper= new DatabaseHelper(getApplicationContext(),db_name,db_version);
        mTextView=(TextView)findViewById(R.id.txtView1);
        mTextView1=(TextView)findViewById(R.id.txtView2);
        mEditText=(EditText)findViewById(R.id.edtText1);
        mEditText1=(EditText)findViewById(R.id.edtText2);
        mButton=(Button)findViewById(R.id.btnInsert);
        mButton1=(Button)findViewById(R.id.btnShow);
        create();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mEditText.getText().toString();
                String place = mEditText1.getText().toString();
                Insert(name,place);
            }
        });
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListView=(ListView)findViewById(R.id.listVIew);
                updateList();
            }
        });

    }
    public void create()
    {
        String query="CREATE TABLE IF NOT EXISTS stud(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT,place TEXT);";
        mDatabaseHelper.executeQuery(query);
        Toast.makeText(this, "Table Created", Toast.LENGTH_SHORT).show();
    }
    public void Insert(String name,String place)
    {
        String query="INSERT INTO stud(name,place)VALUES('"+name+"','"+place+"');";
        mDatabaseHelper.executeQuery(query);
        Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
    }
    public ArrayList<String> selectAll()
    {
        ArrayList<String> data = new ArrayList<>();
        String query = "SELECT * FROM stud";
        Cursor result = mDatabaseHelper.executeSelect(query);
        if(result.moveToFirst())
        {
            do {
                int id = result.getInt(0);
                String name = result.getString(1);
                String place= result.getString(2);
                data.add(id+","+name+","+place);
            }while(result.moveToNext());
        }
        return data;
    }
    public void updateList()
    {   ArrayList<String > values = selectAll();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,values);
        mListView.setAdapter(arrayAdapter);
    }
}
class Help
{

}
