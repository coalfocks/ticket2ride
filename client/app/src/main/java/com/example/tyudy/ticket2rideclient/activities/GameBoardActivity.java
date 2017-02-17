package com.example.tyudy.ticket2rideclient.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.model.ClientModelFacade;

public class GameBoardActivity extends AppCompatActivity {

    private Button mStartGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_board);

        mStartGameButton = (Button) findViewById(R.id.start_game_button);
        mStartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ClientModelFacade.SINGLETON.getCurrentTTRGame().getOwnerID() == ClientModelFacade.SINGLETON.getCurrentUser().getPlayerID()) {
                    MethodsFacade.SINGLETON.startGame();
                } else {
                    Toast.makeText(getBaseContext(), "Who do you think you are to start someone elses game?!", Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}
