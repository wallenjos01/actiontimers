import buildlogic.Utils

plugins {
    id("build.library")
    id("build.fabric")
    id("build.publish")
}

Utils.setupResources(project, rootProject, "fabric.mod.json")

dependencies {

    minecraft("com.mojang:minecraft:1.21.10")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:0.18.1")

    compileOnly(libs.pseudonym)
    compileOnly(libs.midnightcfg)
    modCompileOnly(libs.pseudonym.minecraft)
    modCompileOnly(libs.midnightcfg.minecraft)

}

