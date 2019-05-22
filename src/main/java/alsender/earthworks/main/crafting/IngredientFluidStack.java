package alsender.earthworks.main.crafting;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.fluids.*;

/**
 * @author BluSunrize
 */
public class IngredientFluidStack extends Ingredient {
	private final FluidStack fluid;

	public IngredientFluidStack(FluidStack fluid) {
		super(0);
		this.fluid = fluid;
	}

	public IngredientFluidStack(Fluid fluid, int amount) {
		this(new FluidStack(fluid, amount));
	}

	public FluidStack getFluid() {
		return fluid;
	}

	ItemStack[] cachedStacks;

	@Override
	public ItemStack[] getMatchingStacks() {
		if(cachedStacks == null) {
			cachedStacks = new ItemStack[] { FluidUtil.getFilledBucket(fluid) };
		}
		return this.cachedStacks;
	}

	@Override
	public boolean apply(@Nullable ItemStack stack) {
		if(stack == null) {
			return false;
		}
		else {
			FluidStack fs = FluidUtil.getFluidContained(stack);
			return fs == null && this.fluid == null || fs != null && fs.containsFluid(fluid);
		}
	}
}