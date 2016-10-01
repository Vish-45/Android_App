
package com.ibm.mobileappbuilder.storesreview20160225105920.ui;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ibm.mobileappbuilder.storesreview20160225105920.R;
import ibmmobileappbuilder.behaviors.ShareBehavior;
import ibmmobileappbuilder.ds.restds.AppNowDatasource;
import ibmmobileappbuilder.util.image.ImageLoader;
import ibmmobileappbuilder.util.image.PicassoImageLoader;
import ibmmobileappbuilder.util.StringUtils;
import java.net.URL;
import static ibmmobileappbuilder.util.image.ImageLoaderRequest.Builder.imageLoaderRequest;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.storesreview20160225105920.ds.StoresDSItem;
import com.ibm.mobileappbuilder.storesreview20160225105920.ds.StoresDS;

public class GhostSightingsDetailFragment extends ibmmobileappbuilder.ui.DetailFragment<StoresDSItem> implements ShareBehavior.ShareListener  {

    private Datasource<StoresDSItem> datasource;
    public static GhostSightingsDetailFragment newInstance(Bundle args){
        GhostSightingsDetailFragment fr = new GhostSightingsDetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public GhostSightingsDetailFragment(){
        super();
    }

    @Override
    public Datasource<StoresDSItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
       datasource = StoresDS.getInstance(new SearchOptions());
        return datasource;
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        addBehavior(new ShareBehavior(getActivity(), this));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.ghostsightingsdetail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final StoresDSItem item, View view) {
        if (item.nameOfThePlace != null){
            
            TextView view0 = (TextView) view.findViewById(R.id.view0);
            view0.setText(item.nameOfThePlace);
            
        }
        if (item.rating != null){
            
            TextView view1 = (TextView) view.findViewById(R.id.view1);
            view1.setText(item.rating.toString());
            
        }
        
        ImageView view2 = (ImageView) view.findViewById(R.id.view2);
        URL view2Media = ((AppNowDatasource) getDatasource()).getImageUrl(item.picture);
        if(view2Media != null){
          ImageLoader imageLoader = new PicassoImageLoader(view2.getContext());
          imageLoader.load(imageLoaderRequest()
                                   .withPath(view2Media.toExternalForm())
                                   .withTargetView(view2)
                                   .fit()
                                   .build()
                    );
        	
        } else {
          view2.setImageDrawable(null);
        }
        if (item.location != null){
            
            TextView view3 = (TextView) view.findViewById(R.id.view3);
            view3.setText(item.location.toString());
            
        }
        if (item.reviewDate != null){
            
            TextView view4 = (TextView) view.findViewById(R.id.view4);
            view4.setText(DateFormat.getMediumDateFormat(getActivity()).format(item.reviewDate));
            
        }
        if (item.address != null){
            
            TextView view5 = (TextView) view.findViewById(R.id.view5);
            view5.setText(item.address);
            
        }
        if (item.sightingDetailsWebsites != null){
            
            TextView view6 = (TextView) view.findViewById(R.id.view6);
            view6.setText(item.sightingDetailsWebsites);
            
        }
    }

    @Override
    protected void onShow(StoresDSItem item) {
        // set the title for this fragment
        getActivity().setTitle(item.nameOfThePlace);
    }
    @Override
    public void onShare() {
        StoresDSItem item = getItem();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, (item.nameOfThePlace != null ? item.nameOfThePlace : "" ) + "\n" +
                    (item.rating != null ? item.rating.toString() : "" ) + "\n" +
                    (item.location != null ? item.location.toString() : "" ) + "\n" +
                    (item.reviewDate != null ? DateFormat.getMediumDateFormat(getActivity()).format(item.reviewDate) : "" ) + "\n" +
                    (item.address != null ? item.address : "" ) + "\n" +
                    (item.sightingDetailsWebsites != null ? item.sightingDetailsWebsites : "" ));
        intent.putExtra(Intent.EXTRA_SUBJECT, item.nameOfThePlace);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.share)), 1);
    }
}

