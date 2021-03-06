package com.nmmoc7.randombotany.specialflower.generating.base;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.AxisAlignedBB;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author DustW
 **/
public abstract class BaseGeneratingFlower extends SubTileGenerating {
    protected List<EntityItem> searchItems(@Nullable Predicate<EntityItem> filter) {
        return getWorld().getEntitiesWithinAABB(EntityItem.class,
                new AxisAlignedBB(getPos().add(-getRange(), -getRange(), -getRange()),
                        getPos().add(getRange() + 1, getRange() + 1, getRange() + 1)),
                (item) ->
                        !item.getItem().isEmpty() && !item.isDead && (filter == null || filter.test(item)));
    }

    abstract public int getRange();

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(toBlockPos(), getRange());
    }
}
