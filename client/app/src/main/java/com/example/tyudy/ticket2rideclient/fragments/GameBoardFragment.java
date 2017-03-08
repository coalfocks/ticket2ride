package com.example.tyudy.ticket2rideclient.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.common.cards.TrainCard;
import com.example.tyudy.ticket2rideclient.interfaces.iObserver;
import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.presenters.GameBoardPresenter;
import com.example.tyudy.ticket2rideclient.presenters.PresenterHolder;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;

import static com.example.tyudy.ticket2rideclient.R.color.yellow;
import static com.example.tyudy.ticket2rideclient.common.Color.YELLOW;


/**
 * Created by colefox on 2/24/17.
 */

public class GameBoardFragment extends Fragment implements iObserver
{
    private DrawerLayout mDrawerLayout;
    private ListView mPlayerScores;
    private ListView mMyInfo;
    private SlidingUpPanelLayout mChat;
    private GameBoardPresenter mGameBoardPresenter;
    private ArrayList<User> mPlayers;
    private ArrayList<String> mPlayerNames;
    private ArrayList<TrainCard> mCards;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mGameBoardPresenter = PresenterHolder.SINGLETON.getGameBoardPresenter();
        mGameBoardPresenter.setGameBoardFragment(this);
        ClientModel.SINGLETON.addObserver(this);

        User pug = new User();
        pug.setUsername("pug");
        pug.setColor(Color.RED);
        pug.addPoints(1000);

        User cat = new User();
        cat.setUsername("cat");
        cat.setColor(Color.YELLOW);

        User milo = new User();
        milo.setUsername("milo");
        milo.setColor(Color.BLUE);
        milo.addPoints(100);

        User golden = new User();
        golden.setUsername("daisy");
        golden.setColor(Color.MAGENTA);
        golden.addPoints(10000000);

        mPlayers = new ArrayList<>();
        mPlayers.add(pug);
        mPlayers.add(cat);
        mPlayers.add(milo);
        mPlayers.add(golden);

//        mCards = ClientModel.SINGLETON.getCurrentPlayer().returnTrainCards();
        mCards = new ArrayList<TrainCard>();
        for(int i = 0; i < 10; i++){
            TrainCard myCard = new TrainCard();
            myCard.setColor(YELLOW);
            myCard.setNum(i);
            mCards.add(myCard);
        }

        
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.gameplay_fragment, container, false);

        mDrawerLayout = (DrawerLayout) v.findViewById(R.id.gameplay_layout);
        mPlayerScores = (ListView) v.findViewById(R.id.left_drawer);
        mMyInfo = (ListView) v.findViewById(R.id.right_drawer);
        mChat = (SlidingUpPanelLayout) v.findViewById(R.id.bottom_sheet);

        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener()
        {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset)
            {

            }

            @Override
            public void onDrawerOpened(View drawerView)
            {

            }

            @Override
            public void onDrawerClosed(View drawerView)
            {

            }

            @Override
            public void onDrawerStateChanged(int newState)
            {

            }
        });

        //observe function to make it change with updated info
        this.observe();

        return v;
    }

    @Override
    public void observe()
    {
        //        mCards = ClientModel.SINGLETON.getCurrentPlayer().returnTrainCards();
        mPlayerScores.setAdapter(new PlayerAdapter(this.getContext(),
                R.layout.points_fragment, mPlayers));
        mMyInfo.setAdapter(new CardsAdapter(this.getContext(),
                R.layout.points_fragment, mCards));

    }

    private class PlayerAdapter extends ArrayAdapter<User> {

        private Context mContext;

        public PlayerAdapter(Context context, int resourceId, ArrayList<User> items) {
            super(context, resourceId, items);
            this.mContext = context;
        }

        /*private view holder class*/
        private class ViewHolder {
            TextView mUsername;
            TextView mPoints;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            User user = getItem(position);

            LayoutInflater mInflater = (LayoutInflater) mContext
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.points_fragment, null);
                holder = new ViewHolder();
                holder.mUsername = (TextView) convertView.findViewById(R.id.player_username);
                holder.mPoints = (TextView) convertView.findViewById(R.id.player_points);
                convertView.setTag(holder);
            } else
                holder = (ViewHolder) convertView.getTag();

            holder.mUsername.setText(user.getUsername().toUpperCase());
            holder.mUsername.setBackgroundColor(user.getColor());
            holder.mPoints.setText(String.valueOf(user.getPoints()));

            return convertView;
        }
    }

    private class CardsAdapter extends ArrayAdapter<TrainCard> {

        private Context mContext;

        public CardsAdapter(Context context, int resourceId, ArrayList<TrainCard> items) {
            super(context, resourceId, items);
            this.mContext = context;
        }

        /*private view holder class*/
        private class ViewHolder {
            TextView mUsername;
            TextView mPoints;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            TrainCard myCard = getItem(position);

            LayoutInflater mInflater = (LayoutInflater) mContext
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.points_fragment, null);
                holder = new ViewHolder();
                holder.mUsername = (TextView) convertView.findViewById(R.id.player_username);
                holder.mPoints = (TextView) convertView.findViewById(R.id.player_points);
                convertView.setTag(holder);
            } else
                holder = (ViewHolder) convertView.getTag();
                holder.mUsername.setText(String.valueOf(myCard.getNum()));
                switch( myCard.getColor()) {
                    case YELLOW:
                        holder.mUsername.setBackgroundColor(Color.YELLOW);
                        break;
                    default:
                        holder.mUsername.setBackgroundColor(Color.BLACK);
                        break;
                }

            return convertView;
        }
    }
}