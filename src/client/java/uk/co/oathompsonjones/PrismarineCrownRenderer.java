package uk.co.oathompsonjones;

import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class PrismarineCrownRenderer implements ArmorRenderer {
    private PrismarineCrownModel model;

    @Override
    public void render(
            MatrixStack matrices,
            VertexConsumerProvider vertices,
            ItemStack stack,
            LivingEntity entity,
            EquipmentSlot slot,
            int light,
            BipedEntityModel<LivingEntity> contextModel
    ) {
        if (model == null) {
            model = new PrismarineCrownModel(MinecraftClient
                    .getInstance()
                    .getEntityModelLoader()
                    .getModelPart(BEPClient.PRISMARINE_HELMET_LAYER));
        }

        // Copy head rotation and position
        model.head.copyTransform(contextModel.head);

        // Render
        VertexConsumer vc = vertices.getBuffer(RenderLayer.getArmorCutoutNoCull(new Identifier(BEP.MOD_ID,
                "textures/models/armor/prismarine_scale_layer_1.png"
        )));

        model.render(matrices, vc, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
    }
}
