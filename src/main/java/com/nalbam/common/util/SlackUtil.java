package com.nalbam.common.util;

import in.ashwanthkumar.slack.webhook.Slack;
import in.ashwanthkumar.slack.webhook.SlackAttachment;
import in.ashwanthkumar.slack.webhook.SlackMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class SlackUtil {

    private String webhook;

    public SlackUtil() {
    }

    public SlackUtil(final String webhook) {
        this.webhook = webhook;
    }

    public SlackUtil webhook(final String webhook) {
        this.webhook = webhook;
        return this;
    }

    public void send(final SlackMessage message) {
        if (StringUtils.isEmpty(this.webhook)) {
            this.loadProperties();
        }
        try {
            new Slack(this.webhook).push(message);
            log.debug("SlackUtil send : ok");
        } catch (final Exception e) {
            log.error("SlackUtil error : {}", e.getMessage());
        }
    }

    public void send(final SlackAttachment attachment) {
        if (StringUtils.isEmpty(this.webhook)) {
            this.loadProperties();
        }
        try {
            new Slack(this.webhook).push(attachment);
            log.debug("SlackUtil send : ok");
        } catch (final Exception e) {
            log.error("SlackUtil error : {}", e.getMessage());
        }
    }

    private void loadProperties() {
        final ConfigUtil config = new ConfigUtil();
        this.webhook = config.getVariable("SLACK_WEBHOOK");
    }

}
