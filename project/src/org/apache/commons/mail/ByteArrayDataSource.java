package org.apache.commons.mail;

/* ====================================================================
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2001-2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowledgement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgement may appear in the software itself,
 *    if and wherever such third-party acknowledgements normally appear.
 *
 * 4. The names "The Jakarta Project", "Commons", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */
import javax.activation.DataSource;
import java.io.*;

/**
 * This class implements a typed DataSource from:<br>
 *
 * - an InputStream<br>
 * - a byte array<br>
 * - a String<br>
 *
 * @author <a href="mailto:colin.chalmers@maxware.nl">Colin Chalmers</a>
 * @author <a href="mailto:jon@latchkey.com">Jon S. Stevens</a>
 * @author <a href="mailto:bmclaugh@algx.net">Brett McLaughlin</a>
 * @version $Id: ByteArrayDataSource.java,v 1.4 2003/10/12 09:41:36 rdonkin Exp $
 * @deprecated no replacement
 */
public class ByteArrayDataSource
        implements DataSource
{
    /** Stream containg the Data */
    private ByteArrayOutputStream baos = null;

    /** Content-type. */
    private String type = "application/octet-stream";

    /**
     * Create a datasource from a byte array.
     *
     * @param data A byte[].
     * @param type A String.
     * @exception IOException
     */
    public ByteArrayDataSource(byte[] data, String type)
            throws IOException
    {
        ByteArrayInputStream Bis = null;

        try
        {
            Bis = new ByteArrayInputStream(data);
            this.byteArrayDataSource(Bis, type);
        }
        catch (IOException ioex)
        {
            throw ioex;
        }
        finally
        {
            try
            {
                if (Bis != null)
                {
                    Bis.close();
                }
            }
            catch (IOException ignored)
            {
            }
        }
    }

    /**
     * Create a datasource from an input stream.
     *
     * @param aIs An InputStream.
     * @param type A String.
     * @exception IOException
     */
    public ByteArrayDataSource(InputStream aIs, String type)
            throws IOException
    {
        this.byteArrayDataSource(aIs, type);
    }

   /**
     * Create a datasource from an input stream.
     *
     * @param aIs An InputStream.
     * @param type A String.
     * @exception IOException
     */
    private void byteArrayDataSource(InputStream aIs, String type)
            throws IOException
    {
        this.type = type;

        BufferedInputStream Bis = null;
        BufferedOutputStream osWriter = null;

        try
        {
            int length = 0;
            byte[] buffer = new byte[512];

            Bis = new BufferedInputStream( aIs );
               baos = new ByteArrayOutputStream();
            osWriter = new BufferedOutputStream( baos );

            //Write the InputData to OutputStream
            while ((length = Bis.read(buffer)) != -1)
            {
                osWriter.write(buffer, 0 , length);
            }
            osWriter.flush();
            osWriter.close();

        }
        catch (IOException ioex)
        {
            throw ioex;
        }
        finally
        {
            try
            {
                if (Bis != null)
                {
                    Bis.close();
                }
                if (baos != null)
                {
                    baos.close();
                }
                if (osWriter != null)
                {
                    osWriter.close();
                }
            }
            catch (IOException ignored)
            {
            }
        }
    }

    /**
     * Create a datasource from a String.
     *
     * @param data A String.
     * @param type A String.
     * @exception IOException
     */
    public ByteArrayDataSource(String data, String type)
            throws IOException
    {
        this.type = type;

        try
        {
            baos = new ByteArrayOutputStream();

            // Assumption that the string contains only ASCII
            // characters!  Else just pass in a charset into this
            // constructor and use it in getBytes().
            baos.write(data.getBytes("iso-8859-1"));
            baos.flush();
            baos.close();
        }
        catch (UnsupportedEncodingException uex)
        {
            // Do something!
        }
           catch (IOException ignored)
           {
            // Ignore
           }
        finally
        {
            try
            {
                if (baos != null)
                {
                    baos.close();
                }
            }
            catch (IOException ignored)
            {
            }
        }
    }

    /**
     * Get the content type.
     *
     * @return A String.
     */
    public String getContentType()
    {
        return (type == null ? "application/octet-stream" : type);
    }

    /**
     * Get the input stream.
     *
     * @return An InputStream.
     * @exception IOException
     */
    public InputStream getInputStream()
            throws IOException
    {
        if (baos == null)
        {
            throw new IOException("no data");
        }
        return new ByteArrayInputStream(baos.toByteArray());
    }

    /**
     * Get the name.
     *
     * @return A String.
     */
    public String getName()
    {
        return "ByteArrayDataSource";
    }

    /**
     * Get the OutputStream to write to
     *
     * @return  An OutputStream
     * @exception   IOException
     */
    public OutputStream getOutputStream()
            throws IOException
    {
        baos = new ByteArrayOutputStream();
        return baos;
    }
}
