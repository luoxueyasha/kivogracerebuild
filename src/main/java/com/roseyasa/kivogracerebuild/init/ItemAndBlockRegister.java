package com.roseyasa.kivogracerebuild.init;

import com.roseyasa.kivogracerebuild.item.BlueShiftingStoneItem;
import com.roseyasa.kivogracerebuild.item.TwistedGraceItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import static com.roseyasa.kivogracerebuild.KivograceRebuild.*;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemAndBlockRegister {

    // public static final RegistryObject<Item> TwistedGraceItem = ITEMS.register("twistedgraceitem", ()->
    // new TwistedGraceItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> TWISTEDGRACE_ITEM = ITEMS.register(TwistedGraceItem.ITEM_NAME, TwistedGraceItem::new);
    public static final RegistryObject<Item> BLUESHIFTINGSTONE_ITEM = ITEMS.register(BlueShiftingStoneItem.ITEM_NAME, BlueShiftingStoneItem::new);
}
