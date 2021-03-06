
package com.ibm.mobileappbuilder.storesreview20160225105920.ui;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.view.View;
import com.ibm.mobileappbuilder.storesreview20160225105920.ds.StoresDSItem;
import com.ibm.mobileappbuilder.storesreview20160225105920.ds.StoresDSService;
import com.ibm.mobileappbuilder.storesreview20160225105920.presenters.ReviewsFormPresenter;
import com.ibm.mobileappbuilder.storesreview20160225105920.R;
import ibmmobileappbuilder.ds.restds.GeoPoint;
import ibmmobileappbuilder.ui.FormFragment;
import ibmmobileappbuilder.util.StringUtils;
import ibmmobileappbuilder.views.DatePicker;
import ibmmobileappbuilder.views.GeoPicker;
import ibmmobileappbuilder.views.ImagePicker;
import ibmmobileappbuilder.views.TextWatcherAdapter;
import java.io.IOException;
import java.util.Date;
import java.io.File;

import static android.net.Uri.fromFile;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.storesreview20160225105920.ds.StoresDSItem;
import com.ibm.mobileappbuilder.storesreview20160225105920.ds.StoresDS;

public class StoresDSItemFormFragment extends FormFragment<StoresDSItem> {

    private CrudDatasource<StoresDSItem> datasource;

    public static StoresDSItemFormFragment newInstance(Bundle args){
        StoresDSItemFormFragment fr = new StoresDSItemFormFragment();
        fr.setArguments(args);

        return fr;
    }

    public StoresDSItemFormFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        // the presenter for this view
        setPresenter(new ReviewsFormPresenter(
                (CrudDatasource) getDatasource(),
                this));

            }

    @Override
    protected StoresDSItem newItem() {
        return new StoresDSItem();
    }

    private StoresDSService getRestService(){
        return StoresDSService.getInstance();
    }

    @Override
    protected int getLayout() {
        return R.layout.reviews_form;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final StoresDSItem item, View view) {
        
        bindString(R.id.storesds_nameoftheplace, item.nameOfThePlace, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.nameOfThePlace = s.toString();
            }
        });
        
        
        bindLong(R.id.storesds_rating, item.rating, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.rating = StringUtils.parseLong(s.toString());
            }
        });
        
        
        bindImage(R.id.storesds_picture,
            item.picture != null ?
                getRestService().getImageUrl(item.picture) : null,
            0,
            new ImagePicker.Callback(){
                @Override
                public void imageRemoved(){
                    item.picture = null;
                    item.pictureUri = null;
                    ((ImagePicker) getView().findViewById(R.id.storesds_picture)).clear();
                }
            }
        );
        
        
        bindLocation(R.id.storesds_location, item.location,
            new GeoPicker.PointChangedListener() {
                @Override
                public void onPointChanged(GeoPoint point) {
                    item.location = point;
                }
            }
        );
        
        
        bindDatePicker(R.id.storesds_reviewdate, item.reviewDate, new DatePicker.DateSelectedListener() {
            @Override
            public void onSelected(Date selected) {
                item.reviewDate = selected;
            }
        });
        
        
        bindString(R.id.storesds_address, item.address, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.address = s.toString();
            }
        });
        
        
        bindString(R.id.storesds_sightingdetailswebsites, item.sightingDetailsWebsites, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.sightingDetailsWebsites = s.toString();
            }
        });
        
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            ImagePicker picker = null;
            Uri imageUri = null;
            StoresDSItem item = getItem();

            if((requestCode & ImagePicker.GALLERY_REQUEST_CODE) == ImagePicker.GALLERY_REQUEST_CODE) {
              imageUri = data.getData();
              switch (requestCode - ImagePicker.GALLERY_REQUEST_CODE) {
                        
                        case 0:   // picture field
                            item.pictureUri = imageUri;
                            item.picture = "cid:picture";
                            picker = (ImagePicker) getView().findViewById(R.id.storesds_picture);
                            break;
                        
                default:
                  return;
              }

              picker.setImageUri(imageUri);
            } else if((requestCode & ImagePicker.CAPTURE_REQUEST_CODE) == ImagePicker.CAPTURE_REQUEST_CODE) {
				      switch (requestCode - ImagePicker.CAPTURE_REQUEST_CODE) {
                        
                        case 0:   // picture field
                            picker = (ImagePicker) getView().findViewById(R.id.storesds_picture);
                            imageUri = fromFile(picker.getImageFile());
                        		item.pictureUri = imageUri;
                            item.picture = "cid:picture";
                            break;
                        
                default:
                  return;
              }
              picker.setImageUri(imageUri);
            }
        }
    }
}

