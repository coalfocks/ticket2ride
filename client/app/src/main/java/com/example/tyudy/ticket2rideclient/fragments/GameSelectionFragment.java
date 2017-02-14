package com.example.tyudy.ticket2rideclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tyudy.ticket2rideclient.IObserver;
import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.model.ClientModelFacade;
import com.example.tyudy.ticket2rideclient.model.Game;

/**
 * Created by tyudy on 2/13/17.
 */

public class GameSelectionFragment extends Fragment implements IObserver {

    private RecyclerView mGameRecyclerView;
    private Button mCreateGameButton;

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        ClientModelFacade.SINGLETON.addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.game_selection_fragment, container, false);

        // TODO: Make this an observable

        mGameRecyclerView = (RecyclerView) v.findViewById(R.id.game_recycler_view);
        mGameRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));




        return v;
    }

    @Override
    public void observe() {
        // Update the screen by reading from the model and presenting data to the view

    }

    // TODO: Implement this class
    private class GameHolder extends RecyclerView.ViewHolder {

        public GameHolder(View gameView){
            super(gameView);
        }
    }

    private class GameAdapter extends RecyclerView.Adapter<GameHolder> {

        @Override
        public GameHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.game_list_item, parent, false);
            return new GameHolder(view);
        }

        @Override
        public void onBindViewHolder(GameHolder holder, int position) {
            Game game = ClientModelFacade.SINGLETON.getGameAtIndex(position);
            // Set fields of view according to this game
        }

        @Override
        public int getItemCount() {
            return ClientModelFacade.SINGLETON.getGameList().size();
        }
    }

}
