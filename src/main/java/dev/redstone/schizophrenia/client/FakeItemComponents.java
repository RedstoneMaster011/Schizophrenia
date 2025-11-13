package dev.redstone.schizophrenia.client;

import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;

public class FakeItemComponents {
    public static final ComponentType<Boolean> FAKEITEM = ComponentType.<Boolean>builder()
            .packetCodec(PacketCodecs.BOOL)
            .build();
}