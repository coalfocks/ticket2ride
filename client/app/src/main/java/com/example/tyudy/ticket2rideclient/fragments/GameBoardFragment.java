package com.example.tyudy.ticket2rideclient.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.common.ColorENUM;
import com.example.tyudy.ticket2rideclient.common.Destination;
import com.example.tyudy.ticket2rideclient.common.TTRGame;
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
import com.example.tyudy.ticket2rideclient.views.MapView;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.TreeSet;


/**
 * Created by colefox on 2/24/17.
 */

public class GameBoardFragment extends Fragment implements iObserver
{
    private DrawerLayout mDrawerLayout;
    private ListView mPlayerScores;
    private ListView mMyInfo;

    private ImageButton mDestCardsButton;
    private Button mTestButton;
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

//        City.initAllCities();

        // TEST PURPOSES ----------------------
//        Toast.makeText(getContext(), "Cities Initialized", Toast.LENGTH_SHORT).show();
//
//        Toast.makeText(getContext(), ClientModel.SINGLETON.getCityByName("Atlanta").toString(),
//                Toast.LENGTH_LONG).show();
//        Toast.makeText(getContext(), ClientModel.SINGLETON.getCityByName("Duluth").toString(),
//                Toast.LENGTH_LONG).show();
        // ------------------------------------
        
//        User pug = new User();
//        pug.setUsername("pug");
//        pug.setColorENUM(ColorENUM.RED);
//        pug.addPoints(1000);
//
//        User cat = new User();
//        cat.setUsername("cat");
//        cat.setColorENUM(ColorENUM.YELLOW);
//
//        User milo = new User();
//        milo.setUsername("milo");
//        milo.setColorENUM(ColorENUM.BLUE);
//        milo.addPoints(100);
//
//        User golden = new User();
//        golden.setUsername("daisy");
//        golden.setColorENUM(ColorENUM.MAGENTA);
//        golden.addPoints(10000000);
//        mPlayers.add(pug);
//        mPlayers.add(cat);
//        mPlayers.add(milo);
//        mPlayers.add(golden);

//        mCards = ClientModel.SINGLETON.getCurrentPlayer().returnTrainCards();
//        mCards = new ArrayList<TrainCard>();
//        for(int i = 0; i < 10; i++){
//            TrainCard myCard = new TrainCard();
//            myCard.setColorENUM(YELLOW);
//            myCard.setNum(i);
//            mCards.add(myCard);
//        }

//         Destination d = new Destination(ClientModel.SINGLETON.getCityByName("Atlanta"),
//                                         ClientModel.SINGLETON.getCityByName("St. Louis"));
//         DestinationCard card = new DestinationCard(d, 5);
//         milo.addDestinationCard(card);
//         milo.addDestinationCard(new DestinationCard(new
//                 Destination(ClientModel.SINGLETON.getCityByName("Montreal"),
//                 ClientModel.SINGLETON.getCityByName("Los Angeles")), 16));

//         milo.addDestinationCard(new DestinationCard(new
//                 Destination(ClientModel.SINGLETON.getCityByName("Washington DC"),
//                 ClientModel.SINGLETON.getCityByName("Nashville")), 8));

//         Path p1 = ClientModel.SINGLETON.getCityByName("Atlanta").getPathTo(
//                 ClientModel.SINGLETON.getCityByName("Nashville")
//         );
//         Path p2 = ClientModel.SINGLETON.getCityByName("Nashville").getPathTo(
//                 ClientModel.SINGLETON.getCityByName("St. Louis")
//         );
//         Path p3 = ClientModel.SINGLETON.getCityByName("Nashville").getPathTo(
//                 ClientModel.SINGLETON.getCityByName("Pittsburgh")
//         );
//         Path p4 = ClientModel.SINGLETON.getCityByName("Pittsburgh").getPathTo(
//                 ClientModel.SINGLETON.getCityByName("Washington DC")
//         );
//         milo.claimPath(p1);
//         milo.claimPath(p2);
//         milo.claimPath(p3);
//         milo.claimPath(p4);

