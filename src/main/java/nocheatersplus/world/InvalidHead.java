package nocheatersplus.world;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import nocheatersplus.flag;

import java.util.HashMap;
import java.util.Map;

import static nocheatersplus.main.mc;

public class InvalidHead {
    private final Map<String, InvalidHead.PlayerData> playerDataMap = new HashMap<>();
    private static class PlayerData {
        public float rotationYaw = 0;
        public float rotationYawHead = 0;
        public PlayerData() {}
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END && mc.thePlayer != null && mc.theWorld != null) {
            for (EntityPlayer player : mc.theWorld.playerEntities) {
                if (player == mc.thePlayer) continue;
                InvalidHead.PlayerData data = playerDataMap.computeIfAbsent(player.getName(), k -> new InvalidHead.PlayerData());
                if (data.rotationYaw == player.rotationYaw && Math.abs(data.rotationYawHead - player.rotationYawHead) > 130) flag.receiveSignal(player.getName(), "InvalidHead");
                data.rotationYaw = player.rotationYaw;
                data.rotationYawHead = player.rotationYawHead;
            }
        }
    }
}