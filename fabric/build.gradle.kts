import buildlogic.Utils

plugins {
    id("build.library")
    id("build.fabric")
    id("build.shadow")
}

Utils.setupResources(project, rootProject, "fabric.mod.json")

dependencies {

    minecraft("com.mojang:minecraft:1.21.4")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:0.16.9")

    api(project(":api"))
    api(project(":common"))

    shadow(project(":api"))
    shadow(project(":common"))

    compileOnly(libs.mcore.common)
    compileOnly(libs.mcore.server)
    modCompileOnly(libs.mcore.fabric)
}

