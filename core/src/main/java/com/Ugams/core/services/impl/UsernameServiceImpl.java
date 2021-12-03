package com.Ugams.core.services.impl;

import com.Ugams.core.services.UsernameService;
import com.Ugams.core.utils.ResolverUtils;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.*;

@Component(service = UsernameService.class,immediate = true)
public class UsernameServiceImpl implements UsernameService {

    final Logger LOG = LoggerFactory.getLogger(UsernameServiceImpl.class);



    @Reference
    ResourceResolverFactory resourceResolverFactory;

    @Reference
    QueryBuilder queryBuilder;

    String user = " ";

    @Activate
    public void activate(){
        LOG.info("\n ==============INSIDE ACTIVATE================");


    }

    @Override
    public String getUsernames() throws RepositoryException {
        List<String> usernames = new ArrayList<>();
        Map<String, String> userMap = new HashMap<>();
        userMap.put("p.hits", "selective");
        userMap.put("p.limit", "-1");
        userMap.put("property", "jcr:primaryType");
        userMap.put("property.value", "rep:User");
        userMap.put("path", "/home/users");
        userMap.put("type", "rep:User");
        userMap.put("p.properties", "rep:principalName");
        try{
            LOG.info("\n Inside Try..");
            ResourceResolver serviceResourceResolver = ResolverUtils.newResolver(resourceResolverFactory);
            Session session = serviceResourceResolver.adaptTo(Session.class);
            LOG.info("\n Result "+session.getUserID());
            Query userQuery = queryBuilder.createQuery(PredicateGroup.create(userMap), session);
            LOG.info("Query "+userQuery);
            SearchResult result = userQuery.getResult();
            LOG.info("Result "+result);
            List<Hit> Hits = result.getHits();
            for (Hit hit : Hits) {

                user = user + "\r\n" + hit.getProperties().get("rep:principalName", String.class);
            }
        } catch (RepositoryException |LoginException e) {
            LOG.info("Service User ERROR",e.getMessage());
        }
        return user;

    }
}
