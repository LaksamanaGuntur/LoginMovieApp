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
import co.id.loginmovieapp.data.ResultData;
import co.id.loginmovieapp.helper.Constant;
import co.id.loginmovieapp.ui.dashboard.home.HomePresenter;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ResultData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ListAdapter(List<ResultData> resultData, Context context){
        mResultData = resultData;
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
        ResultData resultData = mResultData.get(position);

        holder.resultData = resultData;
        Picasso.with(mContext)
                .load(Constant.URL_IMAGE + resultData.getPosterPath())
                .into(holder.mImageView);

        holder.mTxtMovieTitle.setText(resultData.getOriginalTitle());
        holder.mTxtReleaseDate.setText(resultData.getReleaseDate());
        holder.mTxtMovieDesc.setText(resultData.getOverview());
    }

    @Override
    public int getItemCount() {
        return mResultData.size();
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

        ResultData resultData;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @OnClick(R.id.card_item)
        public void openDetail() {
            new HomePresenter().getView().openDetail(resultData);
        }
    }
}
