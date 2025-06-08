package com.roseyasa.kivogracerebuild.item;

import com.roseyasa.kivogracerebuild.init.ItemAndBlockRegister;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.roseyasa.kivogracerebuild.KivograceRebuild.TAB_KIVOGRACEREBUILD;

public class BlueShiftingStoneItem extends SwordItem {
    public static final String ITEM_NAME = "blue_shifting_stone_item";
    public BlueShiftingStoneItem() {
        super(Tiers.IRON,
                0,
                -2.4f,
                new Item.Properties().tab(TAB_KIVOGRACEREBUILD));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        // 以特殊的方式激发生命能量并封存而成的石头，如果用它来夺取性命的话…
        list.add(Component.translatable("tooltip.blue_shifting_stone_item"));
    }


    @Override
    public boolean hurtEnemy(@NotNull ItemStack pStack, @NotNull LivingEntity pTarget, @NotNull LivingEntity pAttacker) {
        boolean retval = super.hurtEnemy(pStack, pTarget, pAttacker);
        if (pTarget.getHealth()<= 5) {
            // if (Math.random() <= 0.3) {
                if (pAttacker instanceof Player player) {
                    ItemStack _stktoremove = new ItemStack(ItemAndBlockRegister.BLUESHIFTINGSTONE_ITEM.get()); // @debug, should get KV_BLUE_SHIFTING
                    player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(),
                            1, player.inventoryMenu.getCraftSlots());
                }
                if (!pTarget.level.isClientSide()) {
                    /*
                    ItemEntity entityToSpawn = new ItemEntity(pTarget.level, pTarget.getX(), pTarget.getY(), pTarget.getZ(),
                            new ItemStack(ItemAndBlockRegister.TWISTEDGRACE_ITEM.get())); // @debug, should get KV_GRACE
                    entityToSpawn.setPickUpDelay(10);
                    entityToSpawn.setUnlimitedLifetime();

                    pTarget.level.addFreshEntity(entityToSpawn); // @debug, we should change spawn item to insert inventory

                     */
                    // 朝朝想实现的是“击杀任何生物都可以概率添加掉落物”，具体是不是用mixin我不知道，总之可能得绑事件总线
                    if (pAttacker instanceof Player player) {
                        player.addItem(new ItemStack(ItemAndBlockRegister.TWISTEDGRACE_ITEM.get()));
                    }
                }
                if (pAttacker instanceof Player player && !player.level.isClientSide())
                    // 定向石发生了奇特的变化……
                    player.displayClientMessage(Component.translatable("message.blue_shifting_stone_item.succ"), true);
            // }
        } else {
            if (pAttacker instanceof Player player && !player.level.isClientSide())
                // 定向石在微微振动…
                player.displayClientMessage(Component.translatable("message.blue_shifting_stone_item.fail"), true);
        }

        return retval;
    }

}
