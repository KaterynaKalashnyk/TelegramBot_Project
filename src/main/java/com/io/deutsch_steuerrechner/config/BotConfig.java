package com.io.deutsch_steuerrechner.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
public class BotConfig {
    @Value("${bot.name}")
    public String botName;

    @Value("${bot.token}")
    public String botToken;



}
