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
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.common.Color;
import com.example.tyudy.ticket2rideclient.common.Destination;
import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.common.cards.DestinationCard;
import com.example.tyudy.ticket2rideclient.common.cards.TrainCard;
import com.example.tyudy.ticket2rideclient.common.cities.City;
import com.example.tyudy.ticket2rideclient.common.cities.Path;
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
    
    private GameBoardPresenter mGameBoardPresenter;
    private User mThisUser;
    private ArrayList<User> mUsers;
    private ArrayList<String> mPlayerNames;
    private ArrayList<TrainCard> mCards;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mGameBoardPresenter = PresenterHolder.SINGLETON.getGameBoardPresenter();
        mGameBoardPresenter.setGameBoardFragment(this);
        ClientModel.SINGLETON.addObserver(this);

        City.initAllCities();

        // TEST PURPOSES ----------------------
//        Toast.makeText(getContext(), "Cities Initialized", Toast.LENGTH_SHORT).show();
//
//        Toast.makeText(getContext(), ClientModel.SINGLETON.getCityByName("Atlanta").toString(),
//                Toast.LENGTH_LONG).show();
//        Toast.makeText(getContext(), ClientModel.SINGLETON.getCityByName("Duluth").toString(),
//                Toast.LENGTH_LONG).show();
        // ------------------------------------

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
        golden.setColor(Color.PURPLE);
        golden.addPoints(10000000);

        Destination d = new Destination(ClientModel.SINGLETON.getCityByName("Atlanta"),
                                        ClientModel.SINGLETON.getCityByName("St. Louis"));
        DestinationCard card = new DestinationCard(d, 5);
        milo.addDestinationCard(card);
        milo.addDestinationCard(new DestinationCard(new
                Destination(ClientModel.SINGLETON.getCityByName("Montreal"),
                ClientModel.SINGLETON.getCityByName("Los Angeles")), 16));

        milo.addDestinationCard(new DestinationCard(new
                Destination(ClientModel.SINGLETON.getCityByName("Washington DC"),
                ClientModel.SINGLETON.getCityByName("Nashville")), 8));

        Path p1 = ClientModel.SINGLETON.getCityByName("Atlanta").getPathTo(
                ClientModel.SINGLETON.getCityByName("Nashville")
        );
        Path p2 = ClientModel.SINGLETON.getCityByName("Nashville").getPathTo(
                ClientModel.SINGLETON.getCityByName("St. Louis")
        );
        Path p3 = ClientModel.SINGLETON.getCityByName("Nashville").getPathTo(
                ClientModel.SINGLETON.getCityByName("Pittsburgh")
        );
        Path p4 = ClientModel.SINGLETON.getCityByName("Pittsburgh").getPathTo(
                ClientModel.SINGLETON.getCityByName("Washington DC")
        );
        milo.claimPath(p1);
        milo.claimPath(p2);
        milo.claimPath(p3);
        milo.claimPath(p4);

        // ---------------------------------------------------

        mUsers = new ArrayList<>();
        mUsers.add(pug);
        mUsers.add(cat);
        mUsers.add(milo);
        mUsers.add(golden);

        mThisUser = milo;   // set current player (FOR TESTING)
        ClientModel.SINGLETON.setCurrentUser(mThisUser);
    }

    public User getCurrentUser() { return mThisUser; }

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
//        mMyInfo.setAdapter(new CardsAdapter(this.getContext(),
//                R.layout.points_fragment, mCards));

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
            //holder.mUsername.setBackgroundColor(user.getColorEnum());
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
