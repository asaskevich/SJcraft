package asaskevich.sjcraft.lib;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class ConfigHandler {

	public static void loadIDs(File configFile) {
		Configuration config = new Configuration(configFile);
		config.load();
		ItemID.guitarID = config.getItem(ItemInfo.guitar_name,
				ItemID.guitarID_default).getInt();
		config.save();
	}

}
