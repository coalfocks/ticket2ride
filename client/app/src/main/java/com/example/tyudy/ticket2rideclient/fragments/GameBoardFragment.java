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

import com.example.tyudy.ticket2rideclient.common.Player;
import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.interfaces.iObserver;
import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.presenters.GameBoardPresenter;
import com.example.tyudy.ticket2rideclient.presenters.PresenterHolder;

import java.util.ArrayList;

import static com.example.tyudy.ticket2rideclient.common.Color.BLUE;
import static com.example.tyudy.ticket2rideclient.common.Color.PURPLE;
import static com.example.tyudy.ticket2rideclient.common.Color.RED;
import static com.example.tyudy.ticket2rideclient.common.Color.YELLOW;


/**
 * Created by colefox on 2/24/17.
 */

public class GameBoardFragment extends Fragment implements iObserver
{
    private DrawerLayout mDrawerLayout;
    private ListView mPlayerScores;
    private ListView mMyInfo;
    private ImageButton mDestCardsButton;
    private GameBoardPresenter mGameBoardPresenter;
    private Player mThisPlayer;
    private ArrayList<User> mUsers;
    private ArrayList<Player> mPlayers;
    private ArrayList<String> mPlayerNames;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mGameBoardPresenter = PresenterHolder.SINGLETON.getGameBoardPresenter();
        mGameBoardPresenter.setGameBoardFragment(this);
        ClientModel.SINGLETON.addObserver(this);

        User pug = new User();
        pug.setUsername("pug");
        pug.setColor(RED);
        pug.addPoints(1000);

        User cat = new User();
        cat.setUsername("cat");
        cat.setColor(YELLOW);

        User milo = new User();
        milo.setUsername("milo");
        milo.setColor(BLUE);
        milo.addPoints(100);

        User golden = new User();
        golden.setUsername("daisy");
        golden.setColor(PURPLE);
        golden.addPoints(10000000);

        // Testing using the Player class in lou of User class
        Player p1 = new Player(pug, pug.getUsername(), pug.getColorEnum());
        Player p2 = new Player(cat, cat.getUsername(), cat.getColorEnum());
        Player p3 = new Player(milo, milo.getUsername(), milo.getColorEnum());
        Player p4 = new Player(golden, golden.getUsername(), golden.getColorEnum());
        // ---------------------------------------------------

        mUsers = new ArrayList<>();
        mUsers.add(pug);
        mUsers.add(cat);
        mUsers.add(milo);
        mUsers.add(golden);

        mPlayers = new ArrayList<>();
        mPlayers.add(p1);
        mPlayers.add(p2);
        mPlayers.add(p3);
        mPlayers.add(p4);

        mThisPlayer = p4;
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

//        for (int i = 0; i < mUsers.length; i++)
//        {
//            TextView listItem = (TextView) mPlayerScores.getAdapter().getView(i, null, mPlayerScores);
//            listItem.setBackgroundColor(mUsers[i].getColorEnum());
//            listItem.setText(mUsers[i].getUsername().toUpperCase());
//        }

        return v;
    }

    @Override
    public void observe()
    {

    }

    public Player getCurrentPlayer() { return mThisPlayer; }

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
}