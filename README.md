# Spring Master class

[Source](https://github.com/landrzejewski/spring-masterclass)

## Jacoco
[Learn How to Use Jacoco Java code Coverage Effectively in 30 Minutes](https://www.techtravelhub.com/jacoco-java-code-coverag/)

[Reporting Code Coverage Using Maven and JaCoCo Plugin](https://dzone.com/articles/reporting-code-coverage-using-maven-and-jacoco-plu)

#### Examples of Jacoco output:

Run Jacoco:
```sh
mvn clean verify
```

Show report:
```sh
firefox target/site/jacoco/index.html
```

Skip Jacoco:
```sh
mvn clean verify -Djacoco.skip=true
```

Let Jacoco skip Lombok methods from code coverage:
```sh
echo "lombok.addLombokGeneratedAnnotation = true" > lombok.config
```
[Ignoring Lombok Code in Jacoco](https://www.rainerhahnekamp.com/en/ignoring-lombok-code-in-jacoco/)


Mvn ERROR output:
```sh
[INFO] --- jacoco-maven-plugin:0.8.3:check (coverage-check) @ Spring-Master ---
[INFO] Loading execution data file /home/christopher/java/Spring-Master/target/jacoco.exec
[INFO] Analyzed bundle 'Spring-Master' with 12 classes
[WARNING] Rule violated for class pl.training.shop.payments.PaymentConsoleLogger: lines covered ratio is 0.00, but expected minimum is 0.50
[WARNING] Rule violated for class pl.training.shop.payments.PaymentsConfiguration: lines covered ratio is 0.00, but expected minimum is 0.50
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  8.263 s
[INFO] Finished at: 2020-12-12T12:30:35+01:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.jacoco:jacoco-maven-plugin:0.8.3:check (coverage-check) on project Spring-Master: Coverage checks have not been met. See log for details. -> [Help 1]
```

Mvn SUCCESS output:
```sh
[INFO] --- jacoco-maven-plugin:0.8.3:report (coverage-report) @ Spring-Master ---
[INFO] Loading execution data file /home/christopher/java/Spring-Master/target/jacoco.exec
[INFO] Analyzed bundle 'Spring-Master' with 12 classes
[INFO] 
[INFO] --- jacoco-maven-plugin:0.8.3:check (coverage-check) @ Spring-Master ---
[INFO] Loading execution data file /home/christopher/java/Spring-Master/target/jacoco.exec
[INFO] Analyzed bundle 'Spring-Master' with 12 classes
[INFO] All coverage checks have been met.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  8.790 s
[INFO] Finished at: 2020-12-12T12:34:43+01:00
[INFO] ------------------------------------------------------------------------
```

Jacoco EXCLUSION exemplary configuration:
```sh
<configuration>
                            <rules>
                                <rule>
                                    <element>CLASS</element>
                                    <excludes>
                                        <exclude>pl.training.shop.Application</exclude>
                                        <exclude>pl.training.shop.payments.PaymentsConfiguration</exclude>
                                        <exclude>pl.training.shop.payments.PaymentConsoleLogger</exclude>
                                    </excludes>
                                    <limits>
                                        <limit>
                                            <counter>LINE</counter>
                                            <value>COVEREDRATIO</value>
                                            <minimum>50%</minimum>
                                        </limit>
                                    </limits>
                                </rule>
                            </rules>
<configuration>
```

Run maven without tests:
```sh
mvn clean install -Dmaven.test.skip=true
```

