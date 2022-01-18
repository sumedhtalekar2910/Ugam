package com.ugams.core.models.impl;

import com.ugams.core.models.FetchUser;
import com.ugams.core.utils.Network;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Iterator;

@Model(adaptables = Resource.class,
        adapters = FetchUser.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FetchUserImpl implements FetchUser {

    final Logger log = LoggerFactory.getLogger(FetchUserImpl.class);

    @Inject
    String id;

    String fname;
    String lname;
    String email;
    String avatar;


    @Override
    public String getUrl(){
        return "https://reqres.in/api/users/"+id;
    }


    @Override
    public String getMessage() throws IOException, JSONException {

        String response = Network.readJson(getUrl());

        JSONObject jsonObject =  new JSONObject(response);
        Iterator x = jsonObject.keys();
        JSONArray jsonArray = new JSONArray();

        while (x.hasNext()){
            String key = (String) x.next();
            jsonArray.put(jsonObject.get(key));
        }
        email = jsonArray.getJSONObject(0).getString("email");
        fname=jsonArray.getJSONObject(0).getString("first_name");
        lname=jsonArray.getJSONObject(0).getString("last_name");
        avatar=jsonArray.getJSONObject(0).getString("avatar");

        return response;
    }


    @Override
    public String getFname() {
        return fname;
    }

    @Override
    public String getLname() {
        return lname;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getAvatar() {
       String imgPath = avatar.replaceAll("https://reqres.in/img/faces/","/content/dam/ugams/");
        return imgPath;
    }

}
