package nocheatersplus.world;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import nocheatersplus.flag;

import java.util.HashMap;
import java.util.Map;

import static nocheatersplus.main.mc;

public class ScaffoldA {
    private final Map<String, PlayerData> playerDataMap = new HashMap<>();

    private static class PlayerData {
        public int placecount = 0;
        public int Y = 0;

        public PlayerData() {}
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END && mc.thePlayer != null && mc.theWorld != null) {
            for (EntityPlayer player : mc.theWorld.playerEntities) {
                if (player == mc.thePlayer) continue;
                PlayerData data = playerDataMap.computeIfAbsent(player.getName(), k -> new PlayerData());
                ItemStack heldItem = player.getHeldItem();
                if ((player.isSwingInProgress && (heldItem != null && heldItem.getItem() instanceof ItemBlock))) {
                    if (player.isSneaking()) data.placecount = 0;
                    if (player.serverPosY != data.Y) data.placecount = 0;
                    double dx = player.posX - player.prevPosX;
                    double dz = player.posZ - player.prevPosZ;
                    if (dx * dx + dz * dz > 0.0001D) {
                        float moveYaw = (float)(Math.atan2(dz, dx) * 180.0D / Math.PI) - 90.0F;
                        float yaw = player.rotationYaw;
                        float yawDiff = MathHelper.wrapAngleTo180_float(yaw - moveYaw);
                        float absYawDiff = Math.abs(yawDiff);
                        if (!(absYawDiff >= 130.0F && absYawDiff <= 140.0F || absYawDiff >= 175.0F && absYawDiff <= 180.0F)) data.placecount = 0;
                    }
                    if (player.swingProgress > 0 && player.prevSwingProgress == 0) data.placecount++;
                    if (data.placecount >= 9) flag.receiveSignal(player.getName(), "ScaffoldA");
                    data.Y = player.serverPosY;
                } else {
                    data.placecount = 0;
                }
            }
        }
    }
}