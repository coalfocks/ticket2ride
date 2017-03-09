package com.example.tyudy.ticket2rideclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.common.User;

/**
 * Created by ZacHeaton
 */

public class CardsFragment extends Fragment {

    private TextView player_points;
    private User user = null;
    private boolean canDo = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (canDo) {
            View v = inflater.inflate(R.layout.cards_fragment, container, false);
//needs to go
            player_points = (TextView) v.findViewById(R.id.player_points);
            player_points.setText(user.getPoints());

            return v;
        }
        else
        {
            Toast.makeText(this.getContext(), "You must set the appropriate player with setUser()", Toast.LENGTH_SHORT);
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    /**
     * This method must be called before viewing the fragment.
     * Calling this method will set canDo to true.
     * @param player The player associated with the points showing (not null)
     */
    public void setUser(User player) {
        if (player != null) {
            this.user = player;
            canDo = true;
        }
    }
}
