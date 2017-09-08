package tehnut.xtones.block.item;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.xtones.ClientHandler;
import tehnut.xtones.block.BlockXtone;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemBlockXtone extends ItemBlock {

    private final BlockXtone xtone;

    public ItemBlockXtone(BlockXtone block) {
        super(block);

        this.xtone = block;
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    @Nonnull
    public String getHighlightTip(ItemStack stack, @Nonnull String displayName) {
        return super.getHighlightTip(stack, displayName) + " (" + xtone.getTypes()[MathHelper.clamp(stack.getItemDamage(), 0, 15)].getDisplayIndex() + ")";
    }

    @Override
    public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> subItems) {
        if (!isInCreativeTab(tab))
            return;

        for (BlockXtone.XtoneType type : xtone.getTypes())
            subItems.add(new ItemStack(this, 1, type.ordinal()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(@Nonnull ItemStack stack, @Nullable World world, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flag) {
        tooltip.add(I18n.format("tooltip.xtones.type", stack.getItemDamage() + 1));
        tooltip.add(I18n.format("tooltip.xtones.cycle", ClientHandler.SCROLL_CATALYST.getDisplayName()));
        super.addInformation(stack, world, tooltip, flag);
    }
}