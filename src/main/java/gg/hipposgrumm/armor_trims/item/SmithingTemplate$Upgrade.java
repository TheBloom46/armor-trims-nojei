package gg.hipposgrumm.armor_trims.item;

import gg.hipposgrumm.armor_trims.util.AssociateTagsWithItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.Arrays;
import java.util.List;

public class SmithingTemplate$Upgrade extends SmithingTemplate {
    private final TagKey<Item> tag;
    private final String translatableName;
    private final String applicableTranslationKey;

    public TagKey<Item> getTag() {
        return this.tag;
    }

    public SmithingTemplate$Upgrade(TagKey<Item> tag, String translatableName, String applicableTranslatable, Properties properties) {
        super(properties.tab(CreativeModeTab.TAB_MATERIALS).stacksTo(1));
        this.tag = tag;
        this.translatableName = translatableName;
        this.applicableTranslationKey = applicableTranslatable;
    }

    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(new TranslatableComponent(translatableName).withStyle(ChatFormatting.DARK_GRAY));
        list.add(new TextComponent(""));
        list.add(new TranslatableComponent("tooltip.armor_trims.applyTo").withStyle(ChatFormatting.GRAY));
        TranslatableComponent usageOutput = new TranslatableComponent(applicableTranslationKey);
        usageOutput.withStyle(usageOutput.getStyle().withColor(getIngredientColor(Items.DIAMOND.getRegistryName())));
        list.add(new TextComponent(" ").append(usageOutput));
        list.add(new TranslatableComponent("tooltip.armor_trims.ingredients").withStyle(ChatFormatting.GRAY));
        List<String> itemsList = Arrays.stream(new AssociateTagsWithItems(tag.location().toString()).getItems()).map(f -> f.getRegistryName().toString()).toList();
        MutableComponent output = createColoredList(itemsList);
        if (output==null) {
            list.add(new TextComponent(" ").append(new TranslatableComponent("tooltip.armor_trims.ingredients.empty").withStyle(ChatFormatting.BLUE, ChatFormatting.ITALIC)));
        } else {
            if (!(List.of(output.getContents().split(", ")).size() > 4) || Screen.hasShiftDown()) {
                list.add(new TextComponent(" ").append(output));
            } else {
                list.add(new TextComponent(" ").append(new TranslatableComponent("tooltip.armor_trims.ingredients.show_more").withStyle(ChatFormatting.BLUE, ChatFormatting.UNDERLINE)));
            }
        }
    }
}
