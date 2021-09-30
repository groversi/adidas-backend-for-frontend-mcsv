package com.adidas.bff.services;


import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@RunWith(MockitoJUnitRunner.class)
public class ArchTest {

    @Test
    public void serviceShouldBeSuffixed() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.adidas.subscription.services");
        ArchRule rule = classes()
                .that()
                .resideInAPackage("..services..")
                .and()
                .haveSimpleNameNotEndingWith("Impl")
                .and()
                .haveSimpleNameNotEndingWith("Test")
                .should()
                .haveSimpleNameEndingWith("Service");

        rule.check(importedClasses);
    }

    @Test
    public void serviceImplShouldBeSuffixed() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.adidas.subscription.impl");
        ArchRule rule = classes()
                .that()
                .resideInAPackage("..services..")
                .and()
                .haveSimpleNameNotEndingWith("Test")
                .should()
                .haveSimpleNameEndingWith("ServiceImpl");

        rule.check(importedClasses);
    }

    @Test
    public void serviceImplShouldBeAnnotatedWithRestService() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.adidas.subscription.impl");
        ArchRule rule = classes()
                .that()
                .resideInAPackage("..services..")
                .and()
                .haveSimpleNameNotEndingWith("Test")
                .should()
                .beAnnotatedWith(Service.class);

        rule.check(importedClasses);
    }

}
