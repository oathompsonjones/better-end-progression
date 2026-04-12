package uk.co.oathompsonjones.datagen;

import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;

import java.util.concurrent.CompletableFuture;

public class BEPRecipeOverrideProvider implements DataProvider {
    private final FabricDataOutput generator;

    public BEPRecipeOverrideProvider(FabricDataOutput generator) {
        this.generator = generator;
    }

    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        return DataProvider.writeToPath(writer,
                new JsonObject(),
                generator.getPath().resolve("data/minecraft/recipes/ender_eye.json")
        );
    }

    @Override
    public String getName() {
        return "Vanilla recipe overrides";
    }
}

