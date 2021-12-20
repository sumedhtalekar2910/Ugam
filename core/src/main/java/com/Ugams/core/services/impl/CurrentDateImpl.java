package com.Ugams.core.services.impl;


import com.Ugams.core.services.CurrentDate;
import com.Ugams.core.utils.ResolverUtils;
import com.day.cq.commons.date.DateUtil;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.jcr.Node;
import javax.jcr.Session;
import java.util.Calendar;
import java.util.TimeZone;

@Component(service = CurrentDate.class,immediate = true)
public class CurrentDateImpl implements  CurrentDate{

    private static final Logger LOG = LoggerFactory.getLogger(CurrentDateImpl.class);


    @Reference
    ResourceResolverFactory resourceResolverFactory;


    @Override
    public void UpdateDate() {
        try{
            ResourceResolver serviceResourceResolver = ResolverUtils.newResolver(resourceResolverFactory);
            Session session = serviceResourceResolver.adaptTo(Session.class);
            Resource resource = serviceResourceResolver.getResource("/content/ugams/us/en/demo/jcr:content/root/container/date");
            Node node = resource.adaptTo(Node.class);
            node.setProperty("Time" , DateUtil.parseISO8601(DateUtil.getISO8601Date(Calendar.getInstance())));
            session.save();
            session.logout();
        } catch (Exception e) {
            LOG.info(e.getMessage());
        }
    }
}
