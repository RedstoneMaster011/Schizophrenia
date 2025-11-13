package dev.redstone.schizophrenia.config;

import me.fzzyhmstrs.fzzy_config.annotations.Comment;
import me.fzzyhmstrs.fzzy_config.api.FileType;
import me.fzzyhmstrs.fzzy_config.api.SaveType;
import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;
import me.fzzyhmstrs.fzzy_config.validation.collection.ValidatedList;
import me.fzzyhmstrs.fzzy_config.validation.minecraft.ValidatedIdentifier;
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
            public int ChanceForInventoryReplacement = 20000;

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

            @Comment("how many ms before the texture reverts for event Fake Item")
            public int TimeForFakeItem = 50;

        }

        @Name("Clone")
        public CloneSection CloneSection = new CloneSection();

        public static class CloneSection extends ConfigSection {

            public CloneSection() {
                super();
            }

            @Comment("if the mod does the event Clone")
            public boolean Clone = true;

            @Comment("a 1 in <val> chance for the event Clone to run.")
            public int ChanceForClone = 40;

        }

        @Name("Camera Move")
        public CameraMoveSection CameraMoveSection = new CameraMoveSection();

        public static class CameraMoveSection extends ConfigSection {

            public CameraMoveSection() {
                super();
            }

            @Comment("if the mod does the event Camera Move")
            public boolean CameraMove = true;

            @Comment("a 1 in <val> chance for the event Camera Move to run.")
            public int ChanceForCameraMove = 15000;

            @Comment("how many px the event Camera Moves")
            public int AmountForCameraMove = 5;

        }

        @Name("Sound")
        public SoundSection SoundSection = new SoundSection();

        public static class SoundSection extends ConfigSection {

            public SoundSection() {
                super();
            }

            @Comment("if the mod does the event Sound")
            public boolean Sound = true;

            @Comment("a 1 in <val> chance for the event Sound to run.")
            public int ChanceForSound = 15000;


            @Comment("list of sounds for event Sound")
            public ValidatedList<Identifier> sounds = new ValidatedIdentifier().toList(
                    Identifier.of("minecraft:entity.creeper.primed"),
                    Identifier.of("minecraft:block.stone.break"),
                    Identifier.of("minecraft:block.stone.step"),
                    Identifier.of("minecraft:ambient.cave"),
                    Identifier.of("minecraft:entity.egg.throw")
            );
        }
    }

    @Comment("how much to increase vals by per day")
    public double AmountPerDayToIncreaseBy = 0.05;


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
