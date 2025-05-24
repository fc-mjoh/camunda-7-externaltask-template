package io.mjoh.camunda.handler;

import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExternalTaskHandler implements org.camunda.bpm.client.task.ExternalTaskHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(ExternalTaskHandler.class);

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        LOGGER.info("Executing external task {}", externalTask.getId());
        externalTaskService.complete(externalTask);
    }
}
