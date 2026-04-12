package uk.co.oathompsonjones;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class BEPClient implements ClientModInitializer {
    // In your client mod initializer
    public static final EntityModelLayer PRISMARINE_HELMET_LAYER = new EntityModelLayer(new Identifier(BEP.MOD_ID,
            "prismarine_crown"
    ), "main");

    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        EntityModelLayerRegistry.registerModelLayer(PRISMARINE_HELMET_LAYER,
                PrismarineCrownModel::getTexturedModelData
        );
        ArmorRenderer.register(new PrismarineCrownRenderer(), BEPItems.PRISMARINE_CROWN);
    }
}