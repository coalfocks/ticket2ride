package com.example.tyudy.ticket2rideclient.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tyudy.ticket2rideclient.common.Color;
import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.common.cards.TrainCard;
import com.example.tyudy.ticket2rideclient.interfaces.iObserver;
import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.presenters.GameBoardPresenter;
import com.example.tyudy.ticket2rideclient.presenters.PresenterHolder;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;


/**
 * Created by colefox on 2/24/17.
 */

public class GameBoardFragment extends Fragment implements iObserver
{
    private DrawerLayout mDrawerLayout;
    private ListView mPlayerScores;
    private ListView mMyInfo;

    private ImageButton mDestCardsButton;
    private SlidingUpPanelLayout mChat;

    private ArrayList<User> mUsers;
    private ArrayList<TrainCard> mCards;
    
    private GameBoardPresenter mGameBoardPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mGameBoardPresenter = PresenterHolder.SINGLETON.getGameBoardPresenter();
        mGameBoardPresenter.setGameBoardFragment(this);
        ClientModel.SINGLETON.addObserver(this);

//        User pug = new User();
//        pug.setUsername("pug");
//        pug.setColor(Color.RED);
//        pug.addPoints(1000);
//
//        User cat = new User();
//        cat.setUsername("cat");
//        cat.setColor(Color.YELLOW);
//
//        User milo = new User();
//        milo.setUsername("milo");
//        milo.setColor(Color.BLUE);
//        milo.addPoints(100);
//
//        User golden = new User();
//        golden.setUsername("daisy");
//        golden.setColor(Color.MAGENTA);
//        golden.addPoints(10000000);
//        mPlayers.add(pug);
//        mPlayers.add(cat);
//        mPlayers.add(milo);
//        mPlayers.add(golden);

//        mCards = ClientModel.SINGLETON.getCurrentPlayer().returnTrainCards();
//        mCards = new ArrayList<TrainCard>();
//        for(int i = 0; i < 10; i++){
//            TrainCard myCard = new TrainCard();
//            myCard.setColor(YELLOW);
//            myCard.setNum(i);
//            mCards.add(myCard);
//        }
        // set current player
        mUsers = new ArrayList<>(ClientModel.SINGLETON.getCurrentTTRGame().getUsers());

        mCards = ClientModel.SINGLETON.getCurrentUser().getTrainCards();
        //mCards = new ArrayList<TrainCard>();
//        for(int i = 0; i < 10; i++){
//            TrainCard myCard = new TrainCard();
//            myCard.setColor(Color.YELLOW);
//            myCard.setNum(i);
//            mCards.add(myCard);
//        }
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
        mDestCardsButton = (ImageButton) v.findViewById(R.id.dest_cards_button);
        mChat = (SlidingUpPanelLayout) v.findViewById(R.id.bottom_sheet);

//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//
//        fm.beginTransaction();
//        Fragment chat = new ChatFragment();
//        ft.add(R.id.chat_container, chat);
//        ft.commit();

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

        mDestCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGameBoardPresenter.showDestCards();
            }
        });

        mPlayerScores.setAdapter(new PlayerAdapter(this.getContext(),
                R.layout.points_fragment, mUsers));

        //observe function to make it change with updated info
        this.observe();

        return v;
    }

    @Override
    public void observe()
    {
        mPlayerScores.setAdapter(new PlayerAdapter(this.getContext(), R.layout.points_fragment, mUsers));
        mMyInfo.setAdapter(new CardsAdapter(this.getContext(),
                R.layout.points_fragment, mCards));

    }


    private class PlayerAdapter extends ArrayAdapter<User> {

        private Context mContext;
        private ArrayList<User> users;

        public PlayerAdapter(Context context, int resourceId, ArrayList<User> items) {
            super(context, resourceId, items);
            this.mContext = context;
            this.users = items;
        }

        /*private view holder class*/
        private class ViewHolder {
            TextView mUsername;
            TextView mPoints;
            TextView mTrains;
            TextView mDest;
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
                holder.mTrains = (TextView) convertView.findViewById(R.id.train_cards);
                holder.mDest = (TextView) convertView.findViewById(R.id.dest_cards);
                convertView.setTag(holder);
            } else
                holder = (ViewHolder) convertView.getTag();

            holder.mUsername.setText(user.getUsername().toUpperCase());
            Color color = user.getColor();
            if(color == null){
                holder.mUsername.setBackgroundColor(android.graphics.Color.LTGRAY);
            }
            else{
            switch( color) {
                case YELLOW:
                    holder.mUsername.setBackgroundColor(YELLOW);
                    break;
                case PURPLE:
                    holder.mUsername.setBackgroundColor(PURPLE);
                    break;
                case BLACK:
                    holder.mUsername.setBackgroundColor(BLACK);
                    break;
                case WHITE:
                    holder.mUsername.setBackgroundColor(WHITE);
                    break;
                case GREEN:
                    holder.mUsername.setBackgroundColor(GREEN);
                    break;
                case ORANGE:
                    holder.mUsername.setBackgroundColor(ORANGE);
                    break;
                case BLUE:
                    holder.mUsername.setBackgroundColor(BLUE);
                    break;
                case RED:
                    holder.mUsername.setBackgroundColor(RED);
                    break;
                default:
                    holder.mUsername.setBackgroundColor(android.graphics.Color.LTGRAY);
                    break;
            }
            }
            holder.mPoints.setText(String.valueOf(user.getPoints()));
            String trains = "Train Cards: " + String.valueOf(user.getTrainCards().size());
            holder.mTrains.setText(trains);
            String dest = "Dest Cards: " + String.valueOf(user.getDestCards().size());
            holder.mDest.setText(dest);

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
                        holder.mUsername.setBackgroundColor(YELLOW);
                        break;
                    case PURPLE:
                        holder.mUsername.setBackgroundColor(PURPLE);
                        break;
                    case BLACK:
                        holder.mUsername.setBackgroundColor(BLACK);
                        break;
                    case WHITE:
                        holder.mUsername.setBackgroundColor(WHITE);
                        break;
                    case GREEN:
                        holder.mUsername.setBackgroundColor(GREEN);
                        break;
                    case ORANGE:
                        holder.mUsername.setBackgroundColor(ORANGE);
                        break;
                    case BLUE:
                        holder.mUsername.setBackgroundColor(BLUE);
                        break;
                    case RED:
                        holder.mUsername.setBackgroundColor(RED);
                        break;
                    default:
                        holder.mUsername.setBackgroundColor(android.graphics.Color.LTGRAY);
                        break;
                }

            return convertView;
        }
    }

    private final int YELLOW = android.graphics.Color.YELLOW;
    private final int PURPLE = android.graphics.Color.MAGENTA;
    private final int BLACK = android.graphics.Color.BLACK;
    private final int WHITE = android.graphics.Color.WHITE;
    private final int GREEN = android.graphics.Color.GREEN;
    private final int ORANGE = android.graphics.Color.rgb(239, 163, 33); // Yes, this is Orange
    private final int RED = android.graphics.Color.RED;
    private final int BLUE = android.graphics.Color.BLUE;
}
