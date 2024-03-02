plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
}


tasks.test {
    // Use the built-in JUnit support of Gradle.
    useJUnitPlatform()
}

dependencies{
    implementation(libs.kotlinx.coroutines.core)

    //test
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)


}

