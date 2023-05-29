# Tests dev-bundle / production bundle

## Run the app without addons

Expected, use the default dev-bundle

2023-05-26T11:12:15.084+03:00  INFO 2801 --- [           main] c.v.f.s.frontend.BundleValidationUtil    : Checking if a development mode bundle build is needed
2023-05-26T11:12:15.189+03:00  INFO 2801 --- [           main] c.v.f.s.frontend.BundleValidationUtil    : A development mode bundle build is not needed

OK

## add an addon and a page to use it

```
<dependency>
   <groupId>org.vaadin.jchristophe</groupId>
   <artifactId>sortable-layout</artifactId>
   <version>1.1.8</version>
</dependency>
```

2023-05-26T11:21:01.169+03:00  INFO 2938 --- [           main] c.v.f.s.frontend.BundleValidationUtil    : Checking if a development mode bundle build is needed
2023-05-26T11:21:01.199+03:00  INFO 2938 --- [           main] c.v.f.s.frontend.BundleValidationUtil    : Dependency sortablejs is missing from the bundle
2023-05-26T11:21:01.200+03:00  INFO 2938 --- [           main] c.v.f.s.frontend.BundleValidationUtil    : A development mode bundle build is needed



## Clean the project and re-run the project

Without the nodes-modules and the target, the project will run with the pre-built dev-bundle

Run
mvn clean
mvn vaadin:clean-frontend

## Remove the dependency

Expected. The previous dev-bundle should be removed or rebuilt since it contains a non-needed Javascript

2023-05-26T12:05:25.026+03:00  INFO 3266 --- [  restartedMain] c.v.f.s.frontend.BundleValidationUtil    : Checking if a development mode bundle build is needed
2023-05-26T12:05:25.232+03:00  INFO 3266 --- [  restartedMain] c.v.f.s.frontend.BundleValidationUtil    : A development mode bundle build is not needed

The dev-bundle has not been rebuilt.

## Try another addon

```
        <dependency>
            <groupId>in.virit</groupId>
            <artifactId>viritin</artifactId>
            <version>2.0.1</version>
        </dependency>

```
New bundle created

## Clean the project and re-run the project

Without the nodes-modules and the target, the project will run with the pre-built dev-bundle

Run

```
mvn clean
mvn vaadin:clean-frontend

```
Caused by: java.lang.IllegalStateException:

Failed to find the following css files in the `node_modules` or `/Users/jean-christophe/Documents/vaadin-pit/hackathon-24-1/./frontend` directory tree:
- lumo-css-framework/all-classes.css
Check that they exist or are installed. If you use a custom directory for your resource files instead of the default `frontend` folder then make sure it's correctly configured (e.g. set 'vaadin.frontend.frontend.folder' property)

https://github.com/vaadin/flow/issues/16885

## Production frontend chunks

Add this addon

```
<dependency>
   <groupId>org.vaadin.jchristophe</groupId>
   <artifactId>sortable-layout</artifactId>
   <version>1.1.8</version>
</dependency>

```

Add the example in the page SortableView

Run the production build.

The javascript for the addon is not loaded in the homepage.
```
window.Vaadin.Flow.sortableConnector
undefined
```

Navigate to the http://localhost:8080/sortable.

The javascript for the addon is loaded.
```
window.Vaadin.Flow.sortableConnector
{initLazy: ƒ}
```