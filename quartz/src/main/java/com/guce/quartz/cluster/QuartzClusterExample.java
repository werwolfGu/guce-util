package com.guce.quartz.cluster;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by Administrator on 2016/7/2.
 */
public class QuartzClusterExample {

    private Logger _log = LoggerFactory.getLogger(QuartzClusterExample.class);

    public void run(boolean inClearJobs,boolean inScheduleJobs) throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        if (inClearJobs) {
            _log.warn("***** Deleting existing jobs/triggers *****");
            sched.clear();
        }

        _log.info("------- Initialization Complete -----------");

        if(inScheduleJobs){

            _log.info("------- Scheduling Jobs ------------------");
            String schedId = sched.getSchedulerInstanceId();

            int count = 1;
            JobDetail job = newJob(SimpleRecoveryJob.class).withIdentity("job_" + count, schedId) // put triggers in group
                    // named after the cluster
                    // node instance just to
                    // distinguish (in logging)
                    // what was scheduled from
                    // where
                    .requestRecovery() // ask scheduler to re-execute this job if it was in progress when the scheduler went
                    // down...
                    .build();

            SimpleTrigger trigger = newTrigger().withIdentity("triger_" + count, schedId)
                    .startAt(futureDate(1, DateBuilder.IntervalUnit.SECOND))
                    .withSchedule(simpleSchedule().withRepeatCount(20).withIntervalInSeconds(5)).build();

            _log.info(job.getKey() + " will run at: " + trigger.getNextFireTime() + " and repeat: "
                    + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000 + " seconds");
            sched.scheduleJob(job, trigger);

            sched.start();
        }
    }
    public static void main(String[] args) {
        boolean clearJobs = false;
        boolean scheduleJobs = true;
        for(String arg : args){
            if (arg.equalsIgnoreCase("clearJobs")) {
                clearJobs = true;
            } else if (arg.equalsIgnoreCase("dontScheduleJobs")) {
                scheduleJobs = false;
            }
        }
        String s = System.getProperty("org.quartz.properties");
        File file = new File(s);
        if(file.exists()){
            System.out.println(file.getAbsolutePath());
        }
        System.out.println(System.getProperty("user.dir"));
        QuartzClusterExample example = new QuartzClusterExample();
        System.out.println("---------" + s +  "-------");
        try {
            example.run(clearJobs,scheduleJobs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