        // ---------------------------------------------------



        // set current player
        mUsers = new ArrayList<>(ClientModel.SINGLETON.getCurrentTTRGame().getUsers());

        mCards = ClientModel.SINGLETON.getCurrentUser().getTrainCards();
        //mCards = new ArrayList<TrainCard>();
//        for(int i = 0; i < 10; i++){
//            TrainCard myCard = new TrainCard();
//            myCard.setColorENUM(ColorENUM.YELLOW);
//            myCard.setNum(i);
//            mCards.add(myCard);
//        }
//         mThisUser = milo;   // set current player (FOR TESTING)
//         ClientModel.SINGLETON.setCurrentUser(mThisUser);

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

//        mUnitedStatesImage = (ImageView) v.findViewById(R.id.UnitedStatesImage);
        mMapHolderFL = (FrameLayout) v.findViewById(R.id.content_frame);
        mMapView = new MapView(getContext());
        mMapHolderFL.addView(mMapView);
        mDrawerLayout = (DrawerLayout) v.findViewById(R.id.gameplay_layout);
        mPlayerScores = (ListView) v.findViewById(R.id.left_drawer);
        mMyInfo = (ListView) v.findViewById(R.id.right_drawer);
        mDestCardsButton = (ImageButton) v.findViewById(R.id.dest_cards_button);
        mChat = (SlidingUpPanelLayout) v.findViewById(R.id.bottom_sheet);
        mTestButton = (Button) v.findViewById(R.id.test_button);
        mTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Test 1
//                if(testCounter == 0) {

//                    User anOwner = ClientModel.SINGLETON.getCurrentUser();
//                    Path tysTestPath = ClientModel.SINGLETON.getPathByName("Atlanta_to_Miami");
//                    Path colesPathThatSucks = ClientModel.SINGLETON.getPathByName("Boston_to_New_York");
//                    tysTestPath.setOwner(anOwner);
//                    colesPathThatSucks.setOwner(anOwner);
//                    MethodsFacade.SINGLETON.claimPath(colesPathThatSucks);
//                    MethodsFacade.SINGLETON.addTrainCard();
//                    mMapView.redrawModelPaths(ClientModel.SINGLETON.getPaths());

                testButtonClicked();


                    //Path path = ClientModel.SINGLETON.getAllPaths().get(0);
                    //MethodsFacade.SINGLETON.claimPath(path);


                    //mMapView.reDrawWithLineBetween(path.getCities().get(0), path.getCities().get(1));
                    //testCounter = 1;
//                } else {
//                    City c = ClientModel.SINGLETON.getCityByName("Atlanta");

//                    City d = ClientModel.SINGLETON.getCityByName("Nashville");
//
//                    mMapView.reDrawWithLineBetween(c, d);
//                    testCounter = 0;
//                }
            }
        });


//        ViewTreeObserver usaViewTreeObserver = mUnitedStatesImage.getViewTreeObserver();
//        usaViewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                mUnitedStatesImage.getViewTreeObserver().removeOnPreDrawListener(this);
//                int height = mUnitedStatesImage.getMeasuredHeight();
//                int width = mUnitedStatesImage.getMeasuredWidth();
//                initializeDrawingHelper(height, width);
//                return true;
//            }
//        });




        // Listener to print coordinates when the image is clicked on
