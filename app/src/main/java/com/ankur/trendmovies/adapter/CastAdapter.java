package com.ankur.trendmovies.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ankur.trendmovies.ApplicationClass;
import com.ankur.trendmovies.R;
import com.ankur.trendmovies.activities.MovieDetailsActivity;
import com.ankur.trendmovies.models.Cast;
import com.ankur.trendmovies.models.Movie;
import com.ankur.trendmovies.utils.Constants;
import com.ankur.trendmovies.utils.Utilities;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {

    private final String TAG = CastAdapter.class.getName();
    private List<Cast> castList;
    private Activity activity;

    public CastAdapter(Activity activity, List<Cast> castList) {
        this.castList = castList;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_card, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Cast cast = castList.get(position);

        holder.nameTV.setText(cast.getName());
        holder.roleTV.setText(cast.getCharacter());

        if (Utilities.has(cast.getProfile_path())) {
            holder.posterIV.setVisibility(View.VISIBLE);
            holder.posterDummyIV.setVisibility(View.GONE);
            Utilities.loadImageInside(ApplicationClass.getInstance(), Constants.BASE_URL_IMAGE_200 + cast.getProfile_path(), holder.posterIV);
        } else {
            holder.posterIV.setVisibility(View.GONE);
            holder.posterDummyIV.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return castList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV;
        TextView roleTV;
        ImageView posterIV;
        ImageView posterDummyIV;

        public ViewHolder(final View v) {
            super(v);

            nameTV = v.findViewById(R.id.cast_name);
            roleTV = v.findViewById(R.id.cast_role);
            posterIV = v.findViewById(R.id.cast_iv);
            posterDummyIV = v.findViewById(R.id.cast__dummy_iv);

        }
    }
}
