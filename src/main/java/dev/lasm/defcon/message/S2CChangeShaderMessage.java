package dev.lasm.defcon.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.optifine.shaders.Shaders;

public class S2CChangeShaderMessage implements IMessage {
    public boolean enable = false;

    public S2CChangeShaderMessage() {

    }

    public S2CChangeShaderMessage(boolean enable) {
        this.enable = enable;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        enable = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(enable);
    }

    public static class Handler implements IMessageHandler<S2CChangeShaderMessage, IMessage> {

        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(S2CChangeShaderMessage message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                if (message.enable) {
                    // Enable shader
                    if (Shaders.currentShaderName != null && !Shaders.currentShaderName.equals("CRT")) {
                        Shaders.setShaderPack("CRT");
                        Shaders.uninit();
                    }
                } else {
                    // Disable shader
                    if (Shaders.currentShaderName != null && !Shaders.currentShaderName.equals("CRT_Internal")) {
                        Shaders.setShaderPack("CRT_Internal");
                        Shaders.uninit();
                    }
                }
            });

            return null;
        }
    }
}
