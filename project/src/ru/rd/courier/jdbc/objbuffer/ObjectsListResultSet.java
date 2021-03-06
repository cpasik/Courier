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
package ru.rd.courier.jdbc.objbuffer;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

/**
 * User: AStepochkin
 * Date: 22.07.2008
 * Time: 19:17:38
 */
public class ObjectsListResultSet extends ObjectsResultSet {
    private final Iterator<Object[]> m_dataIt;

    public ObjectsListResultSet(Statement stmt, ColumnInfo[] columns, Iterator<Object[]> dataIt) {
        super(stmt, columns);
        m_dataIt = dataIt;
    }

    protected boolean getRecord() throws SQLException {
        if (!m_dataIt.hasNext()) return false;
        setData(m_dataIt.next());
        return true;
    }

    protected int skipRecords(int count) throws SQLException {
        int i;
        for (i = 0; i < count; i++) {
            if (!m_dataIt.hasNext()) break;
            m_dataIt.next();
        }
        return i;
    }
}
