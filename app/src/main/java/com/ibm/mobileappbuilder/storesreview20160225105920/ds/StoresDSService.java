
package com.ibm.mobileappbuilder.storesreview20160225105920.ds;
import java.net.URL;
import com.ibm.mobileappbuilder.storesreview20160225105920.R;
import ibmmobileappbuilder.ds.RestService;
import ibmmobileappbuilder.util.StringUtils;

/**
 * "StoresDSService" REST Service implementation
 */
public class StoresDSService extends RestService<StoresDSServiceRest>{

    public static StoresDSService getInstance(){
          return new StoresDSService();
    }

    private StoresDSService() {
        super(StoresDSServiceRest.class);

    }

    @Override
    public String getServerUrl() {
        return "https://ibm-pods.buildup.io";
    }

    @Override
    protected String getApiKey() {
        return "0LEtGP9A";
    }

    @Override
    public URL getImageUrl(String path){
        return StringUtils.parseUrl("https://ibm-pods.buildup.io/app/57ef5df59d17e00300d4d718",
                path,
                "apikey=0LEtGP9A");
    }

}

