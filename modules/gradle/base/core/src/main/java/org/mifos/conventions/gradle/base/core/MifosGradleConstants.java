/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifos.conventions.gradle.base.core;

import java.time.LocalDate;

public final class MifosGradleConstants {
    private MifosGradleConstants() {}

    public static final String MIFOS_BUILD_GRADLE = "build.gradle";

    public static final String MIFOS_BUILD_FOLDER_PATTERN = "**/build/**";

    public static final String MIFOS_PROJECT_MODULE_PREFIX = ":";

    public static final String MIFOS_PROJECT_MODULE_SEPARATOR = "-";

    public static final String MIFOS_EMPTY = "";

    public static final String MIFOS_PREFIX = "mifos.";

    public static final String MIFOS_EXTENSION = "mifos";
    public static final String MIFOS_PLUGIN_ID_PREFIX = "org.mifos.conventions.gradle";

    public static final String MIFOS_TASK_PREFIX = "mifos";
    public static final String MIFOS_TASK_GROUP = "mifos";

    public static final String MIFOS_PROJECTS_PROPERTY_PREFIX = MIFOS_PREFIX + "projects.";

    public static final String MIFOS_PROJECT_DEPENDENCY_NOTATION = "path";

    public static final String MIFOS_PROJECT_DEFAULT_MODULE_FOLDER = "modules";
    public static final String MIFOS_PROJECT_DEFAULT_CLASSIFIER = "imperative";
    public static final String MIFOS_PROJECT_DEFAULT_JDK_VERSION = "25"; // 21, 25, 26
    public static final String MIFOS_PROJECT_DEFAULT_SPRING_BOOT_VERSION = "41"; // 35, 41
    public static final String MIFOS_PROJECT_DEFAULT_ORGANISATION_ID = "openmf";
    public static final String MIFOS_PROJECT_DEFAULT_GROUP_ID = "org.mifos";
    public static final String MIFOS_PROJECT_DEFAULT_DESCRIPTION = "A Mifos Initiative Project";
    public static final String MIFOS_PROJECT_DEFAULT_BASE_PACKAGE = "org.mifos.uknown";
    public static final String MIFOS_PROJECT_DEFAULT_BASE_FOLDER = "org/mifos/uknown";
    public static final String MIFOS_PROJECT_DEFAULT_STORAGE = "relational";
    public static final String MIFOS_PROJECT_DEFAULT_INTEGRATION_FRAMEWORK = "camel";
    public static final String MIFOS_PROJECT_DEFAULT_LICENSE_NAME = "MPL-2.0";
    public static final String MIFOS_PROJECT_DEFAULT_LICENSE_URL = "https://spdx.org/licenses/MPL-2.0.html";
    public static final String MIFOS_PROJECT_DEFAULT_HOMEPAGE = "https://mifos.org";
    public static final String MIFOS_PROJECT_DEFAULT_BUG_TRACKER = "https://mifosforge.jira.com";
    public static final String MIFOS_PROJECT_DEFAULT_CONTACT = "https://mifos.org/about-us/contact-us";
    public static final String MIFOS_PROJECT_DEFAULT_INCEPTION_YEAR = LocalDate.now().getYear() + "";
    public static final String MIFOS_PROJECT_DEFAULT_VENDOR = "Mifos Initiative";
    public static final String MIFOS_PROJECT_DEFAULT_COPYRIGHT = "Copyright © %s Mifos Initiative".formatted(MIFOS_PROJECT_DEFAULT_INCEPTION_YEAR);
    public static final String MIFOS_PROJECT_DEFAULT_SCM_GITHUB_HTTP_URL = "https://github.com";
    public static final String MIFOS_PROJECT_DEFAULT_SCM_GITHUB_GIT_URL = "git://github.com";
    public static final String MIFOS_PROJECT_DEFAULT_CONTRIBUTE = MIFOS_PROJECT_DEFAULT_HOMEPAGE;
    public static final String MIFOS_PROJECT_DEFAULT_DOCUMENTATION = MIFOS_PROJECT_DEFAULT_HOMEPAGE;
    public static final String MIFOS_PROJECT_DEFAULT_DONATION = MIFOS_PROJECT_DEFAULT_HOMEPAGE;
    public static final String MIFOS_PROJECT_DEFAULT_FAQ = MIFOS_PROJECT_DEFAULT_HOMEPAGE;
    public static final String MIFOS_PROJECT_DEFAULT_HELP = MIFOS_PROJECT_DEFAULT_HOMEPAGE;
    public static final String MIFOS_PROJECT_DEFAULT_TRANSLATE = MIFOS_PROJECT_DEFAULT_HOMEPAGE;
    public static final String MIFOS_PROJECT_DEFAULT_TIMEZONE = "UTC";
    public static final String MIFOS_PROJECT_DEFAULT_ROLE = "developer";

    @Deprecated(forRemoval = true)
    public static final int MIFOS_PROJECT_MAX_SIZE = 100;

    public static final String GRADLE_CONFIGURATION_IMPLEMENTATION = "implementation";
    public static final String GRADLE_CONFIGURATION_API = "api";
    public static final String GRADLE_CONFIGURATION_COMPILE_ONLY = "compileOnly";
    public static final String GRADLE_CONFIGURATION_MIFOS_CONFIG = "mifosConfig";
}
