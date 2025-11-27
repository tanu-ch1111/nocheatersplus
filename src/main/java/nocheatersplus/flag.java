package nocheatersplus;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;

import java.util.HashMap;
import java.util.Map;

import static nocheatersplus.main.mc;

public class flag {
    private static final String CHEAT_AUTOBLOCKERA = "AutoBlockerA";
    private static final String CHEAT_AUTOBLOCKERB = "AutoBlockerB";
    private static final String SCAFFOLDA = "ScaffoldA";
    private static final String NOSLOWDOWN = "NoSlowdown";
    private static final String INVALIDHEAD = "InvalidHead";

    private static final Map<String, int[]> flagMap = new HashMap<>();

    public static void receiveSignal(String playerName, String cheatName) {
        if (!(cheatName.equals(CHEAT_AUTOBLOCKERA) || cheatName.equals(CHEAT_AUTOBLOCKERB) || cheatName.equals(SCAFFOLDA) || cheatName.equals(NOSLOWDOWN) || cheatName.equals(INVALIDHEAD))) return;

        int[] flagData = flagMap.getOrDefault(playerName, new int[]{0, (int) (Minecraft.getMinecraft().theWorld.getTotalWorldTime() / 20)});

        flagData[0] += 1;
        flagData[1] = (int) (Minecraft.getMinecraft().theWorld.getTotalWorldTime() / 20);

        flagMap.put(playerName, flagData);
        int MAX_FLAG_COUNT = 5;
        if (cheatName.equals(CHEAT_AUTOBLOCKERA)) MAX_FLAG_COUNT = 5;
        if (cheatName.equals(CHEAT_AUTOBLOCKERB)) MAX_FLAG_COUNT = 4;
        if (cheatName.equals(SCAFFOLDA)) MAX_FLAG_COUNT = 1;
        if (cheatName.equals(NOSLOWDOWN)) MAX_FLAG_COUNT = 1;
        if (cheatName.equals(INVALIDHEAD)) MAX_FLAG_COUNT = 1;

        if (cheatName.equals(INVALIDHEAD)) {
            if (flagData[0] >= MAX_FLAG_COUNT) {
                alert.sendCheatingAlert(playerName, cheatName);
                NameDisplayUtil.addFlagPrefix(playerName);
                mc.thePlayer.playSound("random.anvil_land", 1, 1);
            }
        } else {
            if (flagData[0] >= MAX_FLAG_COUNT) {
                alert.sendCheatingAlert(playerName, cheatName);
                NameDisplayUtil.addFlagPrefix(playerName);
                mc.thePlayer.playSound("random.anvil_land", 1, 1);
            }
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END && Minecraft.getMinecraft().theWorld != null) {
            int currentTimeInSeconds = (int) (Minecraft.getMinecraft().theWorld.getTotalWorldTime() / 20);

            Map<String, int[]> nextFlagMap = new HashMap<>();

            for (Map.Entry<String, int[]> entry : flagMap.entrySet()) {
                String playerName = entry.getKey();
                int[] flagData = entry.getValue();

                if (currentTimeInSeconds - flagData[1] >= 1) {
                } else {
                    nextFlagMap.put(playerName, flagData);
                }
            }

            flagMap.clear();
            flagMap.putAll(nextFlagMap);
        }
    }
}