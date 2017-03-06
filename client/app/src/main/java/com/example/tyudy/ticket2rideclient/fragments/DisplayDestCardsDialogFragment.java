package com.example.tyudy.ticket2rideclient.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.common.cards.DestinationCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trevor on 3/3/2017.
 */

public class DisplayDestCardsDialogFragment extends DialogFragment {
    private MyListAdapter listAdapter;

    public DisplayDestCardsDialogFragment(){
        listAdapter = new MyListAdapter();
    }

    /**
     * Set the list of destination cards for the adapter to use
     * @param list
     */
    public void setCardList(List<DestinationCard> list) {
        listAdapter.setCardList(list);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.dest_cards).
                setAdapter(listAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // For now, do nothing.
                        // Perhaps later we may decide to show more detail about
                        //  the destination cards
                    }
                }).
                setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel(); // Close the dialog
                    }
                }).
                setCancelable(false).
                setIcon(R.drawable.ic_dest);

        return builder.create();
    }


    /**
     * Custom ListAdapter class to view DestinationCards
     */
    private class MyListAdapter implements ListAdapter{
        private List<DestinationCard> cardList;

        public MyListAdapter() {
            cardList = new ArrayList<DestinationCard>();
        }

        public void setCardList(List<DestinationCard> list) {
            cardList = list;
        }

        /**
         * Items will not be clickable, therefore they're not enabled.
         * @return false
         */
        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        /**
         * No item is enabled--will always return false
         * @param i Necessary param
         * @return false
         */
        @Override
        public boolean isEnabled(int i) {
            return false;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public int getCount() {
            return cardList.size();
        }

        @Override
        public Object getItem(int i) {
            if (i < cardList.size())
                return cardList.get(i);

            return null;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return view;
        }

        @Override
        public int getItemViewType(int i) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    }
}
