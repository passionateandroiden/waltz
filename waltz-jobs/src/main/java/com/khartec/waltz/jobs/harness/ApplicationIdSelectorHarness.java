/*
 * Waltz - Enterprise Architecture
 * Copyright (C) 2016, 2017 Waltz open source project
 * See README.md for more information
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.khartec.waltz.jobs.harness;

import com.khartec.waltz.common.SetUtilities;
import com.khartec.waltz.data.application.ApplicationIdSelectorFactory;
import com.khartec.waltz.model.EntityKind;
import com.khartec.waltz.model.EntityReference;
import com.khartec.waltz.model.HierarchyQueryScope;
import com.khartec.waltz.model.application.Application;
import com.khartec.waltz.model.application.ApplicationIdSelectionOptions;
import com.khartec.waltz.model.application.ApplicationKind;
import com.khartec.waltz.service.DIConfiguration;
import com.khartec.waltz.service.application.ApplicationService;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Select;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by dwatkins on 13/05/2016.
 */
public class ApplicationIdSelectorHarness {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DIConfiguration.class);
        ApplicationIdSelectorFactory factory = ctx.getBean(ApplicationIdSelectorFactory.class);
        DSLContext dsl = ctx.getBean(DSLContext.class);

        ApplicationService applicationService = ctx.getBean(ApplicationService.class);

        ApplicationIdSelectionOptions options = ApplicationIdSelectionOptions.mkOpts(
                EntityReference.mkRef(EntityKind.MEASURABLE, 1L),
                HierarchyQueryScope.EXACT);


        Select<Record1<Long>> selector = factory.apply(options);
        System.out.println(selector);
        List<Application> apps = applicationService.findByAppIdSelector(options);

        System.out.println("--- sz: "+apps.size());
        apps.forEach(System.out::println);


        ApplicationIdSelectionOptions opt1 = ApplicationIdSelectionOptions.mkOpts(
                EntityReference.mkRef(EntityKind.ORG_UNIT, 20),
                HierarchyQueryScope.CHILDREN
        );

        ApplicationIdSelectionOptions opt2= ApplicationIdSelectionOptions.mkOpts(
                EntityReference.mkRef(EntityKind.ORG_UNIT, 20),
                HierarchyQueryScope.CHILDREN,
                null,
                SetUtilities.fromArray(ApplicationKind.EXTERNALLY_HOSTED, ApplicationKind.EUC));

        List<Application> apps1 = applicationService.findByAppIdSelector(opt1);
        List<Application> apps2 = applicationService.findByAppIdSelector(opt2);

        System.out.println("--- apps 1 size: "+apps1.size());
//        apps1.forEach(System.out::println);


        System.out.println("--- apps 2 size: "+apps2.size());
//        apps2.forEach(System.out::println);


        System.out.println("--- done");
    }


}
