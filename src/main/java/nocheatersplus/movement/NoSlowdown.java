package nocheatersplus.movement;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import nocheatersplus.alert;
import nocheatersplus.flag;

import java.util.HashMap;
import java.util.Map;

import static nocheatersplus.main.mc;

public class NoSlowdown {

    private final Map<String, PlayerData> playerDataMap = new HashMap<>();
    private static class PlayerData {
        public int UsingTick = 0;
        public PlayerData() {}
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END && mc.thePlayer != null && mc.theWorld != null) {
            for (EntityPlayer player : mc.theWorld.playerEntities) {
                if (player == mc.thePlayer) continue;
                PlayerData data = playerDataMap.computeIfAbsent(player.getName(), k -> new PlayerData());
                ItemStack heldItem = player.getHeldItem();
                if (heldItem != null && (heldItem.getItem() instanceof ItemFood || heldItem.getItem() instanceof ItemPotion || heldItem.getItem() instanceof ItemBow || heldItem.getItem() instanceof ItemSword) && (player.isUsingItem() || player.isSneaking())) {
                    double dx = player.posX - player.prevPosX;
                    double dz = player.posZ - player.prevPosZ;
                    double speed = Math.sqrt(dx * dx + dz * dz);
                    if (data.UsingTick >= 4 && speed >= 1.23) flag.receiveSignal(player.getName(), "NoSlowdown");
                    data.UsingTick++;
                } else if (data.UsingTick != 0) {
                    data.UsingTick = 0;
                }
            }
        }
    }
}