
package com.ibm.mobileappbuilder.storesreview20160225105920.ds;
import android.graphics.Bitmap;
import ibmmobileappbuilder.ds.restds.GeoPoint;
import java.util.Date;
import android.net.Uri;

import ibmmobileappbuilder.mvp.model.IdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class StoresDSItem implements Parcelable, IdentifiableBean {

    @SerializedName("nameOfThePlace") public String nameOfThePlace;
    @SerializedName("rating") public Long rating;
    @SerializedName("picture") public String picture;
    @SerializedName("location") public GeoPoint location;
    @SerializedName("reviewDate") public Date reviewDate;
    @SerializedName("address") public String address;
    @SerializedName("sightingDetailsWebsites") public String sightingDetailsWebsites;
    @SerializedName("id") public String id;
    @SerializedName("pictureUri") public transient Uri pictureUri;

    @Override
    public String getIdentifiableId() {
      return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameOfThePlace);
        dest.writeValue(rating);
        dest.writeString(picture);
        dest.writeDoubleArray(location != null  && location.coordinates.length != 0 ? location.coordinates : null);
        dest.writeValue(reviewDate != null ? reviewDate.getTime() : null);
        dest.writeString(address);
        dest.writeString(sightingDetailsWebsites);
        dest.writeString(id);
    }

    public static final Creator<StoresDSItem> CREATOR = new Creator<StoresDSItem>() {
        @Override
        public StoresDSItem createFromParcel(Parcel in) {
            StoresDSItem item = new StoresDSItem();

            item.nameOfThePlace = in.readString();
            item.rating = (Long) in.readValue(null);
            item.picture = in.readString();
            double[] location_coords = in.createDoubleArray();
            if (location_coords != null)
                item.location = new GeoPoint(location_coords);
            Long reviewDateAux = (Long) in.readValue(null);
            item.reviewDate = reviewDateAux != null ? new Date(reviewDateAux) : null;
            item.address = in.readString();
            item.sightingDetailsWebsites = in.readString();
            item.id = in.readString();
            return item;
        }

        @Override
        public StoresDSItem[] newArray(int size) {
            return new StoresDSItem[size];
        }
    };

}


