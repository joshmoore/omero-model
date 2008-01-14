/*
 *   $Id$
 *
 *   Copyright 2007 Glencoe Software, Inc. All rights reserved.
 *   Use is subject to license terms supplied in LICENSE.txt
 */

package ome.util;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.hibernate.search.bridge.FieldBridge;
import org.springframework.context.ApplicationContext;

/**
 * Delegating {@link FieldBridge} which passes the "fieldBridge" bean from the
 * "ome.model" Spring {@link ApplicationContext} all arguments.
 * 
 * @author Josh Moore, josh at glencoesoftware.com
 * @since 3.0-Beta3
 */
public class DetailsFieldBridge implements FieldBridge {

    private static FieldBridge bridge;

    private final static ReadWriteLock lock = new ReentrantReadWriteLock();

    public static void lock() {
        lock.writeLock().lock();
    }

    public static void unlock() {
        lock.writeLock().unlock();
    }

    public static void setFieldBridge(FieldBridge bridge) {
        DetailsFieldBridge.bridge = bridge;
    }

    public void set(final String name, final Object value,
            final Document document, final Field.Store store,
            final Field.Index index, final Float boost) {

        bridge.set(name, value, document, store, index, boost);

    }

}
