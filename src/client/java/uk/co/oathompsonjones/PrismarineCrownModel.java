package uk.co.oathompsonjones;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class PrismarineCrownModel extends EntityModel<Entity> {
    public final ModelPart head;

    public PrismarineCrownModel(ModelPart root) {
        this.head = root.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        float     dilation  = 0.625F;
        modelData.getRoot().addChild("head", ModelPartBuilder.create()
                // Main crown
                .uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(dilation))
                // Left Spike
                .uv(32, 0).cuboid(
                        4.0F + dilation + dilation / 2,
                        -13.0F - dilation * 1.5F,
                        -1.0F,
                        1.0F,
                        5.0F,
                        3.0F,
                        new Dilation(dilation / 2)
                )
                // Right Spike
                .uv(32, 0).cuboid(
                        -4.0F - dilation / 2,
                        -13.0F - dilation * 1.5F,
                        -1.0F,
                        1.0F,
                        5.0F,
                        3.0F,
                        new Dilation(dilation / 2)
                )
                // Centre spike
                .uv(32, 0).cuboid(
                        0.0F + dilation / 2,
                        -13.0F - dilation * 1.5F,
                        -4.0F - dilation / 2,
                        1.0F,
                        5.0F,
                        3.0F,
                        new Dilation(dilation / 2)
                ), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 32);
    }

    @Override
    public void setAngles(
            Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch
    ) {
    }

    @Override
    public void render(
            MatrixStack matrices,
            VertexConsumer vertexConsumer,
            int light,
            int overlay,
            float red,
            float green,
            float blue,
            float alpha
    ) {
        head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}