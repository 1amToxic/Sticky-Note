package com.example.saira_000.stickynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextPaint;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

class Option{
    public String topic,content;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;
    Button btnadd, btnsearch;
    EditText editText;
    String[] Topic = {""};
    ArrayList<Option> arrayList;
    ArrayAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList = new ArrayList<>();
        adapter= new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, Topic);
        editText = (EditText) findViewById(R.id.content);
        btnadd = (Button) findViewById(R.id.add);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hint = autoCompleteTextView.getText().toString().trim();
                String contenT = editText.getText().toString().trim();
                System.out.println(hint + "\n" + contenT);
                if (!hint.equals("") && !contenT.equals("")) {
                    Option op = new Option();
                    op.setTopic(hint);
                    op.setContent(contenT);
                    if (isExist(hint) == -1) {
                        adapter.add(op.getTopic().toString());
                    }
                    arrayList.add(op);
                    autoCompleteTextView.setText("");
                    editText.setText("");
                }
            }
        });
        btnsearch = (Button) findViewById(R.id.search);
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean canFind = false;
                String findtopic = autoCompleteTextView.getText().toString().trim();
                for (Option s : arrayList) {
                    if (s.getTopic().equals(findtopic)) {
                        editText.setText(s.getContent());
                        canFind = true;
                        break;
                    }
                }
                if (canFind == false) {
                    Toast.makeText(MainActivity.this, "Can not find. Plz Add", Toast.LENGTH_SHORT).show();
                    editText.requestFocus();
                }
            }
        });
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.auto);
        autoCompleteTextView.setThreshold(1);//start matching from first char
        autoCompleteTextView.setAdapter(adapter);//set adapter to save what you searched
    }

    public int isExist(String s) {
        for (String tmp : Topic) {
            if (tmp.equals(s)) {
                return 1;
            }
        }
        return -1;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:{
                String hint = autoCompleteTextView.getText().toString().trim();
                String contenT = editText.getText().toString().trim();
                System.out.println(hint + "\n" + contenT);
                if (!hint.equals("") && !contenT.equals("")) {
                    Option op = new Option();
                    op.setTopic(hint);
                    op.setContent(contenT);
                    if (isExist(hint) == -1) {
                        adapter.add(op.getTopic().toString());
                    }
                    arrayList.add(op);
                    autoCompleteTextView.setText("");
                    editText.setText("");
                }
                return true;
            }

            case R.id.about:{
                Toast.makeText(this, "Made by LamKz", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.search:{
                boolean canFind = false;
                String findtopic = autoCompleteTextView.getText().toString().trim();
                for (Option s : arrayList) {
                    if (s.getTopic().equals(findtopic)) {
                        editText.setText(s.getContent());
                        canFind = true;
                        break;
                    }
                }
                if (canFind == false) {
                    Toast.makeText(MainActivity.this, "Can not find. Plz Add", Toast.LENGTH_SHORT).show();
                    editText.requestFocus();
                }
                return true;
            }
            case R.id.help:{
                Toast.makeText(this, "Call Lam", Toast.LENGTH_SHORT).show();
                return  true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}