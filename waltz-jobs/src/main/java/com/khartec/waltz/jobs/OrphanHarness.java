package com.khartec.waltz.jobs;

import com.khartec.waltz.data.orphan.OrphanDao;
import com.khartec.waltz.model.orphan.OrphanRelationship;
import com.khartec.waltz.service.DIConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class OrphanHarness {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DIConfiguration.class);

        OrphanDao orphanDao = ctx.getBean(OrphanDao.class);

        List<OrphanRelationship> applicationsWithNonExistingOrgUnit = orphanDao.findApplicationsWithNonExistentOrgUnit();
        List<OrphanRelationship> orphanAuthoritativeSources = orphanDao.findOrphanAuthoritiveSourceByDataType();
        System.out.println(applicationsWithNonExistingOrgUnit.size());
    }

}