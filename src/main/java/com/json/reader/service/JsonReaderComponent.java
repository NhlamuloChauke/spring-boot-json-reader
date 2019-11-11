package com.json.reader.service;

import com.json.reader.config.AppProperties;
import com.json.reader.model.PersonConfig;
import com.json.reader.utils.JsonReaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class JsonReaderComponent {

    private static final Logger LOG = LoggerFactory.getLogger(JsonReaderComponent.class);

    @Autowired
    private AppProperties appProperties;

    @PostConstruct
    private void init() throws Exception {
        LOG.info("Application Config Directory: {} ", appProperties.getConfigDir());

        JsonReaderUtil jsonReaderUtil = new JsonReaderUtil();

        //read the config from the directory
        List <PersonConfig> personConfigs = jsonReaderUtil.processConfigsFromProperties(appProperties.getConfigDir());

        //retrieving the config
        if(personConfigs.size() != 0) {
            for (PersonConfig personConfig : personConfigs) {
                LOG.info("Person Name: {}", personConfig.getName());
                LOG.info("Person Surname: {}", personConfig.getSurname());
                LOG.info("Person EmailAddress: {}", personConfig.getEmailAddress());
                LOG.info("Person CompanyName: {}", personConfig.getCompanyName());
                LOG.info("Person ContactNumber: {}", personConfig.getContactNumber());
            }
        }
        else {
            throw new Exception("Person Config doesn't exit");
        }

    }
}
