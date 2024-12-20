plugins {
    id("build.library")
}

dependencies {

    api(project(":api"))

    compileOnly(libs.mcore.common)
    compileOnly(libs.mcore.server)
}

