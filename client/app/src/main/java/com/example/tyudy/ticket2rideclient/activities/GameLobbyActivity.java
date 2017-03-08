package com.example.tyudy.ticket2rideclient.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.presenters.GameLobbyPresenter;
import com.example.tyudy.ticket2rideclient.presenters.PresenterHolder;

public class GameLobbyActivity extends AppCompatActivity {

    private Button mStartGameButton;
    private GameLobbyPresenter mGameLobbyPresenter;
    private TextView mWelcomeMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_lobby);
        mGameLobbyPresenter = PresenterHolder.SINGLETON.getGameLobbyPresenter();
        mGameLobbyPresenter.setGameLobbyActivity(this);

        mStartGameButton = (Button) findViewById(R.id.start_game_button);
        mStartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mGameLobbyPresenter.startGameClicked();
            }
        });

        mWelcomeMsg = (TextView) findViewById(R.id.game_board_title);
        mWelcomeMsg.setText("Welcome to " + ClientModel.SINGLETON.getCurrentTTRGame().getOwnerUsername() + "'s game!");
    }

    @Override
    protected void onResume(){
        super.onResume();
        MethodsFacade.SINGLETON.setContext(this);
    }

    public void onStartGame(){
        // Launch GameBoardActivity Activity
        Intent i = new Intent(this, GameBoardActivity.class);
        startActivity(i);
    }
}
