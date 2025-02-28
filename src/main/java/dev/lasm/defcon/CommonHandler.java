package dev.lasm.defcon;

import dev.lasm.defcon.message.S2CChangeShaderMessage;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Tags.MOD_ID)
public class CommonHandler {
    @SubscribeEvent
    public static void onArmorChange(LivingEquipmentChangeEvent event) {
        if (event.getEntity() instanceof EntityPlayerMP player) {
            if (isHazmatHelmet(event.getTo())) {
                CommonProxy.NETWORK.sendTo(new S2CChangeShaderMessage(true), player);
            } else if (isHazmatHelmet(event.getFrom())) {
                CommonProxy.NETWORK.sendTo(new S2CChangeShaderMessage(false), player);
            }
        }
    }

    private static boolean isHazmatHelmet(ItemStack item) {
        return item.getItem().getRegistryName().equals(new ResourceLocation("nuclearcraft:helm_hazmat"));
    }
}
