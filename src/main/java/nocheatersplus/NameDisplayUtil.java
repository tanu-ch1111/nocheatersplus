package nocheatersplus;

import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashSet;
import java.util.Set;

public class NameDisplayUtil {

    private static final Set<String> flaggedPlayers = new HashSet<>();
    private static final String FLAG_PREFIX = EnumChatFormatting.DARK_PURPLE + "⚠ " + EnumChatFormatting.RESET;

    public static void addFlagPrefix(String playerName) {
        flaggedPlayers.add(playerName);
    }

    @SubscribeEvent
    public void onNameFormat(PlayerEvent.NameFormat event) {
        String playerName = event.entityPlayer.getName();
        if (flaggedPlayers.contains(playerName)) {
            event.displayname = FLAG_PREFIX + event.displayname;
            alert.sendCheatPlayerAlert(playerName);
        }
    }
}