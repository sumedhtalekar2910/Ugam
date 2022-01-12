package com.ugams.core.models.impl;
import com.ugams.core.models.UserNames;

import com.ugams.core.utils.ResolverUtils;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.*;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = UserNames.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class UserNamesImpl implements UserNames {

    final Logger log = LoggerFactory.getLogger(UserNamesImpl.class);


    @Inject
    QueryBuilder queryBuilder;

    @OSGiService
    ResourceResolverFactory resourceResolverFactory;

    String user = " ";


    @Override
    public String getUserNames() throws RepositoryException {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("p.hits", "selective");
        userMap.put("p.limit", "-1");
        userMap.put("property", "jcr:primaryType");
        userMap.put("property.value", "rep:User");
        userMap.put("path", "/home/users");
        userMap.put("type", "rep:User");
        userMap.put("p.properties", "rep:principalName");
        try(ResourceResolver serviceResourceResolver = ResolverUtils.newResolver(resourceResolverFactory);){
            Session session = serviceResourceResolver.adaptTo(Session.class);
            Query userQuery = queryBuilder.createQuery(PredicateGroup.create(userMap), session);
            SearchResult result = userQuery.getResult();
            List<Hit> hits = result.getHits();
            for (Hit hit : hits) {
                user = user.concat("\r\n").concat(hit.getProperties().get("rep:principalName", String.class));
            }
        } catch (RepositoryException | LoginException e) {
            log.info(e.getMessage());
        }
        return user;
    }
}

