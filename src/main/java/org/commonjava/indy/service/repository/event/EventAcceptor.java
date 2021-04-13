/**
 * Copyright (C) 2020 Red Hat, Inc.
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
package org.commonjava.indy.service.repository.event;

import org.commonjava.event.store.IndyStoreEvent;
import org.commonjava.event.store.StorePostUpdateEvent;
import org.commonjava.event.store.StorePreUpdateEvent;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EventAcceptor
{
    private final Logger logger = LoggerFactory.getLogger( this.getClass() );

    public static final String CHANNEL_STORE = "store-event";

    @Incoming( CHANNEL_STORE )
    @Acknowledgment( Acknowledgment.Strategy.PRE_PROCESSING)
    public void accept( final IndyStoreEvent event )
    {
        switch ( event.getEventType() )
        {
            case PreUpdate:
                StorePreUpdateEvent preUpdate = (StorePreUpdateEvent) event;
                logger.info( "Pre update event happened: stores {}, type: {}, changeMap: {}, metadata {}",
                             preUpdate.getKeys(), preUpdate.getType(), preUpdate.getChangeMap(),
                             preUpdate.getEventMetadata() );
                break;
            case PostUpdate:
                StorePostUpdateEvent postUpdate = (StorePostUpdateEvent) event;
                logger.info( "Post update event happened: stores {}, type: {}, changeMap: {}, metadata {}",
                             postUpdate.getKeys(), postUpdate.getType(), postUpdate.getChangeMap(),
                             postUpdate.getEventMetadata() );
                break;
            default:
                logger.info( "Store event happened: stores {}, eventType: {},  metadata {}", event.getKeys(),
                             event.getEventType(), event.getEventMetadata() );
                break;
        }
    }
}
