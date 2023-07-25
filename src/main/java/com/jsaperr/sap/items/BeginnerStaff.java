package com.jsaperr.sap.items;

import com.jsaperr.sap.SpellsAndSuch;
import com.jsaperr.sap.util.RC;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BeginnerStaff extends Item {
    public BeginnerStaff(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        PlayerInventory playerInventory = user.getInventory();

        boolean hasTNT = false;
        boolean hasJumpSpell = false;

        if (playerInventory.contains(new ItemStack(Items.TNT))) {hasTNT = true;}
        if (playerInventory.contains(new ItemStack(SpellsAndSuch.JUMP_SPELL))) {hasJumpSpell = true;}

        // Perform actions based on the player's inventory contents
        if (hasTNT && !hasJumpSpell) { //EXPLODE
            user.playSound(SoundEvents.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
            Vec3d pos = RC.lookLocation(world, user);
            world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 4f, World.ExplosionSourceType.BLOCK);
        } else if (hasJumpSpell && !hasTNT) {//JUMP
            user.playSound(SoundEvents.ENTITY_BAT_TAKEOFF, 1.0F, 1.0F);
            user.addVelocity(0, 1, 0);
        } else if (hasTNT && hasJumpSpell) { //BOTH ITEMS
            user.playSound(SoundEvents.ENTITY_VILLAGER_NO, 1.0F, 1.0F);
            if (!world.isClient) {user.sendMessage(Text.of("The Beginner Staff only allows for one spell at a time!"), false);}
        } else { //NO ITEMS
            user.playSound(SoundEvents.ENTITY_VILLAGER_NO, 1.0F, 0.5F);
            if (!world.isClient) {user.sendMessage(Text.of("No spells present in inventory!"), false);}
        }


        user.getItemCooldownManager().set(this, 20);
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
