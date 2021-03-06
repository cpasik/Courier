/*
 * Copyright 2005-2017 Courier AUTHORS: please see AUTHORS file.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * 1. Redistributions of source code must retain the above
 *    copyright notice, this list of conditions and the
 *    following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above
 *    copyright notice, this list of conditions and the following
 *    disclaimer in the documentation and/or other materials
 *    provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY AUTHORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * <COPYRIGHT HOLDER> OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF
 * THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 */
package ru.rd.pool;

/**
 * User: AStepochkin
 * Date: 15.10.2008
 * Time: 11:45:22
 */
public class ObjectPoolDelegate implements ObjectPoolIntf {
    private final ObjectPoolIntf m_pool;

    public ObjectPoolDelegate(ObjectPoolIntf pool) {
        m_pool = pool;
    }

    public ObjectPoolIntf getPool() {
        return m_pool;
    }

    public String getDesc() {
        return getPool().getDesc();
    }

    public PoolObjectFactory getObjectFactory() {
        return getPool().getObjectFactory();
    }

    public Object getObject() {
        return getPool().getObject();
    }

    public void releaseObject(final Object o) {
        getPool().releaseObject(o);
    }

    public void releaseAndRemoveObject(final Object o) {
        getPool().releaseAndRemoveObject(o);
    }

    public void start() {
        getPool().start();
    }

    public void close() {
        getPool().close();
    }

    public boolean isStarted() {
        return getPool().isStarted();
    }
}
