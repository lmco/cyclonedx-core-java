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
package org.cyclonedx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.cyclonedx.util.DependencyDeserializer;

@SuppressWarnings("unused")
@JacksonXmlRootElement(localName = "bom")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "bomFormat",
        "specVersion",
        "serialNumber",
        "version",
        "metadata",
        "components",
        "services",
        "externalReferences",
        "dependencies",
        "compositions",
        "properties"
})
public class Bom extends ExtensibleElement {

    @XmlOnly
    @JacksonXmlProperty(isAttribute = true)
    private String xmlns;

    @VersionFilter(versions = {"1.2", "1.3"})
    private Metadata metadata;

    @VersionFilter(versions = {"1.0", "1.1", "1.2", "1.3"})
    private List<Component> components;

    @VersionFilter(versions = {"1.2", "1.3"})
    private List<Service> services;

    @VersionFilter(versions = {"1.1", "1.2", "1.3"})
    private List<Dependency> dependencies;

    @VersionFilter(versions = {"1.1", "1.2", "1.3"})
    private List<ExternalReference> externalReferences;

    @VersionFilter(versions = {"1.3"})
    private List<Composition> compositions;

    @VersionFilter(versions = {"1.3"})
    private List<Property> properties;

    @JacksonXmlProperty(isAttribute = true)
    private int version = 1;

    @JacksonXmlProperty(isAttribute = true)
    private String serialNumber;

    @JsonOnly
    private String specVersion;

    @JsonOnly
    private String bomFormat;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    @JacksonXmlElementWrapper(localName = "components")
    @JacksonXmlProperty(localName = "component")
    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public void addComponent(Component component) {
        if (components == null) {
            components = new ArrayList<>();
        }
        components.add(component);
    }

    @JacksonXmlElementWrapper(localName = "services")
    @JacksonXmlProperty(localName = "service")
    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public void addService(Service service) {
        if (services == null) {
            services = new ArrayList<>();
        }
        services.add(service);
    }

    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonDeserialize(using = DependencyDeserializer.class)
    public List<Dependency> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;
    }

    public void addDependency(Dependency dependency) {
        if (dependencies == null) {
            dependencies = new ArrayList<>();
        }
        dependencies.add(dependency);
    }

    public List<ExternalReference> getExternalReferences() {
        return externalReferences;
    }

    public void addExternalReference(ExternalReference externalReference) {
        if (externalReferences == null) {
            externalReferences = new ArrayList<>();
        }
        externalReferences.add(externalReference);
    }

    public void setExternalReferences(List<ExternalReference> externalReferences) {
        this.externalReferences = externalReferences;
    }

    @JacksonXmlElementWrapper(localName = "compositions")
    @JacksonXmlProperty(localName = "composition")
    public List<Composition> getCompositions() {
        return compositions;
    }

    public void setCompositions(List<Composition> compositions) {
        this.compositions = compositions;
    }

    @JacksonXmlElementWrapper(localName = "properties")
    @JacksonXmlProperty(localName = "property")
    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    /**
     * Returns the CycloneDX spec version of a Bom. The spec version will
     * only be populated when paring a bom via {@link org.cyclonedx.parsers.Parser}.
     * It has no affect on bom generation or any other functionality.
     * @return the String version representation of the spec version
     */
    public String getSpecVersion() {
        return specVersion;
    }

    public String getBomFormat() {
        return bomFormat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bom bom = (Bom) o;
        return version == bom.version &&
                Objects.equals(metadata, bom.metadata) &&
                Objects.equals(components, bom.components) &&
                Objects.equals(dependencies, bom.dependencies) &&
                Objects.equals(externalReferences, bom.externalReferences) &&
                Objects.equals(compositions, bom.compositions) &&
                Objects.equals(properties, bom.properties) &&
                Objects.equals(serialNumber, bom.serialNumber) &&
                Objects.equals(specVersion, bom.specVersion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metadata, components, dependencies, externalReferences, compositions, properties, version, serialNumber, specVersion);
    }
}
