package nocheatersplus;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import nocheatersplus.combat.*;
import nocheatersplus.movement.NoSlowdown;
import nocheatersplus.world.*;

@Mod(
        modid = "nocheatersplus",
        name = "NoCheatersPlus",
        version = "B1.3",
        acceptedMinecraftVersions = "[1.8.9]"
)
public class main {
    public static final String MODID = "nocheatersplus";
    public static final String NAME = "NoCheatersPlus";
    public static final String VERSION = "B1.3";
    @Mod.Instance(MODID)
    public static main instance;
    public static Minecraft mc = Minecraft.getMinecraft();

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        // Combat
        MinecraftForge.EVENT_BUS.register(new AutoBlockerA());
        MinecraftForge.EVENT_BUS.register(new AutoBlockerB());
        // Movement
        MinecraftForge.EVENT_BUS.register(new NoSlowdown());
        // World
        MinecraftForge.EVENT_BUS.register(new ScaffoldA());
        MinecraftForge.EVENT_BUS.register(new InvalidHead());
        // Utils
        MinecraftForge.EVENT_BUS.register(new flag());
        MinecraftForge.EVENT_BUS.register(new NameDisplayUtil());
    }
}