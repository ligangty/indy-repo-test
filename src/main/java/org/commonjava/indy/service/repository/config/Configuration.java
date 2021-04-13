/**
 * Copyright (C) 2011-2021 Red Hat, Inc. (https://github.com/Commonjava/service-parent)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.commonjava.indy.service.repository.config;

import io.quarkus.runtime.Startup;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@Startup
@ApplicationScoped
public class Configuration
{
    public static final String STORAGE_INFINISPAN = "infinispan";

    public static final String STORAGE_CASSANDRA = "cassandra";

    @Inject
    @ConfigProperty( name = "repository.affectedGroupsExclude" )
    Optional<String> affectedGroupsExcludeFilter;

    @Inject
    @ConfigProperty( name = "repository.disposableStorePattern" )
    Optional<String> disposableStorePattern;

    @Inject
    @ConfigProperty( name = "repository.data-storage" )
    Optional<String> storageType;


    public String getAffectedGroupsExcludeFilter()
    {
        return affectedGroupsExcludeFilter.orElse( "" );
    }

    public void setAffectedGroupsExcludeFilter( String affectedGroupsExcludeFilter )
    {
        this.affectedGroupsExcludeFilter = Optional.of( affectedGroupsExcludeFilter );
    }

    public String getDisposableStorePattern()
    {
        return disposableStorePattern.orElse( "" );
    }

    public void setDisposableStorePattern( String disposableStorePattern )
    {
        this.disposableStorePattern = Optional.of( disposableStorePattern );
    }


    public String getStorageType()
    {
        return storageType.orElse( "" );
    }

    public void setStorageType( String storageType )
    {
        this.storageType = Optional.of( storageType );
    }
}
