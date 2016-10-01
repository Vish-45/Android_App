

package com.ibm.mobileappbuilder.storesreview20160225105920.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ibm.mobileappbuilder.storesreview20160225105920.R;

import ibmmobileappbuilder.ui.BaseListingActivity;
/**
 * ReviewsActivity list activity
 */
public class ReviewsActivity extends BaseListingActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.reviewsActivity));
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return ReviewsFragment.class;
    }

}

