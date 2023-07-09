package de.bitcoinclient.bcutils.messages;

public enum LanguageType {
    DE_DE("de_de"),
    EN_US("en_us");

    private final String key;

    LanguageType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
