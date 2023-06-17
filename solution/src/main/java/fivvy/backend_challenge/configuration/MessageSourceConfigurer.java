package fivvy.backend_challenge.configuration;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Configurador de los sources de i18n
 */
@Component
public class MessageSourceConfigurer extends ResourceBundleMessageSource {

    /**
     * Constructor que settea los basenames de los properties.
     */
    public MessageSourceConfigurer() {
        super();
        this.setBasenames("bundles/i18n-bundles-en");
    }

    /**
     * Método para obtener un mensaje de algún property. Devuelve un mensaje de error si no lo encontró.
     *
     * @param key clave del mensaje
     * @return mensaje
     */
    public String getMessage(String key) {
        return this.getMessage(key, new Object[]{}, new Locale("ES_es"));
    }
}
