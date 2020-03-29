/*
 * Copyright
 */

package ru.market.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
/**
 * Класс наследующий AbstractSecurityWebApplicationInitializer.
 * Класс нужен для того, чтобы удостовериться, что настройки безопасности включены в основной
 * контекст приложения (их увидел и втянул в себя Root Application Context).
 */

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
}
