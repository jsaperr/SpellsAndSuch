package com.jsaperr.sap;

import com.jsaperr.sap.blocks.MagicBlock;
import com.jsaperr.sap.items.BeginnerStaff;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpellsAndSuch implements ModInitializer {

    public static final String MOD_ID = "sas";
    public static final String MOD_NAME = "SpellsAndSuch";

    public static final Logger LOGGER = LoggerFactory.getLogger("spellsandsuch");

    // Example usage in your mod initialization.


    public static final BeginnerStaff BEGINNER_STAFF = Registry.register(Registries.ITEM, new Identifier("spellsandsuch", "beginner_staff"),
            new BeginnerStaff(new FabricItemSettings()));

    public static final Item JUMP_SPELL = Registry.register(Registries.ITEM, new Identifier("spellsandsuch", "jump_spell"),
            new Item(new FabricItemSettings()));

    public static final MagicBlock MAGIC_BLOCK = new MagicBlock(FabricBlockSettings.create().strength(3.0f,9.0f).requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK));

    @Override
    public void onInitialize() {

        Registry.register(Registries.BLOCK, new Identifier("spellsandsuch", "magic_block"), MAGIC_BLOCK);
        Registry.register(Registries.ITEM, new Identifier("spellsandsuch", "magic_block"), new BlockItem(MAGIC_BLOCK, new FabricItemSettings()));



    }
}
