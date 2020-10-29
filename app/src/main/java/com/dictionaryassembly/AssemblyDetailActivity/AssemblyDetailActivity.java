package com.dictionaryassembly.AssemblyDetailActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dictionaryassembly.Objects.AssemblyForm;
import com.dictionaryassembly.R;
import com.squareup.picasso.Picasso;

public class AssemblyDetailActivity extends AppCompatActivity {

    TextView textViewTitle, textViewContent, textViewDescription, textViewtextSample, textViewCuphap;
    ImageView imageSample;
    AssemblyForm assemblyForm;
    LinearLayout linearLayoutInterrupt, linearLayoutImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assembly_detail);

        Init();

        loadContent();


    }

    private void Init(){
        textViewTitle = findViewById(R.id.title);
        textViewContent = findViewById(R.id.content);
        textViewDescription = findViewById(R.id.description);
        textViewtextSample = findViewById(R.id.textSample);
        textViewCuphap = findViewById(R.id.cuphap);
        imageSample = findViewById(R.id.imageSample);
        linearLayoutInterrupt = findViewById(R.id.interrupt);
        linearLayoutImage = findViewById(R.id.image);


        assemblyForm = (AssemblyForm) getIntent().getSerializableExtra("ITEM");
        setTitle(assemblyForm.getTitle());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void loadContent(){

        textViewTitle.setText(assemblyForm.getTitle());
        textViewDescription.setText(assemblyForm.getDescription());

        if(assemblyForm.getImageLink()==null|| assemblyForm.getImageLink().equals("")){
            linearLayoutImage.setVisibility(View.GONE);
        }else {
            Picasso.get().load(assemblyForm.getImageLink()).into(imageSample);
        }

        switch (assemblyForm.getType()){
            case STATEMENT:
            case MACRO:
            case STRUCT:{
                textViewContent.setText(assemblyForm.getContent());
                linearLayoutInterrupt.setVisibility(View.GONE);
                break;
            }
            case INTERRUPT:{
                textViewContent.setText(assemblyForm.getTypeInterrupt());
                textViewCuphap.setText(assemblyForm.getContent());
                break;
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}