package com.darkstyler.sttc.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sttc")
@Getter
@Setter
@RequiredArgsConstructor
public class TokenConfig {

	private String secretKey;
}
