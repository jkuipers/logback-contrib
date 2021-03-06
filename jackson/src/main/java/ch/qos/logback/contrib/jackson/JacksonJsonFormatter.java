/*
 * Copyright (C) 2012, QOS.ch. All rights reserved.
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
 */
package ch.qos.logback.contrib.jackson;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import ch.qos.logback.contrib.json.JsonFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Jackson-specific implementation of the {@link JsonFormatter}.
 *
 * @author Les Hazlewood
 * @since 0.1
 */
public class JacksonJsonFormatter implements JsonFormatter {

    public static final int BUFFER_SIZE = 512;

    private ObjectMapper objectMapper;
    private boolean prettyPrint;

    public JacksonJsonFormatter() {
        this.objectMapper = new ObjectMapper();
        this.prettyPrint = false;
    }

    @Override
    public String toJsonString(Map m) throws IOException {
        StringWriter writer = new StringWriter(BUFFER_SIZE);
        JsonGenerator generator = this.objectMapper.getFactory().createJsonGenerator(writer);

        if (isPrettyPrint()) {
            generator.useDefaultPrettyPrinter();
        }

        this.objectMapper.writeValue(generator, m);

        writer.flush();

        return writer.toString();
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public boolean isPrettyPrint() {
        return prettyPrint;
    }

    public void setPrettyPrint(boolean prettyPrint) {
        this.prettyPrint = prettyPrint;
    }
}