//        mMapView.setOnTouchListener(new View.OnTouchListener() {
//            public boolean onTouch(View v, MotionEvent event) {
//                WindowManager mWindowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
//                Display display = mWindowManager.getDefaultDisplay();
//                Point size = new Point();
//                display.getSize(size);
//                float maxX = size.x;
//                float maxY = size.y;
//
//                // Display display =  getWindowManager().getDefaultDisplay();
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    float x = event.getX();
//                    float y = event.getY();
//                    Toast.makeText(getContext(), "xScale: " + x/maxX + " yScale: " + y/maxY, Toast.LENGTH_SHORT).show();
//
//                }
//                return true;
//            }
//        });



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
        mPlayerScores.setAdapter(new PlayerAdapter(getContext(), R.layout.points_fragment, mUsers));
        mMyInfo.invalidate();
        mCards = ClientModel.SINGLETON.getCurrentUser().getTrainCards();
        mMyInfo.setAdapter(new CardsAdapter(getContext(),
                R.layout.points_fragment, mCards));

        // Draw routes to screen
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

    public void testButtonClicked(){
        // Change each users number of trains cars
        User currentUser = ClientModel.SINGLETON.getCurrentUser();

        switch(testCounter){
            case 0:
                runTestCaseForTrainCards();
                testCounter++;
                break;
            case 1:
                runTestCaseForPaths();
                testCounter++;
            case 2:
                testCounter = 0;
                break;
            default:
                System.err.print("Error: ");
                System.out.print("The test case button logic is broken. See the testButtonClicked() function in GameBoardFragment");
                break;
        }



    }

    // TEST HELPER FUNCTIONS -------------------------------------------------------------------------------------------------------


    public void runTestCaseForTrainCards(){
        User currentUser = ClientModel.SINGLETON.getCurrentUser();

        Log.i("testCaseForTrainCards", "All Players should have 2 cards");
        Log.i("testCaseForTrainCards", "All Players should have 2 cards");
        Log.i("testCaseForTrainCards", "Your destination cards should be Portland to Nashville (17), Toronto to Miami (10), and Calgary to Salt Lake City (7)");

        //Set up destinations
        Destination dest1 = new Destination(ClientModel.SINGLETON.getCityByName("Portland"), ClientModel.SINGLETON.getCityByName("Nashville"));
        Destination dest2 = new Destination(ClientModel.SINGLETON.getCityByName("Toronto"), ClientModel.SINGLETON.getCityByName("Miami"));
        Destination dest3 = new Destination(ClientModel.SINGLETON.getCityByName("Calgary"), ClientModel.SINGLETON.getCityByName("Salt Lake"));
        DestinationCard destinationCard1 = new DestinationCard(dest1, 17);
        DestinationCard destinationCard2 = new DestinationCard(dest2, 10);
        DestinationCard destinationCard3 = new DestinationCard(dest3, 7);


        // Make sure player 1 has 4 train cards
        for(User u: mUsers){
            if(u.getPlayerID() == currentUser.getPlayerID()){
                //change for current user as well
                currentUser.removeAllTrainCards();
                TrainCard trainCard1 = new TrainCard(ColorENUM.BLUE);
                TrainCard trainCard2 = new TrainCard(ColorENUM.RED);
                currentUser.addTrainCard(trainCard1);
                currentUser.addTrainCard(trainCard2);

            }
            u.removeAllTrainCards();
            TrainCard trainCard1 = new TrainCard(ColorENUM.BLUE);
            TrainCard trainCard2 = new TrainCard(ColorENUM.RED);
            u.addTrainCard(trainCard1);
            u.addTrainCard(trainCard2);
        }

        currentUser.removeAllDestinationCards();
        currentUser.addDestinationCard(destinationCard1);
        currentUser.addDestinationCard(destinationCard2);
        currentUser.addDestinationCard(destinationCard3);

        ClientModel.SINGLETON.notifyObservers();
    }

    private void runTestCaseForPaths(){
        Log.i("runTestCaseForPaths", "It should be player 2's turn");
        Log.i("testCaseForTrainCards", "Your destination cards should be Los Angeles to New York City (21), Duluth to Houston (8), and New York to Atlanta (6)");
        Log.i("runTestCaseForPaths", "Player 1 should have the route Boston_NewYork (2 points) and Portland_SanFran (10 points)");
        Log.i("runTestCaseForPaths", "Player 2 should have the route ElPaso_Houston (15 points) and LA_SanFran (4 points)");
        Log.i("runTestCaseForPaths", "If there is a Player 3, they should have the route Atlanta_Miami (10 points) and Helena_SaltLakeCity (4 points)");
        Log.i("runTestCaseForPaths", "If there is a Player 4, they should have the route NewYork_WashingtonDC (2 points) and NewYork_Pittsburgh (2 points)");
        Log.i("runTestCaseForPaths", "If there is a Player 5, they should have the route Phoenix_SantaFe (4 points) and Pittsburgh_Toronto (2 points)");


        User currentUser = ClientModel.SINGLETON.getCurrentUser();

        Destination dest1 = new Destination(ClientModel.SINGLETON.getCityByName("Los Angeles"), ClientModel.SINGLETON.getCityByName("New York"));
        Destination dest2 = new Destination(ClientModel.SINGLETON.getCityByName("Duluth"), ClientModel.SINGLETON.getCityByName("Houston"));
        Destination dest3 = new Destination(ClientModel.SINGLETON.getCityByName("New York"), ClientModel.SINGLETON.getCityByName("Atlanta"));
        DestinationCard destinationCard1 = new DestinationCard(dest1, 21);
        DestinationCard destinationCard2 = new DestinationCard(dest2, 8);
        DestinationCard destinationCard3 = new DestinationCard(dest3, 6);

        // It is now player 2's turn
        ClientModel.SINGLETON.getCurrentTTRGame().setmTurnIndex(1);

        int userCounter = 0;
        Path tysTestPath = new Path();
        Path colesPathThatSucks = new Path();
        for (User u: mUsers){
            // Set the two paths according to the print statements above
            switch(userCounter){
                case 0: // Set player 1's Paths
                    tysTestPath = ClientModel.SINGLETON.getPathByName("Portland_to_SanFrancisco");
                    colesPathThatSucks = ClientModel.SINGLETON.getPathByName("Boston_to_New_York");
                    userCounter++;
                    break;
                case 1: // Set player 2's Paths
                    tysTestPath = ClientModel.SINGLETON.getPathByName("ElPaso_Houston");
                    colesPathThatSucks = ClientModel.SINGLETON.getPathByName("LosAngeles_to_SanFrancisco");
                    userCounter++;
                    break;
                case 2: // Set player 3's Paths
                    tysTestPath = ClientModel.SINGLETON.getPathByName("Atlanta_to_Miami");
                    colesPathThatSucks = ClientModel.SINGLETON.getPathByName("Helena_to_SaltLake");
                    userCounter++;
                    break;
                case 3: // Set player 4's Paths
                    tysTestPath = ClientModel.SINGLETON.getPathByName("New_York_to_Washington_DC");
                    colesPathThatSucks = ClientModel.SINGLETON.getPathByName("New_York_to_Pittsburgh");
                    userCounter++;
                    break;
                case 4: // Set player 5's Paths
                    tysTestPath = ClientModel.SINGLETON.getPathByName("Phoenix_to_SantaFe");
                    colesPathThatSucks = ClientModel.SINGLETON.getPathByName("Pittsburgh_to_Toronto");
                    userCounter++;
                    break;
                default:
                    System.err.print("Error: ");
                    System.out.print("There are more than 5 players in the game!");
                    break;
            }
            tysTestPath.setOwner(u);
            colesPathThatSucks.setOwner(u);
            u.addPoints(tysTestPath.getPoints());
            u.addPoints(colesPathThatSucks.getPoints());


            currentUser.removeAllDestinationCards();
            currentUser.addDestinationCard(destinationCard1);
            currentUser.addDestinationCard(destinationCard2);
            currentUser.addDestinationCard(destinationCard3);

            ClientModel.SINGLETON.notifyObservers();
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
