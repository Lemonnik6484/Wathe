package dev.doctor4t.trainmurdermystery.mixin;

import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class TmmMixinPlugin implements IMixinConfigPlugin {
    private static final Map<String, String> OPTIONAL_MIXINS = Map.of(
            "sodium", "dev.doctor4t.trainmurdermystery.mixin.compat.sodium",
            "controlify", "dev.doctor4t.trainmurdermystery.mixin.compat.controlify"
    );

    @Override
    public void onLoad(String s) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        for (Map.Entry<String, String> entry : OPTIONAL_MIXINS.entrySet()) {
            String modId = entry.getKey();
            String prefix = entry.getValue();

            // skip mixins if no mod -Lemomik
            if (!FabricLoader.getInstance().isModLoaded(modId) && mixinClassName.startsWith(prefix)) {
                return false;
            }
        }

        // I know that this can be smaller but it would look cursed -Lamomak
        if (FabricLoader.getInstance().isModLoaded("sodium") && mixinClassName.equals("dev.doctor4t.trainmurdermystery.mixin.client.scenery.SceneryWorldRendererMixin")) {
            return false;
        }

        return true;
    }


    @Override
    public void acceptTargets(Set<String> set, Set<String> set1) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode classNode, String mixinClassName, IMixinInfo iMixinInfo) {

    }

    @Override
    public void postApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

    }
}
