package com.example.tyudy.ticket2rideclient.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.tyudy.ticket2rideclient.ClientCommunicator;
import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.fragments.ChatFragment;
import com.example.tyudy.ticket2rideclient.fragments.GameBoardFragment;
import com.example.tyudy.ticket2rideclient.fragments.LoginFragment;
import com.example.tyudy.ticket2rideclient.fragments.PointsFragment;
import com.example.tyudy.ticket2rideclient.presenters.GameBoardPresenter;
import com.example.tyudy.ticket2rideclient.presenters.PresenterHolder;

public class GameBoardActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mPlayerScores;
    private ListView mMyInfo;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);


        fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.activity_game_board);

        if(fragment == null)
        {
            fragment = new GameBoardFragment();
            fm.beginTransaction()
                    .add(R.id.activity_game_board, fragment)
                    .add(R.id.points_holder, new ChatFragment())
                    .commit();
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.gameplay_layout);
        mPlayerScores = (ListView) findViewById(R.id.left_drawer);
        mMyInfo = (ListView) findViewById(R.id.right_drawer);

        //set adapters
    }

    @Override
    protected void onResume(){
        super.onResume();
        MethodsFacade.SINGLETON.setContext(this);
    }
}
