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
package ru.rd.courier.datalinks;

import org.w3c.dom.Node;
import ru.rd.courier.logging.CourierLogger;
import ru.rd.courier.scripting.dataaccess.FileSystemSource;
import ru.rd.courier.utils.DomHelper;
import ru.rd.pool.ObjectPoolIntf;

/**
 * User: AStepochkin
 * Date: 14.11.2007
 * Time: 17:08:44
 */
public class FileSystemSourceFactory extends ReceiverFactory {
    private final boolean m_showExecOutput;
    private final boolean m_noErrorStreamException;

    public FileSystemSourceFactory(CourierLogger logger, ObjectPoolIntf threadPool, Node conf) {
        super(logger, threadPool);
        m_showExecOutput = DomHelper.getBoolYesNo(conf, "show-exec-output", false);
        m_noErrorStreamException =
            DomHelper.getBoolYesNo(conf, "no-error-stream-exception", false)
            || DomHelper.getBoolYesNo(conf, "ignore-error-stream", false);
    }

    public Object getObject(ObjectPoolIntf pool) {
        return new FileSystemSource(m_logger, m_threadPool, m_showExecOutput, m_noErrorStreamException);
    }
}
