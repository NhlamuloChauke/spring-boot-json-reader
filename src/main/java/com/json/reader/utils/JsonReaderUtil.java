package com.json.reader.utils;


import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.json.reader.model.PersonConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParseException;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class JsonReaderUtil {

    private static final Logger LOG = LoggerFactory.getLogger(JsonReaderUtil.class);


    public List <PersonConfig> processConfigsFromProperties(String configDir) {

        List <PersonConfig> configList = new ArrayList();
        try {
            LOG.info("Loading config directory with json files...");
            Path dir = Paths.get(configDir);
            boolean isDir = Files.isDirectory(dir);
            if (isDir) {
                LOG.info("{} config directory has loaded successfully...", dir);
                List <File> fileList = readConfigFiles(dir);

                for (File file : fileList) {
                    PersonConfig config = getPersonConfig(file);
                    configList.add(config);
                }
            } else {
                LOG.error("Failed to load {} config directory, path does not exist!", dir);
                System.exit(0);
            }
        } catch (Exception e) {
            LOG.error("Failed to process configs from properties file!");
            LOG.error(e.getMessage());
            System.exit(0);
        }
        return configList;
    }

    private PersonConfig getPersonConfig(File file) {

        String nameValue = null;
        String surnameValue = null;
        String companyNameValue = null;
        String emailAddressValue = null;
        String contactNumberValue = null;
        PersonConfig config = null;

        try {

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(file);

            JsonNode name = rootNode.path("name");
            JsonNode surname = rootNode.path("surname");
            JsonNode companyName = rootNode.path("companyName");
            JsonNode emailAddress = rootNode.path("emailAddress");
            JsonNode contactNumber = rootNode.path("contactNumber");

            nameValue = name.textValue();
            surnameValue = surname.textValue();
            companyNameValue = companyName.textValue();
            emailAddressValue = emailAddress.textValue();
            contactNumberValue = contactNumber.textValue();

            config = new PersonConfig();

            config.setName(nameValue);
            config.setSurname(surnameValue);
            config.setCompanyName(companyNameValue);
            config.setEmailAddress(emailAddressValue);
            config.setContactNumber(contactNumberValue);

            LOG.info("Person config : {}", config.toString());

        } catch (JsonParseException e) {
            LOG.error("JsonParserException occurred : {}", e.getMessage());
        } catch (JsonMappingException e) {
            LOG.error("JsonMappingException occurred : {}", e.getMessage());
        } catch (IOException e) {
            LOG.error("IOException occurred : {}", e.getMessage());
        }

        return config;
    }

    public List <File> readConfigFiles(Path dir) {

        List <File> fileList = new ArrayList();
        try (DirectoryStream <Path> stream = Files.newDirectoryStream(dir)) {
            for (Path entry : stream) {
                File child = new File(String.valueOf(entry.toFile()));
                if (child.exists() && child.isFile() && child.toString().endsWith(".json")) {
                    fileList.add(child);
                    LOG.info("Loading  {} file from {} config directory...", child, dir);
                }
            }
        } catch (IOException | DirectoryIteratorException de) {
            LOG.error("Failed to load files from {} config directory {}", dir, de.getMessage());
        }
        return fileList;
    }
}
