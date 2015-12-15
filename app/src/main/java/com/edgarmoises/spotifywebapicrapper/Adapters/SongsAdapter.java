package com.edgarmoises.spotifywebapicrapper.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edgarmoises.spotifywebapicrapper.Model.Items;
import com.edgarmoises.spotifywebapicrapper.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edgarperez on 12/12/15.
 */
public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder>{

    private List<Items> items;
    private Context mContext;

    public SongsAdapter(Context context){
        items = new ArrayList<>();

        mContext = context;
    }

    public void AddInformation(List<Items> listItems){
        items = listItems;
        notifyDataSetChanged();
    }

    public void ClearInformation(){
        items.clear();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Items item = items.get(position);

        holder.mArtistName.setText(item.getArtists().get(0).getName());
        holder.mAlbumName.setText(item.getAlbum().getName());
        Picasso.with(mContext).load(item.getAlbum().getImages().get(0).getUrl()).into(holder.mAlbumImage);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mArtistName;
        public TextView mAlbumName;
        public ImageView mAlbumImage;

        public ViewHolder(View itemView) {
            super(itemView);

            mArtistName = (TextView) itemView.findViewById(R.id.artist_name);
            mAlbumName = (TextView) itemView.findViewById(R.id.album_name);
            mAlbumImage = (ImageView) itemView.findViewById(R.id.album_image);

        }
    }
}
