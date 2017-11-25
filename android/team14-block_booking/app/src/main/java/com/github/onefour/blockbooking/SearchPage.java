package com.github.onefour.blockbooking;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

/**
 * Created by shop on 25/11/2017.
 */

public class SearchPage extends AppCompatActivity {
    private CardView demo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);
        if(GenerationActivity.getInstance()!=null)GenerationActivity.getInstance().finish();
        demo = (CardView)findViewById(R.id.demo);
        demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchPage.this, com.github.onefour.blockbooking.Detail.class);
                startActivity(i);
            }
        });
    }

}
