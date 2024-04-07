package com.roseyasa.kivogracerebuild.item;

import com.roseyasa.kivogracerebuild.KivograceRebuild;
import com.sun.tools.jconsole.JConsoleContext;
import net.minecraft.network.chat.Component;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraftforge.fluids.FluidType;

public class TwistedGraceItem extends Item {

    public TwistedGraceItem(Properties pProperties) {
        super(pProperties);

    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {

        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        // check if player is in water, or something that can drown player
        net.minecraftforge.fluids.FluidType fluidType = pPlayer.getMaxHeightFluidType();
        if(!pPlayer.isInWater() || !fluidType.canDrownIn(pPlayer)){
            pPlayer.displayClientMessage(Component.translatable("message.twistedgraceitem.fail"),true);
            return InteractionResultHolder.fail(itemstack);
        }
        pPlayer.getCooldowns().addCooldown(this, 20);

        // first we apply 10 damage to player.
        pPlayer.hurt(DamageSource.MAGIC, 10);

        // check if player is still alive
        if(pPlayer.getHealth() > 0) {
        // if alive, then set health and hunger to 1
            pPlayer.setHealth(1.0f);
            pPlayer.getFoodData().setFoodLevel(1);
        }

        //if(Math.random() <= 0.75) {
            // @debug: this item given needs to be replaced
            pPlayer.addItem(new ItemStack(KivograceRebuild.myblock_item.get())); // add item at last, no matter player is dead or alive
            pPlayer.displayClientMessage(Component.translatable("message.twistedgraceitem.succ"), true);
            /* @debug: No Gacha
        } else{
            pPlayer.displayClientMessage(Component.translatable("message.twistedgraceitem.missed"), true);
        }
        */

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }
}
