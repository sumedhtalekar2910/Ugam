package com.Ugams.core.models.impl;

import com.Ugams.core.models.UserNames;
import com.Ugams.core.services.UsernameService;
import com.Ugams.core.utils.ResolverUtils;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.osgi.resource.Resource;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.*;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = UserNames.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class UserNamesImpl implements UserNames {

    final Logger LOG = LoggerFactory.getLogger(UserNamesImpl.class);

    @Inject
    ResourceResolver resolver;
    @Inject
    QueryBuilder queryBuilder;

    @OSGiService
    ResourceResolverFactory resourceResolverFactory;

    String user = " ";


    @PostConstruct
    protected void init(){
        LOG.info("\n printing Model logs");
    }

    @Override
    public String getUserNames() throws RepositoryException {

        LOG.info("\n Inside Getusername of service ");
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
            ResourceResolver serviceResourceResolver = ResolverUtils.newResolver(resourceResolverFactory);
            Session session = serviceResourceResolver.adaptTo(Session.class);
            LOG.info("\n Result "+session.getUserID());
            Query userQuery = queryBuilder.createQuery(PredicateGroup.create(userMap), session);
            SearchResult result = userQuery.getResult();
            List<Hit> Hits = result.getHits();
            for (Hit hit : Hits) {
                user = user + "\r\n" + hit.getProperties().get("rep:principalName", String.class);
            }
        } catch (RepositoryException e) {
            LOG.info("Service User ERROR",e.getMessage());
        }
        return user;
    }
}
