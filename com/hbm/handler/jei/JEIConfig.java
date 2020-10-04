package com.hbm.handler.jei;

import com.hbm.blocks.ModBlocks;
import com.hbm.inventory.CentrifugeRecipes;
import com.hbm.inventory.CrystallizerRecipes;
import com.hbm.inventory.ShredderRecipes;
import com.hbm.inventory.gui.GUIBook;
import com.hbm.inventory.gui.GUICrystallizer;
import com.hbm.inventory.gui.GUIMachineAssembler;
import com.hbm.inventory.gui.GUIMachineBoiler;
import com.hbm.inventory.gui.GUIMachineBoilerElectric;
import com.hbm.inventory.gui.GUIMachineCMBFactory;
import com.hbm.inventory.gui.GUIMachineCentrifuge;
import com.hbm.inventory.gui.GUIMachineChemplant;
import com.hbm.inventory.gui.GUIMachineCyclotron;
import com.hbm.inventory.gui.GUIMachineEPress;
import com.hbm.inventory.gui.GUIMachineGasCent;
import com.hbm.inventory.gui.GUIMachinePress;
import com.hbm.inventory.gui.GUIMachineReactor;
import com.hbm.inventory.gui.GUIMachineRefinery;
import com.hbm.inventory.gui.GUIMachineShredder;
import com.hbm.inventory.gui.GUITestDiFurnace;
import com.hbm.items.ModItems;
import com.hbm.items.weapon.ItemCustomMissile;
import com.hbm.main.MainRegistry;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

@JEIPlugin
public class JEIConfig implements IModPlugin {

	public static final String ASSEMBLY = "hbm.assembly";
	public static final String CHEMPLANT = "hbm.chemplant";
	public static final String CYCLOTRON = "hbm.cyclotron";
	public static final String PRESS = "hbm.press";
	public static final String ALLOY = "hbm.alloy";
	public static final String BOILER = "hbm.boiler";
	public static final String CENTRIFUGE = "hbm.centrifuge";
	public static final String CMB = "hbm.cmb_furnace";
	public static final String GAS_CENT = "hbm.gas_centrifuge";
	public static final String REACTOR = "hbm.reactor";
	public static final String REFINERY = "hbm.refinery";
	public static final String SHREDDER = "hbm.shredder";
	public static final String FLUIDS = "hbm.fluids";
	public static final String CRYSTALLIZER = "hbm.crystallizer";
	public static final String BOOK = "hbm.book_of";
	public static final String FUSION_BYPRODUCT = "hbm.fusionbyproduct";

