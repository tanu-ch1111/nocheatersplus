package nocheatersplus.combat;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import nocheatersplus.flag;

import static nocheatersplus.main.mc;

public class AutoBlockerA {

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END && mc.thePlayer != null) {
            for (EntityPlayer player : mc.theWorld.playerEntities) {
                if (player == mc.thePlayer) continue;
                boolean isGuarding = false;
                boolean isAttacking = false;
                ItemStack heldItem = player.getHeldItem();
                if (heldItem != null && heldItem.getItem() instanceof ItemSword && player.isBlocking()) isGuarding = true;
                if (player.swingProgress > 0 && player.prevSwingProgress == 0) isAttacking = true;
                if (isGuarding && isAttacking) flag.receiveSignal(player.getName(), "AutoBlockerA");
            }
        }
    }
}