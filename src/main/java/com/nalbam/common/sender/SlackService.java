package com.nalbam.common.sender;

import in.ashwanthkumar.slack.webhook.Slack;
import in.ashwanthkumar.slack.webhook.SlackMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

@Slf4j
public class SlackService {

    public SlackService(final String webhookUrl, final SlackMessage message) {
        send(webhookUrl, message);
    }

    @Async
    public void send(final String webhookUrl, final SlackMessage message) {
        try {
            new Slack(webhookUrl).push(message);
            log.info("slack send : {}", message.toString());
        } catch (final Exception e) {
            log.info("slack send error : {}", e.getMessage());
        }
    }

}
