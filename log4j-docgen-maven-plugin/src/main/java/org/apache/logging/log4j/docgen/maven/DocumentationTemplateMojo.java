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

import org.apache.maven.plugins.annotations.Parameter;

public final class DocumentationTemplateMojo {

    /**
     * The name of the FreeMarker template.
     * <p>
     * This corresponds to the file path (relative to {@link DocumentationGeneratorMojo#templateDirectory the template directory}) of the FreeMarker template.
     * For instance, {@code scalars.adoc.ftl}, {@code plugin.adoc.ftl}, and {@code interface.adoc.ftl} for scalar values, plugins, and interfaces, respectively.
     * </p>
     */
    @Parameter(required = true)
    String name;

    /**
     * The target file path rendered content will be written to.
     * <p>
     * Certain directives are subject to substitution:
     * </p>
     * <ul>
     * <li>%a – artifact ID, if the type is provided with an artifact origin; {@code unknown-artifactId}, otherwise</li>
     * <li>%c – class name</li>
     * <li>%g – group ID, if the type is provided with an artifact origin; {@code unknown-groupId}, otherwise</li>
     * <li>%v – version, if the type is provided with an artifact origin; {@code unknown-version}, otherwise</li>
     * </ul>
     * <p>
     * For instance,
     * </p>
     * <ul>
     * <li>{@code scalars.adoc} for scalar values</li>
     * <li>{@code %g/%a/%c.adoc} for interfaces and plugins</li>
     * </ul>
     */
    @Parameter(required = true)
    String targetPath;
}