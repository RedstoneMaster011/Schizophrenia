package dev.redstone.schizophrenia.config;

import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;

public class SchizoConfigs {

    public static SchizoConfig SchizoConfig = ConfigApiJava.registerAndLoadConfig(SchizoConfig::new);

    public static void init() {

    }
}