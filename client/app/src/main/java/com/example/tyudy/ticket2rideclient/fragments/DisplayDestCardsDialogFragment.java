package com.example.tyudy.ticket2rideclient.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.common.cards.DestinationCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trevor on 3/3/2017.
 */

public class DisplayDestCardsDialogFragment extends DialogFragment {
    private List<DestinationCard> destinationCards;
    private Activity gameBoardActivity;

    public DisplayDestCardsDialogFragment(){
        destinationCards = new ArrayList<>();
    }

    public void setGameBoardActivity(Activity a) { gameBoardActivity = a; }

    /**
     * Set the list of destination cards for the adapter to use
     * @param list
     */
    public void setCardList(List<DestinationCard> list) {
        destinationCards = list;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(gameBoardActivity);
        View v = gameBoardActivity.getLayoutInflater().inflate(R.layout.display_destination_cards, null);

        TextView source = (TextView) v.findViewById(R.id.destCard_source);
        TextView dest = (TextView) v.findViewById(R.id.destCard_dest);
        TextView points = (TextView) v.findViewById(R.id.destCard_points);

        String src = "From: " + destinationCards.get(0).getDestination().getSource();
        String dst = "To: " + destinationCards.get(0).getDestination().getDest();
        String pts = "Points: " + destinationCards.get(0).getPointValue();

        source.setText(src);
        dest.setText(dst);
        points.setText(pts);

        builder.setTitle(R.string.dest_cards).
                setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel(); // Close the dialog
                    }
                }).
                setCancelable(false).
                setIcon(R.drawable.ic_dest).
                setView(v);

        return builder.create();
    }
}
