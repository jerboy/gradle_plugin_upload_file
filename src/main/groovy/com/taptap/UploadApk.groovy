package com.taptap

import org.gradle.api.Plugin
import org.gradle.api.Project

class UploadApk implements Plugin<Project> {

    private Project project = null

    @Override
    void apply(Project project) {
        this.project = project;
        project.extensions.create("upload", UploadParamsExtension)
        if (project.hasProperty("android")){
            if (project.android.hasProperty("applicationVariants")){
                project.android.applicationVariants.all { variant ->
                    String name = variant.name.capitalize()
                    if (name.contains("Release")){
                        project.tasks["assemble${name}"] << {
                            createUploadTask(variant)
                        }
                    }
                }
            }
        } else {
            println "Not android project."
        }
    }

    void createUploadTask(Object variant){
        variant.outputs.each {  output ->
            if (variant.buildType.name.equals("release")){

                boolean enable = project.upload._enable
                if (!enable){
                    return ;
                }

                String channel = variant.productFlavors[0].name
                if ( null == channel || channel.trim() == "" ){
                    return
                }

                while (channel.startsWith("_")){
                    channel = channel.substring(1)
                }

                def apk = output.outputFile;
                def upload = new FileUpload();
                upload.file = apk;
                upload.token = project.upload._token
                upload.url = project.upload._url
                upload.cookie = project.upload._cookie
                upload.channel = channel;
                upload.upload()
            }
        }
    }
}
