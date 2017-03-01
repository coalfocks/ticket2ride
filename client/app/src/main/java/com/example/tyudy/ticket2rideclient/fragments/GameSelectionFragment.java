package com.example.tyudy.ticket2rideclient.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.ClientCommunicator;
import com.example.tyudy.ticket2rideclient.interfaces.iObserver;
import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.activities.GameLobbyActivity;
import com.example.tyudy.ticket2rideclient.common.TTRGame;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.presenters.GameSelectionPresenter;
import com.example.tyudy.ticket2rideclient.presenters.PresenterHolder;

import java.util.ArrayList;

/**
 * Created by tyudy on 2/13/17.
 */

public class GameSelectionFragment extends Fragment implements iObserver {

    private RecyclerView mGameRecyclerView;
    private GameAdapter mGameAdapter;

    private Button mCreateGameButton;
    private GameSelectionPresenter mGameSelectionPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        mGameSelectionPresenter = PresenterHolder.SINGLETON.getGameSelectionPresenter();
        mGameSelectionPresenter.setGameSelectionFragment(this);
        ClientModel.SINGLETON.addObserver(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.game_selection_fragment, container, false);

        mGameRecyclerView = (RecyclerView) v.findViewById(R.id.game_recycler_view);
        mGameRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        observe();

        mCreateGameButton = (Button) v.findViewById(R.id.create_game_button);
        mCreateGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGameSelectionPresenter.createGameClicked();
            }
        });

        return v;
    }

    @Override
    public void observe() {
        // Update the screen by reading from the model and presenting data to the view
        mGameAdapter = new GameAdapter(ClientModel.SINGLETON.getTTRGameList());
        mGameRecyclerView.setAdapter(mGameAdapter);

        if (ClientModel.SINGLETON.getCurrentTTRGame() != null && ClientModel.SINGLETON.getCurrentTTRGame().getInProgress() == 1) {
            ((GameLobbyActivity) getContext()).onStartGame();      // Start new activity
        }

    }

    public class GameHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mGameNumberTitle;
        private TextView mGameOwnerText;
        private TextView mNumberOfPlayers;
        private TTRGame mTTRGame;


        public GameHolder(View gameView){
            super(gameView);
            gameView.setOnClickListener(this);

            mGameNumberTitle = (TextView) gameView.findViewById(R.id.game_number_title);
            mGameOwnerText = (TextView) gameView.findViewById(R.id.game_owner);
            mNumberOfPlayers = (TextView) gameView.findViewById(R.id.game_list_players);

        }

        public void bindGame(TTRGame TTRGame, int gameNumber){
            mTTRGame = TTRGame;

            mGameNumberTitle.setText("TTRGame #" + gameNumber);
            mGameOwnerText.setText("Owner: " + TTRGame.getOwnerUsername());
            mNumberOfPlayers.setText("Number of players: " + TTRGame.getNumPlayers());
        }

        @Override
        public void onClick(View v){

            boolean gameWasNotSet = (ClientModel.SINGLETON.getCurrentTTRGame() == null);

            TTRGame g = ClientModel.SINGLETON.getCurrentTTRGame();

            // The current game is not null and it has also been changed
            if(gameWasNotSet || mTTRGame.getGameID() == ClientModel.SINGLETON.getCurrentTTRGame().getGameID()) {

                // Only Join if user is not in a game
                if(gameWasNotSet) {
                    MethodsFacade.SINGLETON.joinGame(mTTRGame);
                }

                Intent i = new Intent(getContext(), GameLobbyActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(getContext(), "You can't join more than one game silly", Toast.LENGTH_LONG).show();
            }

        }


    }

    public class GameAdapter extends RecyclerView.Adapter<GameHolder> {

        private ArrayList<TTRGame> mTTRGameList;

        public GameAdapter(ArrayList<TTRGame> TTRGameList){
            mTTRGameList = TTRGameList;
        }

        @Override
        public GameHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.game_list_item, parent, false);
            return new GameHolder(view);
        }

        @Override
        public void onBindViewHolder(GameHolder holder, int position) {
            TTRGame TTRGame = mTTRGameList.get(position);
            // Set fields of view according to this TTRGame
            holder.bindGame(TTRGame, position);
        }

        @Override
        public int getItemCount() {
            return mTTRGameList.size();
        }

    }

}
