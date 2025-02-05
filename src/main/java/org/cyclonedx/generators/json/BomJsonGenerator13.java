/*
 * This file is part of CycloneDX Core (Java).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 * Copyright (c) OWASP Foundation. All Rights Reserved.
 */
package org.cyclonedx.generators.json;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.cyclonedx.CycloneDxSchema;
import org.cyclonedx.exception.GeneratorException;
import org.cyclonedx.model.Bom;
import org.cyclonedx.model.Component;

/**
 * BomGenerator creates a CycloneDX bill-of-material document from a set of
 * {@link Component}s.
 * @since 5.0.0
 */
public class BomJsonGenerator13 extends AbstractBomJsonGenerator implements BomJsonGenerator {

    private final Bom bom;

    /**
     * Constructs a new BomGenerator object.
     * @param bom the BOM to generate
     */
    public BomJsonGenerator13(final Bom bom) {
        Bom modifiedBom = null;
        try {
            modifiedBom = injectBomFormatAndSpecVersion(bom);
        }
        catch (GeneratorException e) {
        }
        this.bom = modifiedBom != null ? modifiedBom : bom;
    }

    /**
     * Returns the version of the CycloneDX schema used by this instance
     * @return a CycloneDxSchemaVersion enum
     */
    public Version getSchemaVersion() {
        return CycloneDxSchema.Version.VERSION_13;
    }

    /**
     * Creates a CycloneDX BOM from a set of Components.
     * @return an JSON Document representing a CycloneDX BoM
     * @since 5.0.0
     */
    public JsonObject toJsonObject() {
        try {
            JsonReader reader = Json.createReader(new StringReader(toJson(this.bom, false)));

            return reader.readObject();
        } catch (GeneratorException e) {
            return null;
        }
    }

    @Override
    public String toJsonString() {
        try {
            return toJson(this.bom, true);
        }
        catch (GeneratorException e) {
            return "";
        }
    }

    @Override
    public String toString() {
        try {
            return toJson(this.bom, false);
        }
        catch (GeneratorException e) {
            return "";
        }
    }
}
