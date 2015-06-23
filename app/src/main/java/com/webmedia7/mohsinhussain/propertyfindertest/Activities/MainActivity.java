package com.webmedia7.mohsinhussain.propertyfindertest.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.webmedia7.mohsinhussain.propertyfindertest.Adapters.PropertyListAdapter;
import com.webmedia7.mohsinhussain.propertyfindertest.AppController;
import com.webmedia7.mohsinhussain.propertyfindertest.Modals.Property;
import com.webmedia7.mohsinhussain.propertyfindertest.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ListView listView;
    private PropertyListAdapter listAdapter;
    private List<Property> feedItems;
    ProgressDialog ringProgressDialog;
    int totalItemCount = 0;
    int pageCount = 0;
    String feedOrder = "";
    private String URL_FEED = "https://www.propertyfinder.ae/mobileapi?page=0";
    Button paButton, pdButton, baButton, bdButton;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.propertyListView);

        paButton = (Button) findViewById(R.id.paButton);
        pdButton = (Button) findViewById(R.id.pdButton);
        baButton = (Button) findViewById(R.id.baButton);
        bdButton = (Button) findViewById(R.id.bdButton);


        feedItems = new ArrayList<Property>();

        listAdapter = new PropertyListAdapter(this, feedItems);
        listView.setAdapter(listAdapter);

        // Attach the listener to the AdapterView onCreate
        listView.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                findProperties(feedOrder, page);
            }
        });

        // We first check for cached request
        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(URL_FEED);
        if (entry != null) {
            // fetch the data from cache
            try {
                String data = new String(entry.data, "UTF-8");
                try {
                    parseJsonFeed(new JSONObject(data));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } else {
            //First time you run the app there is no order and values are coming in default order.
            findProperties(feedOrder,pageCount);
        }


        paButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageCount = 0;
                feedOrder = "pa";
                listView.setSelection(0);
                findProperties(feedOrder, pageCount);
            }
        });

        pdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageCount = 0;
                feedOrder = "pd";
                listView.setSelection(0);
                findProperties(feedOrder, pageCount);
            }
        });

        baButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageCount = 0;
                feedOrder = "ba";
                listView.setSelection(0);
                findProperties(feedOrder, pageCount);
            }
        });

        bdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageCount = 0;
                feedOrder = "bd";
                listView.setSelection(0);
                findProperties(feedOrder, pageCount);
            }
        });

    }

    public void findProperties(String order, int page){
        ringProgressDialog = ProgressDialog.show(this, "Please wait ...", "Finding Properties ...", true);
        ringProgressDialog.setCancelable(false);
        if(order.equalsIgnoreCase("")){
            URL_FEED = "https://www.propertyfinder.ae/mobileapi?page="+page;
        }
        else{
            URL_FEED = "https://www.propertyfinder.ae/mobileapi?page="+page+"&order="+order;
        }

        if(page==0){
            feedItems.clear();
        }

        // making fresh volley request and getting json
        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, URL_FEED, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                VolleyLog.d(TAG, "Response: " + response.toString());
                if (response != null) {
                    parseJsonFeed(response);
                    ringProgressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(MainActivity.this, "Error Finding Properties!", Toast.LENGTH_SHORT).show();
                ringProgressDialog.dismiss();
            }
        });

        // Adding request to volley request queue
        AppController.getInstance().addToRequestQueue(jsonReq);
    }

    /**
     * Parsing json reponse and passing the data to feed view list adapter
     * */
    private void parseJsonFeed(JSONObject response) {
        try {
            JSONArray feedArray = response.getJSONArray("res");
            totalItemCount = response.getInt("total");

            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);

                Property item = new Property();
                item.setId(feedObj.getInt("id"));
                item.setCatId(feedObj.getInt("category_id"));
                item.setTitle(feedObj.getString("title"));
                item.setType(feedObj.getString("type"));
                item.setThumbnailURL(feedObj.getString("thumbnail"));
                item.setThumbnailBigUrl(feedObj.getString("thumbnail_big"));
                item.setImageCount(feedObj.getInt("image_count"));
                item.setPrice(feedObj.getString("price"));
                item.setCurrency(feedObj.getString("currency"));
                item.setFeature(feedObj.getString("featured"));
                item.setLocation(feedObj.getString("location"));
                item.setArea(feedObj.getString("area"));
                item.setPoa(feedObj.getString("poa"));
                item.setBathrooms(feedObj.getInt("bathrooms"));
                item.setBedrooms(feedObj.getInt("bedrooms"));
                item.setVisited(feedObj.getBoolean("visited"));
                item.setLat(feedObj.getLong("lat"));
                item.setLang(feedObj.getLong("long"));

                feedItems.add(item);
            }

            // notify data changes to list adapter
            listAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }




    }



    public abstract class EndlessScrollListener implements AbsListView.OnScrollListener {
        // The minimum amount of items to have below your current scroll position
        // before loading more.
        private int visibleThreshold = 4;
        // The current offset index of data you have loaded
        private int currentPage = pageCount;
        // The total number of items in the dataset after the last load
        private int previousTotalItemCount = 0;
        // True if we are still waiting for the last set of data to load.
        private boolean loading = true;
        // Sets the starting page index
        private int startingPageIndex = 0;

        public EndlessScrollListener() {
        }

        public EndlessScrollListener(int visibleThreshold) {
            this.visibleThreshold = visibleThreshold;
        }

        public EndlessScrollListener(int visibleThreshold, int startPage) {
            this.visibleThreshold = visibleThreshold;
            this.startingPageIndex = startPage;
            this.currentPage = startPage;
        }
        @Override
        public void onScroll(AbsListView view,int firstVisibleItem,int visibleItemCount,int totalItemCount)
        {
            // If the total item count is zero and the previous isn't, assume the
            // list is invalidated and should be reset back to initial state
            if (totalItemCount < previousTotalItemCount) {
                this.currentPage = this.startingPageIndex;
                this.previousTotalItemCount = totalItemCount;
                if (totalItemCount == 0) { this.loading = true; }
            }
            // If it’s still loading, I will check to see if the dataset count has
            // changed, if so we conclude it has finished loading and update the current page
            // number and total item count.
            if (loading && (totalItemCount > previousTotalItemCount)) {
                loading = false;
                previousTotalItemCount = totalItemCount;
                currentPage++;
            }

            // If it isn’t currently loading, we check to see if we have breached
            // the visibleThreshold and need to reload more data.
            // If we do need to reload some more data, we execute onLoadMore to fetch the data.
            if (!loading && (totalItemCount - visibleItemCount)<=(firstVisibleItem + visibleThreshold)) {
                onLoadMore(currentPage + 1, totalItemCount);
                loading = true;
            }
        }

        // Defines the process for actually loading more data based on page
        public abstract void onLoadMore(int page, int totalItemsCount);

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            // Don't take any action on changed
        }
    }



}
