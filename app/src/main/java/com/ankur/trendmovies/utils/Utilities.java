package com.ankur.trendmovies.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author ankur
 */
public class Utilities {
    public static boolean has(String field) {
        return field != null && !field.equals("") && !field.equalsIgnoreCase("null");
    }

    public static boolean has(double field) {
        return field != 0;
    }

    public static boolean has(boolean field) {
        return field;
    }

    public static boolean has(BigDecimal field) {
        if (field == null)
            return false;
        else
            return field.compareTo(BigDecimal.ZERO) != 0;
    }

    public static boolean has(Object field) {
        return field != null;
    }

    public static boolean has(Map field) {
        return field != null && field.size() != 0;
    }

    public static boolean has(List field) {
        return field != null && field.size() > 0;
    }

    public static boolean has(JSONObject json, String field) {

        if (json == null)
            return false;

        try {

            if (json.isNull(field))
                return false;

            if (json.has(field) && has(json.getString(field)))
                return true;
            else return false;
        } catch (JSONException e) {
            return false;
        }
    }

    public static boolean has(ArrayList list) {
        return list != null && !list.isEmpty();
    }

    public static boolean has(CopyOnWriteArrayList list) {
        return list != null && !list.isEmpty();
    }

    public static boolean has(Set set) {
        return set != null && !set.isEmpty();
    }

    public static boolean has(View view, int field) {

        if (view == null)
            return false;

        return view.findViewById(field) != null;
    }

    public static boolean has(String[] array) {
        return array != null && array.length != 0;
    }

    public static boolean has(JSONArray array) {
        return array != null && array.length() != 0;
    }

    public static void setText(View view, int resource, final String text) {
        if (view != null && view.findViewById(resource) != null)
            ((TextView) view.findViewById(resource)).setText(text);
    }

    public static void loadImageInside(Context context, String url, ImageView view) {
        Log.v("PicassoImagr--", url);
        Picasso.get().load(url).into(view);
    }

    public static void loadImageInside(Context context, int drawable, ImageView view) {
        Picasso.get().load(drawable).fit().centerCrop().into(view);
    }

    public static void loadImageInside(Context context, Bitmap bitmap, ImageView view) {
        try {
            Uri uri = Uri.fromFile(File.createTempFile("temp_file_name", ".jpg", context.getCacheDir()));
            OutputStream outputStream = context.getContentResolver().openOutputStream(uri);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.close();

            Picasso.get().load(uri).fit().centerInside().into(view);
        } catch (Exception e) {
            Log.e("LoadBitmapByPicasso", e.getMessage());
        }
    }
}
