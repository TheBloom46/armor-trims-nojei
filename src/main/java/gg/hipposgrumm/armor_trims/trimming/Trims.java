package gg.hipposgrumm.armor_trims.trimming;

import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trims {
    public final ResourceLocation name;

    private static Map<ResourceLocation, Pair<ResourceLocation, ResourceLocation>> trims = new HashMap<>();

    public Trims(ResourceLocation name) {
        this.name = name;
    }

    public static void createTrim(ResourceLocation name, ResourceLocation location, ResourceLocation pantsLocation) {
        if (trims.containsKey(name)) {
            LogUtils.getLogger().warn("Trim: "+name+" is already registered.");
        } else {
            trims.put(name, new Pair<>(location, pantsLocation));
        }
    }

    public ResourceLocation getId() {
        return name;
    }

    public ResourceLocation getLocation(boolean pants) {
        return trims.containsKey(name) ? (pants ? trims.get(name).getSecond():trims.get(name).getFirst()) : new ResourceLocation("minecraft:missingno");
    }
}