/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.logging.log4j.docgen.maven;

import java.io.File;
import java.util.Set;
import org.apache.logging.log4j.docgen.PluginSet;
import org.apache.logging.log4j.docgen.generator.DocumentationGenerator;
import org.apache.logging.log4j.docgen.generator.DocumentationGeneratorArgs;
import org.apache.logging.log4j.docgen.generator.DocumentationTemplate;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Goal generating documentation by feeding the configuration collected from the provided plugin descriptors (e.g., {@code plugins.xml}) to the FreeMarker templates provided.
 *
 * @see DocumentationGenerator
 */
@Mojo(name = "generate-documentation", defaultPhase = LifecyclePhase.GENERATE_RESOURCES, threadSafe = true)
public class DocumentationGeneratorMojo extends AbstractMojo {

    /**
     * The paths of the plugin descriptor XML files
     */
    @Parameter(property = "log4j.docgen.descriptorFiles", required = true)
    private File[] descriptorFiles;

    /**
     * The path to the FreeMarker template directory
     */
    @Parameter(property = "log4j.docgen.templateDirectory", required = true)
    private File templateDirectory;

    /**
     * The template that will be used to document scalar types
     */
    @Parameter(required = true)
    private DocumentationTemplateMojo scalarsTemplate;

    /**
     * The template that will be used to document plugins
     */
    @Parameter(required = true)
    private DocumentationTemplateMojo pluginTemplateName;

    /**
     * The template that will be used to document interfaces
     */
    @Parameter(required = true)
    private DocumentationTemplateMojo interfaceTemplate;

    @Override
    public void execute() {
        final Set<PluginSet> pluginSets = PluginSets.ofDescriptorFiles(descriptorFiles);
        final DocumentationGeneratorArgs generatorArgs = new DocumentationGeneratorArgs(
                pluginSets,
                templateDirectory.toPath(),
                toApiModel(scalarsTemplate),
                toApiModel(pluginTemplateName),
                toApiModel(interfaceTemplate));
        DocumentationGenerator.generateDocumentation(generatorArgs);
    }

    private static DocumentationTemplate toApiModel(final DocumentationTemplateMojo mojo) {
        return new DocumentationTemplate(mojo.name, mojo.targetPath);
    }
}