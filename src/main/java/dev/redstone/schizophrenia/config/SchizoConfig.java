package dev.redstone.schizophrenia.config;

import me.fzzyhmstrs.fzzy_config.annotations.Comment;
import me.fzzyhmstrs.fzzy_config.annotations.Inline;
import me.fzzyhmstrs.fzzy_config.api.FileType;
import me.fzzyhmstrs.fzzy_config.api.SaveType;
import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;
import net.minecraft.util.Identifier;


public class SchizoConfig extends Config {

    public SchizoConfig() {
        super(Identifier.of("schizophrenia", "schizo_config"));
    }

    @Name("Events")
    public EventsSection EventsSection = new EventsSection();

    public static class EventsSection extends ConfigSection {

        public EventsSection() {
            super();
        }

        @Name("Inventory Replacement")
        public InventoryReplacementSection InventoryReplacementSection = new InventoryReplacementSection();

        public static class InventoryReplacementSection extends ConfigSection {

            public InventoryReplacementSection() {
                super();
            }

            @Comment("if the mod does the event Inventory Replacement")
            public boolean InventoryReplacement = true;

            @Comment("a 1 in <val> chance for the event Inventory Replacement to run.")
            public int ChanceForInventoryReplacement = 100;

        }

        @Name("Fake Item")
        public FakeItemSection FakeItemSection = new FakeItemSection();

        public static class FakeItemSection extends ConfigSection {

            public FakeItemSection() {
                super();
            }
            @Comment("if the mod does the event Fake Item")
            public boolean FakeItem = true;

            @Comment("a 1 in <val> chance for the event Fake Item to run.")
            public int ChanceForFakeItem = 30;

            @Comment("how many ms before the texture reverts for event FakeItem")
            public int TimeForFakeItem = 30;

        }
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
