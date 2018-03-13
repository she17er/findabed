package com.she17er.seanm.findabed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Adapter that displays list of all shelters for the Dashboard recycler view
 */

public class ShelterAdapter extends RecyclerView.Adapter<ShelterAdapter.ViewHolder> {

    //Listener variable, interface, and method for parent (Dashboard)
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        //All variables to be rendered in a row
        public TextView shelterName;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(final View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            shelterName = (TextView) itemView.findViewById(R.id.shelter_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }
    }

    // Store a member variable for the shelters
    private List<Shelter> mShelters;
    // Store the context for easy access
    private Context mContext;

    // Pass in the shelter array into the constructor
    public ShelterAdapter(Context context, List<Shelter> shelters) {
        mShelters = shelters;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ShelterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View shelterView = inflater.inflate(R.layout.item_shelter, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(shelterView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ShelterAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Shelter shelter = mShelters.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.shelterName;
        textView.setText(shelter.getName());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mShelters.size();
    }
}
