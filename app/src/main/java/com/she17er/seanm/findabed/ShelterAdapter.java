package com.she17er.seanm.findabed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

/**
 * Adapter that displays list of all shelters for the Dashboard recycler view
 */

public class ShelterAdapter extends RecyclerView.Adapter<ShelterAdapter.ViewHolder> {

    @Override
    public String toString() {
        return "ShelterAdapter{" +
                "listener=" + listener +
                ", mShelters=" + mShelters +
                ", mContext=" + mContext +
                '}';
    }

    //Listener variable, interface, and method for parent (Dashboard)
    private OnItemClickListener listener;

    /**
     * Gets a specific shelter from the Dashboard
     */
    public interface OnItemClickListener {

        /**
         * Sets up onClickListener for adapter
         * @param itemView The View passed in to be opened up
         * @param position The shelter's position in the list of all shelters
         */
        void onItemClick(View itemView, int position);
    }

    /**
     * Sets up the shelter actionListeners
     * @param listener The actionlistener for this shelterAdapter
     */
    public final void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        @Override
        public String toString() {
            return "ViewHolder{" +
                    "shelterName=" + shelterName +
                    '}';
        }

        //All variables to be rendered in a row
        final TextView shelterName;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ShelterAdapter.ViewHolder)) return false;
            ShelterAdapter.ViewHolder that = (ShelterAdapter.ViewHolder) o;
            return Objects.equals(this.shelterName, that.shelterName);
        }

        @Override
        public int hashCode() {

            return Objects.hash(this.shelterName);
        }

        /**
         * We also create a constructor that accepts the entire item row
         * and does the view lookups to find each subview
         * @param itemView The view for a given shelter in the adapter
         */
        ViewHolder(final View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            shelterName = itemView.findViewById(R.id.shelter_name);


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
    private final List<Shelter> mShelters;
    // Store the context for easy access
    private final Context mContext;

    /**
     * Pass in the shelter array into the constructor
     * @param context The context for a shelter selected
     * @param shelters The list of all shelters
     */
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
    public final ShelterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View shelterView = inflater.inflate(R.layout.item_shelter, parent, false);

        // Return a new holder instance
        ShelterAdapter.ViewHolder viewHolder = new ShelterAdapter.ViewHolder(shelterView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public final void onBindViewHolder(ShelterAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Shelter shelter = mShelters.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.shelterName;

        textView.setText(shelter.getName());
    }

    // Returns the total count of items in the list
    @Override
    public final int getItemCount() {
        return mShelters.size();
    }
}
