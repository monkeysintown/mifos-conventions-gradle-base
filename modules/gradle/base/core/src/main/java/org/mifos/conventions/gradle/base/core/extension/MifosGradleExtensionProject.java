/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifos.conventions.gradle.base.core.extension;

import org.gradle.api.Named;
import org.gradle.api.provider.Property;
import org.jspecify.annotations.NonNull;

import javax.inject.Inject;
import java.time.LocalDate;

import static java.time.ZoneOffset.UTC;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_BASE_FOLDER;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_BASE_PACKAGE;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_BUG_TRACKER;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_CLASSIFIER;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_CONTACT;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_COPYRIGHT;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_DESCRIPTION;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_DOCUMENTATION;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_DONATION;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_FAQ;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_GROUP_ID;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_HELP;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_HOMEPAGE;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_INTEGRATION_FRAMEWORK;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_JDK_VERSION;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_LICENSE_NAME;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_LICENSE_URL;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_ORGANISATION_ID;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_SPRING_BOOT_VERSION;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_STORAGE;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_VENDOR;

public abstract class MifosGradleExtensionProject implements Named {
    public static final String PROJECT_MAIN = "__main__";
    private final String name;

    @Inject
    public MifosGradleExtensionProject(String name) {
        this.name = name;
        getClassifier().set(MIFOS_PROJECT_DEFAULT_CLASSIFIER);
        getStorage().set(MIFOS_PROJECT_DEFAULT_STORAGE);
        getIntegrationFramework().set(MIFOS_PROJECT_DEFAULT_INTEGRATION_FRAMEWORK);
        getBasePackage().set(MIFOS_PROJECT_DEFAULT_BASE_PACKAGE);
        getBasePath().set(MIFOS_PROJECT_DEFAULT_BASE_FOLDER);
        getJdkVersion().set(MIFOS_PROJECT_DEFAULT_JDK_VERSION);
        getSpringBootVersion().set(MIFOS_PROJECT_DEFAULT_SPRING_BOOT_VERSION);
        getOrganisationId().set(MIFOS_PROJECT_DEFAULT_ORGANISATION_ID);
        getGroupId().set(MIFOS_PROJECT_DEFAULT_GROUP_ID);
        getDescription().set(MIFOS_PROJECT_DEFAULT_DESCRIPTION + (PROJECT_MAIN.equals(name) ? "" : ":" + name));
        getLicenseName().set(MIFOS_PROJECT_DEFAULT_LICENSE_NAME);
        getLicenseUrl().set(MIFOS_PROJECT_DEFAULT_LICENSE_URL);
        getHomepage().set(MIFOS_PROJECT_DEFAULT_HOMEPAGE);
        getBugTracker().set(MIFOS_PROJECT_DEFAULT_BUG_TRACKER);
        getContact().set(MIFOS_PROJECT_DEFAULT_CONTACT);
        getInceptionYear().set(LocalDate.now(UTC).getYear() + "");
        getVendor().set(MIFOS_PROJECT_DEFAULT_VENDOR);
        getCopyright().set(MIFOS_PROJECT_DEFAULT_COPYRIGHT);
        getDocumentation().set(MIFOS_PROJECT_DEFAULT_DOCUMENTATION);
        getDonation().set(MIFOS_PROJECT_DEFAULT_DONATION);
        getFaq().set(MIFOS_PROJECT_DEFAULT_FAQ);
        getHelp().set(MIFOS_PROJECT_DEFAULT_HELP);
    }

    public abstract Property<String> getClassifier();
    public abstract Property<String> getStorage();
    public abstract Property<String> getIntegrationFramework();
    public abstract Property<String> getBasePackage();
    public abstract Property<String> getBasePath();
    public abstract Property<String> getJdkVersion();
    public abstract Property<String> getSpringBootVersion();
    public abstract Property<String> getOrganisationId();
    public abstract Property<String> getGroupId();
    public abstract Property<String> getDescription();
    public abstract Property<String> getLicenseName();
    public abstract Property<String> getLicenseUrl();
    public abstract Property<String> getHomepage();
    public abstract Property<String> getBugTracker();
    public abstract Property<String> getContact();
    public abstract Property<String> getInceptionYear();
    public abstract Property<String> getVendor();
    public abstract Property<String> getCopyright();
    public abstract Property<String> getScmUrl();
    public abstract Property<String> getScmConnectionMain();
    public abstract Property<String> getScmConnectionDeveloper();
    public abstract Property<String> getContribute();
    public abstract Property<String> getDocumentation();
    public abstract Property<String> getDonation();
    public abstract Property<String> getFaq();
    public abstract Property<String> getHelp();
    public abstract Property<String> getTranslate();

    @Override
    public @NonNull String getName() {
        return name;
    }
}
