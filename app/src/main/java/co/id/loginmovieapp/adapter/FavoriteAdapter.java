package co.id.loginmovieapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.loginmovieapp.R;
import co.id.loginmovieapp.data.FavoriteData;
import co.id.loginmovieapp.helper.Constant;
import co.id.loginmovieapp.ui.dashboard.home.HomePresenter;
import co.id.loginmovieapp.ui.favorite.FavoritePresenter;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private List<FavoriteData> mFavoriteData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public FavoriteAdapter(List<FavoriteData> resultData, Context context){
        mFavoriteData = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FavoriteData favoriteData = mFavoriteData.get(position);

        holder.favoriteData = favoriteData;
        Picasso.with(mContext)
                .load(Constant.URL_IMAGE + favoriteData.getPosterPath())
                .into(holder.mImageView);

        holder.mTxtMovieTitle.setText(favoriteData.getOriginalTitle());
        holder.mTxtReleaseDate.setText(favoriteData.getReleaseDate());
        holder.mTxtMovieDesc.setText(favoriteData.getOverview());
    }

    @Override
    public int getItemCount() {
        return mFavoriteData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_thumbnail)
        ImageView mImageView;
        @BindView(R.id.txt_movie_title)
        TextView mTxtMovieTitle;
        @BindView(R.id.txt_release_date)
        TextView mTxtReleaseDate;
        @BindView(R.id.txt_movie_desc)
        TextView mTxtMovieDesc;

        FavoriteData favoriteData;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @OnClick(R.id.card_item)
        public void openDetail() {
            new FavoritePresenter().getView().openDetail(favoriteData);
        }
    }
}
