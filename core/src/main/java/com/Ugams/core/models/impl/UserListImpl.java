package com.ugams.core.models.impl;

import com.ugams.core.models.UserList;
import com.ugams.core.services.FetchApiUrl;
import com.ugams.core.utils.FetchApi;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.util.*;

@Model(adaptables = Resource.class,
        adapters = UserList.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class UserListImpl implements UserList {

    final Logger log = LoggerFactory.getLogger(UserListImpl.class);

    @Inject
    String pageNo;

    @OSGiService
    FetchApiUrl fetchApiUrl;

    @Override
    public List<Map<String, String>> getData() throws JSONException, IOException {

        String response = FetchApi.readData(getUrl());
        JSONObject jsonObject =  new JSONObject(response);
        JSONArray jsonArray1 = jsonObject.getJSONArray("data");

        List<Map<String, String>> userList = new ArrayList<>();
        for (int i=0;i<jsonArray1.length();i++){
            Map<String,String> user =new HashMap<>();
            user.put("fname",jsonArray1.getJSONObject(i).getString("first_name"));
            user.put("lname",jsonArray1.getJSONObject(i).getString("last_name"));
            user.put("email",jsonArray1.getJSONObject(i).getString("email"));
            user.put("avatar",jsonArray1.getJSONObject(i).getString("avatar"));
            userList.add(user);
        }

        return userList;
    }

    @Override
    public String getUrl() {
        String multiUrl = fetchApiUrl.getMultiUserUrl();
        return multiUrl+pageNo;
    }
}
