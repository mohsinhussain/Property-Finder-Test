package com.webmedia7.mohsinhussain.propertyfindertest.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.webmedia7.mohsinhussain.propertyfindertest.AppController;
import com.webmedia7.mohsinhussain.propertyfindertest.Modals.Property;
import com.webmedia7.mohsinhussain.propertyfindertest.R;

import java.util.List;

/**
 * Created by mohsinhussain on 6/16/15.
 */
public class PropertyListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Property> feedItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public PropertyListAdapter(Activity activity, List<Property> feedItems) {
        this.activity = activity;
        this.feedItems = feedItems;
    }

    @Override
    public int getCount() {
        return feedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return feedItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row_property, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        TextView priceTextView = (TextView) convertView.findViewById(R.id.priceTextView);
        TextView imageCountTextView = (TextView) convertView.findViewById(R.id.imageCountTextView);
        TextView currencyTextView = (TextView) convertView
                .findViewById(R.id.currencyTextView);
        TextView titletextView = (TextView) convertView
                .findViewById(R.id.titleTextView);
        TextView addressTextView = (TextView) convertView.findViewById(R.id.addressTextView);
        NetworkImageView imageView = (NetworkImageView) convertView
                .findViewById(R.id.imageView);

        Property item = feedItems.get(position);

        priceTextView.setText(item.getPrice()+"/year");
        titletextView.setText(item.getTitle());
        addressTextView.setText(item.getLocation());
        currencyTextView.setText(item.getCurrency());
        imageCountTextView.setText(item.getImageCount()+"");

        imageView.setImageUrl(item.getThumbnailURL(), imageLoader);

        return convertView;
    }

}
