package com.Ugams.core.schedulers;

import com.Ugams.core.config.SchedulerConfig;
import com.Ugams.core.services.CurrentDate;
import com.Ugams.core.utils.ResolverUtils;
import org.apache.sling.api.resource.*;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.Date;
import java.util.Optional;

@Component(immediate = true, service = Runnable.class)
@Designate(ocd = SchedulerConfig.class)
public class CustomScheduler implements Runnable{

    private static final Logger LOG = LoggerFactory.getLogger(CustomScheduler.class);

    private int schedulerId;

    @Reference
    private Scheduler scheduler;


    @Reference
    CurrentDate currentDate;

    @Activate
    protected void activate(SchedulerConfig config) {
        schedulerId = config.schedulerName().hashCode();
        addScheduler(config);
    }

    @Deactivate
    protected void deactivate(SchedulerConfig config) {
        removeScheduler();
    }

    private void removeScheduler() {
        scheduler.unschedule(String.valueOf(schedulerId));
    }

    private void addScheduler(SchedulerConfig config) {
        ScheduleOptions scheduleOptions = scheduler.EXPR(config.cronExpression());
        scheduleOptions.name(String.valueOf(schedulerId));
        scheduleOptions.canRunConcurrently(true);
        scheduler.schedule(this, scheduleOptions);
        LOG.info("\n ---------Scheduler added----------");

    }
    @Override
    public void run() {
       LOG.info("\n ====> RUN METHOD  ");
        currentDate.UpdateDate();
    }
}
