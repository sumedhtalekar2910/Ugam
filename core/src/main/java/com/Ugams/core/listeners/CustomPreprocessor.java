package com.ugams.core.listeners;

import com.ugams.core.services.CurrentDate;
import com.ugams.core.utils.ResolverUtils;
import com.day.cq.commons.date.DateUtil;
import com.day.cq.commons.date.InvalidDateException;
import com.day.cq.replication.*;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.Calendar;

@Component(immediate = true)
public class CustomPreprocessor implements Preprocessor {

    @Reference
    CurrentDate currentDate;

     @Reference
        private ResourceResolverFactory resourceResolverFactory;
        @Override
        public void preprocess(final ReplicationAction replicationAction,
        final ReplicationOptions replicationOptions) throws ReplicationException {

            if (replicationAction == null || !ReplicationActionType.ACTIVATE.equals(replicationAction.getType())) {
                return;
            }
            String path = replicationAction.getPath();
            if(path.equals("/content/ugams/us/en/demo")){
                try (ResourceResolver serviceResourceResolver= ResolverUtils.newResolver(resourceResolverFactory)){
                    Session session = serviceResourceResolver.adaptTo(Session.class);
                    Resource resource = serviceResourceResolver.getResource("/content/ugams/us/en/demo/jcr:content/root/container/date");
                    Node node = resource.adaptTo(Node.class);
                    if(node.getProperty("Time") != DateUtil.parseISO8601(DateUtil.getISO8601Date(Calendar.getInstance())))
                    {
                        currentDate.updateDate("/content/ugams/us/en/demo/jcr:content/root/container/date");
                    }
                    session.save();
                    session.logout();
                } catch (LoginException | RepositoryException | InvalidDateException e) {
                    e.printStackTrace();
                }
            }
        }
    }