	@Override
	public void register(IModRegistry registry) {
		registry.addRecipeRegistryPlugin(new HbmJeiRegistryPlugin());

		registry.addRecipeCatalyst(new ItemStack(ModBlocks.machine_assembler), ASSEMBLY);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.machine_chemplant), CHEMPLANT);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.machine_cyclotron), CYCLOTRON);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.machine_press), PRESS);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.machine_difurnace_off), ALLOY);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.machine_boiler_off), BOILER);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.machine_centrifuge), CENTRIFUGE);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.machine_combine_factory), CMB);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.machine_gascent), GAS_CENT);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.machine_reactor), REACTOR);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.machine_refinery), REFINERY);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.machine_shredder), SHREDDER);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.machine_fluidtank), FLUIDS);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.machine_crystallizer), CRYSTALLIZER);
		//This recipe catalyst doesn't work, since the book of is blacklisted.
		//registry.addRecipeCatalyst(new ItemStack(ModItems.book_of_), BOOK);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.iter), FUSION_BYPRODUCT);

		// registry.addRecipes(ItemAssemblyTemplate.recipes, ASSEMBLY);
		registry.addRecipes(JeiRecipes.getChemistryRecipes(), CHEMPLANT);
		registry.addRecipes(JeiRecipes.getCyclotronRecipes(), CYCLOTRON);
		registry.addRecipes(JeiRecipes.getPressRecipes(), PRESS);
		registry.addRecipes(JeiRecipes.getAlloyRecipes(), ALLOY);
		registry.addRecipes(JeiRecipes.getBoilerRecipes(), BOILER);
		registry.addRecipes(CentrifugeRecipes.getCentrifugeRecipes(), CENTRIFUGE);
		registry.addRecipes(JeiRecipes.getCMBRecipes(), CMB);
		registry.addRecipes(JeiRecipes.getGasCentrifugeRecipes(), GAS_CENT);
		registry.addRecipes(JeiRecipes.getReactorRecipes(), REACTOR);
		registry.addRecipes(JeiRecipes.getRefineryRecipe(), REFINERY);
		registry.addRecipes(ShredderRecipes.getShredderRecipes(), SHREDDER);
		registry.addRecipes(JeiRecipes.getFluidEquivalences(), FLUIDS);
		registry.addRecipes(CrystallizerRecipes.getRecipes(), CRYSTALLIZER);
		registry.addRecipes(JeiRecipes.getBookRecipes(), BOOK);
		registry.addRecipes(JeiRecipes.getFusionByproducts(), FUSION_BYPRODUCT);

		registry.addRecipeClickArea(GUIMachineAssembler.class, 45, 83, 82, 30, ASSEMBLY);
		registry.addRecipeClickArea(GUIMachineChemplant.class, 45, 90, 85, 15, CHEMPLANT);
		registry.addRecipeClickArea(GUIMachineCyclotron.class, 50, 24, 40, 40, CYCLOTRON);
		registry.addRecipeClickArea(GUIMachinePress.class, 80, 35, 15, 15, PRESS);
		registry.addRecipeClickArea(GUIMachineEPress.class, 80, 35, 15, 15, PRESS);
		registry.addRecipeClickArea(GUITestDiFurnace.class, 102, 36, 21, 14, ALLOY);
		registry.addRecipeClickArea(GUIMachineBoiler.class, 79, 34, 17, 35, BOILER);
		registry.addRecipeClickArea(GUIMachineBoilerElectric.class, 79, 34, 17, 35, BOILER);
		registry.addRecipeClickArea(GUIMachineCentrifuge.class, 61, 17, 53, 52, CENTRIFUGE);
		registry.addRecipeClickArea(GUIMachineCMBFactory.class, 111, 35, 21, 14, CMB);
		registry.addRecipeClickArea(GUIMachineGasCent.class, 118, 36, 51, 13, GAS_CENT);
		registry.addRecipeClickArea(GUIMachineReactor.class, 80, 35, 21, 14, REACTOR);
		registry.addRecipeClickArea(GUIMachineRefinery.class, 79, 71, 71, 17, REFINERY);
		registry.addRecipeClickArea(GUIMachineShredder.class, 43, 89, 53, 17, SHREDDER);
		registry.addRecipeClickArea(GUICrystallizer.class, 103, 34, 23, 16, CRYSTALLIZER);
		registry.addRecipeClickArea(GUIBook.class, 89, 34, 23, 16, BOOK);

		IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();
		blacklist.addIngredientToBlacklist(new ItemStack(ModItems.ams_core_thingy));
		// Some things are even beyond my control...or are they?
		blacklist.addIngredientToBlacklist(new ItemStack(ModItems.memory));

		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.machine_coal_on));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.machine_electric_furnace_on));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.machine_difurnace_on));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.machine_nuke_furnace_on));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.machine_rtg_furnace_on));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.reinforced_lamp_on));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.statue_elb));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.statue_elb_g));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.statue_elb_w));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.statue_elb_f));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.cheater_virus));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.cheater_virus_seed));
		// blacklist.addIngredientToBlacklist(new ItemStack(ModItems.euphemium_helmet));
		// blacklist.addIngredientToBlacklist(new ItemStack(ModItems.euphemium_plate));
		// blacklist.addIngredientToBlacklist(new ItemStack(ModItems.euphemium_legs));
		// blacklist.addIngredientToBlacklist(new ItemStack(ModItems.euphemium_boots));
		// blacklist.addIngredientToBlacklist(new ItemStack(ModItems.apple_euphemium));
		// blacklist.addIngredientToBlacklist(new ItemStack(ModItems.ingot_euphemium));
		// blacklist.addIngredientToBlacklist(new ItemStack(ModItems.nugget_euphemium));
		blacklist.addIngredientToBlacklist(new ItemStack(ModItems.euphemium_kit));
		// blacklist.addIngredientToBlacklist(new ItemStack(ModItems.euphemium_stopper));
		// blacklist.addIngredientToBlacklist(new ItemStack(ModItems.watch));
		// blacklist.addIngredientToBlacklist(new ItemStack(ModItems.rod_quad_euphemium));
		// blacklist.addIngredientToBlacklist(new ItemStack(ModItems.rod_euphemium));
		blacklist.addIngredientToBlacklist(new ItemStack(ModItems.bobmazon_hidden));
		if(MainRegistry.polaroidID != 11) {
			blacklist.addIngredientToBlacklist(new ItemStack(ModItems.book_secret));
			blacklist.addIngredientToBlacklist(new ItemStack(ModItems.book_of_));
			blacklist.addIngredientToBlacklist(new ItemStack(ModItems.burnt_bark));
			blacklist.addIngredientToBlacklist(new ItemStack(ModItems.ams_core_thingy));
		}
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_block_assembler));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_block_centrifuge));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_block_chemplant));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_block_cyclotron));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_block_drill));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_block_flare));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_block_fluidtank));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_block_pumpjack));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_block_refinery));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_block_turbofan));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_block_well));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_block_ams_base));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_block_ams_emitter));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_block_ams_limiter));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_block_reactor_small));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_port_assembler));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_port_chemplant));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_port_cyclotron));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_port_drill));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_port_flare));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_port_fluidtank));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_port_pumpjack));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_port_refinery));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_port_turbofan));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_port_well));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_port_ams_base));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_port_ams_emitter));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_port_ams_limiter));
		blacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.dummy_port_reactor_small));
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		IGuiHelper help = registry.getJeiHelpers().getGuiHelper();
		registry.addRecipeCategories(new AssemblerRecipeHandler(help),
				new ChemplantRecipeHandler(help),
				new CyclotronRecipeHandler(help),
				new PressRecipeHandler(help),
				new AlloyFurnaceRecipeHandler(help),
				new BoilerRecipeHandler(help),
				new CentrifugeRecipeHandler(help),
				new CMBFurnaceRecipeHandler(help),
				new GasCentrifugeRecipeHandler(help),
				new ReactorRecipeHandler(help),
				new RefineryRecipeHandler(help),
				new ShredderRecipeHandler(help),
				new FluidRecipeHandler(help),
				new CrystallizerRecipeHandler(help),
				new BookRecipeHandler(help),
				new FusionRecipeHandler(help));
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {
		subtypeRegistry.registerSubtypeInterpreter(ModItems.cell, (ItemStack stack) -> {
			FluidStack fluid = FluidUtil.getFluidContained(stack);
			return ModItems.cell.getUnlocalizedName() + (fluid == null ? "empty" : fluid.getFluid().getUnlocalizedName() + fluid.amount);
		});
		subtypeRegistry.registerSubtypeInterpreter(ModItems.fluid_barrel_full, (ItemStack stack) -> {
			FluidStack fluid = FluidUtil.getFluidContained(stack);
			return ModItems.fluid_barrel_full.getUnlocalizedName() + (fluid == null ? "empty" : fluid.getFluid().getUnlocalizedName() + fluid.amount);
		});
		subtypeRegistry.registerSubtypeInterpreter(ModItems.fluid_tank_full, (ItemStack stack) -> {
			FluidStack fluid = FluidUtil.getFluidContained(stack);
			return ModItems.fluid_tank_full.getUnlocalizedName() + (fluid == null ? "empty" : fluid.getFluid().getUnlocalizedName() + fluid.amount);
		});
		subtypeRegistry.registerSubtypeInterpreter(ModItems.canister_generic, (ItemStack stack) -> {
			FluidStack fluid = FluidUtil.getFluidContained(stack);
			return ModItems.canister_generic.getUnlocalizedName() + (fluid == null ? "empty" : fluid.getFluid().getUnlocalizedName() + fluid.amount);
		});
		subtypeRegistry.registerSubtypeInterpreter(ModItems.missile_custom, (ItemStack stack) -> {
			return ModItems.missile_custom.getUnlocalizedName() + "w" + Integer.toString(ItemCustomMissile.readFromNBT(stack, "warhead")) + "f" + Integer.toString(ItemCustomMissile.readFromNBT(stack, "fuselage")) + "s" + Integer.toString(ItemCustomMissile.readFromNBT(stack, "stability")) + "t" + Integer.toString(ItemCustomMissile.readFromNBT(stack, "thruster"));
		});
		subtypeRegistry.registerSubtypeInterpreter(ModItems.fluid_icon, (ItemStack stack) -> {
			if(stack.hasTagCompound()) {
				String s = "";
				if(stack.getTagCompound().hasKey("type"))
					s = s + stack.getTagCompound().getString("type");
				return s;
			}
			return "";
		});
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
	}
}