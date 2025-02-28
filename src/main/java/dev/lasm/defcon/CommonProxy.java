package dev.lasm.defcon;

import dev.lasm.defcon.message.S2CChangeShaderMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {
    public static final SimpleNetworkWrapper NETWORK = new SimpleNetworkWrapper("defcon");

    public void preInit() {
        var id = 0;
        NETWORK.registerMessage(S2CChangeShaderMessage.Handler.class, S2CChangeShaderMessage.class, id++, Side.CLIENT);
    }
}
