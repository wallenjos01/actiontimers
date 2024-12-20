plugins {
    id("build.library")
    id("build.publish")
}

dependencies {

    compileOnly(libs.mcore.common)
    compileOnly(libs.mcore.server)
}

