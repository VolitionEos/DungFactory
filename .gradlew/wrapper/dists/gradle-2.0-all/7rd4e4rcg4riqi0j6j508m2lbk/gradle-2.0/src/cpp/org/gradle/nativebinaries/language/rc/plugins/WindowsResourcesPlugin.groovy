/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.nativebinaries.language.rc.plugins

import org.gradle.api.Incubating
import org.gradle.api.Plugin
import org.gradle.api.internal.project.ProjectInternal
import org.gradle.language.rc.WindowsResourceSet
import org.gradle.language.rc.plugins.WindowsResourceScriptPlugin
import org.gradle.nativebinaries.*
import org.gradle.nativebinaries.internal.ProjectNativeBinaryInternal
import org.gradle.nativebinaries.internal.StaticLibraryBinaryInternal
import org.gradle.nativebinaries.language.internal.DefaultPreprocessingTool
import org.gradle.nativebinaries.language.rc.tasks.WindowsResourceCompile
import org.gradle.nativebinaries.plugins.NativeComponentPlugin

/**
 * A plugin for projects wishing to build native binary components from Windows Resource sources.
 *
 * <p>Automatically includes the {@link WindowsResourceScriptPlugin} for core Windows Resource source support
 * and the {@link NativeComponentPlugin} for native component support.</p>
 *
 * <li>Creates a {@link WindowsResourceCompile} task for each {@link WindowsResourceSet} to compile the sources.</li>
 */
@Incubating
class WindowsResourcesPlugin implements Plugin<ProjectInternal> {

    void apply(ProjectInternal project) {
        project.plugins.apply(NativeComponentPlugin)
        project.plugins.apply(WindowsResourceScriptPlugin)

        project.nativeComponents.all { ProjectNativeComponent component ->
            component.binaries.all { NativeBinary binary ->
                if (shouldProcessResources(binary)) {
                    binary.extensions.create("rcCompiler", DefaultPreprocessingTool)
                }
            }
        }

        project.binaries.withType(ProjectNativeBinary) { ProjectNativeBinaryInternal binary ->
            if (shouldProcessResources(binary)) {
                binary.source.withType(WindowsResourceSet).all { WindowsResourceSet resources ->
                    if (resources.mayHaveSources) {
                        def resourceCompileTask = createResourceCompileTask(project, binary, resources)
                        resourceCompileTask.dependsOn resources
                        binary.tasks.add resourceCompileTask
                        final resourceOutputs = resourceCompileTask.outputs.files.asFileTree.matching { include '**/*.res' }
                        binary.tasks.createOrLink.source resourceOutputs
                        if (binary instanceof StaticLibraryBinaryInternal) {
                            binary.additionalLinkFiles resourceOutputs
                        }
                    }
                }
            }
        }
    }

    private boolean shouldProcessResources(NativeBinary binary) {
        binary.targetPlatform.operatingSystem.windows
    }

    private def createResourceCompileTask(ProjectInternal project, ProjectNativeBinaryInternal binary, WindowsResourceSet sourceSet) {
        WindowsResourceCompile compileTask = project.task(binary.namingScheme.getTaskName("compile", sourceSet.fullName), type: WindowsResourceCompile) {
            description = "Compiles resources of the $sourceSet of $binary"
        }

        compileTask.toolChain = binary.toolChain
        compileTask.targetPlatform = binary.targetPlatform

        compileTask.includes {
            sourceSet.exportedHeaders.srcDirs
        }
        compileTask.source sourceSet.source

        compileTask.outputDir = project.file("${project.buildDir}/objectFiles/${binary.namingScheme.outputDirectoryBase}/${sourceSet.fullName}")
        compileTask.macros = binary.rcCompiler.macros
        compileTask.compilerArgs = binary.rcCompiler.args

        compileTask
    }
}