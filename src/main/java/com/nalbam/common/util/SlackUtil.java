package com.nalbam.common.util;

import in.ashwanthkumar.slack.webhook.Slack;
import in.ashwanthkumar.slack.webhook.SlackAttachment;
import in.ashwanthkumar.slack.webhook.SlackMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

@Slf4j
public class SlackUtil {

    private final String webhook;

    public SlackUtil(final String webhook) {
        this.webhook = webhook;
    }

    @Async
    public void send(final SlackMessage message) {
        try {
            new Slack(this.webhook).push(message);
        } catch (final Exception e) {
            log.info("SlackUtil error : {}", e.getMessage());
        }
    }

    @Async
    public void send(final SlackAttachment attachment) {
        try {
            new Slack(this.webhook).push(attachment);
        } catch (final Exception e) {
            log.info("SlackUtil error : {}", e.getMessage());
        }
    }

}
