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
package ru.rd.courier.scripting.dataaccess;

import ru.rd.courier.CourierException;
import ru.rd.courier.scripting.TimedStringReceiver;
import ru.rd.courier.scripting.LinkWarning;

import java.io.PrintStream;
import java.util.List;

public class StreamStringReceiver extends TimedStringReceiver {
    private PrintStream m_out = null;
    private boolean m_needClose;

    public StreamStringReceiver(final PrintStream out, final boolean needClose) {
        m_out = out;
        m_needClose = needClose;
    }

    public StreamStringReceiver(final PrintStream out) {
        this(out, true);
    }

    public List<LinkWarning> timedProcess(final String operation) throws CourierException {
        m_out.println(operation);
        return null;
    }

    public List<LinkWarning> timedFlush() {
        m_out.flush();
        return null;
    }

    public void start() {
    }

    public void timedClose() {
        if (m_needClose) m_out.close();
    }

    public void setTimeout(final int timeout) throws CourierException {
    }

    public void cancel() throws CourierException {
    }

}
