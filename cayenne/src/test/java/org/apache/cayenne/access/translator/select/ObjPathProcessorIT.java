/*****************************************************************
 *   Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 ****************************************************************/

package org.apache.cayenne.access.translator.select;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.dba.DbAdapter;
import org.apache.cayenne.di.Inject;
import org.apache.cayenne.map.ObjEntity;
import org.apache.cayenne.query.ObjectSelect;
import org.apache.cayenne.unit.di.runtime.CayenneProjects;
import org.apache.cayenne.unit.di.runtime.RuntimeCase;
import org.apache.cayenne.unit.di.runtime.UseCayenneRuntime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * @since 4.2
 */
@UseCayenneRuntime(CayenneProjects.INHERITANCE_VERTICAL_PROJECT)
public class ObjPathProcessorIT extends RuntimeCase {

    @Inject
    protected ObjectContext context;

    private ObjPathProcessor pathProcessor;

    @Before
    public void prepareTranslationContext() {
        TranslatorContext translatorContext = new TranslatorContext(
                new FluentSelectWrapper(ObjectSelect.query(Object.class)),
                Mockito.mock(DbAdapter.class),
                context.getEntityResolver(),
                null
        );
        ObjEntity entity = context.getEntityResolver().getObjEntity("IvSub3");
        pathProcessor = new ObjPathProcessor(translatorContext, entity, null);
    }

    @Test
    public void testSimpleAttributePathTranslation() {
        PathTranslationResult result = pathProcessor.process("name");
        assertEquals(1, result.getDbAttributes().size());
        assertEquals(1, result.getAttributePaths().size());

        assertEquals("", result.getLastAttributePath());
        assertEquals("NAME", result.getLastAttribute().getName());
    }

    @Test
    public void testInheritedRelationshipPathTranslation() {
        PathTranslationResult result = pathProcessor.process("ivRoot");
        assertEquals(2, result.getDbAttributes().size());
        assertEquals(2, result.getAttributePaths().size());

        assertEquals("sub3", result.getAttributePaths().get(0));
        assertEquals("ID", result.getDbAttributes().get(0).getName());

        assertEquals("sub3", result.getAttributePaths().get(1));
        assertEquals("IV_ROOT_ID", result.getDbAttributes().get(1).getName());
    }

    @Test
    public void testFlattenedAttributePathTranslation() {
        PathTranslationResult result = pathProcessor.process("ivRoot.discriminator");
        assertEquals(1, result.getDbAttributes().size());
        assertEquals(1, result.getAttributePaths().size());

        assertEquals("sub3.ivRoot1", result.getAttributePaths().get(0));
        assertEquals("DISCRIMINATOR", result.getDbAttributes().get(0).getName());
    }

}