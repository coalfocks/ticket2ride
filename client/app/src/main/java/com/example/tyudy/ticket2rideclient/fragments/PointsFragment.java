package com.example.tyudy.ticket2rideclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.common.Player;
import com.example.tyudy.ticket2rideclient.common.User;

/**
 * Created by Trevor on 2/26/2017.
 */

public class PointsFragment extends Fragment {

    private TextView player_points;
    private Player player = null;         // We should probably have a Player class somewhere?

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.points_fragment, container, false);

        player_points = (TextView)v.findViewById(R.id.player_points);

        if (player == null)
        {
            Toast.makeText(this.getContext(), "You must set the appropriate player with setPlayer()", Toast.LENGTH_SHORT);
            player_points.setText("Invalid!");
        }
        else
        {
            // Should have a player class that holds each person's points
            //player_points.setText(player.getPoints());
        }

        return v;
    }

    /**
     * This method must be called before viewing the fragment.
     * @param player The player associated with the points showing
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
}
