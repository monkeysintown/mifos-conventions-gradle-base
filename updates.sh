#!/bin/sh

mkdir -p build/updates

./gradlew :mifos-conventions-gradle-base-bom-ai:dependencyUpdates --no-parallel > build/updates/ai.txt
./gradlew :mifos-conventions-gradle-base-bom-amazon:dependencyUpdates --no-parallel > build/updates/amazon.txt
./gradlew :mifos-conventions-gradle-base-bom-net:dependencyUpdates --no-parallel > build/updates/net.txt
./gradlew :mifos-conventions-gradle-base-bom-spring:dependencyUpdates --no-parallel > build/updates/spring.txt
./gradlew :mifos-conventions-gradle-base-bom-geo:dependencyUpdates --no-parallel > build/updates/geo.txt
./gradlew :mifos-conventions-gradle-base-bom-xml:dependencyUpdates --no-parallel > build/updates/xml.txt
./gradlew :mifos-conventions-gradle-base-bom-workflow:dependencyUpdates --no-parallel > build/updates/workflow.txt
./gradlew :mifos-conventions-gradle-base-bom-data:dependencyUpdates --no-parallel > build/updates/data.txt
./gradlew :mifos-conventions-gradle-base-bom-cli:dependencyUpdates --no-parallel > build/updates/cli.txt
./gradlew :mifos-conventions-gradle-base-bom-auth:dependencyUpdates --no-parallel > build/updates/auth.txt
./gradlew :mifos-conventions-gradle-base-bom-lock:dependencyUpdates --no-parallel > build/updates/lock.txt
./gradlew :mifos-conventions-gradle-base-bom-commons:dependencyUpdates --no-parallel > build/updates/commons.txt
./gradlew :mifos-conventions-gradle-base-bom-cloudevents:dependencyUpdates --no-parallel > build/updates/cloudevents.txt
./gradlew :mifos-conventions-gradle-base-bom-performance:dependencyUpdates --no-parallel > build/updates/performance.txt
./gradlew :mifos-conventions-gradle-base-bom-observability:dependencyUpdates --no-parallel > build/updates/observability.txt
./gradlew :mifos-conventions-gradle-base-bom-sdk:dependencyUpdates --no-parallel > build/updates/sdk.txt
./gradlew :mifos-conventions-gradle-base-bom-logging:dependencyUpdates --no-parallel > build/updates/logging.txt
./gradlew :mifos-conventions-gradle-base-bom-jakarta:dependencyUpdates --no-parallel > build/updates/jakarta.txt
./gradlew :mifos-conventions-gradle-base-bom-cryptography:dependencyUpdates --no-parallel > build/updates/cryptography.txt
./gradlew :mifos-conventions-gradle-base-bom-automation:dependencyUpdates --no-parallel > build/updates/automation.txt
./gradlew :mifos-conventions-gradle-base-bom-check:dependencyUpdates --no-parallel > build/updates/check.txt
./gradlew :mifos-conventions-gradle-base-bom-kubernetes:dependencyUpdates --no-parallel > build/updates/kubernetes.txt
./gradlew :mifos-conventions-gradle-base-bom-measure:dependencyUpdates --no-parallel > build/updates/measure.txt
./gradlew :mifos-conventions-gradle-base-bom-format:dependencyUpdates --no-parallel > build/updates/format.txt
./gradlew :mifos-conventions-gradle-base-bom-collections:dependencyUpdates --no-parallel > build/updates/collections.txt
./gradlew :mifos-conventions-gradle-base-bom-template:dependencyUpdates --no-parallel > build/updates/template.txt
./gradlew :mifos-conventions-gradle-base-bom-google:dependencyUpdates --no-parallel > build/updates/google.txt
./gradlew :mifos-conventions-gradle-base-bom-cache:dependencyUpdates --no-parallel > build/updates/cache.txt
./gradlew :mifos-conventions-gradle-base-bom-util:dependencyUpdates --no-parallel > build/updates/util.txt
./gradlew :mifos-conventions-gradle-base-bom-math:dependencyUpdates --no-parallel > build/updates/math.txt
./gradlew :mifos-conventions-gradle-base-bom-manifold:dependencyUpdates --no-parallel > build/updates/manifold.txt
./gradlew :mifos-conventions-gradle-base-bom-web:dependencyUpdates --no-parallel > build/updates/web.txt
./gradlew :mifos-conventions-gradle-base-bom-test:dependencyUpdates --no-parallel > build/updates/test.txt
./gradlew :mifos-conventions-gradle-base-bom-finance:dependencyUpdates --no-parallel > build/updates/finance.txt
./gradlew :mifos-conventions-gradle-base-bom-camel:dependencyUpdates --no-parallel > build/updates/camel.txt
./gradlew :mifos-conventions-gradle-base-bom-validation:dependencyUpdates --no-parallel > build/updates/validation.txt
./gradlew :mifos-conventions-gradle-base-bom-mapping:dependencyUpdates --no-parallel > build/updates/mapping.txt
./gradlew :mifos-conventions-gradle-base-bom-i18n:dependencyUpdates --no-parallel > build/updates/i18n.txt
./gradlew :mifos-conventions-gradle-base-bom-vaadin:dependencyUpdates --no-parallel > build/updates/vaadin.txt
./gradlew :mifos-conventions-gradle-base-bom-cep:dependencyUpdates --no-parallel > build/updates/cep.txt
./gradlew :mifos-conventions-gradle-base-bom-graphic:dependencyUpdates --no-parallel > build/updates/graphic.txt
./gradlew :mifos-conventions-gradle-base-bom-json:dependencyUpdates --no-parallel > build/updates/json.txt
./gradlew :mifos-conventions-gradle-base-bom-reporting:dependencyUpdates --no-parallel > build/updates/reporting.txt