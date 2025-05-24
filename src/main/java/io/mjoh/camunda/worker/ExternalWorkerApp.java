package io.mjoh.camunda.worker;

import io.mjoh.camunda.handler.ExternalTaskHandler;
import io.mjoh.camunda.configuration.ExternalTaskProperties;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExternalWorkerApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalWorkerApp.class);

    private final ExternalTaskClient externalTaskClient;
    private final ExternalTaskProperties externalTaskProperties;

    public ExternalWorkerApp(ExternalTaskClient externalTaskClient, ExternalTaskProperties externalTaskProperties) {
        this.externalTaskClient = externalTaskClient;
        this.externalTaskProperties = externalTaskProperties;
    }

    public void handleTask(ExternalTask externalTask, ExternalTaskService externalTaskService) {

        externalTaskClient.subscribe(externalTaskProperties.subscribeTopic())
                .lockDuration(externalTaskProperties.lockDuration())
                .handler(new ExternalTaskHandler())
                .open();
    }

}
