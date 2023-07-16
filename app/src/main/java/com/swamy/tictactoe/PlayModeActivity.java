package com.swamy.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.swamy.tictactoe.databinding.PlaymodeActivityBinding;
import android.app.ActivityManager;

public class PlayModeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PlaymodeActivityBinding binding = PlaymodeActivityBinding.inflate(getLayoutInflater());
        View view= binding.getRoot();
        setContentView(view);
        binding.online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PlayModeActivity.this, "online will come soon", Toast.LENGTH_SHORT).show();
                binding.online.setBackground(getDrawable(R.drawable.rounded_cornor_enabled));
            }
        });
        binding.offline.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PlayModeActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }

        });
        binding.youvsAi.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Toast.makeText(PlayModeActivity.this, "not availble now", Toast.LENGTH_SHORT).show();
                binding.youvsAi.setBackground(getDrawable(R.drawable.rounded_cornor_enabled));
                binding.online.setBackground(getDrawable(R.drawable.rounded_cornor));
                binding.offline.setBackground(getDrawable(R.drawable.rounded_cornor));
            }
        });


    }

}

