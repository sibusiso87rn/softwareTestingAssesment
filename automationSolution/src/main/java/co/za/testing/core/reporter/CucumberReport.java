package co.za.testing.core.reporter;


import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class CucumberReport {

        private static final Logger logger = LoggerFactory.getLogger(CucumberReport.class);

        public void createReport(){

            logger.info("******************Building Report******************");
            File reportOutputDirectory = new File("target");
            List<String> jsonFiles = new ArrayList<>();

            jsonFiles.add("target/cucumber-report.json");

            String projectName = "Shopping App";

            Configuration configuration = new Configuration(reportOutputDirectory, projectName);
            String buildNumber = System.getenv("BUILD_NUMBER") != null ? System.getenv("BUILD_NUMBER") : Integer.toString(ThreadLocalRandom.current().nextInt(1, 100));
            configuration.setBuildNumber(buildNumber);

            try{
                configuration.addClassifications("Application Name", projectName);
                configuration.addClassifications("Test Environment", "SIT");
                configuration.addClassifications("Machine IP",   InetAddress.getLocalHost().getHostAddress());
                configuration.addClassifications("Machine Name", InetAddress.getLocalHost().getHostName());
            }catch(UnknownHostException e){
                logger.error("Failed to get localhost address {}", e.getMessage());
            }
            configuration.addClassifications("Username", System.getProperty("user.name"));
            configuration.addClassifications("Platform Version", System.getProperty("os.name"));
            ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
            try {
                reportBuilder.generateReports();
            }catch (Exception e){
                logger.error("Failed to generate report {}",e.getMessage());
            }
        }
}
