package gg.hipposgrumm.armor_trims.util;

import com.google.common.collect.ImmutableList;
import gg.hipposgrumm.armor_trims.config.Config;
import gg.hipposgrumm.armor_trims.item.SmithingTemplate;
import gg.hipposgrumm.armor_trims.item.SmithingTemplate$Upgrade;
import gg.hipposgrumm.armor_trims.trimming.Trims;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.ArrayUtils;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LargeItemLists {
    private static List<Item> allArmors = List.of();
    private static List<Item> smithingTemplates = List.of();
    private static List<Item> smithingTemplatesUpgrades = List.of();
    private static List<Item> smithingTemplatesTrims = List.of();

    private static List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        for (Item item : ImmutableList.copyOf(ForgeRegistries.ITEMS.iterator())) {
            items.add(item);
            //LogUtils.getLogger().info("Got: "+item);
        }
        return items;
    }

    public static void setAllArmors() {
        allArmors = getAllItemsOfType(ArmorItem.class);
    }

    public static void setAllTemplates() {
        smithingTemplates = getAllItemsOfType(SmithingTemplate.class);
    }

    public static void setAllUpgradeTemplates() {
        smithingTemplatesUpgrades = getAllItemsOfType(SmithingTemplate$Upgrade.class);
    }

    public static void setAllTrimTemplates() {
        smithingTemplatesTrims = smithingTemplates.stream().filter(f -> !smithingTemplatesUpgrades.contains(f)).toList();
    }

    public static List<Item> getAllArmors() {
        return allArmors;
    }

    public static List<Item> getAllSmithingTemplates() {
        return smithingTemplates;
    }

    public static List<Item> getUpgradeSmithingTemplates() {
        return smithingTemplatesUpgrades;
    }

    public static List<Item> getTrimSmithingTemplates() {
        return smithingTemplatesTrims;
    }

    public static List<Item> getAllMaterials() {
        Item[] itemlist = new Item[0];
        for (String item : Config.trimmableMaterials()) {
            if (item.startsWith("#")) {
                Item[] itemTagged = new AssociateTagsWithItems(item.replace("#","")).getItems();
                itemlist = ArrayUtils.addAll(itemlist, itemTagged);
            } else {
                itemlist = ArrayUtils.add(itemlist, ForgeRegistries.ITEMS.getValue(new ResourceLocation(item)));
            }
        }
        itemlist = ArrayUtils.removeAllOccurrences(itemlist, Items.AIR);
        return List.of(itemlist);
    }

    public static List<Item> getAllItemsOfType(Class<? extends Item> itemType) {
        Item[] items = new Item[0];
        for (Item item:getAllItems()) {
            if (itemType.isInstance(item)) items = ArrayUtils.add(items, item);
        }
        return List.of(items);
    }
}
