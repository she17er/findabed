package com.she17er.seanm.findabed;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.she17er.seanm.findabed.R.id;
import com.she17er.seanm.findabed.R.layout;
import com.she17er.seanm.findabed.ShelterAdapter.ViewHolder;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

/**
 * Adapter that displays list of all shelters for the Dashboard recycler view
 */

public class ShelterAdapter extends Adapter<ViewHolder> {

    //SharedPreferences to store saved shelters
//    SharedPreferences sharedPreferences;

    //Listener variable, interface, and method for parent (Dashboard)
    private ShelterAdapter.OnItemClickListener listener;

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
    public void setOnItemClickListener(final ShelterAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        @Override
        public String toString() {
            return "ViewHolder{" +
                    "shelterName=" + this.shelterName +
                    '}';
        }

        //All variables to be rendered in a row
        final TextView shelterName;
        final Button favoritesButton;
        final Button removeFavoritesButton;

        @Override
        public boolean equals(final Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof ViewHolder)) return false;
            final ViewHolder that = (ViewHolder) obj;
            return Objects.equals(shelterName, that.shelterName);
        }

        @Override
        public int hashCode() {

            return Objects.hash(shelterName);
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

            //Initializes all buttons and text per line of a shelter view
            this.shelterName = itemView.findViewById(id.shelter_name);
            this.favoritesButton = itemView.findViewById(id.favorites_button);
            this.removeFavoritesButton = itemView.findViewById(id.removeFavorites_button);


            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(final View view) {
                    if (listener != null) {
                        final int position = ViewHolder.this.getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            ShelterAdapter.this.listener.onItemClick(itemView, position);
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
    public ShelterAdapter(final Context context, final List<Shelter> shelters) {
        super();
        this.mShelters = shelters;
        this.mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return this.mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final Context context = parent.getContext();
        final LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        final View shelterView = inflater.inflate(layout.item_shelter, parent, false);

        // Return a new holder instance
        final ViewHolder viewHolder = new ViewHolder(shelterView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // Get the data model based on position
        final Shelter shelter = this.mShelters.get(position);

        // Set item views based on your views and data model
        final TextView textView = holder.shelterName;
        textView.setText(shelter.getName());

        //Button for removing from favorites
        Button unstar = holder.removeFavoritesButton;
        unstar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("unstar", "works");
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences
                        (mContext);
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences
                        (mContext).edit();
                Set<String> savedShelterNames = new HashSet<>();
                if (preferences != null && preferences.getStringSet("Saved", null) != null) {
                    savedShelterNames = preferences.getStringSet("Saved", null);
                    String name = holder.shelterName.getText().toString();
                    if (savedShelterNames.contains(name)) {
                        savedShelterNames.remove(name);
                        editor.putStringSet("Saved", savedShelterNames);
                        editor.commit();

                        //Toast to confirm action
                        Toast.makeText(getContext(), "Shelter removed!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //Button for adding to favorites
        Button star = holder.favoritesButton;
        star.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("star", "works");
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences
                        (mContext);
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences
                        (mContext).edit();
                Set<String> savedShelterNames = new HashSet<>();
                if (preferences != null && preferences.getStringSet("Saved", null) != null) {
                    savedShelterNames = preferences.getStringSet("Saved", null);
                }
                String name = holder.shelterName.getText().toString();
                savedShelterNames.add(name);
                editor.putStringSet("Saved", savedShelterNames);
                editor.commit();

                //Toast to confirm action
                Toast.makeText(getContext(), "Shelter added!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return this.mShelters.size();
    }

    @Override
    public String toString() {
        return "ShelterAdapter{" +
                "listener=" + this.listener +
                ", mShelters=" + this.mShelters +
                ", mContext=" + this.mContext +
                '}';
    }
}
