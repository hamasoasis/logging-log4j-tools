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
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.logging.log4j.docgen.PluginSet;
import org.apache.logging.log4j.docgen.io.stax.PluginBundleStaxReader;

final class PluginSets {

    static Set<PluginSet> ofDescriptorFiles(File[] descriptorFiles) {
        final PluginBundleStaxReader reader = new PluginBundleStaxReader();
        return Arrays.stream(descriptorFiles)
                .map(descriptorFile -> {
                    try {
                        return reader.read(descriptorFile.toString());
                    } catch (final Exception error) {
                        final String message = String.format("failed reading descriptor file `%s`", descriptorFile);
                        throw new RuntimeException(message, error);
                    }
                })
                .collect(Collectors.toSet());
    }
}