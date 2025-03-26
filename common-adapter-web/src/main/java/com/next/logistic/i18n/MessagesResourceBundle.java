package com.next.logistic.i18n;

import com.next.logistic.model.SoaResponse;
import com.next.logistic.model.SoaResponsePool;
import jakarta.annotation.PostConstruct;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MessagesResourceBundle {

    private static final String BASE_NAME = "i18n/messages";
    private static final String BASE_NAME_CORE = "i18n-core/messages";
    private final Map<String, String> allLocaleRootMessages = new ConcurrentHashMap<>();

    public MessagesResourceBundle() {
    }

    public Map<String, String> getAllLocalRootMessages() {
        return Collections.unmodifiableMap(allLocaleRootMessages);
    }

    @PostConstruct
    public void init() {
        List<ResourceBundle> resourceBundles = new ArrayList<>();
        resourceBundles.add(ResourceBundle.getBundle(BASE_NAME_CORE, Locale.ROOT));
        resourceBundles.add(ResourceBundle.getBundle(BASE_NAME, Locale.ROOT));

        resourceBundles
                .forEach(bundle -> {
                    Collections.list(bundle.getKeys())
                            .forEach(key -> allLocaleRootMessages.put(key, bundle.getString(key)));
                });
        Map<String, String> map = this.getAllLocalRootMessages();
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            if (key.startsWith(SoaResponse.PREFIX_MESSAGE_CODE)) {
                String soa = key.replace(SoaResponse.PREFIX_MESSAGE_CODE, "");
                SoaResponsePool.put(soa);
            }
        }
    }
}
