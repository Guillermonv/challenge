package com.santander.challenge.cache;

import com.santander.challenge.model.response.MeetUpResponse;
import com.santander.challenge.service.MeetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author guillermovarelli
 */
@Transactional
@Component
public class WeatherCache {

    private static final int FIXED_RATE = 6000;
    private static final int INITIAL_DELAY = 6000;

    @Autowired
    private MeetService meetService;

    @Scheduled(initialDelay = INITIAL_DELAY, fixedRate = FIXED_RATE)
    protected void refreshScheduled() throws Exception {
        refresh();
    }

    @Cacheable("findAll")
    public List<MeetUpResponse> getAll() throws Exception {
        return meetService.findAllMeetUp();

    }

    @CachePut(value = "findAll")
    public List<MeetUpResponse> refresh() throws Exception {
        return getAll();
    }
}

