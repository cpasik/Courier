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
package ru.rd.net;

/**
 * User: AStepochkin
 * Date: 31.07.2008
 * Time: 9:44:19
 */
public class MockMessage {
    public int intField1 = 0;
    public String stringField1 = null;
    public long longField1 = 0;
    public String stringField2 = null;
    public String stringField3 = null;
    public long longField2 = 0;
    public int intField2 = 0;

    private static int s_intTestField1 = 1;
    public static MockMessage createTestMessage() {
        MockMessage ret = new MockMessage();
        s_intTestField1++;
        String incStr = Integer.toString(s_intTestField1);
        ret.intField1 = s_intTestField1;
        ret.stringField1 = "StringField1-" + incStr;
        ret.longField1 = s_intTestField1 + 1;
        ret.stringField2 = "StringField2-" + incStr;
        ret.stringField3 = "StringField3-" + incStr;
        ret.longField2 = s_intTestField1 + 2;
        ret.intField2 = s_intTestField1 + 3;
        return ret;
    }
}
