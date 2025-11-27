package nocheatersplus;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class alert {

    public static void sendCheatingAlert(String playerName, String cheat) {
        if (Minecraft.getMinecraft().thePlayer != null) {
            String message = String.format(
                    "%sNoCheatersPlus %s>>> %s %sfailed %s%s",
                    EnumChatFormatting.RED,
                    EnumChatFormatting.WHITE,
                    playerName,
                    EnumChatFormatting.GRAY,
                    EnumChatFormatting.WHITE,
                    cheat
            );
            Minecraft.getMinecraft().thePlayer.addChatMessage(
                    new ChatComponentText(message)
            );
        }
    }

    public static void sendCheatingTestAlert(String playerName, String cheat) {
        if (Minecraft.getMinecraft().thePlayer != null) {
            String message = String.format(
                    "%sNoCheatersPlus %s>>> %s %sfailed %s%s %s%sTestCheck",
                    EnumChatFormatting.RED,
                    EnumChatFormatting.WHITE,
                    playerName,
                    EnumChatFormatting.GRAY,
                    EnumChatFormatting.WHITE,
                    cheat,
                    EnumChatFormatting.BOLD,
                    EnumChatFormatting.GREEN
                    );
            Minecraft.getMinecraft().thePlayer.addChatMessage(
                    new ChatComponentText(message)
            );
        }
    }

    public static void sendCheatPlayerAlert(String playerName) {
        if (Minecraft.getMinecraft().thePlayer != null) {
            String message = String.format(
                    "%sNoCheatersPlus %s>>> %s %sis %sCheating",
                    EnumChatFormatting.RED,
                    EnumChatFormatting.YELLOW,
                    playerName,
                    EnumChatFormatting.GRAY,
                    EnumChatFormatting.YELLOW
            );
            Minecraft.getMinecraft().thePlayer.addChatMessage(
                    new ChatComponentText(message)
            );
        }
    }
}