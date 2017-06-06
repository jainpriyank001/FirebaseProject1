package com.nearbuy.priyankjain.firebaseproject1;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button mFirebasebtn;

    private EditText mNameField;
    private EditText mEmailField;

    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebasebtn=(Button) findViewById(R.id.firebaseBtn);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mNameField=(EditText) findViewById(R.id.editText);
        mEmailField=(EditText)findViewById(R.id.editText2);
        mFirebasebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
            //create child in root object
            // Assign some value to child object
                String name=mNameField.getText().toString().trim();
                String email=mEmailField.getText().toString().trim();
                HashMap<String,String> dataMap=new HashMap<String,String>();
                dataMap.put("Name",name);
                dataMap.put("Email",email);

                mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this,"Stored Successfully",Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(MainActivity.this,"error in storing",Toast.LENGTH_LONG).show();


                    }
                });
            }
        });
    }
}

