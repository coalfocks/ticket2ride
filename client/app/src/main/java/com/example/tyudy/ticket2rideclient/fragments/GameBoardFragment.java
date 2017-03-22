package com.example.tyudy.ticket2rideclient.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.common.ColorENUM;
import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.common.cards.TrainCard;
import com.example.tyudy.ticket2rideclient.common.cities.City;
import com.example.tyudy.ticket2rideclient.common.cities.Path;
import com.example.tyudy.ticket2rideclient.interfaces.iObserver;
import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.presenters.GameBoardPresenter;
import com.example.tyudy.ticket2rideclient.presenters.PresenterHolder;
import com.example.tyudy.ticket2rideclient.views.MapView;
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
    private ImageButton mDecksButton;
    private FrameLayout mMapHolderFL;
    private MapView mMapView;

    private SlidingUpPanelLayout mChat;
    
    int testCounter = 0;
    private User mThisUser;
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

        // set current player
        mUsers = new ArrayList<>(ClientModel.SINGLETON.getCurrentTTRGame().getUsers());
        mCards = ClientModel.SINGLETON.getCurrentUser().getTrainCards();

    }

    public User getCurrentUser() { return mThisUser; }

    @Override
    public void onResume()
    {
        super.onResume();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.gameplay_fragment, container, false);

        // Tester listener to print coordinates when they are clicked on for city location finding
//        v.setOnTouchListener(new View.OnTouchListener() {
//            public boolean onTouch(View v, MotionEvent event) {
//                WindowManager mWindowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
//                Display display = mWindowManager.getDefaultDisplay();
//                Point size = new Point();
//                display.getSize(size);
//                int maxX = size.x;
//                int maxY = size.y;
//
//                // Display display =  getWindowManager().getDefaultDisplay();
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    int x = (int) event.getX();
//                    int y = (int) event.getY();
//                    Toast.makeText(getContext(), "x: " + x + " y: " + y +
//                            "Max Height: " + maxY + "Max Width: " + maxX, Toast.LENGTH_SHORT).show();
//                }
//                return true;
//            }
//        });

        mMapHolderFL = (FrameLayout) v.findViewById(R.id.content_frame);
        mMapView = new MapView(getContext());
        mMapHolderFL.addView(mMapView);
        mDrawerLayout = (DrawerLayout) v.findViewById(R.id.gameplay_layout);
        mPlayerScores = (ListView) v.findViewById(R.id.left_drawer);
        mMyInfo = (ListView) v.findViewById(R.id.right_drawer);
        mDestCardsButton = (ImageButton) v.findViewById(R.id.dest_cards_button);
        mChat = (SlidingUpPanelLayout) v.findViewById(R.id.bottom_sheet);
        mDecksButton = (ImageButton) v.findViewById(R.id.decks_display_button);
        mDecksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                User anOwner = ClientModel.SINGLETON.getCurrentUser();
//                Path tysTestPath = ClientModel.SINGLETON.getPathByName("Atlanta_to_Miami");
//                Path colesPathThatSucks = ClientModel.SINGLETON.getPathByName("Boston_to_New_York");
//                tysTestPath.setOwner(anOwner);
//                colesPathThatSucks.setOwner(anOwner);
//
//                mMapView.redrawModelPaths(ClientModel.SINGLETON.getPaths());

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
        mPlayerScores.invalidate();
        mUsers = new ArrayList<>(ClientModel.SINGLETON.getCurrentTTRGame().getUsers());
        mPlayerScores.setAdapter(new PlayerAdapter(this.getContext(), R.layout.points_fragment, mUsers));
        mMyInfo.invalidate();
        mCards = ClientModel.SINGLETON.getCurrentUser().getTrainCards();
        mMyInfo.setAdapter(new CardsAdapter(this.getContext(),
                R.layout.points_fragment, mCards));

        // Draw route to screen
        mMapView.redrawModelPaths(ClientModel.SINGLETON.getPaths());

    }

    private class PlayerAdapter extends ArrayAdapter<User> {

        private Context mContext;
        private ArrayList<User> users;
        private int mCurrentTurn;

        public PlayerAdapter(Context context, int resourceId, ArrayList<User> items) {
            super(context, resourceId, items);
            this.mContext = context;
            this.users = items;
            this.mCurrentTurn = ClientModel.SINGLETON.getCurrentTTRGame().getWhoTurn();
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
            if (user.getPlayerID() == mCurrentTurn) {
                holder.mUsername.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 45);
            }
            

            ColorENUM color = user.getColor();
            
            if(color == null){
                holder.mUsername.setBackgroundColor(android.graphics.Color.LTGRAY);
            }
            else{
            switch(color) {
                case YELLOW:
                    holder.mUsername.setBackgroundColor(YELLOW);
                    break;
                case PURPLE:
                    holder.mUsername.setBackgroundColor(PURPLE);
                    break;
                case BLACK:
                    holder.mUsername.setBackgroundColor(BLACK);
                    holder.mUsername.setTextColor(COLORLESS);
                    holder.mPoints.setTextColor(COLORLESS);
                    holder.mDest.setTextColor(COLORLESS);
                    holder.mTrains.setTextColor(COLORLESS);
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
            String trains = "Train: " + String.valueOf(user.getTrainCards().size());
            holder.mTrains.setText(trains);
            String dest = "Dest: " + String.valueOf(user.getDestCards().size());
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
                holder.mUsername.setText(String.valueOf(myCard.getNum() + 1));
                switch( myCard.getColor()) {

                    case YELLOW:
                        holder.mUsername.setBackgroundColor(YELLOW);
                        break;
                    case PURPLE:
                        holder.mUsername.setBackgroundColor(PURPLE);
                        break;
                    case BLACK:
                        holder.mUsername.setBackgroundColor(BLACK);
                        holder.mUsername.setTextColor(COLORLESS);
                        holder.mPoints.setTextColor(COLORLESS);
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
                    case WILD:
                        holder.mUsername.setBackgroundColor(WILD);
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
    private final int COLORLESS = android.graphics.Color.LTGRAY;
    private final int WILD = android.graphics.Color.CYAN;
}
