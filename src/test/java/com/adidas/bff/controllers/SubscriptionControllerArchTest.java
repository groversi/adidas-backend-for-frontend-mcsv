package com.adidas.bff.controllers;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.bind.annotation.*;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

@RunWith(MockitoJUnitRunner.class)
public class SubscriptionControllerArchTest {

    @Test
    public void controllersShouldBeSuffixed() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.adidas.subscription.controllers");
        ArchRule rule = classes()
                .that()
                .resideInAPackage("..controllers..")
                .and()
                .haveSimpleNameNotEndingWith("Test")
                .should()
                .haveSimpleNameEndingWith("Controller");

        rule.check(importedClasses);
    }

    @Test
    public void controllersShouldBeAnnotatedWithRestController() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.adidas.subscription.controllers");
        ArchRule rule = classes()
                .that()
                .resideInAPackage("..controllers..")
                .and()
                .haveSimpleNameNotEndingWith("Test")
                .should()
                .beAnnotatedWith(RestController.class);

        rule.check(importedClasses);
    }

    @Test
    public void controllersShouldBeAnnotatedWithRequestMappping() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.adidas.subscription.controllers");
        ArchRule rule = classes()
                .that()
                .resideInAPackage("..controllers..")
                .and()
                .haveSimpleNameNotEndingWith("Test")
                .should()
                .beAnnotatedWith(RequestMapping.class);

        rule.check(importedClasses);
    }

    @Test
    public void getSubscriptionByIdShouldHaveGetMappingAnnotation() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.adidas.subscription.controllers");
        ArchRule rule = methods()
                .that()
                .haveName("getSubscriptionById")
                .should()
                .beAnnotatedWith(GetMapping.class);

        rule.check(importedClasses);
    }

    @Test
    public void getAllSubscriptionsShouldHaveGetMappingAnnotation() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.adidas.subscription.controllers");
        ArchRule rule = methods()
                .that()
                .haveName("getAllSubscriptions")
                .should()
                .beAnnotatedWith(GetMapping.class);

        rule.check(importedClasses);
    }

    @Test
    public void createSubscriptionShouldHaveGetMappingAnnotation() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.adidas.subscription.controllers");
        ArchRule rule = methods()
                .that()
                .haveName("createSubscription")
                .should()
                .beAnnotatedWith(PostMapping.class);

        rule.check(importedClasses);
    }

    @Test
    public void deleteSubscriptionShouldHaveGetMappingAnnotation() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.adidas.subscription.controllers");
        ArchRule rule = methods()
                .that()
                .haveName("deleteSubscription")
                .should()
                .beAnnotatedWith(DeleteMapping.class);

        rule.check(importedClasses);
    }
}
