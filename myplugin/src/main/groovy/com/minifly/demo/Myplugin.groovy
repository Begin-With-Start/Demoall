package com.minifly.demo

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class Myplugin implements Plugin<Project> {

    void apply(Project project) {
        System.out.println("------------------开始----------------------")
        System.out.println("这是我们的自定义插件!")

        def android = project.extensions.getByType(AppExtension)
        def classTransform = new TransformTest(project)
        android.registerTransform(classTransform)

        System.out.println("------------------结束----------------------->")
    }

}