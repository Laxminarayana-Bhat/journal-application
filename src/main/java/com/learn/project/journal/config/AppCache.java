package com.learn.project.journal.config;

import com.learn.project.journal.model.ConfigJournalApp;
import com.learn.project.journal.repository.ConfigJournalRepository;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    private ConfigJournalRepository configJournalRepository;

    private Map<String,String> cacheMap;

    @PostConstruct
    void initialize(){
        cacheMap=new HashMap<>();
        List<ConfigJournalApp> all = configJournalRepository.findAll();
        for (ConfigJournalApp entry:all){
            cacheMap.putIfAbsent(entry.getKey(), entry.getValue());
        }
    }

    @Scheduled(cron = "0 0/5 0 ? * *")
    public void clearCache(){
        initialize();
        System.out.println("Clear cache invoked");
    }
    //0/1 = every 1 minute starting at 0
    // [sec,min,hour,day of month, month, day of week(sun, mon ..)]
    //*/1 = every 1 minute (starts at 0 by default)    - both are same


    /*
Field	Allowed Values
Seconds	0-59
Minutes	0-59
Hours	0-23
Day of month	1-31, ?, L, W
Month	1-12 or JAN-DEC
Day of week	0-7 or SUN-SAT, ?, L
*/
}
