package dev.redstone.schizophrenia.config;

import me.fzzyhmstrs.fzzy_config.annotations.Comment;
import me.fzzyhmstrs.fzzy_config.api.FileType;
import me.fzzyhmstrs.fzzy_config.api.SaveType;
import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;
import net.minecraft.util.Identifier;


public class SchizoConfig extends Config {

    public SchizoConfig() {
        super(Identifier.of("schizophrenia", "schizo_config"));
    }

    public ItemEditingSection ItemEditingSection = new ItemEditingSection();

    public static class ItemEditingSection extends ConfigSection {
        public ItemEditingSection() {
            super();
        }

        @Comment("if the mod replaces your items")
        public boolean InventoryReplacement = true;

        @Comment("how long it takes for this to replace your items in secs")
        public int InventoryReplacementTimer = 150;
    }


    @Override
    public int defaultPermLevel() {
        return 4;
    }

    @Override
    public FileType fileType() {
        return FileType.JSON5;
    }

    @Override
    public SaveType saveType() {
        return SaveType.SEPARATE;
    }
}
